package com.misys.equation.bf;

import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.test.TestEnvironment;
import com.trapedza.bankfusion.client.BankFusionClientFactory;
import com.trapedza.bankfusion.client.IBankFusionClient;
import com.trapedza.bankfusion.client.requests.ExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowRequest;
import com.trapedza.bankfusion.client.requests.IExecuteMicroflowResponse;
import com.trapedza.bankfusion.messaging.core.MessagingManager;
import com.trapedza.bankfusion.messaging.gateway.interfaces.IInvocationMode;

public abstract class TestMicroflow extends TestCase
{
	// Demo login
	DemoLogin demoLogin = new DemoLogin();

	protected static EquationStandardSession session;
	protected static EquationUnit equationUnit;
	protected static EquationUser equationUser;
	public static boolean cleanUp = true;

	protected String user;
	protected String password;

	@Override
	protected void setUp() throws Exception
	{
		System.out.println("======" + this.getClass().getSimpleName() + "======");
		if (session == null)
		{
			// equationUnit = TestEnvironment.getTestEnvironment().getEquationUnit();
			// equationUser = TestEnvironment.getTestEnvironment().getEquationUser();
			// session = equationUser.getSession();
			user = TestEnvironment.getTestEnvironment().getUser();
			password = TestEnvironment.getTestEnvironment().getPassword();
		}
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception
	{
		cleanUp();
		super.tearDown();
	}

	protected void cleanUp() throws Exception
	{
		if (cleanUp)
		{
			logout();
		}
	}

	/**
	 * Start the test with default user and password
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception
	{
		try
		{
			setUp();
			login();
			test();
		}
		finally
		{
			logout();
		}
	}

	/**
	 * Test details
	 * 
	 * @throws Exception
	 */
	public abstract void test() throws Exception;

	/**
	 * Login user
	 */
	protected void login()
	{
		// BFTC Channel no longer supported
		MessagingManager.setInvocationMode(IInvocationMode.SERVER_START);
		demoLogin.demoLogin(user, password);
	}

	/**
	 * Log-off user
	 */
	protected void logout()
	{
		demoLogin.demoLogout(user);
	}

	/**
	 * Print the tag
	 * 
	 * @param tags
	 *            - the tags
	 */
	private static void printTags(Map<String, Object> tags)
	{
		Iterator<String> iterator = tags.keySet().iterator();
		String key = null;
		Object value = null;

		while (iterator.hasNext())
		{
			key = iterator.next();
			value = tags.get(key);

			System.out.println(" - Key = " + key + "; Value = " + value);
		}
	}

	/**
	 * Print response tag
	 * 
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	protected void print(IExecuteMicroflowResponse executeMicroflowResponse)
	{
		System.out.println("Response: ");
		printTags(executeMicroflowResponse.getOutputTags());
	}

	/**
	 * Print request tag
	 * 
	 * @param executeMicroflowRequest
	 *            - the MicroFlow request
	 */
	protected void print(IExecuteMicroflowRequest executeMicroflowRequest)
	{
		System.out.println("Request: ");
		printTags(executeMicroflowRequest.getInputTags());
	}

	/**
	 * Print request and response tag
	 * 
	 * @param executeMicroflowRequest
	 *            - the MicroFlow request
	 * @param executeMicroflowResponse
	 *            - the MicroFlow response
	 */
	protected void print(IExecuteMicroflowRequest executeMicroflowRequest, IExecuteMicroflowResponse executeMicroflowResponse)
	{
		System.out.println("user=" + executeMicroflowRequest.getUsername() + " microflow="
						+ executeMicroflowRequest.getMicroflowName());
		print(executeMicroflowRequest);
		print(executeMicroflowResponse);
	}

	/**
	 * Return the BankFusion client
	 */
	public IBankFusionClient getBankFusionClient()
	{
		return BankFusionClientFactory.getInstance().getBankFusionClient();
	}

	/**
	 * Return the MicroFlow request
	 * 
	 * @param microFlowName
	 *            - the MicroFlow name
	 * 
	 * @return the MicroFlow request
	 */
	public IExecuteMicroflowRequest getMicroflowRequest(String microFlowName)
	{
		IExecuteMicroflowRequest executeMicroflowRequest = new ExecuteMicroflowRequest(user, microFlowName);
		executeMicroflowRequest.setPassword(password);
		return executeMicroflowRequest;
	}

	/**
	 * Execute a MicroFlow request
	 * 
	 * @param executeMicroflowRequest
	 *            - the MicroFlow request
	 * 
	 * @return the MicroFlow response
	 */
	public IExecuteMicroflowResponse executeMicroflow(IExecuteMicroflowRequest executeMicroflowRequest)
	{
		// BFTC Channel no longer supported
		MessagingManager.setInvocationMode(IInvocationMode.SERVER_START);
		IExecuteMicroflowResponse executeMicroflowResponse = getBankFusionClient().executeMicroflow(executeMicroflowRequest);
		print(executeMicroflowRequest, executeMicroflowResponse);
		return executeMicroflowResponse;
	}

}
