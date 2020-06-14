package sv.hawkframework.com.ORM.Validations;

import java.util.Locale;


public class LocaleClient {

	 
	 public static String getPathMessages() {
		 
		 String path = System.getProperty("user.dir");
		 path += "\\src\\main\\resources\\messages_es.properties";
		 
		 return path;
	 }
	 
	 public static Locale getLocale() {		 
		 return  new Locale("es");
	 }
	 
	 
	 
	 
}
