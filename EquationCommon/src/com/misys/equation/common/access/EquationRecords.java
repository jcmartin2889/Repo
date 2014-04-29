/*
 * 
 */
package com.misys.equation.common.access;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.misys.equation.common.additionalinfo.EquationAdditionalInfoGroup;
import com.misys.equation.common.additionalinfo.EquationAdditionalInfoToolbox;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IAAIRecordDao;
import com.misys.equation.common.dao.IBDRecordDao;
import com.misys.equation.common.dao.IC8RecordDao;
import com.misys.equation.common.dao.ICARecordDao;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.IGBRecordDao;
import com.misys.equation.common.dao.IHARecordDao;
import com.misys.equation.common.dao.IHBRecordDao;
import com.misys.equation.common.dao.IHBXRecordDao;
import com.misys.equation.common.dao.IOCRecordDao;
import com.misys.equation.common.dao.IQZRecordDao;
import com.misys.equation.common.dao.IWECRecordDao;
import com.misys.equation.common.dao.beans.AAIRecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.BDRecordDataModel;
import com.misys.equation.common.dao.beans.C8RecordDataModel;
import com.misys.equation.common.dao.beans.CARecordDataModel;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.dao.beans.HARecordDataModel;
import com.misys.equation.common.dao.beans.HBRecordDataModel;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.dao.beans.QZRecordDataModel;
import com.misys.equation.common.dao.beans.WECRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Enhancement;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This class represents a unit, that is an instance of the banking application.
 */
public class EquationRecords
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationRecords.java 17691 2013-12-05 15:53:02Z lima12 $";

	// required if implementing serializable
	private static final long serialVersionUID = 1L;

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EquationRecords.class);
	private static DaoFactory daoFactory = new DaoFactory();
	private final EquationUnit unit;

	/** A cache of Equation GAEPF (Option) records. These are used to get Journal File Names and Offsets */
	private Map<String, GAERecordDataModel> gaeRecords;

	/** A cache of Equation GBPF (Option) records. These are used to validate new layout definition IDs */
	private Map<String, GBRecordDataModel> gbRecords;

	/** A cache of Equation WECPF (Maker-Checker) records. These are used to determine if Maker-Checker is appropriate. */
	private Map<String, WECRecordDataModel> wecRecords;

	/** A cache of Equation CAPF (Branch) records. These are used to convert branch mnemonic to branch number. */
	private Map<String, CARecordDataModel> caRecords;

	/** A cache of Equation AAIPF (Event charge mnemonic) records */
	private Map<String, AAIRecordDataModel> aaiRecords;

	/** A cache of Equation HAPF (System dictionary) records */
	private final Map<String, HARecordDataModel> haRecords = new Hashtable<String, HARecordDataModel>();

	/** A cache of Equation C8PF (Currency) records */
	private Map<String, C8RecordDataModel> c8Records;

	/** A cache of Equation OCPF (user) records */
	private Map<String, OCRecordDataModel> ocRecordsBFUser = new Hashtable<String, OCRecordDataModel>();

	/** A cache of Equation HBPF (Language + File prefix + Code description) records */
	private final Map<String, HBRecordDataModel> hbRecords = new Hashtable<String, HBRecordDataModel>();

	/** A cache of Equation HBXPF */
	private final Map<String, HBXRecordDataModel> hbxRecords = new Hashtable<String, HBXRecordDataModel>();

	/** Cache of HBXPF owner timestamps */
	private final Map<String, String> hbxRecordsTimestamps = new Hashtable<String, String>();

	/** Cache of Equation QZPF (system options) records */
	private final Map<String, QZRecordDataModel> qzRecords = new Hashtable<String, QZRecordDataModel>();

	/** Cache of Equation additional info item */
	private Map<String, BDRecordDataModel> bdRecords;

	/** Cache of Equation additional group */
	private Map<String, EquationAdditionalInfoGroup> additionalInfoGroup = new Hashtable<String, EquationAdditionalInfoGroup>();

	/** Cache of Equation additional info */
	private Map<String, EquationAdditionalInfoGroup> additionalInfo = new Hashtable<String, EquationAdditionalInfoGroup>();

	/**
	 * Default constructor
	 */
	public EquationRecords(EquationUnit unit)
	{
		this.unit = unit;
	}

	/**
	 * This will load records such as the misys and bank owned HBX texts
	 */
	public void setUpRecords()
	{
		try
		{
			// load misys and bank owned HBX texts
			reloadHBXRecords(HBXRecordDataModel.MISYS_OWNED_TEXT, false);
			reloadHBXRecords(HBXRecordDataModel.BANK_OWNED_TEXT, false);
		}
		catch (EQException exception)
		{
			LOG.error(exception);
		}
	}
	/**
	 * This will load records such as the misys and bank owned HBX texts
	 * 
	 * @param session
	 *            - session connection to the unit
	 */
	public void setUpRecords(EquationStandardSession session)
	{
		try
		{
			// load misys and bank owned HBX texts
			reloadHBXRecords(session, HBXRecordDataModel.MISYS_OWNED_TEXT, false);
			reloadHBXRecords(session, HBXRecordDataModel.BANK_OWNED_TEXT, false);
		}
		catch (EQException exception)
		{
			LOG.error(exception);
		}
	}

	/**
	 * Get the option Id details in the GAE file
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the GAERecordDataModel for the option specified
	 */
	public GAERecordDataModel getGAERecord(String optionId) throws EQException
	{
		return getGAERecords().get(optionId);
	}

	/**
	 * Finds the first GAE record matching the given program root.
	 * 
	 * @param programRoot
	 *            - the program root (GYFRO)
	 * 
	 * @return the GAERecordDataModel for the program root specified.
	 */
	public GAERecordDataModel getGAERecordByProgramRoot(String programRoot) throws EQException
	{
		for (GAERecordDataModel gae : getGAERecords().values())
		{
			if (StringUtils.substring(gae.getProgramRoot(), 0, 3).equalsIgnoreCase(StringUtils.substring(programRoot, 0, 3)))
			{
				// this is the one!
				return gae;
			}
		}

		// GAE for given program root not found!
		return null;
	}

	/**
	 * Get the option Id details in the GB file
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the GBRecordDataModel for the option specified
	 */
	public GBRecordDataModel getGBRecord(String optionId) throws EQException
	{
		return getGBRecords(true).get(optionId);
	}
	/**
	 * Return a (cached) Map of Equation GAEPF (option) records, keyed by option Id
	 * 
	 * @return a (cached) Map of Equation GAEPF (option) records, keyed by option Id
	 */
	@SuppressWarnings("unchecked")
	private Map<String, GAERecordDataModel> getGAERecords() throws EQException
	{
		IGAERecordDao dao = null;
		EquationStandardSession session = null;
		if (gaeRecords == null)
		{
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				GAERecordDataModel gaeRecord = new GAERecordDataModel();
				dao = daoFactory.getGAEDao(session, gaeRecord);
				Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("GAEATYP <> '009'");
				gaeRecords = (Map<String, GAERecordDataModel>) list;
			}
			catch (EQException exception)
			{
				gaeRecords = new Hashtable<String, GAERecordDataModel>();
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
		return gaeRecords;
	}
	/**
	 * Return a (cached) Map of Equation GBPF (option) records, keyed by option Id
	 * 
	 * @param useCache
	 *            - use the cache of existing GBPF records if it exists
	 * 
	 * @return a (cached) Map of Equation GBPF (option) records, keyed by option Id
	 */
	@SuppressWarnings("unchecked")
	public Map<String, GBRecordDataModel> getGBRecords(boolean useCache) throws EQException
	{
		IGBRecordDao dao = null;
		EquationStandardSession session = null;
		if (gbRecords == null || useCache == false)
		{
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				GBRecordDataModel gbRecord = new GBRecordDataModel();
				dao = daoFactory.getGBDao(session, gbRecord);
				Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("1=1");
				gbRecords = (Map<String, GBRecordDataModel>) list;
			}
			catch (EQException exception)
			{
				gbRecords = new Hashtable<String, GBRecordDataModel>();
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
		return gbRecords;
	}

	/**
	 * Return a Map of layouts related to a service
	 * 
	 * @param serviceId
	 * @return a Map of Equation GBPF records where option id is the serviceId
	 * @throws EQException
	 */
	public Map<String, GBRecordDataModel> getEQ4LayoutsForService(String serviceId) throws EQException
	{
		Map<String, GBRecordDataModel> records = new LinkedHashMap<String, GBRecordDataModel>();

		Iterator<GBRecordDataModel> iterator = getGBRecords(true).values().iterator();
		while (iterator.hasNext())
		{
			GBRecordDataModel gbRecord = iterator.next();

			if (gbRecord.getOptionIdGA().equals(serviceId))
			{
				records.put(gbRecord.getOptionId(), gbRecord);
			}
		}
		return records;
	}

	/**
	 * Get the option Id details in AAI
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return AAIRecordDataModel for the option specified
	 */
	public AAIRecordDataModel getAAIRecord(String optionId) throws EQException
	{
		return getAAIRecords().get(optionId);
	}

	/**
	 * Return a (cached) Map of Equation AAIPF (option) records, keyed by option Id
	 * 
	 * @return a (cached) Map of Equation AAIPF (option) records, keyed by option Id
	 */
	@SuppressWarnings("unchecked")
	private Map<String, AAIRecordDataModel> getAAIRecords() throws EQException
	{
		IAAIRecordDao dao = null;
		EquationStandardSession session = null;
		if (aaiRecords == null)
		{
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				AAIRecordDataModel aaiRecord = new AAIRecordDataModel();
				dao = daoFactory.getAAIDao(session, aaiRecord);
				Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("1=1");
				aaiRecords = (Map<String, AAIRecordDataModel>) list;
			}
			catch (EQException exception)
			{
				aaiRecords = new Hashtable<String, AAIRecordDataModel>();
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
		return aaiRecords;
	}

	/**
	 * Return the currency details
	 * 
	 * @param currency
	 *            - the currency mnemonic
	 * 
	 * @return C8RecordDataModel for the currency specified
	 */
	public C8RecordDataModel getC8Record(String currency) throws EQException
	{
		return getC8Records().get(currency);
	}

	/**
	 * Return a (cached) Map of Equation C8PF (currency) records, keyed by currency mnemonic
	 * 
	 * @return a (cached) Map of Equation C8PF (currency) records, keyed by currency mnemonic
	 */
	@SuppressWarnings("unchecked")
	private Map<String, C8RecordDataModel> getC8Records() throws EQException
	{
		IC8RecordDao dao = null;
		EquationStandardSession session = null;
		if (c8Records == null)
		{
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				C8RecordDataModel c8Record = new C8RecordDataModel();
				dao = daoFactory.getC8Dao(session, c8Record);
				Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("1=1");
				c8Records = (Map<String, C8RecordDataModel>) list;
			}
			catch (EQException exception)
			{
				c8Records = new Hashtable<String, C8RecordDataModel>();
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
		return c8Records;
	}

	/**
	 * Get the user using CAS user id
	 * 
	 * @param userId
	 *            - the user id
	 * 
	 * @return the OC record
	 */
	public OCRecordDataModel getOCRecordBFUser(String userId) throws EQException
	{
		// check if it is already in the cache
		OCRecordDataModel ocRecord = ocRecordsBFUser.get(userId);

		// not yet cached
		if (ocRecord == null)
		{
			EquationStandardSession session = null;
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				ocRecord = new OCRecordDataModel();
				IOCRecordDao dao = daoFactory.getOCDao(session, ocRecord);

				List<AbsRecord> results = dao.getRecordBy("OCBFUS = '" + userId + "'");
				if (!results.isEmpty())
				{
					// Successfully found BankFusion user id
					ocRecord = (OCRecordDataModel) results.get(0);
					ocRecordsBFUser.put(userId, ocRecord);
				}
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}

		// return record
		return ocRecord;
	}

	/**
	 * Get the description of the code with file prefix ACE in the specified language
	 * 
	 * @param langugageId
	 *            - the language id
	 * @param file
	 *            - the file code
	 * @param code
	 *            - the code
	 * 
	 * @return the HB record
	 */
	public HBRecordDataModel getHBRecord(String languageId, String file, String code) throws EQException
	{
		// set up file key
		String fileKey = Toolbox.trim(Toolbox.pad(file, 3), 3);

		String language = Toolbox.trim(Toolbox.pad(languageId, 2), 2);
		if (language.equals(EquationUser.DEF_LANG))
		{
			language = EquationUser.DEF_LANG2;
		}

		// set up the file code
		String fileCode = Toolbox.trimr(code);

		// check if it is already in the cache
		HBRecordDataModel hbRecord = hbRecords.get(language + fileKey + fileCode);

		// not yet cached
		if (hbRecord == null)
		{
			IHBRecordDao dao = null;
			EquationStandardSession session = null;
			try
			{
				// in user language
				session = unit.getEquationSessionPool().getEquationStandardSession();
				hbRecord = new HBRecordDataModel(language, fileKey, fileCode);
				dao = daoFactory.getHBDao(session, hbRecord);
				hbRecord = dao.getHBRecord();

				// in english language
				if (hbRecord == null && language.trim().length() > 0)
				{
					hbRecord = new HBRecordDataModel(EquationUser.DEF_LANG2, fileKey, fileCode);
					dao = daoFactory.getHBDao(session, hbRecord);
					hbRecord = dao.getHBRecord();
				}

				// still not found?
				if (hbRecord == null)
				{
					hbRecord = new HBRecordDataModel(language, fileKey, fileCode);
				}

				// user's language
				hbRecords.put(language + fileKey + fileCode, hbRecord);

				// english language as well?
				if (language.trim().length() > 0 && hbRecord.getLanguageCode().trim().length() == 0)
				{
					hbRecords.put(EquationUser.DEF_LANG2 + fileKey + fileCode, hbRecord);
				}
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}

		// return record
		return hbRecord;
	}

	/**
	 * Remove a HB record from the cache
	 * 
	 * @param langugageId
	 *            - the language id
	 * @param file
	 *            - the file code
	 * @param code
	 *            - the code
	 * 
	 * @return the deleted HB record
	 * 
	 * @throws EQException
	 */
	public HBRecordDataModel removeHBRecord(String languageId, String file, String code) throws EQException
	{
		String key = getHBCacheKey(languageId, file, code);
		return hbRecords.remove(key);
	}

	/**
	 * Get the list of code definition
	 * 
	 * @param list
	 *            - the comma-separated list of HB code file to load
	 * 
	 * @return null
	 */
	public HBRecordDataModel getHBRecords(String list) throws EQException
	{
		// parse and construct where-clause
		String[] hbCodes = list.split(",");
		StringBuilder whereClause = new StringBuilder();
		for (String hbCode : hbCodes)
		{
			String hb = Toolbox.trim(hbCode.trim(), 3);
			if (hb.length() == 0)
			{
				continue;
			}

			if (whereClause.length() > 0)
			{
				whereClause.append(" " + "or" + " ");
			}
			whereClause.append("HBCFR = '" + hb + "'");
		}

		// no where clause?
		if (whereClause.length() == 0)
		{
			LOG.info("getHBRecords - No recods retrieved");
			return null;
		}

		// log where clause
		LOG.info("getHBRecords() - " + whereClause);

		EquationStandardSession session = null;
		try
		{
			// in user language
			session = unit.getEquationSessionPool().getEquationStandardSession();
			HBRecordDataModel hbRecord = new HBRecordDataModel("", "", "");
			IHBRecordDao dao = daoFactory.getHBDao(session, hbRecord);
			Hashtable<String, AbsRecord> hbCodeRecords = dao.getHashtableRecordBy(whereClause.toString());

			// load records if found
			if (hbCodeRecords != null)
			{
				for (Map.Entry<String, AbsRecord> record : hbCodeRecords.entrySet())
				{
					hbRecord = (HBRecordDataModel) record.getValue();
					String key = getHBCacheKey(hbRecord.getLanguageCode(), hbRecord.getFilePrefix(), hbRecord.getFileKey());
					hbRecords.put(key, hbRecord);
				}
			}
		}
		catch (EQException e)
		{
			LOG.error(e);
		}
		finally
		{
			if (session != null)
			{
				unit.getEquationSessionPool().returnEquationStandardSession(session);
			}
		}

		// return record
		return null;
	}
	
	/**
	 * Return the HB cache key
	 * 
	 * @param languageId
	 *            - the language id
	 * @param file
	 *            - the file prefix
	 * @param code
	 *            - the code
	 * 
	 * @return the HB cache key
	 */
	private String getHBCacheKey(String languageId, String file, String code)
	{
		// set up file key
		String fileKey = Toolbox.trim(Toolbox.pad(file, 3), 3);

		String language = Toolbox.trim(Toolbox.pad(languageId, 2), 2);
		if (language.equals(EquationUser.DEF_LANG))
		{
			language = EquationUser.DEF_LANG2;
		}

		// set up the file code
		String fileCode = Toolbox.trimr(code);

		// return the key
		return language + fileKey + fileCode;
	}

	/**
	 * Get the HBX text based on the owner, language, type and key
	 * <p>
	 * The text is looked for in the user language and if this is not found then the base language of the service is used.
	 * <p>
	 * If the text is still not found, the text is looked for in the default language "GB"
	 * 
	 * @param owner
	 *            - text owner
	 * @param languageCode
	 *            - user language
	 * @param type
	 *            - text type
	 * @param key
	 *            - text key
	 * @param serviceBaseLangauge
	 *            - service or layout base language
	 * @return
	 * @throws EQException
	 */
	public HBXRecordDataModel getHBXRecord(String owner, String languageCode, String type, String key, String serviceBaseLangauge)
					throws EQException
	{
		String hbxOwner = Toolbox.trimr(owner);
		String language = Toolbox.trim(Toolbox.pad(languageCode, 2), 2);
		String hbxType = Toolbox.trimr(type);
		String hbxKey = Toolbox.trimr(key);

		HBXRecordDataModel hbxRecord = null;

		// retrieve key in user language
		if (!language.equals(EquationUser.DEF_LANG) && language.trim().length() > 0)
		{
			hbxRecord = hbxRecords.get(hbxOwner + language + hbxType + hbxKey);
		}

		if (hbxRecord == null)
		{
			// retrieve key in the service base language
			if (serviceBaseLangauge != null && serviceBaseLangauge.trim().length() > 0)
			{
				hbxRecord = hbxRecords.get(hbxOwner + serviceBaseLangauge + hbxType + hbxKey);
			}

			// retrieve key in the default language
			if (hbxRecord == null)
			{
				hbxRecord = hbxRecords.get(hbxOwner + EquationUser.DEF_LANG + hbxType + hbxKey);
			}
		}

		return hbxRecord;
	}

	/**
	 * Loads/re-loads a (cached) Map of Equation HBXPF records for a specific owner
	 * <p>
	 * This should be invoked when we need to reload the cache
	 * 
	 * @param owner
	 *            - the owner of the HBX
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * 
	 * @return a (cached) Map of Equation HBXPF records for a specific language
	 * @throws EQException
	 */
	public void reloadHBXRecords(String owner, boolean forceCheck) throws EQException
	{
		// This HBXPF installed test is to prevent users with only the EquationCommon layer from having problems if the HBXPF is not
		// installed. HBXPF holds text related to WebFacing or Services. If Service Composer is installed problem with the HBXPF
		// must not be hidden.
		if (unit.isHBXPFInstalled() || unit.isEnhancementInstalled(Enhancement.K534)
						|| unit.isEnhancementInstalled(Enhancement.K558))
		{
			if (owner == null || owner.trim().length() == 0)
			{
				return;
			}

			EquationStandardSession session = null;
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				reloadHBXRecords(session, owner, forceCheck);
			}
			catch (EQException exception)
			{
				LOG.error(exception);
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
	}
	/**
	 * Loads/re-loads a (cached) Map of Equation HBXPF records for a specific owner
	 * <p>
	 * This should be invoked when we need to reload the cache
	 * 
	 * @param session
	 * @param owner
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * 
	 * @return a (cached) Map of Equation HBXPF records for a specific language
	 * 
	 * @throws EQException
	 */
	@SuppressWarnings("unchecked")
	public void reloadHBXRecords(EquationStandardSession session, String owner, boolean forceCheck) throws EQException
	{
		// This HBXPF installed test is to prevent users with only the EquationCommon layer from having problems if the HBXPF is not
		// installed. HBXPF holds text related to WebFacing or Services. If Service Composer is installed problem with the HBXPF
		// must not be hidden.
		if (unit.isHBXPFInstalled() || unit.isEnhancementInstalled(Enhancement.K534)
						|| unit.isEnhancementInstalled(Enhancement.K558))
		{
			if (owner == null || owner.trim().length() == 0)
			{
				return;
			}

			String hbxRecordTimestampId = unit.getUnitId() + owner.trim();

			IHBXRecordDao hbxDao = null;
			PreparedStatement statement = null;
			ResultSet resultSet = null;

			try
			{
				hbxDao = daoFactory.getHBXDao(session, new HBXRecordDataModel());

				// Get the timestamp from the cache (or zero it if null)
				String timestamp = hbxRecordsTimestamps.get(hbxRecordTimestampId);

				// Determine whether HBX should be loaded or not
				boolean newHbxRecordsFound = false;

				// Check for latest record?
				if (forceCheck || timestamp == null)
				{
					if (timestamp == null)
					{
						// Retrieve the max timestamp from hx
						String sql = "SELECT MAX(HBXTIM) FROM HBXPF WHERE HBXOWN = ?";

						statement = session.getConnection().prepareStatement(sql);
						statement.setString(1, owner);
						resultSet = statement.executeQuery();
						if (resultSet.next())
						{
							timestamp = resultSet.getString(1);
							if (timestamp != null)
							{
								hbxRecordsTimestamps.put(hbxRecordTimestampId, timestamp);
								newHbxRecordsFound = true;
							}
						}
					}
					else
					{
						// find records with later timestamp
						HBXRecordDataModel hbxRecord = hbxDao.findWithLaterTimestamp(owner, timestamp);

						// if no record found, no need to update hbxRecords
						if (hbxRecord != null)
						{
							hbxRecordsTimestamps.put(hbxRecordTimestampId, hbxRecord.getTimestamp());
							newHbxRecordsFound = true;
						}
					}
				}

				// if there are new records, reload hbx records in the cache
				if (newHbxRecordsFound)
				{
					Map<String, ? extends AbsRecord> list = hbxDao.getHashtableRecordBy("HBXOWN='" + owner + "'");
					if (list != null)
					{
						hbxRecords.putAll((Map<String, HBXRecordDataModel>) list);
					}
				}
			}
			catch (SQLException exception)
			{
				LOG.error(exception);
				throw new EQException(exception);
			}
			finally
			{
				SQLToolbox.close(resultSet);
				SQLToolbox.close(statement);
			}
		}
	}

	/**
	 * Return a (cached) Map of Equation HBXPF records for a specific language
	 * <p>
	 * Use this method when you need to use HBX text without reloading the cache
	 * 
	 * @return a (cached) Map of Equation HBXPF records for a specific language
	 */
	public Map<String, HBXRecordDataModel> getHBXRecords()
	{
		return hbxRecords;
	}

	/**
	 * Get the specified system dictionary
	 * 
	 * @param languageId
	 *            - the language id
	 * @param code
	 *            - the code
	 * 
	 * @return the HA record
	 */
	public HARecordDataModel getHARecord(String languageId, String code) throws EQException
	{
		// set up the language
		String language = Toolbox.trim(Toolbox.pad(languageId, 2), 2);
		if (language.equals(EquationUser.DEF_LANG))
		{
			language = EquationUser.DEF_LANG2;
		}

		// set up the file code
		String fileCode = Toolbox.trimr(code);

		// check if it is already in the cache
		HARecordDataModel haRecord = haRecords.get(language + fileCode);

		// not yet cached
		if (haRecord == null)
		{
			IHARecordDao dao = null;
			EquationStandardSession session = null;
			try
			{
				// in user language
				session = unit.getEquationSessionPool().getEquationStandardSession();
				haRecord = new HARecordDataModel(language, fileCode);
				dao = daoFactory.getHADao(session, haRecord);
				haRecord = dao.getHARecord();

				// in english language
				if (haRecord == null && language.trim().length() > 0)
				{
					haRecord = new HARecordDataModel(EquationUser.DEF_LANG2, fileCode);
					dao = daoFactory.getHADao(session, haRecord);
					haRecord = dao.getHARecord();
				}

				// still not found?
				if (haRecord == null)
				{
					haRecord = new HARecordDataModel(language, fileCode);
				}

				// user's language
				haRecords.put(language + fileCode, haRecord);

				// english language as well?
				if (language.trim().length() > 0 && haRecord.getLanguageCode().trim().length() == 0)
				{
					haRecords.put(EquationUser.DEF_LANG2 + fileCode, haRecord);
				}
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}

		// return record
		return haRecord;
	}

	/**
	 * Refresh the option files so that it is reloaded the next time it is accessed
	 */
	public void refreshOptionFiles()
	{
		gbRecords = null;
		aaiRecords = null;
	}

	/**
	 * Reload GBPF and AAIPF cache
	 * 
	 * @param optionId
	 *            - the option Id
	 * @throws EQException
	 */
	public void reloadOption(String optionId) throws EQException
	{
		EquationStandardSession session = null;

		try
		{
			session = unit.getEquationSessionPool().getEquationStandardSession();

			if (gbRecords != null)
			{
				GBRecordDataModel gbRecord = new GBRecordDataModel(optionId);
				IGBRecordDao dao = daoFactory.getGBDao(session, gbRecord);
				gbRecord = dao.getGBRecord();

				// record not found, then ignore this. This could happen for CRM/AC1/AC2/AC3
				if (gbRecord != null)
				{
					GBRecordDataModel gbRecordCache = gbRecords.get(optionId);
					if (gbRecordCache != null)
					{
						gbRecordCache.updateWithThisRecord(gbRecord);
					}
					else
					{
						gbRecords.put(optionId, gbRecord);
					}
				}
			}

			if (aaiRecords != null)
			{
				AAIRecordDataModel aaiRecord = new AAIRecordDataModel(optionId);
				IAAIRecordDao dao2 = daoFactory.getAAIDao(session, aaiRecord);
				aaiRecord = dao2.getAAIRecord();

				if (aaiRecord != null)
				{
					aaiRecords.get(optionId).updateWithThisRecord(aaiRecord);
				}
				else
				{
					aaiRecords.remove(optionId);
				}
			}
		}
		finally
		{
			if (session != null)
			{
				unit.getEquationSessionPool().returnEquationStandardSession(session);
			}
		}
	}

	/**
	 * Clear cache
	 */
	public void clear()
	{
		if (gaeRecords != null)
		{
			gaeRecords = null;
		}

		if (gbRecords != null)
		{
			gbRecords = null;
		}

		if (wecRecords != null)
		{
			wecRecords = null;
		}

		if (caRecords != null)
		{
			caRecords = null;
		}

		if (aaiRecords != null)
		{
			aaiRecords = null;
		}

		if (haRecords != null)
		{
			haRecords.clear();
		}

		if (c8Records != null)
		{
			c8Records = null;
		}

		if (hbRecords != null)
		{
			hbRecords.clear();
		}

		if (hbxRecords != null)
		{
			hbxRecords.clear();
		}

		if (hbxRecordsTimestamps != null)
		{
			hbxRecordsTimestamps.clear();
		}

		if (qzRecords != null)
		{
			qzRecords.clear();
		}

		if (bdRecords != null)
		{
			bdRecords = null;
		}

		if (additionalInfo != null)
		{
			additionalInfo.clear();
		}

		if (additionalInfoGroup != null)
		{
			additionalInfoGroup.clear();
		}
		if (ocRecordsBFUser != null)
		{
			ocRecordsBFUser.clear();
		}
	}

	/**
	 * Get the QZ record
	 * 
	 * @param systemOptionId
	 *            - the system option id
	 * 
	 * @return the QZ record
	 */
	public QZRecordDataModel getQZRecord(String systemOptionId) throws EQException
	{
		// cache already?
		QZRecordDataModel qzRecord = qzRecords.get(systemOptionId);
		if (qzRecord != null)
		{
			return qzRecord;
		}

		// retrieve the system option detail from the database
		EquationStandardSession session = null;
		try
		{
			session = unit.getEquationSessionPool().getEquationStandardSession();
			QZRecordDataModel dataModel = new QZRecordDataModel(systemOptionId);
			IQZRecordDao dao = daoFactory.getQZDao(session, dataModel);
			qzRecord = dao.getQZRecordByQZFID();
			qzRecords.put(systemOptionId, qzRecord);
			return qzRecord;
		}
		finally
		{
			if (session != null)
			{
				unit.getEquationSessionPool().returnEquationStandardSession(session);
			}
		}
	}

	/**
	 * Return the additional item info
	 * 
	 * @param itemName
	 *            - the item name
	 * 
	 * @return the additional info item
	 */
	public BDRecordDataModel getBDRecord(String itemName) throws EQException
	{
		return getBDRecords().get(itemName);
	}

	/**
	 * Return a (cached) Map of Equation BDPF (currency) records, keyed by currency mnemonic
	 * 
	 * @return a (cached) Map of Equation BDPF (currency) records, keyed by currency mnemonic
	 */
	@SuppressWarnings("unchecked")
	private Map<String, BDRecordDataModel> getBDRecords() throws EQException
	{
		IBDRecordDao dao = null;
		EquationStandardSession session = null;
		if (bdRecords == null)
		{
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				BDRecordDataModel bdRecord = new BDRecordDataModel();
				dao = daoFactory.getBDDao(session, bdRecord);
				Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("1=1");
				bdRecords = (Map<String, BDRecordDataModel>) list;
			}
			catch (EQException exception)
			{
				bdRecords = new Hashtable<String, BDRecordDataModel>();
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
		return bdRecords;
	}

	/**
	 * Return the Equation additional group given the parameter file and value
	 * 
	 * @param parameterFile
	 *            - the parameter file
	 * @param parameterValue
	 *            - the parameter value
	 * @return
	 */
	public EquationAdditionalInfoGroup getEquationAdditionalInfoGroup(String parameterFile, String parameterValue)
					throws EQException
	{
		String key = Toolbox.pad(parameterFile.trim(), 2) + Toolbox.pad(parameterValue.trim(), 20);
		EquationAdditionalInfoGroup infoGroup = additionalInfo.get(key);

		// if not found, then try to load from database
		if (infoGroup == null)
		{
			EquationStandardSession session = null;
			try
			{

				session = unit.getEquationSessionPool().getEquationStandardSession();
				String informationGroup = EquationAdditionalInfoToolbox.retrieveGroup(session, parameterFile, parameterValue);
				infoGroup = getEquationAdditionalInfoGroup(session, informationGroup);

				if (infoGroup != null)
				{
					additionalInfo.put(key, infoGroup);
				}
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}

		return infoGroup;
	}

	/**
	 * Retrieve the additional information group detail
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param informationGroup
	 *            - the information group
	 * 
	 * @return the additional information group detail
	 */
	public EquationAdditionalInfoGroup getEquationAdditionalInfoGroup(EquationStandardSession session, String informationGroup)
					throws EQException
	{
		EquationAdditionalInfoGroup infoGroup = additionalInfoGroup.get(informationGroup);

		// not found, then load from database
		if (infoGroup == null)
		{
			infoGroup = new EquationAdditionalInfoGroup(informationGroup);
			infoGroup.refresh(session);
			additionalInfoGroup.put(informationGroup, infoGroup);
		}

		return infoGroup;
	}
	/**
	 * Return a (cached) Map of Equation WECPF (maker-checker) records, keyed by option Id
	 * 
	 * @return a (cached) Map of Equation WECPF (maker-checker) records, keyed by option Id
	 */
	@SuppressWarnings("unchecked")
	public Map<String, WECRecordDataModel> getWECRecords() throws EQException
	{
		IWECRecordDao dao = null;
		EquationStandardSession session = null;
		if (wecRecords == null)
		{
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				WECRecordDataModel wecRecord = new WECRecordDataModel();
				dao = daoFactory.getWECDao(session, wecRecord);
				Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("1=1");
				wecRecords = (Map<String, WECRecordDataModel>) list;
			}
			catch (EQException exception)
			{
				wecRecords = new Hashtable<String, WECRecordDataModel>();
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
		return wecRecords;
	}

	/**
	 * Return a (cached) Map of Equation CAPF (branch) records, keyed by branch mnemonic
	 * 
	 * @return a (cached) Map of Equation CAPF (branch) records, keyed by branch mnemonic
	 */
	@SuppressWarnings("unchecked")
	public Map<String, CARecordDataModel> getCARecords() throws EQException
	{
		ICARecordDao dao = null;
		EquationStandardSession session = null;
		if (caRecords == null)
		{
			try
			{
				session = unit.getEquationSessionPool().getEquationStandardSession();
				CARecordDataModel caRecord = new CARecordDataModel();
				dao = daoFactory.getCARecordDao(session, caRecord);
				Map<String, ? extends AbsRecord> list = dao.getHashtableRecordBy("1=1");
				caRecords = (Map<String, CARecordDataModel>) list;
			}
			catch (EQException exception)
			{
				caRecords = new Hashtable<String, CARecordDataModel>();
				throw exception;
			}
			finally
			{
				if (session != null)
				{
					unit.getEquationSessionPool().returnEquationStandardSession(session);
				}
			}
		}
		return caRecords;
	}
	/**
	 * Get the branch mnemonic details in the CA file
	 * 
	 * @param branchMnemonic
	 *            - the branch mnemonic
	 * 
	 * @return the CARecordDataModel for the branch mnemonic specified
	 */
	public CARecordDataModel getCARecord(String branchMnemonic) throws EQException
	{
		return getCARecords().get(branchMnemonic);
	}
	/**
	 * Get the option Id details in the WEC file
	 * 
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the WECRecordDataModel for the option specified
	 */
	public WECRecordDataModel getWECRecord(String optionId) throws EQException
	{
		return getWECRecords().get(optionId);
	}
}
