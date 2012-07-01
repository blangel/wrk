package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Board;
import net.ocheyedan.wrk.trello.Card;
import net.ocheyedan.wrk.trello.Trello;
import org.codehaus.jackson.type.TypeReference;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * User: blangel
 * Date: 7/1/12
 * Time: 1:18 PM
 */
public final class Create extends IdCommand {

    private static enum Type {
        Board, List, Card
    }

    private final String url;

    private final String description;

    private final Type type;

    public Create(Args args) {
        super(args);
        if (((args.args.size() == 4) || (args.args.size() == 5))
                && "board".equals(args.args.get(0)) && "in".equals(args.args.get(1))) {
            TrelloId orgId = parseWrkId(args.args.get(2), orgPrefix);
            String name = validate(encode(args.args.get(3)), "Board name", "board names");
            String desc = (args.args.size() == 5 ? "&desc=" + validate(encode(args.args.get(4)), "Board desc", "board descriptions") : "");
            url = Trello.url("https://trello.com/1/boards?name=%s&idOrganization=%s%s&key=%s&token=%s", name, orgId.id,
                             desc, Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Creating board in organization ^b^%s^r^:", orgId.id);
            type = Type.Board;
        } else if ((args.args.size() == 4) && "list".equals(args.args.get(0)) && "in".equals(args.args.get(1))) {
            TrelloId boardId = parseWrkId(args.args.get(2), boardsPrefix);
            String name = validate(encode(args.args.get(3)), "List name", "list names");
            url = Trello.url("https://trello.com/1/lists?name=%s&idBoard=%s&key=%s&token=%s", name, boardId.id,
                             Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Creating list in board ^b^%s^r^:", boardId.id);
            type = Type.List;
        } else if (((args.args.size() == 4) || (args.args.size() == 5))
                && "card".equals(args.args.get(0)) && "in".equals(args.args.get(1))) {
            TrelloId listId = parseWrkId(args.args.get(2), listsPrefix);
            String name = validate(encode(args.args.get(3)), "Card name", "card names");
            String desc = (args.args.size() == 5 ? "&desc=" + validate(encode(args.args.get(4)), "Card desc",
                    "card descriptions") : "");
            url = Trello.url("https://trello.com/1/cards?name=%s&idList=%s%s&key=%s&token=%s", name, listId.id,
                    desc, Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Creating card in list ^b^%s^r^:", listId.id);
            type = Type.Card;
        } else {
            url = description = null;
            type = null;
        }
    }

    @Override protected Map<String, String> _run() {
        if (url == null) {
            new Usage(args).run();
            return Collections.emptyMap();
        }
        Output.print(description);
        Map<String, String> wrkIds = new HashMap<String, String>(1, 1.0f);
        String wrkId = "wrk1";
        switch (type) {
            case Board:
                Board board = RestTemplate.post(url, new TypeReference<Board>() { });
                if (board == null) {
                    Output.print("^red^Invalid id or insufficient privileges.^r^");
                    break;
                }
                wrkIds.put(wrkId, String.format("b:%s", board.getId()));
                Output.print("  ^b^%s^r^ ^black^| %s^r^", board.getName(), wrkId);
                Output.print("    ^black^%s^r^", board.getUrl());
                return wrkIds;
            case List:
                net.ocheyedan.wrk.trello.List list = RestTemplate.post(url, new TypeReference<net.ocheyedan.wrk.trello.List>() { });
                if (list == null) {
                    Output.print("^red^Invalid id or insufficient privileges.^r^");
                    break;
                }
                wrkIds.put(wrkId, String.format("l:%s", list.getId()));
                Output.print("  ^b^%s^r^ ^black^| %s^r^", list.getName(), wrkId);
                return wrkIds;
            case Card:
                Card card = RestTemplate.post(url, new TypeReference<Card>() { });
                if (card == null) {
                    Output.print("^red^Invalid id or insufficient privileges.^r^");
                    break;
                }
                String labels = Cards.buildLabel(card.getLabels());
                wrkIds.put(wrkId, String.format("c:%s", card.getId()));
                Output.print("  ^b^%s^r^%s ^black^| %s^r^", card.getName(), labels, wrkId);
                Output.print("    ^black^%s^r^", Cards.getPrettyUrl(card));
                return wrkIds;
        }
        return Collections.emptyMap();
    }
}
