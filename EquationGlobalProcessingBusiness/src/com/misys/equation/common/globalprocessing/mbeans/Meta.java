package com.misys.equation.common.globalprocessing.mbeans;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for method parameters and classes that provide a name and a description.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.TYPE })
public @interface Meta
{
	/** Returns the name of the entity. */
	String name();

	/** Returns the description of the entity. */
	String desc();
}
