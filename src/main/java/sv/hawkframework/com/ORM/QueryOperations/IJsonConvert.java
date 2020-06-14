package sv.hawkframework.com.ORM.QueryOperations;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IJsonConvert {

	ArrayList<Object> getArrayStringJson(String query,Object object) throws SQLException;
	String getStringJsonArray(String query, Object object);
	
	
}
