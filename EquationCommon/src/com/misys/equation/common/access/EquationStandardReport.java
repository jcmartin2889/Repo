package com.misys.equation.common.access;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.datastructure.EqDS_Report;
import com.misys.equation.common.internal.eapi.core.EQMessage;

public class EquationStandardReport implements IEquationStandardObject
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardReport.java 8910 2010-08-26 15:25:20Z MACDONP1 $";

	private String id;
	private final EquationDataStructureData eqDsDta;
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a new Equation Standard Report
	 * 
	 * @param report
	 *            - the report request
	 * @param selection
	 *            - the report selection
	 */
	public EquationStandardReport(String report)
	{
		this.id = report;

		// great the data structure
		eqDsDta = new EquationDataStructureData(new EqDS_Report());
		eqDsDta.setFieldValue(EqDS_Report.RPT, report);
	}

	/**
	 * Return the id
	 * 
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the id
	 * 
	 * @param id
	 *            - the id
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Return the report
	 * 
	 * @return the report
	 */
	public String getReport()
	{
		return eqDsDta.getFieldValue(EqDS_Report.RPT);
	}

	/**
	 * Return the report selection
	 * 
	 * @return the report selection
	 */
	public String getSelection()
	{
		return eqDsDta.getFieldValue(EqDS_Report.SEL);
	}

	/**
	 * This is not supported
	 * 
	 * @return null
	 */
	public byte[] getBytes()
	{
		return null;
	}

	/**
	 * This is not supported
	 * 
	 * @param data
	 *            - the data bytes
	 */
	public void setBytes(byte[] data)
	{
		// not supported
	}

	/**
	 * Return error messages
	 * 
	 * @return the error messages
	 */
	public List<EQMessage> getMessages()
	{
		return new ArrayList<EQMessage>();
	}

	/**
	 * Return the transaction mode
	 * 
	 * @return the transaction mode
	 */
	public String getMode()
	{
		return IEquationStandardObject.FCT_RPT;
	}

	/**
	 * Set the transaction mode
	 * 
	 * @param mode
	 *            - the transaction mode
	 */
	public void setMode(String mode)
	{
		// do nothing
	}

	/**
	 * Determine if there are any errors
	 * 
	 * @return true (assume always true)
	 */
	public boolean getValid()
	{
		return true;
	}

	/**
	 * Set the transaction valid
	 * 
	 * @param valid
	 *            - true if transaction is valid
	 */
	public void setValid(boolean valid)
	{
	}

	/**
	 * Return the field value
	 * 
	 * @return the field value
	 */
	public String getFieldValue(String fieldName)
	{
		return eqDsDta.getFieldValue(fieldName);
	}

	/**
	 * Set the field value
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param fieldValue
	 *            - the field value
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		eqDsDta.setFieldValue(fieldName, fieldValue);
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		return id + "\n" + eqDsDta.toString();
	}

}
