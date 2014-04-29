package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;
import com.misys.equation.function.tools.TranslationToolbox;

public class AC32 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AC32.java 11921 2011-09-27 03:33:50Z ESTHER.WILLIAMS $";

	public AC32()
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
		AC32 test = new AC32();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSetParameter("PRIMARY", "GBL000196", "GBL000197");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Deal branch
		inputField = FunctionToolbox.getInputField("BRNM", "Deal branch", "This is the deal branch", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Deal type
		inputField = FunctionToolbox.getInputField("DLP", "Deal type", "This is the deal type", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Deal reference
		inputField = FunctionToolbox.getInputField("DLR", "Deal reference", "This is the deal reference", "A", "13", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Commitment reference
		inputField = FunctionToolbox
						.getInputField("CMR", "Commitment reference", "This is the commitment reference", "A", "13", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account branch
		inputField = FunctionToolbox.getInputField("AB", "Account branch", "This is the account branch", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account number
		inputField = FunctionToolbox.getInputField("AN", "Account number", "This is the account number", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account suffix
		inputField = FunctionToolbox.getInputField("AS", "Account suffix", "This is the account suffix", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Reference
		inputField = FunctionToolbox.getInputFieldParameter("REF", "GBL000110", "GBL000111", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction reference (DSEVNT key)
		inputField = FunctionToolbox.getInputField("TREF", "Transaction reference", "This is the transaction reference", "A", "22",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Credit account branch (DSEVNT key)
		inputField = FunctionToolbox.getInputField("CAB", "Credit account branch", "This is the credit account branch", "A", "4",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Credit account number (DSEVNT key)
		inputField = FunctionToolbox.getInputField("CAN", "Credit account number", "This is the credit account number", "A", "6",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Credit account suffix (DSEVNT key)
		inputField = FunctionToolbox.getInputField("CAS", "Credit account suffix", "This is the credit account suffix", "A", "3",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Extra reference (DSEVNT key)
		inputField = FunctionToolbox.getInputField("AREF", "Extra reference", "This is the extra reference", "A", "22", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction date (DSEVNT key)
		inputField = FunctionToolbox.getInputField("DDTD", "Transaction date", "This is the transaction date", "D", "7", "");
		// inputField.setExtendedAttribute(InputField.EXT_ATTRIB_DATE_INPUT8D);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction sequence (DSEVNT key)
		inputField = FunctionToolbox.getInputField("ESQN", "Transaction sequence", "This is the transaction sequence", "P", "16",
						"0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Event sequence number (DSEVNT key)
		inputField = FunctionToolbox.getInputField("EESQN", "Event sequence number", "This is the event sequence number", "P", "3",
						"0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction currency (DSEVNT key)
		inputField = FunctionToolbox
						.getInputField("TCCY", "Transaction currency", "This is the transaction currency", "A", "3", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction amount (DSEVNT key)
		inputField = FunctionToolbox.getInputField("TAMT", "Transaction amount", "This is the transaction amount", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Account type (DSEVENT)
		inputField = FunctionToolbox.getInputField("ATP", "Account type", "This is the account type", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge based on description
		inputField = FunctionToolbox.getInputFieldParameter("CHGS", "GBL000134", "GBL000135", "A", "30", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Event mnemonic
		inputField = FunctionToolbox.getInputFieldParameter("EVNM", "GBL000102", "GBL000103", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge component
		inputField = FunctionToolbox.getInputFieldParameter("ECNM", "GBL000136", "GBL000137", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge code
		inputField = FunctionToolbox.getInputFieldParameter("CHGC", "GBL000138", "GBL000139", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge currency
		inputField = FunctionToolbox.getInputFieldParameter("CCY", "GBL000140", "GBL000141", "A", "3", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge frequency
		inputField = FunctionToolbox.getInputFieldParameter("FRQ", "GBL000142", "GBL000143", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV13R", "", true, "N"));

		// Charge start date
		inputField = FunctionToolbox.getInputFieldParameter("SDTE", "GBL000144", "GBL000145", "D", "7", "");
		// inputField.setExtendedAttribute(InputField.EXT_ATTRIB_DATE_INPUT8D);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("CAL");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));

		// Charge end date
		inputField = FunctionToolbox.getInputFieldParameter("EDTE", "GBL000146", "GBL000147", "D", "7", "");
		// inputField.setExtendedAttribute(InputField.EXT_ATTRIB_DATE_INPUT8D);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("CAL");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV94R", "", true, "N"));

		// Input charge amount
		inputField = FunctionToolbox.getInputFieldParameter("AMT", "GBL000148", "GBL000149", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		// Charge percentage base code
		inputField = FunctionToolbox.getInputFieldParameter("PBRR", "GBL000150", "GBL000151", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("D4R47R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "D4R47R", "", true, "N"));

		// Charge percentage rate of base code
		inputField = FunctionToolbox.getInputField("PBCR", "Charge percentage rate of base code",
						"This is the charge percentage rate of base code", "P", "15", "9");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge percentage rate
		inputField = FunctionToolbox.getInputFieldParameter("PRAT", "GBL000152", "GBL000153", "P", "15", "9");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV28R", "", true, "N"));

		// Calculate on amount
		inputField = FunctionToolbox.getInputFieldParameter("CAMT", "GBL000154", "GBL000155", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setShowDescriptionAsValue(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Calculate on amount description
		inputField = FunctionToolbox.getInputField("COAD", "Calculate on amount description",
						"This is the calculate on amount description", "A", "35", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Minimum charge amount
		inputField = FunctionToolbox.getInputFieldParameter("MINC", "GBL000156", "GBL000157", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setShowDescriptionAsValue(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Maximum charge amount
		inputField = FunctionToolbox.getInputFieldParameter("MAXC", "GBL000158", "GBL000159", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setShowDescriptionAsValue(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Calculated charge amount
		inputField = FunctionToolbox.getInputFieldParameter("CALA", "GBL000160", "GBL000161", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setShowDescriptionAsValue(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Waived charge amount
		inputField = FunctionToolbox.getInputFieldParameter("WAMT", "GBL000162", "GBL000163", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV30R", "", true, "N"));

		// Charge amount
		inputField = FunctionToolbox.getInputFieldParameter("CHGA", "GBL000116", "GBL000117", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		displayAttributes.setShowDescriptionAsValue(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Calculation narrative 1
		inputField = FunctionToolbox.getInputFieldParameter("CNR1", "GBL000164", "GBL000165", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Calculation narrative 2
		inputField = FunctionToolbox.getInputFieldParameter("CNR2", "GBL000166", "GBL000167", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Calculation narrative 3
		inputField = FunctionToolbox.getInputFieldParameter("CNR3", "GBL000168", "GBL000169", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Calculation narrative 4
		inputField = FunctionToolbox.getInputFieldParameter("CNR4", "GBL000170", "GBL000171", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Funding account branch
		inputField = FunctionToolbox.getInputFieldParameter("FAB", "GBL000172", "GBL000173", "A", "4", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Funding account number
		inputField = FunctionToolbox.getInputFieldParameter("FAN", "GBL000174", "GBL000173", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Funding account suffix
		inputField = FunctionToolbox.getInputFieldParameter("FAS", "GBL000174", "GBL000173", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);
		displayAttributes.setPrompt("GWR76R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, "N"));

		// Capitalised charge?
		inputField = FunctionToolbox.getInputFieldParameter("CPI", "GBL000178", "GBL000179", "B", "1", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("YNO");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Force to fee?
		inputField = FunctionToolbox.getInputFieldParameter("FORC", "GBL000180", "GBL000181", "B", "1", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("YNO");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Posting narrative 1
		inputField = FunctionToolbox.getInputFieldParameter("PNR1", "GBL000182", "GBL000183", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Posting narrative 2
		inputField = FunctionToolbox.getInputFieldParameter("PNR2", "GBL000184", "GBL000185", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Posting narrative 3
		inputField = FunctionToolbox.getInputFieldParameter("PNR3", "GBL000186", "GBL000187", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Posting narrative 4
		inputField = FunctionToolbox.getInputFieldParameter("PNR4", "GBL000188", "GBL000189", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWV59R", "", true, "N"));

		// Sundry item
		inputField = FunctionToolbox.getInputFieldParameter("SRC", "GBL000190", "GBL000191", "A", "2", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// User code 1
		inputField = FunctionToolbox.getInputFieldParameter("UC1", "GBL000192", "GBL000193", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// User code 2
		inputField = FunctionToolbox.getInputFieldParameter("UC2", "GBL000194", "GBL000195", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge tax
		inputField = FunctionToolbox.getInputField("TAX", "Charge tax", "This is the charge tax", "P", "15", "9");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge excluded?
		inputField = FunctionToolbox.getInputField("EXCL", "Charge excluded?", "This is the Charge excluded flag", "B", "1", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Number of digits
		inputField = FunctionToolbox.getInputField("DIGIT", "Number of digits", "This is the number of digits for the amounts",
						"S", "2", "0");
		inputField.setInitialValue("15");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Number of decimal
		inputField = FunctionToolbox.getInputField("DECI", "Number of decimals", "This is the number of decimals for the amounts",
						"S", "1", "0");
		inputField.setInitialValue("0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge calculation method
		inputField = FunctionToolbox.getInputField("CCAL", "Charge calculation method", "This is the charge calculation method",
						"A", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Regular charge?
		inputField = FunctionToolbox.getInputField("REGF", "Regular charge?", "This is the regular charge flag", "A", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_NO);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Add text owner
		TranslationToolbox.addTextOwnerToDisplayAttributes(attributeSet, TextBean.OWNER_MISYS, FunctionGenerator.BASE_LANG);
		TranslationToolbox.addTextOwnerToInputFields(null, inputFieldSet, TextBean.OWNER_MISYS, FunctionGenerator.BASE_LANG);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg)
	{
	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		fg.getFunction().addReservedFieldSet(Function.CRM_FIELDSET, "");
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// validate mapping
		fg.addValidateMappingToPV("PRIMARY", "DIGIT", "PRIMARY", "AMT", "GWV30R", "$NODIG");
		fg.addValidateMappingToPV("PRIMARY", "DECI", "PRIMARY", "AMT", "GWV30R", "$NDPAM");
		fg.addValidateMappingToPV("PRIMARY", "DECI", "PRIMARY", "WAMT", "GWV30R", "$NDPAM");
		fg.addValidateMappingToPV("PRIMARY", "DIGIT", "PRIMARY", "WAMT", "GWV30R", "$NODIG");

		fg.addValidateMappingToPV("PRIMARY", "WAMT", "PRIMARY", "WAMT", "GWV30R", "$INPAM");
		fg.addValidateMappingToPV("PRIMARY", "CCY", "PRIMARY", "WAMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingFromPV("PRIMARY", "WAMT", "GWV30R", "$EDTAM", "PRIMARY", "WAMT", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "WAMT", "GWV30R", "$NUMAM", "PRIMARY", "WAMT", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "CCY", "PRIMARY", "AMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("PRIMARY", "AMT", "PRIMARY", "AMT", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("PRIMARY", "AMT", "GWV30R", "$EDTAM", "PRIMARY", "AMT", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "AMT", "GWV30R", "$NUMAM", "PRIMARY", "AMT", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "FAB", "PRIMARY", "FAS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "FAN", "PRIMARY", "FAS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "FAS", "PRIMARY", "FAS", "GWR76R", "$R76AS");
		fg.addValidateMappingFromPV("PRIMARY", "FAS", "GWR76R", "$R76AB", "PRIMARY", "FAB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "FAS", "GWR76R", "$R76AN", "PRIMARY", "FAN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "FAS", "GWR76R", "$R76AS", "PRIMARY", "FAS", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "CCY", "PRIMARY", "CAMT", "GWV30R", "$CCYAM");
		fg.addValidateMappingToPV("PRIMARY", "DECI", "PRIMARY", "CAMT", "GWV30R", "$NDPAM");
		fg.addValidateMappingToPV("PRIMARY", "DIGIT", "PRIMARY", "CAMT", "GWV30R", "$NODIG");
		fg.addValidateMappingToPV("PRIMARY", "CAMT", "PRIMARY", "CAMT", "GWV30R", "$INPAM");
		fg.addValidateMappingFromPV("PRIMARY", "CAMT", "GWV30R", "$EDTAM", "PRIMARY", "CAMT", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "CAMT", "GWV30R", "$NUMAM", "PRIMARY", "CAMT", MappingToolbox.TYPE_PRIMITIVE);

		fg.addValidateMappingToPV("PRIMARY", "PBRR", "PRIMARY", "PBRR", "D4R47R", "$D4BRR");
		fg.addValidateMappingFromPV("PRIMARY", "PBRR", "D4R47R", "$D4BRAR", "PRIMARY", "PBCR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "PBRR", "D4R47R", "$D4BRR", "PRIMARY", "PBRR", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "PBRR", "D4R47R", "$D4DSC", "PRIMARY", "PBRR", MappingToolbox.TYPE_OUTPUT);
		fg.addValidateMappingFromPV("PRIMARY", "PBRR", "D4R47R", "$D4BRAR", "PRIMARY", "PBCR", MappingToolbox.TYPE_INPUT);
		fg.addValidateMappingFromPV("PRIMARY", "PBRR", "D4R47R", "$D4BRAR", "PRIMARY", "PBCR", MappingToolbox.TYPE_OUTPUT);
	}

	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("AC3", "GBL000196", "GBL000197", "com.misys.equation.screens",
							"com.misys.equation.screens.layout");
			fg.setToParameter(null);
			addRecord1(fg);
			addLoadAPI(fg);
			addUpdateAPI(fg);
			addMappings(fg);

			// Write to DB
			FunctionToolboxStub.writeToDB(unit, fg, false);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
