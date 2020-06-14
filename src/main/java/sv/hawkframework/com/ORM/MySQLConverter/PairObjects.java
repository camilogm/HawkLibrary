package sv.hawkframework.com.ORM.MySQLConverter;

import java.io.Serializable;

class PairObjects implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String key;
	private final Object value;
	private final String typeOf;
	
	public PairObjects(String key, Object value, String typeOf) {
		super();
		this.key = key;
		this.value = value;
		this.typeOf = typeOf;
	}

	public String getKey() {
		return key;
	}

	public Object getValue() {
		return value;
	}

	public String getTypeOf() {
		return typeOf;
	}
	
	
	
	
	
}
