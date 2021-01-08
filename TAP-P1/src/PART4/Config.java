package PART4;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author Julen Bohoyo Bengoetxea
 * @author Alberto Iglesias Burgos
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Config {
	
	/**
	 * Keep the info of which of the 3  MailStore we want to initialize
	 * @return type of MailStore will be used
	 */
	String store() default "PART1.MailStoreMem";
	
	/**
	 * Activate or disactivate log mode that prints on screen the whole methods done by the MailStore
	 * @return boolean that indicates the activation
	 */
	boolean log() default true;

}
