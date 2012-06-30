package net.ocheyedan.wrk;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 9:34 AM
 */
public final class Json {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static ObjectMapper mapper() {
        return mapper;
    }

    private Json() { }

}
