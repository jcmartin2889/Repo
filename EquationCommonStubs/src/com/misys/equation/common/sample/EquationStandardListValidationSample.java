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

import java.net.UnknownHostException;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationPVData;
import com.misys.equation.common.access.EquationStandardListValidation;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * An validation sample for illustration of basic validation processing steps - list data is supported
 */
public class EquationStandardListValidationSample
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardListValidationSample.java 15055 2012-12-18 15:04:53Z williae1 $";

	public static void main(String[] args) throws Exception
	{
		EquationStandardListValidationSample promptSample = new EquationStandardListValidationSample();
		promptSample.process();
	}
	public boolean process()
	{
		boolean continueProcessing = true;
		try
		{
			// Get a connection to the iSeries. A QZDASONIT job is created and a session identifier is returned.
			EquationStandardSession session = EquationContextSample.getContext().getSampleSession();

			// First validation
			continueProcessing = processC8R01RPrompt(session);

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
	 * Process an C8R01R currency validation with hardcoded values
	 * 
	 * @param session
	 * @return status of posted ASI transaction
	 * @throws UnknownHostException
	 * @throws EQException
	 */
	private boolean processC8R01RPrompt(EquationStandardSession session) throws UnknownHostException, EQException
	{
		// Transaction variables
		String decode = " ";
		String pvModule = "C8R01R";
		String blanksAllowed = "N";
		String newCode = "N";
		EquationUser user = EquationCommonContext.getContext().getEquationUser(session.getSessionIdentifier());

		EquationPVData equationPVData = new EquationPVData(session.getUnit().getPVMetaData(pvModule), session.getCcsid());
		equationPVData.setFieldValue("C8@CCY", "G*");
		EquationStandardListValidation listValidation = new EquationStandardListValidation(decode, pvModule, equationPVData,
						blanksAllowed, newCode, "", "", 1, 16);
		session.executeListValidate(user, listValidation);
		System.out.println("PVDATA: " + listValidation);

		int page = 1;
		while (listValidation.isMoreRecords())
		{
			System.out.println("page " + ++page);
			session.executeNextListValidate(user, listValidation);
			if (!listValidation.getValid())
			{
				return listValidation.getValid();
			}
			System.out.println("PVDATA: " + listValidation);
		}
		return true;
	}
}
