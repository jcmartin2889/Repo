package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.TranslationToolbox;

public class AC22 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AC22.java 11921 2011-09-27 03:33:50Z ESTHER.WILLIAMS $";

	public AC22()
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
		AC22 test = new AC22();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSetParameter("PRIMARY", "GBL000108", "GBL000109");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// Reference
		inputField = FunctionToolbox.getInputFieldParameter("REF", "GBL000110", "GBL000111", "A", "30", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Option selection
		inputField = FunctionToolbox.getInputFieldParameter("OPT", "GBL000112", "GBL000113", "A", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setWidget("OPT");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Calculated charge
		inputField = FunctionToolbox.getInputFieldParameter("CALA", "GBL000114", "GBL000115", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge amount
		inputField = FunctionToolbox.getInputFieldParameter("CHGA", "GBL000116", "GBL000117", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Event mnemonic
		inputField = FunctionToolbox.getInputFieldParameter("EVNT", "GBL000118", "GBL000119", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge structure
		inputField = FunctionToolbox.getInputFieldParameter("CHGS", "GBL000120", "GBL000121", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge description
		inputField = FunctionToolbox.getInputFieldParameter("CHGD", "GBL000122", "GBL000123", "A", "35", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge start date
		inputField = FunctionToolbox.getInputFieldParameter("GSSDT", "GBL000124", "GBL000125", "D", "7", "");
		// inputField.setExtendedAttribute(InputField.EXT_ATTRIB_DATE_INPUT8D);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge end date
		inputField = FunctionToolbox.getInputFieldParameter("GSEND", "GBL000126", "GBL000127", "D", "7", "");
		// inputField.setExtendedAttribute(InputField.EXT_ATTRIB_DATE_INPUT8D);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Charge value date
		inputField = FunctionToolbox.getInputFieldParameter("GSVAL", "GBL000128", "GBL000129", "D", "7", "");
		// inputField.setExtendedAttribute(InputField.EXT_ATTRIB_DATE_INPUT8D);
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Event mnemonic during Add charge component (not multilanguge as not displayed on screen)
		inputField = FunctionToolbox.getInputField("TECNM", "Charge component entry", "This is the charge mnenonic being added",
						"A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Calculate on amount description
		inputField = FunctionToolbox.getInputFieldParameter("COAD", "GBL000130", "GBL000131", "A", "35", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// F6=Add status
		inputField = FunctionToolbox.getInputField("ADDS", "F6=Add status", "This is the F6=Add status", "A", "1", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Message status
		inputField = FunctionToolbox.getInputField("MSGSTS", "Message status", "This is the message status", "A", "6", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Number of charge records
		inputField = FunctionToolbox.getInputField("CURDATA", "Number of charge records", "This is the number of charge records",
						"S", "3", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Current index
		inputField = FunctionToolbox.getInputField("CURINDEX", "Current charge record",
						"This is the current charge record being processed", "S", "3", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction reference (DSEVNT key)
		inputField = FunctionToolbox.getInputField("TREF", "Transaction reference", "This is the transaction reference", "A", "22",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Credit account branch (DSEVNT key)
		inputField = FunctionToolbox.getInputField("CAB", "Credit account branch", "This is the credit account branch", "A", "4",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Credit account number (DSEVNT key)
		inputField = FunctionToolbox.getInputField("CAN", "Credit account number", "This is the credit account number", "A", "6",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Credit account suffix (DSEVNT key)
		inputField = FunctionToolbox.getInputField("CAS", "Credit account suffix", "This is the credit account suffix", "A", "3",
						"");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Extra reference (DSEVNT key)
		inputField = FunctionToolbox.getInputField("AREF", "Extra reference", "This is the extra reference", "A", "22", "");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction date (DSEVNT key)
		inputField = FunctionToolbox.getInputField("DDTD", "Transaction date", "This is the transaction date", "D", "7", "");
		// inputField.setExtendedAttribute(InputField.EXT_ATTRIB_DATE_INPUT8D);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction sequence (DSEVNT key)
		inputField = FunctionToolbox.getInputField("ESQN", "Transaction sequence", "This is the transaction sequence", "P", "16",
						"0");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
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
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction amount (DSEVNT key)
		inputField = FunctionToolbox.getInputField("TAMT", "Transaction amount", "This is the transaction amount", "P", "15", "0");
		inputField.setUpperCase(false);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Transaction new flag (DSEVNT key)
		inputField = FunctionToolbox.getInputField("ENEW", "Transaction new?", "This is the transaction new flag", "A", "1", "");
		inputField.setUpperCase(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
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

	private void addUpdateAPI(FunctionGenerator fg)
	{
	}

	private void addMappings(FunctionGenerator fg)
	{
	}

	public void test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("AC2", "GBL000132", "GBL000133", "com.misys.equation.screens",
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
