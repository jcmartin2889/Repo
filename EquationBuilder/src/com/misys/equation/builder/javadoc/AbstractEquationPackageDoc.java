package com.misys.equation.builder.javadoc;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationTypeDoc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.SeeTag;
import com.sun.javadoc.SourcePosition;
import com.sun.javadoc.Tag;

/**
 * This class encapsulates a PackageDoc instance.<br>
 * 
 * It implements the interface PackageDoc and use the PackageDoc instance to execute each method
 * 
 */
public class AbstractEquationPackageDoc extends AbstractEquationDoc implements PackageDoc
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractEquationPackageDoc.java 11191 2011-06-14 10:22:56Z perkinj1 $";

	// The package doc to encapsulates
	protected PackageDoc packageDoc;

	public AbstractEquationPackageDoc(PackageDoc packageDoc)
	{
		this.packageDoc = packageDoc;
	}

	public ClassDoc[] allClasses()
	{
		return packageDoc.allClasses();
	}

	public ClassDoc[] allClasses(boolean flag)
	{
		return packageDoc.allClasses(flag);
	}

	public AnnotationTypeDoc[] annotationTypes()
	{
		return packageDoc.annotationTypes();
	}

	public AnnotationDesc[] annotations()
	{
		return packageDoc.annotations();
	}

	public ClassDoc[] enums()
	{
		return packageDoc.enums();
	}

	public ClassDoc[] errors()
	{
		return packageDoc.errors();
	}

	public ClassDoc[] exceptions()
	{
		return packageDoc.exceptions();
	}

	public ClassDoc findClass(String s)
	{
		return packageDoc.findClass(s);
	}

	public ClassDoc[] interfaces()
	{
		return packageDoc.interfaces();
	}

	public ClassDoc[] ordinaryClasses()
	{
		return packageDoc.ordinaryClasses();
	}

	public String commentText()
	{
		return packageDoc.commentText();
	}

	public int compareTo(Object obj)
	{
		return packageDoc.compareTo(obj);
	}

	public Tag[] firstSentenceTags()
	{
		return packageDoc.firstSentenceTags();
	}

	public String getRawCommentText()
	{
		return packageDoc.getRawCommentText();
	}

	public Tag[] inlineTags()
	{
		return packageDoc.inlineTags();
	}

	public boolean isAnnotationType()
	{
		return packageDoc.isAnnotationType();
	}

	public boolean isAnnotationTypeElement()
	{
		return packageDoc.isAnnotationTypeElement();
	}

	public boolean isClass()
	{
		return packageDoc.isClass();
	}

	public boolean isConstructor()
	{
		return packageDoc.isConstructor();
	}

	public boolean isEnum()
	{
		return packageDoc.isEnum();
	}

	public boolean isEnumConstant()
	{
		return packageDoc.isEnumConstant();
	}

	public boolean isError()
	{
		return packageDoc.isError();
	}

	public boolean isException()
	{
		return packageDoc.isException();
	}

	public boolean isField()
	{
		return packageDoc.isField();
	}

	public boolean isIncluded()
	{
		return packageDoc.isIncluded();
	}

	public boolean isInterface()
	{
		return packageDoc.isInterface();
	}

	public boolean isMethod()
	{
		return packageDoc.isMethod();
	}

	public boolean isOrdinaryClass()
	{
		return packageDoc.isOrdinaryClass();
	}

	public String name()
	{
		return packageDoc.name();
	}

	public SourcePosition position()
	{
		return packageDoc.position();
	}

	public SeeTag[] seeTags()
	{
		return packageDoc.seeTags();
	}

	public void setRawCommentText(String s)
	{
		packageDoc.setRawCommentText(s);
	}

	public Tag[] tags()
	{
		return packageDoc.tags();
	}

	public Tag[] tags(String s)
	{
		return packageDoc.tags(s);
	}

}
