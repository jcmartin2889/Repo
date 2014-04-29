package com.misys.equation.function.test.option;

import java.io.File;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

public class CBY extends TestOptionStub
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CBY.java 7138 2010-05-04 14:59:54Z lima12 $";
	private static final String MCD_FILE = "bin\\com\\misys\\equation\\screens\\CBY.class";

	private String mode;
	private String newCode;
	private static final EquationLogger LOG = new EquationLogger(CBY.class);

	public CBY()
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
		CBY test = new CBY();
		test.test("CBY", "Maintain Customer Basic Details", IEquationStandardObject.FCT_FUL, true);
	}

	private void firstScreen(FunctionGenerator fg) throws EQException
	{

		// -------------------------------------Customer mnemonic----------------------------------

		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of MCD", "Customer mnemonic");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		inputField = FunctionToolbox.getInputField("CMA_CUS", "Customer mnemonic", "Customer mnemonic", "A", "6", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Customer location----------------------------------

		inputField = FunctionToolbox.getInputField("CMA_CLC", "Customer location", "Customer location", "A", "3", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setKey(true);

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setKeepWithPrevious(true);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "", "", true, "", inputField
		// .rtvPath()));

		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "GFR70R", "", true, ""));

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
		// this is the progam id )
		APIFieldSet apiFieldSet = FunctionToolbox.getLoadAPIFieldSet(session, "CMA", "MCD", "G01M", "GZG011",
						"Maintain Customer Basic Details", mode, true);

		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

	}

	private void addMappingsFirstScreen(FunctionGenerator fg) throws EQException
	{

		// this is related to the output; Both are field are mapped to the GFR70R pront.
		fg.addValidateMappingToPV("PRIMARY", "CMA_CLC", "PRIMARY", "CMA_CLC", "GFR70R", "$GFCLC");
		fg.addValidateMappingToPV("PRIMARY", "CMA_CUS", "PRIMARY", "CMA_CLC", "GFR70R", "$GFCUS");

		// Add validate mapping ---------------(VALIDATE TAB)---------------------
		fg.addValidateMappingFromPV("PRIMARY", "CMA_CLC", "GFR70R", "$GFCUS", "PRIMARY", "CMA_CUS", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "CMA_CLC", "GFR70R", "$GFCLC", "PRIMARY", "CMA_CLC", MappingToolbox.TYPE_PRIMITIVE);

		// Add update mapping tab ---------------(UPDATE TAB)---------------------
		fg.addUpdateMapping("PRIMARY", "CMA_CUS", "CMA", "GZCUS");
		fg.addUpdateMapping("PRIMARY", "CMA_CLC", "CMA", "GZCLC");

		// Load PV field set ---------------(LOAD TAB)---------------------
		fg.addLoadMappingToPVLoad("PRIMARY", "CMA_CUS", "PRIMARY", "GFR70R", "$GFCUS");
		fg.addLoadMappingToPVLoad("PRIMARY", "CMA_CLC", "PRIMARY", "GFR70R", "$GFCLC");

		// add load mapping ---------------(LOAD TAB)---------------------

		// It will pass the key filed to the second screen.
		fg.addLoadMappingToLoad("PRIMARY", "CMA_CUS", "PRIMARY", "CMA", "GZCUS");// key
		fg.addLoadMappingToLoad("PRIMARY", "CMA_CLC", "PRIMARY", "CMA", "GZCLC");// key
		// these are the field to be load.
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

			functionGenerator.getFunction().setAllowedEnq(true);

			functionGenerator.getFunction().setAllowedAdd(true);
			functionGenerator.getFunction().setAllowedMaint(true);
			functionGenerator.getFunction().setAllowedDel(true);

			firstScreen(functionGenerator);
			secondScreen(functionGenerator);

			addLoadAPI(functionGenerator);
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
