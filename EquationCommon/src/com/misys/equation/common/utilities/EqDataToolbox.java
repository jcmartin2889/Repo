package com.misys.equation.common.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.misys.equation.common.access.EquationData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTable;
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.internal.eapi.core.EQMessage;

public class EqDataToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDataToolbox.java 17189 2013-09-03 11:49:03Z Lima12 $";

	// returned date format
	public static final int DATEFORMAT_DB = 1;
	public static final int DATEFORMAT_EXT = 2;
	public static final int DATEFORMAT_PRT = 3;
	public static final int DATEFORMAT_USER = 4;
	public static final int DATEFORMAT_DOC = 5;

	/** Cache of valid options */
	private static Map<String, String> optionsCache = new ConcurrentHashMap<String, String>();

	/**
	 * Call the PV module
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param pvModule
	 *            - the PV module name
	 * @param decode
	 *            - the decode
	 * @param dsccn
	 *            - the DSCCN
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * 
	 * @return the EqData
	 */
	public static EquationData callEqData(EquationStandardSession session, String pvModule, String decode, String dsccn,
					String blankAllowed, String newCode)
	{
		try
		{
			EquationData eqData = new EquationData(session, pvModule, decode, dsccn, blankAllowed, newCode);
			return eqData;
		}
		catch (Exception e)
		{
			EquationData eqData = new EquationData();
			eqData.setErrorMessage("KSM7340" + Toolbox.getExceptionMessage(e) + " " + Toolbox.getExceptionMessage(e));
			return eqData;
		}
	}

	/**
	 * Call the PV module
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param pvModule
	 *            - the PV module name
	 * @param decode
	 *            - the decode
	 * @param dsccn
	 *            - the DSCCN
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * 
	 * @return the EqData
	 */
	public static EquationStandardValidation callPV(EquationStandardSession session, String pvModule, String decode, String dsccn,
					String blankAllowed, String newCode)
	{
		try
		{
			EquationStandardValidation equationStandardValidation = new EquationStandardValidation(decode, pvModule, dsccn,
							blankAllowed, newCode);
			equationStandardValidation = session.executeValidate(equationStandardValidation);
			return equationStandardValidation;
		}
		catch (Exception e)
		{
			EquationStandardValidation equationStandardValidation = new EquationStandardValidation("", "", "", "", "");
			List<EQMessage> errorList = new ArrayList<EQMessage>();
			try
			{
				errorList.add(session.getMessage("KSM7340" + Toolbox.getExceptionMessage(e)));
			}
			catch (Exception e2)
			{
				errorList.add(new EQMessage("KSM7340", "20", "KSM7340 &1&2&3", Toolbox.getExceptionMessage(e2)));
			}
			equationStandardValidation.setErrorList(errorList);
			return equationStandardValidation;

		}
	}

	/**
	 * Validate the option id
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param optionId
	 *            - the option Id
	 * @param userId
	 *            - the user Id
	 * 
	 * @return the error message
	 */
	public static String validateOption(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String optionId, String userId)
	{
		// if production unit then check cache first
		if (!session.getUnit().isDevelopmentUnit() && optionsCache.containsKey(optionId + userId))
		{
			return optionsCache.get(optionId + userId);
		}

		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, optionId, 1, 3, false);
		DsccnToolbox.setupDSCCN(dsccn, userId, 4, 4, false);

		EquationStandardValidation eqData = callPV(session, "GBR39R", decode, dsccn.toString(), blankAllowed, newCode);

		// if production unit then save in cache
		if (!session.getUnit().isDevelopmentUnit())
		{
			optionsCache.put(optionId + userId, eqData.getError());
		}

		return eqData.getError();
	}

	/**
	 * Validate the amount
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param amount
	 *            - the amount to validate
	 * @param currency
	 *            - the currency code
	 * @param decimal
	 *            - number of decimal places
	 * @param nodig
	 *            - number of digits
	 * 
	 * @return the error message
	 */
	public static String validateAmount(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String amount, String currency, int decimal, int nodig)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, currency, 1, 3, false);
		DsccnToolbox.setupDSCCN(dsccn, String.valueOf(decimal), 4, 1, false);
		DsccnToolbox.setupDSCCN(dsccn, String.valueOf(nodig), 5, 2, true);
		DsccnToolbox.setupDSCCN(dsccn, amount, 7, 30, false);
		EquationStandardValidation eqData = callPV(session, "GWV30R", decode, dsccn.toString(), "", "");
		return eqData.getError();
	}

	/**
	 * Edit the amount
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param amount
	 *            - the amount to validate
	 * @param currency
	 *            - the currency code
	 * @param decimal
	 *            - number of decimal places
	 * @param nodig
	 *            - number of digits
	 * 
	 * @return the edited amount. If the amount is not valid, then return the input amount
	 */
	public static String editAmount(EquationStandardSession session, String decode, String amount, String currency, int decimal,
					int nodig)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, currency, 1, 3, false);
		DsccnToolbox.setupDSCCN(dsccn, String.valueOf(decimal), 4, 1, false);
		DsccnToolbox.setupDSCCN(dsccn, String.valueOf(nodig), 5, 2, true);
		DsccnToolbox.setupDSCCN(dsccn, amount, 7, 30, false);
		EquationStandardValidation eqData = callPV(session, "GWV30R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 36, 66).trim();
		}
		else
		{
			return amount;
		}
	}

	/**
	 * Edit the charge component
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param chargeComponent
	 *            - the charge component
	 * @param eventId
	 *            - the event Id
	 * 
	 * @return the charge component description
	 */
	public static String editChargeComponent(EquationStandardSession session, String decode, String chargeComponent, String eventId)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, chargeComponent, 1, 6, false);
		DsccnToolbox.setupDSCCN(dsccn, eventId, 7, 3, false);
		EquationStandardValidation eqData = callPV(session, "AAJR10R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 18, 48);
		}
		else
		{
			return chargeComponent;
		}
	}

	/**
	 * Edit the account
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param accountBranch
	 *            - account branch
	 * @param accountNumber
	 *            - account number
	 * @param accountSuffix
	 *            - account suffix
	 * 
	 * @return the account short name
	 */
	public static String editAccount(EquationStandardSession session, String decode, String accountBranch, String accountNumber,
					String accountSuffix)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, accountBranch, 1, 4, false);
		DsccnToolbox.setupDSCCN(dsccn, accountNumber, 5, 6, false);
		DsccnToolbox.setupDSCCN(dsccn, accountSuffix, 11, 3, false);
		EquationStandardValidation eqData = callPV(session, "GWR76R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 31, 46);
		}
		else
		{
			return accountBranch + accountNumber + accountSuffix;
		}
	}

	/**
	 * Validate the deal reference
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param dealtBranch
	 *            - deal branch
	 * @param dealType
	 *            - deal type
	 * @param dealRef
	 *            - deal reference
	 * 
	 * @return the error message
	 */
	public static String validateDealReference(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String dealtBranch, String dealType, String dealRef)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, dealtBranch, 1, 4, false);
		DsccnToolbox.setupDSCCN(dsccn, dealType, 5, 3, false);
		DsccnToolbox.setupDSCCN(dsccn, dealRef, 8, 13, false);
		EquationStandardValidation eqData = callPV(session, "OSR33R", decode, dsccn.toString(), blankAllowed, newCode);
		return eqData.getError();
	}

	/**
	 * Edit deal type
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param dealType
	 *            - deal type
	 * 
	 * @return the error message
	 */
	public static String validateDealType(EquationStandardSession session, String decode, String dealType)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, dealType, 1, 3, false);
		EquationStandardValidation eqData = callPV(session, "OBR37R", decode, dsccn.toString(), "", "");
		return eqData.getError();
	}

	/**
	 * Edit deal type
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param dealType
	 *            - deal type
	 * 
	 * @return the deal type description
	 */
	public static String editDealType(EquationStandardSession session, String decode, String dealType)
	{
		return editParameter(session, decode, "", "OB", dealType);
	}

	/**
	 * Edit charge code
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param chargeCode
	 *            - charge code
	 * 
	 * @return the charge code description
	 */
	public static String editChargeCode(EquationStandardSession session, String decode, String chargeCode)
	{
		return editParameter(session, decode, "", "CK", chargeCode);
	}

	/**
	 * Edit base code
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param baseCode
	 *            - base code
	 * 
	 * @return the base code description
	 */
	public static String editBaseCode(EquationStandardSession session, String decode, String baseCode)
	{
		return editParameter(session, decode, "", "D4", baseCode);
	}

	/**
	 * Retrieve number of decimal places
	 * 
	 * @param session
	 *            - an EquationStandardSession
	 * @param decode
	 *            - the decode
	 * @param ccy
	 *            - currency code
	 * 
	 * @return the deal type description
	 */
	public static int rtvDecimal(EquationStandardSession session, String decode, String ccy)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, ccy, 1, 3, false);
		EquationStandardValidation eqData = callPV(session, "C8R01R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return Toolbox.parseInt(DsccnToolbox.getData(eqData.getDataOutput(), 41, 41), 2);
		}
		else
		{
			return 2;
		}
	}

	/**
	 * Edit currency
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param ccy
	 *            - currency code
	 * 
	 * @return the currency description
	 */
	public static String editCcy(EquationStandardSession session, String decode, String ccy)
	{
		return editParameter(session, decode, "", "C8", ccy);
	}

	/**
	 * Edit country
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param country
	 *            - country code
	 * 
	 * @return the country description
	 */
	public static String editCountry(EquationStandardSession session, String decode, String country)
	{
		return editParameter(session, decode, "", "C7", country);
	}

	/**
	 * Edit date
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param date
	 *            - date (input format)
	 * @param dateFormat
	 *            - type of returned format - see constant DATEFORMAT
	 * 
	 * @return the user date
	 */
	public static String editDate(EquationStandardSession session, String decode, String date, int dateFormat)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, date, 1, 6, false);
		DsccnToolbox.setupDSCCN(dsccn, "Y", 60, 1, false);
		EquationStandardValidation eqData = callPV(session, "GWV94R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			if (dateFormat == DATEFORMAT_DB)
			{
				return DsccnToolbox.getData(eqData.getDataOutput(), 26, 33);
			}
			else if (dateFormat == DATEFORMAT_DOC)
			{
				return DsccnToolbox.getData(eqData.getDataOutput(), 66, 71);
			}
			else if (dateFormat == DATEFORMAT_USER)
			{
				return DsccnToolbox.getData(eqData.getDataOutput(), 60, 66);
			}
			else if (dateFormat == DATEFORMAT_EXT)
			{
				return DsccnToolbox.getData(eqData.getDataOutput(), 39, 50);
			}
			else
			{
				return DsccnToolbox.getData(eqData.getDataOutput(), 50, 57);
			}
		}
		else
		{
			return date;
		}
	}

	/**
	 * Edit frequency
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param frq
	 *            - frequency code
	 * 
	 * @return the frequency description
	 */
	public static String editFreq(EquationStandardSession session, String decode, String frq)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, frq, 1, 3, false);
		EquationStandardValidation eqData = callPV(session, "GWV13R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 10, 40);
		}
		else
		{
			return frq;
		}
	}

	/**
	 * Edit event
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param event
	 *            - the event
	 * 
	 * @return the event description
	 */
	public static String editEvent(EquationStandardSession session, String decode, String event)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, event, 1, 6, false);
		EquationStandardValidation eqData = callPV(session, "AAIR10R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 9, 39);
		}
		else
		{
			return event;
		}
	}

	/**
	 * Edit customer mnemonic/location
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param cusMnem
	 *            - customer mnemonic
	 * @param cusLoc
	 *            - customer location
	 * 
	 * @return the customer name
	 */
	public static String editCustomer(EquationStandardSession session, String decode, String cusMnem, String cusLoc)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, cusMnem, 1, 6, false);
		DsccnToolbox.setupDSCCN(dsccn, cusLoc, 7, 3, false);
		EquationStandardValidation eqData = callPV(session, "GFR70R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 9, 44);
		}
		else
		{
			return cusMnem + cusLoc;
		}
	}

	/**
	 * Edit parameter (from HB file)
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param language
	 *            - language code
	 * @param file
	 *            - file code
	 * @param code
	 *            - parameter code
	 * 
	 * @return the parameter description
	 */
	public static String editParameter(EquationStandardSession session, String decode, String language, String file, String code)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		EquationStandardValidation eqData;

		if (file.length() <= 2)
		{
			DsccnToolbox.setupDSCCN(dsccn, language, 1, 2, false);
			DsccnToolbox.setupDSCCN(dsccn, file, 3, 2, false);
			DsccnToolbox.setupDSCCN(dsccn, code, 5, 10, false);
			eqData = callPV(session, "HBR10R", decode, dsccn.toString(), "", "N");
		}
		else
		{
			DsccnToolbox.setupDSCCN(dsccn, language, 1, 2, false);
			DsccnToolbox.setupDSCCN(dsccn, file, 3, 3, false);
			DsccnToolbox.setupDSCCN(dsccn, code, 6, 10, false);
			eqData = callPV(session, "HBR30R", decode, dsccn.toString(), "", "N");
		}

		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 14, 50);
		}
		else
		{
			return language + file + code;
		}
	}

	/**
	 * Edit parameter (from HA file)
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param language
	 *            - language code
	 * @param code
	 *            - parameter code
	 * 
	 * @return the parameter description
	 */
	public static String editParameter(EquationStandardSession session, String decode, String language, String code)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, language, 1, 2, false);
		DsccnToolbox.setupDSCCN(dsccn, code, 3, 10, false);
		EquationStandardValidation eqData = callPV(session, "HAR10R", decode, dsccn.toString(), "", "");

		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 7, 73);
		}
		else
		{
			return language + code;
		}
	}

	/**
	 * Edit group
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param group
	 *            - group mnemonic
	 * 
	 * @return the group description
	 */
	public static String editGroup(EquationStandardSession session, String decode, String group)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, group, 1, 6, false);
		EquationStandardValidation eqData = callPV(session, "TAR52R", decode, dsccn.toString(), "", "");
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 6, 41);
		}
		else
		{
			return group;
		}
	}

	/**
	 * Retrieve the risk country of the customer
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param cusMnem
	 *            - customer mnemonic
	 * @param cusLoc
	 *            - customer location
	 * @param riskCountry
	 *            - internal risk country exposure (Y or N)
	 * 
	 * @return the risk country associated with the customer
	 */
	public static String rtvCustomerRiskCountry(EquationStandardSession session, String decode, String cusMnem, String cusLoc,
					String riskCountry)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, cusMnem, 1, 6, false);
		DsccnToolbox.setupDSCCN(dsccn, cusLoc, 7, 3, false);
		EquationStandardValidation eqData = callPV(session, "GFR70R", decode, dsccn.toString(), "", "");

		if (eqData.getError().equals(""))
		{
			if (riskCountry.equals("Y"))
			{
				return DsccnToolbox.getData(eqData.getDataOutput(), 101, 103);
			}
			else
			{
				return DsccnToolbox.getData(eqData.getDataOutput(), 69, 71);
			}
		}
		else
		{
			return "";
		}
	}

	/**
	 * Edit the account
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param commitment
	 *            - commitment reference
	 * 
	 * @return the commitment title
	 */
	public static String editCommitment(EquationStandardSession session, String decode, String commitment)
	{
		try
		{
			EquationStandardTable table = new EquationStandardTable("LCPF", "LC10LF", "LCCMR", session);
			table.setFieldValue("LCCMR", commitment);
			session.retrieveEquationTable(table);
			if (table.getValid())
			{
				return table.getFieldValue("LCNR");
			}
		}
		catch (Exception e)
		{
		}

		return commitment;
	}

	/**
	 * Validate the option id
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param optionId
	 *            - the option Id
	 * @param userId
	 *            - the user Id
	 * 
	 * @return the error message
	 */
	public static String editOption(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String optionId, String userId)
	{
		StringBuffer dsccn = DsccnToolbox.getBuffer();
		DsccnToolbox.setupDSCCN(dsccn, optionId, 1, 3, false);
		DsccnToolbox.setupDSCCN(dsccn, userId, 4, 4, false);

		EquationStandardValidation eqData = callPV(session, "GBR39R", decode, dsccn.toString(), blankAllowed, newCode);
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 10, 45);
		}
		else
		{
			return "";
		}
	}

	/**
	 * Retrieve the customer number given the customer mnemonic/location
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param customerMnemonic
	 *            - customer mnemonic
	 * @param customerLocation
	 *            - customer location
	 * 
	 * @return the customer number
	 */
	public static String retrieveCustomer(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String customerMnemonic, String customerLocation)
	{
		String dsccn = Toolbox.pad(customerMnemonic, 6) + Toolbox.pad(customerLocation, 3);

		EquationStandardValidation eqData = callPV(session, "GFR70R", decode, dsccn.toString(), blankAllowed, newCode);
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 44, 50);
		}
		else
		{
			return "";
		}
	}

	/**
	 * Retrieve customer mnemonic/location given the customer number
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param cusMnem
	 *            - customer mnemonic
	 * @param cusLoc
	 *            - customer location
	 * 
	 * @return the customer mnemonic/number
	 */
	public static String retrieveCustomer(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String custNumber)
	{
		EquationStandardValidation eqData = callPV(session, "GFV71R", decode, custNumber, blankAllowed, newCode);
		if (eqData.getError().equals(""))
		{
			return DsccnToolbox.getData(eqData.getDataOutput(), 41, 50);
		}
		else
		{
			return "";
		}
	}

	/**
	 * Retrieve the accounts given the IBAN
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param iban
	 *            - the IBAN account format
	 * 
	 * @return the Equation account format, External account format and IBAN format
	 */
	public static String[] retrieveAccountIBAN(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String iban)
	{
		EquationStandardValidation eqData = callPV(session, "NER40R", decode, iban, blankAllowed, newCode);
		if (eqData.getError().equals(""))
		{
			String abanas = DsccnToolbox.getData(eqData.getDataOutput(), 34, 47);
			String ab = DsccnToolbox.getData(eqData.getDataOutput(), 34, 38);
			String an = DsccnToolbox.getData(eqData.getDataOutput(), 38, 44);
			String as = DsccnToolbox.getData(eqData.getDataOutput(), 44, 47);
			String ean = DsccnToolbox.getData(eqData.getDataOutput(), 62, 82);
			return new String[] { abanas, ean, iban, ab, an, as };
		}
		else
		{
			return new String[] { "", "", iban, "", "", "" };
		}
	}

	/**
	 * Retrieve the accounts given the EAN
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param decode
	 *            - the decode
	 * @param blankAllowed
	 *            - blank allowed?
	 * @param newCode
	 *            - new code?
	 * @param IBAN
	 *            - the EAN account format
	 * 
	 * @return the Equation account format, External account format and IBAN format
	 */
	public static String[] retrieveAccountEAN(EquationStandardSession session, String decode, String blankAllowed, String newCode,
					String ean)
	{
		EquationStandardValidation eqData = callPV(session, "NER11R", decode, ean, blankAllowed, newCode);
		if (eqData.getError().equals(""))
		{
			String abanas = DsccnToolbox.getData(eqData.getDataOutput(), 20, 33);
			String ab = DsccnToolbox.getData(eqData.getDataOutput(), 20, 24);
			String an = DsccnToolbox.getData(eqData.getDataOutput(), 24, 30);
			String as = DsccnToolbox.getData(eqData.getDataOutput(), 30, 33);
			String iban = DsccnToolbox.getData(eqData.getDataOutput(), 200, 234);
			return new String[] { abanas, ean, iban, ab, an, as };
		}
		else
		{
			return new String[] { "", ean, "", "", "", "" };
		}
	}

	/**
	 * Clear cache
	 */
	public static void clear()
	{
		optionsCache.clear();
	}

}
