// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQField: Abstract class to store the state for an Equation field
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.util.ArrayList;

/***********************************************************************************************************************************
 * Class for storing the state of a field.
 * <P>
 * A field knows its field definition and value. A field can have messages.
 * <P>
 * This class implements the behaviour of both input capable and output fields.
 * <P>
 * Note in Incremental mode Equation can dynamically modify the display characteristics of an input field, such that it should be
 * displayed like an output field. See the displayAsInputField and displayAsOutputField methods.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
public class EQField implements java.io.Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQField.java 12544 2012-02-29 21:38:32Z perkinj1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	protected EQFieldDefinition definition;
	protected String value;
	// The field's visibility is determined by the state of one
	// of these fields. If both are false the field is invisible.
	protected boolean bDisplayAsInput;
	protected boolean bDisplayAsOutput;
	// The field can be either or none of error/warning
	protected boolean bError;
	protected boolean bWarning;
	// The field can be read-only
	protected boolean bProtected;
	// Whether the field has been initialised with a non-blank value
	protected boolean bInitialised;
	// any associated messages for the field
	protected ArrayList<EQMessage> msgs;
	/*******************************************************************************************************************************
	 * Default Constructor.
	 */
	@SuppressWarnings("unused")
	private EQField()
	{
	}
	/*******************************************************************************************************************************
	 * Constructs a field object using its definition.
	 * <P>
	 * 
	 * @param fieldDefinition
	 */
	protected EQField(EQFieldDefinition fieldDefinition)
	{
		definition = fieldDefinition;
		value = "";
		bDisplayAsInput = false;
		bDisplayAsOutput = false;
		bError = false;
		bWarning = false;
		bProtected = false;
		bInitialised = false;
		msgs = null;
	}
	/*******************************************************************************************************************************
	 * Get the definition of the field.
	 * <P>
	 * 
	 * @return the field name.
	 */
	public EQFieldDefinition getDefinition()
	{
		return definition;
	}
	/*******************************************************************************************************************************
	 * Determine whether the field has been initialised.
	 * <P>
	 * The field tracks whether it has been initialised with a non-blank value. This will be false until setValue is called with a
	 * string whose length is greater than zero.
	 * <P>
	 * 
	 * @return true if the field has had a non-blank value set.
	 */
	protected boolean isInitialised()
	{
		return bInitialised;
	}
	/*******************************************************************************************************************************
	 * Set the value of the field.
	 * <P>
	 * Only valid for input fields. This method checks that the value is within the maximum size supported by the field.
	 * <P>
	 * Note that this method does not check the case of the value. This will be validated by the host.
	 * <P>
	 * 
	 * @param value
	 *            the new value for the field
	 * @return true if the field was modified, false if the value was the same as the value stored.
	 * @throws IllegalStateException
	 *             if the field is an Output field or is protected.
	 * @throws IllegalArgumentException
	 *             if the value is too large for the field.
	 */
	public boolean setValue(String value)
	{
		if (definition.isInputCapable() == false || bProtected)
		{
			throw new IllegalStateException("Can't set values for output or protected fields");
		}
		if (value.length() > definition.getMaxSize())
		{
			throw new IllegalArgumentException("Field value length exceeds maximum field size");
		}
		return internal_SetValue(value);
	}
	/*******************************************************************************************************************************
	 * Force the value of the field.
	 * <P>
	 * This method changes the value of a field, even if it is an output field or if it is protected. This method checks that the
	 * value is within the maximum size supported by the field.
	 * <P>
	 * Note that this method does not check the case of the value. This will be validated by the host.
	 * <P>
	 * <i>In general the value of output fields should not be changed, so this method would not be used - </i>setValue(String)<i>
	 * would be used instead. This method should only be used when extending EQTransactionImpl, EQEnquiryImpl, etc.</i>
	 * <P>
	 * 
	 * @param value
	 *            the new value for the field
	 * @return true if the field was modified, false if the value was the same as the value stored.
	 * @throws IllegalArgumentException
	 *             if the value is too large for the field.
	 */
	public boolean forceValue(String value)
	{
		if (value.length() > definition.getMaxSize())
		{
			throw new IllegalArgumentException("Field value length exceeds maximum field size");
		}
		return internal_SetValue(value);
	}
	/*******************************************************************************************************************************
	 * Internal package method for seting the value of a field. Bypasses exception checking.
	 * 
	 * @return true if the value was modified, false if the value wasn't (i.e.it was the same)
	 */
	protected boolean internal_SetValue(String fieldValue)
	{
		if (value.equals(fieldValue))
		{
			return false;
		}
		value = fieldValue; // accept the value
		// detect when the field first becomes non-zero in length
		if (value.length() > 0)
		{
			bInitialised = true;
		}
		return true;
	}
	/*******************************************************************************************************************************
	 * Get the value of the field.
	 * <P>
	 * 
	 * @return the field's current value.
	 */
	public String getValue()
	{
		return value;
	}
	/*******************************************************************************************************************************
	 * Determine if the field should be displayed as an input capable field.
	 * <P>
	 * This always returns false for an output field. For an input field this can return true or false.
	 * <P>
	 * This is only relevant in incremental mode as it uses run-time visiblity information from Equation.
	 * <P>
	 * 
	 * @return true if the field is visible and should be displayed as an input field.
	 */
	public boolean displayAsInputField()
	{
		return bDisplayAsInput;
	}
	/*******************************************************************************************************************************
	 * Determine if the field should be displayed as an output field.
	 * <P>
	 * This always returns true for an output field. For an input field this can return true or false. If true the field should be
	 * displayed like an output field, as opposed to a protected input field.
	 * <P>
	 * This is only relevant in incremental mode as it uses run-time visiblity information from Equation.
	 * <P>
	 * 
	 * @return true if the field is visible and should be displayed as an output field.
	 */
	public boolean displayAsOutputField()
	{
		return bDisplayAsOutput;
	}
	/*******************************************************************************************************************************
	 * Determine whether the field is in error.
	 * <P>
	 * If this returns true use getMessages to obtain the errors for the field.
	 * <P>
	 * 
	 * @return true if the field is in error.
	 */
	public boolean isError()
	{
		return bError;
	}
	/*******************************************************************************************************************************
	 * Determine whether the field has any warning messages.
	 * <P>
	 * If this returns true use getMessages to obtain the warnings for the field.
	 * <P>
	 * 
	 * @return true if the field has warnings.
	 */
	public boolean isWarning()
	{
		return bWarning;
	}
	/*******************************************************************************************************************************
	 * Determine if the field is visible.
	 * <P>
	 * This is only relevant in incremental mode as it uses run-time visiblity information from Equation.
	 * <P>
	 * 
	 * @return true if the field is visible and should eb displayed to the user.
	 */
	public boolean isVisible()
	{
		return bDisplayAsInput || bDisplayAsOutput;
	}
	/*******************************************************************************************************************************
	 * Mark the field as read-only.
	 * <P>
	 * If the field is protected the user should not be allowed to set its value. When true, a call to setValue will throw an
	 * exception.
	 */
	protected void setProtected()
	{
		bProtected = true;
	}
	/*******************************************************************************************************************************
	 * Determine if the field is protected.
	 * <P>
	 * If the field is protected the user should not be allowed to set its value. When true, a call to setValue will throw an
	 * exception.
	 * <P>
	 * This is only relevant in incremental mode as it uses run-time visiblity information from Equation.
	 * <P>
	 * 
	 * @return true if the field is protected (read-only).
	 */
	public boolean isProtected()
	{
		return bProtected;
	}
	/*******************************************************************************************************************************
	 * Get the list of messages associated with the field.
	 * <P>
	 * The field may have zero, one or more messages associated with it. These messages have severity of Error, Warning or
	 * Information.
	 * <P>
	 * If isError returns true then this method will return 1 or more error messages.
	 * <P>
	 * If isWarning returns true then this method will return 1 or more warning messages.
	 * <P>
	 * Otherwise this method could return null or 1 or more information messages.
	 * <P>
	 * Messages of different severity are never mixed.
	 * <P>
	 * 
	 * @return a list of EQMessage objects assocated with the field, can be null.
	 */
	public ArrayList<EQMessage> getMessages()
	{
		return msgs;
	}
	/*******************************************************************************************************************************
	 * Associates an Equation message with the field.
	 * <P>
	 * This method is called by the framework when a call to the host has been made and messages related to the field are returned.
	 * The STATUS of that host call is used to set the field's state. If the value is STATUS_ERRORS the field is marked as being in
	 * error, if STATUS_WARINING the field is marked with warnings.
	 * <P>
	 * This method is public to allow the application to seamlessly apply further messages to the field if required.
	 * <P>
	 * 
	 * @param msg
	 *            the message to be assoicated with the field.
	 */
	public void addMessage(EQMessage msg)
	{
		if (msgs == null)
		{
			msgs = new ArrayList<EQMessage>(2); // don't expect more than two messages
		}
		msgs.add(msg);
		if (msg.getSeverity().equals(EQMessage.SEVERITY_ERROR))
		{
			bError = true; // error field
		}
		else if (msg.getSeverity().equals(EQMessage.SEVERITY_WARNING))
		{
			bWarning = true; // warning field
		}
	}
	/*******************************************************************************************************************************
	 * Set the visibility state of the field returned from Equation.
	 * 
	 * @param visibility
	 *            the visibility code ('O' for output, 'B' for input, 'P' for protected input)
	 */
	protected void setVisibility(char visibility)
	{
		resetState();
		// Whether the field is visible or not
		if (visibility == 'O')
		{
			bDisplayAsOutput = true;
		}
		else if (visibility == 'B')
		{
			bDisplayAsInput = true;
		}
		else if (visibility == 'P')
		{
			bDisplayAsInput = true;
			bProtected = true;
		}
	}
	/*******************************************************************************************************************************
	 * Reset the run-time attributes of the field.
	 */
	protected void resetState()
	{
		bDisplayAsInput = false;
		bDisplayAsOutput = false;
		bProtected = false;
		resetMessageState();
	}
	/*******************************************************************************************************************************
	 * Reset the run-time attributes of the field.
	 */
	protected void resetMessageState()
	{
		bError = false;
		bWarning = false;
		msgs = null;
	}
}
