package sv.hawklibrary.com.ORM.Interfaces;

import java.sql.SQLException;

public interface IOperations<T> {

	Boolean addAndSave() throws SQLException;
	void updateAndSave() throws SQLException;
	Boolean deleteAndSave() throws SQLException;
	Boolean deleteAndSave(Object[][] conditions);
	
	
	
	
}
