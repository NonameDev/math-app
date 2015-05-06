package burrows.apps.mathapp.type;

import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents an equation
 */
public class Equation {

    /**
     * Formats used to generate the result of the toString method
     */
    private static final String NAME_FORMAT = "Equation{Name{%s},";
    private static final String VARIABLES_FORMAT = "Variables{%s},";
    private static final String KEYWORDS_FORMAT = "Keywords{%s}}";

    /**
     * Name of the equations
     */
    private final String name;
    /**
     * Variables that make up the equation
     */
    private final Set<Variable> variables;
    /**
     * Keywords that describe the equation
     */
    private final Set<String> keywords;

    /**
     * Constructor. Sets the values of the fields to the given values
     *
     * @param name String containing the name of the equation
     * @param variables Set of Variables that make up the equation
     * @param keywords Set of Strings that describe the equation
     */
    private Equation(final String name,
                     final Set<Variable> variables,
                     final Set<String> keywords) {
        this.name = name;
        this.variables = variables;
        this.keywords = keywords;
    }

    /**
     * Constructor. Sets the values of the fields using the values from
     * the given builder
     *
     * @param builder Builder used to build the equation
     */
    private Equation(final Builder builder) {
        this(builder.name, builder.variables, builder.keywords);
    }

    /**
     * Getter for the name field of the equation
     *
     * @return String containing the name of the equation
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the variables field of the equation
     *
     * @return Set of Variables that make up the equation
     */
    public Set<Variable> getVariables() {
        return this.variables;
    }

    /**
     * Getter for the keywords field of the equation
     *
     * @return Set of Strings that describe the equation
     */
    public Set<String> getKeywords() {
        return this.keywords;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append(String.format(NAME_FORMAT, this.name));
        stringBuilder.append(String.format(VARIABLES_FORMAT, this.variables.toString()));
        stringBuilder.append(String.format(KEYWORDS_FORMAT, this.keywords.toString()));
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equation equation = (Equation) o;

        if (!keywords.equals(equation.keywords)) return false;
        if (!name.equals(equation.name)) return false;
        if (!variables.equals(equation.variables)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + variables.hashCode();
        result = 31 * result + keywords.hashCode();
        return result;
    }

    /**
     * Builder class for the Equation class
     */
    public static class Builder {

        /**
         * Name of the equation being built
         */
        private String name;
        /**
         * Variables of the Equation being built
         */
        private Set<Variable> variables;
        /**
         * Keywords for the equation of the variable being built
         */
        private Set<String> keywords;

        /**
         * Constructor. Initializes the variables and keyword fields
         */
        public Builder() {
            this.variables = new HashSet<>();
            this.keywords = new HashSet<>();
        }

        /**
         * Sets the name field to the given name
         *
         * @param name String containing the name of the equation
         * @return Builder current instance of the Builder
         */
        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        /**
         * Adds the given variable to the variables field
         *
         * @param variable Variable which will be added to the variables field
         * @return Builder current instance of the builder
         */
        public Builder withVariable(final Variable ... variable) {
            for (final Variable var : variable) {
                this.variables.add(var);
            }
            return this;
        }

        /**
         * Adds the kiven keyword/keywords to the keywords field
         *
         * @param keyWord Strings containing keywords that will be added to
         *                the keywords field
         * @return Builder current instance of the builder
         */
        public Builder withKeyWord(final String ... keyWord) {
            for (final String k : keyWord) {
                this.keywords.add(k);
            }
            return this;
        }

        /**
         * Finalizes the building of the equation
         *
         * @return Equation built using the values of the builder
         */
        public Equation build() {
            return new Equation(this);
        }
    }
}