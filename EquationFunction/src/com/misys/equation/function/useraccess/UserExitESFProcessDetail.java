package com.misys.equation.function.useraccess;

import com.misys.equation.bankfusion.lrp.engine.TaskEngineToolbox;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * This class will contain the properties of an ESF process.
 * 
 * @author yzobdabu
 */
public class UserExitESFProcessDetail
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UserExitESFProcessDetail.java 11964 2011-09-30 09:52:23Z rpatrici $";

	private String url = "";

	/**
	 * Create an empty constructor
	 */
	public UserExitESFProcessDetail()
	{
	}

	/**
	 * Construct a return value
	 * 
	 * @param url
	 *            - the url to the web service to create an LRP process
	 */
	public UserExitESFProcessDetail(String url)
	{
		this.url = url;
	}

	/**
	 * Return the url
	 * 
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * Set the url
	 * 
	 * @param url
	 *            - the url
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * Return the URL. This will automatically add the LRP server location if needed (e.g. http:// is not in the string)
	 * 
	 * @return the URL
	 */
	public String rtvURL() throws EQException
	{
		if (url.trim().length() == 0)
		{
			return "";
		}
		else if (url.startsWith("http://"))
		{
			return url.trim();
		}
		else
		{
			return TaskEngineToolbox.getInstance().getLRPServer().trim() + url.trim();
		}
	}

}
