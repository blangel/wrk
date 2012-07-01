package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Trello;
import org.codehaus.jackson.type.TypeReference;

import java.util.Collections;
import java.util.Map;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 9:07 PM
 */
public final class Close extends IdCommand {

    private final String url;

    private final String description;

    public Close(Args args) {
        super(args);
        if (args.args.size() == 1) {
            TrelloId id = parseWrkId(args.args.get(0), boardsCardsPrefix);
            if (id.idWithTypePrefix.startsWith("b:")) {
                String boardId = id.idWithTypePrefix.substring(2);
                url = Trello.url("https://trello.com/1/boards/%s/closed?value=true&key=%s&token=%s", boardId,
                        Trello.APP_DEV_KEY, Trello.USR_TOKEN);
                description = String.format("Closing board ^b^%s^r^:", boardId);
            } else if (id.idWithTypePrefix.startsWith("c:")) {
                String cardId = id.idWithTypePrefix.substring(2);
                url = Trello.url("https://trello.com/1/cards/%s/closed?value=true&key=%s&token=%s", cardId,
                        Trello.APP_DEV_KEY, Trello.USR_TOKEN);
                description = String.format("Closing card ^b^%s^r^:", cardId);
            } else {
                url = description = null;
            }
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
        Map<String, Object> result = RestTemplate.put(url, new TypeReference<Map<String, Object>>() { });
        if (result == null) {
            Output.print("  ^red^Invalid id or insufficient privileges.^r^");
        } else {
            Output.print("  ^b^Closed!^r^", result);
        }
        return Collections.emptyMap();
    }
}
