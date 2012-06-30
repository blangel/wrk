package net.ocheyedan.wrk;

import org.codehaus.jackson.type.TypeReference;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * User: blangel
 * Date: 6/29/12
 * Time: 9:26 PM
 */
public final class RestTemplate {

    public static <T> T invokeRest(String restfulEndpoint, TypeReference<T> forResultType) {
        T result = null;
        InputStream stream = null;
        try {
            URL url = new URL(restfulEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");

            stream = connection.getInputStream();
            result = Json.mapper().readValue(stream, forResultType);
            connection.getResponseCode();
            connection.disconnect();
        } catch (FileNotFoundException fnfe) {
            return null;
        } catch (IOException ioe) {
            if (ioe.getMessage().startsWith("Server returned HTTP response code: 400")) {
                return result;
            } else {
                Output.print(ioe);
            }
        } catch (Exception e) {
            Output.print(e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException ioe) {
                    Output.print(ioe);
                }
            }
        }
        return result;
    }

    private RestTemplate() { }

}
