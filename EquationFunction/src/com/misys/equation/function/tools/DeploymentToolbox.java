/*
 * 
 */
package com.misys.equation.function.tools;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.FileAttributes;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IAAIRecordDao;
import com.misys.equation.common.dao.ICHRecordDao;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.IGARecordDao;
import com.misys.equation.common.dao.IGAZRecordDao;
import com.misys.equation.common.dao.IGBRecordDao;
import com.misys.equation.common.dao.IGDRecordDao;
import com.misys.equation.common.dao.IHBXRecordDao;
import com.misys.equation.common.dao.IINORDRecordDao;
import com.misys.equation.common.dao.IINORHRecordDao;
import com.misys.equation.common.dao.IOHRecordDao;
import com.misys.equation.common.dao.beans.AAIRecordDataModel;
import com.misys.equation.common.dao.beans.ACNRecordDataModel;
import com.misys.equation.common.dao.beans.ACORecordDataModel;
import com.misys.equation.common.dao.beans.AbsRecord;
import com.misys.equation.common.dao.beans.CHRecordDataModel;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.dao.beans.GARecordDataModel;
import com.misys.equation.common.dao.beans.GAXRecordDataModel;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.dao.beans.GBRecordDataModel;
import com.misys.equation.common.dao.beans.GDRecordDataModel;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.dao.beans.INORDRecordDataModel;
import com.misys.equation.common.dao.beans.INORHRecordDataModel;
import com.misys.equation.common.dao.beans.OHRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.SQLToolbox;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.Language;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LinkService;
import com.misys.equation.function.beans.ServiceUserExit;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.beans.Translation;
import com.misys.equation.function.beans.UserExit;
import com.misys.equation.function.beans.ValidationUserExitFilter;
import com.misys.equation.function.journal.JournalFile;
import com.misys.equation.function.journal.JournalRecord;
import com.misys.equation.function.linkage.ServiceLinkage;
import com.misys.equation.function.runtime.FunctionCommonData;

/**
 * Note: DO NOT ADD TRY/CATCH to handle errors in this class. Errors need to percolate to the UI layer when there are problems. This
 * toolbox class also serves as a cache of function information.
 * 
 * Note: The Equation database is updated in this class using SQL. SQL depends on commitment control. Commitment control depends on
 * journaling. Equation files are journaled during the DAY phase but will probably not be journaled during other phases. End users
 * can configure journaling to be off/on during EOD. Production installation will be done during EOD phase C50.
 * 
 * Deployment/Installation can happen in 3 ways: Deploy to development unit, Deploy as a patch release where a new library is
 * created - installation is done via IMENU1/RPG, Install service bundle. Patch Release creation has been depreciated.
 * 
 * The current state of file journaling will be determined at the start of deployment. At the start if files are not journaled they
 * will be journaled. At the end (or if error) file journaling status will be set back to its original settings. See
 * ServiceExportWizard and ServiceBundleInstallerProcess for callers of DeploymentToolbox.
 * 
 */
public class DeploymentToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DeploymentToolbox.java 17359 2013-09-25 23:15:29Z williae1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(DeploymentToolbox.class);
	private static DaoFactory daoFactory = new DaoFactory();
	private static final String EQ_BUNDLE = "EQ_BUNDLE";
	public static final String BANK_BUNDLE_PREFIX = "UQ";

	private EquationStandardSession session;
	private EquationUnit unit;

	private CommandCall command;
	private AS400 as400;
	private String commandString = null;

	private boolean isUnitDeployment;
	private boolean isPatchLibraries;
	private boolean isPatchRelease;
	private boolean isEnhancementLibraries;
	private boolean isMisysBundle;
	private boolean isBundle;

	private String release;
	private String level;
	private String upgrade;
	private String releaseDescription;
	private boolean includeMenuOptions;
	private String order;

	private String targetLibLibrary;
	private String targetFilLibrary;
	private String targetInpLibrary;
	private String targetWrkLibrary;

	private boolean createdOHPFRecord;
	private boolean createdGAPFRecord;
	private boolean createdGBPFRecord;
	private boolean createdGAEPFRecord;
	private boolean createdGAXPFRecord;
	private boolean createdGAZPFRecord;
	private boolean createdAAIPFRecord;
	private boolean createdACNPFRecord;
	private boolean createdACOPFRecord;
	private boolean createdHBXPFRecord;

	// journaled flag recording original state of the file before deployment
	private boolean isOriginallyJournaledOHPF;
	private boolean isOriginallyJournaledGAPF;
	private boolean isOriginallyJournaledGBPF;
	private boolean isOriginallyJournaledGDPF;
	private boolean isOriginallyJournaledGAEPF;
	private boolean isOriginallyJournaledGAXPF;
	private boolean isOriginallyJournaledGAZPF;
	private boolean isOriginallyJournaledAAIPF;
	private boolean isOriginallyJournaledACNPF;
	private boolean isOriginallyJournaledACOPF;
	private boolean isOriginallyJournaledHBXPF;

	private boolean targetLibLibraryUsed;
	private boolean targetFilLibraryUsed;
	private boolean targetInpLibraryUsed;
	private boolean targetWrkLibraryUsed;

	private List<Object> updatedCollection;

	/**
	 * Constructor
	 */
	@SuppressWarnings("unused")
	private DeploymentToolbox()
	{
	}
	/**
	 * Constructor
	 */
	public DeploymentToolbox(EquationStandardSession session) throws Exception
	{
		// TODO have this constructor call the other constructor

		this.session = session;
		unit = session.getUnit();

		isUnitDeployment = true;
		isPatchLibraries = false;
		isPatchRelease = false;
		isEnhancementLibraries = false;
		isMisysBundle = false;

		this.release = null;
		this.level = null;
		this.upgrade = null;
		this.releaseDescription = null;
		includeMenuOptions = true;

		this.order = null;

		as400 = session.getUnit().getEquationSystem().getAS400();
		command = new CommandCall(as400);

		targetLibLibrary = session.getUnit().getKLIBLibrary();
		targetFilLibrary = session.getUnit().getKFILLibrary();
		targetInpLibrary = session.getUnit().getKINPLibrary();
		targetWrkLibrary = session.getUnit().getKWRKLibrary();
		isBundle = false;
		determineJournalingStatus();
	}
	/**
	 * Constructor
	 */
	public DeploymentToolbox(EquationStandardSession session, boolean isUnitDeployment, boolean isPatchLibraries,
					boolean isPatchRelease, boolean isEnhancementLibraries, String release, String level, String upgrade,
					String releaseDescription, boolean includeMenuOptions, boolean isMisysBundle) throws Exception
	{
		this(session, isUnitDeployment, isPatchLibraries, isPatchRelease, isEnhancementLibraries, release, level, upgrade,
						releaseDescription, includeMenuOptions);
		this.isMisysBundle = isMisysBundle;
		isBundle = true;
		determineJournalingStatus();
	}
	/**
	 * Constructor
	 */
	public DeploymentToolbox(EquationStandardSession session, boolean isUnitDeployment, boolean isPatchLibraries,
					boolean isPatchRelease, boolean isEnhancementLibraries, String release, String level, String upgrade,
					String releaseDescription, boolean includeMenuOptions) throws Exception
	{

		this.session = session;
		unit = session.getUnit();
		// Make sure the GB cache is up to date as we need to know what layouts go with what services to deploy translation text
		unit.getRecords().getGBRecords(false);

		this.isUnitDeployment = isUnitDeployment;
		this.isPatchLibraries = isPatchLibraries;
		this.isPatchRelease = isPatchRelease;
		this.isEnhancementLibraries = isEnhancementLibraries;
		isMisysBundle = false;

		this.release = release.trim().toUpperCase();
		this.level = level.trim().toUpperCase();
		this.upgrade = upgrade.trim().toUpperCase();
		this.releaseDescription = releaseDescription;
		this.includeMenuOptions = includeMenuOptions;

		this.order = '@' + this.release + this.level + this.upgrade;

		as400 = session.getUnit().getEquationSystem().getAS400();
		command = new CommandCall(as400);

		if (!isUnitDeployment)
		{
			targetLibLibrary = "LIB" + this.release + this.level + this.upgrade;
			targetFilLibrary = "FIL" + this.release + this.level + this.upgrade;
			targetInpLibrary = "INP" + this.release + this.level + this.upgrade;
			targetWrkLibrary = "WRK" + this.release + this.level + this.upgrade;
		}
		else
		{
			targetLibLibrary = session.getUnit().getKLIBLibrary();
			targetFilLibrary = session.getUnit().getKFILLibrary();
			targetInpLibrary = session.getUnit().getKINPLibrary();
			targetWrkLibrary = session.getUnit().getKWRKLibrary();
			determineJournalingStatus();
		}
		isBundle = false;
	}

	/**
	 * Protect against cloning
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
		// that'll teach 'em
	}
	/**
	 * Determine journaling status of the files
	 * 
	 * @throws Exception
	 */
	public void determineJournalingStatus() throws Exception
	{
		// CHPF is only written when doing patch bundles or service bundle install
		// CHPF is handled outside of this routine because library changes depending on type of deployment
		isOriginallyJournaledOHPF = isFileJournaled(targetFilLibrary, "OHPF");
		isOriginallyJournaledGAPF = isFileJournaled(targetFilLibrary, "GAPF");
		isOriginallyJournaledGBPF = isFileJournaled(targetFilLibrary, "GBPF");
		if (isUnitDeployment && !isBundle)
		{
			isOriginallyJournaledGDPF = isFileJournaled(targetFilLibrary, "GDPF");
		}
		isOriginallyJournaledGAEPF = isFileJournaled(targetFilLibrary, "GAEPF");
		isOriginallyJournaledGAXPF = isFileJournaled(targetFilLibrary, "GAXPF");
		isOriginallyJournaledGAZPF = isFileJournaled(targetFilLibrary, "GAZPF");
		isOriginallyJournaledAAIPF = isFileJournaled(targetFilLibrary, "AAIPF");
		isOriginallyJournaledACNPF = isFileJournaled(targetFilLibrary, "ACNPF");
		isOriginallyJournaledACOPF = isFileJournaled(targetFilLibrary, "ACOPF");
		isOriginallyJournaledHBXPF = isFileJournaled(targetFilLibrary, "HBXPF");
	}
	/**
	 * Update Equation unit with service definition
	 */
	private void updateEquation(Function function) throws Exception
	{
		// If the following code fails then an EQException will be thrown up the chain

		DaoFactory daoFactory = new DaoFactory();

		// create the journal file
		JournalFile journalFile = new JournalFile(function, targetInpLibrary);

		// add the GZ (delete if needed)
		if (journalFile.isFileExists(session.getConnection()))
		{
			journalFile.dropFile(session.getConnection());
		}
		journalFile.writeFile(session, targetInpLibrary);
		targetInpLibraryUsed = true;
		// start the journaling
		JournalRecord record = new JournalRecord(function);
		if (isUnitDeployment)
		{
			String unitName = session.getUnitId();
			record.startJournal(session, unitName);
			record.endJournal(session, unitName);
			record.startJournal(session, unitName);
		}

		if (isUnitDeployment && !isBundle)
		{
			// Add a record to the GD file - authorise developer to function
			GDRecordDataModel gdRecordDataModel = new GDRecordDataModel(session.getEquationUserId(), function.getId());
			gdRecordDataModel.setLibrary(targetFilLibrary);
			IGDRecordDao gdRecordDao = daoFactory.getGDDao(session, gdRecordDataModel);

			gdRecordDao.insertOrUpdateRecord();
		}

		// add a record to the OH file
		OHRecordDataModel oHRecordDataModel = new OHRecordDataModel(journalFile.getFileName(), "INP", "PF");
		oHRecordDataModel.setLibrary(targetFilLibrary);
		IOHRecordDao ohRecordDao = daoFactory.getOHDao(session, oHRecordDataModel);

		ohRecordDao.insertOrUpdateRecord();

		createdOHPFRecord = true;
		targetFilLibraryUsed = true;

		// Add a record to the GA file
		if (includeMenuOptions)
		{
			GARecordDataModel gaRecordDataModel = new GARecordDataModel(function.getId(),
							EquationStandardTransaction.EDF_SHORT_ROOT + function.getId(), function.getLabel(), function
											.getApplication());
			gaRecordDataModel.setLibrary(targetFilLibrary);
			gaRecordDataModel.setExtendedInput(function.isExtendedBusinessHoursAllowed() ? "Y" : "N");
			IGARecordDao gaRecordDao = daoFactory.getGADao(session, gaRecordDataModel);
			gaRecordDao.insertOrUpdateRecord();
			getUpdatedCollection().add(gaRecordDao.getGARecord());
			createdGAPFRecord = true;
			targetFilLibraryUsed = true;
		}

		// Add a record to the GAE file
		GAERecordDataModel gaeRecordDataModel = new GAERecordDataModel(function.getId());
		gaeRecordDataModel.setLibrary(targetFilLibrary);
		IGAERecordDao gaeRecordDao = daoFactory.getGAEDao(session, gaeRecordDataModel);
		// Set field values relevant to services
		gaeRecordDataModel.setFunctionId(function.getId());
		gaeRecordDataModel.setProgramName(""); // This is the input program name
		gaeRecordDataModel.setDescription(function.getLabel());
		gaeRecordDataModel.setExtendedInputAllowed(function.isExtendedBusinessHoursAllowed());
		gaeRecordDataModel.setProgramRoot(EquationStandardTransaction.EDF_ROOT);
		gaeRecordDataModel.setHeaderJournalFileName(journalFile.getFileName());
		// TODO More logic will be needed here for lists if new lists are supported in the Service Composer.
		if (function.isAllowedEnq())
		{
			gaeRecordDataModel.setType(GAERecordDataModel.TYPE_FIXED_ENQUIRY_API);
			gaeRecordDataModel.setSupportsAdd(false);
			gaeRecordDataModel.setSupportsMaintain(false);
			gaeRecordDataModel.setSupportsDelete(false);
		}
		else
		{
			gaeRecordDataModel.setType(GAERecordDataModel.TYPE_FIXED_INPUT_API);
			gaeRecordDataModel.setSupportsAdd(function.isAllowedAdd());
			gaeRecordDataModel.setSupportsMaintain(function.isAllowedMaint());
			gaeRecordDataModel.setSupportsDelete(function.isAllowedDel());
		}
		String keys = "";
		for (InputField inputField : function.rtvPrimaryInputFieldSet().getInputFields())
		{
			if (inputField.isKey())
			{
				keys = keys + (keys.length() > 0 ? ":" + inputField.getId() : inputField.getId());
			}
		}
		gaeRecordDataModel.setKeys(keys);

		gaeRecordDao.insertOrUpdateRecord();
		getUpdatedCollection().add(gaeRecordDao.getGAERecord());
		createdGAEPFRecord = true;
		targetFilLibraryUsed = true;

		// Add/Update/Delete AAI record
		AAIRecordDataModel aaiRecord = new AAIRecordDataModel(function.getId());
		aaiRecord.setLibrary(targetFilLibrary);
		IAAIRecordDao aaiRecordDao = daoFactory.getAAIDao(session, aaiRecord);
		if (function.containsAPIFieldSet(Function.EFC_FIELDSET))
		{
			populateAAIRecord(aaiRecord, function);
			aaiRecordDao.insertOrUpdateRecord();
			createdAAIPFRecord = true;
			targetFilLibraryUsed = true;
		}
		else
		{
			if (isUnitDeployment)
			{
				aaiRecordDao.deleteRecord();
			}
			else
			{
				aaiRecord.setEvent(function.getId());
				aaiRecord.setDescription("*DELETE*");
				aaiRecordDao.insertOrUpdateRecord();
				createdAAIPFRecord = true;
				targetFilLibraryUsed = true;
			}
		}
	}

	/**
	 * This method populates the values of the AAI record
	 * 
	 * The display flag fields will be Y or N depending on whether the corresponding field in the EFC update API is assigned by
	 * mapping, script or java code. The corresponding fields to check are as follows:
	 * <ul>
	 * <li>AAIDTRF (Display transaction reference) = ETREF</li>
	 * <li>AAIDCRA (Display credit account) = ECAB (Check credit account branch only)</li>
	 * <li>AAIDDTE (Display transaction date) = ECDTE</li>
	 * <li>AAIDARF (Display additional reference) = EXREF</li>
	 * <li>AAIDSQN ( Display sequence number) = ESQN</li>
	 * </ul>
	 * 
	 * The Transaction Amount Type field can be 0, 1 or 2, depending on whether the debit,
	 * 
	 * 
	 * @param aaiRecord
	 *            the <code>AAIRecordDataModel</code> instance to populate
	 * @param function
	 *            the <code>Function</code> being deployed
	 * 
	 * @throws EQException
	 */
	private void populateAAIRecord(AAIRecordDataModel aaiRecord, Function function) throws Exception
	{
		// Obtain the EFC update API FieldSet to check for mappings
		APIFieldSet efcFieldSet = function.getUpdateAPI(Function.EFC_FIELDSET);

		aaiRecord.setDescription(function.getLabel()); // AAIEVDS Description
		aaiRecord.setType(AAIRecordDataModel.EVENT_TYPE_WMENU1); // AAIEVTP Event type
		aaiRecord.setApp(""); // AAIAPP Application
		aaiRecord.setReference(getEntityType(function, efcFieldSet)); // AAIADC (Account/Deal/Commitment)
		int operationsSupportedCount = 0;
		if (function.isAllowedAdd())
		{
			operationsSupportedCount++;
		}
		if (function.isAllowedMaint())
		{
			operationsSupportedCount++;
		}
		if (function.isAllowedDel())
		{
			operationsSupportedCount++;
		}
		// Evaluate transaction type.
		// Note that this will default to 'F' if none of add/maintain/delete are allowed, but
		// it is assumed that this would be invalid and validation would prevent deployment of
		// such service definitions. Or, for Enquiries, EFC would not be allowed.
		String transactionType = AAIRecordDataModel.TRANSACTION_TYPE_FULLY_FUNCTIONAL;
		if (operationsSupportedCount == 1)
		{
			if (function.isAllowedAdd())
			{
				transactionType = AAIRecordDataModel.TRANSACTION_TYPE_ADD;
			}
			else if (function.isAllowedMaint())
			{
				transactionType = AAIRecordDataModel.TRANSACTION_TYPE_MAINTAIN;
			}
			if (function.isAllowedDel())
			{
				transactionType = AAIRecordDataModel.TRANSACTION_TYPE_CANCEL;
			}
		}
		aaiRecord.setTranType(transactionType); // AAITTP Transaction type
		aaiRecord.setUserDefined("N"); // AAIUSRD User defined?

		// Set transaction amount depending on the mapped amounts.
		String amountType;
		if (function.hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_ECAMT))
		{
			// Note that validation exists to check for mapping to the credit amount without mapping to the debit amount
			amountType = "2";
		}
		else if (function.hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_EDAMT))
		{
			amountType = "1";
		}
		else
		{
			amountType = "0";
		}
		aaiRecord.setTranAmount(amountType); // AAITATP Transaction amount type
		aaiRecord.setDispTranRef(isAssignedString(function, efcFieldSet, FunctionCommonData.EFC_ETREF)); // AAIDTRF Display
		// transaction reference
		aaiRecord.setDispCreditAc(isAssignedString(function, efcFieldSet, FunctionCommonData.EFC_ECAB)); // AAIDCRA Display credit
		// account
		aaiRecord.setDispTranDate(isAssignedString(function, efcFieldSet, FunctionCommonData.EFC_ETDTE)); // AAIDDTE Display
		// transaction date
		aaiRecord.setDispAddRef(isAssignedString(function, efcFieldSet, FunctionCommonData.EFC_EXREF)); // AAIDARF Display
		// additional reference
		aaiRecord.setDispSqn(isAssignedString(function, efcFieldSet, FunctionCommonData.EFC_ESQN)); // AAIDSQN Display sequence
		// number
		aaiRecord.setSupTQ("N"); // AAITQ - Support transaction queueing
		aaiRecord.setSupFE("N"); // AAIFE - Support four-eyes
		aaiRecord.setChargeEvent("N"); // AAICHG - Support charge event
		aaiRecord.setSupEFC("Y"); // AAIEFE - Support enhanced fees
		aaiRecord.setSupTranAudTrail("Y"); // AAITAT - Support transaction audit trail
	}

	/**
	 * Determines the value of the AAIADC (Account/Deal/Commitment) field, based on the mappings made to the EFC update API.
	 * <p>
	 * This assumes that at least one Entity is mapped to (validation should ensure this). This may need to be enhanced to cater for
	 * Customer and Customer or Account entities.
	 * 
	 * @param function
	 *            the <code>Function</code> being deployed
	 * @param efcFieldSet
	 *            the EFC <code>APIFieldSet</code>
	 * @return a <code>String</code> containing the type
	 */
	private String getEntityType(Function function, APIFieldSet efcFieldSet)
	{
		String result = AAIRecordDataModel.ENTITY_TYPE_ACCOUNT;
		if (function.hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_ECMR))
		{
			result = AAIRecordDataModel.ENTITY_TYPE_COMMITMENT;
		}
		else if (function.hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_EBRNM))
		{
			result = AAIRecordDataModel.ENTITY_TYPE_DEAL;
		}
		return result;
	}

	/**
	 * Determines whether the specified EFC field is assigned (by mapping/script/java) or not, and returns either "Y" or "N" values,
	 * suitable for using directly in the relevant setter property.
	 * 
	 * @param function
	 *            the <code>Function</code> being deployed
	 * @param efcFieldSet
	 *            the EFC <code>APIFieldSet</code>
	 * @param fieldName
	 *            the name of the EFC API field to check for an update mapping
	 * @return a <code>String</code> containing either "Y" or "N", indicating whether the EFC field is mapped to
	 */
	private String isAssignedString(Function function, APIFieldSet efcFieldSet, String fieldName)
	{
		return function.hasUpdateAssignment(efcFieldSet, fieldName) ? "Y" : "N";
	}

	/**
	 * Writes an equation GBPF (option) record for a layout
	 * 
	 * @param layout
	 *            - the layout
	 * 
	 */
	private void createLayoutOption(Layout layout, String title)
	{
		IGBRecordDao gbDao = null;

		// retrieve the GA record
		String app = "";
		GARecordDataModel gaRecord = new GARecordDataModel(layout.getId());
		IGARecordDao gaDao = daoFactory.getGADao(session, gaRecord);
		gaRecord = gaDao.getGARecord();
		if (gaRecord != null)
		{
			app = gaRecord.getApplication();
		}

		// add a record to the GB file
		GBRecordDataModel gbRecord = new GBRecordDataModel(layout.getId(), EquationStandardTransaction.EDF_SHORT_ROOT
						+ layout.getId(), title, app, layout.getServiceId());

		gbRecord.setLibrary(targetFilLibrary);
		gbRecord.setGbwm1("N");
		gbRecord.setMandatoryNextReq(layout.getNextRequest());
		gbRecord.setUserFuncKey1(layout.getCommandKeyOption1());
		gbRecord.setUserFuncKey2(layout.getCommandKeyOption2());
		gbRecord.setUserFuncKey3(layout.getCommandKeyOption3());
		gbRecord.setUserFuncKey4(layout.getCommandKeyOption4());
		gbDao = daoFactory.getGBDao(session, gbRecord);
		gbDao.insertOrUpdateRecord();
		getUpdatedCollection().add(gbDao.getGBRecord());
		createdGBPFRecord = true;
		targetFilLibraryUsed = true;
	}

	/**
	 * Deploys a function and its associated java user exit code
	 * 
	 * @param function
	 * @param javaBinaryFile
	 *            (may be null - a function may not have any user exit requirements)
	 * 
	 * @throws EQException
	 */
	public synchronized void deployFunction(Function function, File javaBinaryFile, File javaSourceFile) throws Exception
	{
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		String functionXML = beanFactory.serialiseBeanAsXML(function);
		GAXRecordDataModel gaxRecord = XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, targetFilLibrary,
						GAXRecordDataModel.SERVICE_CODE, function.getId(),
						Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), functionXML);
		createdGAXPFRecord = true;
		targetFilLibraryUsed = true;
		updateEquation(function);
		if (javaBinaryFile == null)
		{
			LOG.warn("deployFunction - No java binary file specified");
		}
		else
		{
			AdaptorToolbox.writeFunctionAdaptor(session, targetFilLibrary, function, javaBinaryFile, javaSourceFile);
			createdGAZPFRecord = true;
		}
		getUpdatedCollection().add(gaxRecord);
	}

	/**
	 * Deploy translation bean
	 * 
	 * Deployment of reusable text can involve thousand of records. Reusable text is never physically deleted. In order to have a
	 * quick development deployment process, the whole set of current HBXPF records is retrieved first so that insert or update
	 * checking can be done in memory without having to use SQL to access the iSeries. This is considerably faster.
	 * 
	 * Reference text is always completed deleted and then re-added.
	 * 
	 * @param translationToInstall
	 * @param owners
	 *            (used for service translation beans as these contains owners for the service and the layout)
	 * @throws EQException
	 */

	public synchronized void deployTranslation(Translation translationToInstall, ArrayList<String> owners) throws EQException
	{
		// Use one timestamp for whole collection of text beans / HBX records being updated
		String timestamp = Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT);
		boolean reusableText = false;
		if (TextBean.OWNER_MISYS.equalsIgnoreCase(translationToInstall.getOwnerId())
						|| TextBean.OWNER_BANK.equalsIgnoreCase(translationToInstall.getOwnerId()))
		{
			reusableText = true;
		}
		// Reusable text is never deleted. Reference text may be deleted depending on who made the text - Misys or the bank.
		// References text will be deleted and added back.
		if (!reusableText && isUnitDeployment)
		{
			// Delete existing records owned by service and layouts.
			Translation translationFromDB = XMLToolbox.getXMLToolbox().getTranslationFromDBWithCache(session, owners, false);
			if (translationFromDB != null)
			{
				// Process Database translations and delete if not found in translations to install
				List<Language> listLang = translationFromDB.getLanguages();
				for (Language lang : listLang)
				{
					List<TextBean> tempContainer = lang.getDescriptions();
					tempContainer.addAll(lang.getLabels());
					tempContainer.addAll(lang.getMasks());
					tempContainer.addAll(lang.getRegularExpressions());
					tempContainer.addAll(lang.getValidValues());

					for (TextBean textBeanFromDB : tempContainer)
					{
						deleteTextFromDB(translationToInstall, textBeanFromDB);
					}
				}
			}
		}
		Translation translationFromDB = XMLToolbox.getXMLToolbox().getTranslationFromDBWithCache(session, owners, false);
		List<Language> listLang = translationToInstall.getLanguages();
		for (Language lang : listLang)
		{
			List<TextBean> tempContainer = lang.getDescriptions();
			tempContainer.addAll(lang.getLabels());
			tempContainer.addAll(lang.getMasks());
			tempContainer.addAll(lang.getRegularExpressions());
			tempContainer.addAll(lang.getValidValues());

			for (TextBean textBeanToInstall : tempContainer)
			{
				insertOrUpdateTextToDB(translationFromDB, textBeanToInstall, reusableText, timestamp);
			}

		}

		createdHBXPFRecord = true;
		targetFilLibraryUsed = true;

	}
	/**
	 * Delete text from Database
	 * 
	 * @param translationToInstall
	 * @param textBeanFromDB
	 * @throws EQException
	 */
	private void deleteTextFromDB(Translation translationToInstall, TextBean textBeanFromDB) throws EQException
	{
		HBXRecordDataModel hbxRecordDataModel = new HBXRecordDataModel();
		hbxRecordDataModel.setLibrary(targetFilLibrary);

		IHBXRecordDao dao = daoFactory.getHBXDao(session, hbxRecordDataModel);
		boolean isMisysUnit = session.getUnit().isMisysMode();

		if (!TranslationToolbox.isReferenceTextKeyUsed(translationToInstall, textBeanFromDB.getType(), textBeanFromDB.getKey()))
		{
			// Misys text can only be deleted when deployed in Misys bundle. Misys text starts with 0.
			if (isMisysBundle || isMisysUnit)
			{
				dao.deleteRecordsBy("HBXOWN = '" + textBeanFromDB.getOwner() + "' AND HBXLNM = '" + textBeanFromDB.getLanguage()
								+ "' AND HBXTYP = '" + textBeanFromDB.getType() + "' AND HBXKEY = '" + textBeanFromDB.getKey()
								+ "' AND HBXKEY like '0%'");
			}
			else
			{
				// Bank text starting with keys starting 1 can only be deleted when deployed by bank
				dao.deleteRecordsBy("HBXOWN = '" + textBeanFromDB.getOwner() + "' AND HBXLNM = '" + textBeanFromDB.getLanguage()
								+ "' AND HBXTYP = '" + textBeanFromDB.getType() + "' AND HBXKEY = '" + textBeanFromDB.getKey()
								+ "' AND HBXKEY like '1%'");
			}
		}

	}
	/**
	 * Inserts or updates or deletes a HBX record
	 * 
	 * @param translationFromDB
	 * @param textBeanToInstall
	 * @param reusableText
	 * @param timestamp
	 */
	private void insertOrUpdateTextToDB(Translation translationFromDB, TextBean textBeanToInstall, boolean reusableText,
					String timestamp)
	{
		String owner = textBeanToInstall.getOwner();
		String languageId = textBeanToInstall.getLanguage();
		String type = textBeanToInstall.getType();
		String key = textBeanToInstall.getKey();
		String transText = textBeanToInstall.getText();
		HBXRecordDataModel hbxRecord = new HBXRecordDataModel(owner, languageId, type, key);
		IHBXRecordDao dao = daoFactory.getHBXDao(session, hbxRecord);
		hbxRecord.setLibrary(targetFilLibrary);
		hbxRecord.setOwner(owner);
		hbxRecord.setLanguageCode(languageId);
		hbxRecord.setType(type);
		hbxRecord.setKey(key);
		hbxRecord.setText(transText);

		// If making a patch then all records will be inserted into #HBXPF.
		if (!isUnitDeployment)
		{
			if (textBeanToInstall.isLogicallyDeleted())
			{
				// Note null value is not allowed here so a single early timestamp is used.
				hbxRecord.setTimestamp(Toolbox.getEarliestTimestamp());
			}
			else
			{
				// Change timestamp
				hbxRecord.setTimestamp(timestamp);
			}
			dao.insertRecord(hbxRecord);

			// Add hbx record to cache
			if (reusableText)
			{
				getUpdatedCollection().add(textBeanToInstall);
			}

		}
		else
		{
			TextBean textBeanFromDB = null;
			if (translationFromDB != null)
			{
				textBeanFromDB = TranslationToolbox.findReferenceText(translationFromDB, languageId, owner, type, key);
			}
			// Insert HBX record. If not reusable text then there should be no record to update.
			if (textBeanFromDB == null)
			{
				// If text has never been deployed then don't add it if its been logically deleted
				if (!textBeanToInstall.isLogicallyDeleted())
				{
					hbxRecord.setTimestamp(timestamp);
					dao.insertRecord(hbxRecord);

					// Add hbx record to cache
					if (reusableText)
					{
						getUpdatedCollection().add(textBeanToInstall);
					}
				}
			}

			// Update HBX record
			else
			{
				// Don't update if both text are the same
				if (!transText.equals(textBeanFromDB.getText()) || textBeanToInstall.isLogicallyDeleted())
				{
					if (textBeanToInstall.isLogicallyDeleted())
					{
						// Note null value is not allowed here so a single early timestamp is used.
						hbxRecord.setTimestamp(Toolbox.getEarliestTimestamp());
					}
					else
					{
						hbxRecord.setTimestamp(timestamp);
					}
					dao.updateRecord(hbxRecord);
					// Update hbx record in cache
					if (reusableText)
					{
						getUpdatedCollection().add(textBeanToInstall);
					}
				}

			}
		}
	}
	/**
	 * Deploys a single layout
	 * <p>
	 * 
	 * @param layout
	 * @param javaBinaryFile
	 *            (may be null - a layout may not have any user exit requirements)
	 * @throws EQException
	 */
	public synchronized void deployLayout(Layout layout, String title, File javaBinaryFile, File javaSourceFile) throws Exception
	{
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		String layoutXML = beanFactory.serialiseBeanAsXML(layout);
		GAXRecordDataModel gaxRecord = XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, targetFilLibrary,
						GAXRecordDataModel.LAYOUT_CODE, layout.getId(),
						Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), layoutXML);
		if (includeMenuOptions)
		{
			createLayoutOption(layout, title);
		}
		if (javaBinaryFile == null)
		{
			LOG.warn("deployLayout - No java binary file specified");
		}
		else
		{
			AdaptorToolbox.writeLayoutAdaptor(session, targetFilLibrary, layout, javaBinaryFile, javaSourceFile);
		}

		getUpdatedCollection().add(gaxRecord);

		createdGAXPFRecord = true;
		targetFilLibraryUsed = true;

		if (isUnitDeployment && !isBundle)
		{
			// Add a record to the GD file - authorise developer to layout
			GDRecordDataModel gdRecordDataModel = new GDRecordDataModel(session.getEquationUserId(), layout.getId());
			gdRecordDataModel.setLibrary(targetFilLibrary);
			IGDRecordDao gdRecordDao = daoFactory.getGDDao(session, gdRecordDataModel);

			gdRecordDao.insertOrUpdateRecord();
		}

	}

	/**
	 * Deploys a single PV
	 * <p>
	 * 
	 * @param javaBinaryFile
	 * @param javaSourceFile
	 * @throws Exception
	 */
	public synchronized void deployPvMetaData(EquationPVMetaData pvMetaData, File javaBinaryFile, File javaSourceFile)
					throws Exception
	{
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		String pvXML = beanFactory.serialiseBeanAsXML(pvMetaData);
		GAXRecordDataModel gaxRecord = XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, targetFilLibrary,
						GAXRecordDataModel.PV_CODE, pvMetaData.getId(),
						Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), pvXML);

		AdaptorToolbox.writeClassToDB(session, targetFilLibrary, javaBinaryFile, "", pvMetaData.getId(), "",
						GAZRecordDataModel.TYP_PV, pvMetaData.getPackageName(), pvMetaData.getId());

		AdaptorToolbox.writeClassToDB(session, targetFilLibrary, javaSourceFile, "", pvMetaData.getId(), "",
						GAZRecordDataModel.TYP_PV_SRC, pvMetaData.getPackageName(), pvMetaData.getId());

		DaoFactory d = new DaoFactory();
		IGAERecordDao gdao = d.getGAEDao(session, new GAERecordDataModel());
		gdao.insertOrUpdateRecord(pvMetaDataToGAE(pvMetaData));
		getUpdatedCollection().add(gdao.getGAERecord());
		getUpdatedCollection().add(gaxRecord);
		createdGAXPFRecord = true;
		createdGAZPFRecord = true;
		createdGAEPFRecord = true;
		targetFilLibraryUsed = true;
	}

	private GAERecordDataModel pvMetaDataToGAE(EquationPVMetaData pv)
	{
		GAERecordDataModel record = new GAERecordDataModel();

		if (pv.getDescription() == null)
		{
			pv.setDescription("");
		}

		record.setApiId(pv.getId());
		record.setCopysourceName("");
		record.setDatastructureName("");
		record.setDescription(pv.getDescription());
		record.setDisplayFile("");
		record.setEnabledFor24x7("");
		record.setEnhancedAPIEnhanced("N");
		record.setEnhancement("N");
		record.setExtendedInputAllowed(false);
		record.setFunctionId("");
		record.setKeys(pv.rtvDelimetedKeyList());
		record.setLibrary(getTargetFilLibrary());
		record.setListRetrievalRequired("");
		record.setPcProgramName("");
		record.setPrimaryPVModule("");
		record.setProgramName("");
		record.setProgramRoot("W90F");
		record.setPromptable("Y");
		record.setScreenHandler(pv.getId());
		record.setScreenHandlerDescription(pv.getDescription());
		record.setStandardAPIEnhanced("N");
		record.setSupportsAdd(false);
		record.setSupportsMaintain(false);
		record.setSupportsDelete(false);
		record.setType(GAERecordDataModel.TYPE_BFEQPV);
		record.setUserDefinedAndPromptable("Y");
		record.setUserDefinedKeysAllowed("Y");

		return record;
	}
	/**
	 * Deploys a single service user exit
	 * 
	 * @param userExit
	 * @param binaryFile
	 * @param javaSourceFile
	 * @throws EQException
	 */
	public synchronized void deployServiceUserExit(ServiceUserExit userExit, File binaryFile, File javaSourceFile) throws Exception
	{

		// Write the Java class file record to the GAZPF
		// Note that this does NOT cater for inner classes (not supported for service user exit Java classes)
		AdaptorToolbox.writeClassToDB(session, targetFilLibrary, binaryFile, "", userExit.getServiceUserExitName(), "",
						GAZRecordDataModel.TYP_SERVICE_USEREXIT, userExit.getPackageName(), userExit.getServiceUserExitName());

		AdaptorToolbox.writeClassToDB(session, targetFilLibrary, javaSourceFile, "", userExit.getServiceUserExitName(), "",
						GAZRecordDataModel.TYP_SERVICE_USEREXIT_SRC, userExit.getPackageName(), userExit.getServiceUserExitName());
		createdGAZPFRecord = true;
		targetFilLibraryUsed = true;
	}
	/**
	 * Deploys a single user exit
	 * 
	 * @param userExit
	 * @param binaryFile
	 * @param javaSourceFile
	 * @throws EQException
	 */
	public synchronized void deployUserExit(UserExit userExit, File binaryFile, File javaSourceFile) throws Exception
	{

		// Ensure that the collection is sorted, so that comparisons will work OK
		userExit.sort();

		// Write the control record to the ACNPF
		writeUserExitControlRecord(userExit);

		// Write the inclusion filter records to the ACOPF
		updateExitFilterDB(userExit);

		// Write the Java class file record to the GAZPF
		// Note that this does NOT cater for inner classes (not supported for RPG user exit Java classes)
		AdaptorToolbox.writeClassToDB(session, targetFilLibrary, binaryFile, "", userExit.getProgramName(), "",
						GAZRecordDataModel.TYP_RPGUSEREXIT, userExit.getPackageName(), userExit.getProgramName());

		AdaptorToolbox.writeClassToDB(session, targetFilLibrary, javaSourceFile, "", userExit.getProgramName(), "",
						GAZRecordDataModel.TYP_RPGUSEREXIT_SRC, userExit.getPackageName(), userExit.getProgramName());

		createdGAZPFRecord = true;
		targetFilLibraryUsed = true;
	}

	/**
	 * Removes all iSeries database records for a service user exit
	 * 
	 * @param userExit
	 */
	public void undeployServiceUserExit(ServiceUserExit userExit)
	{
		GAZRecordDataModel gazRecord = new GAZRecordDataModel("", userExit.getServiceUserExitName(), "",
						GAZRecordDataModel.TYP_SERVICE_USEREXIT);
		IGAZRecordDao gazRecordDao = daoFactory.getGAZDao(session, gazRecord);
		gazRecordDao.deleteRecord();

		gazRecord = new GAZRecordDataModel("", userExit.getServiceUserExitName(), "", GAZRecordDataModel.TYP_SERVICE_USEREXIT_SRC);
		gazRecordDao = daoFactory.getGAZDao(session, gazRecord);
		gazRecordDao.deleteRecord();

	}

	/**
	 * Removes all iSeries database records for a UserExit
	 * 
	 * @param userExit
	 */
	public void undeployUserExit(UserExit userExit)
	{
		ACNRecordDataModel acn = new ACNRecordDataModel();
		acn.setProgram(userExit.getProgramName());
		daoFactory.getACNDao(session, acn).deleteRecord();

		ACORecordDataModel aco = new ACORecordDataModel();
		aco.setUserExit(userExit.getProgramName());
		daoFactory.getACODao(session, aco).deleteRecord();

		GAZRecordDataModel gazRecord = new GAZRecordDataModel("", userExit.getProgramName(), "", GAZRecordDataModel.TYP_RPGUSEREXIT);
		IGAZRecordDao gazRecordDao = daoFactory.getGAZDao(session, gazRecord);
		gazRecordDao.deleteRecord();

		gazRecord = new GAZRecordDataModel("", userExit.getProgramName(), "", GAZRecordDataModel.TYP_RPGUSEREXIT_SRC);
		gazRecordDao = daoFactory.getGAZDao(session, gazRecord);
		gazRecordDao.deleteRecord();

	}

	/**
	 * This method ensures that a control record (ACNPF) record exists for the validation program, with the correct fully-qualified
	 * class name
	 */
	private void writeUserExitControlRecord(UserExit userExit)
	{
		ACNRecordDataModel acn = new ACNRecordDataModel();
		acn.setLibrary(targetFilLibrary);
		acn.setProgram(userExit.getProgramName());
		ACNRecordDataModel record = daoFactory.getACNDao(session, acn).getACNRecord();
		String qualifiedClassName = userExit.getPackageName().length() > 0 ? userExit.getPackageName() + "."
						+ userExit.getProgramName() : userExit.getProgramName();
		if (record == null || !isUnitDeployment)
		{
			record = new ACNRecordDataModel();
			record.setProgram(userExit.getProgramName());
			record.setClassName(qualifiedClassName);
			daoFactory.getACNDao(session, record).insertRecord();
			createdACNPFRecord = true;
			targetFilLibraryUsed = true;
		}
		else
		{
			// Check that the class name is correct:
			if (!record.getClassName().equals(qualifiedClassName))
			{
				record.setClassName(qualifiedClassName);
				daoFactory.getACNDao(session, record).updateRecord();
			}
		}
	}

	/**
	 * This method updates the validate user exit java filters on the iSeries (held in the ACNPF) to match those in the .eqx file
	 * being deployed
	 */
	private void updateExitFilterDB(UserExit userExit)
	{
		ACORecordDataModel acoModel = new ACORecordDataModel();
		acoModel.setLibrary(targetFilLibrary);
		List<AbsRecord> list = daoFactory.getACODao(session, acoModel).getRecordBy(
						"ACOPGM = '" + userExit.getProgramName() + "' ORDER BY ACOPGM, ACOSCN, ACOMOD");

		List<ValidationUserExitFilter> localFilters = userExit.getUserExitFilters();
		List<ValidationUserExitFilter> deployedFilters = new ArrayList<ValidationUserExitFilter>();
		// If deployment is via Patch/Enhancement then installation program will
		// delete all the ACOPF records for the program and then add all records for the program.
		if (!isUnitDeployment)
		{
			// Need to insert this record
			ACORecordDataModel newRecordDelete = new ACORecordDataModel();
			newRecordDelete.setUserExit(userExit.getProgramName());
			newRecordDelete.setScreen(0);
			newRecordDelete.setMode('X');
			newRecordDelete.setLibrary(targetFilLibrary);
			daoFactory.getACODao(session, newRecordDelete).insertRecord();
			createdACOPFRecord = true;
			targetFilLibraryUsed = true;

			int localIndex = 0;
			ValidationUserExitFilter localFilter = safeGetFilter(localFilters, localIndex++);
			while (localFilter != null)
			{
				// Need to insert this record
				ACORecordDataModel newRecord = new ACORecordDataModel();
				newRecord.setUserExit(userExit.getProgramName());
				newRecord.setScreen(localFilter.getScreen());
				newRecord.setLibrary(targetFilLibrary);
				if (localFilter.getMode().length() > 0)
				{
					newRecord.setMode(localFilter.getMode().charAt(0));
				}

				daoFactory.getACODao(session, newRecord).insertRecord();
				createdACOPFRecord = true;
				targetFilLibraryUsed = true;
				localFilter = safeGetFilter(localFilters, localIndex++);
			}
		}
		else
		{
			// Read through all existing ACOPF records, and populate a
			// temporary collection
			for (AbsRecord absRecord : list)
			{
				ACORecordDataModel dbFilter = (ACORecordDataModel) absRecord;
				ValidationUserExitFilter temp = new ValidationUserExitFilter();
				temp.setScreen(dbFilter.getScreen());
				temp.setMode(String.valueOf(dbFilter.getMode()));
				deployedFilters.add(temp);
			}

			int localIndex = 0;
			ValidationUserExitFilter localFilter = safeGetFilter(localFilters, localIndex++);
			int deployedIndex = 0;
			ValidationUserExitFilter deployedFilter = safeGetFilter(deployedFilters, deployedIndex++);

			// Compare the two items, and insert / delete the existing database item
			// if appropriate
			while (localFilter != null || deployedFilter != null)
			{
				int compare = compareFilters(localFilter, deployedFilter);
				if (compare == 0)
				{
					// Increment both:
					localFilter = safeGetFilter(localFilters, localIndex++);
					deployedFilter = safeGetFilter(deployedFilters, deployedIndex++);
				}
				if (compare == 1)
				{
					// Need to insert this record
					ACORecordDataModel newRecord = new ACORecordDataModel();
					newRecord.setUserExit(userExit.getProgramName());
					newRecord.setScreen(localFilter.getScreen());
					newRecord.setLibrary(targetFilLibrary);
					if (localFilter.getMode().length() > 0)
					{
						newRecord.setMode(localFilter.getMode().charAt(0));
					}

					daoFactory.getACODao(session, newRecord).insertRecord();
					createdACOPFRecord = true;
					targetFilLibraryUsed = true;
					localFilter = safeGetFilter(localFilters, localIndex++);
				}
				if (compare == -1)
				{
					// Need to delete this record..
					ACORecordDataModel model = new ACORecordDataModel();
					model.setUserExit(userExit.getProgramName());
					model.setScreen(deployedFilter.getScreen());
					String pgm = userExit.getProgramName();
					int scn = deployedFilter.getScreen();
					String mode = String.valueOf(deployedFilter.getMode());
					// model.setMode(deployedFilter.getMode());
					daoFactory.getACODao(session, model).deleteRecordsBy(
									"ACOPGM = '" + pgm + "' AND ACOSCN = " + scn + " AND ACOMOD = '" + mode + "' ");
					// Add move onto next deployed filter:
					deployedFilter = safeGetFilter(deployedFilters, deployedIndex++);
				}
			}
		}
	}

	/**
	 * This method will return either the requested item in the collection, or null if beyond the size of the collection
	 * 
	 * @param filters
	 * @param index
	 * @return ValidationUserExitFilter
	 */
	private static ValidationUserExitFilter safeGetFilter(List<ValidationUserExitFilter> filters, int index)
	{
		ValidationUserExitFilter result = null;
		if (filters.size() > index)
		{
			result = filters.get(index);
		}
		return result;
	}

	/**
	 * Compares the two filters and returns a value to indicate which is the 'greatest':
	 * 
	 * Returns 1 if the deployedFilter filter is greater than the local filter (so need to insert) Returns -1 if the localFilter
	 * filter is greater than the deployed filter (so need to delete) Returns 0 if the same
	 * 
	 * @param localFilter
	 * @param deployedFilter
	 * @return int
	 */
	private static int compareFilters(ValidationUserExitFilter localFilter, ValidationUserExitFilter deployedFilter)
	{
		int result = 0;

		// Process comparisons when one or both is null here, to
		// simplify the latest comparisons.
		if (localFilter == null && deployedFilter == null)
		{
			return 0;
		}
		if (localFilter == null && deployedFilter != null)
		{
			return -1;
		}
		if (localFilter != null && deployedFilter == null)
		{
			return 1;
		}

		// Both items are not null here:
		if (localFilter.getScreen() == deployedFilter.getScreen() && localFilter.getMode().equals(deployedFilter.getMode()))
		{
			result = 0;
		}
		else if (localFilter.getScreen() > deployedFilter.getScreen()
						|| (localFilter.getScreen() == deployedFilter.getScreen() && localFilter.getMode().compareTo(
										deployedFilter.getMode()) > 0))
		{
			result = -1;
		}
		else
		{
			result = 1;
		}
		return result;
	}

	/**
	 * Returns true if any libraries involved in the release already exist
	 * 
	 * @return true if any libraries involved in the release already exist
	 */
	public boolean releaseExists() throws Exception
	{
		boolean result = false;

		result = chkobj("QSYS", targetLibLibrary, "*LIB");

		if (result == false)
		{
			result = chkobj("QSYS", targetFilLibrary, "*LIB");

		}
		if (result == false)
		{
			result = chkobj("QSYS", targetInpLibrary, "*LIB");
		}
		if (result == false)
		{
			result = chkobj("QSYS", targetWrkLibrary, "*LIB");
		}
		if (result == false)
		{
			result = chkobj("QSYS", order, "*LIB");
		}
		return result;
	}

	private void createReleaseLibraries() throws Exception
	{
		crtlib(targetLibLibrary, releaseDescription);
		crtlib(targetFilLibrary, releaseDescription);
		crtlib(targetInpLibrary, releaseDescription);
		// crtlib(targetWrkLibrary, releaseDescription);
	}

	private void deleteReleaseLibraries() throws Exception
	{
		if (targetLibLibraryUsed == true)
		{
			dltlib(targetLibLibrary);
		}
		if (targetFilLibraryUsed == true)
		{
			dltlib(targetFilLibrary);
		}
		if (targetInpLibraryUsed == true)
		{
			dltlib(targetInpLibrary);
		}
		if (targetWrkLibraryUsed == true)
		{
			// dltlib(targetWrkLibrary);
		}
	}

	private void createDuplicateObjects() throws Exception
	{
		String unitFilLibrary = unit.getKFILLibrary();

		crtdupobj("OHPF", unitFilLibrary, targetFilLibrary, false);
		crtdupobj("GAPF", unitFilLibrary, targetFilLibrary, false);
		crtdupobj("GBPF", unitFilLibrary, targetFilLibrary, false);
		crtdupobj("GAEPF", unitFilLibrary, targetFilLibrary, false);

		crtdupobj("GAXPF", unitFilLibrary, targetFilLibrary, false);

		crtdupobj("GAZPF", unitFilLibrary, targetFilLibrary, false);

		crtdupobj("AAIPF", unitFilLibrary, targetFilLibrary, false);

		crtdupobj("ACNPF", unitFilLibrary, targetFilLibrary, false);

		crtdupobj("ACOPF", unitFilLibrary, targetFilLibrary, false);

		crtdupobj("HBXPF", unitFilLibrary, targetFilLibrary, false);

		createdOHPFRecord = false;
		createdGAPFRecord = false;
		createdGBPFRecord = false;
		createdGAEPFRecord = false;
		createdGAXPFRecord = false;
		createdGAZPFRecord = false;
		createdAAIPFRecord = false;
		createdACNPFRecord = false;
		createdACOPFRecord = false;
		createdHBXPFRecord = false;

		targetLibLibraryUsed = false;
		targetFilLibraryUsed = false;
		targetInpLibraryUsed = false;
		targetWrkLibraryUsed = false;
	}
	/**
	 * Start journaling on files updated by installation of Equation services. These files may not be journaled unless the phase is
	 * "DAY".
	 * 
	 * @throws Exception
	 */
	public void startJournaling() throws Exception
	{
		// CHPF is only written when doing patch bundles or service bundle install
		// CHPF is handled outside of this routine because library changes depending on type of deployment

		if (!isOriginallyJournaledOHPF)
		{
			strjrnpf(targetFilLibrary, "OHPF");
		}

		if (!isOriginallyJournaledGAPF)
		{
			strjrnpf(targetFilLibrary, "GAPF");
		}

		if (!isOriginallyJournaledGBPF)
		{
			strjrnpf(targetFilLibrary, "GBPF");
		}

		if (isUnitDeployment && !isBundle)
		{

			if (!isOriginallyJournaledGDPF)
			{
				strjrnpf(targetFilLibrary, "GDPF");
			}
		}

		if (!isOriginallyJournaledGAEPF)
		{
			strjrnpf(targetFilLibrary, "GAEPF");
		}

		if (!isOriginallyJournaledGAXPF)
		{
			strjrnpf(targetFilLibrary, "GAXPF");
		}

		if (!isOriginallyJournaledGAZPF)
		{
			strjrnpf(targetFilLibrary, "GAZPF");
		}

		if (!isOriginallyJournaledAAIPF)
		{
			strjrnpf(targetFilLibrary, "AAIPF");
		}

		if (!isOriginallyJournaledACNPF)
		{
			strjrnpf(targetFilLibrary, "ACNPF");
		}

		if (!isOriginallyJournaledACOPF)
		{
			strjrnpf(targetFilLibrary, "ACOPF");
		}

		if (!isOriginallyJournaledHBXPF)
		{
			strjrnpf(targetFilLibrary, "HBXPF");
		}

	}
	/**
	 * End journaling on files updated by installation of Equation services. These files may not be journaled unless the phase is
	 * "DAY".
	 * 
	 * @throws Exception
	 */
	public void endJournaling() throws Exception
	{
		// CHPF is only written when doing patch bundles or service bundle install
		// CHPF is handled outside of this routine because library changes depending on type of deployment

		// Put the journaling state back to what it was before deployment.
		if (!isOriginallyJournaledOHPF)
		{
			endjrnpf(targetFilLibrary, "OHPF");
		}
		if (!isOriginallyJournaledGAPF)
		{
			endjrnpf(targetFilLibrary, "GAPF");
		}
		if (!isOriginallyJournaledGBPF)
		{
			endjrnpf(targetFilLibrary, "GBPF");
		}
		if (isUnitDeployment && !isBundle)
		{
			if (!isOriginallyJournaledGDPF)
			{
				endjrnpf(targetFilLibrary, "GDPF");
			}
		}
		if (!isOriginallyJournaledGAEPF)
		{
			endjrnpf(targetFilLibrary, "GAEPF");
		}
		if (!isOriginallyJournaledGAXPF)
		{
			endjrnpf(targetFilLibrary, "GAXPF");
		}
		if (!isOriginallyJournaledGAZPF)
		{
			endjrnpf(targetFilLibrary, "GAZPF");
		}
		if (!isOriginallyJournaledAAIPF)
		{
			endjrnpf(targetFilLibrary, "AAIPF");
		}
		if (!isOriginallyJournaledACNPF)
		{
			endjrnpf(targetFilLibrary, "ACNPF");
		}
		if (!isOriginallyJournaledACOPF)
		{
			endjrnpf(targetFilLibrary, "ACOPF");
		}
		if (!isOriginallyJournaledHBXPF)
		{
			endjrnpf(targetFilLibrary, "HBXPF");
		}
	}

	private void deleteEmptyFiles() throws Exception
	{
		if (createdOHPFRecord == false)
		{
			dltf(targetFilLibrary, "OHPF", true);
		}
		if (createdGAPFRecord == false)
		{
			dltf(targetFilLibrary, "GAPF", true);
		}
		if (createdGBPFRecord == false)
		{
			dltf(targetFilLibrary, "GBPF", true);
		}
		if (createdGAEPFRecord == false)
		{
			dltf(targetFilLibrary, "GAEPF", true);
		}
		if (createdGAXPFRecord == false)
		{
			dltf(targetFilLibrary, "GAXPF", true);
		}
		if (createdGAZPFRecord == false)
		{
			dltf(targetFilLibrary, "GAZPF", true);
		}
		if (createdAAIPFRecord == false)
		{
			dltf(targetFilLibrary, "AAIPF", true);
		}
		if (createdACNPFRecord == false)
		{
			dltf(targetFilLibrary, "ACNPF", true);
		}
		if (createdACOPFRecord == false)
		{
			dltf(targetFilLibrary, "ACOPF", true);
		}
		if (createdHBXPFRecord == false)
		{
			dltf(targetFilLibrary, "HBXPF", true);
		}
	}

	private void deleteEmptyLibraries() throws Exception
	{
		if (targetLibLibraryUsed == false)
		{
			dltlib(targetLibLibrary);
		}
		if (targetFilLibraryUsed == false)
		{
			dltlib(targetFilLibrary);
		}
		if (targetInpLibraryUsed == false)
		{
			dltlib(targetInpLibrary);
		}
	}

	private void renameFiles() throws Exception
	{
		if (createdGAEPFRecord == true)
		{
			rnmobj(targetFilLibrary, "GAEPF", true);
		}
		if (createdGAXPFRecord == true)
		{
			rnmobj(targetFilLibrary, "GAXPF", true);
		}
		if (createdGAZPFRecord == true)
		{
			rnmobj(targetFilLibrary, "GAZPF", true);
		}
		if (createdAAIPFRecord == true)
		{
			rnmobj(targetFilLibrary, "AAIPF", true);
		}
		if (createdACNPFRecord == true)
		{
			rnmobj(targetFilLibrary, "ACNPF", true);
		}
		if (createdACOPFRecord == true)
		{
			rnmobj(targetFilLibrary, "ACOPF", true);
		}
		if (createdHBXPFRecord == true)
		{
			rnmobj(targetFilLibrary, "HBXPF", true);
		}
	}

	private void createInstallationProgram() throws Exception
	{
		if (targetFilLibraryUsed)
		{
			String installationProgram = release + level + upgrade;
			commandString = "CRTDUPOBJ OBJ(UTW66C) FROMLIB(*LIBL) OBJTYPE(*PGM) TOLIB(" + targetLibLibrary.trim() + ") NEWOBJ("
							+ installationProgram + ")";

			String sqlStatement = SQLToolbox.getQcmdexcString(commandString);
			try
			{
				executeStatement(session, sqlStatement);
			}
			catch (EQException e)
			{
				throw new EQException("Command failed: " + commandString, e);
			}

			targetLibLibraryUsed = true;
		}
	}

	private void createSaveFiles() throws Exception
	{
		if (targetLibLibraryUsed == true)
		{
			crtsavf(order, targetLibLibrary);
		}
		if (targetFilLibraryUsed == true)
		{
			crtsavf(order, targetFilLibrary);
		}
		if (targetInpLibraryUsed == true)
		{
			crtsavf(order, targetInpLibrary);
		}
		if (targetWrkLibraryUsed == true)
		{
			// crtsavf(order, targetWrkLibrary);
		}
	}

	private void saveLibraries() throws Exception
	{
		if (targetLibLibraryUsed == true)
		{
			savlib(targetLibLibrary, order, targetLibLibrary);
		}
		if (targetFilLibraryUsed == true)
		{
			savlib(targetFilLibrary, order, targetFilLibrary);
		}
		if (targetInpLibraryUsed == true)
		{
			savlib(targetInpLibrary, order, targetInpLibrary);
		}
		if (targetWrkLibraryUsed == true)
		{
			// savlib(targetWrkLibrary, order, targetWrkLibrary);
		}
	}

	/**
	 * Start the process of making a release
	 * 
	 * @throws Exception
	 */
	public void setupReleaseEnvironment() throws Exception
	{
		createReleaseLibraries();
		createDuplicateObjects();

	}

	/**
	 * Finish the process of making a release
	 * 
	 * @throws Exception
	 */
	public void completeRelease() throws Exception
	{
		// It is VERY important that the following methods are only used if NOT deploying to a unit
		// as iSeries objects will be DELETED.
		if (!isUnitDeployment)
		{
			populateCHPF();
			// Misys is making the release. A bank will only make Patch Releases because they do not have DI
			if (isPatchLibraries || isEnhancementLibraries)
			{
				encryptCHPF();
			}
			renameFiles();
			deleteEmptyFiles();
			createInstallationProgram();
			deleteEmptyLibraries();

			if (isPatchRelease)
			{
				createInstallableOrder();
			}
		}

	}

	/**
	 * Complete the process of making an installable release
	 * 
	 * @throws Exception
	 */
	private void createInstallableOrder() throws Exception
	{
		populateOrderFiles();
		createSaveFiles();
		saveLibraries();
		deleteReleaseLibraries();
	}

	private void populateOrderFiles() throws Exception
	{
		dltf("QTEMP", "INORHPF", false);
		dltf("QTEMP", "INORDPF", false);

		String tempLibrary = "TEMP" + release;
		String release = order.substring(1);
		String orderLibLibrary = "";
		String orderFilLibrary = "";
		String orderInpLibrary = "";

		crtlib(tempLibrary, releaseDescription);

		crtdupobj("INORHPF", unit.getKWRKLibrary(), tempLibrary, false);
		crtdupobj("INORDPF", unit.getKWRKLibrary(), tempLibrary, false);

		strjrnpf(tempLibrary, "INORHPF");
		strjrnpf(tempLibrary, "INORDPF");

		String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance(); // today
		Integer today = new Integer(sdf.format(c1.getTime()));

		// Add a record to the INORHPF Order Header file
		INORHRecordDataModel inorhRecordDataModel = new INORHRecordDataModel(release, "", today.intValue(), 0, "220", "", "EM", "",
						"SN", "N", "", releaseDescription, "", "", "release", "CSER", "N", 0, 0);
		inorhRecordDataModel.setLibrary(tempLibrary);
		IINORHRecordDao inorhRecordDao = daoFactory.getINORHDao(session, inorhRecordDataModel);
		inorhRecordDao.insertOrUpdateRecord();

		INORDRecordDataModel inordRecordDataModel;
		IINORDRecordDao inordRecordDao;

		int installSequence = 0;

		// Installation LIB Library Record 1
		// Add a record to the INORDPF Order Detail file
		installSequence = installSequence + 1;
		inordRecordDataModel = new INORDRecordDataModel(release, "INSTEQ3", "I", "AS400", installSequence, "N", "LIBINSMEQ3",
						"Installation Software", "AS400", "L", 0, 0, 0, "", "", "", "", "");
		inordRecordDataModel.setLibrary(tempLibrary);
		inordRecordDao = daoFactory.getINORDDao(session, inordRecordDataModel);
		inordRecordDao.insertOrUpdateRecord();

		// Installation LIB Library Record 2
		// Add a record to the INORDPF Order Detail file
		installSequence = installSequence + 1;
		inordRecordDataModel = new INORDRecordDataModel(release, "INSTEQ3", "I", "AS400", installSequence, "N", "LIBINSTEQ3",
						"Installation Software", "AS400", "L", 0, 0, 0, "", "", "", "", "");
		inordRecordDataModel.setLibrary(tempLibrary);
		inordRecordDao = daoFactory.getINORDDao(session, inordRecordDataModel);
		inordRecordDao.insertOrUpdateRecord();

		// Installation LIB Library Record 3
		// Add a record to the INORDPF Order Detail file
		installSequence = installSequence + 1;
		inordRecordDataModel = new INORDRecordDataModel(release, "INSTEQ3", "I", "AS400", installSequence, "N", "@SUMMARY",
						"Installation Software", "AS400", "", 0, 0, 0, "Y", "LIBINSTEQ3", "", "", "");
		inordRecordDataModel.setLibrary(tempLibrary);
		inordRecordDao = daoFactory.getINORDDao(session, inordRecordDataModel);
		inordRecordDao.insertOrUpdateRecord();

		// LIB Library Record 1
		// Add a record to the INORDPF Order Detail file
		if (targetLibLibraryUsed == true)
		{
			installSequence = installSequence + 1;
			inordRecordDataModel = new INORDRecordDataModel(release, release, "P", "AS400", installSequence, "N", targetLibLibrary,
							"Library Objects", "AS400", "L", 0, 0, 0, "", "", "", "", "");
			inordRecordDataModel.setLibrary(tempLibrary);
			inordRecordDao = daoFactory.getINORDDao(session, inordRecordDataModel);
			inordRecordDao.insertOrUpdateRecord();
			orderLibLibrary = targetLibLibrary;
		}

		// FIL Library Record 1
		// Add a record to the INORDPF Order Detail file
		if (targetFilLibraryUsed == true)
		{
			installSequence = installSequence + 1;
			inordRecordDataModel = new INORDRecordDataModel(release, release, "P", "AS400", installSequence, "N", targetFilLibrary,
							"Library Objects", "AS400", "L", 0, 0, 0, "", "", "", "", "");
			inordRecordDataModel.setLibrary(tempLibrary);
			inordRecordDao = daoFactory.getINORDDao(session, inordRecordDataModel);
			inordRecordDao.insertOrUpdateRecord();
			orderFilLibrary = targetFilLibrary;
		}

		// INP Library Record 1
		// Add a record to the INORDPF Order Detail file
		if (targetInpLibraryUsed == true)
		{
			installSequence = installSequence + 1;
			inordRecordDataModel = new INORDRecordDataModel(release, release, "P", "AS400", installSequence, "N", targetInpLibrary,
							"Library Objects", "AS400", "L", 0, 0, 0, "", "", "", "", "");
			inordRecordDataModel.setLibrary(tempLibrary);
			inordRecordDao = daoFactory.getINORDDao(session, inordRecordDataModel);
			inordRecordDao.insertOrUpdateRecord();
			orderInpLibrary = targetInpLibrary;
		}

		// release Summary Record
		// Add a record to the INORDPF Order Detail file
		installSequence = installSequence + 1;
		inordRecordDataModel = new INORDRecordDataModel(release, release, "P", "AS400", installSequence, "N", "@SUMMARY",
						"Summary", "AS400", "", 0, 0, 0, "Y", orderLibLibrary, orderFilLibrary, "", orderInpLibrary);
		inordRecordDataModel.setLibrary(tempLibrary);
		inordRecordDao = daoFactory.getINORDDao(session, inordRecordDataModel);
		inordRecordDao.insertOrUpdateRecord();

		session.commitTransaction();

		endjrnpf(tempLibrary, "INORHPF");
		endjrnpf(tempLibrary, "INORDPF");

		crtdupobj("INORHPF", tempLibrary, "QTEMP", true);
		crtdupobj("INORDPF", tempLibrary, "QTEMP", true);

		crtlib(order, releaseDescription);

		crtsavf(order, "EQUATION");
		String[] objects = { "INORHPF", "INORDPF" };
		savobj("QTEMP", objects, order, "EQUATION");

		dltlib(tempLibrary);
	}

	private void populateCHPF() throws Exception
	{

		String targetLibrary = null;
		if (isEnhancementLibraries)
		{
			targetLibrary = targetFilLibrary;
			targetFilLibraryUsed = true;
		}
		else
		{
			targetLibrary = targetLibLibrary;
			targetLibLibraryUsed = true;
		}
		String unitFilLibrary = unit.getKFILLibrary();
		crtdupobj("CHPF", unitFilLibrary, targetLibrary, false);
		strjrnpf(targetLibrary, "CHPF");
		// Add a record to the CHPF file
		CHRecordDataModel chRecordDataModel = new CHRecordDataModel(release, releaseDescription, "", "N", release, 0, 0, level,
						upgrade);
		chRecordDataModel.setLibrary(targetLibrary);
		ICHRecordDao chRecordDao = daoFactory.getCHDao(session, chRecordDataModel);
		chRecordDao.insertOrUpdateRecord();
		session.commitTransaction();
		endjrnpf(targetLibrary, "CHPF");
	}
	/**
	 * Write a CHPF record that records installation of Service Bundle
	 * 
	 * @param release
	 * @param releaseDescription
	 * @param level
	 * @param upgrade
	 * @throws Exception
	 */
	public void populateCHPFForBundle(String release, String releaseDescription, String level, String upgrade) throws Exception
	{

		String targetLibrary = session.getUnit().getKFILLibrary();

		boolean isOriginallyJournaledCHPF = isFileJournaled(targetFilLibrary, "CHPF");
		if (!isOriginallyJournaledCHPF)
		{
			strjrnpf(targetFilLibrary, "CHPF");
		}
		// Add a record to the CHPF file
		int date = Toolbox.getDateDBFormat(Calendar.getInstance());
		// Time is only 4 digits
		int time = Toolbox.getTimeDBFormat(Calendar.getInstance()) / 100;
		CHRecordDataModel chRecordDataModel = new CHRecordDataModel(release, releaseDescription, "", "N", EQ_BUNDLE, date, time,
						level, upgrade);
		chRecordDataModel.setLibrary(targetLibrary);
		ICHRecordDao chRecordDao = daoFactory.getCHDao(session, chRecordDataModel);
		chRecordDao.insertOrUpdateRecord();
		session.commitTransaction();
		try
		{
			if (!isOriginallyJournaledCHPF)
			{
				endjrnpf(targetFilLibrary, "CHPF");
			}
		}
		catch (Exception e)
		{
			// Don't make this a fatal error
			LOG.info(targetLibrary + "/CHPF remains journalled.");
		}
	}
	private void encryptCHPF() throws Exception
	{
		commandString = "ADDLIBLE LIB(EQUTLLIB) POSITION(*LAST)";
		command.run(commandString);
		if (isEnhancementLibraries)
		{
			commandString = "EQUTLLIB/UTU23C LIB(" + targetFilLibrary + ")";
		}
		else
		{
			commandString = "EQUTLLIB/UTU23C LIB(" + targetLibLibrary + ")";
		}
		if (command.run(commandString) == false)
		{
			throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
		}
	}

	private boolean chkobj(String library, String file, String type) throws Exception
	{
		commandString = "CHKOBJ OBJ(" + library.trim() + "/" + file.trim() + ") OBJTYPE(" + type.trim() + ")";
		return command.run(commandString);
	}

	private void crtlib(String library, String description) throws Exception
	{
		description = description.replaceAll("'", "''");
		commandString = "CRTLIB LIB(" + library.trim() + ") TEXT('" + description + "')";
		if (command.run(commandString) == false)
		{
			throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
		}
	}

	private void dltlib(String library) throws Exception
	{
		commandString = "DLTLIB LIB(" + library.trim() + ")";
		if (command.run(commandString) == false)
		{
			throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
		}
	}

	private void crtdupobj(String object, String sourceLibrary, String targetLibrary, boolean data) throws Exception
	{
		commandString = "CRTDUPOBJ OBJ(" + object.trim() + ") FROMLIB(" + sourceLibrary.trim() + ") OBJTYPE(*FILE) TOLIB("
						+ targetLibrary.trim() + ")";
		if (data == false)
		{
			commandString = commandString + " DATA(*NO)";
		}
		else
		{
			commandString = commandString + " DATA(*YES)";
		}
		if (command.run(commandString) == false)
		{
			throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
		}
	}

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

	private void rnmobj(String library, String file, boolean errorWhenNotFound) throws Exception
	{
		commandString = "RNMOBJ OBJ(" + library.trim() + "/" + file.trim() + ") OBJTYPE(*FILE) NEWOBJ(#" + file.trim() + ")";
		boolean result = command.run(commandString);
		if (result == false && errorWhenNotFound == true)
		{
			if (command.run(commandString) == false)
			{
				throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
			}
		}
	}

	private void strjrnpf(String library, String file) throws Exception
	{
		// Note that the standard non-default options when starting journalling are to journal both images (before and after)
		// and to omit file open/close journal entries
		String journalFile = session.getUnit().getNIJournalFullPath();
		String command = "STRJRNPF FILE(" + rtvFullPath(library, file) + ")" + " JRN(" + journalFile
						+ ") IMAGES(*BOTH) OMTJRNE(*OPNCLO)";
		try
		{
			executeStatement(session, SQLToolbox.getQcmdexcString(command));
		}
		catch (EQException e)
		{
			String message = Toolbox.getExceptionMessage(e);
			if ((message.indexOf("CPF7030")) == -1)
			{
				throw e;
			}
		}
	}

	private void endjrnpf(String library, String file) throws Exception
	{
		String journalFile = session.getUnit().getNIJournalFullPath();
		String command = "ENDJRNPF FILE(" + rtvFullPath(library, file) + ")" + " JRN(" + journalFile + ")";
		String sqlStatement = SQLToolbox.getQcmdexcString(command);
		try
		{
			executeStatement(session, sqlStatement);
		}
		catch (EQException e)
		{
			String message = Toolbox.getExceptionMessage(e);
			if ((message.indexOf("CPF7032")) == -1)
			{
				throw e;
			}

		}
	}
	/**
	 * Return the file name
	 */
	private String rtvFullPath(String library, String eqFileName)
	{
		if (library.equals(""))
		{
			return eqFileName;
		}
		else
		{
			return library + "/" + eqFileName;
		}
	}
	/**
	 * Execute a query
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param sqlStatement
	 *            - the SQL statement
	 * 
	 * @throws EQException
	 */
	public void executeStatement(EquationStandardSession session, String sqlStatement) throws EQException
	{
		Statement statement = null;
		try
		{
			Connection connection = session.getConnection();
			statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			statement.execute(sqlStatement);
		}
		catch (SQLException e)
		{
			throw new EQException("EQFile: executeStatement() Failed: " + Toolbox.getExceptionMessage(e), e);
		}
		finally
		{
			SQLToolbox.close(statement);
		}
	}
	private void crtsavf(String library, String file) throws Exception
	{
		commandString = "CRTSAVF FILE(" + library.trim() + "/" + file.trim() + ")";
		if (command.run(commandString) == false)
		{
			throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
		}
	}

	private void savlib(String library, String saveFileLibrary, String saveFileFile) throws Exception
	{
		commandString = "SAVLIB LIB(" + library.trim() + ") DEV(*SAVF) SAVF(" + saveFileLibrary.trim() + "/" + saveFileFile
						+ ") DTACPR(*HIGH)";
		if (command.run(commandString) == false)
		{
			throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
		}
	}

	private void savobj(String library, String[] objects, String saveFileLibrary, String saveFileFile) throws Exception
	{
		commandString = "SAVOBJ OBJ(";
		for (int i = 0; i < objects.length; i++)
		{
			commandString = commandString + objects[i];
			if (i < (objects.length - 1))
			{
				commandString = commandString + " ";
			}
		}
		commandString = commandString + ") LIB(" + library.trim() + ") DEV(*SAVF) SAVF(" + saveFileLibrary.trim() + "/"
						+ saveFileFile + ") DTACPR(*HIGH)";
		if (command.run(commandString) == false)
		{
			throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
		}
	}

	/**
	 * Returns target LIB library - this may be unit library or a release library
	 * 
	 * @return target LIB library - this may be unit library or a release library
	 */
	public String getTargetLibLibrary()
	{
		return targetLibLibrary;
	}

	/**
	 * Returns target FIL library - this may be unit library or a release library
	 * 
	 * @return target FIL library - this may be unit library or a release library
	 */
	public String getTargetFilLibrary()
	{
		return targetFilLibrary;
	}

	/**
	 * Returns target INP library - this may be unit library or a release library
	 * 
	 * @return target INP library - this may be unit library or a release library
	 */
	public String getTargetInpLibrary()
	{
		return targetInpLibrary;
	}

	/**
	 * Returns target WRK library - this may be unit library or a release library
	 * 
	 * @return target WRK library - this may be unit library or a release library
	 */
	public String getTargetWrkLibrary()
	{
		return targetWrkLibrary;
	}

	/**
	 * Returns the collection of objects updated in the whole deployment process
	 * 
	 * @return the collection of objects updated in the whole deployment process
	 */
	public List<Object> getUpdatedCollection()
	{
		if (updatedCollection == null)
		{
			updatedCollection = new ArrayList<Object>();
		}
		return updatedCollection;
	}

	/**
	 * Deploy link service bean
	 * 
	 * @param linkService
	 *            - the link service to be deployed
	 */

	public synchronized void deployLinkage(LinkService linkService) throws Exception
	{
		createdGAXPFRecord = true;
		targetFilLibraryUsed = true;
		// create Equation files
		updateEquation(linkService);
	}

	/**
	 * Create the journal of the function
	 */
	private void updateEquation(LinkService linkService) throws Exception
	{
		// Get the layout/function of the primary option
		Layout primaryLayout = XMLToolbox.getXMLToolbox().getLayout(session, linkService.getPrimaryId(), true);
		Function primaryFunction = XMLToolbox.getXMLToolbox().getFunction(session, primaryLayout.getServiceId(), true);

		// Default to the primary function
		ServiceLinkage serviceLinkage = new ServiceLinkage(linkService);
		Function linkedFunction = serviceLinkage.rtvLinkedFunction(session, true);
		if (linkedFunction == null)
		{
			throw new EQException(serviceLinkage.getMessageContainer().getFirstErrorMessage().getText());
		}

		// create the journal file
		JournalFile journalFile = createJournal(linkedFunction, linkService.getId());

		// create the OH for the journal file
		createOHRecord(journalFile.getFileName(), "INP", "PF");

		// update the GAX
		GAXRecordDataModel gaxRecord = createGAXRecord(linkService.getId(), linkService);

		// create GA
		createGARecord(linkService.getId(), linkService.getLabel(), primaryFunction);

		// create GB
		createGBRecord(linkService.getId(), linkService.getLabel(), primaryFunction, primaryLayout);

		// create GD record
		if (isUnitDeployment && !isBundle)
		{
			createGDRecord(session.getEquationUserId(), linkService.getId());
		}

		// create AAI
		createAAIRecord(linkService.getId(), linkService.getLabel(), primaryFunction);
		getUpdatedCollection().add(gaxRecord);
	}

	/**
	 * Create journal processing
	 * 
	 * @param function
	 *            - the Function
	 * @param optionId
	 *            - the option Id to override the journal file name
	 * 
	 * @return the journal record
	 * 
	 * @throws Exception
	 */
	private JournalFile createJournal(Function function, String optionId) throws Exception
	{
		// create the journal file
		JournalFile journalFile = new JournalFile(function, targetInpLibrary);

		// override journal name?
		if (optionId != null)
		{
			journalFile.setFileName(JournalFile.getJournalName(optionId));
		}

		// delete the journal file if existing
		if (journalFile.isFileExists(session.getConnection()))
		{
			journalFile.dropFile(session.getConnection());
		}
		journalFile.writeFile(session, targetInpLibrary);
		targetInpLibraryUsed = true;

		// start the journaling for the journal file
		JournalRecord record = new JournalRecord(function);

		// override journal name?
		if (optionId != null)
		{
			record.setEqFileName(JournalFile.getJournalName(optionId));
		}

		// start/end journaling
		if (isUnitDeployment)
		{
			String unitName = session.getUnitId();
			record.startJournal(session, unitName);
			record.endJournal(session, unitName);
			record.startJournal(session, unitName);
		}

		return journalFile;
	}

	/**
	 * Create OH record
	 * 
	 * @param fileName
	 *            - the file name
	 * @param fileLocation
	 *            - the file location - INP or FIL
	 * @param fileType
	 *            - the file type - PF or LF
	 * 
	 * @return the OH record
	 * 
	 * @throws Exception
	 */
	private OHRecordDataModel createOHRecord(String fileName, String fileLocation, String fileType) throws Exception
	{
		// Add a record to the OH file for the journal file
		OHRecordDataModel oHRecordDataModel = new OHRecordDataModel(fileName, fileLocation, fileType);
		oHRecordDataModel.setLibrary(targetFilLibrary);
		IOHRecordDao ohRecordDao = daoFactory.getOHDao(session, oHRecordDataModel);
		if (isUnitDeployment)
		{
			strjrnpf(targetFilLibrary, "OHPF");
		}
		ohRecordDao.insertOrUpdateRecord();
		if (isUnitDeployment)
		{
			session.commitTransaction();
			endjrnpf(targetFilLibrary, "OHPF");
		}
		createdOHPFRecord = true;
		targetFilLibraryUsed = true;

		// return the OH
		return oHRecordDataModel;
	}

	/**
	 * Create GAX record
	 * 
	 * @param optionId
	 *            - the option id
	 * @param beanModel
	 *            - the bean
	 * 
	 * @return the GAX record
	 * 
	 * @throws Exception
	 */
	private GAXRecordDataModel createGAXRecord(String optionId, Object beanModel) throws Exception
	{
		// write to GAXPF
		EqBeanFactory beanFactory = EqBeanFactory.getEqBeanFactory();
		String functionXML = beanFactory.serialiseBeanAsXML(beanModel);
		GAXRecordDataModel gaxRecord = XMLToolbox.getXMLToolbox().writeDefinitionXMLtoDB(session, targetFilLibrary,
						GAXRecordDataModel.LINKAGE_CODE, optionId,
						Toolbox.formatDate(Calendar.getInstance(), Toolbox.TIMESTAMP_FORMAT), functionXML);
		createdGAXPFRecord = true;
		targetFilLibraryUsed = true;

		return gaxRecord;
	}

	/**
	 * Create GA record
	 * 
	 * @param optionId
	 *            - the option id
	 * @param label
	 *            - the label
	 * @param function
	 *            - the Function bean
	 * 
	 * @return the GA record
	 */
	private GARecordDataModel createGARecord(String optionId, String label, Function function)
	{
		// Add a record to the GA file
		GARecordDataModel gaRecordDataModel = new GARecordDataModel(optionId,
						EquationStandardTransaction.EDF_SHORT_ROOT + optionId, label, function.getApplication());
		gaRecordDataModel.setLibrary(targetFilLibrary);
		gaRecordDataModel.setExtendedInput(function.isExtendedBusinessHoursAllowed() ? "Y" : "N");
		IGARecordDao gaRecordDao = daoFactory.getGADao(session, gaRecordDataModel);
		gaRecordDao.insertOrUpdateRecord();
		getUpdatedCollection().add(gaRecordDao.getGARecord());
		createdGAPFRecord = true;
		targetFilLibraryUsed = true;

		return gaRecordDataModel;
	}

	/**
	 * Create the GB record
	 * 
	 * @param optionId
	 *            - the option id
	 * @param label
	 *            - the label
	 * @param function
	 *            - the Function bean
	 * @param layout
	 *            - the Layout bean
	 * 
	 * @return the GB record
	 */
	private GBRecordDataModel createGBRecord(String optionId, String label, Function function, Layout layout)
	{
		// Add a record to the GB file
		GBRecordDataModel gbRecord = new GBRecordDataModel(optionId, EquationStandardTransaction.EDF_SHORT_ROOT + optionId, label,
						function.getApplication(), optionId);
		gbRecord.setLibrary(targetFilLibrary);
		gbRecord.setGbwm1("N");
		gbRecord.setMandatoryNextReq(layout.getNextRequest());
		gbRecord.setUserFuncKey1(layout.getCommandKeyOption1());
		gbRecord.setUserFuncKey2(layout.getCommandKeyOption2());
		gbRecord.setUserFuncKey3(layout.getCommandKeyOption3());
		gbRecord.setUserFuncKey4(layout.getCommandKeyOption4());
		IGBRecordDao gbRecordDao = daoFactory.getGBDao(session, gbRecord);
		gbRecordDao.insertOrUpdateRecord();
		getUpdatedCollection().add(gbRecordDao.getGBRecord());
		createdGBPFRecord = true;
		targetFilLibraryUsed = true;

		return gbRecord;
	}

	/**
	 * Create GD record
	 * 
	 * @param userId
	 *            - the user id
	 * @param optionId
	 *            - the option id
	 * @return the GD record
	 */
	private GDRecordDataModel createGDRecord(String userId, String optionId)
	{
		GDRecordDataModel gdRecordDataModel = new GDRecordDataModel(userId, optionId);
		gdRecordDataModel.setLibrary(targetFilLibrary);
		IGDRecordDao gdRecordDao = daoFactory.getGDDao(session, gdRecordDataModel);

		gdRecordDao.insertOrUpdateRecord();

		return gdRecordDataModel;
	}

	/**
	 * Create AAI record
	 * 
	 * @param function
	 *            - the Function bean
	 * 
	 * @return the AAI record
	 * 
	 * @throws Exception
	 */
	private AAIRecordDataModel createAAIRecord(String optionId, String label, Function function) throws Exception
	{
		// Add/Update/Delete AAI record
		AAIRecordDataModel aaiRecord = new AAIRecordDataModel(optionId);
		aaiRecord.setLibrary(targetFilLibrary);
		IAAIRecordDao aaiRecordDao = daoFactory.getAAIDao(session, aaiRecord);
		if (function.checkEfcEnabled())
		{
			populateAAIRecord(aaiRecord, function);
			aaiRecord.setDescription(label);
			aaiRecordDao.insertOrUpdateRecord();
			createdAAIPFRecord = true;
			targetFilLibraryUsed = true;
		}
		else
		{
			if (isUnitDeployment)
			{
				aaiRecordDao.deleteRecord();
			}
			else
			{
				aaiRecord.setEvent(function.getId());
				aaiRecord.setDescription("*DELETE*");
				aaiRecordDao.insertOrUpdateRecord();
				createdAAIPFRecord = true;
				targetFilLibraryUsed = true;
			}
		}

		return aaiRecord;
	}
	/**
	 * Return true if file is currently journaled
	 * 
	 * @param library
	 * @param fileName
	 * @return true if file is currently journaled
	 * @throws Exception
	 */
	private boolean isFileJournaled(String library, String fileName) throws Exception
	{

		FileAttributes objectDescription = new FileAttributes(as400, "/QSYS.LIB/" + library + ".LIB/" + fileName + ".FILE", true);
		return objectDescription.isJournalingStatus();

	}
	/**
	 * Update the unit records cache given a session and a list of changed objects
	 * 
	 * @throws EQException
	 */
	public void updateUnitCache() throws EQException
	{
		List<Object> changedObjects = getUpdatedCollection();
		for (int i = 0; i < changedObjects.size(); i++)
		{
			if (changedObjects.get(i) instanceof GBRecordDataModel)
			{
				GBRecordDataModel item = (GBRecordDataModel) changedObjects.get(i);
				session.getUnit().getRecords().reloadOption(item.getOptionId());
			}
		}
	}

}