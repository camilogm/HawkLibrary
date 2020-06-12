package sv.hawkframework.com.ORM.Exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Camilo González
 * @return a Exception with the fields that are duplicated in the database
 */
public class NotDuplicatedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<ErrorORM> fieldErrors;
	
	
	public NotDuplicatedException(String message) {
		super(message);
		this.fieldErrors=new ArrayList<>();
		
	}
	
	public List<ErrorORM> getFieldErrors() {
		return fieldErrors;
	}


	public void addFieldError(String field,String value) {
		ErrorORM error=new ErrorORM(field,value);
		this.fieldErrors.add(error);
	}
	
}
