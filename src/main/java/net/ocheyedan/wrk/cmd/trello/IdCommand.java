package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Json;
import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Command;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 3:03 PM
 *
 * Collects wrk ids and saves them for subsequent use by other commands.
 */
abstract class IdCommand extends Command {

    static final class TrelloId {

        final String id;

        final String idWithTypePrefix;

        TrelloId(String id, String idWithTypePrefix) {
            this.id = id;
            this.idWithTypePrefix = idWithTypePrefix;
        }
    }

    @SuppressWarnings("serial")
    protected static final Set<String> orgPrefix = new HashSet<String>(1) { { add("o:"); } };
    @SuppressWarnings("serial")
    protected static final Set<String> boardsPrefix = new HashSet<String>(1) { { add("b:"); } };
    @SuppressWarnings("serial")
    protected static final Set<String> cardsPrefix = new HashSet<String>(1) { { add("c:"); } };
    @SuppressWarnings("serial")
    protected static final Set<String> allPrefix = new HashSet<String>(1) { { add("o:"); add("b:"); add("c:"); } };

    private final File wrkIdsFile;

    private final LinkedList<Map<String, String>> existingWrkIds;

    private final Map<String, String> existingHead;

    protected IdCommand(Args args) {
        super(args);
        LinkedList<Map<String, String>> existing = new LinkedList<Map<String, String>>();
        wrkIdsFile = new File(String.format("%s%s%s%s%s", System.getProperty("user.home"), File.separator, ".wrk", File.separator, "wrk-ids"));
        try {
            if (wrkIdsFile.exists()) {
                existing = Json.mapper().readValue(wrkIdsFile, new TypeReference<LinkedList<Map<String, String>>>() { });
            }
        } catch (FileNotFoundException fnfe) {
            // ignore
        } catch (IOException ioe) {
            Output.print(ioe);
        }
        this.existingWrkIds = existing;
        Map<String, String> existingHead = existingWrkIds.peek();
        existingHead = (existingHead == null ? Collections.<String, String>emptyMap() : existingHead);
        this.existingHead = existingHead;
    }

    @Override public final void run() {
        Map<String, String> wrkids = _run();
        if (wrkids.isEmpty()) {
            return; // don't push an empty
        }
        try {
            if (existingWrkIds.size() >= 50) {
                existingWrkIds.removeLast();
            }
            existingWrkIds.push(wrkids);
            Json.mapper().writeValue(wrkIdsFile, existingWrkIds);
        } catch (IOException ioe) {
            Output.print(ioe);
        }
    }

    protected void pop(int times) {
        for (int i = 0; i < times; i++) {
            if (!this.existingWrkIds.isEmpty()) {
                this.existingWrkIds.pop();
            } else {
                break;
            }
        }
        try {
            Json.mapper().writeValue(wrkIdsFile, existingWrkIds);
        } catch (IOException ioe) {
            Output.print(ioe);
        }
    }

    protected TrelloId parseWrkId(String wrkId, Set<String> desiredPrefixes) {
        String trelloId = existingHead.get(wrkId);
        if (trelloId == null) {
            return new TrelloId(wrkId, wrkId); // user entered a trello-id directly
        }
        String existingPrefix = (trelloId.length() > 2 ? trelloId.substring(0, 2) : "");
        if (!desiredPrefixes.contains(existingPrefix)) {
            // given a wrk-id for the wrong prefix; alert and exit.
            String type = prettyPrint(existingPrefix);
            Output.print("The wrk-id [ ^b^%s^r^ ] is for ^red^%s^r^ but the command is for %s.", wrkId, type, prefixesToString(desiredPrefixes));
            System.exit(1);
        }
        return new TrelloId(trelloId.substring(2), trelloId);
    }

    private String prettyPrint(String prefix) {
        if ("b:".equals(prefix)) {
            return "boards";
        } else if ("c:".equals(prefix)) {
            return "cards";
        } else if ("o:".equals(prefix)) {
            return "orgs";
        } else {
            return "<unknown>";
        }
    }

    private String prefixesToString(Set<String> prefixes) {
        StringBuilder buffer = new StringBuilder("[ ");
        boolean first = true;
        for (String prefix : prefixes) {
            if (!first) {
                buffer.append(", ");
            }
            buffer.append("^b^");
            buffer.append(prettyPrint(prefix));
            buffer.append("^r^");
            first = false;
        }
        buffer.append(" ]");
        return buffer.toString();
    }

    protected abstract Map<String, String> _run();
}
