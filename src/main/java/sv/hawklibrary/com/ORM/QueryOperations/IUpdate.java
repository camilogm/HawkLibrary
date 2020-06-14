package sv.hawklibrary.com.ORM.QueryOperations;

import java.sql.SQLException;

public interface IUpdate {
	
	Boolean update(Object object) throws SQLException;
}
