package com.urban.utillities;

import java.io.FileReader;
import java.util.Properties;

public class PropertiesReader {
	static Properties p;
	static String propertiesPath = System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties";
	public static Properties getProperties() throws Exception {
		FileReader file = new FileReader(propertiesPath);
		p = new Properties();
		p.load(file);
		return p;
	}

}
