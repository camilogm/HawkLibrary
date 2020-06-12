package sv.hawkframework.com.ORM.Interfaces;

import java.sql.SQLException;

public interface IFindOperation<T>  {

	/**
	 * 
	 * @param id
	 * @param object
	 * @param fields
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Integer id);
	/**
	 * 
	 * @param id
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Integer id, String fields[]);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions, String fields[]);
	
	/**
	 * 
	 * @param object
	 * @return the count of the table
	 */
	Integer getCount();
	
	
	
}
