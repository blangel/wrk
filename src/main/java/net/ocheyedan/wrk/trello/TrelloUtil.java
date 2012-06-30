package net.ocheyedan.wrk.trello;

import java.net.URI;
import java.net.URLEncoder;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 8:33 PM
 *
 * Utility methods for when interacting with {@literal Trello}
 */
public final class TrelloUtil {

    public static final String APP_DEV_KEY = "8d56bbd601877abfd13150a999a840d0";

    private static final String APP_OAUTH_KEY = "68b0cd5adf3dffaa2d7941a026628e7a8006662759a3d8fb65fa9c4c96bceaa0";

    public static final String USR_TOKEN = System.getProperty("wrk.trello.usr.token");

    public static String url(String base, Object ... args) {
        String combined = String.format(base, args);
        URI uri = URI.create(combined);
        return uri.toASCIIString();
    }

    private TrelloUtil() { }

}
