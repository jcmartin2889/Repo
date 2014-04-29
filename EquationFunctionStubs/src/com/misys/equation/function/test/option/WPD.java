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

public class WPD extends TestOptionStub
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WPD.java 17139 2013-08-29 16:00:56Z whittap1 $";
	private static final String WPD_FILE = "bin\\com\\misys\\equation\\screens\\WPD.class";
	private String newCode;

	private String mode;
	private static final EquationLogger LOG = new EquationLogger(WPD.class);

	public WPD()
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
		WPD test = new WPD();
		test.test("WPD", "GreenFields", IEquationStandardObject.FCT_FUL, true);
	}

	private void firstScreen(FunctionGenerator fg) throws EQException
	{

		// -------------------------------------Account branch----------------------------------

		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "GreenFields", "Product Code");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// Key group
		DisplayGroup keygroup = new DisplayGroup("+KEYS", "Key", "Key description");
		attributeSet.addItem(keygroup);

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		inputField = FunctionToolbox.getInputField("WPD_PRDCDE", "Product Code", "Product Code", "A", "5", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		// inputField.setInitialValue("TT");
		inputField.setKey(true);
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);

		FunctionToolbox.addInputField(inputFieldSet, inputField);
		keygroup.addItem(displayAttributes);
	}

	private void secondScreen(FunctionGenerator fg) throws EQException
	{

		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("second_screen", "Green Fields", "Green Fields");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// Input field.
		InputField inputField;
		DisplayAttributes displayAttributes;

		// -------------------------------------Product Description----------------------------------
		inputField = FunctionToolbox.getInputField("WPD_PRDDSC", "Product Description", "Product Description", "A", "35", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		// inputField.setInitialValue("PEPE");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// -------------------------------------Product account----------------------------------
		inputField = FunctionToolbox.getInputField("WPD_PRDACC", "Product account", "Product account", "A", "34", "");
		inputField.setMandatory(InputField.MANDATORY_YES);
		// inputField.setInitialValue("2222222222222222");

		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

	}

	private void addMappingsSecondScreen(FunctionGenerator fg) throws EQException
	{

		// Add load mapping ---------------(LOAD TAB)---------------------

		// It will pass the key filed to the second screen.
		fg.addLoadMappingToLoad("PRIMARY", "WPD_PRDDSC", "PRIMARY", "WPD", "PRDDSC");// key
		fg.addLoadMappingToLoad("PRIMARY", "WPD_PRDACC", "PRIMARY", "WPD", "PRDACC");// key

		// these are the field to be load.
		fg.addLoadMappingFromLoad("PRIMARY", "WPD", "PRDDSC", "PRIMARY", "WPD_PRDDSC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addLoadMappingFromLoad("PRIMARY", "WPD", "PRDACC", "PRIMARY", "WPD_PRDACC", MappingToolbox.TYPE_PRIMITIVE);

		// Add update mapping tab ---------------(UPDATE TAB)---------------------
		// EXAMPLE: addUpdateMapping (services fields to map into the PV)
		// fg.addUpdateMapping(
		// "PRIMARY",
		// "[input_filed_name prefix= + _ + filed]",
		// "API NAME",
		// "[field name in the Api]"
		// );

		fg.addUpdateMapping("PRIMARY", "WPD_PRDDSC", "WPD", "PRDDSC");
		fg.addUpdateMapping("PRIMARY", "WPD_PRDACC", "WPD", "PRDACC");

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

		// fg.addValidateMappingToPV("PRIMARY", "ASO_AB", "PRIMARY","ASO_AS", "GWR76R", "$R76AB");

		// EXAMPLE: addValidateMappingFromPV (The validation modules that will be called)
		// fg.addValidateMappingFromPV("PRIMARY",
		// "[input_filed_name prefix= + _ + filed]",
		// "[PROMPT_NAME]",
		// "[FILED NAME IN THE PROMPT]",
		// "PRIMARY",
		// "[input_filed_name prefix= + _ + filed]",
		// MappingToolbox.TYPE_PRIMITIVE
		// );

		// fg.addValidateMappingFromPV("PRIMARY", "ASO_AS", "GWR76R", "$R76AB", "PRIMARY", "ASO_AB", MappingToolbox.TYPE_PRIMITIVE);

		// Add load mapping ---------------(LOAD TAB)---------------------

		// It will pass the key filed to the second screen.
		fg.addLoadMappingToLoad("PRIMARY", "WPD_PRDCDE", "PRIMARY", "WPD", "PRDCDE");// key
		// these are the field to be load.
		fg.addLoadMappingFromLoad("PRIMARY", "WPD", "PRDCDE", "PRIMARY", "WPD_PRDCDE", MappingToolbox.TYPE_PRIMITIVE);

		// Add update mapping tab ---------------(UPDATE TAB)---------------------
		// EXAMPLE: addUpdateMapping (services fields to map into the PV)
		// fg.addUpdateMapping(
		// "PRIMARY",
		// "[input_filed_name prefix= + _ + filed]",
		// "API NAME",
		// "[field name in the Api]"
		// );

		fg.addUpdateMapping("PRIMARY", "WPD_PRDCDE", "WPD", "PRDCDE");
	}

	private void addLoadAPI(FunctionGenerator fg) throws EQException
	{
		// this is the progam id )
		APIFieldSet apiFieldSet = null;

		apiFieldSet = FunctionToolbox.getTableFieldSet(session, "WPD", "PRDA10LF", "Green Fields", "PRDCDE", "PRDACC", "", true,
						false);
		fg.addLoadAPIFieldSet("PRIMARY", apiFieldSet);

	}

	private void addUpdateAPI(FunctionGenerator fg) throws EQException
	{
		APIFieldSet apiFieldSet = null;

		// Table test
		apiFieldSet = FunctionToolbox.getTableFieldSet(session, "WPD", "PRDA10LF", "Green Fields", "PRDCDE", "PRDACC", "", false,
						false);
		fg.addUpdateAPIFieldSet(apiFieldSet);

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
				serviceClass = new File(WPD_FILE);
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
