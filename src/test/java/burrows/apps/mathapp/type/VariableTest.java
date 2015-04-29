package burrows.apps.mathapp.type;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests for Variable class
 */
public class VariableTest {

    /**
     * Constants
     */
    private static final String NAME = "name";
    private static final String OTHER_NAME = "otherName";
    private static final String SYMBOL = "symbol";
    private static final String OTHER_SYMBOL = "otherSymbol";
    private static final String EXPRESSION = "expression";
    private static final String OTHER_EXPRESSION = "otherExpression";
    private static final String EXPECTED_STRING = "symbol(name) = expression";

    @Test
    public void testBuilder() {
        final Variable var = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();

        Assert.assertEquals(NAME, var.getName());
        Assert.assertEquals(SYMBOL, var.getSymbol());
        Assert.assertEquals(EXPRESSION, var.getExpression());
    }

    @Test
    public void testEquals() {
        final Variable var1 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();
        final Variable var2 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();

        Assert.assertTrue(var1.equals(var2));
    }

    @Test
    public void testEqualsDifferentName() {
        final Variable var1 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();
        final Variable var2 = new Variable.Builder()
                .withName(OTHER_NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();

        Assert.assertFalse(var1.equals(var2));
    }

    @Test
    public void testEqualsDifferentSymbol() {
        final Variable var1 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();
        final Variable var2 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(OTHER_SYMBOL)
                .withExpression(EXPRESSION)
                .build();

        Assert.assertFalse(var1.equals(var2));
    }

    @Test
    public void testEqualsDifferentExpression() {
        final Variable var1 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();
        final Variable var2 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(OTHER_EXPRESSION)
                .build();

        Assert.assertFalse(var1.equals(var2));
    }

    @Test
    public void testEqualsNull() {
        final Variable var1 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();

        Assert.assertFalse(var1.equals(null));
    }


    @Test
    public void testEqualsDifferentClass() {
        final Variable var1 = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();

        Assert.assertFalse(var1.equals(new Object()));
    }

    @Test
    public void testToString() {
        final Variable var = new Variable.Builder()
                .withName(NAME)
                .withSymbol(SYMBOL)
                .withExpression(EXPRESSION)
                .build();

        Assert.assertEquals(EXPECTED_STRING, var.toString());
    }
}