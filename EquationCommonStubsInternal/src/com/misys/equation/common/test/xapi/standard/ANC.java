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
package com.misys.equation.common.test.xapi.standard;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.TestEnvironment;

public class ANC
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ANC.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EquationStandardSession session;
	EquationStandardTransaction anc;
	EquationStandardTransaction caa;
	EquationStandardTransaction mco;
	// fields required for ANC, change according to your environment
	String cus = "555506";
	String clc = "   ";
	String ctp = "EA";
	String cpnc = cus;
	String cun = "Frederic";
	String das = "FREDDY";
	String crb1 = "00";
	String crb2 = "00";
	// fields required for CAA, change according to your environment
	String na1 = "Fred House";
	String na2 = "Fred Street";
	String na3 = "Fred Town";
	String na4 = "Fred County";
	String na5 = "Fred Country";
	// fields required for MCO, change according to your environment
	String c1r = "AA";
	public ANC()
	{
		try
		{
			session = TestEnvironment.getTestEnvironment().getStandardSession();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		try
		{
			ANC test = new ANC();
			test.test();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
	public void test()
	{
		try
		{
			/*
			 * OK, as we enter this routine we should have a job on the as400. Put a breakpoint on the next available line of code
			 * Find the job and have a look at its Commitment Control status I usually do this by doing...
			 * 
			 * WRKOBJLCK <user profile you specified in TestEnvironment> *USRPRF or WRKJOB and enter the details that should have
			 * been displayed in the log
			 * 
			 * Take option 16, then option 5 on the EQUATION activation group, then press F6 to see the resource under commitment
			 * control, then take option 1 on the records and then press F5 at each of the suggested breakpoints in the code below.
			 */
			// Get an "Add New Customer" API...
			anc = new EquationStandardTransaction("G01ARRANC", session);
			// Set the key fields
			anc.setFieldValue("GZCUS", cus);
			anc.setFieldValue("GZCLC", clc);
			// Now set detail fields...
			anc.setFieldValue("GZCTP", ctp);
			anc.setFieldValue("GZCPNC", cpnc);
			anc.setFieldValue("GZCUN", cun);
			anc.setFieldValue("GZDAS", das);
			anc.setFieldValue("GZCRB1", crb1);
			anc.setFieldValue("GZCRB2", crb2);
			// Call the API
			session.addEquationTransaction(anc);
			// OK, so now we should have a customer. Perhaps pop a break point
			// around here and have a look at the iSeries job and see the commitment status
			// Onwards, now. Lets try the address...
			caa = new EquationStandardTransaction("H60FRRCAA", session);
			// Set the fields
			caa.setFieldValue("GZCUS", cus);
			caa.setFieldValue("GZCLC", clc);
			// Now set detail fields...
			caa.setFieldValue("GZNA1", na1);
			caa.setFieldValue("GZNA2", na2);
			caa.setFieldValue("GZNA3", na3);
			caa.setFieldValue("GZNA4", na4);
			caa.setFieldValue("GZNA5", na5);
			// Enter the address
			session.addEquationTransaction(caa);
			// OK, so now we should have a customer addess. Perhaps pop a break point
			// around here and have a look at the iSeries job and see the commitment status
			// Onwards, now. Lets try the other details...
			mco = new EquationStandardTransaction("G09MRRMCO", session);
			// Set the fields
			mco.setFieldValue("GZCUS", cus);
			mco.setFieldValue("GZCLC", clc);
			// Now set detail fields...
			mco.setFieldValue("GZC1R", c1r);
			// maintain the customer other details
			session.addEquationTransaction(mco);
			// now we are in a position to commit the transactions...
			session.applyTransactions();
			// Maybe pop in another breakpoint on the next two lines of code to see the state of play of the iSeries job?
			session.commitTransactions();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
