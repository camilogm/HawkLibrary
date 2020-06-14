package sv.hawkframework.com.ORM.MySQLConverter;

import java.sql.ResultSet;
import java.sql.SQLException;


import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.QueryOperations.ICount;
import sv.hawkframework.com.connections.DataBaseConnection;
import sv.hawkframework.com.connections.MySqlConnection;
import sv.hawkframework.factorys.LoggerFactory;
import sv.hawkframework.loggers.NoLogger;
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
