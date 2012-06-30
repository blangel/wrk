package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 7:54 AM
 *
 * Representation of a {@literal Trello} pref object.
 *
 * {
 *  permissionLevel=org,
 *  voting=members,
 *  comments=members,
 *  invitations=members,
 *  selfJoin=false
 * }
 */
public final class Pref {

    private final String permissionLevel;

    private final String voting;

    private final String comments;

    private final String invitations;

    private final Boolean selfJoin;

    private Pref() {
        this(null, null, null, null, null);
    }

    public Pref(Boolean selfJoin, String invitations, String comments, String voting, String permissionLevel) {
        this.selfJoin = selfJoin;
        this.invitations = invitations;
        this.comments = comments;
        this.voting = voting;
        this.permissionLevel = permissionLevel;
    }

    public String getPermissionLevel() {
        return permissionLevel;
    }

    public String getVoting() {
        return voting;
    }

    public String getComments() {
        return comments;
    }

    public String getInvitations() {
        return invitations;
    }

    public Boolean getSelfJoin() {
        return selfJoin;
    }
}
