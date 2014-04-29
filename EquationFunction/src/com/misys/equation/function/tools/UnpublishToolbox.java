/*
 * 
 */
package com.misys.equation.function.tools;

import java.sql.SQLException;
import java.util.List;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.CommandCall;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IAAIRecordDao;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.IGDRecordDao;
import com.misys.equation.common.dao.IOHRecordDao;
import com.misys.equation.common.dao.beans.AAIRecordDataModel;
import com.misys.equation.common.dao.beans.ACNRecordDataModel;
import com.misys.equation.common.dao.beans.ACORecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.dao.beans.GARecordDataModel;
import com.misys.equation.common.dao.beans.GAXRecordDataModel;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.dao.beans.GDRecordDataModel;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.dao.beans.OHRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.journal.JournalFile;

/**
 * This class performs the deletion of Service, Layouts, User Exits, Prompt Validate and Language in the Database.
 */
public class UnpublishToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UnpublishToolbox.java 14804 2012-11-05 11:57:35Z williae1 $";
	private static final EquationLogger LOG = new EquationLogger(UnpublishToolbox.class);
	private static DaoFactory daoFactory = new DaoFactory();
	private EquationStandardSession session;
	private EquationUnit unit;
	private CommandCall command;
	private AS400 as400;
	private String commandString = null;
	private String targetFilLibrary;
	private String targetInpLibrary;
	public static final String SERVICE = "S";
	public static final String PV = "P";
	public static final String ALL = "ALL RECORDS";
	private static final String MISYS_TEXT = "misysText.eqt";
	private static final String BANK_TEXT = "bankText.eqt";

	private String SERVICE_EXT;
	private String LAYOUT_EXT;
	/**
	 * Constructor
	 */
	@SuppressWarnings("unused")
	private UnpublishToolbox()
	{
	}
	/**
	 * Constructor
	 */
	public UnpublishToolbox(EquationStandardSession session, String serviceExt, String layoutExt) throws EQException
	{
		this.SERVICE_EXT = serviceExt;
		this.LAYOUT_EXT = layoutExt;
		this.session = session;
		unit = session.getUnit();
		as400 = session.getUnit().getEquationSystem().getAS400();
		command = new CommandCall(as400);
		targetFilLibrary = session.getUnit().getKFILLibrary();
		targetInpLibrary = session.getUnit().getKINPLibrary();

	}
	/**
	 * Protect against cloning
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();

	}
	/**
	 * Delete Language from the DB
	 * 
	 * @param id
	 * @throws SQLException
	 * @throws EQException
	 */
	public void deleteLanguage(String id) throws SQLException, EQException
	{
		deleteHBX(id);
		session.commitTransaction();

	}
	/**
	 * Delete Service from the DB
	 * 
	 * @param serviceId
	 * @throws Exception
	 */
	public void deleteService(String serviceId) throws Exception
	{
		// delete the GZX
		boolean journalExists = chkobj(targetInpLibrary, JournalFile.getJournalName(serviceId), "*FILE");
		if (journalExists)
		{
			this.endjrnpf(targetInpLibrary, JournalFile.getJournalName(serviceId));
			this.dltf(targetInpLibrary, JournalFile.getJournalName(serviceId), true);
		}
		deleteGD(serviceId);
		strjrnpf(targetFilLibrary, "OHPF");
		deleteOH(serviceId);
		session.commitTransaction();
		endjrnpf(targetFilLibrary, "OHPF");
		deleteGA(serviceId);
		deleteGAE(SERVICE, serviceId);
		deleteAAI(serviceId);
		deleteGAX(serviceId, GAXRecordDataModel.SERVICE_CODE);
		/*
		 * deleteGAZ(serviceId, GAZRecordDataModel.TYP_SERVICE, GAZRecordDataModel.TYP_FIELD, GAZRecordDataModel.TYP_VALUE,
		 * GAZRecordDataModel.TYP_UPDATE, GAZRecordDataModel.TYP_LOAD, GAZRecordDataModel.TYP_APIFIELDSET,
		 * GAZRecordDataModel.TYP_PVFIELDSET, GAZRecordDataModel.TYP_SERVICE_SRC);
		 */
		deleteGAZ(serviceId, ALL);
		GBRecordDataModel gbModel = new GBRecordDataModel();
		gbModel.setLibrary(targetFilLibrary);
		/*
		 * List<AbsRecord> gbRecords = daoFactory.getGBDao(session, gbModel).getRecordBy("GBFID = '" + serviceId + "'"); for
		 * (AbsRecord record : gbRecords) { deleteLayout(((GBRecordDataModel) record).getOptionId()); }
		 */
		deleteLanguage(serviceId + "." + SERVICE_EXT);
		session.commitTransaction();
	}
	/**
	 * Delete Layout from the DB
	 * 
	 * @param layoutId
	 * @throws EQException
	 */
	public void deleteLayout(String layoutId) throws Exception
	{
		deleteLanguage(layoutId + "." + LAYOUT_EXT);
		deleteGB(layoutId);
		deleteGD(layoutId);
		deleteGAX(layoutId, GAXRecordDataModel.LAYOUT_CODE);
		deleteGAZ(layoutId, GAZRecordDataModel.TYP_LAYOUT, GAZRecordDataModel.TYP_ATTRIBUTES,
						GAZRecordDataModel.TYP_GROUP_ATTRIBUTES, GAZRecordDataModel.TYP_LAYOUT_SRC,
						GAZRecordDataModel.TYP_BUTTONLINK_ATTRIBUTES);
		session.commitTransaction();
	}
	/**
	 * Delete Prompt Validate from the DB
	 * 
	 * @param pvMetaDataId
	 * @throws EQException
	 */
	public synchronized void deletePvMetaData(String pvMetaDataId) throws EQException
	{
		deleteGAE(PV, pvMetaDataId);
		deleteGD(pvMetaDataId);
		deleteGAX(pvMetaDataId, GAXRecordDataModel.PV_CODE);
		deleteGAZ(pvMetaDataId, GAZRecordDataModel.TYP_PV, GAZRecordDataModel.TYP_PV_SRC);
		session.commitTransaction();
	}
	/**
	 * Delete service user exit from the DB
	 * 
	 * @param userExitId
	 * @throws EQException
	 */
	public synchronized void deleteServiceUserExit(String userExitId) throws EQException
	{
		deleteGAZ(userExitId, GAZRecordDataModel.TYP_SERVICE_USEREXIT, GAZRecordDataModel.TYP_SERVICE_USEREXIT_SRC);
		session.commitTransaction();
	}
	/**
	 * Delete UserExit from the DB
	 * 
	 * @param userExitId
	 * @throws EQException
	 */
	public synchronized void deleteUserExit(String userExitId) throws EQException
	{
		deleteJavaUserExitControl(userExitId);
		deleteJavaUserExitFilter(userExitId);
		deleteGAZ(userExitId, GAZRecordDataModel.TYP_RPGUSEREXIT, GAZRecordDataModel.TYP_RPGUSEREXIT_SRC);
		session.commitTransaction();
	}
	public void deleteMicroflow(String id) throws EQException
	{
		deleteGAZByField(id, "GAZCLN");
		session.commitTransaction();
	}
	/**
	 * AS400 Command for CHKOBJ
	 * 
	 * @param library
	 * @param file
	 * @param type
	 * @return true if the object exits
	 * @throws Exception
	 */
	private boolean chkobj(String library, String file, String type) throws Exception
	{
		commandString = "CHKOBJ OBJ(" + library.trim() + "/" + file.trim() + ") OBJTYPE(" + type.trim() + ")";
		return command.run(commandString);
	}
	/**
	 * AS400 Command
	 * 
	 * @param library
	 * @param file
	 * @param errorWhenNotFound
	 * @throws Exception
	 */
	private void dltf(String library, String file, boolean errorWhenNotFound) throws Exception
	{
		commandString = "DLTF FILE(" + library.trim() + "/" + file.trim() + ")";
		boolean result = command.run(commandString);
		if (result == false && errorWhenNotFound == true)
		{
			if (command.run(commandString) == false)
			{
				throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
			}
		}
	}
	/**
	 * Start of Journaling
	 * 
	 * @param library
	 * @param file
	 * @throws Exception
	 */
	private void strjrnpf(String library, String file) throws Exception
	{
		// Note that the standard non-default options when starting journaling are to journal both images (before and after)
		// and to omit file open/close journal entries
		commandString = "STRJRNPF FILE(" + library.trim() + "/" + file.trim() + ") JRN(" + unit.getNIJournalFullPath()
						+ ") IMAGES(*BOTH) OMTJRNE(*OPNCLO)";
		if (command.run(commandString) == false)
		{

			// If messages were produced from the command, print them
			AS400Message[] messagelist = command.getMessageList();

			for (AS400Message element : messagelist)
			// if the error is the file is already journaled, then ignore the error
			{
				if ((element.getID().indexOf("CPF7030")) == -1)
				{
					LOG.error(element.getID() + "-" + element.getText());
					throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
				}
			}
		}
	}
	/**
	 * End of Journaling
	 * 
	 * @param library
	 * @param file
	 * @throws Exception
	 */
	private void endjrnpf(String library, String file) throws Exception
	{
		commandString = "ENDJRNPF FILE(" + library.trim() + "/" + file.trim() + ") JRN(" + unit.getNIJournalFullPath() + ")";
		if (command.run(commandString) == false)
		{// If messages were produced from the command, print them
			AS400Message[] messagelist = command.getMessageList();

			for (AS400Message element : messagelist)
			// if the error is the file is not already journaled, then ignore the error
			{
				if ((element.getID().indexOf("CPF7032")) == -1)
				{
					LOG.error(element.getID() + "-" + element.getText());
					throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
				}
			}
		}
	}
	/**
	 * Delete the record from the GAPF
	 * 
	 * @param id
	 */
	protected void deleteGA(String id) throws SQLException
	{
		GARecordDataModel model = new GARecordDataModel();
		model.setLibrary(targetFilLibrary);
		model.setOptionId(id);
		new DaoFactory().getGADao(session, model).deleteRecordsBy("GAFID = '" + id + "'");
	}
	/**
	 * Delete the record from the GAEPF
	 * 
	 * @param id
	 */
	protected void deleteGAE(String type, String id)
	{
		GAERecordDataModel gaeRecordDataModel = new GAERecordDataModel(id);
		gaeRecordDataModel.setLibrary(targetFilLibrary);
		IGAERecordDao gaeRecordDao = daoFactory.getGAEDao(session, gaeRecordDataModel);
		if (type.equals(SERVICE))
		{
			gaeRecordDataModel.setFunctionId(id);
			gaeRecordDao.deleteRecord();
		}
		else if (type.equals(PV))
		{
			gaeRecordDataModel.setApiId(id);
			gaeRecordDao.deleteRecord();
		}
	}
	/**
	 * Delete the record from the GDPF
	 * 
	 * @param id
	 */
	protected void deleteGD(String id)
	{
		GDRecordDataModel gdModel = new GDRecordDataModel(this.session.getEquationUserId(), id);
		gdModel.setLibrary(session.getUnit().getKFILLibrary());
		IGDRecordDao gDRecordDao = new DaoFactory().getGDDao(session, gdModel);
		gDRecordDao.deleteRecord();
	}
	/**
	 * Delete the record from the HBXPF
	 * 
	 * @param id
	 */
	public void deleteHBX(String id) throws SQLException, EQException
	{
		if (id.equals(MISYS_TEXT) || id.equals(BANK_TEXT))
		{
			LOG.info("SKIPPING: " + MISYS_TEXT + ", " + BANK_TEXT);
			return;
		}
		HBXRecordDataModel model = new HBXRecordDataModel();
		model.setLibrary(session.getUnit().getKFILLibrary());
		String where = "HBXOWN = '" + id + "'";
		new DaoFactory().getHBXDao(session, model).deleteRecordsBy(where);
	}
	/**
	 * Delete the record from the OHPF
	 * 
	 * @param id
	 */
	protected void deleteOH(String id)
	{
		OHRecordDataModel oHRecordDataModel = new OHRecordDataModel(JournalFile.getJournalName(id), "INP", "PF");
		oHRecordDataModel.setLibrary(targetFilLibrary);
		IOHRecordDao ohRecordDao = daoFactory.getOHDao(session, oHRecordDataModel);
		ohRecordDao.deleteRecord();
	}
	/**
	 * Delete the record from the AAIPF
	 * 
	 * @param id
	 */
	protected void deleteAAI(String id)
	{
		AAIRecordDataModel model = new AAIRecordDataModel(id);
		model.setLibrary(targetFilLibrary);
		IAAIRecordDao record = daoFactory.getAAIDao(session, model);
		record.deleteRecord();
	}
	/**
	 * params is a vararg:(Service=S; Layout=L; Pv=P;) ex: deleteGAX("YAH","L")
	 * 
	 * @param id
	 * @param params
	 */
	protected void deleteGAX(String id, String... params)
	{
		GAXRecordDataModel model = new GAXRecordDataModel();
		model.setLibrary(targetFilLibrary);
		model.setKey(id);
		for (String type : params)
		{
			model.setCode(type);
			daoFactory.getGAXDao(session, model).deleteRecord();

		}
	}
	/**
	 * This method is specifically for microlow (.bfg file)
	 * 
	 * @param id
	 */
	protected void deleteGAZByField(String id, String field)
	{
		GAZRecordDataModel model = new GAZRecordDataModel();
		model.setLibrary(targetFilLibrary);
		daoFactory.getGAZDao(session, model).deleteRecordsBy(field + " like '%" + id + "%'");
	}
	/**
	 * params is a vararg:(Service=' ',1,2,3,4,5,6; Layout=L,A,G) ex: deleteGAZ("YAH","1","2","3","4")
	 * 
	 * @param id
	 * @param params
	 * 
	 */
	protected void deleteGAZ(String id, String... params)
	{
		GAZRecordDataModel model = new GAZRecordDataModel();
		model.setLibrary(targetFilLibrary);
		String query = generateQueryString("GAZTYP", " OR ", params);
		if (params.length == 0)
		{
			daoFactory.getGAZDao(session, model).deleteRecordsBy("GAZOID='" + id + "'");

		}
		else
		{
			if (params[0].equals(ALL))
			{
				daoFactory.getGAZDao(session, model).deleteRecordsBy("GAZOID = '" + id + "'");
				return;
			}
			if (params[0].equals(GAZRecordDataModel.TYP_PV_SRC) || params[0].equals(GAZRecordDataModel.TYP_RPGUSEREXIT)
							|| params[0].equals(GAZRecordDataModel.TYP_SERVICE_USEREXIT))
			{
				daoFactory.getGAZDao(session, model).deleteRecordsBy("GAZFLD = '" + id + "' AND (" + query + ")");
			}
			else
			{
				daoFactory.getGAZDao(session, model).deleteRecordsBy("GAZOID = '" + id + "' AND (" + query + ")");
			}
		}
	}
	/**
	 * Delete the record from the GBPF
	 * 
	 * @param id
	 */
	protected void deleteGB(String id)
	{
		GBRecordDataModel model = new GBRecordDataModel();
		model.setLibrary(targetFilLibrary);
		model.setOptionId(id);
		daoFactory.getGBDao(session, model).deleteRecordsBy("GBOID = '" + id + "'");
	}
	/**
	 * Get GBRecords by Function ID
	 * 
	 * @param serviceId
	 * @return list of GB records for an service
	 */
	public List<GBRecordDataModel> getGBByFID(String serviceId)
	{
		GBRecordDataModel model = new GBRecordDataModel();
		model.setLibrary(targetFilLibrary);
		model.setOptionId(serviceId);
		List<? extends AbsRecord> list = daoFactory.getGBDao(session, model).getRecordBy("GBFID = '" + serviceId + "'");
		return (List<GBRecordDataModel>) list;
	}
	/**
	 * Delete the control record from the ACNPF
	 * 
	 * @param id
	 */
	protected void deleteJavaUserExitControl(String id)
	{
		ACNRecordDataModel model = new ACNRecordDataModel();
		model.setProgram(id);
		model.setLibrary(targetFilLibrary);
		daoFactory.getACNDao(session, model).deleteRecord();
	}
	/**
	 * Delete the filter record from the ACOPF
	 * 
	 * @param id
	 */
	protected void deleteJavaUserExitFilter(String id)
	{
		ACORecordDataModel model = new ACORecordDataModel();
		model.setUserExit(id);
		model.setLibrary(targetFilLibrary);
		daoFactory.getACODao(session, model).deleteRecord();
	}
	private String generateQueryString(String field, String logic, String... str)
	{
		String query = "";
		for (int i = 0; i < str.length; i++)
		{
			query += field + "='" + str[i] + "' ";
			if ((i + 1) != str.length)
			{
				query += logic;
			}
		}
		return query;
	}

	/**
	 * Delete service linkage from DB
	 * 
	 * @param linkageId
	 *            - the linkage id
	 * @throws Exception
	 */
	public void deleteLinkage(String linkageId) throws Exception
	{
		// delete the journal file
		String journalFile = JournalFile.getJournalName(linkageId);
		boolean journalExists = chkobj(targetInpLibrary, journalFile, "*FILE");
		if (journalExists)
		{
			this.endjrnpf(targetInpLibrary, journalFile);
			this.dltf(targetInpLibrary, journalFile, true);
		}

		// delete OH
		strjrnpf(targetFilLibrary, "OHPF");
		deleteOH(linkageId);
		session.commitTransaction();
		endjrnpf(targetFilLibrary, "OHPF");

		// delete GA/GB/GAX/GD/AAI
		deleteGA(linkageId);
		deleteGAX(linkageId, GAXRecordDataModel.LINKAGE_CODE);
		deleteGB(linkageId);
		deleteGD(linkageId);
		deleteAAI(linkageId);

		// commit changes
		session.commitTransaction();
	}

}
