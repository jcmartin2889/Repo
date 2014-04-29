package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.Mapping;
import com.misys.equation.function.language.LanguageResources;

/**
 * Utility class containing constants and helper method relating to generating Mapping Paths within the service XML
 */
public class MappingToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: MappingToolbox.java 11022 2011-05-23 15:43:39Z ESTHER.WILLIAMS $";

	/** Denotes an input field set in a mapping path */
	private static final String INPUT_FIELDSET_MAPPING_PATH = "InputFieldSet";

	/** Denotes a Repeating Group in a mapping path */
	private static final String REPEATING_GROUP_PATH = "RepeatingGroup";

	/** Denotes a PV field set in a mapping path */
	private static final String PV_FIELDSET_MAPPING_PATH = "PVFieldSet";

	/** Denotes a load API field set in a mapping path */
	private static final String LOAD_API_FIELDSET_MAPPING_PATH = "APIFieldSet";

	/** Denotes an update API field set in a mapping path */
	private static final String UPDATE_API_FIELDSET_MAPPING_PATH = "APIFieldSet";

	/** Denotes an input field in a mapping path */
	private static final String INPUT_FIELD_MAPPING_PATH = "InputField";

	/** Denotes an API field in a mapping path */
	private static final String API_FIELD_MAPPING_PATH = "APIField";

	/** Denotes a PV field in a mapping path */
	private static final String PV_FIELD_MAPPING_PATH = "PVField";

	/** Denotes the psuedo WorkField set mapping path */
	private static final String WORKFIELDS_MAPPING_PATH = "WorkField";

	/** Denotes the subfields */
	public static final String DOT = ".";
	public static final String SUBFIELD = "SubField";
	public static final String WORKFIELD = "WorkField";
	public static final String DOT_SUBFIELD_DOT = DOT + SUBFIELD + DOT;

	/**
	 * When using a . in a regular expression, a preceding backslash is required, so the actual String definition is "\\.".
	 * <p>
	 * This is typically used with the <code>String.split</code> method.
	 */
	public static final String REGEX_DOT = "\\.";
	/**
	 * Input subfield type string ("Input").
	 * <P>
	 * This is currently used for internal Path strings
	 */
	private static final String INPUT = "Input";
	/**
	 * Output subfield type string ("Output").
	 * <P>
	 * This is used both in internal Path strings and in message parameters.
	 */
	public static final String OUTPUT = "Output";
	/**
	 * Primitive subfield type string ("Primitive").
	 * <P>
	 * This is used both in internal Path strings and in message parameters.
	 */
	public static final String PRIMITIVE = "Primitive";
	public static final String SUBFIELD_OUTPUT = DOT_SUBFIELD_DOT + OUTPUT;
	public static final String SUBFIELD_PRIMITIVE = DOT_SUBFIELD_DOT + PRIMITIVE;
	public static final String SUBFIELD_INPUT = DOT_SUBFIELD_DOT + INPUT;

	/** Text for "Load", for use in message parameters */
	public static final String LOAD = LanguageResources.getString("Language.Load");
	/** Text for "Update", for use in message parameters */
	public static final String UPDATE = LanguageResources.getString("Language.Update");
	/** Text for "Validate", for use in message parameters */
	public static final String VALIDATE = LanguageResources.getString("Language.Validate");

	public final static int TYPE_PRIMITIVE = 0;
	public final static int TYPE_INPUT = 1;
	public final static int TYPE_OUTPUT = 2;
	public final static int TYPE_WORK = 3;

	/**
	 * Return the primitive, input, output path
	 * 
	 * @param type
	 *            - the type of path required
	 * 
	 * @return the path sub-name for the primitive, input or output
	 */
	private static String getType(int type)
	{
		if (type == TYPE_PRIMITIVE)
		{
			return SUBFIELD + DOT + PRIMITIVE;
		}
		if (type == TYPE_INPUT)
		{
			return SUBFIELD + DOT + INPUT;
		}
		if (type == TYPE_OUTPUT)
		{
			return SUBFIELD + DOT + OUTPUT;
		}
		if (type == TYPE_WORK)
		{
			return WORKFIELD + DOT;
		}
		return "";
	}

	/**
	 * Generate the path of an InputFieldSet
	 * 
	 * @param id
	 *            - the display input field set id
	 * 
	 * @return the path for the InputFieldSet
	 */
	public static String getInputFieldSetPath(String id)
	{
		return INPUT_FIELDSET_MAPPING_PATH + DOT + id;
	}

	/**
	 * Generate the path of a field of an display input field set
	 * 
	 * @param id
	 *            - the field id
	 * 
	 * @return the display input field path
	 */
	public static String getInputFieldPath(String id)
	{
		return INPUT_FIELD_MAPPING_PATH + DOT + id;
	}
	/**
	 * Return the Input Field Set of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return the Input Field Set Id associated with the path
	 */
	public static String getInputFieldSetUsingLabel(String path)
	{
		int index = path.lastIndexOf(INPUT_FIELDSET_MAPPING_PATH);

		// no dot
		if (index == -1)
		{
			return "";
		}
		String inputFieldSet = path.substring(index + 14, path.length());
		index = inputFieldSet.indexOf(DOT);
		if (index != -1)
		{
			inputFieldSet = inputFieldSet.substring(0, index);
		}

		// return the first word
		return (inputFieldSet.substring(0, index));

	}
	/**
	 * Generate the full path of a field of an input field or work field
	 * 
	 * @param inputFieldSet
	 *            - the input field set id
	 * @param inputField
	 *            - the input field id
	 * 
	 * @return the input field path
	 */
	public static String getFullInputFieldPath(String inputFieldSet, String inputField)
	{
		if (inputFieldSet.length() == 0)
		{
			// Assume work field, which have only the Workfields puesdo set name and the field id
			return WORKFIELDS_MAPPING_PATH + DOT + inputField;
		}
		// Normal Input field processing
		return getInputFieldSetPath(inputFieldSet) + DOT + getInputFieldPath(inputField);
	}

	/**
	 * Generate the path of a repeating group
	 * <p>
	 * Note that although RepeatingGroups are not used in mappings, a path is generated for them to assist with tree control
	 * processing
	 * 
	 * @param id
	 *            - the repeating group
	 * 
	 * @return the repeating group path
	 */
	public static String getRepeatingGroupPath(String id)
	{
		return REPEATING_GROUP_PATH + DOT + id;
	}

	/**
	 * Generate the full path of a field of an input field or work field
	 * 
	 * @param inputFieldSetId
	 *            - the input field set id (must not be empty)
	 * @param repeatingGroupId
	 *            - the id of the Repeating Group
	 * 
	 * @return the input field path
	 */
	public static String getFullRepeatingGroupPath(String inputFieldSetId, String repeatingGroupId)
	{
		// Normal Input field processing
		return getInputFieldSetPath(inputFieldSetId) + DOT + getRepeatingGroupPath(repeatingGroupId);
	}
	/**
	 * Determine whether the path is for a RepeatingGroup
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return true if the path is a RepeatingGroup path
	 */
	public static boolean isRepeatingGroupPath(String path)
	{
		String[] parts = path.split(REGEX_DOT);
		return parts.length == 4 && parts[2].equals(REPEATING_GROUP_PATH);
	}

	/**
	 * Generate the full path of a field of an input field
	 * 
	 * @param inputFieldSet
	 *            - the input field set id
	 * @param inputField
	 *            - the input field id
	 * @param type
	 *            - identify whether it is the primitive, input or output
	 * 
	 * @return the display input field path
	 */
	public static String getFullInputFieldPath(String inputFieldSet, String inputField, int type)
	{
		return getInputFieldSetPath(inputFieldSet) + DOT + getInputFieldPath(inputField) + DOT + getType(type);
	}

	/**
	 * Generate the path of a PV field set
	 * 
	 * @param pvName
	 *            - the PV name
	 * 
	 * @return the PV field set path
	 */
	private static String getPVFieldSetPath(String pvName)
	{
		return PV_FIELDSET_MAPPING_PATH + DOT + pvName;
	}

	/**
	 * Generate the full path of a PV field set
	 * 
	 * @param inputFieldPath
	 *            the input field path
	 * @param pvName
	 *            - the PV name
	 * 
	 * @return the PV field set path
	 */
	public static String getFullPVFieldSetPath(String inputFieldPath, String pvName)
	{
		return inputFieldPath + DOT + getPVFieldSetPath(pvName);
	}

	/**
	 * Generate the path of a PV field
	 * 
	 * @param field
	 *            - the pv field
	 * 
	 * @return the PV field path
	 */
	private static String getPVFieldPath(String field)
	{
		return PV_FIELD_MAPPING_PATH + DOT + field;
	}

	/**
	 * Generate the full path of a PV field
	 * 
	 * @param inputFieldSet
	 *            - the input field set id
	 * @param inputField
	 *            - the input field id
	 * @param pvName
	 *            - the PV name
	 * @param pvField
	 *            - the PV field
	 * 
	 * @return the display input field path
	 */
	public static String getFullPVFieldPath(String inputFieldSet, String inputField, String pvName, String pvField)
	{
		return getInputFieldSetPath(inputFieldSet) + DOT + getInputFieldPath(inputField) + DOT + getPVFieldSetPath(pvName) + DOT
						+ getPVFieldPath(pvField);
	}

	/**
	 * Generate the path of an API field
	 * 
	 * @param field
	 *            - the field name
	 * 
	 * @return the API field path
	 */
	private static String getAPIFieldPath(String field)
	{
		return API_FIELD_MAPPING_PATH + DOT + field;
	}

	/**
	 * Generate the path of an update API field set
	 * 
	 * @param id
	 *            - update API id
	 * 
	 * @return the update API field set path
	 */
	private static String getUpdateAPIFieldSetPath(String id)
	{
		return UPDATE_API_FIELDSET_MAPPING_PATH + DOT + id;
	}

	/**
	 * Generate the path of an load API field set
	 * 
	 * @param id
	 *            - update API id
	 * 
	 * @return the load API field set path
	 */
	public static String getLoadAPIFieldSetPath(String id)
	{
		return LOAD_API_FIELDSET_MAPPING_PATH + DOT + id;
	}

	/**
	 * Generate the path of an Load PV field
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 * @param pvName
	 *            - the PV name
	 * @param pvField
	 *            - the PV field name
	 * 
	 * @return the Load PV Field path
	 */
	public static String getPVFieldLoadAPI(String inputFieldSet, String pvName, String pvField)
	{
		return getPVFieldSetLoadAPI(inputFieldSet, pvName) + DOT + getPVFieldPath(pvField);
	}

	/**
	 * Generate the path of an Load PV field set
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 * @param pvName
	 *            - the PV name
	 * 
	 * @return the Load PV path
	 */
	public static String getPVFieldSetLoadAPI(String inputFieldSet, String pvName)
	{
		return getInputFieldSetPath(inputFieldSet) + DOT + getPVFieldSetPath(pvName);
	}

	/**
	 * Generate the full path of an update API field
	 * 
	 * @param apiFieldSet
	 *            - the API field set id
	 * @param apiField
	 *            - the API field id
	 * 
	 * @return the display input field path
	 */
	public static String getFullUpdateFieldPath(String apiFieldSet, String apiField)
	{
		return getUpdateAPIFieldSetPath(apiFieldSet) + DOT + getAPIFieldPath(apiField);
	}

	/**
	 * Generate the full path of an load API field set
	 * 
	 * @param inputFieldSet
	 *            the Input Field set Id
	 * @param apiFieldSet
	 *            - the API field set id
	 * 
	 * @return the load API field set path
	 */
	public static String getFullLoadFieldSetPath(String inputFieldSet, String apiFieldSet)
	{
		return getInputFieldSetPath(inputFieldSet) + DOT + LOAD_API_FIELDSET_MAPPING_PATH + DOT + apiFieldSet;
	}

	/**
	 * Generate the full path of an load API field
	 * 
	 * @param inputFieldSet
	 *            the Input field set id
	 * @param apiFieldSet
	 *            the API field set id
	 * @param apiField
	 *            - the API field id
	 * 
	 * @return the display input field path
	 */
	public static String getFullLoadFieldPath(String inputFieldSet, String apiFieldSet, String apiField)
	{
		return getFullLoadFieldSetPath(inputFieldSet, apiFieldSet) + DOT + getAPIFieldPath(apiField);
	}
	/**
	 * Return the API of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return the API Id associated with the path
	 */
	public static String getAPIUsingLabel(String path)
	{
		int index = path.lastIndexOf(LOAD_API_FIELDSET_MAPPING_PATH);

		// no dot
		if (index == -1)
		{
			return "";
		}
		String api = path.substring(index + 12, path.length());
		index = api.indexOf(DOT);
		if (index != -1)
		{
			api = api.substring(0, index);
		}

		// return the first word
		return (api.substring(0, index));

	}
	/**
	 * Return the input field of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return the input field Id associated with the path
	 */
	public static String getInputFieldUsingLabel(String path)
	{
		int substringIndex = 11;
		int index = path.lastIndexOf(INPUT_FIELD_MAPPING_PATH + DOT);
		// no dot
		if (index == -1)
		{
			return "";
		}
		String fieldId = path.substring(index + substringIndex, path.length());
		index = fieldId.indexOf(DOT);
		if (index != -1)
		{
			fieldId = fieldId.substring(0, index);
		}
		// return the first word
		return fieldId;
	}
	/**
	 * Return the work field of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return the work field Id associated with the path
	 */
	public static String getWorkFieldUsingLabel(String path)
	{

		int substringIndex = 10;
		int index = path.lastIndexOf(WORKFIELDS_MAPPING_PATH);

		// no dot
		if (index == -1)
		{
			return "";
		}
		String fieldId = path.substring(index + substringIndex, path.length());
		index = fieldId.indexOf(DOT);
		if (index != -1)
		{
			fieldId = fieldId.substring(0, index);
		}
		// return the first word
		return fieldId;
	}
	/**
	 * Return the field of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return the field associated with the path
	 */
	public static String getField(String path)
	{
		// input field?
		int index = path.indexOf(DOT_SUBFIELD_DOT);
		if (index >= 0)
		{
			path = path.substring(0, index);
		}

		// work field?
		String workFieldStr = DOT + getType(TYPE_WORK);
		index = path.lastIndexOf(workFieldStr);
		if (index >= 0)
		{
			if (path.indexOf(DOT, index + workFieldStr.length()) < 0)
			{
				path = path.substring(0, index);
			}
		}

		// return the last field
		index = path.lastIndexOf(DOT);
		return path.substring(index + 1, path.length());
	}
	/**
	 * Return the field of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return the field associated with the path
	 */
	public static List<Mapping> getMappings(List<Mapping> mappings, Field field)
	{
		ArrayList<Mapping> result = new ArrayList<Mapping>();

		for (Mapping mapping : mappings)
		{
			// Contains logic cannot be used! Finding field called AB could also pick up ABB.
			// InputFields
			String mappingFieldId = MappingToolbox.getInputFieldUsingLabel(mapping.getTarget());
			if (mappingFieldId.equals(""))
			{
				mappingFieldId = MappingToolbox.getWorkFieldUsingLabel(mapping.getTarget());
			}
			if (field.getId().equals(mappingFieldId))
			{
				result.add(mapping);
			}

			mappingFieldId = MappingToolbox.getInputFieldUsingLabel(mapping.getSource());
			if (mappingFieldId.equals(""))
			{
				mappingFieldId = MappingToolbox.getWorkFieldUsingLabel(mapping.getSource());
			}
			if (field.getId().equals(mappingFieldId))
			{
				result.add(mapping);
			}
			// Workfields
			mappingFieldId = MappingToolbox.getWorkFieldUsingLabel(mapping.getTarget());
			if (mappingFieldId.equals(""))
			{
				mappingFieldId = MappingToolbox.getWorkFieldUsingLabel(mapping.getTarget());
			}
			if (field.getId().equals(mappingFieldId))
			{
				result.add(mapping);
			}

			mappingFieldId = MappingToolbox.getWorkFieldUsingLabel(mapping.getSource());
			if (mappingFieldId.equals(""))
			{
				mappingFieldId = MappingToolbox.getWorkFieldUsingLabel(mapping.getSource());
			}
			if (field.getId().equals(mappingFieldId))
			{
				result.add(mapping);
			}

		}
		return result;
	}
	/**
	 * Returns the FieldSet Id of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return a <code>String</code> containing the FieldSet associated with the path. This will be an empty <code>String</code> for
	 *         WorkFields (which do not have parent FieldSets).
	 */
	public static String getFieldSet(String path)
	{
		// If the path contains four or more '.' delimited parts, then it's a normal
		// FieldSet/Field path. For example, for the path InputFieldSet.REC1.InputField.CLC
		// the REC1 substring must be returned:
		String[] parts = path.split(REGEX_DOT);
		if (parts.length > 3)
		{
			return parts[1];
		}
		// Otherwise, return an empty string for WorkFields
		return "";
	}

	/**
	 * Returns the input field of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return a <code>String</code> containing the input field associated with the path
	 */
	public static String getInputField(String path)
	{
		// If the path contains four or more '.' delimited parts, then it's a normal
		// FieldSet/Field path. For example, for the path InputFieldSet.REC1.InputField.CLC
		// the CLC substring must be returned:
		String[] parts = path.split(REGEX_DOT);
		if (parts.length > 3)
		{
			return parts[3];
		}
		return "";
	}

	/**
	 * Returns the Load API field set of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return a <code>String</code> containing the Load API associated with the path
	 */
	public static String getLoadAPIFieldSet(String path)
	{
		// If the path contains 5 or more '.' delimited parts, then it's a normal
		// Load API fieldset path. For example, for the path InputFieldSet.REC1.APIFieldSet.CAA.APIField.GZNA1
		// the CAA substring must be returned:
		String[] parts = path.split(REGEX_DOT);
		if (parts.length > 5)
		{
			if (parts[2].equals(LOAD_API_FIELDSET_MAPPING_PATH))
			{
				return parts[3];
			}
		}
		return "";
	}

	/**
	 * Returns the PV field set of a mapping path
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return a <code>String</code> containing the PV field set associated with the path
	 */
	public static String getPVFieldSet(String path)
	{
		// If the path contains 5 or more '.' delimited parts, then it's a normal
		// PV fieldset path. For example, for the path InputFieldSet.REC1.PVFieldSet.C8R01R.PVField.C8CCY
		// the C8R01R substring must be returned:
		String[] parts = path.split(REGEX_DOT);
		if (parts.length > 7)
		{
			if (parts[4].equals(PV_FIELDSET_MAPPING_PATH))
			{
				return parts[5];
			}
		}
		return "";
	}

	/**
	 * Determine whether the mapping is a load API of the specified id
	 * 
	 * @param id
	 *            - the load API field set id
	 * @param path
	 *            - the mapping path
	 * 
	 * @return true if the path is a load api field set of the api field set
	 */
	public static boolean isLoadAPIPath(String id, String path)
	{
		return path.indexOf(getLoadAPIFieldSetPath(id) + DOT) >= 0;
	}

	/**
	 * Determine whether the mapping is to a primitive value
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return true if the path is to a primitive
	 */
	public static boolean isMappedToPrimitive(String path)
	{
		return path.indexOf(getType(TYPE_PRIMITIVE)) >= 0;
	}

	/**
	 * Determine whether the mapping is to an input value
	 * <p>
	 * Note that mappings to the Input form of fields are no longer allowed, so this method always returns false
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return true if the path is to an input
	 */
	public static boolean isMappedToInput(String path)
	{
		return path.indexOf(getType(TYPE_INPUT)) >= 0;
	}

	/**
	 * Determine whether the mapping is to an output value
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return true if the path is to an output
	 */
	public static boolean isMappedToOutput(String path)
	{
		return path.indexOf(getType(TYPE_OUTPUT)) >= 0;
	}

	/**
	 * Determine whether the mapping is to a work field
	 * 
	 * @param path
	 *            - the mapping path
	 * 
	 * @return true if the path is to a work field
	 */
	public static boolean isMappedToWorkField(String path)
	{
		return path.indexOf(getType(TYPE_WORK)) >= 0;
	}

	/**
	 * Returns the index of the given PV field from the list of mappings
	 * 
	 * @param fieldPath
	 *            - field path
	 * @param pvName
	 *            - PV name
	 * @param pvFieldName
	 *            - PV field name
	 * @param mappings
	 *            Collection of Mapping definitions to search
	 * 
	 * @return the index within the mappings
	 */
	public static int getMappedToPVFieldIndex(String fieldPath, String pvName, String pvFieldName, List<Mapping> mappings)
	{
		String path = getFullPVFieldSetPath(fieldPath, pvName) + DOT + getPVFieldPath(pvFieldName);
		return getMappedIndex(path, mappings);
	}

	/**
	 * Returns the index of the given Update API field from the list of mappings
	 * 
	 * @param apiFieldSet
	 *            - API field set
	 * @param apiField
	 *            - API field
	 * @param mappings
	 *            Collection of Mapping definitions to search
	 * 
	 * @return the index within the mappings
	 */
	public static int getMappedAPIFieldIndex(String apiFieldSet, String apiField, ArrayList<Mapping> mappings)
	{
		String path = getFullUpdateFieldPath(apiFieldSet, apiField);
		return getMappedIndex(path, mappings);
	}

	/**
	 * Return the index of the given path from the list
	 * 
	 * @param path
	 *            - the target path to locate in the list
	 * @param mappings
	 *            - the list of mappings
	 * 
	 * @return the index within the array (-1 if not found)
	 */
	public static int getMappedIndex(String path, List<Mapping> mappings)
	{
		for (int i = 0; i < mappings.size(); i++)
		{
			if (mappings.get(i).getTarget().equals(path))
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Return the value specified by the field that is mapped to the given path
	 * 
	 * @param functionData
	 *            - the Function Data
	 * @param path
	 *            - the target mapping path
	 * @param mappings
	 *            - an ArrayList of Mapping definitions
	 * 
	 * @return the value specified by the field that is mapped to the given path
	 */
	public static String getFieldData(FunctionData functionData, String path, ArrayList<Mapping> mappings)
	{
		String value = "";
		int index = getMappedIndex(path, mappings);
		if (index > 0)
		{
			Mapping mapping = mappings.get(index);
			String mapFromStr = getField(mapping.getSource());
			value = functionData.rtvFieldInputValue(mapFromStr);
		}

		// return value
		return value;
	}

}
