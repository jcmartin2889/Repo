package com.misys.equation.common.globalprocessing.calculators;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.List;

import com.misys.equation.common.consolidation.GFGDaoConsolidator;
import com.misys.equation.common.dao.beans.GFGDataModel;
import com.misys.equation.common.dao.beans.GPMRecordDataManager;
import com.misys.equation.common.dates.EquationCalendar;
import com.misys.equation.common.globalprocessing.systemoptions.GlobalSystemOptions;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author Paul Hempenstall
 */
public final class GlobalFXGapPositionsEnquiry extends AbstractGlobalProcessingEnquiry
{
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalFXGapPositionsEnquiry.class);

	private GFGDaoConsolidator daoConsolidator;
	private String enqCurrency;
	private List<String> gapIntervals;
	private List<Calendar> gapDates;
	private String lastOvernightDate;

	public GlobalFXGapPositionsEnquiry(final String sessionIdentifier)
	{
		// Call the initialisation method
		super(sessionIdentifier);
		init(sessionIdentifier);

		try
		{
			daoConsolidator = new GFGDaoConsolidator(session);
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

		GPMRecordDataManager gPMRecordDataManager = currentGlobalSystemOptions.getSystemOptionValue("GP_GAP_PERIODS_GLB_GFG");
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
		Collection<GFGDataModel> totalList = daoConsolidator.getFxTotal(enqCurrency);
		Collection<GFGDataModel> totalPosList = daoConsolidator.getAggregatePosition(enqCurrency);
		Collection<GFGDataModel> totalSODList = daoConsolidator.getStartOfDayMaturities(enqCurrency);

		Collection<GFGDataModel> gapPositionList = new LinkedHashSet<GFGDataModel>();

		// Set the latest pdate as the cutoff for rolling positions into
		long effectiveDateCutOff = Long.parseLong(lastOvernightDate);

		// Iterate through the positions list to create a running total of all the overnight positions
		for (GFGDataModel record : totalPosList)
		{
			runningTotal = runningTotal + record.getGfgAggregate();
		}

		// Adjust overnight position to take account of any start of day maturity positions (U1)
		for (GFGDataModel sodm : totalSODList)
		{
			runningTotal = runningTotal - sodm.getGfgSumTotal();
		}

		// Run through the selected OI and U1 position records and change any date that is before the unit's pdate to the pdate of
		// that unit
		long lastInterval = effectiveDateCutOff;

		for (GFGDataModel gfgRecord : totalList)
		{
			long gfgDate = Long.parseLong(gfgRecord.getUpToDate());
			long effectiveUnitDate = Long.parseLong(gfgRecord.getProcessingDate());
			if (gfgDate < effectiveUnitDate)
			{
				gfgRecord.setUpToDate(((Long) effectiveUnitDate).toString());
			}
		}
		for (GFGDataModel sodm : totalSODList)
		{
			long sodmDate = Long.parseLong(sodm.getUpToDate());
			long effectiveUnitDate = Long.parseLong(sodm.getProcessingDate());
			if (sodmDate < effectiveUnitDate)
			{
				sodm.setUpToDate(((Long) effectiveUnitDate).toString());
			}
		}

		// Next accumulate all the positions (OI and U1) before the latest processing date to include in the overnight position
		for (GFGDataModel gfgOvernight : totalList)
		{
			long gfgDate = Long.parseLong(gfgOvernight.getUpToDate());
			if (gfgDate <= effectiveDateCutOff)
			{
				runningPayTotal += gfgOvernight.getGfgPayTotal();
				runningRecTotal += gfgOvernight.getGfgReceiveTotal();
				runningNetTotal += gfgOvernight.getGfgSumTotal();
			}
		}
		for (GFGDataModel sodm : totalSODList)
		{
			long sodmDate = Long.parseLong(sodm.getUpToDate());
			if (sodmDate <= effectiveDateCutOff)
			{
				runningPayTotal += sodm.getGfgPayTotal();
				runningRecTotal += sodm.getGfgReceiveTotal();
				runningNetTotal += sodm.getGfgSumTotal();
			}
		}
		runningTotal += runningNetTotal;

		// Initialise and build a data model record to hold the overnight position values
		GFGDataModel overnightPosition = new GFGDataModel();

		overnightPosition.setSelectable("N");
		overnightPosition.setUpToDate(lastOvernightDate);
		overnightPosition.setGfgPayTotal(runningPayTotal);
		overnightPosition.setGfgReceiveTotal(runningRecTotal);
		overnightPosition.setGfgSumTotal(runningNetTotal);
		overnightPosition.setGfgAggregate(runningTotal);
		overnightPosition.setGfgIntervalStart("0000000");
		overnightPosition.setGfgIntervalEnd("0000000");
		gapPositionList.add(overnightPosition);

		// For every calculate gap position date, create a new data model to hold that gap dates
		// result and iterate through the positions (OI and U1) to build a running total for each interval
		// Set up the interval start and end dates for passing to the drill down function
		boolean firstGap = true;
		for (Calendar gapDate : gapDates)
		{
			GFGDataModel workingPosition = new GFGDataModel();
			Integer tempdate = Toolbox.getDateDBFormat(gapDate);
			long intervalDate = Long.parseLong(tempdate.toString());

			workingPosition.setSelectable("Y");
			workingPosition.setUpToDate(Toolbox.padAtFront(Long.toString(intervalDate), "0", 7));

			for (GFGDataModel gfgRecord : totalList)
			{
				long positionDate = Long.parseLong(gfgRecord.getUpToDate());

				if ((positionDate > lastInterval) && (positionDate <= intervalDate))
				{
					workingPosition.setGfgPayTotal(workingPosition.getGfgPayTotal() + gfgRecord.getGfgPayTotal());
					workingPosition.setGfgReceiveTotal(workingPosition.getGfgReceiveTotal() + gfgRecord.getGfgReceiveTotal());
					workingPosition.setGfgSumTotal(workingPosition.getGfgSumTotal() + gfgRecord.getGfgSumTotal());

					runningTotal += gfgRecord.getGfgSumTotal();
				}
			}

			// U1 positions only apply to first gap
			if (firstGap)
			{
				for (GFGDataModel sodm : totalSODList)
				{
					long positionDate = Long.parseLong(sodm.getUpToDate());

					if ((positionDate > lastInterval) && (positionDate <= intervalDate))
					{
						workingPosition.setGfgPayTotal(workingPosition.getGfgPayTotal() + sodm.getGfgPayTotal());
						workingPosition.setGfgReceiveTotal(workingPosition.getGfgReceiveTotal() + sodm.getGfgReceiveTotal());
						workingPosition.setGfgSumTotal(workingPosition.getGfgSumTotal() + sodm.getGfgSumTotal());

						runningTotal += sodm.getGfgSumTotal();
					}
				}
			}

			workingPosition.setGfgAggregate(runningTotal);

			GregorianCalendar startDate = Toolbox.parseEqDate(Long.toString(lastInterval));
			startDate.add(Calendar.DATE, 1);
			if (firstGap)
			{
				workingPosition.setGfgIntervalStart("0000000");
			}
			else
			{
				workingPosition.setGfgIntervalStart(Toolbox.getDateDBStringFormat(startDate));
			}
			workingPosition.setGfgIntervalEnd(Toolbox.padAtFront(Long.toString(intervalDate), "0", 7));

			gapPositionList.add(workingPosition);
			lastInterval = intervalDate;
			firstGap = false;
		}

		return new GlobalFXGapPositionsResult(gapPositionList);
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