package com.misys.equation.common.search.results;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.misys.equation.common.search.criteria.AccountSearchCriteria;

public class AccountSearchResult extends AccountSearchCriteria implements ISearchResult, ISelectedSearchResult
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AccountSearchResult.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	private final String unit;
	private final String system;
	private final long id;
	private final Date timeStamp;

	public AccountSearchResult(String accountBranch, String accountNumber, String accountSuffix, String externalAccountNumber,
					String shortName, String accountType, String currency, final String unit, final String system,
					final Date timeStamp)
	{
		super(accountBranch, accountNumber, accountSuffix, externalAccountNumber, shortName, accountType, currency);
		this.unit = unit;
		this.system = system;
		this.id = -1;
		this.timeStamp = timeStamp;
	}

	public AccountSearchResult(String accountBranch, String accountNumber, String accountSuffix, String externalAccountNumber,
					String shortName, String accountType, String currency, final String unit, final String system, final long id,
					final Date timeStamp)
	{
		super(accountBranch, accountNumber, accountSuffix, externalAccountNumber, shortName, accountType, currency);
		this.unit = unit;
		this.system = system;
		this.id = id;
		this.timeStamp = timeStamp;
	}

	public AccountSearchResult(Map<PropertyType, String> properties)
	{
		super(properties.get(PropertyType.BRANCH), properties.get(PropertyType.ACCNT_NO), properties.get(PropertyType.ACCNT_SUF),
						properties.get(PropertyType.EX_ACNTNO), properties.get(PropertyType.SHORT_NAME), "", "");
		this.unit = properties.get(PropertyType.UNIT);
		this.system = properties.get(PropertyType.SYSTEM);
		if (properties.get(PropertyType.ID) != null)
		{
			this.id = Long.parseLong(properties.get(PropertyType.ID));
		}
		else
		{
			this.id = -1;
		}
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

	public long getId()
	{
		return id;
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
		map.put(PropertyType.BRANCH.toString(), getAccountBranch());
		map.put(PropertyType.ACCNT_NO.toString(), getAccountNumber());
		map.put(PropertyType.ACCNT_SUF.toString(), getAccountSuffix());
		map.put(PropertyType.SHORT_NAME.toString(), getShortName());
		map.put(PropertyType.EX_ACNTNO.toString(), getExternalAccountNumber());
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
		sb.append(getAccountBranch());
		sb.append("-");
		sb.append(getAccountNumber());
		sb.append("-");
		sb.append(getAccountSuffix());
		sb.append(" - ");
		// If we have an external account number we can show it ...
		if (getExternalAccountNumber() != null && !getExternalAccountNumber().equals(""))
		{
			sb.append(getExternalAccountNumber());
			sb.append(" - ");
		}
		sb.append(getShortName());
		return sb.toString().toUpperCase();
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
		sb.append("document.getElementById('SYSTEM').value='" + getSystem() + "';");
		sb.append("selectionMade('SearchAccounts', '");
		sb.append(PropertyType.BRANCH);
		sb.append("=");
		sb.append(getAccountBranch());
		sb.append(",");
		sb.append(PropertyType.ACCNT_NO);
		sb.append("=");
		sb.append(getAccountNumber());
		sb.append(",");
		sb.append(PropertyType.UNIT);
		sb.append("=");
		sb.append(getUnit());
		sb.append(",");
		sb.append(PropertyType.ID);
		sb.append("=");
		sb.append(getId());
		sb.append(",");
		sb.append(PropertyType.ACCNT_SUF);
		sb.append("=");
		sb.append(getAccountSuffix());
		sb.append("');");
		return sb.toString();
	}
}
