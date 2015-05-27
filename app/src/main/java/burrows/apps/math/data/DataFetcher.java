package burrows.apps.math.data;

import java.util.List;

import burrows.apps.mathapp.json.EquationDataJSONParser;
import burrows.apps.mathapp.json.VersionJSONParser;
import burrows.apps.mathapp.type.Equation;

/**
 * Class in charge of downloading the data from the repository.
 */
public class DataFetcher {

    /**
     * Url used to download the version data
     */
    private static final String VERSION_URL =
            "https://raw.githubusercontent.com/NonameDev/MathAppData/master/data/version.json";
    /**
     * Url used to download the equation data
     */
    private static final String EQUATION_DATA_URL =
            "https://raw.githubusercontent.com/NonameDev/MathAppData/master/data/equation_data.json";

    /**
     * Client used to download the data
     */
    private UrlFetcher urlFetcher;

    /**
     * Default constructor. Creates a new UrlFetcher and sets the urlFetcher
     * field to it.
     */
    public DataFetcher() {
        this(new UrlFetcher());
    }

    /**
     * Constructor. Sets the httpClient field to the given OkHttpClient
     *
     * @param urlFetcher UrlFetcher that will be used by the class
     */
    public DataFetcher(final UrlFetcher urlFetcher) {
        this.urlFetcher = urlFetcher;
    }

    /**
     * Fetches for the equation data and parses it into java objects
     *
     * @return List of equations stored in the repository
     */
    public List<Equation> getEquations() {
        final String rawEquationData = this.urlFetcher.fetchUrl(EQUATION_DATA_URL);
        System.out.println(rawEquationData);
        if (rawEquationData == null) {
            return null;
        } else {
            final EquationDataJSONParser jsonParser = new EquationDataJSONParser(rawEquationData);
            return jsonParser.getEquationList();
        }
    }

    /**
     * Checks the current version of the data stored in the repository
     *
     * @return integer representing the current version of the data stored in
     *         the repository
     */
    public Integer getCurrentVersion() {
        final String rawVersionData = this.urlFetcher.fetchUrl(VERSION_URL);
        if (rawVersionData == null) {
            return null;
        } else {
            final VersionJSONParser jsonParser = new VersionJSONParser(rawVersionData);
            return jsonParser.getVersionNumber();
        }
    }
}