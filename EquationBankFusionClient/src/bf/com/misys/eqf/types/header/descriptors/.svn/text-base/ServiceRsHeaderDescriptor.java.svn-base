/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header.descriptors;

// ---------------------------------/
// - Imported classes and packages -/
// ---------------------------------/

import bf.com.misys.eqf.types.header.ServiceRsHeader;

/**
 * Class ServiceRsHeaderDescriptor.
 * 
 * @version $Revision$ $Date$
 */
public class ServiceRsHeaderDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl
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

	public ServiceRsHeaderDescriptor()
	{
		super();
		_nsURI = "http://www.misys.com/eqf/types/header";
		_xmlName = "serviceRsHeader";
		_elementDefinition = false;

		// -- set grouping compositor
		setCompositorAsSequence();
		org.exolab.castor.xml.util.XMLFieldDescriptorImpl desc = null;
		org.exolab.castor.mapping.FieldHandler handler = null;
		org.exolab.castor.xml.FieldValidator fieldValidator = null;
		// -- initialize attribute descriptors

		// -- initialize element descriptors

		// -- _rsHeader
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.RsHeader.class, "_rsHeader",
						"rsHeader", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getRsHeader();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setRsHeader((bf.com.misys.eqf.types.header.RsHeader) value);
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
				return new bf.com.misys.eqf.types.header.RsHeader();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.RsHeader");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _rsHeader
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _checksum
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.String.class, "_checksum", "checksum",
						org.exolab.castor.xml.NodeType.Element);
		desc.setImmutable(true);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getChecksum();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setChecksum((java.lang.String) value);
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

		// -- validation code for: _checksum
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
			org.exolab.castor.xml.validators.StringValidator typeValidator;
			typeValidator = new org.exolab.castor.xml.validators.StringValidator();
			fieldValidator.setValidator(typeValidator);
			typeValidator.setWhiteSpace("preserve");
			typeValidator.setMaxLength(50);
		}
		desc.setValidator(fieldValidator);
		// -- _enquiryRs
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.EnquiryRs.class, "_enquiryRs",
						"enquiryRs", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getEnquiryRs();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setEnquiryRs((bf.com.misys.eqf.types.header.EnquiryRs) value);
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
				return new bf.com.misys.eqf.types.header.EnquiryRs();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.EnquiryRs");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _enquiryRs
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _journalKey
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.JournalKey.class, "_journalKey",
						"journalKey", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getJournalKey();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setJournalKey((bf.com.misys.eqf.types.header.JournalKey) value);
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
				return new bf.com.misys.eqf.types.header.JournalKey();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.JournalKey");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _journalKey
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _crmMessages
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.CrmMessages.class,
						"_crmMessages", "crmMessages", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getCrmMessages();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setCrmMessages((bf.com.misys.eqf.types.header.CrmMessages) value);
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
				return new bf.com.misys.eqf.types.header.CrmMessages();
			}
		};
		desc.setSchemaType("crmMessages");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setRequired(true);
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _crmMessages
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		fieldValidator.setMinOccurs(1);
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _uiControlRs
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.UiControlRs.class,
						"_uiControlRs", "uiControlRs", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getUiControlRs();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setUiControlRs((bf.com.misys.eqf.types.header.UiControlRs) value);
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
				return new bf.com.misys.eqf.types.header.UiControlRs();
			}
		};
		desc.setSchemaType("uiControlRs");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _uiControlRs
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _misysExtensionData
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.ExtensionDataRs.class,
						"_misysExtensionData", "misysExtensionData", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getMisysExtensionData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setMisysExtensionData((bf.com.misys.eqf.types.header.ExtensionDataRs) value);
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
				return new bf.com.misys.eqf.types.header.ExtensionDataRs();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.ExtensionDataRs");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _misysExtensionData
		fieldValidator = new org.exolab.castor.xml.FieldValidator();
		{ // -- local scope
		}
		desc.setValidator(fieldValidator);
		// -- _userExtensionData
		desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(bf.com.misys.eqf.types.header.ExtensionDataRs.class,
						"_userExtensionData", "userExtensionData", org.exolab.castor.xml.NodeType.Element);
		handler = new org.exolab.castor.xml.XMLFieldHandler()
		{
			@Override
			public java.lang.Object getValue(java.lang.Object object) throws IllegalStateException
			{
				ServiceRsHeader target = (ServiceRsHeader) object;
				return target.getUserExtensionData();
			}
			@Override
			public void setValue(java.lang.Object object, java.lang.Object value) throws IllegalStateException,
							IllegalArgumentException
			{
				try
				{
					ServiceRsHeader target = (ServiceRsHeader) object;
					target.setUserExtensionData((bf.com.misys.eqf.types.header.ExtensionDataRs) value);
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
				return new bf.com.misys.eqf.types.header.ExtensionDataRs();
			}
		};
		desc.setSchemaType("bf.com.misys.eqf.types.header.ExtensionDataRs");
		desc.setHandler(handler);
		desc.setNameSpaceURI("http://www.misys.com/eqf/types/header");
		desc.setMultivalued(false);
		addFieldDescriptor(desc);
		addSequenceElement(desc);

		// -- validation code for: _userExtensionData
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
		return bf.com.misys.eqf.types.header.ServiceRsHeader.class;
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
