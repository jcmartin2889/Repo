package com.misys.equation.function.test.run;

import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;

// Via API
public class FunctionHandlerStubMultipleALZ extends FunctionHandlerStubTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionHandlerStubMultipleALZ.java 7085 2010-04-28 08:18:53Z weddelc1 $";

	EquationSystem eqSystem;
	EquationUnit eqUnit;

	public FunctionHandlerStubMultipleALZ()
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
		FunctionHandlerStubMultipleALZ test = new FunctionHandlerStubMultipleALZ();
		test.test();
	}

	public class ALZThread implements Runnable
	{
		boolean success = false;
		boolean done = false;
		int name;
		FunctionHandlerStubMultipleALZ01 stub;

		public ALZThread(int name)
		{
			this.name = name;
			stub = new FunctionHandlerStubMultipleALZ01(name, eqUnit);
		}

		public void run()
		{
			try
			{
				success = stub.test();
				done = true;
			}
			catch (Exception e)
			{
				success = false;
				done = true;
			}
		}

		public boolean isSuccess()
		{
			return success;
		}

		public boolean isDone()
		{
			return done;
		}
	}

	public boolean test()
	{
		try
		{
			eqSystem = new EquationSystem("SLOUGH1", "EQUIADMIN", "EQUIADMIN");
			eqUnit = eqSystem.getUnit("AL9");

			ALZThread alzThread1 = new ALZThread(1);
			ALZThread alzThread2 = new ALZThread(2);
			ALZThread alzThread3 = new ALZThread(3);

			Thread thread1 = new Thread(alzThread1);
			Thread thread2 = new Thread(alzThread2);
			Thread thread3 = new Thread(alzThread3);

			boolean notdone = true;

			// start the threads
			thread1.start();
			thread2.start();
			thread3.start();
			while (notdone)
			{
				if (alzThread1.isDone() && alzThread2.isDone() && alzThread3.isDone())
				{
					break;
				}

				Thread.sleep(1000);
			}

			return (alzThread1.isSuccess() && alzThread2.isSuccess() && alzThread3.isSuccess());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			cleanUp();
		}
	}

	public void testStub03_001()
	{
		FunctionHandlerStubMultipleALZ stub = new FunctionHandlerStubMultipleALZ();
		boolean success = stub.test();
		assertEquals(true, success);

	}

}
