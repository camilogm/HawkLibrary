package sv.hawkframework.com.ORM.Annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Camilo Gonzalez
 * @version 1.0
 * @apiNote for use this annotation is mandatatory that the properties in the class has a original get method
 */

@Target({FIELD,PARAMETER, })
@Retention(RetentionPolicy.RUNTIME)
public @interface NotDuplicated {

	String message() default "sv.edu.udb.ORM.Annotations.messages";	
}
