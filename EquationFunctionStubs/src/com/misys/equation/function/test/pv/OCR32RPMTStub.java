package com.misys.equation.function.test.pv;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.FunctionGenerator;
import com.misys.equation.function.test.run.FunctionHandlerStubTestCase;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.HTMLToolbox;
import com.misys.equation.function.tools.MappingToolbox;

/**
 * FunctionHandler Stub 2
 * 
 * This is how to use the Function Handler validation each screen separately
 * 
 */
public class OCR32RPMTStub extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OCR32RPMTStub.java 6793 2010-03-31 12:10:20Z deroset $";
	InputField userInputField;

	public OCR32RPMTStub()
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
		OCR32RPMTStub test = new OCR32RPMTStub();
		test.test();
	}

	private DisplayFieldSetWrapper addRecord1(FunctionGenerator fg) throws EQException
	{
		// add the record
		DisplayFieldSetWrapper fieldSetWrapper = fg.addFieldSet("PRIMARY", "Record 1 of ALZ", "Description 1 of ALZ");
		InputFieldSet inputFieldSet = fieldSetWrapper.getInputFieldSet();
		DisplayAttributesSet attributeSet = fieldSetWrapper.getDisplayAttributesSet();

		// input field
		InputField inputField;
		DisplayAttributes displayAttributes;

		// add all the fields in this section ---------------------------------------

		// User id
		inputField = FunctionToolbox.getInputField("userId", "User Id", "Amount", "A", "15", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		displayAttributes.setPrompt("OCR32R");
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, "OCR32R", "", false, "N"));
		userInputField = inputField;

		// Errors
		inputField = FunctionToolbox.getInputField("$OCEMH", "Error", "Amount", "A", "15", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Amount
		inputField = FunctionToolbox.getInputField("$OCEA", "Amount", "Amount", "A", "15", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Branch
		inputField = FunctionToolbox.getInputField("$OCEB", "Branch", "Amount", "A", "15", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		// Debit/Credit
		inputField = FunctionToolbox.getInputField("$OCDR", "Dr/Cr", "Amount", "A", "15", "");
		displayAttributes = FunctionToolbox.getInputFieldAttribute(inputField);
		FunctionToolbox.addInputField(inputFieldSet, inputField);
		FunctionToolbox.addDisplayAttributes(attributeSet, displayAttributes);

		return fieldSetWrapper;
	}

	private void addMappings(FunctionGenerator fg) throws EQException
	{
		// add validate mapping
		fg.addValidateMappingToPV("PRIMARY", "userId", "PRIMARY", userInputField.getId(), "OCR32R", "$OCUSID");
		fg.addValidateMappingToPV("PRIMARY", "$OCEMH", "PRIMARY", userInputField.getId(), "OCR32R", "$OCEMH");
		fg.addValidateMappingToPV("PRIMARY", "$OCEA", "PRIMARY", userInputField.getId(), "OCR32R", "$OCEA");
		fg.addValidateMappingToPV("PRIMARY", "$OCEB", "PRIMARY", userInputField.getId(), "OCR32R", "$OCEB");
		fg.addValidateMappingToPV("PRIMARY", "$OCDR", "PRIMARY", userInputField.getId(), "OCR32R", "$OCDR");
		fg.addValidateMappingFromPV("PRIMARY", userInputField.getId(), "OCR32R", "$OCUSID", "PRIMARY", "userId",
						MappingToolbox.TYPE_INPUT);
		fg.addValidateMappingFromPV("PRIMARY", userInputField.getId(), "OCR32R", "$OCONLN", "PRIMARY", "$OCONLN",
						MappingToolbox.TYPE_INPUT);
	}

	private void test()
	{
		// Have a bash...
		try
		{
			FunctionGenerator fg = new FunctionGenerator("XXX", "Prompt Stub for OCR32R", "Description of Add Sundry Item",
							"com.misys.equation.screens", "com.misys.equation.screens.layout");
			addRecord1(fg);
			addMappings(fg);

			// Print the XML
			String serviceXML = fg.getService();
			EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			Function function = (Function) beanFactory.deserialiseXMLAsBean(serviceXML, Function.class);

			// Function data
			FunctionData functionData = new FunctionData();
			functionData.addFieldData("userId", new FieldData());
			functionData.addFieldData("$OCEMH", new FieldData());
			functionData.addFieldData("$OCEA", new FieldData());
			functionData.addFieldData("$OCEB", new FieldData());
			functionData.addFieldData("$OCDR", new FieldData());
			functionData.addFieldData("$OCONLN", new FieldData());

			HTMLToolbox htmlToolbox = new HTMLToolbox();
			htmlToolbox.setFunctionData(functionData);
			htmlToolbox.setEqUser(user);
			htmlToolbox.setSession(session);
			String str = htmlToolbox.getScriptPMT(function, userInputField, "OCR32R", "", "");
			System.out.println(str);

			// real PMT script
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
