package burrows.apps.math.data;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import burrows.apps.mathapp.json.EquationDataJSONParser;
import burrows.apps.mathapp.json.VersionJSONParser;
import burrows.apps.mathapp.type.Equation;
import burrows.apps.mathapp.type.Variable;

/**
 * Unit test for the DataFetcher class
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DataFetcher.class,
        EquationDataJSONParser.class,
        VersionJSONParser.class})
public class DataFetcherTest {

    /**
     * Constants
     */
    private static final int VERSION_NUM = 1;
    private static final String VERSION_URL =
            "https://raw.githubusercontent.com/NonameDev/MathAppData/master/data/version.json";
    private static final String EQUATION_DATA_URL =
            "https://raw.githubusercontent.com/NonameDev/MathAppData/master/data/equation_data.json";
    private static final String RESPONSE_STRING = "responseString";
    private static final String NAME = "name";
    private static final String KEYWORD = "keyword";
    private static final String IMAGE_KEY= "imageKey";
    private static final String CATEGORY = "category";
    private static final String SYMBOL = "symbol";
    private static final String EXPRESSION = "expression";

    /**
     * Mocked Objects
     */
    private UrlFetcher mockUrlFetcher;
    private EquationDataJSONParser mockEquationJsonParser;
    private VersionJSONParser mockVersionJsonParser;

    @Before
    public void setup() {
        this.mockUrlFetcher = EasyMock.createMock(UrlFetcher.class);
        this.mockEquationJsonParser = EasyMock.createMock(EquationDataJSONParser.class);
        this.mockVersionJsonParser = EasyMock.createMock(VersionJSONParser.class);
    }

    @After
    public void cleanup() {
        this.verifyMocks();
    }

    @Test
    public void testGetEquations() throws Exception {
        final List<Equation> equationList = new ArrayList<>();
        equationList.add(new Equation.Builder()
                .withName(NAME)
                .withKeyWord(KEYWORD)
                .withImageKey(IMAGE_KEY)
                .withCategory(CATEGORY)
                .withVariable(new Variable.Builder()
                        .withExpression(EXPRESSION)
                        .withName(NAME)
                        .withSymbol(SYMBOL)
                        .build())
                .build());

        EasyMock.expect(this.mockUrlFetcher.fetchUrl(EQUATION_DATA_URL)).andReturn(RESPONSE_STRING);
        PowerMock.expectNew(EquationDataJSONParser.class, RESPONSE_STRING).andReturn(this.mockEquationJsonParser);
        EasyMock.expect(this.mockEquationJsonParser.getEquationList()).andReturn(equationList);

        this.replayMocks();
        final DataFetcher dataFetcher = new DataFetcher(this.mockUrlFetcher);
        Assert.assertEquals(equationList, dataFetcher.getEquations());
    }

    @Test
    public void testGetEquationsNullData() throws Exception {
        EasyMock.expect(this.mockUrlFetcher.fetchUrl(EQUATION_DATA_URL)).andReturn(null);

        this.replayMocks();
        final DataFetcher dataFetcher = new DataFetcher(this.mockUrlFetcher);
        Assert.assertNull(dataFetcher.getEquations());
    }

    @Test
    public void testGetVersion() throws Exception {
        EasyMock.expect(this.mockUrlFetcher.fetchUrl(VERSION_URL)).andReturn(RESPONSE_STRING);
        PowerMock.expectNew(VersionJSONParser.class, RESPONSE_STRING).andReturn(this.mockVersionJsonParser);
        EasyMock.expect(this.mockVersionJsonParser.getVersionNumber()).andReturn(VERSION_NUM);

        this.replayMocks();
        final DataFetcher dataFetcher = new DataFetcher(this.mockUrlFetcher);
        Assert.assertEquals(new Integer(VERSION_NUM), dataFetcher.getCurrentVersion());
        this.verifyMocks();
    }

    @Test
    public void testGetVersionNullData() throws Exception {
        EasyMock.expect(this.mockUrlFetcher.fetchUrl(VERSION_URL)).andReturn(null);

        this.replayMocks();
        final DataFetcher dataFetcher = new DataFetcher(this.mockUrlFetcher);
        Assert.assertNull(dataFetcher.getCurrentVersion());
    }

    /**
     * Replays all the mocks
     */
    private void replayMocks() {
        PowerMock.replayAll(this.mockUrlFetcher, this.mockEquationJsonParser, this.mockVersionJsonParser);
    }

    /**
     * Verifies all the mocks
     */
    private void verifyMocks() {
        PowerMock.verifyAll();
    }
}