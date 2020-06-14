package sv.hawkframework.com.ORM.MySQLConverter;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import sv.hawkframework.com.ORM.Annotations.FieldName;

public class SerializeSingleObject {

	private Object object;
	private ArrayList<PairObjects> pairs;
	private Object value;
	private String typeOf;
		
	public SerializeSingleObject(Object object, ArrayList<PairObjects> pairs) {
		this.object = object;
		this.pairs = pairs;
	}


	public  Object execute () {
		
		Object response = null;
		Class<? extends Object> classObject = object.getClass();
		Constructor<?>[] constructors = classObject.getConstructors();
		Field[] fields = classObject.getDeclaredFields();
				
				
		for (Constructor<?> construct : constructors) {
			
			if (construct.getParameterTypes().length == 0) {
				try {
					response = construct.newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		for (Field field : fields) {
			
			FieldName fieldNameAnn=(FieldName) field.getAnnotation(FieldName.class); 
			String fieldName = field.getName();
			String key = "";
			String methodName = "set"+fieldName.substring(0,1).toUpperCase()+fieldName.substring(1,fieldName.length()-1);
			Method method;
			
			
			if (fieldNameAnn!=null)
				key = fieldNameAnn.name();
			else
				key = fieldName;
			
			System.out.println(methodName);
			this.setParameters(key);
	        Class[] args = new Class[1];
	        
	        if (this.typeOf.equals("java.lang.String")) {
	        	args[0] = java.lang.String.class;
	        }
	        
	        try {
				method = classObject.getMethod(methodName, args);
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				System.out.println("error serializando");
			}
	        
			
				
			
		}
		
		
		
		return response;	
	}
	
	
	private void setParameters(String key) {
		
		for (PairObjects pair : this.pairs) { 
			if (key.equals(pair.getKey())) {
				this.value = pair.getValue();
				this.typeOf = pair.getTypeOf();
				return;
			}	
		}
	}
	
	
	
	
	
	
}
