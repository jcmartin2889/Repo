package com.misys.equation.common.additionalinfo;

import java.util.Map;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IBFRecordDao;
import com.misys.equation.common.dao.IBTRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.BFRecordDataModel;
import com.misys.equation.common.dao.beans.BTRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;

public class EquationAdditionalInfoToolbox
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAdditionalInfoToolbox.java 14832 2012-11-05 19:03:33Z williae1 $";

	private static final int XF_COLLATERAL_REFERENCE = 1;
	private static final int XF_COLLATERAL_DEAL = 2;
	private static final int XF_COLLATERAL_ACCOUNT = 3;
	private static final int XF_CUSTOMER = 1;
	private static final int XF_ACCOUNT = 1;
	private static final int XF_DEAL = 1;
	private static final int XF_BRANCH = 1;
	private static final int XF_TRANSACTION = 1;

	/**
	 * Retrieve the information group
	 * 
	 * @param session
	 *            - the session
	 * @param parameterFile
	 *            - the parameter file
	 * @param parameterValue
	 *            - the parameter value
	 * 
	 * @return the information group
	 */
	public static String retrieveGroup(EquationStandardSession session, String parameterFile, String parameterValue)
	{
		// Read the BT file
		DaoFactory daoFactory = new DaoFactory();
		BTRecordDataModel bTRecordDataModel = new BTRecordDataModel(parameterFile, parameterValue);
		IBTRecordDao dao = daoFactory.getBTDao(session, bTRecordDataModel);
		bTRecordDataModel = dao.getBTRecord();

		// no record found, then try to retrieve the default group
		if (bTRecordDataModel == null)
		{
			bTRecordDataModel = new BTRecordDataModel(parameterFile, BTRecordDataModel.DEFAULT_GROUP);
			dao = daoFactory.getBTDao(session, bTRecordDataModel);
			bTRecordDataModel = dao.getBTRecord();
		}

		// no record found?
		if (bTRecordDataModel == null)
		{
			return null;
		}

		// return the information group
		else
		{
			return bTRecordDataModel.getInformationGroup();
		}
	}

	/**
	 * Return a (cached) Map of Equation BDPF (currency) records, keyed by currency mnemonic
	 * 
	 * @return a (cached) Map of Equation BDPF (currency) records, keyed by currency mnemonic
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, BFRecordDataModel> getBFRecords(EquationStandardSession session, String informationGroup)
	{
		BFRecordDataModel bfRecord = new BFRecordDataModel();
		DaoFactory daoFactory = new DaoFactory();
		IBFRecordDao dao = daoFactory.getBFDao(session, bfRecord);
		Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("BFCIGR='" + informationGroup + "'");
		Map<String, BFRecordDataModel> bfRecords = (Map<String, BFRecordDataModel>) list;
		return bfRecords;
	}

	/**
	 * Determine if the nth item is valid
	 * 
	 * @param equationUnit
	 *            - the Equation unit
	 * @param parameterFile
	 *            - the parameter file
	 * @param parameterValue
	 *            - the parameter value
	 * @param nItem
	 *            - the nth item
	 * 
	 * @return true if the item at the specified index is valid
	 * 
	 * @throws EQException
	 */
	public static boolean isItemAtIndexValid(EquationUnit equationUnit, String parameterFile, String parameterValue, int nItem)
	{
		try
		{
			EquationAdditionalInfoGroup infoGroup = equationUnit.getRecords().getEquationAdditionalInfoGroup(parameterFile,
							parameterValue);
			return nItem <= infoGroup.itemCount();
		}
		catch (EQException e)
		{
			return false;
		}
	}

	/**
	 * Retrieve the nth item name
	 * 
	 * @param equationUnit
	 *            - the Equation unit
	 * @param parameterFile
	 *            - the parameter file
	 * @param parameterValue
	 *            - the parameter value
	 * @param nItem
	 *            - the nth item
	 * 
	 * @return the item name at the specified index
	 */
	public static String getItemNameAtIndex(EquationUnit equationUnit, String parameterFile, String parameterValue, int nItem)
	{
		try
		{
			EquationAdditionalInfoGroup infoGroup = equationUnit.getRecords().getEquationAdditionalInfoGroup(parameterFile,
							parameterValue);
			return infoGroup.getItemName(nItem - 1);
		}
		catch (EQException e)
		{
			return "";
		}
	}

	/**
	 * Retrieve link parameter for a collateral reference
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param collateral
	 *            - the collateral reference
	 * @return
	 */
	private static String getCollateralReferenceLinkParameter(EquationStandardSession session, String collateral)
					throws EQException
	{
		return callXFV10R(session, BTRecordDataModel.INFO_COLLATERAL, XF_COLLATERAL_REFERENCE, collateral, "", "", "");
	}

	/**
	 * Retrieve link parameter for a customer
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param customer
	 *            - the customer mnemonic
	 * @param customerLoation
	 *            - the customer location
	 * @return
	 */
	private static String getCustomerLinkParameter(EquationStandardSession session, String customer, String customerLocation)
					throws EQException
	{
		return callXFV10R(session, BTRecordDataModel.INFO_CUSTOMER, XF_CUSTOMER, customer, customerLocation, "", "");
	}

	/**
	 * Call XFV10R
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param equationFile
	 *            - the Equation file
	 * @param sequenceNumber
	 *            - the sequence number
	 * @param key1
	 *            - key 1
	 * @param key2
	 *            - key 2
	 * @param key3
	 *            - key 3
	 * @param key4
	 *            - key 4
	 * 
	 * @return the link parameter
	 */
	private static String callXFV10R(EquationStandardSession session, String equationFile, int sequenceNumber, String key1,
					String key2, String key3, String key4) throws EQException
	{
		StringBuilder dsccn = new StringBuilder();
		dsccn.append(Toolbox.pad(equationFile, 2));
		dsccn.append(Toolbox.padAtFront(String.valueOf(sequenceNumber), "0", 3));
		dsccn.append(Toolbox.pad(key1, 35));
		dsccn.append(Toolbox.pad(key2, 35));
		dsccn.append(Toolbox.pad(key3, 35));
		dsccn.append(Toolbox.pad(key4, 35));
		EquationStandardValidation validation = new EquationStandardValidation("", "XFV10RC", dsccn.toString(), "N", "N");

		session.executeValidate(validation);

		String dataOutput = validation.getDataOutput().trim();
		if (dataOutput.length() > 86)
		{
			return dataOutput.substring(85, 95);
		}

		return "";
	}

}
