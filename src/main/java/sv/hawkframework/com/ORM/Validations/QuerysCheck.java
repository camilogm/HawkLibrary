package sv.hawkframework.com.ORM.Validations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sv.hawkframework.com.connections.DataBaseConnection;
import sv.hawkframework.com.connections.MySqlConnection;

public class QuerysCheck {

	
	private DataBaseConnection conn = MySqlConnection.getInstance();
	
	
	/**
	 * 
	 * @param idName
	 * @param tableName
	 * @param fieldName
	 * @param fieldValue
	 * @param primaryKeyValue
	 * @return true if the value is duplicated and false if it is not.
	 * @throws SQLException 
	 */
	
	public Boolean checkDuplicateValues(String idName,String tableName,
			String fieldName,String fieldValue,String primaryKeyValue) throws SQLException {
		
		String query="SELECT "+idName+" FROM "+tableName+" where "+fieldName+"=?";	     
   		PreparedStatement ps;
		
		ps = conn.getConnection().prepareStatement(query);
		ps.setString(1, fieldValue);	
   		ResultSet rs=ps.executeQuery();
   		rs.last();
   		
   		if (rs.getRow()>=1) {
   		
   			/*if the primary key is not null, that means that the operation is a update and this block of code determines
   			  if the id is the same value. If this a same value returns false because the data belows to the same register
   			  and prevent a block of the update process
   			*/
   			if (primaryKeyValue!=null) {
	   			rs.beforeFirst();
	   			while (rs.next()) {
	   				
	   				String primaryKeyValueQuery=rs.getString(1);	   				
	   				if (primaryKeyValueQuery.equals(primaryKeyValue)) {
	   					return false;
	   				}else {
	   					return true;
	   				}	   				   				
	   			}
   			}
   			return true;
   		}
   		else
   			return false;
			
		
	}
	
	
}
