package burrows.apps.mathapp.type;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class EquationTest {

    private static final String EXPECTED_STRING = "Equation{Name{name},Variables{[symbol2(name2) = expression2, " +
            "symbol1(name1) = expression1, symbol0(name0) = expression0]},ImageKey{imageKey0},Category{category0},Keywords{[keyword1, keyword2, keyword0]}}";
    private static final String NAME = "name";
    private static final String OTHER_NAME = "otherName";
    private static final String CATEGORY_0 = "category0";
    private static final String CATEGORY_1 = "category1";
    private static final String IMAGE_KEY_0 = "imageKey0";
    private static final String IMAGE_KEY_1 = "imageKey1";
    private static final String KEYWORD_0 = "keyword0";
    private static final String KEYWORD_1= "keyword1";
    private static final String KEYWORD_2 = "keyword2";
    private static final String NAME_0 = "name0";
    private static final String SYMBOL_0 = "symbol0";
    private static final String EXPRESSION_0 = "expression0";
    private static final String NAME_1 = "name1";
    private static final String SYMBOL_1 = "symbol1";
    private static final String EXPRESSION_1 = "expression1";
    private static final String NAME_2 = "name2";
    private static final String SYMBOL_2= "symbol2";
    private static final String EXPRESSION_2 = "expression2";
    private static final Variable VAR_0 = new Variable.Builder()
            .withName(NAME_0)
            .withSymbol(SYMBOL_0)
            .withExpression(EXPRESSION_0)
            .build();
    private static final Variable VAR_1 = new Variable.Builder()
            .withName(NAME_1)
            .withSymbol(SYMBOL_1)
            .withExpression(EXPRESSION_1)
            .build();
    private static final Variable VAR_2 = new Variable.Builder()
            .withName(NAME_2)
            .withSymbol(SYMBOL_2)
            .withExpression(EXPRESSION_2)
            .build();

    private static Set<String> expectedKeywords;
    private static Set<Variable> expectedVariables;

    @BeforeClass
    public static void classSetup() {
        expectedKeywords = new HashSet<>();
        expectedVariables = new HashSet<>();
    }

    @Before
    public void setup() {
        expectedKeywords.clear();
        expectedVariables.clear();
    }

    @Test
    public void testBuilder() {
        expectedKeywords.add(KEYWORD_0);
        expectedKeywords.add(KEYWORD_1);
        expectedKeywords.add(KEYWORD_2);

        expectedVariables.add(VAR_0);
        expectedVariables.add(VAR_1);
        expectedVariables.add(VAR_2);

        final Equation eqn = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertEquals(NAME, eqn.getName());
        Assert.assertEquals(CATEGORY_0, eqn.getCategory());
        Assert.assertEquals(IMAGE_KEY_0, eqn.getImageKey());
        Assert.assertEquals(expectedKeywords, eqn.getKeywords());
        Assert.assertEquals(expectedVariables, eqn.getVariables());
    }

    @Test
    public void testEquals() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .withVariable(VAR_2)
                .withVariable(VAR_2)
                .build();

        final Equation eqn2 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertTrue(eqn1.equals(eqn2));
    }

    @Test
    public void testEqualsDifferentName() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        final Equation eqn2 = new Equation.Builder()
                .withName(OTHER_NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertFalse(eqn1.equals(eqn2));
    }


    @Test
    public void testEqualsDifferentImageKey() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        final Equation eqn2 = new Equation.Builder()
                .withName(OTHER_NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_1)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertFalse(eqn1.equals(eqn2));
    }


    @Test
    public void testEqualsDifferentCategory() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        final Equation eqn2 = new Equation.Builder()
                .withName(OTHER_NAME)
                .withCategory(CATEGORY_1)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertFalse(eqn1.equals(eqn2));
    }

    @Test
    public void testEqualsDifferentKeywords() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        final Equation eqn2 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertFalse(eqn1.equals(eqn2));
    }

    @Test
    public void testEqualsDifferentVariables() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        final Equation eqn2 = new Equation.Builder()
                .withName(OTHER_NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .build();

        Assert.assertFalse(eqn1.equals(eqn2));
    }

    @Test
    public void testEqualsDifferentNull() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertFalse(eqn1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        final Equation eqn1 = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertFalse(eqn1.equals(new Object()));
    }

    @Test
    public void testToString() {
        final Equation eqn = new Equation.Builder()
                .withName(NAME)
                .withCategory(CATEGORY_0)
                .withImageKey(IMAGE_KEY_0)
                .withKeyWord(KEYWORD_0)
                .withKeyWord(KEYWORD_1, KEYWORD_2)
                .withVariable(VAR_0)
                .withVariable(VAR_1)
                .withVariable(VAR_2)
                .build();

        Assert.assertEquals(EXPECTED_STRING, eqn.toString());
    }
}
