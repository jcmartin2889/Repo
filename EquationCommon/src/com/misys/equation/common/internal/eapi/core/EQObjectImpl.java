// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQFunction: Base class to store the state for an Equation function call
// -------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.misys.equation.common.utilities.EQCommonConstants;
import com.misys.equation.common.utilities.EquationLogger;

/***********************************************************************************************************************************
 * Class for storing the non-control data for an Equation API.
 * <P>
 * An API primariliy consists of a set of fields. These fields can be accesed through the getField, getFieldValue, setFieldValue
 * methods. Some APIs have an additional EQList object which contains a repeating set of rows of further fields. These fields can be
 * accessed through the corresponding methods on the list object. The list object itself is accessed through the getList method.
 * <P>
 * The fields used in an API and other API definition information is stored in an EQObjectMetaData object. This can be accessed
 * through the accessor method getMetaData.
 * <P>
 * EQTransaction, EQEnquiry, and EQPrompt implement EQObject. For examples of how the underlying EQObjects are accessed see the
 * samples.
 * <P>
 * 
 * @see com.misys.equation.ebs.samples.EQTransactionSample
 * @see com.misys.equation.ebs.samples.EQEnquirySample
 * @see com.misys.equation.ebs.samples.EQPromptSample
 * @author Misys International Banking Systems Ltd.
 */
public class EQObjectImpl implements java.io.Serializable, EQObject
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQObjectImpl.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006" </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/*******************************************************************************************************************************
	 * The interface version.
	 * <P>
	 * The minimum version of Equation that this interface is compatible with.
	 */
	public static final String equationVersion = "CX011";
	protected static final EquationLogger LOG = new EquationLogger(EQObjectImpl.class);

	private static final String UNINITIALISED = "UNINITIALISED";
	private static final String INCOMPLETE = "INCOMPLETE";
	private static final String REFER = "REFER";
	private static final String ERRORS = "ERRORS";
	private static final String WARNINGS = "WARNINGS";
	private static final String VALID = "VALID";
	private static final String COMPLETE = "COMPLETE";
	private static final String PROMPTED = "PROMPTED";
	private static final String FAILED = "FAILED";
	private static final String INT_UPDATE_ERROR = "INT_UPDATE_ERROR";
	private static final String DATA_RETRIEVED = "DATA RETRIEVED";
	private static final String SQL_ERROR = "SQL ERROR";
	private static final String UNKNOWN = "UNKNOWN";
	/*
	 * Equation's List Input and Output
	 */
	protected EQList list;
	// The current state of the transaction
	protected int status;
	/*
	 * Messages, Field State and Function Keys
	 */
	protected ArrayList<EQMessage> MSGS;
	/*
	 * Equation's Fixed Image
	 */
	private static final int MAX_FIELD_IMAGE_SIZE = 9999;
	private static final String BLANK_FIELD_IMAGE = EQCommonConstants.initBlankStringBuffer(MAX_FIELD_IMAGE_SIZE).toString();
	/*******************************************************************************************************************************
	 * Get the list of messages for the object.
	 */
	public ArrayList<EQMessage> getMessages()
	{
		return MSGS;
	}
	/*******************************************************************************************************************************
	 * Get the string representation of the current status code.
	 */
	public String getStatusString()
	{
		switch (status)
		{
			case EQObject.STATUS_UNINITIALISED:
				return UNINITIALISED;
			case EQObject.STATUS_INCOMPLETE:
				return INCOMPLETE;
			case EQObject.STATUS_REFER:
				return REFER;
			case EQObject.STATUS_ERRORS:
				return ERRORS;
			case EQObject.STATUS_WARNINGS:
				return WARNINGS;
			case EQObject.STATUS_VALID:
				return VALID;
			case EQObject.STATUS_COMPLETE:
				return COMPLETE;
			case EQObject.STATUS_PROMPTED:
				return PROMPTED;
			case EQObject.STATUS_FAILED:
				return FAILED;
			case EQObject.STATUS_INT_UPDATE_ERROR:
				return INT_UPDATE_ERROR;
			case EQObject.STATUS_DATA_RETRIEVED:
				return DATA_RETRIEVED;
			case EQObject.STATUS_SQL_ERROR:
				return SQL_ERROR;
			default:
				return UNKNOWN;
		}
	}
	protected HashMap<String, EQField> hmFields = null;
	protected EQObjectMetaData metaData = null;
	// modified property
	// set to true when the function has any fields modified
	// reset to false when the function becomes complete
	protected boolean modified;
	// readOnly property
	// prevents any modification of field values
	private boolean readOnly;
	/*******************************************************************************************************************************
	 * Default Constructor.
	 */
	protected EQObjectImpl()
	{
	}
	/*******************************************************************************************************************************
	 * Constructor which uses the previously created EQObjectMetaData contain field definition information.
	 */
	protected void initialise(EQObjectMetaData metaData)
	{
		this.metaData = metaData;
		hmFields = new HashMap<String, EQField>(metaData.nFixedInputFields + metaData.nFixedOutputFields + 1, 1.0f);
		if (metaData.nListInputFields > 0 || metaData.nListOutputFields > 0)
		{
			list = new EQList(metaData);
		}
		reset();
	}
	/*******************************************************************************************************************************
	 * Get the field object for a specific field in the function.
	 */
	/*******************************************************************************************************************************
	 * Get the field object for a specific field in the function.
	 * <P>
	 * Note this method does not return any of the fields contained in any list in the fucntion. Use getList to obtain the list.
	 * <P>
	 */
	public EQField getField(String fieldID)
	{
		return (hmFields.get(fieldID));
	}
	/*******************************************************************************************************************************
	 * Get the function's field image.
	 * <P>
	 * 
	 * @return the function's field image
	 */
	protected String getFieldImage()
	{
		char image[] = new char[metaData.nFixedImageSize];
		int nCount = 0, nImagePointer = 0;
		String strFieldValue;
		// initialse with blanks
		BLANK_FIELD_IMAGE.getChars(0, metaData.nFixedImageSize, image, 0);
		int nlength = 0;
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		for (; nCount < metaData.nFixedInputFields; nCount++)
		{
			eqFieldDefinition = metaData.arrFixedInputFieldDefinitions.get(nCount);
			eqField = hmFields.get(eqFieldDefinition.getFieldName());
			if (eqField.isInitialised())
			{
				strFieldValue = eqField.getValue();
				nlength = strFieldValue.length();
				strFieldValue.getChars(0, nlength, image, nImagePointer);
			}
			else
			{
				// Value not initialised - mark it as such when we call Equation
				image[nImagePointer] = '\u007f';
			}
			nImagePointer += eqField.definition.maxSize;
		}
		return EQCommonConstants.rightTrimmedString(image, 0, metaData.nFixedImageSize);
	}
	/*******************************************************************************************************************************
	 * Get the HashMap of all fields in the function.
	 */
	@SuppressWarnings("unchecked")
	public HashMap<String, EQField> getFields()
	{
		return (HashMap<String, EQField>) hmFields.clone();
	}
	/*******************************************************************************************************************************
	 * Get the value of a specific field in the function.
	 * <P>
	 * Note this method does not return any values for the fields contained in any list in the fucntion. Use getList to obtain the
	 * list.
	 * <P>
	 * Note this method is equivalent to <code>getField(String).getValue()</code>.
	 * <P>
	 */
	public String getFieldValue(String fieldID)
	{
		return (hmFields.get(fieldID)).getValue();
	}
	/*******************************************************************************************************************************
	 * Get any list associated with the equation enquiry, transaction or prompt.
	 */
	public EQList getList()
	{
		return list;
	}
	/*******************************************************************************************************************************
	 * Get the EQObjectMetaData associated with the Equation object.
	 */
	public EQObjectMetaData getMetaData()
	{
		return metaData;
	}
	/*******************************************************************************************************************************
	 * Determine if the function has been modified.
	 */
	public boolean isModified()
	{
		if (list != null && list.isModified())
		{
			return true;
		}
		return modified;
	}
	/*******************************************************************************************************************************
	 * Determine if the object can be modified.
	 */
	public boolean isReadOnly()
	{
		return readOnly;
	}
	/*******************************************************************************************************************************
	 * Reset the object's data.
	 * <P>
	 * All properties, fields and state are reset to that of a newly constructed object.
	 */
	protected void reset()
	{
		readOnly = false;
		modified = false;
		resetFields();
		MSGS = new ArrayList<EQMessage>(0);
		status = STATUS_UNINITIALISED;
	}
	/*******************************************************************************************************************************
	 * Resets the function's field values.
	 * <P>
	 * This resets all of the values for the function's fields to their uninitialised state. If the function contains a list that is
	 * also emptied.
	 */
	private void resetFields()
	{
		readOnly = false;
		modified = false;
		// reseting the fields means than any journal key must also be reset
		// reseting the fields means than any before image must also be reset
		// Reset Input/Output Fields
		int nCount = 0;
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		for (nCount = metaData.nFixedInputFields; --nCount >= 0;)
		{
			eqFieldDefinition = metaData.arrFixedInputFieldDefinitions.get(nCount);
			eqField = new EQField(eqFieldDefinition);
			hmFields.put(eqFieldDefinition.fieldName, eqField);
		}
		for (nCount = metaData.nFixedOutputFields; --nCount >= 0;)
		{
			eqFieldDefinition = metaData.arrFixedOutputFieldDefinitions.get(nCount);
			eqField = new EQField(eqFieldDefinition);
			hmFields.put(eqFieldDefinition.fieldName, eqField);
		}
		if (list != null)
		{
			list.reset();
		}
	}
	/*******************************************************************************************************************************
	 * Allow the definition to be defined at runtime.
	 */
	public void setFieldDefinition(EQObjectMetaData map)
	{
		hmFields = new HashMap<String, EQField>(map.nFixedInputFields + map.nFixedOutputFields + 1, 1.0f);
	}
	/*******************************************************************************************************************************
	 * Extract the function's individual field values from the field image.
	 */
	protected void setFieldsFromImage(String image)
	{
		int nImagePointerStart = 0, nImagePointerEnd = 0, nImageLength = image.length();
		int nCount = 0;
		char[] charImage = image.toCharArray();
		EQField eqField = null;
		EQFieldDefinition eqFieldDefinition = null;
		for (; nCount < metaData.nFixedInputFields; nCount++)
		{
			eqFieldDefinition = metaData.arrFixedInputFieldDefinitions.get(nCount);
			nImagePointerStart = eqFieldDefinition.dataSetImageOffset - 1;
			if (nImagePointerStart < nImageLength)
			{
				nImagePointerEnd = nImagePointerStart + eqFieldDefinition.maxSize;
				if (nImagePointerEnd > nImageLength)
				{
					nImagePointerEnd = nImageLength;
				}
				eqField = hmFields.get(eqFieldDefinition.fieldName);
				eqField.internal_SetValue(EQCommonConstants.rightTrimmedString(charImage, nImagePointerStart, nImagePointerEnd));
				eqField.resetMessageState();
				// nImagePointerStart = nImagePointerEnd;
			}
			else
			{
				eqField = hmFields.get(eqFieldDefinition.fieldName);
				eqField.internal_SetValue("");
			}
		}
		// Reset nCount for Output Parameters
		nCount = 0;
		for (; nCount < metaData.nFixedOutputFields; nCount++)
		{
			eqFieldDefinition = metaData.arrFixedOutputFieldDefinitions.get(nCount);
			nImagePointerStart = eqFieldDefinition.dataSetImageOffset - 1;
			if (nImagePointerStart < nImageLength)
			{
				nImagePointerEnd = nImagePointerStart + eqFieldDefinition.maxSize;
				if (nImagePointerEnd > nImageLength)
				{
					nImagePointerEnd = nImageLength;
				}
				eqField = hmFields.get(eqFieldDefinition.fieldName);
				eqField.internal_SetValue(EQCommonConstants.rightTrimmedString(charImage, nImagePointerStart, nImagePointerEnd));
				// nImagePointerStart = nImagePointerEnd;
			}
			else
			{
				eqField = hmFields.get(eqFieldDefinition.fieldName);
				eqField.internal_SetValue("");
			}
		}
	}
	/*******************************************************************************************************************************
	 * Set any of the object's fields from a map.
	 */
	public boolean setFieldValue(Map<String, String[]> parameters)
	{
		if (isReadOnly())
		{
			throw new IllegalStateException("Can't set field values in readOnly mode");
		}
		boolean fieldModified = false;
		Iterator<String> iter = parameters.keySet().iterator();
		String key;
		String[] values;
		while (iter.hasNext())
		{
			key = iter.next();
			// examine fields collection
			if (hmFields.containsKey(key))
			{
				values = parameters.get(key);
				EQField eqField = (hmFields.get(key));
				if (!eqField.isProtected() && eqField.getDefinition().inputCapable)
				{
					if (eqField.setValue(values[0]))
					{
						fieldModified = true;
					}
				}
			}
			else if (list != null) // examine list
			{
				String fieldName;
				int rowNum = 0;
				int posOfColon1 = 0;
				if (key.length() > 5 && (key.substring(0, 5).equals("List:")))
				{
					posOfColon1 = key.indexOf(":", 5);
					rowNum = Integer.parseInt(key.substring(5, posOfColon1));
					fieldName = key.substring(posOfColon1 + 1);
					values = parameters.get(key);
					if (list.setFieldValue(rowNum, fieldName, values[0]))
					{
						fieldModified = true;
					}
				}
			}
		}
		if (fieldModified)
		{
			modified = true;
			status = STATUS_INCOMPLETE;
		}
		return modified;
	}
	/*******************************************************************************************************************************
	 * Set the value of a specific field in the API.
	 */
	public boolean setFieldValue(String fieldID, String fieldValue)
	{
		if (isReadOnly())
		{
			throw new IllegalStateException("Can't set field values in readOnly mode");
		}
		EQField eqField = null;
		if ((eqField = hmFields.get(fieldID)) == null)
		// Field does not exist
		{
			throw new IllegalArgumentException("Field does not belong to this transaction : " + fieldID);
		}
		else
		{
			if (eqField.setValue(fieldValue))
			{
				modified = true;
				status = STATUS_INCOMPLETE;
				return true;
			}
		}
		return false;
	}
	/*******************************************************************************************************************************
	 * Return the current status of the object, one of the STATUS constants.
	 */
	public int getStatus()
	{
		return status;
	}
	/*******************************************************************************************************************************
	 * Get the object identifier.
	 */
	public String getIdentifier()
	{
		return metaData.getIdentifier();
	}
	/*******************************************************************************************************************************
	 * Specifies that the fields of the function can no longer be modified.
	 */
	public void setReadOnly()
	{
		readOnly = true;
		// mark all fields as protected
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		int nCount = 0;
		for (nCount = metaData.nFixedInputFields; --nCount > 0;)
		{
			eqFieldDefinition = metaData.arrFixedInputFieldDefinitions.get(nCount);
			eqField = (hmFields.get(eqFieldDefinition.fieldName));
			eqField.setProtected();
		}
		if (list != null)
		{
			list.setReadOnly();
		}
	}
}
