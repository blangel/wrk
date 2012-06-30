package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 7:02 AM
 *
 * Representation of a {@literal Trello} badge object.
 *
 * {
 *  votes=0,
 *  viewingMemberVoted=false,
 *  subscribed=true,
 *  fogbugz=,
 *  checkItems=0,
 *  checkItemsChecked=0,
 *  comments=1,
 *  attachments=0,
 *  description=false,
 *  due=null
 * }
 */
public final class Badge {

    private final Integer votes;

    private final Boolean viewingMemberVoted;

    private final Boolean subscribed;

    private final String fogbugz;

    private final Integer checkItems;

    private final Integer checkItemsChecked;

    private final Integer comments;

    private final Integer attachments;

    private final Boolean description;

    private final String due;

    private Badge() {
        this(null, null, null, null, null, null, null, null, null, null);
    }

    public Badge(Integer votes, Boolean viewingMemberVoted, Boolean subscribed, String fogbugz, Integer checkItems,
                 Integer checkItemsChecked, Integer comments, Integer attachments, Boolean description, String due) {
        this.votes = votes;
        this.viewingMemberVoted = viewingMemberVoted;
        this.subscribed = subscribed;
        this.fogbugz = fogbugz;
        this.checkItems = checkItems;
        this.checkItemsChecked = checkItemsChecked;
        this.comments = comments;
        this.attachments = attachments;
        this.description = description;
        this.due = due;
    }

    public Integer getVotes() {
        return votes;
    }

    public Boolean getViewingMemberVoted() {
        return viewingMemberVoted;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public String getFogbugz() {
        return fogbugz;
    }

    public Integer getCheckItems() {
        return checkItems;
    }

    public Integer getCheckItemsChecked() {
        return checkItemsChecked;
    }

    public Integer getComments() {
        return comments;
    }

    public Integer getAttachments() {
        return attachments;
    }

    public Boolean getDescription() {
        return description;
    }

    public String getDue() {
        return due;
    }
}
