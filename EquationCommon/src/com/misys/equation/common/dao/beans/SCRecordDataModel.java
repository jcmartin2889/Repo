package com.misys.equation.common.dao.beans;

import com.misys.equation.common.search.results.AccountSearchResult;
import com.misys.equation.common.search.results.ISearchResult;

public class SCRecordDataModel extends SearchResultDataModel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SCRecordDataModel.java 6788 2010-03-30 16:39:35Z hempensp $";
	/**
	 * Unique serialID
	 */
	private static final long serialVersionUID = -9192881601662910724L;

	private static final String RECORD_NAME = "SC01LF"; // Note that we base the DAO on the logical not Physical.

	private String accountBranch; // SCAB
	private String basicNumber; // SCAN
	private String accountSuffix; // SCAS
	private String shortName; // SCSHN

	/**
	 * Parameterless Constructor
	 */
	public SCRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Constructor
	 * 
	 * @param accountBranch
	 * @param basicNumber
	 * @param accountSuffix
	 */
	public SCRecordDataModel(final String accountBranch, final String basicNumber, final String accountSuffix,
					final String shortName)
	{
		this();
		this.accountBranch = accountBranch;
		this.basicNumber = basicNumber;
		this.accountSuffix = accountSuffix;
		this.shortName = shortName;
	}

	/**************************************************************************
	 * Getters & Setters for Data Model Properties
	 ***************************************************************************/

	/*
	 * @return
	 */
	public String getAccountBranch()
	{
		return accountBranch;
	}
	public void setAccountBranch(final String accountBranch)
	{
		this.accountBranch = accountBranch;
	}

	public String getBasicNumber()
	{
		return basicNumber;
	}
	public void setBasicNumber(final String basicNumber)
	{
		this.basicNumber = basicNumber;
	}

	public String getAccountSuffix()
	{
		return accountSuffix;
	}
	public void setAccountSuffix(final String accountSuffix)
	{
		this.accountSuffix = accountSuffix;
	}

	public String getShortName()
	{
		return shortName;
	}

	public void setShortName(String shortName)
	{
		this.shortName = shortName;
	}

	@Override
	public ISearchResult populateFromDataModel(AbsRecord dataModel, final String unit, final String system)
	{
		return new AccountSearchResult(accountBranch, basicNumber, accountSuffix, "", shortName, "", "", unit, system, null);
	}

}
