package com.misys.equation.common.search.criteria;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.SCRecordDataModel;
import com.misys.equation.common.search.results.SearchType;

public class AccountSearchCriteria implements ISearchCriteria
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AccountSearchCriteria.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	private final String accountBranch;
	private final String accountNumber;
	private final String accountSuffix;
	private final String externalAccountNumber;
	private final String shortName;
	private final String accountType;
	private final String currency;

	public AccountSearchCriteria(final String accountBranch, final String accountNumber, final String accountSuffix,
					final String externalAccountNumber, final String shortName, final String accountType, final String currency)
	{
		super();

		if (accountBranch == null || accountBranch.equals("*"))
		{
			this.accountBranch = "";
		}
		else
		{
			this.accountBranch = accountBranch.trim();
		}

		if (accountNumber == null || accountNumber.equals("*"))
		{
			this.accountNumber = "";
		}
		else
		{
			this.accountNumber = accountNumber.trim();
		}

		if (accountSuffix == null || accountSuffix.equals("*"))
		{
			this.accountSuffix = "";
		}
		else
		{
			this.accountSuffix = accountSuffix.trim();
		}

		if (externalAccountNumber == null || externalAccountNumber.equals("*"))
		{
			this.externalAccountNumber = "";
		}
		else
		{
			this.externalAccountNumber = externalAccountNumber.trim();
		}

		if (shortName == null || shortName.equals("*"))
		{
			this.shortName = "";
		}
		else
		{
			this.shortName = shortName.trim();
		}

		if (accountType == null || accountType.equals("*"))
		{
			this.accountType = "";
		}
		else
		{
			this.accountType = accountType.trim();
		}

		if (currency == null || currency.equals("*"))
		{
			this.currency = "";
		}
		else
		{
			this.currency = currency.trim();
		}
	}

	public String getAccountBranch()
	{
		return accountBranch;
	}

	public String getAccountNumber()
	{
		return accountNumber;
	}

	public String getAccountSuffix()
	{
		return accountSuffix;
	}

	public String getExternalAccountNumber()
	{
		return externalAccountNumber;
	}
	public String getShortName()
	{
		return shortName;
	}
	public String getAccountType()
	{
		return accountType;
	}
	public String getCurrency()
	{
		return currency;
	}

	public SearchType getSearchType()
	{
		return SearchType.ACCOUNT;
	}

	public String getSearchString()
	{
		return "SCAB LIKE '%" + accountBranch + "%' AND SCAN LIKE '%" + accountNumber + "%' AND SCAS LIKE '%" + accountSuffix
						+ "%'";
	}

	public AbsRecord getDataModel()
	{
		return new SCRecordDataModel();
	}

	public IDao getRecordDao(EquationStandardSession session, DaoFactory daoFactory, AbsRecord record)
	{
		return daoFactory.getSCRecordDao(session, record);
	}

}
