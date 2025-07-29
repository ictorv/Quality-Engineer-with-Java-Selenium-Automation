package utilities;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;

public class JSONUtilities{
	
	
	
	public JSONObject readJSONData(String filePath) throws Exception {
		String content = new String(Files.readAllBytes(Paths.get(filePath)));
		
		JSONObject jsonObject = new JSONObject(content);
		
		
		return jsonObject;
	}		
}
