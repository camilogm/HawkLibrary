package sv.hawkframework.com.ORM.MySQLConverter;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import sv.hawkframework.com.ORM.QueryOperations.IJsonConvert;
import sv.hawkframework.com.ORM.Validations.ValidationTypes;
import sv.hawkframework.com.ORM.Validations.Interfaces.IValidationTypes;
import sv.hawkframework.com.connections.DataBaseConnection;
import sv.hawkframework.com.connections.MySqlConnection;

public class JsonConvert  implements IJsonConvert {

	private DataBaseConnection connection = MySqlConnection.getInstance();
	protected IValidationTypes validationTypes = new  ValidationTypes();
	
	


	@Override
	public ArrayList<Object> getArrayStringJson(String query,Object object) throws SQLException,NullPointerException {
		
		 ArrayList<Object> pairs = new ArrayList<>();
		 
		 
		 
		 ResultSet resultSet=connection.executeReader(query);
		 ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
		 
         int columnsNum=resultSetMetaData.getColumnCount();
         resultSet.last();
         int numRows = resultSet.getRow();
        
         if(numRows == 0){
             numRows =1;
             return null;
         }
         
         resultSet.beforeFirst();  
         System.out.println(query);
         while (resultSet.next()){
             
             for (int i = 1; i <= columnsNum; i++) {
            
               String key=resultSetMetaData.getColumnName(i); 
               String typeOf = resultSetMetaData.getColumnClassName(i);
               String value=resultSet.getString(i);
             
               PairObjects pair = new PairObjects(key, value,typeOf);
               pairs.add(pair);                    
             }
         }

         return pairs;
				
	}

	@Override
	public String getStringJsonArray(String query, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
