package com.misys.equation.common.access;

import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.SQLToolbox;

/**
 * This is for running PV created via the PV editor. As of now, this is treated as if it is a database table
 */
public class EquationPVEditorValidation extends EquationStandardQuery
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPVEditorValidation.java 9981 2010-11-19 15:01:37Z MACDONP1 $";

	private static final long serialVersionUID = 1L;

	private String pvId;
	private String decode;
	private String newField;

	/**
	 * Construct a standard transaction with default journal name
	 * 
	 * @param pvId
	 *            - the PV id
	 * @param newField
	 *            - determine whether key must exists or not<br>
	 *            if Y, then the key must not be existing<Br>
	 *            if N, then the key must be existing<Br>
	 *            if blank, then either existing or not
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * 
	 * @throws EQException
	 */
	public EquationPVEditorValidation(String pvId, String decode, String newField, EquationStandardSession equationStandardSession)
					throws EQException
	{
		super();
		EquationPVMetaData pvMetaData = equationStandardSession.getUnit().getPVMetaData(pvId);
		EquationPVDecodeMetaData decodeMetaData = pvMetaData.getDecodeMetaData(decode);

		String sqlFrom = null;
		if (pvMetaData.isGlobalLibraryPrompt())
		{
			sqlFrom = EquationCommonContext.getConfigProperty("eq.GlobalLibraryName") + "/" + decodeMetaData.getSqlFrom();
		}
		else
		{
			sqlFrom = decodeMetaData.getSqlFrom();
		}
		String sql = SQLToolbox.constructSQLFromWhere(sqlFrom, decodeMetaData.getSqlWhere());
		initialiseQueryData(sql, equationStandardSession);

		this.pvId = pvId;
		this.decode = decode;
		this.newField = newField;
		setKeys(decodeMetaData.getKeyFields());
	}

	/**
	 * Return the PV id
	 * 
	 * @return the PV id
	 */
	public String getPvId()
	{
		return pvId;
	}

	/**
	 * Set the PV id
	 * 
	 * @param pvId
	 *            - the PV id
	 */
	public void setPvId(String pvId)
	{
		this.pvId = pvId;
	}

	/**
	 * Return the decode
	 * 
	 * @return the decode
	 */
	public String getDecode()
	{
		return decode;
	}

	/**
	 * Set the decode
	 * 
	 * @param decode
	 *            - the decode
	 */
	public void setDecode(String decode)
	{
		this.decode = decode;
	}

	/**
	 * Return the new field flag
	 * 
	 * @return the new field flag
	 */
	public String getNewField()
	{
		return newField;
	}

	/**
	 * Set the new field flag
	 * 
	 * @param newField
	 *            - the new field flag
	 */
	public void setNewField(String newField)
	{
		this.newField = newField;
	}
}