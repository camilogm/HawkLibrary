package sv.hawkframework.com.ORM.QueryOperations;


import java.sql.SQLException;
import java.util.ArrayList;


public interface IFindMany {

	/**
	 * ç
	 * @param conditions
	 * @param object
	 * @return
	 * @throws SQLException
	 */
	ArrayList<Object> findMany(Object[][] conditions, Object object);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 */
	ArrayList<Object> findMany(Object[][] conditions, Object object, String[] fields);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param limits
	 * @return
	 * @throws SQLException
	 */
	ArrayList<Object> findMany(Object[][] conditions, Object object, Integer[] limits);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @param limits
	 * @return
	 * @throws SQLException
	 */
	ArrayList<Object> findMany(Object[][] conditions,Object object,String[] fields, Integer[] limits);
	
}
