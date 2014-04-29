package com.misys.equation.common.globalprocessing.mbeans;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for constructors that should be exposed as MBean constructors that provide a description of the constructor.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.CONSTRUCTOR })
public @interface MBeanConstructor
{
	/** Returns the description of the constructor. */
	String value();
}
