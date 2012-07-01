package net.ocheyedan.wrk.cmd;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.trello.TrelloUtil;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 4:05 PM
 *
 * A {@link Command} to print the usage information.
 */
public final class Usage extends Command {

    public Usage(Args args) {
        super(args);
    }

    @Override public void run() {
        Output.print("wrk [--usage|--version|^b^command^r^]");
        Output.print("  where ^b^command^r^ is either:");
        Output.print("    ^b^orgs^r^");
        Output.print("        lists the user's organizations");
        Output.print("    ^b^boards^r^");
        Output.print("        lists the user's open boards");
        Output.print("    ^b^boards in <org-id>^r^");
        Output.print("        lists the open boards for ^b^org-id^r^ which is either the Trello or wrk id of an organization.");
        Output.print("    ^b^cards^r^");
        Output.print("        lists the open cards assigned to the user");
        Output.print("    ^b^cards in <board-id>^r^");
        Output.print("        lists the open cards for ^b^board-id^r^ which is either the Trello or wrk id of a board.");
        Output.print("    ^b^comments in <card-id>^r^");
        Output.print("        lists the comments for ^b^card-id^r^ which is either the Trello or wrk id of a card.");
        Output.print("    ^b^members in o:<org-id>|b:<board-id>|c:<card-id>^r^");
        Output.print("        lists the members in ^b^org-id^r^|^b^board-id^r^|^b^card-id^r^ which is either the Trello or wrk id of a organization|board|card.");
        Output.print("    ^b^on <card-id>^r^");
        Output.print("        assigns the user to the ^b^card-id^r^ which is either the Trello or wrk id of a card.");
        Output.print("    ^b^quit <card-id>^r^");
        Output.print("        un-assigns the user from the ^b^card-id^r^ which is either the Trello or wrk id of a card.");
        Output.print("    ^b^close b:<board-id>|c:<card-id>^r^");
        Output.print("        closes the ^b^board-id^r^|^b^card-id^r^ which is either the Trello or wrk id of a board|card.");
        Output.print("    ^b^pop [num|all]^r^");
        Output.print("        pops 1 (or ^b^num^r^ or ^b^all^r^) of the queued wrk id results.");

    }
}
