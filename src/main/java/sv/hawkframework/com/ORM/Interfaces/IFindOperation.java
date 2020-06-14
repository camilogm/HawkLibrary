package sv.hawkframework.com.ORM.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public interface IFindOperation<T>  {

	/**
	 * 
	 * @param id
	 * @param object
	 * @param fields
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Integer id) throws NullPointerException,SQLException;
	/**
	 * 
	 * @param id
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Integer id, String fields[])throws NullPointerException,SQLException;
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions)throws NullPointerException,SQLException;
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions, String fields[])throws NullPointerException,SQLException;
	
	/**
	 * 
	 * @param object
	 * @return the count of the table
	 */
	Integer getCount();
	
	
	
}
