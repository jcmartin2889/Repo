package com.misys.equation.common.globalprocessing.calculators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import com.misys.equation.common.consolidation.GMLDaoConsolidator;
import com.misys.equation.common.dao.beans.GMLDataModel;
import com.misys.equation.common.dates.EquationCalendar;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * 
 * @author Paul Wright
 * 
 */
public final class GlobalMMCurrencyLadderEnquiry extends AbstractGlobalProcessingEnquiry
{
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalMMCurrencyLadderEnquiry.class);

	private GMLDaoConsolidator daoConsolidator;
	private String enqCurrency;
	private String enqStartDate;
	private String enqEndDate;
	private String latestOvernightDate;
	private String latestProcessingDate;

	public GlobalMMCurrencyLadderEnquiry(final String sessionIdentifier)
	{
		// Call the initialisation method
		super(sessionIdentifier);
		init(sessionIdentifier);

		// Determine latest overnight date
		latestOvernightDate = Toolbox.padAtFront(EquationCalendar.getLatestOvernightDateString(sessions), "0", 7);

		// Determine latest processing date
		latestProcessingDate = Toolbox.padAtFront(EquationCalendar.getLatestProcessingDateString(sessions), "0", 7);

		try
		{
			daoConsolidator = new GMLDaoConsolidator(session);
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	@Override
	public AbstractGPEnquiryResult calculate()
	{
		Collection<GMLDataModel> mmCurrencyLadder = new LinkedHashSet<GMLDataModel>();

		Collection<GMLDataModel> overnightPositions = daoConsolidator.getOvernightPositions(enqCurrency);
		Collection<GMLDataModel> positions = daoConsolidator.getPositions(enqCurrency, enqEndDate);

		long runningCumulativeAmt = 0L;

		// Determine overnight position
		for (GMLDataModel ovPos : overnightPositions)
		{
			runningCumulativeAmt += ovPos.getMMCumulativeAmt();
		}

		// Save overnight position, if required
		if (!enqStartDate.equals(enqEndDate) || Toolbox.parseInt(enqStartDate, 0) == 0)
		{
			if (Toolbox.parseInt(enqStartDate, 0) <= Toolbox.parseInt(latestProcessingDate, 0))
			{
				GMLDataModel mmLadder = new GMLDataModel("N", latestOvernightDate, 0L, 0L, 0L, runningCumulativeAmt);
				mmCurrencyLadder.add(mmLadder);

			}
		}

		// Build list of ladder dates
		List<String> ladderDates = new ArrayList<String>();

		for (GMLDataModel pos : positions)
		{
			String posDate = Toolbox.padAtFront(pos.getMMLadderDate(), "0", 7);

			if (!ladderDates.contains(posDate))
			{
				ladderDates.add(posDate);
			}
		}

		// Sort list of ladder dates into ascending sequence
		Collections.sort(ladderDates);

		// Process sorted list of ladder dates
		for (String ladderDate : ladderDates)
		{
			long totalPayAmt = 0L;
			long totalReceiveAmt = 0L;
			long totalNetAmt = 0L;

			// Process positions
			for (GMLDataModel position : positions)
			{
				String positionDate = Toolbox.padAtFront(position.getMMLadderDate(), "0", 7);

				// Total positions according to ladder date
				if (positionDate.equals(ladderDate))
				{
					totalPayAmt += position.getMMPayAmt();
					totalReceiveAmt += position.getMMReceiveAmt();
					totalNetAmt += position.getMMNetAmt();
				}
			}

			runningCumulativeAmt += totalNetAmt;

			// Save position, if required
			if (Toolbox.parseInt(enqStartDate, 0) <= Toolbox.parseInt(ladderDate, 0))
			{
				GMLDataModel mmLadder = new GMLDataModel("Y", ladderDate, totalPayAmt, totalReceiveAmt, totalNetAmt,
								runningCumulativeAmt);
				mmCurrencyLadder.add(mmLadder);
			}
		}

		return new GlobalMMCurrencyLadderResult(mmCurrencyLadder);
	}

	@Override
	public AbstractGPEnquiryResult calculate(String... params) throws EQException
	{
		if (params.length != 3)
		{
			throw new EQException("Invalid initialisation arguments");
		}

		this.enqCurrency = params[0];
		this.enqStartDate = params[1];
		this.enqEndDate = params[2];

		return calculate();
	}

}
