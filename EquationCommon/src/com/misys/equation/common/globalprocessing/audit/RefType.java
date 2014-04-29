package com.misys.equation.common.globalprocessing.audit;

/**
 * The key data type for the record
 * 
 * @author deroset
 * 
 */
public enum RefType
{

	GLOBAL_CUST("GLOBALCUST"), CUST_NO("CUSTNO"), CUST_MNM("CUSTMNM"), BRANCH("BRANCH"), ACCNT_NO("ACCNTNO"), EX_ACNTNO("EXACNTNO"), PROP_RULEID(
					"PROPRULEID"), PROP_SETID("PROPSETID"), LINK_SYNC("LINKSYNC");

	private final String value;

	RefType(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

}
