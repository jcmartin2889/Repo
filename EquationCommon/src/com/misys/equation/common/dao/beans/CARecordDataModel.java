package com.misys.equation.common.dao.beans;

import com.misys.equation.common.search.results.BranchSearchResult;
import com.misys.equation.common.search.results.ISearchResult;

/**
 * This is the data model representing the branches logical table (CA02LF).
 * 
 * @author camillen
 * 
 */
public class CARecordDataModel extends SearchResultDataModel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CARecordDataModel.java 6788 2010-03-30 16:39:35Z hempensp $";
	/**
	 * Unique serialID
	 */
	private static final long serialVersionUID = 5875245351012381324L;
	private static final String RECORD_NAME = "CA02LF";

	private String branchNumber; // CABBN
	private String branchName; // CABRN
	private String branchMnemonic; // CABRNM

	/**
	 * Default constructor
	 */
	public CARecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Parameterised constructor
	 * 
	 * @param branchNumber
	 *            - String
	 * @param branchName
	 *            - String
	 * @param branchMnemonic
	 *            - String
	 */
	public CARecordDataModel(String branchNumber, String branchName, String branchMnemonic)
	{
		this();
		this.branchNumber = branchNumber;
		this.branchName = branchName;
		this.branchMnemonic = branchMnemonic;
	}

	/**
	 * Getter method for branchNumber
	 * 
	 * @return branchNumber - String
	 */
	public String getBranchNumber()
	{
		return branchNumber;
	}

	/**
	 * Setter method for branchNumber
	 * 
	 * @return branchNumber - String
	 */
	public void setBranchNumber(String branchNumber)
	{
		this.branchNumber = branchNumber;
	}

	/**
	 * Getter method for branchName
	 * 
	 * @return branchName - String
	 */
	public String getBranchName()
	{
		return branchName;
	}

	/**
	 * Setter method for branchName
	 * 
	 * @return branchName - String
	 */
	public void setBranchName(String branchName)
	{
		this.branchName = branchName;
	}

	/**
	 * Getter method for branchMnemonic
	 * 
	 * @return branchMnemonic - String
	 */
	public String getBranchMnemonic()
	{
		return branchMnemonic;
	}

	/**
	 * Setter method for branchMnemonic
	 * 
	 * @return branchMnemonic - String
	 */
	public void setBranchMnemonic(String branchMnemonic)
	{
		this.branchMnemonic = branchMnemonic;
	}

	@Override
	public ISearchResult populateFromDataModel(AbsRecord dataModel, final String unit, final String system)
	{
		return new BranchSearchResult(branchNumber, branchName, branchMnemonic, unit, system, null);
	}

}
