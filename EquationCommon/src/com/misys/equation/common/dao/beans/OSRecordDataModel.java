package com.misys.equation.common.dao.beans;

import com.misys.equation.common.search.results.DealSearchResult;
import com.misys.equation.common.search.results.ISearchResult;

/**
 * This is the data model representing the deals logical table (OS10LF).
 * 
 * @author camillen
 * 
 */
public class OSRecordDataModel extends SearchResultDataModel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: OSRecordDataModel.java 6788 2010-03-30 16:39:35Z hempensp $";
	/**
	 * Unique serialID
	 */
	private static final long serialVersionUID = -5351032836928373885L;

	private static final String RECORD_NAME = "OS10LF"; // Note that we base the DAO on the logical not Physical.

	private String branchMnemonic; // Branch Mnemonic (OSBRNM)
	private String dealType; // Deal Type (OSDLP)
	private String dealReference; // Deal Reference (OSDLR)

	/**
	 * Default constructor
	 */
	public OSRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 * Parameterised constructor
	 * 
	 * @param branchMnemonic
	 * @param dealType
	 * @param dealReference
	 */
	public OSRecordDataModel(final String branchMnemonic, final String dealType, final String dealReference)
	{
		this();
		this.branchMnemonic = branchMnemonic;
		this.dealType = dealType;
		this.dealReference = dealReference;
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
	 * @param branchMnemonic
	 *            - String
	 */
	public void setBranchMnemonic(String branchMnemonic)
	{
		this.branchMnemonic = branchMnemonic;
	}

	/**
	 * Getter method for dealType
	 * 
	 * @return dealType - String
	 */
	public String getDealType()
	{
		return dealType;
	}

	/**
	 * Setter method for dealType
	 * 
	 * @param dealType
	 *            - String
	 */
	public void setDealType(String dealType)
	{
		this.dealType = dealType;
	}

	/**
	 * Getter method for dealType
	 * 
	 * @return dealType - String
	 */
	public String getDealReference()
	{
		return dealReference;
	}

	/**
	 * Setter method for dealReference
	 * 
	 * @param dealReference
	 *            - String
	 */
	public void setDealReference(String dealReference)
	{
		this.dealReference = dealReference;
	}

	@Override
	public ISearchResult populateFromDataModel(AbsRecord dataModel, final String unit, final String system)
	{
		return new DealSearchResult(branchMnemonic, dealType, dealReference, unit, system, "CUSTOMER", null);
	}

}
