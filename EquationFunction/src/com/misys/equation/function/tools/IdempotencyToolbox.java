package com.misys.equation.function.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import bf.com.misys.eqf.types.header.Orig;
import bf.com.misys.eqf.types.header.ServiceResponse;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;

import com.misys.bankfusion.subsystem.persistence.runtime.impl.BankFusionThreadLocal;
import com.misys.equation.bf.EquationServiceHandler;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGYRecordDao;
import com.misys.equation.common.dao.IIPVRecordDao;
import com.misys.equation.common.dao.beans.GYRecordDataModel;
import com.misys.equation.common.dao.beans.IPVRecordDataModel;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.runtime.ConversionRules;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.ScreenSet;

public class IdempotencyToolbox
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: IdempotencyToolbox.java 17765 2014-01-13 13:07:25Z lima12 $";
	// Log
	protected final static EquationLogger LOG = new EquationLogger(IdempotencyToolbox.class);

	// DAO factory
	private static DaoFactory daoFactory = new DaoFactory();

	// Function BF
	private static FunctionBankFusion functionBankFusion = new FunctionBankFusion();

	/**
	 * Retrieve the idempotency retention period
	 * 
	 * @param appId
	 *            - the application id
	 * 
	 * @return the idempotency retention period
	 */
	public static int getRetentionPeriod(String appId)
	{
		String retention = "";
		int days = -1;

		// retrieve the app specified retention period
		if (appId.trim().length() > 0)
		{
			retention = EquationCommonContext.getConfigProperty("eq.idempotency.retention" + "." + appId);
			if (retention.length() > 0)
			{
				days = Toolbox.parseInt(retention, -1);
			}
		}

		// retrieve the default retention period
		if (days < 0)
		{
			retention = EquationCommonContext.getConfigProperty("eq.idempotency.retention");
			if (retention.length() > 0)
			{
				days = Toolbox.parseInt(retention, -1);
			}
		}

		// retrieve the default retention period
		if (days < 0)
		{
			days = 0;
		}

		return days;
	}

	/**
	 * Return the idempotency reference from the service header
	 * 
	 * @param serviceRqHeader
	 *            - the service header
	 * 
	 * @return the idempotency reference from the service header
	 */
	public static Object[] getIdempotencyId(ServiceRqHeader serviceRqHeader)
	{
		// not specified
		if (serviceRqHeader == null || serviceRqHeader.getRqHeader() == null || serviceRqHeader.getRqHeader().getOrig() == null)
		{
			return null;
		}

		// extract Orig information
		Orig orig = serviceRqHeader.getRqHeader().getOrig();

		// no reference?
		String ref = orig.getOrigCtxtId();
		if (ref == null || ref.trim().length() == 0)
		{
			return null;
		}

		// extract information
		Object[] str = new Object[3];
		str[0] = orig.getOrigCtxtId();
		str[1] = orig.getAppId() == null ? "" : orig.getAppId();
		str[2] = orig.getOrigCtxtIdSeqNo() == null ? 0 : orig.getOrigCtxtIdSeqNo();

		return str;
	}

	/**
	 * Write details to idempotency file
	 * 
	 * @param session
	 *            - the session
	 * @param referenceId
	 *            - the reference id
	 * @param appId
	 *            - the app id
	 * @param sequence
	 *            - the sequence number
	 * @param expiryDate
	 *            - the expiry date
	 * @param gyRecord
	 *            - the GY record. Some details are copied from the GY
	 * @param responseData
	 *            - the data
	 */
	public static void writeIdempotencyDetails(EquationStandardSession session, String referenceId, String appId, int sequence,
					int processedDate, int expiryDate, String userId, GYRecordDataModel gyRecord, Object responseData)
					throws EQException
	{
		IPVRecordDataModel dataModel = new IPVRecordDataModel(referenceId, appId, sequence);
		dataModel.setCcLinkSeqNo(gyRecord.getCcLinkSeqNo());
		dataModel.setCcLinkTime(gyRecord.getCcLinkTime());
		dataModel.setCreateDate(gyRecord.getCreateDate());
		dataModel.setProcessedDate(processedDate);
		dataModel.setExpiryDate(expiryDate);
		dataModel.setJobNumber(gyRecord.getJobNumber());
		dataModel.setOptionId(gyRecord.getUserDefinedOptionBeingJournalled());
		dataModel.setResponse(convertDataToBytes(responseData));
		dataModel.setUserId(gyRecord.getUserId());

		// User id specified?
		if (userId != null)
		{
			dataModel.setUserId(userId);
		}

		// write
		writeIdempotencyDetails(session, dataModel);
	}

	/**
	 * Write details to idempotency file
	 * 
	 * @param session
	 *            - the session
	 * @param ipvDataModel
	 *            - the IPV data
	 */
	public static void writeIdempotencyDetails(EquationStandardSession session, IPVRecordDataModel ipvDataModel)
	{
		IIPVRecordDao dao = daoFactory.getIPVDao(session, ipvDataModel);
		dao.insertRecord();
	}

	/**
	 * Read the idempotency details of the given reference
	 * 
	 * @param session
	 *            - the session
	 * @param referenceId
	 *            - the reference id
	 * @param appId
	 *            - the application id
	 * @param sequence
	 *            - the sequence number
	 * 
	 * @return the idempotency details
	 */
	public static IPVRecordDataModel readIdempotencyDetails(EquationStandardSession session, String referenceId, String appId,
					int sequence)
	{
		IPVRecordDataModel dataModel = new IPVRecordDataModel(referenceId, appId, sequence);
		IIPVRecordDao dao = daoFactory.getIPVDao(session, dataModel);
		return dao.getIPVRecord();
	}

	/**
	 * Convert an object into bytes
	 * 
	 * @param object
	 *            - the objec to be converted
	 * 
	 * @return the equivalent bytes of the object
	 * 
	 * @throws EQException
	 */
	public static byte[] convertDataToBytes(Object object) throws EQException
	{
		ByteArrayOutputStream objectBytes = new ByteArrayOutputStream();
		ObjectOutputStream writer;
		try
		{
			writer = new ObjectOutputStream(objectBytes);
			writer.writeObject(object);
			writer.close();
			return objectBytes.toByteArray();
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new EQException("IdempotencyToolbox: convertDataToBytes() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
	}

	/**
	 * Retrieve the response
	 * 
	 * @param ipvRecord
	 *            - the ipvRecord
	 * 
	 * @return the IPV record
	 * 
	 * @throws EQException
	 */
	public static Object getData(IPVRecordDataModel ipvRecord) throws EQException
	{
		// no record?
		if (ipvRecord == null)
		{
			return null;
		}

		ByteArrayInputStream bytesin = new ByteArrayInputStream(ipvRecord.getResponse());
		try
		{
			EquationObjectInputStream reader = new EquationObjectInputStream(bytesin);
			Object objectData = reader.readObject();
			return objectData;
		}
		catch (Exception e)
		{
			LOG.error(e);
			throw new EQException("IdempotencyToolbox: getData() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
	}

	/**
	 * Update processing
	 * 
	 * @param session
	 *            - the session
	 * @param fhd
	 *            - the function handler data
	 * @param journalHeader
	 *            - the journal header
	 * 
	 * @return true if IPV record has been written to database
	 * 
	 * @throws EQException
	 */
	public static boolean updateProcessing(EquationStandardSession session, FunctionHandlerData fhd, JournalHeader journalHeader)
					throws EQException
	{
		Object[] reference = getIdempotencyId(fhd.getServiceRqHeader());
		if (reference != null)
		{
			// data
			ScreenSet screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();
			FunctionData functionData = screenSetMain.getFunctionData();
			Function function = screenSetMain.getFunction();
			ConversionRules conversionRule = FunctionRuntimeToolbox.getConversionRules(fhd.getServiceRqHeader(), fhd);

			// only for enhaced XSD
			if (!function.chkXSDGeneric())
			{
				LOG.warn("Idempotency is compatible with enhanced XSD service only");
				return false;
			}

			// Read GY record
			GYRecordDataModel gyRecord = new GYRecordDataModel();
			gyRecord.setWorkstationId(journalHeader.getWorkstationID());
			gyRecord.setDayInMonth(journalHeader.getJrnDay());
			gyRecord.setTimeHhmmss(journalHeader.getJrnTime());
			gyRecord.setSequenceNumber(journalHeader.getJrnSequence());
			gyRecord.setProgramRoot(journalHeader.getProgramRoot());
			gyRecord.setJournalTransType(journalHeader.getFunctionMode());
			IGYRecordDao gyDao = daoFactory.getGYDao(session, gyRecord);
			gyRecord = gyDao.getGYRecord();

			// Processed date
			int processedDate = session.getUnit().getProcessingDateCYYMMDD();

			// Calculate expiry date
			int expiryDays = getRetentionPeriod((String) reference[1]);
			Calendar cal = Toolbox.parseEqDate(String.valueOf(processedDate));
			cal.add(Calendar.DAY_OF_MONTH, expiryDays);
			int expiryDate = Toolbox.getDateDBFormat(cal);

			// Response
			Object response = null;
			try
			{
				FunctionBankFusion fb = new FunctionBankFusion();
				Object inputServiceData = screenSetMain.getFunctionAdaptor().getBFComplexTypeClass(session,
								screenSetMain.getOptionId());

				// temporarily set
				fhd.setJournalHeader(journalHeader);

				EquationServiceHandler serviceHandler = new EquationServiceHandler(1, session.getSessionIdentifier());
				serviceHandler.setData(functionData, function, inputServiceData, fhd.getServiceRqHeader().getRqHeader()
								.getMessageId());
				serviceHandler.setOutputServiceHeader(fhd, conversionRule);
				serviceHandler.setOutputServiceData(fhd.getServiceRqHeader(), fhd);
				response = serviceHandler.getOutputResponseData();
			}
			finally
			{
				// unset
				fhd.setJournalHeader(null);
			}

			// user id
			String userId = getCurrentUser(fhd.getServiceRqHeader().getRqHeader().getUserId(), session.getUserId());

			// write
			IdempotencyToolbox.writeIdempotencyDetails(session, (String) reference[0], (String) reference[1],
							(Integer) reference[2], processedDate, expiryDate, userId, gyRecord, response);

			return true;
		}

		return false;
	}

	/**
	 * Retrieve the idempotency details from the database
	 * 
	 * @param session
	 *            - the session
	 * @param serviceRqHeader
	 *            - the Service header
	 * 
	 * @return the idempotency details
	 * 
	 * @throws EQException
	 */
	public static ServiceResponse retrieveProcessing(EquationStandardSession session, ServiceRqHeader serviceRqHeader)
					throws EQException
	{
		// no idempotency id
		Object[] reference = getIdempotencyId(serviceRqHeader);
		if (reference == null)
		{
			return null;
		}

		IPVRecordDataModel ipvRecord = new IPVRecordDataModel((String) reference[0], (String) reference[1], (Integer) reference[2]);
		IIPVRecordDao ipvDao = daoFactory.getIPVDao(session, ipvRecord);
		ipvRecord = ipvDao.getIPVRecord();

		// no record found
		if (ipvRecord == null)
		{
			return null;
		}

		// return the response
		LOG.info("Idempotency details found for [" + reference[0] + "][ " + reference[1] + "][ " + reference[2] + "]");
		Object responseData = getData(ipvRecord);

		Object serviceData = functionBankFusion.getFieldObject(responseData, "get" + XSDToolbox.SERVICE_DATA_TAG);
		ServiceRsHeader serviceHeader = (ServiceRsHeader) functionBankFusion.getFieldObject(responseData, "get"
						+ XSDToolbox.SERVICE_HEADER_TAG);

		ServiceResponse serviceResponse = new ServiceResponse();
		serviceResponse.setData(serviceData);
		serviceResponse.setHeader(serviceHeader);
		return serviceResponse;
	}

	/**
	 * Return the user
	 * 
	 * @param userId1
	 *            - the user id from the Equation service Rq header
	 * @param userId2
	 *            - the user id from the session
	 * 
	 * @return the user
	 */
	public static String getCurrentUser(String userId1, String userId2)
	{
		// Priority is the real user from the Equation Rq header
		if (userId1 != null && userId1.trim().length() > 0)
		{
			return userId1;
		}

		// Next priority is from the BF user id
		if (EquationCommonContext.isBankFusionInstalled())
		{
			return BankFusionThreadLocal.getBankFusionEnvironment().getUserSession().getUserId();
		}

		// Next priority is the user from the session
		if (userId2 != null && userId2.trim().length() > 0)
		{
			return userId2;
		}

		// no user id
		return null;
	}

}
