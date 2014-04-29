package com.misys.equation.common.utilities;

public class MeridianCheck
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BankFusionCheck.java 15796 2013-05-13 22:08:34Z williae1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(MeridianCheck.class);

	/**
	 * Indicates whether Meridian is installed. Do not use this method directly! Use EquationCommonContext isMeridianInstalled()
	 * method instead as indirection is needed. Catch here may not occur as load of MeridianCheck class itself may fail.
	 * 
	 * @return boolean
	 */
	public static boolean isMeridianInstalled()
	{
		boolean result = false;
		try
		{
			Class meridianClass = Class.forName("com.misys.meridian.userapi.Message");
			if (meridianClass != null)
			{
				result = true;
			}
		}
		catch (ClassNotFoundException e)
		{
			// This situation is expected when Meridian application is not installed,
			// so no error handling is required.
		}
		catch (Throwable t)
		{
			// This situation is expected when Meridian application is not installed,
			// so no error handling is required.
		}

		if (LOG.isInfoEnabled())
		{
			LOG.info("isMeridianInstalled - returning " + result);
		}
		return result;
	}
}
