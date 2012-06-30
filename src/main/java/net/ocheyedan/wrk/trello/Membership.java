package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 7:56 AM
 *
 * Representation of a {@literal Trello} membership object.
 *
 * {
 *  id=4fee0d37ca6ff8287a018e04,
 *  idMember=4feb72a3a616022c2d8ca2b1,
 *  memberType=admin
 * }
 */
public final class Membership {

    private final String id;

    private final String idMember;

    private final String memberType;

    private Membership() {
        this(null, null, null);
    }

    public Membership(String id, String idMember, String memberType) {
        this.id = id;
        this.idMember = idMember;
        this.memberType = memberType;
    }

    public String getId() {
        return id;
    }

    public String getIdMember() {
        return idMember;
    }

    public String getMemberType() {
        return memberType;
    }
}
