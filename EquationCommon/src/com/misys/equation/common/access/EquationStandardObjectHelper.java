package com.misys.equation.common.access;

import com.misys.equation.common.datastructure.EqDS_Report;
import com.misys.equation.common.internal.eapi.core.EQException;

public class EquationStandardObjectHelper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardObjectHelper.java 9981 2010-11-19 15:01:37Z MACDONP1 $";
	public final static String API_PGM_SFX = "RR";
	public final static String ENQ_PGM_SFX = "ER";

	/**
	 * Create and return the Equation transaction of the specified type
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param type
	 *            - the transaction type
	 * @param id
	 *            - the transaction id
	 * @param pgm
	 *            - the program / label name
	 * @param decode
	 *            a <code>String</code> containing the PV Decode value (PV specific)
	 * @param newField
	 *            - for PV, if Y, then the key must not be existing<Br>
	 *            if N, then the key must be existing<Br>
	 *            if blank, then either existing or not
	 * 
	 * @return the Equation transaction
	 * 
	 * @throws EQException
	 */
	public static IEquationStandardObject getTransaction(EquationStandardSession session, String type, String id, String pgm,
					String root, String keys, String decode, String newField) throws EQException
	{
		IEquationStandardObject equationStandardObject = null;

		// enquiry
		if (type.equals(IEquationStandardObject.TYPE_ENQUIRY))
		{
			String program = root + ENQ_PGM_SFX;
			equationStandardObject = new EquationStandardEnquiry(program, session);
		}

		// list enquiry
		else if (type.equals(IEquationStandardObject.TYPE_LIST_ENQUIRY))
		{
			String program = root + ENQ_PGM_SFX;
			equationStandardObject = new EquationStandardListEnquiry(program, session);
		}

		// table
		else if (type.equals(IEquationStandardObject.TYPE_TABLE))
		{
			String tableId = root;
			String indexId = pgm;

			// this is to solve issue of adding a physical file instead of a logical file
			if (tableId.trim().length() == 0)
			{
				tableId = indexId;
			}
			equationStandardObject = new EquationStandardTable(tableId, indexId, keys, session);
		}

		// list table
		else if (type.equals(IEquationStandardObject.TYPE_LIST_TABLE))
		{
			String tableId = root;
			String indexId = pgm;

			// this is to solve issue of adding a physical file instead of a logical file
			if (tableId.trim().length() == 0)
			{
				tableId = indexId;
			}
			equationStandardObject = new EquationStandardListTable(tableId, indexId, keys, session);
		}

		// PV created via PV editor
		else if (type.equals(IEquationStandardObject.TYPE_PVEDITOR))
		{
			equationStandardObject = new EquationPVEditorValidation(id, decode, newField, session);
		}

		// list query
		else if (type.equals(IEquationStandardObject.TYPE_LIST_QUERY))
		{
			String sql = root;
			equationStandardObject = new EquationStandardQueryList(sql, session);
		}

		// query
		else if (type.equals(IEquationStandardObject.TYPE_SQL_QUERY))
		{
			String sql = root;
			equationStandardObject = new EquationStandardQuery(sql, session);
		}

		// prompt and validate module
		else if (type.equals(IEquationStandardObject.TYPE_PV))
		{
			EquationPVData equationPVData = new EquationPVData(session.getUnit().getPVMetaData(pgm), session.getCcsid());
			equationStandardObject = new EquationStandardValidation(decode, pgm, equationPVData, "N", newField);
		}

		// report
		else if (type.equals(IEquationStandardObject.TYPE_REPORT))
		{
			EquationStandardReport equationStandardReport = new EquationStandardReport(pgm);
			equationStandardReport.setFieldValue(EqDS_Report.RPT, root);
			equationStandardReport.setFieldValue(EqDS_Report.SEL, keys);
			equationStandardObject = equationStandardReport;
		}

		// input transaction
		else
		{
			String program = root + API_PGM_SFX + pgm;
			equationStandardObject = new EquationStandardTransaction(program, session);
		}

		// set the id
		equationStandardObject.setId(id);

		return equationStandardObject;
	}

	/**
	 * Execute the transaction
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param equationStandardObject
	 *            - the transaction
	 * @param fct
	 *            - the transaction mode
	 * @param limitBytes
	 *            - the maximum bytes to be retrieved from a list API
	 * 
	 * @return the executed transaction
	 * 
	 * @throws EQException
	 */
	public static IEquationStandardObject applyTransaction(EquationStandardSession session,
					IEquationStandardObject equationStandardObject, long limitBytes) throws EQException
	{
		// function mode
		String fct = equationStandardObject.getMode();

		// Equation standard list enquiry?
		if (equationStandardObject instanceof EquationStandardListEnquiry)
		{
			session.executeListEnquiry((EquationStandardListEnquiry) equationStandardObject, limitBytes);
			return equationStandardObject;
		}

		// Equation standard enquiry?
		if (equationStandardObject instanceof EquationStandardEnquiry)
		{
			session.executeEnquiry((EquationStandardEnquiry) equationStandardObject);
			return equationStandardObject;
		}

		// Equation standard transaction?
		if (equationStandardObject instanceof EquationStandardTransaction)
		{
			if (fct.equals(IEquationStandardObject.FCT_VAL))
			{
				session.validateEquationTransaction((EquationStandardTransaction) equationStandardObject);
			}
			else if (fct.equals(IEquationStandardObject.FCT_RTV))
			{
				session.retrieveEquationTransaction((EquationStandardTransaction) equationStandardObject);
			}
			else
			{
				session.applyEquationTransaction((EquationStandardTransaction) equationStandardObject);
			}
			return equationStandardObject;
		}

		// Equation standard table as a list?
		if (equationStandardObject instanceof EquationStandardListTable)
		{
			// Validate not currently supported
			if (fct.equals(IEquationStandardObject.FCT_RTV))
			{
				session.retrieveEquationListTable((EquationStandardListTable) equationStandardObject, limitBytes);
			}
			// Update not currently supported
			return equationStandardObject;
		}

		// Equation PV editor validation?
		if (equationStandardObject instanceof EquationPVEditorValidation)
		{
			session.executeEquationPVEditorValidation((EquationPVEditorValidation) equationStandardObject);
			return equationStandardObject;
		}

		// Equation standard table?
		if (equationStandardObject instanceof EquationStandardTable)
		{
			if (fct.equals(IEquationStandardObject.FCT_VAL))
			{
				session.validateEquationTable((EquationStandardTable) equationStandardObject);
			}
			else if (fct.equals(IEquationStandardObject.FCT_RTV))
			{
				session.retrieveEquationTable((EquationStandardTable) equationStandardObject);
			}
			else
			{
				session.applyEquationTable((EquationStandardTable) equationStandardObject);
			}
			return equationStandardObject;
		}

		// SQL list query?
		if (equationStandardObject instanceof EquationStandardQueryList)
		{
			// SQL Query only supports retrieve
			if (fct.equals(IEquationStandardObject.FCT_RTV))
			{
				session.retrieveEquationQueryList((EquationStandardQueryList) equationStandardObject, limitBytes);
			}
			return equationStandardObject;
		}

		// SQL query?
		if (equationStandardObject instanceof EquationStandardQuery)
		{
			// SQL Query only supports retrieve
			if (fct.equals(IEquationStandardObject.FCT_RTV))
			{
				session.retrieveEquationQuery((EquationStandardQuery) equationStandardObject);
			}
			return equationStandardObject;
		}

		// Equation standard validate
		if (equationStandardObject instanceof EquationStandardValidation)
		{
			EquationStandardValidation equationStandardValidation = (EquationStandardValidation) equationStandardObject;
			equationStandardValidation.setDataInput(equationStandardValidation.getEquationPVData().parseFieldsIntoDSCCN("N"));
			session.executeValidate(equationStandardValidation);
			return equationStandardObject;
		}

		// Equation standard report
		if (equationStandardObject instanceof EquationStandardReport)
		{
			EquationStandardReport equationStandardReport = (EquationStandardReport) equationStandardObject;
			session.executeReport(equationStandardReport);
			return equationStandardObject;
		}

		return equationStandardObject;
	}

	/**
	 * Determine whether the mode is a valid mode during load processing (add, maintain, delete, enquiry)
	 * 
	 * @param fct
	 *            - the function mode to test
	 * 
	 * @return true if this is a valid mode
	 */
	public static boolean isValidModeOnLoad(String fct)
	{
		if (fct.equals(IEquationStandardObject.FCT_ADD) || fct.equals(IEquationStandardObject.FCT_DEL)
						|| fct.equals(IEquationStandardObject.FCT_MNT) || fct.equals(IEquationStandardObject.FCT_ENQ))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determine whether the mode is a valid mode during updateprocessing (add, maintain, delete)
	 * 
	 * @param fct
	 *            - the function mode to test
	 * 
	 * @return true if this is a valid mode
	 */
	public static boolean isValidModeOnUpdate(String fct)
	{
		if (fct.equals(IEquationStandardObject.FCT_ADD) || fct.equals(IEquationStandardObject.FCT_DEL)
						|| fct.equals(IEquationStandardObject.FCT_MNT))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
