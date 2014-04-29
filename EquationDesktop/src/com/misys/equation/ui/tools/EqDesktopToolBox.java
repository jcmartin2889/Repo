/*
 * 
 */
package com.misys.equation.ui.tools;

import java.io.File;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationGroupConfiguration;
import com.misys.equation.common.access.EquationLogin;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.datastructure.EqDS_DSJOBE;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.ThreadData;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.context.EquationFunctionContext;
import com.misys.equation.function.runtime.FunctionHandlerData;
import com.misys.equation.function.runtime.FunctionHandlerTable;
import com.misys.equation.function.runtime.FunctionMessage;
import com.misys.equation.function.runtime.ScreenSet;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.ui.context.EquationDesktopContext;
import com.misys.equation.ui.forms.LoginForm;

/**
 * 
 */
public class EqDesktopToolBox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqDesktopToolBox.java 16593 2013-06-24 15:32:19Z perkinj1 $";

	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EqDesktopToolBox.class);

	// Name of the style sheet parameter
	public final static String STYLE_PARAMETER = "style";
	private final static String STYLE_NAME_MAIN = "EqUI.css";
	private final static String STYLE_NAME_RTL = "EqUI_AR.css";
	private final static String STYLE_NAME_CLASSIC_MAIN = "EqClassicUI.css";
	private final static String STYLE_NAME_CLASSIC_RTL = "EqClassicUI_AR.css";

	// Name of the style sheet for UXP
	private final static String STYLE_NAME_MAIN_UXP = "EqUI_UXP.css"; // main
	private final static String STYLE_NAME_MAIN_UXP2 = "EqUI_UXP2.css"; // Arabic
	private final static String STYLE_NAME_MAIN_UXP3 = "EqUI_UXP3.css"; // classic
	private final static String STYLE_NAME_MAIN_UXP4 = "EqUI_UXP4.css"; // classic Arabic

	/** Default length of the user field */
	private static final int DEFAULT_USER_LENGTH = 10;
	/** BF user field length */
	private static final int BF_USER_LENGTH = 30;
	/** Default length of the password field */
	private static final int DEFAULT_PASSWORD_LENGTH = 10;
	/** Length of the password field when using BF authentication */
	private static final int BF_PASSWORD_LENGTH = 50;

	/**
	 * Empty constructor
	 */
	private EqDesktopToolBox()
	{
	}

	/**
	 * Protect against cloning
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
		// that'll teach 'em
	}

	/**
	 * Return the domain of a given server
	 * 
	 * @param serverName
	 *            - the server name
	 * 
	 * @return the domain of the server
	 */
	public static String getDomain(String serverName) throws EQException
	{
		String domain = "";
		try
		{
			InetAddress serverAddress = InetAddress.getByName(serverName);
			InetAddress localAddress = InetAddress.getByName("localhost");
			if (serverAddress.getHostAddress().equals(InetAddress.getLocalHost().getHostAddress()))
			{
				domain = localAddress.getCanonicalHostName();
			}
			else
			{
				domain = serverAddress.getCanonicalHostName();
			}
			if (!domain.equals(serverAddress.getHostAddress()))
			{
				int dot = domain.indexOf(".");
				if (dot != -1)
				{
					domain = domain.substring(dot + 1);
				}
			}
		}
		catch (UnknownHostException e)
		{
			throw new EQException(e);
		}
		return domain.toLowerCase();
	}

	/**
	 * Return the number of days between 2 dates
	 * 
	 * @param cal2
	 *            - date 1
	 * @param cal1
	 *            - date 2
	 * 
	 * @return the number of days in between
	 */
	public static long calcDateDuration(GregorianCalendar cal2, GregorianCalendar cal1)
	{
		Date date1 = cal1.getTime();
		Date date2 = cal2.getTime();

		long days = (date2.getTime() - date1.getTime()) / (1000L * 60L * 60L * 24L); // seconds, minutes, hours, days

		return days;
	}

	/**
	 * Return the number of days prior to a given month
	 * 
	 * @param date
	 *            - the date
	 * 
	 * @return the number of days prior to a given month
	 */
	public static long convertDateToDays(GregorianCalendar date)
	{
		final int[] DaysPrior = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334 };
		return ((date.get(Calendar.YEAR) - 1900) * 365 + DaysPrior[date.get(Calendar.MONTH)] + date.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * Allocate the appropriate "slot" for a given date
	 * 
	 * @param date2
	 *            - reference date (e.g. current date)
	 * @param date1
	 *            - reference date 2
	 * 
	 * @return the slot number
	 */
	public static int getSlot(GregorianCalendar date2, GregorianCalendar date1)
	{
		int slot = 0, n1, n2;
		long n = EqDesktopToolBox.calcDateDuration(date2, date1);

		// determine slot based on duration

		// earlier than today's date, then assume today
		if (n < 0)
		{
			slot = 0;
		}
		// Less than a week
		else if (n <= 6)
		{
			slot = (int) n;
		}
		// Last week
		else if (n >= 7 && n <= 13)
		{
			slot = 7;
		}
		// 2 weeks ago
		else if (n >= 14 && n <= 20)
		{
			slot = 8;
		}
		// 3 weeks ago
		else if (n >= 21 && n <= 27)
		{
			slot = 9;
		}
		// Last month and older
		else
		{
			n2 = date2.get(Calendar.MONTH) - 1;
			n1 = date1.get(Calendar.MONTH);

			// Last month
			if (n1 == n2)
			{
				slot = 10;
			}

			// Older
			else
			{
				slot = 11;
			}
		}
		return slot;
	}

	/**
	 * Return the slot name. This must match with the getSlot()
	 * 
	 * @param eqProperties
	 *            - Properties
	 * @param equationUser
	 *            - Equation User
	 * 
	 * @return the slot name
	 */
	public static String[] getSlotName(Properties eqProperties, EquationUser equationUser)
	{
		GregorianCalendar now = new GregorianCalendar();
		String[] strSlotName = new String[12];
		// Create arrays
		for (int i = 0; i < strSlotName.length; i++)
		{
			strSlotName[i] = "";
		}
		// Initialise string
		strSlotName[0] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLTODAY");
		strSlotName[1] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLYEST");
		strSlotName[7] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000030");
		strSlotName[8] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000031");
		strSlotName[9] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000032");
		strSlotName[10] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBL000033");
		strSlotName[11] = EquationCommonContext.getContext().getLanguageResource(equationUser, "GBLOLDER");

		// 2 days ago - 6 days ago
		now.add(Calendar.DATE, -1);
		for (int i = 2; i <= 6; i++)
		{
			now.add(Calendar.DATE, -1);
			strSlotName[i] = EquationCommonContext.getContext().getLanguageResource(equationUser,
							"FRQWK" + Integer.toString(now.get(Calendar.DAY_OF_WEEK)));
		}
		return strSlotName;
	}

	/**
	 * Format a date
	 * 
	 * @param gdate
	 *            - the date
	 * @return the formatted date
	 */
	public static String formatDate(Calendar gdate)
	{
		String str;
		DateFormat df = DateFormat.getDateInstance();
		str = df.format(gdate.getTime());
		return str;
	}

	/**
	 * Format a time
	 * 
	 * @param gdate
	 *            - the date
	 * 
	 * @return the formatted time
	 */
	public static String formatTime(Calendar gdate)
	{
		String str;
		DateFormat df = DateFormat.getTimeInstance();
		str = df.format(gdate.getTime());
		return str;
	}

	/**
	 * Return the equivalent IMG tag HTML
	 * 
	 * @param imagePath
	 *            - image path
	 * @param hoverText
	 *            - hover text
	 * @param id
	 *            - HTML Id
	 * 
	 * @return the equivalent IMG tag HTML
	 */
	public static String formatIntoImageHTML(String imagePath, String hoverText, String id)
	{
		String str;
		str = "<img src=\"" + imagePath + "\" " + "alt=\"" + hoverText + "\" " + "title=\"" + hoverText + "\" " + "id=\"" + id
						+ "\" " + "border=\"0\" " + ">";
		return str;
	}

	/**
	 * Return the equivalent anchor tag HTML
	 * 
	 * @param text
	 *            - the text to display
	 * @param hoverText
	 *            - the hover text
	 * @param onClickAction
	 *            - the onclick action
	 * @param classStyle
	 *            - class style
	 * 
	 * @return the equivalent anchor tag HTML
	 */
	public static String formatIntoAnchorHTML(String text, String hoverText, String onClickAction, String classStyle)
	{
		return formatIntoAnchorHTML(text, hoverText, onClickAction, classStyle, "");
	}

	/**
	 * Return the equivalent anchor tag HTML
	 * 
	 * @param text
	 *            - the text to display
	 * @param hoverText
	 *            - the hover text
	 * @param onClickAction
	 *            - the onclick action
	 * @param classStyle
	 *            - class style
	 * @param others
	 *            - other element
	 * 
	 * @return the equivalent anchor tag HTML
	 */
	public static String formatIntoAnchorHTML(String text, String hoverText, String onClickAction, String classStyle, String others)
	{
		String str;
		str = "<a href=\"" + onClickAction + "\" " + "title=\"" + hoverText + "\" " + "class=\"" + classStyle + "\" " + others
						+ ">" + text + "</a>";
		return str;
	}

	/**
	 * Return the equivalent TR tag HTML with default class name
	 * 
	 * @param label
	 *            - the text to display at column 1
	 * @param value
	 *            - the text to display at column 2
	 * 
	 * @return the equivalent TR tag HTML
	 */
	public static String formatTR(String label, String value)
	{
		String tr;
		tr = "<tr valign='top'>" + "<td class='labelText'>" + label + "</td>" + "<td class='fieldValue'>" + value + "</td>"
						+ "</tr>";
		return tr;
	}

	/**
	 * Return the equivalent TR tag HTML
	 * 
	 * @param label
	 *            - the text to display at column 1
	 * @param value
	 *            - the text to display at column 2
	 * @param labelClass
	 *            - the class for column 1
	 * @param valueClass
	 *            - the class for column 2
	 * 
	 * @return the equivalent TR tag HTML
	 */
	public static String formatTR(String label, String value, String labelClass, String valueClass)
	{
		String tr;
		tr = "<tr valign='top'>" + "<td class='" + labelClass + "'>" + label + "</td>" + "<td class='" + valueClass + "'>" + value
						+ "</td>" + "</tr>";
		return tr;
	}

	/**
	 * Create LI tag HTML
	 * 
	 * @param id
	 *            - the id for the LI tag
	 * @param text
	 *            - the LI tag text to display
	 * 
	 * @return String containing the LI tag HTML
	 */
	public static String formatLI(String id, String text)
	{
		String li;
		li = "<li id='" + id + "'>";
		li += text + "</li>";
		return li;
	}

	/**
	 * Create UL tag HTML
	 * 
	 * @param id
	 *            - the id for the UL tag
	 * @param text
	 *            - the UL tag text to display
	 * 
	 * @return String containing the UL tag HTML
	 */
	public static String formatUL(String id, String text)
	{
		String ul;
		ul = "<ul id='" + id + "'>";
		ul += text + "</ul>";
		return ul;
	}

	/**
	 * Create SPAN tag HTML
	 * 
	 * @param text
	 *            - the text
	 * @param classStyle
	 *            - the class style
	 * 
	 * @return String containing the SPAN tag HTML
	 */
	public static String formatSpan(String text, String classStyle)
	{
		String span = "<span class=\"" + classStyle + "\">" + text + "</span>";
		return span;
	}

	/**
	 * Return the equivalent span tag HTML
	 * 
	 * @param text
	 *            - the text to display
	 * @param hoverText
	 *            - the hover text
	 * @param onClickAction
	 *            - the onclick action
	 * @param classStyle
	 *            - class style
	 * @param others
	 *            - other element
	 * 
	 * @return the equivalent anchor tag HTML
	 */
	public static String formatIntoSpanHTML(String text, String hoverText, String onClickAction, String classStyle, String others)
	{
		String str;
		String onClickActionText = "";
		if (onClickAction.length() > 0)
		{
			onClickActionText = "click=\"" + onClickAction + "\"";
		}
		str = "<span " + onClickActionText + "title=\"" + hoverText + "\" " + "class=\"" + classStyle + "\" " + others + ">" + text
						+ "</span>";
		return str;
	}

	/**
	 * Return the language property
	 * 
	 * @param eqProperties
	 *            - Property
	 * @param languageId
	 *            - language id
	 * @param key
	 *            - code
	 * 
	 * @return the string represented by the code and language id in the property
	 */
	public static String getLanguageText(Properties eqProperties, String languageId, String key)
	{
		String str;
		str = eqProperties.getProperty(languageId + "." + key);
		if (str == null)
		{
			str = eqProperties.getProperty("GB." + key);
		}
		return str;
	}

	/**
	 * Remove the control characters in a string
	 * 
	 * @param str
	 *            - the string
	 * 
	 * @return the string without the control characters
	 */
	public static String stripCtrlChar(String str)
	{
		int p1 = 0;
		int p2 = str.length();
		int n1 = str.charAt(0);
		int n2 = str.charAt(p2 - 1);
		if (n1 >= 128)
		{
			p1 = 1;
		}
		if (n2 >= 128)
		{
			p2 = str.length() - 1;
		}
		return str.substring(p1, p2);
	}

	/**
	 * Return the value stored in the cookie
	 * 
	 * @param key
	 *            - the cookie name
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return the value stored in the cookie
	 */
	public static String getCookie(String key, HttpServletRequest request)
	{
		// Get the value of the cookie
		Cookie[] cookies = request.getCookies();
		String value = null;
		for (int i = cookies.length - 1; i >= 0; i--)
		{
			Cookie currCookie = cookies[i];
			if (currCookie.getName().equals(key))
			{
				value = currCookie.getValue();
			}
		}
		return value;
	}

	/**
	 * Return a string with the specified length
	 * 
	 * @param str
	 *            - the string
	 * @param length
	 *            - the length
	 * 
	 * @return the string with padded spaces or trimmed down string
	 */
	public static String getFixSizeStr(String str, int length)
	{
		if (str.length() >= length)
		{
			return str.substring(0, length);
		}
		else
		{
			StringBuffer sbuf = new StringBuffer(str);
			sbuf.append(padSpaces(length - str.length()));
			return sbuf.toString();
		}
	}

	/**
	 * Return a blank string with the specified length
	 * 
	 * @param spaces
	 *            - the number of blank characters
	 * 
	 * @return a blank string with the specified length
	 */
	public static StringBuffer padSpaces(int spaces)
	{
		StringBuffer sbuf = new StringBuffer(spaces);
		for (int i = 1; i <= spaces; i++)
		{
			sbuf.append(" ");
		}
		return sbuf;
	}

	/**
	 * Return a string in the format of DSWSID
	 * 
	 * @param lf
	 *            - login form
	 * 
	 * @return the fix-length string of 512 with field position of DSWSID
	 */
	public static String generateContextData1(LoginForm lf)
	{
		StringBuffer sbuf = new StringBuffer(512);
		sbuf.append(padSpaces(512));
		// customer mnemonic and location
		sbuf.replace(0, 6, getFixSizeStr(lf.getCtxtCus(), 6));
		sbuf.replace(6, 9, getFixSizeStr(lf.getCtxtClc(), 3));
		// branch
		sbuf.replace(18, 22, getFixSizeStr(lf.getCtxtBrnm(), 4));
		// deal type and deal reference
		sbuf.replace(25, 28, getFixSizeStr(lf.getCtxtDlp(), 3));
		sbuf.replace(28, 41, getFixSizeStr(lf.getCtxtDlr(), 13));
		// account
		sbuf.replace(71, 75, getFixSizeStr(lf.getCtxtAbc(), 4));
		sbuf.replace(75, 81, getFixSizeStr(lf.getCtxtAnc(), 6));
		sbuf.replace(81, 84, getFixSizeStr(lf.getCtxtAns(), 3));
		// external account number
		sbuf.replace(414, 434, getFixSizeStr(lf.getCtxtEan(), 20));
		return (sbuf.toString());
	}

	/**
	 * Return a string in the format of DSWSI2
	 * 
	 * @param lf
	 *            - login form
	 * 
	 * @return the fix-length string of 512 with field position of DSWSI2
	 */
	public static String generateContextData2(LoginForm lf)
	{
		StringBuffer sbuf = new StringBuffer(512);
		// this is currently not being. Just set the value to 1 space
		sbuf.append(" ");
		return (sbuf.toString());
	}

	/**
	 * Print the list of attributes in a HTTP request
	 * 
	 * @param request
	 *            - HTTP request
	 */
	public static void printHttpServletRequestAttributes(HttpServletRequest request)
	{
		Enumeration<?> list = request.getSession().getAttributeNames();
		while (list.hasMoreElements())
		{
			String object = (String) list.nextElement();
			if (LOG.isDebugEnabled())
			{
				LOG.debug("   Request Attribute [" + object + "] " + request.getSession().getAttribute(object));
			}
		}
	}

	/**
	 * Return the string representation of an array
	 * 
	 * @param array
	 *            - list of messages
	 * @param delimiter
	 *            - the string is enclosed in this delimiter
	 * 
	 * @return the string representation of an array
	 */
	public static String functionMessageToString(List<FunctionMessage> array, String delimiter)
	{
		StringBuffer str = new StringBuffer();
		str.append("[");
		for (int i = 0; i < array.size(); i++)
		{
			if (i != 0)
			{
				str.append(",");
			}
			str.append(delimiter);
			str.append(Toolbox.rplSlashWith2Slash(Toolbox.rplQuote(Toolbox.rplSingleQuoteWithSlashSingleQuote(array.get(i)
							.rtvFormattedText()))));
			str.append(delimiter);
		}
		str.append("]");
		return str.toString();
	}

	/**
	 * Remove the dots
	 * 
	 * @param str
	 *            - the String (e.g. IP address)
	 * 
	 * @return the str without any dots
	 */
	public static String removeDot(String str)
	{
		StringBuilder s = new StringBuilder(str);
		for (int i = 0; i < s.length(); i++)
		{
			if (s.charAt(i) == '.')
			{
				s.deleteCharAt(i);
			}
		}
		return s.toString();
	}

	/**
	 * Return all the parameters in URL format
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return all the parameters in URL format
	 */
	@SuppressWarnings("unchecked")
	public static String getParameterAsURL(HttpServletRequest request)
	{
		StringBuilder buffer = new StringBuilder("?");
		Enumeration<String> paramEnum = request.getParameterNames();

		// loop through all parameters
		while (paramEnum.hasMoreElements())
		{
			String parameterName = paramEnum.nextElement();
			String parameterValue = request.getParameter(parameterName);
			buffer.append(parameterName);
			buffer.append("=");

			// encode the value (e.g. convert some characters to hex value)
			try
			{
				buffer.append(URLEncoder.encode(parameterValue, "UTF-8"));
			}
			catch (Exception e)
			{
				buffer.append(parameterValue);
			}

			buffer.append("&");
		}

		// no parameters?
		if (buffer.length() == 1)
		{
			return "";
		}

		// remove trailing &
		buffer.setLength(buffer.length() - 1);

		// return string
		return buffer.toString();
	}

	/**
	 * Return the name of the CSS style - EqUI.css
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return all the parameters in URL format
	 */
	public static String getStyleSheetMain(HttpServletRequest request)
	{
		return (String) request.getSession().getAttribute(STYLE_NAME_MAIN);
	}

	/**
	 * Return the name of the CSS style for right-to-left screen - EqUI_AR.css
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return all the parameters in URL format
	 */
	public static String getStyleSheetRTL(HttpServletRequest request)
	{
		return (String) request.getSession().getAttribute(STYLE_NAME_RTL);
	}

	/**
	 * Return the name of the CSS style for classic WebFacing - EqClassicUI.css
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return all the parameters in URL format
	 */
	public static String getStyleSheetClassicMain(HttpServletRequest request)
	{
		return (String) request.getSession().getAttribute(STYLE_NAME_CLASSIC_MAIN);
	}

	/**
	 * Return the name of the CSS style for right-to-left classic WebFacing - EqClassicUI_AR.css
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return all the parameters in URL format
	 */
	public static String getStyleSheetClassicRTL(HttpServletRequest request)
	{
		return (String) request.getSession().getAttribute(STYLE_NAME_CLASSIC_RTL);
	}

	/**
	 * Initialise the style sheet to use
	 * 
	 * @param request
	 *            - the HTTP request
	 * @param servletContext
	 *            - the ServLet context
	 * @param systemId
	 *            - the system id
	 * @param unitId
	 *            - the unit id
	 * @param userId
	 *            - the user id
	 */
	public static void initStyleSheetMain(HttpServletRequest request, ServletContext servletContext, String systemId,
					String unitId, String userId)
	{
		// get the style from the parameter
		String style = request.getParameter(STYLE_PARAMETER);

		// not specified, then default to blank
		if (style == null)
		{
			style = "";
		}
		String styleMain = null;
		String styleRTL = null;
		String styleClassicMain = null;
		String styleCLassicRTL = null;

		EquationLogin equationLogin = (EquationLogin) request.getSession().getAttribute("equationLogin");

		// Use different style sheet for UXP.
		if (equationLogin != null && equationLogin.getUserInterfaceType() == EquationCommonContext.UI_UXP)
		{
			// initialise the styles
			styleMain = getStyleSheetToUse(style, servletContext, STYLE_NAME_MAIN_UXP, ".css", systemId, unitId, userId);
			styleRTL = getStyleSheetToUse(style, servletContext, STYLE_NAME_MAIN_UXP2, "2.css", systemId, unitId, userId);
			styleClassicMain = getStyleSheetToUse(style, servletContext, STYLE_NAME_MAIN_UXP3, "3.css", systemId, unitId, userId);
			styleCLassicRTL = getStyleSheetToUse(style, servletContext, STYLE_NAME_MAIN_UXP4, "4.css", systemId, unitId, userId);
		}
		else
		{
			// initialise the styles
			styleMain = getStyleSheetToUse(style, servletContext, STYLE_NAME_MAIN, ".css", systemId, unitId, userId);
			styleRTL = getStyleSheetToUse(style, servletContext, STYLE_NAME_RTL, "2.css", systemId, unitId, userId);
			styleClassicMain = getStyleSheetToUse(style, servletContext, STYLE_NAME_CLASSIC_MAIN, "3.css", systemId, unitId, userId);
			styleCLassicRTL = getStyleSheetToUse(style, servletContext, STYLE_NAME_CLASSIC_RTL, "4.css", systemId, unitId, userId);
		}

		// set the styles
		request.getSession().setAttribute(STYLE_NAME_MAIN, styleMain);
		request.getSession().setAttribute(STYLE_NAME_RTL, styleRTL);
		request.getSession().setAttribute(STYLE_NAME_CLASSIC_MAIN, styleClassicMain);
		request.getSession().setAttribute(STYLE_NAME_CLASSIC_RTL, styleCLassicRTL);
	}
	/**
	 * Determine the style sheet to use
	 * 
	 * @param style
	 *            - the CSS style
	 * @param servletContext
	 *            - the ServLet context
	 * @param defaultStyle
	 *            - the default style
	 * @param suffix
	 *            - the suffix
	 * @param systemId
	 *            - the system id
	 * @param unitId
	 *            - the unit id
	 * @param userId
	 *            - the user id
	 * 
	 * @return the style sheet to use
	 */
	private static String getStyleSheetToUse(String style, ServletContext servletContext, String defaultStyle, String suffix,
					String systemId, String unitId, String userId)
	{
		// Default style name
		String styleName = defaultStyle;

		// Style has been specified as a parameter?
		if (style != null && style.length() > 0 && isFileInServer(servletContext, style + suffix))
		{
			styleName = style + suffix;
		}

		// Style has been defined in the property file
		else
		{
			// get system.unit.user
			String s = EquationCommonContext.getConfigProperty("style." + systemId + "." + unitId + "." + userId);
			s = s.trim();

			// get system.unit
			if (s.length() == 0)
			{
				s = EquationCommonContext.getConfigProperty("style." + systemId + "." + unitId);
				s = s.trim();
			}

			// get system
			if (s.length() == 0)
			{
				s = EquationCommonContext.getConfigProperty("style." + systemId);
				s = s.trim();
			}

			// system wide
			if (s.length() == 0)
			{
				s = EquationCommonContext.getConfigProperty("style");
				s = s.trim();
			}

			// valid style?
			if (s.length() > 0 && isFileInServer(servletContext, s + suffix))
			{
				styleName = s + suffix;
			}
		}

		// return the style
		return getStyleSheetEqFullPath(styleName);
	}

	/**
	 * Return the style sheet full path
	 * 
	 * @param styleName
	 *            - the style sheet name
	 * 
	 * @return the style sheet full path
	 */
	private static String getStyleSheetEqFullPath(String styleName)
	{
		return "/equation/styles/" + styleName;
	}

	/**
	 * Check if file exists
	 * 
	 * @param servletContext
	 *            - the Servlet context
	 * @param filename
	 *            - the file name
	 * @return boolean
	 */
	private static boolean isFileInServer(ServletContext servletContext, String filename)
	{
		String path = servletContext.getRealPath(getStyleSheetEqFullPath(filename));
		File file = new File(path);
		return file.exists();
	}

	/**
	 * This method will return the library/output queue given the user Id.
	 * 
	 * @param userId
	 *            - the User Id
	 * 
	 * @return Output Queue
	 */
	public static String getInitialOutputQueue(String userId)
	{
		// Get the user level output queue
		String outputQueue = null;

		// User Id must be in lower case
		if (userId == null)
		{
			return "";
		}
		if (EquationFunctionContext.getContext().isEquationAuthentication())
		{
			userId = userId.toLowerCase();
		}

		outputQueue = EquationCommonContext.getConfigProperty(EquationGroupConfiguration.USER_LEVEL_OUTPUT_QUEUE_PROPERTY + userId);

		if (outputQueue == null || outputQueue.trim().length() == 0)
		{

			// Get the group level output queue
			outputQueue = getGroupOutputQueue(userId);

			if (outputQueue.length() == 0)
			{
				// Get the default level output queue
				outputQueue = EquationCommonContext
								.getConfigProperty(EquationGroupConfiguration.DEFAULT_LEVEL_OUTPUT_QUEUE_PROPERTY);
			}

		}

		// Return blank if the output queue was not found
		if (outputQueue == null)
		{
			return "";
		}

		// Check if the library has been specified
		else if (outputQueue.trim().length() > 0)
		{
			if (outputQueue.indexOf("/") < 0)
			{
				outputQueue = EquationGroupConfiguration.getContext().getDefaultLibrary() + "/" + outputQueue.trim();
			}
			else if (outputQueue.startsWith("/"))
			{
				outputQueue = EquationGroupConfiguration.getContext().getDefaultLibrary() + outputQueue.trim();
			}
		}

		return outputQueue;

	}

	/**
	 * This method retrieves the output queue of the group where the user belongs to
	 * 
	 * @param userId
	 *            - the User Id
	 * 
	 * @return the Group Output Queue, otherwise returns a blank value
	 */
	private static String getGroupOutputQueue(String userId)
	{

		String groupOutputQueue = null;
		String userGroup = EquationGroupConfiguration.getContext().getUserGroup(userId);
		if (userGroup != null)
		{
			groupOutputQueue = EquationCommonContext.getConfigProperty(EquationGroupConfiguration.GROUP_LEVEL_OUTPUT_QUEUE_PROPERTY
							+ userGroup);

			if (groupOutputQueue == null)
			{
				return "";
			}
		}

		return groupOutputQueue.trim();
	}
	/**
	 * Setup the ThreadData with EquationLogin
	 * 
	 * @param request
	 *            - the HTTP request
	 */
	public static void setupThreadData(HttpServletRequest request)
	{
		if (request != null)
		{
			EquationLogin equationLogin = (EquationLogin) request.getSession().getAttribute(EquationCommonContext.LOGIN_KEY);
			if (equationLogin != null)
			{
				ThreadData.set(EquationDesktopContext.SESSION_LOGIN, equationLogin);
			}
		}
	}

	/**
	 * Setup the ThreadData with EquationLogin
	 * 
	 * @param sessionIdentifier
	 *            - the session identifier
	 */
	public static void setupThreadData(String sessionIdentifier)
	{
		if (sessionIdentifier != null)
		{
			EquationLogin equationLogin = EquationCommonContext.getContext().getEquationLogin(sessionIdentifier);
			if (equationLogin != null)
			{
				ThreadData.set(EquationDesktopContext.SESSION_LOGIN, equationLogin);
			}
		}
	}

	/**
	 * Gets the plugin version of the function
	 * 
	 * @return functionPluginVer - the plugin version of the function
	 */
	public static String getFunctionPluginVersion(FunctionHandlerData fhd)
	{
		String functionPluginVer = "";
		ScreenSet screenSetMain = fhd.getScreenSetHandler().rtvScreenSetMain();
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();
		if (screenSetMain.getId() != screenSet.getId() && !screenSet.getFunction().getPluginVersion().trim().isEmpty())
		{
			functionPluginVer = screenSetMain.getFunction().getPluginVersion() + "(" + screenSet.getFunction().getPluginVersion()
							+ ")";
		}
		else
		{
			functionPluginVer = screenSetMain.getFunction().getPluginVersion();
		}
		return functionPluginVer;
	}

	/**
	 * Gets the mode of the function
	 * 
	 * @return functionMode - Add, Maint, Del, Enq
	 */
	public static String getFunctionMode(FunctionHandlerData fhd, EquationUser equationUser)
	{
		String functionMode = "";
		ScreenSet screenSet = fhd.getScreenSetHandler().rtvScrnSetCurrent();

		if (screenSet.getMode().equalsIgnoreCase(IEquationStandardObject.FCT_ADD))
		{
			functionMode = equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.MENT);
		}
		else if (screenSet.getMode().equalsIgnoreCase(IEquationStandardObject.FCT_DEL))
		{
			functionMode = equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.MDEL);
		}
		else if ((screenSet.getMode().equalsIgnoreCase(IEquationStandardObject.FCT_ENQ)))
		{
			functionMode = equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.MENQ);
		}
		else
		{
			functionMode = equationUser.getDsjobctle().getFieldValue(EqDS_DSJOBE.MMNT);
		}

		return functionMode;
	}

	/**
	 * Returns the length of the user id field.
	 * 
	 * @return the length of the user id field
	 */
	public static String getUserIdLength()
	{
		int length = EquationFunctionContext.getContext().isEquationAuthentication() ? DEFAULT_USER_LENGTH : BF_USER_LENGTH;
		return Integer.toString(length);
	}

	/**
	 * Returns the length of the password field.
	 * 
	 * Note: This is only used for the maxlength. The jsp will use the user field length as the size of the password field as well
	 * as the user field.
	 * 
	 * @return the length of the password field
	 */
	public static String getPasswordLength()
	{
		int length = EquationFunctionContext.getContext().isEquationAuthentication() ? DEFAULT_PASSWORD_LENGTH : BF_PASSWORD_LENGTH;
		return Integer.toString(length);
	}

	/**
	 * Returns the user id used for logging on to the Equation Desktop.
	 * 
	 * @param request
	 *            The HttpServletRequest
	 * @return the login user
	 */
	public static String getDesktopLoginUser(HttpServletRequest request)
	{
		String sessionId = EquationDesktopContext.getSessionId(request);
		return EquationFunctionContext.getContext().getLoginUserBySessionId(sessionId);
	}

	/**
	 * Put the BFEQ session into the HTTP session
	 * 
	 * @param request
	 *            - the HTTP request
	 * @param sessionId
	 *            - the session id
	 * @param force
	 *            - true if to force refresh the one in HTTP session
	 */
	public static boolean putBFEQIntoHTTPSession(HttpServletRequest request, String sessionId, boolean force) throws EQException
	{
		HttpSession session = request.getSession();
		boolean sessionExist = true;

		// Retrieve the details
		EquationLogin equationLogin = (EquationLogin) request.getSession().getAttribute("equationLogin");
		EquationUser equationUser = (EquationUser) request.getSession().getAttribute("equationUser");
		EquationUnit equationUnit = (EquationUnit) request.getSession().getAttribute("equationUnit");
		FunctionHandlerTable functionHandlerTable = (FunctionHandlerTable) request.getSession().getAttribute(
						FunctionRuntimeToolbox.NAME);

		if (equationUser == null || force)
		{
			LOG.info("EquationUser from HTTP Session is null or force refresh");
			equationUser = EquationCommonContext.getContext().getEquationUser(sessionId);
			session.setAttribute("equationUser", equationUser);

			if (equationUser == null)
			{
				sessionExist = false;
			}
		}

		if (equationLogin == null || force)
		{
			LOG.info("EquationLogin from HTTP session is null or force refresh");
			equationLogin = EquationCommonContext.getContext().getEquationLogin(sessionId);
			session.setAttribute("equationLogin", equationLogin);
			// equationLogin.setSessionType(EquationCommonContext.SESSION_FULL_DESKTOP);
		}

		if (equationUnit == null || force)
		{
			LOG.info("EquationUnit from HTTP session is null or force refresh");
			equationUnit = EquationCommonContext.getContext().getEquationUnit(sessionId);
			session.setAttribute("equationUnit", equationUnit);
		}

		if (functionHandlerTable == null || force)
		{
			LOG.info("FunctionHandlerTable from HTTP session is null or force refresh");
			functionHandlerTable = (FunctionHandlerTable) EquationFunctionContext.getContext().getFunctionHandlerTable(sessionId);
			request.getSession().setAttribute(FunctionRuntimeToolbox.NAME, functionHandlerTable);
		}

		return sessionExist;
	}

	/**
	 * Determine if style has already been initialised or not
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return true if style has already been initialised
	 */
	public static boolean isStyleSheetInitialise(HttpServletRequest request)
	{
		String style = getStyleSheetMain(request);
		if (style == null)
		{
			return false;
		}
		if (style.trim().length() == 0)
		{
			return false;
		}
		return true;
	}

	/**
	 * Close WebFacing job
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return true if WebFacing session is closed
	 */
	public static boolean closeWebFacingJob(HttpSession httpSession)
	{
		try
		{
			// try to load WFClient
			LOG.info("Trying to determine if WebFacing is installed");
			Class httpSessionManagerClass = Class.forName("com.ibm.as400ad.webfacing.runtime.httpcontroller.HttpSessionManager");
			if (httpSessionManagerClass != null)
			{
				LOG.info("WebFacing is installed, closing WebFacing job");
				Method getWFClientMethod = httpSessionManagerClass.getMethod("getWFClient", javax.servlet.http.HttpSession.class);
				Object wfclient = getWFClientMethod.invoke(null, httpSession);

				if (wfclient != null)
				{
					Method browserLogoffMethod = wfclient.getClass().getMethod("browserLogoff");
					browserLogoffMethod.invoke(wfclient);
					return true;
				}
			}
		}
		catch (ClassNotFoundException e)
		{
			LOG.info("WebFacing clas not found");
		}
		catch (Exception e)
		{
		}
		return false;
	}

	/**
	 * Return the HTML DIR attribute
	 * 
	 * @param RTL
	 *            - left to right?
	 * 
	 * @return the DIR attribute
	 */
	public static String getHTMLDirAttribute(boolean RTL)
	{
		if (RTL)
		{
			return "dir=RTL";
		}
		else
		{
			return "";
		}
	}

	/**
	 * Check if WebFacing session is in the HTTP session
	 * 
	 * @param httpSession
	 *            - the HTTP Session
	 * 
	 * @return true if WebFacing is installed
	 */
	public static boolean isWebFacingInHTTPSession(HttpSession httpSession)
	{
		return httpSession.getServletContext().getAttribute("WFApplication") != null;
	}

	/**
	 * Return the user style sheet
	 * 
	 * @param request
	 *            - the HTTP request
	 * 
	 * @return the user style sheet
	 */
	public static String getStyleSheetUserStyle(HttpServletRequest request)
	{
		EquationLogin equationLogin = (EquationLogin) request.getSession().getAttribute("equationLogin");

		// For desktop, user style sheet is userUI.css
		if (equationLogin == null || equationLogin.chkDesktopUserInterface())
		{
			return "/equation/styles/userUI.css";
		}

		// For UXP, return any other name
		// As of now, the bank is not allowed to change the UXP style sheet. This name is for future-use,
		// when UXP will allow bank to have their own style
		else
		{
			return "/equation/styles/userUIUXP.css";
		}
	}

}