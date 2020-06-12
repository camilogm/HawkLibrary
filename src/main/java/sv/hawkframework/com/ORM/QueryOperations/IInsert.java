package sv.hawkframework.com.ORM.QueryOperations;


import java.sql.SQLException;

/**
 * 
 * @author Camilo González
 * @version 1.0
 */
public interface IInsert {

	/**
	 * 
	 * @param object
	 * @return boolean if accepts the insert
	 * @throws SQLException
	 */
	Boolean insert(Object object);
	
	
}
