package sv.hawkframework.com.connections;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import sv.hawkframework.factorys.LoggerFactory;
import sv.hawkframework.loggers.Logger;
import sv.hawkframework.loggers.NoLogger;


public class MySqlConnection  implements DataBaseConnection,AutoCloseable{


	private static final Logger logger = LoggerFactory.getLogger(null, NoLogger.class);
	private static MySqlConnection mySqlConnection;
	private  Connection connectionMysql;
	
	private MySqlConnection() {
	}
	

	public static MySqlConnection getInstance() {
		
		
	  	if (mySqlConnection==null) { 	
	  		mySqlConnection = new MySqlConnection();
	  		
	  	}

  		try {
			if (mySqlConnection.getConnection() == null || mySqlConnection.getConnection().isClosed()) {
					mySqlConnection.openConnection();
				
			}
		} catch (SQLException e) {
			System.out.println("erwa");
		}
	  	
		return mySqlConnection;
	}

	@Override
	public void close() throws Exception {
		closeConnection();
		
	}
	
	
	private void openConnection() {
		
		String path = System.getProperty("user.dir");
		path += "\\src\\main\\resources\\database.properties";
		Properties properties = new Properties();
		try {
			properties.load(new FileReader(path));
			String host = (String) properties.get("host");
			String user = (String) properties.get("user");
			String password = (String) properties.get("password");
			String database = (String) properties.get("database");
			String extraParameters = (String) properties.get("extra.parameters");
			
			String url = host+"/"+database+"?"+"user="+user+"&"+"password="+password+"&"+extraParameters;
			this.connectionMysql = DriverManager.getConnection(url);
			
		} catch (IOException | SQLException e) {
			
			System.out.println(e.getMessage());
		}
		
	}
	

	public void closeConnection() {
		   try {
	           
	           if(connectionMysql != null){
	            connectionMysql.close();
	            } 
	        } catch (SQLException e) {
	        	
	            logger.error(e.getMessage(),e.fillInStackTrace());
	      }
		
	}

	@Override
	public Boolean executeQuery(String query) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean executeCall(String query) {
		return null;
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet readerCall(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet executeReader(String query) throws SQLException,NullPointerException {
		return this.connectionMysql.prepareStatement(query).executeQuery();
	}


	@Override
	public Connection getConnection() {
	
		return this.connectionMysql;
      
	}


	






	

	
	

	
}
