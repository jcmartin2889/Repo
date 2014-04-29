package com.misys.equation.function.runtime;

import java.lang.reflect.Method;

import com.misys.equation.common.access.EquationDataStructure;
import com.misys.equation.common.access.EquationFieldDefinition;
import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.tools.ComplexTypeToolbox;
import com.misys.equation.function.tools.FunctionToolbox;

public class FunctionBankFusionSrv extends FunctionBankFusion
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionBankFusionSrv.java 17190 2013-09-03 11:49:59Z Lima12 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(FunctionBankFusionSrv.class);

	/** Default Equation application */
	public static final String EQUATION_DEFAULT_APP = "EQ";

	/** Default Equation module */
	public static final String EQUATION_DEFAULT_MODULE = "CMN";

	/** EFC Row fields */
	private final String[] efcRowInputFelds = new String[] { "OPT", "MSGSTS", "CALA", "CHGA", "COAD" };

	/**
	 * Return and set the given CRM Function Data based on the content of the specified complex data type
	 * 
	 * @param function
	 *            - the CRM Function
	 * @param screenSet
	 *            - the CRM screen set
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 */
	public void loadToCRMFunctionData(EquationStandardSession session, ScreenSet screenSet, Object bf_functionData,
					boolean bInputValue)
	{
		Function function = screenSet.getFunction();
		FunctionData functionData = screenSet.getFunctionData();

		// retrieve CURDATA
		InputField inputField = function.rtvInputField("CURDATA");
		if (inputField == null)
		{
			return;
		}

		String fieldValue = getFieldValue(bf_functionData, getFieldNameUsingInputFieldId(inputField));
		changeFieldData(functionData, "CURDATA", fieldValue, bInputValue);
		int curData = Toolbox.parseInt(fieldValue, 0);

		if (curData > 0)
		{
			// RA Repeating Data
			String repeatGroup = "RA";
			int rows = getRowCount(bf_functionData, repeatGroup);

			for (int index = 0; index < rows; index++)
			{
				Object rowObject = getRow(bf_functionData, repeatGroup, index);

				// retrieve the index
				String indexValue = getFieldValue(rowObject, repeatGroup + "INDEX_" + repeatGroup.toLowerCase() + "index");
				if (indexValue == null || indexValue.length() == 0)
				{
					continue;
				}

				insertFieldData(functionData, rowObject, function, "APIID", indexValue);
				insertFieldData(functionData, rowObject, function, "APILBL", indexValue);
				insertFieldData(functionData, rowObject, function, "APIDSC", indexValue);
				insertFieldData(functionData, rowObject, function, "REF", indexValue);
				insertFieldData(functionData, rowObject, function, "CUR", indexValue);
				insertFieldData(functionData, rowObject, function, "AMT", indexValue);
				insertFieldData(functionData, rowObject, function, "MSGSTS", indexValue);

			}

			// RB Repeating Data
			repeatGroup = "RB";
			rows = getRowCount(bf_functionData, repeatGroup);

			for (int index = 0; index < rows; index++)
			{
				Object rowObject = getRow(bf_functionData, repeatGroup, index);

				// retrieve the index
				String indexValue = getFieldValue(rowObject, repeatGroup + "INDEX_" + repeatGroup.toLowerCase() + "index");
				if (indexValue == null || indexValue.length() == 0)
				{
					continue;
				}

				insertFieldData(functionData, rowObject, function, "CUS", indexValue);
				insertFieldData(functionData, rowObject, function, "CLC", indexValue);
				insertFieldData(functionData, rowObject, function, "GRP", indexValue);
				insertFieldData(functionData, rowObject, function, "CNA", indexValue);
				insertFieldData(functionData, rowObject, function, "LC", indexValue);
				insertFieldData(functionData, rowObject, function, "CCY", indexValue);
				insertFieldData(functionData, rowObject, function, "RAM", indexValue);
				insertFieldData(functionData, rowObject, function, "LAM", indexValue);
				insertFieldData(functionData, rowObject, function, "AAM", indexValue);
				insertFieldData(functionData, rowObject, function, "XPD", indexValue);
				insertFieldData(functionData, rowObject, function, "ERR", indexValue);
				insertFieldData(functionData, rowObject, function, "CNA1", indexValue);
				insertFieldData(functionData, rowObject, function, "INTH", indexValue);

			}

			ScreenSetCRM.updateFieldOutputValue(session, functionData);
		}

	}

	/**
	 * Return and set the given EFC Function Data based on the content of the specified complex data type
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param screenSet
	 *            - the EFC screen set
	 * @param bf_functionData
	 *            - the source EFC complex data type
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 */
	public void loadToEFCFunctionData(EquationStandardSession session, ScreenSet screenSet, Object bf_functionData,
					String optionId, boolean bInputValue) throws EQException
	{
		Function function = screenSet.getFunction();
		FunctionData functionData = screenSet.getFunctionData();

		for (int i = 0; i < function.getInputFieldSets().size(); i++)
		{
			InputFieldSet inputFieldSet = function.getInputFieldSets().get(i);
			for (int j = 0; j < inputFieldSet.getInputFields().size(); j++)
			{
				InputField inputField = inputFieldSet.getInputFields().get(j);
				String fieldName = inputField.getId();

				if (!Field.isRepeating(inputField))
				{

					// check if this is used as a row input field
					boolean isRowInputField = false;
					for (String rowInputField : efcRowInputFelds)
					{
						if (rowInputField.equals(inputField.getId()))
						{
							isRowInputField = true;
							break;
						}
					}
					if (isRowInputField)
					{
						continue;
					}

					// retrieve the field value
					String fieldValue = getFieldValue(bf_functionData, getFieldNameUsingInputFieldId(inputField));

					// check if the value will be updated
					boolean changed = functionData.chkFieldChange(fieldName, fieldValue, bInputValue);

					// if there is no changed, then there is no need to update
					if (changed)
					{
						functionData.chgInputOrDBField(fieldName, fieldValue, bInputValue);
					}
				}
			}
		}

		// HZU641 and HZU642 data
		EquationStandardListEnquiry chargeEnquiry = new EquationStandardListEnquiry("U64DER", session);
		EquationDataStructure eqDs = chargeEnquiry.getHzDSData().getEqDS();
		// Set the field data
		setFieldData(eqDs, functionData, bf_functionData, "");
		((ScreenSetAC2) screenSet).editData(functionData, session);

		// GSC621 and GZC621 data
		String repeatGroup = "RA";
		int rows = getRowCount(bf_functionData, repeatGroup);
		if (rows > 0)
		{
			EquationStandardTransaction transaction = new EquationStandardTransaction("C62ARRACG", session, 1000);
			eqDs = transaction.getGsDSData().getEqDS();

			for (int index = 0; index < rows; index++)
			{

				Object rowObject = getRow(bf_functionData, repeatGroup, index);
				if (rowObject == null)
				{
					continue;
				}

				String indexValue = FunctionToolbox.UNDERSCORE + (index + 1);
				String fieldValue = "";

				// Set the field data
				setFieldData(eqDs, functionData, rowObject, indexValue);

				for (String rowInputField : efcRowInputFelds)
				{
					FieldData fieldDataDef = functionData.getFieldDatas().get(rowInputField);
					if (fieldDataDef != null)
					{
						fieldValue = getFieldValue(rowObject, fieldDataDef.getFieldName() + FunctionToolbox.UNDERSCORE
										+ Toolbox.textToCamelCase(fieldDataDef.getFieldName()));
						functionData.insertFieldData(fieldDataDef.getFieldName() + indexValue, fieldDataDef, fieldValue, false);
					}
				}

				((ScreenSetAC2) screenSet).editData(functionData, session, optionId, (index + 1));
			}
		}

		((ScreenSetAC2) screenSet).setupChargeKeyBasedOnHZ();

	}
	/**
	 * Set the field data
	 * 
	 * @param eqDs
	 *            - the Equation Data Structure
	 * @param functionData
	 *            - the function data
	 * @param bf_functionData
	 *            - the complex data type
	 */
	public void setFieldData(EquationDataStructure eqDs, FunctionData functionData, Object bf_functionData, String indexValue)
	{
		for (String fieldName : eqDs.getFieldNames())
		{
			FieldData fieldDataDef = functionData.getFieldDatas().get(fieldName);
			if (fieldDataDef == null)
			{
				EquationFieldDefinition fd = eqDs.getFieldDefinition(fieldName);
				if (fd != null)
				{
					fieldDataDef = new FieldData(fieldName, fd.getFieldDataTypeString(), fd.getFieldLength(), fd.getFieldDecimal());
					fieldDataDef.setUpperCase(true);
				}
			}

			if (fieldDataDef != null)
			{
				String fieldValue = getFieldValue(bf_functionData, fieldDataDef.getFieldName() + FunctionToolbox.UNDERSCORE
								+ Toolbox.textToCamelCase(fieldDataDef.getFieldName()));

				functionData.insertFieldData(fieldName + indexValue, fieldDataDef, fieldValue, false);
			}

		}
	}

	/**
	 * Change the value of the field data
	 * 
	 * @param functionData
	 *            - the function data
	 * @param fieldName
	 *            - the input field name
	 * @param fieldValue
	 *            - the field value
	 * @param bInputValue
	 *            - true - check for field input value
	 *            <p>
	 *            otherwise check for field database value
	 */
	private void changeFieldData(FunctionData functionData, String fieldName, String fieldValue, boolean bInputValue)
	{
		boolean fieldChanged = false;

		// check if the value will be updated
		if (!fieldChanged)
		{
			fieldChanged = functionData.chkFieldChange(fieldName, fieldValue, bInputValue);
		}

		// if there is no changed, then there is no need to update
		if (fieldChanged)
		{
			functionData.chgInputOrDBField(fieldName, fieldValue, bInputValue);
		}

	}

	/**
	 * Insert a new field data to the function data
	 * 
	 * @param functionData
	 *            - the function data
	 * @param rowObject
	 *            - the row complex data
	 * @param function
	 *            - the function
	 * @param inputFieldName
	 *            - the input field name
	 * @param index
	 *            - the index of the new field data
	 * 
	 * @return the new field data or null if the field data was not created
	 */
	public FieldData insertFieldData(FunctionData functionData, Object rowObject, Function function, String inputFieldName,
					String index)
	{
		InputField inputField = function.rtvInputField(inputFieldName);
		if (inputField == null)
		{
			return null;
		}

		String inputFieldMethodName = getFieldNameUsingInputFieldId(inputField);
		if (inputFieldMethodName == null || inputFieldMethodName.length() == 0)
		{
			return null;
		}

		String fieldValue = getFieldValue(rowObject, inputFieldMethodName);
		if (fieldValue != null)
		{
			FieldData fieldData = functionData.insertFieldData(inputFieldName + index, functionData.rtvFieldData(inputFieldName),
							fieldValue, false);
			return fieldData;
		}

		return null;
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
	 * @param enhancement
	 *            - the enhancement
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataType(EquationStandardSession session, Function function, Object anyNode, String enhancement)
					throws EQException
	{
		Object bf_functionData = getBankFusionDataType(session, function, enhancement);
		return getBankFusionDataType(function, anyNode, bf_functionData);
	}

	/**
	 * Return the equivalent complex data type of the given AnyNode
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param function
	 *            - the Function
	 * @param enhancement
	 *            - the enhancement
	 * 
	 * @return the complex data type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataType(EquationStandardSession session, Function function, String enhancement) throws EQException
	{
		String fullClassName = null;
		if (!function.chkXSDGeneric())
		{
			fullClassName = ComplexTypeToolbox.getComplexTypePackage(function) + "."
							+ getServiceEnhancementsMicroflowName(function, enhancement);
		}
		else
		{
			// This code should never be reached as this method should not be used for services using enhanced XSD
			throw new EQException("Generic XSD class name required.");
		}
		return getBankFusionDataType(session, fullClassName);
	}
	/**
	 * Return and set the given complex Data type based on the content of the specified CRM Function Data
	 * 
	 * @param crmFunction
	 *            - the function
	 * @param crmFunctionData
	 *            - the function data
	 * @param bf_functionData
	 *            - the complex data type
	 * @param bInputValue
	 *            - - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return the complex data type
	 * @throws EQException
	 */
	public Object getCRMBankFusionDataType(Function crmFunction, FunctionData crmFunctionData, Object bf_functionData,
					boolean bInputValue)
	{

		int curData = Toolbox.parseInt(crmFunctionData.rtvFieldDbValue("CURDATA"), 0);

		if (curData > 0)
		{
			// set CURDATA
			InputField inputField = crmFunction.rtvInputField("CURDATA");

			if (inputField != null)
			{
				setFieldValue(crmFunction, crmFunctionData, bf_functionData, "CURDATA", bInputValue, "");
			}

			for (int index = 1; index <= curData; index++)
			{
				String indexValue = String.valueOf(index);
				Object rowObject = addRow(bf_functionData, "RA");

				// set the field value of the complex data type
				setFieldValue(crmFunction, crmFunctionData, rowObject, "APIID", bInputValue, indexValue);
				setFieldValue(crmFunction, crmFunctionData, rowObject, "APILBL", bInputValue, indexValue);
				setFieldValue(crmFunction, crmFunctionData, rowObject, "APIDSC", bInputValue, indexValue);
				setFieldValue(crmFunction, crmFunctionData, rowObject, "REF", bInputValue, indexValue);
				setFieldValue(crmFunction, crmFunctionData, rowObject, "CUR", bInputValue, indexValue);
				setFieldValue(crmFunction, crmFunctionData, rowObject, "AMT", bInputValue, indexValue);
				setFieldValue(crmFunction, crmFunctionData, rowObject, "MSGSTS", bInputValue, indexValue);
				setFieldValue(rowObject, "RAINDEX_raindex", indexValue);

				for (int j = 1; j <= 100; j++)
				{

					indexValue = index + "_" + j;

					// check if this exists, if it does not exists, then exit
					if (crmFunctionData.rtvFieldData("CUS" + indexValue) == null)
					{
						break;
					}

					rowObject = addRow(bf_functionData, "RB");

					setFieldValue(crmFunction, crmFunctionData, rowObject, "CUS", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "CLC", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "GRP", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "CNA", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "LC", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "CCY", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "RAM", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "LAM", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "AAM", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "XPD", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "ERR", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "CNA1", bInputValue, indexValue);
					setFieldValue(crmFunction, crmFunctionData, rowObject, "INTH", bInputValue, indexValue);
					setFieldValue(rowObject, "RBINDEX_rbindex", indexValue);

				}
			}
		}

		// return the output object
		return bf_functionData;
	}

	/**
	 * Return and set the given complex Data type based on the content of the specified EFC Function Data
	 * 
	 * @param session
	 *            - the Equation Standard Session
	 * @param efcFunction
	 *            - the function
	 * @param efcFunctionData
	 *            - the function data
	 * @param bf_functionData
	 *            - the complex data type
	 * @param bInputValue
	 *            - - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 * 
	 * @return the complex data type
	 * @throws EQException
	 */
	public Object getEFCBankFusionDataType(EquationStandardSession session, Function efcFunction, FunctionData efcFunctionData,
					Object bf_functionData, boolean bInputValue) throws EQException
	{

		for (int i = 0; i < efcFunction.getInputFieldSets().size(); i++)
		{
			InputFieldSet inputFieldSet = efcFunction.getInputFieldSets().get(i);
			for (int j = 0; j < inputFieldSet.getInputFields().size(); j++)
			{
				InputField inputField = inputFieldSet.getInputFields().get(j);
				String fieldName = inputField.getId();

				if (!Field.isRepeating(inputField))
				{
					// check if this is used as a row input field
					boolean isRowInputField = false;
					for (String rowInputField : efcRowInputFelds)
					{
						if (rowInputField.equals(inputField.getId()))
						{
							isRowInputField = true;
							break;
						}
					}
					if (isRowInputField)
					{
						continue;
					}

					// set the field value
					setFieldValue(efcFunction, efcFunctionData, bf_functionData, fieldName, bInputValue, "");
				}
			}
		}

		// HZU641 and HZU642 data
		EquationStandardListEnquiry chargeEnquiry = new EquationStandardListEnquiry("U64DER", session);
		EquationDataStructure eqDs = chargeEnquiry.getHzDSData().getEqDS();
		for (String fieldName : eqDs.getFieldNames())
		{
			FieldData fieldDataDef = efcFunctionData.getFieldDatas().get(fieldName);
			if (fieldDataDef == null)
			{
				EquationFieldDefinition fd = eqDs.getFieldDefinition(fieldName);
				if (fd != null)
				{
					fieldDataDef = new FieldData(fieldName, fd.getFieldDataTypeString(), fd.getFieldLength(), fd.getFieldDecimal());
					fieldDataDef.setUpperCase(true);
				}
			}

			if (fieldDataDef != null)
			{
				setFieldValue(fieldDataDef, efcFunction, efcFunctionData, bf_functionData, fieldName, bInputValue, "");
			}

		}

		EquationStandardTransaction transaction = new EquationStandardTransaction("C62ARRACG", session, 1000);
		eqDs = transaction.getGsDSData().getEqDS();

		// GSC621 and GZC621 data
		for (int index = 1; index < 100; index++)
		{
			String indexValue = FunctionToolbox.UNDERSCORE + index;

			// check if this exists, if it does not exists, then exit
			if (efcFunctionData.rtvFieldData("OPT" + indexValue) == null)
			{
				break;
			}

			Object rowObject = addRow(bf_functionData, "RA");

			for (String rowInputField : efcRowInputFelds)
			{
				setFieldValue(efcFunction, efcFunctionData, rowObject, rowInputField, bInputValue, indexValue);
			}

			for (String fieldName : eqDs.getFieldNames())
			{
				FieldData fieldDataDef = efcFunctionData.getFieldDatas().get(fieldName);
				if (fieldDataDef == null)
				{
					EquationFieldDefinition fd = eqDs.getFieldDefinition(fieldName);
					if (fd != null)
					{
						fieldDataDef = new FieldData(fieldName, fd.getFieldDataTypeString(), fd.getFieldLength(), fd
										.getFieldDecimal());
						fieldDataDef.setUpperCase(true);
					}
				}

				if (fieldDataDef != null)
				{
					setFieldValue(fieldDataDef, efcFunction, efcFunctionData, rowObject, fieldName, bInputValue, indexValue);
				}

			}
		}

		// return the output object
		return bf_functionData;
	}

	/**
	 * Set the field value of the field name of a complex data type
	 * 
	 * @param function
	 *            - the function
	 * @param functionData
	 *            - the function data
	 * @param bf_functionData
	 *            - the complex data type
	 * @param inputFieldName
	 *            - the name of the input field
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 */
	private void setFieldValue(Function function, FunctionData functionData, Object bf_functionData, String inputFieldName,
					boolean bInputValue, String indexValue)
	{

		InputField inputField = function.rtvInputField(inputFieldName);

		if (inputField != null)
		{
			String fieldValue = bInputValue ? functionData.rtvFieldInputValue(inputFieldName + indexValue) : functionData
							.rtvFieldDbValue(inputFieldName + indexValue);
			// set the field value of the complex data type
			setFieldValue(bf_functionData, getFieldNameUsingInputFieldId(inputField), fieldValue);
		}

	}

	/**
	 * Set the field value of the field name of a complex data type
	 * 
	 * @param fieldDataDef
	 *            - the field data definition
	 * @param function
	 *            - the function
	 * @param functionData
	 *            - the function data
	 * @param bf_functionData
	 *            - the complex data type
	 * @param inputFieldName
	 *            - the name of the input field
	 * @param bInputValue
	 *            - true - the field values relate to the input field value <br>
	 *            false - the field values relate to the database field value
	 */
	private void setFieldValue(FieldData fieldDataDef, Function function, FunctionData functionData, Object bf_functionData,
					String inputFieldName, boolean bInputValue, String indexValue)
	{

		InputField inputField = function.rtvInputField(inputFieldName);
		String inputFieldMethodName = null;
		if (inputField != null)
		{
			inputFieldMethodName = getFieldNameUsingInputFieldId(inputField);
		}
		else if (inputField == null && fieldDataDef != null)
		{
			inputFieldMethodName = fieldDataDef.getFieldName() + FunctionToolbox.UNDERSCORE
							+ Toolbox.textToCamelCase(fieldDataDef.getFieldName());
		}

		if (inputFieldMethodName != null && inputFieldMethodName.length() > 0)
		{
			String fieldValue = bInputValue ? functionData.rtvFieldInputValue(inputFieldName + indexValue) : functionData
							.rtvFieldDbValue(inputFieldName + indexValue);
			// set the field value of the complex data type
			setFieldValue(bf_functionData, inputFieldMethodName, fieldValue);
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
	public void setFieldValue(Object bf_functionData, String inputField, String fieldValue)
	{
		String methodName = "set" + inputField;

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
	 * Return the string containing the name of the CRM / EFC service microflow
	 * 
	 * @param service
	 *            - the Equation Service
	 * @param enhancement
	 *            - the enhancement name (EFC or CRM)
	 * 
	 * @return the string containing the name of the CRM / EFC service microflow
	 */
	public String getServiceEnhancementsMicroflowName(Function service, String enhancement)
	{
		StringBuilder microflowName = new StringBuilder(50);

		microflowName.append(service.getappBankId());
		microflowName.append(FunctionToolbox.UNDERSCORE);
		microflowName.append(service.getModuleId());
		microflowName.append(FunctionToolbox.UNDERSCORE);
		if (enhancement.equalsIgnoreCase(ScreenSetCRM.OPTION))
		{
			microflowName.append("creditRiskManagement");
		}
		else if (enhancement.equalsIgnoreCase(ScreenSetAC2.OPTION))
		{
			microflowName.append("enhancedFeesAndCharges");
		}

		microflowName.append(enhancement);
		microflowName.append(FunctionToolbox.UNDERSCORE);
		microflowName.append("SRV");

		return microflowName.toString();
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
	public static String getFieldNameUsingInputFieldId(InputField field)
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
				return field.getId() + FunctionToolbox.UNDERSCORE + Toolbox.textToCamelCase(field.getId());
			}
		}
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
	public String getFieldValue(Object bf_functionData, String inputField)
	{
		String methodName = "get" + inputField;

		try
		{
			Method method = bf_functionData.getClass().getDeclaredMethod(methodName);
			String fieldValue = (String) method.invoke(bf_functionData);
			return fieldValue;
		}
		catch (Exception e)
		{
			LOG.error(e);
			return "";
		}
	}

	/**
	 * Retrieve the CRM complex type
	 * 
	 * @param equationStandardSession
	 *            - the Equation Standard Session
	 * @param screenSet
	 *            - the screen set for CRM
	 * 
	 * @return the CRM complex type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataTypeCRM(EquationStandardSession equationStandardSession, ScreenSetCRM screenSet)
					throws EQException
	{
		if (screenSet.curData() > 0)
		{
			Function crmFunction = screenSet.getFunction();
			setupDefaultFunctionModule(crmFunction);
			Object crm_bf_functionData = getBankFusionDataType(equationStandardSession, crmFunction, ScreenSetCRM.OPTION);
			FunctionData crmFunctionData = screenSet.getFunctionData();
			Object crmData = getCRMBankFusionDataType(crmFunction, crmFunctionData, crm_bf_functionData, false);
			return crmData;
		}

		return null;
	}

	/**
	 * Retrieve the AC2 complex type
	 * 
	 * @param equationStandardSession
	 *            - the Equation Standard Session
	 * @param screenSet
	 *            - the screen set for AC2
	 * 
	 * @return the AC2 complex type
	 * 
	 * @throws EQException
	 */
	public Object getBankFusionDataTypeAC2(EquationStandardSession equationStandardSession, ScreenSetAC2 screenSet)
					throws EQException
	{
		if (screenSet.curData() > 0)
		{
			Function efcFunction = screenSet.getFunction();
			setupDefaultFunctionModule(efcFunction);
			Object efc_bf_functionData = getBankFusionDataType(equationStandardSession, efcFunction, ScreenSetAC2.OPTION);
			FunctionData efcFunctionData = screenSet.getFunctionData();
			Object efcData = getEFCBankFusionDataType(equationStandardSession, efcFunction, efcFunctionData, efc_bf_functionData,
							false);

			return efcData;
		}

		return null;
	}

	/**
	 * Set up default module id
	 * 
	 * @param function
	 *            - the function
	 */
	private void setupDefaultFunctionModule(Function function)
	{
		if (function.getModuleId().trim().length() == 0)
		{
			function.setModuleId(EQUATION_DEFAULT_MODULE);
		}

		if (function.getappBankId().trim().length() == 0)
		{
			function.setAppBankId(EQUATION_DEFAULT_APP);
		}
	}

}
