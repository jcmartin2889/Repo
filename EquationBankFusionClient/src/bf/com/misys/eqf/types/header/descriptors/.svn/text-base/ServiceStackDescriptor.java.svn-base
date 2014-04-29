/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header.descriptors;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import bf.com.misys.eqf.types.header.ServiceStack;

/**
 * Class ServiceStackDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class ServiceStackDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field _elementDefinition.
	 */
	private boolean _elementDefinition;

	/**
	 * Field _nsPrefix.
	 */
	private java.lang.String _nsPrefix;

	/**
	 * Field _nsURI.
	 */
	private java.lang.String _nsURI;

	/**
	 * Field _xmlName.
	 */
	private java.lang.String _xmlName;

	/**
	 * Field _identity.
	 */
	private org.exolab.castor.xml.XMLFieldDescriptor _identity;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public ServiceStackDescriptor()
	{
		super();
		_nsURI = "http://www.misys.com/eqf/types/header";
		_xmlName = "serviceStack";
		_elementDefinition = false;

		// -- set grouping compositor
		setCompositorAsSequence();
		org.exolab.castor.xml.util.XMLFieldDescriptorImpl desc = null;
		org.exolab.castor.mapping.FieldHandler handler = null;
		org.exolab.castor.xml.FieldValidator fieldValidator = null;
		// -- initialize attribute descriptors

		// -- initialize element descriptors

		// -- _serviceStackEntryList
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_serviceStackEntryList",
						"serviceStackEntry", org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceStack target = (ServiceStack) object;
				return target.getServiceStackEntry();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceStack target = (ServiceStack) object;
					target.addServiceStackEntry((java.lang.String) value);
				}
				catch (java.lang.Exception ex)
				{
					throw new IllegalStateException(ex.toString());
				}
			}
			public void resetValue(Object object) throws IllegalStateException, IllegalArgumentException
			{
				try
				{
					ServiceStack target = (ServiceStack) object;
					target.removeAllServiceStackEntry();
				}
				catch (java.lang.Exception ex)
				{
					throw new IllegalStateException(ex.toString());
				}
			}
			@Override
			@SuppressWarnings("unused")
			public java.lang.Object newInstance(java.lang.Object parent)
			{
				return null;
			}
		};
		desc.setSchemaType("list");
		desc.setComponentType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(true);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _serviceStackEntryList
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(0);
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Method getAccessMode.
	 * 
	 * @return the access mode specified for this class.
	 */
	@Override()
	public org.exolab.castor.mapping.AccessMode getAccessMode()
	{
		return null;
	}

	/**
	 * Method getIdentity.
	 * 
	 * @return the identity field, null if this class has no identity.
	 */
	@Override()
	public org.exolab.castor.mapping.FieldDescriptor getIdentity()
	{
		return _identity;
	}

	/**
	 * Method getJavaClass.
	 * 
	 * @return the Java class represented by this descriptor.
	 */
	@Override()
	public java.lang.Class getJavaClass()
	{
		return bf.com.misys.eqf.types.header.ServiceStack.class;
	}

	/**
	 * Method getNameSpacePrefix.
	 * 
	 * @return the namespace prefix to use when marshaling as XML.
	 */
	@Override()
	public java.lang.String getNameSpacePrefix()
	{
		return _nsPrefix;
	}

	/**
	 * Method getNameSpaceURI.
	 * 
	 * @return the namespace URI used when marshaling and unmarshaling as XML.
	 */
	@Override()
	public java.lang.String getNameSpaceURI()
	{
		return _nsURI;
	}

	/**
	 * Method getValidator.
	 * 
	 * @return a specific validator for the class described by this ClassDescriptor.
	 */
	@Override()
	public org.exolab.castor.xml.TypeValidator getValidator()
	{
		return this;
	}

	/**
	 * Method getXMLName.
	 * 
	 * @return the XML Name for the Class being described.
	 */
	@Override()
	public java.lang.String getXMLName()
	{
		return _xmlName;
	}

	/**
	 * Method isElementDefinition.
	 * 
	 * @return true if XML schema definition of this Class is that of a global element or element with anonymous type definition.
	 */
	public boolean isElementDefinition()
	{
		return _elementDefinition;
	}

}
