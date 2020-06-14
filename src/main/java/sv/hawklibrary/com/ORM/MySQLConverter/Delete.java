package sv.hawklibrary.com.ORM.MySQLConverter;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sv.hawkframework.factorys.LoggerFactory;
import sv.hawkframework.loggers.Logger;
import sv.hawkframework.loggers.NoLogger;
import sv.hawklibrary.com.ORM.TablesDataProperties;
import sv.hawklibrary.com.ORM.Annotations.PrimaryKey;
import sv.hawklibrary.com.ORM.QueryOperations.IConditionsConstructor;
import sv.hawklibrary.com.ORM.QueryOperations.IDelete;
import sv.hawklibrary.com.ORM.Validations.GettersSetters;
import sv.hawklibrary.com.connections.DataBaseConnection;
import sv.hawklibrary.com.connections.MySqlConnection;


public class Delete implements IDelete {

	private DataBaseConnection conn = MySqlConnection.getInstance();
	private IConditionsConstructor conditionsConstructor;
	
	private static final Logger logger = LoggerFactory.getLogger(null, NoLogger.class);

	private static Delete delete;
	
	private Delete() {
		
	}
	
	public static Delete getInstance() {
		
		if (delete == null) {
			delete = new Delete();
		}
		return delete;
	}
	
	
	
	@Override
	public Boolean delete(Object object) throws SQLException {

		String tableName=TablesDataProperties.getTableName(object);
		String idName=TablesDataProperties.getIdName(object);
		
		Class<? extends Object> classProperties=object.getClass();
		Field[] fields=classProperties.getDeclaredFields();
		
		String value=null;

		for (Field field:fields) { 			
			
			PrimaryKey primaryKeyAnn=(PrimaryKey) field.getAnnotation(PrimaryKey.class);
			if (primaryKeyAnn!=null || idName.equals(field.getName())) {
				
				try {
					value=GettersSetters.getPrimaryKeyValue(field, classProperties, object);
				} catch (Exception ex) {
					logger.error(ex.getMessage(),ex.fillInStackTrace());
				}
				
				break;
			}	
			
		}
		
		String query="DELETE FROM "+tableName+" where "+idName+"=?";
	
		
		if (value!=null) { 
			logger.info(query);			
			PreparedStatement ps=conn.getConnection().prepareStatement(query);
			ps.setString(1, value);
			ps.execute();
			return true;
		}
		
		return false;
		
	}
	
	
	@Override 
	public Boolean delete(Object object, Object[][] conditions) { 
		
		String tableName=TablesDataProperties.getTableName(object);
		
		if (conditions!=null) { 
			
			String query="DELETE FROM "+tableName+" "+conditionsConstructor.conditionsWithMatrix(conditions);
			PreparedStatement ps;
			try {
				logger.info(query);
				ps = conn.getConnection().prepareStatement(query);
				ps.execute();
				return true;
			} catch (SQLException ex) {
				logger.error(ex.getMessage(),ex.fillInStackTrace());
			}	
		}
		
		return false;
	}
	

}
