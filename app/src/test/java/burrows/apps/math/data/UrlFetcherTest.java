package burrows.apps.math.data;

import android.util.Log;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Unit test for the UrlFetcher class
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({UrlFetcher.class,
        URL.class,
        InputStreamReader.class,
        BufferedReader.class,
        BufferedInputStream.class,
        Log.class})
public class UrlFetcherTest {

    /**
     * Constants
     */
    private static final String URL = "url";
    private static final String LINE_1 = "line1";
    private static final String LINE_2 = "line2";
    private static final String RESULT = LINE_1 + LINE_2;
    private static final String LOG_FAILED_CLOSING_READER = "Failed closing reader";
    private static final String LOG_MALFORMED_URL = "Failed creating url with given string: %s";
    private static final String LOG_FAILED_FETCHING_CONTENTS = "Failed gettings contents for url: %s";

    /**
     * Mocked Objects
     */
    private URL mockUrl;
    private InputStreamReader mockStreamReader;
    private  BufferedReader mockBufferedReader;
    private InputStream mockInputStream;

    @Before
    public void setup() {
        PowerMock.mockStatic(Log.class);

        this.mockUrl = PowerMock.createMock(URL.class);
        this.mockStreamReader = EasyMock.createMock(InputStreamReader.class);
        this.mockBufferedReader = EasyMock.createMock(BufferedReader.class);
        this.mockInputStream = EasyMock.createMock(BufferedInputStream.class);
    }

    @After
    public void cleanup() {
        this.verifyMocks();
    }

    @Test
    public void testFetchUrl() throws Exception {
        this.setupFetchUrl(false, false, false, false);

        this.replayMocks();
        final UrlFetcher urlFetcher = new UrlFetcher();
        Assert.assertEquals(RESULT, urlFetcher.fetchUrl(this.mockUrl));
    }

    @Test
    public void testFetchUrlWithString() throws Exception {

        PowerMock.expectNew(URL.class, URL).andReturn(this.mockUrl);
        this.setupFetchUrl(false, false, false, false);

        this.replayMocks();
        final UrlFetcher urlFetcher = new UrlFetcher();
        Assert.assertEquals(RESULT, urlFetcher.fetchUrl(URL));
    }

    @Test
    public void testFetchUrlWithStringMalformedUrl() throws Exception {
        final MalformedURLException exception = new MalformedURLException();
        PowerMock.expectNew(URL.class, URL).andThrow(exception);
        this.mockLog(String.format(LOG_MALFORMED_URL, URL), exception);

        this.replayMocks();
        final UrlFetcher urlFetcher = new UrlFetcher();
        Assert.assertNull(urlFetcher.fetchUrl(URL));
    }

    @Test
    public void testFetchUrlFailOpenStream() throws Exception {
        this.setupFetchUrl(true, false, false, false);

        this.replayMocks();
        final UrlFetcher urlFetcher = new UrlFetcher();

        Assert.assertNull(urlFetcher.fetchUrl(this.mockUrl));
    }

    @Test
    public void testFetchUrlFailReadLine() throws Exception {
        this.setupFetchUrl(false, true, false, false);

        this.replayMocks();
        final UrlFetcher urlFetcher = new UrlFetcher();

        Assert.assertNull(urlFetcher.fetchUrl(this.mockUrl));
    }


    @Test
    public void testFetchUrlFailClosingReaders() throws Exception {
        this.setupFetchUrl(false, false, true, true);

        this.replayMocks();
        final UrlFetcher urlFetcher = new UrlFetcher();
        Assert.assertEquals(RESULT, urlFetcher.fetchUrl(this.mockUrl));
    }

    /**
     * Sets up the mocks for the fetchUrl method
     *
     * @param failOpenStream boolean indicating whether the openSteam method
     *                       will fail
     * @param failReadline boolean indicating whether the readLine method
     *                     will fail
     * @param failInputClose boolean indicating whether the close call on the
     *                       InputStreamReader will fail
     * @param failBufferedClose boolean indicating whether the close call on
     *                          the BufferedReader will fail
     * @throws Exception
     */
    private void setupFetchUrl(final boolean failOpenStream,
                               final boolean failReadline,
                               final boolean failInputClose,
                               final boolean failBufferedClose) throws Exception {
        if (failOpenStream) {
            final Exception exception =  new IOException();
            EasyMock.expect(this.mockUrl.openStream()).andThrow(exception);
            EasyMock.expect(this.mockUrl.toExternalForm()).andReturn(URL);
            this.mockLog(String.format(LOG_FAILED_FETCHING_CONTENTS, URL), exception);
            return;
        } else {
            EasyMock.expect(this.mockUrl.openStream()).andReturn(this.mockInputStream);
        }
        PowerMock.expectNew(InputStreamReader.class, this.mockInputStream).andReturn(this.mockStreamReader);
        PowerMock.expectNew(BufferedReader.class, this.mockStreamReader).andReturn(this.mockBufferedReader);
        if (failReadline) {
            final Exception exception =  new IOException();
            EasyMock.expect(this.mockBufferedReader.readLine()).andThrow(exception);
            EasyMock.expect(this.mockUrl.toExternalForm()).andReturn(URL);
            this.mockLog(String.format(LOG_FAILED_FETCHING_CONTENTS, URL), exception);
        } else {
            EasyMock.expect(this.mockBufferedReader.readLine()).andReturn(LINE_1);
            EasyMock.expect(this.mockBufferedReader.readLine()).andReturn(LINE_2);
            EasyMock.expect(this.mockBufferedReader.readLine()).andReturn(null);
        }
        this.setupClose(failInputClose, failBufferedClose);
    }

    /**
     * Sets up the mocks for the closeReader method calls
     *
     * @param failInput boolean indicating whether the close call on the
     *                  InputStreamReader will fail
     * @param failBuffered boolean indicating whether the close call on the
     *                     BufferedReader will fail
     * @throws Exception
     */
    private void setupClose(final boolean failInput, final boolean failBuffered) throws Exception {
        this.mockStreamReader.close();
        if (failInput) {
            final Exception exception =  new IOException();
            EasyMock.expectLastCall().andThrow(exception);
            this.mockLog(LOG_FAILED_CLOSING_READER, exception);
        } else {
            EasyMock.expectLastCall();
        }
        this.mockBufferedReader.close();
        if (failBuffered) {
            final Exception exception =  new IOException();
            EasyMock.expectLastCall().andThrow(exception);
            this.mockLog(LOG_FAILED_CLOSING_READER, exception);
        } else {
            EasyMock.expectLastCall();
        }
    }

    /**
     * Mocks out the Log call when an IOExeption is thrown
     *
     * @param message String containing the message passed to the log
     * @param exception Exception that will be thrown
     */
    private void mockLog(final String message, final Exception exception) {
        EasyMock.expect(Log.d(UrlFetcher.class.getSimpleName(),
                message,
                exception)).andReturn(1);
    }

    /**
     * Replays all the mocks
     */
    private void replayMocks() {
        PowerMock.replayAll(Log.class,
                this.mockUrl,
                this.mockStreamReader,
                this.mockBufferedReader,
                this.mockInputStream);
    }

    /**
     * Verifies all the mocks
     */
    private void verifyMocks() {
        PowerMock.verifyAll();
    }
}