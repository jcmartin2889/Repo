package com.misys.equation.common.globalcustomers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.GlobalProcessingApiExecuter;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.ICLDRecordDaoImp;
import com.misys.equation.common.dao.beans.CLDRecordDataModel;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * This class will calculate the balance for the universal customer.
 * 
 * @author deroset
 */
public class GlobalBalanceCalculator
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalBalanceCalculator.java 13731 2012-07-02 10:03:58Z whittap1 $";

	// This is the API name which will be called to retrieve customer balances.
	private final static String apiName = "H70DER"; // HZH701
	private EquationStandardSession session;
	private ICLDRecordDaoImp cldRecordDao;
	private String globalCustomerNumber;
	private GlobalProcessingApiExecuter globalProcessingApiExecuter;
	private static final EquationLogger LOG = new EquationLogger(GlobalBalanceCalculator.class);
	private List<Map<String, Object>> customersNumberAndUnits;

	private GlobalBalancePerCustomerBean globalBalanceBean;

	/**
	 * This is the class constructor.
	 * 
	 * @param session
	 *            this is the current session.
	 * @param globalCustomerNumber
	 *            this is the universal customer id.
	 */
	public GlobalBalanceCalculator(EquationStandardSession session, String globalCustomerNumber)
	{
		if (session != null)
		{
			this.session = session;
			this.globalCustomerNumber = globalCustomerNumber;
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
	 * This method will initialise all resources.
	 */
	private void initialisation()
	{
		DaoFactory daoFactory = new DaoFactory();
		cldRecordDao = daoFactory.getCLDRecordDao(session, new CLDRecordDataModel());
		globalProcessingApiExecuter = new GlobalProcessingApiExecuter(session);
		globalBalanceBean = new GlobalBalancePerCustomerBean();
		globalBalanceBean.setGlobalUserId(globalCustomerNumber);
	}

	/**
	 * This method will calculate the balance for the current universal customer. <br>
	 * This method will <br>
	 * <ul>
	 * 1) query the CLD DAO in order to get the customer number and unit.
	 * </ul>
	 * <ul>
	 * 2) call the API for all GP unit and calculate the balance.
	 * <ul>
	 */
	public void calculateCustomerBalanceForTheCurrentCustomer()
	{
		String currentUnit = null;
		String customerNumber = null;

		// It will query the CLD DAO in order to get the customer number and unit.
		customersNumberAndUnits = cldRecordDao.getCustomerNumberAndUnitBaseOnGlobalCustomerNumber(globalCustomerNumber);

		if (customersNumberAndUnits.isEmpty())
		{
			return;
		}
		for (Map<String, Object> listOrderedMap : customersNumberAndUnits)
		{
			customerNumber = (String) listOrderedMap.get("CLDCOUN");
			currentUnit = (String) listOrderedMap.get("CLDCNO");

			callAPIH70DER(customerNumber, currentUnit);
		}
	}

	/**
	 * This method will call the API H70DER for the current customer and process all output.
	 * 
	 * @param customerNumber
	 *            this is the local customer id.
	 * @param currentUnit
	 *            this is the unit name.
	 */
	private void callAPIH70DER(String customerNumber, String currentUnit)
	{
		Map<String, String> apiFields = new HashMap<String, String>();
		Map<String, IEquationStandardObject> results = null;
		String unit = null;
		EquationStandardListEnquiry apiResultForUnit = null;

		apiFields.put("HZAN", customerNumber);

		globalProcessingApiExecuter = new GlobalProcessingApiExecuter(session);
		globalProcessingApiExecuter.setApiFields(apiFields);
		globalProcessingApiExecuter.setApiName(apiName);
		globalProcessingApiExecuter.executeAPI();
		results = globalProcessingApiExecuter.getResults();

		for (Entry<String, IEquationStandardObject> entry : results.entrySet())
		{
			unit = entry.getKey();
			apiResultForUnit = (EquationStandardListEnquiry) results.get(unit);
			processRecords(apiResultForUnit);
		}
	}

	/**
	 * this method will process the current record. It will extract the branch, balance and the equivalent balance.
	 * 
	 * @param apiResultForUnit
	 *            this is the current from the API call result.
	 */
	private void processRecords(EquationStandardListEnquiry apiResultForUnit)
	{
		int recordSize = apiResultForUnit.getHzListRcds().size();
		String branch = null;
		String balance = null;
		String equivalentBalance = null;

		for (int index = 0; index < recordSize; index++)
		{
			try
			{
				branch = apiResultForUnit.getListField("HZAB", index);

				balance = apiResultForUnit.getListField("HZSBL", index);
				equivalentBalance = apiResultForUnit.getListField("HZSBLS", index);

				if (globalBalanceBean.doesItContainThisBranch(branch))
				{
					globalBalanceBean.addBalances(branch, balance, equivalentBalance);
				}
				else
				{
					globalBalanceBean.addNewbranch(branch, balance, equivalentBalance);
				}

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
}