package com.misys.equation.common.conversion;

import com.misys.equation.common.utilities.EqDataType;

public class OutputParameterConversion
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OutputParameterConversion.java 17435 2013-10-15 14:16:27Z lima12 $";
	
	/**
	 * Convert an amount (15,0) in Equation database format enquiry into WebService format
	 * 
	 * @param amount
	 *            - the amount
	 * 
	 * @return the amount in WebService format
	 */
	public static String convertAmount(String amount)
	{
		return convertAmount(amount, 15, 0);
	}

	/**
	 * Convert a rate (11,7) in Equation database format enquiry into WebService format
	 * 
	 * @param rate
	 *            - the rate
	 * 
	 * @return the rate in WebService format
	 */
	public static String convertRate(String rate)
	{
		return convertAmount(rate, 11, 7);
	}

	/**
	 * Convert an amount in Equation database enquiry format into WebService format
	 * 
	 * @param amount
	 *            - the amount
	 * @param length
	 *            - the length
	 * @param decimal
	 *            - the decimal
	 * 
	 * @return the edited amount
	 */
	public static String convertAmount(String amount, int length, int decimal)
	{
		String edittedAmount = EqDataType.formatEquationAmount(amount, length, decimal, EqDataType.INTEGERSEP,
						EqDataType.DECIMALSEP);
		return edittedAmount.replaceAll(EqDataType.INTEGERSEP, "");
	}

	/**
	 * Convert a date (cyymmdd) into yyyymmdd
	 * 
	 * @param date
	 *            - the date
	 * 
	 * @return the date in yyyymmdd
	 */
	public static String convertDate(String scyymmdd)
	{
		try
		{
			int cyymmdd = Integer.parseInt(scyymmdd);
			if (cyymmdd == 0)
			{
				return "";
			}
			else if (cyymmdd == EqDataType.DATE_OPENN)
			{
				return InputParameterConversion.DATE_OPEN;
			}
			else
			{
				return String.valueOf(cyymmdd + 19000000);
			}
		}
		catch (NumberFormatException numFmtEx)
		{
			throw new RuntimeException(InputParameterConversion.formatString("Equation Date specified [%] is invalid", scyymmdd));
		}
	}

}
