package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Command;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Board;
import net.ocheyedan.wrk.trello.TrelloUtil;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;
import java.util.Map;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 9:24 PM
 */
public final class Boards extends Command {

    private final String description;

    private final String url;

    public Boards(Args args) {
        super(args);
        if ((args.args.size() == 2) && "in".equals(args.args.get(0))) {
            String orgId = args.args.get(1); // TODO - parse for wrk-id
            url = TrelloUtil.url("https://trello.com/1/organization/%s/boards?filter=open&key=%s&token=%s", orgId, TrelloUtil.APP_DEV_KEY, TrelloUtil.USR_TOKEN);
            description = String.format("Open boards for organization ^b^%s^r^:", orgId);
        } else if (args.args.isEmpty()) {
            url = TrelloUtil.url("https://trello.com/1/members/my/boards?filter=open&key=%s&token=%s", TrelloUtil.APP_DEV_KEY, TrelloUtil.USR_TOKEN);
            description = "Open boards you've created:";
        } else {
            url = description = null;
        }
    }

    @Override public void run() {
        if (url == null) {
            new Usage(args).run();
            return;
        }
        Output.print(description);
        List<Board> boards = RestTemplate.invokeRest(url, new TypeReference<List<Board>>() { });
        if ((boards == null) || boards.isEmpty()) {
            Output.print("  ^black^None^r^");
            return;
        }
        for (Board board : boards) {
            Output.print("  ^b^%s^r^", board.getName());
            Output.print("    ^black^%s^r^", board.getUrl());
        }
    }
}
