package burrows.apps.mathapp.json;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import burrows.apps.mathapp.type.Equation;
import burrows.apps.mathapp.type.Variable;

/**
 * Unit tests for the EquationDataJSONParser
 */
public class EquationDataJSONParserTest {

    private static final String TEST_DATA = "{\"equations\":[{\"name\":\"equation_name\",\"keywords\":[\"this\",\"is\""
            + ",\"an\",\"equation\"],\"variables\":[{\"name\":\"var1\",\"symbol\":\"a\",\"expression\":\"b+c+d\"},"
            + "{\"name\":\"var2\",\"symbol\":\"b\",\"expression\":\"a-c-d\"},{\"name\":\"var3\",\"symbol\":\"c\",\"ex"
            + "pression\":\"a-b-d\"},{\"name\":\"var4\",\"symbol\":\"d\",\"expression\":\"a-b-c\"}]}]}\n";


    @Test
    public void testParseData() {
        final List<Equation> expectedEquations = new ArrayList<>();
        final Variable[] variables = new Variable[] {
                new Variable.Builder()
                        .withName("var1")
                        .withSymbol("a")
                        .withExpression("b+c+d")
                        .build(),
                new Variable.Builder()
                        .withName("var2")
                        .withSymbol("b")
                        .withExpression("a-c-d")
                        .build(),
                new Variable.Builder()
                        .withName("var3")
                        .withSymbol("c")
                        .withExpression("a-b-d")
                        .build(),
                new Variable.Builder()
                        .withName("var4")
                        .withSymbol("d")
                        .withExpression("a-b-c")
                        .build()
        };
        final String[] keywords = new String[] {
                "this",
                "is",
                "an",
                "equation"
        };
        expectedEquations.add(new Equation.Builder()
                .withName("equation_name")
                .withVariable(variables)
                .withKeyWord(keywords)
                .build());

        final EquationDataJSONParser jsonParser = new EquationDataJSONParser(TEST_DATA);
        Assert.assertEquals(expectedEquations, jsonParser.getEquationList());
    }

    @Test
    public void testCache() {
        final EquationDataJSONParser jsonParser = new EquationDataJSONParser(TEST_DATA);
        final List<Equation> firstRun = jsonParser.getEquationList();
        Assert.assertSame(firstRun, jsonParser.getEquationList());
    }
}