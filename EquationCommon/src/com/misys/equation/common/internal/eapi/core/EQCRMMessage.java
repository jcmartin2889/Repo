// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQCRMMessage: Class to store CRM messages
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

/***********************************************************************************************************************************
 * Represents an Equation CRM Message.
 * <P>
 * Equation provides realtime diagnostic messages during its processing. These can have severities of Errors, Warnings and
 * Information Only. Messages can be accessed using the object's getMessages method. This method returns an ArrayList of EQMessage
 * objects. If CRM Limit Violations have occurred then EQCRMMessage objects will be in the ArrayList. EQCRMMessage objects always
 * have severities of warnings.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
public class EQCRMMessage extends EQMessage implements java.io.Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQCRMMessage.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// This attribute is used to store cvs version information.
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	// name of the field and record format which this message refers to
	protected String countryCode;
	protected String customerGroup;
	protected String customerName;
	protected String customerLocation;
	protected String limitCategory;
	protected String currency;
	protected String earliestExpiryDate;
	protected String riskAmount;
	protected String limitAmount;
	protected String availableAmount;
	/*******************************************************************************************************************************
	 * Default Constructor.
	 */
	public EQCRMMessage()
	{
		countryCode = "";
		customerGroup = "";
		customerName = "";
		customerLocation = "";
		limitCategory = "";
		currency = "";
		earliestExpiryDate = "";
		riskAmount = "";
		limitAmount = "";
		availableAmount = "";
		severity = EQMessage.SEVERITY_WARNING;
	}
	/*******************************************************************************************************************************
	 * Construct a message from a fixed format message buffer.
	 * <P>
	 * This is used after a call to Equation to constuct messages. The buffer may contain more than one message. This constructor
	 * builds one EQCRMMessage from part of the buffer, starting at the specified index. The format of the buffer starting at that
	 * index must conform to the following:
	 * 
	 * <pre>
	 * &lt;TABLE&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * &lt;B&gt;Description
	 * &lt;/TD&gt;&lt;TD&gt;
	 * &lt;B&gt;Size
	 * &lt;/TD&gt;&lt;TD&gt;
	 * &lt;B&gt;Start Position
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * dataType
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 1 char
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 0&lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * countryCode
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 2 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 1&lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * customerGroup
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 6 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 3&lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * customerName
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 6 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 9&lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * customerLocation
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 3 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 15
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * limitCategory
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 5 char
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 18
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * currency
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 3 char
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 23
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * earliestExpiryDate
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 11 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 26
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * riskAmount
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 23 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 37
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * limitAmount
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 21 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 60
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * availableAmount
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 23 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 81
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * error
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 132 chars
	 * &lt;/TD&gt;&lt;TD&gt;
	 * 104
	 * &lt;/TD&gt;&lt;/TR&gt;
	 * &lt;TR&gt;&lt;TD&gt;
	 * TOTAL = 236 chars
	 * &lt;/TABLE&gt;
	 * </pre>
	 * 
	 * @param buff
	 *            a buffer of messages
	 * @param offset
	 *            the index within the buffer for the start of the message
	 */
	public EQCRMMessage(char[] buff, int offset)
	{
		countryCode = new String(buff, offset + 1, 2).trim();
		customerGroup = new String(buff, offset + 3, 6).trim();
		customerName = new String(buff, offset + 9, 6).trim();
		customerLocation = new String(buff, offset + 15, 3).trim();
		limitCategory = new String(buff, offset + 18, 5).trim();
		currency = new String(buff, offset + 23, 3).trim();
		earliestExpiryDate = new String(buff, offset + 26, 11).trim();
		riskAmount = new String(buff, offset + 37, 23).trim();
		limitAmount = new String(buff, offset + 60, 21).trim();
		availableAmount = new String(buff, offset + 81, 23).trim();
		// removing control characters from text before creating string
		char character = ' ';
		int txtStartIndex = offset + 104;
		int txtEndIndex = offset + 236;
		if (txtEndIndex > buff.length)
		{
			txtEndIndex = buff.length;
		}
		for (int i = txtStartIndex; i < txtEndIndex; i++)
		{
			character = buff[i];
			if (character >= 0x0080 && character <= 0x0090)
			{
				buff[i] = ' ';
			}
		}
		formattedMessage = new String(buff, txtStartIndex, txtEndIndex - txtStartIndex).trim();
		messageID = formattedMessage.substring(0, 7);
		severity = EQMessage.SEVERITY_WARNING;
		unFormattedText = null;
	}
	/*******************************************************************************************************************************
	 * Get the country code.
	 * <P>
	 * 
	 * @return the country code of this CRM message.
	 */
	public String getCountryCode()
	{
		return countryCode;
	}
	/*******************************************************************************************************************************
	 * Get the customer group.
	 * <P>
	 * 
	 * @return the customer group of this CRM message.
	 */
	public String getCustomerGroup()
	{
		return customerGroup;
	}
	/*******************************************************************************************************************************
	 * Get the customer name.
	 * <P>
	 * 
	 * @return the customer name of this CRM message.
	 */
	public String getCustomerName()
	{
		return customerName;
	}
	/*******************************************************************************************************************************
	 * Get the customer location.
	 * <P>
	 * 
	 * @return the customer location of this CRM message.
	 */
	public String getCustomerLocation()
	{
		return customerLocation;
	}
	/*******************************************************************************************************************************
	 * Get the limit category.
	 * <P>
	 * 
	 * @return the limit category of this CRM message.
	 */
	public String getLimitCategory()
	{
		return limitCategory;
	}
	/*******************************************************************************************************************************
	 * Get the currency.
	 * <P>
	 * 
	 * @return the currency of this CRM message.
	 */
	public String getCurrency()
	{
		return currency;
	}
	/*******************************************************************************************************************************
	 * Get the earliest expiry date.
	 * <P>
	 * 
	 * @return the earliest expiry date of this CRM message as a string.
	 */
	public String getEarliestExpiryDate()
	{
		return earliestExpiryDate;
	}
	/*******************************************************************************************************************************
	 * Get the risk amount.
	 * <P>
	 * 
	 * @return the risk amount of this CRM message as a string.
	 */
	public String getRiskAmount()
	{
		return riskAmount;
	}
	/*******************************************************************************************************************************
	 * Get the limit amount.
	 * <P>
	 * 
	 * @return the limit amount of this CRM message as a string.
	 */
	public String getLimitAmount()
	{
		return limitAmount;
	}
	/*******************************************************************************************************************************
	 * Get the available amount.
	 * <P>
	 * 
	 * @return the available amount of this CRM message as a string.
	 */
	public String getAvailableAmount()
	{
		return availableAmount;
	}
}
