package com.misys.equation.bankfusion.test;

import java.util.HashMap;
import java.util.Hashtable;

import test.old.BFCommandHelper;

public class TestMultiThreadExecuteMF
{
	private BFCommandHelper bfCommandHelper;
	public TestMultiThreadExecuteMF()
	{
		super();
		bfCommandHelper = BFCommandHelper.getBFCommandHelper();
	}
	public void logon()
	{
		bfCommandHelper.start();
		bfCommandHelper.logOn("retail", "retail");
	}
	public void logoff()
	{
		bfCommandHelper.logOff();
		bfCommandHelper.closedown();
	}
	public class TestThread extends Thread
	{
		public TestThread(String str)
		{
			super(str);
		}
		public void run()
		{
			Hashtable<String, String> mfParms = new Hashtable<String, String>();
			HashMap<?, ?> bp;
			for (int i = 0; i < 10; i++)
			{
				mfParms.put("innyMF", i + " " + getName());
				mfParms.put("outtyMF", "");
				bp = bfCommandHelper.executeBusinessProcess("retail", "MicroflowWithAS1", mfParms);
				System.out.println(bp.get("outtyMF"));
			}
			System.out.println("DONE! " + getName());
		}
	}
	public static void main(String[] args)
	{
		// Get us a helper that will create the BF call environment
		TestMultiThreadExecuteMF test = new TestMultiThreadExecuteMF();
		test.logon();
		Thread thread1 = test.kickOffTestThread("a");
		Thread thread2 = test.kickOffTestThread("b");
		Thread thread3 = test.kickOffTestThread("c");
		Thread thread4 = test.kickOffTestThread("d");
		Thread thread5 = test.kickOffTestThread("e");
		Thread thread6 = test.kickOffTestThread("f");
		Thread thread7 = test.kickOffTestThread("g");
		Thread thread8 = test.kickOffTestThread("h");
		Thread thread9 = test.kickOffTestThread("i");
		Thread thread10 = test.kickOffTestThread("j");
		try
		{
			// wait for the threads to die
			thread1.join();
			thread2.join();
			thread3.join();
			thread4.join();
			thread5.join();
			thread6.join();
			thread7.join();
			thread8.join();
			thread9.join();
			thread10.join();
			// ready to log off
			test.logoff();
			System.out.println("DONE! " + Thread.currentThread().getName());
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Thread kickOffTestThread(String name)
	{
		TestThread thread = new TestThread(name);
		thread.start();
		return thread;
	}
}
