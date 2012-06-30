package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 7:31 AM
 *
 * Representation of a {@literal Trello} label object.
 */
public final class Label {

    private final String color;

    private final String name;

    private Label() {
        this(null, null);
    }

    public Label(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
