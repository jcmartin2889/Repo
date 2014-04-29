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

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;

/**
 * An enquiry sample for illustration of basic enquiry processing steps
 */
public class EquationStandardEnquirySample
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardEnquirySample.java 15055 2012-12-18 15:04:53Z williae1 $";

	public static void main(String[] args) throws Exception
	{
		EquationStandardEnquirySample transactionSample = new EquationStandardEnquirySample();
		transactionSample.process();
	}
	private String decimalSeparator;
	private String integerSeparator;
	private String yesValue;
	private String noValue;

	public boolean process()
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
			continueProcessing = processABTransaction(session);

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
	 * Process an AB enquiry with hardcoded values
	 * 
	 * @param session
	 * @return status of posted AB enquiry
	 * @throws UnknownHostException
	 * @throws EQException
	 */
	private boolean processABTransaction(EquationStandardSession session) throws UnknownHostException, EQException
	{
		// Get us an enquiry class
		EquationStandardEnquiry enquiry = new EquationStandardEnquiry("H68EER", session);
		// Create a enquiry id based on timestamp
		String enquiryId = Toolbox.getTimeBasedReference13();
		// Set enquiry id that will appear in messages
		enquiry.setId(session.getUserId() + " " + InetAddress.getLocalHost().getHostAddress() + enquiryId);
		// Set some enquiry fields up
		enquiry.setFieldValue("HZAB", "0543");
		enquiry.setFieldValue("HZAN", "012826");
		enquiry.setFieldValue("HZAS", "002");
		enquiry = session.executeEnquiry(enquiry);
		if (enquiry.getValid())
		{
			// Display formatted output
			System.out.println("String - Customer Full Name = " + enquiry.getFieldValue("HZCUN"));
			// Format Date
			System.out.println("Date - Account Open Date = " + Toolbox.formatDate(enquiry.getFieldValue("HZOAD")));
			// Format Amount
			String statusBalance = enquiry.getFieldValue("HZSTBL");
			String formattedStatusBalance = EqDataType.formatEquationAmount(statusBalance, 15, Toolbox.parseInt(statusBalance, 2),
							integerSeparator, decimalSeparator);
			System.out.println("Amount - Status Balance = " + formattedStatusBalance);
			// Check for Messages and Errors
			System.out.println("Messages = " + enquiry.getMessages());
			System.out.println("Errors = " + enquiry.getErrorList());
		}
		return enquiry.getValid();
	}
}
