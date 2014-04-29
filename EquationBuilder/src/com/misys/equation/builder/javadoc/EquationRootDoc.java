package com.misys.equation.builder.javadoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.RootDoc;

/**
 * This encapsulates the RootDoc instance into an EquationRootDoc instance <br>
 * in order to return only valid classes and methods for Equation Java <br>
 * Documentation
 * 
 */
public class EquationRootDoc extends AbstractEquationRootDoc
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationRootDoc.java 11191 2011-06-14 10:22:56Z perkinj1 $";

	/**
	 * Constructor to accept a RootDoc instance
	 * 
	 * @param rootDoc
	 *            - the root document to encapsulate
	 */
	public EquationRootDoc(RootDoc rootDoc)
	{
		super(rootDoc);
	}

	// Note:
	// All of these classes have been overridden in order to return
	// the Equation equivalent classes

	public ClassDoc classNamed(String s)
	{
		return JavaDocToolbox.convertToEquationClassDoc(rootDoc.classNamed(s));
	}

	public ClassDoc[] classes()
	{
		return JavaDocToolbox.filterAndConvertToEquationClassDoc(rootDoc.classes());
	}

	public PackageDoc packageNamed(String s)
	{
		return JavaDocToolbox.convertToEquationPackageDoc(rootDoc.packageNamed(s));
	}

	public ClassDoc[] specifiedClasses()
	{
		return JavaDocToolbox.filterAndConvertToEquationClassDoc(rootDoc.specifiedClasses());
	}

	public PackageDoc[] specifiedPackages()
	{
		return JavaDocToolbox.filterAndConvertToEquationPackageDoc(rootDoc.specifiedPackages());
	}

	public int compareTo(Object o)
	{
		return rootDoc.name().compareTo(o.toString());
	}

}
