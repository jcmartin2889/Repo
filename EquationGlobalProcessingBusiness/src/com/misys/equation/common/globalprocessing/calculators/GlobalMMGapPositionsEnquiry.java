package com.misys.equation.common.globalprocessing.calculators;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;

import com.misys.equation.common.consolidation.GMGDaoConsolidator;
import com.misys.equation.common.dao.beans.GMGDataModel;
import com.misys.equation.common.dao.beans.GPMRecordDataManager;
import com.misys.equation.common.dates.EquationCalendar;
import com.misys.equation.common.globalprocessing.systemoptions.GlobalSystemOptions;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author Paul Wright
 */
public final class GlobalMMGapPositionsEnquiry extends AbstractGlobalProcessingEnquiry
{
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalMMGapPositionsEnquiry.class);

	private GMGDaoConsolidator daoConsolidator;
	private String enqCurrency;
	private List<String> gapIntervals;
	private List<Calendar> gapDates;
	private String lastOvernightDate;

	public GlobalMMGapPositionsEnquiry(final String sessionIdentifier)
	{
		// Call the initialisation method
		super(sessionIdentifier);
		init(sessionIdentifier);

		try
		{
			daoConsolidator = new GMGDaoConsolidator(session);
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	@Override
	protected void init(final String sessionIdentifier)
	{
		super.init(sessionIdentifier);
		// Do any additional initialisations here

		// Get the last overnight date ...
		lastOvernightDate = Toolbox.padAtFront(EquationCalendar.getLatestOvernightDateString(sessions), "0", 7);

		// Set the number of days and start and end dates
		gapDates = calculateDays();
	}

	private List<Calendar> calculateDays()
	{
		List<Calendar> calculatedDaysAndAmounts = new ArrayList<Calendar>();

		defineGapIntervals();

		for (String gapInterval : gapIntervals)
		{
			GregorianCalendar now2 = Toolbox.parseEqDate(lastOvernightDate);
			int interval = Integer.parseInt(gapInterval);
			now2.add(Calendar.DATE, interval);
			calculatedDaysAndAmounts.add(now2);
		}

		return calculatedDaysAndAmounts;
	}

	/**
	 * This method will define the number of days to be displayed. It will be done base on system options.
	 */
	private void defineGapIntervals()
	{
		GlobalSystemOptions currentGlobalSystemOptions = new GlobalSystemOptions(session);

		GPMRecordDataManager gPMRecordDataManager = currentGlobalSystemOptions.getSystemOptionValue("GP_GAP_PERIODS_GLB_GMG");
		gapIntervals = gPMRecordDataManager.getSystemOptionStringArrayListValue();
	}

	@Override
	public AbstractGPEnquiryResult calculate()
	{
		// Set up running total local variables
		long runningTotal = 0L;
		long runningRecTotal = 0L;
		long runningPayTotal = 0L;
		long runningNetTotal = 0L;

		// Run the consolidated queries and set up the new result set
		Collection<GMGDataModel> totalPosList = daoConsolidator.getOvernightPosition(enqCurrency);
		Collection<GMGDataModel> totalList = daoConsolidator.getPositions(enqCurrency);

		Collection<GMGDataModel> mmGapPositions = new LinkedHashSet<GMGDataModel>();

		// Set the latest pdate as the cutoff for rolling positions into
		long effectiveDateCutOff = Long.parseLong(lastOvernightDate);

		// Iterate through the positions list to create a running total of all the overnight positions
		for (GMGDataModel record : totalPosList)
		{
			runningTotal = runningTotal + record.getGapCumulativeAmt();
		}

		// Run through the selected OJ position records and change any date that is before the unit's pdate to the pdate of that
		// unit
		long lastInterval = effectiveDateCutOff;

		for (GMGDataModel gmgRecord : totalList)
		{
			long gmgDate = Long.parseLong(gmgRecord.getGapDate());
			long effectiveUnitDate = Long.parseLong(gmgRecord.getProcessingDate());
			if (gmgDate < effectiveUnitDate)
			{
				gmgRecord.setGapDate(((Long) effectiveUnitDate).toString());
			}
		}

		// Next accumulate all the positions before the latest processing date to include in the overnight position
		for (GMGDataModel gmgOvernight : totalList)
		{
			long gmgDate = Long.parseLong(gmgOvernight.getGapDate());
			if (gmgDate <= effectiveDateCutOff)
			{
				runningPayTotal += gmgOvernight.getGapPayAmt();
				runningRecTotal += gmgOvernight.getGapReceiveAmt();
				runningNetTotal += gmgOvernight.getGapNetAmt();
			}
		}
		runningTotal += runningNetTotal;

		// Initialise and build a data model record to hold the overnight position values
		GMGDataModel overnightPosition = new GMGDataModel();

		overnightPosition.setSelectable("N");
		overnightPosition.setGapDate(lastOvernightDate);
		overnightPosition.setGapPayAmt(runningPayTotal);
		overnightPosition.setGapReceiveAmt(runningRecTotal);
		overnightPosition.setGapNetAmt(runningNetTotal);
		overnightPosition.setGapCumulativeAmt(runningTotal);
		overnightPosition.setIntervalStartDate("0000000");
		overnightPosition.setIntervalEndDate("0000000");
		mmGapPositions.add(overnightPosition);

		// For every calculate gap position date, create a new data model to hold that gap dates
		// result and iterate through the positions to build a running total for each interval
		// Set up the interval start and end dates for passing to the drill down function
		boolean firstGap = true;
		for (Calendar gapDate : gapDates)
		{
			GMGDataModel workingPosition = new GMGDataModel();
			Integer tempdate = Toolbox.getDateDBFormat(gapDate);
			long intervalDate = Long.parseLong(tempdate.toString());

			workingPosition.setSelectable("Y");
			workingPosition.setGapDate(Toolbox.padAtFront(Long.toString(intervalDate), "0", 7));

			for (GMGDataModel gmgRecord : totalList)
			{
				long positionDate = Long.parseLong(gmgRecord.getGapDate());

				if ((positionDate > lastInterval) && (positionDate <= intervalDate))
				{
					workingPosition.setGapPayAmt(workingPosition.getGapPayAmt() + gmgRecord.getGapPayAmt());
					workingPosition.setGapReceiveAmt(workingPosition.getGapReceiveAmt() + gmgRecord.getGapReceiveAmt());
					workingPosition.setGapNetAmt(workingPosition.getGapNetAmt() + gmgRecord.getGapNetAmt());

					runningTotal += gmgRecord.getGapNetAmt();
				}
			}

			workingPosition.setGapCumulativeAmt(runningTotal);

			GregorianCalendar startDate = Toolbox.parseEqDate(Long.toString(lastInterval));
			startDate.add(Calendar.DATE, 1);
			if (firstGap)
			{
				workingPosition.setIntervalStartDate("0000000");
			}
			else
			{
				workingPosition.setIntervalStartDate(Toolbox.getDateDBStringFormat(startDate));
			}
			workingPosition.setIntervalEndDate(Toolbox.padAtFront(Long.toString(intervalDate), "0", 7));

			mmGapPositions.add(workingPosition);
			lastInterval = intervalDate;
			firstGap = false;
		}

		return new GlobalMMGapPositionsResult(mmGapPositions);
	}

	@Override
	public AbstractGPEnquiryResult calculate(String... params) throws EQException
	{
		if (params.length != 1)
		{
			throw new EQException("Invalid initialisation arguments");
		}

		this.enqCurrency = params[0];

		return calculate();
	}
}