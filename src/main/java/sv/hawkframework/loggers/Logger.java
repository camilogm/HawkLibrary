package sv.hawkframework.loggers;

public interface Logger {

	void error(Object message);
	void error(Object message, Object information);
	void info(Object message);
	void info(Object message,Object information);
}
