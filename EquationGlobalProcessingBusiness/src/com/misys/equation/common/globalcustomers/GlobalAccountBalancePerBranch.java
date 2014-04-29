package com.misys.equation.common.globalcustomers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GlobalAccountBalancePerBranch
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalAccountBalancePerBranch.java 10410 2011-02-01 14:32:24Z MACDONP1 $";
	private final List<GlobalAccountBalanceBean> globalAccountBalances;
	private final String branchName;

	public GlobalAccountBalancePerBranch(String branchName)
	{
		this.branchName = branchName;
		globalAccountBalances = new ArrayList<GlobalAccountBalanceBean>();
	}

	public void addGlobalAccountBalance(GlobalAccountBalanceBean globalAccountBalanceBean)
	{
		globalAccountBalances.add(globalAccountBalanceBean);
	}

	public List<GlobalAccountBalanceBean> getGlobalAccountBalances()
	{
		return globalAccountBalances;
	}

	public String getBranchName()
	{
		return branchName;
	}

	/**
	 * This method will sort all grouped global account balances by branch.
	 */
	public void sortGlobalAccountBalances()
	{
		Collections.sort(globalAccountBalances, new GlobalAccountBalanceBean.GlobalAccountBalanceBeanComparator());
	}
}
