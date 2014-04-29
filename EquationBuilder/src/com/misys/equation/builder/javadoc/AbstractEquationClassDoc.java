package com.misys.equation.builder.javadoc;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationTypeDoc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.PackageDoc;
import com.sun.javadoc.ParamTag;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.SeeTag;
import com.sun.javadoc.SourcePosition;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;
import com.sun.javadoc.TypeVariable;
import com.sun.javadoc.WildcardType;

/**
 * This class encapsulates a ClassDoc instance.<br>
 * 
 * It implements the interface ClassDoc and use the ClassDoc instance to execute each method
 * 
 */
public class AbstractEquationClassDoc extends AbstractEquationDoc implements ClassDoc
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractEquationClassDoc.java 11242 2011-06-21 09:52:57Z lima12 $";

	// The class doc to encapsulates
	protected ClassDoc classDoc;

	public AbstractEquationClassDoc(ClassDoc classDoc)
	{
		this.classDoc = classDoc;
	}

	public ConstructorDoc[] constructors()
	{
		return classDoc.constructors();
	}

	public ConstructorDoc[] constructors(boolean flag)
	{
		return classDoc.constructors(flag);
	}

	public boolean definesSerializableFields()
	{
		return classDoc.definesSerializableFields();
	}

	public FieldDoc[] enumConstants()
	{
		return classDoc.enumConstants();
	}

	public FieldDoc[] fields()
	{
		return classDoc.fields();
	}

	public FieldDoc[] fields(boolean flag)
	{
		return classDoc.fields(flag);
	}

	public ClassDoc findClass(String s)
	{
		return classDoc.findClass(s);
	}

	public ClassDoc[] importedClasses()
	{
		return classDoc.importedClasses();
	}

	public PackageDoc[] importedPackages()
	{
		return classDoc.importedPackages();
	}

	public ClassDoc[] innerClasses()
	{
		return classDoc.innerClasses();
	}

	public ClassDoc[] innerClasses(boolean flag)
	{
		return classDoc.innerClasses(flag);
	}

	public Type[] interfaceTypes()
	{
		return classDoc.interfaceTypes();
	}

	public ClassDoc[] interfaces()
	{
		return classDoc.interfaces();
	}

	public boolean isAbstract()
	{
		return classDoc.isAbstract();
	}

	public boolean isExternalizable()
	{
		return classDoc.isExternalizable();
	}

	public boolean isSerializable()
	{
		return classDoc.isSerializable();
	}

	public MethodDoc[] methods()
	{
		return classDoc.methods();
	}

	public MethodDoc[] methods(boolean flag)
	{
		return classDoc.methods(flag);
	}

	public FieldDoc[] serializableFields()
	{
		return classDoc.serializableFields();
	}

	public MethodDoc[] serializationMethods()
	{
		return classDoc.serializationMethods();
	}

	public boolean subclassOf(ClassDoc classdoc)
	{
		return classDoc.subclassOf(classdoc);
	}

	public ClassDoc superclass()
	{
		return classDoc.superclass();
	}

	public Type superclassType()
	{
		return classDoc.superclassType();
	}

	public ParamTag[] typeParamTags()
	{
		return classDoc.typeParamTags();
	}

	public TypeVariable[] typeParameters()
	{
		return classDoc.typeParameters();
	}

	public AnnotationDesc[] annotations()
	{
		return classDoc.annotations();
	}

	public ClassDoc containingClass()
	{
		return classDoc.containingClass();
	}

	public PackageDoc containingPackage()
	{
		return classDoc.containingPackage();
	}

	public boolean isFinal()
	{
		return classDoc.isFinal();
	}

	public boolean isPackagePrivate()
	{
		return classDoc.isPackagePrivate();
	}

	public boolean isPrivate()
	{
		return classDoc.isPrivate();
	}

	public boolean isProtected()
	{
		return classDoc.isProtected();
	}

	public boolean isPublic()
	{
		return classDoc.isPublic();
	}

	public boolean isStatic()
	{
		return classDoc.isStatic();
	}

	public int modifierSpecifier()
	{
		return classDoc.modifierSpecifier();
	}

	public String modifiers()
	{
		return classDoc.modifiers();
	}

	public String qualifiedName()
	{
		return classDoc.qualifiedName();
	}

	public String commentText()
	{
		return classDoc.commentText();
	}

	public int compareTo(Object obj)
	{
		return classDoc.compareTo(obj);
	}

	public Tag[] firstSentenceTags()
	{
		return classDoc.firstSentenceTags();
	}

	public String getRawCommentText()
	{
		return classDoc.getRawCommentText();
	}

	public Tag[] inlineTags()
	{
		return classDoc.inlineTags();
	}

	public boolean isAnnotationType()
	{
		return classDoc.isAnnotationType();
	}

	public boolean isAnnotationTypeElement()
	{
		return classDoc.isAnnotationTypeElement();
	}

	public boolean isClass()
	{
		return classDoc.isClass();
	}

	public boolean isConstructor()
	{
		return classDoc.isConstructor();
	}

	public boolean isEnum()
	{
		return classDoc.isEnum();
	}

	public boolean isEnumConstant()
	{
		return classDoc.isEnumConstant();
	}

	public boolean isError()
	{
		return classDoc.isError();
	}

	public boolean isException()
	{
		return classDoc.isException();
	}

	public boolean isField()
	{
		return classDoc.isField();
	}

	public boolean isIncluded()
	{
		return classDoc.isIncluded();
	}

	public boolean isInterface()
	{
		return classDoc.isInterface();
	}

	public boolean isMethod()
	{
		return classDoc.isMethod();
	}

	public boolean isOrdinaryClass()
	{
		return classDoc.isOrdinaryClass();
	}

	public String name()
	{
		return classDoc.name();
	}

	public SourcePosition position()
	{
		return classDoc.position();
	}

	public SeeTag[] seeTags()
	{
		return classDoc.seeTags();
	}

	public void setRawCommentText(String s)
	{
		classDoc.setRawCommentText(s);
	}

	public Tag[] tags()
	{
		return classDoc.tags();
	}

	public Tag[] tags(String s)
	{
		return classDoc.tags(s);
	}

	public AnnotationTypeDoc asAnnotationTypeDoc()
	{
		return classDoc.asAnnotationTypeDoc();
	}

	public ClassDoc asClassDoc()
	{
		return classDoc.asClassDoc();
	}

	public ParameterizedType asParameterizedType()
	{
		return classDoc.asParameterizedType();
	}

	public TypeVariable asTypeVariable()
	{
		return classDoc.asTypeVariable();
	}

	public WildcardType asWildcardType()
	{
		return classDoc.asWildcardType();
	}

	public String dimension()
	{
		return classDoc.dimension();
	}

	public boolean isPrimitive()
	{
		return classDoc.isPrimitive();
	}

	public String qualifiedTypeName()
	{
		return classDoc.qualifiedTypeName();
	}

	public String simpleTypeName()
	{
		return classDoc.simpleTypeName();
	}

	public String typeName()
	{
		return classDoc.typeName();
	}

}
