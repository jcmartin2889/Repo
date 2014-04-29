package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

public class RLX extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RLX.java 7138 2010-05-04 14:59:54Z lima12 $";
	int blankIndex = 0;

	public RLX()
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
		RLX test = new RLX();
		test.test();
	}

	private void addBlankSpace(DisplayAttributesSet displayAttributeSet)
	{
		blankIndex++;
		DisplayLabel displayLabel = FunctionToolbox.getDisplayLabel("BlankSpace" + blankIndex, "   ", " ");
		FunctionToolbox.addDisplayLabel(displayAttributeSet, displayLabel);
	}

	private void workFields(FunctionGenerator fg) throws EQException
	{
		FunctionToolbox.addWorkField(fg.getFunction(), "wfEAN", "EAN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfAB", "AB", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfAN", "AN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfAS", "AS", "", "A", "20", "");

		FunctionToolbox.addWorkField(fg.getFunction(), "wfEANP", "EAN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfABP", "AB", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfANP", "AN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfASP", "AS", "", "A", "20", "");

		FunctionToolbox.addWorkField(fg.getFunction(), "wfEANI", "EAN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfABI", "AB", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfANI", "AN", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfASI", "AS", "", "A", "20", "");

		FunctionToolbox.addWorkField(fg.getFunction(), "wfMDT", "MDT", "", "A", "20", "");
		FunctionToolbox.addWorkField(fg.getFunction(), "wfNCD", "MDT", "", "A", "20", "");
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Loan details", "Description record 1");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Loan type
		inputField = FunctionToolbox.getInputField("LNP", "Loan type", "This is the loan type", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("OBR37R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OBR37R", "", true, "N"));

		// Loan reference
		inputField = FunctionToolbox.getInputField("LNR", "Reference", "This is the loan refernce", "A", "13", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Loan branch
		inputField = FunctionToolbox.getInputField("BRNM", "Branch", "This is the loan branch", "A", "4", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue(EqDS_DSJOBE.DBRNM);
		inputField.setInitialValueType(InputField.INIT_VALUE_DAJOB);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("CAR73R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CAR73R", "", true, "N"));

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("CUS", "Customer", "This is the customer", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField("CLC", "Customer", "This is the customer", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		displayAttributes.setPrompt("GFR70R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", true, "N"));

		// Commitment
		inputField = FunctionToolbox.getInputField("CMR", "Commitment", "This is the commitment", "A", "13", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("LCR01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "LCR01R", "", true, "N"));

		// blank space
		addBlankSpace(displayAttributeSet);

		// Currency
		inputField = FunctionToolbox.getInputField("CCY", "Currency", "This is the currency", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, "N"));

		// Amount
		inputField = FunctionToolbox.getInputField("DLA", "Amount", "This is the loan amount", "P", "15", "0");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "UTR71R", "", true, "N"));

		pvFieldSet.getPVField("@69AMT").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("@69LCY").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("@69DBR").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("@69DR").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("@69USR").setValidateAssignment(Field.ASSIGNMENT_CODE);

		// Equivalent amount
		inputField = FunctionToolbox.getInputField("EAM", "Equivalent amount", "This is the equivalent amount", "P", "15", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);

		// Start date
		inputField = FunctionToolbox.getInputField("SDT", "Start date", "This is the start date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));

		// blank space
		addBlankSpace(displayAttributeSet);

		// Contract date
		inputField = FunctionToolbox.getInputField("CTRD", "Contract date", "This is the contract date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("INTDET", "Interest details", "Description of record 2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Base code
		inputField = FunctionToolbox.getInputField("BRR", "Base code", "This is the base code", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("D4R47R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D4R47R", "", true, "N"));

		// Diff code
		inputField = FunctionToolbox.getInputField("DRR", "Diff code", "This is the diff code", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("D5R48R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D5R48R", "", true, "N"));

		// Margin
		inputField = FunctionToolbox.getInputField("RTM", "Margin rate", "This is the margin rate", "P", "15", "9");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV28R", "", true, "N"));

		// Pegged
		inputField = FunctionToolbox.getInputField("PEG", "Pegged?", "This is the pegged", "B", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Actual rate
		inputField = FunctionToolbox.getInputField("RAT", "Actual rate", "This is the actual rate", "P", "15", "9");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV28R", "", true, "N"));

		// IDB
		inputField = FunctionToolbox.getInputField("IDB", "Interest days basis", "This is the IDB", "A", "1", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("IDB");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Interest frequency
		inputField = FunctionToolbox.getInputField("IFQ", "Interest Frequency", "This is the interest frequency", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV13R", "", true, "N"));

		// Interest date
		inputField = FunctionToolbox.getInputField("NCD", "Interest Date", "This is the interest date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));

		// Capitalised?
		inputField = FunctionToolbox.getInputField("CPI", "Capitalised?", "This is the capitalised flag?", "B", "1", "");
		inputField.setDefaultValue("N");
		inputField.setInitialValue("N");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Repayment method
		inputField = FunctionToolbox.getInputField("RPYM", "Repayment method", "This is the repayment method", "A", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("RPM");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Schedule recalculation
		inputField = FunctionToolbox.getInputField("SCHC", "Schedule recalculation", "This is the schedule recalculation", "A",
						"1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("SCH");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		return fieldSetWrapper;
	}

	private DisplayFieldSetWrapper addRecord3(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRINDET", "Principal details", "Description of record 3");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Number of payments
		inputField = FunctionToolbox.getInputField("NPY", "Number of payments", "This is the number of payments", "S", "3", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV45R", "", true, "N"));

		// Repayment frequency
		inputField = FunctionToolbox.getInputField("RPQ", "Repayment frequency", "This is the repayment frequency", "A", "3	", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV13R", "", true, "N"));

		// Repayment amount
		inputField = FunctionToolbox.getInputField("RPA", "Repayment amount", "This is the repayment amount", "P", "15", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);

		// First amount
		inputField = FunctionToolbox.getInputField("FTA", "First amount", "This is the first amount", "P", "15", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);

		// First repayment date
		inputField = FunctionToolbox.getInputField("FDT", "First repayment date", "This is the first repayment date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));

		// First amount can differ flag
		inputField = FunctionToolbox.getInputField("DIF", "First amount can differ?", "This is the first amount can differ flag",
						"B", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Maturity date
		inputField = FunctionToolbox.getInputField("MDT", "Maturity date", "This is the maturity date", "D", "7", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));

		// Group for receiving account
		DisplayGroup subGroupEQN = new DisplayGroup("+EQN", "Standard format", "Receiving account in Equation format");
		DisplayGroup subGroupEAN = new DisplayGroup("+EAN", "EAN format", "Receiving account in EAN format");
		DisplayGroup group = new DisplayGroup("+ACC", "Receiving account", "Receiving account group");
		group.addItem(subGroupEQN);
		group.addItem(subGroupEAN);
		displayAttributeSet.addItem(group);

		// Account branch
		inputField = FunctionToolbox.getInputField("AB", "Receiving account number", "This is the receiving account branch", "A",
						"4", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("AN", "Receiving account", "This is the receiving account number", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("AS", "Receiving account suffix", "This is the receiving account suffix", "A",
						"3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));
		pvFieldSet.setSecurity(EquationPVMetaDataHelper.SEC_UPD);

		// External account number
		inputField = FunctionToolbox.getInputField("EAN", "Receiving account", "This is the receiving account in EAN format", "A",
						"20", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEAN.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));

		// Group for repay principal account
		subGroupEQN = new DisplayGroup("+EQN2", "Standard format", "Repay principal account in Equation format");
		subGroupEAN = new DisplayGroup("+EAN2", "EAN format", "Repay principal account in EAN format");
		group = new DisplayGroup("+ACC2", "Repay principal account", "Repay principal account group");
		group.addItem(subGroupEQN);
		group.addItem(subGroupEAN);
		displayAttributeSet.addItem(group);

		// Account branch
		inputField = FunctionToolbox.getInputField("ABP", "Repay principal a/c", "This is the repay principal a/c  branch", "A",
						"4", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("ANP", "Repay principal a/c number", "This is the repay principal a/c  number",
						"A", "6", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("ASP", "Repay principal suffix", "This is the repay principal a/c suffix", "A",
						"3", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));

		pvFieldSet.setSecurity(EquationPVMetaDataHelper.SEC_UPD);

		// External account number
		inputField = FunctionToolbox.getInputField("EANP", "Repay principal ", "This is the repay principal a/c in EAN format",
						"A", "20", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEAN.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));

		// Group for repay interest account
		subGroupEQN = new DisplayGroup("+EQN3", "Standard format", "Repay interest account in Equation format");
		subGroupEAN = new DisplayGroup("+EAN3", "EAN format", "Repay interest account in EAN format");
		group = new DisplayGroup("+ACC3", "Repay interest account", "Repay interest account group");
		group.addItem(subGroupEQN);
		group.addItem(subGroupEAN);
		displayAttributeSet.addItem(group);

		// Account branch
		inputField = FunctionToolbox.getInputField("ABI", "Repay interest a/c", "This is the repay interest a/c  branch", "A", "4",
						"");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("ANI", "Repay interest a/c number", "This is the repay interest a/c  number",
						"A", "6", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("ASI", "Repay interest suffix", "This is the repay interest a/c suffix", "A",
						"3", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEQN.addItem(displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));

		pvFieldSet.setSecurity(EquationPVMetaDataHelper.SEC_UPD);

		// External account number
		inputField = FunctionToolbox.getInputField("EANI", "Repay interest ", "This is the repay interest a/c in EAN format", "A",
						"20", "");
		inputField.setValidatePrimitiveAssignment(Field.ASSIGNMENT_CODE);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GIR33R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		subGroupEAN.addItem(displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GIR33R", "", true, "N"));

		// Schedule advice?
		inputField = FunctionToolbox.getInputField("SAP", "Schedule advice?", "This is the schedule advice flag?", "B", "1", "");
		inputField.setDefaultValue("N");
		inputField.setInitialValue("N");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord4(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("CPDET", "Payment type", "Description of record 4");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Payment type
		inputField = FunctionToolbox.getInputField("PYT", "Payment type", "This is the payment type", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("N4R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "N4R01R", "", true, "N"));

		return fieldSetWrapper;
	}

	private DisplayFieldSetWrapper addRecord5(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("CP2DET", "Payment details", "Description of record 5");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Receive currency
		inputField = FunctionToolbox.getInputField("RCCY", "Receive currency", "This is the receive currency", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, ""));

		// Receive amount - total
		inputField = FunctionToolbox.getInputField("RAMT", "Receive amount - total", "This is the receive total amount", "P", "15",
						"0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));
		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);

		// Receive amount - net
		inputField = FunctionToolbox.getInputField("RNMT", "Receive amount - net", "This is the receive total net", "P", "15", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));
		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);

		// Pay currency
		inputField = FunctionToolbox.getInputField("PCCY", "Pay currency", "This is the pay currency", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "", true, ""));

		// Pay amount - total
		inputField = FunctionToolbox.getInputField("PAMT", "Pay amount - total", "This is the pay total amount", "P", "15", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);

		// Pay amount - net
		inputField = FunctionToolbox.getInputField("PNMT", "Pay amount - net", "This is the pay total net", "P", "15", "0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		pvFieldSet.getPVField("$NDPAM").setValidateAssignment(Field.ASSIGNMENT_CODE);
		pvFieldSet.getPVField("$NODIG").setValidateAssignment(Field.ASSIGNMENT_CODE);

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord6(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg
						.addFieldSet("CPSETDET", "Payment settlement details", "Description of record 6");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Payment confirmation
		inputField = FunctionToolbox
						.getInputField("PMC", "Payment confirmation?", "This is the payment confirmation", "B", "1", "");
		inputField.setDefaultValue("Y");
		inputField.setInitialValue("Y");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// External reference
		inputField = FunctionToolbox.getInputField("XREF", "External reference", "This is the external reference", "A", "16", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Remitter Id
		inputField = FunctionToolbox.getInputField("RMTR", "Remitter Id", "This is the remitter Id", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		return fieldSetWrapper;
	}

	private DisplayFieldSetWrapper addRecord7(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("RECVDET", "We Receive Details", "Description of record 7");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Nostro
		inputField = FunctionToolbox.getInputField("NST1", "Receive nostro", "This is the receive nostro", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GJR74R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GJR74R", "", true, "N"));

		// Sender - Name and Address
		inputField = FunctionToolbox.getInputField("SAD1", "Sender name and address 1", "This is the sender name and address 1",
						"A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SAD2", "Sender name and address 2", "This is the sender name and address 2",
						"A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SAD3", "Sender name and address 3", "This is the sender name and address 3",
						"A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SAD4", "Sender name and address 4", "This is the sender name and address 4",
						"A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addRecord8(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PAYDET", "We Pay Details", "Description of record 7");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet displayAttributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;
		PVFieldSet pvFieldSet;
		DisplayLabel displayLabel;

		// add all the fields in this section ---------------------------------------

		// Nostro
		inputField = FunctionToolbox.getInputField("NST2", "Pay nostro", "This is the pay nostro", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GJR74R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GJR74R", "", true, "N"));

		// Beneficiary
		addBlankSpace(displayAttributeSet);

		displayLabel = FunctionToolbox.getDisplayLabel("58A Beneficiary", "** 58a: Beneficiary Institution **", "");
		FunctionToolbox.addDisplayLabel(displayAttributeSet, displayLabel);

		// Beneficiary - Account number
		inputField = FunctionToolbox.getInputField("OAN1", "Beneficiary Account number", "This is the beneficiary account number",
						"A", "35", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Beneficiary - SWIFT address
		inputField = FunctionToolbox.getInputField("SWB7", "Beneficiary SWIFT address", "This is the beneficiary SWIFT bank id",
						"A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SNA7", "Beneficiary SWIFT country code",
						"This is the beneficiary SWIFT country code", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SWL7", "Beneficiary SWIFT location", "This is the beneficiary SWIFT location",
						"A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SWR7", "Beneficiary SWIFT branch id",
						"This is the beneficiary SWIFT branch id", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("S9R01R");
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "S9R01R", "", true, "N"));

		// Beneficiary - Name and address
		inputField = FunctionToolbox.getInputField("BAD1", "Beneficiary name and address 1",
						"This is the beneficiary name and address 1", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("BAD2", "Beneficiary name and address 2",
						"This is the beneficiary name and address 2", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("BAD3", "Beneficiary name and address 3",
						"This is the beneficiary name and address 3", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("BAD4", "Beneficiary name and address 4",
						"This is the beneficiary name and address 4", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Account with institution
		addBlankSpace(displayAttributeSet);
		displayLabel = FunctionToolbox.getDisplayLabel("57A A/c with Inst", "** 57a: Account with Institution **", "");
		FunctionToolbox.addDisplayLabel(displayAttributeSet, displayLabel);

		// Account with institution - Account number
		inputField = FunctionToolbox.getInputField("OAN2", "A/c with Inst Account number",
						"This is the a/c with inst account number", "A", "35", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Account with institution - SWIFT address
		inputField = FunctionToolbox.getInputField("SWB3", "A/c with Inst SWIFT address",
						"This is the a/c with inst SWIFT bank id", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("CNA3", "A/c with Inst SWIFT country code",
						"This is the a/c with inst SWIFT country code", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SWL3", "A/c with Inst SWIFT location",
						"This is the a/c with inst SWIFT location", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SWR3", "A/c with Inst SWIFT branch id",
						"This is the a/c with inst SWIFT branch id", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("S9R01R");
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_NONE);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "S9R01R", "", true, "N"));

		// Account with institution - Name and Address
		inputField = FunctionToolbox.getInputField("AAD1", "A/c with Inst name and address 1",
						"This is the a/c with inst name and address 1", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("AAD2", "A/c with Inst name and address 2",
						"This is the a/c with inst name and address 2", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("AAD3", "A/c with Inst name and address 3",
						"This is the a/c with inst name and address 3", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("AAD4", "A/c with Inst name and address 4",
						"This is the a/c with inst name and address 4", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		// Account with institution - User reference
		inputField = FunctionToolbox
						.getInputField("US1", "A/c with Inst user 1", "This is the a/c with inst user 1", "A", "10", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		inputField = FunctionToolbox
						.getInputField("US2", "A/c with Inst user 2", "This is the a/c with inst user 2", "A", "10", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(displayAttributeSet, displayAttributes);

		return fieldSetWrapper;
	}
	private void addLoadAPI(FunctionGenerator fg)
	{
		APIFieldSet apiFieldSet;
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet;

		// Add Retail Loan
		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "RLA", "RLA", "N94A", "Add Retail Loan", "A");
		fg.addUpdateAPIFieldSet(apiFieldSet);
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// Validate mapping for receiving account GWR76R
		fg.addValidateMappingToPV("PRINDET", "AB", "PRINDET", "AS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRINDET", "AN", "PRINDET", "AS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRINDET", "AS", "PRINDET", "AS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRINDET", "AS", "GWR76R", "$R76AB", "PRINDET", "AB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "AS", "GWR76R", "$R76AN", "PRINDET", "AN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "AS", "GWR76R", "$R76AS", "PRINDET", "AS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "AS", "GWR76R", "$R76SN", "PRINDET", "AB", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRINDET", "AS", "GWR76R", "$R76EZ", "WorkField", "wfEAN", MappingToolbox.TYPE_WORK);

		fg.addValidateMappingFromPV("PRINDET", "EAN", "GIR33R", "$I33AB", "WorkField", "wfAB", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EAN", "GIR33R", "$I33AN", "WorkField", "wfAN", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EAN", "GIR33R", "$I33AS", "WorkField", "wfAS", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EAN", "GIR33R", "$I33EX", "PRINDET", "EAN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "EAN", "GIR33R", "$I33SN", "PRINDET", "EAN", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for repay principal account GWR76R
		fg.addValidateMappingToPV("PRINDET", "ABP", "PRINDET", "ASP", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRINDET", "ANP", "PRINDET", "ASP", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRINDET", "ASP", "PRINDET", "ASP", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRINDET", "ASP", "GWR76R", "$R76AB", "PRINDET", "ABP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "ASP", "GWR76R", "$R76AN", "PRINDET", "ANP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "ASP", "GWR76R", "$R76AS", "PRINDET", "ASP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "ASP", "GWR76R", "$R76SN", "PRINDET", "ABP", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRINDET", "ASP", "GWR76R", "$R76EZ", "WorkField", "wfEANP", MappingToolbox.TYPE_WORK);

		fg.addValidateMappingFromPV("PRINDET", "EANP", "GIR33R", "$I33AB", "WorkField", "wfABP", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EANP", "GIR33R", "$I33AN", "WorkField", "wfANP", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EANP", "GIR33R", "$I33AS", "WorkField", "wfASP", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EANP", "GIR33R", "$I33EX", "PRINDET", "EANP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "EANP", "GIR33R", "$I33SN", "PRINDET", "EANP", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for repay interest account GWR76R
		fg.addValidateMappingToPV("PRINDET", "ABI", "PRINDET", "ASI", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRINDET", "ANI", "PRINDET", "ASI", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRINDET", "ASI", "PRINDET", "ASI", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRINDET", "ASI", "GWR76R", "$R76AB", "PRINDET", "ABI", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "ASI", "GWR76R", "$R76AN", "PRINDET", "ANI", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "ASI", "GWR76R", "$R76AS", "PRINDET", "ASI", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "ASI", "GWR76R", "$R76SN", "PRINDET", "ABI", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRINDET", "ASI", "GWR76R", "$R76EZ", "WorkField", "wfEANI", MappingToolbox.TYPE_WORK);

		fg.addValidateMappingFromPV("PRINDET", "EANI", "GIR33R", "$I33AB", "WorkField", "wfABI", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EANI", "GIR33R", "$I33AN", "WorkField", "wfANI", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EANI", "GIR33R", "$I33AS", "WorkField", "wfASI", MappingToolbox.TYPE_WORK);
		fg.addValidateMappingFromPV("PRINDET", "EANI", "GIR33R", "$I33EX", "PRINDET", "EANI", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "EANI", "GIR33R", "$I33SN", "PRINDET", "EANI", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for customer
		fg.addValidateMappingToPV("PRIMARY", "CUS", "PRIMARY", "CLC", "GFR70R", "$GFCUS");
		fg.addValidateMappingToPV("PRIMARY", "CLC", "PRIMARY", "CLC", "GFR70R", "$GFCLC");
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCUS", "PRIMARY", "CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCLC", "PRIMARY", "CLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CLC", "GFR70R", "$GFCUN", "PRIMARY", "CUS", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for base code
		fg.addValidateMappingToPV("INTDET", "BRR", "INTDET", "BRR", "D4R47R", "$D4BRR");
		fg.addValidateMappingFromPV("INTDET", "BRR", "D4R47R", "$D4BRR", "INTDET", "BRR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("INTDET", "BRR", "D4R47R", "$D4DSC", "INTDET", "BRR", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for deal amount GWV30R
		fg.addValidateMappingToPV("PRIMARY", "CCY", "PRIMARY", "DLA", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("PRIMARY", "DLA", "PRIMARY", "DLA", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("PRIMARY", "DLA", "GWV30R", "$NUMAM", "PRIMARY", "DLA", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "DLA", "GWV30R", "$EDTAM", "PRIMARY", "DLA", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for deal amount UTR71R
		fg.addValidateMappingToPV("PRIMARY", "CCY", "PRIMARY", "DLA", "UTR71R", "@69CCY");
		fg.addValidateMappingToPV("PRIMARY", "AB", "PRIMARY", "DLA", "UTR71R", "@69ABR");

		// Validate mapping for equivalent amount
		fg.addValidateMappingToPV("PRIMARY", "CCY", "PRIMARY", "EAM", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("PRIMARY", "EAM", "PRIMARY", "EAM", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("PRIMARY", "EAM", "GWV30R", "$NUMAM", "PRIMARY", "EAM", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "EAM", "GWV30R", "$EDTAM", "PRIMARY", "EAM", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for repayment amount
		fg.addValidateMappingToPV("PRINDET", "CCY", "PRINDET", "RPA", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("PRINDET", "RPA", "PRINDET", "RPA", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("PRINDET", "RPA", "GWV30R", "$NUMAM", "PRINDET", "RPA", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "RPA", "GWV30R", "$EDTAM", "PRINDET", "RPA", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for first amount
		fg.addValidateMappingToPV("PRINDET", "CCY", "PRINDET", "FTA", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("PRINDET", "FTA", "PRINDET", "FTA", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("PRINDET", "FTA", "GWV30R", "$NUMAM", "PRINDET", "FTA", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRINDET", "FTA", "GWV30R", "$EDTAM", "PRINDET", "FTA", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for receive amount - total
		fg.addValidateMappingToPV("CP2DET", "RCCY", "CP2DET", "RAMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("CP2DET", "RAMT", "CP2DET", "RAMT", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("CP2DET", "RAMT", "GWV30R", "$NUMAM", "CP2DET", "RAMT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("CP2DET", "RAMT", "GWV30R", "$EDTAM", "CP2DET", "RAMT", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for receive amount - net
		fg.addValidateMappingToPV("CP2DET", "RCCY", "CP2DET", "RNMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("CP2DET", "RNMT", "CP2DET", "RNMT", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("CP2DET", "RNMT", "GWV30R", "$NUMAM", "CP2DET", "RNMT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("CP2DET", "RNMT", "GWV30R", "$EDTAM", "CP2DET", "RNMT", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for pay amount - total
		fg.addValidateMappingToPV("CP2DET", "PCCY", "CP2DET", "PAMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("CP2DET", "PAMT", "CP2DET", "PAMT", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("CP2DET", "PAMT", "GWV30R", "$NUMAM", "CP2DET", "PAMT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("CP2DET", "PAMT", "GWV30R", "$EDTAM", "CP2DET", "PAMT", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for pay amount - net
		fg.addValidateMappingToPV("CP2DET", "PCCY", "CP2DET", "PNMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("CP2DET", "PNMT", "CP2DET", "PNMT", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("CP2DET", "PNMT", "GWV30R", "$NUMAM", "CP2DET", "PNMT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("CP2DET", "PNMT", "GWV30R", "$EDTAM", "CP2DET", "PNMT", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for beneficiary swift address
		fg.addValidateMappingToPV("PAYDET", "SWB7", "PAYDET", "SWR7", "S9R01R", "$S9SWB");
		fg.addValidateMappingToPV("PAYDET", "SNA7", "PAYDET", "SWR7", "S9R01R", "$S9CNA");
		fg.addValidateMappingToPV("PAYDET", "SWL7", "PAYDET", "SWR7", "S9R01R", "$S9SWL");
		fg.addValidateMappingToPV("PAYDET", "SWR7", "PAYDET", "SWR7", "S9R01R", "$S9SWR");
		fg.addValidateMappingFromPV("PAYDET", "SWR7", "S9R01R", "$S9SWB", "PAYDET", "SWB7", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR7", "S9R01R", "$S9CNA", "PAYDET", "SNA7", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR7", "S9R01R", "$S9SWL", "PAYDET", "SWL7", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR7", "S9R01R", "$S9SWR", "PAYDET", "SWR7", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR7", "S9R01R", "$S9INS2", "PAYDET", "SWR7", MappingToolbox.TYPE_OUTPUT);

		// Validate mapping for a/c with inst swift address
		fg.addValidateMappingToPV("PAYDET", "SWB3", "PAYDET", "SWR3", "S9R01R", "$S9SWB");
		fg.addValidateMappingToPV("PAYDET", "CNA3", "PAYDET", "SWR3", "S9R01R", "$S9CNA");
		fg.addValidateMappingToPV("PAYDET", "SWL3", "PAYDET", "SWR3", "S9R01R", "$S9SWL");
		fg.addValidateMappingToPV("PAYDET", "SWR3", "PAYDET", "SWR3", "S9R01R", "$S9SWR");
		fg.addValidateMappingFromPV("PAYDET", "SWR3", "S9R01R", "$S9SWB", "PAYDET", "SWB3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR3", "S9R01R", "$S9CNA", "PAYDET", "CNA3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR3", "S9R01R", "$S9SWL", "PAYDET", "SWL3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR3", "S9R01R", "$S9SWR", "PAYDET", "SWR3", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PAYDET", "SWR3", "S9R01R", "$S9INS2", "PAYDET", "SWR3", MappingToolbox.TYPE_OUTPUT);

		// add update mapping for RLA
		fg.addUpdateMapping("PRIMARY", "LNP", "RLA", "GZLNP");
		fg.addUpdateMapping("PRIMARY", "LNR", "RLA", "GZLNR");
		fg.addUpdateMapping("PRIMARY", "BRNM", "RLA", "GZBRNM");
		fg.addUpdateMapping("PRIMARY", "CUS", "RLA", "GZCUS");
		fg.addUpdateMapping("PRIMARY", "CLC", "RLA", "GZCLC");
		fg.addUpdateMapping("PRIMARY", "CMR", "RLA", "GZCMR");
		fg.addUpdateMapping("PRIMARY", "CCY", "RLA", "GZCCY");
		fg.addUpdateMapping("PRIMARY", "DLA", "RLA", "GZDLA");
		fg.addUpdateMapping("PRIMARY", "SDT", "RLA", "GZSDT");
		fg.addUpdateMapping("PRIMARY", "CTRD", "RLA", "GZCTRD");
		fg.addUpdateMapping("PRIMARY", "EAM", "RLA", "GZEAM");

		fg.addUpdateMapping("INTDET", "BRR", "RLA", "GZBRR");
		fg.addUpdateMapping("INTDET", "DRR", "RLA", "GZDRR");
		fg.addUpdateMapping("INTDET", "RTM", "RLA", "GZRTM");
		fg.addUpdateMapping("INTDET", "PEG", "RLA", "GZPEG");
		fg.addUpdateMapping("INTDET", "RAT", "RLA", "GZRAT");
		fg.addUpdateMapping("INTDET", "IDB", "RLA", "GZIDB");

		fg.addUpdateMapping("INTDET", "IFQ", "RLA", "GZIFQ");
		fg.addUpdateMapping("INTDET", "wfNCD", "RLA", "GZNCD");
		fg.addUpdateMapping("INTDET", "CPI", "RLA", "GZCPI");
		fg.addUpdateMapping("INTDET", "RPYM", "RLA", "GZRPYM");
		fg.addUpdateMapping("INTDET", "SCHC", "RLA", "GZSCHC");

		fg.addUpdateMapping("PRINDET", "NPY", "RLA", "GZNPY");
		fg.addUpdateMapping("PRINDET", "RPQ", "RLA", "GZRPQ");
		fg.addUpdateMapping("PRINDET", "RPA", "RLA", "GZRPA");
		fg.addUpdateMapping("PRINDET", "FTA", "RLA", "GZFTA");
		fg.addUpdateMapping("PRINDET", "FDT", "RLA", "GZFDT");
		fg.addUpdateMapping("PRINDET", "wfMDT", "RLA", "GZMDT");
		fg.addUpdateMapping("PRINDET", "DIF", "RLA", "GZDIF");
		fg.addUpdateMapping("PRINDET", "AB", "RLA", "GZAB");
		fg.addUpdateMapping("PRINDET", "AN", "RLA", "GZAN");
		fg.addUpdateMapping("PRINDET", "AS", "RLA", "GZAS");
		fg.addUpdateMapping("PRINDET", "ABP", "RLA", "GZABP");
		fg.addUpdateMapping("PRINDET", "ANP", "RLA", "GZANP");
		fg.addUpdateMapping("PRINDET", "ASP", "RLA", "GZASP");
		fg.addUpdateMapping("PRINDET", "ABI", "RLA", "GZABI");
		fg.addUpdateMapping("PRINDET", "ANI", "RLA", "GZANI");
		fg.addUpdateMapping("PRINDET", "ASI", "RLA", "GZASI");
		fg.addUpdateMapping("PRINDET", "SAP", "RLA", "GZSAP");
	}

	public void generate(FunctionGenerator fg) throws EQException
	{
		workFields(fg);
		addRecord1(fg);
		addRecord2(fg);
		addRecord3(fg);
		addRecord4(fg);
		addRecord5(fg);
		addRecord6(fg);
		addRecord7(fg);
		addRecord8(fg);
		addLoadAPI(fg);
		addUpdateAPI(fg);
		addMappings(fg);

		fg.getFunction().setApplyDuringExtInput(false);
		fg.getFunction().setApplyDuringRecovery(false);

	}

	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("RLX", "Add Retail Loan with Clean Payment",
							"Description of Add Retail Loan with Clean Payment", "com.misys.equation.screens",
							"com.misys.equation.screens.layout");
			generate(fg);

			fg.getFunction().setApplyDuringExtInput(true);
			fg.getFunction().setApplyDuringRecovery(true);

			// Write to DB
			File serviceClass = new File(workSpace + "com\\misys\\equation\\screens\\RLX.class");
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
