package com.misys.equation.function.runtime;

import com.misys.equation.function.language.LanguageResources;

public class FunctionDebugInfo
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionDebugInfo.java 10420 2011-02-03 12:22:26Z MACDONP1 $";

	// Buffer
	private StringBuffer buffer;

	/**
	 * Construct an empty debug info
	 */
	public FunctionDebugInfo()
	{
		buffer = new StringBuffer();
	}

	/**
	 * Return the debug info
	 */
	@Override
	public String toString()
	{
		return "\n" + LanguageResources.getString("FunctionDebugInfo.Debug.StartLog") + "\n" + buffer.toString() + "\n"
						+ LanguageResources.getString("FunctionDebugInfo.Debug.EndLog");
	}

	/**
	 * Clear info
	 */
	public void clear()
	{
		buffer = new StringBuffer();
	}

	/**
	 * Add an info
	 * 
	 * @param str
	 *            - the info to be added
	 */
	public void addInfo(String str)
	{
		buffer.append(str);
	}

	/**
	 * Add an info with newline
	 * 
	 * @param str
	 *            - the info to be added
	 */
	public void addInfoLn(String str)
	{
		addInfo(str);
		buffer.append("\n");
	}

	/**
	 * Print start processing of method log
	 * 
	 * @param methodName
	 *            - the method/function/procedure name
	 */
	public void printStartMethod(String methodName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.StartMethod", methodName));
	}

	/**
	 * Print end processing of method log
	 * 
	 * @param methodName
	 *            - the method/function/procedure name
	 */
	public void printEndMethod(String methodName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.EndMethod", methodName));
	}

	/**
	 * Print field log
	 * 
	 * @param inputField
	 *            - the method/function/procedure name
	 */
	public void printField(String fieldName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.FieldProcessing", fieldName));
	}

	/**
	 * Print syntax validation log
	 * 
	 * @param scrnNo
	 *            - screen number
	 */
	public void printSyntaxValidation(int scrnNo)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.SyntaxValidation", String.valueOf(scrnNo)));
	}

	/**
	 * Print application validation log
	 * 
	 * @param scrnNo
	 *            - screen number
	 */
	public void printApplicationValidation(int scrnNo)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.ApplicationValidation", String.valueOf(scrnNo)));
	}

	/**
	 * Print generate screen HTML log
	 * 
	 * @param scrnNo
	 *            - screen number
	 */
	public void printHTMLScreen(int scrnNo)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.DisplayScreen", String.valueOf(scrnNo)));
	}

	/**
	 * Print generate field HTML log
	 * 
	 * @param inputFieldSet
	 *            - input field set
	 * @param inputField
	 *            - field
	 */
	public void printHTMLScreenField(String inputFieldSet, String inputField)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.DisplayScreenField", new String[] { inputFieldSet,
						inputField }));
	}

	/**
	 * Print generate display label HTML log
	 * 
	 * @param displayAttributesId
	 *            the Id of the DisplayAttributesSet
	 * @param textLineId
	 *            - the Id of this Text line
	 */
	public void printHTMLScreenTextLine(String displayAttributesId, String textLineId)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.DisplayScreenTextLine", new String[] {
						displayAttributesId, textLineId }));
	}

	/**
	 * Print generate follow-on field HTML log
	 * 
	 * @param inputFieldSet
	 *            - input field set
	 * @param inputField
	 *            - field
	 */
	public void printHTMLScreenFieldFollowOn(String inputFieldSet, String inputField)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.DisplayScreenFieldFollowOn", new String[] {
						inputFieldSet, inputField }));
	}

	/**
	 * Print API being processed log
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printAPI(String apiName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.APIProcessing", apiName));
	}

	/**
	 * Print Load API being processed log
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printLoadAPI(String apiName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.LoadAPIProcessing", apiName));
	}

	/**
	 * Print API being processed completed log
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printAPICompleted(String apiName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.APIProcessingCompleted", apiName));
	}

	/**
	 * Print Load API being processed completed log
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printLoadAPICompleted(String apiName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.LoadAPIProcessingCompleted", apiName));
	}

	/**
	 * Print API being processed error log
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printAPIError(String apiName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.APIProcessingError", apiName));
	}

	/**
	 * Print API being processed error log
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printLoadAPIError(String apiName)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.LoadAPIProcessingError", apiName));
	}

	/**
	 * Print update screen set processing
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printUpdateScreenSet(int screenSet)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.UpdateScreenSetProcessing",
						String.valueOf(screenSet)));
	}

	/**
	 * Print update screen set processing
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printUpdateScreenSetComplete(int screenSet)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.UpdateScreenSetProcessingCompleted",
						String.valueOf(screenSet)));
	}

	/**
	 * Print update screen set processing
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printUpdateScreenSetError(int screenSet)
	{
		addInfoLn(LanguageResources.getFormattedString("FunctionDebugInfo.Debug.UpdateScreenSetProcessingError",
						String.valueOf(screenSet)));
	}

	/**
	 * Print write journal processing
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printJournal()
	{
		addInfoLn(LanguageResources.getString("FunctionDebugInfo.Debug.WritingJournal"));
	}

	/**
	 * Print write journal processing
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printJournalComplete()
	{
		addInfoLn(LanguageResources.getString("FunctionDebugInfo.Debug.WritingJournalCompleted"));
	}

	/**
	 * Print write journal processing
	 * 
	 * @param api
	 *            - the api name
	 */
	public void printJournalError()
	{
		addInfoLn(LanguageResources.getString("FunctionDebugInfo.Debug.WritingJournalError"));
	}

}
