/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * This is the ESF key fields
 * 
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class EsfKey implements java.io.Serializable
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
	 * Option id
	 * 
	 */
	private java.lang.String _optionId;

	/**
	 * User id
	 * 
	 */
	private java.lang.String _userId;

	/**
	 * Session id
	 * 
	 */
	private java.lang.String _sessionId;

	/**
	 * Transaction id
	 * 
	 */
	private java.lang.String _transactionId;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public EsfKey()
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

		if (obj instanceof EsfKey)
		{

			EsfKey temp = (EsfKey) obj;
			boolean thcycle;
			boolean tmcycle;
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
			if (this._transactionId != null)
			{
				if (temp._transactionId == null)
					return false;
				if (this._transactionId != temp._transactionId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._transactionId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._transactionId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transactionId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transactionId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._transactionId.equals(temp._transactionId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transactionId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transactionId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._transactionId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._transactionId);
					}
				}
			}
			else if (temp._transactionId != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'optionId'. The field 'optionId' has the following description: Option id
	 * 
	 * 
	 * @return the value of field 'OptionId'.
	 */
	public java.lang.String getOptionId()
	{
		return this._optionId;
	}

	/**
	 * Returns the value of field 'sessionId'. The field 'sessionId' has the following description: Session id
	 * 
	 * 
	 * @return the value of field 'SessionId'.
	 */
	public java.lang.String getSessionId()
	{
		return this._sessionId;
	}

	/**
	 * Returns the value of field 'transactionId'. The field 'transactionId' has the following description: Transaction id
	 * 
	 * 
	 * @return the value of field 'TransactionId'.
	 */
	public java.lang.String getTransactionId()
	{
		return this._transactionId;
	}

	/**
	 * Returns the value of field 'userId'. The field 'userId' has the following description: User id
	 * 
	 * 
	 * @return the value of field 'UserId'.
	 */
	public java.lang.String getUserId()
	{
		return this._userId;
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
		if (_optionId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_optionId))
		{
			result = 37 * result + _optionId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_optionId);
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
		if (_transactionId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_transactionId))
		{
			result = 37 * result + _transactionId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_transactionId);
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
	 * Sets the value of field 'optionId'. The field 'optionId' has the following description: Option id
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
	 * Sets the value of field 'sessionId'. The field 'sessionId' has the following description: Session id
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
	 * Sets the value of field 'transactionId'. The field 'transactionId' has the following description: Transaction id
	 * 
	 * 
	 * @param transactionId
	 *            the value of field 'transactionId'.
	 */
	public void setTransactionId(final java.lang.String transactionId)
	{
		this._transactionId = transactionId;
	}

	/**
	 * Sets the value of field 'userId'. The field 'userId' has the following description: User id
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
	 * Method unmarshalEsfKey.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.EsfKey
	 */
	public static bf.com.misys.eqf.types.header.EsfKey unmarshalEsfKey(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.EsfKey) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.EsfKey.class, reader);
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
