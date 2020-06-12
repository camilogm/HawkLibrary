package sv.hawkframework.com.ORM.QueryOperations;

import java.sql.SQLException;

public interface IFind {

	/**
	 * 
	 * @param id
	 * @param object
	 * @param fields
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Integer id,Object object);
	/**
	 * 
	 * @param id
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Integer id,Object object, String fields[]);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions,Object object);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions,Object object, String fields[]);
	
}
