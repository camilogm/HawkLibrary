package sv.hawkframework.com.ORM.Validations;

import java.util.Locale;


public class LocaleClient {

	 
	 public static String getPathMessages() {
		 
		 String language="messages";
		 String locale = "in";
		 if (!locale.equals("es"))
			 language=language+"_"+locale;

		 try {
			 return "src\\main\\java\\sv\\edu\\udb\\ORM\\Validations\\"+language+".properties";
		 }
		 catch (Exception e) {
			 return "src\\main\\java\\sv\\edu\\udb\\ORM\\Validations\\messages.properties";
		 }
		 	 
	 }
	 
	 public static Locale getLocale() {		 
		 return  new Locale("es");
	 }
	 
	 
	 
	 
}
