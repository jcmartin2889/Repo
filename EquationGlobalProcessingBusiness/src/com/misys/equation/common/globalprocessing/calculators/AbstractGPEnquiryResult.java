package com.misys.equation.common.globalprocessing.calculators;

import java.io.Serializable;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.beans.C8RecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;

/**
 * This is the base class for the results returned for all the Java Global Processing enquiries.
 * 
 * @author camillen
 * 
 */
public abstract class AbstractGPEnquiryResult implements Serializable
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbstractGPEnquiryResult.java 9210 2010-09-17 15:31:21Z deroset $";
	/**
	 * Unique serial UID
	 */
	private static final long serialVersionUID = 767189381874384539L;

	/**
	 * Given an amount and a currency this method will convert the amount to the required currency format.
	 * 
	 * @param sessionIdentifier
	 *            String - sessionIdentifier
	 * 
	 * @param currency
	 *            String - currency
	 * @param amount
	 *            String - amount
	 * 
	 * @return String - Formatted amount
	 */
	public String formatAmount(final String sessionIdentifier, final String currency, final String amount)
	{
		// Get the equation user to be able to retrieve the decimal and integer separator
		EquationUser eqUser = EquationCommonContext.getContext().getEquationUser(sessionIdentifier);
		String foreignDecimalSeparator = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.DECST);
		String foreignIntegerSeparator = eqUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.INTST);

		C8RecordDataModel currencyInfo;
		int decimal = 2;
		try
		{
			currencyInfo = eqUser.getEquationUnit().getRecords().getC8Record(currency);
			decimal = Integer.parseInt(currencyInfo.getEditField());
		}
		catch (EQException e)
		{
		}

		return EqDataType.formatEquationAmount(amount, Integer.parseInt(EqDataType.EDIT_AMOUNT_DEFAULT), decimal,
						foreignIntegerSeparator, foreignDecimalSeparator);
	}
}
