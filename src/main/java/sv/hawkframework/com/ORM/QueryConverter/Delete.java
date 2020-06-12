package sv.hawkframework.com.ORM.QueryConverter;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.Annotations.PrimaryKey;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConditionsConstructor;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConexion;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IDelete;
import sv.hawkframework.com.ORM.Validations.GettersSetters;

public class Delete implements IDelete {

	private IConexion conn;
	private TablesDataProperties tableProperties;
	private IConditionsConstructor conditionsConstructor;
	
	private static final Logger logger=Logger.getLogger(Find.class);
	
	
	@Override
	public Boolean delete(Object object) {

		String tableName=tableProperties.getTableName(object);
		String idName=tableProperties.getIdName(object);
		
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
		try {
		
			if (value!=null) { 
				logger.info(query);			
				PreparedStatement ps=conn.getConnection().prepareStatement(query);
				ps.setString(1, value);
				ps.execute();
				return true;
			}
			
		} catch (SQLException ex) {	
			logger.error(ex.getMessage(),ex.fillInStackTrace());
			return false;
			
		}	
		
		return false;
		
	}
	
	
	@Override 
	public Boolean delete(Object object, Object[][] conditions) { 
		
		String tableName=tableProperties.getTableName(object);
		
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
