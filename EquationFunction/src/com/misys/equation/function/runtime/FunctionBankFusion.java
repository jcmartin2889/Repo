package com.misys.equation.function.runtime;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.exolab.castor.xml.Unmarshaller;

import bf.com.misys.eqf.types.header.BranchKeys;
import bf.com.misys.eqf.types.header.BranchLimitParameters;
import bf.com.misys.eqf.types.header.CrmMessages;
import bf.com.misys.eqf.types.header.EnquiryRq;
import bf.com.misys.eqf.types.header.EnquiryRs;
import bf.com.misys.eqf.types.header.EqMessage;
import bf.com.misys.eqf.types.header.FieldLocation;
import bf.com.misys.eqf.types.header.Formatting;
import bf.com.misys.eqf.types.header.JournalKey;
import bf.com.misys.eqf.types.header.MessageStatus;
import bf.com.misys.eqf.types.header.Orig;
import bf.com.misys.eqf.types.header.Overrides;
import bf.com.misys.eqf.types.header.RqHeader;
import bf.com.misys.eqf.types.header.RsHeader;
import bf.com.misys.eqf.types.header.SearchRsHeader;
import bf.com.misys.eqf.types.header.ServiceRqHeader;
import bf.com.misys.eqf.types.header.ServiceRsHeader;
import bf.com.misys.eqf.types.header.ServiceStack;
import bf.com.misys.eqf.types.header.UiControlRq;
import bf.com.misys.eqf.types.header.UiControlRs;

import com.misys.bankfusion.encryption.client.data.EncryptedData;
import com.misys.bankfusion.encryption.client.service.AbstractBankFusionEncryptionService;
import com.misys.bankfusion.subsystem.security.runtime.impl.AS400Token;
import com.misys.equation.bankfusion.tools.BFFunctionClassLoader;
import com.misys.equation.bankfusion.tools.BFToolbox;
import com.misys.equation.bankfusion.tools.ReturnDataBF;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.beans.XSDStructure;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.ComplexTypeToolbox;
import com.misys.equation.function.tools.FieldFilterLocator;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.ReturnDataMFUserExit;
import com.misys.equation.function.tools.XMLToolbox;
import com.misys.equation.function.tools.XSDToolbox;
import com.misys.equation.function.useraccess.UserExitMessage;
import com.trapedza.bankfusion.boundary.outward.BankFusionPropertySupport;
import com.trapedza.bankfusion.core.CommonConstants;
import com.trapedza.bankfusion.encryption.services.EncryptionService;
import com.trapedza.bankfusion.servercommon.core.BankFusionThreadLocal;
import com.trapedza.bankfusion.servercommon.services.ServiceManager;
import com.trapedza.bankfusion.servercommon.services.ServiceManagerFactory;

public class FunctionBankFusion
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionBankFusion.java 17834 2014-02-07 11:38:46Z lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FunctionBankFusion.class);

	/** Repeating group suffix */
	public static final String BF_LIST_SUFFIX = "_row";

	/** Enhanced XSD name - Request suffix */
	public static final String REQUEST_SUFFIX = "Rq";

	/** Enhanced XSD name - Response suffix */
	public static final String RESPONSE_SUFFIX = "Rs";

	/**
	 * Default constructor
	 */
	public FunctionBankFusion()
	{
	}

	/**
	 * Return the Function Data equivalent of the specified complex data type
	 * 
	 * @param function
	 *            - the Function
	 * @param bf_functionData
	 *            - the source complex data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return the Function Data
	 */
	public FunctionData getFunctionDataFromTemplate(Function function, FunctionData templateData, Object bf_functionData,
					boolean bInputValue) throws EQException
	{
		FunctionData functionData = new FunctionData(function, null);
		functionData.copyAttrib(templateData);
		loadToFunctionData(function, functionData, bf_functionData, bInputValue, null);
		return functionData;
	}

	/**
	 * Return and set the given Function Data based on the content of the specified complex data type
	 * 
	 * @param function
	 *            - the Function
	 * @param functionData
	 *            - the destination Function Data
	 * @param bf_functionData
	 *            - the source complex data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param conversionRules
	 *            - the conversion rule to convert between user format and Equation format
	 * 
	 * @return the Function Data
	 */
	public boolean loadToFunctionData(Function function, FunctionData functionData, Object bf_functionData, boolean bInputValue,
					ConversionRules conversionRules)
	{
		// enhanced XSD
		if (conversionRules != null && conversionRules.isGenerics())
		{
			return loadToFunctionData(function.getXsdStructureRequest(), functionData, bf_functionData, bf_functionData,
							bInputValue, conversionRules, -1);
		}

		Map<String, LinkedHashMap<String, InputField>> repeatingInputFields = new LinkedHashMap<String, LinkedHashMap<String, InputField>>();
		boolean changed = false;

		for (int i = 0; i < function.getInputFieldSets().size(); i++)
		{
			InputFieldSet inputFieldSet = function.getInputFieldSets().get(i);
			for (int j = 0; j < inputFieldSet.getInputFields().size(); j++)
			{
				InputField inputField = inputFieldSet.getInputFields().get(j);
				String fieldName = inputField.getId();

				// Store the repeating field details for later processing
				if (Field.isRepeating(inputField))
				{
					LinkedHashMap<String, InputField> map = repeatingInputFields.get(inputField.getRepeatingGroup());
					if (map == null)
					{
						map = new LinkedHashMap<String, InputField>();
						repeatingInputFields.put(inputField.getRepeatingGroup(), map);
					}
					map.put(fieldName, inputField);
				}

				// Non repeating fields
				else
				{
					String fieldValue = getFieldValue(bf_functionData, inputField);

					// convert?
					if (conversionRules != null && fieldValue != null)
					{
						String[] parameters = getConversionParameterFromComplexType(bf_functionData, functionData, inputField,
										conversionRules, null, function);
						fieldValue = conversionRules.convert(inputField, fieldValue, parameters);
					}

					// check if the value will be updated
					if (!changed)
					{
						changed = functionData.chkFieldChange(fieldName, fieldValue, bInputValue);
					}

					// if there is no changed, then there is no need to update
					if (changed)
					{
						functionData.chgInputOrDBField(fieldName, fieldValue, bInputValue);
					}
				}
			}
		}

		// load the repeating details
		for (RepeatingDataManager repeatingDataManager : functionData.getRepeatingDataManagers())
		{
			String repeatGroup = repeatingDataManager.getId();

			// Does this function has this repeating group?
			// For linkage services, the function data contains all the repeating group, however, the function refers to the primary
			// function only
			if (repeatingInputFields.get(repeatGroup) == null)
			{
				continue;
			}

			int rows = getRowCount(bf_functionData, repeatGroup);
			for (int i = 0; i < rows; i++)
			{
				// maintain a row?
				if (repeatingDataManager.totalRows() > i)
				{
					repeatingDataManager.setRow(i);
				}

				// adding a new row?
				else
				{
					repeatingDataManager.addRow();
				}

				// set it
				LinkedHashMap<String, InputField> map = repeatingInputFields.get(repeatingDataManager.getId());
				if (map != null)
				{
					// Use the list of repeating InputFields (from the service definition) to drive the loop:
					Object rowObject = getRow(bf_functionData, repeatGroup, i);
					for (Entry<String, InputField> entry : map.entrySet())
					{
						String fieldName = entry.getKey();
						String fieldValue = getFieldValue(rowObject, entry.getValue());

						// convert?
						if (conversionRules != null && fieldValue != null)
						{
							String[] parameters = getConversionParameterFromComplexType(bf_functionData, functionData, entry
											.getValue(), conversionRules, rowObject, function);
							fieldValue = conversionRules.convert(entry.getValue(), fieldValue, parameters);
						}

						// check if the value will be updated
						if (!changed)
						{
							changed = functionData.chkFieldChange(fieldName, fieldValue, bInputValue);
						}

						// if there is no changed, then there is no need to update
						if (changed)
						{
							functionData.chgInputOrDBField(fieldName, fieldValue, bInputValue);
						}

					}
				}
			}

			// Delete rows from the repeating data manager until it is equal to the number of rows returned
			if (rows >= 0)
			{
				while (repeatingDataManager.totalRows() > rows)
				{
					repeatingDataManager.deleteRow(rows);
				}
			}
		}

		return changed;
	}

	/**
	 * Return and set the given Function Data based on the content of the specified complex data type (enhanced XSD)
	 * 
	 * @param xsdStructure
	 *            - the root XSD
	 * @param functionData
	 *            - the destination Function Data
	 * @param root_bf_functionData
	 *            - the top most complex data type
	 * @param bf_functionData
	 *            - the current (group) complex data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param conversionRules
	 *            - the conversion rule to convert between user format and Equation format
	 * @param index
	 *            - index for the repeating list
	 * 
	 * @return true if something has changed
	 */
	protected boolean loadToFunctionData(XSDStructure xsdStructure, FunctionData functionData, Object root_bf_functionData,
					Object bf_functionData, boolean bInputValue, ConversionRules conversionRules, int index)
	{
		boolean changed = false;

		for (XSDStructure subField : xsdStructure.getSubFields())
		{
			String methodName = "get" + subField.rtvRuntimeWebServiceName();

			// group?
			if (subField.chkGroup())
			{
				// retrieve the object
				Object objectValue = getFieldObject(bf_functionData, methodName);
				if (objectValue != null)
				{
					loadToFunctionData(subField, functionData, root_bf_functionData, objectValue, bInputValue, conversionRules,
									index);
				}
			}

			// repeating groups
			else if (subField.chkRepeatingGroup())
			{
				// retrieve the object
				methodName = "get" + subField.rtvRuntimeWebServiceRepeatingGroupCollectionsName();
				Object objectValue = getFieldObject(bf_functionData, methodName);
				if (objectValue != null)
				{
					boolean b = loadRepeatingDataToFunctionData(subField, functionData, root_bf_functionData, objectValue,
									bInputValue, conversionRules);
					changed = changed || b;
				}
			}

			// field
			else
			{
				String fieldName = subField.rtvFieldName();
				String fieldValue = getFieldValueEnhanced(bf_functionData, methodName);

				// not specified, then ignore this field
				if (fieldValue == null)
				{
					continue;
				}

				if (conversionRules != null && fieldValue != null)
				{
					String[] parameters = getConversionParameterFromEnhancedComplexType(root_bf_functionData, functionData,
									subField.rtvRuntimeInputField(), conversionRules, index);
					fieldValue = conversionRules.convert(subField.rtvRuntimeInputField(), fieldValue, parameters);
				}

				// check if the value will be updated
				if (!changed)
				{
					changed = functionData.chkFieldChange(fieldName, fieldValue, bInputValue);
				}

				// if there is no changed, then there is no need to update
				if (changed)
				{
					functionData.chgInputOrDBField(fieldName, fieldValue, bInputValue);
				}
			}
		}

		return changed;
	}

	/**
	 * Load the list complex type into the Function data
	 * 
	 * @param functionData
	 *            - the destination Function Data
	 * @param root_bf_functionData
	 *            - the top most complex data type
	 * @param bf_functionData
	 *            - the current (group) complex data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param conversionRules
	 *            - the conversion rule to convert between user format and Equation format
	 * 
	 * @return true if something has changed
	 */
	public boolean loadRepeatingDataToFunctionData(XSDStructure xsdStructure, FunctionData functionData,
					Object root_bf_functionData, Object bf_functionData, boolean bInputValue, ConversionRules conversionRules)
	{
		boolean changed = false;
		RepeatingDataManager repeatingDataManager = functionData.getRepeatingDataManager(xsdStructure.rtvBareId());
		repeatingDataManager.clear();

		// get row count
		int rows = getRowCount(bf_functionData, xsdStructure);

		// load the repeating details
		for (int i = 0; i < rows; i++)
		{
			// add row
			repeatingDataManager.addRow();
			changed = true;

			// Use the list of repeating InputFields (from the service definition) to drive the loop:
			Object rowObject = getRow(bf_functionData, xsdStructure, i);

			loadToFunctionData(xsdStructure, functionData, root_bf_functionData, rowObject, bInputValue, conversionRules, i);

		}

		return changed;
	}

	/**
	 * Return the equivalent complex data type of the microflow specified
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param fullClassName
	 *            - the full class name of the service microflow
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataType(EquationStandardSession session, String fullClassName) throws EQException
	{
		Object bf_functionData = null;
		Class<Object> c = getBFComplexTypeClass(session, fullClassName);
		if (c == null)
		{
			throw new EQException(LanguageResources.getString("FunctionBankFusion.NoComplexType"));
		}
		try
		{
			bf_functionData = c.newInstance();
		}
		catch (Exception e)
		{
			LOG.error("FunctionBankFusion.NoComplexType", e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
		return bf_functionData;
	}

	/**
	 * Return the equivalent complex data type of the Function
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param function
	 *            - the Function
	 * @param request
	 *            - True if Request XSD (only if enhanced XSD)
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataType(EquationStandardSession session, Function function, ConversionRules conversionRules)
					throws EQException
	{
		String fullClassName = null;

		// Enhanced XSD
		if (conversionRules != null && conversionRules.isGenerics())
		{
			if (conversionRules.isRequest())
			{
				String webServiceName = function.getXsdStructureRequest().rtvRuntimeWebServiceName();
				fullClassName = ComplexTypeToolbox.getComplexTypePackage(function) + "." + webServiceName;
			}
			else
			{
				String webServiceName = function.getXsdStructureResponse().rtvRuntimeWebServiceName();
				fullClassName = ComplexTypeToolbox.getComplexTypePackage(function) + "." + webServiceName;
			}
		}
		else
		{
			fullClassName = ComplexTypeToolbox.getComplexTypePackage(function) + "." + getServiceMicroflowName(function);
		}
		Object bf_functionData = getBankFusionDataType(session, fullClassName);

		return bf_functionData;
	}

	/**
	 * Return the equivalent complex data type of the given Function Data
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param functionAdaptor
	 *            - the Function Adaptor (obsolete)
	 * @param function
	 *            - the Function
	 * @param functionData
	 *            - the source Function Data
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return the complex data type
	 * @throws EQException
	 */
	public Object getBankFusionDataType(EquationStandardSession session, FunctionAdaptor functionAdaptor, Function function,
					FunctionData functionData, boolean bInputValue, ConversionRules conversionRules) throws EQException
	{
		Object bf_functionData = getBankFusionDataType(session, function, conversionRules);
		return getBankFusionDataType(function, functionData, bf_functionData, bInputValue, conversionRules);
	}

	/**
	 * Return the equivalent complex data type of the given AnyNode
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param function
	 *            - the Function
	 * @param anyNode
	 *            - the any node
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataType(EquationStandardSession session, Function function, Object anyNode) throws EQException
	{
		Object bf_functionData = getBankFusionDataType(session, function, null);
		return getBankFusionDataType(function, anyNode, bf_functionData);
	}

	/**
	 * Return the class definition of a function adaptor using the BankFusion Dynamic Class Loader
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param optionId
	 *            - the option Id
	 * 
	 * @return the complex type class of the option
	 */
	@SuppressWarnings("unchecked")
	public Class<Object> getBFComplexTypeClass(EquationStandardSession session, String fullClassName) throws EQException
	{
		if (LOG.isDebugEnabled())
		{
			LOG.debug("getInstance - loading definition of complex type class [" + fullClassName
							+ "] using BankFusion Dynamic Class Loader");
		}

		Class<Object> bfType = BFFunctionClassLoader.getBFClassLoader().loadClass(session, fullClassName);

		return bfType;
	}

	/**
	 * Return and set the given complex Data type based on the content of the specified Function Data
	 * 
	 * @param function
	 *            - the Function
	 * @param functionData
	 *            - the source Function Data
	 * @param bf_functionData
	 *            - the destination bank fusion data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param conversionRules
	 *            - the conversion rule to convert between user format and Equation format
	 * 
	 * @return the BF complex type
	 */
	public Object getBankFusionDataType(Function function, FunctionData functionData, Object bf_functionData, boolean bInputValue,
					ConversionRules conversionRules)
	{
		// enhanced XSD
		if (conversionRules != null && conversionRules.isGenerics())
		{
			XSDStructure xsdStructure = conversionRules.isRequest() ? function.getXsdStructureRequest() : function
							.getXsdStructureResponse();
			return getBankFusionDataType(function, xsdStructure, functionData, bf_functionData, bf_functionData, bInputValue,
							conversionRules, false);
		}

		Map<String, LinkedHashMap<String, InputField>> repeatingInputFields = new LinkedHashMap<String, LinkedHashMap<String, InputField>>();

		// Process non-repeating fields:
		for (InputFieldSet inputFieldSet : function.getInputFieldSets())
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				String fieldName = inputField.getId();

				// is this included in response?
				if (conversionRules != null
								&& conversionRules.isIncludedInFilter(inputField) == FieldFilterLocator.FILTERING_EXCLUDE)
				{
					continue;
				}

				if (!Field.isRepeating(inputField))
				{
					String fieldValue = bInputValue ? functionData.rtvFieldInputValue(fieldName) : functionData
									.rtvFieldDbValue(fieldName);

					// auto-convert?
					if (conversionRules != null)
					{
						fieldValue = conversionRules.convert(inputField, fieldValue, getConversionParameterFromFunctionData(
										functionData, inputField, conversionRules));
					}

					// set the field value of the complex data type
					setFieldValue(bf_functionData, inputField, fieldValue);
				}
				else
				{
					// Store the repeating field details for later processing
					LinkedHashMap<String, InputField> map = repeatingInputFields.get(inputField.getRepeatingGroup());
					if (map == null)
					{
						map = new LinkedHashMap<String, InputField>();
						repeatingInputFields.put(inputField.getRepeatingGroup(), map);
					}
					map.put(fieldName, inputField);
				}
			}
		}

		// Now process the repeating fields
		for (RepeatingDataManager repeatingDataManager : functionData.getRepeatingDataManagers())
		{
			// Does this function has this repeating group?
			// For linkage services, the function data contains all the repeating group, however, the function refers to the primary
			// function only
			if (repeatingInputFields.get(repeatingDataManager.getId()) == null)
			{
				continue;
			}

			repeatingDataManager.moveFirst();
			while (repeatingDataManager.next())
			{
				Object rowObject = addRow(bf_functionData, repeatingDataManager.getId());
				// Likely to fail, until deployment of all types is sorted...
				if (rowObject != null)
				{
					LinkedHashMap<String, InputField> map = repeatingInputFields.get(repeatingDataManager.getId());
					if (map != null)
					{
						// Use the list of repeating InputFields (from the service definition)
						// to drive the loop:
						for (Entry<String, InputField> entry : map.entrySet())
						{
							String fieldName = entry.getKey();
							String fieldValue = bInputValue ? functionData.rtvFieldInputValue(fieldName) : functionData
											.rtvFieldDbValue(fieldName);

							// auto-convert?
							if (conversionRules != null)
							{
								fieldValue = conversionRules.convert(entry.getValue(), fieldValue,
												getConversionParameterFromFunctionData(functionData, entry.getValue(),
																conversionRules));
							}

							// set the field value in the row complex data type
							setFieldValue(rowObject, entry.getValue(), fieldValue);
						}
					}
				}
			}
		}

		// return the output object
		return bf_functionData;
	}

	/**
	 * Return and set the given complex Data type based on the content of the specified Function Data
	 * 
	 * @param xsdStructure
	 *            - the root XSD
	 * @param functionData
	 *            - the source Function Data
	 * @param root_bf_functionData
	 *            - the top most complex data type
	 * @param bf_functionData
	 *            - the destination bank fusion data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param conversionRules
	 *            - the conversion rule to convert between user format and Equation format
	 * @param includeAllFields
	 *            - true - all fields are to be included<br>
	 *            - false - use the conversionRules's filtering to determine if field is to be included or not
	 * 
	 * @return the BF complex type
	 */
	protected Object getBankFusionDataType(Function function, XSDStructure xsdStructure, FunctionData functionData,
					Object root_bf_functionData, Object bf_functionData, boolean bInputValue, ConversionRules conversionRules,
					boolean includeAllFields)
	{
		// initialise
		int filterResult = FieldFilterLocator.FILTERING_EXCLUDE;

		for (XSDStructure subField : xsdStructure.getSubFields())
		{
			// is this included in response?
			if (!includeAllFields)
			{
				filterResult = conversionRules.isIncludedInFilter(subField);
				if (filterResult == FieldFilterLocator.FILTERING_EXCLUDE)
				{
					continue;
				}
			}

			// group?
			if (subField.chkGroup())
			{
				String methodName = "get" + subField.rtvRuntimeWebServiceName();
				Object objectValue = getFieldObject(bf_functionData, methodName);

				// retrieve the object
				if (objectValue == null)
				{
					Function ffff = function;

					// Load from BF, then use CTS package name
					if (subField.chkComplexType())
					{
						ffff = XMLToolbox.getXMLToolbox().getCtsBean(conversionRules.getEquationUnit().getUnitId());
					}

					objectValue = createAndSetObject(bf_functionData, subField.rtvRuntimeWebServiceName(), subField
									.rtvRuntimeComplexType(), ffff, conversionRules);
				}

				if (objectValue != null)
				{
					getBankFusionDataType(function, subField, functionData, root_bf_functionData, objectValue, bInputValue,
									conversionRules, (includeAllFields || filterResult == FieldFilterLocator.FILTERING_MATCH));
				}
			}

			// repeating groups
			else if (subField.chkRepeatingGroup())
			{
				String webserviceName = subField.rtvRuntimeWebServiceRepeatingGroupCollectionsName();
				String methodName = "get" + webserviceName;
				Object objectValue = getFieldObject(bf_functionData, methodName);
				if (objectValue == null)
				{
					objectValue = createAndSetObject(bf_functionData, webserviceName, webserviceName, function, conversionRules);
				}

				if (objectValue != null)
				{
					getBankFusionRepeatingDataType(function, subField, functionData, root_bf_functionData, objectValue,
									bInputValue, conversionRules);
				}
			}

			// field
			else
			{
				String methodName = "set" + subField.rtvRuntimeWebServiceName();

				String fieldValue = bInputValue ? functionData.rtvFieldInputValue(subField.rtvFieldName()) : functionData
								.rtvFieldDbValue(subField.rtvFieldName());

				// auto-convert?
				if (conversionRules != null)
				{
					fieldValue = conversionRules.convert(subField.rtvRuntimeInputField(), fieldValue,
									getConversionParameterFromFunctionData(functionData, subField.rtvRuntimeInputField(),
													conversionRules));
				}

				// set the field value of the complex data type
				setFieldObjectEnhanced(bf_functionData, methodName, fieldValue, subField.rtvRuntimeDataType());
			}
		}

		// return the output object
		return bf_functionData;
	}

	/**
	 * Return and set the given complex Data type based on the content of the specified Function Data
	 * 
	 * @param xsdStructure
	 *            - the root XSD
	 * @param functionData
	 *            - the source Function Data
	 * @param root_bf_functionData
	 *            - the top most complex data type
	 * @param bf_functionData
	 *            - the destination bank fusion data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * @param conversionRules
	 *            - the conversion rule to convert between user format and Equation format
	 * 
	 * @return the BF complex type
	 */
	protected Object getBankFusionRepeatingDataType(Function function, XSDStructure xsdStructure, FunctionData functionData,
					Object root_bf_functionData, Object bf_functionData, boolean bInputValue, ConversionRules conversionRules)
	{
		RepeatingDataManager repeatingDataManager = functionData.getRepeatingDataManager(xsdStructure.rtvBareId());

		// determine if repeating group is included or not
		int filter = conversionRules.isIncludedInFilter(xsdStructure);

		repeatingDataManager.moveFirst();
		while (repeatingDataManager.next())
		{
			Object rowObject = addRow(bf_functionData, xsdStructure, function, conversionRules);

			if (rowObject != null)
			{
				getBankFusionDataType(function, xsdStructure, functionData, root_bf_functionData, rowObject, bInputValue,
								conversionRules, filter == FieldFilterLocator.FILTERING_MATCH);
			}
		}

		return bf_functionData;
	}

	/**
	 * Return and set the given complex Data type based on the content of the AnyNode
	 * 
	 * @param function
	 *            - the Function
	 * @param anyNode
	 *            - the AnyNode data type
	 * @param bf_functionData
	 *            - the destination bank fusion data type
	 * 
	 * @return
	 */
	public Object getBankFusionDataType(Function function, Object anyNode, Object bf_functionData) throws EQException
	{
		String xml = anyNode.toString();
		StringReader reader = new StringReader(xml);
		Unmarshaller unMarshaller = new Unmarshaller(bf_functionData);

		try
		{
			bf_functionData = unMarshaller.unmarshal(reader);
		}
		catch (Exception e)
		{
			throw new EQException("FunctionBankFusion.getBankFusionDataType(): " + e.getMessage());
		}

		// return the output object
		return bf_functionData;
	}

	/**
	 * Method to add a Row object to the BF Function data.
	 * <p>
	 * This uses the class name of the BF Function data to determine the name of the row class
	 * 
	 * @param bf_functionData
	 * @param repeatGroup
	 *            The Id of the repeat group, used to determine the class and method names
	 * 
	 * @return Object the newly added row object
	 */
	public Object addRow(Object bf_functionData, String repeatGroup)
	{
		Object rowObject = null;
		// Name of the row class is a munge of the repeating group Id.
		// This matches the name determined in XSDToolbox.
		String rowsName = FunctionToolbox.UNDERSCORE + repeatGroup + BF_LIST_SUFFIX;

		// Assume the same Package as the class, but note that getPackage returns null for
		// a class loaded by BankFusion, so determine this from the name
		String[] parts = bf_functionData.getClass().getName().split("\\.");
		parts[parts.length - 1] += rowsName;
		String rowsClassName = Toolbox.join(parts, ".");

		try
		{
			// Use the same classloader as BankFusion
			Class<?> rowsClass = bf_functionData.getClass().getClassLoader().loadClass(rowsClassName);
			rowObject = rowsClass.newInstance();
			// Use reflection to find the addRows method and invoke it, passing in the new object
			String methodName = "add" + parts[parts.length - 1];
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName, rowsClass);
			method.invoke(bf_functionData, rowObject);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}

		return rowObject;
	}

	/**
	 * Method to get a Row object
	 * 
	 * @param bf_functionData
	 * @param repeatGroup
	 *            The Id of the repeat group, used to determine the class and method names
	 * 
	 * @return the row object
	 */
	protected Object getRow(Object bf_functionData, String repeatGroup, int index)
	{
		Object rowObject = null;
		// Name of the row class is a munge of the repeating group Id.
		// This matches the name determined in XSDToolbox.
		String[] parts = bf_functionData.getClass().getName().split("\\.");
		String rowsName = parts[parts.length - 1] + FunctionToolbox.UNDERSCORE + repeatGroup + BF_LIST_SUFFIX;

		try
		{
			// Use reflection to find the getRow method and invoke it, passing the desired index
			String methodName = "get" + rowsName;
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName, int.class);
			rowObject = method.invoke(bf_functionData, index);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}

		return rowObject;
	}

	/**
	 * Method to get a Row object
	 * 
	 * @param bf_functionData
	 * 
	 * @param repeatGroup
	 *            The Id of the repeat group, used to determine the class and method names
	 * 
	 * @return the row object
	 */
	protected int getRowCount(Object bf_functionData, String repeatGroup)
	{
		// Name of the row class is a munge of the repeating group Id.
		// This matches the name determined in XSDToolbox.
		String[] parts = bf_functionData.getClass().getName().split("\\.");
		String rowsName = parts[parts.length - 1] + FunctionToolbox.UNDERSCORE + repeatGroup + BF_LIST_SUFFIX;

		try
		{
			// Use reflection to find the getRow method and invoke it, passing the desired index
			String methodName = "get" + rowsName + "Count";
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName);
			Integer i = (Integer) method.invoke(bf_functionData);
			return i.intValue();
		}
		catch (Exception e)
		{
			LOG.error(e);
		}

		return -1;
	}

	/**
	 * Method to add a Row object to the BF Function data.
	 * <p>
	 * This uses the class name of the BF Function data to determine the name of the row class
	 * 
	 * @param bf_functionData
	 * @param repeatGroup
	 *            The Id of the repeat group, used to determine the class and method names
	 * 
	 * @return Object the newly added row object
	 */
	public Object addRow(Object bf_functionData, XSDStructure xsd, Function function, ConversionRules conversionRules)
	{
		Object rowObject = null;
		try
		{
			String fullClassName = ComplexTypeToolbox.getComplexTypePackageEnhancedXsd(function, xsd.rtvRuntimeWebServiceName());
			rowObject = conversionRules.getBankfusionDataType(fullClassName);

			// Use reflection to find the addRows method and invoke it, passing in the new object
			String methodName = "add" + xsd.rtvRuntimeWebServiceName();
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName, rowObject.getClass());
			method.invoke(bf_functionData, rowObject);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}

		return rowObject;
	}

	/**
	 * Method to get a Row object
	 * 
	 * @param bf_functionData
	 * @param repeatGroup
	 *            The Id of the repeat group, used to determine the class and method names
	 * 
	 * @return the row object
	 */
	protected Object getRow(Object bf_functionData, XSDStructure xsd, int index)
	{
		Object rowObject = null;
		try
		{
			// Use reflection to find the getRow method and invoke it, passing the desired index
			String methodName = "get" + xsd.rtvRuntimeWebServiceName();
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName, int.class);
			rowObject = method.invoke(bf_functionData, index);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}

		return rowObject;
	}

	/**
	 * Method to get a Row object
	 * 
	 * @param bf_functionData
	 * 
	 * @param repeatGroup
	 *            The Id of the repeat group, used to determine the class and method names
	 * 
	 * @return the row object
	 */
	protected int getRowCount(Object bf_functionData, XSDStructure xsd)
	{
		try
		{
			// Use reflection to find the getRow method and invoke it, passing the desired index
			String methodName = "get" + xsd.rtvRuntimeWebServiceName() + "Count";
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName);
			Integer i = (Integer) method.invoke(bf_functionData);
			return i.intValue();
		}
		catch (Exception e)
		{
			LOG.error(e);
		}

		return -1;
	}

	/**
	 * Return the field value of the field name of a complex data type
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param inputField
	 *            - the input field
	 * 
	 * @return the field value
	 */
	public String getFieldValue(Object bf_functionData, InputField inputField)
	{
		String methodName = "get" + getFieldName(inputField);
		return getFieldValue(bf_functionData, methodName);
	}

	/**
	 * Return the field value of the field name of a complex data type using reflection. If the getter method for the field does not
	 * exist an empty string will be returned.
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param methodName
	 *            - the method name
	 * 
	 * @return the field value
	 */
	public String getFieldValue(Object bf_functionData, String methodName)
	{
		try
		{
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName);
			String fieldValue = (String) method.invoke(bf_functionData);
			return fieldValue;
		}
		catch (Exception e)
		{
			LOG.warn(e);
			return "";
		}
	}

	/**
	 * Set the field value of the field name of a complex data type
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param inputField
	 *            - the input field
	 * @param fieldValue
	 *            - the field value
	 * 
	 * @return the field value, otherwise NULL if failed
	 */
	public void setFieldValue(Object bf_functionData, InputField inputField, String fieldValue)
	{
		String methodName = "set" + getFieldName(inputField);
		setFieldValue(bf_functionData, methodName, fieldValue);
	}

	/**
	 * Set the field value of the field name of a complex data type
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param methodName
	 *            - the method name
	 * @param fieldValue
	 *            - the field value
	 * 
	 * @return the field value, otherwise NULL if failed
	 */
	public void setFieldValue(Object bf_functionData, String methodName, String fieldValue)
	{
		try
		{
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName, String.class);
			method.invoke(bf_functionData, fieldValue);
		}
		catch (Exception e)
		{
			LOG.error("setFieldValue", e);
		}
	}

	/**
	 * Return the field value of the field name of a complex data type using reflection. If the getter method for the field does not
	 * exist an empty string will be returned.
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param methodName
	 *            - the method name
	 * 
	 * @return the field value
	 */
	public Object getFieldObject(Object bf_functionData, String methodName)
	{
		try
		{
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName);
			Object fieldValue = (Object) method.invoke(bf_functionData);
			return fieldValue;
		}
		catch (Exception e)
		{
			LOG.warn(e);
			return null;
		}
	}

	/**
	 * Set the field value of the field name of a complex data type
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param methodName
	 *            - the method name
	 * @param fieldValue
	 *            - the value
	 */
	public void setFieldObject(Object bf_functionData, String methodName, Object fieldValue)
	{
		try
		{
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName, fieldValue.getClass());
			method.invoke(bf_functionData, fieldValue);
		}
		catch (Exception e)
		{
			LOG.error("setFieldObject", e);
		}
	}

	/**
	 * Return the field value of the field name of a complex data type
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param path
	 *            - the path to the field
	 * @param index
	 *            - index for the repeating list
	 * 
	 * @return the field value
	 */
	public String getFieldValue(Object bf_functionData, ArrayList<XSDStructure> path, int index)
	{
		// traverse the hierarchy
		Object referenceObj = bf_functionData;
		for (XSDStructure xsd : path)
		{
			if (xsd.chkGroup())
			{
				referenceObj = getFieldObject(referenceObj, "get" + xsd.rtvRuntimeWebServiceName());
			}
			else if (xsd.chkRepeatingGroup())
			{
				Object listObjects = getFieldObject(referenceObj, "get" + xsd.rtvRuntimeWebServiceRepeatingGroupCollectionsName());
				if (listObjects == null)
				{
					return "";
				}
				// retrieve the nth element identified by index
				referenceObj = null;
				if (index >= 0)
				{
					referenceObj = getRow(listObjects, xsd, index);
				}
			}
			else
			{
				return getFieldValueEnhanced(referenceObj, "get" + xsd.rtvRuntimeWebServiceName());
			}

			// no more object?
			if (referenceObj == null)
			{
				break;
			}
		}

		// field not found, then return null value
		return null;
	}

	/**
	 * Return the field value of the field name of a complex data type
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param id
	 *            - the field id
	 * @param fieldValue
	 *            - the field value
	 * @param conversionRules
	 *            - the conversion rules
	 * 
	 * @return the field value
	 */
	public String setFieldValue(Object bf_functionData, String id, String fieldValue, ConversionRules conversionRules)
					throws EQException
	{
		// traverse the hierarchy
		Object referenceObj = bf_functionData;
		for (XSDStructure xsd : conversionRules.getPath(id))
		{
			String methodName = "set" + xsd.rtvRuntimeWebServiceName();
			if (xsd.chkGroup() || xsd.chkRepeatingGroup())
			{
				Object groupObject = getFieldObject(bf_functionData, methodName);

				// null, then create it
				if (referenceObj == null)
				{
					String webServiceName = xsd.rtvRuntimeWebServiceName();
					String fullClassName = ComplexTypeToolbox.getComplexTypePackageEnhancedXsd(null, webServiceName);
					groupObject = conversionRules.getBankfusionDataType(fullClassName);
					setFieldObject(referenceObj, methodName, groupObject);
				}

				referenceObj = groupObject;
			}
			else
			{
				setFieldValue(referenceObj, methodName, fieldValue);
			}
		}

		return "";
	}
	/**
	 * Determines the name of the Service Microflow
	 * <p>
	 * When deployed as a BankFusion WebService, the Microflow Id will be used as the WebService operation name. In order to provide
	 * both uniqueness and human readability, the Microflow name is generated using both the service id and title. This method is
	 * used by the Service Composer (to provide a read-only indication of what the WebService field name should be), and also when
	 * the Service Microflow is actually generated.
	 * 
	 * @param service
	 *            The EQ4 service to generate the operation for
	 * 
	 * @return a String containing the name of the Service Microflow
	 */
	public static String getServiceMicroflowName(Function service)
	{
		return getServiceXSDName(service) + FunctionToolbox.UNDERSCORE + "SRV";
	}

	/**
	 * Generates the name of the XSD file associated with this service
	 * 
	 * @param service
	 *            The EQ4 service to generate the operation for
	 * 
	 * @return a String containing the name of the XSD file
	 */
	public static String getServiceXSDName(Function service)
	{
		return service.getappBankId() + FunctionToolbox.UNDERSCORE + service.getModuleId() + FunctionToolbox.UNDERSCORE
						+ Toolbox.textToCamelCase(service.getLabel()) + service.getId();
	}

	/**
	 * Generates the name of the XSD file associated with this layout
	 * 
	 * @param service
	 *            The EQ4 service to generate the operation for
	 * @param layout
	 *            The EQ4 service layout to generate the operation for
	 * 
	 * @return a String containing the name of the XSD file
	 */
	public static String getServiceXSDName(Function service, Layout layout)
	{
		return service.getappBankId() + FunctionToolbox.UNDERSCORE + service.getModuleId() + FunctionToolbox.UNDERSCORE
						+ Toolbox.textToCamelCase(service.getLabel()) + layout.getId();
	}

	/**
	 * Generates the name of a service field used in the Complex Type
	 * <p>
	 * The name of the field is based on both the ID and the label, in order to provide both uniqueness and human readability. This
	 * method is used by the Service Composer (to provide a read-only indication of what the WebService field name should be), and
	 * also when the complex type is actually generated. It is also used at runtime by the FunctionHandlerActivityStep code when
	 * marshalling field values between the BankFusion complex type and the EQ4 FunctionData.
	 * 
	 * @param field
	 *            An <code>InputField</code> to generate the name for. Note that only InputFields are exposed in the WebService (not
	 *            WorkFields).
	 * 
	 * @return a String containing the complex type name
	 */
	public static String getFieldName(InputField field)
	{
		if (field.isExcludeFromType())
		{
			return ""; // Blank name
		}
		else
		{
			if (field.isShortTypeName())
			{
				return field.getId();
			}
			else
			{
				return field.getId() + FunctionToolbox.UNDERSCORE + Toolbox.textToCamelCase(field.getLabel());
			}
		}
	}

	/**
	 * Determines the name of the Update user exit Microflow
	 * 
	 * @param service
	 * @return a String containing the name of the microflow
	 */
	public static String getUpdateUserExitMicroflowName(Function service)
	{
		return getServiceXSDName(service) + FunctionToolbox.UNDERSCORE + "UpdateUserExit";
	}

	/**
	 * Determines the name of the default user exit Microflow
	 * 
	 * @param service
	 *            - the Equation service function
	 * @param inputFieldSet
	 *            - the input field set
	 * 
	 * @return a String containing the name of the microflow
	 */
	public static String getDefaultUserExitMicroflowName(Function service, InputFieldSet inputFieldSet)
	{
		return getServiceXSDName(service) + FunctionToolbox.UNDERSCORE + inputFieldSet.getId() + FunctionToolbox.UNDERSCORE
						+ "DefaultUserExit";
	}

	/**
	 * Determines the name of the validate user exit Microflow
	 * 
	 * @param service
	 *            - the Equation service function
	 * @param inputFieldSet
	 *            - the input field set
	 * 
	 * @return a String containing the name of the microflow
	 */
	public static String getValidateUserExitMicroflowName(Function service, InputFieldSet inputFieldSet)
	{
		return getServiceXSDName(service) + FunctionToolbox.UNDERSCORE + inputFieldSet.getId() + FunctionToolbox.UNDERSCORE
						+ "ValidateUserExit";
	}

	/**
	 * Determines the name of the Previous screen user exit Microflow
	 * 
	 * @param service
	 *            - the Equation service function
	 * @param layout
	 *            - the Equation service layout
	 * 
	 * @return a String containing the name of the microflow
	 */
	public static String getPrevScrnUserExitMicroflowName(Function service, Layout layout)
	{
		return getServiceXSDName(service, layout) + FunctionToolbox.UNDERSCORE + "PreviousScreenUserExit";
	}

	/**
	 * Determines the name of the Next screen user exit Microflow
	 * 
	 * @param service
	 *            - the Equation service function
	 * @param layout
	 *            - the Equation service layout
	 * 
	 * @return a String containing the name of the microflow
	 */
	public static String getNextScrnUserExitMicroflowName(Function service, Layout layout)
	{
		return getServiceXSDName(service, layout) + FunctionToolbox.UNDERSCORE + "NextScreenUserExit";
	}

	/**
	 * Return the BankFusion function header based on a screen set
	 * 
	 * @param screenSet
	 *            - the screen set
	 * 
	 * @return the BankFusion function header
	 */
	public ServiceRqHeader getFunctionBFHeader(ScreenSet screenSet)
	{
		return getFunctionBFHeader(screenSet, 0, "", "", "", "", "", "");
	}

	/**
	 * Return the BankFusion function header based on a screen set
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param ckey
	 *            - the control key
	 * @param fldValidate
	 *            - the field validate
	 * @param loadField
	 *            - the load field
	 * @param loadFieldSet
	 *            - the load field set
	 * @param mode
	 *            - the mode of the UI transaction ("V" - validate or "U" - update)
	 * @param reason
	 *            - the supervisor override decline reason
	 * @param transactionId
	 *            - the transaction id
	 * 
	 * @return the BankFusion function header
	 */
	public ServiceRqHeader getFunctionBFHeader(ScreenSet screenSet, int ckey, String fldValidate, String loadField,
					String loadFieldSet, String mode, String reason, String transactionId)
	{
		String sessionId = screenSet.getFhd().getFunctionInfo().getSessionId();
		EquationUser eqUser = screenSet.getFhd().getEquationUser();
		EquationUnit eqUnit = eqUser.getEquationUnit();

		ServiceRqHeader bf_serviceHeader = new ServiceRqHeader();
		initialiseServiceRqHeader(bf_serviceHeader);
		RqHeader rqHeader = bf_serviceHeader.getRqHeader();
		rqHeader.setSystemId(eqUnit.getSystem().getSystemId());
		rqHeader.setUnitId(eqUnit.getUnitId());
		// User should come from the BankFusion Header. Only in headless call should EQ request header have real user set.
		// rqHeader.setUserId(eqUser.getUserId());
		rqHeader.setSessionId(sessionId);

		// default date format and amount format
		Formatting formatting = new Formatting();
		formatting.setDateFormat(ConversionRules.DATE_CYYMMDD);
		formatting.setAmountFormat(ConversionRules.AMOUNT_MINOR_CURRENCY);
		rqHeader.setFormatting(formatting);

		bf_serviceHeader.setServiceMode(screenSet.getFunctionData().rtvFieldDbValue(FunctionData.FLD_FCT));
		bf_serviceHeader.setOptionId(screenSet.getOptionId());
		boolean keyLoaded = screenSet.getFhd().getScreenSetHandler().rtvScreenSetMain().getFunctionData().rtvFieldDbValue(
						FunctionData.FLD_KEYLOADED).equals("Y");
		bf_serviceHeader.setUiControlRq(getUiControlRq(ckey, fldValidate, loadField, loadFieldSet, mode, reason, transactionId,
						keyLoaded, screenSet.getFieldSetId(screenSet.getScrnNo() + 1)));

		return bf_serviceHeader;
	}
	/**
	 * Return the FunctionHeader UiControlData - information from Desktop Request to be passed to Microflow
	 * 
	 * @param ckey
	 *            - the control key
	 * @param fldValidate
	 *            - the field validate
	 * @param loadField
	 *            - the load field
	 * @param loadFieldSet
	 *            - the load field set
	 * @param mode
	 *            - the mode of the UI transaction ("V" - validate or "U" - update)
	 * @param reason
	 *            - the supervisor override decline reason
	 * @param transactionId
	 *            - the transaction id
	 * 
	 * @return the iControlData user control data in desktop request
	 */
	public UiControlRq getUiControlRq(int ckey, String fldValidate, String loadField, String loadFieldSet, String mode,
					String reason, String transactionId, boolean keyLoaded, String curScrn)
	{
		UiControlRq bf_uiControl = new UiControlRq();
		bf_uiControl.setUiMode(mode);
		bf_uiControl.setCkey(ckey);
		bf_uiControl.setTransactionId(transactionId);
		bf_uiControl.setFldValidate(fldValidate);
		bf_uiControl.setReason(reason);
		bf_uiControl.setLoadFieldSet(loadFieldSet);
		bf_uiControl.setLoadField(loadField);
		bf_uiControl.setCurScrn(curScrn);
		bf_uiControl.setKeyLoaded(keyLoaded);
		bf_uiControl.setChildFunctionHandlerId("");

		return bf_uiControl;
	}

	/**
	 * Initialise service request header to blank when NULL
	 * 
	 * @param serviceRqHeader
	 *            - the Service Request Header to be initialised
	 */
	public void initialiseServiceRqHeader(ServiceRqHeader serviceRqHeader)
	{
		// RqHeader is not set, then initialise
		RqHeader rqHeader = serviceRqHeader.getRqHeader();
		if (rqHeader == null)
		{
			rqHeader = new RqHeader();
			rqHeader.setSystemId(CommonConstants.EMPTY_STRING);
			rqHeader.setUnitId(CommonConstants.EMPTY_STRING);
			rqHeader.setUserId(CommonConstants.EMPTY_STRING);
			rqHeader.setSessionId(CommonConstants.EMPTY_STRING);
			rqHeader.setSessionType(CommonConstants.EMPTY_STRING);
			rqHeader.setDataSourceName(CommonConstants.EMPTY_STRING);
			rqHeader.setVersion(CommonConstants.EMPTY_STRING);
			Orig orig = new Orig();
			orig.setAppId(CommonConstants.EMPTY_STRING);
			BranchKeys branchKeys = new BranchKeys();
			branchKeys.setBranchCode(CommonConstants.EMPTY_STRING);
			branchKeys.setBranchMnemonic(CommonConstants.EMPTY_STRING);
			orig.setOrigBranch(branchKeys);
			orig.setOrigWorkstation(CommonConstants.EMPTY_STRING);
			orig.setOrigTCPIP(CommonConstants.EMPTY_STRING);
			rqHeader.setOrig(orig);
			rqHeader.setMessageType(CommonConstants.EMPTY_STRING);
			Overrides overrides = new Overrides();
			overrides.setOverrideType(CommonConstants.EMPTY_STRING);
			rqHeader.setOverrides(overrides);
			rqHeader.setEqMessageDetail(CommonConstants.EMPTY_STRING);
			rqHeader.setResponseFilter(new String[0]);
			Formatting formatting = new Formatting();
			formatting.setDateFormat(CommonConstants.EMPTY_STRING);
			formatting.setAmountFormat(CommonConstants.EMPTY_STRING);
			formatting.setReturnAllFormats(CommonConstants.EMPTY_STRING);
			rqHeader.setFormatting(formatting);
			rqHeader.setUserExtension(CommonConstants.EMPTY_STRING); // do not set to new Object
			serviceRqHeader.setRqHeader(rqHeader);
		}
		else
		{
			if (rqHeader.getSystemId() == null)
			{
				rqHeader.setSystemId(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getUnitId() == null)
			{
				rqHeader.setUnitId(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getUserIdType() == null)
			{
				rqHeader.setUserIdType(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getUserId() == null)
			{
				rqHeader.setUserId(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getSessionId() == null)
			{
				rqHeader.setSessionId(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getSessionType() == null)
			{
				rqHeader.setSessionType(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getDataSourceName() == null)
			{
				rqHeader.setDataSourceName(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getVersion() == null)
			{
				rqHeader.setVersion(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getOrig() == null)
			{
				Orig orig = new Orig();
				orig.setAppId(CommonConstants.EMPTY_STRING);
				BranchKeys branchKeys = new BranchKeys();
				branchKeys.setBranchCode(CommonConstants.EMPTY_STRING);
				branchKeys.setBranchMnemonic(CommonConstants.EMPTY_STRING);
				orig.setOrigBranch(branchKeys);
				orig.setOrigWorkstation(CommonConstants.EMPTY_STRING);
				orig.setOrigTCPIP(CommonConstants.EMPTY_STRING);
				rqHeader.setOrig(orig);
			}
			else
			{
				Orig orig = rqHeader.getOrig();
				if (orig.getAppId() == null)
				{
					orig.setAppId(CommonConstants.EMPTY_STRING);
				}
				if (orig.getOrigBranch() == null)
				{
					BranchKeys branchKeys = new BranchKeys();
					branchKeys.setBranchCode(CommonConstants.EMPTY_STRING);
					branchKeys.setBranchMnemonic(CommonConstants.EMPTY_STRING);
					orig.setOrigBranch(branchKeys);
				}
				else
				{
					BranchKeys branchKeys = orig.getOrigBranch();
					if (branchKeys.getBranchCode() == null)
					{
						branchKeys.setBranchCode(CommonConstants.EMPTY_STRING);
					}
					if (branchKeys.getBranchCode() == null)
					{
						branchKeys.setBranchMnemonic(CommonConstants.EMPTY_STRING);
					}
				}
				if (orig.getOrigWorkstation() == null)
				{
					orig.setOrigWorkstation(CommonConstants.EMPTY_STRING);
				}
				if (orig.getOrigTCPIP() == null)
				{
					orig.setOrigTCPIP(CommonConstants.EMPTY_STRING);
				}
			}
			if (rqHeader.getMessageType() == null)
			{
				rqHeader.setMessageType(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getOverrides() == null)
			{
				Overrides overrides = new Overrides();
				overrides.setOverrideType(CommonConstants.EMPTY_STRING);
				rqHeader.setOverrides(overrides);
			}
			else
			{
				Overrides overrides = rqHeader.getOverrides();
				if (overrides.getOverrideType() == null)
				{
					overrides.setOverrideType(CommonConstants.EMPTY_STRING);
				}
			}
			if (rqHeader.getEqMessageDetail() == null)
			{
				rqHeader.setEqMessageDetail(CommonConstants.EMPTY_STRING);
			}
			if (rqHeader.getResponseFilter() == null)
			{
				rqHeader.setResponseFilter(new String[0]);
			}
			if (rqHeader.getFormatting() == null)
			{
				Formatting formatting = new Formatting();
				formatting.setDateFormat(CommonConstants.EMPTY_STRING);
				formatting.setAmountFormat(CommonConstants.EMPTY_STRING);
				formatting.setReturnAllFormats(CommonConstants.EMPTY_STRING);
				rqHeader.setFormatting(formatting);
			}
			else
			{
				Formatting formatting = rqHeader.getFormatting();
				if (formatting.getDateFormat() == null)
				{
					formatting.setDateFormat(CommonConstants.EMPTY_STRING);
				}
				if (formatting.getAmountFormat() == null)
				{
					formatting.setAmountFormat(CommonConstants.EMPTY_STRING);
				}
				if (formatting.getReturnAllFormats() == null)
				{
					formatting.setReturnAllFormats(CommonConstants.EMPTY_STRING);
				}
			}
			if (rqHeader.getUserExtension() == null)
			{
				rqHeader.setUserExtension(CommonConstants.EMPTY_STRING); // do not set to new Object)
			}
		}

		if (serviceRqHeader.getOptionId() == null)
		{
			serviceRqHeader.setOptionId(CommonConstants.EMPTY_STRING);
		}
		if (serviceRqHeader.getServiceMode() == null)
		{
			serviceRqHeader.setServiceMode(CommonConstants.EMPTY_STRING);
		}

		if (serviceRqHeader.getEnquiryRq() == null)
		{
			EnquiryRq enquiryRq = new EnquiryRq();
			enquiryRq.setRequestedPage(0);
			enquiryRq.setNumberOfRows(0);
			enquiryRq.setPagingInformation(CommonConstants.EMPTY_STRING);
			serviceRqHeader.setEnquiryRq(enquiryRq);

		}
		else
		{
			EnquiryRq enquiryRq = serviceRqHeader.getEnquiryRq();
			if (enquiryRq.getRequestedPage() == null)
			{
				enquiryRq.setRequestedPage(0);
			}
			if (enquiryRq.getNumberOfRows() == null)
			{
				enquiryRq.setNumberOfRows(0);
			}
			if (enquiryRq.getPagingInformation() == null)
			{
				enquiryRq.setPagingInformation(CommonConstants.EMPTY_STRING);
			}
		}

		if (serviceRqHeader.getChecksumFilter() == null)
		{
			serviceRqHeader.setChecksumFilter(new String[0]);
		}

		if (serviceRqHeader.getChecksum() == null)
		{
			serviceRqHeader.setChecksum(CommonConstants.EMPTY_STRING);
		}
		if (serviceRqHeader.getSupervisor() == null)
		{
			serviceRqHeader.setSupervisor(CommonConstants.EMPTY_STRING);
		}
		if (serviceRqHeader.getReference() == null)
		{
			serviceRqHeader.setReference(CommonConstants.EMPTY_STRING);
		}

		// UI control is not set, then initialise
		UiControlRq uiControlRq = serviceRqHeader.getUiControlRq();
		if (uiControlRq == null)
		{
			uiControlRq = new UiControlRq();
			uiControlRq.setUiMode(CommonConstants.EMPTY_STRING);
			uiControlRq.setCkey(0);
			uiControlRq.setTransactionId(CommonConstants.EMPTY_STRING);
			uiControlRq.setFldValidate(CommonConstants.EMPTY_STRING);
			uiControlRq.setReason(CommonConstants.EMPTY_STRING);
			uiControlRq.setLoadFieldSet(CommonConstants.EMPTY_STRING);
			uiControlRq.setLoadField(CommonConstants.EMPTY_STRING);
			uiControlRq.setCurScrn(CommonConstants.EMPTY_STRING);
			uiControlRq.setKeyLoaded(false);
			uiControlRq.setChildFunctionHandlerId(CommonConstants.EMPTY_STRING);
			serviceRqHeader.setUiControlRq(uiControlRq);
		}
		else
		{
			if (uiControlRq.getUiMode() == null)
			{
				uiControlRq.setUiMode(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRq.getCkey() == null)
			{
				uiControlRq.setCkey(0);
			}
			if (uiControlRq.getTransactionId() == null)
			{
				uiControlRq.setTransactionId(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRq.getFldValidate() == null)
			{
				uiControlRq.setFldValidate(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRq.getReason() == null)
			{
				uiControlRq.setReason(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRq.getLoadFieldSet() == null)
			{
				uiControlRq.setLoadFieldSet(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRq.getLoadField() == null)
			{
				uiControlRq.setLoadField(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRq.getCurScrn() == null)
			{
				uiControlRq.setCurScrn(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRq.getKeyLoaded() == null)
			{
				uiControlRq.setKeyLoaded(false);
			}
			if (uiControlRq.getChildFunctionHandlerId() == null)
			{
				uiControlRq.setChildFunctionHandlerId(CommonConstants.EMPTY_STRING);
			}
		}
	}
	/**
	 * Initialise service response header to blank when NULL
	 * 
	 * @param serviceRsHeader
	 *            - the Service Response Header to be initialised
	 */
	public void initialiseServiceRsHeader(ServiceRsHeader serviceRsHeader)
	{
		// RsHeader is not set, then initialise
		RsHeader rsHeader = serviceRsHeader.getRsHeader();
		if (rsHeader == null)
		{
			rsHeader = new RsHeader();
			MessageStatus status = new MessageStatus();
			status.setOverallStatus(CommonConstants.EMPTY_STRING);
			rsHeader.setStatus(status);
			rsHeader.setUserExtension(CommonConstants.EMPTY_STRING); // do not set to Object
			rsHeader.setSessionId(CommonConstants.EMPTY_STRING);
			serviceRsHeader.setRsHeader(rsHeader);
		}
		else
		{
			if (rsHeader.getStatus() == null)
			{
				MessageStatus status = new MessageStatus();
				status.setOverallStatus(CommonConstants.EMPTY_STRING);
				rsHeader.setStatus(status);
			}
			if (rsHeader.getUserExtension() == null)
			{
				rsHeader.setUserExtension(CommonConstants.EMPTY_STRING);
			}
			if (rsHeader.getSessionId() == null)
			{
				rsHeader.setSessionId(CommonConstants.EMPTY_STRING);
			}
		}

		EnquiryRs enquiryRs = serviceRsHeader.getEnquiryRs();
		if (enquiryRs == null)
		{
			enquiryRs = new EnquiryRs();
			enquiryRs.setPagingInformation(CommonConstants.EMPTY_STRING);
			enquiryRs.setTotalPages(0);
			serviceRsHeader.setEnquiryRs(enquiryRs);
		}
		else
		{
			if (enquiryRs.getPagingInformation() == null)
			{
				enquiryRs.setPagingInformation(CommonConstants.EMPTY_STRING);
			}
			if (enquiryRs.getTotalPages() == null)
			{
				enquiryRs.setTotalPages(0);
			}
		}

		JournalKey journalKey = serviceRsHeader.getJournalKey();
		if (journalKey == null)
		{
			journalKey = new JournalKey();
			journalKey.setWorkstationId(CommonConstants.EMPTY_STRING);
			journalKey.setDayInMonth(CommonConstants.EMPTY_STRING);
			journalKey.setTime(CommonConstants.EMPTY_STRING);
			journalKey.setSequence(CommonConstants.EMPTY_STRING);
			journalKey.setProgramRoot(CommonConstants.EMPTY_STRING);
			journalKey.setTransactionType(CommonConstants.EMPTY_STRING);
			journalKey.setCreateDate(CommonConstants.EMPTY_STRING);
			journalKey.setCcLinkTime(CommonConstants.EMPTY_STRING);
			journalKey.setCcSequence(CommonConstants.EMPTY_STRING);
			journalKey.setJobNumber(CommonConstants.EMPTY_STRING);
			journalKey.setRecoveryStatus(CommonConstants.EMPTY_STRING);
			serviceRsHeader.setJournalKey(journalKey);
		}
		else
		{
			if (journalKey.getWorkstationId() == null)
			{
				journalKey.setWorkstationId(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getDayInMonth() == null)
			{
				journalKey.setDayInMonth(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getTime() == null)
			{
				journalKey.setTime(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getSequence() == null)
			{
				journalKey.setSequence(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getProgramRoot() == null)
			{
				journalKey.setProgramRoot(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getTransactionType() == null)
			{
				journalKey.setTransactionType(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getCreateDate() == null)
			{
				journalKey.setCreateDate(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getCcLinkTime() == null)
			{
				journalKey.setCcLinkTime(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getCcSequence() == null)
			{
				journalKey.setCcSequence(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getJobNumber() == null)
			{
				journalKey.setJobNumber(CommonConstants.EMPTY_STRING);
			}
			if (journalKey.getRecoveryStatus() == null)
			{
				journalKey.setRecoveryStatus(CommonConstants.EMPTY_STRING);
			}
		}
		CrmMessages crmMessages = serviceRsHeader.getCrmMessages();
		if (crmMessages == null)
		{
			crmMessages = new CrmMessages();
			serviceRsHeader.setCrmMessages(crmMessages);
		}
		// UI control is not set, then initialise
		UiControlRs uiControlRs = serviceRsHeader.getUiControlRs();
		if (uiControlRs == null)
		{
			uiControlRs = new UiControlRs();
			uiControlRs.setNextScrn(CommonConstants.EMPTY_STRING);
			uiControlRs.setNextProcess(0);
			serviceRsHeader.setUiControlRs(uiControlRs);
		}
		else
		{
			if (uiControlRs.getNextScrn() == null)
			{
				uiControlRs.setNextScrn(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRs.getNextProcess() == null)
			{
				uiControlRs.setNextProcess(0);
			}
		}
	}

	/**
	 * Initialise search response header to blank when NULL
	 * 
	 * @param searchRsHeader
	 *            - the Search Response Header to be initialised
	 */
	public void initialiseSearchRsHeader(SearchRsHeader searchRsHeader)
	{
		// RsHeader is not set, then initialise
		RsHeader rsHeader = searchRsHeader.getRsHeader();
		if (rsHeader == null)
		{
			rsHeader = new RsHeader();
			MessageStatus status = new MessageStatus();
			status.setOverallStatus(CommonConstants.EMPTY_STRING);
			rsHeader.setStatus(status);
			rsHeader.setUserExtension(CommonConstants.EMPTY_STRING); // do not set to Object
			rsHeader.setSessionId(CommonConstants.EMPTY_STRING);
			searchRsHeader.setRsHeader(rsHeader);
		}
		else
		{
			if (rsHeader.getStatus() == null)
			{
				MessageStatus status = new MessageStatus();
				status.setOverallStatus(CommonConstants.EMPTY_STRING);
				rsHeader.setStatus(status);
			}
			if (rsHeader.getUserExtension() == null)
			{
				rsHeader.setUserExtension(CommonConstants.EMPTY_STRING);
			}
			if (rsHeader.getSessionId() == null)
			{
				rsHeader.setSessionId(CommonConstants.EMPTY_STRING);
			}
		}
		UiControlRs uiControlRs = searchRsHeader.getUiControlRs();
		if (uiControlRs == null)
		{
			uiControlRs = new UiControlRs();
			uiControlRs.setNextScrn(CommonConstants.EMPTY_STRING);
			uiControlRs.setNextProcess(0);
			searchRsHeader.setUiControlRs(uiControlRs);
		}
		else
		{
			if (uiControlRs.getNextScrn() == null)
			{
				uiControlRs.setNextScrn(CommonConstants.EMPTY_STRING);
			}
			if (uiControlRs.getNextProcess() == null)
			{
				uiControlRs.setNextProcess(0);
			}
		}
	}

	/**
	 * Obtains the BF UserLocator from the supplied ScreenSet object
	 * 
	 * @param screenSet
	 *            A Screenset object
	 * @return A String containing the user locator
	 */
	public String getUserLocator(ScreenSet screenSet)
	{
		String sessionId = screenSet.getFhd().getFunctionInfo().getSessionId();
		return EquationFunctionContext.getContext().getUserLocator(sessionId);
	}

	/**
	 * Print service request header
	 * 
	 * @param serviceRqHeader
	 *            - the Service Request Header to be initialised
	 */
	public void printServiceRqHeader(ServiceRqHeader serviceRqHeader)
	{
		if (LOG.isDebugEnabled())
		{
			RqHeader rqHeader = serviceRqHeader.getRqHeader();
			LOG.debug("System id = " + rqHeader.getSystemId());
			LOG.debug("Unit id = " + rqHeader.getUnitId());
			LOG.debug("User id = " + rqHeader.getUserId());
			LOG.debug("Session id = " + rqHeader.getSessionId());
			LOG.debug("UI mode = " + serviceRqHeader.getUiControlRq().getUiMode());
			LOG.debug("Reference = " + serviceRqHeader.getReference());
			LOG.debug("Mode = " + serviceRqHeader.getServiceMode());
			LOG.debug("Option = " + serviceRqHeader.getOptionId());
			LOG.debug("Cur scrn = " + serviceRqHeader.getUiControlRq().getCurScrn());
		}
	}

	/**
	 * Call the default user exit of the screen set
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return true if anything has changed
	 * 
	 * @throws EQException
	 */
	public boolean callDefaultUserExit(ScreenSet screenSet, int scrnNo) throws EQException
	{
		Function function = screenSet.getPrimaryFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, true, null);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet);

		// call
		return callDefaultUserExit(function, functionData, bf_serviceHeader, bf_serviceData, scrnNo);
	}

	/**
	 * Call the default user exit of the screen set
	 * 
	 * @param function
	 *            - the function
	 * @param functionData
	 *            - the function data
	 * @param bf_serviceHeader
	 *            - the BF service header
	 * @param bf_serviceData
	 *            - the BF data
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return true if anything has changed
	 * 
	 * @throws EQException
	 */
	public boolean callDefaultUserExit(Function function, FunctionData functionData, ServiceRqHeader bf_serviceHeader,
					Object bf_serviceData, int scrnNo) throws EQException
	{
		// set the microflow name
		String microFlowName = getDefaultUserExitMicroflowName(function, function.getInputFieldSets().get(scrnNo));

		// print before data
		printBeforeImage("FunctionBankFusion.DefaultMode", functionData, bf_serviceData, bf_serviceHeader);

		// call
		BFToolbox bfToolbox = new BFToolbox();
		ReturnDataBF returnDataBF = bfToolbox.callDefaultUserExit(microFlowName, bf_serviceHeader, bf_serviceData);

		// transfer the details back to the function data
		boolean changed = loadToFunctionData(function, functionData, returnDataBF.getFunctionData(), true, null);

		// print after data
		printAfterImage("FunctionBankFusion.DefaultMode", functionData, returnDataBF.getFunctionData(), returnDataBF
						.getServiceRsHeader());

		// changed?
		return changed;
	}

	/**
	 * Call the validate user exit of the screen set
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return the list of messages and if data has been changed
	 * 
	 * @throws EQException
	 */
	public ReturnDataMFUserExit callValidateUserExit(ScreenSet screenSet, int scrnNo) throws EQException
	{
		Function function = screenSet.getPrimaryFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, false, null);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet);

		return callValidateUserExit(function, functionData, bf_serviceHeader, bf_serviceData, scrnNo);
	}

	/**
	 * Call the validate user exit of the screen set
	 * 
	 * @param function
	 *            - the function
	 * @param functionData
	 *            - the function data
	 * @param bf_serviceHeader
	 *            - the BF service header
	 * @param bf_serviceData
	 *            - the BF data
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return the list of messages and if data has been changed
	 * 
	 * @throws EQException
	 */
	public ReturnDataMFUserExit callValidateUserExit(Function function, FunctionData functionData,
					ServiceRqHeader bf_serviceHeader, Object bf_serviceData, int scrnNo) throws EQException
	{
		// set the microflow name
		String microFlowName = getValidateUserExitMicroflowName(function, function.getInputFieldSets().get(scrnNo));

		// print before data
		printBeforeImage("FunctionBankFusion.ValidateMode", functionData, bf_serviceData, bf_serviceHeader);

		// call
		BFToolbox bfToolbox = new BFToolbox();
		ReturnDataBF returnDataBF = bfToolbox.callValidateUserExit(microFlowName, bf_serviceHeader, bf_serviceData);

		// transfer the details back to the function data
		boolean changed = loadToFunctionData(function, functionData, returnDataBF.getFunctionData(), false, null);

		// print after data
		printAfterImage("FunctionBankFusion.ValidateMode", functionData, returnDataBF.getFunctionData(), returnDataBF
						.getServiceRsHeader());

		// return other details
		ReturnDataMFUserExit returnData = new ReturnDataMFUserExit();
		returnData.setChanged(changed);
		returnData.setMessages(getMessages(returnDataBF.getServiceRsHeader()));

		return returnData;
	}

	/**
	 * Call dummy BankFusion Microflow to wrap API process for External Input.
	 * 
	 * @param screenSet
	 *            - the screen set
	 * 
	 * @return number of next process
	 * 
	 * @throws EQException
	 */
	public FunctionMessages callDummyAPIMicroflow(String uiMode, String serviceMode, ScreenSet screenSet,
					FunctionData functionData, ConversionRules conversionRules) throws Exception
	{
		Function function = screenSet.getPrimaryFunction();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, false, conversionRules);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet);
		bf_serviceHeader.setServiceMode(serviceMode);
		bf_serviceHeader.getUiControlRq().setUiMode(uiMode);

		// Service
		if (function.chkXSDGeneric())
		{
			Object combinedData = getRequestCombinedType(function, bf_serviceHeader, bf_serviceData);
			if (combinedData != null)
			{
				bf_serviceHeader = null;
				bf_serviceData = combinedData;
			}
		}

		// call
		BFToolbox bfToolbox = new BFToolbox();
		ServiceRsHeader outputServiceHeader = bfToolbox.callDummyMicroflow(getUserLocator(screenSet),
						getServiceMicroflowName(function), bf_serviceHeader, bf_serviceData);

		return screenSet.getFhd().getFunctionMsgManager().getFunctionMessages();
	}

	/**
	 * Call dummy BankFusion Microflow to wrap update process.
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param ckey
	 *            - the control key
	 * @param reason
	 *            - the supervisor override decline reason
	 * @param loadFieldSet
	 *            - the load field set
	 * @param loadField
	 *            - the load field
	 * @param fhName
	 *            - the Function Handler name to identify whether the main or the popup
	 * 
	 * @return number of next process
	 * 
	 * @throws EQException
	 */
	public int callDummyUpdateMicroflow(ScreenSet screenSet, int ckey, String reason, String loadFieldSet, String loadField,
					String fhName, ConversionRules conversionRules) throws Exception
	{
		Function function = screenSet.getPrimaryFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, false, conversionRules);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet, ckey, "", loadField, loadFieldSet, "U", reason, "");

		// TODO: this is not the proper fix. When we redo the renaming of BF artefacts, add a new field in the UIControl
		// also change FunctionHandlerActivityStep to retrieve from this new uiControl field
		bf_serviceHeader.setReference(fhName);

		// Service
		if (function.chkXSDGeneric())
		{
			Object combinedData = getRequestCombinedType(function, bf_serviceHeader, bf_serviceData);
			if (combinedData != null)
			{
				bf_serviceHeader = null;
				bf_serviceData = combinedData;
			}
		}

		// call
		BFToolbox bfToolbox = new BFToolbox();
		ServiceRsHeader outputServiceHeader = bfToolbox.callDummyMicroflow(getUserLocator(screenSet),
						getServiceMicroflowName(function), bf_serviceHeader, bf_serviceData);

		return outputServiceHeader.getUiControlRs().getNextProcess();
	}

	/**
	 * Call dummy BankFusion Microflow to wrap validate process.
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param ckey
	 *            - the control key
	 * @param fldValidate
	 *            - the field validate
	 * @param transactionId
	 *            - the transaction id
	 * @param fhName
	 *            - the Function Handler name to identify whether the main or the popup
	 * 
	 * @throws EQException
	 */
	public void callDummyValidateMicroflow(ScreenSet screenSet, int ckey, String fldValidate, String transactionId, String fhName,
					ConversionRules conversionRules) throws Exception
	{
		Function function = screenSet.getPrimaryFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, true, conversionRules);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet, ckey, fldValidate, "", "", "V", "", transactionId);

		// TODO: this is not the proper fix. When we redo the renaming of BF artefacts, add a new field in the UIControl
		// also change FunctionHandlerActivityStep to retrieve from this new uiControl field
		bf_serviceHeader.setReference(fhName);

		// Service
		if (function.chkXSDGeneric())
		{
			Object combinedData = getRequestCombinedType(function, bf_serviceHeader, bf_serviceData);
			if (combinedData != null)
			{
				bf_serviceHeader = null;
				bf_serviceData = combinedData;
			}
		}

		// call
		BFToolbox bfToolbox = new BFToolbox();
		bfToolbox
						.callDummyMicroflow(getUserLocator(screenSet), getServiceMicroflowName(function), bf_serviceHeader,
										bf_serviceData);
	}

	/**
	 * Call the validate user exit of the screen set
	 * 
	 * @param screenSet
	 * 
	 *            - the screen set
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return the list of messages
	 * 
	 * @throws EQException
	 */
	public ReturnDataMFUserExit callPostUpdateUserExit(ScreenSet screenSet) throws EQException
	{
		Function function = screenSet.getPrimaryFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, false, null);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet);

		return callPostUpdateUserExit(function, functionData, bf_serviceHeader, bf_serviceData);
	}

	/**
	 * Call the validate user exit of the screen set
	 * 
	 * @param function
	 *            - the function
	 * @param functionData
	 *            - the function data
	 * @param bf_serviceHeader
	 *            - the BF service header
	 * @param bf_serviceData
	 *            - the BF data
	 * 
	 * @return the list of messages
	 * 
	 * @throws EQException
	 */
	public ReturnDataMFUserExit callPostUpdateUserExit(Function function, FunctionData functionData,
					ServiceRqHeader bf_serviceHeader, Object bf_serviceData) throws EQException
	{
		// set the microflow name
		String microFlowName = getUpdateUserExitMicroflowName(function);

		// print before data
		printBeforeImage("FunctionBankFusion.PostUpdateMode", functionData, bf_serviceData, bf_serviceHeader);

		// call
		BFToolbox bfToolbox = new BFToolbox();
		ReturnDataBF returnDataBF = bfToolbox.callUpdateUserExit(microFlowName, bf_serviceHeader, bf_serviceData);

		// return other details
		ReturnDataMFUserExit returnData = new ReturnDataMFUserExit();
		returnData.setMessages(getMessages(returnDataBF.getServiceRsHeader()));

		return returnData;
	}

	/**
	 * Call the previous screen user exit
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return the next screen to display when paging up
	 * 
	 * @throws EQException
	 */
	public int callPrevScrnUserExit(ScreenSet screenSet, int scrnNo) throws EQException
	{
		Function function = screenSet.getPrimaryFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, true, null);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet);

		// call
		String scrnId = callPrevScrnUserExit(function, screenSet.getLayout(), functionData, bf_serviceHeader, bf_serviceData,
						scrnNo);
		return screenSet.getScrnNoFromId(scrnId);
	}

	/**
	 * Call the previous screen user exit
	 * 
	 * @param function
	 *            - the function
	 * @param layout
	 *            - the layout
	 * @param functionData
	 *            - the function data
	 * @param bf_serviceHeader
	 *            - the BF service header
	 * @param bf_serviceData
	 *            - the BF data
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return the screen set id to be displayed when paging up
	 * 
	 * @throws EQException
	 */
	public String callPrevScrnUserExit(Function function, Layout layout, FunctionData functionData,
					ServiceRqHeader bf_serviceHeader, Object bf_serviceData, int scrnNo) throws EQException
	{
		// set the microflow name
		String microFlowName = getPrevScrnUserExitMicroflowName(function, layout);

		// print before data
		printBeforeImage("FunctionBankFusion.PreviousScreenMode", functionData, bf_serviceData, bf_serviceHeader);

		// call
		BFToolbox bfToolbox = new BFToolbox();
		String scrnId = bfToolbox.callPrevScrnUserExit(microFlowName, bf_serviceHeader, bf_serviceData);
		return scrnId;
	}

	/**
	 * Call the next screen user exit
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return the next screen to display when paging down
	 * 
	 * @throws EQException
	 */
	public int callNextScrnUserExit(ScreenSet screenSet, int scrnNo) throws EQException
	{
		Function function = screenSet.getPrimaryFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// setup the BF function data
		Object bf_serviceData = getBankFusionDataType(screenSet.getFhd().getEquationUser().getSession(), screenSet
						.getFunctionAdaptor(), function, functionData, true, null);

		// setup the BF function header
		ServiceRqHeader bf_serviceHeader = getFunctionBFHeader(screenSet);

		// call
		String scrnId = callNextScrnUserExit(function, screenSet.getLayout(), functionData, bf_serviceHeader, bf_serviceData,
						scrnNo);
		return screenSet.getScrnNoFromId(scrnId);
	}

	/**
	 * Call the next screen user exit
	 * 
	 * @param screenSet
	 *            - the screen set
	 * @param scrnNo
	 *            - the screen number
	 * 
	 * @return the next screen to display when paging down
	 * 
	 * @throws EQException
	 */
	public String callNextScrnUserExit(Function function, Layout layout, FunctionData functionData,
					ServiceRqHeader bf_serviceHeader, Object bf_serviceData, int scrnNo) throws EQException
	{
		// set the microflow name
		String microFlowName = getNextScrnUserExitMicroflowName(function, layout);

		// print before data
		printBeforeImage("FunctionBankFusion.NextScreenMode", functionData, bf_serviceData, bf_serviceHeader);

		// call
		BFToolbox bfToolbox = new BFToolbox();
		String scrnId = bfToolbox.callNextScrnUserExit(microFlowName, bf_serviceHeader, bf_serviceData);
		return scrnId;
	}

	/**
	 * Return the list of messages from the Service Response Header
	 * 
	 * @param serviceRsHeader
	 *            - the function response header
	 * 
	 * @return the list of messages
	 */
	public List<UserExitMessage> getMessages(ServiceRsHeader serviceRsHeader)
	{
		ArrayList<UserExitMessage> returnMessages = new ArrayList<UserExitMessage>();
		MessageStatus msgStatus = serviceRsHeader.getRsHeader().getStatus();
		for (int i = 0; i < msgStatus.getEqMessagesCount(); i++)
		{
			UserExitMessage returnMessage = new UserExitMessage(msgStatus.getEqMessages(i).getFieldLocation().getFieldId(0),
							msgStatus.getEqMessages(i).getFormattedMessage());
			returnMessages.add(returnMessage);
		}

		// return the list
		return returnMessages;
	}

	/**
	 * Print the before image
	 * 
	 * @param functionData
	 *            - the function data
	 * @param bf_functionData
	 *            - the bf function data
	 */
	private void printBeforeImage(String resourceModeKey, FunctionData functionData, Object bf_serviceData,
					ServiceRqHeader bf_serviceHeader)
	{
		if (functionData != null && LOG.isDebugEnabled())
		{
			LOG.debug(LanguageResources.getFormattedString("FunctionBankFusion.Debug.FunctionData.Before", new String[] {
							LanguageResources.getString("FunctionBankFusion.Name"), LanguageResources.getString(resourceModeKey) })
							+ "\n" + functionData.toString() + "\n");
		}

		if (bf_serviceData != null)
		{
			// LOG.debug(LanguageResources.getFormattedString("FunctionBankFusion.Debug.BFFunctionData.Before", new String[] {
			// LanguageResources.getString("FunctionBankFusion.Name"), LanguageResources.getString(resourceModeKey) })
			// + "\n" + bf_functionData.toString() + "\n");
		}

		if (bf_serviceHeader != null && LOG.isDebugEnabled())
		{
			LOG.debug(LanguageResources.getFormattedString("FunctionBankFusion.Debug.FunctionHeader.Before", new String[] {
							LanguageResources.getString("FunctionBankFusion.Name"), LanguageResources.getString(resourceModeKey) })
							+ "\n" + bf_serviceHeader.toString() + "\n");
		}
	}

	/**
	 * Print the after image
	 * 
	 * @param functionData
	 *            - the function data
	 * @param bf_functionData
	 *            - the bf function data
	 */
	private void printAfterImage(String resourceModeKey, FunctionData functionData, Object bf_serviceData,
					ServiceRsHeader bf_serviceHeader)
	{
		if (functionData != null && LOG.isDebugEnabled())
		{
			LOG.debug(LanguageResources.getFormattedString("FunctionBankFusion.Debug.FunctionData.After", new String[] {
							LanguageResources.getString("FunctionBankFusion.Name"), LanguageResources.getString(resourceModeKey) })
							+ "\n" + functionData.toString() + "\n");
		}

		if (bf_serviceData != null)
		{
			// LOG.debug(LanguageResources.getFormattedString("FunctionBankFusion.Debug.BFFunctionData.After", new String[] {
			// LanguageResources.getString("FunctionBankFusion.Name"), LanguageResources.getString(resourceModeKey) })
			// + "\n" + bf_functionData.toString() + "\n");
		}

		if (bf_serviceHeader != null && LOG.isDebugEnabled())
		{
			LOG.debug(LanguageResources.getFormattedString("FunctionBankFusion.Debug.FunctionHeader.After", new String[] {
							LanguageResources.getString("FunctionBankFusion.Name"), LanguageResources.getString(resourceModeKey) })
							+ "\n" + bf_serviceHeader.toString() + "\n");
		}
	}

	/**
	 * Logs a user off BankFusion
	 * 
	 * @param userLocator
	 *            A UserLocator String to logoff. May be null, in which case no work is done
	 */
	public static void logoff(String userLocator)
	{
		if (userLocator != null)
		{
			try
			{
				BFToolbox.logoff(userLocator);
			}
			catch (EQException e)
			{
				LOG.error(e);
			}
		}
	}

	/**
	 * Code to extract and decrypt the iSeries profile token obtained from BankFusion
	 * <p>
	 * The following requires that the BankFusion encryption service is running
	 * 
	 * @param as400Token
	 *            A BankFusion wrapper class which holds the encrypted profile token
	 * @return An String containing the decrypted token in Base64
	 * 
	 * @throws Exception
	 */
	public String decryptTokenToBase64String(AS400Token as400Token) throws Exception
	{
		final String AS400_TOKEN_KEYSTORE_STRING = "AS400_ENCRYPTION_KEYALIAS";

		// reading the KEYALIAS property from bankfusion.properties file
		String dataIdentifier = BankFusionPropertySupport.getProperty(BankFusionPropertySupport.BANKFUSION_PROPERTY_FILE_NAME,
						CommonConstants.AS400_ENCRYPTION_KEYALIAS, AS400_TOKEN_KEYSTORE_STRING);
		EncryptionService encryptionService = (EncryptionService) ServiceManagerFactory.getInstance().getServiceManager()
						.getServiceForName(ServiceManager.ENCRYPTION_SERVICE);
		AbstractBankFusionEncryptionService bankFusionEncryptionService = encryptionService.getBankFusionEncryptionService();

		EncryptedData encryptedData = as400Token.getEncryptedData();
		LOG.info("Encrypted profile token – [" + as400Token.getTokenId() + "]");

		EncryptedData decryptedData = bankFusionEncryptionService.decryptAS400Token(dataIdentifier, BankFusionThreadLocal
						.getUserSession().getUserId(), encryptedData.getCipherText(), encryptedData.getEncodedCipherParameters());

		String decryptedAS400TokenID = decryptedData.getDecryptedPassword();
		LOG.info("Decrypted profile token – [" + decryptedAS400TokenID + "]");
		return decryptedAS400TokenID;
	}

	/**
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 */
	public EqMessage[] rtvMessagesAsMessageArray(List<FunctionMessage> messages, ScreenSetHandler screenSetHandler)
	{
		EqMessage[] eqMsgs = new EqMessage[messages.size()];
		for (int i = 0; i < messages.size(); i++)
		{
			EqMessage message = new EqMessage();

			FieldLocation fieldLocation = new FieldLocation();
			message.setFieldLocation(fieldLocation);

			BranchLimitParameters branchLimit = new BranchLimitParameters();
			message.setBranchLimit(branchLimit);
			BranchKeys branchKeys = new BranchKeys();
			branchLimit.setAffectedBranch(branchKeys);

			ServiceStack serviceStack = new ServiceStack();
			message.setServiceStack(serviceStack);

			branchLimit.setLocalCurrencyAmount(messages.get(i).getAmount());
			branchKeys.setBranchMnemonic(messages.get(i).getBranch());
			branchLimit.setDebitCredit(messages.get(i).getDebitCredit());

			fieldLocation.addFieldId(messages.get(i).getFieldName());
			message.setFirstLevelText(messages.get(i).getFirstLevelText());
			message.setFormattedMessage(messages.get(i).rtvFormattedText());
			message.setEqMessageId(messages.get(i).getEqMessage().getMessageID());
			message.setEqMessageParameter1(messages.get(i).getEqMessage().getMessageParameter1());
			message.setEqMessageParameter2(messages.get(i).getEqMessage().getMessageParameter2());
			message.setEqMessageParameter3(messages.get(i).getEqMessage().getMessageParameter3());

			if (screenSetHandler != null && !screenSetHandler.getScreenSets().isEmpty())
			{
				int screenSetId = messages.get(i).getScreenSetId();
				if (screenSetId >= screenSetHandler.getScreenSets().size())
				{
					screenSetId = screenSetHandler.getScreenSets().size() - 1;
				}
				ScreenSet screenSet = screenSetHandler.getScreenSets().get(screenSetId);
				String setId = screenSet.getOptionId();
				String fieldSet = screenSet.getFieldSetId(messages.get(i).getScrnNo());
				fieldLocation.setSetId(setId);
				fieldLocation.setFieldSet(fieldSet);
			}
			message.setSecondLevelText(messages.get(i).getSecondLevelText());
			fieldLocation.setRowSequence(messages.get(i).getSequence());

			// Severity string should be 00, 10 or 20
			String severity = Toolbox.padAtFront(Integer.toString(messages.get(i).rtvMsgSev()), "0", 2);
			if (severity.length() > 2)
			{
				severity = severity.substring(0, 2);
			}
			message.setEqMessageSeverity(severity);

			eqMsgs[i] = message;
		}

		return eqMsgs;
	}

	/**
	 * Check BankFusion properties match what is expected in EQ4
	 */
	public static void checkBankFusionProperties()
	{
		final String JTA = "bankfusion.JTAEnabled";

		// reading the JTA property from bankfusion.properties file
		String jtaProperty = BankFusionPropertySupport.getProperty(BankFusionPropertySupport.BANKFUSION_PROPERTY_FILE_NAME, JTA,
						"false");
		if ((jtaProperty.equals("true") && !EquationCommonContext.getContext().isXAUsed())
						|| (jtaProperty.equals("false") && EquationCommonContext.getContext().isXAUsed()))
		{
			String error = "bankfusion.properties JTAEnabled inconsistent with equationConfiguration.properties eq.xa.supported";
			LOG.error(error);
			throw new RuntimeException(error);
		}
	}

	/**
	 * Create an empty BankFusion message
	 * 
	 * @return an empty BankFusion message
	 */
	public EqMessage getEmptyMessage()
	{
		EqMessage message = new EqMessage();

		FieldLocation fieldLocation = new FieldLocation();
		fieldLocation.addFieldId("");
		message.setFieldLocation(fieldLocation);

		BranchKeys branchKeys = new BranchKeys();
		BranchLimitParameters branchLimit = new BranchLimitParameters();
		branchLimit.setAffectedBranch(branchKeys);
		branchLimit.setLocalCurrencyAmount("");
		branchLimit.setDebitCredit("");
		message.setBranchLimit(branchLimit);
		branchKeys.setBranchMnemonic("");

		ServiceStack serviceStack = new ServiceStack();
		message.setServiceStack(serviceStack);

		message.setFirstLevelText("");
		message.setFormattedMessage("");
		message.setEqMessageId("");
		message.setEqMessageParameter1("");
		message.setEqMessageParameter2("");
		message.setEqMessageParameter3("");
		message.setSecondLevelText("");
		message.setEqMessageSeverity("");

		return message;
	}

	/**
	 * Load a BF property
	 * 
	 * @param propertyName
	 *            - the property name
	 * @param defaultValue
	 *            - the default value (if not found??)
	 * @return the property value
	 */
	public static String loadBFProperty(String propertyName, String defaultValue)
	{
		return BankFusionPropertySupport.getProperty(BankFusionPropertySupport.BANKFUSION_PROPERTY_FILE_NAME, propertyName,
						defaultValue);
	}

	/**
	 * Determine if BF offline is enabled
	 * 
	 * @return true if BF offline is enabled
	 */
	public static boolean isOfflineEnabled()
	{
		String offlineEnabled = loadBFProperty("OfflineEnabled", "false");
		return offlineEnabled.equals("true");
	}

	/**
	 * Return the group id
	 * 
	 * @param function
	 *            the Function
	 * @param repeatingGroup
	 *            the group id
	 * 
	 * @return the complex type name
	 */
	public static String getComplexTypeNameRepeatingGroup(Function function, String repeatingGroup)
	{
		return getServiceMicroflowName(function) + "_" + repeatingGroup + BF_LIST_SUFFIX;
	}

	/**
	 * Generates the name of the XSD file associated with this service (enhanced XSD naming convention)
	 * 
	 * @param service
	 *            The EQ4 service to generate the operation for
	 * 
	 * @return a String containing the name of the XSD file
	 */
	public static String getEnhancedServiceName(Function service)
	{
		return Toolbox.textToCamelCase(service.getLabel()) + service.getId();
	}

	/**
	 * Return the group id's request complex type name (enhanced XSD naming convention)
	 * 
	 * @param function
	 *            the Function
	 * @param groupLabel
	 *            the group label
	 * 
	 * @return the complex type name
	 */
	public static String getEnhancedComplexTypeNameRequest(Function function, String groupLabel)
	{
		return Toolbox.textToCamelCase(groupLabel);
	}

	/**
	 * Return the group id's response complex type name (enhanced XSD naming convention)
	 * 
	 * @param function
	 *            the Function
	 * @param groupLabel
	 *            the group label
	 * 
	 * @return the complex type name
	 */
	public static String getEnhancedComplexTypeNameResponse(Function function, String groupLabel)
	{
		return Toolbox.textToCamelCase(groupLabel);
	}

	/**
	 * Return the Request complex type name (enhanced XSD naming convention)
	 * 
	 * @param function
	 *            - the Function
	 * 
	 * @return the Request complex type name
	 */
	public static String getEnhancedServiceNameRequest(Function function)
	{
		return Toolbox.textToCamelCase(function.getLabel()) + REQUEST_SUFFIX;
	}

	/**
	 * Return the Response complex type name (enhanced XSD naming convention)
	 * 
	 * @param function
	 *            - the Function
	 * 
	 * @return the Response complex type name
	 */
	public static String getEnhancedServiceNameResponse(Function function)
	{
		return Toolbox.textToCamelCase(function.getLabel()) + RESPONSE_SUFFIX;
	}

	/**
	 * Return the XSD name (enhanced XSD naming convention)
	 * 
	 * @param label
	 *            - the label
	 * 
	 * @return the XSD name
	 */
	public static String getEnhancedFieldName(String label)
	{
		return Toolbox.textToCamelCase(label);
	}

	/**
	 * Return the conversion parameters from an enhanced BF complex type
	 * 
	 * @param bf_functionData
	 *            - the BF complex type data
	 * @param functionData
	 *            - the function data
	 * @param inputField
	 *            - the Input field
	 * @param conversionRule
	 *            - the conversion rule
	 * @param index
	 *            - index for the repeating list
	 * 
	 * @return the conversion parameter
	 */
	public String[] getConversionParameterFromEnhancedComplexType(Object bf_functionData, FunctionData functionData,
					InputField inputField, ConversionRules conversionRules, int index)
	{
		String[] parameters = new String[] { null };

		// linked currency parameter
		if (conversionRules.getAmountFormat().equals(ConversionRules.AMOUNT_MAJOR_CURRENCY))
		{
			String linkedCcy = inputField.getCcyLinkedField().trim();
			if (linkedCcy.length() > 0)
			{
				parameters[ConversionRules.PARAMETER_LINKED_CCY] = getFieldValue(bf_functionData, conversionRules
								.getPath(linkedCcy), index);

				// Null, then try to load from Function Data in case it has default values
				if (parameters[ConversionRules.PARAMETER_LINKED_CCY] == null)
				{
					parameters[ConversionRules.PARAMETER_LINKED_CCY] = functionData.rtvFieldDbValue(linkedCcy);
				}
			}
		}

		// return all parameters
		return parameters;
	}

	/**
	 * Return the conversion parameters from a BF complex type
	 * 
	 * @param bf_functionData
	 *            - the BF complex type data
	 * @param functionData
	 *            - the function data
	 * @param inputField
	 *            - the Input field
	 * @param conversionRule
	 *            - the conversion rule
	 * @param rowObject
	 *            - this is the current row object (if the input field is a repeating field)
	 * 
	 * @return the conversion parameter
	 */
	public String[] getConversionParameterFromComplexType(Object bf_functionData, FunctionData functionData, InputField inputField,
					ConversionRules conversionRules, Object rowObject, Function function)
	{
		String[] parameters = new String[] { null };

		// linked currency parameter
		if (conversionRules.getAmountFormat().equals(ConversionRules.AMOUNT_MAJOR_CURRENCY))
		{
			String linkedCcy = inputField.getCcyLinkedField().trim();
			if (linkedCcy.length() > 0)
			{
				InputField linkCcy = function.rtvInputField(linkedCcy);

				// non repeating field
				if (linkCcy.getRepeatingGroup().length() == 0)
				{
					parameters[ConversionRules.PARAMETER_LINKED_CCY] = getFieldValue(bf_functionData, linkCcy);
				}
				else
				{
					parameters[ConversionRules.PARAMETER_LINKED_CCY] = getFieldValue(rowObject, linkCcy);
				}

				// Null, then try to load from Function Data in case it has default values
				if (parameters[ConversionRules.PARAMETER_LINKED_CCY] == null)
				{
					parameters[ConversionRules.PARAMETER_LINKED_CCY] = functionData.rtvFieldDbValue(linkedCcy);
				}
			}
		}

		// return all parameters
		return parameters;
	}

	/**
	 * Return the conversion parameter
	 * 
	 * @param functionData
	 *            - the Function data
	 * @param inputField
	 *            - the Input field
	 * 
	 * @return the conversion parameter
	 */
	public String[] getConversionParameterFromFunctionData(FunctionData functionData, InputField inputField,
					ConversionRules conversionRules)
	{
		String[] parameters = new String[] { null };

		// linked currency parameter
		if (conversionRules.getAmountFormat().equals(ConversionRules.AMOUNT_MAJOR_CURRENCY))
		{
			if (inputField.getCcyLinkedField().trim().length() > 0)
			{
				parameters[ConversionRules.PARAMETER_LINKED_CCY] = functionData.rtvFieldDbValue(inputField.getCcyLinkedField());
			}
		}

		// return all parameters
		return parameters;
	}

	/**
	 * Create an instance of a complex type and assign it to the parent
	 * 
	 * @param bf_functionData
	 *            - the parent complex type
	 * @param webserviceName
	 *            - the web service name (for getter/setter)
	 * @param complexType
	 *            - the complex type (for the actual class type)
	 * @param function
	 *            - the Function bean where the complex type is defined
	 * @param conversionRules
	 *            - the Conversion rules
	 * 
	 * @return the created object
	 */
	public Object createAndSetObject(Object bf_functionData, String webserviceName, String complexType, Function function,
					ConversionRules conversionRules)
	{
		// create the object
		try
		{
			String fullClassName = ComplexTypeToolbox.getComplexTypePackageEnhancedXsd(function, complexType);
			Object objectValue = conversionRules.getBankfusionDataType(fullClassName);

			// set
			String methodName = "set" + webserviceName;
			setFieldObject(bf_functionData, methodName, objectValue);

			return objectValue;
		}
		catch (EQException e)
		{
			LOG.warn(e);
			return null;
		}
	}

	/**
	 * Return the field value of the field name of a complex data type using reflection. If the getter method for the field does not
	 * exist an empty string will be returned.
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param methodName
	 *            - the method name
	 * 
	 * @return the field value
	 */
	public String getFieldValueEnhanced(Object bf_functionData, String methodName)
	{
		Object fieldObject = getFieldObject(bf_functionData, methodName);
		if (fieldObject == null)
		{
			return null;
		}

		String fieldValue = "";

		// Boolean type - Y / N
		if (fieldObject instanceof Boolean)
		{
			if (fieldObject == Boolean.TRUE)
			{
				fieldValue = EqDataType.YES;
			}
			else
			{
				fieldValue = EqDataType.NO;

			}
		}

		// Big Decimal type - Retrieve plain number, not scientific notation
		else if (fieldObject instanceof BigDecimal)
		{
			BigDecimal bd = (BigDecimal) fieldObject;
			fieldValue = bd.toPlainString();
		}

		// Others
		else
		{
			fieldValue = fieldObject.toString();
		}

		// return the field value
		return fieldValue;
	}

	/**
	 * Set the field value of the field name of a complex data type
	 * 
	 * @param bf_functionData
	 *            - the complex data type
	 * @param methodName
	 *            - the method name
	 * @param fieldValue
	 *            - the value
	 * @param parameterClass
	 *            - the class type in the XSD
	 */
	public void setFieldObjectEnhanced(Object bf_functionData, String methodName, String fieldValue, Class parameterClass)
	{
		Object fieldValueObject = null;

		try
		{
			// Boolean?
			if (parameterClass == Boolean.class)
			{
				fieldValueObject = fieldValue.equals(EqDataType.YES);
			}

			// BigDecimal
			else if (parameterClass == BigDecimal.class)
			{
				if (fieldValue.trim().length() == 0)
				{
					fieldValue = "0";
				}

				fieldValueObject = new BigDecimal(fieldValue);
			}

			// Long
			else if (parameterClass == Long.class)
			{
				if (fieldValue.trim().length() == 0)
				{
					fieldValue = "0";
				}
				fieldValueObject = new Long(fieldValue);
			}

			// Integer
			else if (parameterClass == Integer.class)
			{
				if (fieldValue.trim().length() == 0)
				{
					fieldValue = "0";
				}
				fieldValueObject = new Integer(fieldValue);
			}

			// String
			else
			{
				fieldValueObject = fieldValue;
			}
		}
		catch (Exception e)
		{
			LOG.error(e.getMessage() + "[" + methodName + "]" + "[" + fieldValue + "]");
		}

		// Set the value
		if (fieldValueObject != null)
		{
			setFieldObject(bf_functionData, methodName, fieldValueObject);
		}
	}

	/**
	 * Return the request combined type
	 * 
	 * @param service
	 *            - the function
	 * @param serviceHeader
	 *            - the service request header
	 * @param serviceData
	 *            - the data
	 */
	public Object getRequestCombinedType(Function service, ServiceRqHeader serviceHeader, Object serviceData) throws EQException
	{
		String combinedRequestType = ComplexTypeToolbox.getComplexTypePackage(service) + "."
						+ XSDToolbox.getCombinedComplexTypeRequestName(service);
		Object combinedData = getBankFusionDataType(null, combinedRequestType);

		if (combinedData != null)
		{
			setFieldObject(combinedData, "set" + XSDToolbox.SERVICE_HEADER_TAG, serviceHeader);
			setFieldObject(combinedData, "set" + XSDToolbox.SERVICE_DATA_TAG, serviceData);
		}

		return combinedData;
	}

}