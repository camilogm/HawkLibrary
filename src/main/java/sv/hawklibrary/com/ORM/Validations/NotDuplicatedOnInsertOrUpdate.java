package sv.hawklibrary.com.ORM.Validations;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import sv.hawklibrary.com.ORM.TablesDataProperties;
import sv.hawklibrary.com.ORM.Annotations.NotDuplicated;
import sv.hawklibrary.com.ORM.Annotations.PrimaryKey;
import sv.hawklibrary.com.ORM.Validations.Interfaces.INotDuplicatedField;
import sv.hawklibrary.com.validators.NotDuplicatedException;

public class NotDuplicatedOnInsertOrUpdate implements INotDuplicatedField{

	private QuerysCheck querysCheck = new QuerysCheck();;
	
	public NotDuplicatedOnInsertOrUpdate() {
		
	}
	
	
	@Override
	public Boolean notDuplicatedValidationInsert(Object object, Boolean isUpdate) throws NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, FileNotFoundException,
	IOException, SQLException , NullPointerException {
		 
		 Class<? extends Object> classProperties = object.getClass();		 
		 Field[] fields=classProperties.getDeclaredFields();
		 Boolean auxiliar=false;
		 NotDuplicatedException ex=new NotDuplicatedException("Valores duplicados");

	    		
		

		 String tableName=TablesDataProperties.getTableName(object);
		 String idName=TablesDataProperties.getIdName(object);
		 String primaryKeyValue=null;
			 
			 
			 //determines the value of primary key if the request is update, don't happens is the request is  insert
			 if (isUpdate) {
				 Field primaryKeyField =null;
				 
				 for (Field field:fields) {
					 
					 PrimaryKey primaryKeyAnn=(PrimaryKey) field.getAnnotation(PrimaryKey.class);
					 if (primaryKeyAnn!=null) {
						 primaryKeyField=field;
						 break;
					 }
				 }
				 if (primaryKeyField!=null)
					 primaryKeyValue=GettersSetters.getPrimaryKeyValue(primaryKeyField, classProperties, object);
			 }
			 
			 
			 
	         for (Field field:fields) {
            		  
	       	     NotDuplicated notDuplicated=(NotDuplicated) field.getAnnotation(NotDuplicated.class);
	       	     	  
	       		 if (notDuplicated!=null) {
	       			       		
	       			String fieldName=field.getName(); 
		       		Method method=GettersSetters.getGetterMethod(field, classProperties);
		       		String fieldValue=method.invoke(object, new Object[0]).toString();
		       		
		       		Boolean check=querysCheck.checkDuplicateValues(idName, tableName, fieldName, fieldValue,primaryKeyValue);
		       				       		
		       		if (check) {
		       			
		       			String key=notDuplicated.message();
		       			fieldName=FieldProperties.getMessage(field, tableName, fieldName);
		       			
		       			ex.addFieldError(field.getName(), 
		       					PropertiesLoad.getProperty(key,
		       							new String[] {fieldName}));
		       			
		       			auxiliar=true;
		       		}	       			       			       		
	       		 }
	         }
	         
		   
	  
		  if (auxiliar)
  			throw ex;
		  else
			  return true;
		  
         }	 
      

}
