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

public class MCY extends TestOptionStub
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MCY.java 17139 2013-08-29 16:00:56Z whittap1 $";
	private static final String MCD_FILE = "bin\\com\\misys\\equation\\screens\\MCY.class";

	private String mode;
	private String newCode;
	private static final EquationLogger LOG = new EquationLogger(MCY.class);

	public MCY()
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
		MCY test = new MCY();
		// MCY This is going to be the option name. It will be use as option ID.

		/** This example has two screens **/
		test.test("MCY", "Maintain Customer Basic Details", IEquationStandardObject.FCT_FUL, true);
	}

	private void firstScreen(FunctionGenerator fg) throws EQException
	{
		// ------------------ This is part of the fields TAB------------------------------//

		// It will define the KEY input fields

		// -------------------------------------Customer mnemonic----------------------------------

		// add the record

		// This this thing gives me default PK fields.
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of MCD", "Customer mnemonic");

		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// Key group
		DisplayGroup keygroup = new DisplayGroup("+KEYS", "Key", "Key description");
		attributeSet.addItem(keygroup);

		// Input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		inputField = FunctionToolbox.getInputField("CMA_CUS", "Customer mnemonic", "Customer mnemonic", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);

		// It will be the layout for this filed, from here I can change the visibility and others.
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		// -------------------------------------Customer location----------------------------------

		inputField = FunctionToolbox.getInputField("CMA_CLC", "Customer location", "Customer location", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);

		// It will be the layout for this filed, from here I can change the visibility and others.
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);

		/** The intention of these lines is add a validator using the pront model **/
		// This thing add the validate PV model for this filed, in this case Customer location.
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", true, ""));
		// PV model I will the related PV model from the wmenu1. I need to use the option name. MCD.
		displayAttributes.setPrompt("GFR70R");

	}

	private void secondScreen(FunctionGenerator fg) throws EQException
	{

		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("RESIDENCE_COUNTRY", "Test Record of MCD", "Residence country");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// -------------------------------------Residence country----------------------------------
		// //MCD_CNAL $GFCNL
		inputField = FunctionToolbox.getInputField("MCD_CNAL", "Residence country", "Residence country", "A", "2", "");
		inputField.setMandatory(InputField.MANDATORY_YES);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C7R05R", "", true, ""));
		displayAttributes.setPrompt("C7R05R");

		// -------------------------------------Customer type----------------------------------

		// MCD_CTP $GFCTP
		inputField = FunctionToolbox.getInputField("MCD_CTP", "Customer type", "Customer type", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "C4R54R", "", true, ""));
		displayAttributes.setPrompt("C4R54R");

	}

	private void addMappingsSecondScreen(FunctionGenerator fg) throws EQException
	{

		// THE SECOND SCREEN FIELD HAS NOT TO BE LOADED.

		// this is related to the output; Both are field are mapped to the C7R05R pront.
		// fg.addValidateMappingToPV("RESIDENCE_COUNTRY", "MCD_CTP", "RESIDENCE_COUNTRY","MCD_CTP", "C7R05R", "$C7CNA");
		//		
		// // Add validate mapping ---------------(VALIDATE TAB)---------------------
		// fg.addValidateMappingFromPV("RESIDENCE_COUNTRY", "MCD_CTP", "C7R05R", "$C7CNA", "RESIDENCE_COUNTRY", "MCD_CTP",
		// MappingToolbox.TYPE_PRIMITIVE);
		//		
		// // Add update mapping tab ---------------( UPDATE TAB)---------------------
		// fg.addUpdateMapping("RESIDENCE_COUNTRY", "MCD_CTP", "CMA", "GZCNAL");
		//		

		// Load PV field set ---------------(LOAD TAB)---------------------
		// fg.addLoadMappingToPVLoad("RESIDENCE_COUNTRY", "MCD_CTP", "RESIDENCE_COUNTRY", "C7R05R", "$C7CNA");

	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		// ------------------ This is part of the fields TAB------------------------------//
		// This section correspond to the fields tabs LOAD APIS table.

		// Parameters:

		// CMA this is just an id for this API in this case MCD. if I am trying to update and delete this id must be the same.
		// MCD: this is the option name.
		// "G01M" program name. from green
		// GZG011: This is the journal file.
		APIFieldSet apiFieldSet = FunctionToolbox.getLoadAPIFieldSet(session, "CMA", "MCD", "G01M", "GZG011",
						"Maintain Customer Basic Details", mode, true);

		// It adds to the function.
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

		// note: getLoadAPIFieldSet it is used for input transaction.
		// note: getPVFieldSet or getLoadAPIFieldSetit is used for validate module.
		// note: getEnquiryFieldSet it is used for enquire.
		// note: getTableFieldSet it is used for data base table.

	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		// ------------------ This is part of the fields TAB------------------------------//
		// This section correspond to the fields tabs UPDATE APIS table.

		// CMA this is just an id for this API in this case MCD. if I am trying to update and delete this id must be the same.
		// MCD: this is the option name.
		// "G01M" program name. from green
		APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, "CMA", "MCD", "G01M", "Maintain Customer Basic Details",
						mode);

		fg.addUpdateAPIFieldSet(apiFieldSet);

		// note: getLoadAPIFieldSet it is used for input transaction.
		// note: getPVFieldSet or getLoadAPIFieldSetit is used for validate module.
		// note: getEnquiryFieldSet it is used for enquire.
		// note: getTableFieldSet it is used for data base table.
	}

	private void addMappingsFirstScreen(FunctionGenerator fg) throws EQException
	{

		// ------------------ This hole method work with the validate, update and load tabs.-------------------------//

		// Add validate mapping ---------------(VALIDATE TAB)---------------------
		// this is related to the output; Both are field are mapped to the GFR70R pront.
		// This the PV GFR70R.
		// $GFCLC this is field name . I will need to run the pv meta-data. or I will get it from the source.

		// From The services fields to map into the PV table to The validation modules that will be called table
		fg.addValidateMappingToPV("PRIMARY", "CMA_CLC", "PRIMARY", "CMA_CLC", "GFR70R", "$GFCLC");
		fg.addValidateMappingToPV("PRIMARY", "CMA_CUS", "PRIMARY", "CMA_CLC", "GFR70R", "$GFCUS");

		// From The validation modules that will be called table to Map required PV output values back to these service fields
		// table.
		fg.addValidateMappingFromPV("PRIMARY", "CMA_CLC", "GFR70R", "$GFCUS", "PRIMARY", "CMA_CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CMA_CLC", "GFR70R", "$GFCLC", "PRIMARY", "CMA_CLC", MappingToolbox.TYPE_PRIMITIVE);

		// Add update mapping tab ---------------(UPDATE TAB)---------------------
		// From The service field that will be mapped from to The API field that will be mapped to table.
		fg.addUpdateMapping("PRIMARY", "CMA_CUS", "CMA", "GZCUS");
		fg.addUpdateMapping("PRIMARY", "CMA_CLC", "CMA", "GZCLC");
		fg.addUpdateMapping("PRIMARY", "MCD_CTP", "CMA", "GZCTP");
		fg.addUpdateMapping("PRIMARY", "MCD_CNAL", "CMA", "GZCNAL");

		// Load PV field set ---------------(LOAD TAB)---------------------
		fg.addLoadMappingToPVLoad("PRIMARY", "CMA_CUS", "PRIMARY", "GFR70R", "$GFCUS");
		fg.addLoadMappingToPVLoad("PRIMARY", "CMA_CLC", "PRIMARY", "GFR70R", "$GFCLC");

		// Do not use this method any more. addLoadMappingToPVLoad.

		// add load mapping ---------------(LOAD TAB)---------------------

		// It will pass the key filed to the second screen.
		// From Service fields to map table to API and PV Modules for loading data table
		fg.addLoadMappingToLoad("PRIMARY", "CMA_CUS", "PRIMARY", "CMA", "GZCUS");// key
		fg.addLoadMappingToLoad("PRIMARY", "CMA_CLC", "PRIMARY", "CMA", "GZCLC");// key

		// These are the field to be load.
		// From API and PV Modules for loading data table to Service fields to map into table.
		fg.addLoadMappingFromLoad("PRIMARY", "CMA", "GZCTP", "PRIMARY", "MCD_CTP", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "CMA", "GZCNAL", "PRIMARY", "MCD_CNAL", MappingToolbox.TYPE_PRIMITIVE);

	}

	public FunctionGenerator test(String id, String desc, String mode, boolean print)
	{

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

			printStartStatus(this.getClass().getSimpleName());
			functionGenerator = new FunctionGenerator(id, desc, new StringBuilder("This is the description of ").append(desc)
							.toString(), "com.misys.equation.screens", "com.misys.equation.screens.layout");

			// ------------------ This is part of the fields Overview------------------------------//

			// It will set the models that the service support.
			functionGenerator.getFunction().setAllowedAdd(true);
			functionGenerator.getFunction().setAllowedMaint(true);
			functionGenerator.getFunction().setAllowedDel(true);

			// ------------------ This is part of the fields TAB------------------------------//

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
				serviceClass = new File(MCD_FILE);
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
