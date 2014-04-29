package com.misys.equation.common.search.results;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.misys.equation.common.search.criteria.DealSearchCriteria;

public class DealSearchResult extends DealSearchCriteria implements ISearchResult, ISelectedSearchResult
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DealSearchResult.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	private final String unit;
	private final String system;
	private final String customerDescription;
	private final long id;
	private final Date timeStamp;

	public DealSearchResult(final String branchMnemonic, final String dealType, final String dealReference, final String unit,
					final String system, final String customerDescription, final Date timeStamp)
	{
		super(branchMnemonic, dealType, dealReference);
		this.unit = unit;
		this.system = system;
		this.customerDescription = customerDescription;
		this.id = -1;
		this.timeStamp = timeStamp;
	}

	public DealSearchResult(final String branchMnemonic, final String dealType, final String dealReference, final String unit,
					final String system, final String customerDescription, final long id, final Date timeStamp)
	{
		super(branchMnemonic, dealType, dealReference);
		this.unit = unit;
		this.system = system;
		this.customerDescription = customerDescription;
		this.id = id;
		this.timeStamp = timeStamp;
	}

	public DealSearchResult(Map<PropertyType, String> properties)
	{
		super(properties.get(PropertyType.DL_BRH_MNM), properties.get(PropertyType.DEAL_TYPE), properties
						.get(PropertyType.DEAL_REF));
		this.unit = properties.get(PropertyType.UNIT);
		this.system = properties.get(PropertyType.UNIT);
		if (properties.get(PropertyType.ID) != null)
		{
			this.id = Long.parseLong(properties.get(PropertyType.ID));
		}
		else
		{
			this.id = -1;
		}
		this.customerDescription = "Nicholas Camilleri"; // TODO NC REMOVE
		this.timeStamp = new Date(Long.parseLong(properties.get(PropertyType.TIME_STAMP)));
	}

	public String getUnit()
	{
		return unit;
	}

	public String getSystem()
	{
		return system;
	}

	/**
	 * Get the customer description
	 * 
	 * @return
	 */
	public String getCustomerDescription()
	{
		return customerDescription;
	}

	public Date getTimeStamp()
	{
		return timeStamp;
	}

	/**
	 * This method returns a map representing the tuples to be inserted into the database as properties for the key data for the
	 * selected search result.
	 * 
	 * @return Map<String, String> - properties for the key data for the selected search result
	 */
	public Map<String, String> getKeyDataToBeRecorded()
	{
		final Map<String, String> map = new HashMap<String, String>();
		map.put(PropertyType.DL_BRH_MNM.toString(), getBranchMnemonic());
		map.put(PropertyType.DEAL_TYPE.toString(), getDealType());
		map.put(PropertyType.DEAL_REF.toString(), getDealReference());
		map.put(PropertyType.CUST_DES.toString(), getCustomerDescription());
		return map;
	}

	/**
	 * This method return the string to be displayed in the tree view.
	 * 
	 * @return String - String to display
	 */
	public String getSelectedResultKeyString()
	{
		final StringBuffer sb = new StringBuffer();
		final Date date = getTimeStamp();
		Locale locale = Locale.getDefault();
		DateFormat timeFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		sb.append(timeFormatter.format(date));
		sb.append(" - ");
		sb.append(getUnit());
		sb.append(" - ");
		sb.append(getBranchMnemonic());
		sb.append(" ");
		sb.append(getDealType());
		sb.append(" ");
		sb.append(getDealReference());
		sb.append(" ");
		sb.append(getCustomerDescription());
		return sb.toString().toUpperCase();
	}

	public long getId()
	{
		return id;
	}

	public int compareTo(ISelectedSearchResult comparable)
	{
		if (comparable.getTimeStamp().getTime() == getTimeStamp().getTime())
		{
			return 0;
		}
		else if (comparable.getTimeStamp().getTime() < getTimeStamp().getTime())
		{
			return -1;
		}
		else if (comparable.getTimeStamp().getTime() > getTimeStamp().getTime())
		{
			return 1;
		}
		return 0;
	}

	public String getSelectedResultLink()
	{
		final StringBuffer sb = new StringBuffer();
		sb.append("javascript:document.getElementById('UNIT').value='" + getUnit() + "';");
		sb.append("javascript:document.getElementById('SYSTEM').value='" + getSystem() + "';");
		sb.append("selectionMade('SearchDealByBranchNoTypeRef', '");
		sb.append(PropertyType.DEAL_TYPE);
		sb.append("=");
		sb.append(getDealType());
		sb.append(",");
		sb.append(PropertyType.UNIT);
		sb.append("=");
		sb.append(getUnit());
		sb.append(",");
		sb.append(PropertyType.DEAL_REF);
		sb.append("=");
		sb.append(getDealReference());
		sb.append(",");
		sb.append(PropertyType.DL_BRH_MNM);
		sb.append("=");
		sb.append(getBranchMnemonic());
		sb.append(",");
		sb.append(PropertyType.ID);
		sb.append("=");
		sb.append(getId());
		sb.append("');");
		return sb.toString();
	}

}
