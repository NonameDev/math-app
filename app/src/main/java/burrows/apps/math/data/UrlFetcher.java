package burrows.apps.math.data;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class used to fetch the contents of a url
 */
public class UrlFetcher {

    /**
     * Log messages
     */
    private static final String LOG_FAILED_CLOSING_READER = "Failed closing reader";
    private static final String LOG_MALFORMED_URL = "Failed creating url with given string: %s";
    private static final String LOG_FAILED_FETCHING_CONTENTS = "Failed gettings contents for url: %s";

    /**
     * Fetches the contents of the given url
     *
     * @param stringUrl String containing the url whose contents will be
     *                  fetched
     * @return String containing the contents of the given url
     */
    public String fetchUrl(final String stringUrl) {
        try {
            final URL url = new URL(stringUrl);
            return this.fetchUrl(url);
        } catch (final MalformedURLException e) {
            Log.d(this.getClass().getSimpleName(), String.format(LOG_MALFORMED_URL, stringUrl), e);
            return null;
        }
    }

    /**
     * Fetches the contents of the given url
     *
     * @param url URL whose contents will be fetched
     * @return String containing the contents of the given url
     */
    public String fetchUrl(final URL url) {
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStreamReader = new InputStreamReader(url.openStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            final StringBuilder stringBuilder = new StringBuilder();
            String buffer;
            while ((buffer = bufferedReader.readLine()) != null) {
                stringBuilder.append(buffer);
            }
            return stringBuilder.toString();
        } catch (final IOException e) {
            Log.d(this.getClass().getSimpleName(),
                    String.format(LOG_FAILED_FETCHING_CONTENTS, url.toExternalForm()), e);
            return null;
        } finally {
            this.closeReader(inputStreamReader);
            this.closeReader(bufferedReader);
        }
    }

    /**
     * Closes the given reader
     *
     * @param reader Reader which will be closed
     */
    private void closeReader(final Reader reader) {
        if (reader != null) {
            try {
                reader.close();
            } catch (final IOException e) {
                Log.d(this.getClass().getSimpleName(), LOG_FAILED_CLOSING_READER, e);
            }
        }
    }
}