package com.misys.equation.function.runtime;

import java.io.CharConversionException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.apache.html.dom.HTMLDivElementImpl;
import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.html.dom.HTMLInputElementImpl;
import org.apache.html.dom.HTMLScriptElementImpl;
import org.apache.html.dom.HTMLTableCellElementImpl;
import org.apache.html.dom.HTMLTableElementImpl;
import org.apache.html.dom.HTMLTableRowElementImpl;
import org.w3c.dom.html.HTMLElement;

import com.ibm.as400.access.Record;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationStandardListEnquiry;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationStandardTransaction;
import com.misys.equation.common.access.EquationWidget;
import com.misys.equation.common.access.IEquationStandardObject;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EditingToolbox;
import com.misys.equation.common.utilities.EqDataToolbox;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FieldSetAdaptor;
import com.misys.equation.function.adaptor.FunctionAdaptor;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.FieldData;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.HTMLToolbox;

public class ScreenSetAC2 extends ScreenSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ScreenSetAC2.java 14803 2012-11-05 11:57:09Z williae1 $";

	// Logger
	private static final EquationLogger LOG = new EquationLogger(ScreenSetAC2.class);

	public final static String OPTION = "AC2";
	public static final String ACC_REF = "A";
	public static final String DEAL_REF = "D";
	public static final String COM_REF = "C";

	private final String parentOption;
	private final ScreenSet screenSetToCheck;
	private final FunctionData functionTemplateData;

	private final String chargeReference; // Account or deal or commitment
	private int curData; // total number of charge records

	// charge identity
	private String chargeENEW = "";
	private String chargeDAB = "";
	private String chargeDAN = "";
	private String chargeDAS = "";
	private String chargeDCCY = "";
	private String chargeDAMT = "";
	private String chargeBRNM = "";
	private String chargeDLP = "";
	private String chargeDLR = "";
	private String chargeCMR = "";
	private String chargeATP = "";
	private String chargeDTP = "";

	// field in error?
	private boolean fieldInError;

	// allow enquire on each record during ESF? This determines whether the supervisor is
	// allowed to take option 1 on each of the charge details. This will only be set to
	// true provided the teller encountered warning messages on the subfile screen
	private boolean allowSelectRecord;

	// this store the original messages as restored from a session
	private final FunctionMessages orgFuncMessages;

	/**
	 * Construct a Function Screen AC2
	 * 
	 * @param id
	 *            - screen set id
	 * @param functionHandlerData
	 *            the Function Handler Data
	 * @param parentOption
	 *            - the parent transaction
	 * @param screenSetToCheck
	 *            - the Screen set to apply EFC
	 * 
	 * @throws EQException
	 */
	public ScreenSetAC2(int id, FunctionHandlerData fhd, String parentOption, ScreenSet screenSetToCheck) throws EQException
	{
		super(id, fhd, OPTION);
		this.maxScrnNo = 1;
		this.curData = 0;
		this.parentOption = parentOption;
		this.functionTemplateData = functionData.duplicateFunctionData();
		this.allowSelectRecord = false;
		this.orgFuncMessages = new FunctionMessages();
		this.chargeReference = fhd.getAaiRecord().getReference();
		this.fKeyToNextFunction = FunctionKeys.KEY_ACCPT;
		this.screenSetToCheck = screenSetToCheck;
	}

	/**
	 * Return the current index being processed
	 * 
	 * @return the current index being processed
	 */
	protected int rtvCurIndex()
	{
		return Toolbox.parseInt(functionData.rtvFieldDbValue("CURINDEX"), 0);
	}

	/**
	 * Setup fields based on the Function Data
	 * 
	 */
	protected void setupChargeKeyBasedOnHZ()
	{
		chargeENEW = functionData.rtvFieldDbValue("ENEW");
		chargeDAB = functionData.rtvFieldDbValue("HZAB");
		chargeDAN = functionData.rtvFieldDbValue("HZAN");
		chargeDAS = functionData.rtvFieldDbValue("HZAS");
		chargeBRNM = functionData.rtvFieldDbValue("HZBRNM");
		chargeDLP = functionData.rtvFieldDbValue("HZDLP");
		chargeDLR = functionData.rtvFieldDbValue("HZDLR");
		chargeCMR = functionData.rtvFieldDbValue("HZCMR");
		chargeDCCY = functionData.rtvFieldDbValue("HZCCY");
		chargeDAMT = functionData.rtvFieldDbValue("HZSAM1");
		chargeATP = functionData.rtvFieldDbValue("HZATP");
		chargeDTP = functionData.rtvFieldDbValue("HZDTP");
		curData = Toolbox.parseInt(functionData.rtvFieldDbValue("CURDATA"), 0);
		firstTime = false;
	}

	/**
	 * Setup fields based on the Function Data
	 * 
	 * @return true if key details have been setup successfully, otherwise, no EFC charge
	 * 
	 */
	protected boolean setupChargeKeyBasedOnScreen() throws EQException
	{
		// retrieve details from the main function
		Function parentFunction = screenSetToCheck.getFunction();

		// efc not activated?
		if (!screenSetToCheck.getFunctionData().rtvFieldInputValue(FunctionData.FLD_EFC).equals(EqDataType.YES))
		{
			resetCharges();
			return false;
		}

		// should execute EFC?
		APIFieldSet efcApiFieldSet = parentFunction.getUpdateAPI(Function.EFC_FIELDSET);
		FieldSetAdaptor fieldSetAdaptor = screenSetToCheck.getFunctionAdaptor().getFieldSetAdaptor(equationStandardSession,
						efcApiFieldSet);
		if (!fieldSetAdaptor.shouldExecute(efcApiFieldSet))
		{
			resetCharges();
			return false;
		}

		// load key details
		FunctionCommonData mapData = new FunctionCommonData(equationStandardSession, parentFunction, efcApiFieldSet,
						screenSetToCheck.getFunctionData(), screenSetToCheck.getFunctionAdaptor(), fhd);

		// no mapping to EFC
		if (!mapData.isDataExist())
		{
			resetCharges();
			return false;
		}

		// deal based
		if (chargeReference.equals(DEAL_REF))
		{
			String temp = mapData.getFieldValue(FunctionCommonData.EFC_EBRNM);
			if (!temp.equals(chargeBRNM))
			{
				firstTime = true;
				chargeBRNM = temp;
			}

			temp = mapData.getFieldValue(FunctionCommonData.EFC_EDLR);
			if (!temp.equals(chargeDLR))
			{
				firstTime = true;
				chargeDLR = temp;
			}

			temp = mapData.getFieldValue(FunctionCommonData.EFC_EDLP);
			if (!temp.equals(chargeDLP))
			{
				firstTime = true;
				chargeDLP = temp;
			}

			temp = mapData.getFieldValue(FunctionCommonData.EFC_EDTP);
			if (!temp.equals(chargeDTP))
			{
				firstTime = true;
				chargeDTP = temp;
			}

			chargeCMR = "";
			chargeDAB = "";
			chargeDAN = "";
			chargeDAS = "";
			chargeATP = "";
		}

		// commitment based
		else if (chargeReference.equals(COM_REF))
		{
			String temp = mapData.getFieldValue(FunctionCommonData.EFC_ECMR);
			if (!temp.equals(chargeCMR))
			{
				firstTime = true;
				chargeCMR = temp;
			}

			temp = mapData.getFieldValue(FunctionCommonData.EFC_EATP);
			if (!temp.equals(chargeATP))
			{
				firstTime = true;
				chargeATP = temp;
			}

			chargeBRNM = "";
			chargeDLR = "";
			chargeDLP = "";
			chargeDAB = "";
			chargeDAN = "";
			chargeDAS = "";
			chargeDTP = "";
		}

		// account based
		else if (chargeReference.equals(ACC_REF))
		{
			String temp = mapData.getFieldValue(FunctionCommonData.EFC_EDAB);
			if (!temp.equals(chargeDAB))
			{
				firstTime = true;
				chargeDAB = temp;
			}

			temp = mapData.getFieldValue(FunctionCommonData.EFC_EDAN);
			if (!temp.equals(chargeDAN))
			{
				firstTime = true;
				chargeDAN = temp;
			}

			temp = mapData.getFieldValue(FunctionCommonData.EFC_EDAS);
			if (!temp.equals(chargeDAS))
			{
				firstTime = true;
				chargeDAS = temp;
			}

			temp = mapData.getFieldValue(FunctionCommonData.EFC_EATP);
			if (!temp.equals(chargeATP))
			{
				firstTime = true;
				chargeATP = temp;
			}

			chargeBRNM = "";
			chargeDLR = "";
			chargeDLP = "";
			chargeCMR = "";
			chargeDTP = "";
		}

		// invalid, not efc
		else
		{
			return false;
		}

		// transaction amount (debit)
		String temp = mapData.getFieldValue(FunctionCommonData.EFC_EDAMT);
		if (Toolbox.parseLong(temp, Long.MAX_VALUE) != Toolbox.parseLong(chargeDAMT, Long.MIN_VALUE))
		{
			firstTime = true;
			chargeDAMT = temp;
		}

		// new transaction?
		temp = mapData.getFieldValue(FunctionCommonData.EFC_ENEW);
		if (!temp.equals(chargeENEW))
		{
			firstTime = true;
			chargeENEW = temp;
		}

		// transaction currency (debit)
		temp = mapData.getFieldValue(FunctionCommonData.EFC_EDCCY);
		if (!temp.equals(chargeDCCY))
		{
			firstTime = true;
			chargeDCCY = temp;
		}

		functionData.chgFieldDbValue("TREF", mapData.getFieldValue(FunctionCommonData.EFC_ETREF));
		functionData.chgFieldDbValue("CAB", mapData.getFieldValue(FunctionCommonData.EFC_ECAB));
		functionData.chgFieldDbValue("CAN", mapData.getFieldValue(FunctionCommonData.EFC_ECAN));
		functionData.chgFieldDbValue("CAS", mapData.getFieldValue(FunctionCommonData.EFC_ECAS));
		functionData.chgFieldDbValue("AREF", mapData.getFieldValue(FunctionCommonData.EFC_EXREF));
		functionData.chgFieldDbValue("DDTD", mapData.getFieldValue(FunctionCommonData.EFC_ETDTE));
		functionData.chgFieldDbValue("ESQN", mapData.getFieldValue(FunctionCommonData.EFC_ESQN));
		functionData.chgFieldDbValue("TCCY", mapData.getFieldValue(FunctionCommonData.EFC_EDCCY));
		functionData.chgFieldDbValue("TAMT", mapData.getFieldValue(FunctionCommonData.EFC_EDAMT));
		functionData.chgFieldDbValue("ENEW", mapData.getFieldValue(FunctionCommonData.EFC_ENEW));

		return true;
	}

	/**
	 * Edit the all the charge details
	 * 
	 */
	private void editData()
	{
		editData(functionData, equationStandardSession);

		// edit all the charges
		for (int i = 1; i <= curData; i++)
		{
			editData(i);
		}
	}

	/**
	 * Edit a charge record
	 */
	private void editData(int n)
	{
		editData(functionData, equationStandardSession, parentOption, n);
	}

	/**
	 * Edit the all the charge details
	 * 
	 * @param functionData
	 *            - the function data
	 * @param equationStandardSession
	 *            - the Equation Standard Session
	 */
	public void editData(FunctionData functionData, EquationStandardSession equationStandardSession)
	{
		String result;

		// edit reference
		if (!functionData.rtvFieldDbValue("HZAB").equals(""))
		{
			functionData.chgFieldInputValue("REF", EditingToolbox.editAccount(functionData.rtvFieldDbValue("HZAB"), functionData
							.rtvFieldDbValue("HZAN"), functionData.rtvFieldDbValue("HZAS")));
			result = EqDataToolbox.editAccount(equationStandardSession, "", functionData.rtvFieldDbValue("HZAB"), functionData
							.rtvFieldDbValue("HZAN"), functionData.rtvFieldDbValue("HZAS"));
			functionData.chgFieldOutputValue("REF", result);
		}
		else if (!functionData.rtvFieldDbValue("HZBRNM").equals(""))
		{
			functionData.chgFieldInputValue("REF", EditingToolbox.editDealReference(functionData.rtvFieldDbValue("HZBNRM"),
							functionData.rtvFieldDbValue("HZDLP"), functionData.rtvFieldDbValue("HZDLR")));
			result = EqDataToolbox.editDealType(equationStandardSession, "", functionData.rtvFieldDbValue("HZDLP"));
			functionData.chgFieldOutputValue("REF", result);
		}
		else if (!functionData.rtvFieldDbValue("HZCMR").equals(""))
		{
			functionData.chgFieldInputValue("REF", functionData.rtvFieldDbValue("HZCMR"));
			result = EqDataToolbox.editCommitment(equationStandardSession, "", functionData.rtvFieldDbValue("HZCMR"));
			functionData.chgFieldOutputValue("REF", result);
		}

		// edit event
		result = EqDataToolbox.editEvent(equationStandardSession, "", functionData.rtvFieldDbValue("HZEVNT"));
		functionData.chgFieldOutputValue("HZEVNT", result);

	}

	/**
	 * Edit a charge record
	 * 
	 * @param functionData
	 *            - the function data
	 * @param equationStandardSession
	 *            - the Equation Standard Session
	 * @param parentOption
	 *            - the parent option id
	 * @param n
	 *            - the index value
	 */
	public void editData(FunctionData functionData, EquationStandardSession equationStandardSession, String parentOption, int n)
	{
		String result;
		FieldData fieldData;
		String index = "_" + String.valueOf(n);

		// edit the charge component
		result = EqDataToolbox.editChargeComponent(equationStandardSession, "", functionData.rtvFieldDbValue("GSECNM" + index),
						parentOption);
		functionData.chgFieldOutputValue("GSECNM" + index, result);

		// edit the charge code
		result = EqDataToolbox.editChargeCode(equationStandardSession, "", functionData.rtvFieldDbValue("GSCHGC" + index));
		functionData.chgFieldOutputValue("GSCHGC" + index, result);

		// edit the input amount
		result = EqDataToolbox.editAmount(equationStandardSession, "", functionData.rtvFieldDbValue("GSCHA" + index), functionData
						.rtvFieldDbValue("GSCCYC" + index), 0, 15);
		functionData.chgFieldOutputValue("GSCHA" + index, result);
		functionData.chgFieldInputValue("GSCHA" + index, result);

		// edit the calculate amount (charge amount + waived amount)
		functionData.chgFieldDbValue("CALA" + index, functionData.rtvFieldDbValue("GSCHA" + index));
		functionData.chgFieldOutputValue("CALA" + index, result);

		// edit the charge amount
		functionData.chgFieldDbValue("CHGA" + index, functionData.rtvFieldDbValue("GSCHA" + index));
		functionData.chgFieldOutputValue("CHGA" + index, result);

		// edit the base code
		result = EqDataToolbox.editBaseCode(equationStandardSession, "", functionData.rtvFieldDbValue("GSBRR" + index));
		functionData.chgFieldOutputValue("GSBRR" + index, result);

		// edit the percentage rate
		fieldData = functionData.rtvFieldData("GSPAMT" + index);
		String s = EqDataType.cvtDbNumericToInput(functionData.rtvFieldDbValue("GSPAMT" + index), functionData
						.getIntegerSeparator(), functionData.getDecimalSeparator());
		result = EqDataToolbox.editAmount(equationStandardSession, "", s, "", fieldData.getFieldDecimal(), fieldData
						.getFieldLength());
		functionData.chgFieldOutputValue("GSPAMT" + index, result);
		functionData.chgFieldInputValue("GSPAMT" + index, result);

		// edit the calculate on amount
		result = EqDataToolbox.editAmount(equationStandardSession, "", functionData.rtvFieldDbValue("GSCAOD" + index), functionData
						.rtvFieldDbValue("GSCCYC" + index), 0, 15);
		functionData.chgFieldOutputValue("GSCAOD" + index, result);

		// edit the minimum amount
		result = EqDataToolbox.editAmount(equationStandardSession, "", functionData.rtvFieldDbValue("GSMIND" + index), functionData
						.rtvFieldDbValue("GSCCYC" + index), 0, 15);
		functionData.chgFieldOutputValue("GSMIND" + index, result);

		// edit the maximum amount
		result = EqDataToolbox.editAmount(equationStandardSession, "", functionData.rtvFieldDbValue("GSMAXD" + index), functionData
						.rtvFieldDbValue("GSCCYC" + index), 0, 15);
		functionData.chgFieldOutputValue("GSMAXD" + index, result);

		// edit the waive amount
		result = EqDataToolbox.editAmount(equationStandardSession, "", functionData.rtvFieldDbValue("GSWAMD" + index), functionData
						.rtvFieldDbValue("GSCCYC" + index), 0, 15);
		functionData.chgFieldOutputValue("GSWAMD" + index, result);
		functionData.chgFieldInputValue("GSWAMD" + index, result);

		// edit the currency
		result = EqDataToolbox.editCcy(equationStandardSession, "", functionData.rtvFieldDbValue("GSCCYC" + index));
		functionData.chgFieldOutputValue("GSCCYC" + index, result);

		// edit the start date
		result = EqDataToolbox.editDate(equationStandardSession, "", functionData.rtvFieldInputValue("GSSDT" + index),
						EqDataToolbox.DATEFORMAT_EXT);
		functionData.chgFieldOutputValue("GSSDT" + index, result);

		// edit the end date
		result = EqDataToolbox.editDate(equationStandardSession, "", functionData.rtvFieldInputValue("GSEND" + index),
						EqDataToolbox.DATEFORMAT_EXT);
		functionData.chgFieldOutputValue("GSEND" + index, result);

		// edit the frequency
		result = EqDataToolbox.editFreq(equationStandardSession, "", functionData.rtvFieldDbValue("GSFRQ" + index));
		functionData.chgFieldOutputValue("GSFRQ" + index, result);

		// edit the funding account
		result = EqDataToolbox.editAccount(equationStandardSession, "", functionData.rtvFieldDbValue("GSFAB"), functionData
						.rtvFieldDbValue("GSFAN"), functionData.rtvFieldDbValue("GSFAS"));
		functionData.chgFieldOutputValue("GSFAB" + index, result);

		// edit the tax
		fieldData = functionData.rtvFieldData("GSTAX" + index);
		result = EqDataToolbox.editAmount(equationStandardSession, "", functionData.rtvFieldDbValue("GSTAX" + index), "", fieldData
						.getFieldDecimal(), fieldData.getFieldLength());
		functionData.chgFieldOutputValue("GSTAX" + index, result);
		functionData.chgFieldInputValue("GSTAX" + index, result);

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
		// generate the fix detail html
		HTMLElement divFixedDetails = rtvFixedDetailHTML(htmlToolbox, htmlDocument);

		// generate the repeating detail html
		HTMLTableElementImpl table1 = rtvHeadHTML(htmlToolbox, htmlDocument);

		// retrieve the widget
		EquationWidget widget = equationStandardSession.getUnit().getWidget("OPT");

		// assume no error
		fieldInError = false;

		for (int j = 1; j <= curData; j++)
		{
			// check if this exists, if it does not exists, then exit
			if (functionData.rtvFieldData("GSECNM_" + j) != null)
			{
				boolean enquire = securityLevel.chkNoUpdate();
				if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER && allowSelectRecord)
				{
					enquire = false;
				}
				rtvListHtml(widget, htmlToolbox, htmlDocument, table1, j, enquire);
			}
		}
		table1.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 2));

		// return the HTML element
		String inputFieldId = function.getInputFieldSets().get(0).getId();
		HTMLDivElementImpl div = htmlToolbox.createDivElement(htmlDocument, HTMLToolbox.DIV_MAIN);
		div.setClassName("inputArea MainDivClass");
		div.appendChild(divFixedDetails);
		div.appendChild(table1);
		if (!fieldInError)
		{
			htmlToolbox.processShutter(htmlDocument, HTMLToolbox.TABLE_DETAIL + inputFieldId + "1", inputFieldId + "1", true);
		}

		// add the elements to the document
		htmlDocument.appendChild(htmlToolbox.createBodyElement(htmlDocument, "Body")).appendChild(div);
		htmlToolbox.positionCursorList(htmlDocument, curData, OPTION + "_Row3", "OPT");

		// return
		return div;
	}

	/**
	 * Generate the HTML screen of the HZ fields
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table element
	 * 
	 * @return the table element
	 */
	private HTMLElement rtvFixedDetailHTML(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument)
	{
		// retrieve the input fields from the function
		DisplayAttributes refDisplayAttributeField = null;
		DisplayAttributes evntDisplayAttributeField = null;
		DisplayAttributes chgsDisplayAttributeField = null;
		for (int i = 0; i < layout.getDisplayAttributesSets().size(); i++)
		{
			DisplayItemList displayItems = layout.getDisplayAttributesSets().get(i).getDisplayItems();
			for (int j = 0; j < displayItems.size(); j++)
			{
				if (displayItems.get(j) instanceof DisplayAttributes)
				{
					DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(j);
					if (displayAttributes.getId().equals("REF"))
					{
						refDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("EVNT"))
					{
						evntDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("CHGS"))
					{
						chgsDisplayAttributeField = displayAttributes;
					}
				}
			}
		}

		// create the table
		String inputFieldId = function.getInputFieldSets().get(0).getId();
		HTMLTableElementImpl table = htmlToolbox.createDefaultTableWithRow(htmlDocument, inputFieldId + "0", inputFieldId + "0",
						layout.getDisplayAttributesSets().get(0).rtvLabel(eqUser), layout.getDisplayAttributesSets().get(0)
										.rtvDescription(eqUser), 2, false);

		// work fields
		HTMLTableCellElementImpl cellElement;
		HTMLElement outputElement;

		// create the first row
		HTMLTableRowElementImpl row1 = htmlToolbox.createRowElement(htmlDocument, refDisplayAttributeField.getId()
						+ HTMLToolbox.ROW_SUFFIX);
		cellElement = htmlToolbox.createCellElement(htmlDocument, row1, HTMLToolbox.TD1_PREFIX + refDisplayAttributeField.getId(),
						0, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "REFLabel", refDisplayAttributeField.rtvLabel(eqUser));
		outputElement.setClassName("labelTextBold");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row1, HTMLToolbox.TD2_PREFIX + refDisplayAttributeField.getId(),
						1, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "REF", functionData.rtvFieldInputValue("REF"));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "REF" + HTMLToolbox.ID_OUTPUT, functionData
						.rtvFieldOutputValue("REF"));
		outputElement.setClassName("outputText");
		cellElement.appendChild(outputElement);

		// create the second row
		HTMLTableRowElementImpl row2 = htmlToolbox.createRowElement(htmlDocument, chgsDisplayAttributeField.getId()
						+ HTMLToolbox.ROW_SUFFIX);
		cellElement = htmlToolbox.createCellElement(htmlDocument, row2, HTMLToolbox.TD1_PREFIX + chgsDisplayAttributeField.getId(),
						0, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "HZCHDSLabel", chgsDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelTextBold");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row2, HTMLToolbox.TD2_PREFIX + chgsDisplayAttributeField.getId(),
						1, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "HZCHDS", functionData.rtvFieldInputValue("HZCHDS"));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		// create the third row
		HTMLTableRowElementImpl row3 = htmlToolbox.createRowElement(htmlDocument, evntDisplayAttributeField.getId()
						+ HTMLToolbox.ROW_SUFFIX);
		cellElement = htmlToolbox.createCellElement(htmlDocument, row3, HTMLToolbox.TD1_PREFIX + evntDisplayAttributeField.getId(),
						0, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "EVNTLabel", evntDisplayAttributeField.rtvLabel(eqUser));
		outputElement.setClassName("labelTextBold");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row3, HTMLToolbox.TD2_PREFIX + evntDisplayAttributeField.getId(),
						1, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "EVNT", functionData.rtvFieldInputValue("HZEVNT"));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "EVNT" + HTMLToolbox.ID_OUTPUT, functionData
						.rtvFieldOutputValue("HZEVNT"));
		outputElement.setClassName("outputText");
		cellElement.appendChild(outputElement);

		// add all the rows to the table
		table.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 2));
		table.appendChild(row1);
		table.appendChild(row2);
		table.appendChild(row3);
		table.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 2));

		// create the main div
		HTMLElement div = htmlToolbox.createDefaultGroupDiv(htmlDocument, HTMLToolbox.DIV_DETAIL, table, false, "");
		htmlToolbox.processShutter(htmlDocument, HTMLToolbox.TABLE_DETAIL + inputFieldId + "0", inputFieldId + "0", true);

		return div;
	}

	/**
	 * Generate the HTML screen of the HZ fields
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table element
	 * 
	 * @return the table element
	 */
	private HTMLTableElementImpl rtvHeadHTML(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument)
	{
		HTMLTableCellElementImpl cellElement;
		HTMLElement outputElement;

		// retrieve the input fields from the function
		DisplayAttributes calaDisplayAttributeField = null;
		DisplayAttributes chgaDisplayAttributeField = null;
		DisplayAttributes optDisplayAttributeField = null;
		DisplayAttributes chgdDisplayAttributeField = null;
		for (int i = 0; i < layout.getDisplayAttributesSets().size(); i++)
		{
			DisplayItemList displayItems = layout.getDisplayAttributesSets().get(i).getDisplayItems();
			for (int j = 0; j < displayItems.size(); j++)
			{
				if (displayItems.get(j) instanceof DisplayAttributes)
				{
					DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(j);
					if (displayAttributes.getId().equals("CALA"))
					{
						calaDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("CHGA"))
					{
						chgaDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("OPT"))
					{
						optDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("CHGD"))
					{
						chgdDisplayAttributeField = displayAttributes;
					}
				}
			}
		}

		// create the table
		String inputFieldId = function.getInputFieldSets().get(0).getId();
		HTMLTableElementImpl table = htmlToolbox.createDefaultTableWithRow(htmlDocument, inputFieldId + "1", inputFieldId + "1",
						layout.getDisplayAttributesSets().get(0).rtvLabel(eqUser), layout.getDisplayAttributesSets().get(0)
										.rtvDescription(eqUser), 5, false);
		table.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 5));
		table.setWidth("60%");

		HTMLTableRowElementImpl row = htmlToolbox.createRowElement(htmlDocument, OPTION + "_HeaderRow");
		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 0, 1, "8%");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "OPTLabel", optDisplayAttributeField.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 1, 1, "25%");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CHGDLabel", chgdDisplayAttributeField.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 2, 1, "20%");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CALALabel", calaDisplayAttributeField.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 3, 1, "20%");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CHGALabel", chgaDisplayAttributeField.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 4, 1, "27%");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "", "");
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		// add all the rows to the table
		table.appendChild(row);

		return table;
	}

	/**
	 * Generate the HTML screen of a message warning
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table element
	 * @param dshh02
	 *            - the warning
	 * @param index
	 *            - the current index
	 * 
	 * @return the table element
	 */
	private HTMLElement rtvListHtml(EquationWidget widget, HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument,
					HTMLTableElementImpl table, int indexData, boolean enquire)
	{
		HTMLTableCellElementImpl cellElement;
		HTMLElement outputElement;
		String index = "_" + String.valueOf(indexData);

		// create a row of charge details
		HTMLTableRowElementImpl row = htmlToolbox.createRowElement(htmlDocument, OPTION + "_Row3" + index);

		// message severity
		int msgSev = Toolbox.parseInt(functionData.rtvFieldDbValue("MSGSTS" + index), 0);
		if (msgSev == FunctionMessages.MSG_WARN && functionMessages.getMsgSev() < FunctionMessages.MSG_ERROR)
		{
			fieldInError = true;
			row.setClassName(row.getClassName() + " wf_WARNING");
		}
		else if (msgSev == FunctionMessages.MSG_ERROR)
		{
			fieldInError = true;
			row.setClassName(row.getClassName() + " wf_ERROR");
		}

		// get the opt field
		DisplayAttributes optAttribute = (DisplayAttributes) layout.getDisplayAttributesSets().get(0).getDisplayItems().get("OPT");

		// create the dummy field and display attributes
		InputField field = new InputField("OPT" + index, "", "");
		DisplayAttributes displayAttributes = new DisplayAttributes("OPT" + index, optAttribute.rtvLabel(eqUser), optAttribute
						.rtvDescription(eqUser));
		displayAttributes.setWidget("OPT");

		// create the first column
		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 0, 1, "10%");
		HTMLInputElementImpl inputElement = htmlToolbox.createInputElement(htmlDocument, field.getId(), true, "input");
		inputElement.setSize("1");
		inputElement.setMaxLength(1);
		inputElement.setValue(functionData.rtvFieldInputValue(field.getId()));
		inputElement.setClassName("wf_default wf_field wf_UPPERCASE enquiryText");
		inputElement.setReadOnly(enquire);
		inputElement.setTitle(displayAttributes.rtvDescription(eqUser));
		inputElement.setAttribute("label", displayAttributes.rtvLabel(eqUser));

		if (enquire)
		{
			inputElement.setClassName(inputElement.getClassName() + " wf_pr");
		}

		Hashtable<String, String> mapping = htmlToolbox.getMapping(field, displayAttributes);
		mapping.put("'&ADDFLD1'", "getLanguageLabel(\"GBL600001\")");
		mapping.put("'&ADDFLD2'", "getLanguageLabel(\"GBL600002\")");

		HTMLScriptElementImpl script = htmlToolbox.createScriptElement(htmlDocument, "OPT" + index + "Widget");
		script.setText(widget.getWidgetScript(mapping));
		htmlToolbox.processFieldAttribBasedOnWidget(field, widget, mapping, inputElement);

		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "", " ");
		outputElement.setClassName("labelText");
		cellElement.appendChild(outputElement);
		cellElement.appendChild(inputElement);
		cellElement.appendChild(script);

		// create the second column
		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 1, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "GSECNM" + index, functionData
						.rtvFieldOutputValue("GSECNM" + index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		// create the third column
		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 2, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CALA" + index, functionData.rtvFieldOutputValue("CALA"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		// create the fourth column
		cellElement = htmlToolbox.createCellElement(htmlDocument, row, "", 3, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CHGA" + index, functionData.rtvFieldOutputValue("CHGA"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		// add all the rows to the table
		table.appendChild(row);

		return table;
	}

	/**
	 * When this screen set is called from the "previous screen set",<br>
	 * then the system should display this only if EFC screen should be displayed
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 *         SCRN_NEXT - if the next screen set should be displayed<br>
	 *         SCRN_CUR - if this screen set should be displayed
	 */
	@Override
	protected int onEntryScreenSetFromPrev(int sourceScreenSetId) throws EQException
	{
		// setup key details
		if (!setupChargeKeyBasedOnScreen())
		{
			return SCRN_NEXT;
		}

		// supervisor mode always display
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			return SCRN_CUR;
		}

		// reload charges if any details have changed
		if (firstTime)
		{
			shutterHandler.clear();
			loadDefaultCharges(parentOption);
			editData();
			firstTime = false;
		}

		// charge subfile displayed conditionally?
		if (EquationCommonContext.isDesktopSession(functionInfo.getSessionType())
						&& functionKeys.getFuncKey() != FunctionKeys.KEY_CHARGE)
		{
			String dsplc = eqUser.getEquationUnit().getSystemOption(equationStandardSession, "KFIL", "DSPLC",
							eqUser.getEquationUnit().isDevelopmentUnit());
			if (dsplc.equals("N"))
			{
				// no details?
				if (curData == 0)
				{
					return SCRN_NEXT;
				}

				// validate
				validate(0, 0);

				// no messages, then do not display
				if (functionMessages.getMsgSev() == FunctionMessages.MSG_NONE)
				{
					return SCRN_NEXT;
				}
			}
		}

		// display screen
		return SCRN_CUR;
	}

	/**
	 * Called to do any processing prior to displaying this screen set upon entry <br>
	 * from next screen set (e.g. paging up)
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 *         SCRN_NEXT - if the next screen set should be displayed<br>
	 *         SCRN_CUR - if this screen set should be displayed
	 * 
	 * @throws EQException
	 */
	@Override
	protected int onEntryScreenSetFromNext(int sourceScreenSetId) throws EQException
	{
		// supervisor mode?
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			if (allowSelectRecord)
			{
				if (screenSetHandler.getScreenSets().size() > 3)
				{
					screenSetHandler.getScreenSets().remove(3);
				}
				screenSetHandler.setLastScreenSetViewed(id);
			}

			return SCRN_CUR;
		}

		// F6 was cancelled?
		if (functionData.rtvFieldDbValue("ADDS").equals("1"))
		{
			curData--;
			functionData.chgFieldDbValue("ADDS", "");
		}

		// check if the temporary charge component contains value
		String chargeComponent = functionData.rtvFieldDbValue("TECNM").trim();
		if (!chargeComponent.equals(""))
		{
			insertDetailsToFunctionData(chargeComponent, curData + 1);
			editData(curData + 1);
			processChargeDetail(curData + 1);
			functionData.chgFieldDbValue("ADDS", "1");
			functionKeys.setFuncKey(FunctionKeys.KEY_NONE);
			curData++;
		}

		// always set it to blank
		functionData.chgFieldDbValue("TECNM", "");

		// update number of details
		functionData.chgFieldDbValue("CURDATA", String.valueOf(curData));

		// set the last screen set viewed back to this screen set
		screenSetHandler.setLastScreenSetViewed(id);

		return SCRN_CUR;
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
		functionMessages.clearMessages();

		// validate all options and ensure it is valid
		for (int i = 1; i <= curData; i++)
		{
			// reset message severity only if needed
			// message severity status MSGSTS determines whether it needs to be validated
			// - blank - never been validated
			// - 0 - validated and no further validation needed
			// - >0 - validated already and needs to be revalidated
			int msgSev = Toolbox.parseInt(functionData.rtvFieldDbValue("MSGSTS_" + i), 20);
			if (msgSev > 1)
			{
				functionData.chgFieldDbValue("MSGSTS_" + i, "1");
			}

			// validate the option selection
			String fieldName = "OPT_" + i;
			String inputValue = functionData.rtvFieldInputValue(fieldName).toUpperCase();
			if (inputValue.equals("") || inputValue.equals("1") || inputValue.equals("X"))
			{
				functionData.chgFieldDbValue(fieldName, inputValue);

				// restore original message severity
				if (allowSelectRecord)
				{
					functionData.chgFieldDbValue("MSGSTS_" + i, functionData.rtvFieldDbValue("ORGMSGSTS_" + i));
				}
			}
			else
			{
				FieldData fieldData = functionData.rtvFieldData(fieldName);
				String parm1 = Toolbox.pad(inputValue, 10);
				String parm2 = Toolbox.pad("1 or X", 20);
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, id, scrnNo, fieldName, i, fieldData,
								"KSM2042" + parm1 + parm2, "", "", FunctionMessages.MSG_NONE);
				functionData.chgFieldDbValue("MSGSTS_" + i, String.valueOf(functionMessages.getMsgSev()));
			}
		}

		// no error, then check for 1
		boolean selectionMade = false;
		if (functionMessages.getMsgSev() == FunctionMessages.MSG_NONE)
		{
			// validate all options and ensure it is valid
			for (int i = 1; i <= curData; i++)
			{
				String fieldName = "OPT_" + i;
				String inputValue = functionData.rtvFieldInputValue(fieldName);
				if (inputValue.equals("1"))
				{
					processChargeDetail(i);
					functionData.chgFieldInputValue(fieldName, "");
					functionData.chgFieldDbValue("GSEXCL_" + i, EqDataType.NO);
					functionData.chgFieldDbValue("ADDS", "");
					functionKeys.setFuncKey(FunctionKeys.KEY_NONE);
					selectionMade = true;
					break;
				}
				else if (inputValue.equals("X"))
				{
					functionData.chgFieldDbValue("GSEXCL_" + i, EqDataType.YES);
				}
				else
				{
					functionData.chgFieldDbValue("GSEXCL_" + i, EqDataType.NO);
				}
			}

			// validate record if it has not been previously validated
			// so that it will not re-issue warnings twice (one during the charge detail
			// screen and another one on the subfile screen)
			if (!selectionMade && !allowSelectRecord)
			{
				for (int i = 1; i <= curData; i++)
				{
					int msgSev = Toolbox.parseInt(functionData.rtvFieldDbValue("MSGSTS_" + i), 20);
					if (msgSev > 0 && functionData.rtvFieldDbValue("GSEXCL_" + i).equals("N"))
					{
						functionData.chgFieldDbValue("GSEXCL_" + i, "N");
						ScreenSetAC3 ssAC3 = new ScreenSetAC3(0, fhd);
						screenSetHandler.addScreenSet(ssAC3);
						screenSetHandler.setCurScreenSet(screenSetHandler.getCurScreenSet() + 1);
						ssAC3.inData(functionData, i);
						ssAC3.validate(0, ssAC3.maxScrnNo - 1);
						ssAC3.outData();
						functionMessages.insertMessages(ssAC3.getFunctionMessages());
						screenSetHandler.getScreenSets().remove(ssAC3.getId());
						screenSetHandler.setCurScreenSet(screenSetHandler.getCurScreenSet() - 1);
					}
				}
			}
		}

		// restore original message when no message to display
		if (allowSelectRecord)
		{
			if (functionMessages.getMsgSev() == FunctionMessages.MSG_NONE)
			{
				functionMessages.insertMessages(orgFuncMessages);
			}
		}

		// Log all messages
		if (!functionMessages.chkNoMessage())
		{
			LOG.debug(LanguageResources.getString("FunctionValidate.ReturnedMessageFromValidate") + " : " + functionMessages);
		}

		return functionMessages.getMsgSev();
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
	 * @return true - this screen has already handled the updated processing<br>
	 *         false - let the standard process perform update
	 * 
	 */
	@Override
	public boolean update(JournalHeader journalHeader, FunctionTransactions functionTransactions, EquationStandardSession session)
					throws EQException
	{
		// clear the messages
		functionMessages.clearMessages();

		// loop through all the charge records
		for (int i = 1; i <= curData; i++)
		{
			ScreenSetAC3 fs = new ScreenSetAC3(id + i, fhd);
			fs.inData(functionData, i);
			fs.update(journalHeader, functionTransactions, session);

			// any error in update, then bypass
			functionMessages.insertMessages(fs.getFunctionMessages());
			if (functionMessages.getMsgSev() >= FunctionMessages.MSG_ERROR)
			{
				break;
			}
		}

		// Log all messages
		if (!functionMessages.chkNoMessage())
		{
			LOG.debug(LanguageResources.getString("FunctionUpdate.ReturnedMessageFromUpdate") + " : " + functionMessages);
		}

		return true;
	}

	/**
	 * Generate the function key for the screen
	 * 
	 * @return the function keys
	 */
	@Override
	protected FunctionKeys generateFkeys() throws EQException
	{
		super.generateFkeys();

		// should it include Add?
		if (securityLevel.isUpdateMessage() || securityLevel.isEnquireMode()
						|| securityLevel.getCheckerType() != securityLevel.CHCKR_NONE)
		{
		}
		else
		{
			functionKeys.addKey(FunctionKeys.KEY_ADD, "Add");

			if (!fhd.isLRPAuthTask())
			{
				functionKeys.addKey(FunctionKeys.KEY_ACCPT, "Accept");
			}

		}

		return functionKeys;
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
	 * 
	 * @throws EQException
	 */
	@Override
	protected int actionFkey(int ckey) throws EQException
	{
		// handle F6=Add
		if (ckey == FunctionKeys.KEY_ADD)
		{
			functionKeys.setFuncKey(FunctionKeys.KEY_NONE);

			// add the charge screen
			ScreenSetAC1 fs = new ScreenSetAC1(0, fhd);
			screenSetHandler.addScreenSet(fs);
			screenSetHandler.nextScreenSet();

			// load the data
			fs.inData(functionData);

			// display this new screen set
			return 0;
		}

		return 3;
	}

	/**
	 * Processing to setup the charge detail screen
	 * 
	 * @param index
	 *            - - the nth charge record
	 * 
	 * @return the function screen AC3
	 * 
	 * @throws EQException
	 */
	private ScreenSetAC3 processChargeDetail(int index) throws EQException
	{
		// add the charge screen
		ScreenSetAC3 fs = new ScreenSetAC3(0, fhd);
		screenSetHandler.addScreenSet(fs);
		screenSetHandler.nextScreenSet();

		// load the data
		functionData.chgFieldDbValue("CURINDEX", String.valueOf(index));
		fs.inData(functionData, index);

		return fs;
	}

	/**
	 * Generate the default charges
	 * 
	 */
	private void loadDefaultCharges(String optionFunction) throws EQException
	{
		try
		{
			EquationStandardListEnquiry chargeEnquiry = new EquationStandardListEnquiry("U64DER", equationStandardSession);

			// setup the fields for the enquiry API
			chargeEnquiry.setFieldValue("HZAB", chargeDAB); // Account branch (4A)
			chargeEnquiry.setFieldValue("HZAN", chargeDAN); // Basic part of account number (6A)
			chargeEnquiry.setFieldValue("HZAS", chargeDAS); // Account suffix (3A)
			chargeEnquiry.setFieldValue("HZBRNM", chargeBRNM); // Branch mnemonic (4A)
			chargeEnquiry.setFieldValue("HZDLP", chargeDLP); // Deal type (3A)
			chargeEnquiry.setFieldValue("HZDLR", chargeDLR); // Deal reference (13A)
			chargeEnquiry.setFieldValue("HZCMR", chargeCMR); // Commitment reference (13A)
			chargeEnquiry.setFieldValue("HZCCY", chargeDCCY); // Currency mnemonic (3A)
			chargeEnquiry.setFieldValue("HZDTP", chargeDTP); // Branch mnemonic (4A)
			chargeEnquiry.setFieldValue("HZATP", chargeATP); // Deal type (3A)
			chargeEnquiry.setFieldValue("HZEVNT", parentOption); // Event mnemonic (6A)
			chargeEnquiry.setFieldValue("HZSAM1", chargeDAMT); // Charge amount

			// new transaction, then remove the deal/account/commitment as this is not yet existing
			if (chargeENEW.equals(EqDataType.YES))
			{
				chargeEnquiry.setFieldValue("HZAB", "");
				chargeEnquiry.setFieldValue("HZAN", "");
				chargeEnquiry.setFieldValue("HZAS", "");
				chargeEnquiry.setFieldValue("HZBRNM", "");
				chargeEnquiry.setFieldValue("HZDLP", "");
				chargeEnquiry.setFieldValue("HZDLR", "");
				chargeEnquiry.setFieldValue("HZCMR", "");
			}

			// execute
			equationStandardSession.executeListEnquiry(chargeEnquiry);

			// resetup
			chargeEnquiry.setFieldValue("HZAB", chargeDAB); // Account branch (4A)
			chargeEnquiry.setFieldValue("HZAN", chargeDAN); // Basic part of account number (6A)
			chargeEnquiry.setFieldValue("HZAS", chargeDAS); // Account suffix (3A)
			chargeEnquiry.setFieldValue("HZBRNM", chargeBRNM); // Branch mnemonic (4A)
			chargeEnquiry.setFieldValue("HZDLP", chargeDLP); // Deal type (3A)
			chargeEnquiry.setFieldValue("HZDLR", chargeDLR); // Deal reference (13A)
			chargeEnquiry.setFieldValue("HZCMR", chargeCMR); // Commitment reference (13A)
			chargeEnquiry.setFieldValue("HZCCY", chargeDCCY); // Currency mnemonic (3A)
			chargeEnquiry.setFieldValue("HZDTP", chargeDTP); // Branch mnemonic (4A)
			chargeEnquiry.setFieldValue("HZATP", chargeATP); // Deal type (3A)
			chargeEnquiry.setFieldValue("HZEVNT", parentOption); // Event mnemonic (6A)
			chargeEnquiry.setFieldValue("HZSAM1", chargeDAMT); // Charge amount

			// load the HZ fields
			String tref = functionData.rtvFieldDbValue("TREF");
			String cab = functionData.rtvFieldDbValue("CAB");
			String can = functionData.rtvFieldDbValue("CAN");
			String cas = functionData.rtvFieldDbValue("CAS");
			String aref = functionData.rtvFieldDbValue("AREF");
			String ddtd = functionData.rtvFieldDbValue("DDTD");
			String esqn = functionData.rtvFieldDbValue("ESQN");
			functionData = functionTemplateData.duplicateFunctionData();
			functionData.chgFieldDbValue("TREF", tref);
			functionData.chgFieldDbValue("CAB", cab);
			functionData.chgFieldDbValue("CAN", can);
			functionData.chgFieldDbValue("CAS", cas);
			functionData.chgFieldDbValue("AREF", aref);
			functionData.chgFieldDbValue("DDTD", ddtd);
			functionData.chgFieldDbValue("ESQN", esqn);
			functionData.chgFieldDbValue("TCCY", chargeDCCY);
			functionData.chgFieldDbValue("TAMT", chargeDAMT);
			functionData.chgFieldDbValue("ENEW", chargeENEW);
			functionData.insertFieldData(functionData, chargeEnquiry.getHzDSData(), "", "", false);

			// for each of the repeating data, load the default value for the event mnemonic via calling ACG
			List<Record> records = chargeEnquiry.getHzListRcds();
			EquationDataStructureData eqDsDta = new EquationDataStructureData(chargeEnquiry.getHzListTemplateDSData().getEqDS());
			for (int i = 0; i < records.size(); i++)
			{
				String is = "_" + String.valueOf(i + 1);
				eqDsDta.setBytes(records.get(i).getContents());
				insertDetailsToFunctionData(eqDsDta.getFieldValue("HZECNM"), i + 1);
				functionData.chgFieldDbValue("COAD" + is, eqDsDta.getFieldValue("HZCOAD"));
			}
			curData = records.size();
			functionData.chgFieldDbValue("CURDATA", String.valueOf(curData));

			// allow user to override details
			FunctionAdaptor functionAdaptor = screenSetToCheck.getFunctionAdaptor();
			functionAdaptor.postLoadEFC(functionData);
		}
		catch (UnsupportedEncodingException e)
		{
			throw new EQException("FunctionScreenACG: defaultCharges:", e);
		}
		catch (CharConversionException e)
		{
			throw new EQException("FunctionScreenACG: defaultCharges:", e);
		}
	}

	/**
	 * Load the default value for a charge component
	 * 
	 * @param chargeComponent
	 *            - charge component
	 * 
	 * @return the transaction
	 */
	private EquationStandardTransaction loadDefaultChargeDetail(String chargeComponent, String calcOnAmount) throws EQException
	{
		EquationStandardTransaction transaction = new EquationStandardTransaction("C62ARRACG", equationStandardSession, 1000);

		// Set the transaction type
		transaction.setMode(IEquationStandardObject.FCT_ADD);
		transaction.setFieldValue("GZDLP", chargeDLP);
		transaction.setFieldValue("GZDLR", chargeDLR);
		transaction.setFieldValue("GZDBRM", chargeBRNM);
		transaction.setFieldValue("GZCMR", chargeCMR);
		transaction.setFieldValue("GZAB", chargeDAB);
		transaction.setFieldValue("GZAN", chargeDAN);
		transaction.setFieldValue("GZAS", chargeDAS);
		transaction.setFieldValue("GZEVNM", parentOption);
		transaction.setGSFieldValue("GSECNM", chargeComponent);
		transaction.setGSFieldValue("GSCAOD", calcOnAmount);

		if (chargeENEW.equals(EqDataType.YES))
		{
			transaction.setGSFieldValue("GSCCYC", chargeDCCY);
			transaction.setFieldValue("GZFLAG", "U");
			transaction.setFieldValue("GZATP", chargeATP);
		}
		else
		{
			transaction.setFieldValue("GZFLAG", "A");
		}

		eqUser.getSession().retrieveEquationTransaction(transaction);
		return transaction;
	}

	/**
	 * Retrieve the charge component details
	 * 
	 * @param chargeComponent
	 *            - the charge component to retrieve
	 * @param index
	 *            - the index
	 * 
	 * @throws EQException
	 */
	private void insertDetailsToFunctionData(String chargeComponent, int index) throws EQException
	{
		String is = "_" + String.valueOf(index);

		EquationStandardTransaction chargeTransaction = loadDefaultChargeDetail(chargeComponent, chargeDAMT);
		functionData.insertFieldData("OPT" + is, functionData.getFieldDatas().get("OPT"), "", false);
		functionData.insertFieldData("MSGSTS" + is, functionData.getFieldDatas().get("MSGSTS"), "", false);

		// charge amount + waived amount
		functionData.insertFieldData("CALA" + is, functionData.getFieldDatas().get("CALA"), "0", false);

		// charge amount
		functionData.insertFieldData("CHGA" + is, functionData.getFieldDatas().get("CHGA"), "0", false);

		functionData.insertFieldData("COAD" + is, functionData.getFieldDatas().get("COAD"), "", false);
		functionData.insertFieldData(functionData, chargeTransaction.getGsDSData(), "", is, false);
	}

	/**
	 * Ensure all records are revalidated
	 */
	private void revalidateAllRecords()
	{
		for (int i = 1; i <= curData; i++)
		{
			String index = "_" + i;
			functionData.chgFieldDbValue("MSGSTS" + index, "");
		}
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
		return allowSelectRecord;
	}

	/**
	 * Backup the original message severity of each charge record
	 */
	private void backupOrgMessages()
	{
		orgFuncMessages.insertMessages(functionMsgManager.getFunctionMessages());

		for (int i = 1; i <= curData; i++)
		{
			String index = "_" + i;
			functionData.insertFieldData("ORGMSGSTS" + index, functionData.rtvFieldData("MSGSTS"), functionData
							.rtvFieldDbValue("MSGSTS" + index), false);
		}
	}

	/**
	 * Called when a session (template) is restored
	 * 
	 */
	@Override
	protected void restoreTemplate()
	{
		revalidateAllRecords();
	}

	/**
	 * Called when a session is restored by supervisor
	 * 
	 */
	@Override
	protected void restoreSupervisor()
	{
		// is last record the EFC subfile?
		if (screenSetHandler.getScreenSets().size() == 3)
		{
			backupOrgMessages();
			allowSelectRecord = true;
		}
	}

	/**
	 * Performs printing on the specified screen
	 * 
	 * @returns the list of lines
	 * 
	 * @throws EQException
	 */
	@Override
	protected List<String> print() throws EQException
	{
		List<String> lines = new ArrayList<String>();

		// loop through all the charge records
		for (int i = 1; i <= curData; i++)
		{
			ScreenSetAC3 fs = new ScreenSetAC3(id + i, fhd);
			fs.inData(functionData, i);
			List<String> fslines = fs.print();
			lines.addAll(fslines);
			lines.add(" ");
			lines.add(" ");
		}
		return lines;
	}

	/**
	 * Reset charge details
	 */
	private void resetCharges()
	{
		curData = 0;
		chargeENEW = "";
		chargeDAB = "";
		chargeDAN = "";
		chargeDAS = "";
		chargeDCCY = "";
		chargeDAMT = "";
		chargeBRNM = "";
		chargeDLP = "";
		chargeDLR = "";
		chargeCMR = "";
		chargeATP = "";
		chargeDTP = "";
	}

	/**
	 * Return number of records
	 * 
	 * @return number of records
	 */
	public int curData()
	{
		return curData;
	}

	/**
	 * Convert the data into the BF complex type
	 * 
	 * @return the converted BF complex type
	 * 
	 * @throws EQException
	 */
	public Object cvtDataToBfData() throws EQException
	{
		FunctionBankFusionSrv functionBankFusionSrv = new FunctionBankFusionSrv();
		Object efcData = functionBankFusionSrv.getBankFusionDataTypeAC2(equationStandardSession, this);
		return efcData;
	}

}