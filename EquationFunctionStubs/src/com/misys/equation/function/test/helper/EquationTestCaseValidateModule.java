package com.misys.equation.function.test.helper;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.EquationTestCase;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.tools.DeploymentToolbox;
import com.misys.equation.function.tools.FunctionToolbox;

/**
 * Test cases for PVs
 */
abstract public class EquationTestCaseValidateModule extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationTestCaseValidateModule.java 14808 2012-11-05 11:58:49Z williae1 $";
	/** Wrapper around the Business and User Interface data */
	protected DisplayFieldSetWrapper setWrapper;

	public abstract void addFieldsWithPrompts() throws EQException;

	/**
	 * Creates a DisplayFieldWrapper
	 * 
	 * @param id
	 *            field id
	 * @param label
	 *            field label
	 * @param length
	 *            Field length
	 * @param prompt
	 *            Prompt module name
	 * @param decode
	 *            Prompt module decode
	 * 
	 * @return the DisplayFieldWrapper
	 */
	protected DisplayFieldWrapper createField(String id, String label, int length, String prompt, String decode) throws EQException
	{
		DisplayFieldWrapper wrapper = new DisplayFieldWrapper();
		wrapper.setId(id);
		wrapper.setLabel(label);
		wrapper.setDescription(label);
		// Business layer attributes
		InputField inputField = wrapper.getInputField();
		inputField.setLabel(label);
		inputField.setDescription(label);
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
		FunctionToolbox.addPVFieldSet(inputField, FunctionToolbox.getPVFieldSet(session, prompt, "", false, "N"));

		// UI layer attributes
		DisplayAttributes displayAttributes = wrapper.getDisplayAttributes();
		displayAttributes.setLabel(label);
		displayAttributes.setDescription(label);
		displayAttributes.setKeepWithPrevious(false);
		displayAttributes.setLabelPosition(DisplayAttributes.FIELD_LABEL_LEFT);
		displayAttributes.setMask("");
		// displayAttributes.setPrompt(prompt);
		displayAttributes.setProtected(DisplayAttributes.PROTECTED_NO);
		displayAttributes.setVisible(DisplayAttributes.VISIBLE_YES);
		displayAttributes.setWidget("");

		return wrapper;
	}

	public DisplayFieldSetWrapper createFieldSetWrapper()
	{
		DisplayFieldSetWrapper setWrapper = new DisplayFieldSetWrapper();
		setWrapper.setId("REC1");
		setWrapper.setLabel("PV Testing");
		setWrapper.setDescription("This is the PV testing");
		return setWrapper;
	}

	public void test()
	{
		try
		{
			// Create the FUNCTION
			Function function = new Function();
			function.setId(this.getClass().getSimpleName());
			function.setLabel("Prompt and Validate Test Stub");
			function.setDescription("Prompt and Validate Test Stub");

			Layout layout = new Layout(function, "", null, false, false);

			setWrapper = createFieldSetWrapper();

			// child classes have to implement the following method
			addFieldsWithPrompts();

			function.addInputFieldSet(setWrapper.getInputFieldSet());
			layout.addDisplayAttributesSet(setWrapper.getDisplayAttributesSet());
			// get the session
			EquationStandardSession session = unit.getEquationSessionPool().getEquationStandardSession();

			// DEPLOY IT...
			DeploymentToolbox deploymentToolbox = new DeploymentToolbox(session);
			deploymentToolbox.deployFunction(function, null, null);
			deploymentToolbox.deployLayout(layout, layout.getLabel(), null, null);
			//
			// // Print the XML
			// EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			// String xml = beanFactory.serialiseBeanAsXML(function);
			// System.out.println(xml);
			//
			// // Write to GAXPF
			// XMLToolbox.getXMLToolbox().writeFunctionXMLtoDB(unit,
			// Toolbox.formatTime(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), xml);
		}
		catch (Exception e)
		{
			// Any exception should be thrown so that JUnit can report an error
			throw new RuntimeException(e);
		}
	}
}
