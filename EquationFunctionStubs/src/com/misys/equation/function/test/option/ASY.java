package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

public class ASY extends TestOptionStub
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ASY.java 17139 2013-08-29 16:00:56Z whittap1 $";
	private static final String ASY_FILE = "bin\\com\\misys\\equation\\screens\\ASY.class";

	private String mode;
	private String newCode;
	private static final EquationLogger LOG = new EquationLogger(ASY.class);

	public ASY()
	{
		initialisation();
	}

	private void initialisation()
	{

		try
		{
			setUp();
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{

				LOG.error("There was a problem when the MCD was initialisated", exception);
			}
		}
	}

	public static void main(String[] inputParameters)
	{
		ASY test = new ASY();
		test.test("ASY", "Add Standing order", IEquationStandardObject.FCT_FUL, true);
	}

	private void firstScreen(FunctionGenerator fg) throws EQException
	{

		// -------------------------------------Account branch----------------------------------

		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ASO", "Account branch");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// Key group
		DisplayGroup keygroup = new DisplayGroup("+KEYS", "Key", "Key description");
		attributeSet.addItem(keygroup);

		inputField = FunctionToolbox.getInputField("ASO_AB", "Account branch", "Account branch", "A", "4", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("1000");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// -------------------------------------Basic part of account number----------------------------------

		inputField = FunctionToolbox.getInputField("ASO_AN", "Basic part of account number", "Basic part of account number", "A",
						"6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("500003");
		inputField.setKey(true);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// -------------------------------------Account suffix----------------------------------

		inputField = FunctionToolbox.getInputField("ASO_AS", "Account suffix", "Account suffix", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("006");
		inputField.setKey(true);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// --------------------------------------GWR76R Prompt-------------------------------------

		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GWR76R", "", true, ""));
		displayAttributes.setPrompt("GWR76R");

		// ------------------------------------- Standing order reference----------------------------------

		inputField = FunctionToolbox.getInputField("ASO_REF", " Standing order reference", " Standing order reference", "A", "16",
						"");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

	}

	private void secondScreen(FunctionGenerator fg) throws EQException
	{

		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("second_screen", "Add Standing order", "Add Standing order");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// Input field.
		InputField inputField;
		DisplayAttributes displayAttributes;

		// -------------------------------------Beneficiary currency----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_CCY", "Beneficiary currency", "Beneficiary currency", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("GBP");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Frequency code----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_FRQ", "Frequency code", "Frequency code", "A", "10", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("V31");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------First action date----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_FAD", "First action date", "First action date", "S", "7", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("111208");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Final action date----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_FLD", "Final action date", "Final action date", "S", "7", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("111209");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Regular payment amount----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_RPA", "Regular payment amount", "Regular payment amount", "P", "15", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("500000");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Dr S/o if no avail funds?----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_DSIF", "Dr S/o if no avail funds?", "Dr S/o if no avail funds?", "A", "1",
						"");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("N");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Dr S/o if no avail funds?----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_DCIF", "Dr chgs if no avail funds?", "Dr chgs if no avail funds?", "A",
						"1", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("N");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------User ID (4A)----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_USID", "User ID (4A)", "User ID (4A)", "A", "4", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("EQUI");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Auto-authorised (API)? (1A)----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_AUTO", "Auto-authorised (API)? (1A)", "Auto-authorised (API)? (1A)", "A",
						"1", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("N");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Standing order authorised? (1A)----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_AUTH", "Standing order authorised? (1A)",
						"Standing order authorised? (1A)", "A", "1", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("N");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Authorise standing order flag (1A)----------------------------------
		inputField = FunctionToolbox.getInputField("ASO_RSO", "Authorise standing order flag (1A)",
						"Authorise standing order flag (1A)", "A", "1", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("N");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// ************MSD*************

		// -------------------------------------Menu option id----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_OID", " Menu option id", " Menu option id", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("ASO");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Settlement type----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_PYT", "Settlement type", "Settlement type", "A", "2", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("SO");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Settlement reference----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_REF", "Settlement reference", "Settlement reference", "A", "16", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Continuation flag (1A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_CFG", "Continuation flag (1A)", "Continuation flag (1A)", "A", "1", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("N");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Abbreviated instructions? (1A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_ABR", "Abbreviated instructions? (1A)", "Abbreviated instructions? (1A)",
						"A", "1", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("N");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Credit transaction code (3A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_CTC", "Credit transaction code (3A)", "Credit transaction code (3A)", "A",
						"3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("510");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Credit currency mnemonic (3A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_CCY", "Credit currency mnemonic (3A)", "Credit currency mnemonic (3A)",
						"A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("GBP");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Debit transaction code (3A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_DTC", "Debit transaction code (3A)", "Debit transaction code (3A)", "A",
						"3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("010");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Debit currency mnemonic (3A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_DCY", "Debit transaction code (3A)", "Debit transaction code (3A)", "A",
						"3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("USD");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Receive transfer method (2A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_TRM1", "Debit transaction code (3A)", "Debit transaction code (3A)", "A",
						"3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("AC");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Pay a/c branch (4A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_AB2", "Pay a/c branch (4A)", "Pay a/c branch (4A)", "A", "4", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("0132");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Pay a/c number (6A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_AN2", "Pay a/c branch (6A)", "Pay a/c branch (6A)", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("012008");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Pay a/c suffix (3A)----------------------------------

		inputField = FunctionToolbox.getInputField("MSD_AS2", "Pay a/c suffix (3A)", "Pay a/c suffix (3A)", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setInitialValue("050");
		inputField.setKey(false);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(false);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

	}

	// transaction.setFieldValue("GZAB", "1000"); // Account branch
	// transaction.setFieldValue("GZAN", "500003"); // Basic part of account number
	// transaction.setFieldValue("GZAS", "006"); // Account suffix
	// transaction.setFieldValue("GZREF", "TEST-19"); // Standing order reference

	private void addMappingsSecondScreen(FunctionGenerator fg) throws EQException
	{

		// THE SECOND SCREEN FIELD HAS NOT TO BE LOADED.

		fg.addValidateMappingToPV("second_screen", "ASO_RPA", "second_screen", "ASO_RPA", "GWV30R", "$NUMAM");
		fg.addValidateMappingToPV("second_screen", "ASO_FAD", "second_screen", "ASO_FAD", "GWV94R", "$V94DM");
		fg.addValidateMappingToPV("second_screen", "ASO_FLD", "second_screen", "ASO_FLD", "GWV94R", "$V94DM");

		// MSD
		// fg.addValidateMappingToPV("PRIMARY", "MSD_AB", "PRIMARY","ASO_AS", "GWR76R", "$R76AB");
		// fg.addValidateMappingToPV("PRIMARY", "MSD_AN", "PRIMARY","ASO_AS", "GWR76R", "$R76AN");
		// fg.addValidateMappingToPV("PRIMARY", "MSD_AS", "PRIMARY","ASO_AS", "GWR76R", "$R76AS");

		// Add validate mapping ---------------(VALIDATE TAB)---------------------
		fg.addValidateMappingFromPV("second_screen", "ASO_FAD", "GWV94R", "$V94DM", "second_screen", "ASO_FAD",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("second_screen", "ASO_FLD", "GWV94R", "$V94DM", "second_screen", "ASO_FLD",
						MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("second_screen", "ASO_RPA", "GWV30R", "$NUMAM", "second_screen", "ASO_RPA",
						MappingToolbox.TYPE_PRIMITIVE);

		// MSD
		// fg.addValidateMappingFromPV("PRIMARY", "ASO_AS", "GWR76R", "$R76AB", "PRIMARY", "MSD_AB", MappingToolbox.TYPE_PRIMITIVE);
		// fg.addValidateMappingFromPV("PRIMARY", "ASO_AS", "GWR76R", "$R76AN", "PRIMARY", "MSD_AN", MappingToolbox.TYPE_PRIMITIVE);
		// fg.addValidateMappingFromPV("PRIMARY", "ASO_AS", "GWR76R", "$R76AS", "PRIMARY", "MSD_AS", MappingToolbox.TYPE_PRIMITIVE);

		// Add update mapping tab ---------------( UPDATE TAB)---------------------
		fg.addUpdateMapping("second_screen", "ASO_RPA", "ASO", "GZRPA");
		fg.addUpdateMapping("second_screen", "ASO_FAD", "ASO", "GZFAD");
		fg.addUpdateMapping("second_screen", "ASO_FLD", "ASO", "GZFLD");
		fg.addUpdateMapping("second_screen", "ASO_FRQ", "ASO", "GZFRQ");
		fg.addUpdateMapping("second_screen", "ASO_CCY", "ASO", "GZBCCY");

		// MSD
		fg.addUpdateMapping("PRIMARY", "ASO_AB", "MSD", "GZAB");
		fg.addUpdateMapping("PRIMARY", "ASO_AN", "MSD", "GZAN");
		fg.addUpdateMapping("PRIMARY", "ASO_AS", "MSD", "GZAS");
		fg.addUpdateMapping("PRIMARY", "ASO_REF", "MSD", "GZREF");

		fg.addUpdateMapping("PRIMARY", "MSD_PYT", "MSD", "GZPYT");
		fg.addUpdateMapping("PRIMARY", "MSD_ABR", "MSD", "GZABR");
		fg.addUpdateMapping("PRIMARY", "MSD_CTC", "MSD", "GZCTC");
		fg.addUpdateMapping("PRIMARY", "MSD_CCY", "MSD", "GZCCY");
		fg.addUpdateMapping("PRIMARY", "MSD_DTC", "MSD", "GZDTC");
		fg.addUpdateMapping("PRIMARY", "MSD_DCY", "MSD", "GZDCY");
		fg.addUpdateMapping("PRIMARY", "MSD_TRM1", "MSD", "GZTRM1");

		fg.addUpdateMapping("PRIMARY", "MSD_OID", "MSD", "GZOID");
		fg.addUpdateMapping("PRIMARY", "MSD_AB2", "MSD", "GZAB2");
		fg.addUpdateMapping("PRIMARY", "MSD_AN2", "MSD", "GZAN2");
		fg.addUpdateMapping("PRIMARY", "MSD_AS2", "MSD", "GZAS2");

	}

	private void addMappingsFirstScreen(FunctionGenerator fg) throws EQException
	{

		// Add validate mapping ---------------(VALIDATE TAB)---------------------
		// Here all mappings to the prompt take place.; All field are mapped to the GWR76R Prompt.
		// EXAMPLE: addValidateMappingToPV (services fields to map into the PV)
		// fg.addValidateMappingToPV(
		// "PRIMARY",
		// "[input_filed_name= prefix + _ + filed]",
		// "PRIMARY",
		// "[input_filed_name= prefix + _ + filed]",
		// "[PROMPT_NAME]",
		// "[FILED NAME IN THE PROMPT]"
		// );

		fg.addValidateMappingToPV("PRIMARY", "ASO_AB", "PRIMARY", "ASO_AS", "GWR76R", "$R76AB");
		fg.addValidateMappingToPV("PRIMARY", "ASO_AN", "PRIMARY", "ASO_AS", "GWR76R", "$R76AN");
		fg.addValidateMappingToPV("PRIMARY", "ASO_AS", "PRIMARY", "ASO_AS", "GWR76R", "$R76AS");

		// EXAMPLE: addValidateMappingFromPV (The validation modules that will be called)
		// fg.addValidateMappingFromPV("PRIMARY",
		// "[input_filed_name prefix= + _ + filed]",
		// "[PROMPT_NAME]",
		// "[FILED NAME IN THE PROMPT]",
		// "PRIMARY",
		// "[input_filed_name prefix= + _ + filed]",
		// MappingToolbox.TYPE_PRIMITIVE
		// );

		fg.addValidateMappingFromPV("PRIMARY", "ASO_AS", "GWR76R", "$R76AB", "PRIMARY", "ASO_AB", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ASO_AS", "GWR76R", "$R76AN", "PRIMARY", "ASO_AN", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "ASO_AS", "GWR76R", "$R76AS", "PRIMARY", "ASO_AS", MappingToolbox.TYPE_PRIMITIVE);

		// Add update mapping tab ---------------(UPDATE TAB)---------------------
		// EXAMPLE: addUpdateMapping (services fields to map into the PV)
		// fg.addUpdateMapping(
		// "PRIMARY",
		// "[input_filed_name prefix= + _ + filed]",
		// "API NAME",
		// "[field name in the Api]"
		// );

		fg.addUpdateMapping("PRIMARY", "ASO_AB", "ASO", "GZAB");
		fg.addUpdateMapping("PRIMARY", "ASO_AN", "ASO", "GZAN");
		fg.addUpdateMapping("PRIMARY", "ASO_AS", "ASO", "GZAS");
		fg.addUpdateMapping("PRIMARY", "ASO_REF", "ASO", "GZREF");

		// Add load mapping ---------------(LOAD TAB)---------------------

		// It will pass the key filed to the second screen.
		// fg.addLoadMappingToLoad("PRIMARY", "ASO_AB", "PRIMARY", "ASO", "GZAB");//key
		// fg.addLoadMappingToLoad("PRIMARY", "ASO_AN", "PRIMARY", "ASO", "GZAN");//key
		// fg.addLoadMappingToLoad("PRIMARY", "ASO_AS", "PRIMARY", "ASO", "GZAS");//key
		// fg.addLoadMappingToLoad("PRIMARY", "ASO_REF", "PRIMARY","ASO", "GZREF");//key
		//		
		// //these are the field to be load.
		// fg.addLoadMappingFromLoad("PRIMARY", "CMA", "GZAB", "PRIMARY", "ASO_AB", MappingToolbox.TYPE_PRIMITIVE);
		// fg.addLoadMappingFromLoad("PRIMARY", "CMA", "GZAN", "PRIMARY", "ASO_AN", MappingToolbox.TYPE_PRIMITIVE);
		// fg.addLoadMappingFromLoad("PRIMARY", "CMA", "GZAS", "PRIMARY", "ASO_AS", MappingToolbox.TYPE_PRIMITIVE);
		// fg.addLoadMappingFromLoad("PRIMARY", "CMA", "GZREF", "PRIMARY", "ASO_REF", MappingToolbox.TYPE_PRIMITIVE);
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		// this is the progam id )
		// APIFieldSet apiFieldSet = FunctionToolbox.getLoadAPIFieldSet(session, "ASO", "ASO", "H11A", "GZH111",
		// "Add Standing order",
		// mode, true);
		//
		// fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{

		// /------------MSD---------------///

		// GZH111 journal file = [GZ+ 3 characters of the program id + 1]
		// H11A program ID
		// ASO API prefix
		// ASO API option id.
		// Program root option and id input transaction
		// K42F= 4 characters of the program id
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "MSD", "MSD", "K42F", "Add/Maintain Sttlement details",
						mode);

		fg.addUpdateAPIFieldSet(apiFieldSet);

		// /------------ASO----------------///

		// GZH111 journal file = [GZ+ 3 characters of the program id + 1]
		// H11A program ID
		// ASO API prefix
		// ASO API option id.
		// Program root option and id input transaction
		// H11A= 4 characters of the program id
		apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "ASO", "ASO", "H11A", "Add Standing order", mode);

		fg.addUpdateAPIFieldSet(apiFieldSet);
	}

	public FunctionGenerator test(String id, String desc, String mode, boolean print)
	{
		printStartStatus(this.getClass().getSimpleName());
		FunctionGenerator functionGenerator = null;
		File serviceClass = null;

		// Have a bash...
		try
		{
			this.mode = mode;
			this.newCode = "";
			if (mode.equals("A"))
			{
				newCode = "Y";
			}
			else if (mode.equals("M") || mode.equals("D"))
			{
				newCode = "N";
			}

			functionGenerator = new FunctionGenerator(id, desc, new StringBuilder("This is the description of ").append(desc)
							.toString(), "com.misys.equation.screens", "com.misys.equation.screens.layout");

			functionGenerator.getFunction().setAllowedAdd(true);
			functionGenerator.getFunction().setAllowedMaint(true);
			functionGenerator.getFunction().setAllowedDel(true);

			firstScreen(functionGenerator);
			secondScreen(functionGenerator);

			addLoadAPI(functionGenerator);
			addUpdateAPI(functionGenerator);

			addMappingsFirstScreen(functionGenerator);
			addMappingsSecondScreen(functionGenerator);

			// print the serialise version
			if (print)
			{
				// Write to DB
				serviceClass = new File(ASY_FILE);
				FunctionToolboxStub.writeToDB(unit, functionGenerator.getFunctionBean(), functionGenerator.getLayoutBean(),
								serviceClass, null);

				// print
				printCompleteStatus(functionGenerator, this.getClass().getSimpleName(), printXML);
			}
			if (LOG.isInfoEnabled())
			{
				LOG.info("Done !! ");
			}
		}
		catch (Exception exception)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("There was a problem when the MCD was initialisated", exception);
			}

		}
		return functionGenerator;
	}

}
