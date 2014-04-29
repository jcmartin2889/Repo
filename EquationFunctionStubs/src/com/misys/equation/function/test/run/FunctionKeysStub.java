package com.misys.equation.function.test.run;

import com.misys.equation.function.runtime.FunctionKeys;

// TESTING FOR JOURNAL FILE
public class FunctionKeysStub
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionKeysStub.java 4735 2009-09-15 16:58:25Z lima12 $";
	public FunctionKeysStub()
	{
		try
		{
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public static void main(String[] inputParameters)
	{
		FunctionKeysStub test = new FunctionKeysStub();
		test.test();
	}

	private void test()
	{
		// Have a bash...
		try
		{
			FunctionKeys functionKeys = new FunctionKeys();
			functionKeys.addKey(new Integer(FunctionKeys.KEY_AUTH), "Authorised");
			functionKeys.addKey(new Integer(FunctionKeys.KEY_EXIT), "Exit");
			functionKeys.addKey(new Integer(FunctionKeys.KEY_ENT), "Enter");
			functionKeys.addKey(new Integer(FunctionKeys.KEY_ADD), "Add");
			functionKeys.addKey(new Integer(FunctionKeys.KEY_AUTHA), "Authorise");
			functionKeys.addKey(new Integer(FunctionKeys.KEY_HELP), "Help");
			functionKeys.addKey(new Integer(FunctionKeys.KEY_OVR), "Override");
			System.out.println(functionKeys.rtvFunctionKeys());
			System.out.println(functionKeys.rtvFunctionKeysWithLabel());
			System.out.println(functionKeys.rtvFunctionKeysText());
			System.out.println("Done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
