
package net.a220vfor.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines action properties. 
 * The annotation allows to explicitly associate an action (a class method) with its template and add a constraint to
 * request method, which defaults to GET.
 * 
 * @author Andrew
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionProperties {
    
    /**
     * The request method type to which this action will respond; can be GET, POST or BOTH. 
     * Defaults to GET, if not specified.
     * @return 
     */
    String method() default "GET";
    
    String template();
}
