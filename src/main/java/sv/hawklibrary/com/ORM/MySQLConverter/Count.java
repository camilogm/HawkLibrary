package sv.hawklibrary.com.ORM.MySQLConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import sv.hawkframework.factorys.LoggerFactory;
import sv.hawkframework.loggers.NoLogger;
import sv.hawklibrary.com.ORM.TablesDataProperties;
import sv.hawklibrary.com.ORM.QueryOperations.ICount;
import sv.hawklibrary.com.connections.DataBaseConnection;
import sv.hawklibrary.com.connections.MySqlConnection;
import sv.hawkframework.loggers.Logger;

public class Count implements ICount {

	private DataBaseConnection conn = MySqlConnection.getInstance();
	private static Count count;
	private static final Logger logger = LoggerFactory.getLogger(null, NoLogger.class);

	
	private Count() {
		
	}
	
	public static Count getInstance() { 
		
		if (count == null ) {
			count = new Count();
		}
		return count;
	}

	@Override
	public Integer getCount(Object object) {
		
		String query="SELECT count(*) as 'Count' from "+TablesDataProperties.getTableName(object);
		
		try {
		
			ResultSet rs=conn.executeReader(query);
			rs.next();
			return Integer.parseInt(rs.getString("Count"));
		
		} catch (NullPointerException | SQLException ex) {
			
			logger.error(ex.getMessage(),ex.fillInStackTrace());
		}		
		return null;
	}
	
	

}
