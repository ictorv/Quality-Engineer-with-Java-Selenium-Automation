package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FetchProperties {
	public String getPATH(String key) {
		Properties properties = new Properties();
		try (FileInputStream fis = new FileInputStream("UrlData.properties")) {
		    properties.load(fis);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return properties.getProperty(key);	
	}
}
