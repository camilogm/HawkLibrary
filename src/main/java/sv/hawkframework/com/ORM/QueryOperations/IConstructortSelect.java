package sv.hawkframework.com.ORM.QueryOperations;


public interface IConstructortSelect {

	String finalQuery(Object object,String[] fields,Object[][] conditions,Integer[] limits) 
			throws NullPointerException;
	void setTable() throws NullPointerException;
	void setFields(String[] fields) throws NullPointerException,ArrayIndexOutOfBoundsException;
	void setConditions(Object[][] conditions) throws NullPointerException,ArrayIndexOutOfBoundsException;
	void setLimits(Integer[] limits) throws NullPointerException,ArrayIndexOutOfBoundsException;
	
	
}
