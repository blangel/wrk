package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Member;
import net.ocheyedan.wrk.trello.Trello;
import org.codehaus.jackson.type.TypeReference;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 1:33 PM
 */
public final class Members extends IdCommand {

    private final String url;

    private final String description;

    public Members(Args args) {
        super(args);
        if ((args.args.size() == 2) && "in".equals(args.args.get(0))) {
            TrelloId id = parseWrkId(args.args.get(1), orgsBoardsCardsPrefix);
            if (id.idWithTypePrefix.startsWith("o:")) {
                String orgId = id.idWithTypePrefix.substring(2);
                url = Trello.url("https://trello.com/1/organizations/%s/members?key=%s&token=%s", orgId,
                        Trello.APP_DEV_KEY, Trello.USR_TOKEN);
                description = String.format("Members of organization ^b^%s^r^:", orgId);
            } else if (id.idWithTypePrefix.startsWith("b:")) {
                String boardId = id.idWithTypePrefix.substring(2);
                url = Trello.url("https://trello.com/1/boards/%s/members?key=%s&token=%s", boardId,
                        Trello.APP_DEV_KEY, Trello.USR_TOKEN);
                description = String.format("Members of board ^b^%s^r^:", boardId);
            } else if (id.idWithTypePrefix.startsWith("c:")) {
                String cardId = id.idWithTypePrefix.substring(2);
                url = Trello.url("https://trello.com/1/cards/%s/members?key=%s&token=%s", cardId,
                        Trello.APP_DEV_KEY, Trello.USR_TOKEN);
                description = String.format("Members of card ^b^%s^r^:", cardId);
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
        List<Member> members = RestTemplate.get(url, new TypeReference<List<Member>>() { });
        if ((members == null) || members.isEmpty()) {
            Output.print("  ^black^None^r^");
            return Collections.emptyMap();
        }
        return printMembers(members, 1);
    }

    static Map<String, String> printMembers(List<Member> members, int indexBase) {
        Map<String, String> wrkIds = new HashMap<String, String>(members.size());
        int memberIndex = indexBase;
        for (Member member : members) {
            String wrkId = "wrk" + memberIndex++;
            wrkIds.put(wrkId, String.format("m:%s", member.getId()));

            Output.print("  ^b^%s^r^ ^black^| %s^r^", member.getFullName(), wrkId);
            Output.print("    ^black^username^r^ %s", member.getUsername());
        }
        return wrkIds;
    }

}
