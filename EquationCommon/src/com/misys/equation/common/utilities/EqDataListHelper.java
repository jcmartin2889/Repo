package com.misys.equation.common.utilities;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;

public class EqDataListHelper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDataListHelper.java 10420 2011-02-03 12:22:26Z MACDONP1 $";

	// pre-defined names
	private final static String preDefinedFieldsName[] = { "$USER4", "$USER", "$PDATEDB", "$PDDDMMMYY", "$LCY", "$BCY", "$DBRNM" };

	// length of the data of the equivalent pre-defined names
	private final static int preDefinedFieldsLen[] = { 4, 10, 7, 7, 3, 3, 4 };

	/**
	 * Return the length of the pre-defined field name
	 * 
	 * @param field
	 *            - the pre-defined field name
	 * @return
	 */
	public static int getLength(String field)
	{
		// non?
		if (field.length() == 0)
		{
			return 0;
		}

		// constant?
		if (field.charAt(0) == '\'')
		{
			return field.length() - 2; // less the quotes at both end
		}

		// pre-defined fields?
		for (int i = 0; i < preDefinedFieldsName.length; i++)
		{
			if (preDefinedFieldsName[i].equals(field))
			{
				return preDefinedFieldsLen[i];
			}
		}

		return 0;
	}

	public static String getFieldValue(EquationUser eqUser, String keyField)
	{
		EquationStandardSession session = eqUser.getSession();

		if (keyField.equals(preDefinedFieldsName[0]))
		{
			return "'" + session.getUserId().substring(0, 4) + "'";
		}
		else if (keyField.equals(preDefinedFieldsName[1]))
		{
			return "'" + session.getUserId() + "'";
		}
		else if (keyField.equals(preDefinedFieldsName[2]))
		{
			return "'" + session.getUnit().getProcessingDateCYYMMDD() + "'";
		}
		else if (keyField.equals(preDefinedFieldsName[3]))
		{
			return "'" + session.getUnit().getProcessingDate() + "'";
		}
		else if (keyField.equals(preDefinedFieldsName[4]))
		{
			return "'" + session.getUnit().getLocalCurrency() + "'";
		}
		else if (keyField.equals(preDefinedFieldsName[5]))
		{
			return "'" + session.getUnit().getBaseCurrency() + "'";
		}
		else if (keyField.equals(preDefinedFieldsName[6]))
		{
			return "'" + eqUser.getInputBranch() + "'";
		}
		else
		{
			return keyField;
		}
	}
}
