package com.misys.equation.common.conversion;

import com.misys.equation.common.utilities.EqDataType;

public class InputParameterConversion
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: InputParameterConversion.java 17435 2013-10-15 14:16:27Z lima12 $";

	public static final String DATE_OPEN = "99999999";
	public static final int DATE_OPENN = 99999999;

	/**
	 * Format a string
	 * 
	 * @param str
	 *            - the string
	 * @param parameter
	 *            - the replacement string for %1
	 * 
	 * @return the formatted string
	 */
	public static String formatString(String str, String parameter)
	{
		return str.replace("%1", parameter);
	}

	/**
	 * Convert an unsigned amount (15,0) into Equation enquiry format (15A)
	 * 
	 * @param amount
	 *            - the unsigned amount
	 * 
	 * @return the amount in Equation format
	 */
	public static String convertAmount(String amount)
	{
		return convertAmount(amount, 15, 0);
	}

	/**
	 * Convert a signed amount (15,0) into Equation enquiry format(16A)
	 * 
	 * @param amount
	 *            - the signed amount
	 * 
	 * @return the amount in Equation format
	 */
	public static String convertSignedAmount(String amount)
	{
		return convertSignedAmount(amount, 15, 0);
	}

	/**
	 * Convert an unsigned rate (11, 7) into Equation format accepted by Equation enquiry (12A)
	 * 
	 * @param rate
	 *            - the unsigned rate
	 * 
	 * @return the rate in Equation format
	 */
	public static String convertRate(String rate)
	{
		return convertAmount(rate, 11, 7);
	}

	/**
	 * Convert a signed rate (11, 7) into Equation enquiry format (13A)
	 * 
	 * @param rate
	 *            - the signed rate
	 * 
	 * @return the rate in Equation format
	 */
	public static String convertSignedRate(String rate)
	{
		return convertSignedAmount(rate, 11, 7);
	}

	/**
	 * Convert the unsigned amount into Equation enquiry format
	 * 
	 * @param amount
	 *            - the amount
	 * @param length
	 *            - the maximum length of the amount
	 * @param decimal
	 *            - the number of decimal places
	 * 
	 * @return the amount in Equation format
	 */
	public static String convertAmount(String amount, int length, int decimal)
	{
		// Null
		if (amount == null)
		{
			return "";
		}

		// empty?
		if (amount.trim().length() == 0)
		{
			return "";
		}

		return EqDataType.cvtDBNumericToISeriesNumeric(amount, length, decimal);

		// // Amount
		// String strAmount = amount;
		//
		// // decimal point?
		// int decimalPointIndex = strAmount.indexOf(".");
		//
		// // decimal point exists but there should not be any decimal point
		// if (decimalPointIndex >= 0 && decimal == 0)
		// {
		// throw new RuntimeException(formatString("Amount specified [%1] is invalid.  No decimal point expected", amount));
		// }
		//
		// // get the decimal part and make sure it is of correct length
		// String decimalPart = "";
		// if (decimalPointIndex >= 0 && (decimalPointIndex + 1) < strAmount.length())
		// {
		// decimalPart = strAmount.substring(decimalPointIndex + 1);
		// }
		// decimalPart = Toolbox.pad(decimalPart, "0", decimal);
		//
		// // get the whole number part
		// String wholePart;
		// if (decimalPointIndex >= 0)
		// {
		// wholePart = strAmount.substring(0, decimalPointIndex);
		// }
		// else
		// {
		// wholePart = strAmount;
		// }
		// wholePart = Toolbox.padAtFront(wholePart, "0", length - decimal);
		//
		// // combine whole number + decimal part
		// String newString = wholePart + decimalPart;
		//
		// return newString;
	}

	/**
	 * Convert the signed amount into Equation enquiry format
	 * 
	 * @param amount
	 *            - the amount
	 * @param length
	 *            - the maximum length of the amount
	 * @param decimal
	 *            - the number of decimal places
	 * 
	 * @return the amount in Equation format
	 */
	public static String convertSignedAmount(String amount, int length, int decimal)
	{
		// Null
		if (amount == null)
		{
			return "";
		}

		// empty?
		if (amount.trim().length() == 0)
		{
			return "";
		}

		// negative sign?
		boolean negative = amount.indexOf("-") > -1;

		// remove negative sign
		String strAmount = amount.replace("-", "");

		strAmount = convertAmount(strAmount, length, decimal);

		if (negative)
		{
			strAmount = "-" + strAmount;
		}
		else
		{
			strAmount = "0" + strAmount;
		}

		return strAmount;
	}

	/**
	 * Convert date in yyyymmdd format into Equation date format
	 * 
	 * @param yyyymmdd
	 *            - date in yyyymmdd format
	 * 
	 * @return the Equation date format
	 */
	public static String convertDate(String yyyymmdd)
	{
		// blank?
		if (yyyymmdd == null)
		{
			return "";
		}
		if (yyyymmdd.trim().length() == 0)
		{
			return "";
		}
		if (yyyymmdd.equals(DATE_OPEN))
		{
			return EqDataType.DATE_OPEN;
		}

		try
		{
			int inputDate = Integer.parseInt(yyyymmdd);
			return Integer.toString(convertDate(inputDate));
		}
		catch (NumberFormatException numFmtEx)
		{
			throw new RuntimeException(formatString("Date specified [%1] is invalid", yyyymmdd));
		}
	}

	/**
	 * Convert date in yyyymmdd format into Equation date format
	 * 
	 * @param yyyymmdd
	 *            - date in yyyymmdd format
	 * 
	 * @return the Equation date format
	 */
	public static int convertDate(int yyyymmdd)
	{
		// blank?
		if (yyyymmdd == 0)
		{
			return 0;
		}
		else if (yyyymmdd == InputParameterConversion.DATE_OPENN)
		{
			return EqDataType.DATE_OPENN;
		}

		int formattedDate = yyyymmdd - (19000000);
		if (formattedDate < -1)
		{
			throw new RuntimeException(formatString("Date specified [%1] is invalid", String.valueOf(yyyymmdd)));
		}

		return formattedDate;
	}

}
