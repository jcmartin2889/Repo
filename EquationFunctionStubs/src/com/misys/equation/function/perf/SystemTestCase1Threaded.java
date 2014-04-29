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

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.FunctionHandler;
import com.misys.equation.function.runtime.FunctionInfo;
import com.misys.equation.function.runtime.FunctionMessages;

public class SystemTestCase1Threaded implements Runnable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SystemTestCase1Threaded.java 11517 2011-07-26 16:03:49Z ESTHER.WILLIAMS $";
	/**
	 * Logger for this class
	 */
	private static final EquationLogger LOG = new EquationLogger(SystemTestCase1Threaded.class);

	// Non Static trannie vars...
	private EquationStandardSession session;
	private EquationUnit eqUnit;
	private EquationUser user;
	private FunctionInfo functionInfo;
	private FunctionHandler functionHandler;
	private int numberToAdd;
	private int cus;
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
	private static final String sdte = "T";
	private static final String mdte = "1Y";
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
			user = new EquationUser(eqUnit);
			functionInfo = new FunctionInfo(Thread.currentThread().getName(), Thread.currentThread().getName());
			functionInfo.setWorkStationId(String.valueOf(cus).substring(2, 6));
			functionHandler = new FunctionHandler(user, functionInfo);
			session = functionHandler.rtvEquationSession();

			PreparedStatement statement = session.getConnection().prepareStatement("SELECT COUNT(*) FROM GFPF WHERE GFCPNC=?");
			// We might need to change USA24R for API calls in a threaded environment due to duplicate GYs.
			// For the time being I'm adding unique WSIDs as a workaround.
			for (int i = 0; i < numberToAdd; i++)
			{
				String cusStr = String.valueOf(cus);

				// See if the customer already exists...
				statement.setString(1, cusStr);
				ResultSet resultSet = statement.executeQuery();
				resultSet.next();
				int count = resultSet.getInt(1);
				resultSet.close();
				if (count > 0)
				{
					LOG.info("Customer: " + cusStr + " already exists");
				}
				else
				{
					functionHandler.doNewTransaction("ZT1", "");
					FunctionData functionData = functionHandler.rtvFunctionData();
					functionData.chgFieldInputValue("ANC_CUS", cusStr);
					functionData.chgFieldInputValue("ANC_CLC", clc);
					// functionHandler.applyRetrieveTransaction();
					functionData.chgFieldInputValue("ANC_CUN", cun);
					functionData.chgFieldInputValue("ANC_CPNC", cusStr);
					functionData.chgFieldInputValue("ANC_DAS", das);
					functionData.chgFieldInputValue("ANC_CTP", ctp);
					functionData.chgFieldInputValue("ANC_BRNM", brnm);
					functionData.chgFieldInputValue("ANC_CRB1", crb1);
					functionData.chgFieldInputValue("ANC_CRB2", crb2);

					functionData.chgFieldInputValue("ANX_EAD1", "whatever@misys.com");

					functionData.chgFieldInputValue("CAA_NA1", na1);
					functionData.chgFieldInputValue("CAA_NA2", na2);
					functionData.chgFieldInputValue("CAA_NA3", na3);
					functionData.chgFieldInputValue("CAA_NA4", na4);
					functionData.chgFieldInputValue("CAA_NA5", na5);

					functionData.chgFieldInputValue("MCO_C1R", c1r);

					functionData.chgFieldInputValue("OCA_ACT", act);

					functionData.chgFieldInputValue("RDS_DLP", dlp);
					functionData.chgFieldInputValue("RDS_DLR", cusStr + "001");
					functionData.chgFieldInputValue("RDS_DLA", dla);
					functionData.chgFieldInputValue("RDS_SDT", sdte);
					functionData.chgFieldInputValue("RDS_MDT", mdte);
					functionData.chgFieldInputValue("RDS_PRC", prc);

					functionData.chgFieldInputValue("ASC_VFR", "T");
					functionData.chgFieldInputValue("ASC_AMA", amtCr);
					functionData.chgFieldInputValue("ASC_DRF", cusStr + "T001");
					functionData.chgFieldInputValue("ASC_TCD", tcdCr);
					functionData.chgFieldInputValue("ASD_TCD", tcdDr);

					functionHandler.applyTransaction();

					// any error, then log
					if (functionHandler.rtvMsgSev() == FunctionMessages.MSG_ERROR)
					{
						LOG.error(functionHandler.rtvFunctionMessages().getMessages().toString());
					}
				}
				// increment the customer number...
				cus++;
			}
			long endTime = System.nanoTime();
			LOG.info("Duration: " + ((endTime - startTime) / 1000000) + "ms");
		}
		catch (Exception e)
		{
			LOG.error("DUFF TEST", e);
		}
	}
}
