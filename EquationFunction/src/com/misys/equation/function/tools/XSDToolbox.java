package com.misys.equation.function.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQRuntimeException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.beans.XSDStructure;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionBankFusion;

/**
 * This class generates a XML schema definition (.XSD) from a function bean model
 * <p>
 * For repeating data, it is currently assumed that all repeating items are included in a single block (or API).
 * 
 */
public class XSDToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: XSDToolbox.java 17473 2013-10-22 11:16:29Z lima12 $";

	/** File extension for XSD Schemas */
	public final static String SCHEMA_EXTENSION = "xsd";

	/** The colon separator for namespace prefixes */
	private static final String COLON = ":";

	// Tags
	public static final String SCHEMA_TAG = "xs:schema";
	public static final String ELEMENT_TAG = "xs:element";
	public static final String SIMPLE_TYPE_TAG = "xs:simpleType";
	public static final String SIMPLE_CONTENT_TAG = "xs:simpleContent";
	public static final String EXTENSION_TAG = "xs:extension";
	public static final String ATTRIBUTE_TAG = "xs:attribute";
	public static final String COMPLEX_TYPE_TAG = "xs:complexType";
	public static final String COMPLEX_CONTENT_TAG = "xs:complexContent";
	public static final String SEQUENCE_TAG = "xs:sequence";
	public static final String RESTRICTION_TAG = "xs:restriction";
	public static final String ENUMERATION_TAG = "xs:enumeration";
	public static final String MIN_LENGTH_TAG = "xs:minLength";
	public static final String MAX_LENGTH_TAG = "xs:maxLength";
	public static final String TOTAL_DIGITS_TAG = "xs:totalDigits";
	public static final String FRACTION_DIGITS_TAG = "xs:fractionDigits";
	public static final String PATTERN_TAG = "xs:pattern";
	public static final String IMPORT_TAG = "xs:import";
	public static final String ANNOTATION_TAG = "xs:annotation";
	public static final String DOCUMENTATION_TAG = "xs:documentation";

	// AttributeTags
	public static final String NAMESPACE_ATTR = "xmlns";

	public static final String BASE_SCHEMA_ATTR = "xmlns:xs";

	/** This is the namespace used for the all the service type definitions. */
	public static final String EQ_SERVICE_NAMESPACE = "http://www.misys.com/equation/bankfusion/service";

	/** This is the namespace used for the all the pv definitions. */
	public static final String EQ_PV_NAMESPACE = "http://www.misys.com/equation/bankfusion/search";

	/** This is the namespace used for the common type definitions. */
	public static final String EQ_TYPE_NAMESPACE = "http://www.misys.com/equation/bankfusion/types";

	/** This is the namespace used for the common type definitions. */
	public static final String CTS_TYPE_NAMESPACE = "http://www.misys.com/equation/bankfusion/complextypes";

	public static final String XML_SCHEMA_NAMESPACE = "http://www.w3.org/2001/XMLSchema";

	public static final String VERSION_ATTR = "version";
	public static final String TARGET_NAMESPACE_ATTR = "targetNamespace";
	public static final String ELEMENT_FORM_DEFAULT_ATTR = "elementFormDefault";
	public static final String NAME_ATTR = "name";
	public static final String TYPE_ATTR = "type";
	public static final String BASE_ATTR = "base";
	public static final String VALUE_ATTR = "value";
	public static final String MIN_OCCURS_ATTR = "minOccurs";
	public static final String MAX_OCCURS_ATTR = "maxOccurs";
	public static final String DEFAULT_ATTR = "default";
	public static final String USE_ATTR = "use";
	public static final String ID_ATTR = "id";
	public static final String INCLUDE_NAMESPACE_ATTR = "namespace";
	public static final String INCLUDE_SCHEMA_LOCATION_ATTR = "schemaLocation";

	public static final String XS_STRING = "xs:string";
	public static final String XS_BOOLEAN = "xs:boolean";
	public static final String XS_DECIMAL = "xs:decimal";
	public static final String XS_INTEGER = "xs:int";
	public static final String XS_LONG = "xs:long";
	public static final String DEFAULT = "default";
	public static final String QUALIFIED = "qualified";
	public static final String UNQUALIFIED = "unqualified";
	public static final String DOCUMENT = "Document";
	/** MaxOccurs unbounded value */
	public static final String UNBOUNDED = "unbounded";
	public static final String MISYS_URN_ROOT = "urn:misys:xsd:";

	public static final String EQ_PV_ROW = "_PV_row";

	/**
	 * The URL of the global reference schema. Note that this is currently a bare file name as BankFusion will not manage inclusion
	 * of schemas from different folders.
	 */
	public static final String INCLUDE_SCHEMA_LOCATION = "equationFREF.xsd";

	/** BFEQ CTS */
	public static final String CTS_SCHEMA_FOLDER = "EquationCoreXSD";
	public static final String CTS_SCHEMA_FILE = "EQ_CMN_templateServiceCTS.xsd";
	public static final String CTS_SCHEMA_LOCATION = CTS_SCHEMA_FOLDER + "/" + CTS_SCHEMA_FILE;
	public static final String INCLUDE_CTS_SCHEMA_LOCATION = "../../" + CTS_SCHEMA_FOLDER + "/" + CTS_SCHEMA_FILE;
	public static final String BANKFUSION_INCLUDE_CTS_SCHEMA_LOCATION = CTS_SCHEMA_FILE;

	/** BFEQ Core XSD details */
	public static final String EQFHDR_ID = "eqfhdr";
	public static final String SERVICE_REQUEST_HEADER_TYPE = EQFHDR_ID + COLON + "serviceRqHeader";
	public static final String SERVICE_RESPONSE_HEADER_TYPE = EQFHDR_ID + COLON + "serviceRsHeader";
	public static final String SERVICE_HEADER_TAG = "ServiceHeader";
	public static final String SERVICE_DATA_TAG = "ServiceData";
	public static final String EQFHDR_BF_PACKAGE = "bf.com.misys.eqf.types.header";
	public static final String EQFHDR_TYPE_NAMESPACE = "http://www.misys.com/eqf/types/header";
	public static final String EQFHDR_SCHEMA_FILE = "EQ_MSG_MessageHeader.xsd";
	public static final String EQFHDR_SCHEMA_LOCATION = CTS_SCHEMA_FOLDER + "/" + EQFHDR_SCHEMA_FILE;
	public static final String INCLUDE_EQFHDR_SCHEMA_LOCATION = "../../" + CTS_SCHEMA_FOLDER + "/" + EQFHDR_SCHEMA_FILE;
	public static final String BANKFUSION_INCLUDE_EQFHDR_SCHEMA_LOCATION = EQFHDR_SCHEMA_FILE;

	public static final String BF_NAMESPACE = "http://www.misys.com/bankfusion/attributes";

	private static List<String> types = new ArrayList<String>();

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(XSDToolbox.class);

	private static final String XSD_PROPERTY_FILE = "xsdConfiguration.properties";
	private static Hashtable<String, String> xsdProperties = new Hashtable<String, String>();

	static
	{

		URL resourceURL = null;
		String systemPropertyValue = null;

		// The property is named after the property file.
		systemPropertyValue = System.getProperty(XSD_PROPERTY_FILE);

		if (systemPropertyValue != null && systemPropertyValue.length() > 0)
		{
			try
			{
				resourceURL = new URL(new StringBuilder("file:").append(systemPropertyValue.replace("\\", "/")).toString());
			}
			catch (Exception exception)
			{
				LOG.error(exception);
			}
		}
		else
		{
			resourceURL = Thread.currentThread().getContextClassLoader().getResource(XSD_PROPERTY_FILE);
		}

		// Need to check if we can load the properties file for the given key name
		InputStream propertiesIS;
		try
		{
			propertiesIS = resourceURL.openStream();

			if (propertiesIS != null)
			{
				Properties properties = new Properties();

				try
				{
					properties.load(propertiesIS);
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				for (Entry<Object, Object> propertyName : properties.entrySet())
				{
					xsdProperties.put(((String) propertyName.getKey()).trim(), ((String) properties.get(propertyName.getKey()))
									.trim());
				}

			}
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/** Constructor */
	private XSDToolbox()
	{
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
	 * Generate the XSD element of the function
	 * <p>
	 * This returns an XSD Document in the form required for BankFusion attribute type definition.
	 * 
	 * @param function
	 *            - the function
	 * @param referenceService
	 *            - the reference service storing complex types
	 * @param isBankFusion
	 *            - BankFusion is installed
	 * 
	 * @return a <code>Document</code> containing the schema
	 */
	public static Document getFunctionSchemaDocument(Function function, Function referenceService, boolean isBankFusion)
	{

		types.clear();

		// get us a DOM factory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		// get us a DOM builder
		DocumentBuilder documentBuilder;
		try
		{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			throw new EQRuntimeException("Could not create a document builder", e);
		}
		// get us a DOM builder
		Document document = documentBuilder.newDocument();

		// get us the schema element (root)
		Element schemaElement = createSchemaElement(document, function);

		// add CTS import and Equation header
		if (function.chkXSDGeneric() && !function.getId().equals(EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID))
		{
			schemaElement.setAttribute(NAMESPACE_ATTR + COLON + EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID.toLowerCase(),
							CTS_TYPE_NAMESPACE);
			schemaElement.setAttribute(NAMESPACE_ATTR + COLON + EQFHDR_ID, EQFHDR_TYPE_NAMESPACE);
		}
		document.appendChild(schemaElement);

		// Add the import of the CTS reference schema and Equation header
		if (function.chkXSDGeneric() && !function.getId().equals(EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID))
		{
			addCtsImportElement(schemaElement, isBankFusion);
			addEqfHdrImportElement(schemaElement, isBankFusion);
		}

		if (!function.chkXSDGeneric())
		{
			// Get a collection of the the repeating field Elements
			Map<String, Element> repeatingTypesSequenceElements = createRepeatingTypesSequence(document, function);
			// get us the function type element
			Element functionElement = createFunctionElement(document, function, repeatingTypesSequenceElements);
			schemaElement.appendChild(functionElement);

			// Add all repeating block type sequences
			for (Map.Entry<String, Element> entry : repeatingTypesSequenceElements.entrySet())
			{
				Element repeatingTypesSequenceElement = entry.getValue();
				Element repeatingBlockElement = getRepeatingBlockDefinitionType(document, function, entry.getKey(),
								repeatingTypesSequenceElement);
				schemaElement.appendChild(repeatingBlockElement);
			}

		}
		else
		{
			// User reference schema for Complex Types
			// Process the XSD Structure
			HashMap<String, Element> complexTypesSequenceElements = new HashMap<String, Element>();
			HashMap<String, XSDStructure> complexTypes = new HashMap<String, XSDStructure>();

			createEnhancedXSDSequence(complexTypesSequenceElements, complexTypes, document, function, function
							.getXsdStructureRequest(), referenceService);
			createEnhancedXSDSequence(complexTypesSequenceElements, complexTypes, document, function, function
							.getXsdStructureResponse(), referenceService);

			// if Request or Response has no fields then generate empty class
			if (!complexTypes.containsKey(function.getXsdStructureRequest().rtvWebServiceName(function, true)))
			{
				Element emptyType = getEnhancedXsdEmptyType(document, function, function.getXsdStructureRequest());
				schemaElement.appendChild(emptyType);
			}
			if (!complexTypes.containsKey(function.getXsdStructureResponse().rtvWebServiceName(function, true)))
			{
				Element emptyType = getEnhancedXsdEmptyType(document, function, function.getXsdStructureResponse());
				schemaElement.appendChild(emptyType);
			}
			// Add all repeating block type sequences
			for (Map.Entry<String, Element> entry : complexTypesSequenceElements.entrySet())
			{
				Element repeatingTypesSequenceElement = entry.getValue();
				XSDStructure xsdStructure = complexTypes.get(entry.getKey());
				if (!xsdStructure.chkComplexTypeGroup())
				{
					if (xsdStructure.chkRepeatingGroup())
					{
						Element repeatingGroupType = getEnhancedXsdRepeatingGroupType(document, function, xsdStructure,
										repeatingTypesSequenceElement);
						schemaElement.appendChild(repeatingGroupType);
					}
					Element groupType = getEnhancedXsdGroupType(document, function, xsdStructure, repeatingTypesSequenceElement);
					schemaElement.appendChild(groupType);
				}
			}

			// For CTS service, add the simple types
			if (function.getId().equals(referenceService.getId()))
			{
				createReusableSimpleTypeFieldElement(document, schemaElement, function, function.getXsdStructureRequest());
			}

			// For enhanced service, then generate the combined complex type
			// add a complex type request and a a complex type response combining the ServiceHeader and the data
			else
			{
				String requestComplexTypeName = getCombinedComplexTypeRequestName(function);
				String responseComplexTypeName = getCombinedComplexTypeResponseName(function);
				String requestDataType = function.getId().toLowerCase() + COLON
								+ function.getXsdStructureRequest().rtvWebServiceName(function, true);
				String responseDataType = function.getId().toLowerCase() + COLON
								+ function.getXsdStructureResponse().rtvWebServiceName(function, false);
				Element combinedRequest = createCombinedComplexType(document, requestComplexTypeName, requestDataType,
								SERVICE_REQUEST_HEADER_TYPE);
				Element combinedResponse = createCombinedComplexType(document, responseComplexTypeName, responseDataType,
								SERVICE_RESPONSE_HEADER_TYPE);
				schemaElement.appendChild(combinedRequest);
				schemaElement.appendChild(combinedResponse);
			}
		}

		return document;
	}

	/**
	 * Generate the XSD element of a PV
	 * <p>
	 * This returns an XSD Document in the form required for BankFusion attribute type definition.
	 * 
	 * @param pvMetaData
	 *            - the PV meta data
	 * 
	 * @return a <code>Document</code> containing the schema
	 */
	public static Document getPVSchemaDocument(EquationPVMetaData pvMetaData)
	{
		types.clear();

		// get us a DOM factory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

		// get us a DOM builder
		DocumentBuilder documentBuilder;
		try
		{
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			throw new EQRuntimeException("Could not create a document builder", e);
		}
		// get us a DOM builder
		Document document = documentBuilder.newDocument();

		// get us the schema element (root)
		Element schemaElement = createSchemaElement(document, pvMetaData);

		document.appendChild(schemaElement);

		// get us the pv type element
		Element pvElement = createPVElement(document, pvMetaData);
		schemaElement.appendChild(pvElement);

		// Need to add an empty complex type..
		Element baseComplexType = document.createElement(COMPLEX_TYPE_TAG);
		baseComplexType.setAttribute(NAME_ATTR, pvMetaData.getPvMicroflowName() + "_row");
		schemaElement.appendChild(baseComplexType);

		return document;
	}

	/**
	 * Helper method which creates a XML element for a function XSD schema
	 * 
	 * @param document
	 *            The base Document
	 * @param function
	 *            The service definition
	 * 
	 * @return XML element snippet representing the schema element
	 */
	private static Element createSchemaElement(Document document, Function function)
	{
		// Main schema element
		Element schemaElement = document.createElement(SCHEMA_TAG);

		schemaElement.setAttribute(BASE_SCHEMA_ATTR, XML_SCHEMA_NAMESPACE);

		if (function.getId().equals(EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID))
		{
			schemaElement.setAttribute(getBFNameSpaceAttr(function.getId()), CTS_TYPE_NAMESPACE);
			schemaElement.setAttribute(TARGET_NAMESPACE_ATTR, CTS_TYPE_NAMESPACE);
		}
		else
		{
			schemaElement.setAttribute(getBFNameSpaceAttr(function.getId()), getComplexTypeURL(function));
			schemaElement.setAttribute(TARGET_NAMESPACE_ATTR, getComplexTypeURL(function));
		}

		schemaElement.setAttribute(ELEMENT_FORM_DEFAULT_ATTR, UNQUALIFIED);
		schemaElement.setAttribute(VERSION_ATTR, function.getXsdVersion());

		return schemaElement;
	}
	/**
	 * Helper method which creates a XML element for a PV XSD schema
	 * 
	 * @param document
	 *            The base Document
	 * @param pvMetaData
	 *            The PV meta data
	 * 
	 * @return XML element snippet representing the schema element
	 */
	private static Element createSchemaElement(Document document, EquationPVMetaData pvMetaData)
	{
		// Main schema element
		Element schemaElement = document.createElement(SCHEMA_TAG);

		schemaElement.setAttribute(BASE_SCHEMA_ATTR, XML_SCHEMA_NAMESPACE);
		schemaElement.setAttribute(getBFNameSpaceAttr(pvMetaData.getId()), EQ_PV_NAMESPACE + "/"
						+ pvMetaData.getModuleId().toLowerCase());
		schemaElement.setAttribute(TARGET_NAMESPACE_ATTR, EQ_PV_NAMESPACE + "/" + pvMetaData.getModuleId().toLowerCase());
		schemaElement.setAttribute(ELEMENT_FORM_DEFAULT_ATTR, UNQUALIFIED);

		return schemaElement;
	}

	/**
	 * Helper method which creates a XML element
	 * 
	 * @param document
	 *            XML document
	 * @param function
	 *            - The function for which we need to create an XML representation
	 * @param repeatingTypesSequenceElements
	 *            the xs:schema definition of the repeating types (may be null)
	 * 
	 * @return XML element snippet representing the function
	 */
	private static Element createFunctionElement(Document document, Function function,
					Map<String, Element> repeatingTypesSequenceElements)
	{
		Element functionElement = document.createElement(COMPLEX_TYPE_TAG);
		functionElement.setAttribute(NAME_ATTR, FunctionBankFusion.getServiceMicroflowName(function));

		Element functionElementSequence = document.createElement(SEQUENCE_TAG);

		// loop the field sets
		for (InputFieldSet fieldSet : function.getInputFieldSets())
		{
			// loop through the fields within the field set
			for (InputField inputField : fieldSet.getInputFields())
			{
				// Only include non-repeating fields at this stage
				if (!Field.isRepeating(inputField) && !inputField.isExcludeFromType())
				{
					Element fieldElement = getFieldElement(document, inputField);
					functionElementSequence.appendChild(fieldElement);
				}
			}
		}

		// Add all repeating block sequences after the non-repeating fields
		for (Map.Entry<String, Element> entry : repeatingTypesSequenceElements.entrySet())
		{
			Element repeatingTypesSequenceElement = entry.getValue();
			Element repeatingBlockElement = getRepeatingBlockDefinition(document, function, entry.getKey(),
							repeatingTypesSequenceElement);
			functionElementSequence.appendChild(repeatingBlockElement);
		}

		functionElement.appendChild(functionElementSequence);
		return functionElement;
	}

	/**
	 * Helper method which creates a XML element
	 * 
	 * @param document
	 *            XML document
	 * @param pvMetaData
	 *            - The meta data of the PV for which we need to create an XML representation
	 * 
	 * @return XML element snippet representing the function
	 */
	private static Element createPVElement(Document document, EquationPVMetaData pvMetaData)
	{
		Element pvElement = document.createElement(COMPLEX_TYPE_TAG);
		pvElement.setAttribute(NAME_ATTR, pvMetaData.getPvMicroflowName());

		Element pvElementSequence = document.createElement(SEQUENCE_TAG);

		// Add the repeating block element
		Element repeatingBlockElement = document.createElement(ELEMENT_TAG);
		repeatingBlockElement.setAttribute(NAME_ATTR, pvMetaData.getPvMicroflowName() + EQ_PV_ROW);
		repeatingBlockElement.setAttribute(MIN_OCCURS_ATTR, "0");
		repeatingBlockElement.setAttribute(MAX_OCCURS_ATTR, UNBOUNDED);
		pvElementSequence.appendChild(repeatingBlockElement);

		// Inside the element, declare complex type, extension and sequence. Then
		// the repeating elements are added to the sequence...
		Element complexTypeElement = document.createElement(COMPLEX_TYPE_TAG);
		repeatingBlockElement.appendChild(complexTypeElement);

		Element complexContentElement = document.createElement(COMPLEX_CONTENT_TAG);
		complexTypeElement.appendChild(complexContentElement);

		Element extensionElement = document.createElement(EXTENSION_TAG);
		extensionElement.setAttribute(BASE_ATTR, pvMetaData.getId().toLowerCase() + COLON + pvMetaData.getPvMicroflowName()
						+ "_row");
		complexContentElement.appendChild(extensionElement);

		Element extensionSequenceElement = document.createElement(SEQUENCE_TAG);
		extensionElement.appendChild(extensionSequenceElement);

		// process each pv field
		for (int i = 0; i < pvMetaData.rtvNumberOfFields(); i++)
		{
			Element fieldElement = getFieldElement(document, pvMetaData.rtvFieldMetaData(i));
			extensionSequenceElement.appendChild(fieldElement);
		}

		pvElement.appendChild(pvElementSequence);

		return pvElement;
	}

	/**
	 * Helper method which creates a XML Element containing the repeating fields sequence tag
	 * <p>
	 * Note that this is the the xs:sequence element; which needs to be added to a parent tag
	 * 
	 * @param document
	 *            XML document
	 * @param function
	 *            - The function for which we need to create an XML representation
	 * 
	 * @return Map of XML Elements representing the repeating field groups, keyed by RepeatingGroup id. May be empty, but not null.
	 */
	private static Map<String, Element> createRepeatingTypesSequence(Document document, Function function)
	{
		Map<String, Element> result = new HashMap<String, Element>();

		// loop the field sets
		for (InputFieldSet fieldSet : function.getInputFieldSets())
		{
			// loop through the fields within the field set
			for (InputField inputField : fieldSet.getInputFields())
			{
				// Only include repeating fields
				if (Field.isRepeating(inputField) && !inputField.isExcludeFromType())
				{
					Element repeatingElementSequence = result.get(inputField.getRepeatingGroup());
					if (repeatingElementSequence == null)
					{
						repeatingElementSequence = document.createElement(SEQUENCE_TAG);
						result.put(inputField.getRepeatingGroup(), repeatingElementSequence);
					}

					Element fieldElement = getFieldElement(document, inputField);
					repeatingElementSequence.appendChild(fieldElement);
				}
			}
		}
		return result;
	}

	/**
	 * Helper method which creates a XML Element containing the repeating fields sequence tag
	 * <p>
	 * Note that this is the the xs:sequence element; which needs to be added to a parent tag
	 * 
	 * @param repeatingTypesSequenceElements
	 * @param complexTypes
	 *            - the map of already used complex types
	 * @param document
	 *            The XML document
	 * @param function
	 *            - The function for which we need to create an XML representation
	 * @param xsdStructure
	 * @param referenceService
	 * 
	 * @return Map of XML Elements representing the repeating field groups, keyed by RepeatingGroup id. May be empty, but not null.
	 * @throws EQException
	 */
	private static void createEnhancedXSDSequence(HashMap<String, Element> repeatingTypesSequenceElements,
					HashMap<String, XSDStructure> complexTypes, Document document, Function function, XSDStructure xsdStructure,
					Function referenceService)
	{
		if (!complexTypes.containsKey(getEnhancedXsdName(function, xsdStructure)) && xsdStructure.getSubFields().size() > 0)
		{
			// loop through the fields within the field set
			for (XSDStructure xsdSubField : xsdStructure.getSubFields())
			{

				if (xsdSubField.chkField() && !xsdSubField.chkComplexTypeField())
				{
					InputField inputField = function.rtvInputField(xsdSubField.getId());
					// Only include repeating fields
					if (!inputField.isExcludeFromType())
					{
						Element repeatingElementSequence = repeatingTypesSequenceElements.get(getEnhancedXsdName(function,
										xsdStructure));
						if (repeatingElementSequence == null)
						{
							repeatingElementSequence = document.createElement(SEQUENCE_TAG);
							repeatingTypesSequenceElements
											.put(getEnhancedXsdName(function, xsdStructure), repeatingElementSequence);
						}

						Element fieldElement = getEnhancedXsdFieldElement(document, function, inputField, xsdSubField,
										referenceService);
						repeatingElementSequence.appendChild(fieldElement);
					}
				}
				else
				{

					Element repeatingElementSequence = repeatingTypesSequenceElements
									.get(getEnhancedXsdName(function, xsdStructure));
					if (repeatingElementSequence == null)
					{
						repeatingElementSequence = document.createElement(SEQUENCE_TAG);
						repeatingTypesSequenceElements.put(getEnhancedXsdName(function, xsdStructure), repeatingElementSequence);
					}

					Element groupElement = getEnhancedXsdGroupElement(document, function, xsdSubField, referenceService);
					repeatingElementSequence.appendChild(groupElement);
					createEnhancedXSDSequence(repeatingTypesSequenceElements, complexTypes, document, function, xsdSubField,
									referenceService);
				}

			}

			complexTypes.put(getEnhancedXsdName(function, xsdStructure), xsdStructure);

		}

	}
	/**
	 * Helper method which creates a XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param inputField
	 *            The InputField definition for which to create the type definition
	 * 
	 * 
	 * @return An Element containing the field definition
	 */
	private static Element getFieldElement(Document document, InputField inputField)
	{
		Element fieldElementSequenceElement = document.createElement(ELEMENT_TAG);
		fieldElementSequenceElement.setAttribute(NAME_ATTR, FunctionBankFusion.getFieldName(inputField));

		// If not using a reference type, then use anonymous type definition
		Element simpleTypeElement = document.createElement(SIMPLE_TYPE_TAG);
		fieldElementSequenceElement.appendChild(simpleTypeElement);
		// add the field restrictions
		addFieldRestrictionsToElement(simpleTypeElement, inputField);

		// Currently use Strings with no restrictions for all field types
		if (!InputField.MANDATORY_YES.equals(inputField.getMandatory()))
		{
			fieldElementSequenceElement.setAttribute(MIN_OCCURS_ATTR, "0");
		}
		return fieldElementSequenceElement;
	}
	/**
	 * Helper method which creates a XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param function
	 *            - The function for which we need to create an XML representation
	 * @param inputField
	 *            The InputField definition for which to create the type definition
	 * @param xsdStructure
	 *            The XSDStructure related to the inputField
	 * @param referenceService
	 * 
	 * 
	 * @return An Element containing the field definition
	 */
	private static Element getEnhancedXsdFieldElement(Document document, Function function, InputField inputField,
					XSDStructure xsdStructure, Function referenceService)
	{
		InputField reusableSimpleTypeInputField = xsdStructure.rtvReusableSimpleTypeInputField(function, referenceService);
		// Field description
		String description = null;
		if (!xsdStructure.rtvDescription(function).equals("")
						&& !xsdStructure.rtvDescription(function).equals(
										com.misys.equation.function.beans.Element.DEFAULT_TEXT_VALUE))
		{
			description = xsdStructure.rtvDescription(function);
		}
		// Reusable simple type description
		XSDStructure reusableSimpleTypeXsd = null;
		String reusableSimpleTypeDescription = null;
		if (reusableSimpleTypeInputField != null)
		{
			reusableSimpleTypeXsd = referenceService.getXsdStructureRequest().findField(reusableSimpleTypeInputField.getId(), true,
							false);
			reusableSimpleTypeDescription = reusableSimpleTypeXsd.rtvDescription(referenceService);
		}

		Element fieldElementSequenceElement = document.createElement(ELEMENT_TAG);
		fieldElementSequenceElement.setAttribute(NAME_ATTR, xsdStructure.rtvWebServiceName(function, true));
		if ((description != null && !description.equals(""))
						|| (reusableSimpleTypeDescription != null && !reusableSimpleTypeDescription.equals("")))
		{
			String finalDescription = "";
			if (description != null)
			{
				finalDescription = finalDescription + description;
			}
			// if (reusableSimpleTypeInputField != null)
			// {
			// if (!finalDescription.equals(""))
			// {
			// finalDescription = finalDescription + Toolbox.NEW_LINE;
			// }
			// finalDescription = finalDescription + reusableSimpleTypeDescription;
			// }
			Element descriptionElement = getDescriptionElement(document, finalDescription);
			fieldElementSequenceElement.appendChild(descriptionElement);

		}

		// Use reusable simple type for CTS service
		if (reusableSimpleTypeXsd != null)
		{
			String reuseableSimpleTypeWebServiceName = getSimpleTypeName(reusableSimpleTypeXsd.rtvWebServiceName(referenceService,
							true));
			// Use a named type (declared in a common type schema)
			// Note that the type name must be qualified with the appropriate namespace prefix.
			fieldElementSequenceElement.setAttribute(TYPE_ATTR, EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID.toLowerCase()
							+ COLON + reuseableSimpleTypeWebServiceName);
		}
		else
		{
			// If not using a reference type, then use anonymous type definition
			Element simpleTypeElement = document.createElement(SIMPLE_TYPE_TAG);
			fieldElementSequenceElement.appendChild(simpleTypeElement);
			// add the field restrictions
			addEnhancedXsdFieldRestrictionsToElement(simpleTypeElement, inputField);
		}

		// Currently use Strings with no restrictions for all field types
		if (!InputField.MANDATORY_YES.equals(inputField.getMandatory()))
		{
			fieldElementSequenceElement.setAttribute(MIN_OCCURS_ATTR, "0");
		}
		// Default values - note multi-language default values does not seem to be supported
		String status = inputField.getDefaultValueType();
		if (inputField.getDefaultValue().length() > 0 && WorkField.VALUE_CONSTANT_TEXT.equals(status))
		{
			String defaultValue = inputField.getDefaultValue();
			if (EqDataType.TYPE_BOOLEAN.equals(inputField.getDataType()))
			{
				if (defaultValue.equals("Y"))
				{
					defaultValue = "true";
				}
				else
				{
					defaultValue = "false";
				}
			}
			fieldElementSequenceElement.setAttribute(DEFAULT_ATTR, defaultValue);

		}
		return fieldElementSequenceElement;
	}
	/**
	 * Helper method which creates a XML annotation/description element
	 * 
	 * @param document
	 *            The XML document
	 * @param description
	 *            The description
	 * 
	 * @return An Element containing the description definition
	 */
	private static Element getDescriptionElement(Document document, String description)
	{
		Element annotation = document.createElement(ANNOTATION_TAG);
		Element documentation = document.createElement(DOCUMENTATION_TAG);
		String fixedDescription = description.replace(Toolbox.LINE_BREAK_BR, Toolbox.NEW_LINE);
		documentation.setTextContent(Toolbox.NEW_LINE + fixedDescription + Toolbox.NEW_LINE);
		annotation.appendChild(documentation);
		return annotation;
	}
	/**
	 * Helper method which creates a XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param pvField
	 *            The PV field meta data
	 * 
	 * 
	 * @return An Element containing the field definition
	 */
	private static Element getFieldElement(Document document, EquationPVFieldMetaData pvField)
	{
		// Retrieve the pv id and make it valid
		String pvFieldId = translateFieldId(pvField.getId());

		Element fieldElementSequenceElement = document.createElement(ELEMENT_TAG);
		fieldElementSequenceElement.setAttribute(NAME_ATTR, pvFieldId + FunctionToolbox.UNDERSCORE
						+ Toolbox.textToCamelCase(pvField.getDescription()));

		// If not using a reference type, then use anonymous type definition
		Element simpleTypeElement = document.createElement(SIMPLE_TYPE_TAG);
		fieldElementSequenceElement.appendChild(simpleTypeElement);
		// add the field restrictions
		addFieldRestrictionsToElement(simpleTypeElement, pvField);

		// Currently use Strings with no restrictions for all field types
		fieldElementSequenceElement.setAttribute(MIN_OCCURS_ATTR, "0");

		return fieldElementSequenceElement;
	}

	/**
	 * Adds an import declaration for the Equation reference types schema
	 * <p>
	 * This is called for each individual service schema
	 * 
	 * <code>
	 * <xs:import namespace="http://www.misys.com/eq4/types" schemaLocation="equationFREF.xsd" />
	 * </code>
	 * 
	 * @param schemaElement
	 *            - The <code>Element</code> of the schema
	 * @return XML element snippet representing
	 */
	private static void addCtsImportElement(Element schemaElement, boolean isBankfusion)
	{
		Element importElement = null;
		if (!isBankfusion)
		{
			importElement = schemaElement.getOwnerDocument().createElement(IMPORT_TAG);
			importElement.setAttribute(INCLUDE_NAMESPACE_ATTR, CTS_TYPE_NAMESPACE);
			importElement.setAttribute(INCLUDE_SCHEMA_LOCATION_ATTR, INCLUDE_CTS_SCHEMA_LOCATION);
			schemaElement.appendChild(importElement);
		}
		else
		{
			// BankFusion import with different relative path
			importElement = schemaElement.getOwnerDocument().createElement(IMPORT_TAG);
			importElement.setAttribute(INCLUDE_NAMESPACE_ATTR, CTS_TYPE_NAMESPACE);
			importElement.setAttribute(INCLUDE_SCHEMA_LOCATION_ATTR, BANKFUSION_INCLUDE_CTS_SCHEMA_LOCATION);
			schemaElement.appendChild(importElement);
		}
	}

	/**
	 * Adds an import declaration for the BFEQ core XSD
	 * <p>
	 * This is called for each individual service schema
	 * 
	 * <code>
	 * <xs:import namespace="http://www.misys.com/eq4/types" schemaLocation="equationFREF.xsd" />
	 * </code>
	 * 
	 * @param schemaElement
	 *            - The <code>Element</code> of the schema
	 * 
	 *            Note: the location of this will be in the same location as the CTS.xsd
	 * 
	 * @return XML element snippet representing
	 */
	private static void addEqfHdrImportElement(Element schemaElement, boolean isBankfusion)
	{
		Element importElement = null;
		if (!isBankfusion)
		{
			importElement = schemaElement.getOwnerDocument().createElement(IMPORT_TAG);
			importElement.setAttribute(INCLUDE_NAMESPACE_ATTR, EQFHDR_TYPE_NAMESPACE);
			importElement.setAttribute(INCLUDE_SCHEMA_LOCATION_ATTR, INCLUDE_EQFHDR_SCHEMA_LOCATION);
			schemaElement.appendChild(importElement);
		}
		else
		{
			// BankFusion import with different relative path
			importElement = schemaElement.getOwnerDocument().createElement(IMPORT_TAG);
			importElement.setAttribute(INCLUDE_NAMESPACE_ATTR, EQFHDR_TYPE_NAMESPACE);
			importElement.setAttribute(INCLUDE_SCHEMA_LOCATION_ATTR, BANKFUSION_INCLUDE_EQFHDR_SCHEMA_LOCATION);
			schemaElement.appendChild(importElement);
		}
	}

	/**
	 * Helper method which creates the repeating group XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param function
	 *            the Function
	 * @param repeatingGroup
	 *            The Id of this repeating group
	 * 
	 * @param repeatingTypesSequenceElement
	 * 
	 * @return An Element containing the repeating block definition
	 */
	private static Element getRepeatingBlockDefinition(Document document, Function function, String repeatingGroup,
					Element repeatingTypesSequenceElement)
	{
		Element repeatingBlockElement = document.createElement(ELEMENT_TAG);

		String name = FunctionBankFusion.getServiceMicroflowName(function) + "_" + repeatingGroup + "_row";

		repeatingBlockElement.setAttribute(NAME_ATTR, name);
		repeatingBlockElement.setAttribute(MIN_OCCURS_ATTR, "0");
		repeatingBlockElement.setAttribute(MAX_OCCURS_ATTR, UNBOUNDED);
		repeatingBlockElement.setAttribute(TYPE_ATTR, function.getId().toLowerCase() + COLON + name);

		return repeatingBlockElement;
	}

	/**
	 * Helper method to get the elements name
	 * 
	 * @param function
	 *            - The function for which we need to create an XML representation
	 * @param xsdStructure
	 *            - The XSDStructure
	 * 
	 * @return element name
	 */
	private static String getEnhancedXsdName(Function function, XSDStructure xsdStructure)
	{

		String name = null;

		if (xsdStructure.chkRootRequest())
		{
			name = xsdStructure.rtvWebServiceName(function, true);

		}
		else if (xsdStructure.chkRootResponse())
		{
			name = xsdStructure.rtvWebServiceName(function, false);
		}
		else
		{
			// Request or response should not matter
			name = xsdStructure.rtvWebServiceName(function, true);
		}
		return name;
	}
	/**
	 * Helper method which creates the group XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param function
	 *            the Function
	 * @param xsdStructure
	 *            - the XSD structure
	 * @param referenceService
	 *            - the reference service storing complex types
	 * 
	 * @return An Element containing the repeating block definition
	 * @throws EQException
	 */
	private static Element getEnhancedXsdGroupElement(Document document, Function function, XSDStructure xsdStructure,
					Function referenceService)

	{
		// Field description
		String description = null;
		if (!xsdStructure.rtvDescription(function).equals("")
						&& !xsdStructure.rtvDescription(function).equals(
										com.misys.equation.function.beans.Element.DEFAULT_TEXT_VALUE))
		{
			description = xsdStructure.rtvDescription(function);
		}
		// Reusable complex type description
		String reusableComplexTypeDescription = null;

		String name = null;
		if (xsdStructure.chkRepeatingGroup())
		{
			name = xsdStructure.rtvWebServiceRepeatingGroupCollectionsName(function);
		}
		else
		{
			name = getEnhancedXsdName(function, xsdStructure);
		}

		Element groupElement = document.createElement(ELEMENT_TAG);

		groupElement.setAttribute(NAME_ATTR, name);

		if (!xsdStructure.chkRepeatingGroup())
		{
			groupElement.setAttribute(MIN_OCCURS_ATTR, new Integer(xsdStructure.getMinOccur()).toString());
		}
		else
		{
			int min = new Integer(xsdStructure.getMinOccur());
			if (min == 0)
			{
				groupElement.setAttribute(MIN_OCCURS_ATTR, "0");
			}
			else
			{
				groupElement.setAttribute(MIN_OCCURS_ATTR, "1");
			}
		}

		if (!xsdStructure.chkComplexTypeGroup())
		{
			groupElement.setAttribute(TYPE_ATTR, function.getId().toLowerCase() + COLON + name);
		}
		else
		{
			// retrieve the XSD structure either from the CTS's request or response
			XSDStructure reusableComplexTypeXsd = XSDStructureHelper.findXSD(referenceService, XSDStructure.getGroupName(
							xsdStructure.getComplexTypeId(), false), true, false);
			name = getEnhancedXsdName(referenceService, reusableComplexTypeXsd);
			reusableComplexTypeDescription = reusableComplexTypeXsd.rtvDescription(referenceService);
			groupElement.setAttribute(TYPE_ATTR, EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID.toLowerCase() + COLON + name);
		}
		if ((description != null && !description.equals(""))
						|| (reusableComplexTypeDescription != null && !reusableComplexTypeDescription.equals("")))
		{
			String finalDescription = "";
			if (description != null)
			{
				finalDescription = finalDescription + description;
			}
			// if (reusableComplexTypeDescription != null)
			// {
			// if (!finalDescription.equals(""))
			// {
			// finalDescription = finalDescription + Toolbox.NEW_LINE;
			// }
			// finalDescription = finalDescription + reusableComplexTypeDescription;
			// }
			Element descriptionElement = getDescriptionElement(document, finalDescription);
			groupElement.appendChild(descriptionElement);

		}
		return groupElement;
	}
	/**
	 * Helper method which creates the repeating group type XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param function
	 *            The Function
	 * @param repeatingGroup
	 *            The Id of this repeating group
	 * 
	 * @param repeatingTypesSequenceElement
	 * 
	 * @return An Element containing the repeating block definition
	 */
	private static Element getRepeatingBlockDefinitionType(Document document, Function function, String repeatingGroup,
					Element repeatingTypesSequenceElement)
	{
		String name = FunctionBankFusion.getComplexTypeNameRepeatingGroup(function, repeatingGroup);

		// Declare complex type
		Element complexTypeElement = document.createElement(COMPLEX_TYPE_TAG);
		complexTypeElement.setAttribute(NAME_ATTR, name);

		// Add the sequence to the complex type
		complexTypeElement.appendChild(repeatingTypesSequenceElement);

		return complexTypeElement;
	}

	/**
	 * Helper method which creates the repeating group type XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param function
	 *            The Function
	 * @param groupName
	 *            The Id of this repeating group
	 * @param repeatingTypesSequenceElement
	 * @param complexTypes
	 *            - the map of already used complex types
	 * 
	 * @return An Element containing the repeating block definition
	 * @throws EQException
	 */
	private static Element getEnhancedXsdRepeatingGroupType(Document document, Function function, XSDStructure xsdStructure,
					Element repeatingTypesSequenceElement)
	{
		String repeatingGroupCollectionName = xsdStructure.rtvWebServiceRepeatingGroupCollectionsName(function);

		Element complexTypeElement = document.createElement(COMPLEX_TYPE_TAG);

		complexTypeElement.setAttribute(NAME_ATTR, repeatingGroupCollectionName);
		if (!xsdStructure.rtvDescription(function).equals("")
						&& !xsdStructure.rtvDescription(function).equals(
										com.misys.equation.function.beans.Element.DEFAULT_TEXT_VALUE))

		{
			Element descriptionElement = getDescriptionElement(document, xsdStructure.rtvDescription(function));
			complexTypeElement.appendChild(descriptionElement);
		}

		Element repeatingGroupSequence = document.createElement(SEQUENCE_TAG);

		String name = getEnhancedXsdName(function, xsdStructure);
		Element repeatingGroupElement = document.createElement(ELEMENT_TAG);
		repeatingGroupElement.setAttribute(NAME_ATTR, name);
		if (!xsdStructure.chkComplexTypeGroup())
		{
			repeatingGroupElement.setAttribute(TYPE_ATTR, function.getId().toLowerCase() + COLON + name);
		}
		else
		{
			repeatingGroupElement.setAttribute(TYPE_ATTR, EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID.toLowerCase() + COLON
							+ name);
		}

		int min = xsdStructure.getMinOccur();
		int max = xsdStructure.getMaxOccur();

		repeatingGroupElement.setAttribute(MIN_OCCURS_ATTR, new Integer(min).toString());

		if (xsdStructure.chkRepeatingGroup())
		{
			if (max != 0)
			{
				repeatingGroupElement.setAttribute(MAX_OCCURS_ATTR, new Integer(max).toString());
			}
			else
			{
				repeatingGroupElement.setAttribute(MAX_OCCURS_ATTR, UNBOUNDED);
			}
		}

		repeatingGroupSequence.appendChild(repeatingGroupElement);

		complexTypeElement.appendChild(repeatingGroupSequence);

		return complexTypeElement;
	}

	/**
	 * Helper method which creates the group type XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param function
	 *            The Function
	 * @param xsdStructure
	 * @param repeatingTypesSequenceElement
	 * 
	 * @return An Element containing the repeating block definition
	 * @throws EQException
	 */
	private static Element getEnhancedXsdGroupType(Document document, Function function, XSDStructure xsdStructure,
					Element repeatingTypesSequenceElement)
	{
		Element complexTypeElement = document.createElement(COMPLEX_TYPE_TAG);

		complexTypeElement.setAttribute(NAME_ATTR, getEnhancedXsdName(function, xsdStructure));
		if (!xsdStructure.chkRepeatingGroup()
						&& !xsdStructure.rtvDescription(function).equals("")
						&& !xsdStructure.rtvDescription(function).equals(
										com.misys.equation.function.beans.Element.DEFAULT_TEXT_VALUE))

		{
			Element descriptionElement = getDescriptionElement(document, xsdStructure.rtvDescription(function));
			complexTypeElement.appendChild(descriptionElement);
		}

		// Add the sequence to the complex type
		complexTypeElement.appendChild(repeatingTypesSequenceElement);

		return complexTypeElement;
	}
	/**
	 * Helper method which creates empty type XML element
	 * 
	 * @param document
	 *            The XML document
	 * @param function
	 *            The Function
	 * @param xsdStructure
	 * 
	 * @return An Element containing the repeating block definition
	 * @throws EQException
	 */
	private static Element getEnhancedXsdEmptyType(Document document, Function function, XSDStructure xsdStructure)
	{
		Element complexTypeElement = document.createElement(COMPLEX_TYPE_TAG);

		complexTypeElement.setAttribute(NAME_ATTR, getEnhancedXsdName(function, xsdStructure));
		return complexTypeElement;
	}
	/**
	 * Adds a Simple Type element to the XSD document for the field type definition in described by the acmRecord parameter.
	 * <p>
	 * This method is used when building a global reference field of all the reference fields definitions (on the ACMPF). See the
	 * <code>getEquationSchemaDocument</code> method
	 * 
	 * @param document
	 *            the schema XML document
	 * @param schemaElement
	 * @param function
	 * @param xsdStructure
	 * 
	 * @return XML Element snippet representing the field
	 */
	private static void createReusableSimpleTypeFieldElement(Document document, Element schemaElement, Function function,
					XSDStructure xsdStructure)
	{
		for (XSDStructure xsdSubField : xsdStructure.getSubFields())
		{
			if (xsdSubField.chkField() && !xsdSubField.chkComplexTypeField())
			{
				if (xsdSubField.getId().startsWith(XSDStructure.SIMPLE_TYPE_PREFIX))
				{
					Element fieldElement = null;
					InputField inputField = function.rtvInputField(xsdSubField.getId());
					fieldElement = document.createElement(SIMPLE_TYPE_TAG);
					fieldElement.setAttribute(NAME_ATTR, getSimpleTypeName(xsdSubField.rtvWebServiceName(function, true)));
					if (!(xsdSubField.rtvDescription(function).equals("") && !(xsdSubField.rtvDescription(function)
									.equals(com.misys.equation.function.beans.Element.DEFAULT_TEXT_VALUE))))
					{
						Element descriptionElement = getDescriptionElement(document, (xsdSubField.rtvDescription(function)));
						fieldElement.appendChild(descriptionElement);

					}

					addEnhancedXsdFieldRestrictionsToElement(fieldElement, inputField);

					schemaElement.appendChild(fieldElement);
				}
			}
			else
			{
				createReusableSimpleTypeFieldElement(document, schemaElement, function, xsdSubField);
			}
		}
	}
	/**
	 * A method to generate the name of a Simple Type
	 * <p>
	 * Use this method to ensure that the same name is used when creating the type definitions as when referencing it. This
	 * currently simply appends "_Type" to the field name.
	 * 
	 * @param fieldType
	 *            The type of the field
	 * 
	 * @return a String containing the name of the Simple Type
	 */
	private static String getSimpleTypeName(String fieldType)
	{
		// return fieldType + "Type";
		return fieldType;
	}

	/**
	 * Helper method which fleshes out the restrictions for the field attributes
	 * 
	 * @param fieldElement
	 *            - the element to be enriched
	 * @param field
	 *            - The field for which we need to create an XSD representation
	 */
	private static void addFieldRestrictionsToElement(Element fieldElement, InputField field)
	{
		// String
		Element fieldElementRestriction = fieldElement.getOwnerDocument().createElement(RESTRICTION_TAG);

		fieldElementRestriction.setAttribute(BASE_ATTR, XS_STRING);

		fieldElement.appendChild(fieldElementRestriction);

		// The minLength and maxLength tags have XSD limitations which can be exceeded by
		// which are relevant to numeric values, not lengths. Therefore,
		// only character field lengths are currently handled
		if (EqDataType.TYPE_CHAR.equals(field.getDataType()))
		{
			// Min
			if (field.getMinLength() != null && !field.getMinLength().equals(""))
			{
				Element fieldElementMinRestriction = fieldElement.getOwnerDocument().createElement(MIN_LENGTH_TAG);
				fieldElementMinRestriction.setAttribute(VALUE_ATTR, field.getMinLength());
				fieldElementRestriction.appendChild(fieldElementMinRestriction);
			}

			// Max
			if (field.getMaxLength() != null && !field.getMaxLength().equals(""))
			{
				Element fieldElementMaxRestriction = fieldElement.getOwnerDocument().createElement(MAX_LENGTH_TAG);
				fieldElementMaxRestriction.setAttribute(VALUE_ATTR, field.getMaxLength());
				fieldElementRestriction.appendChild(fieldElementMaxRestriction);
			}
			else
			{
				Element fieldElementMaxRestriction = fieldElement.getOwnerDocument().createElement(MAX_LENGTH_TAG);
				fieldElementMaxRestriction.setAttribute(VALUE_ATTR, field.getSize());
				fieldElementRestriction.appendChild(fieldElementMaxRestriction);
			}
			// Regular expression
			if (field.getRegExp() != null && !field.getRegExp().equals(""))
			{
				// TODO: Figure out how multi-language regular expression is going to work without a user.
				String regExpType = field.getRegExpType();
				if (!regExpType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REFERENCE)
								&& !regExpType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REUSABLE_REFERENCE))
				{
					Element fieldElementPatternRestriction = fieldElement.getOwnerDocument().createElement(PATTERN_TAG);
					fieldElementPatternRestriction.setAttribute(VALUE_ATTR, field.getRegExp());
					fieldElementRestriction.appendChild(fieldElementPatternRestriction);
				}
			}
			// Valid values
			if (field.getValidValues() != null && !field.getValidValues().equals(""))
			{
				// TODO: Figure out how multi-language valid values is going to work without a user.
				String validValuesType = field.getValidValuesType();
				if (!validValuesType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REFERENCE)
								&& !validValuesType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REUSABLE_REFERENCE))
				{
					String[] validValuesArray = field.getValidValues().split(EqDataType.VALUESSEP);
					for (String validValue : validValuesArray)
					{
						Element fieldElementValueRestriction = fieldElement.getOwnerDocument().createElement(ENUMERATION_TAG);
						fieldElementValueRestriction.setAttribute(VALUE_ATTR, validValue);
						fieldElementRestriction.appendChild(fieldElementValueRestriction);
					}

				}
			}
		}

	}

	/**
	 * Helper method which fleshes out the restrictions for the field attributes
	 * 
	 * @param fieldElement
	 *            - the element to be enriched
	 * @param field
	 *            - The field for which we need to create an XSD representation
	 */
	private static void addEnhancedXsdFieldRestrictionsToElement(Element fieldElement, InputField field)
	{
		// String
		Element fieldElementRestriction = fieldElement.getOwnerDocument().createElement(RESTRICTION_TAG);
		if (EqDataType.TYPE_BOOLEAN.equals(field.getDataType()))
		{
			fieldElementRestriction.setAttribute(BASE_ATTR, XS_BOOLEAN);
		}
		else
		{
			if (EqDataType.TYPE_PACKED.equals(field.getDataType()) || EqDataType.TYPE_ZONED.equals(field.getDataType()))
			{

				if (field.getDecimals() != null && !field.getDecimals().equals("") && !field.getDecimals().equals("0"))
				{
					fieldElementRestriction.setAttribute(BASE_ATTR, XS_DECIMAL);
				}
				else

				{
					Integer size = new Integer(field.getSize());
					if (size < 10)
					{
						fieldElementRestriction.setAttribute(BASE_ATTR, XS_INTEGER);
					}
					else
					{
						fieldElementRestriction.setAttribute(BASE_ATTR, XS_LONG);
					}
				}
			}
			else

				fieldElementRestriction.setAttribute(BASE_ATTR, XS_STRING);
		}
		fieldElement.appendChild(fieldElementRestriction);

		// The minLength and maxLength tags have XSD limitations which can be exceeded by
		// which are relevant to numeric values, not lengths. Therefore,
		// only character field lengths are currently handled
		// The minLength and maxLength tags have XSD limitations which can be exceeded by
		// which are relevant to numeric values, not lengths. Therefore,
		// only character field lengths are currently handled
		if (EqDataType.TYPE_CHAR.equals(field.getDataType()))
		{
			// Min
			if (field.getMinLength() != null && !field.getMinLength().equals(""))
			{
				Element fieldElementMinRestriction = fieldElement.getOwnerDocument().createElement(MIN_LENGTH_TAG);
				fieldElementMinRestriction.setAttribute(VALUE_ATTR, field.getMinLength());
				fieldElementRestriction.appendChild(fieldElementMinRestriction);
			}

			// Max
			if (field.getMaxLength() != null && !field.getMaxLength().equals(""))
			{
				Element fieldElementMaxRestriction = fieldElement.getOwnerDocument().createElement(MAX_LENGTH_TAG);
				fieldElementMaxRestriction.setAttribute(VALUE_ATTR, field.getMaxLength());
				fieldElementRestriction.appendChild(fieldElementMaxRestriction);
			}
			else
			{
				Element fieldElementMaxRestriction = fieldElement.getOwnerDocument().createElement(MAX_LENGTH_TAG);
				fieldElementMaxRestriction.setAttribute(VALUE_ATTR, field.getSize());
				fieldElementRestriction.appendChild(fieldElementMaxRestriction);
			}
		}
		if (EqDataType.TYPE_PACKED.equals(field.getDataType()) || EqDataType.TYPE_ZONED.equals(field.getDataType()))
		{
			if (field.getSize() != null && !field.getSize().equals(""))
			{
				Element fieldElementTotalDigitsRestriction = fieldElement.getOwnerDocument().createElement(TOTAL_DIGITS_TAG);
				fieldElementTotalDigitsRestriction.setAttribute(VALUE_ATTR, field.getSize());
				fieldElementRestriction.appendChild(fieldElementTotalDigitsRestriction);
			}

			if (field.getDecimals() != null && !field.getDecimals().equals(""))
			{
				Element fieldElementFractionDigitsRestriction = fieldElement.getOwnerDocument().createElement(FRACTION_DIGITS_TAG);
				fieldElementFractionDigitsRestriction.setAttribute(VALUE_ATTR, field.getDecimals());
				fieldElementRestriction.appendChild(fieldElementFractionDigitsRestriction);
			}
		}
		// Regular expression
		if (field.getRegExp() != null && !field.getRegExp().equals(""))
		{
			// TODO: Figure out how multi-language regular expression is going to work without a user.
			String regExpType = field.getRegExpType();
			if (!regExpType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REFERENCE)
							&& !regExpType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				Element fieldElementPatternRestriction = fieldElement.getOwnerDocument().createElement(PATTERN_TAG);
				fieldElementPatternRestriction.setAttribute(VALUE_ATTR, field.getRegExp());
				fieldElementRestriction.appendChild(fieldElementPatternRestriction);
			}
		}
		// Valid values
		if (field.getValidValues() != null && !field.getValidValues().equals(""))
		{
			// TODO: Figure out how multi-language valid values is going to work without a user.
			String validValuesType = field.getValidValuesType();
			if (!validValuesType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REFERENCE)
							&& !validValuesType.equals(com.misys.equation.function.beans.Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				String[] validValuesArray = field.getValidValues().split(EqDataType.VALUESSEP);
				for (String validValue : validValuesArray)
				{
					Element fieldElementValueRestriction = fieldElement.getOwnerDocument().createElement(ENUMERATION_TAG);
					fieldElementValueRestriction.setAttribute(VALUE_ATTR, validValue);
					fieldElementRestriction.appendChild(fieldElementValueRestriction);
				}
			}
		}

	}
	/**
	 * Helper method which fleshes out the restrictions for the field attributes
	 * 
	 * @param fieldElement
	 *            - the element to be enriched
	 * @param pvField
	 *            - The meta data of the PV field for which we need to create an XSD representation
	 */
	private static void addFieldRestrictionsToElement(Element fieldElement, EquationPVFieldMetaData pvField)
	{
		// String
		Element fieldElementRestriction = fieldElement.getOwnerDocument().createElement(RESTRICTION_TAG);
		fieldElementRestriction.setAttribute(BASE_ATTR, XS_STRING);
		fieldElement.appendChild(fieldElementRestriction);

		// The minLength and maxLength tags have XSD limitations which can be exceeded by
		// which are relevant to numeric values, not lengths. Therefore,
		// only character field lengths are currently handled
		if (EqDataType.TYPE_CHAR.equals(pvField.getType()))
		{
			// Max length
			Element fieldElementMaxRestriction = fieldElement.getOwnerDocument().createElement(MAX_LENGTH_TAG);
			fieldElementMaxRestriction.setAttribute(VALUE_ATTR, String.valueOf(pvField.getLength()));
			fieldElementRestriction.appendChild(fieldElementMaxRestriction);
		}

		return;
	}

	/**
	 * Serialise a Document to a string
	 * 
	 * @param document
	 *            - the Document to serialise
	 * @return a String containing the serialised form of the document
	 */
	public static String serialiseDocument(Document document)
	{
		String xmlString = "";
		try
		{
			OutputFormat outputFormat = new OutputFormat(document);
			outputFormat.setIndenting(true);
			outputFormat.setOmitComments(false);
			outputFormat.setOmitDocumentType(false);
			outputFormat.setOmitXMLDeclaration(false);
			outputFormat.setPreserveEmptyAttributes(true);
			outputFormat.setLineWidth(132);
			outputFormat.setPreserveSpace(false);
			StringWriter xmlStringWriter = new StringWriter();
			XMLSerializer xmlSerializer = new XMLSerializer(xmlStringWriter, outputFormat);
			xmlSerializer.serialize(document.getDocumentElement());
			xmlString = xmlStringWriter.toString();
		}
		catch (IOException e)
		{
			throw new EQRuntimeException("Could not serialise", e);
		}
		return xmlString;
	}

	/**
	 * Return the BF name space
	 * 
	 * @param id
	 *            - the function id
	 * 
	 * @return BF name space
	 */
	private static String getBFNameSpaceAttr(String id)
	{
		return NAMESPACE_ATTR + COLON + id.toLowerCase();
	}

	/**
	 * Convert a field id into a valid XSD id
	 * 
	 * @param pvId
	 *            - the original pv field id
	 * 
	 * @return a valid XSD id for the PV field id
	 */
	public static String translateFieldId(String pvId)
	{
		StringBuilder newId = new StringBuilder(pvId);
		char firstChar = newId.charAt(0);

		// if the first character is not alpha-numeric, then remove the first character
		if ((firstChar >= 'A' && firstChar <= 'Z') || (firstChar >= '0' && firstChar <= '9'))
		{
		}
		else
		{
			newId.delete(0, 1);
		}

		// Recheck again if the new first character is a number, in this scenario,
		// The XSD cannot have a number as the first character, so add a dummy character
		firstChar = newId.charAt(0);
		if (firstChar >= '0' && firstChar <= '9')
		{
			newId.insert(0, "P");
		}

		// now convert invalid character @ to _
		String str = newId.toString();
		str = str.replaceAll("@", "_");

		// return the new id
		return str;
	}

	/**
	 * Validates the label - which is used as an XSD webservice name
	 * 
	 * @param label
	 *            - the label to be validated
	 */
	public static boolean isValidForXSDMustStartWithAToZ(String label)
	{
		boolean valid = true;

		// empty string, assume valid
		if (label.length() <= 0)
		{
			return valid;
		}

		// Must start with A-Z or a-z
		char ch = label.charAt(0);
		if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z'))
		{
		}
		else
		{
			valid = false;
		}

		// return result
		return valid;
	}
	/**
	 * Validates the label - which is used as an XSD webservice name
	 * 
	 * @param label
	 *            - the label to be validated
	 */
	public static boolean isValidForXSDMustNotBeBlank(String label)
	{
		if (label == null)
		{
			return false;
		}
		if (label.trim().length() == 0)
		{
			return false;
		}
		if (label.equals(com.misys.equation.function.beans.Element.DEFAULT_TEXT_VALUE))
		{
			return false;
		}

		return true;
	}

	/**
	 * return the Complex Type URL
	 * 
	 * @param function
	 * @return Complex Type URL for Enhanced XSD
	 */
	public static String getComplexTypeURL(Function function)
	{
		if (function.chkXSDGeneric())
		{
			if (function.getId().equals(EquationFunctionContext.MISYS_REFERENCE_SERVICE_ID))
			{
				return CTS_TYPE_NAMESPACE;
			}
			else
			{
				return EQ_SERVICE_NAMESPACE + '/' + function.getModuleId().toLowerCase() + "/" + function.getId().toLowerCase()
								+ "/" + function.rtvXsdMajorVersion();
			}
		}
		else
		{
			return EQ_SERVICE_NAMESPACE + '/' + function.getModuleId().toLowerCase();
		}
	}
	/**
	 * Get a property from the xsdConfigurationProperties file
	 * 
	 * @param key
	 *            - the key
	 * 
	 * @return a String which will be the requested property value
	 */
	public static String getXsdConfigProperty(String key)
	{
		return xsdProperties.get(key);
	}

	/**
	 * Create the combined complex type for the Service header and Service data
	 * 
	 * @param document
	 *            - the document
	 * @param complexTypeName
	 *            - the complex type name
	 * @param serviceData
	 *            - the service data field type
	 * @param serviceHeader
	 *            - the service header field type
	 * 
	 * @return the complex type element
	 */
	public static Element createCombinedComplexType(Document document, String complexTypeName, String serviceData,
					String serviceHeader)
	{
		Element serviceHeaderElement = null;
		serviceHeaderElement = document.createElement(ELEMENT_TAG);
		serviceHeaderElement.setAttribute(NAME_ATTR, SERVICE_HEADER_TAG);
		serviceHeaderElement.setAttribute(MIN_OCCURS_ATTR, "1");
		serviceHeaderElement.setAttribute(TYPE_ATTR, serviceHeader);

		Element serviceDataElement = null;
		serviceDataElement = document.createElement(ELEMENT_TAG);
		serviceDataElement.setAttribute(NAME_ATTR, SERVICE_DATA_TAG);
		serviceDataElement.setAttribute(MIN_OCCURS_ATTR, "1");
		serviceDataElement.setAttribute(TYPE_ATTR, serviceData);

		// create the sequence
		Element functionElementSequence = document.createElement(SEQUENCE_TAG);
		functionElementSequence.appendChild(serviceHeaderElement);
		functionElementSequence.appendChild(serviceDataElement);

		// create the complex type
		Element complexType = document.createElement(COMPLEX_TYPE_TAG);
		complexType.setAttribute(NAME_ATTR, complexTypeName);
		complexType.appendChild(functionElementSequence);
		return complexType;
	}

	/**
	 * Return the combined request complex type name
	 * 
	 * @param function
	 *            - the function
	 * @return the combined request complex type name
	 */
	public static String getCombinedComplexTypeRequestName(Function function)
	{
		String mf = FunctionBankFusion.getServiceXSDName(function) + FunctionToolbox.UNDERSCORE + "Rq";
		return mf;
	}

	/**
	 * Return the combined response complex type name
	 * 
	 * @param function
	 *            - the function
	 * @return the combined response complex type name
	 */
	public static String getCombinedComplexTypeResponseName(Function function)
	{
		String mf = FunctionBankFusion.getServiceXSDName(function) + FunctionToolbox.UNDERSCORE + "Rs";
		return mf;
	}

}