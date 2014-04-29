package com.misys.equation.function.test.run;

// Via API
public class FunctionHandlerStub99 extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStub99.java 5067 2009-10-06 16:53:35Z lima12 $";
	public FunctionHandlerStub99()
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
		FunctionHandlerStub99 test = new FunctionHandlerStub99();
		test.test();
	}

	public boolean test()
	{
		cleanUp = true;
		cleanUp();
		return true;
	}

	public void testStub99()
	{
		FunctionHandlerStub99 stub = new FunctionHandlerStub99();
		boolean success = stub.test();
		assertEquals(true, success);
	}

}
