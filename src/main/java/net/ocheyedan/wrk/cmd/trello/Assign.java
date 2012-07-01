package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Member;
import net.ocheyedan.wrk.trello.Trello;
import org.codehaus.jackson.type.TypeReference;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 5:16 PM
 */
public final class Assign extends IdCommand {

    private final String url;

    private final String description;

    public Assign(Args args) {
        super(args);
        if (args.args.size() == 1) {
            TrelloId cardId = parseWrkId(args.args.get(0), cardsPrefix);
            url = Trello.url("https://trello.com/1/cards/%s/members?value=%s&key=%s&token=%s", cardId.id,
                    Trello.getUsrId(), Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Assigning user to card ^b^%s^r^:", cardId.id);
        } else if ((args.args.size() == 3) && "to".equals(args.args.get(1))) {
            TrelloId cardId = parseWrkId(args.args.get(2), cardsPrefix);
            TrelloId memberId = parseWrkId(args.args.get(0), membersPrefix);
            url = Trello.url("https://trello.com/1/cards/%s/members?value=%s&key=%s&token=%s", cardId.id,
                    memberId.id, Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Assigning user ^b^%s^r^ to card ^b^%s^r^:", memberId.id, cardId.id);
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
        List<Member> members = RestTemplate.post(url, new TypeReference<List<Member>>() { });
        if ((members == null) || members.isEmpty()) {
            Output.print("  ^red^Already added or invalid user.^r^");
        } else {
            Output.print("  ^b^Added!^r^");
        }
        return Collections.emptyMap();
    }

}
