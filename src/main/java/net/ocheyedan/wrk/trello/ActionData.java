package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 10:18 AM
 *
 * Representation of the data element of a {@literal Trello} {@linkplain Action} object.
 *
 * {
 *  text=Should we do psuedo authentication with oauth or openId for authentication (but what, then also use oauth, do we even need information from providers? yes in the case of facebook [ friend graph, profile photo, etc]),
 *  board={name=Create Landing Page, id=4fee0d37ca6ff8287a018e00},
 *  card={name=Add Facebook/Google/Twitter Oauth2 support, idShort=1, id=4fee0d52ca6ff8287a019d0f}
 * }
 */
public final class ActionData {

    private final String text;

    private final Board board;

    private final Card card;

    private ActionData() {
        this(null, null, null);
    }

    public ActionData(String text, Board board, Card card) {
        this.text = text;
        this.board = board;
        this.card = card;
    }

    public String getText() {
        return text;
    }

    public Board getBoard() {
        return board;
    }

    public Card getCard() {
        return card;
    }
}
