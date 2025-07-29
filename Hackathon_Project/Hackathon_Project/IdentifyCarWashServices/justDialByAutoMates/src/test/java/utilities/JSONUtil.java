package utilities;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONUtil {

    public String[][] TC006JSON() {
        String[][] data = null;

        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(".\\testData\\Identify-Car-Wash-Services_TestDataJSON"));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray searchTerms = (JSONArray) jsonObject.get("search_terms");

            data = new String[searchTerms.size()][1];
            for (int i = 0; i < searchTerms.size(); i++) {
            	data[i][0] = searchTerms.get(i).toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
       
    }


