package com.misys.equation.function.test.option;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionToolboxStub;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingToolbox;

public class SSC extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SSC.java 7138 2010-05-04 14:59:54Z lima12 $";

	public SSC()
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
		SSC test = new SSC();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of SSC2", "Description 1 of SSC2");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// pv field set
		PVFieldSet pvFieldSet;

		// add all the fields in this section ---------------------------------------

		// SSC03R Test
		inputField = FunctionToolbox.getInputField("SSC03R", "SSC03R", "SSC03R", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SSC03RW", "", true, ""));
		pvFieldSet.getPVField("$SCONF").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("$SCONF").setValidateScript("'R'");
		pvFieldSet.getPVField("$SAB").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("$SAB").setValidateScript("'0543'");
		pvFieldSet.getPVField("$SNEGP").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("$SNEGP").setValidateScript("'C'");
		pvFieldSet.getPVField("$SCCY").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("$SCCY").setValidateScript("'GBP'");
		pvFieldSet.getPVField("$SAN").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("$SAN").setValidateScript("'234567'");
		pvFieldSet.getPVField("$SAN").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("$SAN").setValidateScript("'001'");

		// SSC03R output
		inputField = FunctionToolbox.getInputField("SABPL", "SABPL", "SABPL", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SANPL", "SABPL", "SABPL", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SASPL", "SABPL", "SABPL", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// SSC10R Test
		inputField = FunctionToolbox.getInputField("SSC10R", "SSC10R", "SSC10R", "A", "3", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		pvFieldSet = FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "SSC10RW", "", true, ""));
		pvFieldSet.getPVField("SCAB").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("SCAB").setValidateScript("'0543'");
		pvFieldSet.getPVField("SCAN").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("SCAN").setValidateScript("'234567'");
		pvFieldSet.getPVField("SCAS").setValidateAssignment(Field.ASSIGNMENT_SCRIPT);
		pvFieldSet.getPVField("SCAS").setValidateScript("'001'");

		// SSC10R output
		inputField = FunctionToolbox.getInputField("SCSUMD", "SCSUMD", "SCSUMD", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SCSUMC", "SCSUMC", "SCSUMC", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("SCSUMA", "SCSUMA", "SCSUMA", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		inputField = FunctionToolbox.getInputField("BALCA", "BALCA", "BALCA", "A", "50", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_YES);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		return fieldSetWrapper;
	}

	private void addLoadAPI(FunctionGenerator fg)
	{
	}

	private void addUpdateAPI(FunctionGenerator fg)
	{
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// SSC03R
		fg.addValidateMappingFromPV("PRIMARY", "SSC03R", "SSC03RW", "$SABPL", "PRIMARY", "SABPL", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "SSC03R", "SSC03RW", "$SANPL", "PRIMARY", "SANPL", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "SSC03R", "SSC03RW", "$SASPL", "PRIMARY", "SASPL", MappingToolbox.TYPE_PRIMITIVE);

		// SSC10R
		fg.addValidateMappingFromPV("PRIMARY", "SSC10R", "SSC10RW", "SCSUMD", "PRIMARY", "SCSUMD", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "SSC10R", "SSC10RW", "SCSUMC", "PRIMARY", "SCSUMC", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "SSC10R", "SSC10RW", "SCSUMA", "PRIMARY", "SCSUMA", MappingToolbox.TYPE_PRIMITIVE);
		fg.addValidateMappingFromPV("PRIMARY", "SSC10R", "SSC10RW", "$BALCA", "PRIMARY", "BALCA", MappingToolbox.TYPE_PRIMITIVE);

	}

	public FunctionGenerator test()
	{
		// Have a bash...
		try
		{
			printStartStatus(this.getClass().getSimpleName());
			FunctionGenerator fg = new FunctionGenerator("SSC", "SSC", "This is the description of SSC",
							"com.misys.equation.screens", "com.misys.equation.screens.layout");
			fg.getFunction().setAllowedAdd(true);
			fg.getFunction().setAllowedMaint(true);
			fg.getFunction().setAllowedDel(true);
			addRecord1(fg);
			addLoadAPI(fg);
			addUpdateAPI(fg);
			addMappings(fg);
			FunctionToolboxStub.writeToDB(unit, fg.getFunctionBean(), fg.getLayoutBean(), null, null);

			// print
			printCompleteStatus(fg, this.getClass().getSimpleName(), printXML);

			return fg;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
