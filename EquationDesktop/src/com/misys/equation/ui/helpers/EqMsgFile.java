package com.misys.equation.ui.helpers;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.MessageFile;
import com.ibm.as400.access.ObjectDescription;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.ui.tools.EqDesktopToolBox;

public class EqMsgFile
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqMsgFile.java 9962 2010-11-18 17:31:39Z MACDONP1 $";

	private final MessageFile msgFile;
	private AS400Message msgD;
	private String eqMsgStr;
	boolean rtl = false;
	/**
	 * Construct a message file based on the specified parameter
	 * 
	 * @param msgf
	 *            - the message file
	 */
	public EqMsgFile(MessageFile msgf)
	{
		msgFile = msgf;
		msgD = null;
	}

	/**
	 * Determine whether the KSMMSGF exists in the specified path
	 * 
	 * @param eqAS400
	 *            - the AS400 system
	 * @param path
	 *            - the path
	 * @return true if KSMMSGF exists
	 */
	public boolean existsKSM(AS400 eqAS400, String path)
	{
		ObjectDescription objd;
		objd = new ObjectDescription(eqAS400, path, "KSMMSGF", "MSGF");
		try
		{
			return (objd.exists());
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
		}
		return false;
	}

	/**
	 * Retrieve an entry from the message file
	 * 
	 * @param msg
	 *            - the message to retrieve
	 * @param rtl
	 *            - right to left?
	 * 
	 * @throws Exception
	 */
	public void getEqMsgD(String msg, boolean rtl) throws Exception
	{
		int startIndex = 0;

		// msg contains the standard KSM message - "KSMxxxx Description ..."
		this.rtl = false;
		eqMsgStr = msg;

		// get starting position of the message id
		while (msg.charAt(startIndex) == ' ')
		{
			startIndex++;
		}
		String msgID = msg.substring(startIndex, startIndex + 7);

		// String msgTxt = msg.substring(startIndex + 7, msg.length());
		// if the message starts with KAP, then in KAPMSG
		if (msgID.startsWith("KAP"))
		{
			msgFile.setPath("/QSYS.LIB/*LIBL.LIB/KAPMSG.MSGF");
		}

		// get the message
		try
		{
			msgD = msgFile.getMessage(msgID);
		}
		// catch any exception and see if there are still processing needed
		catch (Exception e)
		{
			// during RTL, then check if the KSM id is at the end!
			if (rtl)
			{
				// get starting position of the message id
				msg = msg.trim();
				startIndex = msg.length() - 1;
				while (msg.charAt(startIndex) == ' ')
				{
					startIndex--;
				}
				msgID = msg.substring(startIndex - 6, msg.length());
				// msgTxt = msg.substring(0, startIndex - 5);
				msgD = msgFile.getMessage(msgID);
				this.rtl = rtl;
			}
			// otherwise, just pass the exception to the calling program
			else
			{
				EquationCommonContext.getContext().getLOG().error(e);
				throw e;
			}
		}
	}

	/**
	 * Set the message id
	 * 
	 * @param msgid
	 *            - the message id
	 * @param msg
	 *            - the message
	 * 
	 * @throws Exception
	 */
	public void setMsgId(String msgid, String msg) throws Exception
	{
		// this contain the actual message displayed
		eqMsgStr = msg;
		msgD = msgFile.getMessage(msgid);
	}

	/**
	 * Return the message id
	 * 
	 * @return the message id
	 */
	public String getId()
	{
		// message description not setup
		if (msgD == null)
		{
			return ("");
		}

		// return Id
		return (msgD.getID());
	}

	/**
	 * Return the message text
	 * 
	 * @return the message text
	 */
	public String getText()
	{
		// message description not setup
		if (msgD == null)
		{
			return ("");
		}

		// return Id
		return (msgD.getText());
	}

	/**
	 * Return the actual message displayed
	 * 
	 * @return the actual message displayed
	 */
	public String getEqMsgStr()
	{
		return eqMsgStr;
	}

	/**
	 * Return the message severity
	 * 
	 * @return the message severity
	 */
	public int getSeverity()
	{
		// message description not setup
		if (msgD == null)
		{
			return -1;
		}

		// return severity
		return (msgD.getSeverity());
	}

	/**
	 * Return the message type
	 * 
	 * @return the message type
	 */
	public int getType()
	{
		// message description not setup
		if (msgD == null)
		{
			return -1;
		}

		// return severity
		return (msgD.getType());
	}

	/**
	 * Return the second level text
	 * 
	 * @return the second level text
	 */
	public String getHelp()
	{
		String str;
		// message description not setup
		if (msgD == null)
		{
			return ("");
		}

		// check if 2nd level text has any substitution text
		str = msgD.getHelp();
		if (!isSubstExists(str))
		{
			return str;
		}

		// rtl, the control character is in a different position!
		str = EqDesktopToolBox.stripCtrlChar(msgD.getText());
		if (rtl)
		{
			str = str.substring(0, str.length() - 8) + " " + str.substring(str.length() - 7);
		}

		// since the message is retrieved from the message file, it has the substitution parameter
		// (e.g. &1, &2, etc) in it, instead of the actual message (which exists in joblog but
		// we dont know where). Do best guess to determine the substitution text based on the
		// text available
		String[] subsText = getSubstText(eqMsgStr, str);

		// replace the text
		str = msgD.getHelp();
		for (int i = 0; i < subsText.length; i++)
		{
			String field = "&" + String.valueOf(i + 1);
			if (subsText[i] == null)
			{
				subsText[i] = "";
			}
			str = str.replaceAll(field, subsText[i]);
		}
		return str;
	}

	/**
	 * Predict the substitution text based on the original message received
	 * 
	 * @param strFormatted
	 *            - the full message
	 * @param strUnformatted
	 *            - the message from the message file
	 * 
	 * @return the list of substitution text
	 */
	public String[] getSubstText(String strFormatted, String strUnformatted)
	{
		// initialise the subst text with empty spaces
		String strSubst;
		String[] arrSubst = new String[9];
		for (int i = 1; i < 9; i++)
		{
			arrSubst[i] = "";
		}
		// search for all substitution field (&x)
		int start = 0;
		int pos;
		try
		{
			while ((pos = strUnformatted.indexOf("&", start)) >= 0)
			{
				// end of string, then out
				if (pos >= strUnformatted.length() - 1)
				{
					break;
				}

				// substitution field?
				if (isSubstField(strUnformatted.substring(pos, pos + 2)))
				{
					strSubst = processSubstField(strFormatted, strUnformatted, pos);
					strUnformatted = addSubst(arrSubst, strSubst, strUnformatted.substring(pos, pos + 2), strUnformatted);
				}

				// next
				start = pos + 1;
			}
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
		}
		// return array of substitution string
		return arrSubst;
	}

	/**
	 * Process substitution field
	 * 
	 * @param strFormatted
	 *            - formatted text
	 * @param strUnformatted
	 *            - unformmated text
	 * @param pos
	 *            - starting position within the text
	 * 
	 * @return the substitution string for the next substitution control
	 */
	public String processSubstField(String strFormatted, String strUnformatted, int pos)
	{
		// search for next substitution field
		int next = pos + 1;
		int xpos = pos;
		while ((next = strUnformatted.indexOf("&", next)) >= 0)
		{
			if (isSubstField(strUnformatted.substring(next, next + 2)))
			{
				// ensure it is not immediately after the substitution text
				if (next > xpos + 2)
				{
					break;
				}
				else
				{
					xpos = next;
					next++;
				}
			}
			else
			{
				break;
			}
		}
		// no more substitution text
		if (next == -1)
		{
			next = strUnformatted.length();
		}

		// get the left side of the string (relative to the original substitution field in POS)
		String strLeft = strUnformatted.substring(0, pos);

		// get the right side of the string (relative to the original substitution field in POS)
		String strRight;

		if (strUnformatted.length() > pos + 2)
		{
			strRight = strUnformatted.substring(xpos + 2, next);
		}
		else
		{
			strRight = "";
		}

		// search in formatted text
		int n1 = strFormatted.indexOf(strLeft);
		int n2 = strFormatted.indexOf(strRight);
		if (strRight.equals(""))
		{
			n2 = strFormatted.length();
		}

		// must be found!
		if (n1 == -1)
		{
			return "";
		}

		if (n2 == -1)
		{
			return "";
		}

		// get the text
		String str = strFormatted.substring(n1 + strLeft.length(), n2);
		return str;
	}

	/**
	 * Add the substitution text to the list and return the updated string
	 * 
	 * @param str
	 *            - the list of substitution text
	 * @param subst
	 *            - the substitution text to be added
	 * @param field
	 *            - the substitution control (e.g. &1, &2, etc)
	 * @param strUnformatted
	 *            - the unformatted text
	 * 
	 * @return the updated text
	 */
	public String addSubst(String[] str, String subst, String field, String strUnformatted)
	{
		// add to the array
		int n = field.charAt(1) - '1';
		str[n] = subst;
		// reformat the unformatted text
		return (strUnformatted.replaceAll(field, subst));
	}

	/**
	 * Determine whether substitution control (e.g. &1, &2, etc)
	 * 
	 * @param str
	 *            - the text
	 * 
	 * @return true if substitution control (e.g. &1, &2, etc)
	 */
	public boolean isSubstExists(String str)
	{
		// search for &1
		if (str.indexOf("&1") >= 0)
		{
			return true;
		}
		return false;
	}

	/**
	 * Determine whether the text is a substitution control (e.g. &1, &2, etc)
	 * 
	 * @param str
	 *            - the text
	 * 
	 * @return true if it is a substitution control
	 */
	public boolean isSubstField(String str)
	{
		// must be 2 character only
		if (str.length() != 2)
		{
			return false;
		}

		// start with &
		if (str.charAt(0) != '&')
		{
			return false;
		}

		// end with 1..9
		if (str.charAt(1) >= '0' && str.charAt(1) <= '9')
		{
			return true;
		}

		// otherwise, invalid
		return false;
	}
}
