package com.misys.equation.common.utilities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.list.SpooledFileListItem;
import com.ibm.as400.access.list.SpooledFileOpenList;
import com.misys.equation.common.access.EquationCommonContext;

public class EqSpooledFileList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqSpooledFileList.java 17525 2013-11-07 23:14:48Z williae1 $";

	private static final EquationLogger LOG = new EquationLogger(EqSpooledFileList.class);

	private static final int TOTAL_ITEMS = 1000;
	private final SpooledFileOpenList splfList;
	private List<SpooledFileListItem> sortedList;
	private final String id;
	private long pagesize;
	private long maxpagesize;

	/**
	 * Construct a new spooled file list
	 * 
	 * @param eqAS400
	 *            - AS400 connection
	 */
	public EqSpooledFileList(AS400 eqAS400, String id)
	{
		this.id = id;
		this.splfList = new SpooledFileOpenList(eqAS400);

		// default page size to show to the user
		this.pagesize = Toolbox.parseLong(EquationCommonContext.getConfigProperty("eq.spooledfile.pagesize"), 0);

		// maximum page size before warning is shown to the user
		this.maxpagesize = Toolbox.parseLong(EquationCommonContext.getConfigProperty("eq.spooledfile.maxpagesize"), 0);
	}

	/**
	 * Default spooled file attribute to retrieve
	 * 
	 */
	public void setAttributesToRetrieve()
	{
		// Set attribute
		splfList.addSortField(SpooledFileOpenList.DATE_OPENED, false);
		splfList.addSortField(SpooledFileOpenList.TIME_OPENED, false);
		splfList.setFormat(SpooledFileOpenList.FORMAT_0300);
	}

	/**
	 * Filter the list of spooled files to the specified user id and queue
	 * 
	 * @param userID
	 *            - user id
	 * @param filter
	 *            - queue
	 * 
	 * @throws Exception
	 */
	public void getSpooledFiles(String userID, String filter) throws Exception
	{
		if (userID != null)
		{
			splfList.setFilterUsers(new String[] { userID });
		}

		if (filter != null)
		{
			splfList.setFilterOutputQueues(new String[] { filter });
		}
	}

	/**
	 * Filter the list of spooled files to the specified user id
	 * 
	 * @param userID
	 *            - user id
	 * @throws Exception
	 */
	public void setFilterUser(String userID) throws Exception
	{
		if (userID != null)
		{
			splfList.setFilterUsers(new String[] { userID });
		}
	}

	/**
	 * Filter the list of spooled files to the specified creation date
	 * 
	 * @param cyymmddDate
	 *            - creation date in CYYMMDD format
	 * @throws Exception
	 */
	public void setFilterCreationDate(int cyymmddDate) throws Exception
	{

		if (cyymmddDate > 0)
		{
			Calendar calendarStart = Toolbox.parseEqDate(Integer.toString(cyymmddDate));
			Calendar calendarEnd = Toolbox.parseEqDate(Integer.toString(cyymmddDate));
			calendarEnd.set(Calendar.HOUR, 24);
			calendarEnd.set(Calendar.MINUTE, 00);
			calendarEnd.set(Calendar.SECOND, 00);
			splfList.setFilterCreationDate(calendarStart.getTime(), calendarEnd.getTime());
		}
	}

	/**
	 * Filter the list of spooled files to the specified creation date
	 * 
	 * @param calendarStart
	 *            - creation date
	 * @throws Exception
	 */
	public void setFilterCreationDate(Calendar calendarStart) throws Exception
	{
		if (calendarStart != null)
		{
			Calendar calendarEnd = Calendar.getInstance();
			calendarEnd.set(Calendar.YEAR, calendarStart.get(Calendar.YEAR));
			calendarEnd.set(Calendar.MONTH, calendarStart.get(Calendar.MONTH));
			calendarEnd.set(Calendar.DAY_OF_MONTH, calendarStart.get(Calendar.DAY_OF_MONTH));
			calendarEnd.set(Calendar.HOUR, 24);
			calendarEnd.set(Calendar.MINUTE, 00);
			calendarEnd.set(Calendar.SECOND, 00);
			splfList.setFilterCreationDate(calendarStart.getTime(), calendarEnd.getTime());
		}
	}
	/**
	 * Filter the list of spooled files to the specified job
	 * 
	 * @param jobName
	 *            - job name
	 * @param jobUser
	 *            - job user
	 * @param jobNumber
	 *            - job number
	 * @throws Exception
	 */
	public void setFilterJob(String jobName, String jobUser, String jobNumber) throws Exception
	{

		splfList.setFilterJobInformation(jobName, jobUser, jobNumber);

	}
	/**
	 * Filter the list of spooled files to the specified device
	 * 
	 * @param device
	 *            - device
	 * @throws Exception
	 */
	public void setFilterDevice(String device) throws Exception
	{
		if (device != null)
		{
			splfList.setFilterDevices(new String[] { device });
		}
	}

	/**
	 * Filter the list of spooled files to the specified form type
	 * 
	 * @param formType
	 *            - form type
	 * @throws Exception
	 */
	public void setFilterFormType(String formType) throws Exception
	{
		if (formType != null)
		{
			splfList.setFilterFormType(formType);
		}
	}

	/**
	 * Filter the list of spooled files to the specified output queue
	 * 
	 * @param outQueue
	 *            - output queue
	 * @throws Exception
	 */
	public void setFilterOutQueue(String outQueue) throws Exception
	{
		if (outQueue != null)
		{
			splfList.setFilterOutputQueues(new String[] { outQueue });
		}
	}

	/**
	 * Filter the list of spooled files to the specified status
	 * 
	 * @param status
	 *            - status
	 * @throws Exception
	 */
	public void setFilterStatus(String status) throws Exception
	{
		if (status != null)
		{
			splfList.setFilterStatuses(new String[] { status });
		}
	}

	/**
	 * Filter the list of spooled files to the specified user data
	 * 
	 * @param userData
	 *            - user data
	 * @throws Exception
	 */
	public void setFilterUserData(String userData) throws Exception
	{
		if (userData != null)
		{
			splfList.setFilterUserData(userData);
		}
	}
	/**
	 * Retrieve the spooled files
	 * 
	 * @throws Exception
	 */
	public void open() throws Exception
	{
		splfList.open();
		transferToArray();
		splfList.close();
	}

	/**
	 * Close the spooled file connection
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception
	{
		splfList.close();
	}

	/**
	 * Transfer the spooled file list to an array
	 * 
	 * @return the array of spooled files
	 */
	protected List<SpooledFileListItem> transferToArray() throws Exception
	{
		sortedList = new ArrayList<SpooledFileListItem>();
		Enumeration<SpooledFileListItem> entry = splfList.getItems();
		int count = 0;
		while (entry.hasMoreElements())
		{
			SpooledFileListItem item = (SpooledFileListItem) entry.nextElement();
			sortedList.add(item);
			count++;
			if (count >= TOTAL_ITEMS)
			{
				break;
			}
		}

		return (sortedList);
	}

	/**
	 * Sort the array based on the DateTimeOrder comparator
	 * 
	 * @throws Exception
	 */
	public void sort() throws Exception
	{
	}

	/**
	 * Return the sorted spooled file list
	 * 
	 * @return the sorted spooled file list
	 */
	public List<SpooledFileListItem> getSortedList()
	{
		return sortedList;
	}

}
