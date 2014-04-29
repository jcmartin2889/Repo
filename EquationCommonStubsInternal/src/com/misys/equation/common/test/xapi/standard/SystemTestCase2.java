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

public class SystemTestCase2
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemTestCase2.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EquationStandardSession session;
	EquationStandardTransaction mcd;
	EquationStandardTransaction caa;
	EquationStandardTransaction mab;
	// fields required for MCD, change according to your environment
	String cus = "100208";
	String clc = "   ";
	String ctp = "EA";
	String cpnc = cus;
	String brnm = "LOND";
	String cun = "Anthony B Customer";
	String das = "A B Customer";
	String crb1 = "00";
	String crb2 = "00";
	String crf = "AA123456789B";
	// fields required for CAA, change according to your environment
	String na1 = "The White House";
	String na2 = "1 High Street";
	String na3 = "Madeup Town";
	String na4 = "Falseshire";
	String na5 = "UK";
	// fields required for MAB, change according to your environment
	String ab = "0543";
	String an = cus;
	String as = "001";
	String act = "CA";
	String shn = das;
	String ccy = "USD";
	String dte = "1000105";
	String cna = "GB";

	public SystemTestCase2()
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
			SystemTestCase2 test = new SystemTestCase2();
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

			mcd = new EquationStandardTransaction("G01MRRMCD", session);
			mcd.setFieldValue("GZCUS", cus);
			mcd.setFieldValue("GZCLC", clc);
			mcd.setFieldValue("GZCTP", ctp);
			mcd.setFieldValue("GZCPNC", cpnc);
			mcd.setFieldValue("GZCUN", cun);
			mcd.setFieldValue("GZDAS", das);
			mcd.setFieldValue("GZBRNM", brnm);
			mcd.setFieldValue("GZCRB1", crb1);
			mcd.setFieldValue("GZCRB2", crb2);
			mcd.setFieldValue("GZCUBD", "N");
			mcd.setFieldValue("GZCRF", crf);
			mcd.setFieldValue("GZCNAL", cna);
			mcd.setFieldValue("GZCNAR", cna);
			mcd.setFieldValue("GZCNAI", cna);
			session.addEquationTransaction(mcd);

			caa = new EquationStandardTransaction("H60FRRCAA", session);
			caa.setMode("M");
			caa.setFieldValue("GZCUS", cus);
			caa.setFieldValue("GZCLC", clc);
			caa.setFieldValue("GZNA1", na1);
			caa.setFieldValue("GZNA2", na2);
			caa.setFieldValue("GZNA3", na3);
			caa.setFieldValue("GZNA4", na4);
			caa.setFieldValue("GZNA5", na5);
			session.addEquationTransaction(caa);

			mab = new EquationStandardTransaction("H39MRRMAB", session);
			mab.setFieldValue("GZAB", ab);
			mab.setFieldValue("GZAN", an);
			mab.setFieldValue("GZAS", as);
			mab.setFieldValue("GZSHN", shn);
			mab.setFieldValue("GZCNAR", cna);
			mab.setFieldValue("GZCNAI", cna);
			mab.setFieldValue("GZOAD", dte);
			session.addEquationTransaction(mab);

			session.applyTransactions();
			session.rollbackTransactions();
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
