package com.misys.equation.function.test.el;

import java.io.StringReader;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.el.ELException;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import junit.framework.TestCase;

import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.parser.ELParser;
import org.apache.el.parser.Token;

import com.misys.equation.function.el.ELContextImpl;
import com.misys.equation.function.el.ELRuntime;

public class TestExpressions extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestExpressions.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	public void testCompareNameForTrue()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${(firstname eq 'MIKE' or firstname eq 'DAVE') and surname eq 'HARRIS'}";
		ELParser parser = new ELParser(new StringReader(elText));

		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			if (token.kind == ELRuntime.IDENTIFIER)
			{
				System.out.println(token.image);
			}
			token = parser.getNextToken();
		}

		context.bind("firstname", "MIKE");
		context.bind("surname", "HARRIS");
		try
		{
			ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);

			Object objectValue = valueExpression.getValue(context);
			assertTrue(objectValue instanceof Boolean);
			assertTrue(((Boolean) objectValue).booleanValue());
			System.out.println(objectValue);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	public void testConditional()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${a eq b ? 'ITS TRUE' : 'ITS FALSE'}";
		ELParser parser = new ELParser(new StringReader(elText));

		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			if (token.kind == ELRuntime.IDENTIFIER)
			{
				System.out.println(token.image);
			}
			token = parser.getNextToken();
		}

		context.bind("a", 5.1);
		context.bind("b", 5.10000000000000001);
		try
		{
			ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
			Object objectValue = valueExpression.getValue(context);
			assertTrue(objectValue instanceof String);
			System.out.println(objectValue);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void testNumericResult()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${parm1 + parm2}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", "1");
		context.bind("parm2", "2");
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		Object objectValue = valueExpression.getValue(context);
		assertTrue(objectValue instanceof Long);
		Long longValue = (Long) objectValue;
		assertEquals(3, longValue.intValue());
		System.out.println(objectValue);
	}

	public void testNumericCompare()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${parm1 < parm2}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", new BigDecimal(10.0));
		context.bind("parm2", new BigDecimal(20.0));
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		Object objectValue = valueExpression.getValue(context);
		assertTrue(objectValue instanceof Boolean);
		System.out.println(objectValue);
	}

	/**
	 * Tests that a user defined function (substring) is valid
	 */
	public void testFunction()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${fn:substring(parm1,'1','3')}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", "ABCDEFGHIJ");
		ValueExpression converted = factory.createValueExpression(context, elText, Object.class);
		Object convertedText = converted.getValue(context);
		System.out.println(convertedText);
		assertEquals("BC", convertedText);
	}

	// @Test(expected = java.lang.NumberFormatException.class)
	public void testNumberFormatException()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${weli3429 + sdf}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			if (token.kind == ELRuntime.IDENTIFIER)
			{
				// context.bind(token.image, functionData.rtvFieldInputValue(token.image.toUpperCase()));
				context.bind(token.image, "");
			}
			token = parser.getNextToken();
		}
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		boolean numberFormatExceptionThrown = false;
		try
		{
			valueExpression.getValue(context);
		}
		catch (NumberFormatException e)
		{
			numberFormatExceptionThrown = true;
		}
		assertTrue(numberFormatExceptionThrown);
	}

	public void testAnotherException()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${we~'li3429 eq 'sdf'}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			if (token.kind == ELRuntime.IDENTIFIER)
			{
				// context.bind(token.image, functionData.rtvFieldInputValue(token.image.toUpperCase()));
				context.bind(token.image, "");
			}
			token = parser.getNextToken();
		}
		// 
		boolean numberFormatExceptionThrown = false;
		try
		{
			// The createValueExpression method throws a ParseException...
			ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
			valueExpression.getValue(context);
		}
		catch (ELException e)
		{
			numberFormatExceptionThrown = true;
		}
		assertTrue(numberFormatExceptionThrown);
	}

	public void testDateCompare()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${parm1 < parm2}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", new GregorianCalendar(2000, 11, 11));
		context.bind("parm2", new GregorianCalendar(2000, 11, 10));
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		Object objectValue = valueExpression.getValue(context);
		assertTrue(objectValue instanceof Boolean);
		System.out.println(objectValue);
	}

	public void testBigDecimal()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${parm1 * -1}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", new BigDecimal(10));
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		Object objectValue = valueExpression.getValue(context);
		System.out.println("testBigDecimal must be -10 == " + objectValue);
		assertTrue(objectValue instanceof BigDecimal);
		System.out.println(objectValue);
	}

	public void testDouble()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${parm1 * -1.0}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", 10);
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		Object objectValue = valueExpression.getValue(context);
		System.out.println("testDouble must be -10.0 == " + objectValue);
		assertTrue(objectValue instanceof Double);
		System.out.println(objectValue);
	}

	public void testParseLong()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${fn:trim(parm1) * -1}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", "  100  ");
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		Object objectValue = valueExpression.getValue(context);
		System.out.println("testParseLong must be  -100 == " + objectValue);
		assertTrue(objectValue instanceof Long);
		System.out.println(objectValue);
	}

	public void testConvertToLongBackToString()
	{
		// get us an expression factory
		ExpressionFactory factory = new ExpressionFactoryImpl();
		ELContextImpl context = new ELContextImpl();

		String elText = "${fn:toStringLong(fn:trim(parm1) * -1)}";
		ELParser parser = new ELParser(new StringReader(elText));
		Token token = parser.getNextToken();
		while (!token.image.equals(""))
		{
			System.out.println(token.kind + ":" + token.image);
			token = parser.getNextToken();
		}
		context.bind("parm1", "  200  ");
		ValueExpression valueExpression = factory.createValueExpression(context, elText, Object.class);
		Object objectValue = valueExpression.getValue(context);
		System.out.println("testConvertToLongBackToString must be  -200 == " + objectValue);
		assertTrue(objectValue instanceof String);
		System.out.println(objectValue);
	}

}
