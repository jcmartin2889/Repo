package com.misys.equation.bf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.misys.bankfusion.subsystem.messaging.webservice.BankFusionCommanderFactory;
import com.misys.bankfusion.subsystem.messaging.webservice.IBankFusionCommander;
import com.misys.equation.common.utilities.Toolbox;
import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.CommandRequesterClientSide;
import com.trapedza.bankfusion.client.IBankFusionClient;
import com.trapedza.bankfusion.client.exceptions.BankFusionClientError;
import com.trapedza.bankfusion.core.BankFusionException;
import com.trapedza.bankfusion.messaging.core.MessagingManager;
import com.trapedza.bankfusion.messaging.gateway.interfaces.IInvocationMode;

/**
 * DemoLogin
 */
public class DemoLogin
{
	public static final String CVS_REVISION = "$Revision$";

	static
	{
		com.trapedza.bankfusion.utils.Tracer.register(CVS_REVISION);
	}

	private static final transient Log logger = LogFactory.getLog(DemoLogin.class.getName());

	public void demoLogin(String userName, String password) throws BankFusionClientError
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();

			MessagingManager.setInvocationMode(IInvocationMode.SERVER_START);

			bankFusionClient.login(userName, password);
			System.out.println("DemoLogin: " + userName + " is logged in");
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	public void demoLogout(String userName)
	{
		try
		{
			IBankFusionClient bankFusionClient = BankFusionClientFactory.getInstance().getBankFusionClient();

			bankFusionClient.logout(userName);
			System.out.println("DemoLogin: " + userName + " is logged out");
		}
		catch (Exception e)
		{
			System.out.println(Toolbox.getExceptionMessage(e));
		}
	}

}
