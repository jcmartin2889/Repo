package com.misys.equation.function.test.option;

import com.misys.equation.common.access.IEquationStandardObject;
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

// THIS IS A COPY OF ALZ BUT ENQUIRY

public class ZT1 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ZT1.java 8739 2010-08-19 10:59:04Z lima12 $";

	public ZT1()
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
		ZT1 test = new ZT1();
		test.test();
	}

	private DisplayFieldSetWrapper addScreen1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Primary", "Customer details");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		inputFieldSet.setExecuteMode(0);
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		DisplayGroup groupKeys = new DisplayGroup("+KEYS", "Customer", "Customer key fields");
		attributeSet.addItem(groupKeys);

		// add all the fields in this section ---------------------------------------

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("ANC_CUS", "Customer mnemonic", "Customer mnemonic", "A", "6", "");
		inputField.setUpperCase(true);
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Customer");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupKeys.addItem(displayAttributes);

		// Customer location
		inputField = FunctionToolbox.getInputField("ANC_CLC", "Customer location", "Customer location", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("Customer");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", false, "Y"));

		groupKeys.addItem(displayAttributes);

		// Customer type
		inputField = FunctionToolbox.getInputField("ANC_CTP", "Customer type", "Customer type", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setKey(false);
		inputField.setType("CTP");
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_NO);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C4R54R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C4R54R", "", false, "N"));

		// Customer's basic number
		inputField = FunctionToolbox.getInputField("ANC_CPNC", "Customer's basic number", "Customer's basic number", "A", "6", "");
		inputField.setUpperCase(true);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Number");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFV71R", "C", false, "Y"));

		// Customer full name
		inputField = FunctionToolbox.getInputField("ANC_CUN", "Customer's full name", "Customer's full name", "A", "35", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Customer full name");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", false, ""));

		// Default account short name
		inputField = FunctionToolbox.getInputField("ANC_DAS", "Default account short name", "Default account short name", "A",
						"15", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", false, ""));

		// Branch mnemonic
		inputField = FunctionToolbox.getInputField("ANC_BRNM", "Branch mnemonic", "Branch mnemonic", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setKey(false);
		inputField.setInitialValue("LOND");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Branch");
		displayAttributes.setPrompt("CAR73R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CAR73R", "", false, "N"));

		// Address line 1
		inputField = FunctionToolbox.getInputField("CAA_NA1", "Address line 1", "Address line 1", "A", "35", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Address");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", false, ""));

		// Address line 2
		inputField = FunctionToolbox.getInputField("CAA_NA2", "Address line 2", "Address line 2", "A", "35", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, ""));

		// Address line 3
		inputField = FunctionToolbox.getInputField("CAA_NA3", "Address line 3", "Address line 3", "A", "35", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, ""));

		// Address line 4
		inputField = FunctionToolbox.getInputField("CAA_NA4", "Address line 4", "Address line 4", "A", "35", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, ""));

		// Address line 5
		inputField = FunctionToolbox.getInputField("CAA_NA5", "Address line 5", "Address line 5", "A", "35", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, ""));

		// Email address
		inputField = FunctionToolbox.getInputField("ANX_EAD1", "Email address", "Email address", "A", "60", "");
		inputField.setUpperCase(false);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, ""));

		// Tax code 1
		inputField = FunctionToolbox.getInputField("ANC_CRB1", "Tax code 1", "Tax code 1", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("D4R47R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D4R47R", "", false, "N"));

		// Tax code 2
		inputField = FunctionToolbox.getInputField("ANC_CRB2", "Tax code 2", "Tax code 2", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("D4R47R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D4R47R", "", false, "N"));

		// Credit rating
		inputField = FunctionToolbox.getInputField("MCO_C1R", "Credit rating", "Credit rating", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setKey(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("GKR40R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GKR40R", "", false, "N"));

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addScreen2(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("ACCOUNT", "Account", "Account input");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		DisplayGroup groupKeys = new DisplayGroup("+KEYS", "Customer", "Customer key fields");
		attributeSet.addItem(groupKeys);

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Account branch
		inputField = FunctionToolbox.getInputField("OCA_AB", "Account branch", "Account branch", "A", "4", "");
		inputField.setUpperCase(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("Account");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account basic number
		inputField = FunctionToolbox.getInputField("OCA_AN", "Account basic number", "Account basic number", "A", "6", "");
		inputField.setUpperCase(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("OCA_AS", "Account suffix", "Account suffix", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("001");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", false, "Y"));

		// Account type
		inputField = FunctionToolbox.getInputField("OCA_ACT", "Account type", "Account type", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("C5R55R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C5R55R", "J", false, "N"));

		// Account short name
		inputField = FunctionToolbox.getInputField("OCA_SHN", "Account short name", "Account short name", "A", "15", "");
		inputField.setUpperCase(false);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", false, ""));

		// Account currency
		inputField = FunctionToolbox.getInputField("OCA_CCY", "Account currency", "Account currency", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("GBP");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Currency");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "T", false, "N"));

		// Date account opened
		inputField = FunctionToolbox.getInputField("OCA_OAD", "Date account opened", "Date account opened", "D", "7", "0");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", false, "N"));

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addScreen3(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("DEPOSIT", "Deposit", "Deposit");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		DisplayGroup groupKeys = new DisplayGroup("+KEYS", "Deposit", "Deposit");
		attributeSet.addItem(groupKeys);

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Customer mnemonic
		inputField = FunctionToolbox.getInputField("RDS_CUS", "Customer mnemonic", "Customer mnemonic", "A", "6", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("Customer");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "A", false, ""));

		// Customer location
		inputField = FunctionToolbox.getInputField("RDS_CLC", "Customer location", "Customer location", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Branch mnemonic
		inputField = FunctionToolbox.getInputField("RDS_BRNM", "Branch mnemonic", "Branch mnemonic", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("Branch");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Deal type
		inputField = FunctionToolbox.getInputField("RDS_DLP", "Deal type", "Deal type", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("OBR37R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OBR37R", "6", false, "N"));

		// Deal reference
		inputField = FunctionToolbox.getInputField("RDS_DLR", "Deal reference", "Deal reference", "A", "13", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Reference");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OSR34R", "", false, "Y"));

		// Deal amount
		inputField = FunctionToolbox.getInputField("RDS_DLA", "Deal amount", "Deal amount", "P", "15", "0");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Amount");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "A", false, ""));

		// Currency mnemonic
		inputField = FunctionToolbox.getInputField("RDS_CCY", "Currency mnemonic", "Currency mnemonic", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("");
		displayAttributes.setPrompt("C8R01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C8R01R", "T", false, "N"));

		// Start date
		inputField = FunctionToolbox.getInputField("RDS_SDT", "Start date", "Start date", "D", "7", "0");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// Maturity date
		inputField = FunctionToolbox.getInputField("RDS_MDT", "Maturity date", "Maturity date", "D", "7", "0");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// Contract date
		inputField = FunctionToolbox.getInputField("RDS_CTRD", "Contract date", "Contract date", "D", "7", "0");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, ""));

		// Period code
		inputField = FunctionToolbox.getInputField("RDS_PRC", "Period code", "Period code", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("JRR01R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "JRR01R", "", false, "N"));

		DisplayGroup groupFunding = new DisplayGroup("+FUNDING", "Funding settlement details", "Funding settlement details");
		attributeSet.addItem(groupFunding);

		// Funding settlement transfer method
		inputField = FunctionToolbox.getInputField("RDS_XMF", "Funding transfer method", "Funding transfer method", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Transfer method");
		displayAttributes.setPrompt("D1R02R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D1R02R", "", false, "N"));

		groupFunding.addItem(displayAttributes);

		// Funding settlement account branch
		inputField = FunctionToolbox.getInputField("RDS_ABF", "Funding account branch", "Funding account branch", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Account");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupFunding.addItem(displayAttributes);

		// Funding settlement account basic number
		inputField = FunctionToolbox.getInputField("RDS_ANF", "Funding account basic number", "Funding account basic number", "A",
						"6", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupFunding.addItem(displayAttributes);

		// Funding settlement account suffix
		inputField = FunctionToolbox.getInputField("RDS_ASF", "Funding account suffix", "Funding account suffix", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		displayAttributes.setPrompt("SCR10R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SCR10R", "", false, ""));

		groupFunding.addItem(displayAttributes);

		DisplayGroup groupMaturity = new DisplayGroup("+MATURITY", "Maturity settlement details", "Maturity settlement details");
		attributeSet.addItem(groupMaturity);

		// Maturity settlement transfer method
		inputField = FunctionToolbox.getInputField("RDS_XMM", "Maturity transfer method", "Maturity transfer method", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Transfer method");
		displayAttributes.setPrompt("D1R02R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D1R02R", "", false, "N"));

		groupMaturity.addItem(displayAttributes);

		// Maturity settlement account branch
		inputField = FunctionToolbox.getInputField("RDS_ABM", "Maturity account branch", "Maturity account branch", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Account");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupMaturity.addItem(displayAttributes);

		// Maturity settlement account basic number
		inputField = FunctionToolbox.getInputField("RDS_ANM", "Maturity  account basic number", "Maturity account basic number",
						"A", "6", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupMaturity.addItem(displayAttributes);

		// Maturity settlement account suffix
		inputField = FunctionToolbox.getInputField("RDS_ASM", "Maturity account suffix", "Maturity account suffix", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		displayAttributes.setPrompt("SCR10R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SCR10R", "", false, ""));

		groupMaturity.addItem(displayAttributes);

		DisplayGroup groupInterest = new DisplayGroup("+INTEREST", "Interest settlement details", "Interest settlement details");
		attributeSet.addItem(groupInterest);

		// Interest settlement transfer method
		inputField = FunctionToolbox.getInputField("RDS_XMI", "Interest transfer method", "Interest transfer method", "A", "2", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Transfer method");
		displayAttributes.setPrompt("D1R02R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D1R02R", "", false, "N"));

		groupInterest.addItem(displayAttributes);

		// Interest settlement account branch
		inputField = FunctionToolbox.getInputField("RDS_ABI", "Interest account branch", "Interest account branch", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setLabel("Account");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupInterest.addItem(displayAttributes);

		// Interest settlement account basic number
		inputField = FunctionToolbox.getInputField("RDS_ANI", "Interest account basic number", "Interest account basic number",
						"A", "6", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupInterest.addItem(displayAttributes);

		// Interest settlement account suffix
		inputField = FunctionToolbox.getInputField("RDS_ASI", "Interest account suffix", "Interest account suffix", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		displayAttributes.setPrompt("SCR10R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SCR10R", "", false, ""));

		groupInterest.addItem(displayAttributes);

		return fieldSetWrapper;
	}
	private DisplayFieldSetWrapper addScreen4(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("TRANSACT", "Transactions", "Transactions");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		DisplayGroup groupKeys = new DisplayGroup("+KEYS", "Customer", "Customer key fields");
		attributeSet.addItem(groupKeys);
		DisplayGroup groupAccntPost = new DisplayGroup("+ACCNTPOST", "Account posting", "Account posting");
		attributeSet.addItem(groupAccntPost);
		DisplayGroup groupContraPost = new DisplayGroup("+CONTRAPOST", "Contra posting", "Contra posting");
		attributeSet.addItem(groupContraPost);

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Account branch
		inputField = FunctionToolbox.getInputField("ASC_AB", "Account branch", "Account branch", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("Account");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SCR10R", "", false, ""));

		groupAccntPost.addItem(displayAttributes);

		// Account basic number
		inputField = FunctionToolbox.getInputField("ASC_AN", "Account basic number", "Account basic number", "A", "6", "");
		inputField.setUpperCase(true);
		// inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupAccntPost.addItem(displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("ASC_AS", "Account suffix", "Account suffix", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupAccntPost.addItem(displayAttributes);

		// Value from date
		inputField = FunctionToolbox.getInputField("ASC_VFR", "Value from date", "Value from date", "D", "7", "0");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "A", true, ""));

		groupAccntPost.addItem(displayAttributes);

		// Amount
		inputField = FunctionToolbox.getInputField("ASC_AMA", "Amount", "Amount", "P", "15", "0");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "A", false, ""));

		groupAccntPost.addItem(displayAttributes);

		// Reference
		inputField = FunctionToolbox.getInputField("ASC_DRF", "Reference", "Reference", "A", "16", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "A", false, ""));

		groupAccntPost.addItem(displayAttributes);

		// Transaction code
		inputField = FunctionToolbox.getInputField("ASC_TCD", "Transaction code", "Transaction code", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("CTR56R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CTR56R", "E", false, "N"));

		groupAccntPost.addItem(displayAttributes);

		// Contra account branch
		inputField = FunctionToolbox.getInputField("ASD_AB", "Account branch", "Account branch", "A", "4", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setLabel("Account");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupContraPost.addItem(displayAttributes);

		// Contra account basic number
		inputField = FunctionToolbox.getInputField("ASD_AN", "Account basic number", "Account basic number", "A", "6", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		groupContraPost.addItem(displayAttributes);

		// Contra account suffix
		inputField = FunctionToolbox.getInputField("ASD_AS", "Account suffix", "Account suffix", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setLabel("");
		displayAttributes.setPrompt("SCR10R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SCR10R", "", false, "N"));

		groupContraPost.addItem(displayAttributes);

		// Contra transaction code
		inputField = FunctionToolbox.getInputField("ASD_TCD", "Transaction code", "Transaction code", "A", "3", "");
		inputField.setUpperCase(true);
		inputField.setInitialValue("");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("CTR56R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "CTR56R", "E", false, "N"));

		groupContraPost.addItem(displayAttributes);

		return fieldSetWrapper;
	}
	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet;
		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "ANX", "ANX", "V30F", "Add/maint customer extended details",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "ANC", "ANC", "G01A", "Add new customer",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "CAA", "CAA", "H60F", "Customer and account addresses",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "MCO", "MCO", "G09M", "Customer other details",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "OCA", "OCA", "H38A", "Open customer accounr",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "RDS", "RDS", "G36A", "Open customer accounr",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "ASC", "ASI", "H15A", "Add sundry item",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "ASD", "ASI", "H15A", "Add sundry item",
						IEquationStandardObject.FCT_ADD);
		fg.addUpdateAPIFieldSet(apiFieldSet);

		fg.getFunction().addReservedFieldSet(Function.CRM_FIELDSET, "Description of CRM");
		fg.getFunction().addReservedFieldSet(Function.EFC_FIELDSET, "Description of EFC");
		fg.getFunction().addReservedFieldSet(Function.GY_FIELDSET, "Description of GY");
		fg.getFunction().addReservedFieldSet(Function.G5_FIELDSET, "Description of G5");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// add validate mapping

		fg.addValidateMappingToPV("PRIMARY", "ANC_CUS", "PRIMARY", "ANC_CLC", "GFR70R", "$GFCUS");
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CLC", "GFR70R", "$GFCUS", "PRIMARY", "ANC_CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CLC", "GFR70R", "$GFCUS", "DEPOSIT", "RDS_CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingToPV("PRIMARY", "ANC_CLC", "PRIMARY", "ANC_CLC", "GFR70R", "$GFCLC");
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CLC", "GFR70R", "$GFCLC", "PRIMARY", "ANC_CLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CLC", "GFR70R", "$GFCLC", "DEPOSIT", "RDS_CLC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CLC", "GFR70R", "$GFCUN", "DEPOSIT", "RDS_CUS", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CLC", "GFR70R", "$GFCUN", "DEPOSIT", "RDS_CLC", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingToPV("PRIMARY", "ANC_DAS", "PRIMARY", "ANC_CLC", "GFR70R", "$GFDAS");

		fg.addValidateMappingToPV("PRIMARY", "ANC_CUN", "PRIMARY", "ANC_CUN", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CUN", "GWV59R", "$V59DES", "PRIMARY", "ANC_CUN", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "ANC_CPNC", "PRIMARY", "ANC_CPNC", "GFV71R", "$ACCNO");
		fg
						.addValidateMappingFromPV("PRIMARY", "ANC_CPNC", "GFV71R", "$ACCNO", "PRIMARY", "ANC_CPNC",
										MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CPNC", "GFV71R", "$ACCNO", "ACCOUNT", "OCA_AN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CPNC", "GFV71R", "$CUN", "PRIMARY", "ANC_CPNC", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CPNC", "GFV71R", "$ACCNO", "TRANSACT", "ASC_AN", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "ANC_DAS", "PRIMARY", "ANC_DAS", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "ANC_DAS", "GWV59R", "$V59DES", "PRIMARY", "ANC_DAS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_DAS", "GWV59R", "$V59DES", "ACCOUNT", "OCA_SHN", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "ANC_CTP", "PRIMARY", "ANC_CTP", "C4R54R", "$C4CTP");
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CTP", "C4R54R", "$C4CTP", "PRIMARY", "ANC_CTP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CTP", "C4R54R", "$C4CTD", "PRIMARY", "ANC_CTP", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("PRIMARY", "ANC_BRNM", "PRIMARY", "ANC_BRNM", "CAR73R", "$CABRNM");
		fg.addValidateMappingFromPV("PRIMARY", "ANC_BRNM", "CAR73R", "$CABRNM", "PRIMARY", "ANC_BRNM",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_BRNM", "CAR73R", "$CABRNM", "DEPOSIT", "RDS_BRNM",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_BRNM", "CAR73R", "$CABNM", "PRIMARY", "ANC_BRNM", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_BRNM", "CAR73R", "$CABNM", "DEPOSIT", "RDS_BRNM", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_BRNM", "CAR73R", "$CABBN", "ACCOUNT", "OCA_AB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_BRNM", "CAR73R", "$CABBN", "TRANSACT", "ASC_AB", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "ANC_CRB1", "PRIMARY", "ANC_CRB1", "D4R47R", "$D4BRR");
		fg.addValidateMappingPVScript("PRIMARY", "ANC_CRB1", "D4R47R", "$D4DTE", "''");
		fg
						.addValidateMappingFromPV("PRIMARY", "ANC_CRB1", "D4R47R", "$D4BRR", "PRIMARY", "ANC_CRB1",
										MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CRB1", "D4R47R", "$D4DSC", "PRIMARY", "ANC_CRB1", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("PRIMARY", "ANC_CRB2", "PRIMARY", "ANC_CRB2", "D4R47R", "$D4BRR");
		fg.addValidateMappingPVScript("PRIMARY", "ANC_CRB2", "D4R47R", "$D4DTE", "''");
		fg
						.addValidateMappingFromPV("PRIMARY", "ANC_CRB2", "D4R47R", "$D4BRR", "PRIMARY", "ANC_CRB2",
										MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ANC_CRB2", "D4R47R", "$D4DSC", "PRIMARY", "ANC_CRB2", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("PRIMARY", "ANX_EAD1", "PRIMARY", "ANX_EAD1", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "ANX_EAD1", "GWV59R", "$V59DES", "PRIMARY", "ANX_EAD1",
						MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "CAA_NA1", "PRIMARY", "CAA_NA1", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "CAA_NA1", "GWV59R", "$V59DES", "PRIMARY", "CAA_NA1", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "CAA_NA2", "PRIMARY", "CAA_NA2", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "CAA_NA2", "GWV59R", "$V59DES", "PRIMARY", "CAA_NA2", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "CAA_NA3", "PRIMARY", "CAA_NA3", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "CAA_NA3", "GWV59R", "$V59DES", "PRIMARY", "CAA_NA3", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "CAA_NA4", "PRIMARY", "CAA_NA4", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "CAA_NA4", "GWV59R", "$V59DES", "PRIMARY", "CAA_NA4", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "CAA_NA5", "PRIMARY", "CAA_NA5", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("PRIMARY", "CAA_NA5", "GWV59R", "$V59DES", "PRIMARY", "CAA_NA5", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "MCO_C1R", "PRIMARY", "MCO_C1R", "GKR40R", "$GKC1R");
		fg.addValidateMappingFromPV("PRIMARY", "MCO_C1R", "GKR40R", "$GKC1R", "PRIMARY", "MCO_C1R", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "MCO_C1R", "GKR40R", "$GKC1D", "PRIMARY", "MCO_C1R", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("ACCOUNT", "OCA_AN", "ACCOUNT", "OCA_AN", "GFV71R", "$ACCNO");
		// fg.addValidateMappingFromPV("ACCOUNT", "OCA_AN", "GFV71R", "$ACCNO", "TRANSACT", "ASC_AN",
		// MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("ACCOUNT", "OCA_AB", "ACCOUNT", "OCA_AS", "GWR76R", "$R76AB");
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AB", "DEPOSIT", "RDS_ABM", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AB", "DEPOSIT", "RDS_ABF", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AB", "DEPOSIT", "RDS_ABI", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AB", "TRANSACT", "ASD_AB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingToPV("ACCOUNT", "OCA_AN", "ACCOUNT", "OCA_AS", "GWR76R", "$R76AN");
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AN", "DEPOSIT", "RDS_ANM", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AN", "DEPOSIT", "RDS_ANI", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingToPV("ACCOUNT", "OCA_AS", "ACCOUNT", "OCA_AS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AS", "TRANSACT", "ASC_AS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AS", "ACCOUNT", "OCA_AS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AS", "DEPOSIT", "RDS_ASM", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_AS", "GWR76R", "$R76AS", "DEPOSIT", "RDS_ASI", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingToPV("ACCOUNT", "OCA_CCY", "ACCOUNT", "OCA_AS", "GWR76R", "$R76CY");
		fg.addValidateMappingToPV("ACCOUNT", "OCA_ACT", "ACCOUNT", "OCA_AS", "GWR76R", "$R76AT");

		fg.addValidateMappingToPV("ACCOUNT", "OCA_ACT", "ACCOUNT", "OCA_ACT", "C5R55R", "$C5ATP");
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_ACT", "C5R55R", "$C5ATP", "ACCOUNT", "OCA_ACT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_ACT", "C5R55R", "$C5ATD", "ACCOUNT", "OCA_ACT", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("ACCOUNT", "OCA_SHN", "ACCOUNT", "OCA_SHN", "GWV59R", "$V59DES");

		fg.addValidateMappingToPV("ACCOUNT", "OCA_CCY", "ACCOUNT", "OCA_CCY", "C8R01R", "C8@CCY");
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_CCY", "C8R01R", "C8@CCY", "ACCOUNT", "OCA_CCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_CCY", "C8R01R", "C8@CCY", "DEPOSIT", "RDS_CCY", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_CCY", "C8R01R", "C8@CCN", "DEPOSIT", "RDS_ASF", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_CCY", "C8R01R", "C8@CCN", "TRANSACT", "ASD_AS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_CCY", "C8R01R", "C8@CUR", "ACCOUNT", "OCA_CCY", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("ACCOUNT", "OCA_OAD", "ACCOUNT", "OCA_OAD", "GWV94R", "$V94DM");
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_OAD", "GWV94R", "$94OF1", "ACCOUNT", "OCA_OAD", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("ACCOUNT", "OCA_OAD", "GWV94R", "$94OF3", "ACCOUNT", "OCA_OAD", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_DLP", "DEPOSIT", "RDS_DLP", "OBR37R", "$OBDLP");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_DLP", "OBR37R", "$OBDLP", "DEPOSIT", "RDS_DLP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_DLP", "OBR37R", "$OBDPD", "DEPOSIT", "RDS_DLP", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_BRNM", "DEPOSIT", "RDS_DLR", "OSR34R", "$BRNM");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_DLP", "DEPOSIT", "RDS_DLR", "OSR34R", "$DLP");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_DLR", "DEPOSIT", "RDS_DLR", "OSR34R", "$DLR");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_DLR", "OSR34R", "$DLR", "DEPOSIT", "RDS_DLR", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_CUS", "DEPOSIT", "RDS_CUS", "GFR70R", "$GFCUS");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_CLC", "DEPOSIT", "RDS_CUS", "GFR70R", "$GFCLC");

		fg.addValidateMappingToPV("DEPOSIT", "RDS_CCY", "DEPOSIT", "RDS_CCY", "C8R01R", "C8@CCY");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_CCY", "C8R01R", "C8@CUR", "DEPOSIT", "RDS_CCY", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("ACCOUNT", "OCA_CCY", "DEPOSIT", "RDS_DLA", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_DLA", "DEPOSIT", "RDS_DLA", "GWV30R", "$INPAM");
		fg.addValidateMappingPVScript("DEPOSIT", "RDS_DLA", "GWV30R", "$NDPAM", "''");
		fg.addValidateMappingPVScript("DEPOSIT", "RDS_DLA", "GWV30R", "$NODIG", "'15'");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_DLA", "GWV30R", "$EDTAM", "DEPOSIT", "RDS_DLA", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_DLA", "GWV30R", "$NUMAM", "DEPOSIT", "RDS_DLA", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_SDT", "DEPOSIT", "RDS_SDT", "GWV94R", "$V94DM");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_SDT", "GWV94R", "$94OF1", "DEPOSIT", "RDS_SDT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_SDT", "GWV94R", "$94OF3", "DEPOSIT", "RDS_SDT", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_MDT", "DEPOSIT", "RDS_MDT", "GWV94R", "$V94DM");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_MDT", "GWV94R", "$94OF1", "DEPOSIT", "RDS_MDT", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_MDT", "GWV94R", "$94OF3", "DEPOSIT", "RDS_MDT", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_CTRD", "DEPOSIT", "RDS_CTRD", "GWV94R", "$V94DM");
		fg
						.addValidateMappingFromPV("DEPOSIT", "RDS_CTRD", "GWV94R", "$94OF1", "DEPOSIT", "RDS_CTRD",
										MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_CTRD", "GWV94R", "$94OF3", "DEPOSIT", "RDS_CTRD", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_ABF", "DEPOSIT", "RDS_ASF", "SCR10R", "$R76AB");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_ANF", "DEPOSIT", "RDS_ASF", "SCR10R", "$R76AN");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_ASF", "DEPOSIT", "RDS_ASF", "SCR10R", "$R76AS");
		fg.addValidateMappingPrimitiveFieldScript("DEPOSIT", "RDS_ANF", "'820110'");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASF", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ASF", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASF", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ABF", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASF", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ANF", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_XMF", "DEPOSIT", "RDS_XMF", "D1R02R", "$D1XM");
		fg.addValidateMappingPrimitiveFieldScript("DEPOSIT", "RDS_XMF", "'AC'");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_XMF", "D1R02R", "$D1XMD", "DEPOSIT", "RDS_XMF", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_ABM", "DEPOSIT", "RDS_ASM", "SCR10R", "$R76AB");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_ANM", "DEPOSIT", "RDS_ASM", "SCR10R", "$R76AN");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_ASM", "DEPOSIT", "RDS_ASM", "SCR10R", "$R76AS");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASM", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ASM", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASM", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ABM", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASM", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ANM", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_XMM", "DEPOSIT", "RDS_XMM", "D1R02R", "$D1XM");
		fg.addValidateMappingPrimitiveFieldScript("DEPOSIT", "RDS_XMM", "'AC'");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_XMM", "D1R02R", "$D1XMD", "DEPOSIT", "RDS_XMM", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_ABI", "DEPOSIT", "RDS_ASI", "SCR10R", "$R76AB");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_ANI", "DEPOSIT", "RDS_ASI", "SCR10R", "$R76AN");
		fg.addValidateMappingToPV("DEPOSIT", "RDS_ASI", "DEPOSIT", "RDS_ASI", "SCR10R", "$R76AS");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASI", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ASI", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASI", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ABI", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_ASI", "SCR10R", "$R76SN", "DEPOSIT", "RDS_ANI", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_XMI", "DEPOSIT", "RDS_XMI", "D1R02R", "$D1XM");
		fg.addValidateMappingPrimitiveFieldScript("DEPOSIT", "RDS_XMI", "'AC'");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_XMI", "D1R02R", "$D1XMD", "DEPOSIT", "RDS_XMI", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("DEPOSIT", "RDS_PRC", "DEPOSIT", "RDS_PRC", "JRR01R", "$JRPRC");
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_PRC", "JRR01R", "$JRPRC", "DEPOSIT", "RDS_PRC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("DEPOSIT", "RDS_PRC", "JRR01R", "$JRPRD", "DEPOSIT", "RDS_PRC", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("TRANSACT", "ASC_AB", "TRANSACT", "ASC_AB", "SCR10R", "$R76AB");
		fg.addValidateMappingToPV("TRANSACT", "ASC_AN", "TRANSACT", "ASC_AB", "SCR10R", "$R76AN");
		fg.addValidateMappingToPV("TRANSACT", "ASC_AS", "TRANSACT", "ASC_AB", "SCR10R", "$R76AS");
		fg.addValidateMappingToPV("ACCOUNT", "OCA_CCY", "TRANSACT", "ASC_AB", "SCR10R", "$R76CY");
		fg.addValidateMappingFromPV("TRANSACT", "ASC_AB", "SCR10R", "$R76SN", "TRANSACT", "ASC_AS", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("TRANSACT", "ASC_AB", "SCR10R", "$R76SN", "TRANSACT", "ASC_AN", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("TRANSACT", "ASC_AB", "SCR10R", "$R76SN", "TRANSACT", "ASC_AB", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("TRANSACT", "ASC_VFR", "TRANSACT", "ASC_VFR", "GWV94R", "$V94DM");
		fg
						.addValidateMappingFromPV("TRANSACT", "ASC_VFR", "GWV94R", "$94OF1", "TRANSACT", "ASC_VFR",
										MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("TRANSACT", "ASC_VFR", "GWV94R", "$94OF3", "TRANSACT", "ASC_VFR", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("TRANSACT", "ASC_DRF", "TRANSACT", "ASC_DRF", "GWV59R", "$V59DES");
		fg.addValidateMappingFromPV("TRANSACT", "ASC_DRF", "GWV59R", "$V59DES", "TRANSACT", "ASC_DRF",
						MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("ACCOUNT", "OCA_CCY", "TRANSACT", "ASC_AMA", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("TRANSACT", "ASC_AMA", "TRANSACT", "ASC_AMA", "GWV30R", "$INPAM");
		fg.addValidateMappingPVScript("TRANSACT", "ASC_AMA", "GWV30R", "$NDPAM", "''");
		fg.addValidateMappingPVScript("TRANSACT", "ASC_AMA", "GWV30R", "$NODIG", "'15'");
		fg.addValidateMappingFromPV("TRANSACT", "ASC_AMA", "GWV30R", "$EDTAM", "TRANSACT", "ASC_AMA", MappingToolbox.TYPE_OUTPUT);
		fg
						.addValidateMappingFromPV("TRANSACT", "ASC_AMA", "GWV30R", "$NUMAM", "TRANSACT", "ASC_AMA",
										MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("TRANSACT", "ASC_TCD", "TRANSACT", "ASC_TCD", "CTR56R", "$56TCD");
		fg
						.addValidateMappingFromPV("TRANSACT", "ASC_TCD", "CTR56R", "$56TCD", "TRANSACT", "ASC_TCD",
										MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("TRANSACT", "ASC_TCD", "CTR56R", "$56TCN", "TRANSACT", "ASC_TCD", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("TRANSACT", "ASD_AB", "TRANSACT", "ASD_AS", "SCR10R", "$R76AB");
		fg.addValidateMappingToPV("TRANSACT", "ASD_AN", "TRANSACT", "ASD_AS", "SCR10R", "$R76AN");
		fg.addValidateMappingToPV("TRANSACT", "ASD_AS", "TRANSACT", "ASD_AS", "SCR10R", "$R76AS");
		fg.addValidateMappingPrimitiveFieldScript("TRANSACT", "ASD_AN", "'820110'");
		fg.addValidateMappingFromPV("TRANSACT", "ASD_AS", "SCR10R", "$R76SN", "TRANSACT", "ASD_AS", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("TRANSACT", "ASD_AS", "SCR10R", "$R76SN", "TRANSACT", "ASD_AN", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("TRANSACT", "ASD_AS", "SCR10R", "$R76SN", "TRANSACT", "ASD_AB", MappingToolbox.TYPE_OUTPUT);

		fg.addValidateMappingToPV("TRANSACT", "ASD_TCD", "TRANSACT", "ASD_TCD", "CTR56R", "$56TCD");
		fg
						.addValidateMappingFromPV("TRANSACT", "ASD_TCD", "CTR56R", "$56TCD", "TRANSACT", "ASD_TCD",
										MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("TRANSACT", "ASD_TCD", "CTR56R", "$56TCN", "TRANSACT", "ASD_TCD", MappingToolbox.TYPE_OUTPUT);

		// add update mapping

		fg.addUpdateMappingScript("ANX", "MODE", "'A'");
		fg.addUpdateMapping("PRIMARY", "ANC_CUS", "ANX", "GZCUS");
		fg.addUpdateMapping("PRIMARY", "ANC_CLC", "ANX", "GZCLC");
		fg.addUpdateMapping("PRIMARY", "ANX_EAD1", "ANX", "GZEAD1");

		fg.addUpdateMapping("PRIMARY", "ANC_CUS", "ANC", "GZCUS");
		fg.addUpdateMapping("PRIMARY", "ANC_CLC", "ANC", "GZCLC");
		fg.addUpdateMapping("PRIMARY", "ANC_CUN", "ANC", "GZCUN");
		fg.addUpdateMapping("PRIMARY", "ANC_CPNC", "ANC", "GZCPNC");
		fg.addUpdateMapping("PRIMARY", "ANC_DAS", "ANC", "GZDAS");
		fg.addUpdateMapping("PRIMARY", "ANC_CTP", "ANC", "GZCTP");
		fg.addUpdateMappingScript("ANC", "GZCUBD", "'N'");
		fg.addUpdateMapping("PRIMARY", "ANC_BRNM", "ANC", "GZBRNM");
		fg.addUpdateMapping("PRIMARY", "ANC_CRB1", "ANC", "GZCRB1");
		fg.addUpdateMapping("PRIMARY", "ANC_CRB2", "ANC", "GZCRB2");

		fg.addUpdateMappingScript("CAA", "MODE", "'A'");
		fg.addUpdateMapping("PRIMARY", "ANC_CUS", "CAA", "GZCUS");
		fg.addUpdateMapping("PRIMARY", "ANC_CLC", "CAA", "GZCLC");
		fg.addUpdateMappingScript("CAA", "GZAB", "''");
		fg.addUpdateMappingScript("CAA", "GZAN", "''");
		fg.addUpdateMappingScript("CAA", "GZAS", "''");
		fg.addUpdateMappingScript("CAA", "GZPRM", "''");
		fg.addUpdateMapping("PRIMARY", "CAA_NA1", "CAA", "GZNA1");
		fg.addUpdateMapping("PRIMARY", "CAA_NA2", "CAA", "GZNA2");
		fg.addUpdateMapping("PRIMARY", "CAA_NA3", "CAA", "GZNA3");
		fg.addUpdateMapping("PRIMARY", "CAA_NA4", "CAA", "GZNA4");
		fg.addUpdateMapping("PRIMARY", "CAA_NA5", "CAA", "GZNA5");

		fg.addUpdateMapping("PRIMARY", "ANC_CUS", "MCO", "GZCUS");
		fg.addUpdateMapping("PRIMARY", "ANC_CLC", "MCO", "GZCLC");
		fg.addUpdateMapping("PRIMARY", "MCO_C1R", "MCO", "GZC1R");

		fg.addUpdateMappingScript("OCA", "MODE", "'A'");
		fg.addUpdateMapping("ACCOUNT", "OCA_AB", "OCA", "GZAB");
		fg.addUpdateMapping("ACCOUNT", "OCA_AN", "OCA", "GZAN");
		fg.addUpdateMapping("ACCOUNT", "OCA_AS", "OCA", "GZAS");
		fg.addUpdateMapping("ACCOUNT", "OCA_ACT", "OCA", "GZACT");
		fg.addUpdateMapping("ACCOUNT", "OCA_SHN", "OCA", "GZSHN");
		fg.addUpdateMapping("ACCOUNT", "OCA_CCY", "OCA", "GZCCY");
		fg.addUpdateMapping("ACCOUNT", "OCA_OAD", "OCA", "GZOAD");

		fg.addUpdateMapping("DEPOSIT", "RDS_DLP", "RDS", "GZDLP");
		fg.addUpdateMappingScript("RDS", "GZTDT", "'D'");
		fg.addUpdateMappingScript("RDS", "GZAPP", "'RD'");
		fg.addUpdateMapping("DEPOSIT", "RDS_DLR", "RDS", "GZDLR");
		fg.addUpdateMapping("DEPOSIT", "RDS_BRNM", "RDS", "GZBRNM");
		fg.addUpdateMapping("DEPOSIT", "RDS_CUS", "RDS", "GZCUS");
		fg.addUpdateMapping("DEPOSIT", "RDS_CLC", "RDS", "GZCLC");
		fg.addUpdateMapping("DEPOSIT", "RDS_CCY", "RDS", "GZCCY");
		fg.addUpdateMapping("DEPOSIT", "RDS_DLA", "RDS", "GZDLA");
		fg.addUpdateMapping("DEPOSIT", "RDS_SDT", "RDS", "GZSDT");
		fg.addUpdateMapping("DEPOSIT", "RDS_MDT", "RDS", "GZMDT");
		fg.addUpdateMapping("DEPOSIT", "RDS_CTRD", "RDS", "GZCTRD");
		fg.addUpdateMapping("DEPOSIT", "RDS_ABF", "RDS", "GZABF");
		fg.addUpdateMapping("DEPOSIT", "RDS_ANF", "RDS", "GZANF");
		fg.addUpdateMapping("DEPOSIT", "RDS_ASF", "RDS", "GZASF");
		fg.addUpdateMapping("DEPOSIT", "RDS_XMF", "RDS", "GZXMF");
		fg.addUpdateMapping("DEPOSIT", "RDS_ABM", "RDS", "GZABM");
		fg.addUpdateMapping("DEPOSIT", "RDS_ANM", "RDS", "GZANM");
		fg.addUpdateMapping("DEPOSIT", "RDS_ASM", "RDS", "GZASM");
		fg.addUpdateMapping("DEPOSIT", "RDS_XMM", "RDS", "GZXMM");
		fg.addUpdateMapping("DEPOSIT", "RDS_ABI", "RDS", "GZABI");
		fg.addUpdateMapping("DEPOSIT", "RDS_ANI", "RDS", "GZANI");
		fg.addUpdateMapping("DEPOSIT", "RDS_ASI", "RDS", "GZASI");
		fg.addUpdateMapping("DEPOSIT", "RDS_XMI", "RDS", "GZXMI");
		fg.addUpdateMapping("DEPOSIT", "RDS_PRC", "RDS", "GZPRC");
		fg.addUpdateMappingScript("RDS", "GZYROL", "'A'");
		fg.addUpdateMapping("DEPOSIT", "RDS_CCY", "RDS", "GZCCYM");
		fg.addUpdateMapping("DEPOSIT", "RDS_CCY", "RDS", "GZCCYF");
		fg.addUpdateMapping("DEPOSIT", "RDS_CCY", "RDS", "GZCCYI");

		fg.addUpdateMapping("TRANSACT", "ASC_AB", "ASC", "GZAB");
		fg.addUpdateMapping("TRANSACT", "ASC_AN", "ASC", "GZAN");
		fg.addUpdateMapping("TRANSACT", "ASC_AS", "ASC", "GZAS");
		fg.addUpdateMapping("PRIMARY", "ANC_BRNM", "ASC", "GZBRNM");
		fg.addUpdateMappingScript("ASC", "GZPBR", "'TEST'");
		fg.addUpdateMapping("TRANSACT", "ASC_VFR", "ASC", "GZVFR");
		fg.addUpdateMapping("TRANSACT", "ASC_DRF", "ASC", "GZDRF");
		fg.addUpdateMapping("TRANSACT", "ASC_AMA", "ASC", "GZAMA");
		fg.addUpdateMapping("TRANSACT", "ASC_TCD", "ASC", "GZTCD");
		fg.addUpdateMapping("ACCOUNT", "OCA_CCY", "ASC", "GZCCY");
		fg.addUpdateMappingScript("ASC", "GZNPE", "'1'");

		fg.addUpdateMapping("TRANSACT", "ASD_AB", "ASD", "GZAB");
		fg.addUpdateMapping("TRANSACT", "ASD_AN", "ASD", "GZAN");
		fg.addUpdateMapping("TRANSACT", "ASD_AS", "ASD", "GZAS");
		fg.addUpdateMapping("PRIMARY", "ANC_BRNM", "ASD", "GZBRNM");
		fg.addUpdateMappingScript("ASD", "GZPBR", "'TEST'");
		fg.addUpdateMapping("TRANSACT", "ASC_VFR", "ASD", "GZVFR");
		fg.addUpdateMapping("TRANSACT", "ASC_DRF", "ASD", "GZDRF");
		fg.addUpdateMapping("TRANSACT", "ASC_AMA", "ASD", "GZAMA");
		fg.addUpdateMapping("TRANSACT", "ASD_TCD", "ASD", "GZTCD");
		fg.addUpdateMapping("ACCOUNT", "OCA_CCY", "ASD", "GZCCY");
		fg.addUpdateMappingScript("ASD", "GZNPE", "'1'");

	}

	public void test()
	{
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("ZT1", "New Customer and Product", "New Customer, Account and Deposit",
							"com.misys.equation.screens", "com.misys.equation.screens.layout");
			fg.getFunction().setAllowedAdd(true);
			fg.getFunction().setAllowedMaint(true);
			fg.getFunction().setAllowedDel(false);
			fg.getFunction().setAllowedEnq(false);
			fg.getFunction().setExtendedBusinessHoursAllowed(false);
			fg.getFunction().setApplyDuringExtInput(true);
			fg.getFunction().setApplyDuringRecovery(true);
			addScreen1(fg);
			addScreen2(fg);
			addScreen3(fg);
			addScreen4(fg);
			addUpdateAPI(fg);
			addMappings(fg);

			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), null, null);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
