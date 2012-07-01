package net.ocheyedan.wrk.cmd;

import net.ocheyedan.wrk.Output;

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
        if (args.args.size() == 1) {
            String subcommand = args.args.get(0);
            subcommand: {
                if ("orgs".equals(subcommand)) {
                    Output.print("^b^orgs^r^");
                    Output.print("    lists the user's organizations");
                    Output.print("");
                    Output.print("The listed organizations will be assigned wrk ids and placed in the wrk id results queue.");
                    Output.print("Wrk ids are short versions of Trello ids prefixed with ^b^wrk^r^.");
                } else if ("boards".equals(subcommand)) {
                    Output.print("^b^boards^r^");
                    Output.print("    lists the user's open boards");
                    Output.print("^b^boards in <org-id>^r^");
                    Output.print("    lists the open boards for ^b^org-id^r^ which is the Trello or wrk id of an organization.");
                    Output.print("");
                    Output.print("The listed boards will be assigned wrk ids and placed in the wrk id results queue.");
                    Output.print("Wrk ids are short versions of Trello ids prefixed with ^b^wrk^r^.");
                } else if ("lists".equals(subcommand)) {
                    Output.print("^b^lists in <board-id>^r^");
                    Output.print("    lists the open lists for ^b^board-id^r^ which is the Trello or wrk id of a board.");
                    Output.print("");
                    Output.print("The listed lists will be assigned wrk ids and placed in the wrk id results queue.");
                    Output.print("Wrk ids are short versions of Trello ids prefixed with ^b^wrk^r^.");
                } else if ("cards".equals(subcommand)) {
                    Output.print("^b^cards^r^");
                    Output.print("    lists the open cards assigned to the user");
                    Output.print("^b^cards in b:<board-id>|l:<list-id>^r^");
                    Output.print("    lists the open cards for ^b^board-id^r^|^b^list-id^r^ which is the Trello or wrk id of a board|list.");
                    Output.print("    Note, if using a wrk id the prefix is not necessary as wrk ids know their type.");
                    Output.print("");
                    Output.print("The listed cards will be assigned wrk ids and placed in the wrk id results queue.");
                    Output.print("Wrk ids are short versions of Trello ids prefixed with ^b^wrk^r^.");
                } else if ("comments".equals(subcommand)) {
                    Output.print("^b^comments in <card-id>^r^");
                    Output.print("    lists the comments for ^b^card-id^r^ which is the Trello or wrk id of a card.");
                } else if ("members".equals(subcommand)) {
                    Output.print("^b^members in o:<org-id>|b:<board-id>|c:<card-id>^r^");
                    Output.print("    lists the members in ^b^org-id^r^|^b^board-id^r^|^b^card-id^r^ which is the Trello or wrk id of a organization|board|card.");
                    Output.print("    Note, if using a wrk id the prefix is not necessary as wrk ids know their type.");
                    Output.print("");
                    Output.print("The listed members will be assigned wrk ids and placed in the wrk id results queue.");
                    Output.print("Wrk ids are short versions of Trello ids prefixed with ^b^wrk^r^.");
                } else if ("desc".equals(subcommand)) {
                    Output.print("^b^desc o:<org-id>|b:<board-id>|l:<list-id>|c:<card-id>|m:<member-id>^r^");
                    Output.print("    describe the value of ^b^org-id^r^|^b^board-id^r^|^b^list-id^r^|^b^card-id^r^|^b^member-id^r^ which is the Trello or wrk id of a organization|board|list|card|member.");
                    Output.print("    Note, if using a wrk id the prefix is not necessary as wrk ids know their type.");
                } else if ("comment".equals(subcommand)) {
                    Output.print("^b^comment on <card-id> [message]^r^");
                    Output.print("    adds ^b^message^r^ as a comment on ^b^card-id^r^ which is the Trello or wrk id of a card.");
                    Output.print("    Note, if ^b^message^r^ is not provided the ^b^editor^r^ (defined in ^b^~/.wrk/config^r^) will be opened.");
                } else if ("assign".equals(subcommand)) {
                    Output.print("^b^assign <card-id>^r^");
                    Output.print("    assigns the user to the ^b^card-id^r^ which is the Trello or wrk id of a card.");
                    Output.print("^b^assign <member-id> to <card-id>^r^");
                    Output.print("    assigns the ^b^member-id^r^ to the ^b^card-id^r^ which are the Trello or wrk ids of a member/card.");
                } else if ("unassign".equals(subcommand)) {
                    Output.print("^b^unassign <card-id>^r^");
                    Output.print("    un-assigns the user from the ^b^card-id^r^ which is the Trello or wrk id of a card.");
                    Output.print("^b^unassign <member-id> from <card-id>^r^");
                    Output.print("    un-assigns the ^b^member-id^r^ from the ^b^card-id^r^ which are the Trello or wrk ids of a member/card.");
                } else if ("move".equals(subcommand)) {
                    Output.print("^b^move <card-id> to <list-id>");
                    Output.print("    moves the ^b^card-id^r^ to the given ^b^list-id^r^ which are the Trello or wrk ids of a card/list.");
                } else if ("close".equals(subcommand)) {
                    Output.print("^b^close b:<board-id>|l:<list-id>|c:<card-id>^r^");
                    Output.print("    closes the ^b^board-id^r^|^b^list-id^r^|^b^card-id^r^ which is the Trello or wrk id of a board|list|card.");
                    Output.print("    Note, if using a wrk id the prefix is not necessary as wrk ids know their type.");
                } else if ("pop".equals(subcommand)) {
                    Output.print("^b^pop [num|all]^r^");
                    Output.print("    pops 1 (or ^b^num^r^ or ^b^all^r^) of the queued wrk id results.");
                    Output.print("");
                    Output.print("Wrk id results are created and pushed onto the queue by commands which list Trello ids.");
                    Output.print("Wrk ids are short versions of Trello ids prefixed with ^b^wrk^r^.");
                } else {
                    break subcommand; // sub-command given was not found, print general usage
                }
                return;
            }
        }
        Output.print("wrk [--usage|--help|help] [--version|-v|-version] <command> [<args>]");
        Output.print("");
        Output.print("  where ^b^command^r^ is either:");
        Output.print("    ^b^orgs^r^      Lists organizations.");
        Output.print("    ^b^boards^r^    Lists boards (all created by user or those for an organization).");
        Output.print("    ^b^lists^r^     Lists lists for a particular board.");
        Output.print("    ^b^cards^r^     Lists cards (all open assigned to user, or those within a board or list).");
        Output.print("    ^b^comments^r^  Lists comments for a particular card.");
        Output.print("    ^b^members^r^   Lists members belonging to an organization, board or card.");
        Output.print("    ^b^desc^r^      Prints a description of an organization, board, list, card or member.");
        Output.print("    ^b^comment^r^   Creates a comment on a particular card.");
        Output.print("    ^b^assign^r^    Assigns the user or a member to a particular card.");
        Output.print("    ^b^unassign^r^  Un-assigns the user or a member from a particular card.");
        Output.print("    ^b^move^r^      Moves a list/card to a board/list.");
        Output.print("    ^b^close^r^     Closes a board/list/card.");
        Output.print("    ^b^pop^r^       Pops wrk ids from the queue of wrk id results.");
        Output.print("");
        Output.print("See 'wrk help <command>' for more information on a specific command.");
    }
}
