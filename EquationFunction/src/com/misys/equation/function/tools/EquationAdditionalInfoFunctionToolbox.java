package com.misys.equation.function.tools;

import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.additionalinfo.EquationAdditionalInfoGroup;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.runtime.UserModifyData;

public class EquationAdditionalInfoFunctionToolbox
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationAdditionalInfoFunctionToolbox.java 14804 2012-11-05 11:57:35Z williae1 $";

	/**
	 * Set the item label in the UserData given the work field name
	 * 
	 * @param equationUnit
	 *            - the Equation unit
	 * @param parameterFile
	 *            - the parameter file
	 * @param parameterValue
	 *            - the parameter value
	 * @param userData
	 *            - the user data
	 * @param workFieldPrefix
	 *            - the work field prefix (e.g. WFLABEL001)
	 * @param lengthOfSuffix
	 *            - the length of suffix (e.g. if the work field name is WFLABEL001, then this should be 3. If the work field field
	 *            name is WFLABEL01, then this should be 2)
	 * 
	 * @throws EQException
	 */
	public static void setLabels(EquationUnit equationUnit, String parameterFile, String parameterValue, UserModifyData userData,
					String workFieldPrefix, int lengthOfSuffix) throws EQException
	{
		EquationAdditionalInfoGroup infoGroup = equationUnit.getRecords().getEquationAdditionalInfoGroup(parameterFile,
						parameterValue);

		for (int i = 1; i <= infoGroup.itemCount() + 1; i++)
		{
			String suffix = Toolbox.padAtFront(String.valueOf(i), "0", lengthOfSuffix);
			String workFieldName = workFieldPrefix + suffix;
			String fieldValue = infoGroup.getItemDescription(i - 1);
			userData.chgFieldDbValue(workFieldName, fieldValue);
		}
	}

}
