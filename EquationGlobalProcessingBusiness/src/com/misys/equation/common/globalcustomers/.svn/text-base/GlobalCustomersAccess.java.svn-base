package com.misys.equation.common.globalcustomers;

import java.util.Enumeration;
import java.util.Map;
import java.util.WeakHashMap;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * This class provides access to an implementation of the global customers management functionality. It acts like a session bean in
 * that it returns a separate instance of each implementation for each of the sessions.
 * 
 * @author CAMILLN1
 */
public final class GlobalCustomersAccess
{
	// Note that we use a WeakHashMap. An entry in a WeakHashMap will automatically be removed when its key is no longer
	// in ordinary use. Given that the key is mapped to the session ID in common context it should be removed once the
	// session id is removed from equation common.
	private static final Map<String, GlobalCustomersFacade> instanceMap = new WeakHashMap<String, GlobalCustomersFacade>();

	/**
	 * Returns the static instance of the implementation (for global customers functionality) in the form of an interface.
	 * 
	 * @return GlobalCustomersFacade - interface for global customers functionality
	 */
	public synchronized static GlobalCustomersFacade getImpl(final String sessionId) throws EQException
	{
		GlobalCustomersFacade facadeImpl = instanceMap.get(sessionId);
		if (facadeImpl == null)
		{
			facadeImpl = new GlobalCustomersImpl();
			final Enumeration<String> sessionIds = EquationCommonContext.getContext().getSessionIdentifiers();
			while (sessionIds.hasMoreElements())
			{
				String id = sessionIds.nextElement();
				if (sessionId.equals(id))
				{
					instanceMap.put(id, facadeImpl);
				}
			}
			if (instanceMap.get(sessionId) != null)
			{
				return facadeImpl;
			}
			else
			{
				throw new EQException("No Equation session exists for this user");
			}
		}
		else
		{
			return facadeImpl;
		}
	}

	/**
	 * This method is invoked when the web tier considers this session completed. i.e. upon finish or cancel.
	 * 
	 * @param sessionId
	 *            String - The sessionId for the current session.
	 */
	public synchronized static void clearConversionalState(final String sessionId)
	{
		if (instanceMap.containsKey(sessionId))
		{
			instanceMap.remove(sessionId);
		}
	}

	/**
	 * Returns the current number of instances.
	 * 
	 * @return
	 */
	public static int getCurrentNoOfInstances()
	{
		return instanceMap.size();
	}
}