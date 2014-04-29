package com.misys.equation.common.globalprocessing.calculators;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationConfigurationPropertiesBean;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.UnitPropertiesBean;
import com.misys.equation.common.dao.beans.C8RecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This is the base class for all global processing enquiries. All global processing queries should extend from this class.
 * 
 * @author camillen
 */
public abstract class AbstractGlobalProcessingEnquiry
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractGlobalProcessingEnquiry.java 10648 2011-03-21 16:12:53Z MACDONP1 $";

	public static final String SQL_PARAMETER = "?";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(AbstractGlobalProcessingEnquiry.class);

	protected boolean isGlobalProcessing;
	protected String sessionIdentifier;
	protected List<EquationStandardSession> sessions;
	protected EquationStandardSession session;

	protected List<String> unauthorisedSessions = new ArrayList<String>();

	/**
	 * Constructor
	 * 
	 * @param sessionIdentifier
	 *            String
	 */
	public AbstractGlobalProcessingEnquiry(final String sessionIdentifier)
	{
		// Set the session ID for this calculation ..
		this.sessionIdentifier = sessionIdentifier;
		init(sessionIdentifier);
	}

	/**
	 * This method initialises the Enquiry class by getting the sessions for the user and executing an init query
	 * 
	 * @param sessionIdentifier
	 *            String - Session Id for the current user
	 * @param initQuery
	 *            String - The initialisation query to be executed for data loading.
	 */
	protected void init(final String sessionIdentifier)
	{
		// Get the session ...
		session = EquationCommonContext.getContext().getEquationUser(sessionIdentifier).getSession();
		// Determine if global processing is on ...
		try
		{
			isGlobalProcessing = session.getUnit().hasGlobalProcessing();
		}
		catch (EQException e1)
		{
			LOG.error("Could not read has global processing setting. Defaulting to false.");
			isGlobalProcessing = false;
		}

		try
		{
			this.sessions = EquationCommonContext.getContext().getGlobalProcessingEquationStandardSessions(sessionIdentifier);
		}
		catch (Exception e)
		{
			LOG.error("Cannot retrieve the global processing sessions", e);
		}

		// Put any missing sessions in a list
		for (UnitPropertiesBean unit : EquationConfigurationPropertiesBean.getInstance().getUnitsList())
		{
			boolean unitFound = false;
			for (EquationStandardSession cSession : sessions)
			{
				if (unit.getSystemId().equals(cSession.getSystemId()) && unit.getUnitId().equals(cSession.getUnitId()))
				{
					unitFound = true;
				}
			}

			if (!unitFound)
			{
				unauthorisedSessions.add(unit.getSystemId() + "/" + unit.getUnitId());
			}
		}
	}

	/**
	 * This abstract method performs all the calculations and returns an EnquiryResult to be processed by the calling module.
	 * 
	 * @return AbstractGPEnquiryResult result
	 */
	public abstract AbstractGPEnquiryResult calculate();

	public abstract AbstractGPEnquiryResult calculate(String... params) throws EQException;

	/**
	 * Given an amount and a currency this method will convert the amount to the required currency format.
	 * 
	 * @param currency
	 *            String - currency
	 * @param amount
	 *            String - amount
	 * 
	 * @return String - Formatted amount
	 */
	public String formatAmount(final String currency, final String amount)
	{
		// Get the equation user to be able to retrieve the decimal and integer separator
		EquationUser eqUser = EquationCommonContext.getContext().getEquationUser(sessionIdentifier);
		String foreignDecimalSeparator = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.DECST);
		String foreignIntegerSeparator = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.INTST);

		C8RecordDataModel currencyInfo;
		int decimal = 2;
		try
		{
			currencyInfo = eqUser.getEquationUnit().getRecords().getC8Record(currency);
			decimal = Integer.parseInt(currencyInfo.getEditField());
		}
		catch (EQException e)
		{
		}

		return EqDataType.formatEquationAmount(amount, Integer.parseInt(EqDataType.EDIT_AMOUNT_DEFAULT), decimal,
						foreignIntegerSeparator, foreignDecimalSeparator);
	}

	/**
	 * Getter method for the global processing sessions
	 * 
	 * @return List<EquationStandardSession> - A list of sessions for the user
	 */
	public List<EquationStandardSession> getSessions()
	{
		return sessions;
	}

	/**
	 * Utility method to print to the log. Could be used to write to the audit simultaneously for example.
	 * 
	 * @param message
	 *            String - Message to be shown
	 */
	public void printToLog(final String message)
	{
		LOG.info("Global Enquiry: " + message);
	}

	public boolean isGlobalProcessing()
	{
		return isGlobalProcessing;
	}

	public String getSessionIdentifier()
	{
		return sessionIdentifier;
	}

	public EquationStandardSession getSession()
	{
		return session;
	}

	public List<String> getUnauthorisedSessions()
	{
		return unauthorisedSessions;
	}
}