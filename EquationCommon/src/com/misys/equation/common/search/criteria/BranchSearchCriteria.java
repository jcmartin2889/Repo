package com.misys.equation.common.search.criteria;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CARecordDataModel;
import com.misys.equation.common.search.results.SearchType;

public class BranchSearchCriteria implements ISearchCriteria
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: BranchSearchCriteria.java 10607 2011-03-07 17:32:43Z MACDONP1 $";
	private final String branchNumber;
	private final String branchName;
	private final String branchMnemonic;

	public BranchSearchCriteria(final String branchNumber, final String branchName, final String branchMnemonic)
	{
		super();

		if (branchNumber == null || branchNumber.equals("*"))
		{
			this.branchNumber = "";
		}
		else
		{
			this.branchNumber = branchNumber.trim();
		}

		if (branchName == null || branchName.equals("*"))
		{
			this.branchName = "";
		}
		else
		{
			this.branchName = branchName.trim();
		}

		if (branchMnemonic == null || branchMnemonic.equals("*"))
		{
			this.branchMnemonic = "";
		}
		else
		{
			this.branchMnemonic = branchMnemonic.trim();
		}

	}

	public String getBranchNumber()
	{
		return branchNumber;
	}

	public String getBranchName()
	{
		return branchName;
	}

	public String getBranchMnemonic()
	{
		return branchMnemonic;
	}

	public AbsRecord getDataModel()
	{
		return new CARecordDataModel();
	}

	public String getSearchString()
	{
		return "CABBN LIKE '%" + branchNumber + "%' AND CABRN LIKE '%" + branchName + "%' AND CABRNM LIKE '%" + branchMnemonic
						+ "%'";
	}

	public SearchType getSearchType()
	{
		return SearchType.BRANCH;
	}

	public IDao getRecordDao(EquationStandardSession session, DaoFactory daoFactory, AbsRecord record)
	{
		return daoFactory.getCARecordDao(session, record);
	}
}
