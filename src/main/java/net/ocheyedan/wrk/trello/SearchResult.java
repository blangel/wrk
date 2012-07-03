package net.ocheyedan.wrk.trello;

import java.util.Collections;
import java.util.List;

/**
 * User: blangel
 * Date: 7/3/12
 * Time: 3:55 PM
 *
 * Representation of a {@literal Trello} pref object.
 *
 * {
 *  options:{
 *      terms:[],
 *      modifiers:[ { text: "member:me" } ],
 *      modelTypes: [],
 *      partial: false
 *  },
 *  cards:[],
 *  boards: [],
 *  organizations: [],
 *  members: [],
 *  actions: []
 *
 * }
 */
public final class SearchResult {

    public static final class Term {

        private final String text;

        private Term() {
            this(null);
        }

        public Term(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public static final class Options {

        private final List<Term> terms;

        private final List<Term> modifiers;

        private final List<String> modelTypes;

        private final Boolean partial;

        private Options() {
            this(null, null, null, null);
        }

        public Options(List<Term> terms, List<Term> modifiers, List<String> modelTypes, Boolean partial) {
            this.terms = terms;
            this.modifiers = modifiers;
            this.modelTypes = modelTypes;
            this.partial = partial;
        }

        public List<Term> getTerms() {
            return terms;
        }

        public List<Term> getModifiers() {
            return modifiers;
        }

        public List<String> getModelTypes() {
            return modelTypes;
        }

        public Boolean getPartial() {
            return partial;
        }
    }

    private final Options options;

    private final List<Board> boards;

    private final List<Card> cards;

    private final List<Action> actions;

    private final List<Organization> organizations;

    private final List<Member> members;

    private SearchResult() {
        this(null, null, null, null, null, null);
    }

    public SearchResult(Options options, List<Board> boards, List<Card> cards, List<Action> actions,
                        List<Organization> organizations, List<Member> members) {
        this.options = options;
        this.boards = boards;
        this.cards = cards;
        this.actions = actions;
        this.organizations = organizations;
        this.members = members;
    }

    public Options getOptions() {
        return options;
    }

    public List<Board> getBoards() {
        return (boards == null ? Collections.<Board>emptyList() : boards);
    }

    public List<Card> getCards() {
        return (cards == null ? Collections.<Card>emptyList() : cards);
    }

    public List<Action> getActions() {
        return (actions == null ? Collections.<Action>emptyList() : actions);
    }

    public List<Organization> getOrganizations() {
        return (organizations == null ? Collections.<Organization>emptyList() : organizations);
    }

    public List<Member> getMembers() {
        return (members == null ? Collections.<Member>emptyList() : members);
    }
}
