package sv.hawkframework.com.ORM.QueryConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConexion;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IJsonConvert;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IUpdate;
import sv.hawkframework.com.ORM.Validations.Interfaces.INotDuplicatedField;

public class Update  implements IUpdate  {

	protected IConexion conn;
	protected TablesDataProperties tableDataProperties;
	protected IJsonConvert jsonConvert;
	private final static Logger logger=Logger.getLogger(Update.class);
	
	private INotDuplicatedField notDuplicatedValidation;
	
	
	@Override
	public Boolean update(Object object)  {
		
		   try {
			notDuplicatedValidation.notDuplicatedValidationInsert(object, Boolean.TRUE);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | IOException | SQLException e) {

				logger.error(e.getMessage(),e.fillInStackTrace());
			}
		 
		   JsonObject jsonObject= jsonConvert.jsonConvert(object); 
	       String tableName=this.tableDataProperties.getTableName(object);
	       String idName=this.tableDataProperties.getIdName(object);
	       
	       
	       String query="UPDATE `"+tableName+"`  SET ";
	       ArrayList<String> values=new ArrayList<>();
	       
	       String id = null;
	      
	       for (Object key:  jsonObject.keySet()){
	           
	            String keyStr = (String) key;
	            String keyValue = jsonObject.get(keyStr)+"";
	            
	            if (idName.equals(keyStr)) {
	            	id=keyValue;
	            }else {
	            	query=query+="`"+keyStr+"`=?,";
	            	values.add(keyValue);
	            }
	       }
	       query=query.substring(0,query.length()-1);
	       query=query.replace("\"", "");
	       query=query+" WHERE "+idName+"='"+id+"'";
	       
	       try {   
	    	   
	    	   logger.info(query);
		       PreparedStatement ps=this.conn.getConnection().prepareStatement(query);
		       for (int i = 0; i < values.size(); i++) {
				   ps.setString(i+1, values.get(i).replace("\"",""));
		       }
		       ps.execute();		       
		       return true;
		   }
	       catch(SQLException e) {	    	   
	    	   logger.error(e.getMessage(),e.fillInStackTrace());
	       }
	       
	       return false;
	}

}
