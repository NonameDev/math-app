package burrows.apps.mathapp.json;

import org.json.JSONObject;

import static burrows.apps.mathapp.json.Constants.VERSION_KEY;

/**
 * Class in charge of parsing the version json file
 */
public class VersionJSONParser {

    /**
     * JSONObject holding all the data in the file
     */
    private JSONObject versionJsonObject;

    /**
     * Constructor. Initializes the versionJsonObject using the given data
     *
     * @param versionContent String containing the contents of the version
     *                       json file
     */
    public VersionJSONParser(final String versionContent) {
        this.versionJsonObject = new JSONObject(versionContent);
    }

    /**
     * Extracts and returns the version number from the file
     *
     * @return int represention the version of the data
     */
    public int getVersionNumber() {
        return this.versionJsonObject.getInt(VERSION_KEY);
    }
}