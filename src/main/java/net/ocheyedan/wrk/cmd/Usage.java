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
        Output.print("        lists the open boards for ^b^org-id^r^ which is the Trello or wrk id of an organization.");
        Output.print("    ^b^cards^r^");
        Output.print("        lists the open cards assigned to the user");
        Output.print("    ^b^cards in <board-id>^r^");
        Output.print("        lists the open cards for ^b^board-id^r^ which is the Trello or wrk id of a board.");
        Output.print("    ^b^comments in <card-id>^r^");
        Output.print("        lists the comments for ^b^card-id^r^ which is the Trello or wrk id of a card.");
        Output.print("    ^b^members in o:<org-id>|b:<board-id>|c:<card-id>^r^");
        Output.print("        lists the members in ^b^org-id^r^|^b^board-id^r^|^b^card-id^r^ which is the Trello or wrk id of a organization|board|card.");
        Output.print("        Note, if using a wrk id the prefix is not necessary as wrk ids know their type.");
        Output.print("    ^b^desc o:<org-id>|b:<board-id>|c:<card-id>|m:<member-id>^r^");
        Output.print("        describe the value of ^b^org-id^r^|^b^board-id^r^|^b^card-id^r^|^b^member-id^r^ which is the Trello or wrk id of a organization|board|card|member.");
        Output.print("        Note, if using a wrk id the prefix is not necessary as wrk ids know their type.");
        Output.print("    ^b^comment on <card-id> [message]^r^");
        Output.print("        adds ^b^message^r^ as a comment on ^b^card-id^r^ which is the Trello or wrk id of a card.");
        Output.print("        Note, if ^b^message^r^ is not provided the ^b^editor^r^ (defined in ^b^~/.wrk/config^r^) will be opened.");
        Output.print("    ^b^assign <card-id>^r^");
        Output.print("        assigns the user to the ^b^card-id^r^ which is the Trello or wrk id of a card.");
        Output.print("    ^b^assign <member-id> to <card-id>^r^");
        Output.print("        assigns the ^b^member-id^r^ to the ^b^card-id^r^ which are the Trello or wrk ids of a member/card.");
        Output.print("    ^b^unassign <card-id>^r^");
        Output.print("        un-assigns the user from the ^b^card-id^r^ which is the Trello or wrk id of a card.");
        Output.print("    ^b^unassign <member-id> from <card-id>^r^");
        Output.print("        un-assigns the ^b^member-id^r^ from the ^b^card-id^r^ which are the Trello or wrk ids of a member/card.");
        Output.print("    ^b^close b:<board-id>|c:<card-id>^r^");
        Output.print("        closes the ^b^board-id^r^|^b^card-id^r^ which is the Trello or wrk id of a board|card.");
        Output.print("        Note, if using a wrk id the prefix is not necessary as wrk ids know their type.");
        Output.print("    ^b^pop [num|all]^r^");
        Output.print("        pops 1 (or ^b^num^r^ or ^b^all^r^) of the queued wrk id results.");

    }
}
