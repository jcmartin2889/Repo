/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class ServiceRsHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServiceRsHeader implements java.io.Serializable
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field serialVersionUID.
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * Field _rsHeader.
	 */
	private bf.com.misys.eqf.types.header.RsHeader _rsHeader;

	/**
	 * The checksum retrieved from retrieval services for intermediate update checking.
	 * 
	 */
	private java.lang.String _checksum;

	/**
	 * Information used for enquiries that page.
	 * 
	 */
	private bf.com.misys.eqf.types.header.EnquiryRs _enquiryRs;

	/**
	 * Field _journalKey.
	 */
	private bf.com.misys.eqf.types.header.JournalKey _journalKey;

	/**
	 * Field _crmMessages.
	 */
	private bf.com.misys.eqf.types.header.CrmMessages _crmMessages;

	/**
	 * Field _uiControlRs.
	 */
	private bf.com.misys.eqf.types.header.UiControlRs _uiControlRs;

	/**
	 * Field _misysExtensionData.
	 */
	private bf.com.misys.eqf.types.header.ExtensionDataRs _misysExtensionData;

	/**
	 * Field _userExtensionData.
	 */
	private bf.com.misys.eqf.types.header.ExtensionDataRs _userExtensionData;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public ServiceRsHeader()
	{
		super();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * Overrides the java.lang.Object.equals method.
	 * 
	 * @param obj
	 * @return true if the objects are equal.
	 */
	@Override()
	public boolean equals(final java.lang.Object obj)
	{
		if (this == obj)
			return true;

		if (obj instanceof ServiceRsHeader)
		{

			ServiceRsHeader temp = (ServiceRsHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._rsHeader != null)
			{
				if (temp._rsHeader == null)
					return false;
				if (this._rsHeader != temp._rsHeader)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._rsHeader);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._rsHeader);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rsHeader);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rsHeader);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._rsHeader.equals(temp._rsHeader))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rsHeader);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rsHeader);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rsHeader);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rsHeader);
					}
				}
			}
			else if (temp._rsHeader != null)
				return false;
			if (this._checksum != null)
			{
				if (temp._checksum == null)
					return false;
				if (this._checksum != temp._checksum)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._checksum);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._checksum);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._checksum);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._checksum);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._checksum.equals(temp._checksum))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._checksum);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._checksum);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._checksum);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._checksum);
					}
				}
			}
			else if (temp._checksum != null)
				return false;
			if (this._enquiryRs != null)
			{
				if (temp._enquiryRs == null)
					return false;
				if (this._enquiryRs != temp._enquiryRs)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._enquiryRs);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._enquiryRs);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._enquiryRs);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._enquiryRs);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._enquiryRs.equals(temp._enquiryRs))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._enquiryRs);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._enquiryRs);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._enquiryRs);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._enquiryRs);
					}
				}
			}
			else if (temp._enquiryRs != null)
				return false;
			if (this._journalKey != null)
			{
				if (temp._journalKey == null)
					return false;
				if (this._journalKey != temp._journalKey)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._journalKey);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._journalKey);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._journalKey);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._journalKey);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._journalKey.equals(temp._journalKey))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._journalKey);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._journalKey);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._journalKey);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._journalKey);
					}
				}
			}
			else if (temp._journalKey != null)
				return false;
			if (this._crmMessages != null)
			{
				if (temp._crmMessages == null)
					return false;
				if (this._crmMessages != temp._crmMessages)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._crmMessages);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._crmMessages);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmMessages);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmMessages);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._crmMessages.equals(temp._crmMessages))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmMessages);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmMessages);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._crmMessages);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._crmMessages);
					}
				}
			}
			else if (temp._crmMessages != null)
				return false;
			if (this._uiControlRs != null)
			{
				if (temp._uiControlRs == null)
					return false;
				if (this._uiControlRs != temp._uiControlRs)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._uiControlRs);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._uiControlRs);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRs);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRs);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._uiControlRs.equals(temp._uiControlRs))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRs);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRs);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRs);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRs);
					}
				}
			}
			else if (temp._uiControlRs != null)
				return false;
			if (this._misysExtensionData != null)
			{
				if (temp._misysExtensionData == null)
					return false;
				if (this._misysExtensionData != temp._misysExtensionData)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._misysExtensionData);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._misysExtensionData);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._misysExtensionData);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._misysExtensionData);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._misysExtensionData.equals(temp._misysExtensionData))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._misysExtensionData);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._misysExtensionData);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._misysExtensionData);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._misysExtensionData);
					}
				}
			}
			else if (temp._misysExtensionData != null)
				return false;
			if (this._userExtensionData != null)
			{
				if (temp._userExtensionData == null)
					return false;
				if (this._userExtensionData != temp._userExtensionData)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._userExtensionData);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._userExtensionData);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtensionData);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtensionData);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._userExtensionData.equals(temp._userExtensionData))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtensionData);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtensionData);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtensionData);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtensionData);
					}
				}
			}
			else if (temp._userExtensionData != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'checksum'. The field 'checksum' has the following description: The checksum retrieved from
	 * retrieval services for intermediate update checking.
	 * 
	 * 
	 * @return the value of field 'Checksum'.
	 */
	public java.lang.String getChecksum()
	{
		return this._checksum;
	}

	/**
	 * Returns the value of field 'crmMessages'.
	 * 
	 * @return the value of field 'CrmMessages'.
	 */
	public bf.com.misys.eqf.types.header.CrmMessages getCrmMessages()
	{
		return this._crmMessages;
	}

	/**
	 * Returns the value of field 'enquiryRs'. The field 'enquiryRs' has the following description: Information used for enquiries
	 * that page.
	 * 
	 * 
	 * @return the value of field 'EnquiryRs'.
	 */
	public bf.com.misys.eqf.types.header.EnquiryRs getEnquiryRs()
	{
		return this._enquiryRs;
	}

	/**
	 * Returns the value of field 'journalKey'.
	 * 
	 * @return the value of field 'JournalKey'.
	 */
	public bf.com.misys.eqf.types.header.JournalKey getJournalKey()
	{
		return this._journalKey;
	}

	/**
	 * Returns the value of field 'misysExtensionData'.
	 * 
	 * @return the value of field 'MisysExtensionData'.
	 */
	public bf.com.misys.eqf.types.header.ExtensionDataRs getMisysExtensionData()
	{
		return this._misysExtensionData;
	}

	/**
	 * Returns the value of field 'rsHeader'.
	 * 
	 * @return the value of field 'RsHeader'.
	 */
	public bf.com.misys.eqf.types.header.RsHeader getRsHeader()
	{
		return this._rsHeader;
	}

	/**
	 * Returns the value of field 'uiControlRs'.
	 * 
	 * @return the value of field 'UiControlRs'.
	 */
	public bf.com.misys.eqf.types.header.UiControlRs getUiControlRs()
	{
		return this._uiControlRs;
	}

	/**
	 * Returns the value of field 'userExtensionData'.
	 * 
	 * @return the value of field 'UserExtensionData'.
	 */
	public bf.com.misys.eqf.types.header.ExtensionDataRs getUserExtensionData()
	{
		return this._userExtensionData;
	}

	/**
	 * Overrides the java.lang.Object.hashCode method.
	 * <p>
	 * The following steps came from <b>Effective Java Programming Language Guide</b> by Joshua Bloch, Chapter 3
	 * 
	 * @return a hash code value for the object.
	 */
	public int hashCode()
	{
		int result = 17;

		long tmp;
		if (_rsHeader != null && !org.castor.core.util.CycleBreaker.startingToCycle(_rsHeader))
		{
			result = 37 * result + _rsHeader.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_rsHeader);
		}
		if (_checksum != null && !org.castor.core.util.CycleBreaker.startingToCycle(_checksum))
		{
			result = 37 * result + _checksum.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_checksum);
		}
		if (_enquiryRs != null && !org.castor.core.util.CycleBreaker.startingToCycle(_enquiryRs))
		{
			result = 37 * result + _enquiryRs.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_enquiryRs);
		}
		if (_journalKey != null && !org.castor.core.util.CycleBreaker.startingToCycle(_journalKey))
		{
			result = 37 * result + _journalKey.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_journalKey);
		}
		if (_crmMessages != null && !org.castor.core.util.CycleBreaker.startingToCycle(_crmMessages))
		{
			result = 37 * result + _crmMessages.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_crmMessages);
		}
		if (_uiControlRs != null && !org.castor.core.util.CycleBreaker.startingToCycle(_uiControlRs))
		{
			result = 37 * result + _uiControlRs.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_uiControlRs);
		}
		if (_misysExtensionData != null && !org.castor.core.util.CycleBreaker.startingToCycle(_misysExtensionData))
		{
			result = 37 * result + _misysExtensionData.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_misysExtensionData);
		}
		if (_userExtensionData != null && !org.castor.core.util.CycleBreaker.startingToCycle(_userExtensionData))
		{
			result = 37 * result + _userExtensionData.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_userExtensionData);
		}

		return result;
	}

	/**
	 * Method isValid.
	 * 
	 * @return true if this object is valid according to the schema
	 */
	public boolean isValid()
	{
		try
		{
			validate();
		}
		catch (org.exolab.castor.xml.ValidationException vex)
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 
	 * @param out
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 */
	public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException,
					org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Marshaller.marshal(this, out);
	}

	/**
	 * 
	 * 
	 * @param handler
	 * @throws java.io.IOException
	 *             if an IOException occurs during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 */
	public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException,
					org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Marshaller.marshal(this, handler);
	}

	/**
	 * Sets the value of field 'checksum'. The field 'checksum' has the following description: The checksum retrieved from retrieval
	 * services for intermediate update checking.
	 * 
	 * 
	 * @param checksum
	 *            the value of field 'checksum'.
	 */
	public void setChecksum(final java.lang.String checksum)
	{
		this._checksum = checksum;
	}

	/**
	 * Sets the value of field 'crmMessages'.
	 * 
	 * @param crmMessages
	 *            the value of field 'crmMessages'.
	 */
	public void setCrmMessages(final bf.com.misys.eqf.types.header.CrmMessages crmMessages)
	{
		this._crmMessages = crmMessages;
	}

	/**
	 * Sets the value of field 'enquiryRs'. The field 'enquiryRs' has the following description: Information used for enquiries that
	 * page.
	 * 
	 * 
	 * @param enquiryRs
	 *            the value of field 'enquiryRs'.
	 */
	public void setEnquiryRs(final bf.com.misys.eqf.types.header.EnquiryRs enquiryRs)
	{
		this._enquiryRs = enquiryRs;
	}

	/**
	 * Sets the value of field 'journalKey'.
	 * 
	 * @param journalKey
	 *            the value of field 'journalKey'.
	 */
	public void setJournalKey(final bf.com.misys.eqf.types.header.JournalKey journalKey)
	{
		this._journalKey = journalKey;
	}

	/**
	 * Sets the value of field 'misysExtensionData'.
	 * 
	 * @param misysExtensionData
	 *            the value of field 'misysExtensionData'.
	 */
	public void setMisysExtensionData(final bf.com.misys.eqf.types.header.ExtensionDataRs misysExtensionData)
	{
		this._misysExtensionData = misysExtensionData;
	}

	/**
	 * Sets the value of field 'rsHeader'.
	 * 
	 * @param rsHeader
	 *            the value of field 'rsHeader'.
	 */
	public void setRsHeader(final bf.com.misys.eqf.types.header.RsHeader rsHeader)
	{
		this._rsHeader = rsHeader;
	}

	/**
	 * Sets the value of field 'uiControlRs'.
	 * 
	 * @param uiControlRs
	 *            the value of field 'uiControlRs'.
	 */
	public void setUiControlRs(final bf.com.misys.eqf.types.header.UiControlRs uiControlRs)
	{
		this._uiControlRs = uiControlRs;
	}

	/**
	 * Sets the value of field 'userExtensionData'.
	 * 
	 * @param userExtensionData
	 *            the value of field 'userExtensionData'.
	 */
	public void setUserExtensionData(final bf.com.misys.eqf.types.header.ExtensionDataRs userExtensionData)
	{
		this._userExtensionData = userExtensionData;
	}

	/**
	 * Method unmarshalServiceRsHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.ServiceRsHeader
	 */
	public static bf.com.misys.eqf.types.header.ServiceRsHeader unmarshalServiceRsHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.ServiceRsHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.ServiceRsHeader.class, reader);
	}

	/**
	 * 
	 * 
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 */
	public void validate() throws org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
		validator.validate(this);
	}

}
