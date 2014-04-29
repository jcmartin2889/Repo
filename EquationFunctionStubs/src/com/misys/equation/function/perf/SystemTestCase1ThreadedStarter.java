/*
 * This sample code is provided by Misys for illustrative purposes only.
 * 
 * These examples have not been thoroughly tested under all conditions.
 * 
 * Misys, therefore, cannot guarantee or imply reliability, serviceability, or function of these programs.
 * 
 * All programs contained herein are provided to you "AS IS" without any warranties of any kind. The implied warranties of
 * non-infringement, merchantability and fitness for a particular purpose are expressly disclaimed.
 */
package com.misys.equation.function.perf;

import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.internal.eapi.core.EQException;

public class SystemTestCase1ThreadedStarter
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemTestCase1ThreadedStarter.java 7085 2010-04-28 08:18:53Z weddelc1 $";
	private static EquationSystem eqSystem;
	private static EquationUnit eqUnit;
	public static final int numberOfTransactionsInAThread = 100;
	public static final int numberOfThreads = 1;
	public static final String systemId = "IS0614";
	public static final String unitId = "EQQ";
	public static final String userId = "EQ4ADMIN";
	public static final String password = "EQ4ADMIN";
	public static final int startingCustomerNumber = 524000;

	public static void main(String[] args)
	{
		try
		{
			eqSystem = new EquationSystem(systemId, userId, password);
			eqUnit = eqSystem.getUnit(unitId);
			for (int x = 0; x < numberOfThreads; x++)
			{
				int cus = (numberOfTransactionsInAThread * x) + startingCustomerNumber;
				new Thread(new SystemTestCase1Threaded(eqUnit, cus, numberOfTransactionsInAThread), "Customer:" + cus).start();
			}
		}
		catch (EQException e)
		{
			e.printStackTrace();
		}
	}
}
