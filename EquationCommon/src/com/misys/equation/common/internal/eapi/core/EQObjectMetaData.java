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
 * Class for storing a transaction/enquiry/prompt API definition.
 * <P>
 * Field definitions are kept in collections for fixed input fields, fixed output fields, list input fields, and list output fields.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
public class EQObjectMetaData implements java.io.Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQObjectMetaData.java 4646 2009-09-07 16:28:30Z weddelc1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	public static final int API_TYPE_FIXED_TRANSACTION = 1;
	public static final int API_TYPE_LIST_TRANSACTION = 2;
	public static final int API_TYPE_FIXED_ENQUIRY = 3;
	public static final int API_TYPE_LIST_ENQUIRY = 4;
	public static final int API_TYPE_PROMPT = 9;
	private char storeFieldDescriptions;
	protected String identifier;
	protected int functionType;
	protected String rootID;
	protected String entryProgram;
	// Source Control Identification
	protected String sourceApplication;
	protected String sourceVersion;
	protected String sourceRelease;
	protected String sourceTask;
	protected String title;
	protected boolean supportsAdd = false;
	protected boolean supportsMaintain = false;
	protected boolean supportsDelete = false;
	protected int nFixedInputFields = 0;
	protected int nFixedOutputFields = 0;
	protected int nFixedImageSize = 0;
	protected int nListInputFields = 0;
	protected int nListOutputFields = 0;
	protected int nRowImageSize = 0;
	protected int nRowControlSize = 0;
	protected ArrayList<EQFieldDefinition> arrFixedInputFieldDefinitions;
	protected ArrayList<EQFieldDefinition> arrFixedOutputFieldDefinitions;
	protected ArrayList<EQFieldDefinition> arrListInputFieldDefinitions;
	protected ArrayList<EQFieldDefinition> arrListOutputFieldDefinitions;
	/*******************************************************************************************************************************
	 * Default Constructor.
	 */
	protected EQObjectMetaData()
	{
	}
	/*******************************************************************************************************************************
	 * Constructs a HashMap of field definitions for a given API. An enquiry has been called at runtime to retrieve this
	 * information.
	 * <P>
	 * 
	 * @param metaDataEnquiry
	 */
	protected EQObjectMetaData(EQObject metaDataEnquiry)
	{
		// Application specific parameters retrieved from environment
		try
		{
			EQEnvironment env = EQEnvironment.getAppEnvironment();
			storeFieldDescriptions = env.getProperty("STORE_FIELD_DESCRIPTIONS", "N").charAt(0);
		}
		catch (Exception e)
		{
		}
		// no need to worry, env problems will get reported earlier
		identifier = metaDataEnquiry.getFieldValue("ZLAID");
		functionType = new Integer(metaDataEnquiry.getFieldValue("ZLATYP")).intValue();
		rootID = metaDataEnquiry.getFieldValue("ZLROOT");
		entryProgram = metaDataEnquiry.getFieldValue("ZLAFPR");
		StringBuffer s = new StringBuffer("          ");
		entryProgram = s.replace(0, 10, entryProgram).toString();
		sourceApplication = metaDataEnquiry.getFieldValue("ZLAPP");
		sourceVersion = metaDataEnquiry.getFieldValue("ZLVER");
		sourceRelease = metaDataEnquiry.getFieldValue("ZLREL");
		sourceTask = metaDataEnquiry.getFieldValue("ZLTSK");
		title = metaDataEnquiry.getFieldValue("ZLONM");
		if ((metaDataEnquiry.getFieldValue("ZLADD").charAt(0)) == 'Y')
		{
			supportsAdd = true;
		}
		if ((metaDataEnquiry.getFieldValue("ZLMNT").charAt(0)) == 'Y')
		{
			supportsMaintain = true;
		}
		if ((metaDataEnquiry.getFieldValue("ZLDEL").charAt(0)) == 'Y')
		{
			supportsDelete = true;
		}
		nFixedInputFields = new Integer(metaDataEnquiry.getFieldValue("ZLFIF").trim()).intValue();
		nFixedOutputFields = new Integer(metaDataEnquiry.getFieldValue("ZLFOF").trim()).intValue();
		int nFixedInputSize = new Integer(metaDataEnquiry.getFieldValue("ZLFIFL").trim()).intValue();
		int nFixedOutputSize = new Integer(metaDataEnquiry.getFieldValue("ZLFOFL").trim()).intValue();
		nFixedImageSize = nFixedInputSize + nFixedOutputSize;
		nListInputFields = new Integer(metaDataEnquiry.getFieldValue("ZLRIF").trim()).intValue();
		nListOutputFields = new Integer(metaDataEnquiry.getFieldValue("ZLROF").trim()).intValue();
		int nRowInputSize = new Integer(metaDataEnquiry.getFieldValue("ZLRIFL").trim()).intValue();
		int nRowOutputSize = new Integer(metaDataEnquiry.getFieldValue("ZLROFL").trim()).intValue();
		nRowImageSize = nRowInputSize + nRowOutputSize;
		nRowControlSize = nListInputFields + 7;
		arrFixedInputFieldDefinitions = new ArrayList<EQFieldDefinition>(nFixedInputFields);
		arrFixedOutputFieldDefinitions = new ArrayList<EQFieldDefinition>(nFixedOutputFields);
		arrListInputFieldDefinitions = new ArrayList<EQFieldDefinition>(nListInputFields);
		arrListOutputFieldDefinitions = new ArrayList<EQFieldDefinition>(nListOutputFields);
		EQFieldDefinition fieldDefinition;
		char defDataSet;
		boolean defInputCapable;
		String defFieldName;
		int defMaxSize;
		String defFieldDescription = null;
		String defFieldReference;
		boolean defUpperCaseOnly;
		boolean defDoubleByteSupported;
		char defDataType;
		int defDecimalPlaces;
		boolean defKeyField;
		boolean defBeforeImage;
		int defImageOffset;
		int nRows = metaDataEnquiry.getList().getNumRows();
		int nEnquiryFieldsCount = 0;
		for (; nEnquiryFieldsCount < nRows; nEnquiryFieldsCount++)
		{
			defDataSet = (metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHDATASET")).charAt(0);
			defFieldName = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHFLDNAM");
			defInputCapable = (metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHUSE").charAt(0) == 'B');
			defMaxSize = Integer.parseInt(metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHSIZE").trim());
			defUpperCaseOnly = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHUCASE").charAt(0) == 'Y';
			defDoubleByteSupported = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHDBCS").charAt(0) == 'Y';
			defFieldDescription = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHFLDD");
			defFieldReference = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHFREF");
			defDataType = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHFLDTYP").charAt(0);
			defDecimalPlaces = Integer.parseInt(metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHDEC").trim());
			defKeyField = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHKEYS").charAt(0) == 'Y';
			defBeforeImage = metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHBIMG").charAt(0) == 'Y';
			defImageOffset = Integer.parseInt(metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHOFSET").trim());
			if (storeFieldDescriptions == 'Y')
			{
				defFieldDescription = (metaDataEnquiry.getList().getFieldValue(nEnquiryFieldsCount, "ZHFLDD")).trim();
			}
			else
			{
				defFieldDescription = null;
			}
			// Numbers have the number of digits (including decimal places in defMaxSize
			// We only process strings, so add 2 characters to the number of digits
			// One for decimal place and one for sign
			if (defDataType != 'A' && defDataSet == 'V')
			{
				defMaxSize = defMaxSize + 2;
			}
			fieldDefinition = new EQFieldDefinition(defDataSet, defFieldName, defInputCapable, defMaxSize, defUpperCaseOnly,
							defDoubleByteSupported, defFieldDescription, defFieldReference, defDataType, defDecimalPlaces,
							defKeyField, defBeforeImage, defImageOffset);
			if (defDataSet == 'F' && defInputCapable)
			{
				arrFixedInputFieldDefinitions.add(fieldDefinition);
			}
			if (defDataSet == 'F' && defInputCapable == false)
			{
				arrFixedOutputFieldDefinitions.add(fieldDefinition);
			}
			if (defDataSet == 'V' && defInputCapable)
			{
				arrListInputFieldDefinitions.add(fieldDefinition);
			}
			if (defDataSet == 'V' && defInputCapable == false)
			{
				arrListOutputFieldDefinitions.add(fieldDefinition);
			}
		}
	}
	/*******************************************************************************************************************************
	 * Get the type of the function.
	 * <P>
	 * 
	 * @return the API function type. One of the API_TYPE Constants.
	 */
	public int getFunctionType()
	{
		return functionType;
	}
	/*******************************************************************************************************************************
	 * Get the identifier of the function.
	 * <P>
	 * 
	 * @return the API function identifier.
	 */
	public String getIdentifier()
	{
		return identifier;
	}
	/*******************************************************************************************************************************
	 * Get entry program.
	 * <P>
	 * 
	 * @return the entry program.
	 */
	protected String getEntryProgram()
	{
		return entryProgram;
	}
	/*******************************************************************************************************************************
	 * Get the data schema version of this function.
	 * <P>
	 * 
	 * @return the version number of this function.
	 */
	public String getVersion()
	{
		return "Application:" + sourceApplication + " Release:" + sourceRelease + " Task:" + sourceTask;
	}
	/*******************************************************************************************************************************
	 * Get the root name of the function.
	 * <P>
	 * This is the base name of the associated Equation program.
	 * <P>
	 * 
	 * @return the root name of the function.
	 */
	public String getRootID()
	{
		if (rootID.length() == 0)
		{
			throw new IllegalStateException("rootID not set by subclass");
		}
		return rootID;
	}
	/*******************************************************************************************************************************
	 * Determine whether this transaction supports the ADD Maintenance mode.
	 * <P>
	 * 
	 * @return true if the Add operation is supported
	 */
	public boolean supportsAdd()
	{
		return supportsAdd;
	}
	/*******************************************************************************************************************************
	 * Determine whether this transaction supports the MAINTAIN Maintenance mode.
	 * <P>
	 * 
	 * @return true if the Maintain operation is supported
	 */
	public boolean supportsMaintain()
	{
		return supportsMaintain;
	}
	/*******************************************************************************************************************************
	 * Determine whether this transaction supports the DELETE Maintenance mode.
	 * <P>
	 * 
	 * @return true if the Delete operation is supported
	 */
	public boolean supportsDelete()
	{
		return supportsDelete;
	}
	/*******************************************************************************************************************************
	 * Get the title of the Transaction/Enquiry/Prompt.
	 * <P>
	 * 
	 * @return the title of the Transaction/Enquiry/Prompt.
	 */
	public String getTitle()
	{
		return title;
	}
	/*******************************************************************************************************************************
	 * Get the source control application.
	 * <P>
	 * 
	 * @return the source control application.
	 */
	public String getSourceApplication()
	{
		return sourceApplication;
	}
	/*******************************************************************************************************************************
	 * Get the source control release.
	 * <P>
	 * 
	 * @return the source control release.
	 */
	public String getSourceRelease()
	{
		return sourceRelease;
	}
	/*******************************************************************************************************************************
	 * Get the source control task.
	 * <P>
	 * 
	 * @return the source control task.
	 */
	public String getSourceTask()
	{
		return sourceTask;
	}
	/*******************************************************************************************************************************
	 * Get the source control version.
	 * <P>
	 * 
	 * @return the source control version.
	 */
	public String getSourceVersion()
	{
		return sourceVersion;
	}
	/*******************************************************************************************************************************
	 * Get the number of fixed input fields.
	 * <P>
	 * 
	 * @return the number of input fields in the function. Excludes any list fields.
	 */
	public int getNumFixedInputFields()
	{
		return nFixedInputFields;
	}
	/*******************************************************************************************************************************
	 * Get the number of fixed output fields.
	 * <P>
	 * 
	 * @return the number of output fields in the function. Excludes any list fields.
	 */
	public int getNumFixedOutputFields()
	{
		return nFixedOutputFields;
	}
	/*******************************************************************************************************************************
	 * Get the size of the field image.
	 * <P>
	 * 
	 * @return the number of characters required for the function's field image.
	 */
	protected int getFixedImageSize()
	{
		return nFixedImageSize;
	}
	/*******************************************************************************************************************************
	 * Get the number of list input fields.
	 * <P>
	 * 
	 * @return the number of input fields in the function's list. i.e. the number of columns corresponding to input fields.
	 */
	public int getNumListInputFields()
	{
		return nListInputFields;
	}
	/*******************************************************************************************************************************
	 * Get the number of list output fields.
	 * <P>
	 * 
	 * @return the number of output fields in the function's list. i.e. the number of columns corresponding to output fields.
	 */
	public int getNumListOutputFields()
	{
		return nListOutputFields;
	}
	/*******************************************************************************************************************************
	 * Get the size of the field image for a row in the list.
	 * <P>
	 * 
	 * @return the number of characters required for the list's row field image.
	 */
	protected int getRowImageSize()
	{
		return nRowImageSize;
	}
	/*******************************************************************************************************************************
	 * Get the size of a row control data in the row.
	 * 
	 * @return Returns an integer containing the number of characters required for all fields in the control fields of a row.
	 */
	protected int getRowControlSize()
	{
		return nRowControlSize;
	}
	/*******************************************************************************************************************************
	 * Get an array containing field definitions for the fixed input fields.
	 * <P>
	 * 
	 * @return an ordered array of EQFieldDefinitions for the fixed input fields of the Eqution object.
	 */
	public ArrayList<EQFieldDefinition> getFixedInputFieldDefinitions()
	{
		return arrFixedInputFieldDefinitions;
	}
	/*******************************************************************************************************************************
	 * Get an array containing field definitions for the fixed output fields.
	 * <P>
	 * 
	 * @return an ordered array of EQFieldDefinitions for the fixed output fields of the Eqution object.
	 */
	public ArrayList<EQFieldDefinition> getFixedOutputFieldDefinitions()
	{
		return arrFixedOutputFieldDefinitions;
	}
	/*******************************************************************************************************************************
	 * Get an array containing field definitions for the list input fields.
	 * <P>
	 * 
	 * @return an ordered array of EQFieldDefinitions for the list input fields of the Eqution object.
	 */
	public ArrayList<EQFieldDefinition> getListInputFieldDefinitions()
	{
		return arrListInputFieldDefinitions;
	}
	/*******************************************************************************************************************************
	 * Get an array containing field definitions for the list output fields.
	 * <P>
	 * 
	 * @return an ordered array of EQFieldDefinitions for the list output fields of the Eqution object.
	 */
	public ArrayList<EQFieldDefinition> getListOutputFieldDefinitions()
	{
		return arrListOutputFieldDefinitions;
	}
	/*******************************************************************************************************************************
	 * Check if this function is an Enquiry by checking the "supports..." fields.
	 * <P>
	 * 
	 * @return true if it is an Enquiry else false
	 */
	public boolean isEnquiry()
	{
		if (!supportsAdd && !supportsMaintain && !supportsDelete)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
