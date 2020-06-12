package sv.hawkframework.com.ORM.MySQLConverter;


import sv.hawkframework.com.ORM.QueryOperations.IConditionsConstructor;

public class ConditionsConstructor implements IConditionsConstructor {

	@Override
	public String conditionsWithMatrix(Object[][] conditions) {
		
		String conditionsString="";
		
		if (conditions!=null) {
			
			  conditionsString += " WHERE ";

	            for (Object[] condicion : conditions) {
	                conditionsString += condicion[0] + " " + condicion[1] + " ";
	                
	                if(condicion[3] == null){condicion[3]="";}
	                conditionsString += "'" + condicion[2] + "' " + condicion[3]+" ";
	                
	            }
	        
	            conditionsString = conditionsString.substring(0, conditionsString.length() - 1);			
		}	
		
		return conditionsString;
	}

}
