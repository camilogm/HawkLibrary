package sv.hawkframework.com.ORM;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

import sv.hawkframework.com.ORM.Interfaces.*;
import sv.hawkframework.com.ORM.MySQLConverter.Count;
import sv.hawkframework.com.ORM.MySQLConverter.Delete;
import sv.hawkframework.com.ORM.MySQLConverter.Find;
import sv.hawkframework.com.ORM.MySQLConverter.FindMany;
import sv.hawkframework.com.ORM.MySQLConverter.Insert;
import sv.hawkframework.com.ORM.MySQLConverter.Update;
import sv.hawkframework.com.ORM.QueryOperations.*;




public class  ORMApplicationTables<T> implements IOperations<Object>,IFindOperation<Object>, IFindManyOperation<Object> {



	private IInsert insert = Insert.getInstance();
	private IUpdate update = Update.getInstance();
	private IDelete delete = Delete.getInstance();
	private IFind find = Find.getInstance();
	private IFindMany findMany = FindMany.getInstance();
	private ICount count = Count.getInstance();
	
	private T object;
	
	@SuppressWarnings("unchecked")
	public ORMApplicationTables(Class<? extends Object> classObject) {
		
		Constructor<?>[] constructors = classObject.getDeclaredConstructors();
		
		for (Constructor<?> constructor : constructors) { 
			
			 if (constructor.getGenericParameterTypes().length == 0) {
				 try {
					T newInstance = (T) constructor.newInstance();
					this.object = newInstance;
					
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					System.out.println("Error creating the instance");
					System.out.println(e.getStackTrace());	
				}
				break;
			 }
		}
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
	public Boolean addAndSave() throws SQLException {
		
		return insert.insert(object);
	}


	@Override
	public void updateAndSave() throws SQLException{
		update.update(object);
		
	}


	@Override
	public Boolean deleteAndSave() throws SQLException{
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
