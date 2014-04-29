package com.misys.equation.function.beans;

import java.util.List;

import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.function.el.ELRuntime;
import com.misys.equation.function.language.LanguageResources;

public class ReplacementToken
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ReplacementToken.java 10420 2011-02-03 12:22:26Z MACDONP1 $";
	String search;
	String replacement;

	/**
	 * Construct an empty replacement token
	 */
	public ReplacementToken()
	{
		search = "";
		replacement = "";
	}

	public ReplacementToken(String search, String replacement)
	{
		this.search = search;
		this.replacement = replacement;
	}

	/**
	 * Return the search string
	 * 
	 * @return the search string
	 */
	public String getSearch()
	{
		return search;
	}

	/**
	 * Set the search string
	 * 
	 * @param search
	 *            - the search string
	 */
	public void setSearch(String search)
	{
		this.search = search;
	}

	/**
	 * Return the replacement string
	 * 
	 * @return the replacement string
	 */
	public String getReplacement()
	{
		return replacement;
	}

	/**
	 * Set the replacement string
	 * 
	 * @param replacement
	 *            - the replacement string
	 */
	public void setReplacement(String replacement)
	{
		this.replacement = replacement;
	}

	/**
	 * Return the String
	 * 
	 * @return the String
	 */
	@Override
	public String toString()
	{
		return search + ":" + replacement;
	}

	/**
	 * Copy the content of a Replacement Token
	 * 
	 * @param replacementToken
	 *            - the token to be copied
	 */
	public void copyFrom(ReplacementToken replacementToken)
	{
		search = replacementToken.getSearch();
		replacement = replacementToken.getReplacement();
	}

	/**
	 * Return the replacement String
	 * 
	 * @param search
	 *            - the search string
	 * 
	 * @return the replacement String
	 */
	public static String rtvReplacement(List<ReplacementToken> replacementTokens, String fieldType, String search,
					FunctionData functionData)
	{
		String replacementToken = null;
		for (ReplacementToken token : replacementTokens)
		{
			// check as String
			String searchToken = token.getSearch();
			if (searchToken.equals(search))
			{
				replacementToken = token.getReplacement();
			}

			// check as Number
			else if (EqDataType.isNumeric(fieldType))
			{
				try
				{
					double d1 = Double.parseDouble(search);
					double d2 = Double.parseDouble(searchToken);
					if (d1 == d2)
					{
						replacementToken = token.getReplacement();
					}
				}
				catch (Exception e)
				{
					// ignore error and proceed to the next list
				}
			}

			// found?
			if (replacementToken != null)
			{
				String replacementString = ELRuntime.runStringScript(replacementToken, functionData, searchToken,
								LanguageResources.getString("Language.EditEquationAssignment"), null, ELRuntime.REALTYPE_VALUE);
				return replacementString;
			}

		}
		return null;
	}

}
