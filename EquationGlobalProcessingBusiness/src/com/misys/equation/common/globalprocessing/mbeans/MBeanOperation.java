package com.misys.equation.common.globalprocessing.mbeans;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for methods that should be exposed as MBean operations that provide a description of the operation.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface MBeanOperation
{
	/** Returns the description of the operation. */
	String value();
}
