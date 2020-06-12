package sv.hawkframework.com.ORM;

import java.util.ArrayList;

import sv.hawkframework.com.ORM.Interfaces.*;
import sv.hawkframework.com.ORM.QueryConverter.Interfaces.*;



public class  ORMApplicationTables<T> implements IOperations<Object>,IFindOperation<Object>, IFindManyOperation<Object> {



	private IInsert insert;
	private IUpdate update;
	private IDelete delete;
	private IFind find;
	private IFindMany findMany;
	private ICount count;
	
	
	
	
	private T object;
	
	public ORMApplicationTables() {
		
		
	}
	

	public ORMApplicationTables(T object) {
		
		this.object=object;
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	
	public T find(Integer id)  {
		return  (T) find.find(id, this.object);
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public T find(Integer id, String[] fields)  {

		return (T) find.find(id, this.object, fields);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public T find(Object[][] conditions) {
		
		return (T) find.find(conditions, this.object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T find(Object[][] conditions, String[] fields){
	
		
		return (T) find.find(conditions, this.object, fields);
	}
	

	@Override
	public Boolean addAndSave() {
		
		return insert.insert(object);
	}


	@Override
	public void updateAndSave() {
		update.update(object);
		
	}


	@Override
	public Boolean deleteAndSave() {
		return delete.delete(object);
		
	}
	
	@Override
	public Boolean deleteAndSave(Object[][] conditions) { 
		return delete.delete(object, conditions);
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> findMany(Object[][] conditions)  {
		return (ArrayList<T>) this.findMany.findMany(conditions,object);
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> findMany(Object[][] conditions, String[] fields) {

		return (ArrayList<T>) this.findMany.findMany(conditions, object, fields);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> findMany(Object[][] conditions, Integer[] limits) {
		
		return (ArrayList<T>) this.findMany.findMany(conditions, object, limits);
	}


	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<T> findMany(Object[][] conditions, String[] fields, Integer[] limits) {
		return (ArrayList<T>) findMany.findMany(conditions, object, fields, limits);
	}

	@SuppressWarnings("unchecked")
	@Override 
	public ArrayList<T> findMany(ConditionsStructure conditionsStructure){
		
		return (ArrayList<T>) findMany.findMany(
				conditionsStructure.getConditions()!=null ? conditionsStructure.serializeConditions() : null,
				object,
				conditionsStructure.getFields(),
				conditionsStructure.serializeLimits()
		);
	}
	
	@Override
	public Integer getCount() {	
		return count.getCount(object);
	}

	
	
	public T getObject() {
		return object;
	}


	public void setObject(T object) {
		this.object = object;
	}


	

	
}
