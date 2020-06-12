package sv.hawkframework.com.ORM.Validations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
public class PropertiesLoad {

	private static Properties properties = new Properties();
	
	public PropertiesLoad() throws FileNotFoundException, IOException {		
		properties.load(new FileReader(LocaleClient.getPathMessages()));
	}
	
	private static  void initializateProperitesFile() throws FileNotFoundException, IOException {
		
		properties.load(new FileReader(LocaleClient.getPathMessages()));
		
	}
	
	
	
	public static String getProperty(String key) throws FileNotFoundException, IOException {

		
	
		PropertiesLoad.initializateProperitesFile();		
		return properties.getProperty(key);
		
	}
	
	public static String getProperty(String key,String[] fields) throws FileNotFoundException, IOException {
		
		
		PropertiesLoad.initializateProperitesFile();
		String message=properties.getProperty(key);		
		for (int i=0;i<fields.length;i++) {
			String space="{"+(i+1)+"}";
			message=message.replace(space, fields[i]);			
		}
		return message;		
	}
	

	
	
	
}
