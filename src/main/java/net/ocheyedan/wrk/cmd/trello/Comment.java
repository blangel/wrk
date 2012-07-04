package net.ocheyedan.wrk.cmd.trello;

import net.ocheyedan.wrk.Config;
import net.ocheyedan.wrk.Output;
import net.ocheyedan.wrk.RestTemplate;
import net.ocheyedan.wrk.cmd.Args;
import net.ocheyedan.wrk.cmd.Usage;
import net.ocheyedan.wrk.trello.Trello;
import org.codehaus.jackson.type.TypeReference;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

/**
 * User: blangel
 * Date: 7/1/12
 * Time: 8:43 AM
 */
public final class Comment extends IdCommand {

    private final String url;

    private final String description;

    public Comment(Args args) {
        super(args);
        if ((args.args.size() == 3) && "on".equals(args.args.get(0))) {
            TrelloId cardId = parseWrkId(args.args.get(1), cardsPrefix);
            String comment = validate(encode(args.args.get(2)), "Comment", "comments");
            url = Trello.url("https://trello.com/1/cards/%s/actions/comments?text=%s&key=%s&token=%s", cardId.id,
                    comment, Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Commenting on card ^b^%s^r^:", cardId.id);
        } else if ((args.args.size() == 2) && "on".equals(args.args.get(0))) {
            TrelloId cardId = parseWrkId(args.args.get(1), cardsPrefix);
            String comment = validate(encode(getComment()), "Comment", "comments");
            url = Trello.url("https://trello.com/1/cards/%s/actions/comments?text=%s&key=%s&token=%s", cardId.id,
                    comment, Trello.APP_DEV_KEY, Trello.USR_TOKEN);
            description = String.format("Commenting on card ^b^%s^r^:", cardId.id);
        } else {
            url = description = null;
        }
    }

    private String getComment() {
        String editor = Config.getEditor();
        if ((editor == null) || editor.isEmpty()) {
            Output.print("^red^No editor defined within ~/.wrk/config, add an editor. For instance;^r^");
            Output.print("^b^{ \"color\": true, \"editor\": \"emacs\", \"editorOpts\": \"-nw -Q\" }^r^");
            System.exit(1);
        }
        try {
            File temp = File.createTempFile("wrk", ".comment");
            String editorOptions = Config.getEditorOpts();
            String editorCommand = String.format("%s %s %s < /dev/tty > /dev/tty", editor, editorOptions, temp.getPath());
            Process process = new ProcessBuilder("/bin/sh", "-c", editorCommand).redirectErrorStream(true).start();
            int result = process.waitFor();
            if (result != 0) {
                Output.print("^red^Could not execute edit command^r^");
                Output.print("^red^^i^%s^r^", editorCommand);
                Output.print("^red^Editor exit code %d^r^", result);
                Output.print("^red^Check editor value within ~/.wrk/config. For instance;^r^");
                Output.print("^b^{ \"color\": true, \"editor\": \"emacs\", \"editorOpts\": \"-nw -Q\" }^r^");
                System.exit(result);
            }
            Scanner scanner = new Scanner(temp).useDelimiter("\\Z");
            if (!scanner.hasNext()) {
                return "";
            }
            return scanner.next();
        } catch (IOException ioe) {
            Output.print(ioe);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.exit(1); return null;
    }

    @Override protected Map<String, String> _run() {
        Output.print(description);
        Map<String, Object> result = RestTemplate.post(url, new TypeReference<Map<String, Object>>() {
        });
        if (result == null) {
            Output.print("  ^red^Invalid id or insufficient privileges.^r^");
        } else {
            Output.print("  ^b^Commented!^r^", result);
        }
        return Collections.emptyMap();
    }

    @Override protected boolean valid() {
        return (url != null);
    }

    @Override protected String getCommandName() {
        return "comment";
    }
}
