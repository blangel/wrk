package net.ocheyedan.wrk.trello;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 7:57 AM
 *
 * Representation of a {@literal Trello} label-names object.
 *
 * {
 *  red=,
 *  orange=,
 *  yellow=,
 *  green=,
 *  blue=,
 *  purple=
 * }
 */
public final class LabelNames {

    private final String red;

    private final String orange;

    private final String yellow;

    private final String green;

    private final String blue;

    private final String purple;

    private LabelNames() {
        this(null, null, null, null, null, null);
    }

    public LabelNames(String red, String orange, String yellow, String green, String blue, String purple) {
        this.red = red;
        this.orange = orange;
        this.yellow = yellow;
        this.green = green;
        this.blue = blue;
        this.purple = purple;
    }

    public String getRed() {
        return red;
    }

    public String getOrange() {
        return orange;
    }

    public String getYellow() {
        return yellow;
    }

    public String getGreen() {
        return green;
    }

    public String getBlue() {
        return blue;
    }

    public String getPurple() {
        return purple;
    }
}
