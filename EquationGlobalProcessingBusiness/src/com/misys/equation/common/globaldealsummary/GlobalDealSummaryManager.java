package com.misys.equation.common.globaldealsummary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.GlobalProcessingApiExecuter;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.ICLDRecordDaoImp;
import com.misys.equation.common.dao.IGFRecordDao;
import com.misys.equation.common.dao.beans.CLDRecordDataModel;
import com.misys.equation.common.dao.beans.GFRecordDataModel;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;

public class GlobalDealSummaryManager
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalDealSummaryManager.java 13731 2012-07-02 10:03:58Z whittap1 $";
	private List<EquationStandardSession> equationStandardSessions = null;
	private EquationStandardSession session;
	private List<GlobalDealSummary> globalDealSummaries = null;
	private List<GlobalDealSummary> tempGlobalDealSummaries = null;
	private String globalCustomerNumberID;
	private String customerLocation;
	private String customerMnemonic;
	private String currentSystem;
	private String customerUnit;
	private DaoFactory daoFactory;
	private static final EquationLogger LOG = new EquationLogger(GlobalDealSummaryManager.class);
	private GlobalProcessingApiExecuter globalProcessingApiExecuter;
	private final static String apiName = "V47DER";
	private String currency;

	/**
	 * @param session
	 */
	public GlobalDealSummaryManager(EquationStandardSession session)
	{
		if (session != null)
		{
			this.session = session;
		}
		else
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("The session cannot be null.");
			}
		}
		initialisation();
	}

	/**
	 * This is the constructor used to create a GlobalDealSummaryManager for global search.
	 * 
	 * @param session
	 *            this is the current session.
	 * @param globalCustomerNumberID
	 *            this is the global customer id, that will be used to get all deals
	 */
	public GlobalDealSummaryManager(EquationStandardSession session, String globalCustomerNumberID)
	{
		this(session);
		this.globalCustomerNumberID = globalCustomerNumberID;
	}

	/**
	 * This is the constructor used to create a GlobalDealSummaryManager for global search.
	 * 
	 * @param session
	 *            this is the current session.
	 * @param customerLocation
	 *            this is the customer location that will be used to get all deals.
	 * @param customerMnemonic
	 *            this is the customer Mnemonic that will be used to get all deals.
	 * @param customerUnit
	 *            this is the customer unit that will be used to get all deals.
	 */
	public GlobalDealSummaryManager(EquationStandardSession session, String customerLocation, String customerMnemonic,
					String currentSystem, String customerUnit)
	{
		this(session);
		this.customerLocation = customerLocation;
		this.customerMnemonic = customerMnemonic;
		this.customerUnit = customerUnit;
		this.currentSystem = currentSystem;
	}

	public void processGlobalDealSummaryManager()
	{
		List<Map<String, Object>> customerNumberAcrossUnits = null;
		Map<String, IEquationStandardObject> apiCallResults = null;
		GlobalDealSummary globalDealSummary = null;

		if (customerMnemonic != null)
		{
			globalDealSummary = new GlobalDealSummary();

			globalDealSummary.setSystem(currentSystem);
			globalDealSummary.setUnit(customerUnit);

			globalDealSummary.setGlobalCustomerID(globalCustomerNumberID);
			globalDealSummary.setCustomerLocation(customerLocation);
			globalDealSummary.setCustomerMnemonic(customerMnemonic);
			globalDealSummaries.add(globalDealSummary);

		}
		else
		{
			customerNumberAcrossUnits = getCustomerNumberAcrossAllUnits();
			setCustomerDetails(customerNumberAcrossUnits);
		}

		for (GlobalDealSummary currentGlobalDealSummary : globalDealSummaries)
		{
			apiCallResults = callAPIDLSDER(currentGlobalDealSummary);
			populateGlobalDealSummary(apiCallResults, currentGlobalDealSummary);
		}
		globalDealSummaries = tempGlobalDealSummaries;
	}

	/**
	 * This method will populate the current <code>GlobalDealSummary</code> model with all <code>IEquationStandardObject</code>
	 * result.
	 * 
	 * @param results
	 *            this is the API call result.
	 * @param currentGlobalDealSummary
	 *            this is the current data model <code>GlobalDealSummary</code>.
	 */
	private void populateGlobalDealSummary(Map<String, IEquationStandardObject> results, GlobalDealSummary currentGlobalDealSummary)
	{
		String unit = null;
		EquationStandardListEnquiry apiResultForUnit = null;

		for (Entry<String, IEquationStandardObject> entry : results.entrySet())
		{
			unit = entry.getKey();
			apiResultForUnit = (EquationStandardListEnquiry) results.get(unit);
			populateGlobalDealSummary(apiResultForUnit, currentGlobalDealSummary);
		}
	}

	/**
	 * This method will create a new <code>GlobalDealSummary</code>, using the previous one.
	 * 
	 * @param currentGlobalDealSummary
	 *            this is the currentGlobalDealSummary which will provide the data.
	 * @return create a new <code>GlobalDealSummary</code>.
	 */
	private GlobalDealSummary getNewGlobalDealSummary(GlobalDealSummary currentGlobalDealSummary)
	{
		GlobalDealSummary newGlobalDealSummary = new GlobalDealSummary();

		newGlobalDealSummary.setSystem(currentGlobalDealSummary.getSystem());
		newGlobalDealSummary.setUnit(currentGlobalDealSummary.getUnit());
		newGlobalDealSummary.setGlobalCustomerID(currentGlobalDealSummary.getGlobalCustomerID());
		newGlobalDealSummary.setCustomerNumber(currentGlobalDealSummary.getCustomerNumber());
		newGlobalDealSummary.setCustomerLocation(currentGlobalDealSummary.getCustomerLocation());
		newGlobalDealSummary.setCustomerMnemonic(currentGlobalDealSummary.getCustomerMnemonic());

		return newGlobalDealSummary;
	}

	/**
	 * This method will initialise all resources.
	 */
	private void initialisation()
	{
		daoFactory = new DaoFactory();
		globalDealSummaries = new ArrayList<GlobalDealSummary>();
		tempGlobalDealSummaries = new ArrayList<GlobalDealSummary>();

		try
		{
			equationStandardSessions = EquationCommonContext.getContext().getGlobalProcessingEquationStandardSessions(
							session.getSessionIdentifier());
		}
		catch (Exception eQException)
		{
			if (LOG.isErrorEnabled())
			{
				StringBuilder message = new StringBuilder("There is a problem creating Global processing the sessions");
				LOG.error(message.toString(), eQException);
			}
		}
	}

	/**
	 * This method will get all customer information (Unit and local customer number) across units.
	 * 
	 * @param globalCustomerNumber
	 *            this the universal customer id.
	 * @return the <code> List </code> of customer of information.
	 */
	public List<Map<String, Object>> getCustomerNumberAcrossAllUnits()
	{
		List<Map<String, Object>> customerNumberAcrossUnits = null;
		ICLDRecordDaoImp cldRecordDao;
		cldRecordDao = daoFactory.getCLDRecordDao(session, new CLDRecordDataModel());
		customerNumberAcrossUnits = cldRecordDao.getCustomerInformationAcrossAllUnits(globalCustomerNumberID);
		return customerNumberAcrossUnits;
	}

	/**
	 * 
	 * @param customerNumberAcrossUnits
	 */
	private void setCustomerDetails(List<Map<String, Object>> customerNumberAcrossUnits)
	{
		String customerNumber = null;
		String currentUnit = null;
		String currentSystem = null;
		GlobalDealSummary globalDealSummary = null;

		for (Map<String, Object> listOrderedMap : customerNumberAcrossUnits)
		{
			customerNumber = (String) listOrderedMap.get("CLDCNO");
			currentUnit = (String) listOrderedMap.get("CLDCOUN");
			currentSystem = (String) listOrderedMap.get("CLDSYS");

			globalDealSummary = new GlobalDealSummary();
			globalDealSummary.setSystem(currentSystem);
			globalDealSummary.setUnit(currentUnit);
			globalDealSummary.setGlobalCustomerID(globalCustomerNumberID);
			globalDealSummary.setCustomerNumber(customerNumber);
			setCustomerMnemonicAndLocation(globalDealSummary, customerNumber, currentUnit, currentSystem);
			globalDealSummaries.add(globalDealSummary);
		}
	}

	/**
	 * This method will search, get and set the customer mnemonic and location in the <code>GlobalDealSummary</code>.
	 * 
	 * @param globalDealSummary
	 *            <code>GlobalDealSummary</code> this is the model where the data will be set.
	 * @param customerNumber
	 *            this is the customerNumber used in the where condition.
	 * @param currentUnit
	 *            this the unit where customer mnemonic and location is.
	 */
	private void setCustomerMnemonicAndLocation(GlobalDealSummary globalDealSummary, String customerNumber, String currentUnit,
					String currentSystem)
	{
		List<Map<String, Object>> result = null;
		IGFRecordDao icldRecordDao;
		EquationStandardSession currentSession = null;

		for (int currentIndex = 0; currentIndex < equationStandardSessions.size(); currentIndex++)
		{
			// TODO there should be any facility which let me retrive a session by UNIT !!!! REFACTOR IN EquationCommonContext.
			currentSession = equationStandardSessions.get(currentIndex);
			// it is only going to query using the unit defined in CLD table.
			if (currentSession.getSystemId().trim().equals(currentSystem.trim())
							&& currentSession.getUnit().getUnitId().trim().equals(currentUnit.trim()))
			{
				icldRecordDao = daoFactory.getGFRecordDao(currentSession, new GFRecordDataModel());
				result = icldRecordDao.getCustomerLocationAndMnemonic(customerNumber);
				// if it is true, it means that there is not any customer defined in that unit which correspond to the passed
				// customer number.
				if (result.isEmpty())
				{
					continue;
				}

				for (Map<String, Object> currentResult : result)
				{
					globalDealSummary.setCustomerMnemonic((String) currentResult.get("GFCUS"));
					globalDealSummary.setCustomerLocation((String) currentResult.get("GFCLC"));
				}
			}
		}
	}

	public List<GlobalDealSummary> getGlobalDealSummarys()
	{
		return globalDealSummaries;
	}

	/**
	 * This method will call the API DLSDER for the current customer and process all output.
	 * 
	 * @param globalDealSummary
	 *            this is the current customer.
	 * @return a <code>Map</code> of IEquationStandardObject base on a <code>String</code> key this is the result of the api call
	 *         across units.
	 */
	private Map<String, IEquationStandardObject> callAPIDLSDER(GlobalDealSummary globalDealSummary)
	{
		Map<String, String> apiFields = new HashMap<String, String>();
		Map<String, IEquationStandardObject> apiCallResults = null;

		apiFields.put("HZCUS", globalDealSummary.getCustomerMnemonic()); // Customer mnemonic (6A)
		apiFields.put("HZCLC", globalDealSummary.getCustomerLocation()); // Customer location (3A)

		if (currency != null)
		{
			apiFields.put("HZCCYR", currency);
		}

		globalProcessingApiExecuter = new GlobalProcessingApiExecuter(session);
		globalProcessingApiExecuter.setApiFields(apiFields);
		globalProcessingApiExecuter.setApiName(apiName);
		globalProcessingApiExecuter.setSystemAndUnitToBeUsed(globalDealSummary.getSystem(), globalDealSummary.getUnit());
		globalProcessingApiExecuter.executeAPI();
		apiCallResults = globalProcessingApiExecuter.getResults();

		return apiCallResults;
	}

	/**
	 * This method will populate the current <code>GlobalDealSummary</code> model with all <code>EquationStandardListEnquiry</code>
	 * result.
	 * 
	 * @param results
	 *            this is the API call result.
	 * @param currentGlobalDealSummary
	 *            this is the current data model <code>GlobalDealSummary</code>.
	 */
	private void populateGlobalDealSummary(EquationStandardListEnquiry equationStandardListEnquiry,
					GlobalDealSummary globalDealSummary)
	{
		StringBuilder reference = null;
		int recordSize = equationStandardListEnquiry.getHzListRcds().size();

		for (int index = 0; index < recordSize; index++)
		{
			try
			{
				GlobalDealSummary newGlobalDealSummary = getNewGlobalDealSummary(globalDealSummary);

				reference = new StringBuilder(equationStandardListEnquiry.getListField("HZDLP", index));
				reference.append("-");
				reference.append(equationStandardListEnquiry.getListField("HZDLR", index));

				newGlobalDealSummary.setApplication(equationStandardListEnquiry.getListField("HZAPP", index));
				newGlobalDealSummary.setBranch(equationStandardListEnquiry.getListField("HZBRNM", index));

				newGlobalDealSummary.setReference(reference.toString());
				newGlobalDealSummary.setAmount(getAmount(equationStandardListEnquiry, index));
				newGlobalDealSummary.setCurrency(getCurrency(equationStandardListEnquiry, index));
				newGlobalDealSummary.setSecondCurrency(getSecondCurrency(equationStandardListEnquiry, index));
				newGlobalDealSummary.setStartDate(Toolbox.padAtFront(getStartDate(equationStandardListEnquiry, index), "0", 7));
				newGlobalDealSummary.setEndDate(Toolbox.padAtFront(getEndDate(equationStandardListEnquiry, index), "0", 7));
				newGlobalDealSummary.setRate(equationStandardListEnquiry.getListField("HZRAT", index).trim());

				newGlobalDealSummary.setDays(getDays(equationStandardListEnquiry, index));

				newGlobalDealSummary.setProvisions(equationStandardListEnquiry.getListField("HZAMTP", index));
				newGlobalDealSummary.setLoanStatusCode(equationStandardListEnquiry.getListField("HZLSC", index));
				newGlobalDealSummary.setNonAccrualStatus(equationStandardListEnquiry.getListField("HZNAST", index));

				newGlobalDealSummary.setDealType(equationStandardListEnquiry.getListField("HZDLP", index));
				newGlobalDealSummary.setDealReference(equationStandardListEnquiry.getListField("HZDLR", index));

				newGlobalDealSummary.setBasicDealType(equationStandardListEnquiry.getListField("HZBDT", index));
				newGlobalDealSummary.setSplitValueDate(getSplitValueDate(equationStandardListEnquiry, index));
				newGlobalDealSummary.setEquivAmount(getEquivAmount(equationStandardListEnquiry, index));

				newGlobalDealSummary.setMessages(equationStandardListEnquiry.getErrorList());

				tempGlobalDealSummaries.add(newGlobalDealSummary);
			}
			catch (Exception exception)
			{
				if (LOG.isErrorEnabled())
				{
					LOG.error(exception);
					LOG.error("There was a problem trying to iterate through the records");
				}
			}
		}
	}

	/**
	 * This method will return the amount to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the amount.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getAmount(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		// HZDLA if Term deal (HZBDT=T)
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("T"))
		{
			return equationStandardListEnquiry.getListField("HZDLA", index).toString();
		}
		// HZPAM if Exchange deal (HZBDT=X)
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("X"))
		{
			return equationStandardListEnquiry.getListField("HZPAM", index).toString();
		}
		// ignore deals if HZBDT is not X or T
		return "";
	}

	/**
	 * This method will return the Currency to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the Currency.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getCurrency(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		StringBuilder currency = null;

		// HZCCY followed by blanks if Term deal (HZBDT=T)
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("T"))
		{
			currency = new StringBuilder(equationStandardListEnquiry.getListField("HZCCY", index).toString());
		}

		// HZPCCY followed by HZSCCY if Exchange deal (HZBDT=X)
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("X"))
		{
			currency = new StringBuilder(equationStandardListEnquiry.getListField("HZPCCY", index).toString());
			// currency.append("-");
			// currency.append(equationStandardListEnquiry.getListField("HZSCCY", index).toString());
		}

		return currency.toString();
	}

	/**
	 * This method will return the Second-Currency to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the Second-Currency.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getSecondCurrency(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		StringBuilder secondCurrency = null;
		// blanks for term deal HZSCCY for exchange deal

		// Blanks for term deal
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("T"))
		{
			secondCurrency = new StringBuilder("");
		}

		// HZSCCY for exchange deal
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("X"))
		{
			secondCurrency = new StringBuilder(equationStandardListEnquiry.getListField("HZSCCY", index).toString());
		}

		return secondCurrency.toString();
	}

	/**
	 * This method will return the start date to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the start date.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getStartDate(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		String startDate = "";

		// HZSDTE for term deal
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("T"))
		{
			startDate = equationStandardListEnquiry.getListField("HZSDTE", index).toString();
		}

		// HZCTRD for exchange deal
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("X"))
		{
			startDate = equationStandardListEnquiry.getListField("HZCTRD", index).toString();
		}

		return startDate;
	}

	/**
	 * This method will return the End date to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the End date.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getEndDate(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		String endDate = "";

		// HZMDT for term deal
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("T"))
		{
			endDate = equationStandardListEnquiry.getListField("HZMDT", index).toString();
		}

		// Earlier of HZPURD or HZSLED for exchange deal
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("X"))
		{
			if (Toolbox.parseInt(equationStandardListEnquiry.getListField("HZPURD", index), 0) <= Toolbox.parseInt(
							equationStandardListEnquiry.getListField("HZSLED", index), 0))
			{
				endDate = equationStandardListEnquiry.getListField("HZPURD", index);
			}
			else
			{
				endDate = equationStandardListEnquiry.getListField("HZSLED", index);
			}
		}

		return endDate;
	}

	public String getCustomerLocation()
	{
		return customerLocation;
	}

	public String getCustomerMnemonic()
	{
		return customerMnemonic;
	}

	public String getCurrency()
	{
		return currency;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	/**
	 * This method will return the split value date flag to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the split value date flag.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getSplitValueDate(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		String splitValueDate = "";

		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("X"))
		{
			if (!equationStandardListEnquiry.getListField("HZPURD", index).equals(
							equationStandardListEnquiry.getListField("HZSLED", index)))
			{
				splitValueDate = "*";
			}
		}

		return splitValueDate;
	}

	/**
	 * This method will return the equivalent amount to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the equivalent amount.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getEquivAmount(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		// HZDLAE if Term deal (HZBDT=T)
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("T"))
		{
			return equationStandardListEnquiry.getListField("HZDLAE", index);
		}
		// HZPAME if Exchange deal (HZBDT=X)
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("X"))
		{
			return equationStandardListEnquiry.getListField("HZPAME", index);
		}
		// ignore deals if HZBDT is not X or T
		return "";
	}

	/**
	 * This method will return the days to use base on the value of the HZBDT field.
	 * 
	 * @param equationStandardListEnquiry
	 *            this the API query result.
	 * @param index
	 *            this is the current index.
	 * @return the days.
	 * @throws Exception
	 *             if there is an error.
	 */
	private String getDays(EquationStandardListEnquiry equationStandardListEnquiry, int index) throws Exception
	{
		// HZTERM if Term deal (HZBDT=T)
		if (equationStandardListEnquiry.getListField("HZBDT", index).equals("T"))
		{
			return equationStandardListEnquiry.getListField("HZTERM", index);
		}
		// ignore deals if HZBDT is not T
		return "";
	}
}
