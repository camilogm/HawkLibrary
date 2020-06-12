package sv.hawkframework.com.ORM.Interfaces;


public interface IOperations<T> {

	Boolean addAndSave();
	void updateAndSave();
	Boolean deleteAndSave();
	Boolean deleteAndSave(Object[][] conditions);
	
	
	
	
}
