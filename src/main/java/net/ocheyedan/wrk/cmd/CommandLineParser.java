package net.ocheyedan.wrk.cmd;

import net.ocheyedan.wrk.cmd.trello.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 3:58 PM
 *
 * Responsible for parsing the command line arguments given to wrk and creating a Command object.
 */
public final class CommandLineParser {

    /**
     * A singleton, empty {@link Args} object.
     */
    final static Args NIL = new Args(Collections.<String>emptyList());

    /**
     * @param args to parse
     * @return a {@link Command} parsed from {@code args}.
     */
    public static Command parse(String[] args) {
        if ((args == null) || (args.length < 1)) {
            return new Cards(parse(args, 0));
        } else if ("--usage".equals(args[0]) || "--help".equals(args[0])) {
            return new Usage(parse(args, 1));
        } else if ("--version".equals(args[0]) || "-v".equals(args[0]) || "-version".equals(args[0])) {
            return new Version(parse(args, 1));
        } else if ("orgs".equals(args[0])) {
            return new Orgs(parse(args, 1));
        } else if ("boards".equals(args[0])) {
            return new Boards(parse(args, 1));
        } else if ("cards".equals(args[0])) {
            return new Cards(parse(args, 1));
        } else if ("comments".equals(args[0])) {
            return new Comments(parse(args, 1));
        } else if ("members".equals(args[0])) {
            return new Members(parse(args, 1));
        } else if ("assign".equals(args[0])) {
            return new Assign(parse(args, 1));
        } else if ("unassign".equals(args[0])) {
            return new UnAssign(parse(args, 1));
        } else if ("close".equals(args[0])) {
            return new Close(parse(args, 1));
        } else if ("pop".equals(args[0])) {
            return new Pop(parse(args, 1));
        } else {
            return new Usage(parse(args, 0));
        }
    }

    public static Args parse(String[] args, int strip) {
        int length = (args.length > strip) ? args.length - strip : 0;
        List<String> parsed = new ArrayList<String>();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                parsed.add(args[i + strip]);
            }
        }
        return new Args(parsed);
    }

}
