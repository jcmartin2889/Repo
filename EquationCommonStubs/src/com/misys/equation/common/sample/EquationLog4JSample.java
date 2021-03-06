/**
 * Copyright and all other intellectual property rights in this software, in any form, is vested in Misys International Banking
 * Systems Ltd ("Misys") or a related company.
 * 
 * This software may not be copied, amended, compiled, translated, or developed; or sold, leased, hired, rented, or disclosed to any
 * third party without the prior written consent of Misys.
 * 
 * Copyright Misys International Banking Systems Ltd, 1975 and later
 */

package com.misys.equation.common.sample;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * Log4J sample code
 */
public class EquationLog4JSample
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationLog4JSample.java 15055 2012-12-18 15:04:53Z williae1 $";

	/** Logger for this class */
	private static final EquationLogger LOG = new EquationLogger(EquationLog4JSample.class);

	public static void main(String[] args) throws Exception
	{
		EquationLog4JSample loggingSample = new EquationLog4JSample();
		loggingSample.process();
	}

	public boolean process()
	{
		boolean continueProcessing = true;
		// Unconditioned Logging
		LOG.debug("debug logging");
		LOG.info("info logging");
		LOG.warn("warn logging");
		LOG.error("error logging");
		LOG.fatal("fatal logging");
		// Conditioned Logging for where the processing of the logging would be time consuming
		if (LOG.isDebugEnabled())
		{
			LOG.debug("conditioned debug logging");
		}
		if (LOG.isInfoEnabled())
		{
			LOG.info("conditioned info logging");
		}
		if (LOG.isWarnEnabled())
		{
			LOG.warn("conditioned warn logging");
		}
		if (LOG.isErrorEnabled())
		{
			LOG.error("conditioned error logging");
		}
		if (LOG.isFatalEnabled())
		{
			LOG.fatal("conditioned fatal logging");
		}
		// Exception logging
		EQException exception = new EQException("Test exception");
		LOG.debug("debug logging with exception", exception);
		LOG.info("info logging with exception", exception);
		LOG.warn("warn logging with exception", exception);
		LOG.error("error logging with exception", exception);
		LOG.fatal("fatal logging with exception", exception);

		return continueProcessing;
	}

}
