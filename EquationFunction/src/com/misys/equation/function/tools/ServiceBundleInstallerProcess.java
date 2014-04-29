package com.misys.equation.function.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.Map.Entry;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400ConnectionPool;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.CommandCall;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.FileUtils;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.LinkService;
import com.misys.equation.function.beans.ServiceUserExit;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.beans.Translation;
import com.misys.equation.function.beans.UserExit;
import com.misys.equation.function.runtime.FunctionBankFusion;

/**
 * Class to manage the Equation installation process for Equation artefacts in a BFEQ Service Bundle. This class is invoked from
 * ServiceBundleInstaller. This class uses parameters passed from BankFusion PnP FusionInstaller. There will be an iteration over
 * folders installed by BankFusion PnP. Once an Equation artefact is located it will be installed using the DeploymentToolbox. This
 * class is similar to ServiceExportWizard the main differences being the folder structure that is iterated.
 */
public class ServiceBundleInstallerProcess
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceBundleInstallerProcess.java 17426 2013-10-11 16:32:28Z whittap1 $";
	/** Logger instance */
	private static EquationLogger LOG = new EquationLogger(ServiceBundleInstallerProcess.class);

	private static final String STATUS_SUCCESS = "Success";
	private static final String STATUS_FAILURE = "Failure";

	public static final String ACTION_BUNDLE_INSTALL = "BUNDLE_INSTALL";
	public static final String ACTION_GRANT_OBJECT_AUTHORITY = "GRTOBJAUT";

	/** The file name used by all NEW service definitions has format - XXX.eqf - 25th Feb 2011 Alex Tucker */
	public static final String SERVICE_FILE_NAME_SUFFIX = ".eqf";
	/** The extension used by all service definitions - "eqf" */
	public static final String SERVICE_FILE_EXTENSION = "eqf";

	/** The file extension used by all layout definitions */
	public static final String LAYOUT_FILE_NAME_SUFFIX = ".eql";
	/** The extension used by all layout definitions - "eql" */
	public static final String LAYOUT_FILE_EXTENSION = "eql";

	/** The extension used by all language definitions - "eqt" */
	public static final String LANGUAGE_FILE_EXTENSION = "eqt";
	public static final String MICROFLOW_FILE_EXTENSION = "bfg";
	/** The extension used by all service user exit definitions - "equ" */
	public static final String SERVICE_USER_EXIT_FILE_EXTENSION = "equ";
	/** The extension used by all user exit definitions - "eqx" */
	public static final String USER_EXIT_FILE_EXTENSION = "eqx";
	/** The extension used by all prompt validate definitions - "eqv" */
	public static final String PROMPT_VALIDATE_FILE_EXTENSION = "eqv";
	/** The extension used by all translation definitions - "eqt" */
	public static final String TRANSLATION_FILE_EXTENSION = "eqt";
	/** The extension used by all translation definitions - "eqt" */
	public static final String TRANSLATION_FILE_EXTENSION_SUFFIX = ".eqt";

	/** The extension used by services linkage - "eqc" */
	public static final String LINKAGE_FILE_EXTENSION = "eqc";

	/** The extension used for microflows */
	public static final String MICROFLOW_SUFFIX = ".bfj";
	public static final String JAVA_SUFFIX = ".java";
	public static final String CLASS_SUFFIX = ".class";
	public static final String TEXT_SUFFIX = ".txt";

	private static final String SERVICE_LABEL = "Service";
	private static final String LAYOUT_LABEL = "Layout";
	private static final String TRANSLATION_LABEL = "Translation";
	private static final String USER_EXIT_LABEL = "User Exit";
	private static final String PROMPT_VALIDATE_LABEL = "Prompt validates";
	private static final String LINKAGE_LABEL = "Service linkage";

	private static final String INSTALLER_FILE_PREFIX = "equationInstaller_";
	private static final String PROPERTIES_SUFFIX = ".properties";

	private static final String SERVICES_FOLDER_SEGMENT = "/SERVICES/";
	private static final String CONF_FOLDER_SEGMENT = "/conf/";
	private static final String MISC_FOLDER_SEGMENT = "/misc";
	private static final String MISC_SRC_FOLDER_SEGMENT = "misc/src/";
	private static final String MISC_CLASSES_FOLDER_SEGMENT = "misc/classes/";
	private static final String ARTEFACT_SRC_FOLDER_SEGMENT = "/artefacts_source";

	private static final String JAR_AUDIT_FILE = "/eqAuditTemplate.txt";
	private static final String PATH_SEPARATOR = "/";
	private static final String FILE_NAME_SEPARATOR = "_";

	private static final String DB2400_URL_PREFIX = "jdbc:as400://";
	// bundle names are in the pattern applicationId_EquationServices_bundleId.bfj
	private static final String EQUATION_SERIVICES_SUFFIX = "_EquationServices";
	private static final String EQUATION_SERVICES_PREFIX = "EquationServices_";

	private String eqAction;
	private static Map<String, String> customProperties;
	private Properties equationInstallerProperties;

	private String servicesLocation;
	private String bundleInstallationLocation;
	private String release;
	private String message = "";

	private static File installationFolder;
	private static File artefactSourceFolder;
	private static File miscFolder;
	private static ArrayList<File> artefacts = new ArrayList<File>();
	private static ArrayList<String> artefactDescriptions = new ArrayList<String>();
	private static Set<String> deployedTranslation = new HashSet<String>();

	public ServiceBundleInstallerProcess(Map<String, String> customProperties) throws Exception
	{
		this.customProperties = customProperties;
	}
	/**
	 * Process install request
	 * 
	 * @throws Exception
	 */
	public void process() throws Exception
	{
		try
		{

			logContextValues();

			String bundleLocation = customProperties.get("BUNDLE_LOCATION_BFEQ").trim().replace("\\", "/");
			// Determine application name from the bundleLocation
			int index = bundleLocation.indexOf(EQUATION_SERIVICES_SUFFIX);
			if (index < 0)
			{
				throw new Exception("Bundle is not an EquationServices bundle");
			}
			String applicationName = bundleLocation.substring(0, index);
			index = applicationName.lastIndexOf("/");
			if (index < 0)
			{
				throw new Exception("Bundle is not an EquationServices bundle");
			}
			applicationName = applicationName.substring(index + 1);
			// Deterimine the release/bundle from the bundle location
			index = bundleLocation.lastIndexOf(EQUATION_SERVICES_PREFIX);
			if (index < 0)
			{
				throw new Exception("Bundle is not an EquationServices bundle");
			}
			String temp = bundleLocation.substring(index + 17);
			index = temp.lastIndexOf(MICROFLOW_SUFFIX);
			release = temp.substring(0, index);

			servicesLocation = customProperties.get("INSTALLATION_ROOT_LOCATION_BFEQ").replace("\\", "/") + PATH_SEPARATOR
							+ applicationName + SERVICES_FOLDER_SEGMENT;

			bundleInstallationLocation = servicesLocation + release;
			installationFolder = new File(bundleInstallationLocation);

			String installerPropertiesName = new StringBuffer(INSTALLER_FILE_PREFIX).append(release).append(PROPERTIES_SUFFIX)
							.toString();
			String equationInstallerLocation = new StringBuilder(servicesLocation).append(release).append(CONF_FOLDER_SEGMENT)
							.append(installerPropertiesName).toString();
			equationInstallerProperties = getEquationInstallerProperties(equationInstallerLocation);

			eqAction = customProperties.get("EQ_ACTION").trim();
			if (eqAction.equals(ACTION_BUNDLE_INSTALL))
			{
				install();
			}
			else if (eqAction.equals(ACTION_GRANT_OBJECT_AUTHORITY))
			{
				grantObjectAuthority();
			}
			else
				throw new Exception("Custom property EQ_ACTION is not valid.");
		}
		catch (Exception e)
		{
			// Log here just in case called does not do enough logging.
			LOG.error(e);
			throw e;
		}

	}

	/**
	 * Install bundle
	 * 
	 * @throws Exception
	 */
	private void install() throws Exception
	{
		LOG.info("---------- Equation Service Installation Start s----------");

		String status = STATUS_SUCCESS;
		String sessionIdentifier = null;
		EquationStandardSession session = null;
		DeploymentToolbox deploymentToolbox = null;
		boolean journalingStarted = false;

		String releaseDescription = equationInstallerProperties.getProperty("RELEASE_DESCRIPTION").trim();
		boolean includeMenuOptions = new Boolean(equationInstallerProperties.getProperty("INCLUDE_MENU_OPTIONS").trim())
						.booleanValue();
		String bundleOwner = equationInstallerProperties.getProperty("BUNDLE_OWNER").trim();

		String dbUrl = customProperties.get("DB_URL_BFEQ").trim();
		String systemId = dbUrl.replace(DB2400_URL_PREFIX, "").toUpperCase().trim();
		String userId = customProperties.get("DB_USER_ID_BFEQ").trim();
		String password = customProperties.get("DB_PASSWORD_BFEQ").trim();

		String unitId = customProperties.get("UNIT").toUpperCase().trim();
		try
		{
			sessionIdentifier = EquationCommonContext.getContext().getEqSession(systemId, unitId, userId, password, null, "",
							EquationCommonContext.PASSWORD_TYPE_TEXT_PLAIN, EquationCommonContext.SESSION_OTHER_MODE, null);
			session = EquationCommonContext.getContext().getEquationUser(sessionIdentifier).getSession();
			boolean isMisysBundle = bundleOwner.equals("Misys");
			deploymentToolbox = new DeploymentToolbox(session, true, false, false, false, release, "", "", releaseDescription,
							includeMenuOptions, isMisysBundle);

			artefactSourceFolder = new File(installationFolder + ARTEFACT_SRC_FOLDER_SEGMENT);
			miscFolder = new File(installationFolder + MISC_FOLDER_SEGMENT);

			ServiceBundleInstallerProcess serviceBundleInstallerProcess = new ServiceBundleInstallerProcess(customProperties);
			deploymentToolbox.startJournaling();
			journalingStarted = true;
			// Install resusable text first before other artefacts
			serviceBundleInstallerProcess.install(installationFolder, true, session, deploymentToolbox);
			serviceBundleInstallerProcess.install(installationFolder, false, session, deploymentToolbox);
			// Cleanup connection pool connections to try and remove locks before writing CHPF.
			if (session != null)
			{
				// Close the connection pool
				EquationCommonContext.getContext().getEquationUnit(sessionIdentifier).close();
			}
			// Note that commit is done after CHPF is written
			deploymentToolbox.populateCHPFForBundle(release, releaseDescription, "", "");
		}
		catch (Exception e)
		{
			status = STATUS_FAILURE;
			if (session != null)
			{
				session.rollbackTransactions();
				if (journalingStarted)
				{ // This is to put files back in their original state
					deploymentToolbox.endJournaling();
				}
			}
			LOG.error(e);
			message = e.getMessage();

		}
		finally
		{
			// Cleanup connections
			if (session != null)
			{
				if (journalingStarted)
				{ // This is to put files back in their original state
					deploymentToolbox.endJournaling();
				}
				// Update unit records cache for GB records as Translation text needs this to be kept up to date
				deploymentToolbox.updateUnitCache();
				// Close the connection
				EquationCommonContext.getContext().logOffSession(sessionIdentifier);
			}
			// Delete Install folders from folder system
			FileUtils.deleteDir(installationFolder);
			// Write Audit file in folder system
			createBundleInstallAuditFile(unitId, release, status, message);

		}
		LOG.info("---------- Equation Service Installation End ----------");

	}

	/**
	 * Installs all bundle items
	 * 
	 * IF YOU CHANGE THIS METHOD ALSO CHECK Service Export Wizard class, BFBundlerToolbox class and bundleDefinition.xml.
	 * 
	 * @throws Exception
	 * 
	 */
	private void install(File file, boolean installReusableText, EquationStandardSession session,
					DeploymentToolbox deploymentToolbox) throws Exception
	{

		// this will hold lists of deployed translation to avoid deploying the same eqt file twice (for service and layout)
		if (file.isDirectory())
		{
			// First remove all existing Jars:
			for (File childFile : file.listFiles())
			{

				install(childFile, installReusableText, session, deploymentToolbox);
			}
		}
		else
		{
			if (file.getName().endsWith(Function.SERVICE_FILE_NAME_SUFFIX) && installReusableText == false)
			{
				LOG.info("Installing Resource " + file.getName());

				Function function = (Function) EqBeanFactory.getBean(file, Function.class);

				File adaptorNativeBinaryFile = findBinaryFile(function.getPackageName(), function.getId());
				File adaptorNativeJavaSourceFile = findJavaSourceFile(function.getPackageName(), function.getId());
				deploymentToolbox.deployFunction(function, adaptorNativeBinaryFile, adaptorNativeJavaSourceFile);
				artefacts.add(file);
				artefactDescriptions.add(function.getLabel());
				artefacts.add(adaptorNativeBinaryFile);
				artefactDescriptions.add(function.getLabel());
				artefacts.add(adaptorNativeJavaSourceFile);
				artefactDescriptions.add(function.getLabel());

				String transFileName = function.getId() + TRANSLATION_FILE_EXTENSION_SUFFIX;
				if (!deployedTranslation.contains(transFileName))
				{
					File translationFile = findFile(artefactSourceFolder, transFileName);

					if (translationFile != null && translationFile.exists())
					{
						Translation translationBean = (Translation) EqBeanFactory.getBean(translationFile, Translation.class);
						deploymentToolbox.deployTranslation(translationBean, TranslationToolbox.getTranslationOwnersForService(
										session, function.getId()));
						deployedTranslation.add(transFileName);
						artefacts.add(translationFile);
						artefactDescriptions.add(function.getLabel());
					}
				}
				// Deploy the Bankfusion Complex Type and Type descriptor classes to the GAZ as
				deployComplexTypeClasses(session, function, deploymentToolbox.getTargetFilLibrary());
			}
			else if (file.getName().endsWith(Layout.LAYOUT_FILE_SUFFIX) && installReusableText == false)
			{
				LOG.info("Installing Resource " + file.getName());

				Layout layout = (Layout) EqBeanFactory.getBean(file, Layout.class);

				File adaptorNativeBinaryFile = findBinaryFile(layout.getPackageName(), layout.getId());
				File adaptorNativeJavaSourceFile = findJavaSourceFile(layout.getPackageName(), layout.getId());

				String layoutTransFileName = layout.getServiceId() + TRANSLATION_FILE_EXTENSION_SUFFIX;
				File layoutTransFile = findFile(artefactSourceFolder, layoutTransFileName);

				if (layoutTransFile != null && layoutTransFile.exists())
				{
					Translation translationBean = (Translation) EqBeanFactory.getBean(layoutTransFile, Translation.class);
					String title = TranslationToolbox.getLabelText(session.getUnit().getRecords().getHBXRecords(), translationBean,
									layout.getBaseLanguage(), layout);
					deploymentToolbox.deployLayout(layout, title, adaptorNativeBinaryFile, adaptorNativeJavaSourceFile);
					artefacts.add(file);
					artefactDescriptions.add(layout.getLabel());
					artefacts.add(adaptorNativeBinaryFile);
					artefactDescriptions.add(layout.getLabel());
					artefacts.add(adaptorNativeJavaSourceFile);
					artefactDescriptions.add(layout.getLabel());
					if (!deployedTranslation.contains(layoutTransFileName))
					{
						deploymentToolbox.deployTranslation(translationBean, TranslationToolbox.getTranslationOwnersForService(
										session, layout.getServiceId()));
						deployedTranslation.add(layoutTransFileName);
						artefacts.add(layoutTransFile);
						artefactDescriptions.add(layout.getLabel());
					}
				}
				else
				{
					deploymentToolbox.deployLayout(layout, layout.getLabel(), adaptorNativeBinaryFile, adaptorNativeJavaSourceFile);
					artefacts.add(file);
					artefactDescriptions.add(layout.getLabel());
					artefacts.add(adaptorNativeBinaryFile);
					artefactDescriptions.add(layout.getLabel());
					artefacts.add(adaptorNativeJavaSourceFile);
					artefactDescriptions.add(layout.getLabel());
				}

			}
			else if (file.getName().endsWith(SERVICE_USER_EXIT_FILE_EXTENSION) && installReusableText == false)
			{
				LOG.info("Installing Resource " + file.getName());

				ServiceUserExit userExit = (ServiceUserExit) EqBeanFactory.getBean(file, ServiceUserExit.class);

				File adaptorNativeBinaryFile = findBinaryFile(userExit.getPackageName(), userExit.getServiceUserExitName());
				File adaptorNativeJavaSourceFile = findJavaSourceFile(userExit.getPackageName(), userExit.getServiceUserExitName());
				deploymentToolbox.deployServiceUserExit(userExit, adaptorNativeBinaryFile, adaptorNativeJavaSourceFile);
				artefacts.add(file);
				artefactDescriptions.add("");
				artefacts.add(adaptorNativeBinaryFile);
				artefactDescriptions.add("");
				artefacts.add(adaptorNativeJavaSourceFile);
				artefactDescriptions.add("");

				// TODO hot deploy of user exit java is not supported at the moment
				// invalidateUserExit(session, userExit, applicationServer);

			}
			else if (file.getName().endsWith(USER_EXIT_FILE_EXTENSION) && installReusableText == false)
			{
				LOG.info("Installing Resource " + file.getName());

				UserExit userExit = (UserExit) EqBeanFactory.getBean(file, UserExit.class);

				File adaptorNativeBinaryFile = findBinaryFile(userExit.getPackageName(), userExit.getProgramName());
				File adaptorNativeJavaSourceFile = findJavaSourceFile(userExit.getPackageName(), userExit.getProgramName());
				deploymentToolbox.deployUserExit(userExit, adaptorNativeBinaryFile, adaptorNativeJavaSourceFile);
				artefacts.add(file);
				artefactDescriptions.add("");
				artefacts.add(adaptorNativeBinaryFile);
				artefactDescriptions.add("");
				artefacts.add(adaptorNativeJavaSourceFile);
				artefactDescriptions.add("");

				// TODO hot deploy of user exit java is not supported at the moment
				// invalidateUserExit(session, userExit, applicationServer);

			}
			else if (file.getName().endsWith(EquationPVMetaData.PROMPT_VALIDATE_FILE_SUFFIX) && installReusableText == false)
			{
				LOG.info("Installing Resource " + file.getName());

				EquationPVMetaData pvMetaData = (EquationPVMetaData) EqBeanFactory.getBean(file, EquationPVMetaData.class);

				File adaptorBinaryFile = findBinaryFile(pvMetaData.getPackageName(), pvMetaData.getId());
				File adaptorNativeJavaSourceFile = findJavaSourceFile(pvMetaData.getPackageName(), pvMetaData.getId());
				deploymentToolbox.deployPvMetaData(pvMetaData, adaptorBinaryFile, adaptorNativeJavaSourceFile);
				artefacts.add(file);
				artefactDescriptions.add(pvMetaData.getLabel());
				artefacts.add(adaptorBinaryFile);
				artefactDescriptions.add(pvMetaData.getLabel());
				artefacts.add(adaptorNativeJavaSourceFile);
				artefactDescriptions.add(pvMetaData.getLabel());

				String transFileName = pvMetaData.getId() + TRANSLATION_FILE_EXTENSION_SUFFIX;
				if (!deployedTranslation.contains(transFileName))
				{
					File translationFile = findFile(miscFolder, transFileName);
					if (translationFile != null && translationFile.exists())
					{
						Translation translationBean = (Translation) EqBeanFactory.getBean(translationFile, Translation.class);
						ArrayList<String> ownersList = new ArrayList<String>();
						ownersList.add(translationBean.getOwnerId().trim());

						deploymentToolbox.deployTranslation(translationBean, ownersList);
						deployedTranslation.add(transFileName);
						artefacts.add(translationFile);
						artefactDescriptions.add(pvMetaData.getLabel());

					}
				}
			}

			else if (file.getName().endsWith(TRANSLATION_FILE_EXTENSION_SUFFIX)
							&& installReusableText == true
							&& (file.getName().equalsIgnoreCase(TextBean.OWNER_MISYS) || file.getName().equalsIgnoreCase(
											TextBean.OWNER_BANK)))
			{
				// Deploy eqt

				if (!deployedTranslation.contains(file.getName()))
				{
					LOG.info("Installing Resource " + file.getName());

					Translation translationBean = (Translation) EqBeanFactory.getBean(file, Translation.class);
					String[] parts = translationBean.getOwnerId().split("\\.");
					ArrayList<String> ownersList = new ArrayList<String>();
					if (parts[1].equals(SERVICE_FILE_EXTENSION))
					{
						ownersList = TranslationToolbox.getTranslationOwnersForService(session, parts[0]);
					}
					else
					{
						ownersList.add(translationBean.getOwnerId());
					}
					// Assume the base language is the first language
					deploymentToolbox.deployTranslation(translationBean, ownersList);
					deployedTranslation.add(file.getName());
					artefacts.add(file);
					artefactDescriptions.add("");

				}
			}

			// Service linkages
			else if (file.getName().endsWith(LINKAGE_FILE_EXTENSION) && installReusableText == false)
			{
				LOG.info("Installing Resource " + file.getName());

				LinkService linkService = (LinkService) EqBeanFactory.getBean(file, LinkService.class);

				deploymentToolbox.deployLinkage(linkService);
				artefacts.add(file);
				artefactDescriptions.add("");
			}
		}
	}

	/**
	 * Returns .java file path
	 * 
	 * @param packageName
	 * @param className
	 *            unqualified class name
	 * @return IFile handle to the binary file (or null)
	 */
	private static File findJavaSourceFile(String packageName, String className)
	{
		String filePath = new StringBuilder(MISC_SRC_FOLDER_SEGMENT).append(packageName.replace('.', '/')).append('/').append(
						className).append(JAVA_SUFFIX).toString();

		return getInstallationFolderFileInstance(filePath);

	}
	/**
	 * Returns .class file path
	 * 
	 * @param packageName
	 * @param className
	 * @return file
	 */
	private File findBinaryFile(String packageName, String className)
	{
		String filePath = new StringBuilder(MISC_CLASSES_FOLDER_SEGMENT).append(packageName.replace('.', '/')).append('/').append(
						className).append(CLASS_SUFFIX).toString();

		return getInstallationFolderFileInstance(filePath);
	}
	/**
	 * Returns file path by navigating through directory
	 * 
	 * @param file
	 * @param fileToFind
	 * @return file
	 */
	private File findFile(File file, String fileToFind)
	{
		File foundFile = null;

		if (file.isDirectory())
		{

			for (File childFile : file.listFiles())
			{
				foundFile = findFile(childFile, fileToFind);
				if (foundFile != null)
				{
					return foundFile;
				}
			}
		}
		else
		{
			if (file.getName().endsWith(TRANSLATION_FILE_EXTENSION_SUFFIX) && file.getName().equalsIgnoreCase(fileToFind))
			{
				return file;

			}
		}
		return foundFile;

	}
	/**
	 * Returns file path prefixed by installation folder location
	 * 
	 * @param filePath
	 * @return file
	 */
	private static File getInstallationFolderFileInstance(String filePath)
	{
		String file = installationFolder + "/" + filePath;
		return new File(file);
	}

	// TODO HOT deploy of user exists in non-development environment is not catered for.
	// Reload of User Exit should be done by runtime checking.
	// This will mean creating a GAXPF at deployment with a timestamp BUT THE GAXPF KEY IS TOO SMALL.
	// Alternatively the GAZPF could have a timestamp field added.
	// Delete of use exists as well as other artefacts is only catered for in the development environment.
	// /**
	// * Attempts a web service call to the application server to cause it to drop the Class bytes for the specified class from its
	// * cache.
	// *
	// * If the application server is not running, this is not a problem (no cache) but need to avoid lengthy delays in this case.
	// *
	// */
	// private void invalidateUserExit(EquationStandardSession session, UserExit userExitBean, String applicationServer)
	// {
	// try
	// {
	// Service service = new Service();
	// Call call = (Call) service.createCall();
	//
	// try
	// {
	//
	// if (applicationServer.length() > 0)
	// {
	// String unit = session.getUnitId();
	// String system = session.getUnit().getSystem().getSystemId();
	// String webAppId = session.getUnit().getSystemOption(session, "KFIL", "WFAPI");
	//
	// // TODO: Review port and machine name for URL...
	// String endpoint = String.format("http://%s/" + webAppId.trim() + "/services/ServiceDirectory",
	// applicationServer);
	//
	// LOG.info("Webservice endpoint determined as [" + endpoint + "]");
	//
	// call.setTargetEndpointAddress(new java.net.URL(endpoint));
	// call.setOperationName(new QName(WEB_SERVICE_NAMESPACE, "invalidateValidationUserExit"));
	//
	// String qualifiedClassName = userExitBean.getPackageName().length() > 0 ? userExitBean.getPackageName() + "."
	// + userExitBean.getProgramName() : userExitBean.getProgramName();
	// LOG.info("Calling webservice invalidateValidationUserExit method for system [" + system + "], unit [" + unit
	// + "], program [" + userExitBean.getProgramName() + "], class [" + qualifiedClassName + "]");
	// call.invoke(new Object[] { system, unit, userExitBean.getProgramName(), qualifiedClassName });
	// LOG.info("Webservice invalidateUserExit method called successfully.");
	// }
	// }
	// catch (IOException e)
	// {
	// if (e.getCause() != null && e.getCause() instanceof ConnectException)
	// {
	// // If this was a connection problem, output a simple warning instead of the full exception details.
	// // Note however that the underlying webservice Axis component will still automatically log information
	// LOG.warn("invalidateUserExit - Unable to connect to web service; server may not be running");
	// }
	// else
	// {
	// // For all other exceptions, log the details
	// LOG.warn("invalidateUserExit", e);
	// }
	// }
	// }
	// catch (Throwable e)
	// {
	// // Catch any error from the web service call
	// System.err.println(e.toString());
	// }
	// }
	/**
	 * Grant object authority to library
	 * 
	 * @throws Exception
	 */
	private void grantObjectAuthority() throws Exception
	{
		LOG.info("---------- Equation Grant Object Authority Start ----------");

		String status = STATUS_SUCCESS;

		String dbUrl = customProperties.get("DB_URL_BFEQ").trim();
		String systemId = dbUrl.replace(DB2400_URL_PREFIX, "").toUpperCase().trim();
		String userId = customProperties.get("DB_USER_ID_BFEQ").trim();
		String password = customProperties.get("DB_PASSWORD_BFEQ").trim();

		String library = customProperties.get("LIBRARY").trim();
		AS400 eqAS400 = null;
		try
		{
			eqAS400 = new AS400(systemId);
			if (eqAS400.authenticate(userId, password))
			{
				// Create an AS400ConnectionPool.
				AS400ConnectionPool aS400Pool = new AS400ConnectionPool();
				// Set a maximum of "no max" connections to this pool.
				aS400Pool.setMaxConnections(1);
				// Set a maximum lifetime for 24 hours for connections (ms*s*m).
				aS400Pool.setMaxLifetime(1000 * 60 * 60);
				// Fill'em up (10 is an accepted "best guess" (in my book anyway))
				aS400Pool.fill(systemId, userId, password, AS400.CENTRAL, 1);
				AS400 as400 = aS400Pool.getConnection(systemId, userId, password, AS400.CENTRAL);

				CommandCall command = new CommandCall(as400);
				String commandString = null;

				// Note that the standard non-default options when starting journaling are to journal both images (before and after)
				// and to omit file open/close journal entries
				commandString = "GRTOBJAUT OBJ(" + library.trim() + ") OBJTYPE(*LIB) USER(*PUBLIC) AUT(*ALL)";
				if (command.run(commandString) == false)
				{
					logCommmandMessages(command);
					throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
				}
				commandString = "GRTOBJAUT OBJ(" + library.trim() + "/*ALL) OBJTYPE(*ALL) USER(*PUBLIC) AUT(*ALL)";
				if (command.run(commandString) == false)
				{
					logCommmandMessages(command);
					throw new EQException("Command failed: " + commandString + " Job: " + command.getServerJob());
				}
			}
			else
			{
				throw new EQException("Logon failed");
			}
		}
		catch (Exception e)
		{
			status = STATUS_FAILURE;
			LOG.error(e);
			message = message + " " + e.getMessage();
		}
		finally
		{
			if (eqAS400 != null)
			{
				eqAS400.disconnectAllServices();
			}
			// Delete Install folders from folder system
			FileUtils.deleteDir(installationFolder);
			// Write Audit file in folder system
			createBundleInstallAuditFile(library, "", status, message);

		}
		LOG.info("---------- Equation Grant Object Authority End ----------");
	}
	/**
	 * Log command messages
	 * 
	 * @param command
	 */
	private void logCommmandMessages(CommandCall command)
	{

		AS400Message[] messageList = command.getMessageList();
		int numberOfMessages = messageList.length;
		for (int i = 0; i < numberOfMessages; i++)
		{

			LOG.error(messageList[i].getText()); //$NON-NLS-1$ //$NON-NLS-2$
			message = message + " " + messageList[i].getText();
		}
	}
	/**
	 * Log context values from custom properties
	 */
	private void logContextValues()
	{

		for (Entry<String, String> entry : customProperties.entrySet())
		{
			LOG.info(entry.getKey() + " " + entry.getValue());
		}
	}
	/**
	 * Get the Equation Installer properties from the bundle
	 * 
	 * @param equationInstaller
	 * @throws IOException
	 */
	private Properties getEquationInstallerProperties(String equationInstaller) throws IOException
	{
		Properties equationInstallerProperties = null;
		URL equationInstallerURL = new URL(new StringBuilder("file:").append(equationInstaller.replace("\\", "/")).toString());
		if (equationInstallerURL == null)
		{
			throw new EQRuntimeException("File [" + equationInstallerURL + "] not found in the plugin jar");
		}

		InputStream propertiesIS = equationInstallerURL.openStream();

		if (propertiesIS != null)
		{
			equationInstallerProperties = new Properties();

			equationInstallerProperties.load(propertiesIS);

		}
		return equationInstallerProperties;
	}

	/**
	 * Create audit text file in the installation folder.
	 * 
	 * @param fileComponent
	 * @param releaseVersion
	 * @param status
	 * @param message
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void createBundleInstallAuditFile(String fileComponent, String releaseVersion, String status, String message)
					throws IOException
	{
		// Take the project out of the path of the artefacts
		String[] artefactsNameArray = new String[artefacts.size()];
		Map<String, String> arteafactsNameDescriptionMap = new HashMap<String, String>();
		int index = 0;
		for (File file : artefacts)
		{
			String relativeFileName = file.getAbsolutePath();
			relativeFileName = relativeFileName.replace("\\", "/");
			relativeFileName = relativeFileName.replace(bundleInstallationLocation.substring(1), "");
			artefactsNameArray[index] = relativeFileName;
			arteafactsNameDescriptionMap.put(relativeFileName, artefactDescriptions.get(index));
			index++;
		}
		// Sort artefacts
		Arrays.sort(artefactsNameArray);
		// Sort installation properties
		Enumeration<Object> equationInstallerPropertiesKeys = equationInstallerProperties.keys();
		List<String> equationInstallerPropertiesSortedList = new ArrayList();
		while (equationInstallerPropertiesKeys.hasMoreElements())
		{
			equationInstallerPropertiesSortedList.add((String) equationInstallerPropertiesKeys.nextElement());
		}
		Collections.sort(equationInstallerPropertiesSortedList);
		// Sort custom properties
		TreeSet<String> customPropertiesKeys = new TreeSet<String>(customProperties.keySet());

		InputStream auditTemplateInputStream = FileUtils.class.getResourceAsStream(JAR_AUDIT_FILE);
		if (auditTemplateInputStream == null)
		{
			throw new EQRuntimeException("File [" + JAR_AUDIT_FILE + "] not found in the installer jar");
		}
		StringBuilder auditContents = new StringBuilder();

		BufferedReader in = new BufferedReader(new InputStreamReader(auditTemplateInputStream));

		String inputLine;

		while ((inputLine = in.readLine()) != null)
		{
			if (inputLine.contains("{LINE_BREAK}"))
			{
				inputLine = inputLine.replace("{LINE_BREAK}", "\r\n");
			}
			if (inputLine.contains("{TAB}"))
			{
				inputLine = inputLine.replace("{TAB}", "\t");
			}
			if (inputLine.contains("{STATUS}"))
			{
				inputLine = inputLine.replace("{STATUS}", status);
			}
			if (inputLine.contains("{MESSAGE}"))
			{
				inputLine = inputLine.replace("{MESSAGE}", message);
			}
			if (inputLine.contains("{PLUGIN_VERSION}"))
			{
				inputLine = inputLine.replace("{PLUGIN_VERSION}", EquationCommonContext.PLUGIN_VERSION);
			}
			if (inputLine.contains("{BUNDLE_PROPERTIES}"))
			{
				Iterator<String> keys = equationInstallerPropertiesSortedList.iterator();
				while (keys.hasNext())
				{
					String key = keys.next();
					String value = equationInstallerProperties.getProperty(key);
					auditContents.append("\t").append(key).append("\t").append("\t").append("\t").append(value).append("\r\n");
				}
				inputLine = "";
			}
			if (inputLine.contains("{CUSTOM_PROPERTIES}"))
			{
				Iterator<String> keys = customPropertiesKeys.iterator();
				while (keys.hasNext())
				{
					String key = keys.next();
					if (!key.toUpperCase().contains("PASSWORD"))
					{
						String value = customProperties.get(key);
						auditContents.append("\t").append(key).append("\t").append("\t").append("\t").append(value).append("\r\n");
					}
				}
				inputLine = "";
			}
			if (inputLine.contains("{EQUATION_ARTEFACT}"))
			{
				index = 0;
				for (String filePath : artefactsNameArray)
				{

					String[] pathParts = filePath.split("/");
					String[] parts = pathParts[pathParts.length - 1].split("\\.");
					String description = arteafactsNameDescriptionMap.get(filePath);
					if (filePath.endsWith(SERVICE_FILE_EXTENSION))
					{
						auditContents.append("\t").append(SERVICE_LABEL).append("\t").append("\t").append(parts[0]).append("\t")
										.append(description).append("\r\n");
					}
					if (filePath.endsWith(LAYOUT_FILE_EXTENSION))
					{
						auditContents.append("\t").append(LAYOUT_LABEL).append("\t").append("\t").append(parts[0]).append("\t")
										.append(description).append("\r\n");
					}
					if (filePath.endsWith(TRANSLATION_FILE_EXTENSION))
					{
						auditContents.append("\t").append(TRANSLATION_LABEL).append("\t").append(parts[0]).append("\t").append(
										description).append("\r\n");
					}
					if (filePath.endsWith(USER_EXIT_FILE_EXTENSION))
					{
						auditContents.append("\t").append(USER_EXIT_LABEL).append("\t").append("\t").append(parts[0]).append("\t")
										.append(description).append("\r\n");
					}
					if (filePath.endsWith(PROMPT_VALIDATE_FILE_EXTENSION))
					{
						auditContents.append("\t").append(PROMPT_VALIDATE_LABEL).append("\t").append("\t").append(parts[0]).append(
										"\t").append(description).append("\r\n");
					}
					if (filePath.endsWith(LINKAGE_FILE_EXTENSION))
					{
						auditContents.append("\t").append(LINKAGE_LABEL).append("\t").append("\t").append(parts[0]).append("\t")
										.append(artefactDescriptions.get(index)).append("\r\n");
					}
					index++;
				}
				inputLine = "";

			}

			auditContents.append(inputLine);
		}
		in.close();

		// Add a record to the CHPF file
		String date = new Integer(Toolbox.getDateDBFormat(Calendar.getInstance())).toString();
		// Time is only 4 digits
		String time = new Integer(Toolbox.getTimeDBFormat(Calendar.getInstance())).toString();
		String auditFilePath = new StringBuilder(servicesLocation).append(fileComponent).append(FILE_NAME_SEPARATOR).append(date)
						.append(FILE_NAME_SEPARATOR).append(time).append(FILE_NAME_SEPARATOR).append(eqAction).append(
										FILE_NAME_SEPARATOR).append(releaseVersion).append(FILE_NAME_SEPARATOR).append(status)
						.append(TEXT_SUFFIX).toString();
		FileUtils.writeInAFile(auditContents.toString(), auditFilePath);

	}
	private String deployComplexTypeClasses(EquationStandardSession session, Function function, String targetFilLibrary)
					throws EQException

	{
		String outCome = null;

		String serviceName = FunctionBankFusion.getServiceXSDName(function);
		String packageName = null;
		String typeClassFileName = null;
		String fullClassName = null;
		File bankFusionTypeJavaBinaryFile = null;

		Map<String, File> javaSource = new HashMap<String, File>();
		Map<String, File> javaBinary = new HashMap<String, File>();
		Map<String, File> javaSourceDescriptor = new HashMap<String, File>();
		Map<String, File> javaBinaryDescriptor = new HashMap<String, File>();
		// Collect and check Complex Types
		packageName = ComplexTypeToolbox.getComplexTypePackage(function);

		String srcPath = new StringBuilder(MISC_SRC_FOLDER_SEGMENT).append(packageName.replace('.', '/')).toString();
		File sourceFolder = (File) getInstallationFolderFileInstance(srcPath);
		if (sourceFolder != null && sourceFolder.isDirectory())
		{
			for (File member : sourceFolder.listFiles())
			{
				// If generic XSD then the folder should only have function's classes
				if (member.getName().endsWith(".java") && (member.getName().startsWith(serviceName) || function.chkXSDGeneric()))
				{
					typeClassFileName = member.getName().substring(0, member.getName().length() - 5);
					fullClassName = packageName + "." + member.getName().replace(".java", "");
					javaSource.put(fullClassName, member);
					bankFusionTypeJavaBinaryFile = findBinaryFile(packageName, typeClassFileName);
					javaBinary.put(fullClassName, bankFusionTypeJavaBinaryFile);
				}
			}

			// Collect and check Complex Type Descriptors
			packageName = ComplexTypeToolbox.getComplexTypeDescriptorPackage(function);

			srcPath = new StringBuilder(MISC_SRC_FOLDER_SEGMENT).append(packageName.replace('.', '/')).toString();
			sourceFolder = (File) getInstallationFolderFileInstance(srcPath);

			for (File member : sourceFolder.listFiles())
			{
				// If generic XSD then the folder should only have function's classes
				if (member.getName().endsWith(".java") && (member.getName().startsWith(serviceName) || function.chkXSDGeneric()))
				{
					typeClassFileName = member.getName().substring(0, member.getName().length() - 5);
					fullClassName = packageName + "." + member.getName().replace(".java", "");
					javaSourceDescriptor.put(fullClassName, member);
					bankFusionTypeJavaBinaryFile = findBinaryFile(packageName, typeClassFileName);
					javaBinaryDescriptor.put(fullClassName, bankFusionTypeJavaBinaryFile);
				}
			}

			ComplexTypeToolbox.writeComplexTypeClasses(session, targetFilLibrary, function, javaSource, javaBinary,
							javaSourceDescriptor, javaBinaryDescriptor);
		}
		else
		{

			// For older bundles that only contain the complex type .class files and no .java source files, just deploy the binary
			// records
			packageName = ComplexTypeToolbox.getComplexTypePackage(function);
			String classesPath = new StringBuilder(MISC_CLASSES_FOLDER_SEGMENT).append(packageName.replace('.', '/')).toString();
			File classesFolder = (File) getInstallationFolderFileInstance(classesPath);

			if (classesFolder != null && classesFolder.isDirectory())
			{
				for (File member : classesFolder.listFiles())
				{
					// If generic XSD then the folder should only have function's classes
					if (member.getName().endsWith(".class")
									&& (member.getName().startsWith(serviceName) || function.chkXSDGeneric()))
					{
						typeClassFileName = member.getName().substring(0, member.getName().length() - 6);
						fullClassName = packageName + "." + member.getName().replace(".class", "");
						javaBinary.put(fullClassName, member);
					}
				}

				// Collect and check Complex Type Descriptors
				packageName = ComplexTypeToolbox.getComplexTypeDescriptorPackage(function);

				srcPath = new StringBuilder(MISC_CLASSES_FOLDER_SEGMENT).append(packageName.replace('.', '/')).toString();
				classesFolder = (File) getInstallationFolderFileInstance(classesPath);

				for (File member : classesFolder.listFiles())
				{
					// If generic XSD then the folder should only have function's classes
					if (member.getName().endsWith(".class")
									&& (member.getName().startsWith(serviceName) || function.chkXSDGeneric()))
					{
						typeClassFileName = member.getName().substring(0, member.getName().length() - 6);
						fullClassName = packageName + "." + member.getName().replace(".class", "");
						javaBinaryDescriptor.put(fullClassName, member);
					}
				}

				ComplexTypeToolbox.writeComplexTypeClasses(session, targetFilLibrary, function, javaSource, javaBinary,
								javaSourceDescriptor, javaBinaryDescriptor);
			}
		}

		return outCome;
	}
}
