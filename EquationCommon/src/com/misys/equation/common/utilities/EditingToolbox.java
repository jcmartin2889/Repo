package com.misys.equation.common.utilities;

public class EditingToolbox
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EditingToolbox.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	/**
	 * Edit the account
	 * 
	 * @param accountBranch
	 *            - account branch
	 * @param accountNumber
	 *            - account number
	 * @param accountSuffix
	 *            - account suffix
	 * 
	 * @return the edited account
	 */
	public static String editAccount(String accountBranch, String accountNumber, String accountSuffix)
	{
		return accountBranch + "-" + accountNumber + "-" + accountSuffix;
	}

	/**
	 * Edit the reference
	 * 
	 * @param dealBranch
	 *            - deal branch
	 * @param dealType
	 *            - deal type
	 * @param dealRef
	 *            - deal reference
	 * 
	 * @return the edited account
	 */
	public static String editDealReference(String dealBranch, String dealType, String dealRef)
	{
		return dealBranch + " " + dealType + "-" + dealRef;
	}

}
