package sv.hawkframework.com.ORM.Validations.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface INotDuplicatedField {

	Boolean notDuplicatedValidationInsert(Object object,Boolean isUpdate) throws NoSuchMethodException, 
	SecurityException, IllegalAccessException, IllegalArgumentException, 
	InvocationTargetException, FileNotFoundException, IOException, SQLException;
}
