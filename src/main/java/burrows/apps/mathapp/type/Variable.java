package burrows.apps.mathapp.type;


import android.support.annotation.NonNull;

import java.lang.Override;
import java.lang.String;

/**
 * Class which represents a variable in an equation.
 */
public class Variable {

    /**
     * Format used to generate the string for the toString method
     */
    private static final String TO_STRING_FORMAT = "%s(%s) = %s";

    /**
     * Name of the variable
     */
    private final String name;
    /**
     * Symbol representing the variable
     */
    private final String symbol;
    /**
     * Expression used to solve the variable
     */
    private final String expression;

    /**
     * Constructor. Sets the fields using the given values
     *
     * @param name String containing the name of the variable
     * @param symbol String containing the symbol of the variable
     * @param expression String containing the expression to solve for the
     *                   variable
     */
    private Variable(@NonNull final String name,
                     @NonNull final String symbol,
                     @NonNull final String expression) {
        this.name = name;
        this.symbol = symbol;
        this.expression = expression;
    }

    /**
     * Constructor, Sets the fields from the values in the builder
     *
     * @param builder Builder used to build the variable
     */
    private Variable(@NonNull final Builder builder) {
        this(builder.name, builder.symbol, builder.epxression);
    }

    /**
     * Getter for the name of the variable
     *
     * @return String containing the name of the variable
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the symbol of the variable
     *
     * @return String containing the symbol for the variable
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Getter for the expression of the variable
     *
     * @return String containing the expression used to solve for the variable
     */
    public String getExpression() {
        return this.expression;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, symbol, name, expression);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Variable variable = (Variable) o;

        if (!expression.equals(variable.expression)) return false;
        if (!name.equals(variable.name)) return false;
        if (!symbol.equals(variable.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + symbol.hashCode();
        result = 31 * result + expression.hashCode();
        return result;
    }

    /**
     * Builder class for the Variable class
     */
    static class Builder {
        /**
         * Name of the Variable being built
         */
        private String name;
        /**
         * Symbol of the Variable being built
         */
        private String symbol;
        /**
         * Expression of the Variable being built
         */
        private String epxression;

        /**
         * Sets the name field to the given name
         *
         * @param name String containing the new value for the name field
         * @return Builder current instance of the builder
         */
        public Builder withName(@NonNull final String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the symbol field to the given symbol
         *
         * @param symbol String containing the symbol of the variable
         * @return Builder current instance of the builder
         */
        public Builder withSymbol(@NonNull final String symbol) {
            this.symbol = symbol;
            return this;
        }

        /**
         * Sets the expression field to the given expression
         *
         * @param expression String containing the expression to solve for the
         *                   variable
         * @return Builder current instance of the builder
         */
        public Builder withExpression(@NonNull final String expression) {
            this.epxression = expression;
            return this;
        }

        /**
         * Finalizes the building of the Variable
         *
         * @return Variable built using the values of the builder
         */
        public Variable build() {
            return new Variable(this);
        }
    }
}
