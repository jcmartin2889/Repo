package com.misys.equation.common.globalprocessing.audit;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CommandCall;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAURecordDao;
import com.misys.equation.common.dao.IGAVRecordDao;
import com.misys.equation.common.dao.beans.APJRecordDataModel;
import com.misys.equation.common.dao.beans.APVRecordDataModel;
import com.misys.equation.common.dao.beans.GAURecordDataModel;
import com.misys.equation.common.dao.beans.GAVRecordDataModel;
import com.misys.equation.common.globalprocessing.audit.APVCacheUtil.APVFields;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

public class GlobalAuditingApplicator
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GlobalAuditingApplicator.java 12552 2012-03-01 13:24:13Z perkinj1 $";

	/** CCSID of GZ / GS data on Global audit tables */
	public static final int GLOBAL_CCSID = 65535;

	protected static final EquationLogger LOG = new EquationLogger(GlobalAuditingApplicator.class);

	private String errors;

	private boolean withCCSIDErrors;

	private final EquationStandardSession session;

	public GlobalAuditingApplicator(EquationStandardSession session)
	{
		this.session = session;
	}

	public boolean applyImage(long auditHeaderId, String subChar) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Executing GlobalAuditingApplicator#applyImage for audit header id: " + auditHeaderId);
		}

		// get propagation information
		final GAURecordDataModel header = getAuditHeader(session, auditHeaderId);
		final GAVRecordDataModel propData = getPropData(session, auditHeaderId);

		// prepare call for SAPJ
		final String apvArf = header.getOptionId();
		final APVFields apvFields = APVCacheUtil.loadAPVFields(session, apvArf);
		final APVRecordDataModel apv = apvFields.getApv();
		final List<APJRecordDataModel> apjRecords = apvFields.getFields();

		// prepare image for applicator and store most recent error (if any generated)
		final SAPJ10RDS targetImage = createTargetImage(session, header, propData, apv, apjRecords, subChar);
		errors = targetImage.getErrorMessage();

		if (targetImage.isValid())
		{
			if ("D".equals(targetImage.getMode()) && !targetImage.isRecordFound())
			{
				// we can't delete a non-existent record, so just consider this applied!
				LOG.warn("Delete of non-existent " + apvArf + " with key = " + header.getReferenceDetails() + "; ignoring...");
				return true;
			}

			// get target system / unit
			final String toSystem = session.getSystemId();
			final String toUnit = session.getUnitId();

			// For Equation versions 3.8 and above call H56H1R
			if (isVersionNew(session))
			{
				final H56H1RDS h56h1Result = callH56H1R(session, toSystem, toUnit, apv.getApiProgram(), targetImage.getMode(),
								targetImage.getApiData());
				final String h56Error = h56h1Result.getErrorMessage();
				final String h56Warning = h56h1Result.getWarningMessage();
				final boolean isApplied = "F".equals(h56h1Result.getReturnCode()) ? false : true;

				if (h56Warning != null && !"".equals(h56Warning.trim()))
				{
					LOG.warn("H56 returned warning while applying " + apv.getApiProgram() + " JTT '" + targetImage.getMode()
									+ "' on " + toSystem + "/" + toUnit + ": " + h56Warning);
				}

				// store most recent error
				errors = h56Error;
				if (errors == null || "".equals(errors.trim()))
				{
					// us warnings
					errors = h56Warning;
				}

				return isApplied;
			}
			else
			{
				// For Equation versions below 3.8 call H56HSR
				final H56HSRDS result = callH56HSR(session, toSystem, toUnit, apv.getApiProgram(), targetImage.getMode(),
								targetImage.getApiData());
				final String h56Error = result.getErrorMessage();
				final String h56Warning = result.getWarningMessage();
				final boolean isApplied = "F".equals(result.getReturnCode()) ? false : true;

				if (h56Warning != null && !"".equals(h56Warning.trim()))
				{
					LOG.warn("H56 returned warning while applying " + apv.getApiProgram() + " JTT '" + targetImage.getMode()
									+ "' on " + toSystem + "/" + toUnit + ": " + h56Warning);
				}

				// store most recent error
				errors = h56Error;
				if (errors == null || "".equals(errors.trim()))
				{
					// us warnings
					errors = h56Warning;
				}

				return isApplied;
			}
		}
		else
		{
			// SAPJ10RUtils.updateAuditTables(session, auditHeaderId, AuditStatus.FAILED, ApplyType.AUTO, errors);
			return false;
		}
	}

	public String getErrorMessage()
	{
		return errors;
	}

	private SAPJ10RDS createTargetImage(EquationStandardSession session, GAURecordDataModel header, GAVRecordDataModel propData,
					APVRecordDataModel apv, List<APJRecordDataModel> apjRecords, String subChar) throws EQException
	{
		// get propData type -- A, M, D
		final String propDataMode = propData.getType();

		// use api reference
		final String arf = apv.getApiReference();

		// Step 1: Determine API File
		final String apiFile = propData.getApiFormat();

		final String sourceUnit = propData.getSourceUnit();

		// Step 3: get source image
		final int ccsid = session.getUnit().getCcsid();

		final DSAIMUtil dsaimUtil = new DSAIMUtil(subChar);

		// prepare GZ image
		byte[] gzConvImage = null;
		gzConvImage = dsaimUtil.convertGZCCSID(propData.getSourceGzImage(), apv, apjRecords,
						propData.getSourceUnitCcsid() /* GLOBAL_CCSID */, ccsid);

		// perform parameter mapping from 'master' to 'unit'
		dsaimUtil.mapGZParameters(gzConvImage, session, apv, apjRecords, ccsid, false);

		byte[] gsConvImage = null;
		if (!(propData.getApiFormat().equals("GZH181")))
		{
			if (propData.getSourceGsImage() != null && propData.getSourceGsImage().length > 0)
			{
				// NOTE: Conversion should be from source unit CCSID directly to target CCSID (though the CCSID of GAV is 65535, the
				// actual data stored depends on what CCSID the source unit was in!)
				gsConvImage = dsaimUtil.convertGSCCSID(propData.getSourceGsImage(), apv, apjRecords,
								propData.getSourceUnitCcsid() /* GLOBAL_CCSID */, ccsid);
			}
		}
		// store ccsid conversion status
		this.withCCSIDErrors = dsaimUtil.isWithCCSIDErrors();

		// create DSAIM
		final byte[] dsaim = gsConvImage != null ? getMergedDsaim(session.getUnit(), gzConvImage, gsConvImage, dsaimUtil, apv,
						apjRecords) : gzConvImage;

		// Step 4: Prepare the "keyIn" parameter - from GAUPF.GAUREF
		final String keyIn = header.getReferenceDetails();

		// Step 5: Prepare exclude params
		final String exclude = propData.getExcludedFields() != null ? propData.getExcludedFields() : "";

		// Call SAPJ10R
		final SAPJ10RDS result;

		// lower-unit check: does this unit even have the file?!?
		final String chkObjCmd = "CHKOBJ KINP" + session.getUnitId().trim() + "/" + apv.getApiFileName() + " *FILE";
		final EquationSystem eqSystem = session.getUnit().getSystem();
		final AS400 eqAS400 = eqSystem.getAS400();
		try
		{
			final CommandCall cc = new CommandCall(eqAS400);
			if (cc.run(chkObjCmd))
			{
				if ("D".equals(propDataMode))
				{
					// for delete transactions, we can't generate a 'target' image because the source is presumably empty (because
					// the record was deleted from the source!), hence, we need to generate the 'before' image for performing a
					// DELETE txn on the target
					result = callSAPJ10R(session, session.getSystemId(),// toSystem
									session.getUnitId(),// toUnit
									arf, // API Identifier loaded from APVPF
									apiFile, // GZ File name
									"B", // 'B' to create 'source' (Before) image
									session.getUnitId(), // Not needed for creating 'before' image, but let's put it in anyway
									keyIn, // The GZ Key field values
									dsaim, // Specify the source ('before') image (presumably this should be empty)
									exclude, // Specify the 'exclude' parameter for creating the 'target' image
									propDataMode);
				}
				else
				{
					// API file exists, we may proceed with creating target image!
					result = callSAPJ10R(session, session.getSystemId(),// toSystem
									session.getUnitId(),// toUnit
									arf, // API Identifier loaded from APVPF
									apiFile, // GZ File name
									"A", // 'A' to create 'target' (After) image
									sourceUnit, // The source unit to create the target image from
									keyIn, // The GZ Key field values
									dsaim, // Specify the source ('before') image,
									exclude, // Specify the 'exclude' parameter for creating the 'target' image
									propDataMode);
				}
			}
			else
			{
				// API file DOES NOT exists, indicate with an invalid record
				result = new SAPJ10RDS();
				result.setErrorMessage(apv.getApiFileName() + " does not exist on unit " + session.getUnitId());
				result.setExcludedFields(exclude);
				result.setApiFile(apv.getApiFileName());
				result.setInternalApi(apv.getApiReference());
				result.setMode("A");
				result.setFromUnit(sourceUnit);
				result.setKeyIn(keyIn);
				result.setRecordFound(false);
				result.setValid(false);
			}
		}
		catch (Exception ex)
		{
			throw new EQException("Error running check on GZ FILE: " + chkObjCmd, ex);
		}
		finally
		{
			eqSystem.returnAS400(eqAS400);
		}

		// store the 'keyIn' that we used!
		result.setKeyIn(keyIn);

		// ...and the file for future reference
		result.setApiFile(apiFile);

		if (LOG.isDebugEnabled())
		{
			LOG.debug("SAPJ10RPRC return results:" //
							+ ("\n  MODE   = " + result.getMode()) //
							+ ("\n  DSAIM  = " + Arrays.toString(result.getApiData())) //
							+ ("\n  DBREC  = " + result.isRecordFound()) //
							+ ("\n  DSEPMS = " + result.getErrorMessage()));
		}

		return result;
	}

	private byte[] getMergedDsaim(EquationUnit eqUnit, byte[] gzConvImage, byte[] gsConvImage, DSAIMUtil dsaimUtil,
					APVRecordDataModel apv, List<APJRecordDataModel> apiFields) throws EQException
	{
		final int gsOffset = dsaimUtil.getGSOffset(eqUnit, apv, apiFields);
		if (gsOffset < gzConvImage.length)
		{
			LOG.warn(String.format("GZ length %d longer than gs offset %d", gzConvImage.length, gsOffset));
		}

		// prepare DSAIM as combination of GZ and GS images
		final byte[] dsaim = new byte[gsOffset + gsConvImage.length];

		// copy GZ / GS data into DSAIM
		System.arraycopy(gzConvImage, 0, dsaim, 0, gzConvImage.length);
		System.arraycopy(gsConvImage, 0, dsaim, gsOffset - 1, gsConvImage.length);

		// DSAIM now contains both GZ and GS data
		return dsaim;
	}

	/**
	 * * This returns a datastructure containing the result to be applied via H56.
	 * 
	 * @param session
	 *            - unit session
	 * @param toSystem
	 *            - target system
	 * @param toUnit
	 *            - target unit
	 * @param apiarf
	 *            - internal api identifier
	 * @param apifil
	 *            - api file
	 * @param mode
	 *            - mode
	 * @param funt
	 *            - source unit
	 * @param keyin
	 *            - keyin
	 * @param dsaim
	 *            - api data
	 * @param excludefields
	 *            - exclude fields
	 * @return - the result of the stored procedure call using a {@link SAPJ10RDS} object
	 */
	public SAPJ10RDS callSAPJ10R(EquationStandardSession session, String toSystem, String toUnit, String apiarf, String apifil,
					String mode, String funt, String keyin, byte[] dsaim, String excludefields, String propDataMode)
					throws EQException
	{
		// Start a transaction Execute SAPJ10R passing in the parameters and commit.
		SAPJ10RDS result = SAPJ10RUtils.callStoredProcSAPJ10R(session, apiarf, apifil, mode, funt, keyin, dsaim, excludefields);

		if (result.isValid())
		{
			if ("CDD".equals(apiarf))
			{
				// special case for 'CDD' (Customer Deletions): JTT is always "D"!
				result.setMode("D");
			}
			else
			{
				// determine JTT based on values
				final String jtt = jttLookUp(propDataMode, result.isRecordFound());
				result.setMode(jtt);
			}
		}

		return result;
	}

	/**
	 * This executes stored procedure H56H1RPRC.
	 * 
	 * @param session
	 * @param system
	 * @param unit
	 * @param rpgm
	 * @param jtt
	 * @param dsaim
	 * @returns the result of the stored procedure call using a {@link H56H1RDS} object
	 * 
	 * @throws EQException
	 */
	private H56H1RDS callH56H1R(EquationStandardSession session, String system, String unit, String rpgm, String jtt, byte[] dsaim)
					throws EQException
	{

		String rrec = ""; // return code
		String rmes = ""; // error message
		String aow = ""; // warning messages;
		String etk = ""; // TODO
		String aext = ""; // Apply transaction during external input (default to N)
		String arec = ""; // Apply transaction during recovery (default to N)

		CallableStatement cs = null;

		String storedProcedureH56H1RPRC = "CALL KLIB" + unit + "/H56H1RPRC (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try
		{
			cs = session.getConnection().prepareCall(storedProcedureH56H1RPRC);

			cs.registerOutParameter(1, java.sql.Types.CHAR);
			cs.registerOutParameter(2, java.sql.Types.CHAR);
			cs.registerOutParameter(3, java.sql.Types.CHAR);
			cs.registerOutParameter(4, java.sql.Types.CHAR);
			cs.registerOutParameter(5, java.sql.Types.CHAR);
			cs.registerOutParameter(6, java.sql.Types.CHAR);
			cs.registerOutParameter(7, java.sql.Types.CHAR);
			cs.registerOutParameter(8, java.sql.Types.CHAR);
			cs.registerOutParameter(9, java.sql.Types.CHAR);
			cs.registerOutParameter(10, java.sql.Types.CHAR);
			cs.registerOutParameter(11, java.sql.Types.DECIMAL);
			cs.registerOutParameter(12, java.sql.Types.CHAR);
			cs.registerOutParameter(13, java.sql.Types.CHAR);

			if ("H48FRR".equals(rpgm.trim()))
			{
				cs.setString(1, Toolbox.pad("H48FRRD1", 10));
			}
			else
			{
				cs.setString(1, Toolbox.pad(rpgm, 10));
			}

			cs.setString(2, Toolbox.pad("", 4));
			cs.setString(3, Toolbox.pad("", 4));

			// set the Workstation ID field @WSID to "@GDP" to ensure changes don't get further propagated
			cs.setString(4, Toolbox.pad("@GDP", 4));
			cs.setString(5, Toolbox.pad(jtt, 1));
			cs.setBytes(6, dsaim);
			cs.setString(7, Toolbox.pad(rrec, 1));
			cs.setString(8, Toolbox.pad(rmes, 37));
			cs.setString(9, Toolbox.pad(aow, 740));
			cs.setString(10, Toolbox.pad(etk, 4));
			cs.setBigDecimal(11, new BigDecimal(0));
			cs.setString(12, Toolbox.pad(aext, 1));
			cs.setString(13, Toolbox.pad(arec, 1));

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Calling H56H1RPRC: "
								+ ("RPGM=" + rpgm) //
								+ ("\n JTT=" + jtt) //
								+ ("\n DSAIM=" + (dsaim != null ? StringUtils.substring(Toolbox.convertAS400TextIntoCCSID(dsaim,
												dsaim.length, session.getUnit().getCcsid()), 0, 200) : "null")));//
			}

			// Execute the call statement
			cs.execute();

			H56H1RDS result = new H56H1RDS();
			result.setReturnCode(cs.getString(7)); // return code
			result.setErrorMessage(cs.getString(8)); // error message
			result.setWarningMessage(cs.getString(9)); // warning messages

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Result of H56H1RPRC: " + ("\n RREC=" + result.getReturnCode()) + //
								("\n RMES=" + result.getErrorMessage()) + //
								("\n AOW=" + result.getWarningMessage())); //

			}
			return result;
		}
		catch (SQLException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Unable to call H56H1RPRC", e);
			}
			throw new EQException("Unable to call H56H1RPRC", e);
		}
		finally
		{
			SQLToolbox.close(cs);
		}
	}
	/**
	 * This executes stored procedure H56HSRPRC.
	 * 
	 * @param session
	 * @param system
	 * @param unit
	 * @param rpgm
	 * @param jtt
	 * @param dsaim
	 * @return - returns the result of the stored procedure call using a {@link H56HSRDS} object
	 * 
	 * @throws EQException
	 */
	private H56HSRDS callH56HSR(EquationStandardSession session, String system, String unit, String rpgm, String jtt, byte[] dsaim)
					throws EQException
	{
		String rrec = ""; // return code
		String rmes = ""; // error message
		String aow = ""; // warning messages;
		String etk = ""; // TODO
		String aext = ""; // Apply transaction during external input (default to N)
		String arec = ""; // Apply transaction during recovery (default to N)

		CallableStatement cs = null;

		String storedProcedureH56H1RPRC = "CALL KLIB" + unit + "/H56HIRPRC (?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try
		{
			cs = session.getConnection().prepareCall(storedProcedureH56H1RPRC);

			cs.registerOutParameter(1, java.sql.Types.CHAR);
			cs.registerOutParameter(2, java.sql.Types.CHAR);
			cs.registerOutParameter(3, java.sql.Types.CHAR);
			cs.registerOutParameter(4, java.sql.Types.CHAR);
			cs.registerOutParameter(5, java.sql.Types.CHAR);
			cs.registerOutParameter(6, java.sql.Types.CHAR);
			cs.registerOutParameter(7, java.sql.Types.CHAR);
			cs.registerOutParameter(8, java.sql.Types.CHAR);
			cs.registerOutParameter(9, java.sql.Types.CHAR);
			cs.registerOutParameter(10, java.sql.Types.CHAR);
			cs.registerOutParameter(11, java.sql.Types.DECIMAL);
			cs.registerOutParameter(12, java.sql.Types.CHAR);
			cs.registerOutParameter(13, java.sql.Types.CHAR);

			cs.setString(1, Toolbox.pad(rpgm, 10));
			cs.setString(2, Toolbox.pad("", 4));
			cs.setString(3, Toolbox.pad("", 4));
			cs.setString(4, Toolbox.pad("", 4));
			cs.setString(5, Toolbox.pad(jtt, 1));
			cs.setBytes(6, dsaim);
			cs.setString(7, Toolbox.pad(rrec, 1));
			cs.setString(8, Toolbox.pad(rmes, 37));
			cs.setString(9, Toolbox.pad(aow, 740));
			cs.setString(10, Toolbox.pad(etk, 4));
			cs.setBigDecimal(11, new BigDecimal(0));
			cs.setString(12, Toolbox.pad(aext, 1));
			cs.setString(13, Toolbox.pad(arec, 1));

			LOG.debug("Calling H56HIRPRC: " + ("RPGM=" + rpgm) //
							+ ("\n JTT=" + jtt) //
							+ ("\n DSAIM=" + (dsaim != null ? Toolbox.cvtBytesToHexString(dsaim) : "null")));//

			// Execute the call statement
			cs.execute();

			H56HSRDS result = new H56HSRDS();
			result.setReturnCode(cs.getString(7)); // return code
			result.setErrorMessage(cs.getString(8)); // error message
			result.setWarningMessage(cs.getString(9)); // warning messages

			if (LOG.isDebugEnabled())
			{
				LOG.debug("Result of H56HIRPRC: " + ("\n RREC=" + result.getReturnCode()) + //
								("\n RMES=" + result.getErrorMessage()) + //
								("\n AOW=" + result.getWarningMessage())); //
			}
			return result;
		}
		catch (SQLException e)
		{
			if (LOG.isErrorEnabled())
			{
				LOG.error("Unable to call H56HSRPRC", e);
			}
			throw new EQException("Unable to call H56HSRPRC", e);
		}
		finally
		{
			SQLToolbox.close(cs);
		}
	}

	public static boolean isVersionNew(EquationStandardSession session) throws EQException
	{
		String query = "SELECT COUNT(*) FROM CHPF WHERE CHENM LIKE '%EQ38%' OR  CHENM LIKE '%EQ39%'";
		List<Object[]> results = UnitAuditUtils.runUnitGenericQuery(session, query);
		if (results.size() > 0 && results.get(0).length > 0)
		{
			int result = Integer.parseInt(results.get(0)[0].toString());
			if (result > 0)
			{
				return true;
			}
		}

		return false;
	}

	private static GAURecordDataModel getAuditHeader(EquationStandardSession session, long auditHeaderId) throws EQException
	{
		String whereClause = "GAUSEQ = " + auditHeaderId;
		IGAURecordDao dao = new DaoFactory().getGAUDao(session, new GAURecordDataModel());
		List<GAURecordDataModel> headers = SAPJ10RUtils.coerce(dao.getRecordBy(whereClause));
		if (headers.size() > 0)
		{
			return headers.get(0);
		}

		return null;
	}

	private static GAVRecordDataModel getPropData(EquationStandardSession session, long auditHeaderId) throws EQException
	{
		String whereClause = "GAVDSEQ IN (SELECT GAUDSEQ FROM GAUPF WHERE GAUSEQ=" + auditHeaderId + ") FETCH FIRST 1 ROWS ONLY";
		final IGAVRecordDao dao = new DaoFactory().getGAVDao(session, new GAVRecordDataModel());
		final List<GAVRecordDataModel> propData = SAPJ10RUtils.coerce(dao.getRecordBy(whereClause));

		if (propData.size() > 0)
		{
			return propData.get(0);
		}
		else
		{
			return null;
		}
	}

	private static String jttLookUp(String mode, boolean recordExists)
	{
		if ("M".equalsIgnoreCase(mode))
		{
			return recordExists ? "M" : "A";
		}
		if ("A".equalsIgnoreCase(mode))
		{
			return recordExists ? "M" : "A";
		}
		if ("D".equalsIgnoreCase(mode))
		{
			// return recordExists ? "D" : null;
			// NOTE: Applicator must recognise that it should not do anything for "D" txns where the record no longer exists!
			return "D";
		}

		return null;
	}

	public boolean isWithCCSIDErrors()
	{
		return withCCSIDErrors;
	}
}
