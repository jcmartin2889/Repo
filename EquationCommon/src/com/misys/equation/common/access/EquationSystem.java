package com.misys.equation.common.access;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400ConnectionPool;
import com.ibm.as400.access.AS400JDBCDataSource;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CharacterDataArea;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ConnectionPoolException;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.IllegalObjectTypeException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.SystemStatus;
import com.misys.equation.common.internal.eapi.core.EQActionErrorException;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.language.LanguageResources;
import com.misys.equation.common.utilities.AS400Toolbox;
import com.misys.equation.common.utilities.EquationControl;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This class represents an AS400 system
 */
public class EquationSystem
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationSystem.java 17621 2013-11-27 02:50:04Z williae1 $";

	// Logger instance
	private static final EquationLogger LOG = new EquationLogger(EquationSystem.class);

	private AS400ConnectionPool aS400Pool;
	private AS400 as400;

	private SystemStatus as400SystemStatus = null;

	private final Hashtable<String, EquationUnit> units = new Hashtable<String, EquationUnit>();
	private String systemId;
	private String systemUserId;
	private String systemPassword;
	private String baseLibrary;

	/** Has the EQSEC file been installed into base? */
	private boolean isEQSECInBase;

	/**
	 * Construct an empty system
	 */
	@SuppressWarnings("unused")
	private EquationSystem()
	{
	}

	/**
	 * Construct a System
	 * 
	 * @param systemId
	 *            - the system Id
	 * @param userId
	 *            - the user Id
	 * @param password
	 *            - the user's password
	 * 
	 * @throws EQException
	 */
	public EquationSystem(String systemId, String userId, String password) throws EQException
	{
		Connection connection = null;
		try
		{
			// we are assuming that the AS400 we get is a good one,
			// i.e. it has sufficient authority to read system objects
			as400 = AS400Toolbox.getAS400(systemId);
			as400.setGuiAvailable(false);
			as400.setUserId(userId);
			as400.setPassword(password);
			this.systemId = systemId;
			systemUserId = userId;
			systemPassword = password;
			baseLibrary = (new CharacterDataArea(as400, "/QSYS.LIB/QGPL.LIB/KAPBASELIB.DTAARA")).read().trim();

			// create the W96HMR stored procedure
			connection = getConnectionFromAS400();
			// First off get some version information about all the units on the system...
			refreshSystemInformation();

			// Create the SQL objects for the base library...
			EquationControl.createBaseSQLObjects(connection, baseLibrary);

			// Add the system into the context
			EquationCommonContext.getContext().addEquationSystem(this);

			// Initialise our non-JDBC connection pool
			initialisePool();
		}
		catch (Exception e)
		{
			throw new EQException("EqSystem: Failed", e);
		}
		finally
		{
			SQLToolbox.close(connection);
		}
	}

	/**
	 * Obtains a database Connection from the Toolbox AS400 instance
	 * 
	 * @return Connection
	 * @throws SQLException
	 */
	private Connection getConnectionFromAS400() throws SQLException
	{
		AS400JDBCDataSource ds = new AS400JDBCDataSource(as400);
		ds.setSecure(AS400Toolbox.isSecure());
		ds.setPrompt(false);
		ds.setTranslateBinary(false);
		ds.setNaming("system");
		ds.setLibraries("*LIBL");
		return ds.getConnection();
	}

	/**
	 * Initialise the pool of Toolbox AS400 objects
	 * 
	 * @throws EQException
	 *             if there is any error that one will be logged in the output and an exception will be thrown.
	 */
	private void initialisePool() throws EQException
	{
		ExecutorService service = Executors.newFixedThreadPool(2);

		service.execute(new Runnable()
		{
			public void run()
			{
				// initialise the AS400 connection pool
				createAS400ConnectionPool();
			}
		});

		service.shutdown();

		try
		{
			service.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Initialise the <code>AS400ConnectionPool</code>
	 */
	private void createAS400ConnectionPool()
	{
		try
		{
			// Create an AS400ConnectionPool.
			aS400Pool = new AS400ConnectionPool();
			// Set a maximum of "no max" connections to this pool.
			aS400Pool.setMaxConnections(-1);
			// Set a maximum lifetime for 24 hours for connections (ms*s*m*h).
			aS400Pool.setMaxLifetime(1000 * 60 * 60 * 24);
			// Fill'em up (10 is an accepted "best guess" (in my book anyway))
			aS400Pool.fill(systemId, systemUserId, systemPassword, AS400.CENTRAL, 10);
		}
		catch (ConnectionPoolException connectionPoolException)
		{
			LOG.error("There was problem creating AS400ConnectionPool.");
			closeAS400ConnectionPool();
		}
	}

	/**
	 * This method will close <code>AS400ConnectionPool</code> resource.
	 */
	private void closeAS400ConnectionPool()
	{
		if (aS400Pool != null)
		{
			aS400Pool.close();
		}
	}

	/**
	 * Refresh the EQSEC flag for this system
	 * 
	 * @throws PropertyVetoException
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws ErrorCompletingRequestException
	 * @throws AS400SecurityException
	 */
	private void refreshSystemInformation() throws PropertyVetoException, InterruptedException, IOException,
					ErrorCompletingRequestException, AS400SecurityException
	{
		CommandCall commandCall = new CommandCall(as400);
		if (commandCall.run("CHKOBJ " + baseLibrary + "/eqsec *FILE"))
		{
			isEQSECInBase = true;
		}
	}

	/**
	 * Add a unit with the specified user. This method is synchronises based on the 'unitId' String value in order to avoid creating
	 * two instances of a unit unnecessarily when accessed in a multi-threaded fashion.
	 * 
	 * @param unitId
	 *            - the unit Id
	 */
	private void addUnit(String unitId, String userId, String password, boolean useJNDI) throws EQException
	{
		try
		{
			// synchronise just based on the Unit ID to allow simultaneous access for different units
			synchronized (unitId.intern())
			{
				EquationUnit unit = new EquationUnit(this, unitId, userId, password, useJNDI);
				units.put(unitId, unit);
			}
		}
		catch (Exception e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationSystem.UnableToCreateUnit", new String[] { unitId,
							systemUserId, systemId, Toolbox.getExceptionMessage(e) }), e);
		}
	}

	/**
	 * Determine whether the unit exists in a system
	 * 
	 * @param unitId
	 *            - the unit id
	 * 
	 * @return true if the unit exists in a system
	 */
	protected boolean unitExists(String unitId)
	{
		try
		{
			unitExists(unitId, as400);
			as400.disconnectAllServices();
		}
		catch (NullPointerException e) // eqlibl dtaara doesn't exist in klib
		{
			return false;
		}
		return true;
	}

	/**
	 * Return an AS400 connection
	 * 
	 * @return an Equation AS400
	 */
	public AS400 getAS400() throws EQException
	{
		AS400 as400 = null;
		try
		{
			AS400 eqAS400 = AS400Toolbox.getAS400(systemId);
			if (eqAS400.authenticate(systemUserId, systemPassword))
			{
				as400 = AS400Toolbox.getConnection(aS400Pool, systemId, systemUserId, systemPassword, AS400.CENTRAL);
			}
			else
			{
				throw new EQException(LanguageResources.getString("EquationSessionPool.GUIAdmin"));
			}
		}
		catch (ConnectionPoolException connectionPoolException)
		{
			LOG.error(connectionPoolException);
			throw new EQException(LanguageResources.getString("EquationSessionPool.GUIAdmin"), connectionPoolException);
		}
		catch (AS400SecurityException e)
		{
			LOG.error(e);
			throw new EQException(LanguageResources.getString("EquationSessionPool.GUIAdmin"), e);
		}
		catch (IOException e)
		{
			LOG.error(e);
			throw new EQException(e);
		}
		return as400;
	}

	/**
	 * Return a AS400 object back to the connection pool
	 * 
	 * @param as400
	 *            The AS400 object
	 */
	public void returnAS400(AS400 as400)
	{
		aS400Pool.returnConnectionToPool(as400);
	}

	/**
	 * Determine whether the unit exists in a system
	 * 
	 * @param unitId
	 *            - the unit id
	 * @param eqAS400
	 *            - the AS400 connection
	 * 
	 * @return true if the unit exists in a system
	 */
	public boolean unitExists(String unitId, AS400 eqAS400)
	{
		boolean success = false;
		try
		{
			// try and get the EQLIBL from the KLIB
			(new CharacterDataArea(eqAS400, "/QSYS.LIB/KLIB" + unitId.trim() + ".LIB/EQLIBL.DTAARA")).read();
			success = true;
		}
		catch (AS400SecurityException e)
		{
		}
		catch (IllegalObjectTypeException e)
		{
		}
		catch (ErrorCompletingRequestException e)
		{
		}
		catch (InterruptedException e)
		{
		}
		catch (IOException e)
		{
		}
		catch (ObjectDoesNotExistException e)
		{
		}
		return success;
	}

	/**
	 * Determine whether the unit is in EOD
	 * 
	 * @param unitId
	 *            - the unit Id
	 * 
	 * @return true if the unit is in EOD
	 * 
	 * @throws EQException
	 */
	public boolean unitInEOD(String unitId) throws EQException
	{
		// Look for KAPPHS
		try
		{
			// try and get the EQLIBL from the KLIB
			String kapphs = (new CharacterDataArea(as400, "/QSYS.LIB/KLIB" + unitId.trim() + ".LIB/KAPPHS.DTAARA")).read().trim();
			as400.disconnectAllServices();
			if (!kapphs.equals("DAY"))
			{
				return true;
			}
		}
		catch (AS400SecurityException e)
		{
			throw new EQException("EqSystem: unitInEOD Failed", e);
		}
		catch (IllegalObjectTypeException e)
		{
			throw new EQException("EqSystem: unitInEOD Failed", e);
		}
		catch (ErrorCompletingRequestException e)
		{
			throw new EQException("EqSystem: unitInEOD Failed", e);
		}
		catch (InterruptedException e)
		{
			throw new EQException("EqSystem: unitInEOD Failed", e);
		}
		catch (IOException e)
		{
			throw new EQException("EqSystem: unitInEOD Failed", e);
		}
		catch (ObjectDoesNotExistException e)
		{
			throw new EQException("EqSystem: unitInEOD Failed", e);
		}
		return false;
	}

	/**
	 * Determine whether the unit is in EOD swap for 24x7 processing
	 * 
	 * @param unitId
	 *            - the unit Id
	 * 
	 * @return true if the unit is in EOD swap for 24x7 processing
	 */
	public boolean unitSwapInProgress(String unitId)
	{
		// Look for EQN0EDS data area
		try
		{
			(new CharacterDataArea(as400, "/QSYS.LIB/QGPL.LIB/EQN0EDS" + unitId.trim() + ".DTAARA")).read();
			as400.disconnectAllServices();
		}
		// object lock
		catch (ErrorCompletingRequestException e)
		{
			return true;
		}
		catch (AS400SecurityException e)
		{
			return false;
		}
		catch (IllegalObjectTypeException e)
		{
			return false;
		}
		catch (InterruptedException e)
		{
			return false;
		}
		catch (IOException e)
		{
			return false;
		}
		catch (ObjectDoesNotExistException e)
		{
			return false;
		}
		return false;
	}

	/**
	 * Return the list of units the user is authorised to
	 * 
	 * @param userId
	 *            - the user Id
	 * 
	 * @return the list of units in String array format
	 * 
	 * @throws EQException
	 */
	public String[] getAuthorisedUnits(String userId) throws EQException
	{
		Connection connection = null;
		try
		{
			connection = getConnectionFromAS400();
			return getAuthorisedUnits(userId, connection);
		}
		catch (SQLException sqle)
		{
			throw new EQException("EqSystem.getAuthorisedUnits: failed", sqle);
		}
		finally
		{
			SQLToolbox.close(connection);
		}
	}

	/**
	 * Return the list of units the user is authorised to
	 * 
	 * @param userId
	 *            - the user Id
	 * @param connection
	 *            - the connection
	 * 
	 * @return the list of units in String array format
	 * 
	 * @throws EQException
	 */
	private String[] getAuthorisedUnits(String userId, Connection connection) throws EQException
	{
		PreparedStatement newsqlStatement = null;
		ResultSet rs = null;
		try
		{
			final String sqlString = "SELECT SECUN1,SECUN2,SECUN3,SECUN4,SECUN5,SECUN6,SECUN7,SECUN8,SECUN9,SECUN10 FROM "
							+ baseLibrary + "/KAPSEC WHERE SECUSR =?";
			newsqlStatement = connection.prepareStatement(sqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			newsqlStatement.setString(1, Toolbox.removeSQLChars(userId));
			rs = newsqlStatement.executeQuery();
			StringBuffer currUnits = new StringBuffer();
			// move cursor to first record
			if (rs.next())
			{
				for (int x = 1; x < 11; x++)
				{
					String unit = rs.getString(x).trim();
					if (unit.length() > 0) // Skip blank entries
					{
						currUnits.append(unit + ',');
					}
				}
			}
			String[] result = currUnits.toString().split(",");

			if (LOG.isDebugEnabled())
			{
				LOG.debug("getAuthorisedUnits - returning [" + currUnits + "]");
			}
			return result;
		}
		catch (SQLException sqle)
		{
			throw new EQException("EqSystem.getAuthorisedUnitss: failed", sqle);
		}
		finally
		{
			SQLToolbox.close(rs);
			SQLToolbox.close(newsqlStatement);
		}
	}

	/**
	 * Return the list of units the user is authorised to based on the EQSEC file.
	 * 
	 * This method obtains its own database connection
	 * 
	 * @param userId
	 *            - the user Id
	 * 
	 * @return a list of unit mnemonics that the user is authorised to
	 * 
	 * @throws EQException
	 */
	public List<String> getEqSecAuthorisedUnits(String userId) throws EQException
	{
		Connection connection = null;
		try
		{
			connection = getConnectionFromAS400();
			return getEqSecAuthorisedUnits(userId, connection);
		}
		catch (SQLException sqle)
		{
			throw new EQException("getEqSecAuthorisedUnits: failed", sqle);
		}
		finally
		{
			SQLToolbox.close(connection);
		}
	}

	/**
	 * Return the list of units the user is authorised to based on the EQSEC file
	 * 
	 * @param userId
	 *            - the user Id
	 * @param connection
	 *            - the connection
	 * 
	 * @return the list of units authorised (used toArray() if you need an array)
	 * 
	 * @throws EQException
	 */
	private List<String> getEqSecAuthorisedUnits(String userId, Connection connection) throws EQException
	{
		PreparedStatement newsqlStatement = null;
		ResultSet rs = null;
		try
		{
			final String sqlString = "SELECT SECUNIT FROM " + baseLibrary + "/EQSEC WHERE SECUSER = ?";
			newsqlStatement = connection.prepareStatement(sqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			newsqlStatement.setString(1, userId.toUpperCase());
			rs = newsqlStatement.executeQuery();

			final List<String> units = new ArrayList<String>();
			while (rs.next())
			{
				final String unit = rs.getString(1).trim();
				if (unit.length() > 0) // Skip blank entries
				{
					units.add(unit);
				}
			}

			if (LOG.isDebugEnabled())
			{
				LOG.debug("getEqSecAuthorisedUnits - returning [" + units + "]");
			}

			return units;
		}
		catch (SQLException sqle)
		{
			throw new EQException("EqSystem.getEqSecAuthorisedUnits: failed", sqle);
		}
		finally
		{
			SQLToolbox.close(rs);
			SQLToolbox.close(newsqlStatement);
		}
	}

	/**
	 * Determine if the user is authorised to a unit
	 * 
	 * @param unitId
	 *            - the unit Id
	 * @param userId
	 *            - the user Id
	 * 
	 * @return true if the user is authorised to the unit
	 * 
	 * @throws EQException
	 */
	public boolean userAuthorisedToUnit(String unitId, String userId) throws EQException
	{
		String unitToFind = unitId.trim();
		List<String> units = getEqSecAuthorisedUnits(userId);
		return units.contains(unitToFind);
	}

	/**
	 * Determine if the user is the administrator
	 * 
	 * @param unitId
	 *            - the unit Id
	 * @param userId
	 *            - the user Id
	 * 
	 * @return true if the user is the desktop administrator
	 */
	private boolean isAdministrator(String unitId, String userId)
	{
		try
		{
			// Get the user id of the administrator for the unit being processed
			String administrator = (new CharacterDataArea(as400, "/QSYS.LIB/KFIL" + unitId.trim() + ".LIB/DASYSCT2.DTAARA")).read(
							1674, 10).trim();
			as400.disconnectAllServices();
			return (administrator.trim().equalsIgnoreCase(userId.trim()));
		}
		catch (ErrorCompletingRequestException e)
		{
			return false;
		}
		catch (AS400SecurityException e)
		{
			return false;
		}
		catch (IllegalObjectTypeException e)
		{
			return false;
		}
		catch (InterruptedException e)
		{
			return false;
		}
		catch (IOException e)
		{
			return false;
		}
		catch (ObjectDoesNotExistException e)
		{
			return false;
		}
	}

	/**
	 * Validate the user id and password against the unit in the specified system
	 * 
	 * @param unitId
	 *            - the unit Id
	 * @param userId
	 *            - the user Id
	 * @param password
	 *            - the user password
	 * 
	 * @return the error code
	 * 
	 * @throws EQActionErrorException
	 * 
	 * @throws EQException
	 */
	public String authenticate(String unitId, String userId, String password) throws EQActionErrorException, EQException
	{
		String ae = null;
		boolean isAuthorisedToUnit = false;

		// Validate the GUI administrator password
		try
		{
			as400.authenticate(systemUserId, systemPassword);
		}
		catch (Exception e)
		{
			units.remove(unitId);
			if (userId.equals(systemUserId))
			{
				try
				{
					if (!systemPassword.equals(password))
					{
						as400.authenticate(userId, password);
						systemPassword = password;
						as400.setPassword(password);
					}
					else
					{
						throw e;
					}
				}
				catch (AS400SecurityException e2)
				{
					LOG.error(e2);
					throw new EQActionErrorException("authenticate: failed", e2, "error.invalid.message", Toolbox
									.getExceptionMessage(e2));
				}
				catch (IOException e2)
				{
					LOG.error(e2);
					return "error.invalid.login";
				}
				catch (Exception e2)
				{
					LOG.error(e2);
					return "error.invalid.login";
				}
			}
			else
			{
				ae = "error.invalid.guiadmin";
				return ae;
			}
		}

		// Do we already have the unit defined in this system
		if (!units.containsKey(unitId))
		{
			ae = newUnitChecks(unitId, userId);
		}
		else
		{
			Connection eqConnection = null;
			try
			{
				// Validate the user password
				as400.authenticate(userId, password);

				// Get the connection from the context
				eqConnection = units.get(unitId).getEquationSessionPool().getConnection(userId);
				isAuthorisedToUnit = userAuthorisedToUnit(unitId, userId);
			}
			catch (Exception e)
			{
				throw new EQActionErrorException("authenticate: failed", e, "error.invalid.message", Toolbox.getExceptionMessage(e));
			}
			finally
			{
				units.get(unitId).getEquationSessionPool().returnConnectionToPool(eqConnection);
			}
			if (!isAuthorisedToUnit)
			{
				ae = "error.invalid.unitAuth";
				LOG.error(ae);
			}
		}
		return ae;
	}

	/**
	 * Validation required when initializing a unit
	 * 
	 * @param unitId
	 *            - the unit Id
	 * @param userId
	 *            - the user Id
	 * 
	 * @return A string containing a struts ActionError code
	 * 
	 * @throws EQException
	 */
	public String newUnitChecks(String unitId, String userId) throws EQException
	{
		String ae = null;

		// need to actually check if it exists on the system
		if (!unitExists(unitId))
		{
			ae = "error.invalid.unit";
			LOG.error(ae);
		}
		else if (!userAuthorisedToUnit(unitId, userId))
		{
			ae = "error.invalid.unitAuth";
			LOG.error(ae);
		}
		else if (!isAdministrator(unitId, userId))
		{
			ae = "error.invalid.admin";
			LOG.error(ae);
		}
		else if (unitSwapInProgress(unitId))
		{
			ae = "error.invalid.unitunavailable";
			LOG.error(ae);
		}
		else if (unitInEOD(unitId))
		{
			ae = "error.invalid.unitEOD";
			LOG.error(ae);
		}
		return ae;
	}

	/**
	 * Remove the unit from the list
	 * 
	 * @param unitId
	 *            - the unit Id
	 */
	public void removeUnit(String unitId)
	{
		if (units.containsKey(unitId))
		{
			EquationUnit unit = units.get(unitId);
			unit.close();
			units.remove(unit.getUnitId());
			unit.setAlive(false);
			unit = null;
		}
	}

	/**
	 * Return the unit
	 * 
	 * @param unitId
	 *            - the unit id
	 * 
	 * @return the unit
	 */
	public EquationUnit getUnit(String unitId) throws EQException
	{
		return getUnit(unitId, systemUserId, systemPassword);
	}

	/**
	 * Return a unit. This method is synchronises based on the 'unitId' String value in order to avoid creating two instances of a
	 * unit unnecessarily when accessed in a multi-threaded fashion.
	 * 
	 * @param unitId
	 *            - the unit id
	 * @param userId
	 * @param password
	 * @return the unit
	 * @throws EQException
	 */
	public EquationUnit getUnit(String unitId, String userId, String password) throws EQException
	{
		// synchronise just based on the Unit ID to allow simultaneous access for different units
		synchronized (unitId.intern())
		{
			LOG.debug("getUnit - entry");
			EquationUnit unit = null;
			if (units.containsKey(unitId))
			{
				unit = units.get(unitId);
			}
			else
			{
				addUnit(unitId, userId, password, EquationCommonContext.getContext().isJNDI());
				unit = units.get(unitId);
			}
			LOG.debug("getUnit - exit");
			return unit;
		}
	}

	/**
	 * Return the system Id
	 * 
	 * @return the system Id
	 */
	public String getSystemId()
	{
		return systemId;
	}

	/**
	 * Return the list of units
	 * 
	 * @return the list of units
	 */
	public Hashtable<String, EquationUnit> getUnits()
	{
		return units;
	}

	/**
	 * Return the system level user id, usually the Desktop administrator
	 * 
	 * @return the system level user id
	 */
	public String getSystemUserId()
	{
		return systemUserId;
	}

	/**
	 * Return the base library
	 * 
	 * @return the base library
	 */
	public String getBaseLibrary()
	{
		return baseLibrary;
	}

	/**
	 * Return the version of the unit, it will be either EQ3 or EQ4
	 * 
	 * @return the version as <code>VERSION_EQ342</code> or <code>VERSION_EQ38</code> or <code>VERSION_EQ4</code>
	 */
	public String getUnitVersion(String unitId)
	{
		if (units.containsKey(unitId))
		{
			EquationUnit unit = units.get(unitId);
			return unit.getUnitVersion();
		}

		// Default behaviour
		return EquationUnit.VERSION_EQ4;
	}

	/**
	 * Determine whether EQSEC is in the base library or not
	 * 
	 * @return true if EQSEC is in the base library
	 */
	public boolean isEQSECInBase()
	{
		return isEQSECInBase;
	}

	public boolean checkUnitEnhancement(String unit, String enhancement) throws EQException
	{
		Connection connection = null;
		try
		{
			AS400JDBCDataSource ds = new AS400JDBCDataSource(as400);
			ds.setSecure(AS400Toolbox.isSecure());
			ds.setPrompt(false);
			ds.setTranslateBinary(false);
			ds.setNaming("system");
			ds.setLibraries("*LIBL");
			connection = ds.getConnection();
			return getUnitEnhancementExists(unit, connection, enhancement);
		}
		catch (SQLException sqle)
		{
			throw new EQException("EqSystem.getAuthorisedUnits: failed", sqle);
		}
		finally
		{
			SQLToolbox.close(connection);
		}
	}

	private boolean getUnitEnhancementExists(String unitId, Connection connection, String enhancement) throws EQException
	{
		CallableStatement equationEnhancementCheckerCaller = null;
		try
		{
			equationEnhancementCheckerCaller = connection.prepareCall("{ CALL " + "KLIB" + unitId + "/UTR00RPRC (?, ?) }");
			// Register output parameters
			equationEnhancementCheckerCaller.registerOutParameter(2, Types.CHAR);

			// Set the PV program name
			equationEnhancementCheckerCaller.setString(1, Toolbox.trim(enhancement, 4));

			// Call the API
			equationEnhancementCheckerCaller.execute();

			// Retrieve the details
			String enh = equationEnhancementCheckerCaller.getString(2);

			return enh.equals("Y");
		}
		catch (SQLException e)
		{
			throw new EQException(LanguageResources.getFormattedString("EquationSystem.ErrorCallingProgram", "UTR00R") + ": "
							+ Toolbox.getExceptionMessage(e));
		}
		finally
		{
			SQLToolbox.close(equationEnhancementCheckerCaller);
		}
	}

	/**
	 * Looks up the iSeries user mapped to the supplied CAS user id
	 * 
	 * This is intended for use during the login process when checking whether a user is the Unit GUI Administrator. Once the unit
	 * is initialised the method on the EquationFunctionContext should be used instead
	 * 
	 * This method obtains its own database connection
	 * 
	 * @param unitMnemonic
	 *            The unit mnemonic
	 * @param casUserId
	 *            The CAS user id
	 * 
	 * @return The mapped IBM i user id, or null if no mapping exists.
	 */
	public String getiSeriesUserForCASUser(String unitMnemonic, String casUserId)
	{
		Connection connection = null;
		PreparedStatement newsqlStatement = null;
		ResultSet rs = null;
		String user = null;
		try
		{
			connection = getConnectionFromAS400();

			final String sqlString = "SELECT OCOSID FROM KFIL" + unitMnemonic + "/OCPF WHERE OCBFUS = ?";
			newsqlStatement = connection.prepareStatement(sqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			newsqlStatement.setString(1, casUserId);
			rs = newsqlStatement.executeQuery();

			if (rs.next())
			{
				user = rs.getString(1).trim();
			}

			if (LOG.isDebugEnabled())
			{
				LOG.debug("getiSeriesUserForCASUser - CAS user [" + casUserId + "] mapped to iSeries user [" + user + "]");
			}
			return user;

		}
		catch (SQLException sqle)
		{
			LOG.error(sqle);
		}
		finally
		{
			SQLToolbox.close(connection);
		}
		return user;
	}
	/**
	 * Looks up the menu option for a user and return the description
	 * 
	 * This is intended for use during the build menu process when the EquationUnit does not exist and pooled connections cannot be
	 * used.
	 * 
	 * This method obtains its own database connection
	 * 
	 * @param unitMnemonic
	 *            The unit mnemonic
	 * @param userId
	 *            The user id
	 * @param optionId
	 *            The option id
	 * 
	 * @return the menu option description
	 */
	public String getMenuOptionDescription(String unitMnemonic, String userId, String optionId)
	{
		String userId4 = Toolbox.trim(userId, 4);
		Connection connection = null;
		PreparedStatement newsqlStatement = null;
		ResultSet rs = null;
		String description = null;
		try
		{
			connection = getConnectionFromAS400();

			final String sqlString = "SELECT GBONM FROM KFIL" + unitMnemonic + "/GBPF, KFIL" + unitMnemonic
							+ "/GDPF WHERE GBOID=GDOID AND GBOID= ? AND GDUSID= ?";
			newsqlStatement = connection.prepareStatement(sqlString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			newsqlStatement.setString(1, optionId);
			newsqlStatement.setString(2, Toolbox.removeSQLChars(userId4));
			rs = newsqlStatement.executeQuery();

			if (rs.next())
			{
				description = rs.getString(1).trim();
			}
			return description;

		}
		catch (SQLException sqle)
		{
			LOG.error(sqle);
		}
		finally
		{
			SQLToolbox.close(connection);
		}
		return description;
	}
	/**
	 * Return a JT400 SystemStatus object for the system that can be used to retrieve information about active jobs and disk usage
	 * 
	 * @return the SystemStatus object for the current AS400
	 */
	public SystemStatus getAS400SystemStatus()
	{
		if (as400SystemStatus == null)
		{
			as400SystemStatus = new SystemStatus(as400);
		}
		else
		{
			as400SystemStatus.refreshCache();
		}
		return as400SystemStatus;
	}

}