package com.misys.equation.function.bf.callback;

import com.misys.bankfusion.subsystem.security.IAppAuthenticationHelper;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.context.EquationFunctionContext;

public class BFAppAuthenticationHelper implements IAppAuthenticationHelper
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BFAppAuthenticationHelper.java 11633 2011-08-26 16:11:56Z GOLDSMC1 $";
	// Log
	private static final EquationLogger LOG = new EquationLogger(BFAppAuthenticationHelper.class);

	/**
	 * Log-in call back from BankFusion
	 * 
	 * @param userId
	 *            - user Id
	 * @param userLocator
	 *            - the user locator
	 * 
	 */
	public void login(String userId, String userLocator)
	{
		LOG.info("Login callback from BankFusion with user id [" + userId + "]");
	}

	/**
	 * Log-off call back from BankFusion
	 * 
	 * @param userId
	 *            - user Id
	 * @param userLocator
	 *            - the user locator
	 * 
	 */
	public void logout(String userId, String userLocator)
	{
		LOG.info("Logout callback from BankFusion with user id [" + userId + "]");
		EquationFunctionContext.getContext().logOffBankFusionSession(userLocator);
	}

}