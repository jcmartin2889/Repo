package com.misys.equation.function.test.option;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.LinkedFunction;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.LinkedFunctionsToolbox;
import com.misys.equation.function.tools.MappingToolbox;

// One repeating group + a table contributing to it

public class ZY3 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY3.java 17139 2013-08-29 16:00:56Z whittap1 $";
	String fldId1 = "A_";
	String repId1 = "RS1";
	String apiId1 = "AS1";

	String fldId2 = fldId1; // intentional to contribute to the same repeating group
	String repId2 = repId1; // intentional to contribute to the same repeating group
	String apiId2 = "AS2";

	String fldId3 = "B_";
	String repId3 = "RS3";
	String apiId3 = "AS3";

	public ZY3()
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
		ZY3 test = new ZY3();
		test.test("ZY3", "Account summary", IEquationStandardObject.FCT_FUL, true);
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ZY3", "Description 1 of ZY3");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Key group
		DisplayGroup keygroup = new DisplayGroup("+KEYS", "Key", "Key description");
		attributeSet.addItem(keygroup);

		// add all the fields in this section ---------------------------------------

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("CUS", "Customer", "Customer", "A", "6", "");
		inputField.setInitialValue("ATLANT");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField("CLC", "Customer", "Customer", "A", "3", "");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GFR70R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", true, ""));

		// Dummy date
		inputField = FunctionToolbox.getInputField("DTEKEY", "Dummy date", "Dummy date", "D", "7", "");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		return fieldSetWrapper;
	}

	private DisplayGroup accountSummary(InputFieldSet inputFieldSet, DisplayAttributesSet attributeSet, String prefix,
					String repeatingGroup) throws EQException
	{
		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Account number
		inputField = FunctionToolbox.getInputField(prefix + "HZAN", "Customer", "Customer", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Customer full name
		inputField = FunctionToolbox.getInputField(prefix + "HZCUN", "Customer full name", "Customer full name", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Customer type
		inputField = FunctionToolbox.getInputField(prefix + "HZCTP", "Customer type", "Customer type", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C4R54R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C4R54R", "", true, ""));

		// Start of repeating fields
		DisplayGroup repeatGroup = new DisplayGroup(DisplayGroup.GROUP_ID_PREFIX + repeatingGroup, "Account summary",
						"Account summary group");
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
		repeatGroup.setKeys(prefix + "HZAB" + ":" + prefix + "_HZAS");
		repeatGroup.setRepeatingRowsDisplayed(5);
		attributeSet.addItem(repeatGroup);

		// Account branch
		inputField = FunctionToolbox.getInputField(prefix + "HZAB", "Account branch", "Account branch", "A", "4", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField(prefix + "HZAS", "Account suffix", "Account suffix", "A", "3", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, ""));

		// Account short name
		inputField = FunctionToolbox.getInputField(prefix + "HZSHN", "Account short name", "Account short name", "A", "35", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("IDB");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Account type
		inputField = FunctionToolbox.getInputField(prefix + "HZACT", "Account type", "Account type", "A", "2", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Account description
		inputField = FunctionToolbox.getInputField(prefix + "C5ATD", "Account type description", "Account type description", "A",
						"35", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// return the group
		return repeatGroup;
	}

	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2 of ZY3", "Description 2 of ZY3");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		DisplayGroup repeatGroup;

		// Dummy date
		inputField = FunctionToolbox.getInputField("DTE", "Dummy date", "Dummy date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// Add account summary fields ===============================================================
		repeatGroup = accountSummary(inputFieldSet, attributeSet, fldId1, repId1);

		repeatGroup = accountSummary(inputFieldSet, attributeSet, fldId3, repId3);
		repeatGroup.setRepeatingRowsDisplayed(0);

		displayAttributes = (DisplayAttributes) repeatGroup.getDisplayItems().get(fldId3 + "HZAS");
		displayAttributes.setKeepWithPreviousInNewLine(true);

		displayAttributes = (DisplayAttributes) repeatGroup.getDisplayItems().get(fldId3 + "HZAB");
		displayAttributes.setLabelScriptRunTime("fn:concat('Accounts for ',fn:concat(" + fldId3 + "HZAN," + fldId3 + "HZCUN))");

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, apiId1, "AS", "H70D", "Account summary", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
		apiFieldSet.setRepeatingGroup(repId1);

		apiFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiId2, "C501LF", "Account type", "", "C5PF", "", false,
						repId2, APIFieldSet.RPTGRP_LOAD_LOOKUP, "C5ATP", fldId2 + "HZACT");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, apiId3, "AS", "H70D", "Account summary", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
		apiFieldSet.setRepeatingGroup(repId3);

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
		// Customer validate mapping
		fg.addValidateMappingToPV("PRIMARY", "CUS", "PRIMARY", "CLC", "GFR70R", "$GFCUS");
		fg.addValidateMappingToPV("PRIMARY", "CLC", "PRIMARY", "CLC", "GFR70R", "$GFCLC");
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCUS", "PRIMARY", "CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCLC", "PRIMARY", "CLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCUN", "PRIMARY", "CLC", MappingToolbox.TYPE_OUTPUT);

		// Account validation
		fg.addValidateMappingToPV("REC2", fldId1 + "HZAB", "REC2", fldId1 + "HZAS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("REC", fldId1 + "HZAN", "REC2", fldId1 + "HZAS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("REC2", fldId1 + "HZAS", "REC2", fldId1 + "HZAS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("REC2", fldId1 + "HZAS", "GWR76R", "$R76AB", "REC2", fldId1 + "HZAB",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", fldId1 + "HZAS", "GWR76R", "$R76AS", "REC2", fldId1 + "HZAS",
						MappingToolbox.TYPE_PRIMITIVE);

		// Account validation
		fg.addValidateMappingToPV("REC2", fldId3 + "HZAB", "REC2", fldId3 + "HZAS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("REC", fldId3 + "HZAN", "REC2", fldId3 + "HZAS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("REC2", fldId3 + "HZAS", "REC2", fldId3 + "HZAS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("REC2", fldId3 + "HZAS", "GWR76R", "$R76AB", "REC2", fldId3 + "HZAB",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", fldId3 + "HZAS", "GWR76R", "$R76AS", "REC2", fldId3 + "HZAS",
						MappingToolbox.TYPE_PRIMITIVE);

		// load mapping
		fg.addLoadMappingToLoad("PRIMARY", "CUS", "PRIMARY", apiId1, "HZCUS");
		fg.addLoadMappingToLoad("PRIMARY", "CLC", "PRIMARY", apiId1, "HZCLC");
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZAN", "REC2", fldId1 + "HZAN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZCUN", "REC2", fldId1 + "HZCUN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZCTP", "REC2", fldId1 + "HZCTP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZAB", "REC2", fldId1 + "HZAB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZAS", "REC2", fldId1 + "HZAS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZSHN", "REC2", fldId1 + "HZSHN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZACT", "REC2", fldId1 + "HZACT", MappingToolbox.TYPE_PRIMITIVE);

		// load mapping
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "C5ATD", "REC2", fldId2 + "C5ATD", MappingToolbox.TYPE_PRIMITIVE);

		// load mapping
		fg.addLoadMappingToLoad("PRIMARY", "CUS", "PRIMARY", apiId3, "HZCUS");
		fg.addLoadMappingToLoad("PRIMARY", "CLC", "PRIMARY", apiId3, "HZCLC");
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZAN", "REC2", fldId3 + "HZAN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZCUN", "REC2", fldId3 + "HZCUN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZCTP", "REC2", fldId3 + "HZCTP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZAB", "REC2", fldId3 + "HZAB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZAS", "REC2", fldId3 + "HZAS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZSHN", "REC2", fldId3 + "HZSHN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZACT", "REC2", fldId3 + "HZACT", MappingToolbox.TYPE_PRIMITIVE);

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

			// print the serialise version
			if (print)
			{
				// Write to DB
				FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), null, null);

				// print
				printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
			}

			return fg;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
