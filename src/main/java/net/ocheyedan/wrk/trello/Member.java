package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 10:20 AM
 *
 * Representation of a {@literal Trello} member object.
 *
 * {
 *  id=4feb72a3a616022c2d8ca2b1,
 *  username=brianlangel,
 *  fullName=Brian Langel,
 *  initials=BL,
 *  avatarHash=0c12938cecec05456cf00a173a40eb4e
 * }
 */
public final class Member {

    private final String id;

    private final String username;

    private final String fullName;

    private final String initials;

    private final String avatarHash;

    private Member() {
        this(null, null, null, null, null);
    }

    public Member(String id, String username, String fullName, String initials, String avatarHash) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.initials = initials;
        this.avatarHash = avatarHash;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInitials() {
        return initials;
    }

    public String getAvatarHash() {
        return avatarHash;
    }
}
