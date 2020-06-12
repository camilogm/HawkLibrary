package sv.hawkframework.com.ORM.MySQLConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.QueryOperations.IJsonConvert;
import sv.hawkframework.com.ORM.QueryOperations.IUpdate;
import sv.hawkframework.com.ORM.Validations.Interfaces.INotDuplicatedField;
import sv.hawkframework.com.factory.connections.DataBaseConnection;
import sv.hawkframework.com.factory.connections.MySqlConnection;

public class Update  implements IUpdate  {

	
	
	protected DataBaseConnection conn = MySqlConnection.getInstance();
	protected IJsonConvert jsonConvert = new JsonConvert();
	private final static Logger logger=Logger.getLogger(Update.class);
	
	private INotDuplicatedField notDuplicatedValidation;
	private static Update update;
	
	private Update() {
		
	}
	
	public static Update getInstance() {
		if (update==null) {
			update = new Update();
		}
		return update;
	}
	
	@Override
	public Boolean update(Object object)  {
		
		   try {
			notDuplicatedValidation.notDuplicatedValidationInsert(object, Boolean.TRUE);
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | IOException | SQLException e) {

				logger.error(e.getMessage(),e.fillInStackTrace());
			}
		 
		   JsonObject jsonObject= jsonConvert.jsonConvert(object); 
	       String tableName=TablesDataProperties.getTableName(object);
	       String idName=TablesDataProperties.getIdName(object);
	       
	       
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
