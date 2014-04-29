package com.misys.equation.function.test.beans;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;

/**
 * Set of test cases to test the validation of FieldSet Field beans
 * 
 * @author perkinj1
 * 
 */
public class FieldSetTest extends BeanTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FieldSetTest.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	private Function function;
	private InputFieldSet fs1;

	/**
	 * Create the function and an input field set
	 */
	@Override
	public void setUp()
	{
		function = new Function();
		function.setId("TST");
		function.setLabel("TEST Title");

		fs1 = new InputFieldSet("FS1", "FS1", "FS1");
		try
		{
			function.addInputFieldSet(fs1);
		}
		catch (EQException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 */
	public void testAddFieldOK()
	{
		InputField alphaField = new InputField();
		try
		{
			fs1.addInputField(alphaField);
		}
		catch (EQException e)
		{
			throw new RuntimeException(e);
		}
	}

	public void testModifyFieldIdMissingField()
	{
		Exception e = null;
		try
		{
			fs1.modifyFieldId("BOGUS", "NEW");
		}
		catch (EQException e1)
		{
			e = e1;
		}
		assertTrue(e instanceof EQException);
	}

	public void testMoveFieldUpMissingField()
	{
		InputField bogus = new InputField("X", "X", "X");
		Exception e = null;
		try
		{
			fs1.moveFieldUp(bogus);
		}
		catch (EQException e1)
		{
			e = e1;
		}
		assertTrue(e instanceof EQException);
	}

	public void testMoveFieldDownMissingField()
	{
		InputField bogus = new InputField("X", "X", "X");
		Exception e = null;
		try
		{
			fs1.moveFieldDown(bogus);
		}
		catch (EQException e1)
		{
			e = e1;
		}
		assertTrue(e instanceof EQException);
	}
}
