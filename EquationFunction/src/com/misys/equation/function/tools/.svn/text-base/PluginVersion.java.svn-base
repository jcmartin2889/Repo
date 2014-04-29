package com.misys.equation.function.tools;

import com.misys.equation.common.utilities.EquationLogger;

public class PluginVersion
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PluginVersion.java 12961 2012-03-15 10:20:16Z bernie.terrado $";
	private static final EquationLogger LOG = new EquationLogger(PluginVersion.class);
	
	// the minimum version of a plugin to be eligible for maker/checker processing
	private final static String PLUGIN_VERSION_MAKER_CHECKER  = "002.001.002";
	
	/**
	 * Determines if a service can be processed through Maker/Checker by checking on the release version of the service
	 * 
	 * @param serviceReleaseVersion services' version 
	 * @return true if version is at least 2.1.2
	 */
	public static boolean isMakerCheckerService(String serviceReleaseVersion)
	{
		if (PLUGIN_VERSION_MAKER_CHECKER.compareTo(serviceReleaseVersion) <= 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
