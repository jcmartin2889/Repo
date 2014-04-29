package com.misys.equation.common.globalprocessing.calculators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import com.misys.equation.common.consolidation.FPGDaoConsolidator;
import com.misys.equation.common.dao.beans.FPGDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

/**
 * @author Paul Hempenstall
 */
public final class GlobalFXCurrencyPayReceiveEnquiry extends AbstractGlobalProcessingEnquiry
{
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(GlobalFXCurrencyPayReceiveEnquiry.class);

	private FPGDaoConsolidator daoConsolidator;
	private String enqCurrency;
	private String enqDate;

	public GlobalFXCurrencyPayReceiveEnquiry(final String sessionIdentifier)
	{
		// Call the initialisation method
		super(sessionIdentifier);
		init(sessionIdentifier);

		try
		{
			daoConsolidator = new FPGDaoConsolidator(session);
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
	}

	@Override
	public AbstractGPEnquiryResult calculate()
	{
		Collection<FPGDataModel> fxCurrencyPayReceive = new LinkedHashSet<FPGDataModel>();

		Collection<FPGDataModel> positions = daoConsolidator.getFxTotal(enqCurrency, enqDate);

		// Build list of units and branches
		List<String> unitsBranches = new ArrayList<String>();

		for (FPGDataModel pos : positions)
		{
			String posUnitBranch = Toolbox.pad(pos.getFxSystem(), 8) + Toolbox.pad(pos.getUnitMnem(), 3)
							+ Toolbox.pad(pos.getBranchMnem(), 4);
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
					FPGDataModel fxPayReceive = new FPGDataModel(prevSystem, prevUnit, "", totalPayAmt, totalReceiveAmt,
									totalNetAmt);
					fxCurrencyPayReceive.add(fxPayReceive);
				}

				prevSystem = system;
				prevUnit = unit;
				totalPayAmt = 0L;
				totalReceiveAmt = 0L;
				totalNetAmt = 0L;
			}

			// Process positions
			for (FPGDataModel position : positions)
			{
				String positionSystem = position.getFxSystem();
				String positionUnit = position.getUnitMnem();
				String positionBranch = position.getBranchMnem();

				// Total positions and save position (branch), if required
				if (positionSystem.equals(system) && positionUnit.equals(unit) && positionBranch.equals(branch))
				{
					totalPayAmt += position.getFxPayTotal();
					totalReceiveAmt += position.getFxReceiveTotal();
					totalNetAmt += position.getFxSumTotal();

					FPGDataModel fxPayReceive = new FPGDataModel(positionSystem, positionUnit, positionBranch, position
									.getFxPayTotal(), position.getFxReceiveTotal(), position.getFxSumTotal());
					fxCurrencyPayReceive.add(fxPayReceive);
				}
			}
		}

		// Save position (unit), if required
		if (unitsBranches.size() > 0)
		{
			FPGDataModel fxPayReceive = new FPGDataModel(prevSystem, prevUnit, "", totalPayAmt, totalReceiveAmt, totalNetAmt);
			fxCurrencyPayReceive.add(fxPayReceive);
		}

		return new GlobalFXResult(fxCurrencyPayReceive);
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
