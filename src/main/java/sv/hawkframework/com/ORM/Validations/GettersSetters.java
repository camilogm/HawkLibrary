package sv.hawkframework.com.ORM.Validations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 
 * @author Camilo González
 * @version 1.0
 */
public class GettersSetters {

	
	/**
	 * 
	 * @param field
	 * @param classProperties
	 * @return the method get of a property in the class. The property and the method have to the standar of getter method.
	 * Example: property: name		getter method: getName()
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 */
	public static final Method getGetterMethod(Field field,Class<? extends Object> classProperties) throws NoSuchMethodException, SecurityException {
		
		String fieldName=field.getName();
   		String methodGet="get"+fieldName.substring(0,1).toUpperCase();
   		methodGet+=fieldName.substring(1,fieldName.length());            	            		
   		Method method=classProperties.getMethod(methodGet);    
   		return method;
		
	}
	/**
	 * 
	 * @param field
	 * @param classProperties
	 * @param object
	 * @return the property message with the values 
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static final String getPrimaryKeyValue(Field field,
			Class<? extends Object> classProperties,
			Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method method=GettersSetters.getGetterMethod(field, classProperties);		
		return method.invoke(object, new Object[0]).toString();
		
	}
	
	
	
}
