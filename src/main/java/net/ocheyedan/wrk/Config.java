package net.ocheyedan.wrk;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

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

        private final String editor;

        private final String editorOpts;

        private JsonFile() {
            this(null, null, null);
        }

        private JsonFile(Boolean color, String editor, String editorOpts) {
            this.color = color;
            this.editor = editor;
            this.editorOpts = editorOpts;
        }

        public Boolean getColor() {
            return color;
        }

        public String getEditor() {
            return editor;
        }

        public String getEditorOpts() {
            return editorOpts;
        }
    }

    private static final AtomicReference<String> editor = new AtomicReference<String>();

    private static final AtomicReference<String> editorOpts = new AtomicReference<String>();

    private static final Boolean defaultColor = true;

    private static final String defaultEditor = System.getProperty("wrk.editor");

    private static final String defaultEmacsOpts = "-nw -Q";

    private static final String defaultViOpts = "";

    public static void init() {
        boolean color = true;

        try {
            File configFile = new File(String.format("%s%s%s%s%s", System.getProperty("user.home"), File.separator, ".wrk", File.separator, "config"));
            if (configFile.exists()) {
                JsonFile config = Json.mapper().readValue(configFile, JsonFile.class);
                color = (config.getColor() == null) || config.getColor();
                editor.set(config.getEditor());
                editorOpts.set(config.getEditorOpts());
            } else {
                // set defaults for user
                String defaultEditorOpts = (defaultEditor.endsWith("emacs") ? defaultEmacsOpts : defaultViOpts);
                JsonFile defaultConfig = new JsonFile(defaultColor, defaultEditor, defaultEditorOpts);
                editor.set(defaultConfig.getEditor());
                editorOpts.set(defaultConfig.getEditorOpts());
                Json.mapper().writeValue(configFile, defaultConfig);
            }
        } catch (IOException ioe) {
            // ignore and take defaults
        }
        Output.init(color);
    }

    public static String getEditor() {
        return editor.get();
    }

    public static String getEditorOpts() {
        return editorOpts.get();
    }

    private Config() { }

}
