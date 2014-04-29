/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header.descriptors;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import bf.com.misys.eqf.types.header.TaskEsfRqHeader;

/**
 * Class TaskEsfRqHeaderDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class TaskEsfRqHeaderDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl
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

	public TaskEsfRqHeaderDescriptor()
	{
		super();
		_nsURI = "http://www.misys.com/eqf/types/header";
		_xmlName = "TaskEsfRqHeader";
		_elementDefinition = false;

		// -- set grouping compositor
		setCompositorAsSequence();
		org.exolab.castor.xml.util.XMLFieldDescriptorImpl desc = null;
		org.exolab.castor.mapping.FieldHandler handler = null;
		org.exolab.castor.xml.FieldValidator fieldValidator = null;
		// -- initialize attribute descriptors

		// -- initialize element descriptors

		// -- _basicDetail
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.TaskBasicDetails.class,
						"_basicDetail", "basicDetail", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getBasicDetail();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setBasicDetail((bf.com.misys.eqf.types.header.TaskBasicDetails) value);
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
				return new bf.com.misys.eqf.types.header.TaskBasicDetails();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.TaskBasicDetails");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _basicDetail
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _systemId
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_systemId", "systemId",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getSystemId();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setSystemId((java.lang.String) value);
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

		// -- validation code for: _systemId
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(256);
		}
		desc.setValidator(fieldValidator);
		// -- _unitId
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_unitId", "unitId",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getUnitId();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setUnitId((java.lang.String) value);
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

		// -- validation code for: _unitId
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(3);
		}
		desc.setValidator(fieldValidator);
		// -- _esfKey
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.EsfKey.class, "_esfKey",
						"esfKey", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getEsfKey();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setEsfKey((bf.com.misys.eqf.types.header.EsfKey) value);
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
				return new bf.com.misys.eqf.types.header.EsfKey();
			}
		};
		desc.setSchemaType("EsfKey");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _esfKey
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _supervisorId
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_supervisorId", "supervisorId",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getSupervisorId();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setSupervisorId((java.lang.String) value);
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

		// -- validation code for: _supervisorId
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(10);
		}
		desc.setValidator(fieldValidator);
		// -- _currentScreenFieldSet
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_currentScreenFieldSet",
						"currentScreenFieldSet", org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getCurrentScreenFieldSet();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setCurrentScreenFieldSet((java.lang.String) value);
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

		// -- validation code for: _currentScreenFieldSet
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
		// -- _currentFieldSet
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_currentFieldSet", "currentFieldSet",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getCurrentFieldSet();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setCurrentFieldSet((java.lang.String) value);
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

		// -- validation code for: _currentFieldSet
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
		// -- _serviceData
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Object.class, "_serviceData", "serviceData",
						org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getServiceData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setServiceData((java.lang.Object) value);
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
				return new java.lang.Object();
			}
		};
		desc.setSchemaType("java.lang.Object");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _serviceData
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _crmData
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Object.class, "_crmData", "crmData",
						org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getCrmData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setCrmData((java.lang.Object) value);
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
				return new java.lang.Object();
			}
		};
		desc.setSchemaType("java.lang.Object");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _crmData
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _efcData
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Object.class, "_efcData", "efcData",
						org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getEfcData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setEfcData((java.lang.Object) value);
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
				return new java.lang.Object();
			}
		};
		desc.setSchemaType("java.lang.Object");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _efcData
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _messages
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.MessageStatus.class,
						"_messages", "messages", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskEsfRqHeader target = (TaskEsfRqHeader) object;
				return target.getMessages();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskEsfRqHeader target = (TaskEsfRqHeader) object;
					target.setMessages((bf.com.misys.eqf.types.header.MessageStatus) value);
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
				return new bf.com.misys.eqf.types.header.MessageStatus();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.MessageStatus");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _messages
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
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
		return bf.com.misys.eqf.types.header.TaskEsfRqHeader.class;
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
