package org.butuka.parse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by iagobelo on 22/06/16.
 */
public class JSONParser {
    private static final String TAG = "JSONParserLog";
    private JSONObject mJsonObject;
    private JSONArray mJsonArray;

    public JSONParser() {

    }

    public int parseResult(String jsonString) throws JSONException {
        mJsonObject = new JSONObject(jsonString);
        return mJsonObject.getInt("result");
    }
}
