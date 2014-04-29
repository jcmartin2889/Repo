package com.misys.equation.common.globalprocessing.calculators;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GPETotalPerDateDataModel;

public class GlobalCashPositionsResult extends AbstractGPEnquiryResult
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalCashPositionsResult.java 9572 2010-10-22 12:37:33Z WRIGHTP1 $";

	/**
	 * Unique serial UID
	 */
	private static final long serialVersionUID = -3476856297494619100L;

	private final Collection<GPETotalPerDateDataModel> result;

	public GlobalCashPositionsResult(Collection<GPETotalPerDateDataModel> result)
	{
		super();
		this.result = result;
	}

	public Collection<GPETotalPerDateDataModel> getResult()
	{
		return result;
	}
}
