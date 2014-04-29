package com.misys.equation.common.globalcustomers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.ICLDRecordDaoImp;
import com.misys.equation.common.dao.ICLHRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CLDRecordDataModel;
import com.misys.equation.common.dao.beans.CLHRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;

/**
 * This class implements the functionality for the global customers management.
 * 
 * @author CAMILLN1
 */
public class GlobalCustomersImpl implements GlobalCustomersFacade
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalCustomersImpl.java 10410 2011-02-01 14:32:24Z MACDONP1 $";
	// Get the logger class ...
	protected static final EquationLogger LOG = new EquationLogger(GlobalCustomersImpl.class);
	// Create a DAO factory class ...
	protected static final DaoFactory daoFactory = new DaoFactory();

	private static final String UNIT_CUSTOMERS_TABLE_NAME = "GF03LF";

	// This map contains a working ordered list of Linked Customers. This is created each time a user starts the LNK process
	// and removed after the changes are saved to the database
	private CLDRecordDataModel[] linkedCustomers = null;

	// Restrict access to this class such that it can only be created by the Access class.
	GlobalCustomersImpl()
	{

	}

	public void checkAuthorityOnLinkedCustomers(final String sessionId, final CLDRecordDataModel[] linkedCustomersList)
					throws EQException
	{
		if (sessionId == null || sessionId.equals(""))
		{
			throw new EQException("Invalid session id");
		}
		final EquationUser eqUser = getEqUser(sessionId);
		if (linkedCustomersList == null || linkedCustomersList.length == 0)
		{
			throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.nocustomerlist"));
		}

		// Create a map to hold the different customers that belong to the same unit ...
		final Map<String, List<CLDRecordDataModel>> mapOfUnitCustomers = new HashMap<String, List<CLDRecordDataModel>>();

		for (CLDRecordDataModel linkedCustomer : linkedCustomersList)
		{
			List<CLDRecordDataModel> tempCustList = mapOfUnitCustomers.get(linkedCustomer.getSystemName()
							+ linkedCustomer.getCustomerOwningUnit());
			if (tempCustList == null)
			{
				tempCustList = new ArrayList<CLDRecordDataModel>();
				tempCustList.add(linkedCustomer);
				mapOfUnitCustomers.put(linkedCustomer.getSystemName() + linkedCustomer.getCustomerOwningUnit(), tempCustList);
			}
			else
			{
				tempCustList.add(linkedCustomer);
			}
		}

		// First we check that the user has access to all the units in the linked customer set
		final Iterator<String> keys = mapOfUnitCustomers.keySet().iterator();
		final List<EquationStandardSession> sessions = EquationCommonContext.getContext()
						.getGlobalProcessingEquationStandardSessions(sessionId);
		while (keys.hasNext())
		{
			String key = keys.next();
			boolean keyFoundInSessions = false;
			for (EquationStandardSession session : sessions)
			{
				if (key.equals(session.getSystemId() + session.getUnitId()))
				{
					keyFoundInSessions = true;
				}
			}
			if (!keyFoundInSessions)
			{
				throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
								"globalcustomerlinking.maintlinkedcust.businesserrors.norightsforunit"));
			}
		}

		// If we got to this line of code then we do have access to all units for all linked customers
		// hence we simply loop through all the sessions for the user and execute the query to check
		// for security on the individual customers inside the units. If there is at least one customer
		// to which we do not have authorisation, then we need to through an error.
		for (EquationStandardSession session : sessions)
		{
			Statement statement = null;
			ResultSet rs = null;
			try
			{
				// Get a connection to the unit ...
				statement = session.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// Build the global customer list (to be checked for security) for this unit ...
				final List<CLDRecordDataModel> listOfCustomers = mapOfUnitCustomers
								.get(session.getSystemId() + session.getUnitId());

				if (listOfCustomers == null || listOfCustomers.size() == 0)
				{
					continue;
				}

				// Build the set of customers to check for security ...
				final StringBuffer customerValueSet = new StringBuffer();
				for (int j = 0; j < listOfCustomers.size(); j++)
				{
					CLDRecordDataModel customer = listOfCustomers.get(j);
					customerValueSet.append("'");
					customerValueSet.append(customer.getCustMnemonic() + customer.getCustLocation());
					customerValueSet.append("'");
					if (j != listOfCustomers.size() - 1)
					{
						customerValueSet.append(",");
					}
				}

				// Generate the SQL string that will check for security on all linked customers for the current unit ...
				final StringBuilder sql = new StringBuilder();
				sql.append("WITH RS AS  ");
				sql.append("( ");
				sql.append("select GFCUS|| GFCLC AS CUSLOC, ");
				sql.append("SUBSTRING(utw06rfnc(' ','GFR70R',GFCUS|| GFCLC,' ',' ','G',''),0,8) AS ERRCODE ");
				sql.append("FROM GF03LF ");
				sql.append("WHERE GFCUS|| GFCLC IN (" + customerValueSet.toString() + ") ");
				sql.append(") ");
				sql.append("SELECT ERRCODE ");
				sql.append("FROM RS ");
				sql.append("WHERE ERRCODE LIKE 'KSM%' OR ERRCODE LIKE 'USM%' ");
				// Execute the statement to check if we have any un-authorised records.
				// If so throw an EQException with the appropriate error message...
				rs = statement.executeQuery(sql.toString());
				rs.last();
				int count = rs.getRow();
				if (count > 0)
				{
					throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
									"globalcustomerlinking.maintlinkedcust.businesserrors.norightsforcustomer"));
				}
			}
			catch (SQLException e)
			{
				// Do nothing the unit may have failed temporarily or might be down ..
				// TODO CAMILLEN - Check if we need to check for particular error codes
			}
			finally
			{
				SQLToolbox.close(statement);
				SQLToolbox.close(rs);
			}
		}
	}

	public void validateGlobalCustomerID(String sessionId, String globaCustomerNum) throws EQException
	{
		final EquationUser eqUser = getEqUser(sessionId);

		// Check for valid length ...
		if (globaCustomerNum == null || globaCustomerNum.equals("") || globaCustomerNum.length() == 0
						|| globaCustomerNum.length() > 20)
		{
			throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.globalcustid.invalid"));
		}

		// Check for alphanumeric only and no blanks ...
		if (!isAlphaNumericNoBlanks(globaCustomerNum))
		{
			throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.globalcustid.invalid"));
		}
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public CLHRecordDataModel getGlobalCustomer(final String sessionId, final String globaCustomerNum) throws EQException
	{
		// Do some basic validations on the session ...
		final EquationStandardSession session = validateSession(sessionId);

		try
		{
			// Look for the record in the database ...
			CLHRecordDataModel rpfDm = new CLHRecordDataModel();
			final ICLHRecordDao dao = daoFactory.getCLHRecordDao(session, rpfDm);
			List<AbsRecord> rpfList = dao.getRecordBy(" CLHGCID='" + globaCustomerNum + "'");

			// Finally return the data model ....
			if (rpfList != null && rpfList.size() > 0)
			{
				linkedCustomers = getCustomerLinkages(sessionId, globaCustomerNum);
				return (CLHRecordDataModel) rpfList.get(0);
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			LOG.error("GlobalCustomersImpl: createGlobalCustomer() Failed: ");
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public String getLinkedCustomersHTMLList(final String sessionId, final String globaCustomerNum)
	{
		return getLinkedCustomersHTMLList(sessionId, globaCustomerNum, -1);
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public String getLinkedCustomersHTMLList(final String sessionId, final String globaCustomerNum, final int selected)
	{
		final StringBuffer htmlContent = new StringBuffer();
		try
		{
			final EquationUser eqUser = getEqUser(sessionId);
			final CLDRecordDataModel[] linkedCustomers = getCustomerLinkagesInMemory(sessionId, globaCustomerNum);

			int i = 0;
			final short multiplicator = 10;

			if (linkedCustomers != null)
			{
				htmlContent.append("<DIV ID='___LastItem' value='" + (linkedCustomers.length - 1) + "'/>");
			}
			else
			{
				htmlContent.append("<DIV ID='___LastItem' value='0'/>");
			}

			htmlContent.append("<TABLE>");
			if (linkedCustomers != null)
			{

				for (CLDRecordDataModel unitCustomer : linkedCustomers)
				{
					if ((i % 2) == 0)
					{
						htmlContent.append("<TR bgcolor=\"#F2F2F2\">");
					}
					else
					{
						htmlContent.append("<TR>");
					}
					htmlContent.append("<TD width=\"20px\">");
					final String value = unitCustomer.getSystemName() + unitCustomer.getCustomerOwningUnit()
									+ unitCustomer.getCustomerNumber() + unitCustomer.getSequenceNumber();
					final String id = "unitcustomerradio";
					if (selected == i)
					{
						htmlContent.append("<input class=\"wf_pr\" type=\"radio\" id=\"" + id + i + "\" name=\"" + id
										+ "\" value=\"" + value + "\" onclick=\"radioCheck(" + i + ", "
										+ (linkedCustomers.length - 1) + ")\" checked/>");
					}
					else
					{
						htmlContent.append("<input class=\"wf_pr\" type=\"radio\" id=\"" + id + i + "\" name=\"" + id
										+ "\" value=\"" + value + "\" onclick=\"radioCheck(" + i + ", "
										+ (linkedCustomers.length - 1) + ")\"/>");
					}
					htmlContent.append("</TD>");
					final String systemHeader = EquationCommonContext.getContext().getLanguageResource(eqUser,
									"globalcustomerlinking.maintlinkedcust.linkedcustomers.table.headers.sysname");
					htmlContent.append("<TD width=\"" + multiplicator * systemHeader.length() + "px\">");
					htmlContent.append(unitCustomer.getSystemName());
					htmlContent.append("</TD>");

					final String unitHeader = EquationCommonContext.getContext().getLanguageResource(eqUser,
									"globalcustomerlinking.maintlinkedcust.linkedcustomers.table.headers.unit");
					htmlContent.append(createHTMLCell(unitHeader.length(), unitCustomer.getCustomerOwningUnit()));

					final String custHeader = EquationCommonContext.getContext().getLanguageResource(eqUser,
									"globalcustomerlinking.maintlinkedcust.linkedcustomers.table.headers.mnemonicloc");
					htmlContent.append(createHTMLCell(custHeader.length(),
									unitCustomer.getCustMnemonic() + " " + unitCustomer.getCustLocation()));

					htmlContent.append(createHTMLCell(10, unitCustomer.getCustName()));

					final String numberHeader = EquationCommonContext.getContext().getLanguageResource(eqUser,
									"globalcustomerlinking.maintlinkedcust.linkedcustomers.table.headers.number");
					htmlContent.append(createHTMLCell(numberHeader.length(), unitCustomer.getCustomerNumber()));

					final String masterHeader = EquationCommonContext.getContext().getLanguageResource(eqUser,
									"globalcustomerlinking.maintlinkedcust.linkedcustomers.table.headers.master");
					htmlContent.append(createHTMLCellWithCheckBox(globaCustomerNum, masterHeader.length(), "isMasterFlag", i,
									unitCustomer.isMasterFlag()));

					htmlContent.append("</TR>");
					i++;
				}
			}
			htmlContent.append("</TABLE>");
		}
		catch (EQException e)
		{
			htmlContent.append("<BR/>");
			EquationCommonContext.getContext().getLOG().error(e);
		}
		return htmlContent.toString();
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public String createGlobalCustomer(final String sessionId, final String globaCustomerNum, final String globaCustomerName)
					throws EQException
	{
		final EquationStandardSession session = validateSession(sessionId);
		final EquationUser eqUser = getEqUser(sessionId);

		validateGlobalCustomerID(sessionId, globaCustomerNum);

		if (globaCustomerName == null || globaCustomerName.equals(""))
		{
			throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.globalcustname.empty"));
		}

		if (globaCustomerName.length() > 35)
		{
			throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.globalcustname.invalid"));
		}

		CLHRecordDataModel dm = null;
		try
		{
			dm = new CLHRecordDataModel(globaCustomerNum);
			dm.setGlobalCustomerName(globaCustomerName);
			dm.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));

			// Insert a record into CLHPF
			final ICLHRecordDao rpfDao = daoFactory.getCLHRecordDao(session, dm);
			rpfDao.insertRecord();

			// Finally Commit the changes & return the searchId PK ...
			session.connectionCommit();
			// Finally return the data model ....
			return dm.getGlobalCustomerName();
		}
		catch (Exception e)
		{
			LOG.error("GlobalCustomersImpl: createGlobalCustomer() Failed: ");
			try
			{
				session.connectionRollback();
			}
			catch (Exception e2)
			{
				throw new EQException(e2);

			}
			throw new EQException(e);
		}
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public String updateGlobalCustomer(final String sessionId, final String globaCustomerNum, final String globaCustomerName,
					final Date lastUpdated) throws EQException
	{
		final EquationStandardSession session = validateSession(sessionId);
		final EquationUser eqUser = getEqUser(sessionId);
		final EquationUnit eqUnit = EquationCommonContext.getContext().getEquationUnit(sessionId);

		validateGlobalCustomerID(sessionId, globaCustomerNum);

		if (globaCustomerName == null || globaCustomerName.equals(""))
		{
			throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.globalcustname.empty"));
		}

		if (globaCustomerName.length() > 35)
		{
			throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.globalcustname.invalid"));
		}

		// Validate against business rules ...
		final String[] businessErrors = validateCLDRecordDataModel(eqUnit, eqUser, linkedCustomers);
		if (businessErrors.length > 0)
		{
			throw new EQException(businessErrors);
		}

		try
		{
			// Look for the record in the database ...
			CLHRecordDataModel rpfDm = new CLHRecordDataModel();
			final ICLHRecordDao dao = daoFactory.getCLHRecordDao(session, rpfDm);
			List<AbsRecord> rpfList = dao.getRecordBy(" CLHGCID='" + globaCustomerNum + "'");

			// Finally return the data model ....
			CLHRecordDataModel dm = null;
			if (rpfList != null && rpfList.size() > 0)
			{

				// There should be only one record (look-up by primary key)
				dm = (CLHRecordDataModel) rpfList.get(0);

				// Get the time stamp from the database and check our time stamp in memory if they match then we are
				// allowed to proceed with the update.

				if (dm.getTimestamp().getTime() == lastUpdated.getTime())
				{
					dm.setGlobalCustomerName(globaCustomerName);
					dm.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
					final ICLHRecordDao rpfDao = daoFactory.getCLHRecordDao(session, dm);
					rpfDao.updateRecord();
				}
				else
				{
					throw new EQException(EquationCommonContext.getContext().getLanguageResource(eqUser,
									"globalcustomerlinking.maintlinkedcust.businesserrors.recordmodifiedbyothers"));
				}
			}
			else
			{
				// Insert a record into CLHPF
				dm = new CLHRecordDataModel(globaCustomerNum);
				dm.setGlobalCustomerName(globaCustomerName);
				dm.setTimestamp(new java.sql.Timestamp(System.currentTimeMillis()));
				final ICLHRecordDao rpfDao = daoFactory.getCLHRecordDao(session, dm);
				rpfDao.insertRecord();
			}

			// We need to update the linked customers so first we delete all of them ...
			CLDRecordDataModel dm2 = new CLDRecordDataModel();
			final ICLDRecordDaoImp dao2 = daoFactory.getCLDRecordDao(session, dm2);
			List<AbsRecord> list2 = dao2.getRecordBy(" CLDGCID='" + globaCustomerNum + "'");

			// Loop through each of the records returned by the where clause (should be only one given that searchId is PK) ...
			for (AbsRecord item2 : list2)
			{
				// Delete !
				dao2.deleteRecord(item2);
			}

			// Then we re-insert the update ones ...
			for (CLDRecordDataModel dmLc : linkedCustomers)
			{
				dao2.insertRecord(dmLc);
			}

			// Finally Commit the changes & return the searchId PK ...
			session.connectionCommit();

			// Removed the cached version ...
			linkedCustomers = null;

			// Finally return the data model ....
			return dm.getGlobalCustomerName();
		}
		catch (EQException e)
		{
			try
			{
				session.connectionRollback();
			}
			catch (Exception e2)
			{
				throw new EQException(e2);

			}
			throw e;
		}
		catch (Exception e)
		{
			LOG.error("GlobalCustomersImpl: createGlobalCustomer() Failed: ");
			try
			{
				session.connectionRollback();
			}
			catch (Exception e2)
			{
				throw new EQException(e2);

			}
			throw new EQException(e);
		}
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public void deleteGlobalCustomer(String sessionId, String globaCustomerNum) throws EQException
	{
		// Do some basic validations on the session ...
		final EquationStandardSession session = validateSession(sessionId);

		try
		{
			// Now delete the global customer definition ...
			CLHRecordDataModel dm = new CLHRecordDataModel();
			final ICLHRecordDao dao = daoFactory.getCLHRecordDao(session, dm);
			List<AbsRecord> list = dao.getRecordBy(" CLHGCID='" + globaCustomerNum + "'");

			// Loop through each of the records returned by the where clause (should be only one given that searchId is PK) ...
			int deletedRecords = 0;
			for (AbsRecord item : list)
			{
				// Delete !
				dao.deleteRecord(item);
				deletedRecords++;
			}

			// User has confirmed this operation so we can go ahead and delete the linkage definitions ...
			CLDRecordDataModel dm2 = new CLDRecordDataModel();
			final ICLDRecordDaoImp dao2 = daoFactory.getCLDRecordDao(session, dm2);
			List<AbsRecord> list2 = dao2.getRecordBy(" CLDGCID='" + globaCustomerNum + "'");

			// Loop through each of the records returned by the where clause (should be only one given that searchId is PK) ...
			for (AbsRecord item2 : list2)
			{
				// Delete !
				dao2.deleteRecord(item2);
			}

			// Safety check, this condition should never be true unless there is a problem with the database ...
			if (deletedRecords > 1)
			{
				throw new EQException("More than 1 record was deleted for a given searchId PK [" + globaCustomerNum + "]");
			}

			// Finally Commit the changes ...
			session.connectionCommit();
		}
		catch (Exception e)
		{
			try
			{
				session.connectionRollback();
			}
			catch (Exception e2)
			{
				throw new EQException(e2);
			}
			throw new EQException(e);
		}
	}

	private CLDRecordDataModel[] getCustomerLinkages(String sessionId, String globaCustomerNum) throws EQException
	{
		// Do some basic validations on the session ...
		final EquationStandardSession session = validateSession(sessionId);

		// Look for the record in the database ...
		CLDRecordDataModel dm = new CLDRecordDataModel();
		final ICLDRecordDaoImp dao = daoFactory.getCLDRecordDao(session, dm);
		List<AbsRecord> list = dao.getRecordBy(" CLDGCID='" + globaCustomerNum + "' ");

		final CLDRecordDataModel[] arr = new CLDRecordDataModel[list.size()];

		// Return the data model ....
		if (list.size() > 0)
		{
			// Set the result in the working ordered list
			linkedCustomers = list.toArray(arr);
			// Return the list
			list.toArray(arr);

			// At this point we need to look up the customer information in the corresponding units ...
			final List<EquationStandardSession> sessions = EquationCommonContext.getContext()
							.getGlobalProcessingEquationStandardSessions(sessionId);
			boolean foundInUnit = false;
			// Loop through each of the items and fetch the customer information ...
			for (CLDRecordDataModel unitCustomer : arr)
			{
				for (EquationStandardSession currentSession : sessions)
				{
					// Get a connection for the unit and execute the statement to populate the customer information retrieved
					// from the unit ...
					if (currentSession.getUnitId().equals(unitCustomer.getCustomerOwningUnit()))
					{
						Statement statement = null;
						ResultSet rs = null;

						try
						{
							// Get a connection to the unit ...
							statement = currentSession.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
											ResultSet.CONCUR_READ_ONLY);
							// Execute the statement to retrieve the customer info against that unit ...
							rs = statement.executeQuery("SELECT GFCUS, GFCLC, GFCUN FROM " + UNIT_CUSTOMERS_TABLE_NAME
											+ " WHERE GFCPNC='" + unitCustomer.getCustomerNumber() + "'");
							// Finally populate the data retrieved into the data model ...
							if (rs.next())
							{
								unitCustomer.setCustMnemonic(rs.getString(1).trim());
								unitCustomer.setCustLocation(rs.getString(2).trim());
								unitCustomer.setCustName(rs.getString(3).trim());
							}
							foundInUnit = true;
							break; // We found what we are looking for so no need for us to continue ...
						}
						catch (SQLException e)
						{
							LOG.error("GlobalCustomersImpl: createGlobalCustomer() Failed: ");
							throw new EQException(e);
						}
						finally
						{
							SQLToolbox.close(rs);
							SQLToolbox.close(statement);
						}
					}
				}
			}
			if (!foundInUnit)
			{
				LOG.warn("No data was found for the global customer linkage in the unit customer file.");
			}

			Arrays.sort(arr);
			return arr;
		}
		else
		{
			return null;
		}
	}

	private CLDRecordDataModel[] getCustomerLinkagesInMemory(String sessionId, String globaCustomerNum) throws EQException
	{
		// If its not cached then we make a DB call for the first and only time during the process ...
		if (linkedCustomers == null)
		{
			linkedCustomers = getCustomerLinkages(sessionId, globaCustomerNum);
		}
		return linkedCustomers;
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public void updateUnitCustomerFlag(String sessionId, String globaCustomerNum, int index, String propertyId, boolean value)
					throws EQException
	{
		try
		{
			linkedCustomers[index].setFlag(propertyId, value);
		}
		catch (Exception e)
		{
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public int moveUnitCustomerLinkagePositionDown(String sessionId, String globaCustomerNum, int arrayPos) throws EQException
	{
		int oldPos = arrayPos;
		int newPos = arrayPos + 1;

		// Sequence numbers start at 1 not 0 (like array indexes)
		int oldSequenceNumber = oldPos + 1;
		int newSequenceNumber = newPos + 1;

		// If the customer is already at the bottom we will put him at the top, so in this case slightly different processing ...
		if (newPos >= linkedCustomers.length)
		{
			newPos = 0;
			newSequenceNumber = newPos + 1;

			// Put the one to be moved at the top ...
			linkedCustomers[oldPos].setSequenceNumber(newSequenceNumber);

			// All the rest we will put down one position ...
			for (int i = 0; i < linkedCustomers.length - 1; i++)
			{
				linkedCustomers[i].setSequenceNumber(linkedCustomers[i].getSequenceNumber() - 1);
			}
		}
		else
		{
			linkedCustomers[newPos].setSequenceNumber(oldSequenceNumber);
			linkedCustomers[oldPos].setSequenceNumber(newSequenceNumber);
		}

		// Finally we sort the array based on the sequence number ...
		Arrays.sort(linkedCustomers);

		return newPos;
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public int moveUnitCustomerLinkagePositionUp(String sessionId, String globaCustomerNum, int arrayPos) throws EQException
	{
		int oldPos = arrayPos;
		int newPos = arrayPos - 1;

		// Sequence numbers start at 1 not 0 (like array indexes)
		int oldSequenceNumber = oldPos + 1;
		int newSequenceNumber = newPos + 1;

		// If the customer is already at the top we will put him at the bottom, so in this case slightly different processing ...
		if (newPos < 0)
		{
			newPos = linkedCustomers.length - 1;
			newSequenceNumber = newPos + 1;

			// Put the one to be moved at the bottom ...
			linkedCustomers[oldPos].setSequenceNumber(newSequenceNumber);

			// All the rest we will put up one position ...
			for (int i = 0; i < newPos; i++)
			{
				linkedCustomers[i].setSequenceNumber(linkedCustomers[i].getSequenceNumber() - 1);
			}
		}
		else
		// easy! simply swap
		{
			linkedCustomers[newPos].setSequenceNumber(oldSequenceNumber);
			linkedCustomers[oldPos].setSequenceNumber(newSequenceNumber);
		}

		Arrays.sort(linkedCustomers);
		return newPos;
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public void deleteUnitCustomer(String sessionId, String globaCustomerNum, int arrayPos) throws EQException
	{
		// Create a new (smaller by 1) array to store the result ...
		CLDRecordDataModel[] newArr = new CLDRecordDataModel[linkedCustomers.length - 1];

		// {1,2,3,4,5} Array Position :3

		// Copy the old elements (only the ones prior to the deleted one arrayPos) one place up ...
		for (int i = 0; i < arrayPos; i++)
		{
			newArr[i] = linkedCustomers[i];
		}

		// Move the old elements (only the ones following the deleted one) one place up ...
		for (int i = arrayPos; i < newArr.length; i++)
		{
			newArr[i] = linkedCustomers[i + 1];
			newArr[i].setSequenceNumber(linkedCustomers[i + 1].getSequenceNumber());
		}

		linkedCustomers = newArr;
	}

	/**
	 * Getter for linked customers
	 * 
	 * @return CLDRecordDataModel - List of linked customers
	 */
	public CLDRecordDataModel[] getLinkedCustomers()
	{
		return linkedCustomers;
	}

	/**
	 * Setter for linked customers
	 * 
	 * @param CLDRecordDataModel
	 *            - List of linked customers
	 */
	public void setLinkedCustomers(CLDRecordDataModel[] linkedCustomers)
	{
		this.linkedCustomers = linkedCustomers;
	}

	/**
	 * @see GlobalCustomerFacade
	 */
	public void addUnitCustomer(final String sessionId, final String globaCustomerNum, final String systemName, final String unit,
					final String customerNumber, final String customerMnemonic, final String customerLocation,
					final String customerName) throws EQException
	{
		CLDRecordDataModel[] oldArr = linkedCustomers;
		// If we have no old array it means that we are adding for the first time ...
		if (oldArr == null)
		{
			oldArr = new CLDRecordDataModel[0];
		}
		String globalSyncID = "";
		// Create a new (large by 1) array to store the result ...
		final CLDRecordDataModel[] newArr = new CLDRecordDataModel[oldArr.length + 1];

		final int sequenceNumber = newArr.length; // TODO change this
		final CLDRecordDataModel newCustomer = new CLDRecordDataModel(globaCustomerNum, sequenceNumber, systemName, unit,
						customerNumber, false, globalSyncID);
		newCustomer.setCustMnemonic(customerMnemonic);
		newCustomer.setCustLocation(customerLocation);
		newCustomer.setCustName(customerName);

		// Copy the old elements (only the ones prior to the deleted one arrayPos) one place up ...
		for (int i = 0; i < oldArr.length; i++)
		{
			newArr[i] = oldArr[i];
		}

		newArr[oldArr.length] = newCustomer;

		// Replace the old to new array in the map ...
		linkedCustomers = newArr;
	}

	// PRIVATE METHODS

	private String createHTMLCell(final int width, final String value)
	{
		final StringBuffer htmlContent = new StringBuffer();
		final short multiplicator = 10;
		htmlContent.append("<TD width=\"" + multiplicator * width + "px\">");
		htmlContent.append(value);
		htmlContent.append("</TD>");
		return htmlContent.toString();
	}

	private String createHTMLCellWithCheckBox(final String globaCustomerNum, final int width, final String id, final int i,
					final boolean value)
	{
		final StringBuffer htmlContent = new StringBuffer();
		final short multiplicator = 10;
		htmlContent.append("<TD width=\"" + multiplicator * width + "px\">");
		htmlContent.append("<input class=\"wf_pr\" type=\"checkbox\" name=\"isMasterFlag\" id=\"" + id + i + "\" value=\""
						+ globaCustomerNum + "\" " + (value ? "checked" : "") + " onClick=\"flagCheckBoxTicked('"
						+ globaCustomerNum + "',  " + i + ", 'updatecustomer:" + id + "=' + document.getElementById('" + id + i
						+ "').checked, '$$$DIVGROUP_DATA')\">");
		htmlContent.append("</TD>");
		return htmlContent.toString();
	}

	/**
	 * This method validates the list of customers for the global customer and returns a list of errors (if any).
	 * 
	 * @param eqUser
	 *            EquationUser - Equation user used to access the properties
	 * @param eqUnit
	 *            EquationUnit - Equation unit used to access the properties
	 * @param arr
	 *            CLDRecordDataModel - List of customers to be linked to the global customer
	 * 
	 * @return ArrayList<String> - list of errors (if any) triggered by validation of business constraints
	 * 
	 */
	private String[] validateCLDRecordDataModel(final EquationUnit eqUnit, final EquationUser eqUser, final CLDRecordDataModel[] arr)
	{
		final List<String> errorList = new ArrayList<String>();

		int counter = 0;

		if (arr == null || arr.length == 0)
		{
			errorList.add(EquationCommonContext.getContext().getLanguageResource(eqUser,
							"globalcustomerlinking.maintlinkedcust.businesserrors.atleastonecustomer"));
		}
		else
		{
			for (CLDRecordDataModel item : arr)
			{
				if (item.isMasterFlag())
				{
					counter++;
				}
				// TODO hempensp - validation removed as synchronisation flags have been removed - will need to be re-instated in
				// phase 2 with new cross-validation.
				// if (item.isMasterFlag() == true )
				// {
				// bothMasterAndSyncSelected = true;
				// }
			}

			// Not more than one master customer per global customer
			if (counter > 1)
			{
				errorList.add(EquationCommonContext.getContext().getLanguageResource(eqUser,
								"globalcustomerlinking.maintlinkedcust.businesserrors.onlyonemastercustomer"));
			}

			// TODO hempensp - validation removed as synchronisation flags have been removed - will need to be re-instated in phase
			// 2 with new cross-validation.
			// Add validation – (Master and Sync cannot be both Y)
			// if (bothMasterAndSyncSelected)
			// {
			// errorList.add(eqUnit.getLanguageResource(eqUser,
			// "globalcustomerlinking.maintlinkedcust.businesserrors.bothmasterandsync"));
			// }

			// If no master than no records can be set to Sync ‘Y’

			// if (counter == 0)
			// {
			// for (CLDRecordDataModel item : arr)
			// {
			// if (item.isSyncBasicDetails() || item.isSyncOtherDetails() || item.isSyncFreeFormat() || item.isSyncAddresses()
			// || item.isSyncExtCustDetails() || item.isSyncAci() || item.isSyncAccountDetails())
			// {
			// errorList.add(eqUnit.getLanguageResource(eqUser,
			// "globalcustomerlinking.maintlinkedcust.businesserrors.nomasterdefined"));
			// break;
			// }
			// }
			// }
		}

		// only if Master = N or Sync = Y then all the rest can be Y

		String[] errorArr = new String[errorList.size()];
		errorList.toArray(errorArr);

		// Finally return the list of errors ...
		return errorArr;
	}

	private EquationStandardSession validateSession(final String sessionId) throws EQException
	{
		// Do some validations ...
		if (sessionId == null || sessionId.equals(""))
		{
			throw new EQException("The sessionId parameter is empty");
		}

		// Get the EquationStandardSession from the Session Id and EQ User ...
		final EquationUser eqUser = getEqUser(sessionId);
		final EquationStandardSession session = getEquationStandardSession(eqUser);

		if (eqUser == null)
		{
			throw new EQException("The eqUser parameter is empty");
		}

		if (session == null)
		{
			throw new EQException("The session parameter is empty");
		}
		return session;
	}

	/**
	 * Given a sessionId this method returns the EqUser by fetching it from EquationCommonContext.
	 */
	private EquationUser getEqUser(final String sessionId) throws EQException
	{
		final EquationUser eqUser = EquationCommonContext.getContext().getEquationUser(sessionId);
		// Check that we do have a eqUser ...
		if (eqUser == null)
		{
			throw new EQException("No user available for session.");
		}
		else
		{
			return eqUser;
		}
	}

	/**
	 * Given a EqUser this method returns the EquationStandardSession by fetching it from EqUser.
	 * 
	 * @param eqUser
	 * @return EquationStandardSession
	 * @throws EQException
	 */
	private EquationStandardSession getEquationStandardSession(final EquationUser eqUser) throws EQException
	{
		// Get the standard session ...
		final EquationStandardSession session = eqUser.getEquationUnit().getEquationSessionPool().getEquationStandardSession();
		// Check that we do have a session ...
		if (session == null)
		{
			throw new EQException("No session available.");
		}
		else
		{
			return session;
		}
	}

	private boolean isAlphaNumericNoBlanks(final String s)
	{
		final char[] chars = s.toCharArray();
		for (final char c : chars)
		{
			if ((c >= 'a') && (c <= 'z'))
			{
				continue; // lowercase
			}
			if ((c >= 'A') && (c <= 'Z'))
			{
				continue; // uppercase
			}
			if ((c >= '0') && (c <= '9'))
			{
				continue; // numeric
			}
			return false;
		}
		return true;
	}
}