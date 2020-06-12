package sv.hawkframework.com.ORM.QueryConverter;


import sv.hawkframework.com.ORM.TablesDataProperties;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConditionsConstructor;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.IConstructortSelect;

public class ConstructorSelect implements IConstructortSelect {

	private String query=null;
	private Object object;
	protected TablesDataProperties tableDataProperties;
	private IConditionsConstructor conditionsConstructor;
	
	
	@Override
	public String finalQuery(Object object,String[] fields,Object[][] conditions,Integer[] limits) 
			throws NullPointerException {
		this.query="";
		this.object=object;
	    this.setFields(fields);
	    this.setTable();
	    this.setConditions(conditions);
	    this.setLimits(limits);
		
		return query;
	}

	@Override
	public void setTable() throws NullPointerException {
		
		String tableName=tableDataProperties.getTableName(this.object);
		query+=" FROM `"+tableName+"` ";
	
	}

	@Override
	public void setFields(String[] fields) throws NullPointerException, ArrayIndexOutOfBoundsException {
		
		if (fields==null) {
			query="SELECT * ";
		}else {
			String fieldsToQuery="";
			for (String field:fields) {
				fieldsToQuery+=" `"+field+"`,";
			}
			fieldsToQuery=fieldsToQuery.substring(0,fieldsToQuery.length()-1);
			query+=" SELECT "+fieldsToQuery;
		}
	}

	@Override
	public void setConditions(Object[][] conditions) throws NullPointerException, ArrayIndexOutOfBoundsException {		
		query+=" "+conditionsConstructor.conditionsWithMatrix(conditions);		
	}

	@Override
	public void setLimits(Integer[] limits) throws NullPointerException, ArrayIndexOutOfBoundsException {
		if (limits!=null) {
			if (limits.length==2)
				query+=" limit "+limits[0]+","+limits[1]+"";
		}
	
	}


	
	
}
