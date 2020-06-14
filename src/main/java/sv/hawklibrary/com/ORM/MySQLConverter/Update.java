package sv.hawklibrary.com.ORM.MySQLConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


import com.google.gson.JsonObject;

import sv.hawkframework.factorys.LoggerFactory;
import sv.hawkframework.loggers.Logger;
import sv.hawkframework.loggers.NoLogger;
import sv.hawklibrary.com.ORM.TablesDataProperties;
import sv.hawklibrary.com.ORM.QueryOperations.IJsonConvert;
import sv.hawklibrary.com.ORM.QueryOperations.IUpdate;
import sv.hawklibrary.com.ORM.Validations.NotDuplicatedOnInsertOrUpdate;
import sv.hawklibrary.com.ORM.Validations.Interfaces.INotDuplicatedField;
import sv.hawklibrary.com.connections.DataBaseConnection;
import sv.hawklibrary.com.connections.MySqlConnection;

public class Update  implements IUpdate  {

	
	
	protected DataBaseConnection conn = MySqlConnection.getInstance();
	protected IJsonConvert jsonConvert = new JsonConvert();
	private static final Logger logger = LoggerFactory.getLogger(null, NoLogger.class);

	
	private INotDuplicatedField notDuplicatedValidation = new NotDuplicatedOnInsertOrUpdate();
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
	public Boolean update(Object object) throws SQLException, NullPointerException  {
		
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
	       
	        
	    	   
    	   logger.info(query);
	       PreparedStatement ps=this.conn.getConnection().prepareStatement(query);
	       for (int i = 0; i < values.size(); i++) {
			   ps.setString(i+1, values.get(i).replace("\"",""));
	       }
	       ps.execute();		       
	       return true;

	}

}
