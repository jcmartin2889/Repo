package com.misys.equation.function.test.option;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
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

// Multiple repeating groups

public class ZY2 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZY2.java 17139 2013-08-29 16:00:56Z whittap1 $";
	String fldId1 = "A_";
	String repId1 = "RS1";
	String apiId1 = "AS1";

	String fldId2 = "B_";
	String repId2 = "RS2";
	String apiId2 = "AS2";

	String fldId3 = "C_";
	String repId3 = "RH1";
	String apiId3 = "TH1";

	public ZY2()
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
		ZY2 test = new ZY2();
		test.test("ZY2", "Account summary", IEquationStandardObject.FCT_FUL, true);
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ZY2", "Description 1 of ZY2");
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

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("CUS2", "Customer", "Customer", "A", "6", "");
		inputField.setInitialValue("ABRAHA");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField("CLC2", "Customer", "Customer", "A", "3", "");
		inputField.setInitialValue("ISA");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GFR70R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", true, ""));

		// Account branch
		inputField = FunctionToolbox.getInputField("AB", "Account branch x", "This is the account branch", "A", "4", "");
		inputField.setInitialValue("0543");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("AN", "Account number", "This is the account number", "A", "6", "");
		inputField.setInitialValue("000001");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("AS", "Account suffix", "This is the account suffix", "A", "3", "");
		inputField.setInitialValue("001");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLinkedFuncId("ALY");
		displayAttributes.setLinkedFuncContext("'XXX':AB:AN:AS");
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		PVFieldSet pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "",
						true, "N"));
		pvFieldSet.setSecurity(EquationPVMetaDataHelper.SEC_UPD);

		return fieldSetWrapper;
	}

	private void accountSummary(InputFieldSet inputFieldSet, DisplayAttributesSet attributeSet, String prefix, String repeatingGroup)
					throws EQException
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
		repeatGroup.setKeys(prefix + "HZAB" + ":" + prefix + "HZAS");
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
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, ""));

		// Account short name
		inputField = FunctionToolbox.getInputField(prefix + "HZSHN", "Account short name", "Account short name", "A", "35", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Account type
		inputField = FunctionToolbox.getInputField(prefix + "HZACT", "Account type", "Account type", "A", "2", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C5R55R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C5R55R", "", true, ""));

		// Account currency
		inputField = FunctionToolbox.getInputField(prefix + "HZCCY", "Account currency", "Account currency", "A", "3", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, ""));
	}

	private void transactionHistory(InputFieldSet inputFieldSet, DisplayAttributesSet attributeSet, String prefix,
					String repeatingGroup) throws EQException
	{
		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Account number
		inputField = FunctionToolbox.getInputField(prefix + "HZSHN", "Account short name", "Account short name", "A", "35", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField(prefix + "HZCUS", "Customer", "Customer", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField(prefix + "HZCLC", "Customer", "Customer", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GFR70R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", true, ""));

		// Customer full name
		inputField = FunctionToolbox.getInputField(prefix + "HZCUN", "Customer full name", "Customer full name", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account type
		inputField = FunctionToolbox.getInputField(prefix + "HZACT", "Account type", "Account type", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C5R55R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C5R55R", "", true, ""));

		// Account currency
		inputField = FunctionToolbox.getInputField(prefix + "HZCCY", "Account currency", "Account currency", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, ""));

		// Start of repeating fields
		DisplayGroup repeatGroup = new DisplayGroup(DisplayGroup.GROUP_ID_PREFIX + repeatingGroup, "Transaction summary",
						"Transaction summary group");
		repeatGroup.setRepeatingGroup(repeatingGroup);
		repeatGroup.addLinkedFunction(new LinkedFunction("2", "HCX", "A_HZAB:A_HZAN:A_HZAS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("4", "ASI", "A_HZAB:A_HZAN:A_HZAS"));
		repeatGroup.addLinkedFunction(new LinkedFunction("5", LinkedFunctionsToolbox.LINKED_EDIT, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("6", LinkedFunctionsToolbox.LINKED_DEL, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("7", LinkedFunctionsToolbox.LINKED_DSP, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("8", LinkedFunctionsToolbox.LINKED_ADD, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("9", LinkedFunctionsToolbox.LINKED_INS, ""));
		repeatGroup.addLinkedFunction(new LinkedFunction("J", "ZP4", "'0000'"));
		repeatGroup.setKeys("C_HZPOD:C_HZBRNM:C_HZPBR:C_HZPSQ7");
		repeatGroup.setBreakBy(prefix + "HZBRNM" + ":" + prefix + "HZTCD");
		repeatGroup.setSummationBy(prefix + "HZAMAP");
		attributeSet.addItem(repeatGroup);

		// Posting date
		inputField = FunctionToolbox.getInputField(prefix + "HZPOD", "Posting date", "Posting date", "D", "7", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Branch
		inputField = FunctionToolbox.getInputField(prefix + "HZBRNM", "Input branch", "Input branch", "A", "4", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("CAR73R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CAR73R", "", true, ""));

		// Posting group
		inputField = FunctionToolbox.getInputField(prefix + "HZPBR", "Posting group", "Posting group", "A", "5", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Sequence number
		inputField = FunctionToolbox.getInputField(prefix + "HZPSQ7", "Posting sequence", "Posting sequence", "S", "7", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Transaction code
		inputField = FunctionToolbox.getInputField(prefix + "HZTCD", "Transaction code", "Transaction code", "A", "3", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("CTR56R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CTR56R", "", true, ""));

		// Transaction name
		inputField = FunctionToolbox.getInputField(prefix + "HZTCM", "Transaction code mnemonic", "Transaction code mnemonic", "A",
						"35", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Transaction amount
		inputField = FunctionToolbox.getInputField(prefix + "HZAMAP", "Transaction amount", "Transaction amount", "P", "15", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Transaction date
		inputField = FunctionToolbox.getInputField(prefix + "HZVFR", "Transaction date", "Transaction date", "D", "7", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

		// Branch
		inputField = FunctionToolbox.getInputField(prefix + "HZBRND", "Branch", "Branch", "A", "4", "");
		inputField.setRepeatingGroup(repeatingGroup);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		repeatGroup.addItem(displayAttributes);

	}

	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2 of ZY2", "Description 2 of ZY2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Add account summary fields ===============================================================
		accountSummary(inputFieldSet, attributeSet, fldId1, repId1);
		transactionHistory(inputFieldSet, attributeSet, fldId3, repId3);

		return fieldSetWrapper;
	}

	private DisplayFieldSetWrapper addRecord3(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC3", "Record 3 of ZY2", "Description 3 of ZY2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Add account summary fields ===============================================================
		accountSummary(inputFieldSet, attributeSet, fldId2, repId2);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, apiId1, "AS", "H70D", "Account summary", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
		apiFieldSet.setRepeatingGroup(repId1);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, apiId2, "AS", "H70D", "Account summary", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
		apiFieldSet.setRepeatingGroup(repId2);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, apiId3, "TH", "H71D", "Transaction history", "");
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
		apiFieldSet.setRepeatingGroup(repId3);
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// Customer validate mapping
		fg.addValidateMappingToPV("PRIMARY", "CUS", "PRIMARY", "CLC", "GFR70R", "$GFCUS");
		fg.addValidateMappingToPV("PRIMARY", "CLC", "PRIMARY", "CLC", "GFR70R", "$GFCLC");
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCUS", "PRIMARY", "CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCLC", "PRIMARY", "CLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCUN", "PRIMARY", "CLC", MappingToolbox.TYPE_OUTPUT);

		// Customer validate mapping
		fg.addValidateMappingToPV("PRIMARY", "CUS2", "PRIMARY", "CLC2", "GFR70R", "$GFCUS");
		fg.addValidateMappingToPV("PRIMARY", "CLC2", "PRIMARY", "CLC2", "GFR70R", "$GFCLC");
		fg.addValidateMappingFromPV("PRIMARY", "CLC2", "GFR70R", "$GFCUS", "PRIMARY", "CUS2", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC2", "GFR70R", "$GFCLC", "PRIMARY", "CLC2", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC2", "GFR70R", "$GFCUN", "PRIMARY", "CLC2", MappingToolbox.TYPE_OUTPUT);

		// Account validation
		fg.addValidateMappingToPV("PRIMARY", "AB", "PRIMARY", "AS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "AN", "PRIMARY", "AS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "AS", "PRIMARY", "AS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AB", "PRIMARY", "AB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AN", "PRIMARY", "AN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AS", "PRIMARY", "AS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AS", "PRIMARY", "AS", MappingToolbox.TYPE_OUTPUT);

		// Repeat group 1
		// Account validation
		fg.addValidateMappingToPV("REC2", fldId1 + "HZAB", "REC2", fldId1 + "HZAS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("REC", fldId1 + "HZAN", "REC2", fldId1 + "HZAS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("REC2", fldId1 + "HZAS", "REC2", fldId1 + "HZAS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("REC2", fldId1 + "HZAS", "GWR76R", "$R76AB", "REC2", fldId1 + "HZAB",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", fldId1 + "HZAS", "GWR76R", "$R76AS", "REC2", fldId1 + "HZAS",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", fldId1 + "HZAS", "GWR76R", "$R76SN", "REC2", fldId1 + "HZAS",
						MappingToolbox.TYPE_OUTPUT);

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
		fg.addLoadMappingFromLoad("PRIMARY", apiId1, "HZCCY", "REC2", fldId1 + "HZCCY", MappingToolbox.TYPE_PRIMITIVE);

		// Repeat group 2
		// Account validation
		fg.addValidateMappingToPV("REC3", fldId2 + "HZAB", "REC3", fldId2 + "HZAS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("REC", fldId2 + "HZAN", "REC3", fldId2 + "HZAS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("REC3", fldId2 + "HZAS", "REC3", fldId2 + "HZAS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("REC3", fldId2 + "HZAS", "GWR76R", "$R76AB", "REC3", fldId2 + "HZAB",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC3", fldId2 + "HZAS", "GWR76R", "$R76AS", "REC3", fldId2 + "HZAS",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC3", fldId2 + "HZAS", "GWR76R", "$R76SN", "REC3", fldId2 + "HZAS",
						MappingToolbox.TYPE_OUTPUT);

		// load mapping
		fg.addLoadMappingToLoad("PRIMARY", "CUS2", "PRIMARY", apiId2, "HZCUS");
		fg.addLoadMappingToLoad("PRIMARY", "CLC2", "PRIMARY", apiId2, "HZCLC");
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZAN", "REC3", fldId2 + "HZAN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZCUN", "REC3", fldId2 + "HZCUN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZCTP", "REC3", fldId2 + "HZCTP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZAB", "REC3", fldId2 + "HZAB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZAS", "REC3", fldId2 + "HZAS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZSHN", "REC3", fldId2 + "HZSHN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZACT", "REC3", fldId2 + "HZACT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId2, "HZCCY", "REC3", fldId2 + "HZCCY", MappingToolbox.TYPE_PRIMITIVE);

		// Repeat group 3
		// load mapping
		fg.addLoadMappingToLoad("PRIMARY", "AB", "PRIMARY", apiId3, "HZAB");
		fg.addLoadMappingToLoad("PRIMARY", "AN", "PRIMARY", apiId3, "HZAN");
		fg.addLoadMappingToLoad("PRIMARY", "AS", "PRIMARY", apiId3, "HZAS");
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZSHN", "REC2", fldId3 + "HZSHN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZCUS", "REC2", fldId3 + "HZCUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZCLC", "REC2", fldId3 + "HZCLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZCUN", "REC2", fldId3 + "HZCUN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZACT", "REC2", fldId3 + "HZACT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZCCY", "REC2", fldId3 + "HZCCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZTCD", "REC2", fldId3 + "HZTCD", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZTCM", "REC2", fldId3 + "HZTCM", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZAMAP", "REC2", fldId3 + "HZAMAP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZPOD", "REC2", fldId3 + "HZPOD", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZBRNM", "REC2", fldId3 + "HZBRNM", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZPBR", "REC2", fldId3 + "HZPBR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZPSQ7", "REC2", fldId3 + "HZPSQ7", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZVFR", "REC2", fldId3 + "HZVFR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZBRND", "REC2", fldId3 + "HZBRND", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", apiId3, "HZDRF", "REC2", fldId3 + "HZDRF", MappingToolbox.TYPE_PRIMITIVE);

		// context mapping
		fg.addContextMapping("HLD", "$GRP");
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
			addRecord3(fg);
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
