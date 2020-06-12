package sv.hawkframework.com.ORM.QueryConverter.Interfaces;
/**
 * 
 * @author camil_000
 * @version 1.0
 */
public interface IDelete {

	Boolean delete(Object object);

	/**
	 * 
	 * @param object
	 * @param conditions
	 * @return true if the objets has been deleted of the database
	 */
	Boolean delete(Object object, Object[][] conditions);
}
