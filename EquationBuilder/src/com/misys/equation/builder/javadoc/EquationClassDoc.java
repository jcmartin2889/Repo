package com.misys.equation.builder.javadoc;

import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.ParameterizedType;

/**
 * This encapsulates the ClassDoc instance into an EquationClassDoc instance <br>
 * in order to return only methods and constructors for Equation Java <br>
 * Documentation
 * 
 */
public class EquationClassDoc extends AbstractEquationClassDoc
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationClassDoc.java 11191 2011-06-14 10:22:56Z perkinj1 $";

	/**
	 * Constructor to accept a ClassDoc instance
	 * 
	 * @param classDoc
	 *            - the class document to encapsulate
	 */
	public EquationClassDoc(ClassDoc classDoc)
	{
		super(classDoc);
	}

	// Note:
	// All of these classes have been overridden in order to return
	// the Equation equivalent classes

	public ConstructorDoc[] constructors()
	{
		return JavaDocToolbox.filterConstructorDoc(classDoc.constructors());
	}

	public ConstructorDoc[] constructors(boolean flag)
	{
		return JavaDocToolbox.filterConstructorDoc(classDoc.constructors(flag));
	}

	public ClassDoc findClass(String s)
	{
		return JavaDocToolbox.convertToEquationClassDoc(classDoc.findClass(s));
	}

	public ClassDoc[] importedClasses()
	{
		return JavaDocToolbox.filterAndConvertToEquationClassDoc(classDoc.importedClasses());
	}

	public PackageDoc[] importedPackages()
	{
		return JavaDocToolbox.filterAndConvertToEquationPackageDoc(classDoc.importedPackages());
	}

	public ClassDoc[] innerClasses()
	{
		return JavaDocToolbox.filterAndConvertToEquationClassDoc(classDoc.innerClasses());
	}

	public ClassDoc[] innerClasses(boolean flag)
	{
		return JavaDocToolbox.filterAndConvertToEquationClassDoc(classDoc.innerClasses());
	}

	public ClassDoc[] interfaces()
	{
		return JavaDocToolbox.filterAndConvertToEquationClassDoc(classDoc.interfaces());
	}

	public MethodDoc[] methods()
	{
		return JavaDocToolbox.filterMethodDoc(classDoc.methods());
	}

	public MethodDoc[] methods(boolean flag)
	{
		return JavaDocToolbox.filterMethodDoc(classDoc.methods(flag));
	}

	public MethodDoc[] serializationMethods()
	{
		return JavaDocToolbox.filterMethodDoc(classDoc.serializationMethods());
	}

	public ClassDoc superclass()
	{
		return JavaDocToolbox.convertToEquationClassDoc(classDoc.superclass());
	}

	public ClassDoc containingClass()
	{
		return JavaDocToolbox.convertToEquationClassDoc(classDoc.containingClass());
	}

	public PackageDoc containingPackage()
	{
		return JavaDocToolbox.convertToEquationPackageDoc(classDoc.containingPackage());
	}

	public ClassDoc asClassDoc()
	{
		return JavaDocToolbox.convertToEquationClassDoc(classDoc.asClassDoc());
	}

	public ParameterizedType asParameterizedType()
	{
		return classDoc.asParameterizedType();
	}

	public int compareTo(Object o)
	{
		return o.toString().compareTo(classDoc.name());
	}

}
