package com.misys.equation.function.test.option;

import java.io.File;
import java.util.ArrayList;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.LinkedFunction;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.LinkedFunctionsToolbox;
import com.misys.equation.function.tools.MappingToolbox;

// One table list with 2 more tables contributing to it

public class ZY4 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY4.java 17139 2013-08-29 16:00:56Z whittap1 $";
	String fldId1 = "A_";
	String repId1 = "RS1";
	String apiId1 = "AS1";

	String fldId2 = fldId1; // intentionally the same as above as this will add details into the same group
	String repId2 = repId1; // intentionally the same as above as this will add details into the same group
	String apiId2 = "AS2";

	String fldId3 = fldId1; // intentionally the same as above as this will add details into the same group
	String repId3 = repId1; // intentionally the same as above as this will add details into the same group
	String apiId3 = "AS3";

	String fldId4 = "B_";
	String repId4 = "RS4";
	String apiId4 = "AS4";

	String fldId5 = "C_";
	String repId5 = "RS5";
	String apiId5 = "AS5";

	boolean cache;

	public ZY4()
	{
		try
		{
			setUp();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		ZY4 test = new ZY4();
		test.test("ZY4", "Loan details", IEquationStandardObject.FCT_FUL, true);
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ZY4", "Description 1 of ZY4");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Key group
		DisplayGroup keygroup = new DisplayGroup("+KEYS", "Key", "Key description");
		attributeSet.addItem(keygroup);

		// add all the fields in this section ---------------------------------------

		// Deal branch
		inputField = FunctionToolbox.getInputField("BRNM", "Deal branch", "Deal branch", "A", "4", "");
		inputField.setInitialValue("HEAD");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Deal type
		inputField = FunctionToolbox.getInputField("DLP", "Deal type", "Deal type", "A", "3", "");
		inputField.setInitialValue("CR1");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Deal reference
		inputField = FunctionToolbox.getInputField("DLR", "Deal reference", "Deal reference", "A", "13", "");
		inputField.setInitialValue("CR1020A");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("OSR34R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OSR34R", "", true, ""));

		return fieldSetWrapper;
	}

	private DisplayGroup ompfTable(InputFieldSet inputFieldSet, DisplayAttributesSet attributeSet, String prefix,
					String repeatingGroup) throws EQException
	{
		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;

		// Start of repeating fields
		DisplayGroup repeatGroup = new DisplayGroup(DisplayGroup.GROUP_ID_PREFIX + repeatingGroup, "Deal movements",
						"Deal movements group");
		repeatGroup.setRepeatingGroup(repeatingGroup);
		repeatGroup.setEditMode(DisplayGroup.EDIT_MODE_IN_PLACE);
		repeatGroup.addLinkedFunction(new LinkedFunction("1", "ALZ", prefix + "OMMVT" + ":" + prefix + "OMDTE" + ":" + prefix
						+ "OMMVTS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("2", "HCX", prefix + "OMDTE"));
		repeatGroup.addLinkedFunction(new LinkedFunction("3", "ALY", "'XX1':" + prefix + "OMMVT" + ":" + prefix + "OMDTE" + ":"
						+ prefix + "OMMVTS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("4", "ASI", prefix + "OMMVT" + ":" + prefix + "OMDTE" + ":" + prefix
						+ "OMMVTS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("5", LinkedFunctionsToolbox.LINKED_EDIT, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("6", LinkedFunctionsToolbox.LINKED_DEL, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("7", LinkedFunctionsToolbox.LINKED_DSP, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("8", LinkedFunctionsToolbox.LINKED_ADD, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("9", LinkedFunctionsToolbox.LINKED_INS, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("J", "ZP4", "'0000'"));
		repeatGroup.setKeys(prefix + "OMDTE" + ":" + prefix + "OMMVT" + ":" + prefix + "OMMVTS" + ":" + prefix + "OMNS3");
		repeatGroup.setRepeatingRowsDisplayed(5);
		repeatGroup.setSummationBy(prefix + "OMNWP" + ":" + prefix + "OMNWR" + ":" + prefix + "LSAMTD" + ":" + prefix + "LSAMTP"
						+ ":" + prefix + "LSUP");
		repeatGroup.setBreakBy(prefix + "OMMVT" + ":" + prefix + "OMMVTS");
		repeatGroup.setTotalLabelPosition(prefix + "OMMVT");
		repeatGroup.setTotalLabelTextScript("'Grand Total'");
		repeatGroup.setTotalSubLabelTextScript("fn:concat('Sub total for ',GZBREAKBY)");
		repeatGroup.setAllowSorting(true);
		repeatGroup.setBreakByText("'Fixed label'");
		attributeSet.addItem(repeatGroup);

		// Movement date
		inputField = FunctionToolbox.getInputField(prefix + "OMDTE", "Date", "Date", "D", "7", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setEditCode(EqDataType.EDIT_DATE_DD_MM_YYYY);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Movement type
		inputField = FunctionToolbox.getInputField(prefix + "OMMVT", "Type", "Type", "A", "1", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Movement sub type
		inputField = FunctionToolbox.getInputField(prefix + "OMMVTS", "Sub Type", "Sub type", "A", "1", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Movement sequence number
		inputField = FunctionToolbox.getInputField(prefix + "OMNS3", "Sequence", "Sequence", "P", "2", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// We Pay or We Received flag
		inputField = FunctionToolbox.getInputField(prefix + "OMPRF", "Pay or Receive", "Pay or receive", "A", "1", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_CODE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Currency
		inputField = FunctionToolbox.getInputField(prefix + "OMCCY", "Currency", "Currency", "A", "3", "");
		inputField.setRepeatingGroup(repeatingGroup);
		inputField.setMandatory(InputField.MANDATORY_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, ""));
		pvFieldSet.setCache(true);

		// Amount we pay
		inputField = FunctionToolbox.getInputField(prefix + "OMNWP", "Amount we pay", "Amount we pay", "P", "15", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setEditCode(EqDataType.EDIT_AMOUNT_DEFAULT);
		displayAttributes.setEditCodeParameter(prefix + "OMCCY");
		displayAttributes.setEditCodeParameterStatus(DisplayAttributes.EDIT_PARAMETER_SCRIPT);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Amount we received
		inputField = FunctionToolbox.getInputField(prefix + "OMNWR", "Amount we receive", "Amount we receive", "P", "15", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setEditCode(EqDataType.EDIT_AMOUNT_DEFAULT);
		displayAttributes.setEditCodeParameter("'BHD'");
		displayAttributes.setEditCodeParameterStatus(DisplayAttributes.EDIT_PARAMETER_SCRIPT);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setShowDescriptionAsValue(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Total amount scheduled
		inputField = FunctionToolbox.getInputField(prefix + "LSAMTD", "Total amount scheduled", "Total amount scheduled", "P",
						"15", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setEditCode(EqDataType.EDIT_AMOUNT_DEFAULT);
		displayAttributes.setEditCodeParameter("'5'");
		displayAttributes.setEditCodeParameterStatus(DisplayAttributes.EDIT_PARAMETER_SCRIPT);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		// Total amount paid
		inputField = FunctionToolbox.getInputField(prefix + "LSAMTP", "Total amount paid", "Total amoutn paid", "P", "15", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setEditCode(EqDataType.EDIT_AMOUNT_DEFAULT);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setKeepWithPreviousInNewLine(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Status code
		inputField = FunctionToolbox.getInputField(prefix + "LSLSC", "Status code", "Status code", "A", "2", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Unpaid penalty
		inputField = FunctionToolbox.getInputField(prefix + "LSUP", "Unpaid penalty", "Unpaid penalty", "P", "15", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Number of decimal places
		inputField = FunctionToolbox.getInputField(prefix + "C8CED", "Decimal places", "Decimal places", "A", "1", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		return repeatGroup;
	}
	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2 of ZY4", "Description 2 of ZY4");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		DisplayGroup displayGroup;

		// Dummy date
		inputField = FunctionToolbox.getInputField("DTE", "Dummy date", "Dummy date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// Add account summary fields ===============================================================
		displayGroup = ompfTable(inputFieldSet, attributeSet, fldId1, repId1);
		displayGroup.setTableDataStyleExpression("'mytabledata'");
		displayGroup.setTableHeaderStyleExpression("'mytableheader'");
		displayGroup.setTableFooterStyleExpression("'mytablefooter'");
		displayGroup.setRowStyleEvenExpression("'myroweven1'");
		displayGroup.setRowStyleOddExpression("'myrowodd1'");
		displayGroup.setGrandTotalStyleExpression("'mygrandtotal'");
		displayGroup.setPagingSize(10);

		// ignore options depending on the content of the record
		displayGroup.getLinkedFunctions().get(0).setIgnoreLinkFuncExpression(fldId1 + "OMMVT == 'P'");
		displayGroup.getLinkedFunctions().get(1).setIgnoreLinkFuncExpression(fldId1 + "OMMVT == 'I'");
		displayGroup.getLinkedFunctions().get(2).setIgnoreLinkFuncExpression(fldId1 + "OMMVTS == 'U'");
		displayGroup.getLinkedFunctions().get(3).setIgnoreLinkFuncExpression(fldId1 + "OMMVTS == 'C'");
		displayGroup.getLinkedFunctions().get(4).setIgnoreLinkFuncExpression(fldId1 + "OMMVTS == 'R'");

		displayGroup = ompfTable(inputFieldSet, attributeSet, fldId4, repId4);
		displayGroup.setRepeatingRowsDisplayed(10);
		displayGroup.setBreakBy("");
		displayGroup.setTotalLabelPosition(fldId4 + "OMDTE");
		displayGroup.setLinkedFunctions(new ArrayList<LinkedFunction>());
		displayGroup.setEditMode(DisplayGroup.EDIT_MODE_DEFAULT);
		displayGroup.setRowStyleEvenExpression("'myroweven'");
		displayGroup.setRowStyleOddExpression("'myrowodd'");
		displayGroup.setPagingSize(5);
		((DisplayAttributes) displayGroup.getDisplayItems().get(fldId4 + "LSAMTP")).setKeepWithPreviousInNewLine(false);

		// Add account summary fields ===============================================================
		displayGroup = ompfTable(inputFieldSet, attributeSet, fldId5, repId5);
		displayGroup.setBreakBy(fldId5 + "OMMVT");
		displayGroup.setLinkedFunctions(new ArrayList<LinkedFunction>());
		displayGroup.addLinkedFunction(new LinkedFunction("1", "ALZ", fldId5 + "OMMVT" + ":" + fldId5 + "OMDTE" + ":" + fldId5
						+ "OMMVTS"));
		displayGroup.setSummationBy("");
		displayGroup.setRowStyleEvenExpression("'myroweven'");
		displayGroup.setAllowSorting(false);
		displayGroup.setBreakByText("DTE");

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiId1, "OM10LF", "Deal movements",
						"OMBRNM:OMDLP:OMDLR", "OMPF", "", false, repId1, APIFieldSet.RPTGRP_LOAD_DEFAULT, "", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		apiFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiId2, "LS10LF", "Deal movements", "LSBRNM:LSDLP:LSDLR",
						"LSPF", "", false, repId2, APIFieldSet.RPTGRP_LOAD_CHAIN, "LSDTE:LSMVT:LSMVTS:LSNS3", fldId2 + "OMDTE"
										+ ":" + fldId2 + "OMMVT" + ":" + fldId2 + "OMMVTS" + ":" + fldId2 + "OMNS3");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		apiFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiId3, "C801LF", "Currency", "", "C8PF", "", false, repId3,
						APIFieldSet.RPTGRP_LOAD_LOOKUP, "C8CCY", fldId3 + "OMCCY");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		apiFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiId4, "OM10LF", "Deal movements", "OMBRNM:OMDLP:OMDLR",
						"OMPF", "", false, repId4, APIFieldSet.RPTGRP_LOAD_DEFAULT, "", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		apiFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiId5, "OM10LF", "Deal movements", "OMBRNM:OMDLP:OMDLR",
						"OMPF", "", false, repId5, APIFieldSet.RPTGRP_LOAD_DEFAULT, "", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = fg.getFunction().addReservedFieldSet(Function.CRM_FIELDSET, "Description of CRM");
		apiFieldSet.getAPIField("HHAB").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("HHAB").setUpdateScript("'9132'");
		apiFieldSet.getAPIField("HHAN").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("HHAN").setUpdateScript("'234567'");
		apiFieldSet.getAPIField("HHAS").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("HHAS").setUpdateScript("'001'");
		apiFieldSet.getAPIField("HHCY1").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("HHCY1").setUpdateScript("'GBP'");
		apiFieldSet.getAPIField("HHAMC").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("HHAMC").setUpdateScript("'1000000'");
		apiFieldSet.getAPIField("HHFCT").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("HHFCT").setUpdateScript("'A'");

		apiFieldSet = fg.getFunction().addReservedFieldSet(Function.EFC_FIELDSET, "Description of EFC");
		apiFieldSet.getAPIField("EDAB").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("EDAB").setUpdateScript("'9132'");
		apiFieldSet.getAPIField("EDAN").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("EDAN").setUpdateScript("'234567'");
		apiFieldSet.getAPIField("EDAS").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("EDAS").setUpdateScript("'001'");
		apiFieldSet.getAPIField("EDAMT").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("EDAMT").setUpdateScript("'1000000'");
		apiFieldSet.getAPIField("EDCCY").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("EDCCY").setUpdateScript("'GBP'");
		apiFieldSet.getAPIField("ETREF").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("ETREF").setUpdateScript("'TRANREF'");
		apiFieldSet.getAPIField("ETDTE").setUpdateAssignment(Field.ASSIGNMENT_SCRIPT);
		apiFieldSet.getAPIField("ETDTE").setUpdateScript("HZ_DTE");

		fg.getFunction().addReservedFieldSet(Function.GY_FIELDSET, "Description of GY");

	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// Deal mapping
		fg.addValidateMappingToPV("PRIMARY", "DLP", "PRIMARY", "DLR", "OSR34R", "$DLP");
		fg.addValidateMappingToPV("PRIMARY", "DLR", "PRIMARY", "DLR", "OSR34R", "$DLR");
		fg.addValidateMappingToPV("PRIMARY", "BRNM", "PRIMARY", "DLR", "OSR34R", "$BRNM");
		fg.addValidateMappingFromPV("PRIMARY", "DLR", "OSR34R", "$DLP", "PRIMARY", "DLP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "DLR", "OSR34R", "$BRNM", "PRIMARY", "BRNM", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "DLR", "OSR34R", "$DLR", "PRIMARY", "DLR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "DLR", "OSR34R", "$BRNM", "PRIMARY", "DLR", MappingToolbox.TYPE_OUTPUT);

		// OM load mapping
		fg.addLoadMappingToLoad("PRIMARY", "DLP", "PRIMARY", apiId1, "OMDLP");
		fg.addLoadMappingToLoad("PRIMARY", "DLR", "PRIMARY", apiId1, "OMDLR");
		fg.addLoadMappingToLoad("PRIMARY", "BRNM", "PRIMARY", apiId1, "OMBRNM");
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMDTE", "REC2", fldId1 + "OMDTE", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMMVT", "REC2", fldId1 + "OMMVT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMMVTS", "REC2", fldId1 + "OMMVTS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMNS3", "REC2", fldId1 + "OMNS3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMPRF", "REC2", fldId1 + "OMPRF", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMCCY", "REC2", fldId1 + "OMCCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMNWP", "REC2", fldId1 + "OMNWP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMNWR", "REC2", fldId1 + "OMNWR", MappingToolbox.TYPE_PRIMITIVE);

		// LS load mapping
		fg.addLoadMappingToLoad("PRIMARY", "DLP", "PRIMARY", apiId2, "LSDLP");
		fg.addLoadMappingToLoad("PRIMARY", "DLR", "PRIMARY", apiId2, "LSDLR");
		fg.addLoadMappingToLoad("PRIMARY", "BRNM", "PRIMARY", apiId2, "LSBRNM");
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "LSAMTD", "REC2", fldId2 + "LSAMTD", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "LSAMTP", "REC2", fldId2 + "LSAMTP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "LSLSC", "REC2", fldId2 + "LSLSC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "LSUP", "REC2", fldId2 + "LSUP", MappingToolbox.TYPE_PRIMITIVE);

		// C8 load mapping
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "C8CED", "REC2", fldId3 + "C8CED", MappingToolbox.TYPE_PRIMITIVE);

		// OM load mapping
		fg.addLoadMappingToLoad("PRIMARY", "DLP", "PRIMARY", apiId4, "OMDLP");
		fg.addLoadMappingToLoad("PRIMARY", "DLR", "PRIMARY", apiId4, "OMDLR");
		fg.addLoadMappingToLoad("PRIMARY", "BRNM", "PRIMARY", apiId4, "OMBRNM");
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMDTE", "REC2", fldId4 + "OMDTE", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMMVT", "REC2", fldId4 + "OMMVT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMMVTS", "REC2", fldId4 + "OMMVTS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMNS3", "REC2", fldId4 + "OMNS3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMPRF", "REC2", fldId4 + "OMPRF", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMCCY", "REC2", fldId4 + "OMCCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMNWP", "REC2", fldId4 + "OMNWP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId4, "OMNWR", "REC2", fldId4 + "OMNWR", MappingToolbox.TYPE_PRIMITIVE);

		// OM load mapping
		fg.addLoadMappingToLoad("PRIMARY", "DLP", "PRIMARY", apiId5, "OMDLP");
		fg.addLoadMappingToLoad("PRIMARY", "DLR", "PRIMARY", apiId5, "OMDLR");
		fg.addLoadMappingToLoad("PRIMARY", "BRNM", "PRIMARY", apiId5, "OMBRNM");
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMDTE", "REC2", fldId5 + "OMDTE", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMMVT", "REC2", fldId5 + "OMMVT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMMVTS", "REC2", fldId5 + "OMMVTS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMNS3", "REC2", fldId5 + "OMNS3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMPRF", "REC2", fldId5 + "OMPRF", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMCCY", "REC2", fldId5 + "OMCCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMNWP", "REC2", fldId5 + "OMNWP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId5, "OMNWR", "REC2", fldId5 + "OMNWR", MappingToolbox.TYPE_PRIMITIVE);

	}

	public FunctionGenerator test(String id, String desc, String mode, boolean print)
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator(id, desc, "This is the description of " + desc,
							"com.misys.equation.screens", "com.misys.equation.screens.layout");
			fg.getFunction().setAllowedAdd(true);
			fg.getFunction().setAllowedMaint(true);
			fg.getFunction().setAllowedDel(true);
			addRecord1(fg);
			addRecord2(fg);
			addLoadAPI(fg);
			addUpdateAPI(fg);
			addMappings(fg);

			File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\ZY4.class");
			File layoutClass = new File(workSpace + "com\\misys\\equation\\screens\\layout\\ZY4.class");

			// Write to DB
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), serviceClass, layoutClass);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);

			return fg;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
