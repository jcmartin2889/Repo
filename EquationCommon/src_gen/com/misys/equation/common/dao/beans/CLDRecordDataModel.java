package com.misys.equation.common.dao.beans;

/**
 * CLD Record data-model.
 * 
 * @author deroset
 */
public class CLDRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CLDRecordDataModel.java 11001 2011-05-19 14:03:00Z MACDONP1 $";
	private final static String RECORD_NAME = "CLD10LF";

	private String globalCustomerId;
	private int sequenceNumber;
	private String systemName;
	private String customerOwningUnit;
	private String customerNumber;
	private boolean masterFlag;
	private String syncID;

	private String custMnemonic;
	private String custName;
	private String custLocation;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1275045708045l;

	/**
	 * Default constructor
	 */
	public CLDRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
		custMnemonic = "";
		custName = "";
		custLocation = "";
	}

	public CLDRecordDataModel(String globalCustomerId, int sequenceNumber)
	{
		this.globalCustomerId = globalCustomerId;
		this.sequenceNumber = sequenceNumber;
		setEqFileName(RECORD_NAME);
	}

	public CLDRecordDataModel(final String globalCustomerId, final int sequenceNumber, final String systemName,
					final String customerOwningUnit, final String customerNumber, final boolean masterFlag, final String syncID)
	{
		this();
		this.globalCustomerId = globalCustomerId;
		this.sequenceNumber = sequenceNumber;
		this.systemName = systemName;
		this.customerOwningUnit = customerOwningUnit;
		this.customerNumber = customerNumber;
		this.masterFlag = masterFlag;
		this.syncID = syncID;
	}

	// ---getters and setters----//

	public String getGlobalCustomerId()
	{
		return this.globalCustomerId.toUpperCase();
	}
	public void setGlobalCustomerId(String parameter)
	{
		this.globalCustomerId = parameter.toUpperCase();
	}
	public int getSequenceNumber()
	{
		return this.sequenceNumber;
	}
	public void setSequenceNumber(int parameter)
	{
		this.sequenceNumber = parameter;
	}
	public String getSystemName()
	{
		return this.systemName;
	}
	public void setSystemName(String parameter)
	{
		this.systemName = parameter;
	}
	public String getCustomerOwningUnit()
	{
		return this.customerOwningUnit;
	}
	public void setCustomerOwningUnit(String parameter)
	{
		this.customerOwningUnit = parameter;
	}
	public String getCustomerNumber()
	{
		return this.customerNumber;
	}
	public void setCustomerNumber(String parameter)
	{
		this.customerNumber = parameter;
	}
	public boolean isMasterFlag()
	{
		return this.masterFlag;
	}
	public void setMasterFlag(boolean parameter)
	{
		this.masterFlag = parameter;
	}
	public String getSyncID()
	{
		return this.syncID;
	}
	public void setSyncID(String parameter)
	{
		this.syncID = parameter;
	}

	public String getCustMnemonic()
	{
		return custMnemonic;
	}

	public void setCustMnemonic(String custMnemonic)
	{
		this.custMnemonic = custMnemonic;
	}

	public String getCustName()
	{
		return custName;
	}

	public void setCustName(String custName)
	{
		this.custName = custName;
	}

	public String getCustLocation()
	{
		return custLocation;
	}

	public void setCustLocation(String custLocation)
	{
		this.custLocation = custLocation;
	}

	public void setFlag(final String propertyId, final boolean propertyValue) throws Exception
	{
		if (propertyId.equals("isMasterFlag"))
		{
			setMasterFlag(propertyValue);
		}
		else
		{
			throw new Exception("Invalid value for property id: " + propertyId);
		}
	}

	public int compareTo(Object arg)
	{
		if (arg instanceof CLDRecordDataModel)
		{
			CLDRecordDataModel cld = (CLDRecordDataModel) arg;
			if (sequenceNumber == cld.getSequenceNumber())
			{
				return 0;
			}
			else if (sequenceNumber > cld.getSequenceNumber())
			{
				return 1;

			}
			else
			{
				return -1;
			}
		}
		else
		{
			return -1;
		}
	}
}
