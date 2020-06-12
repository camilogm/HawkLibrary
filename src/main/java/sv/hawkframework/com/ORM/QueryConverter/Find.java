package sv.hawkframework.com.ORM.QueryConverter;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.Exceptions.NotFoundException;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConstructortSelect;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IFind;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IJsonConvert;
import sv.hawkframework.com.ORM.Validations.PropertiesLoad;

public class Find extends JsonConvert implements IFind {


	protected TablesDataProperties tableDataProperties;
	protected IConstructortSelect constructorSelect;
	protected IJsonConvert jsonConvert;
	private static final Logger logger=Logger.getLogger(Find.class);
	
	
	@Override
	public Object find(Integer id, Object object) {
		
		Object[][] conditions= {{"id","=",id,null}};
		String query=constructorSelect.finalQuery(object, null, conditions, null);
		return this.convertToObject(object, query);
	}
	
	@Override
	public Object find(Integer id, Object object, String[] fields) {
		
		Object[][] conditions= {{"id","=",id,null}};
		String query=constructorSelect.finalQuery(object, fields, conditions, null);
		
		return this.convertToObject(object, query);
	}
	@Override
	public Object find(Object[][] conditions, Object object) {
	
		String query=constructorSelect.finalQuery(object,null, conditions,null);
		return this.convertToObject(object, query);
	}
	@Override
	public Object find(Object[][] conditions, Object object, String[] fields) {
		
		String query=constructorSelect.finalQuery(object, fields, conditions, null);
		return this.convertToObject(object, query);
	}
	
	
	private Object convertToObject(Object object,String query) {
		
		Object objectFind=null;
		String jsonObject;
		logger.info(query);
		try {
			jsonObject = jsonConvert.getArrayStringJson(query, object)[0];
			
			if (jsonObject==null){
				String message;
				message=PropertiesLoad.getProperty("NotFound");
				NotFoundException ex=new NotFoundException(message+" "+tableDataProperties.getTableName(object));
				throw ex;
			}
			
			
			
			Gson gson = new Gson();
		    objectFind=gson.fromJson(jsonObject, object.getClass());
			return objectFind;
		} catch (SQLException | IOException e) {
			
			logger.error(e.getMessage(),e.fillInStackTrace());
		}
		
		return null;

	}
		
	
	


}
