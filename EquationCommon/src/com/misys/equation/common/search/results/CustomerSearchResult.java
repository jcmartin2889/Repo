package com.misys.equation.common.search.results;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.misys.equation.common.search.criteria.CustomerSearchCriteria;

public class CustomerSearchResult extends CustomerSearchCriteria implements ISearchResult, ISelectedSearchResult
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CustomerSearchResult.java 9798 2010-11-11 10:06:18Z MACDONP1 $";
	private final String unit;
	private final String system;
	private final String fullname;
	private final long id;
	private final Timestamp timeStamp;

	public CustomerSearchResult(final String customerMnemonic, final String customerLocation, final String customerNumber,
					final String fullname, final String unit, final String system, final Timestamp timeStamp)
	{
		super(customerMnemonic, customerLocation, customerNumber);
		this.unit = unit;
		this.system = system;
		this.fullname = fullname;
		this.id = -1;
		this.timeStamp = timeStamp;
	}

	public CustomerSearchResult(final String customerMnemonic, final String customerLocation, final String customerNumber,
					final String fullname, final String unit, final String system, final long id, final Timestamp timeStamp)
	{
		super(customerMnemonic, customerLocation, customerNumber);
		this.unit = unit;
		this.system = system;
		this.fullname = fullname;
		this.id = id;
		this.timeStamp = timeStamp;
	}

	public CustomerSearchResult(Map<PropertyType, String> properties)
	{
		super(properties.get(PropertyType.CUST_MNEM), properties.get(PropertyType.CUST_LOC), properties.get(PropertyType.CUST_NO));
		this.unit = properties.get(PropertyType.UNIT);
		this.system = properties.get(PropertyType.SYSTEM);
		this.fullname = properties.get(PropertyType.CUST_FNAME);
		this.timeStamp = new Timestamp(Long.valueOf(properties.get(PropertyType.TIME_STAMP)));
		if (properties.get(PropertyType.ID) != null)
		{
			this.id = Long.parseLong(properties.get(PropertyType.ID));
		}
		else
		{
			this.id = -1;
		}
	}

	public String getUnit()
	{
		return unit;
	}

	public String getSystem()
	{
		return system;
	}

	public String getFullname()
	{
		return fullname;
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
		map.put(PropertyType.CUST_NO.toString(), getCustomerNumber());
		map.put(PropertyType.CUST_FNAME.toString(), getFullname());
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
		sb.append(getCustomerNumber());
		sb.append(" ");
		sb.append(getFullname());
		return sb.toString().toUpperCase();
	}

	public String getSelectedResultLink()
	{
		final StringBuffer sb = new StringBuffer();
		sb.append("javascript:document.getElementById('UNIT').value='" + getUnit() + "';");
		sb.append("javascript:document.getElementById('SYSTEM').value='" + getSystem() + "';");
		sb.append("selectionMade('SearchCustomersDiv', '");
		sb.append(PropertyType.CUST_FNAME);
		sb.append("=");
		sb.append(this.fullname);
		sb.append(",");
		sb.append(PropertyType.CUST_LOC);
		sb.append("=");
		sb.append(getCustomerLocation());
		sb.append(",");
		sb.append(PropertyType.UNIT);
		sb.append("=");
		sb.append(getUnit());
		sb.append(",");
		sb.append(PropertyType.ID);
		sb.append("=");
		sb.append(getId());
		sb.append(",");
		sb.append(PropertyType.CUST_NO);
		sb.append("=");
		sb.append(getCustomerNumber());
		sb.append("');");
		return sb.toString();
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
}