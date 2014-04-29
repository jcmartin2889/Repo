/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header.descriptors;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import bf.com.misys.eqf.types.header.TaskRqHeader;

/**
 * Class TaskRqHeaderDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class TaskRqHeaderDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl
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

	public TaskRqHeaderDescriptor()
	{
		super();
		_nsURI = "http://www.misys.com/eqf/types/header";
		_xmlName = "TaskRqHeader";
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
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getBasicDetail();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
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
		// -- _taskActionList
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_taskActionList", "taskActionList",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getTaskActionList();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
					target.setTaskActionList((java.lang.String) value);
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

		// -- validation code for: _taskActionList
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
		}
		desc.setValidator(fieldValidator);
		// -- _functionMode
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_functionMode", "functionMode",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getFunctionMode();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
					target.setFunctionMode((java.lang.String) value);
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

		// -- validation code for: _functionMode
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(1);
		}
		desc.setValidator(fieldValidator);
		// -- _performUpdate
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Boolean.class, "_performUpdate", "performUpdate",
						org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getPerformUpdate();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
					target.setPerformUpdate((java.lang.Boolean) value);
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

		// -- validation code for: _performUpdate
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
			org.exolab.castor.xml.validators.BooleanValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.BooleanValidator();
			fieldValidator.setValidator(typeValidator);
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
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getServiceData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
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
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getCrmData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
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
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getEfcData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
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
		// -- _messagesList
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.EqMessage.class,
						"_messagesList", "messages", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				TaskRqHeader target = (TaskRqHeader) object;
				return target.getMessages();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					TaskRqHeader target = (TaskRqHeader) object;
					target.addMessages((bf.com.misys.eqf.types.header.EqMessage) value);
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
					TaskRqHeader target = (TaskRqHeader) object;
					target.removeAllMessages();
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
				return new bf.com.misys.eqf.types.header.EqMessage();
			}
		};
		desc.setSchemaType("list");
		desc.setComponentType("eqMessage");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(true);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _messagesList
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(0);
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
		return bf.com.misys.eqf.types.header.TaskRqHeader.class;
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
