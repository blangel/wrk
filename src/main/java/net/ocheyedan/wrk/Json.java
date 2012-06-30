package net.ocheyedan.wrk;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;

/**
 * User: blangel
 * Date: 6/30/12
 * Time: 9:34 AM
 */
public final class Json {

    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static ObjectMapper mapper() {
        return mapper;
    }

    private Json() { }

}
