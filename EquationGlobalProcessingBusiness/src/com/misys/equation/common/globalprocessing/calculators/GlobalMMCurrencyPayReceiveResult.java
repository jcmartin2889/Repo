package com.misys.equation.common.globalprocessing.calculators;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GMEDataModel;

public class GlobalMMCurrencyPayReceiveResult extends AbstractGPEnquiryResult
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalMMCurrencyPayReceiveResult.java 10085 2010-12-03 09:00:00Z WRIGHTP1 $";

	/**
	 * Unique Serial UID
	 */
	private static final long serialVersionUID = 4591110732662164513L;

	private final Collection<GMEDataModel> result;

	public GlobalMMCurrencyPayReceiveResult(Collection<GMEDataModel> result)
	{
		super();
		this.result = result;
	}

	public Collection<GMEDataModel> getResult()
	{
		return result;
	}

}
