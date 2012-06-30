package net.ocheyedan.wrk.trello;

import java.util.List;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 8:23 AM
 *
 * Representation of a {@literal Trello} organization object.
 *
 * {
 *  id=4feda21e9693a8f660520501,
 *  name=varickbergenbusinessdevelopment,
 *  displayName=Varick/Bergen - Business Development,
 *  desc=,
 *  invited=false,
 *  invitations=[],
 *  memberships=[{}],
 *  prefs={},
 *  url=https://trello.com/varickbergenbusinessdevelopment,
 *  website=,
 *  idBoards=[]
 * }
 */
public final class Organization {

    private final String id;

    private final String name;

    private final String displayName;

    private final String desc;

    private final Boolean invited;

    private final List<String> invitations;

    private final List<Membership> memberships;

    private final Pref prefs;

    private final String url;

    private final String website;

    private final List<String> idBoards;

    private Organization() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }

    public Organization(String id, String name, String displayName, String desc, Boolean invited, List<String> invitations,
                        List<Membership> memberships, Pref prefs, String url, String website, List<String> idBoards) {
        this.id = id;
        this.name = name;
        this.displayName = displayName;
        this.desc = desc;
        this.invited = invited;
        this.invitations = invitations;
        this.memberships = memberships;
        this.prefs = prefs;
        this.url = url;
        this.website = website;
        this.idBoards = idBoards;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getInvited() {
        return invited;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public Pref getPrefs() {
        return prefs;
    }

    public String getUrl() {
        return url;
    }

    public String getWebsite() {
        return website;
    }

    public List<String> getIdBoards() {
        return idBoards;
    }
}
