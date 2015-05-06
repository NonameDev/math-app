package burrows.apps.mathapp.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import burrows.apps.mathapp.type.Equation;
import burrows.apps.mathapp.type.Variable;

import static burrows.apps.mathapp.json.Constants.EQUATIONS_KEY;
import static burrows.apps.mathapp.json.Constants.EXPRESSION_KEY;
import static burrows.apps.mathapp.json.Constants.KEYWORDS_KEY;
import static burrows.apps.mathapp.json.Constants.NAME_KEY;
import static burrows.apps.mathapp.json.Constants.SYMBOL_KEY;
import static burrows.apps.mathapp.json.Constants.VARIABLES_KEY;

/**
 * Class in charge of parsing the equation data json file
 */
public class EquationDataJSONParser {

    /**
     * JSON object holding all the data in the file
     */
    private JSONObject eqnDataJsonObject;

    private List<Equation> equations;

    /**
     * Constructor. Initializes the eqnDataJsonObject field using the given
     * equation data content
     *
     * @param eqnDataContent String containing the contents of the equation
     *                       data json file
     */
    public EquationDataJSONParser(final String eqnDataContent) {
        this.eqnDataJsonObject = new JSONObject(eqnDataContent);
    }

    /**
     * Parses the equation json data to generate a list of equation objects
     *
     * @return List of equation objects generated from the json data
     */
    public List<Equation> getEquationList() {
        if (this.equations == null) {
            final List<Equation> result = new ArrayList<>();
            final JSONArray rawEquationArray = this.eqnDataJsonObject.getJSONArray(EQUATIONS_KEY);
            final int arrayLength = rawEquationArray.length();
            for(int i = 0; i < arrayLength; i++) {
                final JSONObject rawEquation = rawEquationArray.getJSONObject(i);
                final Equation equation = this.jsonObjectToEquation(rawEquation);
                result.add(equation);
            }
            this.equations = result;
        }
        return this.equations;
    }

    /**
     * Parses the given JSONObject into a Equation object
     *
     * @param rawEquation JSONObject representing an equation
     * @return Equation object generated using the given json data
     */
    private Equation jsonObjectToEquation(final JSONObject rawEquation) {
        final JSONArray rawVariableArray = rawEquation.getJSONArray(VARIABLES_KEY);
        final JSONArray rawKeywordsArray = rawEquation.getJSONArray(KEYWORDS_KEY);
        final String name = rawEquation.getString(NAME_KEY);
        final Variable[] variables = this.jsonArrayToVariableArray(rawVariableArray);
        final String[] keywords = this.jsonArrayToKeywordArray(rawKeywordsArray);
        return new Equation.Builder()
                .withName(name)
                .withVariable(variables)
                .withKeyWord(keywords)
                .build();
    }

    /**
     * Converts the given JSONArray into an array of Variable objects
     *
     * @param rawVariableArray JSONArray representing an array of variables
     * @return Array of Variable objects generated from the given json data
     */
    private Variable[] jsonArrayToVariableArray(final JSONArray rawVariableArray) {
        final int arrayLength = rawVariableArray.length();
        final Variable[] result = new Variable[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            final JSONObject rawVariable = rawVariableArray.getJSONObject(i);
            final Variable variable = this.jsonObjectToVariable(rawVariable);
            result[i] = variable;
        }
        return result;
    }

    /**
     * Converts the given JSONArray into an array of Strings
     *
     * @param rawVariableArray JSONArray representing the list of keywords for
     *                         an equation
     * @return Array of strings containing the keywords for the equation
     *         parsed from the json data
     */
    private String[] jsonArrayToKeywordArray(final JSONArray rawVariableArray) {
        final int arrayLength = rawVariableArray.length();
        final String[] result = new String[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            final String keyword = rawVariableArray.getString(i);
            result[i] = keyword;
        }
        return result;
    }

    /**
     * Converts the given JSONObject into a Variable object
     *
     * @param rawVariable JSONObject representing a variable
     * @return Variable generated from the given json data
     */
    private Variable jsonObjectToVariable(final JSONObject rawVariable) {
        final String variableName = rawVariable.getString(NAME_KEY);
        final String variableSymbol = rawVariable.getString(SYMBOL_KEY);
        final String variableExpression = rawVariable.getString(EXPRESSION_KEY);
        return new Variable.Builder()
                .withName(variableName)
                .withSymbol(variableSymbol)
                .withExpression(variableExpression)
                .build();
    }
}