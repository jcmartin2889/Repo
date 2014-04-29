package com.misys.equation.common.test.connectivity;

import junit.framework.TestCase;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400ConnectionPool;
import com.ibm.as400.access.Job;
import com.ibm.as400.access.MessageFile;

public class AS400Test extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AS400Test.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 */
	public void test1()
	{
		MessageFile messageFile = null;
		try
		{
			// Create an AS400ConnectionPool...
			AS400ConnectionPool aS400ConnectionPool = new AS400ConnectionPool();
			// Set a maximum of "no max" connections to this pool.
			aS400ConnectionPool.setMaxConnections(-1);
			// Set a maximum lifetime for 5 seconds (ms*s).
			aS400ConnectionPool.setMaxLifetime(1000 * 5);

			// Get an AS400 for processing commands
			AS400 as400A = aS400ConnectionPool.getConnection("SLOUGH1", "WEDDELC1", "ACHT0NG", AS400.COMMAND);

			// Set the message file...
			messageFile = new MessageFile(as400A, "/QSYS.LIB/QCPFMSG.MSGF");

			// Get the job details
			Job as400AJob = messageFile.getSystem().getJobs(AS400.COMMAND)[0];

			// splat out some info about the objects we have and also a CPF message text
			System.out.println("A: " + as400A);
			System.out.println("A: " + as400AJob);
			System.out.println("A: " + messageFile.getMessage("CPFAFAF"));

			// return the as400 and try to regain access to in in the next try block...
			aS400ConnectionPool.returnConnectionToPool(as400A);
			as400A.isConnectionAlive();
			AS400 as400 = aS400ConnectionPool.getConnection("SLOUGH1", "WEDDELC1", "ACHT0NG", AS400.COMMAND);
			Job as400Job = as400.getJobs(AS400.COMMAND)[0];
			System.out.println("A: " + as400);
			System.out.println("A: " + as400Job);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			// Get a handle to the as400 associated to the messageFile, it returns the same object as as400A above...
			// Shouldn't this throw an exception?
			AS400 as400B = messageFile.getSystem();

			// get the job...
			Job as400BJob = as400B.getJobs(AS400.COMMAND)[0];

			System.out.println("B: " + as400B);// Same object...
			System.out.println("B: " + as400BJob);
			// Why does the following line of code work? Is this expected behaviour?
			System.out.println("B: " + messageFile.getMessage("CPFAFAF"));

			// pause the thread for 10 seconds (greater than the timeout) and then try to reuse...
			Thread.sleep(1000 * 10);

			AS400 as400C = messageFile.getSystem();
			Job as400CJob = as400C.getJobs(AS400.COMMAND)[0];
			System.out.println("C: " + as400C);// Same object...
			System.out.println("C: " + as400CJob);
			// Why does the following line of code work? Is this expected behaviour?
			System.out.println("C: " + messageFile.getMessage("CPFAFAF"));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 */
	public void test2()
	{
		MessageFile messageFile = null;
		try
		{
			// Create an AS400ConnectionPool...
			AS400ConnectionPool aS400ConnectionPool = new AS400ConnectionPool();
			// Set a maximum of "no max" connections to this pool.
			aS400ConnectionPool.setMaxConnections(-1);
			// Set a maximum lifetime for 5 seconds (ms*s).
			aS400ConnectionPool.setMaxLifetime(1000 * 5);

			long start = System.currentTimeMillis();
			// Set the message file...
			for (int i = 0; i < 100; i++)
			{
				// Get an AS400 for processing commands
				AS400 as400 = aS400ConnectionPool.getConnection("SLOUGH1", "WEDDELC1", "ACHT0NG", AS400.COMMAND);
				messageFile = new MessageFile(as400, "/QSYS.LIB/QCPFMSG.MSGF");
				System.out.println("test2: " + messageFile.getMessage("CPFAFAF"));
				aS400ConnectionPool.returnConnectionToPool(as400);
			}
			long end = System.currentTimeMillis();
			System.out.println("test2: " + (end - start) + " ms");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 */
	public void test3()
	{
		MessageFile messageFile = null;
		try
		{
			// Create an AS400ConnectionPool...
			AS400ConnectionPool aS400ConnectionPool = new AS400ConnectionPool();
			// Set a maximum of "no max" connections to this pool.
			aS400ConnectionPool.setMaxConnections(-1);
			// Set a maximum lifetime for 5 seconds (ms*s).
			aS400ConnectionPool.setMaxLifetime(1000 * 5);

			long start = System.currentTimeMillis();
			AS400 as400 = aS400ConnectionPool.getConnection("SLOUGH1", "WEDDELC1", "ACHT0NG", AS400.COMMAND);
			messageFile = new MessageFile(as400, "/QSYS.LIB/QCPFMSG.MSGF");
			for (int i = 0; i < 100; i++)
			{
				System.out.println("test3: " + messageFile.getMessage("CPFAFAF"));
			}
			long end = System.currentTimeMillis();
			System.out.println("test3: " + (end - start) + " ms");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 */
	public void test4()
	{
		MessageFile messageFile = null;
		try
		{
			// Create an AS400ConnectionPool...
			AS400ConnectionPool aS400ConnectionPool = new AS400ConnectionPool();
			// Set a maximum of "no max" connections to this pool.
			aS400ConnectionPool.setMaxConnections(-1);
			// Set a maximum lifetime for 5 seconds (ms*s).
			aS400ConnectionPool.setMaxLifetime(1000 * 5);

			long start = System.currentTimeMillis();
			// Set the message file...
			for (int i = 0; i < 100; i++)
			{
				// Get an AS400 for processing commands
				AS400 as400 = aS400ConnectionPool.getConnection("SLOUGH1", "WEDDELC1", "ACHT0NG", AS400.COMMAND);
				messageFile = new MessageFile(as400, "/QSYS.LIB/QCPFMSG.MSGF");
				System.out.println("test4: " + messageFile.getMessage("CPFAFAF"));
			}
			long end = System.currentTimeMillis();
			System.out.println("test4: " + (end - start) + " ms");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}