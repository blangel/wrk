package net.ocheyedan.wrk.cmd;

import java.util.List;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 4:02 PM
 *
 * Represents the command line arguments passed to wrk.
 */
public final class Args {

    public final List<String> args;

    public Args(List<String> args) {
        this.args = args;
    }

    @Override public String toString() {
        if ((args == null) || args.isEmpty()) {
            return "<none>";
        }
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        for (String arg : args) {
            if (!first) {
                buffer.append(' ');
            }
            buffer.append(arg);
            first = false;
        }
        return buffer.toString();
    }
}
