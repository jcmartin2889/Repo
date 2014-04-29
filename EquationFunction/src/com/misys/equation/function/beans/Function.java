package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.KeyedArrayList;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.valid.IValidation;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.FunctionCommonData;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.MappingList;
import com.misys.equation.function.tools.MappingToolbox;
import com.misys.equation.function.tools.XSDStructureHelper;
import com.misys.equation.function.tools.XSDToolbox;
import com.misys.equation.problems.ProblemLocation;

/**
 * This class represents an Equation Desktop function
 * <p>
 * Each Equation desktop function must have a unique ID. It will have 2 sets of field sets: the input field sets and the API field
 * sets
 * <p>
 * Input field sets - Each field set identifies all the fields that will be available to the user for input.
 * <p>
 * API field sets - Each field set identifies an Equation function associated with this Equation desktop function. An Equation
 * desktop function must be mapped to at least one Equation function. Each of the fields in this set is mapped from the input field
 * set. If a field is not mapped, then it will have a default value of blank or 0 depending on the field type.
 * <p>
 * 
 * @see FieldSet
 */
public class Function extends Element implements IValidation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Function.java 17190 2013-09-03 11:49:59Z Lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(Function.class);
	/** Service File suffix. Note: This value is stored in the function layer because it forms part of the translation text owner id */
	public static final String SERVICE_FILE_NAME_SUFFIX = ".eqf";
	/** Specifies that only overview validation be performed */
	public static final String OVERVIEW_VALIDATION = "OVERVIEW_VALIDATION";
	/** Specifies that only update assigned validation be performed */
	public static final String UPDATE_ASSIGNMENT_VALIDATION = "UPDATE_ASSIGNMENT_VALIDATION";
	/** Specifies that only Properties page validation be performed */
	public static final String PROPERTIES_VALIDATION = "PROPERTIES_VALIDATION";

	/** Substring used in Path strings to denote a subfield */
	public static final String SUBFIELD = "SubField";
	/** Denotes the type of a reserved field in the properties file */
	public static final String TYP = "TYP";
	/** Denotes the length of a reserved field in the properties file */
	public static final String LEN = "LEN";
	/** Denotes the reserved GY Journal Header Field Set */
	public static final String GY_FIELDSET = "GY";
	/** Denotes the reserved CRM Field Set */
	public static final String CRM_FIELDSET = "CRM";
	/** Denotes the reserved EFC Field Set */
	public static final String EFC_FIELDSET = "EFC";
	/** Denotes the reserved G5 Enquiry Journal Header Field Set */
	public static final String G5_FIELDSET = "G5";

	/** The ID of the Primary field set */
	public static final String PRIMARY_ID = "PRIMARY";

	/** Indicates post update microflow is not enabled (default) */
	public static final String POST_UPDATE_MICROFLOW_NO = "0";
	/** Indicates post update microflow is enabled */
	public static final String POST_UPDATE_MICROFLOW_YES = "1";
	/** Indicates that there is an EL Script to determine post update */
	public static final String POST_UPDATE_MICROFLOW_SCRIPT = "2";
	/** Indicates that there is Java exit code to determine post update */
	public static final String POST_UPDATE_MICROFLOW_CODE = "3";

	/** Default XSD generation */
	public final static int XSD_DEFAULT = 1;

	/**
	 * Enhanced XSD generation
	 * 
	 * - Allow user to rename the field name<br>
	 * - Allow user to group fields into sub-group<br>
	 * - Allow user to specify which are input/output fields<br>
	 * */
	public final static int XSD_GENERIC = 2;

	/** Ordered list of G5 enquiry journal fields */
	private static final String[] G5_FIELDS = new String[] { "G5APP", "G5WHO", "G5SHN", "G5JREF", "G5N01", "G5N02", "G5N03",
					"G5N04", "G5N05", "G5N06", "G5N07", "G5N08", "G5N09", "G5N010" };
	/** Ordered list of GY input journal fields */
	private static final String[] GY_FIELDS = new String[] { "GYAPP", "GYWHO", "GYSUID", "GYSHN", "GYJREF", "GYIREF", "GYAUID" };

	// Input Field Sets
	private final List<InputFieldSet> inputFieldSets = new ArrayList<InputFieldSet>();
	private final List<String> inputFieldSetKeys = new ArrayList<String>();

	// Update APIs
	private final List<APIFieldSet> updateAPIs = new ArrayList<APIFieldSet>();
	private final List<String> updateAPIKeys = new ArrayList<String>();

	/** Reserved names (GY, EFC, CRM & G5 Enquiry fields) */
	private static final List<String> reservedNames = new ArrayList<String>();

	// All the mappings we'll ever need...
	private List<Mapping> loadMappings = new MappingList();
	private List<Mapping> updateMappings = new MappingList();
	private List<Mapping> validateMappings = new MappingList();

	/** Service scoped collection of work fields */
	private final KeyedArrayList<WorkField> workFields = new KeyedArrayList<WorkField>();

	/** Java package name */
	private String packageName = "";

	/** Version of plug-in used to create the service */
	private String pluginVersion = "";

	/** Release version of the patch/enhancement of the service */
	private String releaseVersion = "";

	// valid transaction modes
	private boolean allowedAdd = true;
	private boolean allowedMaint = false;
	private boolean allowedDel = false;
	private boolean allowedEnq = false;

	private boolean globalEnquiry = false;
	// behaviour during external input and recovery
	// if the property is true, then this function is applied instead of the underlying APIs
	// if the property is false, then the underlying APIS are applied instead of this function
	private boolean applyDuringExtInput = true;
	private boolean applyDuringRecovery = true;

	/** Can this service be run during extended business hours? */
	private boolean extendedBusinessHoursAllowed = false;

	/** Flag to indicate whether BankFusion support is required for this service */
	private boolean bankFusionSupport = false;

	/** Flag to indicate whether a BankFusion User Exit Microflow should be called in post update processing */
	/** 9/20/2011 - this indicator is made obsolete by new String property postUpdateMicroFlow */
	/** - retained only for backward compatibility */
	private boolean postUpdateMicroflow = false;

	/**
	 * Indicates whether a BankFusion User Exit Microflow should be called in post update processing
	 * <ul>
	 * <li>null/"0" = post update microflow is disabled</li>
	 * <li>"1" = post update microflow is enabled</li>
	 * <li>"2" = EL Script determines post update microflow status</li>
	 */
	private String postUpdateMicroFlow;
	private String postUpdateMicroFlowExpression;

	/** Application type */
	private String application = "ED";

	/** Flag indicating if Import is Allowed **/
	private boolean importAllowed = true;

	/** Flag indicating if Desktop UI is not allowed in Production **/
	private boolean noDesktopInProduction = false;

	/** Handle of Original Service ID **/
	private String originalServiceId = "";

	/** Handle of Original Release Version **/
	private String originalReleaseVersion = "";

	/** XSD Version **/
	private String xsdVersion = "";

	/** Handle of Service Owner; "MISYS" if owned by Misys, BLANK/empty if owned by Bank **/
	private String owner = "";

	/** Base language */
	private String baseLanguage;

	/** Application/Bank identifier */
	private String appBankId = "";
	/** Module identifier */
	private String moduleId = "";

	/** Flag indicating if EFC is to be validated (hence enabled) **/
	private boolean validateEfc = true;

	/**
	 * Collection of mapping contexts
	 * <p>
	 * The mapping target will be the name of an Equation Context Item (e.g. $CUS for customer mnemonic). The mapping source will be
	 * an EL expression. Items are added to the list in context item order.
	 */
	private MappingList contextMappings = new MappingList();

	/**
	 * Determine whether to use enhanced XSD
	 * */
	private int xsdGeneration = XSD_DEFAULT;

	/** XSD structure request */
	private XSDStructure xsdStructureRequest;

	/** XSD structure response */
	private XSDStructure xsdStructureResponse;

	static
	{
		initialiseReservedNames();
	}

	/**
	 * Construct a new empty function
	 */
	public Function()
	{
		super();
		commonInitialization();
	}

	/**
	 * Construct a new function with the given label, id and description
	 * 
	 * @param functionIdentifier
	 *            - the id of the function
	 * @param functionTitle
	 *            - the label of the function
	 * @param functionDescription
	 *            - the description of the function
	 */
	public Function(String functionIdentifier, String functionTitle, String functionDescription)
	{
		super(functionIdentifier, functionTitle, functionDescription);
		commonInitialization();
	}
	/**
	 * Common field initialization called by non-copy Constructors.
	 * <p>
	 * When new member variables are added, initialisation code must be added to both this method and the copy constructor.
	 */
	private void commonInitialization()
	{
		postUpdateMicroFlow = POST_UPDATE_MICROFLOW_NO;
		postUpdateMicroFlowExpression = "";
		xsdGeneration = XSD_DEFAULT;
	}

	/**
	 * Return the list of input field sets of the function.
	 * 
	 * @return the list of input field sets of the function
	 */
	public List<InputFieldSet> getInputFieldSets()
	{
		return inputFieldSets;
	}

	/**
	 * Return the list of input field key sets of the function
	 * 
	 * @return the list of input field key sets of the function
	 */
	// Note this method is intentionally not called getInputFieldSetKeys, this is because it should not form part of the bean
	// serialisation
	public List<String> inputFieldSetKeys()
	{
		return inputFieldSetKeys;
	}

	/**
	 * Add an input field set into the function
	 * 
	 * @param fieldSet
	 *            - the field set to be added
	 * 
	 * @throws EQException
	 *             - if an input field set with the same ID already exists
	 */
	public void addInputFieldSet(InputFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (inputFieldSetKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getString("Function.FieldSetExists"))); //$NON-NLS-1$
		}
		else
		{
			inputFieldSets.add(fieldSet);
			inputFieldSetKeys.add(key);
			fieldSet.setFunction(this);
		}
	}

	/**
	 * Determines whether the given Input Field Set exists in the function.
	 * <p>
	 * This is used by the Field Copy facility.
	 * 
	 * @param inputFieldSetId
	 * @return a <code>boolean</code> indicating whether the Input Field Set exists
	 */
	public boolean hasInputFieldSet(String inputFieldSetId) throws EQException
	{
		if (inputFieldSetKeys.contains(inputFieldSetId))
		{
			return true;
		}
		return false;
	}
	/**
	 * Return the input field set identified by the id
	 * 
	 * @param key
	 *            - the input field set ID
	 * 
	 * @return the input field set identified by the key
	 * 
	 * @throws EQException
	 *             - if the input field set does not exist
	 */
	public InputFieldSet getInputFieldSet(String key) throws EQException
	{
		if (!inputFieldSetKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.InputFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = inputFieldSetKeys.indexOf(key);
			return inputFieldSets.get(keyIndex);
		}
	}

	/**
	 * Returns true if the Function contains the passed input field set key.
	 * 
	 * @param key
	 *            - the key of the input field set.
	 * @return true if the Function contains the passed input field set key, otherwise false.
	 */
	public boolean containsInputFieldSet(String key)
	{
		return inputFieldSetKeys.contains(key);
	}

	/**
	 * Returns true if the Function contains the passed API field set key.
	 * 
	 * @param key
	 *            - the key of the input field set.
	 * @return true if the Function contains the passed API field set key, otherwise false.
	 */
	public boolean containsAPIFieldSet(String key)
	{
		return updateAPIKeys.contains(key);
	}

	/**
	 * Determines if the field exists in the service
	 * 
	 * @param key
	 *            Id of the field
	 * @param includeWorkFields
	 *            a boolean indicating whether WorkFields should be included
	 * 
	 * @return boolean
	 */
	public boolean containsField(String key, boolean includeWorkFields)
	{
		for (InputFieldSet set : inputFieldSets)
		{
			if (set.containsField(key))
			{
				return true;
			}
		}
		if (includeWorkFields)
		{
			if (workFields.contains(key))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieve the input field or work field from the service
	 * 
	 * @param key
	 *            Id of the field
	 * @param includeWorkFields
	 *            a boolean indicating whether WorkFields should be included
	 * 
	 * @return the input field or work field if existing
	 */
	public Element rtvInputField(String key, boolean includeWorkFields)
	{
		for (InputFieldSet set : inputFieldSets)
		{
			if (set.containsField(key))
			{
				try
				{
					return set.getInputField(key);
				}
				catch (Exception e)
				{
				}
			}
		}
		if (includeWorkFields)
		{
			if (workFields.contains(key))
			{
				return workFields.get(key);
			}
		}
		return null;
	}

	/**
	 * Updates the Id of the FieldSet.
	 * <p>
	 * Note that it is not sufficient to update the Id in the FieldSet object. This method must also be called to:
	 * <ul>
	 * <li>update the Id in the collection of FieldSet keys.</li>
	 * <li>update the FieldSet Id in all mapping collections.</li>
	 * </ul>
	 * 
	 * @param oldKey
	 *            - The original key of the FieldSet
	 * @param newKey
	 *            - The new key value
	 * 
	 * @throws EQException
	 *             - if the input field set does not exist
	 */
	// ***************************************************************************
	public void modifyFieldSetId(String oldKey, String newKey) throws EQException
	{
		if (!inputFieldSetKeys.contains(oldKey))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.InputFieldSetDoesNotExist", oldKey))); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = inputFieldSetKeys.indexOf(oldKey);
			inputFieldSetKeys.set(keyIndex, newKey);
			inputFieldSets.get(keyIndex).setId(newKey);

			// Update all collections of mappings:
			applyFieldSetIdChangeToMappingCollection(loadMappings, oldKey, newKey);
			applyFieldSetIdChangeToMappingCollection(updateMappings, oldKey, newKey);
			applyFieldSetIdChangeToMappingCollection(validateMappings, oldKey, newKey);
		}
	}

	/**
	 * Updates a collection of Mapping definitions with the new FieldSet id after a field set Id has been changed
	 * 
	 * @param mappings
	 *            the mapping collection to update
	 * @param oldId
	 *            The old FieldSet ID
	 * @param newId
	 *            The new FieldSet ID
	 */
	private void applyFieldSetIdChangeToMappingCollection(List<Mapping> mappings, String oldId, String newId)
	{
		String oldPath = MappingToolbox.getInputFieldSetPath(oldId);
		String newPath = MappingToolbox.getInputFieldSetPath(newId);
		for (Mapping mapping : mappings)
		{
			// Currently, mapping paths do not have embedded InputFieldSets (so can just check the start)
			if (mapping.getSource().startsWith(oldPath))
			{
				String oldSourcePath = mapping.getSource();
				String newSourcePath = oldSourcePath.replaceFirst(oldPath, newPath);
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Updating mapping source path [" + oldSourcePath + "] to [" + newSourcePath + "]");
				}
				((MappingList) mappings).editMapping(mapping, newSourcePath, null);
			}
			if (mapping.getTarget().startsWith(oldPath))
			{
				String oldTargetPath = mapping.getTarget();
				String newTargetPath = oldTargetPath.replaceFirst(oldPath, newPath);
				if (LOG.isDebugEnabled())
				{
					LOG.debug("Updating mapping source path [" + oldTargetPath + "] to [" + newTargetPath + "]");
				}
				((MappingList) mappings).editMapping(mapping, null, newTargetPath);
			}
		}
	}

	/**
	 * Remove the input field set from the function
	 * 
	 * @param fieldSet
	 *            - the input field set to be removed
	 * 
	 * @throws EQException
	 *             - if the input field set does not exist
	 */
	public void removeInputFieldSet(InputFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!inputFieldSetKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.InputFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			// Get rid of the mappings to elements in this fieldset first
			List<Field> fields = fieldSet.getFields();
			for (Field field : fields)
			{
				removeUpdateMappings(field);
				removeValidateMappings(field);
				removeLoadMappings(field);
				// also need to remove any PV mappings...
				if (field instanceof InputField)
				{
					List<PVFieldSet> pvFieldSets = ((InputField) field).getPvFieldSets();
					for (PVFieldSet pvFieldSet : pvFieldSets)
					{
						List<PVField> pvFields = pvFieldSet.getPVFields();
						for (PVField pvField : pvFields)
						{
							removeUpdateMappings(pvField);
							removeValidateMappings(pvField);
							removeLoadMappings(pvField);
						}
					}

					// remove field from XSD
					XSDStructureHelper.removeFieldFromXSD(this, field.getId());
				}
			}
			int keyIndex = inputFieldSetKeys.indexOf(key);
			inputFieldSets.remove(keyIndex);
			inputFieldSetKeys.remove(keyIndex);
		}
	}

	/**
	 * Move the input field set one position up
	 * 
	 * @param fieldSet
	 *            - the input field set to be moved
	 * 
	 * @throws EQException
	 *             - if the field set does not exist
	 */
	public void moveInputFieldSetUp(InputFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!inputFieldSetKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.InputFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			int position = inputFieldSetKeys.indexOf(key);
			if (position > 0)
			{
				inputFieldSetKeys.remove(position);
				inputFieldSetKeys.add(position - 1, key);
				inputFieldSets.remove(position);
				inputFieldSets.add(position - 1, fieldSet);
			}
		}
	}

	/**
	 * Move the input field set one position down
	 * 
	 * @param fieldSet
	 *            - the field set to be moved
	 * 
	 * @throws EQException
	 *             - if the field set does not exist
	 */
	public void moveInputFieldSetDown(InputFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!inputFieldSetKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.InputFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			int position = inputFieldSetKeys.indexOf(key);
			if (position < inputFieldSetKeys.size() - 1)
			{
				inputFieldSetKeys.remove(position);
				inputFieldSetKeys.add(position + 1, key);
				inputFieldSets.remove(position);
				inputFieldSets.add(position + 1, fieldSet);
			}
		}
	}

	/**
	 * Move the API field set one position up
	 * 
	 * @param fieldSet
	 *            - the API field set to be moved
	 * 
	 * @throws EQException
	 *             - if the field set does not exist
	 */
	public void moveUpdateAPIUp(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!updateAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.UpdateFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			int position = updateAPIKeys.indexOf(key);
			if (position > 0)
			{
				updateAPIKeys.remove(position);
				updateAPIKeys.add(position - 1, key);
				updateAPIs.remove(position);
				updateAPIs.add(position - 1, fieldSet);
			}
		}
	}

	/**
	 * Move the API field set one position down
	 * 
	 * @param fieldSet
	 *            - the API field set to be moved
	 * 
	 * @throws EQException
	 *             - if the field set does not exist
	 */
	public void moveUpdateAPIDown(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!updateAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.UpdateFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			int position = updateAPIKeys.indexOf(key);
			if (position < updateAPIKeys.size() - 1)
			{
				updateAPIKeys.remove(position);
				updateAPIKeys.add(position + 1, key);
				updateAPIs.remove(position);
				updateAPIs.add(position + 1, fieldSet);
			}
		}
	}

	/**
	 * Return the API field sets of the function
	 * 
	 * @return the API field sets of the function
	 */
	public List<APIFieldSet> getUpdateAPIs()
	{
		return updateAPIs;
	}

	/**
	 * Return the API field key sets of the function
	 * 
	 * @return the API field key sets of the function
	 */
	// Note this method is intentionally not called getAPIKeys, this is because it should not form part of the bean
	// serialisation
	public List<String> updateAPIKeys()
	{
		return updateAPIKeys;
	}

	/**
	 * Add an API field set into the function
	 * <p>
	 * Note that if adding a real API (i.e. not a Table or a puesdo API), then the addReservedFields method must be called before
	 * calling this method.
	 * 
	 * @param fieldSet
	 *            the field set to be added
	 * @throws EQException
	 *             - if an API field set with the same ID already exists
	 */
	public void addUpdateAPI(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (updateAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getString("Function.FieldSetExists"))); //$NON-NLS-1$
		}
		else
		{
			updateAPIs.add(fieldSet);
			updateAPIKeys.add(key);
			fieldSet.setFunction(this);
		}
	}
	/**
	 * Determines whether the given update API exists in the function.
	 * <p>
	 * This is used by the Field Copy facility.
	 * 
	 * @param apiId
	 * @return a <code>boolean</code> indicating whether the API exists
	 */
	public boolean hasUpdateAPI(String apiId)
	{
		if (!updateAPIKeys.contains(apiId))
		{
			return false;
		}
		else
		{

			return true;
		}
	}

	/**
	 * Return the API field set identified by the id
	 * 
	 * @param key
	 *            - the API field set ID
	 * 
	 * @return the API field set identified by the key
	 * 
	 * @throws EQException
	 *             - if the API field set does not exist
	 */
	public APIFieldSet getUpdateAPI(String key) throws EQException
	{
		if (!updateAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.UpdateFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = updateAPIKeys.indexOf(key);
			return updateAPIs.get(keyIndex);
		}
	}
	/**
	 * Return the API field set sequence identified by the id
	 * 
	 * @param key
	 *            - the API field set ID
	 * 
	 * @return the API field set sequence identified by the key
	 * 
	 * @throws EQException
	 *             - if the API field set does not exist
	 */
	public int rtvUpdateAPISequence(String key) throws EQException
	{
		if (!updateAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.UpdateFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			int keyIndex = updateAPIKeys.indexOf(key);
			return keyIndex;
		}
	}
	/**
	 * Remove the API field set from the function
	 * 
	 * @param fieldSet
	 *            - the API field set to be removed
	 * 
	 * @throws EQException
	 *             - if the API field set does not exist
	 */
	public void removeUpdateAPI(APIFieldSet fieldSet) throws EQException
	{
		String key = fieldSet.getId();
		if (!updateAPIKeys.contains(key))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.UpdateFieldSetDoesNotExist", key))); //$NON-NLS-1$
		}
		else
		{
			List<Field> fields = fieldSet.getFields();
			for (Field field : fields)
			{
				removeUpdateMappings(field);
				removeValidateMappings(field);
				removeLoadMappings(field);
			}
			int keyIndex = updateAPIKeys.indexOf(key);
			updateAPIs.remove(keyIndex);
			updateAPIKeys.remove(keyIndex);
		}
	}

	/**
	 * Remove the API field set from the function
	 * 
	 * @param id
	 *            - the String ID of the field set to be removed
	 * 
	 * @throws EQException
	 *             - if the API field set does not exist
	 */
	public void removeUpdateAPI(String id) throws EQException
	{
		if (!updateAPIKeys.contains(id))
		{
			throw (new EQException(LanguageResources.getFormattedString("Function.UpdateFieldSetDoesNotExist", id))); //$NON-NLS-1$
		}
		else
		{
			FieldSet fieldSet = getUpdateAPI(id);
			List<Field> fields = fieldSet.getFields();
			for (Field field : fields)
			{
				removeUpdateMappings(field);
				removeValidateMappings(field);
				removeLoadMappings(field);
			}
			int keyIndex = updateAPIKeys.indexOf(id);
			updateAPIs.remove(keyIndex);
			updateAPIKeys.remove(keyIndex);
		}
	}

	/**
	 * Retrieve the specified field from the input field set
	 * 
	 * @param fieldSetId
	 *            The Id of the FieldSet
	 * @param fieldKey
	 *            - field id of the field
	 * 
	 * @return the <code>Field</code> identified by the field name if it exists in the input field sets <br>
	 *         null if the field name does not exists in any of the input field sets
	 * @throws EQException
	 */
	public InputField rtvFieldFromInputFieldSet(String fieldSetId, String fieldKey) throws EQException
	{
		InputField field = null;
		InputFieldSet inputFieldSet = getInputFieldSet(fieldSetId);
		field = (InputField) inputFieldSet.getField(fieldKey);
		return field;
	}

	/**
	 * Retrieve the specified field from the API field set
	 * 
	 * @param fieldSetId
	 *            Id of the FieldSet
	 * @param fieldKey
	 *            - field id of the field
	 * @return the <code>Field</code> identified by the field name if it exists in the API field sets <br>
	 *         null if the field name does not exists in any of the input field sets
	 * 
	 */
	public Field rtvFieldFromUpdateAPI(String fieldSetId, String fieldKey)
	{
		Field field = null;
		try
		{
			APIFieldSet inputFieldSet = getUpdateAPI(fieldSetId);
			field = inputFieldSet.getField(fieldKey);
		}
		catch (EQException e)
		{
			return null;
		}
		return field;
	}

	/**
	 * Retrieves the index of a <code>FieldSet</code> The index returned corresponds to the index in the InputFieldSet list if
	 * found, otherwise to the index in the UpdateAPI list.
	 * 
	 * @param fieldSet
	 * @return the index of the <code>FieldSet</code>
	 */
	public int rtvFieldSetIndex(FieldSet fieldSet)
	{
		if (fieldSet instanceof InputFieldSet)
		{
			return inputFieldSets.indexOf(fieldSet);
		}
		else
		{
			return updateAPIs.indexOf(fieldSet);
		}
	}

	/**
	 * Adds an Update mapping to the Function.
	 * 
	 * @param fromPath
	 *            - The path of the Element being mapped from
	 * @param toPath
	 *            - The path of the Element being mapped to
	 * @throws EQException
	 *             - If the mapping already exists.
	 */
	public void addUpdateMapping(String fromPath, String toPath) throws EQException
	{
		addUpdateMapping(new Mapping(fromPath, toPath));
	}

	/**
	 * Removes an Update mapping from the Function.
	 * 
	 * @param toPath
	 *            - The path of the Element being mapped to
	 * @throws EQException
	 *             - If the mapping does not exists.
	 */
	public void removeUpdateMapping(String toPath) throws EQException
	{
		int index = ((MappingList) updateMappings).getSourceIndex(toPath);
		if (index == -1)
		{
			throw (new EQException(LanguageResources.getString("Language.MappingDoesNotExist"))); //$NON-NLS-1$
		}
		else
		{
			updateMappings.remove(index);
		}
	}

	/**
	 * Removes a From Update mapping from the Function.
	 * 
	 * @param fromPath
	 *            - The path of the Element being mapped from
	 * @throws EQException
	 *             - If the mapping does not exists.
	 */
	public void removeFromUpdateMapping(String fromPath) throws EQException
	{
		int index = ((MappingList) updateMappings).getTargetIndex(fromPath);
		if (index == -1)
		{
			throw (new EQException(LanguageResources.getString("Language.MappingDoesNotExist"))); //$NON-NLS-1$
		}
		else
		{
			((MappingList) updateMappings).remove(index);
		}
	}

	/**
	 * Removes all Update mappings from the Function given a <code>Field</code>
	 * 
	 * @param field
	 *            - The <code>Field</code> from which to remove the update mappings
	 */
	public void removeUpdateMappings(Field field)
	{
		String path = field.rtvPath();
		MappingList updateList = (MappingList) updateMappings;
		int index = updateList.getSourceIndex(path);
		while (index != -1)
		{
			updateList.remove(index);
			index = updateList.getSourceIndex(path);
		}
		index = updateList.getTargetIndex(path);
		while (index != -1)
		{
			updateList.remove(index);
			index = updateList.getTargetIndex(path);
		}
	}

	/**
	 * Does the Function have a specific From update mapping
	 * 
	 * @param path
	 *            - the Path of the Element
	 * @return - true if the Function has a from update mapping of the given path, otherwise false
	 */
	public boolean hasFromUpdateMapping(String path)
	{
		// if it has a from mapping the field path will be in the 'To' list
		return (((MappingList) updateMappings).getTargetIndex(path) != -1);
	}

	/**
	 * Return the specific From update mapping
	 * 
	 * @param path
	 *            - the Path of the Element
	 * 
	 * @return the path
	 */
	public String rtvFromUpdateMapping(String path)
	{
		// if it has a from mapping the field path will be in the 'To' list
		int index = ((MappingList) updateMappings).getTargetIndex(path);
		if (index == -1)
		{
			return "";
		}
		else
		{
			return updateMappings.get(index).getSource();
		}
	}

	/**
	 * Does the Function have a specific To update mapping
	 * 
	 * @param path
	 *            - the Path of the Element
	 * @return - true if the Function has a To update mapping of the given path, otherwise false
	 */
	public boolean hasToUpdateMapping(String path)
	{
		return (((MappingList) updateMappings).getSourceIndex(path) != -1);
	}
	/**
	 * Get the update mappings given a <code>Field</code>
	 * 
	 * @param field
	 *            - The Field to retrieve the From update mappings for
	 * @return an <code>ArrayList<String></code> of all the from update mappings
	 */
	public List<Mapping> getUpdateMappings(Field field)
	{
		return MappingToolbox.getMappings(updateMappings, field);
	}
	/**
	 * Get the From update mappings given a <code>Field</code>
	 * 
	 * @param field
	 *            - The Field to retrieve the From update mappings for
	 * @return an <code>ArrayList<String></code> of all the from update mappings
	 */
	public List<String> getUpdateMappingsFrom(Field field)
	{
		String path = field.rtvPath(); // Get this once....

		// check in To and return from indexes
		ArrayList<String> result = new ArrayList<String>();

		for (Mapping mapping : updateMappings)
		{
			if (mapping.getTarget().equals(path))
			{
				result.add(mapping.getSource());
			}
		}
		return result;
	}

	/**
	 * Get the To update mappings given a <code>Field</code>
	 * 
	 * @param field
	 *            - The Field to retrieve the To update mappings for
	 * @return an <code>ArrayList<String></code> of all the To update mappings
	 */
	public List<String> getUpdateMappingsTo(Field field)
	{
		String path = field.rtvPath(); // Get this once....

		// check in To and return from indexes
		ArrayList<String> result = new ArrayList<String>();

		for (Mapping mapping : updateMappings)
		{
			if (mapping.getSource().equals(path))
			{
				result.add(mapping.getTarget());
			}
		}
		return result;

	}

	/**
	 * Removes the mapping to this field <b>FROM</b> another field
	 * <p>
	 * Note that there can only be one mapping to a field.
	 * 
	 * @param fromPath
	 * @throws EQException
	 */
	public void removeFromValidateMapping(String fromPath) throws EQException
	{
		int index = ((MappingList) validateMappings).getTargetIndex(fromPath);
		if (index == -1)
		{
			throw (new EQException(LanguageResources.getString("Language.MappingDoesNotExist"))); //$NON-NLS-1$
		}
		else
		{
			((MappingList) validateMappings).remove(index);
		}
	}

	/**
	 * Removes all validate mappings from this field <b>TO</b> other fields
	 * 
	 * @param path
	 */
	public void removeValidateMappingsTo(String path)
	{
		int index = ((MappingList) validateMappings).getSourceIndex(path);
		while (index != -1)
		{
			((MappingList) validateMappings).remove(index);
			index = ((MappingList) validateMappings).getSourceIndex(path);
		}
	}

	/**
	 * Removes all validate mappings for the field.
	 * 
	 * @param field
	 */
	public void removeValidateMappings(Field field)
	{
		String path = field.rtvPath();
		int index = ((MappingList) validateMappings).getTargetIndex(path);
		if (index != -1)
		{
			((MappingList) validateMappings).remove(index);
		}
		removeValidateMappingsTo(path);
	}

	/**
	 * Adds a validate mapping given a from and to path
	 * 
	 * @param fromPath
	 *            - the path of the element mapped from
	 * @param toPath
	 *            - the path of the element mapped to
	 * @throws EQException
	 *             - if the mapping already exists on the function
	 */
	public void addValidateMapping(String fromPath, String toPath) throws EQException
	{
		addValidateMapping(new Mapping(fromPath, toPath));
	}

	/**
	 * Returns true if the function has a mapping from the passed element's path
	 * 
	 * @param path
	 *            - the path of the element
	 * @return true if the function has a mapping from the passed element's path, otherwise false
	 */
	public boolean hasFromValidateMapping(String path)
	{
		return (((MappingList) validateMappings).getTargetIndex(path) != -1);
	}
	/**
	 * Returns true if the function has a mapping from the passed element's path
	 * 
	 * @param path
	 *            - the path of the element
	 * @return true if the function has a mapping from the passed element's path, otherwise false
	 */
	public String rtvFromValidateMapping(String path)
	{
		// if it has a from mapping the field path will be in the 'To' list
		int index = ((MappingList) validateMappings).getTargetIndex(path);
		if (index == -1)
		{
			return "";
		}
		else
		{
			return validateMappings.get(index).getSource();
		}
	}

	/**
	 * Returns true if the function has a mapping to the passed element's path
	 * 
	 * @param path
	 *            - the path of the element
	 * @return true if the function has a mapping to the passed element's path, otherwise false
	 */
	public boolean hasToValidateMapping(String path)
	{
		return (((MappingList) validateMappings).getSourceIndex(path) != -1);
	}
	/**
	 * Returns an <code>ArrayList<String></code> of the validate mappings from the passed <code>Field</code>
	 * 
	 * @param field
	 *            - the <code>Field</code> to retrieve the from validate mappings for
	 * @return an <code>ArrayList<String></code> of the validate mappings from the passed <code>Field</code>
	 */
	public List<String> getValidateMappingsFrom(Field field)
	{
		String path = field.rtvPath(); // Get this once....
		return getValidateMappingsFrom(path);
	}
	/**
	 * Get the validate mappings given a <code>Field</code>
	 * 
	 * @param field
	 *            - The Field to retrieve the From update mappings for
	 * @return an <code>ArrayList<Mapping></code> of all the load mappings
	 */
	public List<Mapping> getValidateMappings(Field field)
	{
		return MappingToolbox.getMappings(validateMappings, field);
	}
	/**
	 * Returns an <code>ArrayList<String></code> of the validate mappings from the passed field ID
	 * 
	 * @param path
	 *            - the path to retrieve the from validate mappings for
	 * @return an <code>ArrayList<String></code> of the validate mappings from the passed field ID
	 */
	public List<String> getValidateMappingsFrom(String path)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (Mapping mapping : validateMappings)
		{
			if (mapping.getTarget().equals(path))
			{
				result.add(mapping.getSource());
			}
		}
		return result;
	}

	/**
	 * Returns an <code>ArrayList<String></code> of the validate mappings to the passed field ID
	 * 
	 * @param fieldPath
	 *            - the path to retrieve the from validate mappings for
	 * 
	 * @return an <code>ArrayList<String></code> of the validate mappings to the passed field ID
	 */
	public List<String> getValidateMappingsTo(String fieldPath)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (Mapping mapping : validateMappings)
		{
			if (mapping.getSource().equals(fieldPath))
			{
				result.add(mapping.getTarget());
			}
		}
		return result;
	}

	/**
	 * Returns an <code>ArrayList<String></code> of the validate mappings to the passed <code>Field</code>
	 * 
	 * @param field
	 *            - the <code>Field</code> to retrieve the from validate mappings for
	 * @return an <code>ArrayList<String></code> of the validate mappings to the passed <code>Field</code>
	 */
	public List<String> getValidateMappingsTo(Field field)
	{
		String path = field.rtvPath();
		return getValidateMappingsTo(path);
	}

	/**
	 * Removes all load mappings from this field <b>TO</b> other fields
	 * 
	 * @param fromPath
	 * @throws EQException
	 */
	public void removeFromLoadMapping(String fromPath) throws EQException
	{
		int index = ((MappingList) loadMappings).getTargetIndex(fromPath);
		if (index == -1)
		{
			throw (new EQException(LanguageResources.getString("Language.MappingDoesNotExist"))); //$NON-NLS-1$
		}
		else
		{
			((MappingList) loadMappings).remove(index);
		}
	}

	/**
	 * Adds a load mapping
	 * 
	 * @param mapping
	 *            the load mapping
	 */
	public void addLoadMapping(Mapping mapping)
	{
		if (((MappingList) loadMappings).getTargetIndex(mapping.getTarget()) != -1)
		{
			// Only one mapping is allowed to a field
			throw (new EQRuntimeException(LanguageResources.getString("Language.MappingExists"))); //$NON-NLS-1$
		}
		else
		{
			((MappingList) loadMappings).add(mapping);
		}
	}

	/**
	 * Adds a Load mapping to the Function.
	 * 
	 * @param fromPath
	 *            - The path of the Element being mapped from
	 * @param toPath
	 *            - The path of the Element being mapped to
	 * @throws EQException
	 *             - If the mapping already exists.
	 */
	public void addLoadMapping(String fromPath, String toPath) throws EQException
	{
		addLoadMapping(new Mapping(fromPath, toPath));
	}

	/**
	 * 
	 * @param toPath
	 * @throws EQException
	 */
	public void removeLoadMapping(String toPath) throws EQException
	{
		int index = ((MappingList) loadMappings).getSourceIndex(toPath);
		if (index == -1)
		{
			throw (new EQException(LanguageResources.getString("Language.MappingDoesNotExist"))); //$NON-NLS-1$
		}
		else
		{
			((MappingList) loadMappings).remove(index);
		}
	}

	public boolean hasFromLoadMapping(String path)
	{
		// if it has a from mapping the field path will be in the 'To' list
		return (((MappingList) loadMappings).getTargetIndex(path) != -1);
	}

	/**
	 * Return the specific From load mapping
	 * 
	 * @param path
	 *            - the Path of the Element
	 * 
	 * @return the path of the from load mapping
	 */
	public String rtvFromLoadMapping(String path)
	{
		// if it has a from mapping the field path will be in the 'To' list
		int index = ((MappingList) loadMappings).getTargetIndex(path);
		if (index == -1)
		{
			return "";
		}
		else
		{
			return loadMappings.get(index).getSource();
		}
	}

	public boolean hasToLoadMapping(String path)
	{
		// if it has a from mapping the field path will be in the 'To' list
		return (((MappingList) loadMappings).getSourceIndex(path) != -1);
	}

	/**
	 * Returns an <code>ArrayList</code> of String which are the load mappings from the passed <code>Field</code>
	 * 
	 * @param field
	 *            - the <code>Field</code> to retrieve the from load mappings for
	 * @return an <code>ArrayList<String></code> of the load mappings from the passed <code>Field</code>
	 */
	public List<String> getLoadMappingsFrom(Field field)
	{
		String path = field.rtvPath(); // Get this once....
		return getLoadMappingsFrom(path);
	}
	/**
	 * Get the load mappings given a <code>Field</code>
	 * 
	 * @param field
	 *            - The Field to retrieve the From update mappings for
	 * @return an <code>ArrayList<Mapping></code> of all the load mappings
	 */
	public List<Mapping> getLoadMappings(Field field)
	{
		return MappingToolbox.getMappings(loadMappings, field);
	}
	public List<String> getLoadMappingsFrom(String fieldPath)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (Mapping mapping : loadMappings)
		{
			if (mapping.getTarget().equals(fieldPath))
			{
				result.add(mapping.getSource());
			}
		}
		return result;
	}

	public List<String> getLoadMappingsTo(String fieldPath)
	{
		ArrayList<String> result = new ArrayList<String>();
		for (Mapping mapping : loadMappings)
		{
			if (mapping.getSource().equals(fieldPath))
			{
				result.add(mapping.getTarget());
			}
		}
		return result;
	}

	/**
	 * @return the getLoadMappingsTo
	 */
	public List<String> getLoadMappingsTo(Field field)
	{
		String path = field.rtvPath();
		return getLoadMappingsTo(path);
	}

	/**
	 * Removes all load mappings from the passed <code>Field</code>
	 * 
	 * @param field
	 *            - the field from which to remove all the load mappings
	 */
	public void removeLoadMappings(Field field)
	{
		final String path = field.rtvPath();
		MappingList loadList = (MappingList) loadMappings;
		int index = loadList.getSourceIndex(path);
		while (index != -1)
		{
			loadList.remove(index);
			index = loadList.getSourceIndex(path);
		}
		index = loadList.getTargetIndex(path);
		while (index != -1)
		{
			loadList.remove(index);
			index = loadList.getTargetIndex(path);
		}

		String subFieldPath = path + MappingToolbox.SUBFIELD_INPUT;
		index = loadList.getTargetIndex(subFieldPath);
		while (index != -1)
		{
			loadList.remove(index);
			index = loadList.getTargetIndex(subFieldPath);
		}

		subFieldPath = path + MappingToolbox.SUBFIELD_OUTPUT;
		index = loadList.getTargetIndex(subFieldPath);
		while (index != -1)
		{
			loadList.remove(index);
			index = loadList.getTargetIndex(subFieldPath);
		}

		subFieldPath = path + MappingToolbox.SUBFIELD_PRIMITIVE;
		index = loadList.getTargetIndex(subFieldPath);
		while (index != -1)
		{
			loadList.remove(index);
			index = loadList.getTargetIndex(subFieldPath);
		}
	}

	/**
	 * Determines if the supplied path denotes a subfield
	 * 
	 * @return boolean indicating whether the path is a subfield path or not
	 */
	public boolean isSubfield(String path)
	{
		String[] subfieldMapping = path.split("\\.");
		return (subfieldMapping.length > 1 && subfieldMapping[subfieldMapping.length - 2].equals(SUBFIELD));
	}

	/**
	 * Determines the subfield type, for the supplied Path String
	 * 
	 * @param path
	 *            A String containing an element's path within the bean model
	 * 
	 * @return A String containing the subfield type ("Primitive", "Input" or "Output"), or an empty String if not a subfield
	 */
	public String getSubfield(String path)
	{
		String result = "";
		String[] subfieldMapping = path.split("\\.");
		if (subfieldMapping.length > 1 && subfieldMapping[subfieldMapping.length - 2].equals(SUBFIELD))
		{
			result = subfieldMapping[subfieldMapping.length - 1];
		}
		return result;
	}

	/**
	 * Traverse the path and get the element referenced
	 * 
	 * @param path
	 *            The path of the Element to return
	 * @return the <code>Element</code> with the path specified
	 * @throws EQException
	 */
	public Element getElement(String path) throws EQException
	{
		// Store the parent element to the one we are trying to get. We start at the top with <code>this</code> function.
		Element parentElement = this;
		Element childElement = null;
		// cut up the path into a meaningful array
		String[] elements = path.split("\\.");
		for (int x = 1; x < elements.length; x = x + 2)
		{
			String childElementType = elements[x - 1];
			String childElementId = elements[x];
			// If we have a Function then the child is expected to be either a InputFieldSet or a APIFieldSet
			if (parentElement instanceof Function)
			{
				Function function = (Function) parentElement;
				if (childElementType.equals(InputFieldSet.class.getSimpleName()))
				{
					childElement = function.getInputFieldSet(childElementId);
				}
				else if (childElementType.equals(APIFieldSet.class.getSimpleName()))
				{
					childElement = function.getUpdateAPI(childElementId);
				}
				else if (childElementType.equals(WorkField.class.getSimpleName()))
				{
					childElement = function.getWorkFields().get(childElementId);
				}
			}
			// If we have a InputFieldSet then the child is expected to be either of InputField or APIFieldSet
			if (parentElement instanceof InputFieldSet)
			{
				InputFieldSet inputFieldSet = (InputFieldSet) parentElement;
				if (childElementType.equals(InputField.class.getSimpleName()))
				{
					childElement = inputFieldSet.getInputField(childElementId);
				}
				else if (childElementType.equals(APIFieldSet.class.getSimpleName()))
				{
					childElement = inputFieldSet.getLoadAPI(childElementId);
				}
			}
			// If we have a APIFieldSet then the child is expected to be an APIField
			if (parentElement instanceof APIFieldSet)
			{
				APIFieldSet apiFieldSet = (APIFieldSet) parentElement;
				if (childElementType.equals(APIField.class.getSimpleName()))
				{
					childElement = apiFieldSet.getAPIField(childElementId);
				}
			}
			// If we have a PVFieldSet then the child is expected to be an PVField
			if (parentElement instanceof PVFieldSet)
			{
				PVFieldSet pvFieldSet = (PVFieldSet) parentElement;
				if (childElementType.equals(PVField.class.getSimpleName()))
				{
					childElement = pvFieldSet.getPVField(childElementId);
				}
			}
			// If we have an InputField then the child is expected to be an PVFieldSet
			if (parentElement instanceof InputField)
			{
				InputField inputField = (InputField) parentElement;
				if (childElementType.equals(PVFieldSet.class.getSimpleName()))
				{
					childElement = inputField.getPvFieldSet(childElementId);
				}
				// If we got down as far as a subfield then we have got as far as we need to go.
				if (childElementType.equals(SUBFIELD))
				{
					childElement = parentElement;
				}
			}
			// Now if the child hasn't yet been determined then its time to dig our heels in and throw an exception
			if (childElement != null)
			{
				parentElement = childElement;
				childElement = null;
			}
			else
			{
				// We have a problem
				throw new EQException("Cannot find required type " + childElementType + " with id " + childElementId + " in the "
								+ parentElement.getClass().getSimpleName() + " with id " + parentElement.getId());
			}
		}
		return parentElement;
	}

	/**
	 * @return the loadMappings
	 */
	public List<Mapping> getLoadMappings()
	{
		return loadMappings;
	}
	/**
	 * @return a copy of the loadMappings
	 */
	public MappingList cloneLoadMappings()
	{
		return new MappingList(loadMappings);
	}
	/**
	 * @return the updateMappings
	 */
	public List<Mapping> getUpdateMappings()
	{
		return updateMappings;
	}
	/**
	 * @return a copy of the loadMappings
	 */
	public MappingList cloneUpdateMappings()
	{
		return new MappingList(updateMappings);
	}
	/**
	 * @return the validate Mappings
	 */
	public List<Mapping> getValidateMappings()
	{
		return validateMappings;
	}
	/**
	 * @return a copy of the validateMappings
	 */
	public MappingList cloneValidateMappings()
	{
		return new MappingList(validateMappings);
	}
	/**
	 * Adds a validate mapping definition
	 */
	public void addValidateMapping(Mapping mapping)
	{
		if (((MappingList) validateMappings).getTargetIndex(mapping.getTarget()) != -1)
		{
			// Only one mapping is allowed to a field
			throw (new EQRuntimeException(LanguageResources.getString("Language.MappingExists"))); //$NON-NLS-1$
		}
		else
		{
			((MappingList) validateMappings).add(mapping);
		}
	}

	/**
	 * Adds an update mapping
	 * 
	 * @param mapping
	 *            the update mapping
	 */
	public void addUpdateMapping(Mapping mapping)
	{
		if (((MappingList) updateMappings).getTargetIndex(mapping.getTarget()) != -1)
		{
			// Only one mapping is allowed to a field
			throw (new EQRuntimeException(LanguageResources.getString("Language.MappingExists"))); //$NON-NLS-1$
		}
		else
		{
			((MappingList) updateMappings).add(mapping);
		}
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName()
	{
		// TODO: Remove this temporary defaulting
		if (packageName == null)
		{
			packageName = "com.misys.equation.screens";
		}
		return packageName;
	}

	/**
	 * @param packageName
	 *            the packageName to set
	 */
	public void setPackageName(String packageName)
	{
		this.packageName = packageName;
	}

	/**
	 * Validate the bean
	 * 
	 * @param messageContainer
	 *            a <code>MessageContainer</code> to add the messages to
	 * @param subSet
	 *            a String specifying a validation subset (one of {@link #OVERVIEW_VALIDATION},
	 *            {@link #UPDATE_ASSIGNMENT_VALIDATION} or {@link #PROPERTIES_VALIDATION}), or null for full validation
	 * @param includeChildren
	 *            Whether to validate child objects as well
	 * @return a boolean, not currently used by any caller.
	 * @see IValidation#validateBean
	 */
	public boolean validateBean(MessageContainer messageContainer, String subSet, boolean includeChildren)
	{
		String option = getId();
		ProblemLocation problemLocation = new ProblemLocation(Function.class.getSimpleName(), getId());

		if (subSet == null || subSet.equals(OVERVIEW_VALIDATION))
		{
			if (option.length() == 0)
			{
				messageContainer.addErrorMessageId("Language.OptionMustBeSpecified", problemLocation);
			}
			else
			{
				String text = ValidationHelper.validateOptionSyntax(option);
				if (text != null)
				{
					messageContainer.addErrorMessage(text);
				}
			}
			// Module Identifier must be 3 characters
			if (getModuleId().length() != 3)
			{
				messageContainer.addErrorMessageId("Langugae.ModuleIdMustBeThreeCharacters", getModuleId(), problemLocation);
			}
			// A service with a blank label may at this point contain '<none>' due to
			// defaulting when the xml file is processed as a bean
			if (getLabel().length() == 0 || getLabel().trim().equals(DEFAULT_TEXT_VALUE))
			{
				messageContainer.addErrorMessageId("Language.ServiceTitleMustBeEntered", problemLocation); //$NON-NLS-1$
			}
			else if (getLabel().length() > 35)
			{
				messageContainer.addErrorMessageId("Language.ServiceTitleCannotBeLongerThan35Characters", problemLocation); //$NON-NLS-1$
			}

			// if enhanced XSD, then perform additional validation on the title
			if (chkXSDGeneric() && !XSDToolbox.isValidForXSDMustStartWithAToZ(getLabel()))
			{
				messageContainer.addErrorMessageId("Language.ServiceTitleCannotStartWithNumeric", problemLocation);
			}

			if (getBaseLanguage().length() == 0)
			{
				messageContainer.addErrorMessageId("Language.BaseLanguageMustBeEntered", problemLocation); //$NON-NLS-1$
			}

			// Rolldown from head to ML 2011.05.19 start
			// No descriptions will be mandatory
			// if (getDescription().length() == 0 || getDescription().trim().equals(DEFAULT_TEXT_VALUE))
			// {
			//				messageContainer.addErrorMessageId("Language.ServiceDescriptionMustBeEntered", problemLocation); //$NON-NLS-1$
			// }
			// Rolldown from head to ML 2011.05.19 end
			if (!isAllowedAdd() && !isAllowedDel() && !isAllowedMaint() && !isAllowedEnq())
			{
				messageContainer.addErrorMessageId("Language.ServiceModeMustBeEntered", problemLocation);
			}
			// Validate xsdVersion if entered
			if (chkXSDGeneric() && getXsdVersion().length() == 0)
			{
				messageContainer.addErrorMessageId("Language.XsdVersionMustBeEntered", problemLocation); //$NON-NLS-1$
			}
			if (getXsdVersion().length() > 0)
			{
				if (!getXsdVersion().contains("."))
				{
					messageContainer.addErrorMessageId("Language.XsdVersionPatternInvalid", problemLocation); //$NON-NLS-1$
				}
				else
				{
					String[] parts = getXsdVersion().split("\\.");
					if (parts.length != 2)
					{
						messageContainer.addErrorMessageId("Language.XsdVersionPatternInvalid", problemLocation); //$NON-NLS-1$}

					}
					else
					{
						try
						{
							Integer num = new Integer(parts[0]);
						}
						catch (NumberFormatException e)
						{
							messageContainer.addErrorMessageId("Language.XsdVersionPatternInvalid", problemLocation); //$NON-NLS-1$}
						}
						try
						{
							Integer num = new Integer(parts[1]);
						}
						catch (NumberFormatException e)
						{
							messageContainer.addErrorMessageId("Language.XsdVersionPatternInvalid", problemLocation); //$NON-NLS-1$}
						}
					}
				}
			}
		}

		if (subSet == null || OVERVIEW_VALIDATION.equals(subSet) || PROPERTIES_VALIDATION.equals(subSet))
		{
			// Enquiry services won't call update user exits,
			if (isAllowedEnq()
							&& (getPostUpdateMicroFlow().equals(Function.POST_UPDATE_MICROFLOW_YES) || getPostUpdateMicroFlow()
											.equals(Function.POST_UPDATE_MICROFLOW_SCRIPT)))
			{
				messageContainer.addErrorMessageId("Language.UpdateUserExitMicroflowNotValidForEnquiry", problemLocation);
			}
		}

		// Check for a Primary inputFieldSet
		if (!inputFieldSetKeys.contains(PRIMARY_ID))
		{
			messageContainer.addErrorMessageId("Language.NoPrimaryInputFieldSet", problemLocation);
			// If the service doesn't contain a Primary InputFieldSet, then various other
			// validation code that assumes a Primary InputFieldSet will fail. Exit now:
			return false;
		}

		if (subSet == null)
		{
			// A service is meaningless without input fields:
			boolean hasFields = false;
			for (InputFieldSet set : inputFieldSets)
			{
				if (set.getFields().size() > 0)
				{
					hasFields = true;
					break;
				}
			}
			if (!hasFields)
			{
				messageContainer.addErrorMessageId("Language.ServiceHasNoFields", problemLocation); //$NON-NLS-1$
			}
			else
			{
				// Validate total journal file buffer size
				ValidationHelper.validateServiceJournalFileBufferSize(this, messageContainer, problemLocation);
				ValidationHelper.validateServiceKeySize(this, messageContainer, problemLocation);
			}
		}
		// Validate Mappings
		for (Mapping mapping : getLoadMappings())
		{
			try
			{
				if (validLoadMapping(mapping) == false)
				{
					messageContainer.addErrorMessageId("Language.InvalidMapping", new String[] { mapping.toString() },
									problemLocation);
				}

			}
			catch (EQException e)
			{
				messageContainer.addErrorMessageId("Language.InvalidMapping", new String[] { mapping.toString() }, problemLocation);
			}
		}
		for (Mapping mapping : getValidateMappings())
		{
			try
			{
				if (validValidateMapping(mapping) == false)
				{
					messageContainer.addErrorMessageId("Language.InvalidMapping", new String[] { mapping.toString() },
									problemLocation);
				}

			}
			catch (EQException e)
			{
				messageContainer.addErrorMessageId("Language.InvalidMapping", new String[] { mapping.toString() }, problemLocation);
			}
		}
		for (Mapping mapping : getUpdateMappings())
		{

			if (validUpdateMapping(mapping) == false)
			{
				messageContainer.addErrorMessageId("Language.InvalidMapping", new String[] { mapping.toString() }, problemLocation);
			}

		}
		// Validate EFC
		if (subSet == null || subSet.equals(UPDATE_ASSIGNMENT_VALIDATION))
		{
			if (checkEfcEnabled())
			{
				validateEFC(messageContainer, problemLocation);
			}
		}

		if (includeChildren)
		{
			validateChildren(messageContainer, subSet, includeChildren, problemLocation);
		}

		if (Function.POST_UPDATE_MICROFLOW_SCRIPT.equals(postUpdateMicroFlow))
		{
			ValidationHelper.validateBooleanELExpression(postUpdateMicroFlowExpression, this, messageContainer,
							ValidationHelper.BooleanELType.POST_UPDATE_MICROFLOW_EXPRESSION, problemLocation);
		}

		// validate the groups id, and make sure it is unique
		if (includeChildren && chkXSDGeneric())
		{
			XSDStructureHelper.validateBean(this, messageContainer);
		}

		return false;
	}
	private void validateChildren(MessageContainer messageContainer, String subSet, boolean includeChildren,
					ProblemLocation problemLocation)
	{
		for (InputFieldSet set : inputFieldSets)
		{
			set.validateBean(messageContainer, subSet, includeChildren);
		}
		for (APIFieldSet apiFieldSet : updateAPIs)
		{
			apiFieldSet.validateBean(messageContainer, subSet, includeChildren);
		}
		for (WorkField workField : workFields)
		{
			workField.validateBean(messageContainer, subSet, includeChildren);
		}
		for (Mapping contextMapping : contextMappings)
		{
			ValidationHelper.validateContextMappingELExpression(contextMapping.getSource(), this, contextMapping.getTarget(),
							messageContainer, problemLocation);
		}
	}

	private void validateEFC(MessageContainer messageContainer, ProblemLocation problemLocation)
	{
		try
		{
			// Obtain the EFC update API FieldSet to check for mappings
			APIFieldSet efcFieldSet = getUpdateAPI(Function.EFC_FIELDSET);

			// Check for inconsistent account mappings (these should be reported before checking for
			// missing/multiple keys
			if (!isValidUpdateAssignmentCount(efcFieldSet, new String[] { FunctionCommonData.EFC_EDAB, FunctionCommonData.EFC_EDAN,
							FunctionCommonData.EFC_EDAS }))
			{
				messageContainer.addErrorMessageId("Language.InconsistentEFCDebitAccountAssigments", problemLocation); //$NON-NLS-1$	
			}
			// Check for inconsistent deal mappings:
			if (!isValidUpdateAssignmentCount(efcFieldSet, new String[] { FunctionCommonData.EFC_EBRNM,
							FunctionCommonData.EFC_EDLP, FunctionCommonData.EFC_EDLR }))
			{
				messageContainer.addErrorMessageId("Language.InconsistentEFCDealAssigments", problemLocation); //$NON-NLS-1$	
			}

			int entitiesAssigned = 0;
			if (hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_ECMR))
			{
				entitiesAssigned++;
			}
			if (hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_EBRNM))
			{
				entitiesAssigned++;
			}
			if (hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_EDAB))
			{
				entitiesAssigned++;
			}
			if (entitiesAssigned == 0)
			{
				// If no mappings are made to any entities, then this is an error.
				messageContainer.addErrorMessageId("Language.NoEFCMappings", problemLocation); //$NON-NLS-1$	
			}
			else if (entitiesAssigned > 1)
			{
				// If multiple entity mappings are made, then this is an error.
				messageContainer.addErrorMessageId("Language.MultipleEFCEntitiesNotAllowed", problemLocation); //$NON-NLS-1$	
			}

			// Mustn't map to the credit amount without mapping to the debit amount:
			if (hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_ECAMT)
							&& !hasUpdateAssignment(efcFieldSet, FunctionCommonData.EFC_EDAMT))
			{
				// If EFC selected, but no mappings are made to any entities, then this is an error.
				messageContainer.addErrorMessageId("Language.EFCCreditAmountWithoutDebitAmount", problemLocation); //$NON-NLS-1$	
			}

			// More inconsistency checks
			if (!isValidUpdateAssignmentCount(efcFieldSet, new String[] { FunctionCommonData.EFC_EDAMT,
							FunctionCommonData.EFC_EDCCY }))
			{
				messageContainer.addErrorMessageId("Language.InconsistentEFCDebitAmountAssigments", problemLocation); //$NON-NLS-1$	
			}
			if (!isValidUpdateAssignmentCount(efcFieldSet, new String[] { FunctionCommonData.EFC_ECAMT,
							FunctionCommonData.EFC_ECCCY }))
			{
				messageContainer.addErrorMessageId("Language.InconsistentEFCCreditAmountAssigments", problemLocation); //$NON-NLS-1$	
			}
		}
		catch (EQException e)
		{
			LOG.error("validateBean", e);
		}
	}

	/**
	 * Returns the count of which fields in the supplied array have update assignments.
	 * <p>
	 * This method is used to ensure that all fields in a group are mapped, or none.
	 * 
	 * @param efcFieldSet
	 *            the EFC <code>APIFieldSet</code>
	 * 
	 * @param fieldNames
	 *            a <code>String[]</code> containing a set of field names
	 * 
	 * @return true if the results are consistent (either none or all assigned), otherwise false
	 */
	private boolean isValidUpdateAssignmentCount(APIFieldSet efcFieldSet, String[] fieldNames)
	{
		int count = 0;
		for (String fieldName : fieldNames)
		{
			if (hasUpdateAssignment(efcFieldSet, fieldName))
			{
				count++;
			}
		}
		return count == 0 || count == fieldNames.length;
	}

	/**
	 * @return the collection of WorkFields
	 */
	public KeyedArrayList<WorkField> getWorkFields()
	{
		return workFields;
	}

	/**
	 * Adds a <code>WorkField</code>
	 * 
	 * @param workField
	 *            the <code>WorkField</code> to add
	 */
	public void addWorkField(WorkField workField)
	{
		// WorkField objects have the Function as their parent
		workField.setParent(this);
		workFields.add(workField, workField.getId());
	}
	/**
	 * Sort the WorkFields
	 * 
	 */
	public void sortWorkFields()
	{
		Collections.sort(getWorkFields());
		KeyedArrayList<WorkField> sortedList = (KeyedArrayList<WorkField>) workFields.clone();
		workFields.clear();
		workFields.getKeys().clear();
		for (WorkField workField : sortedList)
		{
			workFields.add(workField, workField.getId());
		}

	}
	/**
	 * Adds a <code>WorkField</code>
	 * 
	 * @param location
	 *            the location to add the workfield
	 * @param workField
	 *            the <code>WorkField</code> to add
	 */
	public void addWorkField(int location, WorkField workField)
	{
		// WorkField objects have the Function as their parent
		workField.setParent(this);
		workFields.add(location, workField, workField.getId());
	}
	/**
	 * Add a reserved name to the list
	 * 
	 * @param name
	 *            - the reserved name to add
	 */
	private static void addReservedName(String name)
	{
		if (!reservedNames.contains(name))
		{
			reservedNames.add(name);
		}
	}

	/**
	 * Returns true if the list of reserved names in the Function contains the passed String name, otherwise false.
	 * 
	 * @param name
	 *            - the name to check for.
	 * @return true if the list of reserved names contains the name specified, otherwise false
	 */
	public static boolean containsReservedName(String name)
	{
		return reservedNames.contains(name);
	}

	/**
	 * Adds a reserved field set of a specified type and description
	 * 
	 * @param type
	 *            - type of reserved FieldSet.
	 * @param description
	 *            - a description of the reserved field set.
	 * @return The APIFieldSet added by this method
	 * @throws EQException
	 *             if the API field or UpdateAPI cannot be added to the Function
	 */
	public APIFieldSet addReservedFieldSet(String type, String description) throws EQException
	{
		// Create Update field sets for Journal details, EFC, CRM, and Enquiry journal header
		APIFieldSet apiFieldSet = generateReservedFieldSet(type, description);

		if (type.equals(Function.CRM_FIELDSET) && containsAPIFieldSet(Function.CRM_FIELDSET))
		{
			for (int i = 2; i < 999; i++)
			{
				if (!containsAPIFieldSet(Function.CRM_FIELDSET + i))
				{
					apiFieldSet.setId(Function.CRM_FIELDSET + i);
					break;
				}
			}
		}

		addUpdateAPI(apiFieldSet);
		return apiFieldSet;
	}

	/**
	 * Generate the reserved field set
	 * 
	 * @param type
	 *            - type of field set
	 * @param description
	 *            - description of the field set
	 * @return the field set
	 * 
	 * @throws EQException
	 */
	public static APIFieldSet generateReservedFieldSet(String type, String description) throws EQException
	{
		// Create Update field sets for Journal details, EFC, CRM, and Enquiry journal header
		APIFieldSet apiFieldSet = new APIFieldSet(type, description, description);
		Map<String, String> fields = null;
		if (G5_FIELDSET.equals(type) || GY_FIELDSET.equals(type))
		{
			// Use an ordered list to get the language resources in the
			// correct order
			String[] fieldList = type.equals(G5_FIELDSET) ? G5_FIELDS : GY_FIELDS;
			Map<String, String> unorderedList = LanguageResources.getList(type, 1);

			fields = new LinkedHashMap<String, String>();
			for (String field : fieldList)
			{
				String fullId = type + FunctionToolbox.UNDERSCORE + field;
				fields.put(fullId, unorderedList.get(fullId));
			}
		}
		else
		{
			fields = Toolbox.sortHashMapByValues(LanguageResources.getList(type, 1));
		}

		// Add all the fields as API fields
		for (Entry<String, String> entry : fields.entrySet())
		{
			String id = entry.getKey();
			APIField field = new APIField(id.split("_")[1], fields.get(id), fields.get(id));
			field.setDataType(LanguageResources.getString(id + "." + TYP));
			field.setSize(LanguageResources.getString(id + "." + LEN));
			apiFieldSet.addAPIField(field);
		}

		return apiFieldSet;
	}

	/**
	 * This method retrieves all the reserved Field/FieldSet IDs and adds them to the list of reserved names.
	 */
	private static void initialiseReservedNames()
	{
		// Add in the field set names
		addReservedName(GY_FIELDSET);
		addReservedName(G5_FIELDSET);
		addReservedName(CRM_FIELDSET);
		addReservedName(EFC_FIELDSET);

		// Create a full map of their fields
		Map<String, String> reservedFields = LanguageResources.getList(GY_FIELDSET, 1);
		reservedFields.putAll(LanguageResources.getList(GY_FIELDSET, 1));
		reservedFields.putAll(LanguageResources.getList(EFC_FIELDSET, 1));
		reservedFields.putAll(LanguageResources.getList(CRM_FIELDSET, 1));

		// All all these to the reserved names list
		for (String reservedField : reservedFields.keySet())
		{
			addReservedName(reservedField);
			addReservedName(reservedField.split(FunctionToolbox.UNDERSCORE)[1]);
		}
	}

	/**
	 * Returns true if the Function contains the passed reserved FieldSet type, otherwise false
	 * 
	 * @param type
	 *            - the type of reserved FieldSet. This can be either GY_FIELDSET, G5_FIELDSET, CRM_FIELDSET or EFC_FIELDSET
	 * @return true if the Function contains the specified reserved FieldSet, otherwise false.
	 */
	public boolean hasReservedFieldSet(String type)
	{
		return updateAPIKeys.contains(type);
	}

	/**
	 * Returns true if add mode is allowed for this Function, otherwise false
	 * 
	 * @return true if add mode is allowed, otherwise false
	 */
	public boolean isAllowedAdd()
	{
		return allowedAdd;
	}

	/**
	 * Sets whether add mode is allowed for this Function
	 * 
	 * @param allowedAdd
	 *            - a boolean
	 */
	public void setAllowedAdd(boolean allowedAdd)
	{
		this.allowedAdd = allowedAdd;
	}

	/**
	 * Returns true if maintain mode is allowed for this Function, otherwise false
	 * 
	 * @return true if maintain mode is allowed, otherwise false
	 */
	public boolean isAllowedMaint()
	{
		return allowedMaint;
	}

	/**
	 * Sets whether maintain mode is allowed for this Function
	 * 
	 * @param allowedMaint
	 *            - a boolean
	 */
	public void setAllowedMaint(boolean allowedMaint)
	{
		this.allowedMaint = allowedMaint;
	}

	/**
	 * Returns true if delete mode is allowed for this Function, otherwise false
	 * 
	 * @return true if delete mode is allowed, otherwise false
	 */
	public boolean isAllowedDel()
	{
		return allowedDel;
	}

	/**
	 * Sets whether delete mode is allowed for this Function
	 * 
	 * @param allowedDel
	 *            - a boolean
	 */
	public void setAllowedDel(boolean allowedDel)
	{
		this.allowedDel = allowedDel;
	}

	/**
	 * Returns true if enquiry mode is allowed for this Function, otherwise false
	 * 
	 * @return true if enquiry mode is allowed, otherwise false
	 */
	public boolean isAllowedEnq()
	{
		return allowedEnq;
	}

	/**
	 * Sets whether enquiry mode is allowed for this Function
	 * 
	 * @param allowedEnq
	 *            - a boolean
	 */
	public void setAllowedEnq(boolean allowedEnq)
	{
		this.allowedEnq = allowedEnq;
	}

	/**
	 * Returns true if this Function is a global enquiry, otherwise false
	 * 
	 * @return true if this Function is a global enquiry, otherwise false
	 */
	public boolean isGlobalEnquiry()
	{
		return globalEnquiry;
	}

	/**
	 * Sets whether this is a global enquiry function
	 * 
	 * @param globalEnquiry
	 *            - a boolean
	 */
	public void setGlobalEnquiry(boolean globalEnquiry)
	{
		this.globalEnquiry = globalEnquiry;
	}

	/**
	 * Determine whether this function is applied during external input
	 * 
	 * @return true if this function is applied during external input
	 */
	public boolean isApplyDuringExtInput()
	{
		return applyDuringExtInput;
	}

	/**
	 * Set whether this function is applied during external input
	 * 
	 * @param applyDuringExtInput
	 *            - true if this function is applied during external input
	 */
	public void setApplyDuringExtInput(boolean applyDuringExtInput)
	{
		this.applyDuringExtInput = applyDuringExtInput;
	}

	/**
	 * Determine whether this function is applied during recovery
	 * 
	 * @return true if this function is applied during recovery
	 */
	public boolean isApplyDuringRecovery()
	{
		return applyDuringRecovery;
	}

	/**
	 * Set whether this function is applied during recovery
	 * 
	 * @param applyDuringRecovery
	 *            - true if this function is applied during recovery
	 */
	public void setApplyDuringRecovery(boolean applyDuringRecovery)
	{
		this.applyDuringRecovery = applyDuringRecovery;
	}

	/**
	 * @return the primary <code>InputFieldSet</code>
	 */
	public InputFieldSet rtvPrimaryInputFieldSet()
	{
		try
		{
			return getInputFieldSet(PRIMARY_ID);
		}
		catch (EQException e)
		{
			throw new EQRuntimeException(e);
		}
	}

	/**
	 * Determines whether the specified field has an Update assignment, either a mapping, or script/code.
	 * <p>
	 * This is used by the EFC validation and processing
	 * 
	 * @param efcFieldSet
	 *            the EFC <code>APIFieldSet</code>
	 * @param fieldName
	 *            The field to check
	 * @return a <code>boolean</code> indicating whether the field is assigned
	 */
	public boolean hasUpdateAssignment(APIFieldSet efcFieldSet, String fieldName)
	{
		boolean assigned = false;
		APIField apiField;
		try
		{
			apiField = efcFieldSet.getAPIField(fieldName);
			if (Field.ASSIGNMENT_CODE.equals(apiField.getUpdateAssignment())
							|| Field.ASSIGNMENT_SCRIPT.equals(apiField.getUpdateAssignment()))
			{
				assigned = true;
			}
			else
			{
				// Check for mappings
				String path = apiField.rtvPath();
				assigned = hasFromUpdateMapping(path);
			}
		}
		catch (EQException e)
		{
			LOG.error("isAssigned", e);
		}
		return assigned;
	}

	/**
	 * Indicates whether the service contains the specified field.
	 * <p>
	 * This method can be used to ensure that a fields Id is unique across the whole service definition. All InputFieldSets and
	 * WorkFields are checked to find a match.
	 * 
	 * @param fieldId
	 *            a <code>String</code> containing the Id of the field to check
	 * @return a <code>boolean</code> indicating whether the service contains the field
	 */
	public boolean containsField(String fieldId)
	{
		for (InputFieldSet inputFieldSet : getInputFieldSets())
		{
			if (inputFieldSet.containsField(fieldId))
			{
				return true;
			}
		}
		// Also check WorkFields:
		return getWorkFields().contains(fieldId);
	}
	/**
	 * Return number of xsd web service type name references
	 * <p>
	 * This method can be used to ensure that a field's xsd web service type name reference is unique across the whole service
	 * definition. All InputFieldSets are checked to find a match.
	 * 
	 * @param webServiceFieldTypeName
	 *            a <code>String</code> containing the xsd web service type name of the field to check
	 * @return a <code>integer</code> indicating number of xsd web service type name references
	 */
	public int rtvNumberXsdWebServiceNameReferences(String webServiceFieldTypeName)
	{
		int numberXsdWebServiceNameReferences = 0;
		for (InputFieldSet inputFieldSet : getInputFieldSets())
		{
			numberXsdWebServiceNameReferences = numberXsdWebServiceNameReferences
							+ inputFieldSet.rtvNumberXsdWebServiceNameReferences(webServiceFieldTypeName);

		}
		return numberXsdWebServiceNameReferences;
	}

	/**
	 * Determines whether a BankFusion User Exit Microflow should be called during post update processing. (9/20/2011) - made
	 * obsolete by getPostUpdateMicroFlow and retained only for backward compatibility
	 * 
	 * @return a <code>boolean</code> indicating whether a post update Microflow call is required
	 */
	public boolean getPostUpdateMicroflow()
	{
		return postUpdateMicroflow;
	}

	/**
	 * Determines whether a BankFusion User Exit Microflow should be called during post update processing
	 * 
	 * @return an <code>String</code> indicating whether a post update Microflow call is required. This will be one of the following
	 *         constants: POST_UPDATE_MICROFLOW_NO, POST_UPDATE_MICROFLOW_YES, POST_UPDATE_MICROFLOW_SCRIPT.
	 */
	public String getPostUpdateMicroFlow()
	{
		return postUpdateMicroFlow;
	}

	/**
	 * Set whether a BankFusion User Exit Microflow should be called during post update processing. (9/20/2011) - made obsolete by
	 * setPostUpdateMicroFlow and retained only for backward compatibility
	 * 
	 * @param postUpdateMicroflow
	 *            - true if a Microflow should be generated/called
	 */
	public void setPostUpdateMicroflow(boolean postUpdateMicroflow)
	{
		this.postUpdateMicroflow = postUpdateMicroflow;
	}

	/**
	 * Set whether a BankFusion User Exit Microflow should be called during post update processing
	 * 
	 * @param postUpdateMicroFlow
	 *            an <code>String</code> indicating how post update microflow status is determined. Must be one of the following
	 *            constants: POST_UPDATE_MICROFLOW_NO, POST_UPDATE_MICROFLOW_YES, POST_UPDATE_MICROFLOW_SCRIPT.
	 */
	public void setPostUpdateMicroFlow(String postUpdateMicroFlow)
	{
		this.postUpdateMicroFlow = postUpdateMicroFlow;
	}

	/**
	 * Return the post update Microflow
	 * 
	 * @return the post update Microflow expression
	 */
	public String getPostUpdateMicroFlowExpression()
	{
		return postUpdateMicroFlowExpression;
	}

	/**
	 * Set the post update Microflow expression
	 * 
	 * @param postUpdateMicroFlowExpression
	 *            - the post update microflow expression
	 */
	public void setPostUpdateMicroFlowExpression(String postUpdateMicroFlowExpression)
	{
		this.postUpdateMicroFlowExpression = postUpdateMicroFlowExpression;
	}

	/**
	 * @return a <code>boolean</code> indicating whether the service can be run during extended business hours
	 */
	public boolean isExtendedBusinessHoursAllowed()
	{
		return extendedBusinessHoursAllowed;
	}

	/**
	 * Sets whether the service can be run during extended business hours
	 * 
	 * @param extendedBusinessHoursAllowed
	 *            a <code>boolean</code> indicating whether the service can be run during extended business hours
	 */
	public void setExtendedBusinessHoursAllowed(boolean extendedBusinessHoursAllowed)
	{
		this.extendedBusinessHoursAllowed = extendedBusinessHoursAllowed;
	}

	/**
	 * @return a <code>boolean</code> indicating whether the service requires BankFusion support
	 */
	public boolean isBankFusionSupport()
	{
		return bankFusionSupport;
	}

	/**
	 * @param bankFusionSupport
	 *            sets whether the service requires BankFusion support
	 */
	public void setBankFusionSupport(boolean bankFusionSupport)
	{
		this.bankFusionSupport = bankFusionSupport;
	}

	/**
	 * @return the application that this service belongs to
	 */
	public String getApplication()
	{
		return application;
	}

	/**
	 * @param application
	 *            - sets the application that this service belongs to
	 */
	public void setApplication(String application)
	{
		this.application = application;
	}

	/**
	 * @return the application/bank identifier for this service
	 */
	public String getappBankId()
	{
		return appBankId;
	}

	/**
	 * @param appBankId
	 *            - sets the application/bank identifier for this service
	 */
	public void setAppBankId(String appBankId)
	{
		this.appBankId = appBankId;
	}
	/**
	 * @return the module identifier for this service
	 */
	public String getModuleId()
	{
		return moduleId;
	}

	/**
	 * @param moduleId
	 *            - sets the module identifier for this service
	 */
	public void setModuleId(String moduleId)
	{
		this.moduleId = moduleId;
	}

	/**
	 * @return the EFC validation indicator
	 */
	public boolean getValidateEfc()
	{
		return validateEfc;
	}

	/**
	 * @param validateEfc
	 *            - sets the EFC validation indicator
	 */
	public void setValidateEfc(boolean validateEfc)
	{
		this.validateEfc = validateEfc;
	}

	/**
	 * Adds a Context Mapping
	 * <p>
	 * This method will throw a runtime exception if there is already a mapping to the context item. Items will be added to the list
	 * in context item order to avoid having to re-order when populating the table of mappings.
	 * 
	 * @param newMapping
	 */
	public void addContextMapping(Mapping newMapping)
	{
		// New mappings need to be added in the correct position.
		// This avoids needing to sort the items whenever they are shown
		int insertIndex = 0;
		for (Mapping mapping : contextMappings)
		{
			if (mapping.getTarget().equals(newMapping.getTarget()))
			{
				throw new EQRuntimeException("Duplication mapping to [" + mapping.getTarget() + "]");
			}

			int compResult = mapping.getTarget().compareTo(newMapping.getTarget());
			if (compResult > 0)
			{
				break;
			}
			insertIndex++;
		}
		// No duplicate found, so add mapping
		contextMappings.add(insertIndex, newMapping);
	}

	/**
	 * Removes a Context Mapping
	 * 
	 * @param contextMapping
	 */
	public void removeContextMapping(Mapping contextMapping)
	{
		contextMappings.remove(contextMapping);
	}

	/**
	 * @return The list of ContextMappings
	 */
	public List<Mapping> getContextMappings()
	{
		return contextMappings;
	}

	/**
	 * Checks if there are any Update APIs for tables. If so, the 'apply during external input' and 'apply during recovery'
	 * checkboxes on the Overview page must be set and should not be enabled.
	 * 
	 * @return true if there is one or more Update APIs for a table
	 */
	public boolean tableUpdateAPIExists()
	{
		for (APIFieldSet api : updateAPIs)
		{
			if (IEquationStandardObject.TYPE_TABLE.equals(api.getType()))
			{
				applyDuringExtInput = true;
				applyDuringRecovery = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * Rename the mappings when a field has been updated
	 * 
	 * @param inputFieldSet
	 *            - the input field set where the field belongs to
	 * @param newFieldId
	 *            - the new field id
	 * @param oldFieldId
	 *            - the old field id
	 * 
	 * @return true if some details have been changed
	 */
	public boolean modifyFieldId(String inputFieldSet, String newFieldId, String oldFieldId)
	{
		String newPath = MappingToolbox.getFullInputFieldPath(inputFieldSet, newFieldId);
		String oldPath = MappingToolbox.getFullInputFieldPath(inputFieldSet, oldFieldId);

		return modifyFieldId(newPath, oldPath);
	}

	/**
	 * Rename the mappings when a field has been updated
	 * 
	 * @param newPath
	 *            - the new path
	 * @param oldPath
	 *            - the old path
	 * 
	 * @return true if some details have been changed
	 */
	public boolean modifyFieldId(String newPath, String oldPath)
	{
		// check the validate mapping
		boolean changedV = applyFieldIdChangeToMappingCollection(validateMappings, newPath, oldPath);

		// check the load mapping
		boolean changedL = applyFieldIdChangeToMappingCollection(loadMappings, newPath, oldPath);

		// check the update mapping
		boolean changedU = applyFieldIdChangeToMappingCollection(updateMappings, newPath, oldPath);

		return changedV || changedL || changedU;
	}

	/**
	 * Replace the old path with the new path from the list of mappings
	 * 
	 * @param mappings
	 *            - the list of mappings
	 * @param newPath
	 *            - the new path
	 * @param oldPath
	 *            - the old path
	 * 
	 * @return true if some details have been changed
	 */
	private boolean applyFieldIdChangeToMappingCollection(List<Mapping> mappings, String newPath, String oldPath)
	{
		boolean changed = false;
		for (Mapping mapping : mappings)
		{
			if (mapping.getSource().indexOf(oldPath) >= 0)
			{
				((MappingList) mappings).editMapping(mapping, mapping.getSource().replace(oldPath, newPath), null);
				changed = true;
			}

			if (mapping.getTarget().indexOf(oldPath) >= 0)
			{
				((MappingList) mappings).editMapping(mapping, null, mapping.getTarget().replace(oldPath, newPath));
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Gets an input field
	 * <p>
	 * Note that InputField Ids are unique across the service. Also note that this method will not return WorkFields
	 * 
	 * @param fieldName
	 *            The name of the field to find
	 * @return the InputField instance if found, or null if not found
	 */
	public InputField rtvInputField(String fieldName)
	{
		InputField result = null;
		for (InputFieldSet inputFieldSet : inputFieldSets)
		{
			if (inputFieldSet.containsField(fieldName))
			{
				try
				{
					result = inputFieldSet.getInputField(fieldName);
				}
				catch (EQException e)
				{
					LOG.error("", e);
				}
			}
		}
		return result;
	}

	/**
	 * Return the input group
	 * 
	 * @param inputGroupId
	 *            - the input group id
	 * 
	 * @return the input group
	 */
	public InputGroup rtvInputGroup(String inputGroupId)
	{
		InputGroup result = null;
		for (InputFieldSet inputFieldSet : inputFieldSets)
		{
			if (inputFieldSet.hasInputGroup(inputGroupId))
			{
				result = inputFieldSet.getInputGroup(inputGroupId);
				break;
			}
		}
		return result;
	}

	/**
	 * Return the input field of an input group
	 * 
	 * @param inputGroupId
	 *            - the input group id
	 * 
	 * @return the input field of an input group
	 */
	public InputFieldSet rtvFieldSetOfInputGroup(String inputGroupId)
	{
		InputFieldSet result = null;
		for (InputFieldSet inputFieldSet : inputFieldSets)
		{
			if (inputFieldSet.hasInputGroup(inputGroupId))
			{
				result = inputFieldSet;
				break;
			}
		}
		return result;
	}

	/**
	 * @return a <code>boolean</code> indicating whether import is allowed
	 */
	public boolean isImportAllowed()
	{
		return importAllowed;
	}

	/**
	 * Set Import Allowed Flag, true if allowed, otherwise false
	 * 
	 * @param importAllowed
	 */
	public void setImportAllowed(boolean importAllowed)
	{
		this.importAllowed = importAllowed;
	}

	/**
	 * @return a <code>boolean</code> indicating if Desktop UI is not allow in Production
	 */
	public boolean isNoDesktopInProduction()
	{
		return noDesktopInProduction;
	}

	/**
	 * Set No Desktop UI In Production Flag, true if Desktop UI not allow in Production, otherwise false
	 * 
	 * @param noDesktopInProduction
	 */
	public void setNoDesktopInProduction(boolean noDesktopInProduction)
	{
		this.noDesktopInProduction = noDesktopInProduction;
	}

	/**
	 * 
	 * @return a <code>String</code> containing Original Service ID
	 */
	public String getOriginalServiceId()
	{
		return originalServiceId;
	}

	/**
	 * Set Original Service ID
	 * 
	 * @param originalServiceId
	 */
	public void setOriginalServiceId(String originalServiceId)
	{
		this.originalServiceId = originalServiceId;
	}

	/**
	 * 
	 * @return a <code>String</code> containing Original Release version
	 */
	public String getOriginalReleaseVersion()
	{
		return originalReleaseVersion;
	}

	/**
	 * Set Original Release Version
	 * 
	 * @param origReleaseVersion
	 */
	public void setOriginalReleaseVersion(String origReleaseVersion)
	{
		this.originalReleaseVersion = origReleaseVersion;
	}
	/**
	 * 
	 * @return a <code>String</code> containing XSD version
	 */
	public String getXsdVersion()
	{
		return xsdVersion;
	}
	/**
	 * 
	 * @return a <code>String</code> containing XSD major version including 'v' prefix
	 */
	public String rtvXsdMajorVersion()
	{
		String majorVersion = "";

		if (xsdVersion.length() > 0)
		{
			if (xsdVersion.contains("."))
			{
				String[] parts = xsdVersion.split("\\.");
				if (parts.length == 2)
				{
					majorVersion = "v" + parts[0];
				}
			}
		}
		return majorVersion;
	}
	/**
	 * Set XSD Version
	 * 
	 * @param xsdVersion
	 */
	public void setXsdVersion(String xsdVersion)
	{
		this.xsdVersion = xsdVersion;
	}

	/**
	 * This validation will ensure that Import Allowed is true when Service Composer is used by a bank.
	 * 
	 * @param messageContainer
	 * @param isMisysMode
	 *            - true if SC is used by Misys
	 * @return
	 */
	public boolean isImportAllowedValid(MessageContainer messageContainer, boolean isMisysMode)
	{

		// If service composer is used by a bank, Import Allowed should be true
		if (!isMisysMode && !isImportAllowed())
		{
			ProblemLocation problemLocation = new ProblemLocation(Function.class.getSimpleName(), getId());
			messageContainer.addErrorMessageId("Language.BankImportAllowedMustBeTrue", problemLocation);
		}

		return false;
	}

	/**
	 * 
	 * @return a <code>String</code> containing Service Owner
	 */
	public String getOwner()
	{
		return owner;
	}

	/**
	 * Set Service Owner
	 * 
	 * @param owner
	 */
	public void setOwner(String owner)
	{
		this.owner = owner;
	}

	/**
	 * Gets the plug-in version
	 * 
	 * @return The version of plug-in used to create the service
	 */
	public String getPluginVersion()
	{
		return pluginVersion;
	}

	/**
	 * Sets the plug-in version
	 * 
	 * @param pluginVersion
	 *            - the version of plug-in used to create the service
	 */
	public void setPluginVersion(String pluginVersion)
	{
		this.pluginVersion = pluginVersion;
	}

	/**
	 * Gets the release version
	 * 
	 * @return The patch/enhancement used to despatch/install the service
	 */
	public String getReleaseVersion()
	{
		return releaseVersion;
	}

	/**
	 * Sets the release version
	 * 
	 * @param releaseVersion
	 *            The patch/enhancement used to despatch/install the service
	 */
	public void setReleaseVersion(String releaseVersion)
	{
		this.releaseVersion = releaseVersion;
	}
	/**
	 * Determine if load mapping has valid source and target mappings.
	 * 
	 * @throws EQException
	 */
	public boolean validLoadMapping(Mapping mapping) throws EQException
	{
		String fieldId = null;
		String sourceAPI = MappingToolbox.getAPIUsingLabel(mapping.getSource());
		if (sourceAPI != null && !sourceAPI.equals(""))
		{

			InputFieldSet sourceInputFieldSet = getInputFieldSet(Function.PRIMARY_ID);
			if (!sourceInputFieldSet.hasLoadAPI(sourceAPI))
			{
				return false;
			}
			fieldId = MappingToolbox.getField(mapping.getSource());
			if (fieldId != null && sourceInputFieldSet.getLoadAPI(sourceAPI).getAPIField(fieldId) == null)
			{
				return false;
			}
		}
		else
		{
			// Check source field exists
			fieldId = MappingToolbox.getField(mapping.getSource());
			if (fieldId != null)
			{
				if ((rtvInputField(fieldId) == null && !getWorkFields().contains(fieldId)))
				{
					return false;
				}
			}
		}
		String targetAPI = MappingToolbox.getAPIUsingLabel(mapping.getTarget());
		if (targetAPI != null && !targetAPI.equals(""))
		{

			InputFieldSet targetInputFieldSet = getInputFieldSet(Function.PRIMARY_ID);
			if (!targetInputFieldSet.hasLoadAPI(targetAPI))
			{
				return false;
			}
			fieldId = MappingToolbox.getField(mapping.getTarget());
			if (fieldId != null && targetInputFieldSet.getLoadAPI(targetAPI).getAPIField(fieldId) == null)
			{
				return false;
			}
		}
		else
		{
			// Check target field exists
			fieldId = MappingToolbox.getField(mapping.getTarget());
			if (fieldId != null)
			{
				if (!fieldId.startsWith("GZ") && (rtvInputField(fieldId) == null && !getWorkFields().contains(fieldId)))
				{
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * Determine if validate mapping has valid source and target mappings.
	 * 
	 * @throws EQException
	 */
	public boolean validValidateMapping(Mapping mapping) throws EQException
	{

		String mappingSourcePV = MappingToolbox.getPVFieldSet(mapping.getSource());
		if (mappingSourcePV != null && !mappingSourcePV.equals(""))
		{
			String mappingSourceInputFieldSetId = MappingToolbox.getInputFieldSetUsingLabel(mapping.getSource());
			InputFieldSet mappingSourceInputFieldSet = getInputFieldSet(mappingSourceInputFieldSetId);
			String mappipngSourceFieldId = MappingToolbox.getInputFieldUsingLabel(mapping.getSource());
			if (mappipngSourceFieldId == null || mappipngSourceFieldId.equals(""))
			{
				return false;
			}
			InputField mappingSourceField = mappingSourceInputFieldSet.getInputField(mappipngSourceFieldId);
			if (mappingSourceField == null)
			{
				return false;
			}
			if (!mappingSourceField.hasPvFieldSet(mappingSourcePV))
			{
				return false;
			}
		}
		String mappingTargetPV = MappingToolbox.getPVFieldSet(mapping.getTarget());
		if (mappingTargetPV != null && !mappingTargetPV.equals(""))
		{
			String mappingTargetInputFieldSetId = MappingToolbox.getInputFieldSetUsingLabel(mapping.getTarget());
			InputFieldSet mappingTargetInputFieldSet = getInputFieldSet(mappingTargetInputFieldSetId);
			String mappingTargetFieldId = MappingToolbox.getInputFieldUsingLabel(mapping.getTarget());
			if (mappingTargetFieldId == null || mappingTargetFieldId.equals(""))
			{
				return false;
			}
			InputField mappingTargetField = mappingTargetInputFieldSet.getInputField(mappingTargetFieldId);
			if (mappingTargetField == null)
			{
				return false;
			}
			if (!mappingTargetField.hasPvFieldSet(mappingTargetPV))
			{
				return false;
			}
		}

		return true;
	}
	/**
	 * Determine if update mapping has valid source and target mappings.
	 */
	public boolean validUpdateMapping(Mapping mapping)
	{
		String fieldId = null;
		// Check source API exists
		String sourceAPI = MappingToolbox.getAPIUsingLabel(mapping.getSource());
		if (sourceAPI != null && !sourceAPI.equals(""))
		{
			if (!hasUpdateAPI(sourceAPI))
			{
				return false;
			}
			// Check source API field exists
			fieldId = MappingToolbox.getField(mapping.getSource());
			try
			{
				if (!getUpdateAPI(sourceAPI).containsField(fieldId))
				{
					return false;
				}
			}
			catch (EQException e)
			{
				return false;
			}
		}
		else
		{
			// Check source input field or work field exists
			fieldId = MappingToolbox.getField(mapping.getSource());
			if (fieldId != null)
			{
				if (rtvInputField(fieldId) == null && !getWorkFields().contains(fieldId))
				{
					return false;
				}
			}
		}
		// Check target API exists
		String targetAPI = MappingToolbox.getAPIUsingLabel(mapping.getTarget());
		if (targetAPI != null && !targetAPI.equals("") && !hasUpdateAPI(targetAPI))
		{
			return false;
		}
		// Check target API field exists
		fieldId = MappingToolbox.getField(mapping.getTarget());
		try
		{
			if (!getUpdateAPI(targetAPI).containsField(fieldId))
			{
				return false;
			}
		}
		catch (EQException e)
		{
			return false;
		}
		return true;
	}

	/**
	 * This checks if the entered base language is valid
	 * 
	 * @param messageContainer
	 * @param baseLanguageCodes
	 *            - true if base language is valid
	 * @return true if base language is valid, otherwise false
	 */
	public boolean isBaseLanguageValid(MessageContainer messageContainer, KeyedArrayList<String> baseLanguageCodes)
	{
		boolean isValid = false;
		ProblemLocation problemLocation = new ProblemLocation(Function.class.getSimpleName(), getId());
		String baseLanguage = getBaseLanguage().trim().toUpperCase();

		if (baseLanguage.length() > 0 && baseLanguageCodes.contains(baseLanguage))
		{
			isValid = true;
		}
		if (!isValid)
		{
			messageContainer.addErrorMessageId("Language.BaseLanguageIsInvalid", problemLocation);
		}
		return isValid;
	}

	/**
	 * Sets the base language of the service
	 * 
	 * @param baseLanguage
	 */
	public void setBaseLanguage(String baseLanguage)
	{
		this.baseLanguage = baseLanguage;
	}

	/**
	 * Returns the base language of the service
	 * 
	 * @return the base language
	 */
	public String getBaseLanguage()
	{
		if (baseLanguage == null)
		{
			return "";
		}
		return baseLanguage;
	}
	/**
	 * Returns the text owner for text related to services. This will be stored in text beans in the translation/language beans.
	 * 
	 * @return text owner
	 */
	public String rtvTextOwner()
	{
		return getId() + SERVICE_FILE_NAME_SUFFIX;
	}

	/**
	 * Sort load mappings
	 */
	public void sortLoadMappings()
	{
		List<Mapping> sortedList = getLoadMappings();
		Collections.sort(sortedList);
		loadMappings = new MappingList(sortedList);
	}
	/**
	 * Sort update mappings
	 */
	public void sortUpdateMappings()
	{
		List<Mapping> sortedList = getUpdateMappings();
		Collections.sort(sortedList);
		updateMappings = new MappingList(sortedList);
	}
	/**
	 * Sort validate mappings
	 */
	public void sortValidateMappings()
	{
		List<Mapping> sortedList = getValidateMappings();
		Collections.sort(sortedList);
		validateMappings = new MappingList(sortedList);
	}

	/**
	 * Checks if EFC field set exists or not
	 * 
	 * @return true if EFC field set exists or not
	 */
	public boolean checkEfcFieldSetExist()
	{
		if (containsAPIFieldSet(Function.EFC_FIELDSET))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Checks if EFC is enabled
	 * 
	 * @return true if EFC is enabled, false if otherwise
	 */
	public boolean checkEfcEnabled()
	{
		if (checkEfcFieldSetExist() && validateEfc)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Return the list of work fields as a map
	 * 
	 * @return the map of work fields
	 */
	public Map<String, WorkField> rtvWorkFields()
	{
		// transfer the work fields to a map
		Map<String, WorkField> map = new HashMap<String, WorkField>();
		for (WorkField workField : getWorkFields())
		{
			map.put(workField.getId(), workField);
		}
		return map;
	}

	/**
	 * Return the list of update APIs as a map
	 * 
	 * @return the map of update APIs
	 */
	public Map<String, APIFieldSet> rtvUpdateAPIs()
	{
		// transfer the work fields to a map
		Map<String, APIFieldSet> map = new HashMap<String, APIFieldSet>();
		for (APIFieldSet apiFieldSet : getUpdateAPIs())
		{
			map.put(apiFieldSet.getId(), apiFieldSet);
		}
		return map;
	}

	/**
	 * Return the list of load APIs as a map
	 * 
	 * @return the map of update APIs
	 */
	public Map<String, APIFieldSet> rtvLoadAPIs()
	{
		// transfer the work fields to a map
		Map<String, APIFieldSet> map = new HashMap<String, APIFieldSet>();

		for (InputFieldSet inputFieldSet : getInputFieldSets())
		{
			for (APIFieldSet apiFieldSet : inputFieldSet.getLoadAPIs())
			{
				map.put(apiFieldSet.getId(), apiFieldSet);
			}
		}

		return map;
	}

	/**
	 * Return the list of input fields as a map
	 * 
	 * @return the map of input fields
	 */
	public Map<String, InputField> rtvInputFields()
	{
		// transfer the work fields to a map
		Map<String, InputField> map = new HashMap<String, InputField>();

		for (InputFieldSet inputFieldSet : getInputFieldSets())
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				map.put(inputField.getId(), inputField);
			}
		}

		return map;
	}
	/**
	 * Return the list of potential currency input fields
	 * 
	 * @param repeatingGroupId
	 *            - if groupId is not supplied then can find fields outside of the group
	 * @return the list of potential currency input fields
	 */
	public List<String> rtvPotentialCurrencyFields(String repeatingGroupId)
	{
		// transfer the work fields to a map
		List<String> potentialCurrencyFields = new ArrayList<String>();

		potentialCurrencyFields.add("");
		for (InputFieldSet inputFieldSet : getInputFieldSets())
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				if (inputField.getDataType().equals(EqDataType.TYPE_CHAR)
								&& ValidationHelper.getSafeIntegerValue(inputField.getSize()) == 3)
					if (repeatingGroupId.equals(inputField.getRepeatingGroup()) || inputField.getRepeatingGroup().trim().equals(""))
					{
						potentialCurrencyFields.add(inputField.rtvIdAndLabel());
					}
			}
		}

		return potentialCurrencyFields;
	}
	/**
	 * Return the XSD generation type
	 * 
	 * @return the XSD generation type
	 */
	public int getXsdGeneration()
	{
		return xsdGeneration;
	}

	/**
	 * Set the XSD generation type
	 * 
	 * @param xsdGeneration
	 *            - the XSD generation type
	 */
	public void setXsdGeneration(int xsdGeneration)
	{
		this.xsdGeneration = xsdGeneration;
	}

	/**
	 * Determine if XSD generation is using XSD generic
	 * 
	 * @return true if XSD generation is using XSD generic
	 */
	public boolean chkXSDGeneric()
	{
		return xsdGeneration == XSD_GENERIC;
	}

	/**
	 * Return the request XSD structure
	 * 
	 * @return the request XSD structure
	 */
	public XSDStructure getXsdStructureRequest()
	{
		return xsdStructureRequest;
	}

	/**
	 * Set the request XSD structure
	 * 
	 * @param xsdStructure
	 *            - the request XSD structure
	 */
	public void setXsdStructureRequest(XSDStructure xsdStructureRequest)
	{
		this.xsdStructureRequest = xsdStructureRequest;
	}

	/**
	 * Return the response XSD structure
	 * 
	 * @return the response XSD structure
	 */
	public XSDStructure getXsdStructureResponse()
	{
		return xsdStructureResponse;
	}

	/**
	 * Set the response XSD structure
	 * 
	 * @param xsdStructureResponse
	 *            - the response XSD structure
	 */
	public void setXsdStructureResponse(XSDStructure xsdStructureResponse)
	{
		this.xsdStructureResponse = xsdStructureResponse;
	}

}