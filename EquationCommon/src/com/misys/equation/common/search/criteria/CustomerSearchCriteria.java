package com.misys.equation.common.search.criteria;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GFRecordDataModel;
import com.misys.equation.common.search.results.SearchType;

public class CustomerSearchCriteria implements ISearchCriteria
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CustomerSearchCriteria.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	private String customerMnemonic; // GFCUS
	private String customerLocation; // GFCLC
	private String customerNumber; // GFCPNC

	public CustomerSearchCriteria(final String customerMnemonic, final String customerLocation, final String customerNumber)
	{
		super();

		if (customerMnemonic == null || customerMnemonic.equals("*"))
		{
			this.customerMnemonic = "";
		}
		else
		{
			this.customerMnemonic = customerMnemonic.trim();
		}

		if (customerLocation == null || customerLocation.equals("*"))
		{
			this.customerLocation = "";
		}
		else
		{
			this.customerLocation = customerLocation.trim();
		}

		if (customerNumber == null || customerNumber.equals("*"))
		{
			this.customerNumber = "";
		}
		else
		{
			this.customerNumber = customerNumber.trim();
		}
	}

	public String getCustomerMnemonic()
	{
		return customerMnemonic;
	}

	public String getCustomerLocation()
	{
		return customerLocation;
	}

	public String getCustomerNumber()
	{
		return customerNumber;
	}

	public SearchType getSearchType()
	{
		return SearchType.CUSTOMER;
	}

	public String getSearchString()
	{
		return "GFCUS LIKE '%" + customerMnemonic + "%' AND GFCPNC LIKE '%" + customerNumber + "%' AND GFCLC LIKE '%"
						+ customerLocation + "%'";
	}

	public AbsRecord getDataModel()
	{
		return new GFRecordDataModel();
	}

	public IDao getRecordDao(EquationStandardSession session, DaoFactory daoFactory, AbsRecord record)
	{
		return daoFactory.getGFRecordDao(session, record);
	}
}
