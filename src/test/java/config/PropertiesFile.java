package config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesFile {

	private static Properties prop = new Properties();
	private static String propertiesFilePath = "./src/test/java/config/config.properties";
	
	public static String getProperty(String property) throws Exception {

		FileInputStream input = new FileInputStream(propertiesFilePath);
		prop.load(input);
		input.close();
		return prop.getProperty(property);
	}
	
	/*public static void setProperties(String property, String value) throws Exception{

			OutputStream output = new FileOutputStream(propertiesFilePath);
			prop.setProperty(property, value);
			prop.store(output, null);
			output.close();
	}*/
}
