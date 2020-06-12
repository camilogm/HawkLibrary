package sv.hawkframework.com.ORM.QueryConverter.Interfaces;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConexion {

	void openConnection();
	void closeConnection();
	Boolean  executeQuery(String query);
	Boolean executeCall(String query);
	Connection getConnection();
	ResultSet readerCall(String query);
	ResultSet executeReader(String query) throws SQLException,NullPointerException;
	
}
