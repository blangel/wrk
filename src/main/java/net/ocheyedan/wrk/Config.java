package net.ocheyedan.wrk;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 9:01 PM
 *
 * Parses the configuration file at {@literal ~/.wrk/config}
 *
 * {
 *  color: true
 * }
 */
public final class Config {

    private static final class JsonFile {

        private final Boolean color;

        private JsonFile() {
            this(null);
        }

        private JsonFile(Boolean color) {
            this.color = color;
        }

        public Boolean getColor() {
            return color;
        }
    }

    public static void init() {
        boolean color = true;

        try {
            File configFile = new File(String.format("%s%s%s%s%s", System.getProperty("user.home"), File.separator, ".wrk", File.separator, "config"));
            if (configFile.exists()) {
                JsonFile config = Json.mapper().readValue(configFile, JsonFile.class);
                color = (config.getColor() == null) || config.getColor();
            }
        } catch (IOException ioe) {
            // ignore and take defaults
        }
        Output.init(color);

    }

    private Config() { }

}
