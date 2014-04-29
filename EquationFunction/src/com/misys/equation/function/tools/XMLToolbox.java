/*
 * 
 */
package com.misys.equation.function.tools;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAXRecordDao;
import com.misys.equation.common.dao.IHBXRecordDao;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAXRecordDataModel;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.Language;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LinkService;
import com.misys.equation.function.beans.ServiceUserExit;
import com.misys.equation.function.beans.Translation;
import com.misys.equation.function.beans.UserExit;
import com.misys.equation.function.comparator.ElementDifference;
import com.misys.equation.function.comparator.FunctionJournalComparator;
import com.misys.equation.function.comparator.FunctionLayoutComparator;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.linkage.ServiceLinkage;

/**
 * Note that this toolbox class also serves as a cache of function information
 */
public class XMLToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XMLToolbox.java 17501 2013-11-01 11:06:12Z lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(XMLToolbox.class);

	private static XMLToolbox singletonXMLToolbox;
	private static Hashtable<String, String> functionTimestampCache = new Hashtable<String, String>();

	/** Cache of layout data as XML Strings */
	private static Map<String, Layout> layoutCache = new ConcurrentHashMap<String, Layout>();
	private static Map<String, String> layoutTimestampCache = new ConcurrentHashMap<String, String>();
	private static Map<String, EquationPVMetaData> pvCache = new ConcurrentHashMap<String, EquationPVMetaData>();
	private static Map<String, String> pvTimestampCache = new ConcurrentHashMap<String, String>();
	private static Map<String, Function> functionCache = new ConcurrentHashMap<String, Function>();
	private static Map<String, Translation> translationCache = new ConcurrentHashMap<String, Translation>();
	private static Map<String, String> translationTimestampCache = new ConcurrentHashMap<String, String>();
	private final DaoFactory daoFactory = new DaoFactory();

	private static Hashtable<String, String> linkServiceTimestampCache = new Hashtable<String, String>();
	private static Map<String, ServiceLinkage> linkServiceCache = new ConcurrentHashMap<String, ServiceLinkage>();

	private static Map<String, XSDStructureLink> xsdStructureLinks = new Hashtable<String, XSDStructureLink>();

	/** UTF-8 Charset name. Used when encoding/decoding XML */
	private static final String UTF8_CHARSET = "UTF-8";

	/*
	 * Constructor
	 */
	private XMLToolbox()
	{
	}

	/*
	 * Get the singleton context
	 */
	public static XMLToolbox getXMLToolbox()
	{
		synchronized (XMLToolbox.class)
		{
			if (singletonXMLToolbox == null)
			{
				singletonXMLToolbox = new XMLToolbox();
			}
		}
		return singletonXMLToolbox;
	}

	/*
	 * Protect against cloning
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
		// that'll teach 'em
	}

	/**
	 * Get a String of the Function XML
	 * 
	 * @param inputStream
	 * @return a String of the Function XML
	 * @throws EQException
	 */
	public String getFunctionXML(InputStream inputStream) throws EQException
	{
		String functionXML = null;
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			Document document = documentBuilderFactory.newDocumentBuilder().parse(inputStream);
			OutputFormat outputFormat = new OutputFormat(document);
			outputFormat.setIndenting(false);
			outputFormat.setOmitComments(true);
			outputFormat.setOmitDocumentType(true);
			outputFormat.setOmitXMLDeclaration(true);
			outputFormat.setPreserveEmptyAttributes(true);
			outputFormat.setPreserveSpace(false);
			StringWriter xmlStringWriter = new StringWriter();
			XMLSerializer xmlSerializer = new XMLSerializer(xmlStringWriter, outputFormat);
			xmlSerializer.serialize(document.getDocumentElement());
			functionXML = xmlStringWriter.toString();
		}
		catch (SAXException e)
		{
			throw new EQException("XMLToolbox: getFunctionXML Failed", e);
		}
		catch (ParserConfigurationException e)
		{
			throw new EQException("XMLToolbox: getFunctionXML Failed", e);
		}
		catch (IOException e)
		{
			throw new EQException("XMLToolbox: getFunctionXML Failed", e);
		}
		return functionXML;
	}

	/**
	 * Get a String of the Function XML
	 * 
	 * @param inputStream
	 * @return a String of the Function XML
	 * @throws EQException
	 */
	public String getTranslationXML(InputStream inputStream) throws EQException
	{
		String translationXML = null;
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			Document document = documentBuilderFactory.newDocumentBuilder().parse(inputStream);
			OutputFormat outputFormat = new OutputFormat(document);
			outputFormat.setIndenting(false);
			outputFormat.setOmitComments(true);
			outputFormat.setOmitDocumentType(true);
			outputFormat.setOmitXMLDeclaration(true);
			outputFormat.setPreserveEmptyAttributes(true);
			outputFormat.setPreserveSpace(false);
			StringWriter xmlStringWriter = new StringWriter();
			XMLSerializer xmlSerializer = new XMLSerializer(xmlStringWriter, outputFormat);
			xmlSerializer.serialize(document.getDocumentElement());
			translationXML = xmlStringWriter.toString();
		}
		catch (SAXException e)
		{
			throw new EQException("XMLToolbox: getTranslationXML Failed", e);
		}
		catch (ParserConfigurationException e)
		{
			throw new EQException("XMLToolbox: getTranslationXML Failed", e);
		}
		catch (IOException e)
		{
			throw new EQException("XMLToolbox: getTranslationXML Failed", e);
		}
		return translationXML;
	}

	/**
	 * Get a String of the Link Service XML
	 * 
	 * @param inputStream
	 * 
	 * @return a String of the Link Service XML
	 * 
	 * @throws EQException
	 */
	public String getLinkServiceXML(InputStream inputStream) throws EQException
	{
		String functionXML = null;
		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			Document document = documentBuilderFactory.newDocumentBuilder().parse(inputStream);
			OutputFormat outputFormat = new OutputFormat(document);
			outputFormat.setIndenting(false);
			outputFormat.setOmitComments(true);
			outputFormat.setOmitDocumentType(true);
			outputFormat.setOmitXMLDeclaration(true);
			outputFormat.setPreserveEmptyAttributes(true);
			outputFormat.setPreserveSpace(false);
			StringWriter xmlStringWriter = new StringWriter();
			XMLSerializer xmlSerializer = new XMLSerializer(xmlStringWriter, outputFormat);
			xmlSerializer.serialize(document.getDocumentElement());
			functionXML = xmlStringWriter.toString();
		}
		catch (SAXException e)
		{
			throw new EQException("XMLToolbox: getFunctionXML Failed", e);
		}
		catch (ParserConfigurationException e)
		{
			throw new EQException("XMLToolbox: getFunctionXML Failed", e);
		}
		catch (IOException e)
		{
			throw new EQException("XMLToolbox: getFunctionXML Failed", e);
		}
		return functionXML;
	}

	/**
	 * Get the Function bean
	 * 
	 * @param initialInputStream
	 *            - the input stream
	 * @return the Function bean
	 * @throws EQException
	 */
	public Function getFunctionBean(InputStream initialInputStream) throws EQException
	{
		String functionXML = getFunctionXML(initialInputStream);
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		Function functionBean = (Function) beanFactory.deserialiseXMLAsBean(functionXML, Function.class);
		return functionBean;
	}

	/**
	 * Get the Link Service bean
	 * 
	 * @param initialInputStream
	 *            - the input stream
	 * 
	 * @return the Link Service bean
	 * 
	 * @throws EQException
	 */
	public LinkService getLinkServiceBean(InputStream initialInputStream) throws EQException
	{
		String linkServiceXML = getFunctionXML(initialInputStream);
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		LinkService linkServiceBean = (LinkService) beanFactory.deserialiseXMLAsBean(linkServiceXML, LinkService.class);
		return linkServiceBean;
	}

	/**
	 * Get the Translation bean
	 * 
	 * @param initialInputStream
	 *            - the input stream
	 * @return the Translation bean
	 * @throws EQException
	 */
	public Translation getTranslation(InputStream initialInputStream) throws EQException
	{
		String translationXML = getTranslationXML(initialInputStream);
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		Translation translation = (Translation) beanFactory.deserialiseXMLAsBean(translationXML, Translation.class);
		return translation;
	}
	/**
	 * Get the service user exit bean
	 * 
	 * @param initialInputStream
	 *            - the input stream
	 * @return the UserExit bean
	 * @throws EQException
	 */
	public ServiceUserExit getServiceUserExitBean(InputStream initialInputStream) throws EQException
	{
		String userExitXML = getServiceUserExitXML(initialInputStream);
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		ServiceUserExit userExitBean = (ServiceUserExit) beanFactory.deserialiseXMLAsBean(userExitXML, ServiceUserExit.class);
		return userExitBean;
	}
	/**
	 * Get the UserExit bean
	 * 
	 * @param initialInputStream
	 *            - the input stream
	 * @return the UserExit bean
	 * @throws EQException
	 */
	public UserExit getUserExitBean(InputStream initialInputStream) throws EQException
	{
		String userExitXML = getUserExitXML(initialInputStream);
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		UserExit userExitBean = (UserExit) beanFactory.deserialiseXMLAsBean(userExitXML, UserExit.class);
		return userExitBean;
	}

	/**
	 * Get the EquationPVMetaData bean
	 * 
	 * @param initialInputStream
	 *            - the input stream
	 * @return the EquationPVMetaData bean
	 * @throws EQException
	 */
	public EquationPVMetaData getPvBean(InputStream initialInputStream) throws EQException
	{
		String pvXML = getPvXML(initialInputStream);
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		EquationPVMetaData userExitBean = (EquationPVMetaData) beanFactory.deserialiseXMLAsBean(pvXML, EquationPVMetaData.class);
		return userExitBean;
	}

	/**
	 * This method deserialises a bean from the supplied File object.
	 * 
	 * @param file
	 *            A File containing the XML to deserialise. The file contents must be in UTF-8.
	 * @param clazz
	 *            The expected bean class
	 * @return The bean object
	 */
	public static Object getBean(File file, Class<?> clazz)
	{
		Object result = null;
		Reader reader;
		try
		{
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), UTF8_CHARSET);
			reader = new BufferedReader(inputStreamReader);

			EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
			result = beanFactory.deserialiseXMLAsBean(reader, clazz);
		}
		catch (Exception e)
		{
			LOG.error("getBean", e);
			throw new EQRuntimeException(e);
		}
		return result;
	}

	/**
	 * Writes definition XML to the iSeries database (GAXPF)
	 * 
	 * @param unit
	 * @param code
	 *            - Type of definition
	 * @param key
	 *            - definition key
	 * @param timestamp
	 * @param definitionXML
	 *            - the XML as a String
	 * 
	 * @throws EQException
	 */
	public GAXRecordDataModel writeDefinitionXMLtoDB(EquationStandardSession session, String filLibrary, String code, String key,
					String timestamp, String definitionXML) throws Exception
	{
		GAXRecordDataModel gaxRecord = new GAXRecordDataModel(code, key);
		gaxRecord.setLibrary(filLibrary);
		IGAXRecordDao dao = daoFactory.getGAXDao(session, gaxRecord);
		gaxRecord = dao.getGAXRecord();

		if (gaxRecord == null)
		{

			gaxRecord = new GAXRecordDataModel(code, key);
			gaxRecord.setLibrary(filLibrary);
			gaxRecord.setTimestamp(timestamp);
			gaxRecord.setLayout(definitionXML);
			dao.insertRecord(gaxRecord);
		}
		else
		{
			gaxRecord.setTimestamp(timestamp);
			gaxRecord.setLayout(definitionXML);
			dao.updateRecord(gaxRecord);

		}
		return gaxRecord;
	}

	/**
	 * Read the function definition from the database with cache checking
	 * 
	 * @param session
	 *            - the Equation unit
	 * @param optionId
	 *            - the option Id
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * 
	 * @return the function
	 * 
	 * @throws EQException
	 */
	public Function getFunction(EquationStandardSession session, String optionId, boolean forceCheck) throws EQException
	{
		String unitId = session.getUnitId();
		Statement statement = null;
		ResultSet resultSet = null;

		try
		{
			// Determine whether to reload from database or not
			boolean reload = false;

			// Get the timestamp from the cache (or zero it if null)
			String timestamp = functionTimestampCache.get(unitId + optionId);

			// Check for latest record?
			if (forceCheck)
			{
				if (timestamp == null)
				{
					timestamp = "0";
				}
				// If we have one then need to check if there is a more up to date one in the database
				String sql = "SELECT COUNT(*) FROM GAXPF WHERE GAXCOD ='" + GAXRecordDataModel.SERVICE_CODE + "' and GAXKEY ='"
								+ optionId + "' and GAXTIM >'" + timestamp.trim() + "'";
				statement = session.getConnection().createStatement();
				resultSet = statement.executeQuery(sql);
				resultSet.next();
				reload = resultSet.getInt(1) > 0;
			}

			// Load only if has not been loaded
			else
			{
				reload = (timestamp == null);
			}

			// Reload from file?
			if (reload)
			{
				GAXRecordDataModel gaxRecord = new GAXRecordDataModel(GAXRecordDataModel.SERVICE_CODE, optionId);
				IGAXRecordDao dao = daoFactory.getGAXDao(session, gaxRecord);
				gaxRecord = dao.findWithLaterTimestamp(GAXRecordDataModel.SERVICE_CODE, optionId, timestamp);
				session.getUnit().getRecords().reloadOption(optionId);

				// load in the cache
				functionTimestampCache.put(unitId + optionId, gaxRecord.getTimestamp());
				EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
				Function function = (Function) beanFactory.deserialiseXMLAsBean(gaxRecord.getLayout(), Function.class);
				functionCache.put(unitId + optionId, function);

				// If the definition XML has changed, create a new class loader
				// to pick up any changed Java user exit classes. Note that we don't
				// need to do this when first accessing the definition XML
				if (timestamp != "0")
				{
					LOG.info("readLayout - layout has changed. Creating new ClassLoader");
					session.getUnit().createNewFunctionUserExitClassLoader();
				}

				// load function HBX
				session.getUnit().getRecords().reloadHBXRecords(session, function.rtvTextOwner(), forceCheck);

				// update link services
				updateLinkServiceCache(optionId);

				// update XSD
				updateXSDStructureCache(session, function);
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return functionCache.get(unitId + optionId);
	}

	/**
	 * This method will remove the Function bean from cache
	 * 
	 * This can be invoked when the object in the hash map has been modified but not save in the database therefore the data in the
	 * bean no longer reflects the data in the database i.e. when the function is cloned by the service wizard
	 * 
	 * @param unitId
	 * @param optionId
	 */
	public void removeFunctionBeanFromCache(String unitId, String optionId)
	{
		String key = unitId + optionId;
		try
		{
			if (functionTimestampCache.get(key) != null)
			{
				functionTimestampCache.remove(key);
				functionCache.remove(key);
			}
		}
		catch (Exception e)
		{

		}

	}

	/**
	 * This method will remove the Layout bean from cache
	 * 
	 * This can be invoked when the object in the hash map has been modified but not save in the database therefore the data in the
	 * bean no longer reflects the data in the database i.e. when the layout is cloned by the service wizard
	 * 
	 * @param unitId
	 * @param optionId
	 */
	public void removeLayoutBeanFromCache(String unitId, String optionId)
	{
		String key = unitId + optionId;
		try
		{
			if (layoutTimestampCache.get(key) != null)
			{
				layoutTimestampCache.remove(key);
				layoutCache.remove(key);

			}
		}
		catch (Exception e)
		{

		}

	}

	/**
	 * This method will remove the Function bean from cache
	 * 
	 * This can be invoked when the object in the hash map has been modified but not save in the database therefore the data in the
	 * bean no longer reflects the data in the database i.e. when the prompt validate is cloned by the service wizard
	 * 
	 * @param unitId
	 * @param optionId
	 */
	public void removePVMetaDataBeanFromCache(String unitId, String optionId)
	{
		String key = unitId + optionId;
		try
		{
			if (pvTimestampCache.get(key) != null)
			{
				pvTimestampCache.remove(key);
				pvCache.remove(key);
			}
		}
		catch (Exception e)
		{

		}

	}

	/**
	 * This method will remove the Translation bean from cache
	 * 
	 * @param unitId
	 * @param mainOwnerId
	 */
	public void removeTranslationFromCache(String unitId, String mainOwnerId)
	{
		String key = unitId + mainOwnerId;
		try
		{
			if (translationTimestampCache.get(key) != null)
			{
				translationTimestampCache.remove(key);
				translationCache.remove(key);
			}
		}
		catch (Exception e)
		{

		}

	}

	/**
	 * Read the layout definition from the database with cache checking
	 * 
	 * @param session
	 *            - the EquationStandardSession
	 * @param optionId
	 *            - the option Id
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * 
	 * @return the deserialised Layout instance
	 * 
	 * @throws EQException
	 */
	public Layout getLayout(EquationStandardSession session, String optionId, boolean forceCheck) throws EQException
	{
		String unitId = session.getUnitId();
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			// Determine whether to reload from database or not
			boolean reload = false;

			// Get the timestamp from the cache (or zero it if null)
			String timestamp = layoutTimestampCache.get(unitId + optionId);

			// Check for latest record?
			if (forceCheck)
			{
				if (timestamp == null)
				{
					timestamp = "0";
				}
				// If we have one then need to check if there is a more up to date one in the database
				String sql = "SELECT COUNT(*) FROM GAXPF WHERE GAXCOD ='" + GAXRecordDataModel.LAYOUT_CODE + "' and GAXKEY ='"
								+ optionId + "' and GAXTIM >'" + timestamp.trim() + "'";
				statement = session.getConnection().createStatement();
				resultSet = statement.executeQuery(sql);
				resultSet.next();
				reload = resultSet.getInt(1) > 0;
			}

			// Load only if has not been loaded
			else
			{
				reload = (timestamp == null);
			}

			// Reload from file?
			if (reload)
			{
				GAXRecordDataModel gaxRecord = new GAXRecordDataModel(GAXRecordDataModel.LAYOUT_CODE, optionId);
				IGAXRecordDao dao = daoFactory.getGAXDao(session, gaxRecord);
				gaxRecord = dao.findWithLaterTimestamp(GAXRecordDataModel.LAYOUT_CODE, optionId, timestamp);
				session.getUnit().getRecords().reloadOption(optionId);

				// load in the cache
				EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
				Layout layout = (Layout) beanFactory.deserialiseXMLAsBean(gaxRecord.getLayout(), Layout.class);

				// cache it
				layoutTimestampCache.put(unitId + optionId, gaxRecord.getTimestamp());
				layoutCache.put(unitId + optionId, layout);

				// If the definition XML has changed, create a new class loader
				// to pick up any changed Java user exit classes. Note that we don't
				// need to do this when first accessing the definition XML
				if (timestamp != "0")
				{
					LOG.info("readLayout - layout has changed. Creating new ClassLoader");
					session.getUnit().createNewFunctionUserExitClassLoader();
				}

				// load layout HBX
				session.getUnit().getRecords().reloadHBXRecords(session, layout.rtvTextOwner(), forceCheck);

				// update link services
				updateLinkServiceCache(optionId);
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return layoutCache.get(unitId + optionId);
	}

	/**
	 * Read the PV definition from the database with cache checking
	 * 
	 * @param session
	 *            - the EquationStandardSession
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the deserialised PV instance
	 * 
	 * @throws EQException
	 */
	public EquationPVMetaData getPv(EquationStandardSession session, String optionId) throws EQException
	{
		String unitId = session.getUnitId();
		Statement statement = null;
		ResultSet resultSet = null;
		try
		{
			// Get the timestamp from the cache (or zero it if null)
			String timestamp = pvTimestampCache.get(unitId + optionId);
			if (timestamp == null)
			{
				timestamp = "0";
			}
			// If we have one then need to check if there is a more up to date one in the database
			String sql = "SELECT COUNT(*) FROM GAXPF WHERE GAXCOD ='" + GAXRecordDataModel.PV_CODE + "' and GAXKEY ='" + optionId
							+ "' and GAXTIM >'" + timestamp.trim() + "'";
			statement = session.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.next();

			// if we have a shiny new one then...
			if (resultSet.getInt(1) > 0)
			{
				GAXRecordDataModel gaxRecord = new GAXRecordDataModel(GAXRecordDataModel.PV_CODE, optionId);
				IGAXRecordDao dao = daoFactory.getGAXDao(session, gaxRecord);
				gaxRecord = dao.findWithLaterTimestamp(GAXRecordDataModel.PV_CODE, optionId, timestamp);

				// load in the cache
				pvTimestampCache.put(unitId + optionId, gaxRecord.getTimestamp());
				EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
				EquationPVMetaData pvBean = (EquationPVMetaData) beanFactory.deserialiseXMLAsBean(gaxRecord.getLayout(),
								EquationPVMetaData.class);
				pvCache.put(unitId + optionId, pvBean);

				// If the definition XML has changed, create a new class loader
				// to pick up any changed Java user exit classes. Note that we don't
				// need to do this when first accessing the definition XML
				if (timestamp != "0")
				{
					LOG.info("readPV - PV has changed. Creating new ClassLoader");
					session.getUnit().createNewFunctionUserExitClassLoader();
				}
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return pvCache.get(unitId + optionId);
	}

	/**
	 * Read the translation definition from the database with cache checking
	 * 
	 * @param session
	 *            - the Equation unit
	 * @param owners
	 *            - owner IDs in case of service (with suffix .eqf, .eql ). The first one must be the service.
	 * @param useCache
	 *            - determines if he cache will be used. When retrieving Layouts for Services do not use the cache.
	 * 
	 * @return the translation or null if there are no records found on the HBXPF for the owner
	 * 
	 * @throws EQException
	 */
	public Translation getTranslationFromDBWithCache(EquationStandardSession session, List<String> owners, boolean useCache)
					throws EQException
	{
		String earliestDate = "0001-01-01";
		Timestamp newCacheTimeStamp = new java.sql.Timestamp(System.currentTimeMillis());
		if (owners == null || owners.isEmpty())
		{
			return null;
		}
		String unitId = session.getUnitId();
		Statement statement = null;
		ResultSet resultSet = null;

		// Main owner is the first in the list
		String mainOwnerId = owners.get(0);

		try
		{

			// Get the timestamp from the cache (or zero it if null)
			String cacheTimestamp = translationTimestampCache.get(unitId + mainOwnerId);

			StringBuilder hbxOwnerQueryString = new StringBuilder(" HBXOWN IN (");
			for (String owner : owners)
			{
				hbxOwnerQueryString.append("'").append(owner).append("',");
			}
			hbxOwnerQueryString.deleteCharAt(hbxOwnerQueryString.length() - 1);
			hbxOwnerQueryString.append(")");

			// If we have one then need to check if there is a more up to date
			// one in the database
			String changeTextTimestamp = null;
			String sql = "SELECT MAX(HBXTIM) FROM HBXPF WHERE "
							+ (cacheTimestamp != null ? (" HBXTIM > '" + cacheTimestamp.trim() + "' AND ") : "") // add timestamp if
							// not null
							+ hbxOwnerQueryString;
			statement = session.getConnection().createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next())
			{
				changeTextTimestamp = resultSet.getString(1);
			}

			// if cache should not be used or text has not been cached yet or there is a new data in the database
			if (useCache == false || cacheTimestamp == null || changeTextTimestamp != null)
			{
				IHBXRecordDao hBXRecordDao = daoFactory.getHBXDao(session, new HBXRecordDataModel());
				// This order by does not help as sort sequence in Java does not match
				String appendClause = hbxOwnerQueryString + " ORDER BY HBXLNM, HBXTYP, HBXKEY ASC";
				List<AbsRecord> records = hBXRecordDao.getRecordBy(appendClause);

				if (records.size() > 0)
				{
					// Create the translation bean and set the owner
					Translation translation = new Translation(mainOwnerId);

					// Retrieve the text and group by language into the translation bean
					Language language = null;

					for (AbsRecord record : records)
					{
						HBXRecordDataModel hbxRecordDataModel = (HBXRecordDataModel) record;
						Timestamp recordTimestamp = java.sql.Timestamp.valueOf(hbxRecordDataModel.getTimestamp());
						// if (recordTimestamp.before(newCacheTimeStamp))
						// {
						// newCacheTimeStamp = recordTimestamp;
						// }
						language = translation.getLanguage(hbxRecordDataModel.getLanguageCode());

						if (language == null)
						{
							language = new Language();
							language.setLanguageId(hbxRecordDataModel.getLanguageCode());
							translation.addLanguage(language);
						}
						boolean logicallyDeleted = hbxRecordDataModel.getTimestamp().substring(0, 10).equals(earliestDate);
						language.addText(hbxRecordDataModel.getOwner(), hbxRecordDataModel.getType(), hbxRecordDataModel.getKey(),
										hbxRecordDataModel.getText(), logicallyDeleted);

					}

					// load in the cache
					translationTimestampCache.put(unitId + mainOwnerId, newCacheTimeStamp.toString());
					translationCache.put(unitId + mainOwnerId, translation);
				}

			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		Translation translation = translationCache.get(unitId + mainOwnerId);
		if (translation == null)
		{
			translation = new Translation(mainOwnerId);
		}
		return translation;
	}
	/**
	 * Get a String of the service user exit XML
	 * 
	 * @param inputStream
	 * @return a String of the User Exit XML
	 * @throws EQException
	 */
	public String getServiceUserExitXML(InputStream inputStream) throws EQException
	{
		String userExitXML = null;
		try
		{
			byte[] inputBytes = new byte[1024];
			ByteArrayOutputStream inputByteArrayOutputStream = new ByteArrayOutputStream();
			int bytesRead = inputStream.read(inputBytes);
			while (bytesRead != -1)
			{
				inputByteArrayOutputStream.write(inputBytes, 0, bytesRead);
				bytesRead = inputStream.read(inputBytes);
			}
			userExitXML = inputByteArrayOutputStream.toString();
		}
		catch (IOException e)
		{
			throw new EQException("XMLToolbox - getUserExitXML: Failed", e);
		}
		return userExitXML;
	}
	/**
	 * Get a String of the User Exit XML
	 * 
	 * @param inputStream
	 * @return a String of the User Exit XML
	 * @throws EQException
	 */
	public String getUserExitXML(InputStream inputStream) throws EQException
	{
		String userExitXML = null;
		try
		{
			byte[] inputBytes = new byte[1024];
			ByteArrayOutputStream inputByteArrayOutputStream = new ByteArrayOutputStream();
			int bytesRead = inputStream.read(inputBytes);
			while (bytesRead != -1)
			{
				inputByteArrayOutputStream.write(inputBytes, 0, bytesRead);
				bytesRead = inputStream.read(inputBytes);
			}
			userExitXML = inputByteArrayOutputStream.toString();
		}
		catch (IOException e)
		{
			throw new EQException("XMLToolbox - getUserExitXML: Failed", e);
		}
		return userExitXML;
	}

	/**
	 * Get a String of the PV XML
	 * 
	 * @param inputStream
	 * @return a String of the PV XML
	 * @throws EQException
	 */
	public String getPvXML(InputStream inputStream) throws EQException
	{
		String pvXML = null;
		try
		{
			byte[] inputBytes = new byte[1024];
			ByteArrayOutputStream inputByteArrayOutputStream = new ByteArrayOutputStream();
			int bytesRead = inputStream.read(inputBytes);
			while (bytesRead != -1)
			{
				inputByteArrayOutputStream.write(inputBytes, 0, bytesRead);
				bytesRead = inputStream.read(inputBytes);
			}
			pvXML = inputByteArrayOutputStream.toString();
		}
		catch (IOException e)
		{
			throw new EQException("XMLToolbox - getPvXML: Failed", e);
		}
		return pvXML;
	}

	/**
	 * Process to serialise a function data
	 * 
	 * @param functionData
	 *            - the function data
	 * 
	 * @return the function data in string format
	 * 
	 * @throws EQException
	 */
	public String serialiseFunctionData(FunctionData functionData) throws EQException
	{
		String xml = EqBeanFactory.getEqBeanFactory().serialiseBeanAsXML(functionData);
		return xml;
	}

	/**
	 * Process to deserialise a function data
	 * 
	 * @param xml
	 *            - the function data in string format
	 * 
	 * @return the function data
	 * 
	 * @throws EQException
	 */
	public FunctionData deserialiseFunctionData(String xml) throws EQException
	{
		FunctionData functionData = (FunctionData) EqBeanFactory.getEqBeanFactory().deserialiseXMLAsBean(xml, FunctionData.class);
		return functionData;
	}

	/**
	 * Serialises a bean into XML and returns the XML as an InputStream in UTF-8 encoding
	 * <p>
	 * The InputStream can be used to set a File's contents.
	 * 
	 * @param bean
	 *            An Object to serialise
	 * @return an InputStream containing the XML in UTF-8 encoding
	 * 
	 * @throws EQException
	 */
	public static InputStream getBeanAsStream(Object bean) throws EQException
	{
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		String xml = beanFactory.serialiseBeanAsXML(bean);
		try
		{
			return new ByteArrayInputStream(xml.getBytes(UTF8_CHARSET));
		}
		catch (UnsupportedEncodingException e)
		{
			throw new EQException(e);
		}
	}

	/**
	 * Deserialises an <code>InputStream</code> containing an XML Document to a <code>Document</code>
	 * <p>
	 * Note that although this is XML processing, not specific to Schemas, this is currently only used when loading a BankFusion
	 * schema definition to determine the target namespace. It should not be used for bean deserialisation.
	 * 
	 * @param inputStream
	 *            An <code>InputStream</code> containing serialized XML
	 * 
	 * @return A <code>Document</code> instance.
	 */
	public static Document deserialiseXML(InputStream inputStream)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(inputStream);
		}
		catch (IOException e)
		{
			throw new EQRuntimeException("Could not deserialise", e);
		}
		catch (ParserConfigurationException e)
		{
			throw new EQRuntimeException("Could not deserialise", e);
		}
		catch (SAXException e)
		{
			throw new EQRuntimeException("Could not deserialise", e);
		}
	}

	/**
	 * Serialise function
	 * 
	 * @param function
	 *            - the function to be converted to xml
	 * 
	 * @return the equivalent XML
	 */
	public String serialiseFunction(Function function) throws EQException
	{
		String xml = EqBeanFactory.getEqBeanFactory().serialiseBeanAsXML(function);
		return xml;
	}

	/**
	 * Process to deserialise a function
	 * 
	 * @param xml
	 *            - the xml to be converted to function
	 * 
	 * @return the equivalent Function
	 */
	public Function deserialiseFunction(String xml) throws EQException
	{
		Function function = (Function) EqBeanFactory.getEqBeanFactory().deserialiseXMLAsBean(xml, Function.class);
		return function;
	}

	/**
	 * Serialise layout
	 * 
	 * @param layout
	 *            - the layout to be converted to xml
	 * 
	 * @return the equivalent XML
	 */
	public String serialiseLayout(Layout layout) throws EQException
	{
		String xml = EqBeanFactory.getEqBeanFactory().serialiseBeanAsXML(layout);
		return xml;
	}

	/**
	 * Process to deserialise a layout
	 * 
	 * @param xml
	 *            - the xml to be converted to layout
	 * 
	 * @return the equivalent Layout
	 */
	public Layout deserialiseLayout(String xml) throws EQException
	{
		Layout layout = (Layout) EqBeanFactory.getEqBeanFactory().deserialiseXMLAsBean(xml, Layout.class);
		return layout;
	}

	/**
	 * Duplicate a function via serialisation
	 * 
	 * @param function
	 *            - the function to be duplicated
	 * 
	 * @return a new function instance
	 * 
	 * @throws EQException
	 */
	public Function duplicateFunction(Function function) throws EQException
	{
		String xml = serialiseFunction(function);
		Function reborn = deserialiseFunction(xml);
		return reborn;
	}

	/**
	 * Duplicate a Layout via serialisation
	 * 
	 * @param Layout
	 *            - the Layout to be duplicated
	 * 
	 * @return a new Layout instance
	 * 
	 * @throws EQException
	 */
	public Layout duplicateLayout(Layout layout) throws EQException
	{
		String xml = serialiseLayout(layout);
		Layout reborn = deserialiseLayout(xml);
		return reborn;
	}

	/**
	 * Serialise layout
	 * 
	 * @param linkService
	 *            - the link service to be converted to xml
	 * 
	 * @return the equivalent XML
	 */
	public String serialiseLinkService(LinkService linkService) throws EQException
	{
		String xml = EqBeanFactory.getEqBeanFactory().serialiseBeanAsXML(linkService);
		return xml;
	}

	/**
	 * Process to deserialise a link service
	 * 
	 * @param xml
	 *            - the xml to be converted to link service
	 * 
	 * @return the equivalent Link Service
	 */
	public LinkService deserialiseLinkService(String xml) throws EQException
	{
		LinkService linkService = (LinkService) EqBeanFactory.getEqBeanFactory().deserialiseXMLAsBean(xml, LinkService.class);
		return linkService;
	}

	/**
	 * Read the link service definition from the database with cache checking
	 * 
	 * @param session
	 *            - the Equation unit
	 * @param optionId
	 *            - the option Id
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * 
	 * @return the link service
	 * 
	 * @throws EQException
	 */
	public ServiceLinkage getLinkService(EquationStandardSession session, String optionId, boolean forceCheck) throws EQException
	{
		String key = session.getUnitId() + optionId;
		Statement statement = null;
		ResultSet resultSet = null;

		// validate whether this is non-linked service first
		if (isNonLinkedService(session.getUnitId(), optionId))
		{
			return null;
		}

		try
		{
			// Determine whether to reload from database or not
			boolean reload = false;

			// Get the timestamp from the cache (or zero it if null)
			String timestamp = linkServiceTimestampCache.get(key);

			// Check for latest record?
			if (forceCheck)
			{
				reload = true; // just always reload since this happen only during Dev unit
				timestamp = "0";
			}

			// Load only if has not been loaded
			else
			{
				reload = (timestamp == null);
				if (reload)
				{
					timestamp = "0";
				}
			}

			// Reload from file?
			if (reload)
			{
				GAXRecordDataModel gaxRecord = new GAXRecordDataModel(GAXRecordDataModel.LINKAGE_CODE, optionId);
				IGAXRecordDao dao = daoFactory.getGAXDao(session, gaxRecord);
				gaxRecord = dao.findWithLaterTimestamp(GAXRecordDataModel.LINKAGE_CODE, optionId, timestamp);

				LinkService linkService = null;
				if (gaxRecord != null)
				{
					session.getUnit().getRecords().reloadOption(optionId);
					linkService = deserialiseLinkService(gaxRecord.getLayout());
				}

				// record not found?
				if (linkService == null)
				{
					return null;
				}

				// try to link the functions
				ServiceLinkage serviceLinkage = new ServiceLinkage(linkService);
				Function linkedFunction = serviceLinkage.rtvLinkedFunction(session, forceCheck);
				if (linkedFunction == null)
				{
					throw new EQException(serviceLinkage.getMessageContainer().getFirstErrorMessage().getText());
				}
				Layout linkedLayout = serviceLinkage.rtvLinkedLayout(session, forceCheck);
				if (linkedLayout == null)
				{
					throw new EQException(serviceLinkage.getMessageContainer().getFirstErrorMessage().getText());
				}

				// verify with GZ
				FunctionJournalComparator comparator = new FunctionJournalComparator();
				ElementDifference ed = comparator.compare(session, optionId, linkedFunction);
				if (ed.isDiffer())
				{
					LOG.error(LanguageResources.getFormattedString("Language.LinkageServiceMustBeRedeployedDueToJournal", optionId)
									+ "\n" + ed.toString());
					throw new EQException(LanguageResources.getFormattedString(
									"Language.LinkageServiceMustBeRedeployedDueToJournal", optionId));
				}

				// add to the cache
				linkServiceTimestampCache.put(key, gaxRecord.getTimestamp());
				linkServiceCache.put(key, serviceLinkage);
			}
		}
		catch (Exception e)
		{
			LOG.error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		finally
		{
			SQLToolbox.close(resultSet);
			SQLToolbox.close(statement);
		}
		return linkServiceCache.get(key);
	}

	/**
	 * Clear cache
	 */
	public static void clear()
	{
		layoutCache.clear();
		layoutTimestampCache.clear();
		pvCache.clear();
		pvTimestampCache.clear();
		functionCache.clear();
		functionTimestampCache.clear();
		translationCache.clear();
		translationTimestampCache.clear();
		linkServiceTimestampCache.clear();
		linkServiceCache.clear();
	}

	/**
	 * Remove the link service cache when the underlying service has been updated
	 * 
	 * @param optionId
	 *            - the function id
	 */
	private void updateLinkServiceCache(String optionId)
	{
		Iterator<Map.Entry<String, ServiceLinkage>> serviceLinkageIterator = linkServiceCache.entrySet().iterator();
		while (serviceLinkageIterator.hasNext())
		{
			Map.Entry<String, ServiceLinkage> serviceLinkageEntry = serviceLinkageIterator.next();
			ServiceLinkage serviceLinkage = serviceLinkageEntry.getValue();

			if (serviceLinkage.isUseFunctionOrLayout(optionId))
			{
				serviceLinkageIterator.remove();

				// also remove from the timestamp cache
				linkServiceTimestampCache.remove(serviceLinkageEntry.getKey());
			}
		}
	}

	/**
	 * Remove the layout cache when the underlying service has been updated
	 * 
	 * @param optionId
	 *            - the function id
	 */
	private void updateLayoutCache(String optionId)
	{
		Iterator<Map.Entry<String, Layout>> layoutIterator = layoutCache.entrySet().iterator();
		while (layoutIterator.hasNext())
		{
			Map.Entry<String, Layout> layoutEntry = layoutIterator.next();
			Layout layout = layoutEntry.getValue();

			if (layout.getServiceId().trim().equals(optionId))
			{
				layoutIterator.remove();

				// also remove from the timestamp cache
				layoutTimestampCache.remove(layoutEntry.getKey());
			}
		}
	}

	/**
	 * Determine if the option is known to be a non-linked service
	 * 
	 * @param optionId
	 *            - the option id
	 * 
	 * @return true if option is not a linked service
	 */
	private boolean isNonLinkedService(String unit, String optionId)
	{
		String key = unit + optionId;

		// in the list of layout cache?
		if (layoutCache.containsKey(key))
		{
			return true;
		}

		// in the list of function cache?
		if (functionCache.containsKey(key))
		{
			return true;
		}

		return false;
	}

	/**
	 * Return the function definition from the cache
	 * 
	 * @param unit
	 *            - the unit id
	 * @param optionId
	 *            - the option id
	 * 
	 * @return the function definition from the cache
	 */
	public Function getFunctionFromCache(String unit, String optionId)
	{
		String key = unit + optionId;
		return functionCache.get(key);
	}

	/**
	 * Read the layout definition from the database with cache checking and validation against the function
	 * 
	 * @param session
	 *            - the EquationStandardSession
	 * @param optionId
	 *            - the option Id
	 * @param forceCheck
	 *            - true if it should always try to load the latest from database
	 * @param checkFunction
	 *            - true if the layout needs to be verified against the function
	 * 
	 * @return the deserialised Layout instance
	 * 
	 * @throws EQException
	 */
	public Layout getLayout(EquationStandardSession session, String optionId, boolean forceCheck, boolean checkFunction)
					throws EQException
	{
		String unitId = session.getUnitId();
		String layoutUnitId = unitId + optionId;

		// retrieve the layout
		String timeStampLayout = layoutTimestampCache.get(layoutUnitId);
		Layout layout = getLayout(session, optionId, forceCheck);
		String newTimeStampLayout = layoutTimestampCache.get(layoutUnitId);

		// supposedly, the layout will never be null, as when the option does not exist, system will immediately display an
		// error message. However, for linkage service that has been undeployed, then it is possible to get to this point
		if (layout == null && linkServiceCache.get(layoutUnitId) != null)
		{
			throw new EQException(LanguageResources.getFormattedString("Language.LinkageServiceUndeployed", optionId));
		}

		// layout not found
		if (layout == null)
		{
			layoutTimestampCache.remove(layoutUnitId);
			layoutCache.remove(layoutUnitId);
			return null;
		}

		// if not to be verify with function, then return the layout
		if (!checkFunction)
		{
			return layout;
		}

		// retrieve the function
		String functionId = layout.getServiceId();
		String functionUnitId = unitId + functionId;
		String timeStampFunction = functionTimestampCache.get(functionUnitId);
		Function function = getFunction(session, functionId, forceCheck);
		String newTimeStampFunction = functionTimestampCache.get(functionUnitId);

		// when function has not changed and layout has not changed then no need to recheck
		if (timeStampLayout == null)
		{
			timeStampLayout = "0";
		}
		if (timeStampFunction == null)
		{
			timeStampFunction = "0";
		}

		// when the function bean has changed, then remove all layouts from the cache to force loading from the database the next
		// time it is loaded
		if (!timeStampFunction.equals(newTimeStampFunction))
		{
			updateLayoutCache(functionId);

			// but add this particular layout back to the cache
			layoutTimestampCache.put(layoutUnitId, newTimeStampLayout);
			layoutCache.put(layoutUnitId, layout);
		}

		// if any of the bean has not been changed, then do not update
		if (timeStampLayout.equals(newTimeStampLayout) && timeStampFunction.equals(newTimeStampFunction))
		{
			return layout;
		}

		// verify Layout and Function
		FunctionLayoutComparator comparator = new FunctionLayoutComparator();
		ElementDifference ed = comparator.compare(layout, function);
		if (ed.isDiffer())
		{
			layoutTimestampCache.remove(layoutUnitId);
			layoutCache.remove(layoutUnitId);
			LOG.error(LanguageResources.getFormattedString("Language.LayoutMustBeRedeployed", optionId) + "\n" + ed.toString());
			throw new EQException(LanguageResources.getFormattedString("Language.LayoutMustBeRedeployed", optionId));
		}

		return layout;
	}

	/**
	 * Update the XSD structure cache
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param function
	 *            - the Function bean
	 */
	private void updateXSDStructureCache(EquationStandardSession session, Function function) throws EQException
	{
		String unitId = session.getUnitId();
		String key = unitId + function.getId();

		// old xsd structure?
		if (!function.chkXSDGeneric())
		{
			xsdStructureLinks.remove(key);
			return;
		}

		// generate the XSD structure link
		XSDStructureLink xsdLink = new XSDStructureLink(function);
		xsdStructureLinks.put(key, xsdLink);

		// retrieve the Function containing the complex type definition
		Function functionComplexType = getFunctionFromCache(session.getUnitId(), EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID);
		if (functionComplexType == null)
		{
			functionComplexType = getFunction(session, EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID, false);
		}
		XSDStructureLink xsdLinkComplexType = getXSDStructureLink(unitId, EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID);

		// update the parent structure
		XSDStructureHelper.setupXSDInputField(function);
		XSDStructureHelper.setupXSDParent(function);
		XSDStructureHelper.setupXSDWebServiceName(function, functionComplexType, xsdLinkComplexType);
	}

	/**
	 * Return the XSD Structure link
	 * 
	 * @param id
	 *            - the function id
	 * 
	 * @return the XSD Structure link
	 */
	public XSDStructureLink getXSDStructureLink(String unitId, String id)
	{
		String key = unitId + id;
		return xsdStructureLinks.get(key);
	}

	/**
	 * Return the CTS bean
	 * 
	 * @param unitId
	 *            - the unit id
	 * 
	 * @return the CTS bean
	 */
	public Function getCtsBean(String unitId)
	{
		return getFunctionFromCache(unitId, EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID);
	}

}
