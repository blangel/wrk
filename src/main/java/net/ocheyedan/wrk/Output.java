package net.ocheyedan.wrk;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 4:07 PM
 *
 * Defines the output mechanism within the {@literal Wrk} application.
 * Support for colored VT-100 terminal output is controlled by the property {@literal color}.
 */
public final class Output {

    /**
     * Used to convert a {@link Throwable}'s stacktrace to a {@link String}.
     */
    private static final class StackTraceWriter extends Writer {

        /**
         * Convenience method to convert the {@code t}'s stack-trace into a string
         * @param t to convert its stack-trace into a string
         * @param appendOutputError true if the {@literal ^error^} prefix should be appended to each new line of the stack-trace
         * @return the stack-trace of {@code t} as a string
         */
        static String convertStackTrace(Throwable t, boolean appendOutputError) {
            StackTraceWriter writer = new StackTraceWriter(appendOutputError);
            t.printStackTrace(new PrintWriter(writer));
            return writer.toString();
        }

        /**
         * If true, the {@literal ^error^} prefix will be append to each new line of the stack-trace.
         */
        private final boolean appendOutputError;

        /**
         * Using thread-safe {@link StringBuffer} as character buffer.
         */
        private final StringBuffer buffer = new StringBuffer();

        private StackTraceWriter(boolean appendOutputError) {
            this.appendOutputError = appendOutputError;
            if (this.appendOutputError) {
                buffer.append("^error^ ");
            }
        }

        @Override public void write(char[] cbuf, int off, int len) throws IOException {
            if (appendOutputError) {
                for (int i = off; i < (off + len); i++) {
                    char character = cbuf[i];
                    buffer.append(character);
                    if (character == '\n') {
                        buffer.append("^error^ ");
                    }
                }
            } else {
                buffer.append(cbuf, off, len);
            }
        }

        /**
         * Nothing to do for this implementation
         * @throws IOException
         */
        @Override public void flush() throws IOException { }
        /**
         * Nothing to do for this implementation.
         * @throws IOException
         */
        @Override public void close() throws IOException { }

        @Override public String toString() {
            return buffer.toString();
        }
    }

    /**
     * A regex {@link java.util.regex.Pattern} paired with its corresponding output string.
     */
    private static final class TermCode {
        private final Pattern pattern;
        private final String output;
        private final String nonColoredOutput;
        private TermCode(Pattern pattern, String output, String nonColoredOutput) {
            this.pattern = pattern;
            this.output = output;
            this.nonColoredOutput = nonColoredOutput;
        }
    }

    /**
     * If true, color will be allowed within output.
     */
    private static final AtomicBoolean coloredOutput = new AtomicBoolean(true);
    /**
     * True if environment variable TERM is set to a non-null value.
     */
    private static final AtomicBoolean withinTerminal = new AtomicBoolean(true);
    /**
     * True if the script which invoked wrk is being piped (i.e., non-tty).
     */
    private static final AtomicBoolean beingPiped = new AtomicBoolean(false);

    /**
     * A mapping of easily identifiable words to a {@link TermCode} object for colored output.
     */
    private static final Map<String, TermCode> TERM_CODES = new HashMap<String, TermCode>();

    static void init(boolean coloredOutput) {
        String terminal = System.getenv("TERM");
        withinTerminal.set(terminal != null);
        String piped = System.getProperty("wrk.piped");
        beingPiped.set((piped != null) && "true".equalsIgnoreCase(piped));
        boolean useColor = withinTerminal.get() && coloredOutput && !beingPiped.get();
        Output.coloredOutput.set(useColor);
        // TODO - what are the range of terminal values and what looks best for each?
        TERM_CODES.put("error", new TermCode(Pattern.compile("\\^error\\^"), "[\u001b[1;31merr!\u001b[0m]", "[err!]"));
        TERM_CODES.put("warn", new TermCode(Pattern.compile("\\^warn\\^"), "[\u001b[1;33mwarn\u001b[0m]", "[warn]"));
        TERM_CODES.put("info", new TermCode(Pattern.compile("\\^info\\^"), "[\u001b[1;34minfo\u001b[0m]", "[info]"));
        TERM_CODES.put("dbug", new TermCode(Pattern.compile("\\^dbug\\^"), "[\u001b[1;30mdbug\u001b[0m]", "[dbug]"));
        TERM_CODES.put("reset", new TermCode(Pattern.compile("\\^r\\^"), "\u001b[0m", ""));
        TERM_CODES.put("bold", new TermCode(Pattern.compile("\\^b\\^"), "\u001b[1m", ""));
        TERM_CODES.put("normal", new TermCode(Pattern.compile("\\^n\\^"), "\u001b[2m", ""));
        TERM_CODES.put("inverse", new TermCode(Pattern.compile("\\^i\\^"), "\u001b[7m", ""));
        TERM_CODES.put("black", new TermCode(Pattern.compile("\\^black\\^"), "\u001b[1;30m", ""));
        TERM_CODES.put("red", new TermCode(Pattern.compile("\\^red\\^"),  "\u001b[1;31m", ""));
        TERM_CODES.put("green", new TermCode(Pattern.compile("\\^green\\^"), "\u001b[1;32m", ""));
        TERM_CODES.put("yellow", new TermCode(Pattern.compile("\\^yellow\\^"), "\u001b[1;33m", ""));
        TERM_CODES.put("orange", new TermCode(Pattern.compile("\\^orange\\^"), "\u001b[0;33m", ""));
        TERM_CODES.put("blue", new TermCode(Pattern.compile("\\^blue\\^"), "\u001b[1;34m", ""));
        TERM_CODES.put("magenta", new TermCode(Pattern.compile("\\^magenta\\^"), "\u001b[1;35m", ""));
        TERM_CODES.put("cyan", new TermCode(Pattern.compile("\\^cyan\\^"), "\u001b[1;36m", ""));
        TERM_CODES.put("white", new TermCode(Pattern.compile("\\^white\\^"), "\u001b[0;37m", ""));
    }

    public static void print(String message, Object ... args) {
        String formatted = resolve(message, args);
        if (formatted == null) {
            return;
        }
        System.out.println(formatted);
    }

    public static void print(Throwable t) {
        print("^error^ Message: ^i^^red^%s^r^", (t == null ? "" : t.getMessage()));
        print(StackTraceWriter.convertStackTrace(t, true));
    }

    static String resolve(String message, Object[] args) {
        String formatted = String.format(message, args);
        // TODO - fix!  this case fails: ^cyan^warn^r^ if ^warn^ is evaluated first...really meant for ^cyan^ and ^r^
        // TODO - to be resolved
        for (String key : TERM_CODES.keySet()) {
            TermCode termCode = TERM_CODES.get(key);
            Matcher matcher = termCode.pattern.matcher(formatted);
            if (matcher.find()) {
                String output = isColoredOutput() ? termCode.output : termCode.nonColoredOutput;
                formatted = matcher.replaceAll(output);
            }
        }
        return formatted;
    }

    /**
     * @return true if the client can support colored output.
     */
    public static boolean isColoredOutput() {
        return coloredOutput.get();
    }

    private Output() { }
}
