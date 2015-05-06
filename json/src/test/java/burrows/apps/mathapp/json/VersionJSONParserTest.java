package burrows.apps.mathapp.json;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Unit test for the VersionJSONParser
 */
public class VersionJSONParserTest {

    private static final String TEST_DATA = "{\"version\":1}";
    private static final int EXPECTED_VERSION = 1;

    @Test
    public void testParseData() {
        final VersionJSONParser jsonParser = new VersionJSONParser(TEST_DATA);
        Assert.assertEquals(EXPECTED_VERSION, jsonParser.getVersionNumber());
    }
}