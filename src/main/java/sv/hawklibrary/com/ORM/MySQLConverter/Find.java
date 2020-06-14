package sv.hawklibrary.com.ORM.MySQLConverter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;


import com.google.gson.Gson;

import sv.hawkframework.factorys.LoggerFactory;
import sv.hawkframework.loggers.Logger;
import sv.hawkframework.loggers.NoLogger;
import sv.hawklibrary.com.ORM.TablesDataProperties;
import sv.hawklibrary.com.ORM.QueryOperations.IConstructortSelect;
import sv.hawklibrary.com.ORM.QueryOperations.IFind;
import sv.hawklibrary.com.ORM.QueryOperations.IJsonConvert;
import sv.hawklibrary.com.ORM.Validations.PropertiesLoad;
import sv.hawklibrary.com.validators.NotFoundException;

public class Find extends JsonConvert implements IFind {


	private static Find find;
	protected IConstructortSelect constructorSelect = new ConstructorSelect();
	protected IJsonConvert jsonConvert =new JsonConvert();
	private static final Logger logger = LoggerFactory.getLogger(null, NoLogger.class);
	
	private Find() {
		
	}
	
	public static Find getInstance() {
		
		if (find == null) {
			find = new Find();
		}
		return find;
	}
	
	@Override
	public Object find(Integer id, Object object) throws NullPointerException, FileNotFoundException, SQLException, IOException  {
		
		Object[][] conditions= {{"id","=",id,null}};
		String query=constructorSelect.finalQuery(object, null, conditions, null);
		return this.convertToObject(object, query);
	}
	
	@Override
	public Object find(Integer id, Object object, String[] fields) throws NullPointerException, FileNotFoundException, SQLException, IOException {
		
		Object[][] conditions= {{"id","=",id,null}};
		String query=constructorSelect.finalQuery(object, fields, conditions, null);
		
		return this.convertToObject(object, query);
	}
	@Override
	public Object find(Object[][] conditions, Object object) throws NullPointerException, FileNotFoundException, SQLException, IOException {
	
		String query=constructorSelect.finalQuery(object,null, conditions,null);
		return this.convertToObject(object, query);
	}
	@Override
	public Object find(Object[][] conditions, Object object, String[] fields) throws NullPointerException, FileNotFoundException, SQLException, IOException {
		
		String query=constructorSelect.finalQuery(object, fields, conditions, null);
		return this.convertToObject(object, query);
	}
	
	
	private Object convertToObject(Object object,String query) throws NullPointerException, SQLException, FileNotFoundException, IOException {
	
		
		Object objectFind=null;
		String jsonObject;
		
		jsonObject = jsonConvert.getArrayStringJson(query, object)[0];
		logger.info(query);
		if (jsonObject==null){
			String message;
			message=PropertiesLoad.getProperty("NotFound");
			NotFoundException ex=new NotFoundException(message+" "+TablesDataProperties.getTableName(object));
			throw ex;
		}
		
		
		
		Gson gson = new Gson();
	    objectFind=gson.fromJson(jsonObject, object.getClass());
		return objectFind;

	}
		
	
	


}
