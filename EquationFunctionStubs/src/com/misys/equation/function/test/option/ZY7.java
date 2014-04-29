package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.access.EquationStandardQueryList;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.LinkedFunction;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.LinkedFunctionsToolbox;
import com.misys.equation.function.tools.MappingToolbox;

// One table list with 2 more tables contributing to it

public class ZY7 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY7.java 17139 2013-08-29 16:00:56Z whittap1 $";
	String fldId1 = "A_";
	String repId1 = "RS1";
	String apiId1 = "AS1";

	String fldId2 = fldId1; // intentionally the same as above as this will add details into the same group
	String repId2 = repId1; // intentionally the same as above as this will add details into the same group
	String apiId2 = "AS2";

	String fldId3 = fldId1; // intentionally the same as above as this will add details into the same group
	String repId3 = repId1; // intentionally the same as above as this will add details into the same group
	String apiId3 = "AS3";

	public ZY7()
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
		ZY7 test = new ZY7();
		test.test("ZY7", "Loan details", IEquationStandardObject.FCT_FUL, true);
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ZY7", "Description 1 of ZY7");
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
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OSR34R", "", true, "N"));

		return fieldSetWrapper;
	}

	private DisplayGroup ompfTable(InputFieldSet inputFieldSet, DisplayAttributesSet attributeSet, String prefix,
					String repeatingGroup) throws EQException
	{
		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Start of repeating fields
		DisplayGroup repeatGroup = new DisplayGroup(DisplayGroup.GROUP_ID_PREFIX + repeatingGroup, "Deal movements",
						"Deal movements group");
		repeatGroup.setRepeatingGroup(repeatingGroup);
		repeatGroup.setEditMode(DisplayGroup.EDIT_MODE_IN_PLACE);
		repeatGroup.addLinkedFunction(new LinkedFunction("1", "ALZ", "A_HZAB:A_HZAN:A_HZAS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("2", "HCX", "A_HZAB:A_HZAN:A_HZAS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("3", "ALY", "'XX1':A_HZAB:A_HZAN:A_HZAS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("4", "ASI", "A_HZAB:A_HZAN:A_HZAS"));
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
		repeatGroup.setTotalSubLabelTextScript("'Sub total for'");
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
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Currency
		inputField = FunctionToolbox.getInputField(prefix + "OMCCY", "Currency", "Currency", "A", "3", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, ""));

		// Amount we pay
		inputField = FunctionToolbox.getInputField(prefix + "OMNWP", "Amount we pay", "Amount we pay", "P", "15", "0");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setEditCode(EqDataType.EDIT_AMOUNT_DEFAULT);
		displayAttributes.setEditCodeParameter(prefix + "OMCCY");
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

		// Currency name
		inputField = FunctionToolbox.getInputField(prefix + "C8CUR", "Currency name", "Currency name", "A", "35", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Number of decimal places
		inputField = FunctionToolbox.getInputField(prefix + "C8CED", "Decimal places", "Decimal places", "A", "1", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		return repeatGroup;
	}

	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2 of ZY7", "Description 2 of ZY7");
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
		ompfTable(inputFieldSet, attributeSet, fldId1, repId1);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		String sql = "select * from ompf where ombrnm=? and omdlp=? and omdlr=?";
		APIFieldSet apiFieldSet = FunctionToolbox.getSqlFieldSet(session, apiId1, "Query on OMPF", sql, "", false, repId1,
						APIFieldSet.RPTGRP_LOAD_DEFAULT, "", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		sql = "select * from c8pf where c8ccy=?";
		apiFieldSet = FunctionToolbox.getSqlFieldSet(session, apiId3, "Query on C8PF", sql, "", false, repId3,
						APIFieldSet.RPTGRP_LOAD_SQL_AGGREGATE, "", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
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
		fg.addLoadMappingToLoad("PRIMARY", "DLP", "PRIMARY", apiId1, EquationStandardQueryList.PARM_PREFIX + "2");
		fg.addLoadMappingToLoad("PRIMARY", "DLR", "PRIMARY", apiId1, EquationStandardQueryList.PARM_PREFIX + "3");
		fg.addLoadMappingToLoad("PRIMARY", "BRNM", "PRIMARY", apiId1, EquationStandardQueryList.PARM_PREFIX + "1");
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMDTE", "REC2", fldId1 + "OMDTE", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMMVT", "REC2", fldId1 + "OMMVT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMMVTS", "REC2", fldId1 + "OMMVTS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMNS3", "REC2", fldId1 + "OMNS3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMPRF", "REC2", fldId1 + "OMPRF", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMCCY", "REC2", fldId1 + "OMCCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMNWP", "REC2", fldId1 + "OMNWP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "OMNWR", "REC2", fldId1 + "OMNWR", MappingToolbox.TYPE_PRIMITIVE);

		// C8 load mapping
		fg.addLoadMappingToLoad("REC2", fldId1 + "OMCCY", "PRIMARY", apiId3, EquationStandardQueryList.PARM_PREFIX + "1");
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "C8CUR", "REC2", fldId3 + "C8CUR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "C8CED", "REC2", fldId3 + "C8CED", MappingToolbox.TYPE_PRIMITIVE);
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

			File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\ZY7.class");
			File layoutClass = new File(workSpace + "com\\misys\\equation\\screens\\layout\\ZY7.class");

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
