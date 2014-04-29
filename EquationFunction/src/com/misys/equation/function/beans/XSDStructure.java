package com.misys.equation.function.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.runtime.FunctionBankFusion;
import com.misys.equation.function.tools.XSDStructureHelper;
import com.misys.equation.function.tools.XSDStructureLink;
import com.misys.equation.function.tools.XSDToolbox;

public class XSDStructure extends Element
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XSDStructure.java 17290 2013-09-16 14:37:34Z williae1 $";

	private final EquationLogger LOG = new EquationLogger(this.getClass());

	public static final String GROUP_PREFIX = "+";
	public static final String REPEATINGGROUP_PREFIX = "-";

	public static final String REQUEST = "REQUEST";;
	public static final String RESPONSE = "RESPONSE";

	public static final String ID_REQUEST = GROUP_PREFIX + REQUEST;
	public static final String ID_RESPONSE = GROUP_PREFIX + RESPONSE;
	private static final String PLURALSUFFIX = "s";
	private static final String OUT = "Out";
	private static final String IN = "In";
	public static final String SIMPLE_TYPE_PREFIX = "FR_";

	private static final String XSD_STRUCTURE_DOT = "XSDStructure.";

	/**
	 * Minimum occurence
	 * 
	 * This is applicable to groups and repeating groups
	 * 
	 * For groups, the value with be 0 (optional) or 1 (mandatory)
	 * 
	 * For repeating groups, the value determines the minimum number of records the list can have
	 * */
	private int minOccur;

	/**
	 * 
	 * Maximum occurence
	 * 
	 * This is applicable to repeating groups. The value determines the maximum number of records the list can have. If it is zero,
	 * then it means there is no limit
	 * */
	private int maxOccur;

	/**
	 * Complex Type Id
	 * 
	 * This is applicable to groups
	 * 
	 * For groups, if a value is specified when the XSD is generated the definition will come from the reference service and that
	 * the fields inside the group must conform to the reference service definition.
	 * */
	private String complexTypeId;

	/**
	 * Service Field Id
	 * 
	 * This is applicable to fields inside a reusable complex type group. The complex type field must be mapped to a real service
	 * input field to load/unload data from the complex type.
	 * */
	private String serviceFieldId;

	/**
	 * List of subfields <br>
	 * */
	private ArrayList<XSDStructure> subFields = new ArrayList<XSDStructure>();

	/**
	 * Web service name at runtime.
	 * 
	 * This will have upper case as the first letter so that it is ready to be concatenate with the get/set prefix for the
	 * getter/setter method
	 * 
	 * This field will only be populated during runtime so it will not keep on deriving the web service name.
	 */
	private String runtimeWebserviceName = null;

	/**
	 * Complex type name
	 * 
	 * This is relevant only for group and refers to the actual complex type class name
	 * 
	 * This field will only be populated during runtime
	 */
	private String runtimeComplexType = null;

	/**
	 * Input field associated with this XSD. This is only populated for field
	 * 
	 * This field will only be populated during runtime so it will not keep on searching for the input field in the bean
	 */
	private InputField runtimeInputField = null;

	/**
	 * This determines the data type of the field value
	 * 
	 * This field will only be populated during runtime so it will not keep on deriving the data type
	 */
	private Class runtimeDataType = null;

	/**
	 * This the XPath from the root element.
	 * 
	 * This field will only be populated during runtime so it will not keep on deriving the path
	 */
	private String runtimePath = null;

	/**
	 * Return the group id name
	 * 
	 * @param id
	 *            - the group id
	 * @param repeatingGroup
	 *            - true if repeating group
	 * 
	 * @return the group id
	 */
	public static String getGroupName(String id, boolean repeatingGroup)
	{
		if (repeatingGroup)
		{
			return REPEATINGGROUP_PREFIX + id;
		}
		return GROUP_PREFIX + id;
	}

	/**
	 * Create an XSD structure
	 * 
	 * @param id
	 *            - the field id
	 * @param label
	 *            - the field label (web service name)
	 * 
	 * @return the XSD structure
	 */
	public static XSDStructure createField(String id, String label)
	{
		XSDStructure xsd = new XSDStructure();
		xsd.setId(id);
		xsd.setLabel(label);
		xsd.setParent(null);
		xsd.setServiceFieldId("");
		return xsd;
	}

	/**
	 * Create a group XSD structure
	 * 
	 * @param id
	 *            - the group id
	 * @param label
	 *            - the field label (web service name)
	 * @param repeatingGroup
	 *            - true if repeating group
	 * 
	 * @return the XSD structure
	 */
	public static XSDStructure createGroup(String id, String label, boolean repeatingGroup)
	{
		XSDStructure xsd = createField(getGroupName(id, repeatingGroup), label);
		xsd.setComplexTypeId("");
		return xsd;
	}

	/**
	 * Constructor
	 */
	public XSDStructure()
	{
		super();
	}

	/**
	 * Return the list of subfields
	 * 
	 * @return the list of subfields
	 */
	public ArrayList<XSDStructure> getSubFields()
	{
		return subFields;
	}

	/**
	 * Set the list of subfields
	 * 
	 * @param subFields
	 *            - the list of subfields
	 */
	public void setSubFields(ArrayList<XSDStructure> subFields)
	{
		this.subFields = subFields;
	}

	/**
	 * Add a subfield
	 * 
	 * @param subField
	 *            - the subfield
	 */
	public void addSubField(XSDStructure subField)
	{
		this.subFields.add(subField);
	}

	/**
	 * Add a subfield at a specified index
	 * 
	 * @param index
	 *            - the index position where this field will be inserted (0-based index)
	 * @param subField
	 *            - the subfield
	 * 
	 */
	public void addSubField(int index, XSDStructure subField)
	{
		this.subFields.add(index, subField);
	}

	/**
	 * Return the minimum occurence
	 * 
	 * @return the minimum occurence
	 */
	public int getMinOccur()
	{
		return minOccur;
	}

	/**
	 * Set the minimum occurence
	 * 
	 * @param minOccur
	 *            - the minimum occurence
	 */
	public void setMinOccur(int minOccur)
	{
		this.minOccur = minOccur;
	}

	/**
	 * Return the maximum occurence
	 * 
	 * @return the maximum occurence
	 */
	public int getMaxOccur()
	{
		return maxOccur;
	}

	/**
	 * Set the maximum occurence
	 * 
	 * @param maxOccur
	 *            - the maximum occurence
	 */
	public void setMaxOccur(int maxOccur)
	{
		this.maxOccur = maxOccur;
	}
	/**
	 * Return the complex type id
	 * 
	 * @return the complex type id
	 */
	public String getComplexTypeId()
	{
		return complexTypeId;
	}

	/**
	 * Set the complex type id
	 * 
	 * @param complexTypeId
	 *            - the complex type id
	 */
	public void setComplexTypeId(String complexTypeId)
	{
		this.complexTypeId = complexTypeId;
	}
	/**
	 * Return the service field id
	 * 
	 * @return the service field id
	 */
	public String getServiceFieldId()
	{
		return serviceFieldId;
	}

	/**
	 * Set the service field id
	 * 
	 * @param serviceFieldId
	 *            - the service field id
	 */
	public void setServiceFieldId(String serviceFieldId)
	{
		this.serviceFieldId = serviceFieldId;
	}

	/**
	 * Search for a field within the hierarchy
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param recursive
	 *            - true to find the field regardless where it is down the hierarchy<br>
	 *            - false to find the field only if it is directly one-level down below the hierarchy
	 * @param includeComplexTypes
	 *            - true if complex type service field mappings should be checked
	 * 
	 * @return the XSD structure of the field
	 */
	public XSDStructure findField(String fieldName, boolean recursive, boolean includeComplexTypes)
	{
		if (getId().equals(fieldName))
		{
			return this;
		}

		if (subFields != null)
		{
			for (XSDStructure xsdStructure : subFields)
			{
				// match?
				if (xsdStructure.getId().equals(fieldName))
				{
					return xsdStructure;
				}
				if (includeComplexTypes && xsdStructure.getServiceFieldId() != null
								&& xsdStructure.getServiceFieldId().equals(fieldName))
				{
					return xsdStructure;
				}

				// recursive?
				if (recursive)
				{
					XSDStructure xsd = xsdStructure.findField(fieldName, recursive, includeComplexTypes);
					if (xsd != null)
					{
						return xsd;
					}
				}
			}
		}
		return null;
	}
	/**
	 * Search for a field within the hierarchy and add to collection of service fields to complex type mappings. Mappings contain
	 * paths without XSDStructure labels.
	 * 
	 * @param mapping
	 *            - the field mapping collection to accumulate into
	 * 
	 * @return the mapping of service fields to complex type mappings
	 */
	public void findComplexTypeMappings(HashMap<String, String> mappings)
	{
		if (getServiceFieldId() != null && getServiceFieldId().trim().length() > 0)
		{
			if (mappings.containsKey(getServiceFieldId()))
			{
				String fieldUse = mappings.get(getServiceFieldId()) + Toolbox.COLON_DELIMITER + rtvPathWithoutXsdStructureLabels();
				mappings.put(getServiceFieldId(), fieldUse);
			}
			else
			{
				mappings.put(getServiceFieldId(), rtvPathWithoutXsdStructureLabels());
			}

		}
		for (XSDStructure xsdStructure : subFields)
		{
			xsdStructure.findComplexTypeMappings(mappings);
		}
	}

	/**
	 * Search for the parent of the field within the hierarchy
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the parent XSD structure of the field
	 */
	public XSDStructure findFieldParent(String fieldName)
	{
		if (getId().equals(fieldName))
		{
			return null;
		}

		if (subFields != null)
		{
			for (XSDStructure xsdStructure : subFields)
			{
				if (xsdStructure.getId().equals(fieldName))
				{
					return this;
				}

				XSDStructure xsd = xsdStructure.findFieldParent(fieldName);
				if (xsd != null)
				{
					return xsd;
				}
			}
		}
		return null;
	}

	/**
	 * Remove a field
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param recursive
	 *            - true to delete the field regardless where it is down the hierarchy<br>
	 *            - false to delete the field only if it is directly one-level down below the hierarchy
	 * 
	 * @return the XSD structure of the deleted field
	 */
	public XSDStructure removeField(String fieldName, boolean recursive)
	{
		if (subFields != null)
		{
			for (int i = 0; i < subFields.size(); i++)
			{
				XSDStructure xsd = subFields.get(i);

				// is this the field?
				if (xsd.getId().equals(fieldName))
				{
					subFields.remove(i);
					return xsd;
				}

				// recursive?
				if (recursive)
				{
					xsd.removeField(fieldName, recursive);
				}
			}
		}

		return null;
	}

	/**
	 * Remove a field's complex type mapping
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the XSD structure of the deleted field
	 */
	public XSDStructure removeComplexTypeFieldMapping(String fieldName)
	{
		if (subFields != null)
		{
			for (int i = 0; i < subFields.size(); i++)
			{
				XSDStructure xsd = subFields.get(i);

				// is this the field?
				if (xsd.getServiceFieldId() != null && xsd.getServiceFieldId().equals(fieldName))
				{
					xsd.setServiceFieldId(null);
				}

				xsd.removeComplexTypeFieldMapping(fieldName);

			}
		}

		return null;
	}

	/**
	 * Rename a field
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param recursive
	 *            - true to rename the field regardless where it is down the hierarchy<br>
	 *            - false to rename the field only if it is directly one-level down below the hierarchy
	 * @param newFieldName
	 *            - the new field name
	 * @param newLabel
	 *            - the new label (web service name)
	 * 
	 * @return the updated XSD structure of the field
	 */
	public XSDStructure renameField(String fieldName, boolean recursive, String newFieldName, String newLabel)
	{
		XSDStructure xsd = findField(fieldName, recursive, false);
		if (xsd != null)
		{
			xsd.setId(newFieldName);

			if (newLabel != null)
			{
				xsd.setLabel(newLabel);
			}
		}
		return xsd;
	}

	/**
	 * Rename complex type service field id
	 * 
	 * @param fieldName
	 *            - the field name
	 * @param newFieldName
	 *            - the new field name
	 */
	public void renameComplexTypeServiceField(String fieldName, String newFieldName)
	{

		if (getServiceFieldId() != null && getServiceFieldId().trim().length() > 0 && fieldName.equals(getServiceFieldId()))
		{
			setServiceFieldId(newFieldName);

		}
		for (XSDStructure xsdStructure : subFields)
		{
			xsdStructure.renameComplexTypeServiceField(fieldName, newFieldName);
		}
	}

	/**
	 * Append all the fields
	 * 
	 * @param function
	 *            - the Function definition
	 * @param requestFlag
	 *            - true if building a request XSD, false if response XSD
	 */
	public void appendFields(Function function, boolean requestFlag)
	{
		for (InputFieldSet inputFieldSet : function.getInputFieldSets())
		{
			appendFields(inputFieldSet, requestFlag);
		}
	}

	/**
	 * Append the list of fields from the input field set
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 * @param requestFlag
	 *            - true if building a request XSD, false if response XSD
	 */
	public void appendFields(InputFieldSet inputFieldSet, boolean requestFlag)
	{
		// Add all the non-repeating fields
		for (InputField inputField : inputFieldSet.getInputFields())
		{
			// Non-repeating fields
			String repeatGroupId = inputField.getRepeatingGroup().trim();
			if (repeatGroupId.length() == 0)
			{
				appendField(inputField, requestFlag);
			}
		}

		// Input group is only valid directly below the root element
		boolean root = chkRootRequest() || chkRootResponse();

		// Add the repeating fields
		if (root)
		{
			// add the repeating groups
			appendInputGroup(inputFieldSet, requestFlag);
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				// Non-repeating fields
				String repeatGroupId = inputField.getRepeatingGroup().trim();
				if (repeatGroupId.length() > 0)
				{
					XSDStructure xsdRepeat = findField(getGroupName(repeatGroupId, true), false, false);
					if (xsdRepeat != null)
					{
						xsdRepeat.appendField(inputField, requestFlag);
					}
				}
			}
		}
	}

	/**
	 * Append the input group as a repeating group
	 * 
	 * @param inputFieldSet
	 *            - the input field set
	 * @param requestFlag
	 *            - true if building a request XSD, false if response XSD
	 */
	protected void appendInputGroup(InputFieldSet inputFieldSet, boolean requestFlag)
	{
		for (InputGroup inputGroup : inputFieldSet.getInputGroups())
		{
			String id = inputGroup.getId();
			XSDStructure xsdRepeat = findField(getGroupName(id, true), false, false);
			if (xsdRepeat == null)
			{
				xsdRepeat = appendGroup(id, "", true, null);
			}
		}
	}

	/**
	 * Append an input field
	 * 
	 * @param inputField
	 *            - the input field
	 * @param requestFlag
	 *            - true if building a request XSD, false if response XSD
	 */
	public void appendField(InputField inputField, boolean requestFlag)
	{
		// request XSD
		if (requestFlag && findField(inputField.getId(), true, false) == null)
		{
			appendField(inputField.getId(), "", null, null);
		}

		// response XSD
		else if (!requestFlag && findField(inputField.getId(), true, false) == null)
		{
			appendField(inputField.getId(), "", null, null);
		}
	}

	/**
	 * Append a field
	 * 
	 * @param id
	 *            - the id
	 * @param label
	 *            - the field label (web service name)
	 * @param complexTypeId
	 *            - the optional complex type id
	 * @param serviceFieldId
	 *            - the service field id
	 * 
	 * @return the XSD structure of the field
	 */
	public XSDStructure appendField(String id, String label, String complexTypeId, String serviceFieldId)
	{
		XSDStructure xsd = createField(id, label);
		xsd.setParent(this);
		addSubField(xsd);
		if (complexTypeId != null)
		{
			xsd.complexTypeId = complexTypeId;
		}
		if (serviceFieldId != null)
		{
			xsd.serviceFieldId = serviceFieldId;
		}
		return xsd;
	}

	/**
	 * Append a group
	 * 
	 * @param id
	 *            - the group id
	 * @param label
	 *            - the field label (web service name)
	 * @param repeatingGroup
	 *            - true if repeating group
	 * @param complexTypeId
	 *            - the optional complex type id
	 * 
	 * @return the XSD structure of the group
	 */
	public XSDStructure appendGroup(String id, String label, boolean repeatingGroup, String complexTypeId)
	{
		XSDStructure xsd = createGroup(id, label, repeatingGroup);
		xsd.setParent(this);
		addSubField(xsd);
		if (complexTypeId != null)
		{
			xsd.complexTypeId = complexTypeId;
		}
		return xsd;
	}

	/**
	 * Append a group
	 * 
	 * @param group
	 *            - the group to copy
	 * 
	 * @return the XSD structure of the group
	 */
	public XSDStructure appendGroup(XSDStructure group)
	{
		XSDStructure xsd = createGroup(group.rtvBareId(), group.getLabel(), group.chkRepeatingGroup());
		if (group.getComplexTypeId() != null)
		{
			xsd.complexTypeId = group.getComplexTypeId();
		}
		xsd.setParent(this);
		addSubField(xsd);
		return xsd;
	}

	/**
	 * Append an XSD Structure by cloning
	 * 
	 * @param xsdStructuresToCopy
	 *            - the XSD structures to copy
	 * 
	 * @return the XSD structure of the field
	 */
	public void appendXsdStructure(ArrayList<XSDStructure> xsdStructuresToCopy)
	{
		// Add XSD structure fields to xsd group
		for (XSDStructure xsdSubFieldToCopy : xsdStructuresToCopy)
		{
			// Add field and add field to xsd group
			if (xsdSubFieldToCopy.chkField())
			{
				appendField(xsdSubFieldToCopy.getId(), xsdSubFieldToCopy.getLabel(), xsdSubFieldToCopy.getComplexTypeId(),
								xsdSubFieldToCopy.getServiceFieldId());
			}
			else
			{
				XSDStructure group = appendGroup(xsdSubFieldToCopy.rtvBareId(), xsdSubFieldToCopy.getLabel(), false,
								xsdSubFieldToCopy.getComplexTypeId());
				group.appendXsdStructure(xsdSubFieldToCopy.getSubFields());
			}

		}

	}

	/**
	 * Insert a field before/after the reference field
	 * 
	 * @param id
	 *            - the id
	 * @param label
	 *            - the field label (web service name)
	 * @param referenceId
	 *            - the reference id
	 * @param before
	 *            - true if the field is going to be inserted before the reference field
	 * 
	 * @return the XSD structure of the id (if created)
	 */
	public XSDStructure insertField(String id, String label, String referenceId, boolean before)
	{
		XSDStructure xsd = createField(id, label);
		return insertSubField(xsd, referenceId, before);
	}

	/**
	 * Insert a group before/after the reference field
	 * 
	 * @param id
	 *            - the id
	 * @param label
	 *            - the field label (web service name)
	 * @param referenceId
	 *            - the reference id
	 * @param before
	 *            - true if the field is going to be inserted before the reference field
	 * @param repeatingGroup
	 *            - true if repeating group
	 * @param complexTypeId
	 *            - the optional complex type id
	 * 
	 * @return the XSD structure of the id (if created)
	 */
	public XSDStructure insertGroup(String id, String label, String referenceId, boolean before, boolean repeatingGroup,
					String complexTypeId)
	{
		XSDStructure xsd = createGroup(id, label, repeatingGroup);
		if (complexTypeId != null)
		{
			xsd.complexTypeId = complexTypeId;
		}
		return insertSubField(xsd, referenceId, before);
	}

	/**
	 * Insert a group before/after the reference field
	 * 
	 * @param id
	 *            - the id
	 * @param label
	 *            - the field label (web service name)
	 * @param referenceId
	 *            - the reference id
	 * @param before
	 *            - true if the field is going to be inserted before the reference field
	 * @param repeatingGroup
	 *            - true if repeating group
	 * @param complexTypeId
	 *            - the optional complex type id
	 * 
	 * @return the XSD structure of the id (if created)
	 */
	public XSDStructure insertGroup(XSDStructure group, String referenceId, boolean before)
	{
		XSDStructure xsd = createGroup(group.rtvBareId(), group.getLabel(), group.chkRepeatingGroup());
		if (group.getComplexTypeId() != null)
		{
			xsd.complexTypeId = group.getComplexTypeId();
		}
		return insertSubField(xsd, referenceId, before);
	}

	/**
	 * Insert an XSD structure before/after the reference field
	 * 
	 * @param xsd
	 *            - the XSD structure to be inserted
	 * @param referenceId
	 *            - the reference id
	 * @param before
	 *            - true if the field is going to be inserted before the reference field
	 * 
	 * @return the XSD structure of the id (if created)
	 */
	public XSDStructure insertSubField(XSDStructure xsd, String referenceId, boolean before)
	{
		int index = findIndex(referenceId);
		if (index >= 0)
		{
			xsd.setParent(this);

			if (before)
			{
				addSubField(index, xsd);
			}
			else
			{
				addSubField(index + 1, xsd);
			}
			return xsd;
		}

		return null;
	}

	/**
	 * Determine the index of a field within the current group
	 * 
	 * @param referenceId
	 *            - the reference id
	 * 
	 * @return the index
	 */
	public int findIndex(String referenceId)
	{
		if (subFields != null)
		{
			for (int i = 0; i < subFields.size(); i++)
			{
				XSDStructure xsdField = subFields.get(i);

				// is this the field?
				if (xsdField.getId().equals(referenceId))
				{
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Convert to String
	 * 
	 * @return the String
	 */
	@Override
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append(getId() + " ");
		buffer.append(getLabel() + " ");
		return buffer.toString();
	}

	/**
	 * Determine if this is a group
	 * 
	 * @return true if this is a group
	 */
	public boolean chkGroup()
	{
		return getId().startsWith(GROUP_PREFIX);
	}

	/**
	 * Determine if this is a repeating group
	 * 
	 * @return true if this is a repeating group
	 */
	public boolean chkRepeatingGroup()
	{
		return (getId().startsWith(REPEATINGGROUP_PREFIX));
	}

	/**
	 * Determine if this is a field
	 * 
	 * @return true if this is a field
	 */
	public boolean chkField()
	{
		return !chkGroup() && !chkRepeatingGroup();
	}

	/**
	 * Return the root id
	 * 
	 * @return the root id
	 */
	@Override
	public String rtvBareId()
	{
		if (chkGroup())
		{
			return getId().substring(1);
		}
		else if (chkRepeatingGroup())
		{
			return getId().substring(1);
		}
		return getId();
	}

	/**
	 * Return the repeating group id
	 * 
	 * @return the repeating group id
	 */
	public String rtvRepeatingGroupId()
	{
		if (chkRepeatingGroup() || chkInsideRepeatingGroup() != null)
		{
			int pos = getId().indexOf("_");
			if (pos == -1)
			{
				return getId().substring(1);
			}
			else
			{
				return getId().substring(0, pos);
			}
		}
		return null;
	}

	/**
	 * Determine if this is the Request root
	 * 
	 * @return true if this is the Request root
	 */
	public boolean chkRootRequest()
	{
		return rtvBareId().equals(REQUEST);
	}

	/**
	 * Determine if this is the Response root
	 * 
	 * @return true if this is the Response root
	 */
	public boolean chkRootResponse()
	{
		return rtvBareId().equals(RESPONSE);
	}

	/**
	 * Determine if this is a complex type group
	 * 
	 * @return true if this is a complex type group
	 */
	public boolean chkComplexTypeGroup()
	{
		return (chkComplexType() && chkGroup());
	}

	/**
	 * Determine if this is a complex type field
	 * 
	 * @return true if this is a complex type field
	 */
	public boolean chkComplexTypeField()
	{
		return (chkComplexType() && chkField());
	}

	/**
	 * Determine if this is a complex type
	 * 
	 * @return true if this is a complex type
	 */
	public boolean chkComplexType()
	{
		return (getComplexTypeId() != null && getComplexTypeId().trim().length() > 0);
	}
	/**
	 * Determine if the field is a reusable simple type
	 * 
	 * @param service
	 * @param referenceService
	 * 
	 * @return the reusable simple type input field
	 */
	public InputField rtvReusableSimpleTypeInputField(Function service, Function referenceService)
	{

		if (chkField())
		{

			InputField serviceInputField = service.rtvInputField(getId());
			String referenceServiceInputFieldId = serviceInputField.getType();
			InputField simpleType = referenceService.rtvInputField(SIMPLE_TYPE_PREFIX + referenceServiceInputFieldId);
			if (simpleType == null)
			{
				// Get type from xsd property file which associates EQFREF type with CTS type.
				// Rates and Frequencies done this way.
				String commonXsd = XSDToolbox.getXsdConfigProperty(referenceServiceInputFieldId);
				if (commonXsd != null)
				{
					referenceServiceInputFieldId = commonXsd;
				}
				else
				// Dates
				if (serviceInputField.getDataType().equals("D"))
				{
					referenceServiceInputFieldId = "DATE";
				}
				else
				// Amounts
				if (serviceInputField.getCcyLinkedField() != null && serviceInputField.getCcyLinkedField().trim().length() > 0)
				{
					referenceServiceInputFieldId = "AMOUNT";
				}
			}
			return referenceService.rtvInputField(SIMPLE_TYPE_PREFIX + referenceServiceInputFieldId);

		}
		return null;
	}
	/**
	 * Determine the XSD web service name
	 * 
	 * @param function
	 *            This is relevant only for repeating groups or fields<br>
	 *            - the function
	 * @param request
	 *            This is relevant only for groups or repeating groups<br>
	 *            - true if generating XSD name for request<br>
	 *            - false if generating XSD name for response
	 * 
	 * @return the XSD name
	 */
	public String rtvWebServiceName(Function function, boolean request)
	{
		// Group
		if (chkGroup())
		{
			// derive the web service name

			// Root request
			if (chkRootRequest())
			{
				return FunctionBankFusion.getEnhancedServiceNameRequest(function);
			}

			// Root response
			else if (chkRootResponse())
			{
				return FunctionBankFusion.getEnhancedServiceNameResponse(function);
			}

			// Non-top level group
			else if (rtvParent() != null && rtvParent().rtvParent() != null)
			{
				return FunctionBankFusion.getEnhancedFieldName(rtvLabel());
			}

			// Top level group (directly under the root)
			else if (request)
			{
				return FunctionBankFusion.getEnhancedComplexTypeNameRequest(function, rtvLabel());
			}

			// Top level group (directly under the root)
			else
			{
				return FunctionBankFusion.getEnhancedComplexTypeNameResponse(function, rtvLabel());
			}
		}

		// Repeating group
		else if (chkRepeatingGroup())
		{
			String label = rtvLabel();
			if (label.trim().length() == 0)
			{
				InputGroup inputGroup = function.rtvInputGroup(rtvBareId());
				label = inputGroup.rtvLabel();
			}

			if (request)
			{
				return FunctionBankFusion.getEnhancedComplexTypeNameRequest(function, label);
			}
			else
			{
				return FunctionBankFusion.getEnhancedComplexTypeNameResponse(function, label);
			}
		}

		// Field
		else
		{
			InputField inputField = function.rtvInputField(getId());
			if (inputField != null)
			{
				return inputField.rtvWebServiceName(function.getXsdGeneration());
			}
			else
			{
				// Try as complex type field
				inputField = function.rtvInputField(getServiceFieldId());
				if (inputField != null)
				{
					return inputField.rtvWebServiceName(function.getXsdGeneration());
				}
				else
				{
					return LanguageResources.getString("Language.XSDComplexTypeFieldNotMapped");
				}
			}
		}
	}

	/**
	 * Determine the XSD web service name
	 * 
	 * @param function
	 *            This is relevant only for repeating groups or fields<br>
	 *            - the function
	 * @param request
	 *            This is relevant only for groups or repeating groups<br>
	 *            - true if generating XSD name for request<br>
	 *            - false if generating XSD name for response
	 * @param referenceService
	 *            This is relevant only for Complex Type fields
	 * 
	 * 
	 * @return the XSD name
	 */
	public String rtvWebServiceName(Function function, boolean request, Function referenceService)
	{

		String webServiceName = null;
		// complex type field,
		if (chkComplexTypeField() && getServiceFieldId() != null && getServiceFieldId().trim().length() > 0)
		{

			// Complex types could come from CTS request or response
			webServiceName = XSDStructureHelper.findXSD(referenceService, getId(), true, true).rtvWebServiceName(referenceService,
							true);
			if (webServiceName == null)
			{
				webServiceName = XSDStructureHelper.findXSD(referenceService, getId(), true, true).rtvWebServiceName(
								referenceService, false);
			}
			return webServiceName;
		}
		else
		{
			return rtvWebServiceName(function, request);
		}
	}

	/**
	 * Return the web service name for repeating group collection
	 * 
	 * @param function
	 * @return the web service name for repeating group collection
	 */
	public String rtvWebServiceRepeatingGroupCollectionsName(Function function)
	{
		String group = rtvWebServiceName(function, true);
		String repeatingGroupCollectionName = null;
		if (group.endsWith(OUT))
		{
			repeatingGroupCollectionName = group.substring(0, group.length() - 3) + PLURALSUFFIX + OUT;
		}
		else if (group.endsWith(IN))
		{
			repeatingGroupCollectionName = group.substring(0, group.length() - 2) + PLURALSUFFIX + IN;
		}
		else
		{
			repeatingGroupCollectionName = group + PLURALSUFFIX;
		}
		return repeatingGroupCollectionName;
	}

	/**
	 * Return the runtime web service name for repeating group collection
	 * 
	 * @return the runtime web service name for repeating group collection
	 */
	public String rtvRuntimeWebServiceRepeatingGroupCollectionsName()
	{

		String repeatingGroupCollectionName = rtvRuntimeWebServiceName();
		if (repeatingGroupCollectionName.endsWith(OUT))
		{
			repeatingGroupCollectionName = repeatingGroupCollectionName.substring(0, repeatingGroupCollectionName.length() - 3)
							+ PLURALSUFFIX + OUT;
		}
		else if (repeatingGroupCollectionName.endsWith(IN))
		{
			repeatingGroupCollectionName = repeatingGroupCollectionName.substring(0, repeatingGroupCollectionName.length() - 2)
							+ PLURALSUFFIX + IN;
		}
		else
		{
			repeatingGroupCollectionName = repeatingGroupCollectionName + PLURALSUFFIX;
		}
		return repeatingGroupCollectionName;
	}

	/**
	 * Return the web service name (runtime version)
	 * 
	 * @return the web service name (runtime version)
	 */
	public String rtvRuntimeWebServiceName()
	{
		return runtimeWebserviceName;
	}

	/**
	 * Return the actual complex type associated with this group
	 * 
	 * @return the web service name
	 */
	public String rtvRuntimeComplexType()
	{
		return runtimeComplexType;
	}

	/**
	 * Return the input field associated with this field
	 * 
	 * @return the input field associated with this XSD
	 */
	public InputField rtvRuntimeInputField()
	{
		return runtimeInputField;
	}

	/**
	 * Return the path during runtime
	 * 
	 * @return the path of this XSD
	 */
	public String rtvRuntimePath()
	{
		return runtimePath;
	}

	/**
	 * Return the XSD annotation
	 * 
	 * @param function
	 *            - the function
	 * 
	 * @return the XSD annotation
	 */
	public String rtvDescription(Function function)
	{
		String description = getDescription();

		if (description.equals(Element.DEFAULT_TEXT_VALUE))
		{
			description = "";
		}

		return description;
	}

	/**
	 * Setup the parent values
	 */
	public void setupParent()
	{
		if (subFields != null)
		{
			for (XSDStructure xsd : subFields)
			{
				xsd.setParent(this);
				xsd.setupParent();
			}
		}
	}

	/**
	 * Setup the runtime webservice name
	 * 
	 * @param function
	 *            - the Function bean
	 * @param request
	 *            - true if request XSD
	 * @param functionComplexType
	 *            - the Function containing the complex type definition
	 * @param functionComplextypeXsdLink
	 *            - the XSD Structure link of functionComplexType
	 */
	public void setupWebServiceNameRuntime(Function function, boolean request, Function functionComplexType,
					XSDStructureLink functionComplextypeXsdLink)
	{
		// complex type field?
		if (chkComplexTypeField())
		{
			runtimeWebserviceName = rtvWebServiceFromComplexType(functionComplexType, getId(), request, functionComplextypeXsdLink);
			runtimeComplexType = null;
		}

		// group complex type?
		else if (chkComplexTypeGroup())
		{
			// is this the top level complex type?
			if (chkTopLevelComplexType())
			{
				runtimeComplexType = rtvWebServiceFromComplexType(functionComplexType, getGroupName(complexTypeId, false), request,
								functionComplextypeXsdLink);
				runtimeWebserviceName = rtvWebServiceName(function, request);
			}

			// if this is not the top level complex type, then the web service name will be derived from whatever is defined in the
			// complex type function
			else
			{
				runtimeComplexType = rtvWebServiceFromComplexTypeGroup(functionComplexType, getId(), request,
								functionComplextypeXsdLink);
				runtimeWebserviceName = rtvWebServiceFromComplexType(functionComplexType, getId(), request,
								functionComplextypeXsdLink);
			}
		}

		// non-complex type related
		else
		{
			runtimeWebserviceName = rtvWebServiceName(function, request);
			runtimeComplexType = null;
		}

		// change first character to upper case
		runtimeWebserviceName = runtimeWebserviceName.substring(0, 1).toUpperCase() + runtimeWebserviceName.substring(1);
		if (runtimeComplexType == null)
		{
			runtimeComplexType = runtimeWebserviceName;
		}

		chgPathRunTime();
		rtvRuntimeDataType(functionComplextypeXsdLink);

		if (subFields != null)
		{
			for (XSDStructure xsd : subFields)
			{
				xsd.setupWebServiceNameRuntime(function, request, functionComplexType, functionComplextypeXsdLink);
			}
		}
	}

	/**
	 * Setup the runtime input field
	 * 
	 * @param function
	 *            - the Function bean
	 */
	public void setupInputFieldRunTime(Function function)
	{
		if (chkField())
		{
			if (serviceFieldId == null)
			{
				runtimeInputField = function.rtvInputField(getId());
			}
			else
			{
				runtimeInputField = function.rtvInputField(serviceFieldId);
			}
		}

		if (subFields != null)
		{
			for (XSDStructure xsd : subFields)
			{
				xsd.setupInputFieldRunTime(function);
			}
		}
	}

	/**
	 * Setup the runtime path
	 * 
	 * @return the runtime path
	 */
	public String chgPathRunTime()
	{
		StringBuilder currentPath = new StringBuilder();

		XSDStructure current = this;
		while (current != null)
		{
			currentPath.insert(0, "." + current.runtimeWebserviceName);
			current = (XSDStructure) current.rtvParent();
		}

		// remove the Request/Response webservice name from the path
		int index = currentPath.indexOf(".", 1);
		if (index >= 0)
		{
			runtimePath = currentPath.substring(index + 1);
		}
		else
		{
			runtimePath = currentPath.substring(1);
		}
		return runtimePath;
	}

	/**
	 * Determine if this field is inside a repeating group
	 * 
	 * @return the repeating group if this field is inside the repeating group
	 */
	public XSDStructure chkInsideRepeatingGroup()
	{
		// get the parent
		XSDStructure parent = (XSDStructure) rtvParent();

		// no parent
		if (parent == null)
		{
			return null;
		}

		if (parent.chkRepeatingGroup())
		{
			return parent;
		}
		else
		{
			return parent.chkInsideRepeatingGroup();
		}
	}

	/**
	 * Return the repeating group for this field
	 * 
	 * @return the repeating group for this field. Null if it is not a repeating group or not inside a repeating group
	 */
	public XSDStructure rtvTopLevelRepeatingGroup()
	{
		if (chkRepeatingGroup())
		{
			return this;
		}
		else
		{
			return chkInsideRepeatingGroup();
		}
	}

	/**
	 * Return the topmost parent of this item
	 * 
	 * @return the topmost parent of this item
	 */
	public XSDStructure rtvRoot()
	{
		if (rtvParent() == null)
		{
			return this;
		}
		else
		{
			return ((XSDStructure) rtvParent()).rtvRoot();
		}
	}

	/**
	 * Determine if this is part of the Request XSD
	 * 
	 * @return true if this is part of the Request XSD
	 */
	public boolean chkPartRequest()
	{
		XSDStructure xsd = rtvRoot();
		return xsd.chkRootRequest();
	}

	/**
	 * Determine if this is part of the Response XSD
	 * 
	 * @return true if this is part of the Response XSD
	 */
	public boolean chkPartResponse()
	{
		XSDStructure xsd = rtvRoot();
		return xsd.chkRootResponse();
	}

	/**
	 * Determine if the web service name must be unique
	 * 
	 * @return true if the web service name must be unique
	 * 
	 */
	public boolean chkMustBeUniqueWebServiceName()
	{
		return (chkGroup() && !chkComplexTypeGroup()) || chkRepeatingGroup();
	}
	/**
	 * Determine if the web service name is unique
	 * 
	 * @param function
	 *            - the Function
	 * @param webServiceFieldTypeName
	 *            a <code>String</code> containing the xsd web service type name of the field to check
	 * @param ignorePath
	 *            - ignore the field path matching with this id
	 * @param request
	 *            - true if matching with the Request XSD<br>
	 *            - false if matching with the Respond XSD<br>
	 * 
	 * @return the duplicate XSD structure if it is not unique
	 */
	public XSDStructure chkUniqueWebServiceName(Function function, String webServiceFieldTypeName, String ignorePath,
					boolean request)
	{
		// if this is not ignored
		if (!rtvPath().equals(ignorePath))
		{
			// If there is a complex type involved the web service name does not have to be unique
			if (chkMustBeUniqueWebServiceName())
			{
				String webName = rtvWebServiceName(function, request);
				if (webName.equals(webServiceFieldTypeName))
				{
					return this;
				}
			}
		}

		XSDStructure xsdDuplicate;
		for (XSDStructure xsd : getSubFields())
		{
			xsdDuplicate = xsd.chkUniqueWebServiceName(function, webServiceFieldTypeName, ignorePath, request);
			if (xsdDuplicate != null)
			{
				return xsdDuplicate;
			}
		}

		return null;
	}

	/**
	 * Determine if the web service name is unique in its parent
	 * 
	 * @param function
	 *            - the Function
	 * @param webServiceFieldTypeName
	 *            a <code>String</code> containing the xsd web service type name of the field to check
	 * @param ignorePath
	 *            - ignore the field path matching with this id
	 * @param request
	 *            - true if matching with the Request XSD<br>
	 *            - false if matching with the Respond XSD<br>
	 * 
	 * @return the duplicate XSD structure if it is not unique
	 */
	public XSDStructure chkUniqueWebServiceNameInParent(Function function, String webServiceFieldTypeName, String ignorePath,
					boolean request)
	{
		XSDStructure parent = (XSDStructure) rtvParent();
		// Check levels from parent and below
		if (parent != null)
		{
			// Check parent name
			String webName = parent.rtvWebServiceName(function, request);
			if (webName.equals(webServiceFieldTypeName))
			{
				return this;
			}

			for (XSDStructure subfield : parent.getSubFields())
			{
				// if this is not ignored
				if (!subfield.rtvPath().equals(ignorePath))
				{
					webName = subfield.rtvWebServiceName(function, request);
					if (webName.equals(webServiceFieldTypeName))
					{
						return this;
					}

				}
			}
			XSDStructure ancestor = (XSDStructure) parent.rtvParent();
			// Check levels from parent and above
			while (ancestor != null)
			{
				webName = ancestor.rtvWebServiceName(function, request);
				if (webName.equals(webServiceFieldTypeName))
				{
					return this;
				}
				ancestor = (XSDStructure) ancestor.rtvParent();
			}
		}
		return null;
	}

	/**
	 * Determine if the service field id is unique
	 * 
	 * @param function
	 *            - the Function
	 * @param serviceFieldId
	 *            a <code>String</code> containing the service field id of the field to check
	 * @param ignorePath
	 *            - ignore the field path matching with this id
	 * @param request
	 *            - true if matching with the Request XSD<br>
	 *            - false if matching with the Respond XSD<br>
	 * 
	 * @return the duplicate XSD structure if it is not unique
	 */
	public XSDStructure chkUniqueComplexFieldServiceId(Function function, String serviceFieldId, String ignorePath, boolean request)
	{
		// if this is not ignored
		if (!rtvPath().equals(ignorePath))
		{

			String xsdServiceId = getServiceFieldId();
			if (xsdServiceId != null && xsdServiceId.equals(serviceFieldId))
			{
				return this;
			}

		}

		XSDStructure xsdDuplicate;
		for (XSDStructure xsd : getSubFields())
		{
			xsdDuplicate = xsd.chkUniqueComplexFieldServiceId(function, serviceFieldId, ignorePath, request);
			if (xsdDuplicate != null)
			{
				return xsdDuplicate;
			}
		}

		return null;
	}
	/**
	 * Retrieves the path of this <code>Element</code>
	 * 
	 * @return a <code>String</code> of the path.
	 */
	public String rtvPath()
	{
		StringBuffer currentPath = new StringBuffer("");
		Element currentParent = this.rtvParent();
		// Note that WorkFields have the Function as their parent, but
		// paths should not include the parent
		while (currentParent != null)
		{
			currentPath.insert(0, currentParent.getClass().getSimpleName() + "." + currentParent.getId() + ".");
			if (currentParent.hasParent())
			{
				currentParent = currentParent.rtvParent();
			}
			else
			{
				currentPath.append(this.getClass().getSimpleName() + "." + this.getId());
				return currentPath.toString();
			}
		}
		currentPath.append(this.getClass().getSimpleName() + "." + this.getId());
		if (LOG.isDebugEnabled())
		{
			LOG.debug("Path for " + this.getClass().getSimpleName() + " with Id " + this.getId() + " is [" + currentPath.toString()
							+ "]");
		}
		return currentPath.toString();
	}

	/**
	 * Retrieves the path of this <code>Element</code> without XSDStructure
	 * 
	 * @return a <code>String</code> of the path.
	 */
	public String rtvPathWithoutXsdStructureLabels()
	{
		return rtvPath().replaceAll(XSDStructure.XSD_STRUCTURE_DOT, "");
	}

	/**
	 * Return the structure as a map
	 * 
	 * @return the structure as a list
	 */
	public Hashtable<String, XSDStructure> rtvAsMap()
	{
		Hashtable<String, XSDStructure> list = new Hashtable<String, XSDStructure>();
		rtvAsMap(list);
		return list;
	}

	/**
	 * Return the structure as a map
	 * 
	 * @param map
	 *            - the hash table
	 * 
	 * @return the structure as a list
	 */
	protected Hashtable<String, XSDStructure> rtvAsMap(Hashtable<String, XSDStructure> map)
	{
		map.put(rtvFieldName(), this);

		for (XSDStructure xsd : subFields)
		{
			xsd.rtvAsMap(map);
		}

		return map;
	}

	/**
	 * Return the path to this XSD Structure
	 * 
	 * @return the path to this XSD Structure
	 */
	public ArrayList<XSDStructure> rtvParents()
	{
		ArrayList<XSDStructure> list = new ArrayList<XSDStructure>();
		list.add(this);

		XSDStructure parent = (XSDStructure) rtvParent();
		while (parent != null)
		{
			// dont include the top most element (request/respond xsd)
			if (parent.rtvParent() == null)
			{
				break;
			}

			list.add(0, parent);
			parent = (XSDStructure) parent.rtvParent();
		}

		return list;
	}

	/**
	 * Return the order of classes that needs to be loaded prior to this XSD's class
	 * 
	 * @return the structure as a list
	 */
	public ArrayList<XSDStructure> rtvClassLoadingHierarchy()
	{
		ArrayList<XSDStructure> list = new ArrayList<XSDStructure>();
		rtvClassLoadingHierarchy(list);
		return list;
	}

	/**
	 * Return the order of classes that needs to be loaded prior to this XSD's class
	 * 
	 * @return the structure as a list
	 */
	protected ArrayList<XSDStructure> rtvClassLoadingHierarchy(ArrayList<XSDStructure> list)
	{
		for (XSDStructure xsd : subFields)
		{
			xsd.rtvClassLoadingHierarchy(list);
		}

		// add only groups
		if (!chkField())
		{
			list.add(this);
		}

		return list;
	}

	/**
	 * Retrieve the complex type name from the CTS
	 * 
	 * @param functionComplexType
	 *            - the Function containing the complex type definition
	 * @param id
	 *            - the XSD id
	 * @param request
	 *            - true if request XSD
	 * @param functionComplextypeXsdLink
	 *            - the XSD Structure link of functionComplexType
	 */
	protected String rtvWebServiceFromComplexType(Function functionComplexType, String id, boolean request,
					XSDStructureLink functionComplextypeXsdLink)
	{
		XSDStructureLink link = functionComplextypeXsdLink;

		// Request or response?
		XSDStructure xsd = null;
		xsd = link.getRequestXsd(id);

		if (xsd == null)
		{
			xsd = link.getResponseXsd(id);
		}

		// XSD not found?
		if (xsd == null)
		{
			LOG.error(LanguageResources.getFormattedString("XSDStructure.ComplexTypeNotFound", new String[] { id }));
			return "";
		}

		return xsd.rtvRuntimeWebServiceName();
	}

	/**
	 * Retrieve the complex type name from the CTS
	 * 
	 * @param functionComplexType
	 *            - the Function containing the complex type definition
	 * @param id
	 *            - the XSD id
	 * @param request
	 *            - true if request XSD
	 * @param functionComplextypeXsdLink
	 *            - the XSD Structure link of functionComplexType
	 */
	protected String rtvWebServiceFromComplexTypeGroup(Function functionComplexType, String id, boolean request,
					XSDStructureLink functionComplextypeXsdLink)
	{
		XSDStructureLink link = functionComplextypeXsdLink;

		// Request or response?
		XSDStructure xsd = null;
		xsd = link.getRequestXsd(id);

		if (xsd == null)
		{
			xsd = link.getResponseXsd(id);
		}

		// XSD not found?
		if (xsd == null)
		{
			LOG.error(LanguageResources.getFormattedString("XSDStructure.ComplexTypeNotFound", new String[] { id }));
			return "";
		}

		// If this is a complex type
		if (xsd.chkComplexType())
		{
			return rtvWebServiceFromComplexTypeGroup(functionComplexType, XSDStructure.getGroupName(xsd.getComplexTypeId(), false),
							request, functionComplextypeXsdLink);
		}
		else
		{
			return xsd.rtvRuntimeWebServiceName();
		}
	}

	/**
	 * Return the field id
	 * 
	 * @return the field id
	 */
	public String rtvFieldName()
	{
		if (serviceFieldId != null)
		{
			return serviceFieldId;
		}
		else
		{
			return getId();
		}
	}

	/**
	 * Return the top-most level complex type
	 * 
	 * @return the top-most level complex type
	 */
	public XSDStructure rtvTopLevelComplexType()
	{
		// is this part of the complex type at all?
		if (complexTypeId == null)
		{
			return null;
		}

		if (complexTypeId.trim().length() == 0)
		{
			return null;
		}

		// check the parent if part of the complex type
		XSDStructure xsdParent = (XSDStructure) rtvParent();

		// if it is part of the complex type, then further check the parent's parent
		if (xsdParent.chkComplexTypeGroup())
		{
			return xsdParent.rtvTopLevelComplexType();
		}

		// otherwise, this is the top-level one
		else
		{
			return this;
		}
	}

	/**
	 * Determine if this is the top-level complex type
	 * 
	 * @return true if this is a complex type and the top-level complex type
	 */
	public boolean chkTopLevelComplexType()
	{
		// is this part of the complex type at all?
		if (complexTypeId == null)
		{
			return false;
		}

		if (complexTypeId.trim().length() == 0)
		{
			return false;
		}

		// check the parent if part of the complex type
		XSDStructure xsdParent = (XSDStructure) rtvParent();

		if (xsdParent == null)
		{
			return false;
		}
		// parent is also in a complex type, then this is not the top-level
		if (xsdParent.chkComplexTypeGroup())
		{
			return false;
		}

		// otherwise, this is the top-level one
		else
		{
			return true;
		}
	}

	/**
	 * Return the runtime data type
	 * 
	 * @return the runtime data type
	 */
	public Class rtvRuntimeDataType()
	{
		return runtimeDataType;
	}

	/**
	 * Return and determine the runtime data type
	 * 
	 * @param functionComplextypeXsdLink
	 *            - the XSD Structure link of CTS
	 * 
	 * @return the runtime data type class
	 */
	public Class rtvRuntimeDataType(XSDStructureLink functionComplextypeXsdLink)
	{
		// already been setup?
		if (runtimeDataType != null)
		{
			return runtimeDataType;
		}

		// Derive the data type

		// complex type field - then check for the actual type in the CTS
		if (chkComplexTypeField())
		{
			String id = getId();
			XSDStructure xsd = functionComplextypeXsdLink.getRequestXsd(id);
			if (xsd == null)
			{
				xsd = functionComplextypeXsdLink.getResponseXsd(id);
			}

			runtimeDataType = xsd.rtvRuntimeDataType(functionComplextypeXsdLink);
		}

		// No equivalent field
		else if (runtimeInputField == null)
		{
			runtimeDataType = null;
		}

		// Boolean
		else if (EqDataType.TYPE_BOOLEAN.equals(runtimeInputField.getDataType()))
		{
			runtimeDataType = Boolean.class;
		}

		// Amount field?
		else if (runtimeInputField.getCcyLinkedField().trim().length() > 0)
		{
			runtimeDataType = BigDecimal.class;
		}

		// Numeric - Packed/Zoned
		else if (EqDataType.TYPE_PACKED.equals(runtimeInputField.getDataType())
						|| EqDataType.TYPE_ZONED.equals(runtimeInputField.getDataType()))
		{
			if (runtimeInputField.getDecimals() != null && !runtimeInputField.getDecimals().trim().equals("")
							&& !runtimeInputField.getDecimals().trim().equals("0"))
			{
				runtimeDataType = BigDecimal.class;
			}
			else
			{
				Integer size = new Integer(runtimeInputField.getSize());
				if (size < 10)
				{
					runtimeDataType = Integer.class;
				}
				else
				{
					runtimeDataType = Long.class;
				}
			}
		}

		// Default - String type
		else
		{
			runtimeDataType = String.class;
		}

		// return the data type
		return runtimeDataType;
	}

}
