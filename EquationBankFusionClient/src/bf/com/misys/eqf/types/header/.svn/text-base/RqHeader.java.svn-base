/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class RqHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class RqHeader implements java.io.Serializable
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
	 * This specifies the session type: ' '- dedicated user session '1'- pool session
	 * 
	 */
	private java.lang.String _sessionType;

	/**
	 * If using pool session, specify a data source name here if you do not want to use the default session pool.
	 * 
	 */
	private java.lang.String _dataSourceName;

	/**
	 * The system id
	 * 
	 */
	private java.lang.String _systemId;

	/**
	 * The unit id
	 * 
	 */
	private java.lang.String _unitId;

	/**
	 * This specifies the format of the user profile: ' '- iSeries 10 character user profile identifier '1'- CAS 30 character user
	 * identifier profile
	 * 
	 */
	private java.lang.String _userIdType;

	/**
	 * Specify an Equation user here if different from the BankFusion user (dedicated user session) or data source user (pool
	 * session).
	 * 
	 */
	private java.lang.String _userId;

	/**
	 * If using a dedicated user session without BankFusion, specify an identifier here. For every user transaction, specify the
	 * same session identifier. If one is not specified a session identifier will be automatically calculated and returned in the
	 * response.
	 * 
	 */
	private java.lang.String _sessionId;

	/**
	 * If you pass in a MessageID in a request, the same value will be returned in the CorrelationID field in the response. Pairing
	 * these values can help you track and confirm that a response is returned for every request and to match specific responses to
	 * specific requests. If you do not pass a MessageID value in the request, CorrelationID is not returned.
	 * 
	 */
	private java.lang.String _messageId;

	/**
	 * This is the version of the API that the client is using eg 1.0. The response will contain the current API version. An error
	 * will be generated if the version is not supported.
	 * 
	 */
	private java.lang.String _version;

	/**
	 * Field _orig.
	 */
	private bf.com.misys.eqf.types.header.Orig _orig;

	/**
	 * Reserved for future use.
	 * 
	 */
	private java.lang.String _messageType;

	/**
	 * Field _overrides.
	 */
	private bf.com.misys.eqf.types.header.Overrides _overrides;

	/**
	 * This specifies the amount of detail returned with the Equation message: ' '-Only message id, severity, and formated message
	 * text '1'-All information but NOT including service stack '2'-All information
	 * 
	 */
	private java.lang.String _eqMessageDetail;

	/**
	 * The response filter optionally allows filtering of the fields returned. This is used to reduce the payload returned to the
	 * caller eg when only basic details are required or to increase performance for the remote calls between systems. A collection
	 * of XPath for each complex type or field to include in the response. If this parameter is not used all possible response
	 * fields will be returned. If 'none' is specified no service response values will be returned.
	 * 
	 */
	private java.util.Vector<java.lang.String> _responseFilterList;

	/**
	 * Field _formatting.
	 */
	private bf.com.misys.eqf.types.header.Formatting _formatting;

	/**
	 * OBSOLETE
	 * 
	 */
	private java.lang.Object _userExtension;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public RqHeader()
	{
		super();
		this._responseFilterList = new java.util.Vector<java.lang.String>();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * 
	 * 
	 * @param vResponseFilter
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addResponseFilter(final java.lang.String vResponseFilter) throws java.lang.IndexOutOfBoundsException
	{
		this._responseFilterList.addElement(vResponseFilter);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vResponseFilter
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addResponseFilter(final int index, final java.lang.String vResponseFilter)
					throws java.lang.IndexOutOfBoundsException
	{
		this._responseFilterList.add(index, vResponseFilter);
	}

	/**
	 * Method enumerateResponseFilter.
	 * 
	 * @return an Enumeration over all java.lang.String elements
	 */
	public java.util.Enumeration<? extends java.lang.String> enumerateResponseFilter()
	{
		return this._responseFilterList.elements();
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

		if (obj instanceof RqHeader)
		{

			RqHeader temp = (RqHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._sessionType != null)
			{
				if (temp._sessionType == null)
					return false;
				if (this._sessionType != temp._sessionType)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._sessionType);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._sessionType);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionType);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionType);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._sessionType.equals(temp._sessionType))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionType);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionType);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionType);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionType);
					}
				}
			}
			else if (temp._sessionType != null)
				return false;
			if (this._dataSourceName != null)
			{
				if (temp._dataSourceName == null)
					return false;
				if (this._dataSourceName != temp._dataSourceName)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._dataSourceName);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._dataSourceName);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataSourceName);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataSourceName);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._dataSourceName.equals(temp._dataSourceName))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataSourceName);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataSourceName);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._dataSourceName);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._dataSourceName);
					}
				}
			}
			else if (temp._dataSourceName != null)
				return false;
			if (this._systemId != null)
			{
				if (temp._systemId == null)
					return false;
				if (this._systemId != temp._systemId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._systemId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._systemId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._systemId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._systemId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._systemId.equals(temp._systemId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._systemId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._systemId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._systemId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._systemId);
					}
				}
			}
			else if (temp._systemId != null)
				return false;
			if (this._unitId != null)
			{
				if (temp._unitId == null)
					return false;
				if (this._unitId != temp._unitId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._unitId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._unitId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._unitId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._unitId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._unitId.equals(temp._unitId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._unitId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._unitId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._unitId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._unitId);
					}
				}
			}
			else if (temp._unitId != null)
				return false;
			if (this._userIdType != null)
			{
				if (temp._userIdType == null)
					return false;
				if (this._userIdType != temp._userIdType)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._userIdType);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._userIdType);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userIdType);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userIdType);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._userIdType.equals(temp._userIdType))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userIdType);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userIdType);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userIdType);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userIdType);
					}
				}
			}
			else if (temp._userIdType != null)
				return false;
			if (this._userId != null)
			{
				if (temp._userId == null)
					return false;
				if (this._userId != temp._userId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._userId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._userId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._userId.equals(temp._userId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userId);
					}
				}
			}
			else if (temp._userId != null)
				return false;
			if (this._sessionId != null)
			{
				if (temp._sessionId == null)
					return false;
				if (this._sessionId != temp._sessionId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._sessionId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._sessionId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._sessionId.equals(temp._sessionId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._sessionId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._sessionId);
					}
				}
			}
			else if (temp._sessionId != null)
				return false;
			if (this._messageId != null)
			{
				if (temp._messageId == null)
					return false;
				if (this._messageId != temp._messageId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._messageId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._messageId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messageId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messageId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._messageId.equals(temp._messageId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messageId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messageId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messageId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messageId);
					}
				}
			}
			else if (temp._messageId != null)
				return false;
			if (this._version != null)
			{
				if (temp._version == null)
					return false;
				if (this._version != temp._version)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._version);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._version);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._version.equals(temp._version))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._version);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._version);
					}
				}
			}
			else if (temp._version != null)
				return false;
			if (this._orig != null)
			{
				if (temp._orig == null)
					return false;
				if (this._orig != temp._orig)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._orig);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._orig);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._orig);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._orig);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._orig.equals(temp._orig))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._orig);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._orig);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._orig);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._orig);
					}
				}
			}
			else if (temp._orig != null)
				return false;
			if (this._messageType != null)
			{
				if (temp._messageType == null)
					return false;
				if (this._messageType != temp._messageType)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._messageType);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._messageType);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messageType);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messageType);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._messageType.equals(temp._messageType))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messageType);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messageType);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._messageType);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._messageType);
					}
				}
			}
			else if (temp._messageType != null)
				return false;
			if (this._overrides != null)
			{
				if (temp._overrides == null)
					return false;
				if (this._overrides != temp._overrides)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._overrides);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._overrides);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrides);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrides);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._overrides.equals(temp._overrides))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrides);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrides);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrides);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrides);
					}
				}
			}
			else if (temp._overrides != null)
				return false;
			if (this._eqMessageDetail != null)
			{
				if (temp._eqMessageDetail == null)
					return false;
				if (this._eqMessageDetail != temp._eqMessageDetail)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._eqMessageDetail);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._eqMessageDetail);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageDetail);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageDetail);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._eqMessageDetail.equals(temp._eqMessageDetail))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageDetail);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageDetail);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageDetail);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageDetail);
					}
				}
			}
			else if (temp._eqMessageDetail != null)
				return false;
			if (this._responseFilterList != null)
			{
				if (temp._responseFilterList == null)
					return false;
				if (this._responseFilterList != temp._responseFilterList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._responseFilterList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._responseFilterList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._responseFilterList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._responseFilterList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._responseFilterList.equals(temp._responseFilterList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._responseFilterList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._responseFilterList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._responseFilterList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._responseFilterList);
					}
				}
			}
			else if (temp._responseFilterList != null)
				return false;
			if (this._formatting != null)
			{
				if (temp._formatting == null)
					return false;
				if (this._formatting != temp._formatting)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._formatting);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._formatting);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._formatting);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._formatting);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._formatting.equals(temp._formatting))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._formatting);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._formatting);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._formatting);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._formatting);
					}
				}
			}
			else if (temp._formatting != null)
				return false;
			if (this._userExtension != null)
			{
				if (temp._userExtension == null)
					return false;
				if (this._userExtension != temp._userExtension)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._userExtension);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._userExtension);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtension);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtension);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._userExtension.equals(temp._userExtension))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtension);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtension);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._userExtension);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._userExtension);
					}
				}
			}
			else if (temp._userExtension != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'dataSourceName'. The field 'dataSourceName' has the following description: If using pool session,
	 * specify a data source name here if you do not want to use the default session pool.
	 * 
	 * 
	 * @return the value of field 'DataSourceName'.
	 */
	public java.lang.String getDataSourceName()
	{
		return this._dataSourceName;
	}

	/**
	 * Returns the value of field 'eqMessageDetail'. The field 'eqMessageDetail' has the following description: This specifies the
	 * amount of detail returned with the Equation message: ' '-Only message id, severity, and formated message text '1'-All
	 * information but NOT including service stack '2'-All information
	 * 
	 * 
	 * @return the value of field 'EqMessageDetail'.
	 */
	public java.lang.String getEqMessageDetail()
	{
		return this._eqMessageDetail;
	}

	/**
	 * Returns the value of field 'formatting'.
	 * 
	 * @return the value of field 'Formatting'.
	 */
	public bf.com.misys.eqf.types.header.Formatting getFormatting()
	{
		return this._formatting;
	}

	/**
	 * Returns the value of field 'messageId'. The field 'messageId' has the following description: If you pass in a MessageID in a
	 * request, the same value will be returned in the CorrelationID field in the response. Pairing these values can help you track
	 * and confirm that a response is returned for every request and to match specific responses to specific requests. If you do not
	 * pass a MessageID value in the request, CorrelationID is not returned.
	 * 
	 * 
	 * @return the value of field 'MessageId'.
	 */
	public java.lang.String getMessageId()
	{
		return this._messageId;
	}

	/**
	 * Returns the value of field 'messageType'. The field 'messageType' has the following description: Reserved for future use.
	 * 
	 * 
	 * @return the value of field 'MessageType'.
	 */
	public java.lang.String getMessageType()
	{
		return this._messageType;
	}

	/**
	 * Returns the value of field 'orig'.
	 * 
	 * @return the value of field 'Orig'.
	 */
	public bf.com.misys.eqf.types.header.Orig getOrig()
	{
		return this._orig;
	}

	/**
	 * Returns the value of field 'overrides'.
	 * 
	 * @return the value of field 'Overrides'.
	 */
	public bf.com.misys.eqf.types.header.Overrides getOverrides()
	{
		return this._overrides;
	}

	/**
	 * Method getResponseFilter.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the java.lang.String at the given index
	 */
	public java.lang.String getResponseFilter(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._responseFilterList.size())
		{
			throw new IndexOutOfBoundsException("getResponseFilter: Index value '" + index + "' not in range [0.."
							+ (this._responseFilterList.size() - 1) + "]");
		}

		return (java.lang.String) _responseFilterList.get(index);
	}

	/**
	 * Method getResponseFilter.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public java.lang.String[] getResponseFilter()
	{
		java.lang.String[] array = new java.lang.String[0];
		return (java.lang.String[]) this._responseFilterList.toArray(array);
	}

	/**
	 * Method getResponseFilterCount.
	 * 
	 * @return the size of this collection
	 */
	public int getResponseFilterCount()
	{
		return this._responseFilterList.size();
	}

	/**
	 * Returns the value of field 'sessionId'. The field 'sessionId' has the following description: If using a dedicated user
	 * session without BankFusion, specify an identifier here. For every user transaction, specify the same session identifier. If
	 * one is not specified a session identifier will be automatically calculated and returned in the response.
	 * 
	 * 
	 * @return the value of field 'SessionId'.
	 */
	public java.lang.String getSessionId()
	{
		return this._sessionId;
	}

	/**
	 * Returns the value of field 'sessionType'. The field 'sessionType' has the following description: This specifies the session
	 * type: ' '- dedicated user session '1'- pool session
	 * 
	 * 
	 * @return the value of field 'SessionType'.
	 */
	public java.lang.String getSessionType()
	{
		return this._sessionType;
	}

	/**
	 * Returns the value of field 'systemId'. The field 'systemId' has the following description: The system id
	 * 
	 * 
	 * @return the value of field 'SystemId'.
	 */
	public java.lang.String getSystemId()
	{
		return this._systemId;
	}

	/**
	 * Returns the value of field 'unitId'. The field 'unitId' has the following description: The unit id
	 * 
	 * 
	 * @return the value of field 'UnitId'.
	 */
	public java.lang.String getUnitId()
	{
		return this._unitId;
	}

	/**
	 * Returns the value of field 'userExtension'. The field 'userExtension' has the following description: OBSOLETE
	 * 
	 * 
	 * @return the value of field 'UserExtension'.
	 */
	public java.lang.Object getUserExtension()
	{
		return this._userExtension;
	}

	/**
	 * Returns the value of field 'userId'. The field 'userId' has the following description: Specify an Equation user here if
	 * different from the BankFusion user (dedicated user session) or data source user (pool session).
	 * 
	 * 
	 * @return the value of field 'UserId'.
	 */
	public java.lang.String getUserId()
	{
		return this._userId;
	}

	/**
	 * Returns the value of field 'userIdType'. The field 'userIdType' has the following description: This specifies the format of
	 * the user profile: ' '- iSeries 10 character user profile identifier '1'- CAS 30 character user identifier profile
	 * 
	 * 
	 * @return the value of field 'UserIdType'.
	 */
	public java.lang.String getUserIdType()
	{
		return this._userIdType;
	}

	/**
	 * Returns the value of field 'version'. The field 'version' has the following description: This is the version of the API that
	 * the client is using eg 1.0. The response will contain the current API version. An error will be generated if the version is
	 * not supported.
	 * 
	 * 
	 * @return the value of field 'Version'.
	 */
	public java.lang.String getVersion()
	{
		return this._version;
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
		if (_sessionType != null && !org.castor.core.util.CycleBreaker.startingToCycle(_sessionType))
		{
			result = 37 * result + _sessionType.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_sessionType);
		}
		if (_dataSourceName != null && !org.castor.core.util.CycleBreaker.startingToCycle(_dataSourceName))
		{
			result = 37 * result + _dataSourceName.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_dataSourceName);
		}
		if (_systemId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_systemId))
		{
			result = 37 * result + _systemId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_systemId);
		}
		if (_unitId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_unitId))
		{
			result = 37 * result + _unitId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_unitId);
		}
		if (_userIdType != null && !org.castor.core.util.CycleBreaker.startingToCycle(_userIdType))
		{
			result = 37 * result + _userIdType.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_userIdType);
		}
		if (_userId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_userId))
		{
			result = 37 * result + _userId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_userId);
		}
		if (_sessionId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_sessionId))
		{
			result = 37 * result + _sessionId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_sessionId);
		}
		if (_messageId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_messageId))
		{
			result = 37 * result + _messageId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_messageId);
		}
		if (_version != null && !org.castor.core.util.CycleBreaker.startingToCycle(_version))
		{
			result = 37 * result + _version.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_version);
		}
		if (_orig != null && !org.castor.core.util.CycleBreaker.startingToCycle(_orig))
		{
			result = 37 * result + _orig.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_orig);
		}
		if (_messageType != null && !org.castor.core.util.CycleBreaker.startingToCycle(_messageType))
		{
			result = 37 * result + _messageType.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_messageType);
		}
		if (_overrides != null && !org.castor.core.util.CycleBreaker.startingToCycle(_overrides))
		{
			result = 37 * result + _overrides.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_overrides);
		}
		if (_eqMessageDetail != null && !org.castor.core.util.CycleBreaker.startingToCycle(_eqMessageDetail))
		{
			result = 37 * result + _eqMessageDetail.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_eqMessageDetail);
		}
		if (_responseFilterList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_responseFilterList))
		{
			result = 37 * result + _responseFilterList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_responseFilterList);
		}
		if (_formatting != null && !org.castor.core.util.CycleBreaker.startingToCycle(_formatting))
		{
			result = 37 * result + _formatting.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_formatting);
		}
		if (_userExtension != null && !org.castor.core.util.CycleBreaker.startingToCycle(_userExtension))
		{
			result = 37 * result + _userExtension.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_userExtension);
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
	public void removeAllResponseFilter()
	{
		this._responseFilterList.clear();
	}

	/**
	 * Method removeResponseFilter.
	 * 
	 * @param vResponseFilter
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeResponseFilter(final java.lang.String vResponseFilter)
	{
		boolean removed = _responseFilterList.remove(vResponseFilter);
		return removed;
	}

	/**
	 * Method removeResponseFilterAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public java.lang.String removeResponseFilterAt(final int index)
	{
		java.lang.Object obj = this._responseFilterList.remove(index);
		return (java.lang.String) obj;
	}

	/**
	 * Sets the value of field 'dataSourceName'. The field 'dataSourceName' has the following description: If using pool session,
	 * specify a data source name here if you do not want to use the default session pool.
	 * 
	 * 
	 * @param dataSourceName
	 *            the value of field 'dataSourceName'.
	 */
	public void setDataSourceName(final java.lang.String dataSourceName)
	{
		this._dataSourceName = dataSourceName;
	}

	/**
	 * Sets the value of field 'eqMessageDetail'. The field 'eqMessageDetail' has the following description: This specifies the
	 * amount of detail returned with the Equation message: ' '-Only message id, severity, and formated message text '1'-All
	 * information but NOT including service stack '2'-All information
	 * 
	 * 
	 * @param eqMessageDetail
	 *            the value of field 'eqMessageDetail'.
	 */
	public void setEqMessageDetail(final java.lang.String eqMessageDetail)
	{
		this._eqMessageDetail = eqMessageDetail;
	}

	/**
	 * Sets the value of field 'formatting'.
	 * 
	 * @param formatting
	 *            the value of field 'formatting'.
	 */
	public void setFormatting(final bf.com.misys.eqf.types.header.Formatting formatting)
	{
		this._formatting = formatting;
	}

	/**
	 * Sets the value of field 'messageId'. The field 'messageId' has the following description: If you pass in a MessageID in a
	 * request, the same value will be returned in the CorrelationID field in the response. Pairing these values can help you track
	 * and confirm that a response is returned for every request and to match specific responses to specific requests. If you do not
	 * pass a MessageID value in the request, CorrelationID is not returned.
	 * 
	 * 
	 * @param messageId
	 *            the value of field 'messageId'.
	 */
	public void setMessageId(final java.lang.String messageId)
	{
		this._messageId = messageId;
	}

	/**
	 * Sets the value of field 'messageType'. The field 'messageType' has the following description: Reserved for future use.
	 * 
	 * 
	 * @param messageType
	 *            the value of field 'messageType'.
	 */
	public void setMessageType(final java.lang.String messageType)
	{
		this._messageType = messageType;
	}

	/**
	 * Sets the value of field 'orig'.
	 * 
	 * @param orig
	 *            the value of field 'orig'.
	 */
	public void setOrig(final bf.com.misys.eqf.types.header.Orig orig)
	{
		this._orig = orig;
	}

	/**
	 * Sets the value of field 'overrides'.
	 * 
	 * @param overrides
	 *            the value of field 'overrides'.
	 */
	public void setOverrides(final bf.com.misys.eqf.types.header.Overrides overrides)
	{
		this._overrides = overrides;
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vResponseFilter
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setResponseFilter(final int index, final java.lang.String vResponseFilter)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._responseFilterList.size())
		{
			throw new IndexOutOfBoundsException("setResponseFilter: Index value '" + index + "' not in range [0.."
							+ (this._responseFilterList.size() - 1) + "]");
		}

		this._responseFilterList.set(index, vResponseFilter);
	}

	/**
	 * 
	 * 
	 * @param vResponseFilterArray
	 */
	public void setResponseFilter(final java.lang.String[] vResponseFilterArray)
	{
		// -- copy array
		_responseFilterList.clear();

		for (int i = 0; i < vResponseFilterArray.length; i++)
		{
			this._responseFilterList.add(vResponseFilterArray[i]);
		}
	}

	/**
	 * Sets the value of field 'sessionId'. The field 'sessionId' has the following description: If using a dedicated user session
	 * without BankFusion, specify an identifier here. For every user transaction, specify the same session identifier. If one is
	 * not specified a session identifier will be automatically calculated and returned in the response.
	 * 
	 * 
	 * @param sessionId
	 *            the value of field 'sessionId'.
	 */
	public void setSessionId(final java.lang.String sessionId)
	{
		this._sessionId = sessionId;
	}

	/**
	 * Sets the value of field 'sessionType'. The field 'sessionType' has the following description: This specifies the session
	 * type: ' '- dedicated user session '1'- pool session
	 * 
	 * 
	 * @param sessionType
	 *            the value of field 'sessionType'.
	 */
	public void setSessionType(final java.lang.String sessionType)
	{
		this._sessionType = sessionType;
	}

	/**
	 * Sets the value of field 'systemId'. The field 'systemId' has the following description: The system id
	 * 
	 * 
	 * @param systemId
	 *            the value of field 'systemId'.
	 */
	public void setSystemId(final java.lang.String systemId)
	{
		this._systemId = systemId;
	}

	/**
	 * Sets the value of field 'unitId'. The field 'unitId' has the following description: The unit id
	 * 
	 * 
	 * @param unitId
	 *            the value of field 'unitId'.
	 */
	public void setUnitId(final java.lang.String unitId)
	{
		this._unitId = unitId;
	}

	/**
	 * Sets the value of field 'userExtension'. The field 'userExtension' has the following description: OBSOLETE
	 * 
	 * 
	 * @param userExtension
	 *            the value of field 'userExtension'.
	 */
	public void setUserExtension(final java.lang.Object userExtension)
	{
		this._userExtension = userExtension;
	}

	/**
	 * Sets the value of field 'userId'. The field 'userId' has the following description: Specify an Equation user here if
	 * different from the BankFusion user (dedicated user session) or data source user (pool session).
	 * 
	 * 
	 * @param userId
	 *            the value of field 'userId'.
	 */
	public void setUserId(final java.lang.String userId)
	{
		this._userId = userId;
	}

	/**
	 * Sets the value of field 'userIdType'. The field 'userIdType' has the following description: This specifies the format of the
	 * user profile: ' '- iSeries 10 character user profile identifier '1'- CAS 30 character user identifier profile
	 * 
	 * 
	 * @param userIdType
	 *            the value of field 'userIdType'.
	 */
	public void setUserIdType(final java.lang.String userIdType)
	{
		this._userIdType = userIdType;
	}

	/**
	 * Sets the value of field 'version'. The field 'version' has the following description: This is the version of the API that the
	 * client is using eg 1.0. The response will contain the current API version. An error will be generated if the version is not
	 * supported.
	 * 
	 * 
	 * @param version
	 *            the value of field 'version'.
	 */
	public void setVersion(final java.lang.String version)
	{
		this._version = version;
	}

	/**
	 * Method unmarshalRqHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.RqHeade
	 */
	public static bf.com.misys.eqf.types.header.RqHeader unmarshalRqHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.RqHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.RqHeader.class, reader);
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
