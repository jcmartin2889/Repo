package com.misys.equation.ui.helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.list.SpooledFileListItem;
import com.ibm.as400.access.list.SpooledFileOpenList;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class EqSpooledFileList
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqSpooledFileList.java 17507 2013-11-04 22:19:55Z williae1 $";

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
	 * Return the HTML equivalent for the spooled files
	 * 
	 * @param eqProperties
	 *            - properties
	 * @param equationUser
	 *            - Equation user
	 * 
	 * @return the spooled file list in HTML format
	 * 
	 * @throws Exception
	 */
	public String toHTML(Properties eqProperties, EquationUser equationUser) throws Exception
	{
		StringBuffer strHTML = new StringBuffer();
		SpooledFileListItem splf;
		int slot;
		String strDate;
		GregorianCalendar date = new GregorianCalendar();
		GregorianCalendar now = new GregorianCalendar();
		String[] strSlotName = EqDesktopToolBox.getSlotName(eqProperties, equationUser);
		StringBuffer[] strSlot = new StringBuffer[strSlotName.length];
		int[] nArrSlot = new int[strSlotName.length];

		// Initialise slot
		for (int i = 0; i < strSlot.length; i++)
		{
			strSlot[i] = new StringBuffer();
		}

		// Get each of the spooled file (maximum of TOTAL_ITEMS only)
		int total = sortedList.size();

		for (int i = 0; i < total; i++)
		{
			splf = sortedList.get(i);
			if (splf != null)
			{
				strDate = splf.getDateOpened();
				date.set(1900 + Integer.parseInt(strDate.substring(0, 3)), Integer.parseInt(strDate.substring(3, 5)) - 1, Integer
								.parseInt(strDate.substring(5, 7)));
				slot = EqDesktopToolBox.getSlot(now, date);
				strSlot[slot].append(formatSpooledFileToHTML(splf, equationUser.isLanguageRTL()));
				nArrSlot[slot]++;
			}
		}

		// Bang all slots into a single string
		boolean empty = true;
		for (int i = 0; i < strSlot.length; i++)
		{
			if (strSlot[i].length() > 0)
			{
				String text = "";
				if (equationUser.isLanguageRTL())
				{
					text = " (" + String.valueOf(nArrSlot[i]) + ")" + strSlotName[i];
				}
				else
				{
					text = strSlotName[i] + " (" + String.valueOf(nArrSlot[i]) + ")";
				}
				String ul = EqDesktopToolBox.formatUL(id + "_ul_" + i, strSlot[i].toString());
				String li = EqDesktopToolBox
								.formatLI(id + "_li_" + i, EqDesktopToolBox.formatSpan(text, "menuAndOptionsText") + ul);
				strHTML.append(li);
				empty = false;
			}
		}

		// No spooled files
		if (empty)
		{
			strHTML.append(EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL900022"));
		}
		return (strHTML.toString());
	}

	/**
	 * Return the HTML equivalent of a spooled file entry
	 * 
	 * @param splf
	 *            - spooled file
	 * @param rtl
	 *            - right to left?
	 * 
	 * @return the equivalent HTML
	 * 
	 * @throws Exception
	 */
	private String formatSpooledFileToHTML(SpooledFileListItem splf, boolean rtl) throws Exception
	{
		String htmlStr = null;
		String delimeter = " ";

		// on click action
		int totalPageNumber = splf.getTotalPages();
		String onClickAction = "javascript:showSpooledFile(" + "'" + splf.getName() + "'," + "'" + splf.getJobName() + "'," + "'"
						+ splf.getJobUser() + "'," + "'" + splf.getJobNumber() + "'," + splf.getNumber() + "," + totalPageNumber
						+ "," + pagesize + "," + maxpagesize + ")";

		// outqueue
		String outq = splf.getOutputQueueName();

		// user status
		String status = splf.getStatus();
		if (status.equals("*CLOSED"))
		{
			status = "CLO";
		}
		else if (status.equals("*HELD"))
		{
			status = "HLD";
		}
		else if (status.equals("*MESSAGE"))
		{
			status = "MSGW";
		}
		else if (status.equals("*OPEN"))
		{
			status = "OPN";
		}
		else if (status.equals("*PENDING"))
		{
			status = "PND";
		}
		else if (status.equals("*PRINTER"))
		{
			status = "PRT";
		}
		else if (status.equals("*READY"))
		{
			status = "RDY";
		}
		else if (status.equals("*SAVED"))
		{
			status = "SAV";
		}
		else if (status.equals("*WRITING"))
		{
			status = "WTR";
		}

		// hover text
		String hoverText = splf.getName() + delimeter + splf.getJobName() + delimeter + splf.getJobUser() + delimeter
						+ splf.getJobNumber() + delimeter + splf.getNumber();

		// id
		String id = this.id + hoverText;

		// date
		String sdate = Toolbox.formatDate(splf.getDateOpened());
		String stime = Toolbox.formatTime(splf.getTimeOpened(), 6);

		// text
		StringBuffer text = new StringBuffer();
		if (rtl)
		{
			text.append("(" + splf.getTotalPages() + ")");
			text.append(status + delimeter);
			text.append(splf.getUserData() + delimeter);
			text.append(outq + delimeter);
			text.append(splf.getJobUser() + delimeter);
			text.append(splf.getName() + delimeter);
			text.append(stime + delimeter);
			text.append(sdate + delimeter);
		}
		else
		{
			text.append(sdate + delimeter);
			text.append(stime + delimeter);
			text.append(splf.getName() + delimeter);
			text.append(splf.getJobUser() + delimeter);
			text.append(outq + delimeter);
			text.append(splf.getUserData() + delimeter);
			text.append(status + delimeter);
			text.append("(" + splf.getTotalPages() + ")");
		}

		String key = "event,this" + ",'" + splf.getName() + "'," + "'" + splf.getJobName() + "'," + "'" + splf.getJobUser() + "',"
						+ "'" + splf.getJobNumber() + "'," + splf.getNumber();
		String contextMenu = " oncontextmenu=\"return(spooledFile_Popoup(" + key + "))\" ";
		String anchor = EqDesktopToolBox.formatIntoAnchorHTML(EqDesktopToolBox.formatSpan(text.toString(), ""), hoverText,
						onClickAction, "", contextMenu);
		htmlStr = EqDesktopToolBox.formatLI(id, anchor);

		return (htmlStr);
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
