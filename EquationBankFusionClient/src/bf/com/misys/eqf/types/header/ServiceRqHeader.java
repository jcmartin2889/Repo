/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class ServiceRqHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class ServiceRqHeader implements java.io.Serializable
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
	 * Field _rqHeader.
	 */
	private bf.com.misys.eqf.types.header.RqHeader _rqHeader;

	/**
	 * Equation Service Option ID name eg GSS. User must be authorised in Equation to use this option.
	 * 
	 */
	private java.lang.String _optionId;

	/**
	 * This specifies the mode of the service: ' '-Auto detect Add or Maintain 'A'-Create (Add) Function 'M'-Update (Maintain)
	 * Function 'D'-Cancel Function 'B'-Retrieve (for later Update) 'E'-Read/Search Enquiry Functions 'V'-Validate
	 * 
	 */
	private java.lang.String _serviceMode;

	/**
	 * Information used for enquiries that page.
	 * 
	 */
	private bf.com.misys.eqf.types.header.EnquiryRq _enquiryRq;

	/**
	 * For update services only. A collection of XPath for each complex type or field to include in the checksum in both Retrieve
	 * and Update mode. If this parameter is not used all possible response fields will be included. If 'none' is specified no
	 * checksum will be calculated.
	 * 
	 */
	private java.util.Vector<java.lang.String> _checksumFilterList;

	/**
	 * For optional intermediate update checking this is the checksum from the original retrieve mode. An error is returned if the
	 * checksum has changed.
	 * 
	 */
	private java.lang.String _checksum;

	/**
	 * Reserved for future use.
	 * 
	 */
	private java.lang.String _supervisor;

	/**
	 * Reserved for future use.
	 * 
	 */
	private java.lang.String _reference;

	/**
	 * Field _uiControlRq.
	 */
	private bf.com.misys.eqf.types.header.UiControlRq _uiControlRq;

	/**
	 * This extension data is for Misys use only
	 * 
	 */
	private bf.com.misys.eqf.types.header.ExtensionDataRq _misysExtensionData;

	/**
	 * This extension data is for Bank use only
	 * 
	 */
	private bf.com.misys.eqf.types.header.ExtensionDataRq _userExtensionData;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public ServiceRqHeader()
	{
		super();
		this._checksumFilterList = new java.util.Vector<java.lang.String>();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * 
	 * 
	 * @param vChecksumFilter
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addChecksumFilter(final java.lang.String vChecksumFilter) throws java.lang.IndexOutOfBoundsException
	{
		this._checksumFilterList.addElement(vChecksumFilter);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vChecksumFilter
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addChecksumFilter(final int index, final java.lang.String vChecksumFilter)
					throws java.lang.IndexOutOfBoundsException
	{
		this._checksumFilterList.add(index, vChecksumFilter);
	}

	/**
	 * Method enumerateChecksumFilter.
	 * 
	 * @return an Enumeration over all java.lang.String elements
	 */
	public java.util.Enumeration<? extends java.lang.String> enumerateChecksumFilter()
	{
		return this._checksumFilterList.elements();
	}

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

		if (obj instanceof ServiceRqHeader)
		{

			ServiceRqHeader temp = (ServiceRqHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._rqHeader != null)
			{
				if (temp._rqHeader == null)
					return false;
				if (this._rqHeader != temp._rqHeader)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._rqHeader);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._rqHeader);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rqHeader);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rqHeader);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._rqHeader.equals(temp._rqHeader))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rqHeader);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rqHeader);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rqHeader);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rqHeader);
					}
				}
			}
			else if (temp._rqHeader != null)
				return false;
			if (this._optionId != null)
			{
				if (temp._optionId == null)
					return false;
				if (this._optionId != temp._optionId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._optionId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._optionId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._optionId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._optionId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._optionId.equals(temp._optionId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._optionId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._optionId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._optionId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._optionId);
					}
				}
			}
			else if (temp._optionId != null)
				return false;
			if (this._serviceMode != null)
			{
				if (temp._serviceMode == null)
					return false;
				if (this._serviceMode != temp._serviceMode)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._serviceMode);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._serviceMode);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceMode);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceMode);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._serviceMode.equals(temp._serviceMode))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceMode);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceMode);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceMode);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceMode);
					}
				}
			}
			else if (temp._serviceMode != null)
				return false;
			if (this._enquiryRq != null)
			{
				if (temp._enquiryRq == null)
					return false;
				if (this._enquiryRq != temp._enquiryRq)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._enquiryRq);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._enquiryRq);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._enquiryRq);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._enquiryRq);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._enquiryRq.equals(temp._enquiryRq))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._enquiryRq);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._enquiryRq);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._enquiryRq);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._enquiryRq);
					}
				}
			}
			else if (temp._enquiryRq != null)
				return false;
			if (this._checksumFilterList != null)
			{
				if (temp._checksumFilterList == null)
					return false;
				if (this._checksumFilterList != temp._checksumFilterList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._checksumFilterList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._checksumFilterList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._checksumFilterList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._checksumFilterList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._checksumFilterList.equals(temp._checksumFilterList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._checksumFilterList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._checksumFilterList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._checksumFilterList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._checksumFilterList);
					}
				}
			}
			else if (temp._checksumFilterList != null)
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
			if (this._supervisor != null)
			{
				if (temp._supervisor == null)
					return false;
				if (this._supervisor != temp._supervisor)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._supervisor);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._supervisor);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._supervisor);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._supervisor);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._supervisor.equals(temp._supervisor))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._supervisor);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._supervisor);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._supervisor);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._supervisor);
					}
				}
			}
			else if (temp._supervisor != null)
				return false;
			if (this._reference != null)
			{
				if (temp._reference == null)
					return false;
				if (this._reference != temp._reference)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._reference);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._reference);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reference);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reference);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._reference.equals(temp._reference))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reference);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reference);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._reference);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._reference);
					}
				}
			}
			else if (temp._reference != null)
				return false;
			if (this._uiControlRq != null)
			{
				if (temp._uiControlRq == null)
					return false;
				if (this._uiControlRq != temp._uiControlRq)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._uiControlRq);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._uiControlRq);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRq);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRq);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._uiControlRq.equals(temp._uiControlRq))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRq);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRq);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRq);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRq);
					}
				}
			}
			else if (temp._uiControlRq != null)
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
	 * Returns the value of field 'checksum'. The field 'checksum' has the following description: For optional intermediate update
	 * checking this is the checksum from the original retrieve mode. An error is returned if the checksum has changed.
	 * 
	 * 
	 * @return the value of field 'Checksum'.
	 */
	public java.lang.String getChecksum()
	{
		return this._checksum;
	}

	/**
	 * Method getChecksumFilter.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the java.lang.String at the given index
	 */
	public java.lang.String getChecksumFilter(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._checksumFilterList.size())
		{
			throw new IndexOutOfBoundsException("getChecksumFilter: Index value '" + index + "' not in range [0.."
							+ (this._checksumFilterList.size() - 1) + "]");
		}

		return (java.lang.String) _checksumFilterList.get(index);
	}

	/**
	 * Method getChecksumFilter.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public java.lang.String[] getChecksumFilter()
	{
		java.lang.String[] array = new java.lang.String[0];
		return (java.lang.String[]) this._checksumFilterList.toArray(array);
	}

	/**
	 * Method getChecksumFilterCount.
	 * 
	 * @return the size of this collection
	 */
	public int getChecksumFilterCount()
	{
		return this._checksumFilterList.size();
	}

	/**
	 * Returns the value of field 'enquiryRq'. The field 'enquiryRq' has the following description: Information used for enquiries
	 * that page.
	 * 
	 * 
	 * @return the value of field 'EnquiryRq'.
	 */
	public bf.com.misys.eqf.types.header.EnquiryRq getEnquiryRq()
	{
		return this._enquiryRq;
	}

	/**
	 * Returns the value of field 'misysExtensionData'. The field 'misysExtensionData' has the following description: This extension
	 * data is for Misys use only
	 * 
	 * 
	 * @return the value of field 'MisysExtensionData'.
	 */
	public bf.com.misys.eqf.types.header.ExtensionDataRq getMisysExtensionData()
	{
		return this._misysExtensionData;
	}

	/**
	 * Returns the value of field 'optionId'. The field 'optionId' has the following description: Equation Service Option ID name eg
	 * GSS. User must be authorised in Equation to use this option.
	 * 
	 * 
	 * @return the value of field 'OptionId'.
	 */
	public java.lang.String getOptionId()
	{
		return this._optionId;
	}

	/**
	 * Returns the value of field 'reference'. The field 'reference' has the following description: Reserved for future use.
	 * 
	 * 
	 * @return the value of field 'Reference'.
	 */
	public java.lang.String getReference()
	{
		return this._reference;
	}

	/**
	 * Returns the value of field 'rqHeader'.
	 * 
	 * @return the value of field 'RqHeader'.
	 */
	public bf.com.misys.eqf.types.header.RqHeader getRqHeader()
	{
		return this._rqHeader;
	}

	/**
	 * Returns the value of field 'serviceMode'. The field 'serviceMode' has the following description: This specifies the mode of
	 * the service: ' '-Auto detect Add or Maintain 'A'-Create (Add) Function 'M'-Update (Maintain) Function 'D'-Cancel Function
	 * 'B'-Retrieve (for later Update) 'E'-Read/Search Enquiry Functions 'V'-Validate
	 * 
	 * 
	 * @return the value of field 'ServiceMode'.
	 */
	public java.lang.String getServiceMode()
	{
		return this._serviceMode;
	}

	/**
	 * Returns the value of field 'supervisor'. The field 'supervisor' has the following description: Reserved for future use.
	 * 
	 * 
	 * @return the value of field 'Supervisor'.
	 */
	public java.lang.String getSupervisor()
	{
		return this._supervisor;
	}

	/**
	 * Returns the value of field 'uiControlRq'.
	 * 
	 * @return the value of field 'UiControlRq'.
	 */
	public bf.com.misys.eqf.types.header.UiControlRq getUiControlRq()
	{
		return this._uiControlRq;
	}

	/**
	 * Returns the value of field 'userExtensionData'. The field 'userExtensionData' has the following description: This extension
	 * data is for Bank use only
	 * 
	 * 
	 * @return the value of field 'UserExtensionData'.
	 */
	public bf.com.misys.eqf.types.header.ExtensionDataRq getUserExtensionData()
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
		if (_rqHeader != null && !org.castor.core.util.CycleBreaker.startingToCycle(_rqHeader))
		{
			result = 37 * result + _rqHeader.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_rqHeader);
		}
		if (_optionId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_optionId))
		{
			result = 37 * result + _optionId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_optionId);
		}
		if (_serviceMode != null && !org.castor.core.util.CycleBreaker.startingToCycle(_serviceMode))
		{
			result = 37 * result + _serviceMode.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_serviceMode);
		}
		if (_enquiryRq != null && !org.castor.core.util.CycleBreaker.startingToCycle(_enquiryRq))
		{
			result = 37 * result + _enquiryRq.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_enquiryRq);
		}
		if (_checksumFilterList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_checksumFilterList))
		{
			result = 37 * result + _checksumFilterList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_checksumFilterList);
		}
		if (_checksum != null && !org.castor.core.util.CycleBreaker.startingToCycle(_checksum))
		{
			result = 37 * result + _checksum.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_checksum);
		}
		if (_supervisor != null && !org.castor.core.util.CycleBreaker.startingToCycle(_supervisor))
		{
			result = 37 * result + _supervisor.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_supervisor);
		}
		if (_reference != null && !org.castor.core.util.CycleBreaker.startingToCycle(_reference))
		{
			result = 37 * result + _reference.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_reference);
		}
		if (_uiControlRq != null && !org.castor.core.util.CycleBreaker.startingToCycle(_uiControlRq))
		{
			result = 37 * result + _uiControlRq.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_uiControlRq);
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
     */
	public void removeAllChecksumFilter()
	{
		this._checksumFilterList.clear();
	}

	/**
	 * Method removeChecksumFilter.
	 * 
	 * @param vChecksumFilter
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeChecksumFilter(final java.lang.String vChecksumFilter)
	{
		boolean removed = _checksumFilterList.remove(vChecksumFilter);
		return removed;
	}

	/**
	 * Method removeChecksumFilterAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public java.lang.String removeChecksumFilterAt(final int index)
	{
		java.lang.Object obj = this._checksumFilterList.remove(index);
		return (java.lang.String) obj;
	}

	/**
	 * Sets the value of field 'checksum'. The field 'checksum' has the following description: For optional intermediate update
	 * checking this is the checksum from the original retrieve mode. An error is returned if the checksum has changed.
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
	 * 
	 * 
	 * @param index
	 * @param vChecksumFilter
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setChecksumFilter(final int index, final java.lang.String vChecksumFilter)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._checksumFilterList.size())
		{
			throw new IndexOutOfBoundsException("setChecksumFilter: Index value '" + index + "' not in range [0.."
							+ (this._checksumFilterList.size() - 1) + "]");
		}

		this._checksumFilterList.set(index, vChecksumFilter);
	}

	/**
	 * 
	 * 
	 * @param vChecksumFilterArray
	 */
	public void setChecksumFilter(final java.lang.String[] vChecksumFilterArray)
	{
		// -- copy array
		_checksumFilterList.clear();

		for (int i = 0; i < vChecksumFilterArray.length; i++)
		{
			this._checksumFilterList.add(vChecksumFilterArray[i]);
		}
	}

	/**
	 * Sets the value of field 'enquiryRq'. The field 'enquiryRq' has the following description: Information used for enquiries that
	 * page.
	 * 
	 * 
	 * @param enquiryRq
	 *            the value of field 'enquiryRq'.
	 */
	public void setEnquiryRq(final bf.com.misys.eqf.types.header.EnquiryRq enquiryRq)
	{
		this._enquiryRq = enquiryRq;
	}

	/**
	 * Sets the value of field 'misysExtensionData'. The field 'misysExtensionData' has the following description: This extension
	 * data is for Misys use only
	 * 
	 * 
	 * @param misysExtensionData
	 *            the value of field 'misysExtensionData'.
	 */
	public void setMisysExtensionData(final bf.com.misys.eqf.types.header.ExtensionDataRq misysExtensionData)
	{
		this._misysExtensionData = misysExtensionData;
	}

	/**
	 * Sets the value of field 'optionId'. The field 'optionId' has the following description: Equation Service Option ID name eg
	 * GSS. User must be authorised in Equation to use this option.
	 * 
	 * 
	 * @param optionId
	 *            the value of field 'optionId'.
	 */
	public void setOptionId(final java.lang.String optionId)
	{
		this._optionId = optionId;
	}

	/**
	 * Sets the value of field 'reference'. The field 'reference' has the following description: Reserved for future use.
	 * 
	 * 
	 * @param reference
	 *            the value of field 'reference'.
	 */
	public void setReference(final java.lang.String reference)
	{
		this._reference = reference;
	}

	/**
	 * Sets the value of field 'rqHeader'.
	 * 
	 * @param rqHeader
	 *            the value of field 'rqHeader'.
	 */
	public void setRqHeader(final bf.com.misys.eqf.types.header.RqHeader rqHeader)
	{
		this._rqHeader = rqHeader;
	}

	/**
	 * Sets the value of field 'serviceMode'. The field 'serviceMode' has the following description: This specifies the mode of the
	 * service: ' '-Auto detect Add or Maintain 'A'-Create (Add) Function 'M'-Update (Maintain) Function 'D'-Cancel Function
	 * 'B'-Retrieve (for later Update) 'E'-Read/Search Enquiry Functions 'V'-Validate
	 * 
	 * 
	 * @param serviceMode
	 *            the value of field 'serviceMode'.
	 */
	public void setServiceMode(final java.lang.String serviceMode)
	{
		this._serviceMode = serviceMode;
	}

	/**
	 * Sets the value of field 'supervisor'. The field 'supervisor' has the following description: Reserved for future use.
	 * 
	 * 
	 * @param supervisor
	 *            the value of field 'supervisor'.
	 */
	public void setSupervisor(final java.lang.String supervisor)
	{
		this._supervisor = supervisor;
	}

	/**
	 * Sets the value of field 'uiControlRq'.
	 * 
	 * @param uiControlRq
	 *            the value of field 'uiControlRq'.
	 */
	public void setUiControlRq(final bf.com.misys.eqf.types.header.UiControlRq uiControlRq)
	{
		this._uiControlRq = uiControlRq;
	}

	/**
	 * Sets the value of field 'userExtensionData'. The field 'userExtensionData' has the following description: This extension data
	 * is for Bank use only
	 * 
	 * 
	 * @param userExtensionData
	 *            the value of field 'userExtensionData'.
	 */
	public void setUserExtensionData(final bf.com.misys.eqf.types.header.ExtensionDataRq userExtensionData)
	{
		this._userExtensionData = userExtensionData;
	}

	/**
	 * Method unmarshalServiceRqHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.ServiceRqHeader
	 */
	public static bf.com.misys.eqf.types.header.ServiceRqHeader unmarshalServiceRqHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.ServiceRqHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.ServiceRqHeader.class, reader);
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
