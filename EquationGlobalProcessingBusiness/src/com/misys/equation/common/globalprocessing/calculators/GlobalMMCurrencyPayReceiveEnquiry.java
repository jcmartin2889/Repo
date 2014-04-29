package com.misys.equation.common.globalprocessing.calculators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import com.misys.equation.common.consolidation.GMEDaoConsolidator;
import com.misys.equation.common.dao.beans.GMEDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author Paul Wright
 */
public final class GlobalMMCurrencyPayReceiveEnquiry extends AbstractGlobalProcessingEnquiry
{
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalMMCurrencyPayReceiveEnquiry.class);

	private GMEDaoConsolidator daoConsolidator;
	private String enqCurrency;
	private String enqDate;

	public GlobalMMCurrencyPayReceiveEnquiry(final String sessionIdentifier)
	{
		// Call the initialisation method
		super(sessionIdentifier);
		init(sessionIdentifier);

		try
		{
			daoConsolidator = new GMEDaoConsolidator(session);
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	@Override
	public AbstractGPEnquiryResult calculate()
	{
		Collection<GMEDataModel> mmCurrencyPayReceive = new LinkedHashSet<GMEDataModel>();

		Collection<GMEDataModel> positions = daoConsolidator.getPositions(enqCurrency, enqDate);

		// Build list of units and branches
		List<String> unitsBranches = new ArrayList<String>();

		for (GMEDataModel pos : positions)
		{
			String posUnitBranch = Toolbox.pad(pos.getMMSystem(), 8) + Toolbox.pad(pos.getMMUnit(), 3)
							+ Toolbox.pad(pos.getMMBranch(), 4);
			unitsBranches.add(posUnitBranch);
		}

		// Sort list of units and branches into ascending sequence
		Collections.sort(unitsBranches);

		String prevSystem = "";
		String prevUnit = "";
		long totalPayAmt = 0L;
		long totalReceiveAmt = 0L;
		long totalNetAmt = 0L;

		// Process sorted list of units and branches
		for (String unitBranch : unitsBranches)
		{
			String system = unitBranch.substring(0, 8).trim();
			String unit = unitBranch.substring(8, 11).trim();
			String branch = unitBranch.substring(11, 15).trim();

			// Save position (unit), if required
			if (!system.equals(prevSystem) || !unit.equals(prevUnit))
			{
				if (!prevSystem.equals("") && !prevUnit.equals(""))
				{
					GMEDataModel mmPayReceive = new GMEDataModel(prevSystem, prevUnit, "", totalPayAmt, totalReceiveAmt,
									totalNetAmt);
					mmCurrencyPayReceive.add(mmPayReceive);
				}

				prevSystem = system;
				prevUnit = unit;
				totalPayAmt = 0L;
				totalReceiveAmt = 0L;
				totalNetAmt = 0L;
			}

			// Process positions
			for (GMEDataModel position : positions)
			{
				String positionSystem = position.getMMSystem();
				String positionUnit = position.getMMUnit();
				String positionBranch = position.getMMBranch();

				// Total positions and save position (branch), if required
				if (positionSystem.equals(system) && positionUnit.equals(unit) && positionBranch.equals(branch))
				{
					totalPayAmt += position.getMMPayAmt();
					totalReceiveAmt += position.getMMReceiveAmt();
					totalNetAmt += position.getMMNetAmt();

					GMEDataModel mmPayReceive = new GMEDataModel(positionSystem, positionUnit, positionBranch,
									position.getMMPayAmt(), position.getMMReceiveAmt(), position.getMMNetAmt());
					mmCurrencyPayReceive.add(mmPayReceive);
				}
			}
		}

		// Save position (unit), if required
		if (unitsBranches.size() > 0)
		{
			GMEDataModel mmPayReceive = new GMEDataModel(prevSystem, prevUnit, "", totalPayAmt, totalReceiveAmt, totalNetAmt);
			mmCurrencyPayReceive.add(mmPayReceive);
		}

		return new GlobalMMCurrencyPayReceiveResult(mmCurrencyPayReceive);
	}

	@Override
	public AbstractGPEnquiryResult calculate(String... params) throws EQException
	{
		if (params.length != 2)
		{
			throw new EQException("Invalid initialisation arguments");
		}

		this.enqCurrency = params[0];
		this.enqDate = params[1];

		return calculate();
	}
}
