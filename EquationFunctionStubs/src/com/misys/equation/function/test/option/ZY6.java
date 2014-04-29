package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
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

// One repeating group + a table contributing to it

public class ZY6 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY6.java 17139 2013-08-29 16:00:56Z whittap1 $";
	String fldId1 = "A_";
	String repId1 = "RS1";
	String apiId1 = "AS1";

	public ZY6()
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
		ZY6 test = new ZY6();
		test.test("ZY6", "Account summary", IEquationStandardObject.FCT_FUL, true);
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ZY6", "Description 1 of ZY6");
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

		return fieldSetWrapper;
	}

	private void c8pfTable(InputFieldSet inputFieldSet, DisplayAttributesSet attributeSet, String prefix, String repeatingGroup)
					throws EQException
	{
		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

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
		repeatGroup.setKeys(prefix + "C8CCY");
		attributeSet.addItem(repeatGroup);

		// Currency
		inputField = FunctionToolbox.getInputField(prefix + "C8CCY", "Currency", "Currency", "A", "3", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Currency number
		inputField = FunctionToolbox.getInputField(prefix + "C8CCYN", "Currency number", "Currency number", "A", "3", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Edit code
		inputField = FunctionToolbox.getInputField(prefix + "C8CED", "Edit code", "Edit code", "A", "1", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Description
		inputField = FunctionToolbox.getInputField(prefix + "C8CUR", "Description", "Description", "A", "35", "");
		inputField.setRepeatingGroup(repeatingGroup);
		inputField.setUpperCase(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
	}

	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2 of ZY6", "Description 2 of ZY6");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Dummy date
		inputField = FunctionToolbox.getInputField("DTE", "Dummy date", "Dummy date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// Add account summary fields ===============================================================
		c8pfTable(inputFieldSet, attributeSet, fldId1, repId1);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiId1, "C810LF", "Currency", "", "C8PF", "",
						false, repId1, APIFieldSet.RPTGRP_LOAD_DEFAULT, "", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// C8 load mapping
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "C8CCY", "REC2", fldId1 + "C8CCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "C8CCYN", "REC2", fldId1 + "C8CCYN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "C8CED", "REC2", fldId1 + "C8CED", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "C8CUR", "REC2", fldId1 + "C8CUR", MappingToolbox.TYPE_PRIMITIVE);
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
				File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\ZY6.class");

				// Write to DB
				FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), serviceClass, null);

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
