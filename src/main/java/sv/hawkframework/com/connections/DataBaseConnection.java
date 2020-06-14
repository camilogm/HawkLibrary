package sv.hawkframework.com.connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBaseConnection {

	
	Boolean  executeQuery(String query);
	Boolean executeCall(String query);
	Connection getConnection() throws SQLException;
	ResultSet readerCall(String query);
	ResultSet executeReader(String query) throws SQLException,NullPointerException;
	
}
