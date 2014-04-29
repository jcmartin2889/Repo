package com.misys.equation.function.test.option;

import java.util.Calendar;

import com.misys.equation.common.dao.beans.GAXRecordDataModel;
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
import com.misys.equation.function.tools.XMLToolbox;

public class PV4 extends TestOptionStub
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PV4.java 4735 2009-09-15 16:58:25Z lima12 $";

	public PV4()
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
		PV4 test = new PV4();
		test.test();
	}

	private DisplayFieldWrapper getField(String id, String label, int length, String prompt, String decode)
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

		return wrapper;
	}

	private DisplayFieldSetWrapper getRecord1() throws Exception
	{
		InputFieldSet inputFieldSet;

		// Record 1
		DisplayFieldSetWrapper setWrapper = new DisplayFieldSetWrapper();
		setWrapper.setId("PV4");
		setWrapper.setLabel("PV Testing");
		setWrapper.setDescription("This is the PV testing");
		inputFieldSet = setWrapper.getInputFieldSet();
		inputFieldSet.setValidateUserExit("");

		DisplayAttributesSet displayAttributesSet = setWrapper.getDisplayAttributesSet();
		displayAttributesSet.setNextScreenUserExit("");

		// All the fields for record 1
		setWrapper.addDisplayFieldWrapper(getField("JXR01R", "JXR01R", 50, "JXR01R", ""));
		setWrapper.addDisplayFieldWrapper(getField("LTR30R", "LTR30R", 50, "LTR30R", ""));
		setWrapper.addDisplayFieldWrapper(getField("OSR01R", "OSR01R", 50, "OSR01R", ""));
		setWrapper.addDisplayFieldWrapper(getField("OSR37R", "OSR37R", 50, "OSR37R", ""));
		setWrapper.addDisplayFieldWrapper(getField("OSR40R", "OSR40R", 50, "OSR40R", ""));

		return setWrapper;
	}

	private void test()
	{
		// Have a bash...
		try
		{
			// Create the FUNCTION
			Function function = new Function();
			function.setId("PV4");
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
