package com.misys.equation.common.search.results;

import java.util.HashMap;
import java.util.Map;

import com.misys.equation.common.datastructure.EqDS_DSWSI2;
import com.misys.equation.common.datastructure.EqDS_DSWSID;

public enum PropertyType
{
	SYSTEM("SYSTEM"), UNIT("UNIT"), ID("ID"), SEARCH_TYPE("SEARCH_TYPE"), USER("USER"), TIME_STAMP("TIME_STAMP"), // Generic Keys
	BRANCH("BRANCH"), ACCNT_NO("ACCNT_NO"), EX_ACNTNO("EX_ACNTNO"), ACCNT_SUF("ACCNT_SUF"), SHORT_NAME("SHORT_NAME"), ACC_IBAN(
					"ACC_IBAN"), // Accounts
	// Keys
	CUST_MNEM("CUST_MNEM"), CUST_NO("CUST_NO"), CUST_FNAME("CUST_FNAME"), CUST_LOC("CUST_LOC"), // Customer Keys
	OPT_ID("OPT_ID"), OPT_TITLE("OPT_TITLE"), // Option Keys
	DL_BRH_MNM("DL_BRH_MNM"), DEAL_TYPE("DEAL_TYPE"), DEAL_REF("DEAL_REF"), CUST_DES("CUST_DES"), // Deal Keys
	BRANCH_NO("BRANCH_NO"), B_MNEM("B_MNEM"), B_DESC("B_DESC"); // Branch Keys

	private final String value;

	// Create a Map to contain the mappings between the PropertyTypes and the Data Area IDs
	public static final Map<PropertyType, String> mappings = new HashMap<PropertyType, String>();
	static
	{
		// Customer Mappings
		mappings.put(CUST_NO, EqDS_DSWSID.CBNO);
		mappings.put(CUST_MNEM, EqDS_DSWSID.CUS);
		mappings.put(CUST_LOC, EqDS_DSWSID.CLC);

		// Account Mappings
		mappings.put(BRANCH, EqDS_DSWSI2.AB);
		mappings.put(ACCNT_NO, EqDS_DSWSI2.AN);
		mappings.put(ACCNT_SUF, EqDS_DSWSI2.AS);
		mappings.put(EX_ACNTNO, EqDS_DSWSI2.EAN);
		mappings.put(ACC_IBAN, EqDS_DSWSI2.IBAN);

		// Deal Mappings
		mappings.put(DL_BRH_MNM, EqDS_DSWSID.CBBN);
		mappings.put(DEAL_TYPE, EqDS_DSWSID.DLP);
		mappings.put(DEAL_REF, EqDS_DSWSID.DLR);

		// Branch Mappings
		mappings.put(BRANCH_NO, EqDS_DSWSID.CBBN);
		mappings.put(B_MNEM, EqDS_DSWSID.BRNM);

	}

	private PropertyType(final String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}
}
