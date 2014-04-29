package com.misys.equation.common.utilities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.SystemValue;
import com.ibm.as400.access.list.SpooledFileListItem;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public class SpooledFileToolbox
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SpooledFileToolbox.java 17526 2013-11-07 23:18:52Z williae1 $";
	/**
	 * Create a spooled file using the iSeries command supplied
	 * 
	 * @param session
	 * @param iSeriesCommand
	 * @param spooledFileName
	 * 
	 * @return information about the generated spooled file
	 * @throws Exception
	 */
	public static SpooledFileListItem createSpooledFile(EquationStandardSession session, String iSeriesCommand,
					String spooledFileName) throws Exception
	{
		EquationUser equationUser = EquationCommonContext.getContext().getEquationUser(session.getSessionIdentifier());
		EquationSystem equationSystem = EquationCommonContext.getContext().getEquationSystem(session.getSystemId());

		AS400 as400 = null;
		EqSpooledFileList userSFiles = null;
		try
		{
			// iSeries System date and time
			SystemValue sval = new SystemValue(equationSystem.getAS400SystemStatus().getSystem(), "QDATETIME");
			String dateTime = (String) sval.getValue();
			Calendar iSeriesDate = Toolbox.convertAS400TimeStampToCalendar(dateTime);

			// generate the spooled file
			generateSpooledFile(session, iSeriesCommand);

			// find the spooled file we just generated in the users spooled files. The spooled file we want is assumed to be the
			// last one for the spooled file type.
			as400 = equationSystem.getAS400();
			userSFiles = new EqSpooledFileList(as400, "userSpool");
			userSFiles.setFilterUser(equationUser.getUserId());
			userSFiles.setFilterCreationDate(iSeriesDate);
			userSFiles.setAttributesToRetrieve();
			userSFiles.open();
			userSFiles.sort();
			int total = userSFiles.getSortedList().size();

			SpooledFileListItem splf = null;
			for (int i = 0; i < total; i++)
			{
				// the last spooled file should be the one we want to view
				splf = userSFiles.getSortedList().get(i);
				if (splf != null)
				{
					if (!splf.getName().trim().equals(spooledFileName.trim()))
					{
						continue;
					}
				}
			}
			if (splf != null && splf.getName().trim().equals(spooledFileName.trim()))
			{
				return splf;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (userSFiles != null)
			{
				try
				{
					userSFiles.close();
				}
				catch (Exception e)
				{
					throw e;
				}
			}
			if (as400 != null)
			{
				equationSystem.returnAS400(as400);
			}
		}
	}
	/**
	 * Generate a spooled file using the iSeries command supplied
	 * 
	 * @param session
	 * @param command
	 * @throws Exception
	 */
	private static void generateSpooledFile(EquationStandardSession session, String command) throws Exception
	{
		String sqlStatement = SQLToolbox.getQcmdexcString(command);

		Statement statement = null;
		Connection connection = null;
		try
		{
			connection = session.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			statement.execute(sqlStatement);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (statement != null)
			{
				SQLToolbox.close(statement);
			}
		}
	}
}
