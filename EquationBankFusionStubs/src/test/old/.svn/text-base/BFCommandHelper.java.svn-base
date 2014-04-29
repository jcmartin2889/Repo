package test.old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.misys.bankfusion.subsystem.infrastructure.common.impl.SystemInformationManager;
import com.misys.bankfusion.subsystem.infrastructure.common.impl.SystemInformationManagerClient;
import com.trapedza.bankfusion.core.BankFusionException;
import com.trapedza.bankfusion.core.MetaDataEnum;
import com.trapedza.bankfusion.core.MetaDataWrapper;
import com.trapedza.bankfusion.gateway.messaging.interfaces.IMessagingHeader;
import com.trapedza.bankfusion.messaging.commands.core.CommandHelper;
import com.trapedza.bankfusion.messaging.core.MessagingManager;
import com.trapedza.bankfusion.messaging.gateway.interfaces.IInvocationMode;
import com.trapedza.bankfusion.messaging.utils.MessagingEventCodes;
import com.trapedza.bankfusion.utils.BankFusionMessages;
import com.trapedza.bankfusion.utils.CommonEventCodes;

/**
 * 
 */
public class BFCommandHelper
{
	private static BFCommandHelper singletonBFCommandHelper;

	/*
	 * Get the singleton context
	 */
	public static synchronized BFCommandHelper getBFCommandHelper()
	{
		if (singletonBFCommandHelper == null)
		{
			singletonBFCommandHelper = new BFCommandHelper();
		}
		return singletonBFCommandHelper;
	}

	private BFCommandHelper()
	{
		BankFusionMessages.getInstance().registerEventMessageLookupHandler(CommonEventCodes.getInstance());
		BankFusionMessages.getInstance().registerEventMessageLookupHandler(MessagingEventCodes.getInstance());
		SystemInformationManagerClient systeminformationmanagerclient = new SystemInformationManagerClient();
		SystemInformationManager.setInstance(systeminformationmanagerclient);
		MessagingManager.setInvocationMode(IInvocationMode.BFTC_START);
	}
	public Map logOn(String userName, String password)
	{
		Locale userLocale = Locale.getDefault();
		try
		{
			CommandHelper command = new CommandHelper(CommandHelper.LOGIN_COMMAND);
			HashMap commandParameters = command.getData();
			commandParameters.put(MetaDataEnum.PROP_LOGIN_NAME, userName);
			commandParameters.put(MetaDataEnum.PROP_LOGIN_PASSWORD, password);
			commandParameters.put(MetaDataEnum.PROP_IS_FROM_BFTC, Boolean.TRUE);
			commandParameters.put(MetaDataEnum.PROP_USER_LOCALE_LANGUAGE, userLocale.getLanguage());
			commandParameters.put(MetaDataEnum.PROP_USER_LOCALE_COUNTRY, userLocale.getCountry());
			commandParameters.put(MetaDataEnum.PROP_USER_LOCALE_VARIANT, userLocale.getVariant());
			Object commandResponse = MessagingManager.getInstance().executeCommandSync(command);
			if (commandResponse instanceof Map)
			{
				return (Map) commandResponse;
			}
			else if (commandResponse instanceof BankFusionException)
			{
				throw (BankFusionException) commandResponse;
			}
			else
			{
				throw new BankFusionException(0, new Exception().getStackTrace()[0].getMethodName() + " Is Broken");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public boolean logOff()
	{
		try
		{
			CommandHelper command = new CommandHelper(CommandHelper.LOGOUT_COMMAND);
			// HashMap commandParameters = command.getData();
			Object commandResponse = MessagingManager.getInstance().executeCommandSync(command);
			if (commandResponse instanceof Boolean)
			{
				return ((Boolean) commandResponse).booleanValue();
			}
			else if (commandResponse instanceof BankFusionException)
			{
				throw (BankFusionException) commandResponse;
			}
			else
			{
				throw new BankFusionException(0, new Exception().getStackTrace()[0].getMethodName() + " Is Broken");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public HashMap executeBusinessProcess(String userName, String mfid, Hashtable microflowParameters)
	{
		try
		{
			CommandHelper command = new CommandHelper(CommandHelper.EXECUTE_BP_COMMAND);
			HashMap commandParameters = command.getData();
			// Set the parameteres for the microflow executor
			// commandParameters.put(MetaDataEnum.PROP_LOGIN_NAME, userName);
			// commandParameters.put(MetaDataEnum.PROP_IS_FROM_BFTC, Boolean.TRUE);
			commandParameters.put(MetaDataEnum.PROP_EXECUTE_BP_NOUI, microflowParameters);
			// Identify which microflow we are going to do...
			IMessagingHeader header = command.getHeader();
			header.setMfID(mfid);
			// Execute the microflow
			Object commandResponse = MessagingManager.getInstance().executeCommandSync(command);
			// The hashmap returned isn't really very helpful. I must be missing something!
			if (commandResponse instanceof HashMap)
			{
				return (HashMap) commandResponse;
			}
			else if (commandResponse instanceof BankFusionException)
			{
				throw (BankFusionException) commandResponse;
			}
			else
			{
				throw new BankFusionException(0, new Exception().getStackTrace()[0].getMethodName() + " Is Broken");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public ArrayList getMenuList(String userName)
	{
		try
		{
			CommandHelper command = new CommandHelper(CommandHelper.GET_MENU_LIST_COMMAND);
			HashMap commandParameters = command.getData();
			commandParameters.put(MetaDataEnum.PROP_LOGIN_NAME, userName);
			commandParameters.put(MetaDataEnum.PROP_IS_FROM_BFTC, Boolean.FALSE);
			Object commandResponse = MessagingManager.getInstance().executeCommandSync(command);
			if (commandResponse instanceof ArrayList)
			{
				return (ArrayList) commandResponse;
			}
			else if (commandResponse instanceof BankFusionException)
			{
				throw (BankFusionException) commandResponse;
			}
			else
			{
				throw new BankFusionException(0, new Exception().getStackTrace()[0].getMethodName() + " Is Broken");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public Object[] getBusinessProcessList(String userName)
	{
		try
		{
			CommandHelper command = new CommandHelper(CommandHelper.GET_BP_LIST_COMMAND);
			HashMap commandParameters = command.getData();
			commandParameters.put(MetaDataEnum.PROP_LOGIN_NAME, userName);
			commandParameters.put(MetaDataEnum.PROP_IS_FROM_BFTC, Boolean.FALSE);
			Object commandResponse = MessagingManager.getInstance().executeCommandSync(command);
			if (commandResponse instanceof Object[])
			{
				return (Object[]) commandResponse;
			}
			else if (commandResponse instanceof BankFusionException)
			{
				throw (BankFusionException) commandResponse;
			}
			else
			{
				throw new BankFusionException(0, new Exception().getStackTrace()[0].getMethodName() + " Is Broken");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * **************************************************************************************** Print Helpers
	 * ****************************************************************************************
	 */
	public void printBankFusionMap(Map map)
	{
		if (map != null)
		{
			Set keys = map.keySet();
			Iterator keyIterator = keys.iterator();
			while (keyIterator.hasNext())
			{
				String keyValue = (String) keyIterator.next();
				if (map.get(keyValue) instanceof Hashtable)
				{
					printBankFusionMap((Map) map.get(keyValue));
				}
				else if (map.get(keyValue) instanceof ArrayList)
				{
					printBankFusionArrayList((ArrayList) map.get(keyValue));
				}
				else
				{
					String property = "";
					Object propertyValue = map.get(keyValue);
					String propertyType = "";
					if (MetaDataEnum.getMetaDataDesc(keyValue).equals("UNKNOWN"))
					{
						property = keyValue;
					}
					else
					{
						property = MetaDataEnum.getMetaDataDesc(keyValue);
					}
					if (!map.get(keyValue).getClass().getName().equals("java.lang.String"))
					{
						propertyType = "(" + map.get(keyValue).getClass().getName() + ")";
					}
					System.out.println(property + propertyType + ":" + propertyValue);
				}
			}
		}
	}
	public void printBankFusionArrayList(ArrayList arrayList)
	{
		if (arrayList != null)
		{
			Iterator arrayIterator = arrayList.iterator();
			while (arrayIterator.hasNext())
			{
				Object arrayListElement = arrayIterator.next();
				if (arrayListElement instanceof ArrayList)
				{
					printBankFusionArrayList((ArrayList) arrayListElement);
				}
				else
				{
					System.out.println(arrayListElement);
				}
			}
		}
	}
	public void printBankFusionMetaData(Object[] metaData)
	{
		if (metaData != null)
		{
			for (int i = 0; i < metaData.length; i++)
			{
				MetaDataWrapper metaDataWrapper = (MetaDataWrapper) metaData[i];
				printBankFusionMap(metaDataWrapper.getMetaData());
			}
		}
	}
	public void start()
	{
		// ClientServiceManager.getInstance().start();
	}
	public void closedown()
	{
		// ClientServiceManager.getInstance().closedown();
	}
}
