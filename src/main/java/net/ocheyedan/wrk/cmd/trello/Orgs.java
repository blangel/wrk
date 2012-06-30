package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Command;
import net.ocheyedan.wrk.trello.Organization;
import net.ocheyedan.wrk.trello.TrelloUtil;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 8:19 AM
 */
public final class Orgs extends Command {

    private final String url;

    private final String description;

    public Orgs(Args args) {
        super(args);
        url = TrelloUtil.url("https://trello.com/1/members/my/organizations?key=%s&token=%s", TrelloUtil.APP_DEV_KEY,
                TrelloUtil.USR_TOKEN);
        description = "Your organizations:";
    }

    @Override public void run() {
        Output.print(description);
        List<Organization> orgs = RestTemplate.get(url, new TypeReference<List<Organization>>() {
        });
        if ((orgs == null) || orgs.isEmpty()) {
            Output.print("  ^black^None^r^");
            return;
        }
        for (Organization organization : orgs) {
            Output.print("  ^b^%s^r^", organization.getDisplayName());
            Output.print("    ^black^%s^r^", organization.getUrl());
        }
    }

}
