package sv.hawkframework.com.ORM.QueryConverter.Interfaces;

import java.sql.SQLException;

import com.google.gson.JsonObject;

public interface IJsonConvert {

	
	JsonObject jsonConvert(Object object);
	String[] getArrayStringJson(String query,Object object) throws SQLException;
	String getStringJsonArray(String query, Object object);
	
	
}
