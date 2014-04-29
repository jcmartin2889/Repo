package com.misys.equation.function.test.option;

import java.util.Calendar;

import com.misys.equation.common.dao.beans.GAXRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.DisplayFieldWrapper;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.XMLToolbox;

public class PVU extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PVU.java 4735 2009-09-15 16:58:25Z lima12 $";

	public PVU()
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
		PVU test = new PVU();
		test.test();
	}

	private DisplayFieldWrapper getField(String id, String label, int length, String prompt, String decode) throws EQException
	{
		DisplayFieldWrapper wrapper = new DisplayFieldWrapper();
		wrapper.setId(id);
		wrapper.setLabel(label);
		wrapper.setDescription(label);
		// Business layer attributes
		InputField inputField = wrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize(String.valueOf(length));
		inputField.setInitialValue("");
		inputField.setMaxLength("");
		inputField.setMinLength("0");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_NO);
		inputField.setRegExp("");
		inputField.setType("");
		inputField.setUpperCase(true);
		inputField.setValidValues("");

		// UI layer attributes
		DisplayAttributes displayAttributes = wrapper.getDisplayAttributes();
		displayAttributes.setKeepWithPrevious(false);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_LEFT);
		displayAttributes.setMask("");
		displayAttributes.setPrompt(prompt);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_NO);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttributes.setWidget("");

		// Add the PV field set
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, prompt, decode, false, "N"));

		return wrapper;
	}

	private DisplayFieldSetWrapper getRecord1() throws Exception
	{
		InputFieldSet inputFieldSet;

		// Record 1
		DisplayFieldSetWrapper setWrapper = new DisplayFieldSetWrapper();
		setWrapper.setId("PVU");
		setWrapper.setLabel("PV Testing");
		setWrapper.setDescription("This is the PV testing");
		inputFieldSet = setWrapper.getInputFieldSet();
		inputFieldSet.setValidateUserExit("");

		DisplayAttributesSet displayAttributesSet = setWrapper.getDisplayAttributesSet();
		displayAttributesSet.setNextScreenUserExit("");

		// All the fields for record 1

		setWrapper.addDisplayFieldWrapper(getField("A1", "Acc", 20, "GWR76R", ""));
		setWrapper.addDisplayFieldWrapper(getField("A2", "Acc", 20, "C8R01R", ""));
		setWrapper.addDisplayFieldWrapper(getField("A3", "Acc", 20, "TAR52R", ""));
		setWrapper.addDisplayFieldWrapper(getField("A4", "Acc", 20, "C2R24R", ""));
		return setWrapper;
	}
	private void test()
	{
		// Have a bash...
		try
		{
			// Create the FUNCTION
			Function function = new Function();
			function.setId("PVU");
			function.setLabel("Prompt and Validate Test Stub");
			function.setDescription("Prompt and Validate Test Stub");

			// Records
			DisplayFieldSetWrapper setWrapper = getRecord1();
			function.addInputFieldSet(setWrapper.getInputFieldSet());

			Layout layout = new Layout(function, setWrapper.getDisplayAttributesSet());

			// Print the XML
			EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			String serviceXML = beanFactory.serialiseBeanAsXML(function);
			System.out.println(serviceXML);

			String layoutXML = beanFactory.serialiseBeanAsXML(layout);
			System.out.println(layoutXML);

			// Write to GAXPF
			XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, unit.getKFILLibrary(), GAXRecordDataModel.SERVICE_CODE,
							function.getId(), Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), serviceXML);

			// Write to GBXPF
			XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, unit.getKFILLibrary(), GAXRecordDataModel.LAYOUT_CODE,
							layout.getId(), Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), layoutXML);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
