package sv.hawkframework.com.factory.connections;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DataBaseConnection {

	
	Boolean  executeQuery(String query);
	Boolean executeCall(String query);
	Connection getConnection();
	ResultSet readerCall(String query);
	ResultSet executeReader(String query) throws SQLException,NullPointerException;
	
}
