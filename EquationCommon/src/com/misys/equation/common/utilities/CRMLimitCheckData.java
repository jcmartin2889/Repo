package com.misys.equation.common.utilities;

import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.datastructure.EqDS_DSHH03;

public class CRMLimitCheckData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CRMLimitCheckData.java 9962 2010-11-18 17:31:39Z MACDONP1 $";

	private EquationDataStructureData dshh03;
	private byte[] crmData;
	private String dsepms;

	/**
	 * Construct an CRM limit check data
	 */
	public CRMLimitCheckData(byte[] dshh03, byte[] crmData, String dsepms)
	{
		this.dshh03 = new EquationDataStructureData(new EqDS_DSHH03());
		this.dshh03.setBytes(dshh03);
		this.crmData = crmData;
		this.dsepms = dsepms;
	}

	/**
	 * Return the DSHH03 data
	 * 
	 * @return the DSHH03 data
	 */
	public EquationDataStructureData getDshh03()
	{
		return dshh03;
	}

	/**
	 * Set the DSHH03 data
	 * 
	 * @param dshh03
	 *            - the DSHH03 data
	 */
	public void setDshh03(EquationDataStructureData dshh03)
	{
		this.dshh03 = dshh03;
	}

	/**
	 * Return the CRM data
	 * 
	 * @return the CRM data
	 */
	public byte[] getCrmData()
	{
		return crmData;
	}

	/**
	 * Set the CRM data
	 * 
	 * @param crmData
	 *            - the CRM data
	 */
	public void setCrmData(byte[] crmData)
	{
		this.crmData = crmData;
	}

	/**
	 * Return the DSEPMS
	 * 
	 * @return the DSEPMS
	 */
	public String getDsepms()
	{
		return dsepms;
	}

	/**
	 * Set the DSEPMS
	 * 
	 * @param dsepms
	 *            - the DSEPMS
	 */
	public void setDsepms(String dsepms)
	{
		this.dsepms = dsepms;
	}

}
