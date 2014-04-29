package com.misys.equation.function.comparator;

import java.util.List;

import com.misys.equation.common.access.EquationPVDecodeMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.PVFieldSet;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.FunctionToolbox;
import com.misys.equation.function.tools.RepeatingDetails;

public class SynchroniseServiceToolbox
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SynchroniseServiceToolbox.java 10275 2011-01-17 14:55:18Z MACDONP1 $";

	/**
	 * Synchronise all the Equation-related API of the service
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param function
	 *            - the function
	 */
	public static void synchroniseAPIs(EquationStandardSession session, Function function) throws EQException
	{
		SynchroniseServiceToolbox.updatePVFieldSet(session, function);
		SynchroniseServiceToolbox.updateLoadAPIFieldSet(session, function);
		SynchroniseServiceToolbox.updateUpdateAPIFieldSet(session, function);
	}

	/**
	 * Update the PV field sets
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param function
	 *            - the Equation service to be updated
	 * 
	 * @return true if it has updated the PV field set
	 */
	public static boolean updatePVFieldSet(EquationStandardSession session, Function function) throws EQException
	{
		boolean changed = false;
		for (InputFieldSet inputFieldSet : function.getInputFieldSets())
		{
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				List<PVFieldSet> pvFieldSets = inputField.getPvFieldSets();
				for (int i = 0; i < pvFieldSets.size(); i++)
				{
					PVFieldSet orgPvFieldSet = pvFieldSets.get(i);
					PVFieldSet newPVFieldSet = getPVFieldSet(session, orgPvFieldSet);

					// unknown API type?
					if (newPVFieldSet == null)
					{
						throw new EQException(LanguageResources.getFormattedString("SynchroniseServiceToolbox.PVFieldSetErrorNull",
										new String[] { orgPvFieldSet.getId(), inputField.getId(), inputFieldSet.getId() }));
					}

					// any change?
					ElementDifference ed = ElementComparatorToolbox.compare(orgPvFieldSet, newPVFieldSet, "",
									ElementComparator.COPY_TO_SECOND);
					if (ed.isDiffer())
					{
						pvFieldSets.remove(i);
						pvFieldSets.add(i, newPVFieldSet);
						changed = true;
					}
				}
			}
		}
		return changed;
	}

	/**
	 * Update the Load API field sets
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param function
	 *            - the Equation service to be updated
	 * 
	 * @return true if it has updated the PV field set
	 * 
	 * @throws EQException
	 */
	public static boolean updateLoadAPIFieldSet(EquationStandardSession session, Function function) throws EQException
	{
		boolean changed = false;
		List<APIFieldSet> loadFieldSets = function.rtvPrimaryInputFieldSet().getLoadAPIs();
		for (int i = 0; i < loadFieldSets.size(); i++)
		{
			APIFieldSet orgAPIFieldSet = loadFieldSets.get(i);
			APIFieldSet newAPIFieldSet = getAPIFieldSet(session, orgAPIFieldSet);

			// unknown API type?
			if (newAPIFieldSet == null)
			{
				throw new EQException(LanguageResources.getFormattedString("SynchroniseServiceToolbox.LoadAPIFieldSetErrorNull",
								new String[] { orgAPIFieldSet.getId() }));
			}

			// reserve fields
			if (newAPIFieldSet.getType().equals(IEquationStandardObject.TYPE_TRANSACTION))
			{
				newAPIFieldSet.addReservedFields();
			}

			// any change?
			ElementDifference ed = ElementComparatorToolbox.compare(orgAPIFieldSet, newAPIFieldSet, "",
							ElementComparator.COPY_TO_SECOND);
			if (ed.isDiffer())
			{
				loadFieldSets.remove(i);
				loadFieldSets.add(i, newAPIFieldSet);
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Update the Update API field sets
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param function
	 *            - the Equation service to be updated
	 * 
	 * @return true if it has updated the PV field set
	 * 
	 * @throws EQException
	 */
	public static boolean updateUpdateAPIFieldSet(EquationStandardSession session, Function function) throws EQException
	{
		boolean changed = false;
		List<APIFieldSet> updateFieldSets = function.getUpdateAPIs();
		for (int i = 0; i < updateFieldSets.size(); i++)
		{
			APIFieldSet newAPIFieldSet = null;
			APIFieldSet orgAPIFieldSet = updateFieldSets.get(i);

			// reserved Update API?
			if (Function.containsReservedName(orgAPIFieldSet.getId()))
			{
				newAPIFieldSet = Function.generateReservedFieldSet(orgAPIFieldSet.getId(), orgAPIFieldSet.getDescription());
			}
			else
			{
				newAPIFieldSet = getAPIFieldSet(session, orgAPIFieldSet);
			}

			// unknown API type?
			if (newAPIFieldSet == null)
			{
				throw new EQException(LanguageResources.getFormattedString("SynchroniseServiceToolbox.UpateAPIFieldSetErrorNull",
								new String[] { orgAPIFieldSet.getId() }));
			}

			// add the default Mode field?
			if (!Function.containsReservedName(orgAPIFieldSet.getId())
							&& !newAPIFieldSet.getType().equals(IEquationStandardObject.TYPE_REPORT))
			{
				newAPIFieldSet.addReservedFields();
			}

			// any change?
			ElementDifference ed = ElementComparatorToolbox.compare(orgAPIFieldSet, newAPIFieldSet, "",
							ElementComparator.COPY_TO_SECOND);
			if (ed.isDiffer())
			{
				updateFieldSets.remove(i);
				updateFieldSets.add(i, newAPIFieldSet);
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Retrieve the PV Field set
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param pvFieldSet
	 *            - the PV field set
	 * 
	 * @return the new PV Field set based on the current session
	 * 
	 * @throws EQException
	 */
	private static PVFieldSet getPVFieldSet(EquationStandardSession session, PVFieldSet pvFieldSet) throws EQException
	{
		String type = pvFieldSet.getType();
		PVFieldSet newPVFieldSet;

		if (type.trim().length() == 0 || type.equals(IEquationStandardObject.TYPE_PV))
		{
			newPVFieldSet = new PVFieldSet(pvFieldSet.getId(), pvFieldSet.getDescription(), pvFieldSet.getDecode(),
							pvFieldSet.getNewField(), pvFieldSet.isBlankAllowed());
			FunctionToolbox.populatePVFieldSetFields(session, newPVFieldSet, null, null, false);

			// is this a non-UTW19R PV, then populate other fields, so it can be executed as a table
			EquationPVMetaData equationPVMetaData = session.getUnit().getPVMetaData(pvFieldSet.getId());

			if (equationPVMetaData.getPvSource() != EquationPVMetaData.PV_SOURCE_UTW19R)
			{
				EquationPVDecodeMetaData pvDecodeMetaData = equationPVMetaData.getDecodeMetaData(pvFieldSet.getDecode());
				String file = pvDecodeMetaData.getSqlFrom();
				String keyFields = session.getUnit().getUniqueKeys("KFIL", file, false);
				newPVFieldSet.setRoot(file);
				newPVFieldSet.setKeyFields(keyFields);
			}
		}

		// table
		else if (type.equals(IEquationStandardObject.TYPE_TABLE))
		{
			APIFieldSet apiFieldSet = FunctionToolbox.getTableFieldSet(session, pvFieldSet.getId(), pvFieldSet.getLabel(),
							pvFieldSet.getDescription(), pvFieldSet.getKeyFields(), pvFieldSet.getRoot(),
							IEquationStandardObject.FCT_FUL, false, pvFieldSet.isGlobalTable());
			newPVFieldSet = FunctionToolbox.cvtAPIToPVFieldSet(apiFieldSet);
		}

		// API
		else
		{
			APIFieldSet apiFieldSet = FunctionToolbox.getAPIFieldSet(session, pvFieldSet.getId(), pvFieldSet.getLabel(),
							pvFieldSet.getRoot(), pvFieldSet.getDescription(), IEquationStandardObject.FCT_FUL);
			newPVFieldSet = FunctionToolbox.cvtAPIToPVFieldSet(apiFieldSet);
		}

		// return the new PV Field set
		return newPVFieldSet;
	}

	/**
	 * Retrieve the API field set
	 * 
	 * @param session
	 *            - the Equation standard session
	 * @param apiFieldSet
	 *            - the API Field set
	 * @return the new API Field set based on the current session
	 * 
	 * @throws EQException
	 */
	private static APIFieldSet getAPIFieldSet(EquationStandardSession session, APIFieldSet apiFieldSet) throws EQException
	{
		String type = apiFieldSet.getType();
		APIFieldSet newAPIFieldSet = null;

		// repeating details
		RepeatingDetails repeatingDetails = new RepeatingDetails();
		repeatingDetails.setRepeatingGroupId(apiFieldSet.getRepeatingGroup());
		repeatingDetails.setJoinType(apiFieldSet.getRepeatingGroupLoadMode());
		repeatingDetails.setJoinAPIFields(apiFieldSet.getRepeatingGroupLoadJoinAPIField());
		repeatingDetails.setInputFields(apiFieldSet.getRepeatingGroupLoadJoinInputField());

		// Equation API
		if (type.equals(IEquationStandardObject.TYPE_TRANSACTION) || type.equals(IEquationStandardObject.TYPE_ENQUIRY)
						|| type.equals(IEquationStandardObject.TYPE_LIST_ENQUIRY)
						|| type.equals(IEquationStandardObject.TYPE_SERVICE))
		{
			newAPIFieldSet = FunctionToolbox.getAPIFieldSet(session, apiFieldSet.getId(), apiFieldSet.getLabel(),
							apiFieldSet.getRoot(), apiFieldSet.getDescription(), IEquationStandardObject.FCT_FUL, repeatingDetails);
		}

		// PV
		else if (type.equals(IEquationStandardObject.TYPE_PV))
		{
			// Use existing methods to create an PVFieldSet using the supplied details
			PVFieldSet pvFieldSet = new PVFieldSet(apiFieldSet.getLabel(), apiFieldSet.getDescription(), apiFieldSet.getDecode(),
							apiFieldSet.getNewField(), false);
			FunctionToolbox.populatePVFieldSetFields(session, pvFieldSet, null, null, false);
			newAPIFieldSet = new APIFieldSet(apiFieldSet.getId(), pvFieldSet);
		}

		// SQL List query
		else if (type.equals(IEquationStandardObject.TYPE_LIST_QUERY))
		{
			newAPIFieldSet = FunctionToolbox.getSqlFieldSet(session, apiFieldSet.getId(), apiFieldSet.getDescription(),
							apiFieldSet.getRoot());
			apiFieldSet.setRepeatingGroup(repeatingDetails.getRepeatingGroupId());
			apiFieldSet.setRepeatingGroupLoadMode(repeatingDetails.getJoinType());
			apiFieldSet.setRepeatingGroupLoadJoinAPIField(repeatingDetails.getJoinAPIFields());
			apiFieldSet.setRepeatingGroupLoadJoinInputField(repeatingDetails.getInputFields());
		}

		// Report program
		else if (type.equals(IEquationStandardObject.TYPE_REPORT))
		{
			newAPIFieldSet = FunctionToolbox.getReportFieldSet(apiFieldSet.getId(), apiFieldSet.getRoot(),
							apiFieldSet.getKeyFields());
		}

		// Table
		else if (type.equals(IEquationStandardObject.TYPE_TABLE))
		{
			newAPIFieldSet = FunctionToolbox.getTableFieldSet(session, apiFieldSet.getId(), apiFieldSet.getLabel(),
							apiFieldSet.getDescription(), apiFieldSet.getKeyFields(), apiFieldSet.getRoot(), apiFieldSet.getMode(),
							apiFieldSet.isDetermineMode(), apiFieldSet.isGlobalLibrary());
		}

		// List table
		else if (type.equals(IEquationStandardObject.TYPE_LIST_TABLE))
		{
			newAPIFieldSet = FunctionToolbox.getTableFieldSetAsList(session, apiFieldSet.getId(), apiFieldSet.getLabel(),
							apiFieldSet.getDescription(), apiFieldSet.getKeyFields(), apiFieldSet.getRoot(), apiFieldSet.getMode(),
							repeatingDetails);
		}

		// unknown
		else
		{
			return newAPIFieldSet;
		}

		// return the new API Field set
		return newAPIFieldSet;
	}

}
