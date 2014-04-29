package com.misys.equation.common.access;

import java.util.Hashtable;

import com.misys.equation.common.utilities.Toolbox;

public class EquationWidget
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationWidget.java 11001 2011-05-19 14:03:00Z MACDONP1 $";
	private final static String MARK_ATTRIB = "7 FLD";
	private final static String MARK_SCRIPT = "8 FLD";
	private final static String MARK_WEBF = "A*%%WB";

	private final static String MARK_JSSCRIPT1 = "<script";
	private final static String MARK_JSSCRIPT2 = "</script>";

	private final static char MARK_EOL = '+';
	private final static char MARK_EQUAL = '=';
	private final static char MARK_GT = '>';
	private final static char MARK_QUOTE = '"';
	private final static char MARK_APOS = '\'';

	private final String widgetName;
	private final String widgetDesc;
	private final Hashtable<String, String> widgetAttribs;
	private String widgetScript;

	/**
	 * Create a constructor with all the widget details
	 * 
	 * @param widgetName
	 *            - Widget name
	 * @param scriptX
	 *            - Widget script from the ACEPF file
	 */
	public EquationWidget(String widgetName, String widgetDesc, String script1, String script2, String script3, String script4,
					String script5, String script6, String script7, String script8, String script9, String script10,
					String script11, String script12, String script13, String script14)

	{
		this.widgetName = widgetName;
		this.widgetDesc = widgetDesc;
		this.widgetAttribs = new Hashtable<String, String>();
		this.widgetScript = "";
		setupWebFScript(script1, script2, script3, script4, script5, script6, script7, script8, script9, script10, script11,
						script12, script13, script14);
	}

	/**
	 * Returns the widget name
	 * 
	 * @return the widget name
	 */
	public String getWidgetName()
	{
		return widgetName;
	}

	/**
	 * Returns the widget description
	 * 
	 * @return the widget description
	 */
	public String getWidgetDesc()
	{
		return widgetDesc;
	}

	/**
	 * Returns the widget's attribute
	 * 
	 * @return the widget attribute
	 */
	public Hashtable<String, String> getWidgetAttribs()
	{
		return widgetAttribs;
	}

	/**
	 * Returns the widget script
	 * 
	 * @return the widget script
	 */
	public String getWidgetScript()
	{
		return widgetScript;
	}

	/**
	 * Returns the widget script with string replacement
	 * 
	 * 
	 * @return the widget script
	 */
	public String getWidgetScript(Hashtable<String, String> mapping)
	{
		return Toolbox.replaceWithMapping(new StringBuffer(widgetScript), mapping);
	}

	/**
	 * Setup the script based on the script lines
	 * 
	 * @param scriptX
	 *            - Widget script from the ACEPF file
	 */
	public void setupWebFScript(String script1, String script2, String script3, String script4, String script5, String script6,
					String script7, String script8, String script9, String script10, String script11, String script12,
					String script13, String script14)
	{
		StringBuffer str = new StringBuffer();
		str.append(remove(script1));
		str.append(remove(script2));
		str.append(remove(script3));
		str.append(remove(script4));
		str.append(remove(script5));
		str.append(remove(script6));
		str.append(remove(script7));
		str.append(remove(script8));
		str.append(remove(script9));
		str.append(remove(script10));
		str.append(remove(script11));
		str.append(remove(script12));
		str.append(remove(script13));
		str.append(remove(script14));

		// determine start position of the string
		int nAttrib1 = str.indexOf(MARK_ATTRIB);
		int nScript1 = str.indexOf(MARK_SCRIPT);
		int nAttrib2 = 0;
		int nScript2 = 0;

		// determine end position of the string
		if (nAttrib1 < nScript1)
		{
			nAttrib2 = nScript1 - 1;
			nScript2 = str.length();
		}
		else
		{
			nScript2 = nAttrib1 - 1;
			nAttrib2 = str.length();
		}

		// parse the attribute string
		if (nAttrib1 >= 0)
		{
			setupAttributes(str.substring(nAttrib1 + MARK_ATTRIB.length(), nAttrib2));
		}

		// get the script string
		if (nScript1 >= 0)
		{
			setupScript(str.substring(nScript1 + MARK_SCRIPT.length(), nScript2));
		}
	}

	/**
	 * Setup the attributes details
	 * 
	 * @param attribStr
	 *            - Attribute string
	 */
	public void setupAttributes(String attribStr)
	{
		String attribKey;
		String attribValue;
		int nEqual1 = attribStr.indexOf(MARK_EQUAL);
		int nEqual2 = 0;
		int nStart = 0;
		int nEnd = 0;

		while (nEqual1 != -1)
		{
			// retrieve next equal sign
			nEqual2 = attribStr.indexOf(MARK_EQUAL, nEqual1 + 1);

			// determine starting index of the string on the left hand side
			nStart = rtvLeftSide(attribStr, nEqual1);

			// determine ending index of the string on the right hand side
			if (nEqual2 == -1)
			{
				nEnd = attribStr.length();
			}
			else
			{
				nEnd = rtvLeftSide(attribStr, nEqual2);
			}

			// retrieve the attribute key
			attribKey = attribStr.substring(nStart, nEqual1);

			// retrieve the attribute value, but remove the quotation at either end
			nStart = nEqual1 + 1;
			// .. bypass spaces at the beginning of the string
			for (int i = nStart; i < attribStr.length(); i++)
			{
				if (attribStr.charAt(i) != ' ')
				{
					nStart = i;
					break;
				}
			}
			// .. bypass spaces at the end of the string
			for (int i = nEnd - 1; i > nStart; i--)
			{
				if (attribStr.charAt(i) != ' ')
				{
					nEnd = i + 1;
					break;
				}
			}
			// .. is this a quote or a apostrophe?
			if (attribStr.charAt(nStart) == MARK_APOS || attribStr.charAt(nStart) == MARK_QUOTE)
			{
				nStart++;
				// bypass spaces
				nEnd--;
			}

			attribValue = attribStr.substring(nStart, nEnd);

			// add to the attribute list
			widgetAttribs.put(attribKey, attribValue);

			// next equal sign
			nEqual1 = nEqual2;
		}
	}

	/**
	 * Setup the attributes details
	 * 
	 * @param attribStr
	 *            - Attribute string
	 */
	public void setupScript(String scriptStr)
	{
		StringBuffer str = new StringBuffer();

		// first occurrence
		int nStart1 = scriptStr.indexOf(MARK_JSSCRIPT1);
		int nStart2 = 0;
		int nEnd = 0;

		while (nStart1 != -1)
		{
			nStart2 = scriptStr.indexOf(MARK_GT, nStart1);
			nEnd = scriptStr.indexOf(MARK_JSSCRIPT2, nStart2);

			// add to the string
			str.append(scriptStr.substring(nStart2 + 1, nEnd - 1));
			str.append(" ");

			// next occurence
			nStart1 = scriptStr.indexOf(MARK_JSSCRIPT1, nEnd);
		}

		widgetScript = str.toString();
	}

	/**
	 * Remove unnecessary characters from the script line (as formated in ACE)
	 * 
	 * @param scriptX
	 *            - Widget script from the ACEPF file
	 */
	private StringBuffer remove(String script)
	{
		StringBuffer str = new StringBuffer(script.trim());

		// look for this character A*%%W
		int idx;
		while ((idx = str.indexOf(MARK_WEBF)) != -1)
		{
			str.replace(idx, idx + MARK_WEBF.length(), "");
		}

		// remove the + sign at the start
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == MARK_EOL)
			{
				str.setCharAt(i, ' ');
				break;
			}
		}

		// no more string to process
		if (str.length() <= 0)
		{
			return str;
		}

		// remove the + sign at the end
		if (str.charAt(str.length() - 1) == MARK_EOL)
		{
			str.setCharAt(str.length() - 1, ' ');
		}

		return str;
	}

	/**
	 * Determine the first keyword on the left hand side
	 * 
	 * @param str
	 *            - String to check
	 * @param index
	 *            - Position of the equal sign
	 * 
	 * 
	 */
	private int rtvLeftSide(String str, int index)
	{
		int idx = index - 1;

		// bypass spaces
		for (int i = idx; i >= 0; i--)
		{
			if (str.charAt(i) != ' ')
			{
				idx = i;
				break;
			}
		}

		// get the first space
		for (int i = idx; i >= 0; i--)
		{
			if (str.charAt(i) == ' ')
			{
				idx = i + 1;
				break;
			}
		}

		// cannot be negative
		if (idx < 0)
		{
			idx = 0;
		}

		// return the index
		return idx;
	}
}
