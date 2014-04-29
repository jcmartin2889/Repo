package com.misys.equation.function.test.el;

import junit.framework.TestCase;

import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.el.ELRuntime;

public class Test1 extends TestCase
{
	// This attribute is used to store cvs version information.

	public void testExpression1()
	{
		FieldData fieldData = new FieldData("CUS", "A", 6, 0);
		FunctionData functionData = new FunctionData();
		functionData.addFieldData("CUS", fieldData);
		functionData.chgFieldDbValue("CUS", "CUST01");

		String expression = "fn:concat(\"Andrew Sutton Cust \",fn:substring(CUS,fn:length(CUS)-3,fn:length(CUS))) ";
		Object objectValue = ELRuntime.runStringScript(expression, functionData, ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("Andrew Sutton Cust T01"));
		System.out.println(objectValue);
	}

	public void testExpression2()
	{
		FieldData fieldData = new FieldData("CUS", "A", 6, 0);
		FunctionData functionData = new FunctionData();
		functionData.addFieldData("CUS", fieldData);
		functionData.chgFieldDbValue("CUS", "CUST01");

		String expression = "fn:concat(\"Andrew Sutton Cust \",fn:substring(CUS,fn:length(CUS)-2,fn:length(CUS))) ";
		Object objectValue = ELRuntime.runStringScript(expression, functionData, ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("Andrew Sutton Cust 01"));
		System.out.println(objectValue);
	}

	public void testExpression3()
	{
		FieldData fieldData = new FieldData("CUS", "A", 6, 0);
		FunctionData functionData = new FunctionData();
		functionData.addFieldData("CUS", fieldData);
		functionData.chgFieldDbValue("CUS", "CUST01");

		String expression = "fn:concat(\"Andrew Sutton Cust \",fn:substring(CUS,fn:length(CUS)-1,fn:length(CUS))) ";
		Object objectValue = ELRuntime.runStringScript(expression, functionData, ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("Andrew Sutton Cust 1"));
		System.out.println(objectValue);
	}

	public void testExpression4()
	{
		FieldData fieldData = new FieldData("CUS", "A", 6, 0);
		FunctionData functionData = new FunctionData();
		functionData.addFieldData("CUS", fieldData);
		functionData.chgFieldDbValue("CUS", "CUST01");

		String expression = "fn:parseDouble(fn:substring(CUS,fn:length(CUS)-1,fn:length(CUS)))";
		Object objectValue = ELRuntime.runStringScript(expression, functionData, ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("1.0"));
		System.out.println(objectValue);
	}

	public void testExpression5()
	{
		String expression = "fn:substring(\"Aa\",1,2)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("a"));
		System.out.println(objectValue);
	}

	public void testExpression6()
	{
		String expression = "fn:pad(\"Aa\",10)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("Aa        "));
		System.out.println(objectValue);
	}

	public void testExpression7()
	{
		String expression = "fn:truncate(\"123456789012345678901234567890\",15)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("123456789012345"));
		System.out.println(objectValue);
	}

	public void testExpression8()
	{
		String expression = "fn:padTruncate(\"123456789012345678901234567890\",20)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("12345678901234567890"));
		System.out.println(objectValue);
	}

	public void testExpression9()
	{
		String expression = "fn:padTruncate(\"1\",20)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("1                   "));
		System.out.println(objectValue);
	}

	public void testExpression10()
	{
		String expression = "fn:firstChars(\"1234567890\",3)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("123"));
		System.out.println(objectValue);

		expression = "fn:firstChars(\"1\",3)";
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		str = (String) objectValue;
		assertTrue(str.equals("1"));
		System.out.println(objectValue);

		expression = "fn:firstChars(\"123\",3)";
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		str = (String) objectValue;
		assertTrue(str.equals("123"));
		System.out.println(objectValue);

	}

	public void testExpression11()
	{
		String expression = "fn:lastChars(\"1234567890\",7)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("4567890"));
		System.out.println(objectValue);

		expression = "fn:lastChars(\"1234\",7)";
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		str = (String) objectValue;
		assertTrue(str.equals("1234"));
		System.out.println(objectValue);

		expression = "fn:lastChars(\"1234567\",7)";
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		str = (String) objectValue;
		assertTrue(str.equals("1234567"));
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		System.out.println(objectValue);
	}

	public void testExpression12()
	{
		String expression = "fn:parseDouble(\"20\")==20? 'twenty' : 'thirty'";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("twenty"));
		System.out.println(objectValue);

		expression = "fn:parseLong(\"20\")==20? 'twenty' : 'thirty'";
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		str = (String) objectValue;
		assertTrue(str.equals("twenty"));
		System.out.println(objectValue);
	}

	public void testExpression13()
	{
		String expression = "fn:formatRate(1234.5, 5, 6)";
		Object objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		String str = (String) objectValue;
		assertTrue(str.equals("01234500000 "));
		System.out.println(objectValue);

		expression = "fn:formatInt(-1234, 7)";
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		str = (String) objectValue;
		assertTrue(str.equals("0001234-"));
		System.out.println(objectValue);

		expression = "fn:formatRate(-1234.5, 5, 6)";
		objectValue = ELRuntime.runStringScript(expression, new FunctionData(), ELRuntime.INPUT_VALUE);
		str = (String) objectValue;
		assertTrue(str.equals("01234500000-"));
		System.out.println(objectValue);
	}

}
