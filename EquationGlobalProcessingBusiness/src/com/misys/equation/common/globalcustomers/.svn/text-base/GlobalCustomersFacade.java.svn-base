package com.misys.equation.common.globalcustomers;

import java.util.Date;

import com.misys.equation.common.dao.beans.CLDRecordDataModel;
import com.misys.equation.common.dao.beans.CLHRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * This interface exposes the functionality to be provided by the global customers management module.
 * 
 * @author CAMILLN1
 */
public interface GlobalCustomersFacade
{
	/**
	 * Given a list of linked customers this method should invoke security checks on each unit customers. This method is used to
	 * check authority on all customers for a particular user prior to allowing a user to access a global customer.
	 * 
	 * 
	 * @param linkedCustomersList
	 * @throws EQException
	 */
	void checkAuthorityOnLinkedCustomers(final String sessionId, final CLDRecordDataModel[] linkedCustomersList) throws EQException;

	void validateGlobalCustomerID(final String sessionId, final String globaCustomerNum) throws EQException;

	/**
	 * Retrieves the global customer given a global customer number.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * 
	 * @return CLHRecordDataModel - Representation of the global customer
	 * 
	 * @throws EQException
	 */
	CLHRecordDataModel getGlobalCustomer(final String sessionId, final String globaCustomerNum) throws EQException;

	/**
	 * This method returns the HTML content to represent the current list (in memory) of linked customers.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * @return String
	 */
	String getLinkedCustomersHTMLList(final String sessionId, final String globaCustomerNum);

	/**
	 * This method returns the HTML content to represent the current list (in memory) of linked customers.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * @param selected
	 *            int - The selected customer to be checked (radio box)
	 * @return String
	 */
	String getLinkedCustomersHTMLList(final String sessionId, final String globaCustomerNum, final int selected);

	/**
	 * This method creates a global customer and writes the details to the database.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * @param globaCustomerName
	 *            String - The global customer name
	 * @return
	 * @throws EQException
	 */
	String createGlobalCustomer(final String sessionId, final String globaCustomerNum, final String globaCustomerName)
					throws EQException;

	/**
	 * This method updates a global customer by writing (to the database) the global customer details together with all the linked
	 * customers modified during the session established with the Facade.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * @param globaCustomerName
	 *            String - The global customer name
	 * @param lastUpdated
	 *            - The last update value in order to ensure that no other thread has modified the customer we are dealing with.
	 * @return
	 * @throws EQException
	 */
	String updateGlobalCustomer(final String sessionId, final String globaCustomerNum, final String globaCustomerName,
					final Date lastUpdated) throws EQException;

	/**
	 * This method deletes a global customer from the database together with all its linked customers.
	 * 
	 * @param sessionId
	 * @param globaCustomerNum
	 * @throws EQException
	 */
	void deleteGlobalCustomer(final String sessionId, final String globaCustomerNum) throws EQException;

	// THE FOLLOWING METHODS ARE USED TO MANIPULATE MEMORY STATE FOR THE GLOBAL CUSTOMER PRIOR TO SAVING THE CUSTOMER TO THE
	// DATABASE.
	// NOTE THAT THESE METHODS DO NOT WRITE TO THE DATABASE.

	/**
	 * This method updates one of the many flags to be defined for each of the unit customers. Note that the update happens only in
	 * memory and the changes are reflected to the database only after the updateGlobalCustomer() method is called.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * 
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * 
	 * @param propertyId
	 *            String - The ID of the flag to be updated
	 * 
	 * @param value
	 *            boolean - The new value of the flag in memory
	 * 
	 * @throws EQException
	 */
	void updateUnitCustomerFlag(String sessionId, String globaCustomerNum, int index, String propertyId, boolean value)
					throws EQException;

	/**
	 * This method moves the unit customer up one place in memory by swapping it with the previous customer. Note that this swap
	 * happens only in memory and the changes are reflected to the database only after the updateGlobalCustomer() method is called.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * 
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * 
	 * @param arrayPos
	 *            String - The position of the customer to be moved
	 * 
	 * @return Integer - The new position
	 * 
	 * @throws EQException
	 */
	int moveUnitCustomerLinkagePositionUp(final String sessionId, final String globaCustomerNum, final int arrayPos)
					throws EQException;

	/**
	 * This method moves the unit customer down one place in memory by swapping it with the previous customer. Note that this swap
	 * happens only in memory and the changes are reflected to the database only after the updateGlobalCustomer() method is called.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * 
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * 
	 * @param arrayPos
	 *            String - The position of the customer to be moved
	 * 
	 * @return Integer - The new position
	 * 
	 * @throws EQException
	 */
	int moveUnitCustomerLinkagePositionDown(final String sessionId, final String globaCustomerNum, final int arrayPos)
					throws EQException;

	/**
	 * This method deletes the unit customer from the list of customers for the global customer. Note that this happens only in
	 * memory and the changes are reflected to the database only after the updateGlobalCustomer() method is called.
	 * 
	 * @param sessionId
	 * @param globaCustomerNum
	 * @param arrayPos
	 * @throws EQException
	 */
	void deleteUnitCustomer(String sessionId, String globaCustomerNum, int arrayPos) throws EQException;

	/**
	 * This method adds a unit customer to the end of the list of unit customers for the global customer. Note that this happens
	 * only in memory and the changes are reflected to the database only after the updateGlobalCustomer() method is called.
	 * 
	 * @param sessionId
	 *            String - The current session ID
	 * @param globaCustomerNum
	 *            String - The global customer number
	 * @param systemName
	 *            String - The name of the system in which the customer resides
	 * @param unit
	 *            String - The name of the unit in which the customer resides
	 * @param customerNumber
	 *            String - The unit customer number
	 * @param customerMnemonic
	 *            String - The customer mnemonic
	 * @param customerLocation
	 *            String - The customer location
	 * @param customerName
	 *            String - The customer name
	 * @throws EQException
	 */
	void addUnitCustomer(final String sessionId, final String globaCustomerNum, final String systemName, final String unit,
					final String customerNumber, final String customerMnemonic, final String customerLocation,
					final String customerName) throws EQException;

	/**
	 * Getter for linked customers
	 * 
	 * @return CLDRecordDataModel - List of linked customers
	 */
	CLDRecordDataModel[] getLinkedCustomers();

	/**
	 * Setter for linked customers
	 * 
	 * @param CLDRecordDataModel
	 *            - List of linked customers
	 */
	void setLinkedCustomers(CLDRecordDataModel[] linkedCustomers);

}
