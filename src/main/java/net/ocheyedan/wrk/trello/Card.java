package net.ocheyedan.wrk.trello;

import java.util.List;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 6:59 AM
 *
 * Represents a {@literal Trello} card object.
 *
 * {
 *  id=4fed92aff29a10bc3b40803f,
 *  checkItemStates=[],
 *  closed=false,
 *  desc=,
 *  idBoard=4fed9292f29a10bc3b4077ec,
 *  idChecklists=[],
 *  idList=4fed9292f29a10bc3b4077ef,
 *  idMembers=[4feb72a3a616022c2d8ca2b1],
 *  idShort=1,
 *  labels=[],
 *  name=Create Store implementation.,
 *  pos=66559,
 *  url=https://trello.com/card/create-store-implementation/4fed9292f29a10bc3b4077ec/1,
 *  badges={},
 *  subscribed=true
 * }
 */
public final class Card {

    private final String id;

    private final List<String> checkItemStates;

    private final Boolean closed;

    private final String desc;

    private final String idBoard;

    private final List<String> idChecklists;

    private final String idList;

    private final List<String> idMembers;

    private final Integer idShort;

    private final List<Label> labels;

    private final String name;

    private final Integer pos;

    private final String url;

    private final Badge badges;

    private final Boolean subscribed;

    private Card() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    public Card(String id, List<String> checkItemStates, Boolean closed, String desc, String idBoard,
                List<String> idChecklists, String idList, List<String> idMembers, Integer idShort, List<Label> labels,
                String name, Integer pos, String url, Badge badges, Boolean subscribed) {
        this.id = id;
        this.checkItemStates = checkItemStates;
        this.closed = closed;
        this.desc = desc;
        this.idBoard = idBoard;
        this.idChecklists = idChecklists;
        this.idList = idList;
        this.idMembers = idMembers;
        this.idShort = idShort;
        this.labels = labels;
        this.name = name;
        this.pos = pos;
        this.url = url;
        this.badges = badges;
        this.subscribed = subscribed;
    }

    public String getId() {
        return id;
    }

    public List<String> getCheckItemStates() {
        return checkItemStates;
    }

    public Boolean getClosed() {
        return closed;
    }

    public String getDesc() {
        return desc;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public List<String> getIdChecklists() {
        return idChecklists;
    }

    public String getIdList() {
        return idList;
    }

    public List<String> getIdMembers() {
        return idMembers;
    }

    public Integer getIdShort() {
        return idShort;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public String getName() {
        return name;
    }

    public Integer getPos() {
        return pos;
    }

    public String getUrl() {
        return url;
    }

    public Badge getBadges() {
        return badges;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }
}
