package com.misys.equation.common.utilities;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.LinkedHashMap;
import java.util.Map;

import com.ibm.as400.access.AS400JDBCConnection;
import com.ibm.as400.access.AS400JDBCConnectionHandle;
import com.ibm.websphere.rsadapter.WSCallHelper;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationConfigurationPropertiesBean;
import com.misys.equation.common.access.EquationDB2Table;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.dataaccess.connectionpooling.impl.JndiConnectionPool;

/**
 * @author weddelc1
 */
public class EquationControl
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationControl.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	private static final EquationLogger LOG = new EquationLogger(EquationControl.class);

	// Mode on how to log the user into a unit
	public static final String NONDESKTOP_MODE = "*I";
	public static final String DESKTOP_MODE = "*J";
	// XA Mode is like DESKTOP_MODE but with commitment control differences on iSeries
	public static final String XA_MODE = "*X";

	private static final String BASE_AS400_JDBC_URL = "jdbc:as400://";

	/**
	 * Create SQL object outside of the unit
	 * 
	 * @param connection
	 *            - the connection
	 * @param baseLibrary
	 *            - the base library name
	 */
	public static void createBaseSQLObjects(Connection connection, String baseLibrary) throws EQException
	{
		LOG.info("createBaseSQLObjects [" + baseLibrary + "] - entry");
		// make sure we have the procedure for calling the W96HMC
		String checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = '" + baseLibrary + "'"
						+ " AND SPECIFIC_NAME = 'W96HMCXPRC'";
		String createSQLObject = "CREATE PROCEDURE " + baseLibrary + "/W96HMCXPRC(" + "IN  mode   CHAR    (   2 ) CCSID 37, "
						+ "IN    unit     CHAR (   3 ) CCSID 37, " + "IN    option   CHAR    (   3 ) CCSID 37, "
						+ "INOUT zlslt    CHAR (  55 ) CCSID 37, " + "INOUT msgid    CHAR    (   7 ) CCSID 37, "
						+ "INOUT msgtxt   CHAR ( 132 ) CCSID 37, " + "INOUT context1 CHAR    ( 512 ) CCSID 37, "
						+ "INOUT context2 CHAR ( 512 ) CCSID 37, " + "IN    userid   CHAR    (  10 ) , "
						+ "IN    password CHAR (  10 ) , " + "IN    token    CHAR    ( 32  ) FOR BIT DATA" + ") " + "LANGUAGE CL "
						+ "NOT DETERMINISTIC " + "MODIFIES SQL DATA " + "EXTERNAL NAME W96HMC " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		LOG.info("createBaseSQLObjects [" + baseLibrary + "] - entry");

		// make sure we have the procedure for calling the W96HMC
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = '" + baseLibrary + "'"
						+ " AND SPECIFIC_NAME = 'W96HMCPRC'";
		createSQLObject = "CREATE PROCEDURE " + baseLibrary + "/W96HMCPRC(" + "IN  mode   CHAR    (   2 ) CCSID 37, "
						+ "IN    unit     CHAR (   3 ) CCSID 37, " + "IN    option   CHAR    (   3 ) CCSID 37, "
						+ "INOUT zlslt    CHAR (  55 ) CCSID 37, " + "INOUT msgid    CHAR    (   7 ) CCSID 37, "
						+ "INOUT msgtxt   CHAR ( 132 ) CCSID 37, " + "INOUT context1 CHAR ( 512 ) CCSID 37, "
						+ "INOUT context2 CHAR ( 512 ) CCSID 37 " + ") " + "LANGUAGE CL " + "NOT DETERMINISTIC "
						+ "MODIFIES SQL DATA " + "EXTERNAL NAME W96HMC " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling UTW59R
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = '" + baseLibrary + "'"
						+ " AND SPECIFIC_NAME = 'UTW59RPRC'";
		createSQLObject = "CREATE PROCEDURE " + baseLibrary + "/UTW59RPRC(" + "IN  object   CHAR    (   10 ) CCSID 37, "
						+ "IN    library     CHAR (  10 ) CCSID 37, " + "IN    type   CHAR    (   10 ) CCSID 37, "
						+ "OUT   numberOfLocks   CHAR (   4 ) CCSID 37, " + "OUT locks    VARCHAR (  26000 ) CCSID 37 " + ") "
						+ "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "MODIFIES SQL DATA " + "EXTERNAL NAME UTW59R "
						+ "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);
		LOG.info("createBaseSQLObjects - exit");

		// make sure we have the procedure for calling QUSROBJD
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = '" + baseLibrary + "'"
						+ " AND SPECIFIC_NAME = 'GETLIBPRC'";
		createSQLObject = "CREATE PROCEDURE " + baseLibrary + "/GETLIBPRC (INOUT ROBJIN VARCHAR(10), "
						+ "INOUT RTYPIN VARCHAR(10), " + "OUT RLIB BINARY(10)) " + "LANGUAGE SQL " + "SPECIFIC " + baseLibrary
						+ "/GETLIBPRC " + "NOT DETERMINISTIC " + "MODIFIES SQL DATA " + "CALLED ON NULL INPUT "
						+ "PROGRAM TYPE SUB " + "SET OPTION " + "ALWBLK = *ALLREAD, " + "ALWCPYDTA = *OPTIMIZE, "
						+ "COMMIT = *NONE,  " + "CLOSQLCSR = *ENDMOD, " + "DECRESULT = (31, 31, 00)," + "DFTRDBCOL = *NONE, "
						+ "DYNDFTCOL = *NO, " + "DYNUSRPRF = *OWNER," + "SQLCURRULE = *STD, " + "SRTSEQ = *HEX " + "BEGIN "
						+ "DECLARE RVAR BINARY(48) DEFAULT X'00' NOT NULL; " + "DECLARE RLEN INT DEFAULT 48 NOT NULL; "
						+ "DECLARE RFMT CHAR(8) DEFAULT 'OBJD0100' NOT NULL; " + "DECLARE ROBJ CHAR(20) ; "
						+ "DECLARE RTYP CHAR(10) ; " + "SET ROBJ = CHAR(ROBJIN, 10) CONCAT CHAR('*LIBL      '); "
						+ "SET RTYP = CHAR(RTYPIN, 10); " + "CALL QSYS/QUSROBJD (RVAR, RLEN, RFMT, ROBJ, RTYP); "
						+ "SET RLIB = SUBSTR(RVAR, 39, 10); " + "END";
		createSQLObject(connection, checkExists, createSQLObject);
		LOG.info("createBaseSQLObjects - exit");
	}

	/**
	 * Create SQL object in the unit
	 * 
	 * @param connection
	 *            - the connection
	 * @param unitId
	 *            - the unit mnemonic
	 */
	public static void createUnitSQLObjects(Connection connection, String unitId) throws EQException
	{
		LOG.debug("createUnitSQLObjects [" + unitId + "] - entry");

		// make sure we have the procedure for calling the PV
		String checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW06RPRC'";
		String createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW06RPRC " + "( " + "INOUT DECODE VARCHAR(1), "
						+ "INOUT PVPGM VARCHAR(10), " + "INOUT CCNIN VARCHAR(" + EquationPVMetaDataHelper.LEN_CCN + "), "
						+ "INOUT BLANKALLOW VARCHAR(1), " + "INOUT NEWCODE VARCHAR(1), " + "INOUT PMODE VARCHAR(1), "
						+ "INOUT DSUTW06 VARCHAR(4096), " + "INOUT CCNOUT VARBINARY("
						+ (EquationPVMetaDataHelper.LEN_CCN + EquationPVMetaDataHelper.LEN_DSEPMS) + ") " + ") "
						+ "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL " + "EXTERNAL NAME UTW06R "
						+ "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the function for calling the PV
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSFUNCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW06RFNC'";
		createSQLObject = "CREATE FUNCTION KLIB" + unitId.trim() + "/UTW06RFNC " + "( " + "DECODE VARCHAR(1), "
						+ "PVPGM VARCHAR(10), " + "CCN VARCHAR(" + EquationPVMetaDataHelper.LEN_CCN + "), "
						+ "BLANKALLOW VARCHAR(1), " + "NEWCODE VARCHAR(1), " + "PMODE VARCHAR(1), " + "DSUTW06 VARCHAR(4096) "
						+ ") " + "RETURNS VARCHAR(" + (EquationPVMetaDataHelper.LEN_CCN + EquationPVMetaDataHelper.LEN_DSEPMS)
						+ ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL " + "RETURNS NULL ON NULL INPUT "
						+ "DISALLOW PARALLEL " + "NOT FENCED " + "EXTERNAL NAME UTW06R " + "PARAMETER STYLE SQL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling the PV multiple
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW61RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW61RPRC " + "( " + "IN INPUT VARCHAR(32700), "
						+ "OUT OUTPUT VARBINARY(32700) " + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL "
						+ "EXTERNAL NAME UTW61R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling Legacy Transaction APIs...
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'H56H1RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/H56H1RPRC(" + "INOUT rpgm CHAR (10 ), "
						+ "INOUT brnm CHAR ( 4 ), " + "INOUT usid CHAR ( 4 ), " + "INOUT wsid CHAR ( 4 ), "
						+ "INOUT jtt CHAR ( 1 ), " + "INOUT DSAIM CHAR ( 9999 ) FOR BIT DATA, " + "INOUT rrec CHAR (1 ), "
						+ "INOUT rmes7 CHAR (37 ), " + "INOUT AOW CHAR (740 ), " + "INOUT etk CHAR ( 4 ), "
						+ "INOUT etn DECIMAL ( 7 , 0), " + "INOUT aext CHAR ( 1 ), " + "INOUT arec CHAR ( 1 )) "
						+ "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "MODIFIES SQL DATA " + "EXTERNAL NAME H56H1R "
						+ "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling Legacy Transaction APIs... at EQ342
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'H56HIRPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/H56HIRPRC(" + "INOUT rpgm CHAR (10 ), "
						+ "INOUT brnm CHAR ( 4 ), " + "INOUT usid CHAR ( 4 ), " + "INOUT wsid CHAR ( 4 ), "
						+ "INOUT jtt CHAR ( 1 ), " + "INOUT DSAIM CHAR ( 4000 ) FOR BIT DATA, " + "INOUT rrec CHAR (1 ), "
						+ "INOUT rmes7 CHAR (37 ), " + "INOUT AOW CHAR (222 ), " + "INOUT etk CHAR ( 4 ), "
						+ "INOUT etn DECIMAL ( 7 , 0)) " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "MODIFIES SQL DATA "
						+ "EXTERNAL NAME H56HIR " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling Transaction APIs...
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'X56HMRPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/X56HMRPRC(" + "INOUT rpgm CHAR (10 ), "
						+ "INOUT brnm CHAR ( 4 ), " + "INOUT wsid CHAR ( 4 ), " + "INOUT usid CHAR ( 4 ), "
						+ "INOUT suid CHAR ( 4 ), " + "INOUT spwd CHAR ( 10 ), " + "INOUT jtt CHAR ( 1 ), "
						+ "INOUT gza VARCHAR ( 9999 ) FOR BIT DATA, " + "INOUT gzb VARCHAR ( 9999 ) FOR BIT DATA, "
						+ "INOUT rrec CHAR (1 ), " + "INOUT AOE VARCHAR (740 ), " + "INOUT AOW VARCHAR (740 ), "
						+ "INOUT crm VARCHAR ( 9999 ) FOR BIT DATA, " + "INOUT aext CHAR ( 1 ), " + "INOUT arec CHAR ( 1 ),"
						+ "INOUT emh CHAR ( 740 )," + "INOUT ea CHAR ( 300 )," + "INOUT eb CHAR ( 80 )," + "INOUT dr CHAR ( 20 ), "
						+ "INOUT gyctr VARCHAR ( 4096 ) FOR BIT DATA, " + "INOUT gydet VARCHAR ( 4096 ) FOR BIT DATA,"
						+ "INOUT gyKey VARCHAR ( 4096 ) FOR BIT DATA" + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC "
						+ "MODIFIES SQL DATA " + "EXTERNAL NAME X56HMR " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling Enquiries...
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'X46HMRPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/X46HMRPRC(" + "INOUT epgm CHAR (10), "
						+ "INOUT dsaif CHAR (9999) FOR BIT DATA, " + "INOUT dsair CHAR (9999) FOR BIT DATA, "
						+ "INOUT begin CHAR (1), " + "INOUT noreq DECIMAL (5,0), " + "INOUT noret DECIMAL (5,0), "
						+ "INOUT eqnln CHAR (2), " + "INOUT ercod CHAR (2), " + "INOUT erprm CHAR (10), "
						+ "INOUT flen DECIMAL(5,0), " + "INOUT rlen DECIMAL(5,0)) " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC "
						+ "NO SQL " + "EXTERNAL NAME X46HMR " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling the PV meta data
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW19RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW19RPRC(" + "IN  pgm      CHAR    (    10 ), "
						+ "OUT names    VARCHAR (  2000 ) CCSID 37, " + "OUT namesD   VARCHAR (  9999 ) CCSID 37, "
						+ "OUT types    VARCHAR (   200 ) CCSID 37, " + "OUT lengths  VARCHAR (  2000 ) CCSID 37, "
						+ "OUT indexs   VARCHAR (  2000 ) CCSID 37, " + "OUT decd     VARCHAR (  2000 ) CCSID 37, "
						+ "OUT usage    VARCHAR (   200 ) CCSID 37, " + "OUT header   VARCHAR (  9999 ) CCSID 37, "
						+ "OUT dbs      VARCHAR (  2000 ) CCSID 37, " + "OUT ival     VARCHAR (  2000 ) CCSID 37, "
						+ "OUT dval     VARCHAR (  2000 ) CCSID 37, " + "OUT oval     VARCHAR (  2000 ) CCSID 37, "
						+ "OUT decode   VARCHAR (   200 ) CCSID 37, " + "OUT decoded  VARCHAR (  9999 ) CCSID 37, "
						+ "OUT zpparamd VARCHAR ( 13200 ) CCSID 37, " + "OUT primf    VARCHAR (  2000 ) CCSID 37, "
						+ "OUT keys     VARCHAR (  9999 ) CCSID 37, " + "OUT pvd      VARCHAR (  2000 ) CCSID 37 " + ") "
						+ "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "MODIFIES SQL DATA " + "EXTERNAL NAME UTW19R "
						+ "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling the supervisor validation via Equation Desktop
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW21RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW21RPRC(" + "INOUT parm1 CHAR (10), "
						+ "INOUT parm2 CHAR (10), " + "INOUT parm3 CHAR (37)," + " INOUT parm4 CHAR (1)" + ") " + "LANGUAGE RPGLE "
						+ "NOT DETERMINISTIC " + "EXTERNAL NAME UTW21R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling the GH update module
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'SGH02RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/SGH02RPRC(" + "IN mode CHAR (1), " + "OUT dsepms CHAR (37), "
						+ "IN wid CHAR (10), " + "IN uid CHAR (10), " + "IN jbn DECIMAL (6,0), " + "IN sid CHAR (50), "
						+ "IN oid CHAR (3), " + "IN pgm CHAR (10), " + "IN cc CHAR (1), " + "IN appl CHAR (10), "
						+ "IN logusr CHAR (10), " + "IN tcpip CHAR (15) " + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC "
						+ "EXTERNAL NAME SGH02R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for retrieving the data area
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW52RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW52RPRC " + "( " + "IN DECODE CHAR(10), "
						+ "OUT DATA VARCHAR(2000) FOR BIT DATA" + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL "
						+ "EXTERNAL NAME UTW52R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for retrieving the object path
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW53CPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW53CPRC " + "( " + "IN OBJNAME CHAR(10), "
						+ "IN OBJTYPE CHAR(10), " + "IN OBJLIB CHAR(10), " + "OUT RTNLIB CHAR(10), " + "OUT RTNPATH CHAR(50) "
						+ ") " + "LANGUAGE CL " + "NOT DETERMINISTIC " + "NO SQL " + "EXTERNAL NAME UTW53C "
						+ "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for the Component limit checking wrapper
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW54RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW54RPRC " + "( "
						+ "INOUT INDATA VARCHAR(9999) FOR BIT DATA, " + "INOUT CRMDATA VARCHAR(9999) FOR BIT DATA, "
						+ "INOUT DSEPMS CHAR(37) " + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL "
						+ "EXTERNAL NAME UTW54R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for the Interbranch authority
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW57RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW57RPRC " + "( " + "IN USID CHAR(4), "
						+ "IN DBBN CHAR(4), " + "OUT INTERB CHAR(1) " + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "NO SQL "
						+ "EXTERNAL NAME UTW57R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling UTR00R for CHPF checks
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTR00RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTR00RPRC(" + "IN ENHAN CHAR(4), "
						+ "OUT INSTL CHAR (1) CCSID 37 " + ") " + "LANGUAGE RPGLE " + "NOT DETERMINISTIC " + "MODIFIES SQL DATA "
						+ "EXTERNAL NAME UTR00R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling UTW58R
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW58RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/UTW58RPRC(" + "IN MODE CHAR(3), "
						+ "IN INPUT CHAR (2048) FOR BIT DATA, OUT OUTPUT CHAR (2048) FOR BIT DATA " + ") " + "LANGUAGE RPGLE "
						+ "NOT DETERMINISTIC " + "MODIFIES SQL DATA " + "EXTERNAL NAME UTW58R " + "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling SAPJ10R
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS " //
						+ "WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim() + "' " //
						+ "  AND SPECIFIC_NAME   = 'SAPJ10RPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/SAPJ10RPRC(" //
						+ " INOUT APJARF CHAR(10), " // Internal API identifier - allocated by GP team
						+ " INOUT APJFIL CHAR(10), " // API file name
						+ " INOUT MODE   CHAR(1), " // Mode - 'A' for target unit, 'B' for source unit
						+ " INOUT FUNT   CHAR(3), " // From Unit - used if mode = 'A'
						+ " INOUT KEYIN  CHAR(35), " // Key value for database retrieval
						+ " INOUT DSAIM  BINARY(9999), " // Data structure of API details - in the CCSID of the unit
						+ " INOUT DSFLD  CHAR(1000), " // Fields to be excluded - identified by the APIPF field seq nos
						+ " INOUT DBREC  CHAR(1), " // Record found on database? (Y,N)
						+ " INOUT GZLENB DECIMAL(5, 0), " // Source GZ record length
						+ " INOUT DSEPMS CHAR(37), " // Returned error message
						+ " INOUT YSTUB  CHAR(1)" // For test stub use only, always pass blank!
						+ ") LANGUAGE RPGLE NOT DETERMINISTIC MODIFIES SQL DATA EXTERNAL NAME SAPJ10R PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for calling V45ASR
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS " //
						+ "WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim() + "' " //
						+ "  AND SPECIFIC_NAME   = 'V45ASRPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB" + unitId.trim() + "/V45ASRPRC(" //
						+ " IN UNIT  CHAR(3), " // Unit to execute command on
						+ " IN CMDO  CHAR(3), " // The command option
						+ " IN AEXT  CHAR(1), " // External Input? (Y/N)
						+ " IN AREC  CHAR(1), " // Recovery? (Y/N)
						+ " IN PARMS CHAR(35) " // Variable parameters
						+ ") LANGUAGE RPGLE NOT DETERMINISTIC MODIFIES SQL DATA EXTERNAL NAME V45ASR PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		// make sure we have the procedure for journal print
		checkExists = "SELECT COUNT(*) FROM QSYS2/SYSPROCS WHERE SPECIFIC_SCHEMA = 'KLIB" + unitId.trim()
						+ "' AND SPECIFIC_NAME = 'UTW70CPRC'";
		createSQLObject = "CREATE PROCEDURE KLIB"
						+ unitId.trim()
						+ "/UTW70CPRC "
						+ "(IN fro CHAR(10), IN wsid CHAR(4), IN dim DECIMAL (2,0), IN tim DECIMAL (6,0), IN seq DECIMAL (7,0), IN usid CHAR (4),"
						+ "IN ttp CHAR (1), IN dpost CHAR (1), IN newpg CHAR (1), IN supss CHAR (1), IN prnb CHAR (1), IN oid CHAR (3)"
						+ ") " + "LANGUAGE CL " + "NOT DETERMINISTIC " + "NO SQL " + "EXTERNAL NAME UTW70C "
						+ "PARAMETER STYLE GENERAL";
		createSQLObject(connection, checkExists, createSQLObject);

		LOG.debug("createUnitSQLObjects - exit");
	}

	/**
	 * Execute a query on the unit
	 * 
	 * @param connection
	 *            - the connection
	 * @param checkExists
	 *            - the query to determine whether to execute the query or not
	 * @param createProcedure
	 *            - the query to be executed
	 * 
	 * @throws EQException
	 */
	private static void createSQLObject(Connection connection, String checkExists, String createProcedure) throws EQException
	{
		Statement checkExistsStatement = null;
		ResultSet checkExistsRS = null;
		Statement createProcedureStatement = null;
		try
		{
			checkExistsStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			checkExistsRS = checkExistsStatement.executeQuery(checkExists);

			if (checkExistsRS.next())
			{
				if (checkExistsRS.getInt(1) == 0)
				{
					createProcedureStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
					createProcedureStatement.execute(createProcedure);
				}
			}
		}
		catch (SQLException e)
		{
			throw new EQException("Equation Control: createSQLObject - Pool Operations Failed", e);
		}
		finally
		{
			SQLToolbox.close(checkExistsRS);
			SQLToolbox.close(checkExistsStatement);
			SQLToolbox.close(createProcedureStatement);
		}
	}

	/**
	 * Return the number of user
	 * 
	 * @param connection
	 *            - the connection
	 * 
	 * @return the number of user
	 * 
	 * @throws EQException
	 */
	public static int retrieveNumberUsers(Connection connection) throws EQException
	{
		int numberUsers = 0;
		Statement userCountStatement = null;
		ResultSet userCountRS = null;

		try
		{
			// find the number of users
			String userCountSqlString = "SELECT COUNT(*) FROM OCPF";
			userCountStatement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			userCountRS = userCountStatement.executeQuery(userCountSqlString);
			// ...
			if (userCountRS.next())
			{
				numberUsers = userCountRS.getInt(1);
			}
		}
		catch (SQLException e)
		{
			throw new EQException("EquationControl: retrieveNumberUsers - Pool Operations Failed", e);
		}
		finally
		{
			SQLToolbox.close(userCountRS);
			SQLToolbox.close(userCountStatement);
		}

		return numberUsers;
	}

	/**
	 * Log the user into Equation, note that the {@link EquationCommonContext} and {@link EquationSystem} must have been previously
	 * initialised to execute this method. If a user and password or user and token is supplied the current user of the connection
	 * will be changed.
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @param unitId
	 *            - the unit Id
	 * @param mode
	 *            - the mode used on the iSeries (*I, *J or *X)
	 * @param userId
	 *            - the user of the session
	 * @param password
	 *            - the password for the user of the session
	 * @param token
	 *            - the token for the user of the session
	 * 
	 * @throws EQException
	 */
	public static void logIntoEquation(Connection connection, String systemId, String unitId, String mode, String userId,
					String password, byte[] token) throws EQException
	{
		// Now we have a system name need to make sure it is is set in the context...
		EquationSystem system = EquationCommonContext.getContext().getEquationSystem(systemId);
		boolean isEq4Unit = EquationConfigurationPropertiesBean.getInstance().getGlobalUnitVersion(systemId, unitId).equals("EQ4");

		// no system?
		String baseLibrary = "";
		if (system != null)
		{
			baseLibrary = system.getBaseLibrary();
		}

		// non-EQ4 unit
		if (!isEq4Unit)
		{
			// TODO XA is not catered for at EQ3
			logIntoEQ3(connection, unitId);
		}

		// EQ4 unit
		else if (isEq4Unit)
		{
			logIntoEQ4(connection, baseLibrary, unitId, mode, userId, password, token);
		}
	}

	/**
	 * Log the user into Equation, note that the {@link EquationCommonContext} and {@link EquationSystem} must have been previously
	 * initialised to execute this method
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @param unitId
	 *            - the unit Id
	 * @param mode
	 *            - the mode used on the iSeries (*J or *X)
	 * @param equationIseriesProfile
	 *            - the iSeries/Equation user profile
	 * 
	 * @throws EQException
	 */
	public static void logIntoEquation(Connection connection, String systemId, String unitId, String mode,
					String equationIseriesProfile) throws EQException
	{
		logIntoEquation(connection, systemId, unitId, mode, equationIseriesProfile, null, null);
	}

	/**
	 * Log the user off Equation, note that the {@link EquationCommonContext} and {@link EquationSystem} must have been previously
	 * initialised to execute this method
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @partam systemId - the system ID
	 * @param unitId
	 *            - the unit ID
	 * 
	 * @throws EQException
	 */
	public static void logOffFromEquation(Connection connection, String systemId, String unitId) throws EQException
	{
		// Now we have a system name need to make sure it is is set in the context...
		EquationSystem system = EquationCommonContext.getContext().getEquationSystem(systemId);
		boolean isEq4Unit = EquationConfigurationPropertiesBean.getInstance().getGlobalUnitVersion(systemId, unitId).equals("EQ4");

		// no system?
		String baseLibrary = "";
		if (system != null)
		{
			baseLibrary = system.getBaseLibrary();
		}

		// EQ4
		if (isEq4Unit)
		{
			// We are using the *O mode of login to actually do logoff
			logIntoEQ4(connection, baseLibrary, unitId, "*O", null, null, null);
		}
		// non-EQ4
		else
		{
			// TODO There is no LOGOFF possible in EQ3 at the moment. XA is not catered for.
			// logIntoEQ3(connection, unitId);
		}
	}

	/**
	 * Log the user into Equation 4
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @param baseLibrary
	 *            - the Equation base library of the iSeries - this contains the W96HMC stored procedure
	 * @param unitId
	 *            - the unit Id
	 * @param mode
	 *            - the mode to call W96HMC in
	 * @param userId
	 *            - the user of the session
	 * @param password
	 *            - the password for the user of the session
	 * @param token
	 *            - the token for the user of the session
	 * 
	 * @throws EQException
	 */
	private static void logIntoEQ4(Connection connection, String baseLibrary, String unitId, String mode, String userId,
					String password, byte[] token) throws EQException
	{
		try
		{
			CallableStatement loginAPICaller;

			// base library specified?
			if (baseLibrary.length() == 0)
			{
				loginAPICaller = connection.prepareCall("{ CALL " + "W96HMCXPRC (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
			}
			else
			{
				loginAPICaller = connection.prepareCall("{ CALL " + baseLibrary + "/"
								+ "W96HMCXPRC (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }");
			}

			loginAPICaller.registerOutParameter(4, Types.CHAR);
			loginAPICaller.registerOutParameter(5, Types.CHAR);
			loginAPICaller.registerOutParameter(6, Types.CHAR);
			loginAPICaller.registerOutParameter(7, Types.CHAR);
			loginAPICaller.registerOutParameter(8, Types.CHAR);
			loginAPICaller.setString(1, mode);
			loginAPICaller.setString(2, unitId);
			loginAPICaller.setString(3, "");
			// Use existing parameter for setting login type to keep CL backward compatible.
			// CL can check ZLSLT variable and not directly access new parameters unless they are definitely passed.
			if (password != null)
			{
				loginAPICaller.setString(4, "PASSWORD");
			}
			else if (token != null)
			{
				loginAPICaller.setString(4, "TOKEN");
			}
			else
			{
				loginAPICaller.setString(4, "");
			}
			if (userId != null && userId.length() > 0 && (password == null || password.length() == 0))
			{
				loginAPICaller.setString(4, "PASSWORD");
				password = "*NOPWDCHK ";
			}
			loginAPICaller.setString(5, "");
			loginAPICaller.setString(6, "");
			loginAPICaller.setString(7, "");
			loginAPICaller.setString(8, "");
			if (userId != null)
			{
				loginAPICaller.setString(9, userId);
			}
			else
			{
				loginAPICaller.setString(9, "");
			}
			if (password != null)
			{
				loginAPICaller.setString(10, password);
			}
			else
			{
				loginAPICaller.setString(10, "");
			}
			if (token != null)
			{
				loginAPICaller.setBytes(11, token);
			}
			else
			{
				loginAPICaller.setBytes(11, new byte[0]);
			}

			loginAPICaller.execute();

			// any messages returned?
			String msgId = loginAPICaller.getString(5).trim();
			if (msgId.length() > 0)
			{
				if (!msgId.equals("KAP0143") && !msgId.equals("KSM3714")) // OK if suspended for non-desktop, no??
				{
					// message has funny character?
					String msgTxt = loginAPICaller.getString(6).trim();
					if (msgTxt.codePointAt(0) >= 128)
					{
						msgTxt = msgTxt.substring(1);
					}
					throw new EQException(msgTxt);
				}
			}
		}
		catch (SQLException e)
		{
			throw new EQException("EquationControl: logIntoEQ4 - Failed:" + Toolbox.getExceptionMessage(e), e);
		}
	}

	/**
	 * Log the user into Equation 3
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @param unitId
	 *            - the unit Id
	 * 
	 * @throws EQException
	 */
	private static void logIntoEQ3(Connection connection, String unitId) throws EQException
	{
		// Have a bash...
		try
		{
			// create the statement
			Statement statement = connection.createStatement();

			// Clear QTEMP
			statement.execute(SQLToolbox.getQcmdexcString("CLRLIB QTEMP"));
			connection.commit();

			// Set the library list
			statement.execute("CALL KAPUNLIBL ('" + unitId + "','    ')");
			connection.commit();

			// Set DAJOBCTLE
			statement.execute("CALL UTM83C (' ')");
			connection.commit();
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}

	/**
	 * Setup a list of tables on the iSeries DB
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @param library
	 *            - a library name
	 * 
	 * @return the list of tables in a library
	 */
	public static Map<String, EquationDB2Table> getTables(Connection connection, String library)
	{
		Map<String, EquationDB2Table> tables = new LinkedHashMap<String, EquationDB2Table>();
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			// First up need to get the info for whether the key is unique
			statement = connection.createStatement();
			// String command = "DSPFD FILE(" + library.trim()
			// + "/*ALL) TYPE(*ACCPTH) OUTPUT(*OUTFILE) FILEATR(*LF) OUTFILE(QTEMP/LFFD)";
			// Files do not need to have unique keys or be logical files
			String command = "DSPFD FILE(" + library.trim() + "/*ALL) TYPE(*ACCPTH) OUTPUT(*OUTFILE) OUTFILE(QTEMP/LFFD)";
			String query = SQLToolbox.getQcmdexcString(command);
			statement.execute(query);
			statement.close();

			// Now do the query
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			// Get the list of tables, views, indexes
			String sqlString = "SELECT TABLE_NAME, TABLE_TEXT, TABLE_TYPE, APUNIQ FROM SYSTABLES LEFT JOIN QTEMP/LFFD ON TABLE_NAME = APFILE "
							+ " WHERE TABLE_SCHEMA = '"
							+ library.trim()
							+ "' UNION "
							+ " SELECT SYSTEM_INDEX_NAME AS TABLE_NAME, INDEX_TEXT, 'I', APUNIQ FROM SYSINDEXES LEFT JOIN QTEMP/LFFD ON INDEX_NAME = APFILE "
							+ " WHERE TABLE_SCHEMA = '" + library.trim() + "' " + " ORDER BY TABLE_NAME ASC";
			resultSet = statement.executeQuery(sqlString);
			while (resultSet.next())
			{
				EquationDB2Table tableModel = new EquationDB2Table();
				tableModel.setTableName(resultSet.getString(1).trim());
				tableModel.setTableDescription(resultSet.getString(2).trim());
				tableModel.setTableType(resultSet.getString(3).trim());

				if (resultSet.getString(4) != null && resultSet.getString(4).trim().equals("Y"))
				{
					tableModel.setUnique(true);
				}
				else
				{
					tableModel.setUnique(false);
				}
				tables.put(tableModel.getTableName(), tableModel);
			}
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return tables;
	}

	/**
	 * Setup the list of the columns in a table on the iSeries DB
	 * 
	 * @param connection
	 *            - the AS400 connection
	 * @param library
	 *            - a library name
	 * @param table
	 *            - a table name
	 * 
	 * @return the list of columns in a table
	 */
	public static Map<String, String> getTableColumnMetaData(Connection connection, String library, String tableName)
	{
		Map<String, String> tableColumns = new LinkedHashMap<String, String>();
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			// Now do the query
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			String sqlString = "SELECT COLUMN_NAME, COLUMN_HEADING FROM QSYS2/SYSCOLUMNS WHERE TABLE_NAME = '" + tableName.trim()
							+ "' and TABLE_SCHEMA = '" + library.trim() + "'";

			resultSet = statement.executeQuery(sqlString);
			while (resultSet.next())
			{
				tableColumns.put(resultSet.getString(1).trim(), resultSet.getString(2).trim());
			}
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return tableColumns;
	}

	/**
	 * Determines whether the file is an index file (created by SQL CREATE INDEX)
	 * 
	 * @return true if file is an index file (created by SQL CREATE INDEX)
	 */
	public static boolean isIndexFile(Connection connection, String library, String fileName)
	{
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			// First up need to get the info for whether the key is unique
			statement = connection.createStatement();

			// Now do the query
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

			// Get the list of tables, views, indexes
			String sqlString = " SELECT SYSTEM_INDEX_NAME AS TABLE_NAME FROM SYSINDEXES WHERE TABLE_SCHEMA = '" + library.trim()
							+ "' and SYSTEM_INDEX_NAME = '" + fileName.trim() + "'";
			resultSet = statement.executeQuery(sqlString);
			if (resultSet.next())
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			LOG.error(e);
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return false;
	}

	/**
	 * Retrieve the system name
	 * 
	 * @param connection
	 */
	public static String getSystemName(Connection connection) throws EQException, SQLException
	{
		String systemId = "";

		// AS400JDBCConnection?
		if (connection instanceof AS400JDBCConnection)
		{
			AS400JDBCConnection as400JDBC = (AS400JDBCConnection) connection;
			systemId = as400JDBC.getSystem().getSystemName();
		}

		// AS400JDBCConnectionHandle?
		else if (connection instanceof AS400JDBCConnectionHandle)
		{
			AS400JDBCConnectionHandle as400JDBC = (AS400JDBCConnectionHandle) connection;
			systemId = as400JDBC.getSystem().getSystemName();
		}

		// AS400JDBCConnection?
		else if (connection.getMetaData().getConnection() instanceof AS400JDBCConnection)
		{
			AS400JDBCConnection as400JDBC = (AS400JDBCConnection) connection.getMetaData().getConnection();
			systemId = as400JDBC.getSystem().getSystemName();
		}

		// WAS connection?
		else if (JndiConnectionPool.isAWebSphereApplicationServer())
		{
			Connection wasConnection = WSCallHelper.getNativeConnection(connection);
			AS400JDBCConnection as400JDBC = (AS400JDBCConnection) wasConnection.getMetaData().getConnection();
			systemId = as400JDBC.getSystem().getSystemName();
		}

		// Retrieve from URL
		else
		{
			String url = connection.getMetaData().getURL();
			if (url.indexOf(BASE_AS400_JDBC_URL) > -1)
			{
				systemId = url.substring(BASE_AS400_JDBC_URL.length());

				if (systemId.indexOf("/") > -1)
				{
					systemId = systemId.substring(0, systemId.indexOf("/"));
				}
				else if (systemId.indexOf(";") > -1)
				{
					systemId = systemId.substring(0, systemId.indexOf(";"));
				}
				else
				{
					systemId = systemId.trim();
				}
			}
			else
			{
				throw new EQException("Cannot log in, invalid driver for connection");
			}

		}

		// system name
		return systemId;
	}

}