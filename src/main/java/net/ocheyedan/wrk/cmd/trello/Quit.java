package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Member;
import net.ocheyedan.wrk.trello.TrelloUtil;
import org.codehaus.jackson.type.TypeReference;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 8:48 PM
 */
public final class Quit extends IdCommand {

    private final String url;

    private final String description;

    public Quit(Args args) {
        super(args);
        if (args.args.size() == 1) {
            IdCommand.TrelloId cardId = parseWrkId(args.args.get(0), cardsPrefix);
            url = TrelloUtil.url("https://trello.com/1/cards/%s/members/%s?key=%s&token=%s", cardId.id,
                    TrelloUtil.getUsrId(), TrelloUtil.APP_DEV_KEY, TrelloUtil.USR_TOKEN);
            description = String.format("Un-assigning user from card ^b^%s^r^:", cardId.id);
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
        List<Member> members = RestTemplate.delete(url, new TypeReference<List<Member>>() {
        });
        if (members == null) {
            Output.print("  ^red^Not added or invalid user.^r^");
        } else {
            Output.print("  ^b^Un-assigned!^r^");
        }
        return Collections.emptyMap();
    }
}
