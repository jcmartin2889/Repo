package com.misys.equation.common.dao;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationSystem;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.ApplicationContextManager;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.dataaccess.connectionpooling.ConnectionAccess;
import com.misys.equation.dataaccess.connectionpooling.EQDataAccessException;

/**
 * This class is used as a Factory. It will be used to create Daos. <br>
 * <code>DaoFactory</code> will hide the bean factory implementation and I will works as bridge between the application code and the
 * daos.
 * 
 * @author deroset
 */
public class DaoFactory
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DaoFactory.java 17760 2014-01-10 15:49:34Z lima12 $";
	private final ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();

	public static final String aAIRecordDaoName = "aAIRecordDao";
	public static final String gAERecordDaoName = "gAERecordDao";
	public static final String qZRecordDaoName = "qZRecordDao";
	public static final String oHRecordDaoName = "oHRecordDao";
	public static final String gARecordDaoName = "gARecordDao";
	public static final String gDRecordDaoName = "gDRecordDao";
	public static final String wARecordDaoName = "wARecordDao";
	public static final String xVRecordDaoName = "xVRecordDao";
	public static final String gAXRecordDaoName = "gAXRecordDao";
	public static final String gAZRecordDaoName = "gAZRecordDao";
	public static final String aCMRecordDaoName = "aCMRecordDao";
	public static final String aCNRecordDaoName = "aCNRecordDao";
	public static final String aCORecordDaoName = "aCORecordDao";
	public static final String sessionRecordDaoName = "sessionRecordDao";
	public static final String gBRecordDaoName = "gBRecordDao";
	public static final String gCRecordDaoName = "gCRecordDao";
	public static final String hPRecordDaoName = "hPRecordDao";
	public static final String oC2RecordDaoName = "oC2RecordDao";
	public static final String wERecordDaoName = "wERecordDao";
	public static final String oCRecordDaoName = "oCRecordDao";
	public static final String aceRecordDaoName = "aCERecordDao";
	public static final String acHRecordDaoName = "aCHRecordDao";
	public static final String hbRecordDaoName = "hBRecordDao";
	public static final String hbxRecordDaoName = "hBXRecordDao";
	public static final String weyRecordDaoName = "wEYRecordDao";
	public static final String weyMCRecordDaoName = "wEYMCRecordDao";
	public static final String wfRecordDaoName = "wfRecordDao";
	public static final String jTRecordDaoName = "jTRecordDao";
	public static final String iNORHRecordDaoName = "iNORHRecordDao";
	public static final String iNORDRecordDaoName = "iNORDRecordDao";
	public static final String cHRecordDaoName = "cHRecordDao";
	public static final String hARecordDaoName = "hARecordDao";
	public static final String c8RecordDaoName = "c8RecordDao";
	public static final String c1RecordDaoName = "c1RecordDao";
	public static final String QD1_RECORD_DAO_NAME = "qD1RecordDao";
	public static final String SC_RECORD_DAO_NAME = "sCRecordDao";
	public static final String CA_RECORD_DAO_NAME = "cARecordDao";
	public static final String OS_RECORD_DAO_NAME = "oSRecordDao";
	public static final String GF_RECORD_DAO_NAME = "gFRecordDao";
	public static final String KAP_RECORD_DAO_NAME = "kAPRecordDao";
	public static final String GLU_RECORD_DAO_NAME = "gLURecordDao";
	public static final String GPM_RECORD_DAO_NAME = "gPMRecordDao";
	public static final String GPX1_RECORD_DAO_NAME = "gPX1RecordDao";
	public static final String GPX2_RECORD_DAO_NAME = "gPX2RecordDao";
	public static final String GPX3_RECORD_DAO_NAME = "gPX3RecordDao";
	public static final String GPX4_RECORD_DAO_NAME = "gPX4RecordDao";
	public static final String GPX1_RECORD_DAO_NAME_IMP = "gPX1RecordDaoImp";
	public static final String GAU_RECORD_DAO_NAME = "gAURecordDao";
	public static final String GAV_RECORD_DAO_NAME = "gAVRecordDao";
	public static final String GAF_RECORD_DAO_NAME = "gAFRecordDao";
	public static final String GAA_RECORD_DAO_NAME = "gAARecordDao";
	public static final String GAL_RECORD_DAO_NAME = "gALRecordDao";
	public static final String CLH_RECORD_DAO_NAME = "clhRecordDao";
	public static final String CLD_RECORD_DAO_NAME = "cldRecordDao";
	public static final String CLC_RECORD_DAO_NAME = "clcRecordDao";
	public static final String DHO_RECORD_DAO_NAME = "dhoRecordDao";
	public static final String NE1_RECORD_DAO_NAME = "ne1RecordDao";
	public static final String GCE_RECORD_DAO_NAME = "gpeServiceRecordDao";
	public static final String FPG_RECORD_DAO_NAME = "fpgRecordDao";
	public static final String NOSTRO_POSITIONS_DAO = "nostroPositionsDao";
	public static final String EQS_RECORD_DAO_NAME = "eqsRecordDao";

	public static final String GPA_RECORD_DAO_NAME = "gPARecordDao";
	public static final String GPE_RECORD_DAO_NAME = "gPERecordDao";
	public static final String GPF_RECORD_DAO_NAME = "gPFRecordDao";
	public static final String GPR_RECORD_DAO_NAME = "gPRRecordDao";
	public static final String GPU_RECORD_DAO_NAME = "gPURecordDao";
	public static final String GPV_RECORD_DAO_NAME = "gPVRecordDao";

	public static final String GFL_RECORD_DAO_NAME = "gflRecordDao";
	public static final String GFE_RECORD_DAO_NAME = "gfeRecordDao";
	public static final String GFG_RECORD_DAO_NAME = "gfgRecordDao";

	public static final String APJ_RECORD_DAO_NAME = "aPJRecordDao";
	public static final String APV_RECORD_DAO_NAME = "aPVRecordDao";

	public static final String HEADER_RECORD_DAO_NAME = "headerRecordDao";

	public static final String GPX_RECORD_DAO_NAME = "gPXRecordDao";
	public static final String GSP_RECORD_DAO_NAME = "gSPRecordDao";

	public static final String MPM_RECORD_DAO_NAME = "mPMRecordDao";
	public static final String GAW_RECORD_DAO_NAME = "gAWRecordDao";
	public static final String GY_RECORD_DAO_NAME = "gYRecordDao";
	public static final String EDM_RECORD_DAO_NAME = "eDMRecordDao";
	public static final String VP1_RECORD_DAO_NAME = "vP1RecordDao";
	public static final String LU1_RECORD_DAO_NAME = "lU1RecordDao";
	public static final String CMD_RECORD_DAO_NAME = "CMDRecordDao";

	public static final String SC1_RECORD_DAO_NAME = "sc1RecordDao";
	public static final String X01_RECORD_DAO_NAME = "x01RecordDao";
	public static final String GSR_RECORD_DAO_NAME = "gSRRecordDao";

	public static final String GMG_RECORD_DAO_NAME = "gmgRecordDao";
	public static final String GML_RECORD_DAO_NAME = "gmlRecordDao";
	public static final String GME_RECORD_DAO_NAME = "gmeRecordDao";

	public static final String WE2_RECORD_DAO_NAME = "we2RecordDao";
	public static final String WEC_RECORD_DAO_NAME = "wecRecordDao";
	public static final String WEH_RECORD_DAO_NAME = "wehRecordDao";
	public static final String GYW_RECORD_DAO_NAME = "gywRecordDao";
	public static final String BT_RECORD_DAO_NAME = "btRecordDao";
	public static final String BD_RECORD_DAO_NAME = "bdRecordDao";
	public static final String BF_RECORD_DAO_NAME = "bfRecordDao";

	public static final String ipvRecordDaoName = "ipvRecordDao";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(DaoFactory.class);

	/**
	 * this is the default constructor.
	 */
	public DaoFactory()
	{

	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IDH0RecordDao getDH0Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IDH0RecordDao dao = (IDH0RecordDao) applicationContextManager.getDao(DHO_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}
	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IACORecordDao getACODao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IACORecordDao dao = (IACORecordDao) applicationContextManager.getDao(aCORecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}
	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public INE1RecordDao getNE1Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		INE1RecordDao dao = (INE1RecordDao) applicationContextManager.getDao(NE1_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IACHRecordDao getACHDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IACHRecordDao dao = (IACHRecordDao) applicationContextManager.getDao(acHRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGAURecordDao getGAUDao(EquationStandardSession session, AbsRecord record)
	{
		return getGAUDao(session, record, false);
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @param unit
	 *            Set to true if the connection is unit db, false if global db.
	 * 
	 * @return new Dao instance
	 */
	public IGAURecordDao getGAUDao(EquationStandardSession session, AbsRecord record, boolean unit)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGAURecordDao dao;
		if (unit)
		{
			dao = (IGAURecordDao) applicationContextManager.getDao(GAU_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IGAURecordDao) applicationContextManager.getDao(GAU_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());
		}
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Global Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGAFRecordDao getGAFDao(EquationStandardSession session, AbsRecord record)
	{
		return getGAFDao(session, record, false);
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @param unit
	 *            Set to true if the connection is unit db, false if global db.
	 * 
	 * @return new Dao instance
	 */
	public IGAFRecordDao getGAFDao(EquationStandardSession session, AbsRecord record, boolean unit)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGAFRecordDao dao;
		if (unit)
		{
			dao = (IGAFRecordDao) applicationContextManager.getDao(GAF_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IGAFRecordDao) applicationContextManager.getDao(GAF_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());
		}
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IHeaderRecordDao getHeaderDao(EquationStandardSession session, AbsRecord record)
	{
		return getHeaderDao(session, record, false);
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @param unit
	 *            Set to true if the connection is unit db, false if global db.
	 * 
	 * @return new Dao instance
	 */
	public IHeaderRecordDao getHeaderDao(EquationStandardSession session, AbsRecord record, boolean unit)
	{
		IHeaderRecordDao dao;
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		if (unit)
		{
			dao = (IHeaderRecordDao) applicationContextManager.getDao(HEADER_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IHeaderRecordDao) applicationContextManager.getDao(HEADER_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());
		}

		dao.setRecord(record);

		return dao;
	}

	/**
	 * Retrieves the GAVPF DAO for the global layer.
	 * 
	 * @param session
	 * @param record
	 * @return
	 */
	public IGAVRecordDao getGAVDao(EquationStandardSession session, AbsRecord record)
	{
		return getGAVDao(session, record, false);
	}

	/**
	 * Retrieves the GAVPF DAO.
	 * 
	 * @param session
	 *            - session
	 * @param record
	 *            - GAVRecordDatamodel
	 * @param isUnit
	 *            - global or unit indicator
	 * @return - retrieves the unit DAO if isUnit is true, otherwise retrieves the Global layer DAO
	 */
	public IGAVRecordDao getGAVDao(EquationStandardSession session, AbsRecord record, boolean isUnit)
	{
		IGAVRecordDao dao;
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		if (isUnit)
		{
			dao = (IGAVRecordDao) applicationContextManager.getDao(GAV_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IGAVRecordDao) applicationContextManager.getDao(GAV_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());
		}
		dao.setRecord(record);
		return dao;
	}
	/**
	 * Retrieves the GAAPF DAO for the global layer.
	 * 
	 * @param session
	 * @param record
	 * @return
	 */
	public IGAARecordDao getGAADao(EquationStandardSession session, AbsRecord record)
	{
		return getGAADao(session, record, false);
	}

	/**
	 * Retrieves the GAAPF DAO.
	 * 
	 * @param session
	 *            - session
	 * @param record
	 *            - GAARecordDatamodel
	 * @param isUnit
	 *            - global or unit indicator
	 * @return - retrieves the unit DAO if isUnit is true, otherwise retrieves the Global layer DAO
	 */
	public IGAARecordDao getGAADao(EquationStandardSession session, AbsRecord record, boolean isUnit)
	{
		IGAARecordDao dao;
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		if (isUnit)
		{
			dao = (IGAARecordDao) applicationContextManager.getDao(GAA_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IGAARecordDao) applicationContextManager.getDao(GAA_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());

		}
		dao.setRecord(record);
		return dao;
	}

	/**
	 * Retrieves the GALPF DAO for the global layer.
	 * 
	 * @param session
	 * @param record
	 * @return
	 */
	public IGALRecordDao getGALDao(EquationStandardSession session, AbsRecord record)
	{
		return getGALDao(session, record, false);
	}

	/**
	 * Retrieves the GALPF DAO.
	 * 
	 * @param session
	 *            - session
	 * @param record
	 *            - GALRecordDatamodel
	 * @param isUnit
	 *            - global or unit indicator
	 * @return - retrieves the unit DAO if isUnit is true, otherwise retrieves the Global layer DAO
	 */
	public IGALRecordDao getGALDao(EquationStandardSession session, AbsRecord record, boolean isUnit)
	{
		IGALRecordDao dao;
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		if (isUnit)
		{
			dao = (IGALRecordDao) applicationContextManager.getDao(GAL_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IGALRecordDao) applicationContextManager.getDao(GAL_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());

		}
		dao.setRecord(record);
		return dao;
	}

	public IGPARecordDao getGPADao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPARecordDao dao = (IGPARecordDao) applicationContextManager.getDao(GPA_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGPERecordDao getGPEDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPERecordDao dao = (IGPERecordDao) applicationContextManager.getDao(GPE_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGPFRecordDao getGPFDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPFRecordDao dao = (IGPFRecordDao) applicationContextManager.getDao(GPF_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGPRRecordDao getGPRDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPRRecordDao dao = (IGPRRecordDao) applicationContextManager.getDao(GPR_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGPURecordDao getGPUDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPURecordDao dao = (IGPURecordDao) applicationContextManager.getDao(GPU_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGPVRecordDao getGPVDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPVRecordDao dao = (IGPVRecordDao) applicationContextManager.getDao(GPV_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGPX1RecordDao getGPX1Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPX1RecordDao dao = (IGPX1RecordDao) applicationContextManager.getDao(GPX1_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGPX2RecordDao getGPX2Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPX2RecordDao dao = (IGPX2RecordDao) applicationContextManager.getDao(GPX2_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGPX3RecordDao getGPX3Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPX3RecordDao dao = (IGPX3RecordDao) applicationContextManager.getDao(GPX3_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGPX4RecordDao getGPX4Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPX4RecordDao dao = (IGPX4RecordDao) applicationContextManager.getDao(GPX4_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGPX1RecordDaoImp getGPX1DaoIMP(AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();

		IGPX1RecordDaoImp dao = null;
		try
		{
			dao = (IGPX1RecordDaoImp) applicationContextManager.getDao(GPX1_RECORD_DAO_NAME_IMP, ConnectionAccess
							.getGlobalConnectionPool().getConnection());
		}
		catch (EQDataAccessException e)
		{
			LOG.error(e);
		}
		if (dao != null)
		{
			dao.setRecord(record);
		}
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGPX1RecordDaoImp getGPX1DaoIMP(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();

		IGPX1RecordDaoImp dao = (IGPX1RecordDaoImp) applicationContextManager.getDao(GPX1_RECORD_DAO_NAME_IMP, session
						.getConnectionWrapper().getGlobalConnectionDataSource());

		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGPMRecordDao getGPMDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPMRecordDao dao = (IGPMRecordDao) applicationContextManager.getDao(GPM_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGLURecordDao getGLUDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGLURecordDao dao = (IGLURecordDao) applicationContextManager.getDao(GLU_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());

		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IKAPRecordDao getKAPDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IKAPRecordDao dao = (IKAPRecordDao) applicationContextManager.getDao(KAP_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IACERecordDao getACEDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IACERecordDao dao = (IACERecordDao) applicationContextManager.getDao(aceRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGAERecordDao getGAEDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGAERecordDao dao = (IGAERecordDao) applicationContextManager.getDao(gAERecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IOCRecordDao getOCDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IOCRecordDao dao = (IOCRecordDao) applicationContextManager.getDao(oCRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IAAIRecordDao getAAIDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IAAIRecordDao dao = (IAAIRecordDao) applicationContextManager.getDao(aAIRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IWERecordDao getWEDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IWERecordDao dao = (IWERecordDao) applicationContextManager.getDao(wERecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IOC2RecordDao getOC2Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IOC2RecordDao dao = (IOC2RecordDao) applicationContextManager.getDao(oC2RecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IHPRecordDao getHPDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IHPRecordDao dao = (IHPRecordDao) applicationContextManager.getDao(hPRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGBRecordDao getGBDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGBRecordDao dao = (IGBRecordDao) applicationContextManager.getDao(gBRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public ISessionRecordDao getSessionDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ISessionRecordDao dao = (ISessionRecordDao) applicationContextManager.getDao(sessionRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IACMRecordDao getACMDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IACMRecordDao dao = (IACMRecordDao) applicationContextManager.getDao(aCMRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IACNRecordDao getACNDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IACNRecordDao dao = (IACNRecordDao) applicationContextManager.getDao(aCNRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGAZRecordDao getGAZDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGAZRecordDao dao = (IGAZRecordDao) applicationContextManager.getDao(gAZRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGAXRecordDao getGAXDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGAXRecordDao dao = (IGAXRecordDao) applicationContextManager.getDao(gAXRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IXVRecordDao getXVDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IXVRecordDao dao = (IXVRecordDao) applicationContextManager.getDao(xVRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IOHRecordDao getOHDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IOHRecordDao dao = (IOHRecordDao) applicationContextManager.getDao(oHRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IWARecordDao getWADao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IWARecordDao dao = (IWARecordDao) applicationContextManager.getDao(wARecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IQZRecordDao getQZDao(EquationStandardSession session, AbsRecord record)
	{
		IQZRecordDao dao = (IQZRecordDao) applicationContextManager.getDao(qZRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGARecordDao getGADao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGARecordDao dao = (IGARecordDao) applicationContextManager.getDao(gARecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGDRecordDao getGDDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGDRecordDao dao = (IGDRecordDao) applicationContextManager.getDao(gDRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param unit
	 *            This is the mnemonic of the unit from where the database file will be retrieved.
	 * @param record
	 *            This is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGDRecordDao getGDDao(EquationSystem system, String unitId, AbsRecord record) throws EQException
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGDRecordDao dao = (IGDRecordDao) applicationContextManager.getDao(gDRecordDaoName, system.getUnit(unitId)
						.getEquationSessionPool().getEquationStandardSession());

		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            This is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IQD1RecordDao getQD1Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IQD1RecordDao dao = (IQD1RecordDao) applicationContextManager.getDao(QD1_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param unit
	 *            This is the mnemonic of the unit from where the database file will be retrieved.
	 * @param record
	 *            This is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IQD1RecordDao getQD1Dao(EquationSystem system, String unitId, AbsRecord record) throws EQException
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IQD1RecordDao dao = (IQD1RecordDao) applicationContextManager.getDao(QD1_RECORD_DAO_NAME, system.getUnit(unitId)
						.getEquationSessionPool().getEquationStandardSession());

		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param unit
	 *            This is the mnemonic of the unit from where the database file will be retrieved.
	 * @param record
	 *            This is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IQZRecordDao getQZDao(EquationSystem system, String unitId, AbsRecord record) throws EQException
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IQZRecordDao dao = (IQZRecordDao) applicationContextManager.getDao(qZRecordDaoName, system.getUnit(unitId)
						.getEquationSessionPool().getEquationStandardSession());

		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IHBRecordDao getHBDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IHBRecordDao dao = (IHBRecordDao) applicationContextManager.getDao(hbRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IHBXRecordDao getHBXDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IHBXRecordDao dao = (IHBXRecordDao) applicationContextManager.getDao(hbxRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IWEYRecordDao getWEYDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IWEYRecordDao dao = null;

		if (session.getUnit().isWEYPFBdtaInstalled())
		{
			dao = (IWEYRecordDao) applicationContextManager.getDao(weyMCRecordDaoName, session);
		}
		else
		{
			dao = (IWEYRecordDao) applicationContextManager.getDao(weyRecordDaoName, session);
		}
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IJTRecordDao getJTDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IJTRecordDao dao = (IJTRecordDao) applicationContextManager.getDao(jTRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IC1RecordDao getC1Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IC1RecordDao dao = (IC1RecordDao) applicationContextManager.getDao(c1RecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IINORHRecordDao getINORHDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IINORHRecordDao dao = (IINORHRecordDao) applicationContextManager.getDao(iNORHRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IINORDRecordDao getINORDDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IINORDRecordDao dao = (IINORDRecordDao) applicationContextManager.getDao(iNORDRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}
	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public ICHRecordDao getCHDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ICHRecordDao dao = (ICHRecordDao) applicationContextManager.getDao(cHRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IHARecordDao getHADao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IHARecordDao dao = (IHARecordDao) applicationContextManager.getDao(hARecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IC8RecordDao getC8Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IC8RecordDao dao = (IC8RecordDao) applicationContextManager.getDao(c8RecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	public ISCRecordDao getSCRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ISCRecordDao dao = (ISCRecordDao) applicationContextManager.getDao(SC_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	public IOSRecordDao getOSRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IOSRecordDao dao = (IOSRecordDao) applicationContextManager.getDao(OS_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	public ICARecordDao getCARecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ICARecordDao dao = (ICARecordDao) applicationContextManager.getDao(CA_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	public IGFRecordDao getGFRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGFRecordDao dao = (IGFRecordDao) applicationContextManager.getDao(GF_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	public ICLHRecordDao getCLHRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ICLHRecordDao dao = (ICLHRecordDao) applicationContextManager.getDao(CLH_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public ICLDRecordDaoImp getCLDRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ICLDRecordDaoImp dao = (ICLDRecordDaoImp) applicationContextManager.getDao(CLD_RECORD_DAO_NAME, session
						.getConnectionWrapper().getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public ICLCRecordDao getCLCRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ICLCRecordDao dao = (ICLCRecordDao) applicationContextManager.getDao(CLC_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGPEServiceRecordDao getGCERecordDao(EquationStandardSession session)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPEServiceRecordDao dao = (IGPEServiceRecordDao) applicationContextManager.getDao(GCE_RECORD_DAO_NAME, session);
		return dao;
	}

	public IGFGRecordDao getGFGRecordDao(EquationStandardSession session)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGFGRecordDao dao = (IGFGRecordDao) applicationContextManager.getDao(GFG_RECORD_DAO_NAME, session);
		return dao;
	}

	public IFPGRecordDao getFPGRecordDao(EquationStandardSession session)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IFPGRecordDao dao = (IFPGRecordDao) applicationContextManager.getDao(FPG_RECORD_DAO_NAME, session);
		return dao;
	}

	public INostroPositionsDao getNostroPositionsDao(EquationStandardSession session)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		INostroPositionsDao dao = (INostroPositionsDao) applicationContextManager.getDao(NOSTRO_POSITIONS_DAO, session);
		return dao;
	}

	public IDao getRecordDao(EquationStandardSession session, String daoId)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IDao dao = applicationContextManager.getDao(daoId, session);
		return dao;
	}

	public List<IDao> getDaosAcrossUnits(final List<EquationStandardSession> sessions, String id)
	{
		List<IDao> daos = new ArrayList<IDao>();
		for (EquationStandardSession session : sessions)
		{
			daos.add(getRecordDao(session, id));
		}
		return daos;
	}

	public IGPEServiceRecordDao[] getGCERecordDaoAcrossUnits(List<EquationStandardSession> sessions)
	{
		IGPEServiceRecordDao[] daos = new IGPEServiceRecordDao[sessions.size()];

		int i = 0;
		for (EquationStandardSession session : sessions)
		{
			daos[i] = getGCERecordDao(session);
			i++;
		}
		return daos;
	}

	public IEQSRecordDao getEQSRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IEQSRecordDao dao = (IEQSRecordDao) applicationContextManager.getDao(EQS_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getConnection());
		dao.setRecord(record);
		return dao;
	}

	public IAPJRecordDao getAPJDao(EquationStandardSession session, AbsRecord record)
	{
		return getAPJDao(session, record, false);
	}

	public IAPJRecordDao getAPJDao(EquationStandardSession session, AbsRecord record, boolean isUnit)
	{
		IAPJRecordDao dao;
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		if (isUnit)
		{
			dao = (IAPJRecordDao) applicationContextManager.getDao(APJ_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IAPJRecordDao) applicationContextManager.getDao(APJ_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());

		}
		dao.setRecord(record);
		return dao;
	}

	public IAPVRecordDao getAPVDao(EquationStandardSession session, AbsRecord record)
	{
		return getAPVDao(session, record, false);
	}

	public IAPVRecordDao getAPVDao(EquationStandardSession session, AbsRecord record, boolean isUnit)
	{
		IAPVRecordDao dao;
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		if (isUnit)
		{
			dao = (IAPVRecordDao) applicationContextManager.getDao(APV_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IAPVRecordDao) applicationContextManager.getDao(APV_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());
		}
		dao.setRecord(record);
		return dao;
	}

	public IGPXRecordDao getGPXRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGPXRecordDao dao = (IGPXRecordDao) applicationContextManager.getDao(GPX_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGSPRecordDao getGSPRecordDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGSPRecordDao dao = (IGSPRecordDao) applicationContextManager.getDao(GSP_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;
	}

	public IGCRecordDao getGCDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGCRecordDao dao = (IGCRecordDao) applicationContextManager.getDao(gCRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	public IMPMRecordDao getMPMRecordDao(EquationStandardSession session, AbsRecord record)
	{
		return getMPMRecordDao(session, record, false);
	}

	/**
	 * This method will return a global instance of the DAO
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @param isUnit
	 * 
	 * @return new Dao instance
	 */
	public IMPMRecordDao getMPMRecordDao(EquationStandardSession session, AbsRecord record, boolean isUnit)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IMPMRecordDao dao = null;
		if (isUnit)
		{
			dao = (IMPMRecordDao) applicationContextManager.getDao(MPM_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IMPMRecordDao) applicationContextManager.getDao(MPM_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());
		}

		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a global instance of the DAO
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGAWRecordDao getGAWDao(EquationStandardSession session, AbsRecord record)
	{
		return getGAWDao(session, record, false);
	}

	/**
	 * This method will return a new instance of the DAO
	 * 
	 * @param session
	 *            -This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @param isUnit
	 *            - unit DAO indicator
	 * @return - returns a unit DAO if indicator is 'TRUE' else return a global DAO
	 */
	public IGAWRecordDao getGAWDao(EquationStandardSession session, AbsRecord record, boolean isUnit)
	{
		IGAWRecordDao dao;
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		if (isUnit)
		{
			dao = (IGAWRecordDao) applicationContextManager.getDao(GAW_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getConnection());
		}
		else
		{
			dao = (IGAWRecordDao) applicationContextManager.getDao(GAW_RECORD_DAO_NAME, session.getConnectionWrapper()
							.getGlobalConnectionDataSource());

		}
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGYRecordDao getGYDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGYRecordDao dao = (IGYRecordDao) applicationContextManager.getDao(GY_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getConnection());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IEDMRecordDao getEDMDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IEDMRecordDao dao = (IEDMRecordDao) applicationContextManager.getDao(EDM_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IVP1RecordDao getVP1Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IVP1RecordDao dao = (IVP1RecordDao) applicationContextManager.getDao(VP1_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getConnection());
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public ILU1RecordDao getLU1Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ILU1RecordDao dao = (ILU1RecordDao) applicationContextManager.getDao(LU1_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the database connection.
	 * @param record
	 *            this is the data model to be set in the Dao.
	 * @return new Dao instance
	 */
	public ISC1RecordDao getSC1Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ISC1RecordDao dao = (ISC1RecordDao) applicationContextManager.getDao(SC1_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public ICMDRecordDao getCMDDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		ICMDRecordDao dao = (ICMDRecordDao) applicationContextManager.getDao(CMD_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IX01RecordDao getX01Dao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IX01RecordDao dao = (IX01RecordDao) applicationContextManager.getDao(X01_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * 
	 * @return new Dao instance
	 */
	public IGSRRecordDao getGSRDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGSRRecordDao dao = (IGSRRecordDao) applicationContextManager.getDao(GSR_RECORD_DAO_NAME, session.getConnectionWrapper()
						.getGlobalConnectionDataSource());
		dao.setRecord(record);
		return dao;

	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @return new Dao instance
	 */
	public IGMGRecordDao getGMGRecordDao(EquationStandardSession session)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGMGRecordDao dao = (IGMGRecordDao) applicationContextManager.getDao(GMG_RECORD_DAO_NAME, session);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @return new Dao instance
	 */
	public IGMLRecordDao getGMLRecordDao(EquationStandardSession session)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGMLRecordDao dao = (IGMLRecordDao) applicationContextManager.getDao(GML_RECORD_DAO_NAME, session);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @return new Dao instance
	 */
	public IGMERecordDao getGMERecordDao(EquationStandardSession session)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGMERecordDao dao = (IGMERecordDao) applicationContextManager.getDao(GME_RECORD_DAO_NAME, session);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @return new Dao instance
	 */
	public IWE2RecordDao getWE2Dao(EquationStandardSession session, AbsRecord absRecord)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IWE2RecordDao dao = (IWE2RecordDao) applicationContextManager.getDao(WE2_RECORD_DAO_NAME, session);
		dao.setRecord(absRecord);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @return new Dao instance
	 */
	public IWECRecordDao getWECDao(EquationStandardSession session, AbsRecord absRecord)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IWECRecordDao dao = (IWECRecordDao) applicationContextManager.getDao(WEC_RECORD_DAO_NAME, session);
		dao.setRecord(absRecord);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @return new Dao instance
	 */
	public IWEHRecordDao getWEHDao(EquationStandardSession session, AbsRecord absRecord)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IWEHRecordDao dao = (IWEHRecordDao) applicationContextManager.getDao(WEH_RECORD_DAO_NAME, session);
		dao.setRecord(absRecord);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IGYWRecordDao getGYWDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IGYWRecordDao dao = (IGYWRecordDao) applicationContextManager.getDao(GYW_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IBTRecordDao getBTDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IBTRecordDao dao = (IBTRecordDao) applicationContextManager.getDao(BT_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IBDRecordDao getBDDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IBDRecordDao dao = (IBDRecordDao) applicationContextManager.getDao(BD_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IBFRecordDao getBFDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IBFRecordDao dao = (IBFRecordDao) applicationContextManager.getDao(BF_RECORD_DAO_NAME, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new IWFRecordDao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new IWFRecordDao instance
	 */
	public IWFRecordDao getWFDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IWFRecordDao dao = (IWFRecordDao) applicationContextManager.getDao(wfRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

	/**
	 * This method will return a new Dao instance.
	 * 
	 * @param session
	 *            This is the current session which will provide the data-base connection.
	 * @param record
	 *            this is the data-model to be set in the Dao.
	 * @return new Dao instance
	 */
	public IIPVRecordDao getIPVDao(EquationStandardSession session, AbsRecord record)
	{
		ApplicationContextManager applicationContextManager = ApplicationContextManager.getInstance();
		IIPVRecordDao dao = (IIPVRecordDao) applicationContextManager.getDao(ipvRecordDaoName, session);
		dao.setRecord(record);
		return dao;
	}

}