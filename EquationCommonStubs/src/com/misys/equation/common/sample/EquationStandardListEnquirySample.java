/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.sample;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import com.ibm.as400.access.Record;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;

/**
 * An enquiry sample for illustration of basic enquiry processing steps - list data is supported
 */
public class EquationStandardListEnquirySample
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardListEnquirySample.java 15055 2012-12-18 15:04:53Z williae1 $";

	public static void main(String[] args) throws Exception
	{
		EquationStandardListEnquirySample transactionSample = new EquationStandardListEnquirySample();
		transactionSample.process();
	}
	private String decimalSeparator;
	private String integerSeparator;
	private String yesValue;
	private String noValue;
	public boolean process() throws UnsupportedEncodingException
	{
		boolean continueProcessing = true;
		try
		{
			// Get a connection to the iSeries. A QZDASONIT job is created and a session identifier is returned.
			EquationStandardSession session = EquationContextSample.getContext().getSampleSession();

			// Setup formatting parameters
			EquationUser user = EquationCommonContext.getContext().getEquationUser(session.getSessionIdentifier());
			decimalSeparator = user.getDsjobctle().getFieldValue(EqDS_DSJOBE.DECST);
			integerSeparator = user.getDsjobctle().getFieldValue(EqDS_DSJOBE.INTST);
			yesValue = user.getDsjobctle().getFieldValue(EqDS_DSJOBE.YAB);
			noValue = user.getDsjobctle().getFieldValue(EqDS_DSJOBE.NAB);

			// First enquiry
			continueProcessing = processTHTransaction(session);

			// Log off the session
			EquationCommonContext.getContext().logOffSession(session.getSessionIdentifier());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return continueProcessing;
	}
	/**
	 * Process an AB transaction with hardcoded values
	 * 
	 * @param session
	 * @return status of posted ASI transaction
	 * @throws UnknownHostException
	 * @throws UnsupportedEncodingException
	 * @throws EQException
	 */
	private boolean processTHTransaction(EquationStandardSession session) throws UnknownHostException,
					UnsupportedEncodingException, EQException
	{
		// Get us an enquiry class
		EquationStandardListEnquiry enquiry = new EquationStandardListEnquiry("H71DER", session);
		// Create a transaction id based on timestamp
		String enquiryId = Toolbox.getTimeBasedReference13();
		// Set transaction id that will appear in messages
		enquiry.setId(session.getUserId() + " " + InetAddress.getLocalHost().getHostAddress() + enquiryId);
		// Set some transaction fields up
		enquiry.setFieldValue("HZAB", "0543");
		enquiry.setFieldValue("HZAN", "123432");
		enquiry.setFieldValue("HZAS", "002");
		while (!enquiry.isComplete())
		{
			enquiry = session.executeListEnquiry(enquiry);
			// Display formatted output
			System.out.println("Account = " + enquiry.getFieldValue("HZAB") + " " + enquiry.getFieldValue("HZAN") + " "
							+ enquiry.getFieldValue("HZAS") + " " + enquiry.getFieldValue("HZSHN") + " "
							+ enquiry.getFieldValue("HZCCYP"));
			// Format Date
			System.out.println("Date From = " + Toolbox.formatDate(enquiry.getFieldValue("HZDTEF")));
			System.out.println("Date To = " + Toolbox.formatDate(enquiry.getFieldValue("HZDTET")));
			// Format Amount
			String startingBalance = enquiry.getFieldValue("HZSBAL");
			String formattedStartingBalance = EqDataType.formatEquationAmount(startingBalance, 15, Toolbox.parseInt(
							startingBalance, 2), integerSeparator, decimalSeparator);
			System.out.println("Starting Balance = " + formattedStartingBalance);
			String availableBalance = enquiry.getFieldValue("HZSBAL");
			String formattedAvailableBalance = EqDataType.formatEquationAmount(startingBalance, 15, Toolbox.parseInt(
							availableBalance, 2), integerSeparator, decimalSeparator);
			System.out.println("Available Balance = " + formattedAvailableBalance);
			List<Record> rows = enquiry.getHzListRcds();
			String narrative = "";
			String postingDate = "";
			String postingAmount = "";
			String formattedPostingAmount = "";
			String runningBalance = "";
			String formattedRunningBalance = "";
			String line = "";

			for (Record row : rows)
			{
				narrative = new StringBuilder(((String) row.getField("HZNR1")).trim()).append(" ").append(
								((String) row.getField("HZNR2")).trim()).append(" ")
								.append(((String) row.getField("HZNR3")).trim()).append(" ").append(
												((String) row.getField("HZNR4")).trim()).append(" ").append(
												((String) row.getField("HZA001")).trim()).append(" ").append(
												((String) row.getField("HZA002")).trim()).append(" ").append(
												((String) row.getField("HZA003")).trim()).append(" ").append(
												((String) row.getField("HZA004")).trim()).toString();
				// Format Date
				postingDate = Toolbox.formatDate(((BigDecimal) row.getField("HZPOD")).toString());
				// Format Amount
				postingAmount = (String) row.getField("HZAMAP");
				formattedPostingAmount = EqDataType.formatEquationAmount(postingAmount, 15, Toolbox.parseInt(startingBalance, 2),
								integerSeparator, decimalSeparator);
				runningBalance = (String) row.getField("HZRBAL");
				formattedRunningBalance = EqDataType.formatEquationAmount(runningBalance, 15, Toolbox.parseInt(startingBalance, 2),
								integerSeparator, decimalSeparator);
				line = new StringBuilder(narrative.trim()).append(" ").append(formattedPostingAmount).append(" ").append(
								postingDate).append(" ").append(formattedRunningBalance).toString();
				System.out.println(line);
			}

		}
		// Check for Messages and Errors
		System.out.println("Messages = " + enquiry.getMessages());
		System.out.println("Errors = " + enquiry.getErrorList());
		return enquiry.getValid();
	}
}
