package com.misys.equation.function.test.bf;

import junit.framework.TestCase;

import org.w3c.dom.Document;

import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputGroup;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.test.helper.DisplayFieldSetWrapper;
import com.misys.equation.function.test.helper.DisplayFieldWrapper;
import com.misys.equation.function.tools.XSDToolbox;

public class TestSchemaGeneration extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestSchemaGeneration.java 17211 2013-09-05 14:25:27Z williae1 $";

	/** The Id of the repeating group */
	private static final String REPEATING_GROUP_ID = "A";

	private DisplayFieldSetWrapper getSet1()
	{
		DisplayFieldSetWrapper setWrapper = new DisplayFieldSetWrapper();
		DisplayFieldWrapper fieldWrapper;

		InputField inputField;

		setWrapper.setId("REC1");
		setWrapper.setLabel("REC1");
		setWrapper.setDescription("REC1");

		// AB
		fieldWrapper = setWrapper.addDisplayField("AB");
		fieldWrapper.setLabel("Account branch");
		fieldWrapper.setDescription("This is the account branch");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("4");
		inputField.setMaxLength("4");
		inputField.setMinLength("4");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setRegExp("");
		inputField.setType("AB");
		inputField.setUpperCase(true);

		// AN
		fieldWrapper = setWrapper.addDisplayField("AN");
		fieldWrapper.setLabel("Account number");
		fieldWrapper.setDescription("This is the account number");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("6");
		inputField.setMaxLength("6");
		inputField.setMinLength("6");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setRegExp("");
		inputField.setType("");
		inputField.setUpperCase(true);
		inputField.setValidValues("");

		// AS
		fieldWrapper = setWrapper.addDisplayField("AS");
		fieldWrapper.setLabel("Account suffix");
		fieldWrapper.setDescription("This is the account suffix");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("3");
		inputField.setMaxLength("3");
		inputField.setMinLength("3");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setRegExp("");
		inputField.setType("AS");
		inputField.setUpperCase(true);
		inputField.setValidValues("");

		// EAN
		fieldWrapper = setWrapper.addDisplayField("EAN");
		fieldWrapper.setLabel("External account number");
		fieldWrapper.setDescription("This is the external account number");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("20");
		inputField.setMaxLength("20");
		inputField.setMinLength("1");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setRegExp("");
		inputField.setType("");
		inputField.setUpperCase(true);
		inputField.setValidValues("");

		// BRNM
		fieldWrapper = setWrapper.addDisplayField("BRNM");
		fieldWrapper.setLabel("Branch mnemonic");
		fieldWrapper.setDescription("This is the branch mnemonic");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("4");
		inputField.setMaxLength("4");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_YES);
		inputField.setRegExp("");
		inputField.setType("BRNM");
		inputField.setUpperCase(true);
		inputField.setValidValues("");

		// BRNM2
		fieldWrapper = setWrapper.addDisplayField("BRNM2");
		fieldWrapper.setLabel("Branch mnemonic2");
		fieldWrapper.setDescription("This is the second branch mnemonic");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("4");
		inputField.setMaxLength("4");
		inputField.setDecimals("0");
		inputField.setLocked("");
		inputField.setMandatory(InputField.MANDATORY_NO);
		inputField.setRegExp("");
		inputField.setType("BRNM");
		inputField.setUpperCase(true);
		inputField.setValidValues("");

		// Add the InputGroup
		setWrapper.getInputFieldSet().addInputGroup(new InputGroup(REPEATING_GROUP_ID));

		// HZSFX
		fieldWrapper = setWrapper.addDisplayField("HZSFX");
		fieldWrapper.setLabel("Account suffix ");
		fieldWrapper.setDescription("This is the (repeating) account suffix");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("3");
		inputField.setType("SFX");
		inputField.setUpperCase(true);
		inputField.setRepeatingGroup(REPEATING_GROUP_ID);

		// HZATP
		fieldWrapper = setWrapper.addDisplayField("HZATP");
		fieldWrapper.setLabel("Account type ");
		fieldWrapper.setDescription("This is the (repeating) account type");
		inputField = fieldWrapper.getInputField();
		inputField.setDataType("A");
		inputField.setSize("2");
		inputField.setType("ATP");
		inputField.setUpperCase(true);
		inputField.setRepeatingGroup(REPEATING_GROUP_ID);

		return setWrapper;
	}

	public void test()
	{
		// Have a bash...
		try
		{
			// Create the FUNCTION
			Function function = new Function();
			function.setId("ALZ");
			function.setLabel("Inter Account Transfer");
			function.setDescription("This is the description of Inter account transfer");

			Layout layout = new Layout(function, "", null, false, false);

			// Records
			DisplayFieldSetWrapper set1 = getSet1();

			function.addInputFieldSet(set1.getInputFieldSet());

			// Print the XML
			EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			String xml = beanFactory.serialiseBeanAsXML(function);
			System.out.println(xml);

			layout.addDisplayAttributesSet(set1.getDisplayAttributesSet());

			// xml = beanFactory.serialiseBeanAsXML(layout);
			Document document = XSDToolbox.getFunctionSchemaDocument(function, null, false);
			xml = XSDToolbox.serialiseDocument(document);
			System.out.println(xml);

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
