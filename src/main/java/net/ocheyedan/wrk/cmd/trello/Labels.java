package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Config;
import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Label;
import net.ocheyedan.wrk.trello.Trello;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * User: blangel
 * Date: 7/1/12
 * Time: 1:58 PM
 */
public final class Labels extends IdCommand {

    private final String url;

    private final String description;

    public Labels(Args args) {
        super(args);
        if ((args.args.size() == 3) && "with".equals(args.args.get(1))) {
            TrelloId cardId = parseWrkId(args.args.get(0), cardsPrefix);
            String label = validate(args.args.get(2));
            url = Trello.url("https://trello.com/1/cards/%s/labels?value=%s&key=%s&token=%s", cardId.id,
                             label, Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Commenting on card ^b^%s^r^:", cardId.id);
        } else {
            url = description = null;
        }
    }

    private String validate(String label) {
        if ("green".equals(label) || "yellow".equals(label) || "orange".equals(label) || "red".equals(label) ||
            "purple".equals(label) || "blue".equals(label)) {
            return label;
        }
        Output.print("^red^Label must be one of green|yellow|orange|red|purple|blue.^r^");
        System.exit(1); return null;
    }

    @Override protected Map<String, String> _run() {
        if (url == null) {
            new Usage(args).run();
            return Collections.emptyMap();
        }
        Output.print(description);
        List<Label> result = RestTemplate.post(url, new TypeReference<List<Label>>() { });
        if (result == null) {
            Output.print("  ^red^Invalid id or insufficient privileges.^r^");
        } else {
            Output.print("  ^b^Labeled!^r^", result);
        }
        return Collections.emptyMap();
    }
}
