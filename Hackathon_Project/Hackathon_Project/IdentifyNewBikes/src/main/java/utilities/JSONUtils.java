package utilities;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * A utility class to read from and write to JSON files using the json-simple library.
 */
public class JSONUtils {

    private JSONObject jsonObject;
    private final String filePath;

    
    public JSONUtils(String fileName) {
        // Store the full path for later use (e.g., saving)
        this.filePath = System.getProperty("user.dir") + "//src/test/resources/JSONfiles/"+fileName;
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(this.filePath)) {
            // Parse the JSON file and cast it to a JSONObject
            Object obj = jsonParser.parse(reader);
            this.jsonObject = (JSONObject) obj;
        } catch (IOException e) {
            // If the file is not found, we can start with a new empty object
            System.out.println("File not found at: " + this.filePath + ". A new JSONObject will be created.");
            this.jsonObject = new JSONObject();
        } catch (ParseException e) {
            // Handle JSON parsing errors
            System.err.println("Error parsing JSON file: " + this.filePath);
            e.printStackTrace();
            this.jsonObject = new JSONObject(); // Initialize to avoid NullPointerException
        }
    }

    public String getValue(String key) {
        if (jsonObject == null || !jsonObject.containsKey(key)) {
            System.err.println("Key not found in JSON: " + key);
            return null;
        }
        // Return the value associated with the key
        return (String) jsonObject.get(key);
    }

    
    @SuppressWarnings("unchecked")
    public void setValue(String key, Object value) {
        if (jsonObject == null) {
             this.jsonObject = new JSONObject();
        }
        // Add or update the key-value pair
        jsonObject.put(key, value);
        System.out.println("Set value for key '" + key + "'. Call saveJsonFile() to persist changes.");
    }

    /*
     Writes the current state of the in-memory JSON object back to the original file.
     This will overwrite the existing file.
     */
    public void saveJsonFile() {
        if (jsonObject == null) {
            System.err.println("Cannot save file because JSON object is null.");
            return;
        }
        // Use try-with-resources to automatically close the FileWriter
        try (FileWriter file = new FileWriter(this.filePath)) {
            // Write the JSON object to the file with pretty printing
            file.write(jsonObject.toJSONString());
            file.flush();
            System.out.println("Successfully saved JSON data to: " + this.filePath);
        } catch (IOException e) {
            System.err.println("Error writing to JSON file: " + this.filePath);
            e.printStackTrace();
        }
    }
}