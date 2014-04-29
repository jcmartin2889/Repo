package com.misys.equation.common.dao;

import java.util.List;
import java.util.Map;

/**
 * This is an interface which is going to expose all common Dao services. It is going to behave as a bridge between the Dao and the
 * client code.
 * 
 * @author deroset
 */
public interface ICLDRecordDaoImp extends ICLDRecordDao
{

	/**
	 * This method will return customer and unit base on globalCustomerNumber.
	 * 
	 * @param globalCustomerNumber
	 *            this is the unique id.
	 */
	public List<Map<String, Object>> getCustomerNumberAndUnitBaseOnGlobalCustomerNumber(String globalCustomerNumber);

	/**
	 * This method will get all customer information across units.
	 * 
	 * @param globalCustomerNumber
	 *            this the universal customer id.
	 * @return the <code> List </code> of customer of information.
	 */
	public List<Map<String, Object>> getCustomerInformationAcrossAllUnits(String globalCustomerNumber);

	/**
	 * This method will return a global customer id base on the passed customer number
	 * 
	 * @param this is the customer number used to get the global customer id.
	 * @return the global customer id.
	 */
	public List<Map<String, Object>> getGlobalCustomerIDBaseOnCustomerNumber(String customernumber);

	/**
	 * This method will return global customer data.
	 * 
	 * @param globalCustomerNumber
	 *            this is the unique id.
	 */
	public List<Map<String, Object>> getGlobalCustomerData(String globalCustomerNumber);

	/**
	 * This method will get the next sequence.
	 * 
	 * @return this is the next sequence.
	 */
	public List<Map<String, Object>> getSequence();
}