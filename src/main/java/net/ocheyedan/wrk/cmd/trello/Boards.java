package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Command;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Board;
import net.ocheyedan.wrk.trello.TrelloUtil;
import org.codehaus.jackson.type.TypeReference;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 9:24 PM
 */
public final class Boards extends IdCommand {

    private final String description;

    private final String url;

    public Boards(Args args) {
        super(args);
        if ((args.args.size() == 2) && "in".equals(args.args.get(0))) {
            TrelloId orgId = parseWrkId(args.args.get(1), orgPrefix);
            url = TrelloUtil.url("https://trello.com/1/organization/%s/boards?filter=open&key=%s&token=%s", orgId.id,
                    TrelloUtil.APP_DEV_KEY, TrelloUtil.USR_TOKEN);
            description = String.format("Open boards for organization ^b^%s^r^:", orgId.id);
        } else if (args.args.isEmpty()) {
            url = TrelloUtil.url("https://trello.com/1/members/my/boards?filter=open&key=%s&token=%s",
                    TrelloUtil.APP_DEV_KEY, TrelloUtil.USR_TOKEN);
            description = "Open boards you've created:";
        } else {
            url = description = null;
        }
    }

    @Override protected Map<String, String> _run() {
        if (url == null) {
            new Usage(args).run();
            return Collections.emptyMap();
        }
        Output.print(description);
        List<Board> boards = RestTemplate.get(url, new TypeReference<List<Board>>() {
        });
        if ((boards == null) || boards.isEmpty()) {
            Output.print("  ^black^None^r^");
            return Collections.emptyMap();
        }
        Map<String, String> wrkIds = new HashMap<String, String>(boards.size());
        int boardIndex = 1;
        for (Board board : boards) {
            String wrkId = "wrk" + boardIndex++;
            wrkIds.put(wrkId, String.format("b:%s", board.getId()));

            Output.print("  ^b^%s^r^ ^black^| %s^r^", board.getName(), wrkId);
            Output.print("    ^black^%s^r^", board.getUrl());
        }
        return wrkIds;
    }

}
