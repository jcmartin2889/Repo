package com.misys.equation.function.test.beans;

import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.problems.Message;

/**
 * Set of test cases to test the validation of InputField beans
 * 
 */
public class InputFieldBeanTest extends BeanTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: InputFieldBeanTest.java 13190 2012-04-05 09:42:31Z jonathan.perkins $";

	/** Upper case is only disallowed for boolean fields */
	public void testInvalidUpperCase()
	{
		InputField field = new InputField();
		field.setUpperCase(true);

		field.setDataType(EqDataType.TYPE_BOOLEAN);
		String expected = LanguageResources.getString("Language.UpperCaseInputNotRelevantToBoolean");
		Message message = getMessageWithText(field, expected);
		Object actual = message == null ? null : message.getText();
		assertEquals(expected, actual);

	}

	public void testAlphaMinimumLengthInvalidNumerics()
	{
		InputField field = new InputField();

		field.setUpperCase(true);
		field.setDataType("A");
		field.setMinLength("SDFS");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.UpperCaseInputOnlyForAlphaDataType"));
		assertNull(message);
	}

	public void testMinimumLengthTextLimitLongerThanField()
	{
		InputField field = new InputField();
		field.setDataType("A");
		field.setSize("4");
		field.setMinLength("23");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.MinimumFieldLengthLongerThanFieldLength"));
		assertNotNull(message);
	}

	public void testMaximumLengthTextLimit()
	{
		InputField field = new InputField();
		field.setSize("10");
		field.setDataType("A");
		field.setMaxLength("123456789012345678");
		Message message = getMessageWithText(field, LanguageResources
						.getString("Language.MinimumFieldLengthGreaterThan17Characters"));
		assertNull(message);
	}

	public void testValidUpperCase()
	{
		InputField field = new InputField();
		field.setUpperCase(true);
		field.setDataType("A");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.UpperCaseInputOnlyForAlphaDataType"));
		assertNull(message);
	}
}
