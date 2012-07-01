package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.trello.Organization;
import net.ocheyedan.wrk.trello.Trello;
import org.codehaus.jackson.type.TypeReference;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 8:19 AM
 */
public final class Orgs extends IdCommand {

    private final String url;

    private final String description;

    public Orgs(Args args) {
        super(args);
        url = Trello.url("https://trello.com/1/members/my/organizations?key=%s&token=%s", Trello.APP_DEV_KEY,
                Trello.USR_TOKEN);
        description = "Your organizations:";
    }

    @Override protected Map<String, String> _run() {
        Output.print(description);
        List<Organization> orgs = RestTemplate.get(url, new TypeReference<List<Organization>>() {
        });
        if ((orgs == null) || orgs.isEmpty()) {
            Output.print("  ^black^None^r^");
            return Collections.emptyMap();
        }
        Map<String, String> wrkIds = new HashMap<String, String>(orgs.size());
        int orgIndex = 1;
        for (Organization organization : orgs) {
            String wrkId = "wrk" + orgIndex++;
            wrkIds.put(wrkId, String.format("o:%s", organization.getId()));

            Output.print("  ^b^%s^r^ ^black^| %s^r^", organization.getDisplayName(), wrkId);
            Output.print("    ^black^%s^r^", organization.getUrl());
        }
        return wrkIds;
    }

}
