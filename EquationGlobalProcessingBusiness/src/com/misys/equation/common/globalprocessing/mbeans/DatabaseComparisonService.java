package com.misys.equation.common.globalprocessing.mbeans;

import java.util.List;

import com.misys.equation.common.access.UnitNotAvailableException;
import com.misys.equation.common.globalprocessing.RecordNotFoundException;
import com.misys.equation.common.globalprocessing.audit.CharacterSubstitutionRequiredException;
import com.misys.equation.common.globalprocessing.audit.SystemUnit;
import com.misys.equation.common.internal.eapi.core.EQException;

/**
 * Contains service methods for performing database file comparison functions.
 * 
 * @author berzosa
 */
public interface DatabaseComparisonService
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: DatabaseComparisonService.java 9962 2010-11-18 17:31:39Z MACDONP1 $";

	/**
	 * This method performs a generic database SELECT query and returns a list of Object[] arrays representing all the rows
	 * returned.
	 * <p>
	 * NOTE: This method is not tied to any unit, hence, does not use connection pooling -- but it does allow you to select from any
	 * fully-qualified (library + file name) file.
	 * 
	 * @param query
	 *            The SELECT statement to perform to retrieve records.
	 * @return The results of a query in an list of object arrays representing all the returned rows.
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("This method performs a generic database SELECT query and returns a list of Object[] arrays representing all the rows returned")
	public List<Object[]> runGenericQuery(@Meta(name = "sessionId", desc = "The Session identifier") String sessionId,
					@Meta(name = "systemId", desc = "The System ID") String systemId,
					@Meta(name = "unitId", desc = "The Unit ID") String unitId, @Meta(name = "query", desc = "Query") String query)
					throws EQException, UnitNotAvailableException;

	/**
	 * Performs a copy of a record from a source unit to a target unit given the API Key, API Ref, and Update API.
	 * 
	 * @param sessionId
	 *            A valid session ID obtained from EquationCommonContext
	 * @param fromUnit
	 *            The unit to copy the record from
	 * @param toUnit
	 *            The unit to copy the record to
	 * @param key
	 *            The key value of the record
	 * @param fieldsToInclude
	 *            The API fields to include for copying to the target unit -- These should only include the maintainable fields
	 * @param apiRef
	 *            The API Identifier (APJARF)
	 * @param performCCSIDSubstitution
	 *            If 'true', then characters that can't be converted to the target CCSID will be replaced with a substitution
	 *            character, otherwise, an {@link EQException} will be thrown if there are characters that can't be converted to the
	 *            target CCSID.
	 * @throws EQException
	 *             If any error occurs while copying the record from the source unit to the target unit. *
	 * @throws CharacterSubstitutionRequiredException
	 *             If character substitution was required during CCSID conversion from the source unit CCSID to the target unit
	 *             CCSID but that the performCCSIDSubstitution flag was set to 'false'.
	 * @throws RecordNotFoundException
	 *             If the record in the source (from) unit with the given key could not be found.
	 * @return A non-blank error message if any error occurs
	 * @throws UnitNotAvailableException
	 */
	@MBeanOperation("Performs a copy of a record from a source unit to a target and returns error messages generated per target.")
	public String copyRecord(
					@Meta(name = "sessionId", desc = " A valid session ID obtained from EquationCommonContext") String sessionId,
					@Meta(name = "fromUnit", desc = "The unit to copy the record from") SystemUnit fromUnit,
					@Meta(name = "toUnit", desc = "The unit to copy the record to") SystemUnit toUnit,
					@Meta(name = "key", desc = "The key value of the record") String key,
					@Meta(name = "fieldsToInclude", desc = "The API fields to include for copying to the target unit") List<String> fieldsToInclude,
					@Meta(name = "apiRef", desc = "The API Identifier (APJARF)") String apiRef,
					@Meta(name = "performCCSIDSubstitution", desc = "If character substitution should be performed") boolean performCCSIDSubstitution)
					throws EQException, CharacterSubstitutionRequiredException, UnitNotAvailableException;
}
