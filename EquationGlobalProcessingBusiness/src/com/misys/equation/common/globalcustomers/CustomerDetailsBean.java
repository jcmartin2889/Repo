package com.misys.equation.common.globalcustomers;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;

public class CustomerDetailsBean extends GlobalBean
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CustomerDetailsBean.java 10600 2011-03-03 14:01:00Z WRIGHTP1 $";
	private String globalUserId;
	private String unit;
	private String system;
	private String customerNumber;

	private String customerMnemonic;
	private String customerLocation;
	private String customerName;

	private String masterFlag;
	private String customerType;

	private String parentCountry;
	private String residenceCountry;
	private String act;
	private String responsibilityCode;
	private String analysisCode;
	private String sundryAnalysisCode;

	public CustomerDetailsBean()
	{
		super();
	}

	public String getGlobalUserId()
	{
		return globalUserId;
	}
	public void setGlobalUserId(String globalUserId)
	{
		this.globalUserId = globalUserId;
	}
	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public String getCustomerMnemonic()
	{
		return customerMnemonic;
	}
	public void setCustomerMnemonic(String customerMnemonic)
	{
		this.customerMnemonic = customerMnemonic;
	}
	public String getCustomerLocation()
	{
		return customerLocation;
	}
	public void setCustomerLocation(String customerLocation)
	{
		this.customerLocation = customerLocation;
	}
	public String getCustomerName()
	{
		return customerName;
	}
	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}
	public String getMasterFlag()
	{
		return masterFlag;
	}
	public void setMasterFlag(String masterFlag)
	{
		this.masterFlag = masterFlag;
	}
	public String getCustomerType()
	{
		return customerType;
	}
	public void setCustomerType(String customerType)
	{
		this.customerType = customerType;
	}
	public String getParentCountry()
	{
		return parentCountry;
	}
	public void setParentCountry(String parentCountry)
	{
		this.parentCountry = parentCountry;
	}
	public String getResidenceCountry()
	{
		return residenceCountry;
	}
	public void setResidenceCountry(String residenceCountry)
	{
		this.residenceCountry = residenceCountry;
	}
	public String getAct()
	{
		return act;
	}
	public void setAct(String act)
	{
		this.act = act;
	}
	public String getResponsibilityCode()
	{
		return responsibilityCode;
	}
	public void setResponsibilityCode(String responsibilityCode)
	{
		this.responsibilityCode = responsibilityCode;
	}
	public String getAnalysisCode()
	{
		return analysisCode;
	}
	public void setAnalysisCode(String analysisCode)
	{
		this.analysisCode = analysisCode;
	}
	public String getSundryAnalysisCode()
	{
		return sundryAnalysisCode;
	}
	public void setSundryAnalysisCode(String sundryAnalysisCode)
	{
		this.sundryAnalysisCode = sundryAnalysisCode;
	}

	public String getCustomerNumber()
	{
		return customerNumber;
	}
	public void setCustomerNumber(String customerNumber)
	{
		this.customerNumber = customerNumber;
	}
	public String getSystem()
	{
		return system;
	}
	public void setSystem(String system)
	{
		this.system = system;
	}

	public String getID()
	{
		return new StringBuilder(system).append("-").append(unit).toString();
	}

	public static class CustomerDetailsBeanComparator implements Comparator<CustomerDetailsBean>, Serializable
	{
		/** Serial UID */
		private static final long serialVersionUID = 4442802857557514513L;

		public int compare(CustomerDetailsBean model1, CustomerDetailsBean model2)
		{
			Collator myDefaultCollator = Collator.getInstance();

			if (myDefaultCollator.compare(model1.getID(), model2.getID()) != -1)
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
	}
}
