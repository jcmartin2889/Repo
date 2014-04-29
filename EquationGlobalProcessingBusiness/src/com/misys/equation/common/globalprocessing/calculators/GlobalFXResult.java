package com.misys.equation.common.globalprocessing.calculators;

import java.util.Collection;

import com.misys.equation.common.dao.beans.FPGDataModel;

public class GlobalFXResult extends AbstractGPEnquiryResult
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalFXResult.java 9210 2010-09-17 15:31:21Z deroset $";

	/**
	 * Unique Serial UID
	 */
	private static final long serialVersionUID = 4591110732662164513L;

	private final Collection<FPGDataModel> result;

	public GlobalFXResult(Collection<FPGDataModel> result)
	{
		super();
		this.result = result;
	}

	public Collection<FPGDataModel> getResult()
	{
		return result;
	}

}
