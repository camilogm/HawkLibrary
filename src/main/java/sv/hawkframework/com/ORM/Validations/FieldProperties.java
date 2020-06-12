package sv.hawkframework.com.ORM.Validations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;

import sv.hawkframework.com.ORM.Annotations.FieldName;

public class FieldProperties {

	public static final String getMessage(Field field,String tableName,String fieldName) throws FileNotFoundException, IOException {
		
		FieldName fieldNameAnn=(FieldName) field.getAnnotation(FieldName.class);  	     
		String fieldName1=fieldNameAnn==null ? 
					tableName+"."+fieldName :
						tableName+"."+fieldNameAnn.name();		      	
		fieldName=PropertiesLoad.getProperty(fieldName1);
			
		if (fieldName==null)
				fieldName=field.getName();
		return fieldName;
	}
}
