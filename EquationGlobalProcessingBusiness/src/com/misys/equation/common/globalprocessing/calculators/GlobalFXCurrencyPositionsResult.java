package com.misys.equation.common.globalprocessing.calculators;

import java.util.Collection;

import com.misys.equation.common.dao.beans.GFETotalPerDateDataModel;

public class GlobalFXCurrencyPositionsResult extends AbstractGPEnquiryResult
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalFXCurrencyPositionsResult.java 9210 2010-09-17 15:31:21Z deroset $";
	/**
	 * Unique serial UID
	 */
	private static final long serialVersionUID = -3476856297494619100L;

	private final Collection<GFETotalPerDateDataModel> result;

	public GlobalFXCurrencyPositionsResult(Collection<GFETotalPerDateDataModel> result)
	{
		super();
		this.result = result;
	}

	public Collection<GFETotalPerDateDataModel> getResult()
	{
		return result;
	}

}