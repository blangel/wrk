package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Command;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Card;
import net.ocheyedan.wrk.trello.Label;
import net.ocheyedan.wrk.trello.TrelloUtil;
import org.codehaus.jackson.type.TypeReference;

import java.util.List;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 6:51 AM
 */
public final class Cards extends Command {

    private final String url;

    private final String description;

    public Cards(Args args) {
        super(args);
        if ((args.args.size() == 2) && "in".equals(args.args.get(0))) {
            String boardId = args.args.get(1); // TODO - parse for wrk-id
            url = TrelloUtil.url("https://trello.com/1/boards/%s/cards?filter=open&key=%s&token=%s", boardId,
                    TrelloUtil.APP_DEV_KEY, TrelloUtil.USR_TOKEN);
            description = String.format("Open cards for board ^b^%s^r^:", boardId);
        } else if (args.args.isEmpty()) {
            url = TrelloUtil.url("https://trello.com/1/members/my/cards?filter=open&key=%s&token=%s", TrelloUtil.APP_DEV_KEY, TrelloUtil.USR_TOKEN);
            description = "Open cards assigned to you:";
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
        List<Card> cards = RestTemplate.get(url, new TypeReference<List<Card>>() {
        });
        if ((cards == null) || cards.isEmpty()) {
            Output.print("  ^black^None^r^");
            return;
        }
        for (Card card : cards) {
            String labels = buildLabel(card.getLabels());
            Output.print("  ^b^%s^r^%s", card.getName(), labels);
            Output.print("    ^black^%s^r^", getPrettyUrl(card));
        }
    }

    /**
     * The {@link Card#getUrl()} returns a url based off of board and card's short-id; translating to long-id so that
     * users can copy and paste id printed via url.  Additionally removing the card name from the url to shorten the
     * length of the resulting url.
     * @param card to get long url
     * @return the long url (using {@link Card#getId()} instead of {@link Card#getIdShort()}
     */
    private String getPrettyUrl(Card card) {
        String originalUrl = card.getUrl();
        int firstIndex = originalUrl.indexOf("card/");
        if (firstIndex == -1) {
            return originalUrl; // balk
        }
        String toRemove = originalUrl.substring(firstIndex + 5);
        return originalUrl.replace(toRemove, card.getIdBoard() + "/" + card.getId());
    }

    private String buildLabel(List<Label> labels) {
        StringBuilder buffer = new StringBuilder();
        for (Label label : labels) {
            String name = ((label.getName() == null) || label.getName().isEmpty() ? "  " : " " + label.getName() + " ");
            if ("green".equals(label.getColor())) {
                buffer.append(String.format(" ^i^^green^%s^r^", name));
            } else if ("yellow".equals(label.getColor())) {
                buffer.append(String.format(" ^i^^yellow^%s^r^", name));
            } else if ("orange".equals(label.getColor())) {
                buffer.append(String.format(" ^orange^^i^%s^r^", name));
            } else if ("red".equals(label.getColor())) {
                buffer.append(String.format(" ^i^^red^%s^r^", name));
            } else if ("purple".equals(label.getColor())) {
                buffer.append(String.format(" ^i^^magenta^%s^r^", name));
            } else if ("blue".equals(label.getColor())) {
                buffer.append(String.format(" ^i^^blue^%s^r^", name));
            }
        }
        return buffer.toString();
    }
}
