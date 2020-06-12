package sv.hawkframework.com.ORM.QueryConverter;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConexion;


public class Conexion  implements IConexion,AutoCloseable{

	private Connection conexion;

	static final Logger logger = Logger.getLogger(Conexion.class); 
	
	
	public Conexion() {
		
		openConnection();
	}
	
	
	@Override
	public void close() throws Exception {
		closeConnection();
		
	}

	@Override
	public void openConnection() {
		
		  try {
	            if(conexion==null || conexion.isClosed()){
	            	
	            	/*
	            	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        		dataSource.setUrl("jdbc:mysql://mysql5017.site4now.net/db_a57e35_123?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	        		dataSource.setUsername("a57e35_123");
	        		dataSource.setPassword("pppp1111");
	            	*/
	        		
	            	/*
	            	DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        		dataSource.setUrl("jdbc:mysql://mysql5025.site4now.net/db_a560cc_proyect?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
	        		dataSource.setUsername("a560cc_proyect");
	        		dataSource.setPassword("pppp1111");	            	
	            	*/
	            }

	        } catch (SQLException ex) {
	           logger.error(ex.getMessage(),ex.fillInStackTrace());
	        }
		
		
	}

	@Override
	public void closeConnection() {
		   try {
	           
	           if(conexion != null){
	            conexion.close();
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
		return this.conexion.prepareStatement(query).executeQuery();
	}


	@Override
	public Connection getConnection() {
	    this.openConnection();
		 return this.conexion;
      
	}






	

	
	

	
}
