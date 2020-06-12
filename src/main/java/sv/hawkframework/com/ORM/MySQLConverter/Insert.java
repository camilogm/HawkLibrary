package sv.hawkframework.com.ORM.MySQLConverter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.QueryOperations.IInsert;
import sv.hawkframework.com.ORM.QueryOperations.IJsonConvert;
import sv.hawkframework.com.ORM.Validations.Interfaces.INotDuplicatedField;
import sv.hawkframework.com.factory.connections.DataBaseConnection;
import sv.hawkframework.com.factory.connections.MySqlConnection;


public class Insert implements IInsert{

	protected DataBaseConnection conn = MySqlConnection.getInstance();
	protected IJsonConvert jsonConvert = new JsonConvert();
	
	private static final Logger logger=Logger.getLogger(Insert.class);
	
	private INotDuplicatedField notDuplicatedValidation;
	private static Insert insert;
	
	private Insert() {
		
	}
	
	public static Insert getInstance() {
		if (insert==null) {
			insert = new Insert();
		}
		return insert;
	}
	
	
	
	@Override
	public Boolean insert(Object object)  {
		
		try {
			notDuplicatedValidation.notDuplicatedValidationInsert(object,Boolean.FALSE);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | IOException | SQLException e) {
			logger.error(e.getMessage(),e.fillInStackTrace());
		} catch (NullPointerException ex) {
			System.out.println("nomevalido");
		}
	    
		JsonObject jsonObject= jsonConvert.jsonConvert(object); 
	    String tableName=TablesDataProperties.getTableName(object);
	    	    		    	    
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
