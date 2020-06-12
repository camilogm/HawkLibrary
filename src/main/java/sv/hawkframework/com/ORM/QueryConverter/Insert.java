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
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IInsert;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IJsonConvert;
import sv.hawkframework.com.ORM.Validations.Interfaces.INotDuplicatedField;


public class Insert implements IInsert{

	protected IConexion conn;
	protected TablesDataProperties tableDataProperties;
	protected IJsonConvert jsonConvert;
	
	private static final Logger logger=Logger.getLogger(Insert.class);
	
	private INotDuplicatedField notDuplicatedValidation;
	
	
	@Override
	public Boolean insert(Object object)  {
		
		try {
			notDuplicatedValidation.notDuplicatedValidationInsert(object,Boolean.FALSE);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IOException | SQLException e) {
			logger.error(e.getMessage(),e.fillInStackTrace());
		}
	    
		JsonObject jsonObject= jsonConvert.jsonConvert(object); 
	    String tableName=this.tableDataProperties.getTableName(object);
	    	    		    	    
	    String query="INSERT INTO `"+tableName+"` (";
	    ArrayList<String> values=new ArrayList<>();
	       
	       
	       for (Object key:  jsonObject.keySet()){
	            
	            String keyStr = (String) key;
	            String keyvalue = jsonObject.get(keyStr)+"";
	            values.add(keyvalue);
	            query+="`"+keyStr+"`,";
	        }
	        query=query.substring(0,query.length()-1)+")";
	        query=query+" values (";
	        
	        for (int i = 0; i < values.size(); i++) {
				 query+="?,";
	        }
	        query=query.substring(0,query.length()-1)+"";
	        query+=")";
	       
			try {
				
				logger.info(query);
		        PreparedStatement ps=conn.getConnection().prepareStatement(query);
				for (int i=0;i<values.size();i++) {
						ps.setString(i+1, values.get(i).replace("\"", ""));
				}

				ps.execute();	
				return true;
			}
			catch(SQLException e) {
				
				
				logger.error(e.getMessage(),e.fillInStackTrace());
				return false;
			}
	}

}
