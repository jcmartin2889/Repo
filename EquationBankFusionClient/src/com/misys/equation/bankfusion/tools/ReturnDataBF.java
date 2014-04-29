package com.misys.equation.bankfusion.tools;

import bf.com.misys.eqf.types.header.ServiceRsHeader;

public class ReturnDataBF
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ReturnDataBF.java 11321 2011-06-29 11:05:06Z GOLDSMC1 $";
	private ServiceRsHeader functionHeader;
	private Object functionData;

	/**
	 * Construct an empty return data
	 */
	public ReturnDataBF()
	{
	}

	/**
	 * Construct an return data
	 */
	public ReturnDataBF(ServiceRsHeader functionHeader, Object functionData)
	{
		this.functionHeader = functionHeader;
		this.functionData = functionData;
	}
	/**
	 * Return the function header
	 * 
	 * @return the function header
	 */
	public ServiceRsHeader getServiceRsHeader()
	{
		return functionHeader;
	}

	/**
	 * Set the function header
	 * 
	 * @param functionHeader
	 *            - the function header
	 */
	public void setFunctionHeader(ServiceRsHeader functionHeader)
	{
		this.functionHeader = functionHeader;
	}

	/**
	 * Return the complex type function data
	 * 
	 * @return the complex type function data
	 */
	public Object getFunctionData()
	{
		return functionData;
	}

	/**
	 * Set the complex type function data
	 * 
	 * @param functionData
	 *            - the complex type function data
	 */
	public void setFunctionData(Object functionData)
	{
		this.functionData = functionData;
	}

}
