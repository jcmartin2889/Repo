package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldSet;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

public class JAX extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: JAX.java 10279 2011-01-18 09:02:51Z MACDONP1 $";

	public JAX()
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
		JAX test = new JAX();
		test.test();
	}

	private DisplayGroup addKeyGroup(InputFieldSet inputFieldSet, DisplayAttributesSet displayAttributesSet) throws EQException
	{
		// Work fields
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;

		// Subgroup for primary account in Equation format
		DisplayGroup subGroupEQN = new DisplayGroup("+EQN", "Standard format", "Primary account in Equation format");

		// Subgroup for primary account in EAN format
		DisplayGroup subGroupEAN = new DisplayGroup("+EAN", "EAN format", "Primary account in EAN format");

		// Key group
		DisplayGroup group = new DisplayGroup("+KEYS", "Primary account", "Primary account group");
		group.addItem(subGroupEQN);
		group.addItem(subGroupEAN);
		displayAttributesSet.addItem(group);

		// Account branch
		inputField = FunctionToolbox.getInputField("PAB", "Primary account number", "This is the account branch", "A", "4", "");
		inputField.setDefaultValue("0000");
		inputField.setKey(true);
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("PAN", "Primary account", "This is the account number", "A", "6", "");
		inputField.setDefaultValue("000001");
		inputField.setKey(true);
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("PAS", "Primary account suffix", "This is the account suffix", "A", "3", "");
		inputField.setDefaultValue("001");
		inputField.setKey(true);
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setSecurity(EquationPVMetaDataHelper.SEC_UPD);

		// External account number
		inputField = FunctionToolbox.getInputField("PEAN", "Primary account", "This is the primary account in EAN format", "A",
						"20", "");
		inputField.setKey(true);
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEAN.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));

		// Return the group
		return group;
	}

	private DisplayGroup addRepeatingDataGroup(FunctionGenerator fg, InputFieldSet inputFieldSet,
					DisplayAttributesSet displayAttributesSet) throws EQException
	{
		// Work fields
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;

		// Key group
		DisplayGroup group = new DisplayGroup("SUBFILE", "Secondary customers", "These are the secondary customers");
		displayAttributesSet.addItem(group);

		// Create 20 customers
		for (int i = 1; i <= 20; i++)
		{
			addRepeatingData(fg, inputFieldSet, displayAttributesSet, group, i);
		}

		// Return the group
		return group;
	}

	private DisplayGroup addRepeatingData(FunctionGenerator fg, InputFieldSet inputFieldSet,
					DisplayAttributesSet displayAttributesSet, DisplayGroup group, int index) throws EQException
	{
		// Work fields
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;

		// Suffix
		String is = "_" + index;

		// Input selection
		inputField = FunctionToolbox.getInputField("SINP" + is, "Secondary customer " + index, "This is the input selection "
						+ index, "A", "1", "");
		inputField.setValidValues("D");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);

		// SINP field adaptor
		if (printXML)
		{
			System.out.println("	public class SINP" + is + "_FieldAdaptor extends AbstractFieldAdaptor" + "{"
							+ "public boolean isValid(UserData userData)" + "{" + "if (userData.rtvFieldInputValue(\"SINP" + is
							+ "\").equals(\"D\"))" + "{" + "boolean valid = userData.rtvFieldInputValue(\"SCUS" + is
							+ "\").equals(userData.rtvFieldInputValue(\"OCUS" + is + "\"))"
							+ "&& userData.rtvFieldInputValue(\"SCLC" + is + "\").equals(userData.rtvFieldInputValue(\"OCLC" + is
							+ "\"))" + "&& userData.rtvFieldInputValue(\"SREL" + is
							+ "\").equals(userData.rtvFieldInputValue(\"OREL" + is + "\"))"
							+ "&& userData.rtvFieldInputValue(\"SDUP" + is + "\").equals(userData.rtvFieldInputValue(\"ODUP" + is
							+ "\"));" + "if (!valid)" + "{" + "getReturnMessages().addMessage(\"KSM2539\");" + "}"
							+ "return valid;" + "}" + "return true;" + "}" + "}");
		}

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("SCUS" + is, "Secondary customer mnemonic " + index,
						"This is the secondary customer " + index, "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField("SCLC" + is, "Secondary customer location " + index,
						"This is the secondary customer location " + index, "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		displayAttributes.setPrompt("GFR70R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", true, "N"));
		pvFieldSet.setSecurity(EquationPVMetaDataHelper.SEC_UPD);
		pvFieldSet.setExecuteMode(FieldSet.EXECUTE_CODE);

		// PV field set java code
		if (printXML)
		{
			System.out.println("public class SCLC" + is + "__GFR70R__PVFieldSet extends AbstractFieldSetAdaptor" + "{"
							+ "public boolean shouldExecuteModule(UserData userdata)" + "{"
							+ "return !userdata.rtvFieldDbValue(\"SCUS" + is
							+ "\").trim().equals(\"\") || !userdata.rtvFieldDbValue(\"SCLC" + is + "\").trim().equals(\"\");" + "}"
							+ "}");
		}

		// Relationship
		inputField = FunctionToolbox.getInputField("SREL" + is, "Relationship " + index, "This is the relationship" + index, "A",
						"3", "");
		inputField.setMandatory(InputField.MANDATORY_SCRIPT);
		inputField.setMandatoryExpression("(OCUS" + is + " != \"\"" + "||" + "SCUS" + is + " != \"\")");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		if (index == 1)
		{
			displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_ABOVE);
		}
		else
		{
			displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);

		}
		displayAttributes.setPrompt("RIR10R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "RIR10R", "", true, "N"));
		pvFieldSet.setExecuteMode(FieldSet.EXECUTE_SCRIPT);
		pvFieldSet.setExecuteScript("SREL" + is + " != \"\"");

		// Duplicate statement
		inputField = FunctionToolbox.getInputField("SDUP" + is, "Dup Stmts " + index, "This is the duplicate statement flag "
						+ index, "B", "1", "");
		inputField.setMandatory(InputField.MANDATORY_SCRIPT);
		inputField.setMandatoryExpression("OCUS" + is + " != \"\"");
		inputField.setLoadPrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		if (index == 1)
		{
			displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_ABOVE);
		}
		else
		{
			displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);

		}
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("OCUS" + is, "Secondary customer " + index, "This is the secondary customer "
						+ index, "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField("OCLC" + is, "Secondary customer location " + index,
						"This is the secondary customer location " + index, "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);

		// Relationship
		inputField = FunctionToolbox.getInputField("OREL" + is, "Relationship " + index, "This is the relationship" + index, "A",
						"3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);

		// Duplicate statement
		inputField = FunctionToolbox.getInputField("ODUP" + is, "Dup Stmts " + index, "This is the duplicate statement flag "
						+ index, "B", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		group.addItem(displayAttributes);

		// Mode
		WorkField workField = FunctionToolbox.addWorkField(fg.getFunction(), "wfFCT" + is, "FCT", "", "A", "1", "");
		workField.setLoadAssignment(Field.ASSIGNMENT_CODE);
		workField.setValidateAssignment(Field.ASSIGNMENT_CODE);

		// Generate the FieldAdaptor for SCUS and SCLC
		if (printXML)
		{
			System.out.println("	public class SCUS" + is + "_FieldAdaptor extends AbstractFieldAdaptor" + "{"
							+ "public boolean isMandatory(UserData userData)" + "{" + "return !userData.rtvFieldDbValue(\"OCUS"
							+ is + "\").trim().equals(\"\");" + "}" + "}");
			System.out.println("");
			System.out.println("public class SCLC" + is + "_FieldAdaptor extends AbstractFieldAdaptor" + "{"
							+ "public boolean isMandatory(UserData userData)" + "{" + "return !userData.rtvFieldDbValue(\"OCUS"
							+ is + "\").trim().equals(\"\");" + "}" + "}");
			System.out.println("");

			// Generate the ValueAdaptor for the work field wfWFCT
			System.out.println("	public class wfFCT" + is + "_ValueAdaptor extends AbstractValueAdaptor " + "{"
							+ "public String getValue(String curValue, UserData userData)" + "{"
							+ "if (userData.rtvFieldDbValue(\"SINP" + is + "\").equals(\"D\"))" + "{" + "return \"D\";" + "}"
							+ "else if (!userData.rtvFieldDbValue(\"OCUS" + is + "\").trim().equals(\"\"))" + "{" + "return \"M\";"
							+ "}" + "else if (!userData.rtvFieldDbValue(\"SCUS" + is + "\").trim().equals(\"\"))" + "{"
							+ "return \"A\";" + "}" + "else" + "{" + "return \"\";" + "}" + "}" + "}");
			System.out.println("");
		}

		// Generate the Update API
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "JAR" + is, "JAR", "C54L",
						"Add/Maint Joint A/c Relationships", IEquationStandardObject.FCT_ADD);
		if (index == 1)
		{
			apiFieldSet.setExecuteMode(FieldSet.EXECUTE_CODE);
		}
		else
		{
			apiFieldSet.setExecuteMode(FieldSet.EXECUTE_SCRIPT);
			apiFieldSet.setExecuteScript("wfFCT" + is + " != \"\"");
		}

		apiFieldSet.getAPIField("GZAB").setUpdateAssignment(Field.ASSIGNMENT_CODE);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		// Update API mapping
		// fg.addUpdateMapping("PRIMARY", "PAB", "JAR" + is, "GZAB"); test update value adaptor!
		fg.addUpdateMapping("PRIMARY", "PAN", "JAR" + is, "GZAN");
		fg.addUpdateMapping("PRIMARY", "PAS", "JAR" + is, "GZAS");
		fg.addUpdateMapping("REC2", "CUS", "JAR" + is, "GZPCUS");
		fg.addUpdateMapping("REC2", "CLC", "JAR" + is, "GZPCLC");
		fg.addUpdateMapping("REC2", "SCUS" + is, "JAR" + is, "GZSCUS");
		fg.addUpdateMapping("REC2", "SCLC" + is, "JAR" + is, "GZSCLC");
		fg.addUpdateMapping("REC2", "SREL" + is, "JAR" + is, "GZREL");
		fg.addUpdateMapping("REC2", "SDUP" + is, "JAR" + is, "GZDUPS");
		fg.addUpdateMapping("REC2", "OCUS" + is, "JAR" + is, "GZCUSH");
		fg.addUpdateMapping("REC2", "OCLC" + is, "JAR" + is, "GZCLCH");
		fg.addUpdateMapping("REC2", "OREL" + is, "JAR" + is, "GZRELH");
		fg.addUpdateMapping("REC2", "ODUP" + is, "JAR" + is, "GZDUPH");
		fg.addUpdateMapping("WorkField", "wfFCT" + is, "JAR" + is, "MODE");

		// Load Value adaptor Test (input field)
		if (printXML)
		{
			System.out.println("public class SDUP" + is + "_LoadValueAdaptor extends AbstractValueAdaptor" + "{"
							+ "public String getValue(String curValue, UserData userData)" + "{"
							+ "return userData.rtvFieldDbValue(\"ODUP" + is + "\");" + "}" + "}");
			System.out.println("");

			// Load Value adaptor test (work field)
			System.out.println("public class wfFCT" + is + "_LoadValueAdaptor extends AbstractValueAdaptor" + "{"
							+ "public String getValue(String curValue, UserData userData)" + "{"
							+ "if (userData.rtvFieldDbValue(\"OCUS" + is + "\").trim().equals(\"\"))" + "{" + "return \"\";" + "}"
							+ "else" + "{" + "return \"M\";" + "}" + "}" + "}");
			System.out.println("");

			// Update Value adaptor Test
			System.out.println("public class JAR" + is + "_GZAB_UpdateValueAdaptor extends AbstractValueAdaptor" + "{"
							+ "public String getValue(String curValue, UserData userData)" + "{"
							+ "return userData.rtvFieldDbValue(\"PAB\");" + "}" + "}");
			System.out.println("");
		}

		// Validate mapping for GFR70R
		fg.addValidateMappingToPV("REC2", "SCUS" + is, "REC2", "SCLC" + is, "GFR70R", "$GFCUS");
		fg.addValidateMappingToPV("REC2", "SCLC" + is, "REC2", "SCLC" + is, "GFR70R", "$GFCLC");
		fg.addValidateMappingFromPV("REC2", "SCLC" + is, "GFR70R", "$GFCUS", "REC2", "SCUS" + is, MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "SCLC" + is, "GFR70R", "$GFCLC", "REC2", "SCLC" + is, MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("REC2", "SCLC" + is, "GFR70R", "$GFCUN", "REC2", "SCUS" + is, MappingToolbox.TYPE_OUTPUT);

		// Return the group
		return group;
	}
	private void workFields(FunctionGenerator fg) throws EQException
	{
		FunctionToolbox.addWorkField(fg.getFunction(), "wfCPNC", "Customer number", "", "A", "15", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfEAN", "EAN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfAB", "AB", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfAN", "AN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfAS", "AS", "", "A", "20", "");
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1", "Description record 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;

		// add all the fields in this section ---------------------------------------

		addKeyGroup(inputFieldSet, displayAttributeSet);

		return fieldSetWrapper;
	}

	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("REC2", "Record 2", "Description of record 2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;

		// add all the fields in this section ---------------------------------------

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("CUS", "Customer mnemonic", "This is the customer mnemonic", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField("CLC", "Customer location", "This is the customer location", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Account type
		inputField = FunctionToolbox.getInputField("ATP", "Account type", "This is the account type", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Suffix range 1
		inputField = FunctionToolbox.getInputField("SRNG1", " Suffix range", "This is the suffix range 1", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Suffix range 2
		inputField = FunctionToolbox.getInputField("SRNG2", " Suffix range 2", "This is the suffix range 2", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Group for narratives
		DisplayGroup groupNarrative = new DisplayGroup("NAR", "Narrative group", "This is the narrative group");
		displayAttributeSet.addItem(groupNarrative);

		// Narrative 1
		inputField = FunctionToolbox.getInputField("NR1", "Narrative 1", "This is the narrative 1", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupNarrative.addItem(displayAttributes);

		// Narrative 2
		inputField = FunctionToolbox.getInputField("NR2", "Narrative 2", "This is the narrative 2", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupNarrative.addItem(displayAttributes);

		// Narrative 3
		inputField = FunctionToolbox.getInputField("NR3", "Narrative 3", "This is the narrative 3", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupNarrative.addItem(displayAttributes);

		// Narrative 4
		inputField = FunctionToolbox.getInputField("NR4", "Narrative 4", "This is the narrative 4", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupNarrative.addItem(displayAttributes);

		// Repeating data
		addRepeatingDataGroup(fg, inputFieldSet, displayAttributeSet);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet;

		// Account type details
		apiFieldSet = FunctionToolbox.getLoadPVFieldSet(session, "C5R55R", "C5R55R", "", false, " ", "Account type", false);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		// Customer details
		apiFieldSet = FunctionToolbox.getTableFieldSet(session, "GFPF", "GF03LF", "Customer details", "GFCPNC", "GF03LF", "F",
						false, false);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		// Narrative details
		apiFieldSet = FunctionToolbox.getTableFieldSet(session, "RLPF", "RL10LF", "Joint narratives", "RLAB:RLAN:RLAS", "RL10LF",
						"F", false, false);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet;

		// Narrative details
		// apiFieldSet = FunctionToolbox.getTableFieldSet(session, "RLPF", "RL10LF", "Joint narratives", "RLAB:RLAN:RLAS", "RL10LF",
		// "F");
		// fg.addUpdateAPIFieldSet(apiFieldSet);
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// Validate mapping for primary account GWR76R
		fg.addValidateMappingToPV("PRIMARY", "PAB", "PRIMARY", "PAS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "PAN", "PRIMARY", "PAS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "PAS", "PRIMARY", "PAS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRIMARY", "PAS", "GWR76R", "$R76AB", "PRIMARY", "PAB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "PAS", "GWR76R", "$R76AN", "PRIMARY", "PAN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "PAS", "GWR76R", "$R76AS", "PRIMARY", "PAS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "PAS", "GWR76R", "$R76SN", "PRIMARY", "PAB", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "PAS", "GWR76R", "$R76AT", "REC2", "ATP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "PAS", "GWR76R", "$R76AN", "WorkField", "wfCPNC", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRIMARY", "PAS", "GWR76R", "$R76EZ", "WorkField", "wfEAN", MappingToolbox.TYPE_WORK);

		fg.addValidateMappingFromPV("PRIMARY", "PEAN", "GIR33R", "$I33AB", "WorkField", "wfAB", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRIMARY", "PEAN", "GIR33R", "$I33AN", "WorkField", "wfAN", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRIMARY", "PEAN", "GIR33R", "$I33AS", "WorkField", "wfAS", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRIMARY", "PEAN", "GIR33R", "$I33EX", "PRIMARY", "PEAN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "PEAN", "GIR33R", "$I33SN", "PRIMARY", "PEAN", MappingToolbox.TYPE_OUTPUT);

		// load mapping for Account type
		fg.addLoadMappingToLoad("PRIMARY", "ATP", "PRIMARY", "C5R55R", "$C5ATP");
		fg.addLoadMappingFromLoad("PRIMARY", "C5R55R", "$C5LVS", "PRIMARY", "SRNG1", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "C5R55R", "$C5HVS", "PRIMARY", "SRNG2", MappingToolbox.TYPE_PRIMITIVE);

		// load mapping for Customer details
		fg.addLoadMappingToLoad("WorkField", "wfCPNC", "PRIMARY", "GFPF", "GFCPNC");
		fg.addLoadMappingFromLoad("PRIMARY", "GFPF", "GFCUS", "REC2", "CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "GFPF", "GFCLC", "REC2", "CLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "GFPF", "GFDAS", "REC2", "CUS", MappingToolbox.TYPE_OUTPUT);

		// load transaction for the narratives
		fg.addLoadMappingToLoad("PRIMARY", "PAB", "PRIMARY", "RLPF", "RLAB");
		fg.addLoadMappingToLoad("PRIMARY", "PAN", "PRIMARY", "RLPF", "RLAN");
		fg.addLoadMappingToLoad("PRIMARY", "PAS", "PRIMARY", "RLPF", "RLAS");
		fg.addLoadMappingFromLoad("PRIMARY", "RLPF", "RLNAR1", "PRIMARY", "NR1", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "RLPF", "RLNAR2", "PRIMARY", "NR2", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "RLPF", "RLNAR3", "PRIMARY", "NR3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "RLPF", "RLNAR4", "PRIMARY", "NR4", MappingToolbox.TYPE_PRIMITIVE);

		// add update mapping
		// fg.addUpdateMapping("PRIMARY", "PAB", "RLPF", "RLAB");
		// fg.addUpdateMapping("PRIMARY", "PAN", "RLPF", "RLAN");
		// fg.addUpdateMapping("PRIMARY", "PAS", "RLPF", "RLAS");
		// fg.addUpdateMapping("PRIMARY", "CUS", "RLPF", "RLPCUS");
		// fg.addUpdateMapping("PRIMARY", "CLC", "RLPF", "RLPCLC");
		// fg.addUpdateMapping("PRIMARY", "NR1", "RLPF", "RLNAR1");
		// fg.addUpdateMapping("PRIMARY", "NR2", "RLPF", "RLNAR2");
		// fg.addUpdateMapping("PRIMARY", "NR3", "RLPF", "RLNAR3");
		// fg.addUpdateMapping("PRIMARY", "NR4", "RLPF", "RLNAR4");

		// context mapping
		fg.addContextMapping("PAB", "$CBBN");
		fg.addContextMapping("PAN", "$CBNO");
		fg.addContextMapping("PAS", "$CSFX");
	}

	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("JAX", "Add/Maint Joint A/c Relationships",
							"Description of Add/Maint Joint A/c Relationships", "com.misys.equation.screens",
							"com.misys.equation.screens.layout");
			workFields(fg);
			addRecord1(fg);
			addRecord2(fg);
			addLoadAPI(fg);
			addUpdateAPI(fg);
			addMappings(fg);

			fg.getFunction().setApplyDuringExtInput(true);
			fg.getFunction().setApplyDuringRecovery(true);

			// Write to DB
			File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\JAX.class");
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), serviceClass, null);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
