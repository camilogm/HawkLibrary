package sv.hawkframework.factorys;

import sv.hawkframework.loggers.Logger;
import sv.hawkframework.loggers.NoLogger;

public class LoggerFactory {

	
	public static Logger getLogger(Class<? extends Object> classObject, Class<? extends Object> typeLogger ) {
		
		String className = typeLogger.getSimpleName();
		
		switch (className) {		
			case "NoLogger":
				return new NoLogger();
			default:
				return null;
		}		
	}
	
}
