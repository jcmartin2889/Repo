package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.FunctionData;

public class ContextString
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ContextString.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	/** Literal string delimeter */
	private final static char LITERAL_STR_DELIMETERC = '\'';
	private final static String LITERAL_STR_DELIMETER = "'";
	private final static String LITERAL_ESC_DELIMETER = "''";
	private final static String LITERAL_EMPTY = "''";

	private final List<String> contexts;
	private final List<String> legacyContexts;

	/**
	 * Construct an empty context string
	 */
	public ContextString()
	{
		contexts = new ArrayList<String>();
		legacyContexts = new ArrayList<String>();
	}

	/**
	 * Parse a context string
	 * 
	 * @param contextStr
	 *            - the context string delimeted by CONTEXT_DELIMETER
	 * 
	 * @return the number of elements
	 */
	public static ContextString parseString(String contextStr)
	{
		ContextString cs = new ContextString();
		cs.parse(contextStr);
		return cs;
	}

	/**
	 * Generate the context string given the list of fields and the FunctionData
	 * 
	 * @param contextFields
	 *            - the lsit of fields to generate the context string from
	 * @param functionData
	 *            - the Function Data
	 * 
	 * @return the context String
	 */
	public static ContextString generateContextString(List<String> contextFields, FunctionData functionData)
	{
		ContextString cs = new ContextString();
		for (String contextField : contextFields)
		{
			// trim the context fields otherwise retrieval will not work
			FieldData fieldData = functionData.rtvFieldData(contextField.trim());
			if (fieldData != null)
			{
				String fieldValue = fieldData.getDbValue();
				cs.addNotionalContext(fieldValue);
			}
			else
			{
				String value = contextField;
				int n1 = 0;
				if (value.length() > 1 && value.charAt(0) == LITERAL_STR_DELIMETERC)
				{
					n1 = 1;
				}
				int n2 = value.length();
				if (n1 == 1 && value.length() > 1 && value.charAt(value.length() - 1) == LITERAL_STR_DELIMETERC)
				{
					n2 = value.length() - 1;
				}
				else
				{
					n1 = 0;
				}
				cs.addNotionalContext(value.substring(n1, n2));
			}
		}
		return cs;
	}

	/**
	 * Parse a context string
	 * 
	 * @param contextStr
	 *            - the context string delimeted by CONTEXT_DELIMETER
	 * 
	 * @return the number of elements
	 */
	public int parse(String contextStr)
	{
		contexts.clear();
		legacyContexts.clear();
		int startIndex = 0;
		int endIndex = 0;
		int length = contextStr.length();
		boolean noLiteralDelimeter = true;
		boolean notValidLiteral = false;

		// read all the characters
		while (endIndex < length)
		{
			String c = contextStr.substring(endIndex, endIndex + 1);

			// context delimeter?
			if (noLiteralDelimeter && c.equals(Toolbox.CONTEXT_DELIMETER))
			{
				String s = contextStr.substring(startIndex, endIndex);
				addLiteralContext(s);
				startIndex = endIndex + 1;
				notValidLiteral = false;
				noLiteralDelimeter = true;
			}

			// not a valid literal anymore
			else if (notValidLiteral)
			{
			}

			// literal string delimeter?
			else if (c.equals(LITERAL_STR_DELIMETER))
			{
				// peek on the next character and see if it is another LITERAL_STR_DELIMETER
				boolean escaped = false;
				if ((endIndex + 1) < length)
				{
					c = contextStr.substring(endIndex + 1, endIndex + 2);
					escaped = c.equals(LITERAL_STR_DELIMETER);
				}

				// this is 2 LITERAL_STR_DELIMETER, then assume part of the string
				if (escaped)
				{
					endIndex++; // increment, as we have already peek on the next one
				}

				// toggle between in literal mode or not
				else
				{
					noLiteralDelimeter = !noLiteralDelimeter;
				}
			}

			// any other character, if literal delimeter has not started the field value, then it is not a valid literal string
			// anymore
			else if (noLiteralDelimeter && !c.equals(" "))
			{
				notValidLiteral = true;
			}

			// next index
			endIndex++;
		}

		// add the last string
		if (startIndex != endIndex)
		{
			String s = contextStr.substring(startIndex, endIndex);
			addLiteralContext(s);
		}

		// return number of elements
		return contexts.size();
	}

	/**
	 * Add a context
	 * 
	 * @param context
	 *            - context string to be added
	 */
	public void addActualContext(String context)
	{
		contexts.add(context);
		legacyContexts.add(context);
	}

	/**
	 * Add a literal context. The context is checked if it is enclosed within the LITERAL_STR_DELIMETER. If it is, then it strips
	 * the enclosing LITERAL_STR_DELIMETER. Otherwise, it is the same as addActualContext()
	 * 
	 * @param context
	 *            - context string to be added
	 */
	public void addLiteralContext(String context)
	{
		String s = context;
		int length = s.length();
		if (length >= 2)
		{
			if (s.substring(0, 1).equals(LITERAL_STR_DELIMETER) && s.substring(length - 1, length).equals(LITERAL_STR_DELIMETER))
			{
				// retrieve the string without the enclosing LITERAL_STR_DELIMETER
				s = context.substring(1, length - 1);

				// check if this is a valid literal, there should not be any more LITERAL_STR_DELIMETER within the string unless it
				// is 2 consecutive LITERAL_STR_DELIMETER. Try remove all escaped LITERAL_STR_DELIMETER, and then check if
				// LITERAL_STR_DELIMETER exists exists
				s = s.replaceAll(LITERAL_ESC_DELIMETER, "");
				if (s.indexOf(LITERAL_STR_DELIMETER) >= 0)
				{
					// not a valid literal string, then take the original context as the actual field value
					s = context;
				}
				else
				{
					s = context.substring(1, length - 1);
					s = s.replaceAll(LITERAL_ESC_DELIMETER, LITERAL_STR_DELIMETER);
				}
			}
		}

		// add context
		contexts.add(s);
		legacyContexts.add(context);
	}

	/**
	 * Add a notional context. The context is transformed so that it can be enclosed within LITERAL_STR_DELIMETER (e.g. [ABC's]
	 * becomes [ABC''s]
	 * 
	 * @param context
	 */
	public void addNotionalContext(String context)
	{
		// convert all LITERAL_STR_DELIMETER to LITERAL_ESC_DELIMETER;
		String s = context.replaceAll(LITERAL_STR_DELIMETER, LITERAL_ESC_DELIMETER);
		contexts.add(s);
		legacyContexts.add(context);
	}

	/**
	 * Return the context in array format
	 * 
	 * @return the context in array format
	 */
	public String[] getContextAsArray()
	{
		String[] array = new String[contexts.size()];
		for (int i = 0; i < contexts.size(); i++)
		{
			array[i] = contexts.get(i);
		}
		return array;
	}

	/**
	 * Return the context in array format
	 * 
	 * @return the context in array format
	 */
	public String[] getLegacyContextAsArray()
	{
		String[] array = new String[legacyContexts.size()];
		for (int i = 0; i < legacyContexts.size(); i++)
		{
			array[i] = legacyContexts.get(i);
		}
		return array;
	}

	/**
	 * Return the context string compatible with legacy function
	 * 
	 * @return the context string compatible with legacy function
	 */
	public String getLegacyContextString()
	{
		StringBuilder buffer = new StringBuilder();
		for (String context : legacyContexts)
		{
			buffer.append(context);
			buffer.append(Toolbox.CONTEXT_DELIMETER);
		}
		if (buffer.length() > 1)
		{
			buffer.setLength(buffer.length() - 1);
		}
		return buffer.toString();
	}

	/**
	 * Return the context string
	 * 
	 * @return the context string
	 */
	public String getContextString()
	{
		StringBuilder buffer = new StringBuilder();
		for (String context : contexts)
		{
			buffer.append(LITERAL_STR_DELIMETER);
			buffer.append(context);
			buffer.append(LITERAL_STR_DELIMETER);
			buffer.append(Toolbox.CONTEXT_DELIMETER);
		}
		if (buffer.length() > 1)
		{
			buffer.setLength(buffer.length() - 1);
		}
		return buffer.toString();
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		return getContextString();
	}

	/**
	 * Determine if context is empty
	 * 
	 * @param context
	 *            - the context string to be validated
	 */
	public static boolean isEmpty(String context)
	{
		String s = context.trim();
		return (s.length() == 0) || s.equals(LITERAL_EMPTY);
	}

	public List<String> getContexts()
	{
		return contexts;
	}

}
