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

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.utilities.EquationLogger;

public class SystemTestCase1Threaded implements Runnable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemTestCase1Threaded.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(SystemTestCase1Threaded.class);

	// Non Static trannie vars...
	private EquationStandardSession session;
	private EquationUnit eqUnit;
	private int numberToAdd;
	private int cus;

	private EquationStandardTransaction anc;
	private EquationStandardTransaction anx;
	private EquationStandardTransaction caa;
	private EquationStandardTransaction mco;
	private EquationStandardTransaction oca;
	private EquationStandardTransaction rds;
	private EquationStandardTransaction asiCr;
	private EquationStandardTransaction asiDr;
	// fields required for ANC, change according to your environment
	private static final String clc = "   ";
	private static final String ctp = "EA";
	private static final String brnm = "LOND";
	private static final String cun = "Anthony Customer";
	private static final String das = "A Customer";
	private static final String crb1 = "00";
	private static final String crb2 = "00";
	// fields required for CAA, change according to your environment
	private static final String na1 = "The White House";
	private static final String na2 = "1 High Street";
	private static final String na3 = "Madeup Town";
	private static final String na4 = "Hampshire";
	private static final String na5 = "UK";
	// fields required for MCO, change according to your environment
	private static final String c1r = "AA";
	// fields required for OCA, change according to your environment
	private static final String ab = "0543";
	private static final String as = "001";
	private static final String act = "CA";
	private static final String shn = das;
	private static final String ccy = "GBP";
	private static final String dte = "1000105";
	// fields required for RDS, change according to your environment
	private static final String dlp = "RTD";
	private static final String dla = "100000";
	private static final String prc = "P3";
	// private static final String cashUSDAB = "0543";
	// private static final String cashUSDAN = "012826";
	// private static final String cashUSDAS = "002";
	private static final String xm = "AC";

	// fields required for ASI-CR, change according to your environment
	private static final String tcdCr = "510";
	private static final String amtCr = "12300";
	// fields required for ASI-DR, change according to your environment
	private static final String tcdDr = "010";
	private static final String amtDr = "12100";

	public SystemTestCase1Threaded(EquationUnit eqUnit, int startingCus, int numberToAdd)
	{
		try
		{
			this.cus = startingCus;
			this.eqUnit = eqUnit;
			this.numberToAdd = numberToAdd;

		}
		catch (Exception e)
		{
			LOG.error("Constructor:", e);
		}
	}

	public void run()
	{
		long startTime = System.nanoTime();
		try
		{
			session = eqUnit.getEquationSessionPool().getEquationStandardSession();
			PreparedStatement statement = session.getConnection().prepareStatement("SELECT COUNT(*) FROM GFPF WHERE GFCPNC=?");
			// We might need to change USA24R for API calls in a threaded environment due to duplicate GYs.
			// For the time being I'm adding unique WSIDs as a workaround.
			for (int i = 0; i < numberToAdd; i++)
			{
				String cusStr = String.valueOf(cus);
				String wsid = cusStr.substring(2, 6);

				// See if the customer already exists...
				statement.setString(1, cusStr);
				ResultSet resultSet = statement.executeQuery();
				resultSet.next();
				int count = resultSet.getInt(1);
				resultSet.close();
				if (count > 0)
				{
					LOG.debug("Customer: " + cusStr + " already exists");
				}
				else
				{
					// ANX is required, otherwise the customer is blocked for deal input!
					anx = new EquationStandardTransaction("V30FRRANX", session);
					anx.setMode("A");
					anx.setFieldValue("GZCUS", cusStr);
					anx.setFieldValue("GZCLC", clc);
					anx.setFieldValue("GZEAD1", "Test@Misys.com");
					anx.setWorkStationId(wsid);
					session.addEquationTransaction(anx);

					anc = new EquationStandardTransaction("G01ARRANC", session);
					anc.setWorkStationId(wsid);
					anc.setFieldValue("GZCUS", cusStr);
					anc.setFieldValue("GZCLC", clc);
					anc.setFieldValue("GZCTP", ctp);
					anc.setFieldValue("GZCPNC", cusStr);
					anc.setFieldValue("GZCUN", cun);
					anc.setFieldValue("GZDAS", das);
					anc.setFieldValue("GZBRNM", brnm);
					anc.setFieldValue("GZCRB1", crb1);
					anc.setFieldValue("GZCRB2", crb2);
					anc.setFieldValue("GZCUBD", "N");
					session.addEquationTransaction(anc);

					caa = new EquationStandardTransaction("H60FRRCAA", session);
					caa.setMode("A");
					caa.setWorkStationId(wsid);
					caa.setFieldValue("GZCUS", cusStr);
					caa.setFieldValue("GZCLC", clc);
					caa.setFieldValue("GZNA1", na1);
					caa.setFieldValue("GZNA2", na2);
					caa.setFieldValue("GZNA3", na3);
					caa.setFieldValue("GZNA4", na4);
					caa.setFieldValue("GZNA5", na5);
					session.addEquationTransaction(caa);

					mco = new EquationStandardTransaction("G09MRRMCO", session);
					mco.setWorkStationId(wsid);
					mco.setFieldValue("GZCUS", cusStr);
					mco.setFieldValue("GZCLC", clc);
					mco.setFieldValue("GZC1R", c1r);
					session.addEquationTransaction(mco);

					oca = new EquationStandardTransaction("H38ARROCA", session);
					oca.setWorkStationId(wsid);
					oca.setFieldValue("GZAB", ab);
					oca.setFieldValue("GZAN", cusStr);
					oca.setFieldValue("GZAS", as);
					oca.setFieldValue("GZACT", act);
					oca.setFieldValue("GZSHN", shn);
					oca.setFieldValue("GZCCY", ccy);
					oca.setFieldValue("GZOAD", dte);
					session.addEquationTransaction(oca);

					rds = new EquationStandardTransaction("G36ARRRDS", session);
					rds.setWorkStationId(wsid);
					rds.setFieldValue("GZDLP", dlp);
					rds.setFieldValue("GZDLR", cus + "0000001");
					rds.setFieldValue("GZBRNM", brnm);
					rds.setFieldValue("GZCUS", cusStr);
					rds.setFieldValue("GZCLC", clc);
					rds.setFieldValue("GZCCY", ccy);
					rds.setFieldValue("GZDLA", dla);
					rds.setFieldValue("GZSDT", dte);
					rds.setFieldValue("GZCTRD", dte);
					rds.setFieldValue("GZPRC", prc);
					rds.setFieldValue("GZTDT", "D"); // Term deal type; L=Loan, D=Deposit (1A)
					rds.setFieldValue("GZAPP", "RD"); // Application code; FX, MM, " ", SW, CL, MS, CP, IR, (2A)
					rds.setFieldValue("GZYROL", "A"); // Are rollovers allowed for deals of this type? (1A)
					// rds.setFieldValue("GZABF", cashUSDAB);
					// rds.setFieldValue("GZANF", cashUSDAN);
					// rds.setFieldValue("GZASF", cashUSDAS);
					rds.setFieldValue("GZABF", ab);
					rds.setFieldValue("GZANF", cusStr);
					rds.setFieldValue("GZASF", as);
					rds.setFieldValue("GZXMF", xm);
					rds.setFieldValue("GZABM", ab);
					rds.setFieldValue("GZANM", cusStr);
					rds.setFieldValue("GZASM", as);
					rds.setFieldValue("GZXMM", xm);
					rds.setFieldValue("GZABI", ab);
					rds.setFieldValue("GZANI", cusStr);
					rds.setFieldValue("GZASI", as);
					rds.setFieldValue("GZXMI", xm);
					rds.setFieldValue("GZCCYF", ccy);
					rds.setFieldValue("GZCCYM", ccy);
					rds.setFieldValue("GZCCYI", ccy);

					session.addEquationTransaction(rds);

					asiCr = new EquationStandardTransaction("H15ARRASI", session);
					asiCr.setWorkStationId(wsid);
					asiCr.setFieldValue("GZAB", ab);
					asiCr.setFieldValue("GZAN", cusStr);
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
					asiDr.setWorkStationId(wsid);
					asiDr.setFieldValue("GZAB", ab);
					asiDr.setFieldValue("GZAN", cusStr);
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
				// increment the customer number...
				cus++;
			}
			long endTime = System.nanoTime();
			LOG.debug("Thread time for thread " + cus + ": " + ((endTime - startTime) / 1000000) + "ms");
		}
		catch (Exception e)
		{
			LOG.error("DUFF TEST", e);
		}
	}
}
