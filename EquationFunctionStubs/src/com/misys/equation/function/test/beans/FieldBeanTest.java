package com.misys.equation.function.test.beans;

import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.problems.Message;

/**
 * Set of test cases to test the validation of Function Field beans
 * 
 * @author perkinj1
 * 
 */
public class FieldBeanTest extends BeanTestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FieldBeanTest.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	public void testBlankDataType()
	{
		Field field = new InputField();

		Message message = validateForOneMessage(field);
		assertNotNull(message);
		assertEquals(LanguageResources.getString("Language.DataTypeMustNotBeBlank"), message.getText());

	}

	public void testFieldLength()
	{

		Field field = new InputField();
		field.setDataType("P");
		field.setSize("");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.FieldLengthMustNotBeBlankOrZero"));
		assertNotNull(message);

		field.setSize("SF�$�^�");
		message = getMessageWithText(field, LanguageResources.getString("Language.FieldLengthLongerThan5Chars"));
		assertNotNull(message);

		field.setSize("000");
		message = getMessageWithText(field, LanguageResources.getString("Language.FieldLengthMustNotBeBlankOrZero"));
		assertNotNull(message);

		field.setSize("SF��");
		message = getMessageWithText(field, LanguageResources.getString("Language.InvalidNumericsInFieldLength"));
		assertNotNull(message);

		field.setSize("99999");
		message = getMessageWithText(field, LanguageResources.getString("Language.InvalidNumericsInFieldLength"));
		assertNull(message);

	}

	/**
	 * The decimal places field should only be able to cater for a maximum of two digits:
	 */
	public void testDecimalPlacesTooLong()
	{
		Field field = new InputField();
		field.setDataType("P");
		field.setSize("8");
		field.setDecimals("100");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.DecimalPlacesGreaterThan2Characters"));
		assertNotNull(message);
	}

	/**
	 * Should only have valid numerics in decimal places:
	 */
	public void testInvalidDecimalPlaces()
	{
		Field field = new InputField();
		field.setDataType("P");
		field.setDecimals("W");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.InvalidNumericsInDecimalPlaces"));
		assertNotNull(message);

		field.setDecimals("�$");
		message = getMessageWithText(field, LanguageResources.getString("Language.InvalidNumericsInDecimalPlaces"));
		assertNotNull(message);
	}

	public void testDecimalPlacesGreaterThanLength()
	{
		Field field = new InputField();
		field.setDataType("P");
		field.setSize("8");
		field.setDecimals("9");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.NumberOfDecimalsGreaterThanFieldLength"));
		assertNotNull(message);
	}

	public void testDecimalPlacesNotGreaterThanLength()
	{
		Field field = new InputField();
		field.setDataType("P");
		field.setSize("9");
		field.setDecimals("9");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.NumberOfDecimalsGreaterThanFieldLength"));
		assertNull(message);
	}

	public void testDecimalPlacesValid()
	{
		Field field = new InputField();
		field.setDataType("P");
		field.setDecimals("15");
		Message message = getMessageWithText(field, LanguageResources.getString("Language.InvalidNumericsInDecimalPlaces"));
		assertNull(message);
	}

	public void testMultipleMessages()
	{
		Field field = new InputField();
		field.setDataType("P");
		field.setSize("0");
		field.setDecimals("34255");
		MessageContainer messageContainer = new MessageContainer();
		field.validateBean(messageContainer, null, true);
		assertEquals(2, messageContainer.getMessageCount());
	}

}
