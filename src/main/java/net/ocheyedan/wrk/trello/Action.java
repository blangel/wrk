package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 10:17 AM
 *
 * Representation of a {@literal Trello} action object.
 *
 * {
 *  id=4fee47305a2b20084809173c,
 *  idMemberCreator=4feb72a3a616022c2d8ca2b1,
 *  data={},
 *  type=commentCard,
 *  date=2012-06-30T00:24:16.842Z,
 *  memberCreator={}
 * }
 */
public final class Action {

    private final String id;

    private final String idMemberCreator;

    private final ActionData data;

    private final String type;

    private final String date;

    private final Member memberCreator;

    private Action() {
        this(null, null, null, null, null, null);
    }

    public Action(String id, String idMemberCreator, ActionData data, String type, String date, Member memberCreator) {
        this.id = id;
        this.idMemberCreator = idMemberCreator;
        this.data = data;
        this.type = type;
        this.date = date;
        this.memberCreator = memberCreator;
    }

    public String getId() {
        return id;
    }

    public String getIdMemberCreator() {
        return idMemberCreator;
    }

    public ActionData getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public Member getMemberCreator() {
        return memberCreator;
    }
}
