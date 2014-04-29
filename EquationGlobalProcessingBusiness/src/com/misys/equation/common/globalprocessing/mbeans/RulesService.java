package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;

import com.misys.equation.common.dao.beans.APJRecordDataModel;
import com.misys.equation.common.dao.beans.APVRecordDataModel;
import com.misys.equation.common.dao.beans.GPFRecordDataModel;
import com.misys.equation.common.dao.beans.GPRRecordDataModel;
import com.misys.equation.common.dao.beans.GPURecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains service methods for rules management.
 * 
 * @author berzosa
 */
public interface RulesService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RulesService.java 10223 2011-01-07 12:25:34Z bterrado $";

	/**
	 * Checks if a GPRRecordDataModel exists given it's identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param ruleId
	 *            The record identifier
	 * @return true if the record exists
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Checks if a GPRRecordDataModel exists given it's identifier.")
	public boolean ruleExists(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "ruleId", desc = "The record identifier") String ruleId) throws EQException;

	/**
	 * Checks if a GPRRecordDataModel exists in a Set given it's identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param ruleId
	 *            The record identifier
	 * @return true if the record exists in a Set
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Checks if a GPRRecordDataModel exists given it's identifier.")
	public boolean ruleExistsInSet(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "ruleId", desc = "The record identifier") String ruleId) throws EQException;

	/**
	 * Deletes the entailing GPR, GPF, GPU records given the rule identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param ruleId
	 *            The record identifier
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Deletes the entailing GPR, GPF, GPU records given the rule identifier.")
	public void deleteRule(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "ruleId", desc = "The GPR record identifier") String ruleId) throws EQException;

	/**
	 * Retrieves the GPR record given it's identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param gprRecordId
	 *            The GPR record identifier
	 * @return the GPR record
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Retrieves the GPR record given it's identifier.")
	public GPRRecordDataModel getGPRRecord(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "gprRecordId", desc = "The GPR record identifier") String ruleId) throws EQException;

	/**
	 * Retrieves all the APIRecordDataModel given the export type
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param exportType
	 *            The export type referenced by the fields
	 * @return - the list of APIRecordDataModel
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Retrieves all the fields (API records) given the export type.")
	public List<APJRecordDataModel> retrieveAllFields(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "exportType", desc = "The export type referenced by the fields") String exportType)
					throws EQException;

	/**
	 * Retrieves all the fields (API records) given the export type and rule identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param ruleId
	 *            The rule id referenced by the fields
	 * @param exportType
	 *            The export type referenced by the fields
	 * @return - the list of GPFRecordDataModel
	 * @throws EQException
	 *             If an error occurs retrieving the groups
	 */
	@MBeanOperation("Retrieves all the fields (API records) given the export type and rule identifier.")
	public List<GPFRecordDataModel> retrieveFields(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "ruleId", desc = "The rule id referenced by the fields") String ruleId,
					@Meta(name = "exportType", desc = "The export type referenced by the fields") String exportType)
					throws EQException;

	/**
	 * Retrieves the units (GPU records) given the rule identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param ruleId
	 *            The rule id referenced by the units
	 * @param unitType
	 * @return the list of GPU records
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Retrieves the units (GPU records) given the rule identifier.")
	public List<GPURecordDataModel> retrieveRuleUnits(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "ruleId", desc = "The rule id referenced by the units") String ruleId,
					@Meta(name = "unitType", desc = "The type of the unit; 'O' for source unit, 'T' for target unit, and 'C' for copy unit from") String unitType)
					throws EQException;

	/**
	 * Deletes all gpu records given the rule identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param ruleId
	 *            The rule id referenced by the units
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Deletes all GPU records given the rule identifier.")
	public void deleteRuleUnitsById(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "ruleId", desc = "The rule id referenced by the units") String ruleId) throws EQException;

	/**
	 * Deletes all gpf records given the rule identifier
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @param ruleId
	 *            The rule id referenced by the units
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Deletes all GPF records given the rule identifier.")
	public void deleteRuleFieldsById(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "ruleId", desc = "The rule id referenced by the units") String ruleId) throws EQException;

	/**
	 * Insert the gpu record
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * 
	 * @param record
	 *            The GPURecordDataModel to insert
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Insert the GPU record.")
	public void insertGPURecord(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "record", desc = "The GPURecordDataModel to insert") GPURecordDataModel record) throws EQException;

	/**
	 * Insert the gpu records
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * 
	 * @param records
	 *            The list of GPURecordDataModel records to insert
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Insert the GPU records.")
	public void insertGPURecords(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "records", desc = "The list of GPURecordDataModel to insert") List<GPURecordDataModel> records)
					throws EQException;

	/**
	 * Insert the gpf record
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * 
	 * @param record
	 *            The GPFRecordDataModel to insert
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Insert the GPF record.")
	public void insertGPFRecord(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "record", desc = "The GPFRecordDataModel to insert") GPFRecordDataModel record) throws EQException;

	/**
	 * Insert the gpf records
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * 
	 * @param records
	 *            The list of GPFRecordDataModel records to insert
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Insert the GPF records.")
	public void insertGPFRecords(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "records", desc = "The list of GPFRecordDataModel records to insert") List<GPFRecordDataModel> records)
					throws EQException;

	/**
	 * Saves or updates a rule
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * 
	 * @param gprRecord
	 *            The GPRRecordDataModel record to save or update
	 * @param gpuRecords
	 *            The list of GPURecordDataModel records to update
	 * @param gpfRecords
	 *            The list of GPFRecordDataModel records to update
	 * @return the number steps - used for monitoring progress
	 * @throws EQException
	 *             If an error occurs
	 */
	@MBeanOperation("Saves or updates a rule.")
	public int saveOrUpdateRule(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "gprRecord", desc = "The GPRRecordDataModel record to save or update") GPRRecordDataModel gprRecord,
					@Meta(name = "gpuRecords", desc = "The list of GPURecordDataModel records to update") List<GPURecordDataModel> gpuRecords,
					@Meta(name = "gpfRecords", desc = "The list of GPFRecordDataModel records to update") List<GPFRecordDataModel> gpfRecords)
					throws EQException;

	/**
	 * Loads all defined data propagation rules in the global library.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return All defined data propagation in the global library
	 */
	@MBeanOperation("Loads all defined data propagation rules in the global library.")
	public List<GPRRecordDataModel> getAllRules(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId)
					throws EQException;

	/**
	 * Loads all defined data propagation rules that are members of the set with the given ID.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return All defined data propagation in the global library
	 */
	@MBeanOperation("Loads all defined data propagation rules that are members of the set with the given ID.")
	public List<GPRRecordDataModel> getAdHocRulesBySet(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "setId", desc = "The Set ID") String setID) throws EQException;

	/**
	 * Loads all ad-hoc data propagation rules that don't belong to any sets.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return All data propagation rules that don't belong to any sets
	 */
	@MBeanOperation("Loads all ad-hoc data propagation rules that don't belong to any sets.")
	public List<GPRRecordDataModel> getUnclassifiedAdHocRules(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId) throws EQException;

	/**
	 * Loads all monitor data propagation rules defined in the global library
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return All monitor data propagation rules defined in the global library
	 */
	@MBeanOperation("Loads all monitor data propagation rules defined in the global library.")
	public List<GPRRecordDataModel> getMonitorRules(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId)
					throws EQException;

	/**
	 * Loads all ad-hoc data propagation rules defined in the global library
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return All ad-hoc data propagation rules defined in the global library
	 */
	@MBeanOperation("Loads all ad-hoc data propagation rules defined in the global library.")
	public List<GPRRecordDataModel> getAdHocRules(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId)
					throws EQException;

	/**
	 * Returns true if the global library contains any ad-hoc data propagation rules that don't belong to any sets, false otherwise.
	 * 
	 * @param sessionId
	 *            A valid session identifier
	 * @return true if the global library contains any ad-hoc data propagation rules that don't belong to any sets, false otherwise.
	 */
	@MBeanOperation("Returns true if the global library contains any ad-hoc data propagation rules that don't belong to any sets, false otherwise.")
	public boolean hasUnclassified(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId) throws EQException;

	/**
	 * Returns a list of {@link APVRecordDataModel} given the type
	 * 
	 * @param sessionId
	 * @param type
	 * @return
	 * @throws EQException
	 */
	@MBeanOperation("Returns a list of {@link APVRecordDataModel} given the type.")
	public List<APVRecordDataModel> getAPVRecordsByType(
					@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "type", desc = "'CU' - for Customer types, 'DA' - for data types") String type) throws EQException;
}
