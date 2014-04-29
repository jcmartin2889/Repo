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
package com.misys.equation.common.perf;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.test.EquationTestCase;
import com.misys.equation.common.test.TestEnvironment;

public class SystemTestCase1UnThreaded extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemTestCase1UnThreaded.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	EquationStandardSession session;
	EquationStandardTransaction anc;
	EquationStandardTransaction anx;
	EquationStandardTransaction caa;
	EquationStandardTransaction mco;
	EquationStandardTransaction oca;
	EquationStandardTransaction rds;
	EquationStandardTransaction asiCr;
	EquationStandardTransaction asiDr;
	// fields required for ANC, change according to your environment
	String clc = "   ";
	String ctp = "EA";
	String brnm = "LOND";
	String cun = "Anthony Customer";
	String das = "A Customer";
	String crb1 = "00";
	String crb2 = "00";
	// fields required for CAA, change according to your environment
	String na1 = "The White House";
	String na2 = "1 High Street";
	String na3 = "Madeup Town";
	String na4 = "Hampshire";
	String na5 = "UK";
	// fields required for MCO, change according to your environment
	String c1r = "AA";
	// fields required for OCA, change according to your environment
	String ab = "0543";
	String as = "001";
	String act = "CA";
	String shn = das;
	String ccy = "USD";
	String dte = "1000105";
	// fields required for RDS, change according to your environment
	String dlp = "RTD";
	String dla = "100000";
	String prc = "Y1";
	String cashUSDAB = "0543";
	String cashUSDAN = "000001";
	String cashUSDAS = "010";
	String xm = "AC";

	// fields required for ASI-CR, change according to your environment
	String tcdCr = "510";
	String amtCr = "12300";
	// fields required for ASI-DR, change according to your environment
	String tcdDr = "010";
	String amtDr = "12100";

	public SystemTestCase1UnThreaded()
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
			SystemTestCase1UnThreaded test = new SystemTestCase1UnThreaded();
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
			long startTime = System.nanoTime();
			for (int x = 551200; x < 551201; x++)
			{
				String cus = String.valueOf(x);
				// ANX is required, otherwise the customer is blocked for deal input!
				anx = new EquationStandardTransaction("V30FRRANX", session);
				anx.setMode("A");
				anx.setFieldValue("GZCUS", cus);
				anx.setFieldValue("GZCLC", clc);
				anx.setFieldValue("GZEAD1", "Test@Misys.com");
				session.addEquationTransaction(anx);

				anc = new EquationStandardTransaction("G01ARRANC", session);
				anc.setFieldValue("GZCUS", cus);
				anc.setFieldValue("GZCLC", clc);
				anc.setFieldValue("GZCTP", ctp);
				anc.setFieldValue("GZCPNC", cus);
				anc.setFieldValue("GZCUN", cun);
				anc.setFieldValue("GZDAS", das);
				anc.setFieldValue("GZBRNM", brnm);
				anc.setFieldValue("GZCRB1", crb1);
				anc.setFieldValue("GZCRB2", crb2);
				anc.setFieldValue("GZCUBD", "N");
				session.addEquationTransaction(anc);

				caa = new EquationStandardTransaction("H60FRRCAA", session);
				caa.setMode("A");
				caa.setFieldValue("GZCUS", cus);
				caa.setFieldValue("GZCLC", clc);
				caa.setFieldValue("GZNA1", na1);
				caa.setFieldValue("GZNA2", na2);
				caa.setFieldValue("GZNA3", na3);
				caa.setFieldValue("GZNA4", na4);
				caa.setFieldValue("GZNA5", na5);
				session.addEquationTransaction(caa);

				mco = new EquationStandardTransaction("G09MRRMCO", session);
				mco.setFieldValue("GZCUS", cus);
				mco.setFieldValue("GZCLC", clc);
				mco.setFieldValue("GZC1R", c1r);
				session.addEquationTransaction(mco);

				oca = new EquationStandardTransaction("H38ARROCA", session);
				oca.setFieldValue("GZAB", ab);
				oca.setFieldValue("GZAN", cus);
				oca.setFieldValue("GZAS", as);
				oca.setFieldValue("GZACT", act);
				oca.setFieldValue("GZSHN", shn);
				oca.setFieldValue("GZCCY", ccy);
				oca.setFieldValue("GZOAD", dte);
				session.addEquationTransaction(oca);

				rds = new EquationStandardTransaction("G36ARRRDS", session);
				rds.setFieldValue("GZDLP", dlp);
				rds.setFieldValue("GZDLR", cus + "0000001");
				rds.setFieldValue("GZBRNM", brnm);
				rds.setFieldValue("GZCUS", cus);
				rds.setFieldValue("GZCLC", clc);
				rds.setFieldValue("GZCCY", ccy);
				rds.setFieldValue("GZDLA", dla);
				rds.setFieldValue("GZSDT", dte);
				rds.setFieldValue("GZCTRD", dte);
				rds.setFieldValue("GZPRC", prc);
				rds.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
				rds.setFieldValue("GZAPP", "RD"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
				rds.setFieldValue("GZYROL", "A"); // Are rollovers allowed for deals of this type? (1A)
				rds.setFieldValue("GZABF", cashUSDAB);
				rds.setFieldValue("GZANF", cashUSDAN);
				rds.setFieldValue("GZASF", cashUSDAS);
				rds.setFieldValue("GZXMF", xm);
				rds.setFieldValue("GZABM", ab);
				rds.setFieldValue("GZANM", cus);
				rds.setFieldValue("GZASM", as);
				rds.setFieldValue("GZXMM", xm);
				rds.setFieldValue("GZABI", ab);
				rds.setFieldValue("GZANI", cus);
				rds.setFieldValue("GZASI", as);
				rds.setFieldValue("GZXMI", xm);
				rds.setFieldValue("GZCCYF", ccy);
				rds.setFieldValue("GZCCYM", ccy);
				rds.setFieldValue("GZCCYI", ccy);

				session.addEquationTransaction(rds);

				asiCr = new EquationStandardTransaction("H15ARRASI", session);
				asiCr.setFieldValue("GZAB", ab);
				asiCr.setFieldValue("GZAN", cus);
				asiCr.setFieldValue("GZAS", as);
				asiCr.setFieldValue("GZTCD", tcdCr);
				asiCr.setFieldValue("GZCCY", ccy);
				asiCr.setFieldValue("GZAMA", amtCr);
				asiCr.setFieldValue("GZVFR", dte);
				asiCr.setFieldValue("GZDRF", "JAVA TEST");
				asiCr.setFieldValue("GZNPE", "1");
				asiCr.setFieldValue("GZPBR", "TEST");
				asiCr.setFieldValue("GZBRNM", brnm);
				session.addEquationTransaction(asiCr);

				asiDr = new EquationStandardTransaction("H15ARRASI", session);
				asiDr.setFieldValue("GZAB", ab);
				asiDr.setFieldValue("GZAN", cus);
				asiDr.setFieldValue("GZAS", as);
				asiDr.setFieldValue("GZTCD", tcdDr);
				asiDr.setFieldValue("GZCCY", ccy);
				asiDr.setFieldValue("GZAMA", amtDr);
				asiDr.setFieldValue("GZVFR", dte);
				asiDr.setFieldValue("GZDRF", "JAVA TEST");
				asiDr.setFieldValue("GZNPE", "1");
				asiDr.setFieldValue("GZPBR", "TEST");
				asiDr.setFieldValue("GZBRNM", brnm);
				session.addEquationTransaction(asiDr);
				session.applyTransactions();
				session.commitTransactions();
			}
			long endTime = System.nanoTime();

			System.err.println((endTime - startTime));
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
