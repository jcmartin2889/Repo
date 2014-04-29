package com.misys.equation.function.runtime;

import org.apache.html.dom.HTMLDivElementImpl;
import org.apache.html.dom.HTMLDocumentImpl;
import org.w3c.dom.html.HTMLElement;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.HTMLToolbox;

public class ScreenSetDummy extends ScreenSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ScreenSetDummy.java 14803 2012-11-05 11:57:09Z williae1 $";

	public final static String OPTION = "DUMMY";

	/**
	 * Construct a Function Screen CRM
	 * 
	 * @param id
	 *            - screen set id
	 * 
	 * @throws EQException
	 */
	public ScreenSetDummy(int id)
	{
		super();
		this.id = id;
		this.functionMessages = new FunctionMessages();
	}

	/**
	 * Returns the current screen in HTML format<br>
	 * 
	 * @return the HTML fieldSet of the current screen
	 * 
	 * @throws EQException
	 */
	@Override
	public String rtvScreen() throws EQException
	{
		return "";
	}

	/**
	 * Execute load API for all the field sets
	 * 
	 * @return the message severity
	 */
	public int retrieveAll()
	{
		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Execute load API for the current field set
	 * 
	 * @return the message severity
	 */
	public int retrieve()
	{
		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Execute load API for the given field set id
	 * 
	 * @param inputFieldSetId
	 *            - the input field set Id
	 * 
	 * @return the message severity
	 */
	@Override
	public int retrieve(String inputFieldSetId) throws EQException
	{
		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Execute load API for the given input field set
	 * 
	 * @param index
	 *            - the index of the input field set
	 * 
	 * @return the message severity
	 */
	@Override
	public int retrieve(int index) throws EQException
	{
		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Set other details in the screen set. This is to initialise details that has not been setup in the Function Handler Data at
	 * the time the screen set is initialised
	 */
	@Override
	protected void setOtherDetails()
	{
	}

	/**
	 * When this screen set is called from the "previous screen set",<br>
	 * then the system should display this only if it contains data
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 */
	@Override
	protected int onEntryScreenSetFromPrev(int sourceScreenSetId)
	{
		return SCRN_NEXT;
	}

	/**
	 * When this screen set is called from the "next screen set",<br>
	 * then the system should not redisplay the CRM warning message
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_NEXT - if the next screen set should be displayed<br>
	 */
	@Override
	protected int onEntryScreenSetFromNext(int sourceScreenSetId)
	{
		return SCRN_PREV;
	}

	/**
	 * Returns the current screen in HTML format
	 * 
	 * @param htmlToolbox
	 *            - the HTML toolbox
	 * @param htmlDocument
	 *            - the HTML document
	 * 
	 * @return the HTML document of the current screen
	 * 
	 * @throws EQException
	 */
	@Override
	public HTMLElement rtvHTML(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument) throws EQException
	{
		HTMLDivElementImpl mainDiv = htmlToolbox.createDivElement(htmlDocument, "");
		return mainDiv;
	}

	/**
	 * Performs validation on the specified screens
	 * 
	 * @param fromScrnNo
	 *            - from screen number
	 * @param toScrnNo
	 *            - to screen number
	 * 
	 * @return the message severity
	 * 
	 * @throws EQException
	 */
	@Override
	public int validate(int fromScrnNo, int toScrnNo) throws EQException
	{
		return FunctionMessages.MSG_NONE;
	}

	/**
	 * Performs update on the specified screen
	 * 
	 * @param journalHeader
	 *            - the Journal Header
	 * @param functionTransactions
	 *            - the list of transactions already executed prior to this update
	 * @param session
	 *            - the Equation standard session
	 * 
	 * @return true - no update processing
	 * 
	 */
	@Override
	public boolean update(JournalHeader journalHeader, FunctionTransactions functionTransactions, EquationStandardSession session)
	{
		return true;
	}

	/**
	 * Generate the function key for the screen
	 * 
	 * @return the function keys
	 */
	@Override
	protected FunctionKeys generateFkeys()
	{
		return null;
	}

	/**
	 * Perform validation of the function key
	 * 
	 * @return true - this screen has already handled the validation of the function key<br>
	 *         false - let the standard process validate the function key
	 */
	@Override
	protected boolean validateFkey()
	{
		return false;
	}

	/**
	 * Action the function key
	 * 
	 * @param ckey
	 *            - function key
	 * 
	 * @return 0 - redisplaying current screen <br>
	 *         1 - exit the function<br>
	 *         2 - go back to screen 1<br>
	 *         3 - let the standard process action the function key
	 */
	@Override
	protected int actionFkey(int ckey)
	{
		return FunctionRuntimeToolbox.PROC_LET_SYSTEM_DECIDE;
	}

	/**
	 * Return true if the screenset wants to do some validation
	 * 
	 * @param fromScrnNo
	 *            - from screen number
	 * @param toScrnNo
	 *            - to screen number
	 * 
	 * @return true if the screenset wants to do some validation
	 */
	@Override
	protected boolean performValidateDuringNoUpdate(int fromScrnNo, int toScrnNo)
	{
		return false;
	}

	/**
	 * Called when a session (template) is restored
	 * 
	 */
	@Override
	protected void restoreTemplate()
	{
	}

	/**
	 * Called when a session (work in progress) is restored
	 * 
	 */
	@Override
	protected void restoreWorkProgress()
	{
	}

	/**
	 * Called when a session is restored by supervisor
	 * 
	 */
	@Override
	protected void restoreSupervisor()
	{
	}

}
