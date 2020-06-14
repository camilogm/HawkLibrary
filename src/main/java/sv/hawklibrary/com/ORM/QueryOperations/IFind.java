package sv.hawklibrary.com.ORM.QueryOperations;

import java.io.FileNotFoundException;
import java.io.IOException;
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
	Object find(Integer id,Object object) throws NullPointerException, FileNotFoundException, SQLException, IOException ;
	/**
	 * 
	 * @param id
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Integer id,Object object, String fields[]) throws NullPointerException, FileNotFoundException, SQLException, IOException ;
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions,Object object) throws NullPointerException, FileNotFoundException, SQLException, IOException ;
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	Object find(Object[][] conditions,Object object, String fields[]) throws NullPointerException, FileNotFoundException, SQLException, IOException ;
	
}
