package sv.hawkframework.loggers;

import java.io.Serializable;

public class NoLogger implements Logger, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void error(Object message) {
		System.out.println(message.toString());
		
	}

	@Override
	public void error(Object message, Object information) {
		System.out.println(message.toString());
		System.out.println(information.toString());
	}

	@Override
	public void info(Object message) {
		System.out.println(message.toString());
	}

	@Override
	public void info(Object message, Object information) {
		System.out.println(message.toString());
		System.out.println(information.toString());
	}

}
