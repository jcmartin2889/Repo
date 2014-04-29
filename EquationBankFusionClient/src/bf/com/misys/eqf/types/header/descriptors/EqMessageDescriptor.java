/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header.descriptors;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import bf.com.misys.eqf.types.header.EqMessage;

/**
 * Class EqMessageDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class EqMessageDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl
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

	public EqMessageDescriptor()
	{
		super();
		_nsURI = "http://www.misys.com/eqf/types/header";
		_xmlName = "eqMessage";
		_elementDefinition = false;

		// -- set grouping compositor
		setCompositorAsSequence();
		org.exolab.castor.xml.util.XMLFieldDescriptorImpl desc = null;
		org.exolab.castor.mapping.FieldHandler handler = null;
		org.exolab.castor.xml.FieldValidator fieldValidator = null;
		// -- initialize attribute descriptors

		// -- initialize element descriptors

		// -- _eqMessageId
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_eqMessageId", "eqMessageId",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getEqMessageId();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setEqMessageId((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _eqMessageId
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(7);
		}
		desc.setValidator(fieldValidator);
		// -- _eqMessageSeverity
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_eqMessageSeverity",
						"eqMessageSeverity", org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getEqMessageSeverity();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setEqMessageSeverity((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _eqMessageSeverity
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
		// -- _formattedMessage
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_formattedMessage",
						"formattedMessage", org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getFormattedMessage();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setFormattedMessage((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _formattedMessage
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
		// -- _eqMessageParameter1
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_eqMessageParameter1",
						"eqMessageParameter1", org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getEqMessageParameter1();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setEqMessageParameter1((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _eqMessageParameter1
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(10);
		}
		desc.setValidator(fieldValidator);
		// -- _eqMessageParameter2
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_eqMessageParameter2",
						"eqMessageParameter2", org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getEqMessageParameter2();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setEqMessageParameter2((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _eqMessageParameter2
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(10);
		}
		desc.setValidator(fieldValidator);
		// -- _eqMessageParameter3
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_eqMessageParameter3",
						"eqMessageParameter3", org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getEqMessageParameter3();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setEqMessageParameter3((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _eqMessageParameter3
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(10);
		}
		desc.setValidator(fieldValidator);
		// -- _firstLevelText
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_firstLevelText", "firstLevelText",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getFirstLevelText();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setFirstLevelText((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _firstLevelText
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
		// -- _secondLevelText
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_secondLevelText", "secondLevelText",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getSecondLevelText();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setSecondLevelText((java.lang.String) value);
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
		desc.setSchemaType("string");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _secondLevelText
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
		// -- _fieldLocation
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.FieldLocation.class,
						"_fieldLocation", "fieldLocation", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getFieldLocation();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setFieldLocation((bf.com.misys.eqf.types.header.FieldLocation) value);
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
				return new bf.com.misys.eqf.types.header.FieldLocation();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.FieldLocation");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _fieldLocation
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _branchLimit
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.BranchLimitParameters.class,
						"_branchLimit", "branchLimit", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getBranchLimit();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setBranchLimit((bf.com.misys.eqf.types.header.BranchLimitParameters) value);
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
				return new bf.com.misys.eqf.types.header.BranchLimitParameters();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.BranchLimitParameters");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _branchLimit
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _overridden
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Boolean.class, "_overridden", "overridden",
						org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getOverridden();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setOverridden((java.lang.Boolean) value);
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
		desc.setSchemaType("boolean");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _overridden
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
			org.exolab.castor.xml.validators.BooleanValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.BooleanValidator();
			fieldValidator.setValidator(typeValidator);
		}
		desc.setValidator(fieldValidator);
		// -- _serviceStack
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.ServiceStack.class,
						"_serviceStack", "serviceStack", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				EqMessage target = (EqMessage) object;
				return target.getServiceStack();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					EqMessage target = (EqMessage) object;
					target.setServiceStack((bf.com.misys.eqf.types.header.ServiceStack) value);
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
				return new bf.com.misys.eqf.types.header.ServiceStack();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.ServiceStack");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _serviceStack
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
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
		return bf.com.misys.eqf.types.header.EqMessage.class;
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
