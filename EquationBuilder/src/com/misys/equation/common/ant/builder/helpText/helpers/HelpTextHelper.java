package com.misys.equation.common.ant.builder.helpText.helpers;

import com.misys.equation.common.ant.builder.core.EquationLogger;
import com.misys.equation.common.ant.builder.helpText.core.HelpTextProperties;
import com.misys.equation.common.ant.builder.helpText.models.GAERecordDataModel;

/**
 * This class contains all text helper business logic. <br>
 * Such as options validations.
 * 
 * @author deroset
 * 
 */
public class HelpTextHelper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: HelpTextHelper.java 4741 2009-09-16 16:33:23Z esther.williams $";
	private static HelpTextHelper currentInstance;
	private HelpTextProperties helpTextProperties = HelpTextProperties.getInstance();
	private static final EquationLogger LOG = new EquationLogger(HelpTextHelper.class);

	private HelpTextHelper()
	{

	}

	public static HelpTextHelper getInstance()
	{
		synchronized (HelpTextHelper.class)
		{
			if (currentInstance == null)
			{

				currentInstance = new HelpTextHelper();
			}
		}
		return currentInstance;
	}

	/**
	 * This method will check if the option has been defined in the property file.
	 * 
	 * @param option
	 *            this is the option to be evaluated.
	 * @return true if the option was set in property file.
	 */
	public boolean isAnDefinedOption(String option)
	{
		if (option == null)
		{
			return false;
		}

		if (option.equals(""))
		{
			return false;
		}

		if (helpTextProperties.containsOption(option))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * This method will check if the option needs to be overwritten.
	 * 
	 * @param option
	 *            this is the option key.
	 * @return true if the option has a related value.
	 */
	public boolean isAnOptionToOverwrite(String option)
	{
		if (isAnDefinedOption(option))
		{
			return helpTextProperties.hasADefinedValue(option);
		}
		else
		{
			return false;
		}
	}
	/**
	 * This method will check if the current option can be processed. if the current option needs to be overwritten..
	 * 
	 * @param gaeRecord
	 *            this is the gaeRecord which provides the information.
	 * @return true is the option can be processed.
	 */
	public boolean validateOption(GAERecordDataModel gaeRecord)
	{
		String gzName = gaeRecord.getHeaderJournalFileName().trim();
		String optionId = gaeRecord.getId();

		if (gzName.equals(""))
		{
			return false;
		}
		if (gaeRecord == null)
		{
			if (LOG.isInfoEnabled())
			{

				LOG.info(new StringBuilder("The option:").append(optionId)
								.append(" will not be processed. There is no gae record ").toString());
			}
			return false;
		}

		// checks if the option was set in property file.
		if (isAnDefinedOption(optionId))
		{
			if (LOG.isInfoEnabled())
			{
				LOG.info(new StringBuilder("The option:").append(optionId).append(" will not be processed.").toString());
			}
			// SKIP if this defined option has not a defined value.
			return false;
		}

		return true;
	}

}
