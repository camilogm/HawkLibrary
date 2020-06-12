/**
 * 
 */
package sv.hawkframework.com.ORM.Annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * @author Camilo González
 * @version 1.0
 * @see determines the primary key in the java class to database schema
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface PrimaryKey {

	String name() default "Id";
}
