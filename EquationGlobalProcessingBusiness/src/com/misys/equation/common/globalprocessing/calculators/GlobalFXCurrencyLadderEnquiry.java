package com.misys.equation.common.globalprocessing.calculators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import com.misys.equation.common.consolidation.GFLDaoConsolidator;
import com.misys.equation.common.dao.beans.GFLDataModel;
import com.misys.equation.common.dates.EquationCalendar;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author Paul Hempenstall
 */
public final class GlobalFXCurrencyLadderEnquiry extends AbstractGlobalProcessingEnquiry
{
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalFXCurrencyLadderEnquiry.class);

	private GFLDaoConsolidator daoConsolidator;
	private String enqCurrency;
	private String enqStartDate;
	private String enqEndDate;
	private final String latestOvernightDate;
	private final String latestProcessingDate;

	public GlobalFXCurrencyLadderEnquiry(final String sessionIdentifier)
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
			daoConsolidator = new GFLDaoConsolidator(session);
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	@Override
	public AbstractGPEnquiryResult calculate()
	{
		Collection<GFLDataModel> fxCurrencyLadder = new LinkedHashSet<GFLDataModel>();

		Collection<GFLDataModel> overnightPositions = daoConsolidator.getOvernightPositions(enqCurrency);
		Collection<GFLDataModel> startOfDayMaturityPositions = daoConsolidator.getStartOfDayMaturityPositions(enqCurrency,
						enqEndDate);
		Collection<GFLDataModel> positions = daoConsolidator.getPositions(enqCurrency, enqEndDate);

		long runningCumulativeAmt = 0L;

		// Determine overnight position
		for (GFLDataModel ovPos : overnightPositions)
		{
			runningCumulativeAmt += ovPos.getFxAggregate();
		}

		// Adjust overnight position to take account of any start of day maturity positions (U1)
		for (GFLDataModel sodmPos : startOfDayMaturityPositions)
		{
			runningCumulativeAmt -= sodmPos.getFxSumTotal();
		}

		// Save overnight position, if required
		if (!enqStartDate.equals(enqEndDate) || Toolbox.parseInt(enqStartDate, 0) == 0)
		{
			if (Toolbox.parseInt(enqStartDate, 0) <= Toolbox.parseInt(latestProcessingDate, 0))
			{
				GFLDataModel fxLadder = new GFLDataModel("N", latestOvernightDate, 0L, 0L, 0L, runningCumulativeAmt);
				fxCurrencyLadder.add(fxLadder);
			}
		}

		// Build list of ladder dates (based on U1 and OI)
		List<String> ladderDates = new ArrayList<String>();

		for (GFLDataModel pos : startOfDayMaturityPositions)
		{
			String posDate = Toolbox.padAtFront(pos.getFxLadderDate(), "0", 7) + "A";

			if (!ladderDates.contains(posDate))
			{
				ladderDates.add(posDate);
			}
		}
		for (GFLDataModel pos : positions)
		{
			String posDate = Toolbox.padAtFront(pos.getFxLadderDate(), "0", 7) + "B";

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

			String date = ladderDate.substring(0, 7).trim();
			String type = ladderDate.substring(7, 8).trim();
			String selectable = "";

			// Process positions
			if (type.equals("A"))
			{
				for (GFLDataModel position : startOfDayMaturityPositions)
				{
					String positionDate = Toolbox.padAtFront(position.getFxLadderDate(), "0", 7);

					// Total positions according to ladder date
					if (positionDate.equals(date))
					{
						totalPayAmt += position.getFxPayTotal();
						totalReceiveAmt += position.getFxReceiveTotal();
						totalNetAmt += position.getFxSumTotal();
					}
				}
				selectable = "N";
			}
			else
			{
				for (GFLDataModel position : positions)
				{
					String positionDate = Toolbox.padAtFront(position.getFxLadderDate(), "0", 7);

					// Total positions according to ladder date
					if (positionDate.equals(date))
					{
						totalPayAmt += position.getFxPayTotal();
						totalReceiveAmt += position.getFxReceiveTotal();
						totalNetAmt += position.getFxSumTotal();
					}
				}
				selectable = "Y";
			}

			runningCumulativeAmt += totalNetAmt;

			// Save position, if required
			if (Toolbox.parseInt(enqStartDate, 0) <= Toolbox.parseInt(date, 0))
			{
				GFLDataModel fxLadder = new GFLDataModel(selectable, date, totalPayAmt, totalReceiveAmt, totalNetAmt,
								runningCumulativeAmt);
				fxCurrencyLadder.add(fxLadder);
			}
		}

		return new GlobalFXLadderResult(fxCurrencyLadder);
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