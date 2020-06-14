package sv.hawkframework.com.ORM.QueryOperations;

import java.sql.SQLException;

/**
 * 
 * @author camil_000
 * @version 1.0
 */
public interface IDelete {

	Boolean delete(Object object) throws SQLException;

	/**
	 * 
	 * @param object
	 * @param conditions
	 * @return true if the objets has been deleted of the database
	 */
	Boolean delete(Object object, Object[][] conditions);
}
