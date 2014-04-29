/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class EqMessage.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class EqMessage implements java.io.Serializable
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
	 * Equation message Id eg KSM2010
	 * 
	 */
	private java.lang.String _eqMessageId;

	/**
	 * Message severity. "00" -Information, "10" -Warning, "20" -Error
	 * 
	 */
	private java.lang.String _eqMessageSeverity;

	/**
	 * Error text in datasource pool user system language: KSM2010 Option does not exist on the database. Error and warning messages
	 * are returned in the language of the datasource user. To get multi-language messages the message string text must be
	 * translated and then the replaceable parameters inserted at the correct point. Up to three fields of substitution parameter
	 * text are returned for inclusion in the translated message. Each can be 10 characters long and relate to variables ampersand
	 * 1, ampersand 2 and ampersand 3 in the KSM message text. The message file is available as an extract in the Equation database.
	 * 
	 */
	private java.lang.String _formattedMessage;

	/**
	 * Replaceable parameter 1
	 * 
	 */
	private java.lang.String _eqMessageParameter1;

	/**
	 * Replaceable parameter 2
	 * 
	 */
	private java.lang.String _eqMessageParameter2;

	/**
	 * Replaceable parameter 3
	 * 
	 */
	private java.lang.String _eqMessageParameter3;

	/**
	 * Field _firstLevelText.
	 */
	private java.lang.String _firstLevelText;

	/**
	 * Field _secondLevelText.
	 */
	private java.lang.String _secondLevelText;

	/**
	 * Field _fieldLocation.
	 */
	private bf.com.misys.eqf.types.header.FieldLocation _fieldLocation;

	/**
	 * Field _branchLimit.
	 */
	private bf.com.misys.eqf.types.header.BranchLimitParameters _branchLimit;

	/**
	 * Field _overridden.
	 */
	private java.lang.Boolean _overridden;

	/**
	 * Field _serviceStack.
	 */
	private bf.com.misys.eqf.types.header.ServiceStack _serviceStack;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public EqMessage()
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

		if (obj instanceof EqMessage)
		{

			EqMessage temp = (EqMessage) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._eqMessageId != null)
			{
				if (temp._eqMessageId == null)
					return false;
				if (this._eqMessageId != temp._eqMessageId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._eqMessageId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._eqMessageId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._eqMessageId.equals(temp._eqMessageId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageId);
					}
				}
			}
			else if (temp._eqMessageId != null)
				return false;
			if (this._eqMessageSeverity != null)
			{
				if (temp._eqMessageSeverity == null)
					return false;
				if (this._eqMessageSeverity != temp._eqMessageSeverity)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._eqMessageSeverity);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._eqMessageSeverity);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageSeverity);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageSeverity);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._eqMessageSeverity.equals(temp._eqMessageSeverity))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageSeverity);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageSeverity);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageSeverity);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageSeverity);
					}
				}
			}
			else if (temp._eqMessageSeverity != null)
				return false;
			if (this._formattedMessage != null)
			{
				if (temp._formattedMessage == null)
					return false;
				if (this._formattedMessage != temp._formattedMessage)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._formattedMessage);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._formattedMessage);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._formattedMessage);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._formattedMessage);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._formattedMessage.equals(temp._formattedMessage))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._formattedMessage);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._formattedMessage);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._formattedMessage);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._formattedMessage);
					}
				}
			}
			else if (temp._formattedMessage != null)
				return false;
			if (this._eqMessageParameter1 != null)
			{
				if (temp._eqMessageParameter1 == null)
					return false;
				if (this._eqMessageParameter1 != temp._eqMessageParameter1)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._eqMessageParameter1);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._eqMessageParameter1);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter1);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter1);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._eqMessageParameter1.equals(temp._eqMessageParameter1))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter1);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter1);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter1);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter1);
					}
				}
			}
			else if (temp._eqMessageParameter1 != null)
				return false;
			if (this._eqMessageParameter2 != null)
			{
				if (temp._eqMessageParameter2 == null)
					return false;
				if (this._eqMessageParameter2 != temp._eqMessageParameter2)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._eqMessageParameter2);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._eqMessageParameter2);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter2);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter2);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._eqMessageParameter2.equals(temp._eqMessageParameter2))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter2);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter2);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter2);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter2);
					}
				}
			}
			else if (temp._eqMessageParameter2 != null)
				return false;
			if (this._eqMessageParameter3 != null)
			{
				if (temp._eqMessageParameter3 == null)
					return false;
				if (this._eqMessageParameter3 != temp._eqMessageParameter3)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._eqMessageParameter3);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._eqMessageParameter3);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter3);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter3);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._eqMessageParameter3.equals(temp._eqMessageParameter3))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter3);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter3);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._eqMessageParameter3);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._eqMessageParameter3);
					}
				}
			}
			else if (temp._eqMessageParameter3 != null)
				return false;
			if (this._firstLevelText != null)
			{
				if (temp._firstLevelText == null)
					return false;
				if (this._firstLevelText != temp._firstLevelText)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._firstLevelText);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._firstLevelText);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._firstLevelText);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._firstLevelText);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._firstLevelText.equals(temp._firstLevelText))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._firstLevelText);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._firstLevelText);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._firstLevelText);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._firstLevelText);
					}
				}
			}
			else if (temp._firstLevelText != null)
				return false;
			if (this._secondLevelText != null)
			{
				if (temp._secondLevelText == null)
					return false;
				if (this._secondLevelText != temp._secondLevelText)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._secondLevelText);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._secondLevelText);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._secondLevelText);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._secondLevelText);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._secondLevelText.equals(temp._secondLevelText))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._secondLevelText);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._secondLevelText);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._secondLevelText);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._secondLevelText);
					}
				}
			}
			else if (temp._secondLevelText != null)
				return false;
			if (this._fieldLocation != null)
			{
				if (temp._fieldLocation == null)
					return false;
				if (this._fieldLocation != temp._fieldLocation)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._fieldLocation);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._fieldLocation);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._fieldLocation);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._fieldLocation);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._fieldLocation.equals(temp._fieldLocation))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._fieldLocation);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._fieldLocation);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._fieldLocation);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._fieldLocation);
					}
				}
			}
			else if (temp._fieldLocation != null)
				return false;
			if (this._branchLimit != null)
			{
				if (temp._branchLimit == null)
					return false;
				if (this._branchLimit != temp._branchLimit)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._branchLimit);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._branchLimit);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchLimit);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchLimit);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._branchLimit.equals(temp._branchLimit))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchLimit);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchLimit);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._branchLimit);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._branchLimit);
					}
				}
			}
			else if (temp._branchLimit != null)
				return false;
			if (this._overridden != null)
			{
				if (temp._overridden == null)
					return false;
				if (this._overridden != temp._overridden)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._overridden);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._overridden);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overridden);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overridden);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._overridden.equals(temp._overridden))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overridden);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overridden);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overridden);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overridden);
					}
				}
			}
			else if (temp._overridden != null)
				return false;
			if (this._serviceStack != null)
			{
				if (temp._serviceStack == null)
					return false;
				if (this._serviceStack != temp._serviceStack)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._serviceStack);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._serviceStack);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceStack);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceStack);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._serviceStack.equals(temp._serviceStack))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceStack);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceStack);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._serviceStack);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._serviceStack);
					}
				}
			}
			else if (temp._serviceStack != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'branchLimit'.
	 * 
	 * @return the value of field 'BranchLimit'.
	 */
	public bf.com.misys.eqf.types.header.BranchLimitParameters getBranchLimit()
	{
		return this._branchLimit;
	}

	/**
	 * Returns the value of field 'eqMessageId'. The field 'eqMessageId' has the following description: Equation message Id eg
	 * KSM2010
	 * 
	 * 
	 * @return the value of field 'EqMessageId'.
	 */
	public java.lang.String getEqMessageId()
	{
		return this._eqMessageId;
	}

	/**
	 * Returns the value of field 'eqMessageParameter1'. The field 'eqMessageParameter1' has the following description: Replaceable
	 * parameter 1
	 * 
	 * 
	 * @return the value of field 'EqMessageParameter1'.
	 */
	public java.lang.String getEqMessageParameter1()
	{
		return this._eqMessageParameter1;
	}

	/**
	 * Returns the value of field 'eqMessageParameter2'. The field 'eqMessageParameter2' has the following description: Replaceable
	 * parameter 2
	 * 
	 * 
	 * @return the value of field 'EqMessageParameter2'.
	 */
	public java.lang.String getEqMessageParameter2()
	{
		return this._eqMessageParameter2;
	}

	/**
	 * Returns the value of field 'eqMessageParameter3'. The field 'eqMessageParameter3' has the following description: Replaceable
	 * parameter 3
	 * 
	 * 
	 * @return the value of field 'EqMessageParameter3'.
	 */
	public java.lang.String getEqMessageParameter3()
	{
		return this._eqMessageParameter3;
	}

	/**
	 * Returns the value of field 'eqMessageSeverity'. The field 'eqMessageSeverity' has the following description: Message
	 * severity. "00" -Information, "10" -Warning, "20" -Error
	 * 
	 * 
	 * @return the value of field 'EqMessageSeverity'.
	 */
	public java.lang.String getEqMessageSeverity()
	{
		return this._eqMessageSeverity;
	}

	/**
	 * Returns the value of field 'fieldLocation'.
	 * 
	 * @return the value of field 'FieldLocation'.
	 */
	public bf.com.misys.eqf.types.header.FieldLocation getFieldLocation()
	{
		return this._fieldLocation;
	}

	/**
	 * Returns the value of field 'firstLevelText'.
	 * 
	 * @return the value of field 'FirstLevelText'.
	 */
	public java.lang.String getFirstLevelText()
	{
		return this._firstLevelText;
	}

	/**
	 * Returns the value of field 'formattedMessage'. The field 'formattedMessage' has the following description: Error text in
	 * datasource pool user system language: KSM2010 Option does not exist on the database. Error and warning messages are returned
	 * in the language of the datasource user. To get multi-language messages the message string text must be translated and then
	 * the replaceable parameters inserted at the correct point. Up to three fields of substitution parameter text are returned for
	 * inclusion in the translated message. Each can be 10 characters long and relate to variables ampersand 1, ampersand 2 and
	 * ampersand 3 in the KSM message text. The message file is available as an extract in the Equation database.
	 * 
	 * 
	 * @return the value of field 'FormattedMessage'.
	 */
	public java.lang.String getFormattedMessage()
	{
		return this._formattedMessage;
	}

	/**
	 * Returns the value of field 'overridden'.
	 * 
	 * @return the value of field 'Overridden'.
	 */
	public java.lang.Boolean getOverridden()
	{
		return this._overridden;
	}

	/**
	 * Returns the value of field 'secondLevelText'.
	 * 
	 * @return the value of field 'SecondLevelText'.
	 */
	public java.lang.String getSecondLevelText()
	{
		return this._secondLevelText;
	}

	/**
	 * Returns the value of field 'serviceStack'.
	 * 
	 * @return the value of field 'ServiceStack'.
	 */
	public bf.com.misys.eqf.types.header.ServiceStack getServiceStack()
	{
		return this._serviceStack;
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
		if (_eqMessageId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_eqMessageId))
		{
			result = 37 * result + _eqMessageId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_eqMessageId);
		}
		if (_eqMessageSeverity != null && !org.castor.core.util.CycleBreaker.startingToCycle(_eqMessageSeverity))
		{
			result = 37 * result + _eqMessageSeverity.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_eqMessageSeverity);
		}
		if (_formattedMessage != null && !org.castor.core.util.CycleBreaker.startingToCycle(_formattedMessage))
		{
			result = 37 * result + _formattedMessage.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_formattedMessage);
		}
		if (_eqMessageParameter1 != null && !org.castor.core.util.CycleBreaker.startingToCycle(_eqMessageParameter1))
		{
			result = 37 * result + _eqMessageParameter1.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_eqMessageParameter1);
		}
		if (_eqMessageParameter2 != null && !org.castor.core.util.CycleBreaker.startingToCycle(_eqMessageParameter2))
		{
			result = 37 * result + _eqMessageParameter2.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_eqMessageParameter2);
		}
		if (_eqMessageParameter3 != null && !org.castor.core.util.CycleBreaker.startingToCycle(_eqMessageParameter3))
		{
			result = 37 * result + _eqMessageParameter3.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_eqMessageParameter3);
		}
		if (_firstLevelText != null && !org.castor.core.util.CycleBreaker.startingToCycle(_firstLevelText))
		{
			result = 37 * result + _firstLevelText.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_firstLevelText);
		}
		if (_secondLevelText != null && !org.castor.core.util.CycleBreaker.startingToCycle(_secondLevelText))
		{
			result = 37 * result + _secondLevelText.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_secondLevelText);
		}
		if (_fieldLocation != null && !org.castor.core.util.CycleBreaker.startingToCycle(_fieldLocation))
		{
			result = 37 * result + _fieldLocation.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_fieldLocation);
		}
		if (_branchLimit != null && !org.castor.core.util.CycleBreaker.startingToCycle(_branchLimit))
		{
			result = 37 * result + _branchLimit.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_branchLimit);
		}
		if (_overridden != null && !org.castor.core.util.CycleBreaker.startingToCycle(_overridden))
		{
			result = 37 * result + _overridden.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_overridden);
		}
		if (_serviceStack != null && !org.castor.core.util.CycleBreaker.startingToCycle(_serviceStack))
		{
			result = 37 * result + _serviceStack.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_serviceStack);
		}

		return result;
	}

	/**
	 * Returns the value of field 'overridden'.
	 * 
	 * @return the value of field 'Overridden'.
	 */
	public java.lang.Boolean isOverridden()
	{
		return this._overridden;
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
	 * Sets the value of field 'branchLimit'.
	 * 
	 * @param branchLimit
	 *            the value of field 'branchLimit'.
	 */
	public void setBranchLimit(final bf.com.misys.eqf.types.header.BranchLimitParameters branchLimit)
	{
		this._branchLimit = branchLimit;
	}

	/**
	 * Sets the value of field 'eqMessageId'. The field 'eqMessageId' has the following description: Equation message Id eg KSM2010
	 * 
	 * 
	 * @param eqMessageId
	 *            the value of field 'eqMessageId'.
	 */
	public void setEqMessageId(final java.lang.String eqMessageId)
	{
		this._eqMessageId = eqMessageId;
	}

	/**
	 * Sets the value of field 'eqMessageParameter1'. The field 'eqMessageParameter1' has the following description: Replaceable
	 * parameter 1
	 * 
	 * 
	 * @param eqMessageParameter1
	 *            the value of field 'eqMessageParameter1'.
	 */
	public void setEqMessageParameter1(final java.lang.String eqMessageParameter1)
	{
		this._eqMessageParameter1 = eqMessageParameter1;
	}

	/**
	 * Sets the value of field 'eqMessageParameter2'. The field 'eqMessageParameter2' has the following description: Replaceable
	 * parameter 2
	 * 
	 * 
	 * @param eqMessageParameter2
	 *            the value of field 'eqMessageParameter2'.
	 */
	public void setEqMessageParameter2(final java.lang.String eqMessageParameter2)
	{
		this._eqMessageParameter2 = eqMessageParameter2;
	}

	/**
	 * Sets the value of field 'eqMessageParameter3'. The field 'eqMessageParameter3' has the following description: Replaceable
	 * parameter 3
	 * 
	 * 
	 * @param eqMessageParameter3
	 *            the value of field 'eqMessageParameter3'.
	 */
	public void setEqMessageParameter3(final java.lang.String eqMessageParameter3)
	{
		this._eqMessageParameter3 = eqMessageParameter3;
	}

	/**
	 * Sets the value of field 'eqMessageSeverity'. The field 'eqMessageSeverity' has the following description: Message severity.
	 * "00" -Information, "10" -Warning, "20" -Error
	 * 
	 * 
	 * @param eqMessageSeverity
	 *            the value of field 'eqMessageSeverity'.
	 */
	public void setEqMessageSeverity(final java.lang.String eqMessageSeverity)
	{
		this._eqMessageSeverity = eqMessageSeverity;
	}

	/**
	 * Sets the value of field 'fieldLocation'.
	 * 
	 * @param fieldLocation
	 *            the value of field 'fieldLocation'.
	 */
	public void setFieldLocation(final bf.com.misys.eqf.types.header.FieldLocation fieldLocation)
	{
		this._fieldLocation = fieldLocation;
	}

	/**
	 * Sets the value of field 'firstLevelText'.
	 * 
	 * @param firstLevelText
	 *            the value of field 'firstLevelText'.
	 */
	public void setFirstLevelText(final java.lang.String firstLevelText)
	{
		this._firstLevelText = firstLevelText;
	}

	/**
	 * Sets the value of field 'formattedMessage'. The field 'formattedMessage' has the following description: Error text in
	 * datasource pool user system language: KSM2010 Option does not exist on the database. Error and warning messages are returned
	 * in the language of the datasource user. To get multi-language messages the message string text must be translated and then
	 * the replaceable parameters inserted at the correct point. Up to three fields of substitution parameter text are returned for
	 * inclusion in the translated message. Each can be 10 characters long and relate to variables ampersand 1, ampersand 2 and
	 * ampersand 3 in the KSM message text. The message file is available as an extract in the Equation database.
	 * 
	 * 
	 * @param formattedMessage
	 *            the value of field 'formattedMessage'
	 */
	public void setFormattedMessage(final java.lang.String formattedMessage)
	{
		this._formattedMessage = formattedMessage;
	}

	/**
	 * Sets the value of field 'overridden'.
	 * 
	 * @param overridden
	 *            the value of field 'overridden'.
	 */
	public void setOverridden(final java.lang.Boolean overridden)
	{
		this._overridden = overridden;
	}

	/**
	 * Sets the value of field 'secondLevelText'.
	 * 
	 * @param secondLevelText
	 *            the value of field 'secondLevelText'.
	 */
	public void setSecondLevelText(final java.lang.String secondLevelText)
	{
		this._secondLevelText = secondLevelText;
	}

	/**
	 * Sets the value of field 'serviceStack'.
	 * 
	 * @param serviceStack
	 *            the value of field 'serviceStack'.
	 */
	public void setServiceStack(final bf.com.misys.eqf.types.header.ServiceStack serviceStack)
	{
		this._serviceStack = serviceStack;
	}

	/**
	 * Method unmarshalEqMessage.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.EqMessage
	 */
	public static bf.com.misys.eqf.types.header.EqMessage unmarshalEqMessage(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.EqMessage) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.EqMessage.class, reader);
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
