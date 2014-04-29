package com.misys.equation.common.globalprocessing.calculators;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GMGDataModel;

public class GlobalMMGapPositionsResult extends AbstractGPEnquiryResult
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalMMGapPositionsResult.java 9608 2010-10-27 10:24:24Z WRIGHTP1 $";

	/**
	 * Unique Serial UID
	 */
	private static final long serialVersionUID = 4591110732662164513L;

	private final Collection<GMGDataModel> result;

	public GlobalMMGapPositionsResult(Collection<GMGDataModel> result)
	{
		super();
		this.result = result;
	}

	public Collection<GMGDataModel> getResult()
	{
		return result;
	}

}
