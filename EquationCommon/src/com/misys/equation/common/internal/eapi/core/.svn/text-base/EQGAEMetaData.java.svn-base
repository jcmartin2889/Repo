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
 * Class for storing the GAE Enquiry MetaData details.
 * <P>
 * 
 * @author Misys International Banking Systems Ltd.
 */
class EQGAEMetaData extends EQObjectMetaData implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/*******************************************************************************************************************************
	 * The number of input fields for the T69DFunction.
	 */
	static protected final int nMetaFixedInputFields = 2;
	/*******************************************************************************************************************************
	 * The array containing the names of the input fields for the T69DFunction.
	 */
	static protected final String[] arrMetaFixedInputFieldNames = { "ZLAID", "ZLDOC" };
	/*******************************************************************************************************************************
	 * The array containing the sizes of the input fields for the T69DFunction.
	 */
	static protected final int[] arrMetaFixedInputFieldLengths = { 10, 1 };
	/*******************************************************************************************************************************
	 * The array containing the offsets for the input fields for the T69DFunction.
	 */
	static protected final int[] arrMetaFixedInputFieldOffsets = { 1, 11 };
	/*******************************************************************************************************************************
	 * The array containing the field references of the input fields for the T69DFunction.
	 */
	static protected final String[] arrMetaFixedInputFieldReferences = { "ZD10", "YNI" };
	/*******************************************************************************************************************************
	 * The array containing whether input fields only support upper case for the T69DFunction.
	 */
	static protected final boolean[] arrMetaFixedInputFieldUpperCases = { true, true };
	/*******************************************************************************************************************************
	 * The array containing whether input fields support double byte characters for the T69DFunction.
	 */
	static protected final boolean[] arrMetaFixedInputFieldDoubleBytes = { false, false };
	/*******************************************************************************************************************************
	 * The number of output fields for the T69DFunction.
	 */
	static protected final int nMetaFixedOutputFields = 36;
	/*******************************************************************************************************************************
	 * The array containing the names of the output fields for the T69DFunction.
	 */
	static protected final String[] arrMetaFixedOutputFieldNames = { "ZLKCA", "ZLONM", "ZLKCD", "ZLROOT", "ZLAFPR", "ZLKCC",
					"ZLATYP", "ZLATYPD", "ZLKCB", "ZLADD", "ZLMNT", "ZLDEL", "ZLKCG", "ZLAPP", "ZLKCE", "ZLFIF", "ZLFIFL", "ZLFOF",
					"ZLFOFL", "ZLKCI", "ZLREL", "ZLKCF", "ZLRIF", "ZLRIFL", "ZLROF", "ZLROFL", "ZLKCJ", "ZLVER", "ZLTSK", "ZLKCK",
					"ZLKCL", "ZLKCM", "ZLKCN", "ZLKCO", "ZLDOCN", "ZLKCP", "ZLKCQ" };
	/*******************************************************************************************************************************
	 * The array containing the sizes of the output fields for the T69DFunction.
	 */
	static protected final int[] arrMetaFixedOutputFieldLengths = { 10, 35, 10, 5, 10, 10, 3, 20, 30, 1, 1, 1, 15, 6, 30, 3, 4, 3,
					4, 15, 7, 30, 3, 4, 3, 4, 15, 8, 7, 30, 30, 30, 30, 30, 3, 30, 30 };
	/*******************************************************************************************************************************
	 * The array containing the offsets for the output fields for the T69DFunction.
	 */
	static protected final int[] arrMetaFixedOutputFieldOffsets = { 12, 22, 57, 67, 72, 82, 92, 95, 115, 145, 146, 147, 148, 163,
					169, 199, 202, 206, 209, 213, 228, 235, 265, 268, 272, 275, 279, 294, 302, 309, 339, 369, 399, 429, 459, 462,
					492 };
	/*******************************************************************************************************************************
	 * The array containing the field references of the output fields for the T69DFunction.
	 */
	static protected final String[] arrMetaFixedOutputFieldReferences = { "ZD10", "FNM", "ZD10", "APIR", "SCN", "ZD10", "ZD20",
					"ZD15", "SCAP", "ZD30", "APIA", "APIM", "APID", "ZD15", "SCRL", "ZD30", "NAPF", "LAPD", "NAPF", "LAPD", "ZD15",
					"SCTK", "ZD30", "NAPF", "LAPD", "NAPF", "LAPD", "SCVR", "ZD30", "ZD30", "ZD30", "ZD30", "ZD30", "NDOC", "ZD30",
					"ZD30" };
	/*******************************************************************************************************************************
	 * The size of the field image for the T69DFunction.
	 */
	static protected final int nMetaFixedImageSize = 518;
	// List fields
	/*******************************************************************************************************************************
	 * The number of list input fields for the T69DFunction.
	 */
	static protected final int nMetaListInputFields = 0;
	/*******************************************************************************************************************************
	 * The array containing the names of the list input fields for the T69DFunction.
	 */
	static protected final String[] arrMetaListInputFieldNames = {};
	/*******************************************************************************************************************************
	 * The array containing the sizes of the list input fields for the T69DFunction.
	 */
	static protected final int[] arrMetaListInputFieldLengths = {};
	/*******************************************************************************************************************************
	 * The array containing the offsets for the list input fields for the T69DFunction.
	 */
	static protected final int[] arrMetaListInputFieldOffsets = {};
	/*******************************************************************************************************************************
	 * The array containing the field references of the list input fields for the T69DFunction.
	 */
	static protected final String[] arrMetaListInputFieldReferences = {};
	/*******************************************************************************************************************************
	 * The array containing whether list input fields only support upper case for the T69DFunction.
	 */
	static protected final boolean[] arrMetaListInputFieldUpperCases = {};
	/*******************************************************************************************************************************
	 * The array containing whether list input fields support double byte characters for the T69DFunction.
	 */
	static protected final boolean[] arrMetaListInputFieldDoubleBytes = {};
	/*******************************************************************************************************************************
	 * The number of list output fields for the T69DFunction.
	 */
	static protected final int nMetaListOutputFields = 14;
	/*******************************************************************************************************************************
	 * The array containing the names of the list output fields for the T69DFunction.
	 */
	static protected final String[] arrMetaListOutputFieldNames = { "ZHFLDNAM", "ZHFLDD", "ZHDATASET", "ZHFLDTYP", "ZHUSE",
					"ZHOFSET", "ZHSIZE", "ZHDEC", "ZHFREF", "ZHUCASE", "ZHDBCS", "ZHBIMG", "ZHKEYS", "ZHJDOC" };
	/*******************************************************************************************************************************
	 * The array containing the sizes of the list output fields for the T69DFunction.
	 */
	static protected final int[] arrMetaListOutputFieldLengths = { 10, 30, 1, 1, 1, 4, 4, 2, 10, 1, 1, 1, 1, 80 };
	/*******************************************************************************************************************************
	 * The array containing the sizes of the list output fields for the T69DFunction.
	 */
	static protected final int[] arrMetaListOutputFieldOffsets = { 1, 11, 41, 42, 43, 44, 48, 52, 54, 64, 65, 66, 67, 68 };
	/*******************************************************************************************************************************
	 * The array containing the field references of the list output fields for the T69DFunction.
	 */
	static protected final String[] arrMetaListOutputFieldReferences = { "FNAM", "FDSC", "FDAT", "FTYP", "FUSE", "FOFS", "FSIZ",
					"FDEC", "FNAM", "YNI", "YNI", "YNI", "YNI", "JDOC" };
	/*******************************************************************************************************************************
	 * The size of the field image for a row in the list of the T69DFunction.
	 */
	static protected final int nMetaRowImageSize = 147;
	protected EQGAEMetaData()
	{
		nRowControlSize = 7 + nListInputFields;
		identifier = "GAE";
		functionType = 4;
		rootID = "T69D";
		sourceApplication = "EQ38";
		sourceVersion = "";
		sourceRelease = "P1364";
		sourceTask = "P1364";
		title = "Meta Data Map";
		supportsAdd = false;
		supportsMaintain = false;
		supportsDelete = false;
		nFixedInputFields = nMetaFixedInputFields;
		nFixedOutputFields = nMetaFixedOutputFields;
		nFixedImageSize = nMetaFixedImageSize;
		nListInputFields = nMetaListInputFields;
		nListOutputFields = nMetaListOutputFields;
		nRowImageSize = nMetaRowImageSize;
		nRowControlSize = nMetaListInputFields + 7;
		arrFixedInputFieldDefinitions = new ArrayList<EQFieldDefinition>(nFixedInputFields);
		arrFixedOutputFieldDefinitions = new ArrayList<EQFieldDefinition>(nFixedOutputFields);
		arrListInputFieldDefinitions = new ArrayList<EQFieldDefinition>(nListInputFields);
		arrListOutputFieldDefinitions = new ArrayList<EQFieldDefinition>(nListOutputFields);
		EQFieldDefinition fieldDefinition;
		String defFieldName;
		int defMaxSize;
		String defFieldReference;
		String defFieldDescription = "";
		boolean defUpperCaseOnly;
		boolean defDoubleByteSupported;
		char defDataType = 'A';
		int defDecimalPlaces = 0;
		int defDataSetOffset = 0;
		int nFieldsCount;
		nFieldsCount = 0;
		for (; nFieldsCount < nFixedInputFields; nFieldsCount++)
		{
			defFieldName = arrMetaFixedInputFieldNames[nFieldsCount];
			defMaxSize = arrMetaFixedInputFieldLengths[nFieldsCount];
			defDataSetOffset = arrMetaFixedInputFieldOffsets[nFieldsCount];
			defFieldReference = arrMetaFixedInputFieldReferences[nFieldsCount];
			defUpperCaseOnly = arrMetaFixedInputFieldUpperCases[nFieldsCount];
			defDoubleByteSupported = arrMetaFixedInputFieldDoubleBytes[nFieldsCount];
			fieldDefinition = new EQFieldDefinition('F', defFieldName, true, defMaxSize, defUpperCaseOnly, defDoubleByteSupported,
							defFieldDescription, defFieldReference, defDataType, defDecimalPlaces, true, false, defDataSetOffset);
			arrFixedInputFieldDefinitions.add(fieldDefinition);
		}
		nFieldsCount = 0;
		for (; nFieldsCount < nFixedOutputFields; nFieldsCount++)
		{
			defFieldName = arrMetaFixedOutputFieldNames[nFieldsCount];
			defMaxSize = arrMetaFixedOutputFieldLengths[nFieldsCount];
			defDataSetOffset = arrMetaFixedOutputFieldOffsets[nFieldsCount];
			defFieldReference = arrMetaFixedOutputFieldReferences[nFieldsCount];
			defUpperCaseOnly = false;
			defDoubleByteSupported = false;
			fieldDefinition = new EQFieldDefinition('F', defFieldName, false, defMaxSize, defUpperCaseOnly, defDoubleByteSupported,
							defFieldDescription, defFieldReference, defDataType, defDecimalPlaces, false, false, defDataSetOffset);
			arrFixedOutputFieldDefinitions.add(fieldDefinition);
		}
		nFieldsCount = 0;
		for (; nFieldsCount < nListInputFields; nFieldsCount++)
		{
			defFieldName = arrMetaListInputFieldNames[nFieldsCount];
			defMaxSize = arrMetaListInputFieldLengths[nFieldsCount];
			defDataSetOffset = arrMetaListInputFieldOffsets[nFieldsCount];
			defFieldReference = arrMetaListInputFieldReferences[nFieldsCount];
			defUpperCaseOnly = arrMetaListInputFieldUpperCases[nFieldsCount];
			defDoubleByteSupported = arrMetaListInputFieldDoubleBytes[nFieldsCount];
			fieldDefinition = new EQFieldDefinition('V', defFieldName, true, defMaxSize, defUpperCaseOnly, defDoubleByteSupported,
							defFieldDescription, defFieldReference, defDataType, defDecimalPlaces, false, false, defDataSetOffset);
			arrListInputFieldDefinitions.add(fieldDefinition);
		}
		nFieldsCount = 0;
		for (; nFieldsCount < nListOutputFields; nFieldsCount++)
		{
			defFieldName = arrMetaListOutputFieldNames[nFieldsCount];
			defMaxSize = arrMetaListOutputFieldLengths[nFieldsCount];
			defDataSetOffset = arrMetaListOutputFieldOffsets[nFieldsCount];
			defFieldReference = arrMetaListOutputFieldReferences[nFieldsCount];
			defUpperCaseOnly = false;
			defDoubleByteSupported = false;
			fieldDefinition = new EQFieldDefinition('V', defFieldName, false, defMaxSize, defUpperCaseOnly, defDoubleByteSupported,
							defFieldDescription, defFieldReference, defDataType, defDecimalPlaces, false, false, defDataSetOffset);
			arrListOutputFieldDefinitions.add(fieldDefinition);
		}
	}
}
