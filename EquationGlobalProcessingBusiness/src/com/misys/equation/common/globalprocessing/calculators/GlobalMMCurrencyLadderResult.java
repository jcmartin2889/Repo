package com.misys.equation.common.globalprocessing.calculators;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GMLDataModel;

public class GlobalMMCurrencyLadderResult extends AbstractGPEnquiryResult
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalMMCurrencyLadderResult.java 10087 2010-12-03 09:03:37Z WRIGHTP1 $";

	/**
	 * Unique Serial UID
	 */
	private static final long serialVersionUID = 4591110732662164513L;

	private final Collection<GMLDataModel> result;

	public GlobalMMCurrencyLadderResult(Collection<GMLDataModel> result)
	{
		super();
		this.result = result;
	}

	public Collection<GMLDataModel> getResult()
	{
		return result;
	}

}
