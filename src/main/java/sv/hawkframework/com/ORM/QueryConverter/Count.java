package sv.hawkframework.com.ORM.QueryConverter;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConexion;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.ICount;

public class Count implements ICount {

	private IConexion conn;
	protected TablesDataProperties tableDataProperties;
	private static final Logger logger=Logger.getLogger(Count.class);
	

	@Override
	public Integer getCount(Object object) {
		
		String query="SELECT count(*) as 'Count' from "+tableDataProperties.getTableName(object);
		
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
