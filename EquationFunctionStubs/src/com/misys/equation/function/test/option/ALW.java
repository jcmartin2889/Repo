package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

// THIS IS A COPY OF ALY BUT ENQUIRY

public class ALW extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ALW.java 17139 2013-08-29 16:00:56Z whittap1 $";

	public ALW()
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
		ALW test = new ALW();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of HCX2", "Description 1 of HCX2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Key group
		DisplayGroup keygroup = new DisplayGroup("+KEYS", "Key", "Key description");
		attributeSet.addItem(keygroup);

		// add all the fields in this section ---------------------------------------

		// Hold code
		inputField = FunctionToolbox.getInputField("HLD", "Hold code", "This is the hold code", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("JVR01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "JVR01R", "", true, ""));

		// Account branch
		inputField = FunctionToolbox.getInputField("AB", "Account branch", "This is the account branch", "A", "4", "");
		inputField.setInitialValue("0132");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("AN", "Account number", "This is the account number", "A", "6", "");
		inputField.setInitialValue("891250");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("AS", "Account suffix", "This is the account suffix", "A", "3", "");
		inputField.setInitialValue("380");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));

		// Deal branch
		inputField = FunctionToolbox.getInputField("BRNM", "Deal branch", "This is the deal branch", "A", "4", "");
		inputField.setInitialValue("TEST");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Deal number
		inputField = FunctionToolbox.getInputField("DLP", "Deal type", "This is the deal type", "A", "3", "");
		inputField.setInitialValue("TST");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// Deal suffix
		inputField = FunctionToolbox.getInputField("DLR", "Deal reference", "This is the deal reference", "A", "13", "");
		inputField.setInitialValue("TST-001");
		inputField.setKey(true);
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("OSR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OSR33R", "", true, ""));

		DisplayGroup subGroup = new DisplayGroup("EQN", "Label of EQN", "Description of EQN");
		keygroup.addItem(subGroup);

		// Account branch with toggle
		inputField = FunctionToolbox.getInputField("TAB3", "Account branch 3", "This is the account branch", "A", "4", "");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account number with toggle
		inputField = FunctionToolbox.getInputField("TAN3", "Account number 3", "This is the account number", "A", "6", "");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account suffix with toggle
		inputField = FunctionToolbox.getInputField("TAS3", "Account suffix 3", "This is the account suffix", "A", "3", "");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));

		subGroup = new DisplayGroup("ALT", "Label of ALT", "ALT");
		keygroup.addItem(subGroup);

		// External account number with toggle
		inputField = FunctionToolbox.getInputField("TEAN3", "External account number 3", "Description of EAN", "A", "20", "");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));

		// Date key
		inputField = FunctionToolbox.getInputField("DTEKEY", "Date key", "This is the date key description", "D", "7", "");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// ------------ NON-KEYS !!

		// Hold code description
		inputField = FunctionToolbox.getInputField("DSC", "Hold code description", "This is the hold code description", "A", "35",
						"");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, ""));

		// Account short name
		inputField = FunctionToolbox.getInputField("SHN", "Account short name", "This is the SHN", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account balance
		inputField = FunctionToolbox.getInputField("BAL", "Balance", "This is the balance", "P", "15", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Deal customer
		inputField = FunctionToolbox.getInputField("CUS", "Customer", "This is the customer", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Deal customer location
		inputField = FunctionToolbox.getInputField("CLC", "location", "This is the customer", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Deal originating system
		inputField = FunctionToolbox.getInputField("DOS", "Originating system", "This is the originating system", "A", "16", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		DisplayGroup group = new DisplayGroup("TA1", "Label of TA1", "Description of TA1");
		attributeSet.addItem(group);

		subGroup = new DisplayGroup("EQN", "Label of EQN", "Description of EQN");
		group.addItem(subGroup);

		// Account branch with toggle
		inputField = FunctionToolbox.getInputField("TAB1", "Account branch 1", "This is the account branch", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setOnBlurEventScript("var value = getInputFieldValue(fieldId); " + "if (value.trim() == 'Y') " + "{ "
						+ "	setProtectField('CODCCY'); " + "	setProtectField('CODVFR'); " + "}");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account number with toggle
		inputField = FunctionToolbox.getInputField("TAN1", "Account number 1", "This is the account number", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);

		// Account suffix with toggle
		inputField = FunctionToolbox.getInputField("TAS1", "Account suffix 1", "This is the account suffix", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GWR76R");
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));

		// External Account number display group
		subGroup = new DisplayGroup("ALT", "Label of ALT", "Description of ALT");
		group.addItem(subGroup);

		// External account number with toggle
		inputField = FunctionToolbox.getInputField("TEAN1", "External account number 1", "Description of EAN", "A", "20", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroup.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));

		// Date detail
		inputField = FunctionToolbox.getInputField("DTEDATA", "Date detail", "This is the date non-key description", "D", "7", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("040100");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// Expiry date
		inputField = FunctionToolbox.getInputField("XPD", "Hold cod expiry", "This is the hold code expiry", "A", "5", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2 of HCX2", "Description 2 of HCX2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Hold code
		inputField = FunctionToolbox.getInputField("HLD2", "Hold code dummy", "This is the hold code", "A", "3", "");
		inputField.setInitialValue("SS1");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("JVR01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "JVR01R", "", true, ""));

		// Date detail
		inputField = FunctionToolbox.getInputField("DTEDATA2", "Date detail dummy", "This is the date non-key description", "D",
						"7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord3(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC3", "Record 3 of HCX2", "Description 3 of HCX2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Hold code description
		inputField = FunctionToolbox.getInputField("DSC2", "Hold code description dummy", "This is the hold code description", "A",
						"35", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, ""));

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		// MAIN FIELD SET ================================
		// transaction test
		APIFieldSet apiFieldSet = FunctionToolbox.getLoadAPIFieldSet(session, "LID", "HCI", "J46F", "GZJ461",
						"Add/Maintain/Delete Hold Code", "F", true);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		// PV
		// PVFieldSet pvFieldSet = FunctionToolbox.getPVFieldSet(session, "JVR01R", "", false, "", MappingToolbox
		// .getFullLoadFieldSetPath("PRIMARY", "LID"));
		// fg.setLoadPVFieldSet("PRIMARY", pvFieldSet);
		apiFieldSet = FunctionToolbox.getLoadPVFieldSet(session, "JVR01R", "JVR01R", "", true, "", "Hold code", false);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		// Enquiry test
		apiFieldSet = FunctionToolbox.getEnquiryFieldSet(session, "ABE", "ABE", "H69E", "HZH691", "Account Basic Enquiry", false);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		// Table test
		apiFieldSet = FunctionToolbox.getTableFieldSet(session, "OSPF", "OS10LF", "Deal test", "OSBRNM:OSDLP:OSDLR", "OSPF", "",
						false, false);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		// SUCCEEDING FIELD SET ================================
		apiFieldSet = FunctionToolbox.getLoadAPIFieldSet(session, "LI3", "HCI", "J46F", "GZJ461", "Add/Maintain/Delete Hold Code",
						"F", false);
		fg.addLoadAPIFieldSet("REC3", apiFieldSet);

		// PV
		apiFieldSet = FunctionToolbox.getLoadPVFieldSet(session, "REC3JVR01R", "JVR01R", "", false, "", "", false);
		fg.addLoadAPIFieldSet("REC3", apiFieldSet);
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		// transaction test
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "LID", "HCI", "J46F", "Add/Maintain/Delete Hold Code",
						"F");
		fg.addUpdateAPIFieldSet(apiFieldSet);

		// Table test
		apiFieldSet = FunctionToolbox.getTableFieldSet(session, "OSPF", "OS10LF", "Deal test", "OSBRNM:OSDLP:OSDLR", "OSPF", "F",
						false, false);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		// another update on a load field set on non-main field set
		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "LI3", "HCI", "J46F", "Add/Maintain/Delete Hold Code", "F");
		fg.addUpdateAPIFieldSet(apiFieldSet);

		fg.getFunction().addReservedFieldSet(Function.G5_FIELDSET, "Description of G5");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// add validate mapping
		fg.addValidateMappingFromPV("PRIMARY", "HLD", "JVR01R", "$JVHRC", "PRIMARY", "HLD", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "HLD", "JVR01R", "$JVDSCS", "PRIMARY", "HLD", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingToPV("PRIMARY", "AB", "PRIMARY", "AS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "AN", "PRIMARY", "AS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "AS", "PRIMARY", "AS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AB", "PRIMARY", "AB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AN", "PRIMARY", "AN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76AS", "PRIMARY", "AS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "AS", "GWR76R", "$R76SN", "PRIMARY", "AB", MappingToolbox.TYPE_OUTPUT);

		// add update mapping (record 1 - HCI)
		fg.addUpdateMapping("PRIMARY", "HLD", "LID", "GZHRC");
		fg.addUpdateMapping("PRIMARY", "DSC", "LID", "GZHRD");
		fg.addUpdateMapping("PRIMARY", "XPD", "LID", "GZDED");

		// add update mapping (record 2/3 - HCI)
		fg.addUpdateMapping("REC2", "HLD2", "LI3", "GZHRC");
		fg.addUpdateMapping("REC3", "DSC2", "LI3", "GZHRD");

		// add updating mapping (table)
		fg.addUpdateMapping("PRIMARY", "BRNM", "OSPF", "OSBRNM");
		fg.addUpdateMapping("PRIMARY", "DLP", "OSPF", "OSDLP");
		fg.addUpdateMapping("PRIMARY", "DLR", "OSPF", "OSDLR");
		fg.addUpdateMapping("PRIMARY", "CUS", "OSPF", "OSCUS");
		fg.addUpdateMapping("PRIMARY", "CLC", "OSPF", "OSCLC");
		fg.addUpdateMapping("PRIMARY", "DOS", "OSPF", "OSOREF");

		fg.addUpdateMapping("PRIMARY", "HLD", Function.G5_FIELDSET, "G5WHO");
		fg.addUpdateMapping("PRIMARY", "AB", Function.G5_FIELDSET, "G5SHN");
		fg.addUpdateMapping("PRIMARY", "AN", Function.G5_FIELDSET, "G5JREF");
		fg.addUpdateMapping("PRIMARY", "AS", Function.G5_FIELDSET, "G5APP");
		fg.addUpdateMapping("PRIMARY", "DSC", Function.G5_FIELDSET, "G5N01");
		fg.addUpdateMapping("PRIMARY", "CUS", Function.G5_FIELDSET, "G5N02");
		fg.addUpdateMapping("PRIMARY", "CLC", Function.G5_FIELDSET, "G5N03");
		fg.addUpdateMapping("PRIMARY", "BRNM", Function.G5_FIELDSET, "G5N04");
		fg.addUpdateMapping("PRIMARY", "DLP", Function.G5_FIELDSET, "G5N05");
		fg.addUpdateMapping("PRIMARY", "DLR", Function.G5_FIELDSET, "G5N06");
		fg.addUpdateMapping("PRIMARY", "AN", Function.G5_FIELDSET, "G5N07");
		fg.addUpdateMapping("PRIMARY", "AS", Function.G5_FIELDSET, "G5N08");
		fg.addUpdateMapping("PRIMARY", "HLD", Function.G5_FIELDSET, "G5N09");
		fg.addUpdateMapping("PRIMARY", "AB", Function.G5_FIELDSET, "G5N010");

		// add load PV (record 1)
		fg.addLoadMappingToLoad("PRIMARY", "HLD", "PRIMARY", "JVR01R", "$JVHRC");
		fg.addLoadMappingFromLoad("PRIMARY", "JVR01R", "$JVDEDS", "PRIMARY", "XPD", MappingToolbox.TYPE_PRIMITIVE);

		// add load transaction
		fg.addLoadMappingToLoad("PRIMARY", "HLD", "PRIMARY", "LID", "GZHRC");
		fg.addLoadMappingFromLoad("PRIMARY", "LID", "GZHRD", "PRIMARY", "DSC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "LID", "GZHRD", "PRIMARY", "DSC", MappingToolbox.TYPE_OUTPUT);

		// add load enquiry
		fg.addLoadMappingToLoad("PRIMARY", "AB", "PRIMARY", "ABE", "HZAB");
		fg.addLoadMappingToLoad("PRIMARY", "AN", "PRIMARY", "ABE", "HZAN");
		fg.addLoadMappingToLoad("PRIMARY", "AS", "PRIMARY", "ABE", "HZAS");
		fg.addLoadMappingFromLoad("PRIMARY", "ABE", "HZSHN", "PRIMARY", "SHN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "ABE", "HZBAL", "PRIMARY", "BAL", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "ABE", "HZSHN", "PRIMARY", "SHN", MappingToolbox.TYPE_OUTPUT);
		fg.addLoadMappingFromLoad("PRIMARY", "ABE", "HZBAL", "PRIMARY", "BAL", MappingToolbox.TYPE_OUTPUT);

		// add load table
		fg.addLoadMappingToLoad("PRIMARY", "BRNM", "PRIMARY", "OSPF", "OSBRNM");
		fg.addLoadMappingToLoad("PRIMARY", "DLP", "PRIMARY", "OSPF", "OSDLP");
		fg.addLoadMappingToLoad("PRIMARY", "DLR", "PRIMARY", "OSPF", "OSDLR");
		fg.addLoadMappingFromLoad("PRIMARY", "OSPF", "OSCUS", "PRIMARY", "CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "OSPF", "OSCLC", "PRIMARY", "CLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "OSPF", "OSOREF", "PRIMARY", "DOS", MappingToolbox.TYPE_PRIMITIVE);

		// add load PV (record 3)
		fg.addLoadMappingToLoad("REC2", "HLD2", "REC3", "JVR01R", "$JVHRC");

		// add load transaction (record 3)
		fg.addLoadMappingToLoad("REC2", "HLD2", "REC3", "LI3", "GZHRC");
		fg.addLoadMappingFromLoad("REC3", "LI3", "GZHRD", "REC3", "DSC2", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("REC3", "LI3", "GZHRD", "REC3", "DSC2", MappingToolbox.TYPE_OUTPUT);

		// context mapping
		fg.addContextMapping("AB", "$CBBN");
		fg.addContextMapping("AN", "$CBNO");
		fg.addContextMapping("AS", "$CSFX");
		fg.addContextMapping("BRNM", "$BRNM");
		fg.addContextMapping("DLP", "$DLP");
		fg.addContextMapping("DLR", "$DLR");

	}

	public FunctionGenerator test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("ALW", "Label of ALW", "Desc of ALW", "com.misys.equation.screens",
							"com.misys.equation.screens.layout");
			fg.getFunction().setAllowedEnq(true);
			fg.getFunction().setAllowedAdd(false);
			fg.getFunction().setAllowedMaint(false);
			fg.getFunction().setAllowedDel(false);
			addRecord1(fg);
			addRecord2(fg);
			addRecord3(fg);
			addLoadAPI(fg);
			addUpdateAPI(fg);
			addMappings(fg);

			File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\ALW.class");

			// Write to DB
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), serviceClass, null);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);

			// Return function generator
			return fg;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
