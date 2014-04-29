package com.misys.equation.function.runtime;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.adaptor.ValueAdaptor;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Field;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.MappingToolbox;

public class FunctionCommonData
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionCommonData.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	public static final String GY_GYAPP = "GYAPP";
	public static final String GY_GYWHO = "GYWHO";
	public static final String GY_GYSHN = "GYSHN";
	public static final String GY_GYJREF = "GYJREF";
	public static final String GY_GYIREF = "GYIREF";
	public static final String GY_GYAPID = "GYAPID";
	public static final String GY_GYTCP = "GYTCP";
	public static final String GY_GYAUID = "GYAUID";
	public static final String GY_GYSUID = "GYSUID";

	public static final String G5_G5APP = "G5APP";
	public static final String G5_G5WHO = "G5WHO";
	public static final String G5_G5SHN = "G5SHN";
	public static final String G5_G5JREF = "G5JREF";
	public static final String G5_G5N01 = "G5N01";
	public static final String G5_G5N02 = "G5N02";
	public static final String G5_G5N03 = "G5N03";
	public static final String G5_G5N04 = "G5N04";
	public static final String G5_G5N05 = "G5N05";
	public static final String G5_G5N06 = "G5N06";
	public static final String G5_G5N07 = "G5N07";
	public static final String G5_G5N08 = "G5N08";
	public static final String G5_G5N09 = "G5N09";
	public static final String G5_G5N010 = "G5N010";
	public static final String G5_G5EEAN = "G5EEAN";

	public static final String EFC_ENEW = "ENEW";
	/** EFC Debit account branch */
	public static final String EFC_EDAB = "EDAB";
	/** EFC Debit account number */
	public static final String EFC_EDAN = "EDAN";
	/** EFC Debit account suffix */
	public static final String EFC_EDAS = "EDAS";
	/** EFC Debit account amount */
	public static final String EFC_EDAMT = "EDAMT";
	/** EFC Debit currency */
	public static final String EFC_EDCCY = "EDCCY";
	/** EFC Credit account branch */
	public static final String EFC_ECAB = "ECAB";
	/** EFC Credit account number */
	public static final String EFC_ECAN = "ECAN";
	/** EFC Credit account suffix */
	public static final String EFC_ECAS = "ECAS";
	/** EFC Credit amount */
	public static final String EFC_ECAMT = "ECAMT";
	/** EFC Credit currency */
	public static final String EFC_ECCCY = "ECCCY";
	/** EFC Deal branch */
	public static final String EFC_EBRNM = "EBRNM";
	/** EFC Deal prefix */
	public static final String EFC_EDLP = "EDLP";
	/** EFC Deal reference */
	public static final String EFC_EDLR = "EDLR";
	/** EFC Commitment reference */
	public static final String EFC_ECMR = "ECMR";
	/** EFC Transaction reference */
	public static final String EFC_ETREF = "ETREF";
	/** EFC Transaction date */
	public static final String EFC_ETDTE = "ETDTE";
	/** EFC Extra reference */
	public static final String EFC_EXREF = "EXREF";
	/** EFC Sequence number */
	public static final String EFC_ESQN = "ESQN";
	/** Account type */
	public static final String EFC_EATP = "EATP";
	/** Deal type */
	public static final String EFC_EDTP = "EDTP";

	public static final String CRM_HHAB = "HHAB";
	public static final String CRM_HHAN = "HHAN";
	public static final String CRM_HHAS = "HHAS";
	public static final String CRM_HHBRN = "HHBRN";
	public static final String CRM_HHDLP = "HHDLP";
	public static final String CRM_HHDLR = "HHDLR";
	public static final String CRM_HHMVT = "HHMVT";
	public static final String CRM_HHCMR = "HHCMR";
	public static final String CRM_HHCUS = "HHCUS";
	public static final String CRM_HHCLC = "HHCLC";
	public static final String CRM_HHATP = "HHATP";
	public static final String CRM_HHCY1 = "HHCY1";
	public static final String CRM_HHCY2 = "HHCY2";
	public static final String CRM_HHSRC = "HHSRC";
	public static final String CRM_HHUC1 = "HHUC1";
	public static final String CRM_HHUC2 = "HHUC2";
	public static final String CRM_HHSDT = "HHSDT";
	public static final String CRM_HHMDT = "HHMDT";
	public static final String CRM_HHDRF = "HHDRF";
	public static final String CRM_HHAMC = "HHAMC";
	public static final String CRM_HHODC = "HHODC";
	public static final String CRM_HHFCT = "HHFCT";
	public static final String CRM_HHAB2 = "HHAB2";
	public static final String CRM_HHAN2 = "HHAN2";
	public static final String CRM_HHAS2 = "HHAS2";
	public static final String CRM_HHCU2 = "HHCU2";
	public static final String CRM_HHCL2 = "HHCL2";
	public static final String CRM_HHDR2 = "HHDR2";
	public static final String CRM_HHAMP = "HHAMP";
	public static final String CRM_HHODP = "HHODP";
	public static final String CRM_HHCAC = "HHCAC";
	public static final String CRM_HHCAP = "HHCAP";

	private FunctionDebugInfo functionDebugInfo;
	private FunctionData functionMapData;
	private boolean dataExist;

	/**
	 * Construct an empty map data
	 */
	public FunctionCommonData()
	{
	}

	/**
	 * Construct a Map Data with data
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param function
	 *            - the Function
	 * @param apiFieldSet
	 *            - the API field set
	 * @param functionData
	 *            - the Function Data
	 * @param functionAdaptor
	 *            - the Function Adaptor
	 * @param fhd
	 *            - the Function Handler Data
	 */
	public FunctionCommonData(EquationStandardSession session, Function function, APIFieldSet apiFieldSet,
					FunctionData functionData, FunctionAdaptor functionAdaptor, FunctionHandlerData fhd) throws EQException
	{
		setup(session, function, apiFieldSet, functionData, functionAdaptor, fhd);
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		if (functionMapData == null)
		{
			return "";
		}
		else
		{
			return functionMapData.toString();
		}
	}

	/**
	 * Return the debug info
	 * 
	 * @return the debug info
	 */
	public FunctionDebugInfo getFunctionDebugInfo()
	{
		return functionDebugInfo;
	}

	/**
	 * Return the field value
	 * 
	 * @param fieldName
	 *            - the field name
	 * 
	 * @return the field value
	 */
	public String getFieldValue(String fieldName)
	{
		return functionMapData.rtvFieldInputValue(fieldName);
	}

	/**
	 * Return if there is at least one map data
	 * 
	 * @return true if there is at least one map data
	 */
	public boolean isDataExist()
	{
		return dataExist;
	}

	/**
	 * Setup the data based on the mappings
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param function
	 *            - the Function
	 * @param apiFieldSet
	 *            - the API field set
	 * @param functionData
	 *            - the Function Data
	 * @param functionAdaptor
	 *            - the Function Adaptor
	 */
	public void setup(EquationStandardSession session, Function function, APIFieldSet apiFieldSet, FunctionData functionData,
					FunctionAdaptor functionAdaptor, FunctionHandlerData fhd) throws EQException
	{
		// set the function debug
		functionDebugInfo = new FunctionDebugInfo();

		// function data
		functionMapData = new FunctionData();
		dataExist = false;

		// loop through all the fields
		for (int i = 0; i < apiFieldSet.getAPIFields().size(); i++)
		{
			// retrieve the field
			Field field = apiFieldSet.getAPIFields().get(i);
			String fieldName = field.getId();
			String dbValue = "";

			// path exists?
			boolean tobeUpdated = function.hasFromUpdateMapping(field.rtvPath());

			// get the mapped from field
			if (tobeUpdated)
			{
				String mappingPath = function.getUpdateMappingsFrom(field).get(0);
				String mapFromField = MappingToolbox.getField(mappingPath);
				String mapFromFieldSet = MappingToolbox.getFieldSet(mappingPath);

				// Determine whether the mapping is in the Function Data or to a previous transactions
				// .. from the transaction, get the value
				if (mapFromFieldSet.length() == 0)
				{
					// Mapping from a WorkField.
					functionDebugInfo.addInfoLn(LanguageResources.getFormattedString(
									"FunctionTransactions.Debug.DataMappedToTransaction.Workfield", new String[] { "WorkField",
													mapFromField, fieldName }));
					// Get the WorkField value directly from the FunctionData
					dbValue = functionData.rtvFieldDbValue(mapFromField);

				}
				// .. function data, get the database value
				else
				{
					functionDebugInfo.addInfoLn(LanguageResources.getFormattedString(
									"FunctionTransactions.Debug.DataMappedToTransaction.FunctionData", new String[] { mapFromField,
													fieldName }));
					dbValue = functionData.rtvFieldDbValue(mapFromField);
				}
			}

			// Mapping script expression specified
			if (Field.ASSIGNMENT_SCRIPT.equals(field.getUpdateAssignment()))
			{
				dbValue = ELRuntime.runStringScript(field.getUpdateScript(), functionData, field.getId(),
								LanguageResources.getString("Language.Assignment"), null, ELRuntime.DB_VALUE);
				if (dbValue != null)
				{
					tobeUpdated = true;
				}

				// set the GZ field
				functionDebugInfo.addInfoLn(LanguageResources.getFormattedString(
								"FunctionTransactions.Debug.DataMappedToTransaction.MappingScript", field.getId()));
			}

			// user exit
			if (Field.ASSIGNMENT_CODE.equals(field.getUpdateAssignment()))
			{
				ValueAdaptor valueAdaptor = functionAdaptor.getValueAdaptor(session, apiFieldSet.getId(), fieldName,
								GAZRecordDataModel.TYP_UPDATE);
				String newStr = valueAdaptor.getValue(dbValue);
				if (newStr != null)
				{
					functionDebugInfo.addInfoLn(LanguageResources.getFormattedString(
									"FunctionTransactions.Debug.DataMappedToTransaction.ValueAdaptor",
									new String[] { apiFieldSet.getId(), fieldName }));
					dbValue = newStr;
					tobeUpdated = true;
				}
			}

			// add to function data
			if (tobeUpdated)
			{
				FieldData fieldData = new FieldData(field.getId(), field.getDataType(), Toolbox.parseInt(field.getSize(),
								dbValue.length()), 0, dbValue, dbValue, dbValue);
				functionMapData.addFieldData(fieldName, fieldData);
				dataExist = true;
			}
		}
	}

}
