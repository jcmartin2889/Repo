package com.misys.equation.common.globalprocessing.audit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.StringUtils;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IAPJRecordDao;
import com.misys.equation.common.dao.IAPVRecordDao;
import com.misys.equation.common.dao.beans.APJRecordDataModel;
import com.misys.equation.common.dao.beans.APVRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;

/**
 * Caches APV and APJ records and provides utility methods for looking up data.
 * 
 * @author berzosa
 */
public class APVCacheUtil
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: APVCacheUtil.java 10420 2011-02-03 12:22:26Z MACDONP1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(APVCacheUtil.class);

	/** Cache of APJ records by APJ identifier */
	private final Map<String, List<APJRecordDataModel>> recordsByAPJARF = new HashMap<String, List<APJRecordDataModel>>();

	/** Cache of APV records by APVARF (Api Identifier) */
	private final Map<String, APVRecordDataModel> recordsByAPVARF = new HashMap<String, APVRecordDataModel>();

	/** Cache of APV records by APVFRO (Program Root) -- use first three characters for matching only!!! */
	private final Map<String, List<APVRecordDataModel>> recordsByAPVFRO = new HashMap<String, List<APVRecordDataModel>>();

	private APVCacheUtil()
	{
		// singleton instance..
	}

	/**
	 * Static holder singleton idiom.
	 */
	private static class APVCacheUtilHolder
	{
		/** APVCacheUtil singleton instance */
		private static final APVCacheUtil INSTANCE = new APVCacheUtil();

		/** initialised flag */
		private static volatile boolean initialised = false;

		/**
		 * Prepares this instance (if not already initialised).
		 * 
		 * @param session
		 *            A session to use for initialising, if required
		 * @throws EQException
		 *             If errors occur during initialisation
		 */
		private static void prepare(EquationStandardSession session) throws EQException
		{
			if (!initialised)
			{
				synchronized (INSTANCE)
				{
					if (!initialised)
					{
						// clear current cache
						INSTANCE.recordsByAPJARF.clear();
						INSTANCE.recordsByAPVARF.clear();
						INSTANCE.recordsByAPVFRO.clear();

						// initialise cached data
						INSTANCE.initialise(session);

						// we are now cached!
						initialised = true;
					}
				}
			}
		}
	}

	/**
	 * Returns a singleton instance of the APVCacheUtil. It uses the given session to initialise cache values, if the cache has not
	 * been initialised.
	 * 
	 * @param session
	 *            - the {@link EquationStandardSession}
	 * @return the singleton instance of the APVCacheUtil
	 * @throws EQException
	 */
	public static APVCacheUtil getInstance(EquationStandardSession session) throws EQException
	{
		APVCacheUtilHolder.prepare(session);
		return APVCacheUtilHolder.INSTANCE;
	}

	/**
	 * Clears the current cache and reloads data from the database.
	 * 
	 * @param session
	 *            The session to use for reloading from the database
	 * @throws EQException
	 *             If any errors occur reloading the data
	 */
	public static void resetCache(EquationStandardSession session) throws EQException
	{
		LOG.debug("Reloading APV Cache...");

		// clear the 'initialised' flag
		APVCacheUtilHolder.initialised = false;

		// prepare the instance again
		APVCacheUtilHolder.prepare(session);

		LOG.debug("APV Cache reloaded.");
	}

	/**
	 * Initialises the cache by loading all APV records into memory.
	 * 
	 * @param session
	 *            Session to use for loading APV records
	 * @throws EQException
	 *             If any errors occur initialising the cache.
	 */
	private void initialise(EquationStandardSession session) throws EQException
	{
		// load all APV records from global library
		final IAPVRecordDao dao = new DaoFactory().getAPVDao(session, new APVRecordDataModel(), false);
		List<APVRecordDataModel> apvRecords = coerce(dao.getRecords());

		// expand the records!
		apvRecords = expandAPV(apvRecords);

		for (APVRecordDataModel apv : apvRecords)
		{
			// store in map by APVARF
			recordsByAPVARF.put(apv.getApiReference(), apv);

			// cache by program root - use first three characters for matching only!
			final String programRoot = StringUtils.substring(apv.getProgramRoot(), 0, 3);

			// store in map by APVFRO
			if (!recordsByAPVFRO.containsKey(programRoot))
			{
				// does not yet exist, create a new list for this
				recordsByAPVFRO.put(programRoot, new ArrayList<APVRecordDataModel>());
			}

			// add to cached list
			recordsByAPVFRO.get(programRoot).add(apv);

			// cache the APJ fields
			cacheAPIFields(session, apv.getApiReference());
		}
	}

	/**
	 * Loads the {@link APVRecordDataModel} and corresponding list of {@link APJRecordDataModel} given the API Identifier (APVARF).
	 * <p>
	 * This method always loads from the global library (which is always the most up-to-date).
	 * 
	 * @param session
	 *            The session to load records from
	 * @param apvArf
	 *            The API IDentifier
	 * @return The retrieved {@link APVFields}, null if not found.
	 */
	public static APVFields loadAPVFields(EquationStandardSession session, String apvArf)
	{
		// load all APV records from global library
		final IAPVRecordDao dao = new DaoFactory().getAPVDao(session, new APVRecordDataModel(), false);

		// NOTE: All "G*" records use the "G" api identifier!
		final String apiIdentifier = apvArf2ApjArf(apvArf);
		final List<APVRecordDataModel> apvRecords = coerce(dao.getRecordBy("APVARF = '" + apiIdentifier
						+ "' FETCH FIRST 1 ROW ONLY"));
		if (apvRecords.size() > 0)
		{
			// found the record with given id
			final APVRecordDataModel apv = apvRecords.get(0);

			// NOTE: just in case this was looked up as a "G" record, we MUST map back the actual apvArf that was requested!
			apv.setApiReference(apvArf);

			// load corresponding APJ fields
			final IAPJRecordDao apjDao = new DaoFactory().getAPJDao(session, new APJRecordDataModel(), false);
			final List<APJRecordDataModel> fields = coerce(apjDao.getRecordBy("APJARF = '" + apvArf2ApjArf(apvArf) + "' " //
							+ " ORDER BY APJASQ ASC"));

			// return pair
			return new APVFields(apv, fields);
		}
		else
		{
			// record not found!
			return null;
		}
	}

	/**
	 * Returns a list of cached APV records.
	 * 
	 * @return a list of cached APV records
	 */
	public List<APVRecordDataModel> getAPVRecords()
	{
		return new ArrayList<APVRecordDataModel>(recordsByAPVARF.values());
	}

	/**
	 * Returns the cached APV given its identifier, null if not found.
	 * 
	 * @param apvArf
	 *            The APIARF value
	 * @return The cached APV given its identifier, null if not found.
	 */
	public APVRecordDataModel findAPVById(String apvArf)
	{
		return recordsByAPVARF.get(apvArf);
	}

	/**
	 * Searches for all APV records given a program root, null if none found.
	 * 
	 * @param programRoot
	 * @return
	 */
	public List<APVRecordDataModel> findAPVByProgramRoot(String programRoot)
	{
		// use first three characters for matching only!
		return recordsByAPVFRO.get(StringUtils.substring(programRoot, 0, 3));
	}

	/**
	 * Returns corresponding APJRecords for given APV identifier.
	 * 
	 * @param apvArf
	 *            The APV Identifier
	 * @return All APJRecordDataModel beans for the given apvArf
	 */
	public List<APJRecordDataModel> findAPIFields(String apvArf)
	{
		return recordsByAPJARF.get(apvArf2ApjArf(apvArf));
	}

	/**
	 * Returns corresponding APVRecords for given APV type
	 * 
	 * @param apvTyp
	 *            The APV Type (DA - data /CU - customer)
	 * @return all {@link APVRecordDataModel} beans for the given apvTyp
	 */
	@SuppressWarnings("unchecked")
	public List<APVRecordDataModel> findAPVByType(final String apvTyp)
	{
		List<APVRecordDataModel> records = (List<APVRecordDataModel>) CollectionUtils.select(recordsByAPVARF.values(),
						new Predicate()
						{
							public boolean evaluate(Object object)
							{
								if (object instanceof APVRecordDataModel)
								{
									String type = StringUtils.trim(((APVRecordDataModel) object).getType());
									String typ = StringUtils.trim(apvTyp);

									if (StringUtils.isEmpty(typ) || StringUtils.isEmpty(type))
									{
										return false;
									}

									return StringUtils.equals(type, typ);
								}
								return false;
							}
						});
		return records;
	}

	/**
	 * Finds the APJARF value corresponding to the given APVARF value (NOTE: They are not always the same!)
	 * 
	 * @param apvArf
	 *            The APVARF value to find the corresponding APJARF value for.
	 * @return The APJARF value for the given APVARF value.
	 */
	private static String apvArf2ApjArf(String apvArf)
	{
		// NOTE: apvArf and apjArf are NOT always equal!
		final String apjArf;
		if (apvArf.startsWith("G"))
		{
			// special handling for G*: APJ to use is "G"
			apjArf = "G";
		}
		else
		{
			// no special handling; use same as APV Identifier
			apjArf = apvArf;
		}

		return apjArf;
	}

	/**
	 * Expands the APV records loaded. Specifically, this method expands the single "G" record to 10 records ('GK' to 'GU', except
	 * 'GS') since C1-C5 and P1-P5 codes all re-use "G". The APV record with reference "G" itself is not included in the final list.
	 * 
	 * @param apvRecords
	 *            The list of records loaded from the databases.
	 * @return The same records, but expanded for "G" codes
	 */
	private List<APVRecordDataModel> expandAPV(List<APVRecordDataModel> apvRecords)
	{
		final List<APVRecordDataModel> expandedList = new ArrayList<APVRecordDataModel>();
		for (APVRecordDataModel apv : apvRecords)
		{
			// removed export types MAB, MAO, and ACA
			if (!"G".equals(apv.getApiReference().trim()) && !"MAB".equals(apv.getApiReference().trim())
							&& !"ACA".equals(apv.getApiReference().trim()) && !"MAO".equals(apv.getApiReference().trim()))
			{
				// not the "G" record!
				expandedList.add(apv);
				continue;
			}

			// this is the "G" record, expand it to GK to GU records!

			// expand C two-character user codes
			final APVRecordDataModel apvGK = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGL = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGM = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGN = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGO = (APVRecordDataModel) apv.clone();

			// customise them...
			apvGK.setApiReference("GK");
			apvGL.setApiReference("GL");
			apvGM.setApiReference("GM");
			apvGN.setApiReference("GN");
			apvGO.setApiReference("GO");
			apvGK.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'C1'");
			apvGL.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'C2'");
			apvGM.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'C3'");
			apvGN.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'C4'");
			apvGO.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'C5'");
			apvGK.setApiDescription("C1 2-Char User Codes");
			apvGL.setApiDescription("C2 2-Char User Codes");
			apvGM.setApiDescription("C3 2-Char User Codes");
			apvGN.setApiDescription("C4 2-Char User Codes");
			apvGO.setApiDescription("C5 2-Char User Codes");

			// add them...
			expandedList.add(apvGK);
			expandedList.add(apvGL);
			expandedList.add(apvGM);
			expandedList.add(apvGN);
			expandedList.add(apvGO);

			// expand P user codes
			final APVRecordDataModel apvGP = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGQ = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGR = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGT = (APVRecordDataModel) apv.clone();
			final APVRecordDataModel apvGU = (APVRecordDataModel) apv.clone();

			// customise them...
			apvGP.setApiReference("GP");
			apvGQ.setApiReference("GQ");
			apvGR.setApiReference("GR");
			apvGT.setApiReference("GT");
			apvGU.setApiReference("GU");
			apvGP.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'P1'");
			apvGQ.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'P2'");
			apvGR.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'P3'");
			apvGT.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'P4'");
			apvGU.setConditions("VFAPP = 'D' AND VFTTP = 'B' AND SUBSTRING(VFDATA,5,2) = 'P5'");
			apvGP.setApiDescription("P1 User Codes");
			apvGQ.setApiDescription("P2 User Codes");
			apvGR.setApiDescription("P3 User Codes");
			apvGT.setApiDescription("P4 User Codes");
			apvGU.setApiDescription("P5 User Codes");

			// add them
			expandedList.add(apvGP);
			expandedList.add(apvGQ);
			expandedList.add(apvGR);
			expandedList.add(apvGT);
			expandedList.add(apvGU);
		}

		// return the expanded list
		return expandedList;
	}

	/**
	 * Loads corresponding APJRecords for given APV identifier. This method always looks up data from the global library (it is
	 * assumed the global library is always up to date and complete.)
	 * <p>
	 * NOTE: apvArf and apjArf are NOT always equal!
	 * 
	 * @param session
	 *            Session to use for connecting to the global library
	 * @param apvArf
	 *            The APV Identifier
	 * @return All APJRecordDataModel beans for the given apvArf
	 * @throws EQException
	 *             If an error occurs loading.
	 */
	private List<APJRecordDataModel> cacheAPIFields(EquationStandardSession session, String apvArf) throws EQException
	{
		final String apjArf = apvArf2ApjArf(apvArf);
		if (recordsByAPJARF.containsKey(apjArf))
		{
			return recordsByAPJARF.get(apjArf);
		}

		// never been loaded before, so load it now! (always from the global library)
		final IAPJRecordDao dao = new DaoFactory().getAPJDao(session, new APJRecordDataModel(), false);
		final List<APJRecordDataModel> fields = coerce(dao.getRecordBy("APJARF = '" + apjArf + "' " //
						+ " ORDER BY APJASQ ASC"));

		if (fields.isEmpty())
		{
			throw new EQException("No API Fields defined for API: " + apvArf);
		}

		// store in cache
		recordsByAPJARF.put(apjArf, fields);

		return fields;
	}

	/**
	 * Determines the GZ length based on the API field definitions.
	 * 
	 * @param apiFields
	 *            The API Field definitions to calculate the GZ length of
	 * @return The GZ Length of the API.
	 */
	public static int getGZLength(APVRecordDataModel apv, List<APJRecordDataModel> apiFields) throws EQException
	{
		for (int i = apiFields.size() - 1; i >= 0; i--)
		{
			final APJRecordDataModel apj = apiFields.get(i);
			if (apj.getApiFieldName().startsWith("GZ") || apj.getApiFieldName().startsWith("VF"))
			{
				// The length of the GZ for this API is the end offset of the last GZ field found
				return Integer.parseInt(apj.getApiFieldEnd());
			}
		}

		throw new EQException("Unable to find APILength for APJARF = " + apv.getApiReference());
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> coerce(List genericList)
	{
		return genericList;
	}

	/**
	 * This method returns pseudo columns (i.e., columns that are part of VFDATA) for the GZVF1P file for a given API file name.
	 * 
	 * @param apv
	 *            The {@link APVRecordDataModel} representing the API to get the pseudo columns for.
	 * @return The pseudo columns in a comma-delimited form, or null if this API does not have pseudo columns.
	 */
	public static String getPseudoGZVF1PColumns(APVRecordDataModel apv)
	{
		if (!"GZVF1P".equals(apv.getApiFileName()) || apv.getApiReference() == null)
		{
			// this API does not contain pseudo-fields
			return null;
		}

		String code = apv.getApiReference().trim();
		if (code.compareTo("GK") >= 0 && code.compareTo("GU") <= 0 && !"GS".equals(code))
		{
			// represents the expanded 'G codes!
			code = "G";
		}

		// NOTE: The following data was generated with this query:
		//
		// SELECT APJARF, 'SUBSTRING(VFDATA, ' || (INT(APJAFS)+12-35) || ', ' || INT(APJAFL) || ') AS ' || TRIM(APJACL) || ', ',
		// APJACL, APJAFT, APJAFS, APJAFE, APJAFL, APJAFI, APJAFF, APJFIL, APJADS FROM kgrpgdp/apjpf WHERE apjfil = 'GZVF1P' AND
		// (INT(APJAFS)+12-35 > 0)
		//
		// ...and then was tweaked based on test results.
		final StringBuilder pseudoFields = new StringBuilder();
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 27, 15) AS VFTLA1," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 42, 2) AS VFTLB1," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 44, 2) AS VFTLD1," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 46, 15) AS VFTLA2," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 61, 2) AS VFTLB2," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 63, 2) AS VFTLD2," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 65, 15) AS VFTLA3," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 80, 2) AS VFTLB3," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 82, 2) AS VFTLD3," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 84, 15) AS VFTLA4," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 99, 2) AS VFTLB4," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 101, 2) AS VFTLD4," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 103, 15) AS VFTLA5," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 118, 2) AS VFTLB5," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 120, 2) AS VFTLD5," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 122, 15) AS VFTLA6," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 137, 2) AS VFTLB6," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 139, 2) AS VFTLD6," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 141, 15) AS VFTLA7," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 156, 2) AS VFTLB7," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 158, 2) AS VFTLD7," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 160, 15) AS VFTLA8," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 175, 2) AS VFTLB8," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 177, 2) AS VFTLD8," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 179, 15) AS VFTLA9," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 194, 2) AS VFTLB9," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 196, 2) AS VFTLD9," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 198, 15) AS VFTLA10," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 213, 2) AS VFTLB10," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 215, 2) AS VFTLD10," : "");
		pseudoFields.append("T1".equals(code) ? "SUBSTRING(VFDATA, 217, 1) AS VFEB1," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD2," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR1," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 27, 1) AS VFSGN1," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 28, 11) AS VFRTE1," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 39, 1) AS VFSGN2," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 40, 11) AS VFRTE2," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 51, 1) AS VFSGN3," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 52, 11) AS VFRTE3," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 63, 1) AS VFSGN4," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 64, 11) AS VFRTE4," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 75, 1) AS VFSGN5," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 76, 11) AS VFRTE5," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 87, 1) AS VFSGN6," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 88, 11) AS VFRTE6," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 99, 1) AS VFSGN7," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 100, 11) AS VFRTE7," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 111, 1) AS VFSGN8," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 112, 11) AS VFRTE8," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 123, 1) AS VFSGN9," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 124, 11) AS VFRTE9," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 135, 1) AS VFSGN10," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 136, 11) AS VFRTE10," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 147, 1) AS VFSPR2," : "");
		pseudoFields.append("D".equals(code) ? "SUBSTRING(VFDATA, 148, 1) AS VFEB1," : "");
		pseudoFields.append("19".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("19".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("19".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("19".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("19".equals(code) ? "SUBSTRING(VFDATA, 27, 30) AS VFAON," : "");
		pseudoFields.append("19".equals(code) ? "SUBSTRING(VFDATA, 57, 1) AS VFEB1," : "");
		pseudoFields.append("G".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("G".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("G".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("G".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("G".equals(code) ? "SUBSTRING(VFDATA, 27, 30) AS VFUFD," : "");
		pseudoFields.append("G".equals(code) ? "SUBSTRING(VFDATA, 57, 1) AS VFEB1," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 27, 30) AS VFSPR," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 57, 1) AS VFEB1," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 58, 30) AS VFSUAB," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 88, 1) AS VFBLK," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 89, 1) AS VFEB2," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 90, 131) AS VFSP2," : "");
		pseudoFields.append("03".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("06".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("06".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("06".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("06".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("06".equals(code) ? "SUBSTRING(VFDATA, 27, 30) AS VFANN," : "");
		pseudoFields.append("06".equals(code) ? "SUBSTRING(VFDATA, 57, 1) AS VFEB1," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 27, 20) AS VFCUR," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 47, 3) AS VFCAB," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 50, 3) AS VFSCY," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 53, 1) AS VFCED," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 54, 3) AS VFMTN," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 57, 1) AS VFNUS," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 58, 1) AS VFNUO," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 59, 2) AS VFDBR," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 61, 2) AS VFDDR," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 63, 2) AS VFPDR," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 65, 1) AS VFIDD," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 66, 2) AS VFCBR," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 68, 2) AS VFCDR," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 70, 1) AS VFIDC," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 71, 3) AS GZCCYA," : "");
		pseudoFields.append("08".equals(code) ? "SUBSTRING(VFDATA, 74, 1) AS VFEB1," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 27, 30) AS VFGSD," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 57, 76) AS VFSGD," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 133, 1) AS VFEB1," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 134, 1) AS VFEB2," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 135, 86) AS VFSP2," : "");
		pseudoFields.append("SG".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 27, 30) AS VFSDE," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 57, 1) AS VFCHG," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 58, 2) AS VFCST," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 60, 7) AS VFAMC," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 67, 3) AS VFFRE," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 70, 7) AS VFDIV," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 77, 7) AS VFMIN," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 84, 7) AS VFMAX," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 91, 5) AS VFTAX," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 96, 2) AS VFWV1," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 98, 9) AS VFBA1," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 107, 23) AS VFSP2," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 130, 2) AS VFCHCD," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 132, 1) AS VFEB1," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 133, 1) AS VFEB2," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 134, 87) AS VFSP3," : "");
		pseudoFields.append("SJ".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 27, 20) AS VFRPD," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 47, 5) AS VFCCG," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 52, 5) AS VFCCL," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 57, 5) AS VFPMS," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 62, 5) AS VFPML," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 67, 5) AS VFPRO," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 72, 5) AS VFPRL," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 77, 5) AS VFESC," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 82, 5) AS VFESL," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 87, 5) AS VFHDG," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 92, 30) AS VFMBD," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 122, 2) AS VFCHCD," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 124, 1) AS VFEB1," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 125, 1) AS VFEB2," : "");
		pseudoFields.append("SQ".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFRPN," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 27, 30) AS VFFEN," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 57, 20) AS VFVCE," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 77, 3) AS VFFSP," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 80, 2) AS VFLNG," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 82, 1) AS VFFMT," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 83, 1) AS VFFTP," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 84, 3) AS VFDFL," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 87, 2) AS VFELN," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 89, 1) AS VFDPL," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 90, 3) AS VFJSP," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 93, 4) AS VFSPR," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 97, 3) AS VFJDF," : "");
		pseudoFields.append("SL".equals(code) ? "SUBSTRING(VFDATA, 100, 1) AS VFEB1," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFRPN," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 27, 20) AS VFSLD," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 47, 80) AS VFSLC," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 127, 1) AS VFEB1," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 128, 1) AS VFEB2," : "");
		pseudoFields.append("SS1".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 27, 20) AS VFSLD," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 47, 2) AS VFSRF," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 49, 3) AS VFUC1," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 52, 3) AS VFUC2," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 55, 80) AS VFSLC," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 135, 1) AS VFEB1," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 136, 1) AS VFEB2," : "");
		pseudoFields.append("SS2".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 27, 20) AS VFSLD," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 47, 6) AS VFBNO," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 53, 2) AS VFSP2," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 55, 5) AS VFRPN," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 60, 80) AS VFSLC," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 140, 1) AS VFEB1," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 141, 1) AS VFEB2," : "");
		pseudoFields.append("SS3".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("SD".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SD".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SD".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SD".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFRPN," : "");
		pseudoFields.append("SD".equals(code) ? "SUBSTRING(VFDATA, 27, 66) AS VFWRD," : "");
		pseudoFields.append("SD".equals(code) ? "SUBSTRING(VFDATA, 93, 1) AS VFEB1," : "");
		pseudoFields.append("SH".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SH".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SH".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SH".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFRPN," : "");
		pseudoFields.append("SH".equals(code) ? "SUBSTRING(VFDATA, 27, 66) AS VFPHD," : "");
		pseudoFields.append("SH".equals(code) ? "SUBSTRING(VFDATA, 93, 1) AS VFEB1," : "");
		pseudoFields.append("SM".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SM".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SM".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SM".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SM".equals(code) ? "SUBSTRING(VFDATA, 27, 66) AS VFEMS," : "");
		pseudoFields.append("SM".equals(code) ? "SUBSTRING(VFDATA, 93, 1) AS VFEB1," : "");
		pseudoFields.append("SN".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SN".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SN".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SN".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SN".equals(code) ? "SUBSTRING(VFDATA, 27, 66) AS VFEMS," : "");
		pseudoFields.append("SN".equals(code) ? "SUBSTRING(VFDATA, 93, 1) AS VFEB1," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFRPN," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 27, 100) AS VFCLN," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 127, 1) AS VFEB1," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 128, 1) AS VFEB2," : "");
		pseudoFields.append("SO".equals(code) ? "SUBSTRING(VFDATA, 221, 1) AS VFIND," : "");
		pseudoFields.append("SF1".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SF1".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SF1".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SF1".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SF1".equals(code) ? "SUBSTRING(VFDATA, 27, 25) AS VFPYM," : "");
		pseudoFields.append("SF1".equals(code) ? "SUBSTRING(VFDATA, 52, 10) AS VFSP2," : "");
		pseudoFields.append("SF1".equals(code) ? "SUBSTRING(VFDATA, 62, 1) AS VFEB1," : "");
		pseudoFields.append("SC".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SC".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SC".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SC".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFSPR," : "");
		pseudoFields.append("SC".equals(code) ? "SUBSTRING(VFDATA, 27, 20) AS VFSCN," : "");
		pseudoFields.append("SC".equals(code) ? "SUBSTRING(VFDATA, 47, 1) AS VFEB1," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFRPN," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 27, 119) AS VFFLO," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 146, 2) AS VFCRP," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 148, 79) AS VFBGW," : "");
		pseudoFields.append("SU".equals(code) ? "SUBSTRING(VFDATA, 227, 1) AS VFEB1," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 1, 4) AS VFBBN," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 5, 5) AS VFKCD," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 10, 12) AS VFINB," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 22, 5) AS VFRPN," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 27, 119) AS VFFLO," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 146, 2) AS VFCRP," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 148, 79) AS VFBGW," : "");
		pseudoFields.append("SV".equals(code) ? "SUBSTRING(VFDATA, 227, 1) AS VFEB1," : "");

		final String pseudoFieldsStr = pseudoFields.toString();
		if ("".equals(pseudoFieldsStr))
		{
			return null;
		}
		else
		{
			return pseudoFieldsStr.substring(0, pseudoFieldsStr.length() - 1);
		}
	}

	/**
	 * Combination of an {@link APVRecordDataModel} with its corresponding list of {@link APJRecordDataModel} fields.
	 * 
	 * @author berzosa
	 */
	public static class APVFields
	{
		private final APVRecordDataModel apv;
		private final List<APJRecordDataModel> fields;

		public APVFields(APVRecordDataModel apv, List<APJRecordDataModel> fields)
		{
			this.apv = apv;
			this.fields = fields;
		}

		public APVRecordDataModel getApv()
		{
			return apv;
		}

		public List<APJRecordDataModel> getFields()
		{
			return fields;
		}
	}
}
