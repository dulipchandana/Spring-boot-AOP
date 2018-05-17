/**
 * 
 */
package com.aop.aopsamples.annotation;

import java.lang.annotation.Documented;

@Documented
/**
 * @author dulip
 *
 */
public @interface Restrict {
	boolean localOnly() default false;

	boolean admin() default true;

}
