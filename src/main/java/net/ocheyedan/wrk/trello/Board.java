package net.ocheyedan.wrk.trello;

import java.util.List;
import java.util.prefs.PreferenceChangeEvent;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 7:50 AM
 *
 * Representation of a {@literal Trello} board.
 *
 * {
 *  id=4fee0d37ca6ff8287a018e00,
 *  name=Create Landing Page,
 *  desc=,
 *  closed=false,
 *  idOrganization=4fed9279f29a10bc3b40718b,
 *  invited=false,
 *  pinned=true,
 *  url=https://trello.com/board/create-landing-page/4fee0d37ca6ff8287a018e00,
 *  prefs={},
 *  invitations=[],
 *  memberships=[],
 *  labelNames={}
 * }
 */
public final class Board {

    private final String id;

    private final String name;

    private final String desc;

    private final Boolean closed;

    private final String idOrganization;

    private final Boolean invited;

    private final Boolean pinned;

    private final String url;

    private final Pref prefs;

    private final List<String> invitations;

    private final List<Membership> memberships;

    private final LabelNames labelNames;

    private Board() {
        this(null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Board(String id, String name, String desc, Boolean closed, String idOrganization, Boolean invited,
                 Boolean pinned, String url, Pref prefs, List<String> invitations, List<Membership> memberships,
                 LabelNames labelNames) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.closed = closed;
        this.idOrganization = idOrganization;
        this.invited = invited;
        this.pinned = pinned;
        this.url = url;
        this.prefs = prefs;
        this.invitations = invitations;
        this.memberships = memberships;
        this.labelNames = labelNames;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getClosed() {
        return closed;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public Boolean getInvited() {
        return invited;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public String getUrl() {
        return url;
    }

    public Pref getPrefs() {
        return prefs;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public LabelNames getLabelNames() {
        return labelNames;
    }
}
