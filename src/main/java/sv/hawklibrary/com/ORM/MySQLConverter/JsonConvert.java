package sv.hawklibrary.com.ORM.MySQLConverter;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import sv.hawklibrary.com.ORM.QueryOperations.IJsonConvert;
import sv.hawklibrary.com.ORM.Validations.ValidationTypes;
import sv.hawklibrary.com.ORM.Validations.Interfaces.IValidationTypes;
import sv.hawklibrary.com.connections.DataBaseConnection;
import sv.hawklibrary.com.connections.MySqlConnection;

public class JsonConvert  implements IJsonConvert {

	private DataBaseConnection connection = MySqlConnection.getInstance();
	protected IValidationTypes validationTypes = new  ValidationTypes();
	
	
	@Override
	public JsonObject jsonConvert(Object object) throws JsonIOException {
		 Gson gson = new Gson();
	     String jsonString = gson.toJson(object);
	     JsonObject jsonObject = new Gson().fromJson(jsonString, JsonObject.class);            
	     return jsonObject;
		
	}

	@Override
	public String[] getArrayStringJson(String query,Object object) throws SQLException,NullPointerException {
		
		 String[] obs = null;

		 ResultSet rs=connection.executeReader(query);
		 ResultSetMetaData rsmd = rs.getMetaData();
         int columnsNum=rsmd.getColumnCount();
         rs.last();
         int numRows = rs.getRow();
         if(numRows == 0){
             numRows =1;
             obs = new String[numRows];
             obs[0] = null;
         }
         obs = new String[numRows];
         rs.beforeFirst();
         
         int j=0;
         System.out.println(query);
         while (rs.next()){
             
             String jsonObjectString="{";
             for (int i = 1; i <= columnsNum; i++) {
            
               String key=rsmd.getColumnName(i);                      
               String jsonField="\""+key+"\":";
               String value=rs.getString(i);
               value=this.validationTypes.getValue(value);
               jsonField+=value+"";
               jsonObjectString+=jsonField+",";
                 
             }
           jsonObjectString=jsonObjectString.substring(0,jsonObjectString.length()-1);
           jsonObjectString+="}";
           
           obs[j] = jsonObjectString;
           j++;
         }

         return obs;
				
	}

	@Override
	public String getStringJsonArray(String query, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
