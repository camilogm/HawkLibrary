package sv.hawkframework.com.ORM.Interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import sv.hawkframework.com.ORM.ConditionsStructure;

public interface IFindManyOperation<T> {

	/**
	 * 
	 * @param <T>
	 * @param conditions
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("hiding")
	<T> ArrayList<T> findMany(Object[][] conditions) ;
	
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("hiding")
	<T> ArrayList<T> findMany(Object[][] conditions, String[] fields) ;
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param limits
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("hiding")
	<T> ArrayList<T> findMany(Object[][] conditions, Integer[] limits);
	/**
	 * 
	 * @param conditions
	 * @param object
	 * @param fields
	 * @param limits
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("hiding")
	<T> ArrayList<T> findMany(Object[][] conditions,String[] fields, Integer[] limits);

	/**
	 * 
	 * @param conditionsStructure
	 * @return make the query with a ConditionsStructure objects
	 */
	
	@SuppressWarnings("hiding")
	<T> ArrayList<T> findMany(ConditionsStructure conditionsStructure);
	
	
	
	
	
}
