package sv.hawkframework.com.ORM.MySQLConverter;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import sv.hawkframework.com.ORM.QueryOperations.IConstructortSelect;
import sv.hawkframework.com.ORM.QueryOperations.IFindMany;
import sv.hawkframework.com.ORM.QueryOperations.IJsonConvert;

public class FindMany  implements IFindMany{

	protected IConstructortSelect constructorSelect = new ConstructorSelect();
	protected IJsonConvert jsonConvert = new JsonConvert();
	
	private static final Logger logger=Logger.getLogger(FindMany.class);
	private static FindMany findMany;
	
	private FindMany() {
		
	}
	
	public static FindMany getInstance() {
		
		if (findMany==null) {
			findMany = new FindMany();
		}
		return findMany;
	}
	
	
	@Override
	public ArrayList<Object> findMany(Object[][] conditions, Object object) {
		
		String query=constructorSelect.finalQuery(object, null, conditions, null);		
		return this.convertToObject(object, query);
	}

	@Override
	public ArrayList<Object> findMany(Object[][] conditions, Object object, String[] fields) {
		
		String query=constructorSelect.finalQuery(object, fields, conditions, null);
		return this.convertToObject(object, query);
	}

	@Override
	public ArrayList<Object> findMany(Object[][] conditions, Object object, Integer[] limits)  {
		
		String query=constructorSelect.finalQuery(object, null, conditions, limits);
		return this.convertToObject(object, query);
	}

	@Override
	public ArrayList<Object> findMany(Object[][] conditions, Object object, String[] fields, Integer[] limits) {
		String query=constructorSelect.finalQuery(object, fields, conditions, limits);
		return this.convertToObject(object, query);
	}

	
	private ArrayList<Object> convertToObject(Object object,String query)  {
		
		ArrayList<Object> arrayList=new ArrayList<>();
		logger.info(query);
		
		Object objectFind=null;
		String[] jsonObjectsString;
		try {
			jsonObjectsString = jsonConvert.getArrayStringJson(query, object);
			
			if (jsonObjectsString[0]==null)
				return null;
			for (String jsonObject:jsonObjectsString) {
				Gson gson = new Gson();
				objectFind=gson.fromJson(jsonObject, object.getClass());
				arrayList.add(objectFind);
			}
		    return arrayList;
		    
		} catch (SQLException e) {
			logger.error(e.getMessage(),e.fillInStackTrace());
		}
		
		
		return null;
	}
		
	
	
	
}
