package com.misys.equation.common.dao;

import java.util.List;
import java.util.Map;

public interface INostroPositionsDao
{
	/**
	 * This method will return nostro accounts information base on the passed currency.
	 * 
	 * @param currency
	 *            this is the padded currency.
	 * @return this a collection of nostro accounts information.
	 */
	public List<Map<String, Object>> getAllNostrosAccountsBaseOnThePassedCurrency(String currency);

	/**
	 * This method will return all nostro-account details base on the passed nostro-account id and currency.
	 * 
	 * @param nostroMnemonic
	 *            this is the nostro-account id.
	 * 
	 * @param currency
	 *            this is the passed currency.
	 * 
	 * @param staringDate
	 *            this is date that will be used to filter the data. it is the from date .
	 * 
	 * @param endDate
	 *            this is date that will be used to filter the data. it is the until date .
	 * 
	 * @return all nostro-account details base on the passed nostro-account id and currency.
	 */
	public List<Map<String, Object>> getAllNostrosAccountsDetails(String nostroMnemonic, String currency, String staringDate,
					String endDate);

	/**
	 * This method will return the last nostro-account position. <br>
	 * This calculation is done SUM( OPNWR - OPNWP ) per each date before to the unit date.
	 * 
	 * @param nostroMnemonic
	 *            this is the nostro-account id.
	 * @param currency
	 *            this is the passed currency.
	 * 
	 * @param staringDate
	 *            this is date that will be used to filter the data. it is the from date .
	 * 
	 * @return the last nostro-account position.
	 */
	public List<Map<String, Object>> getAllNostrosAccountsLastPosition(String nostroMnemonic, String currency, String staringDate);

}
