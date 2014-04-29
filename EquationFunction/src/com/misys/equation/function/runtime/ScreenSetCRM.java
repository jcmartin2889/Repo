package com.misys.equation.function.runtime;

import java.util.ArrayList;
import java.util.List;

import org.apache.html.dom.HTMLDivElementImpl;
import org.apache.html.dom.HTMLDocumentImpl;
import org.apache.html.dom.HTMLTableCellElementImpl;
import org.apache.html.dom.HTMLTableElementImpl;
import org.apache.html.dom.HTMLTableRowElementImpl;
import org.w3c.dom.html.HTMLElement;

import com.misys.equation.common.access.EquationDataStructureData;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUnit;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IHPRecordDao;
import com.misys.equation.common.dao.beans.HPRecordDataModel;
import com.misys.equation.common.dao.beans.OCRecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSHH02;
import com.misys.equation.common.datastructure.EqDS_DSHH02Head;
import com.misys.equation.common.files.JournalHeader;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.CRMLimitCheckData;
import com.misys.equation.common.utilities.EqDataToolbox;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.adaptor.FieldSetAdaptor;
import com.misys.equation.function.beans.APIFieldSet;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.language.LanguageResources;
import com.misys.equation.function.tools.FunctionRuntimeToolbox;
import com.misys.equation.function.tools.HTMLToolbox;

public class ScreenSetCRM extends ScreenSet
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ScreenSetCRM.java 14803 2012-11-05 11:57:09Z williae1 $";

	// Logger
	private static final EquationLogger LOG = new EquationLogger(ScreenSetCRM.class);

	public final static String OPTION = "CRM";

	private int curData;
	private final FunctionData functionTemplateData;
	private final DaoFactory daoFactory = new DaoFactory();
	private final ScreenSet screenSetToCheck;

	/**
	 * Construct a Function Screen CRM
	 * 
	 * @param id
	 *            - screen set id
	 * @param functionHandlerData
	 *            the Function Handler Data
	 * 
	 * @throws EQException
	 */
	public ScreenSetCRM(int id, FunctionHandlerData fhd, ScreenSet screenSetToCheck) throws EQException
	{
		super(id, fhd, OPTION);
		this.maxScrnNo = 1;
		this.curData = 0;
		this.functionTemplateData = functionData.duplicateFunctionData();
		this.screenSetToCheck = screenSetToCheck;
	}

	/**
	 * Add a CRM warning message
	 * 
	 * @param apiId
	 *            - a unique id
	 * @param apiLabel
	 *            - a label
	 * @param apiDesc
	 *            - a description
	 * @param crmData
	 *            - the CRM data
	 * 
	 * @throws EQException
	 */
	public void addData(String apiId, String apiLabel, String apiDesc, byte[] crmData) throws EQException
	{
		IHPRecordDao hpDao = null;

		// any crm?
		if (crmData.length > 128)
		{
			// unit
			EquationUnit equationUnit = eqUser.getEquationUnit();

			// retrieve the DSHH02 Head
			EquationDataStructureData dscrmHead = new EquationDataStructureData(new EqDS_DSHH02Head());
			byte[] target = new byte[128];
			System.arraycopy(crmData, 0, target, 0, 128);
			dscrmHead.setBytes(target);

			// assume it will not be added to the function data
			boolean added = false;

			// retrieve the DSHH02 details
			String index = String.valueOf(curData + 1);
			int offset = 0;
			int count = 0;
			for (int i = 1; i <= Toolbox.parseInt(dscrmHead.getFieldValue(EqDS_DSHH02Head.HNUM), 0); i++)
			{
				int length = 128;
				offset += length;
				if (crmData.length < offset + length)
				{
					length = crmData.length - offset;
				}
				System.arraycopy(crmData, offset, target, 0, length);
				EquationDataStructureData dscrmErr = new EquationDataStructureData(new EqDS_DSHH02());
				dscrmErr.setBytes(target);

				// check if this warning has already been overridden
				FunctionMessage fm = functionMsgManager.generateMessage(equationStandardSession, functionMessages, id, 0, "",
								curData + 1, null, dscrmErr.getFieldValue(EqDS_DSHH02.HERR).trim(), "", "",
								FunctionMessages.MSG_NONE);

				// add to the function data
				if (fm != null)
				{
					added = true;
					count++;
					String is = index + "_" + String.valueOf(count);
					functionData.insertFieldData("CUS" + is, functionData.rtvFieldData("CUS"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HCUS), false);
					functionData.insertFieldData("CLC" + is, functionData.rtvFieldData("CLC"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HCLC), false);
					functionData.insertFieldData("GRP" + is, functionData.rtvFieldData("GRP"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HGRP), false);
					functionData.insertFieldData("CNA" + is, functionData.rtvFieldData("CNA"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HCNA), false);
					functionData.insertFieldData("LC" + is, functionData.rtvFieldData("LC"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HLC), false);
					functionData.insertFieldData("CCY" + is, functionData.rtvFieldData("CCY"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HCCY), false);
					functionData.insertFieldData("RAM" + is, functionData.rtvFieldData("RAM"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HRAM), false);
					functionData.insertFieldData("LAM" + is, functionData.rtvFieldData("LAM"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HAMA), false);
					functionData.insertFieldData("AAM" + is, functionData.rtvFieldData("AAM"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HAAM), false);
					functionData.insertFieldData("XPD" + is, functionData.rtvFieldData("XPD"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HXPD), false);
					functionData.insertFieldData("ERR" + is, functionData.rtvFieldData("ERR"), dscrmErr
									.getFieldValue(EqDS_DSHH02.HERR), false);

					// retrieve customer's exposure country
					String cnai = equationUnit.getSystemOption(equationStandardSession, "KFIL", "CNAI", equationUnit
									.isDevelopmentUnit());
					String cusCnai = EqDataToolbox.rtvCustomerRiskCountry(equationStandardSession, "", functionData
									.rtvFieldDbValue("CUS" + is), functionData.rtvFieldDbValue("CLC" + is), cnai);
					functionData.insertFieldData("CNA1" + is, functionData.rtvFieldData("CNA1"), cusCnai, false);

					// display negative available amount?
					String dsna = equationUnit.getSystemOption(equationStandardSession, "KFIL", "DSNA", equationUnit
									.isDevelopmentUnit());
					if (dsna.equals("Y"))
					{
						if (Toolbox.parseLong(functionData.rtvFieldDbValue("AAM" + is), 0) < 0)
						{
							functionData.chgFieldDbValue("AAM" + is, "0");
						}
					}

					functionData.chgFieldOutputValue("CUS" + is, EqDataToolbox.editCustomer(equationStandardSession, "",
									functionData.rtvFieldDbValue("CUS" + is), functionData.rtvFieldDbValue("CLC" + is)));
					functionData.chgFieldOutputValue("GRP" + is, EqDataToolbox.editGroup(equationStandardSession, "", functionData
									.rtvFieldDbValue("GRP" + is)));
					functionData.chgFieldOutputValue("CNA" + is, EqDataToolbox.editCountry(equationStandardSession, "",
									functionData.rtvFieldInputValue("CNA" + is)));

					String lc = functionData.rtvFieldDbValue("LC" + is);
					if (lc.length() > 2)
					{
						functionData.chgFieldOutputValue("LC" + is, EqDataToolbox.editParameter(equationStandardSession, "", "",
										"S" + lc.charAt(1), lc));
					}

					functionData.chgFieldOutputValue("CCY" + is, EqDataToolbox.editCcy(equationStandardSession, "", functionData
									.rtvFieldDbValue("CCY" + is)));

					String xpd = functionData.rtvFieldInputValue("XPD" + is).trim();
					if (!xpd.equals("") && !xpd.equals("0"))
					{
						functionData.chgFieldOutputValue("XPD" + is, EqDataToolbox.editDate(equationStandardSession, "", xpd,
										EqDataToolbox.DATEFORMAT_EXT));
					}

					functionData.chgFieldOutputValue("CNA1" + is, EqDataToolbox.editCountry(equationStandardSession, "",
									functionData.rtvFieldInputValue("CNA1" + is)));

					// in thousand?
					HPRecordDataModel hpRecord = new HPRecordDataModel(functionData.rtvFieldDbValue("CNA" + is), functionData
									.rtvFieldDbValue("GRP" + is), functionData.rtvFieldDbValue("CUS" + is), functionData
									.rtvFieldDbValue("CLC" + is));
					hpDao = daoFactory.getHPDao(equationStandardSession, hpRecord);

					hpRecord = hpDao.getHPRecord();
					if (hpRecord != null)
					{
						if (hpRecord.getInThousand().equals("Y"))
						{
							functionData.insertFieldData("INTH" + is, functionData.rtvFieldData("INTH"), EqDataToolbox
											.editParameter(equationStandardSession, "", "", "SNC75"), false);
							int decimal = EqDataToolbox.rtvDecimal(equationStandardSession, "", functionData.rtvFieldDbValue("CCY"
											+ is));
							functionData.chgFieldOutputValue("RAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
											Toolbox.truncateDbAmount(functionData.rtvFieldDbValue("RAM" + is), decimal, 3), "", 0,
											15));
							functionData.chgFieldOutputValue("LAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
											Toolbox.truncateDbAmount(functionData.rtvFieldDbValue("LAM" + is), decimal, 3), "", 0,
											15));
							functionData.chgFieldOutputValue("AAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
											Toolbox.truncateDbAmount(functionData.rtvFieldDbValue("AAM" + is), decimal, 3), "", 0,
											15));
						}
						else
						{
							functionData.insertFieldData("INTH" + is, functionData.rtvFieldData("INTH"), "", false);
							functionData.chgFieldOutputValue("RAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
											functionData.rtvFieldDbValue("RAM" + is), functionData.rtvFieldDbValue("CCY" + is), 0,
											15));
							functionData.chgFieldOutputValue("LAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
											functionData.rtvFieldDbValue("LAM" + is), functionData.rtvFieldDbValue("CCY" + is), 0,
											15));
							functionData.chgFieldOutputValue("AAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
											functionData.rtvFieldDbValue("AAM" + is), functionData.rtvFieldDbValue("CCY" + is), 0,
											15));
						}
					}
				}
			}

			// are there any messages to be added?
			if (added)
			{
				curData++;
				functionData.insertFieldData("APIID" + index, functionData.rtvFieldData("APIID"), apiId, false);
				functionData.insertFieldData("APILBL" + index, functionData.rtvFieldData("APILBL"), apiLabel, false);
				functionData.insertFieldData("APIDSC" + index, functionData.rtvFieldData("APIDSC"), apiDesc, false);
				functionData.insertFieldData("REF" + index, functionData.rtvFieldData("REF"), dscrmHead
								.getFieldValue(EqDS_DSHH02Head.HREF), false);
				functionData.insertFieldData("CUR" + index, functionData.rtvFieldData("CUR"), dscrmHead
								.getFieldValue(EqDS_DSHH02Head.HCCY), false);
				functionData.insertFieldData("AMT" + index, functionData.rtvFieldData("AMT"), dscrmHead
								.getFieldValue(EqDS_DSHH02Head.HAMA), false);

				functionData.chgFieldOutputValue("CUR" + index, EqDataToolbox.editCcy(equationStandardSession, "", functionData
								.rtvFieldDbValue("CUR" + index)));
				functionData.chgFieldOutputValue("AMT" + index, EqDataToolbox.editAmount(equationStandardSession, "", functionData
								.rtvFieldDbValue("AMT" + index), functionData.rtvFieldDbValue("CUR" + index), 0, 15));

				functionData.chgFieldDbValue("MSGSTS", String.valueOf(functionMessages.getMsgSev()));
			}
		}

		// update number of details
		functionData.chgFieldDbValue("CURDATA", String.valueOf(curData));
	}

	/**
	 * Clear the CRM warning messages
	 * 
	 */
	private void clearData() throws EQException
	{
		curData = 0;
		functionMessages.clearMessages();
		functionData = functionTemplateData.duplicateFunctionData();
	}

	/**
	 * When this screen set is called from the "previous screen set",<br>
	 * then the system should display this only if it contains data
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
		// initialise
		firstTime = false;

		// supervisor mode?
		if (securityLevel.getCheckerType() == SecurityLevel.CHCKR_SUPER)
		{
			if (functionData.rtvFieldDbValue("MSGSTS").trim().length() > 0)
			{
				return SCRN_CUR;
			}
			else
			{
				return SCRN_NEXT;
			}
		}

		// should execute CRM?
		APIFieldSet crmApiFieldSet = screenSetToCheck.getFunction().getUpdateAPI(Function.CRM_FIELDSET);
		FieldSetAdaptor fieldSetAdaptor = screenSetToCheck.getFunctionAdaptor().getFieldSetAdaptor(equationStandardSession,
						crmApiFieldSet);
		fieldSetAdaptor.setFunctionData(fhd, screenSetToCheck.getFunctionData());
		if (!fieldSetAdaptor.shouldExecute(crmApiFieldSet))
		{
			return SCRN_NEXT;
		}

		// perform crm limit checking
		shutterHandler.clear();
		clearData();
		CRMLimitCheckData crmLimitCheckData = FunctionRuntimeToolbox
						.performCRMLimitCheck(equationStandardSession, screenSetToCheck);
		if (crmLimitCheckData != null)
		{
			addData("", layout.rtvLabel(eqUser), layout.rtvDescription(eqUser), crmLimitCheckData.getCrmData());

			// any error?
			if (crmLimitCheckData.getDsepms().trim().length() > 0)
			{
				functionMsgManager.generateMessage(equationStandardSession, functionMessages, id, 0, "", curData + 1, null,
								crmLimitCheckData.getDsepms(), "", LanguageResources
												.getString("ScreenSetCRM.ReturnedMessageFromCRMCheckLimit"),
								FunctionMessages.MSG_NONE);

				// add this function message to the previous screen set list of messages
				screenSetToCheck.getFunctionMessages().insertMessages(functionMessages);

				return SCRN_PREV;
			}
		}

		// display screen or continue previous screen?
		if (curData > 0)
		{
			return SCRN_CUR;
		}
		else
		{
			return SCRN_NEXT;
		}

	}

	/**
	 * When this screen set is called from the "next screen set",<br>
	 * then the system should not redisplay the CRM warning message
	 * 
	 * @param sourceScreenSetId
	 *            - screen set Id of the current screen set
	 * 
	 * @return SCRN_PREV - if the previous screen set should be displayed<br>
	 */
	@Override
	protected int onEntryScreenSetFromNext(int sourceScreenSetId) throws EQException
	{
		return SCRN_PREV;
	}

	/**
	 * Set the values of the default element
	 * 
	 * @param htmlToolbox
	 *            - the HTML toolbox
	 * @param htmlDocument
	 *            - the HTML document
	 */
	@Override
	protected void chgDefaultElement(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument)
	{
		super.chgDefaultElement(htmlToolbox, htmlDocument);

		// limit override S?
		if (fhd.getEquationUser().getLimitOverride().equals(OCRecordDataModel.LIMIT_OVERRIDE_SUPERVISOR))
		{
			htmlToolbox.chgInputElement(htmlDocument, HTMLToolbox.OBJ_CRM_S, fhd.getEquationUser().getLimitOverride());
		}
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
		// table number
		int tableNumber = 0;

		// create the main div
		HTMLDivElementImpl mainDiv = htmlToolbox.createDivElement(htmlDocument, HTMLToolbox.DIV_MAIN);
		mainDiv.setClassName("inputArea MainDivClass");

		// setup number of messages
		curData = Toolbox.parseInt(functionData.rtvFieldDbValue("CURDATA"), 0);

		// default idElement
		String idElement = function.getInputFieldSets().get(0).getId();

		for (int i = 1; i <= curData; i++)
		{
			tableNumber++;
			String apiTableId = idElement + tableNumber;
			HTMLTableElementImpl apiTable = htmlToolbox.createDefaultTableWithRow(htmlDocument, apiTableId, apiTableId,
							functionData.rtvFieldDbValue("APIID" + i) + functionData.rtvFieldDbValue("APILBL" + i), functionData
											.rtvFieldDbValue("APIDSC" + i), 1, false);
			apiTable.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 1));

			// create the row that will contain all the details
			HTMLTableRowElementImpl apiRow = htmlToolbox.createDefaultRow(htmlDocument, "", "", 1, "", null);
			HTMLTableCellElementImpl apiCell = (HTMLTableCellElementImpl) apiRow.getFirstChild();
			apiTable.appendChild(apiRow);

			// group for the fixed details
			tableNumber++;
			String fixedDetailId = idElement + "_FixedDetails";
			HTMLTableElementImpl subFixedTable = (HTMLTableElementImpl) rtvCRMFixedDetailsHTML(htmlToolbox, htmlDocument,
							fixedDetailId, "", i, tableNumber);
			subFixedTable.setWidth("90%");
			subFixedTable.setClassName(subFixedTable.getClassName() + " " + HTMLToolbox.NON_RESIZE);
			subFixedTable.setClassName(subFixedTable.getClassName().replace("NonGroupTableClass", "SubNonGroupTableClass"));

			// add to the api cell
			HTMLElement div = htmlToolbox.createDefaultGroupDiv(htmlDocument, "", subFixedTable, false, "");
			htmlToolbox.processShutter(htmlDocument, HTMLToolbox.TABLE_DETAIL + fixedDetailId + tableNumber, fixedDetailId
							+ tableNumber, true);
			apiCell.appendChild(div);

			for (int j = 1; j <= 100; j++)
			{
				// check if this exists, if it does not exists, then exit
				if (functionData.rtvFieldData("CUS" + i + "_" + j) == null)
				{
					break;
				}

				// group for the repeating details
				String repeatingDetailId = idElement + "_RepeatingHeaderDetails" + i + j;
				tableNumber++;
				HTMLTableElementImpl subRepeatingTable = (HTMLTableElementImpl) rtvCRMHeadHTML(htmlToolbox, htmlDocument,
								repeatingDetailId, "", i, tableNumber);
				subRepeatingTable.setWidth("90%");
				subRepeatingTable.setClassName(subRepeatingTable.getClassName() + " " + HTMLToolbox.NON_RESIZE);
				subRepeatingTable.setClassName(subRepeatingTable.getClassName().replace("NonGroupTableClass",
								"SubNonGroupTableClass"));

				rtvCRMHtml(htmlToolbox, htmlDocument, subRepeatingTable, i, j);
				subRepeatingTable.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 6));

				// add to the api cell
				div = htmlToolbox.createDefaultGroupDiv(htmlDocument, "", subRepeatingTable, false, "");
				htmlToolbox.processShutter(htmlDocument, HTMLToolbox.TABLE_DETAIL + repeatingDetailId + tableNumber,
								repeatingDetailId + tableNumber, true);
				apiCell.appendChild(div);
			}

			// add the table to the main div
			mainDiv.appendChild(htmlToolbox.createDefaultGroupDiv(htmlDocument, "", apiTable, false, ""));
			htmlToolbox.processShutter(htmlDocument, HTMLToolbox.TABLE_DETAIL + apiTableId, apiTableId, true);
		}

		// add the elements to the document
		htmlDocument.appendChild(htmlToolbox.createBodyElement(htmlDocument, OPTION + "Body")).appendChild(mainDiv);

		// return
		return mainDiv;
	}
	/**
	 * Generate the HTML screen of a CRM message warning
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table element
	 * @param dshh02
	 *            - the CRM warning
	 * @param index
	 *            - the current index
	 * 
	 * @return the table element
	 */
	private HTMLElement rtvCRMFixedDetailsHTML(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument, String id, String label,
					int index, int tableNumber)
	{
		HTMLTableCellElementImpl cellElement;
		HTMLElement outputElement;

		// create the table
		HTMLTableElementImpl table = htmlToolbox.createDefaultTableWithRow(htmlDocument, id + tableNumber, id + tableNumber,
						functionData.rtvFieldDbValue("REF" + index), "", 2, false);

		// retrieve the input fields from the function
		DisplayAttributes refDisplayAttributeField = null;
		DisplayAttributes amtDisplayAttributeField = null;

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
					else if (displayAttributes.getId().equals("AMT"))
					{
						amtDisplayAttributeField = displayAttributes;
					}
				}
			}
		}

		// create the first row for the reference
		HTMLTableRowElementImpl row1 = htmlToolbox.createRowElement(htmlDocument, "RefLabel" + index + HTMLToolbox.ROW_SUFFIX);
		cellElement = htmlToolbox.createCellElement(htmlDocument, row1, "RefLabel" + index, 0, 1, "");
		cellElement.setWidth("20%");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "REFLabel" + index, refDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelTextBold");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row1, "RefLabel" + index, 1, 1, "");
		cellElement.setWidth("80%");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "REF" + index, functionData.rtvFieldDbValue("REF"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		// create the second row for the amount
		HTMLTableRowElementImpl row2 = htmlToolbox.createRowElement(htmlDocument, "AmountLabel" + index + HTMLToolbox.ROW_SUFFIX);
		cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "AmountLabel" + index, 0, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "AMTLabel" + index, amtDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelTextBold");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "AmountLabel" + index, 1, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CUR" + index, functionData.rtvFieldDbValue("CUR"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "AMT" + index, functionData.rtvFieldOutputValue("AMT"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		// add all the rows to the table
		table.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 2));
		table.appendChild(row1);
		table.appendChild(row2);
		table.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 2));

		return table;
	}

	/**
	 * Generate the HTML screen of a CRM message warning
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table element
	 * @param dshh02
	 *            - the CRM warning
	 * @param index
	 *            - the current index
	 * 
	 * @return the table element
	 */
	private HTMLElement rtvCRMHeadHTML(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument, String id, String label, int index,
					int tableNumber)
	{
		HTMLTableCellElementImpl cellElement;
		HTMLElement outputElement;

		// create the table
		HTMLTableElementImpl table = htmlToolbox.createDefaultTableWithRow(htmlDocument, id + tableNumber, id + tableNumber, label,
						"", 6, false);
		table.appendChild(htmlToolbox.createDefaultRowSpacer(htmlDocument, "", "", 6));

		// retrieve the input fields from the function
		DisplayAttributes lcDisplayAttributeField = null;
		DisplayAttributes ccyDisplayAttributeField = null;
		DisplayAttributes ramDisplayAttributeField = null;
		DisplayAttributes lamDisplayAttributeField = null;
		DisplayAttributes aamDisplayAttributeField = null;
		DisplayAttributes xpdDisplayAttributeField = null;

		for (int i = 0; i < layout.getDisplayAttributesSets().size(); i++)
		{
			DisplayItemList displayItems = layout.getDisplayAttributesSets().get(i).getDisplayItems();
			for (int j = 0; j < displayItems.size(); j++)
			{
				if (displayItems.get(j) instanceof DisplayAttributes)
				{
					DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(j);
					if (displayAttributes.getId().equals("LC"))
					{
						lcDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("CCY"))
					{
						ccyDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("RAM"))
					{
						ramDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("LAM"))
					{
						lamDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("AAM"))
					{
						aamDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("XPD"))
					{
						xpdDisplayAttributeField = displayAttributes;
					}
				}
			}
		}

		// create the column heading for the details
		HTMLTableRowElementImpl row4 = htmlToolbox.createRowElement(htmlDocument, OPTION + index + "_Row4");
		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 0, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "LCLabel" + index, lcDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 1, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CCYLabel" + index, ccyDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 2, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "RAMLabel" + index, ramDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 3, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "LAMLabel" + index, lamDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 4, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "AAMLabel" + index, aamDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 5, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "XPDLabel" + index, xpdDisplayAttributeField
						.rtvLabel(eqUser));
		outputElement.setClassName("labelColumnHeaderText");
		cellElement.appendChild(outputElement);

		// add all the rows to the table
		table.appendChild(row4);

		return table;
	}

	/**
	 * Generate the HTML screen of a CRM message warning
	 * 
	 * @param htmlDocument
	 *            - the HTML document
	 * @param table
	 *            - the table element
	 * @param dshh02
	 *            - the CRM warning
	 * @param index
	 *            - the current index
	 * 
	 * @return the table element
	 */
	private HTMLElement rtvCRMHtml(HTMLToolbox htmlToolbox, HTMLDocumentImpl htmlDocument, HTMLTableElementImpl table,
					int indexHead, int indexData)
	{
		HTMLTableCellElementImpl cellElement;
		HTMLElement outputElement;
		String index = String.valueOf(indexHead) + "_" + String.valueOf(indexData);

		// retrieve the input fields from the function
		DisplayAttributes cusDisplayAttributeField = null;
		DisplayAttributes cna1DisplayAttributeField = null;
		DisplayAttributes cnaDisplayAttributeField = null;
		DisplayAttributes grpDisplayAttributeField = null;

		for (int i = 0; i < layout.getDisplayAttributesSets().size(); i++)
		{
			DisplayItemList displayItems = layout.getDisplayAttributesSets().get(i).getDisplayItems();
			for (int j = 0; j < displayItems.size(); j++)
			{
				if (displayItems.get(j) instanceof DisplayAttributes)
				{
					DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(j);
					if (displayAttributes.getId().equals("CUS"))
					{
						cusDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("GRP"))
					{
						grpDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("CNA"))
					{
						cnaDisplayAttributeField = displayAttributes;
					}
					else if (displayAttributes.getId().equals("CNA1"))
					{
						cna1DisplayAttributeField = displayAttributes;
					}
				}
			}
		}

		// create the second row for the customer/group/country
		String label = "";
		HTMLTableRowElementImpl row2 = null;
		HTMLTableRowElementImpl row3 = null;
		if (!functionData.rtvFieldDbValue("CUS" + index).equals(""))
		{
			label = functionData.rtvFieldDbValue("CUS" + index) + " " + functionData.rtvFieldDbValue("CLC" + index) + " "
							+ functionData.rtvFieldOutputValue("CUS" + index);

			row2 = htmlToolbox.createRowElement(htmlDocument, OPTION + index + "_Row2");
			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 0, 1, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CUSLabel" + index, cusDisplayAttributeField
							.rtvLabel(eqUser));
			outputElement.setClassName("labelTextBold");
			cellElement.appendChild(outputElement);

			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 1, 3, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CUS" + index, functionData.rtvFieldDbValue("CUS"
							+ index));
			outputElement.setClassName("enquiryText");
			cellElement.appendChild(outputElement);
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CLC" + index, functionData.rtvFieldDbValue("CLC"
							+ index));
			outputElement.setClassName("enquiryText");
			cellElement.appendChild(outputElement);

			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CUSOutput" + index, functionData
							.rtvFieldOutputValue("CUS" + index));
			outputElement.setClassName("outputText");
			cellElement.appendChild(outputElement);

			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 4, 1, "");
			cellElement.setClassName("wf_RIGHT_ALIGN");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "INTH" + index, functionData.rtvFieldDbValue("INTH"
							+ index));
			outputElement.setClassName("outputText");
			cellElement.appendChild(outputElement);

			// create the third row for the country
			row3 = htmlToolbox.createRowElement(htmlDocument, OPTION + index + "_Row3");
			cellElement = htmlToolbox.createCellElement(htmlDocument, row3, "", 0, 1, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CNA1Label" + index, cna1DisplayAttributeField
							.rtvLabel(eqUser));
			outputElement.setClassName("labelTextBold");
			cellElement.appendChild(outputElement);

			cellElement = htmlToolbox.createCellElement(htmlDocument, row3, "", 1, 3, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CNA1" + index, functionData.rtvFieldDbValue("CNA1"
							+ index));
			outputElement.setClassName("enquiryText");
			cellElement.appendChild(outputElement);

			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CNA1Output" + index, functionData
							.rtvFieldOutputValue("CNA1" + index));
			outputElement.setClassName("outputText");
			cellElement.appendChild(outputElement);
		}

		if (!functionData.rtvFieldDbValue("CNA" + index).equals(""))
		{
			label = functionData.rtvFieldDbValue("CNA" + index) + " " + functionData.rtvFieldOutputValue("CNA" + index);

			row2 = htmlToolbox.createRowElement(htmlDocument, OPTION + index + "_Row2");
			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 0, 1, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CNALabel" + index, cnaDisplayAttributeField
							.rtvLabel(eqUser));
			outputElement.setClassName("labelText");
			cellElement.appendChild(outputElement);

			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 1, 3, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CNA" + index, functionData.rtvFieldDbValue("CNA"
							+ index));
			outputElement.setClassName("enquiryText");
			cellElement.appendChild(outputElement);

			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CNAOutput" + index, functionData
							.rtvFieldOutputValue("CNA" + index));
			outputElement.setClassName("outputText");
			cellElement.appendChild(outputElement);

			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 4, 1, "");
			cellElement.setClassName("wf_RIGHT_ALIGN");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "INTH" + index, functionData.rtvFieldDbValue("INTH"
							+ index));
			outputElement.setClassName("enquiryText");
			cellElement.appendChild(outputElement);

		}

		if (!functionData.rtvFieldDbValue("GRP" + index).equals(""))
		{
			label = functionData.rtvFieldDbValue("GRP" + index) + " " + functionData.rtvFieldOutputValue("GRP" + index);

			row2 = htmlToolbox.createRowElement(htmlDocument, OPTION + index + "_Row2");
			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 0, 1, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "GRPLabel" + index, grpDisplayAttributeField
							.rtvLabel(eqUser));
			outputElement.setClassName("labelText");
			cellElement.appendChild(outputElement);

			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 1, 3, "");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "GRP" + index, functionData.rtvFieldDbValue("GRP"
							+ index));
			outputElement.setClassName("enquiryText");
			cellElement.appendChild(outputElement);

			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "GRPOutput" + index, functionData
							.rtvFieldOutputValue("GRP" + index));
			outputElement.setClassName("outputText");
			cellElement.appendChild(outputElement);

			cellElement = htmlToolbox.createCellElement(htmlDocument, row2, "", 4, 1, "");
			cellElement.setClassName("wf_RIGHT_ALIGN");
			outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "INTH" + index, functionData.rtvFieldDbValue("INTH"
							+ index));
			outputElement.setClassName("enquiryText");
			cellElement.appendChild(outputElement);
		}

		// create the fourth row for the details
		HTMLTableRowElementImpl row4 = htmlToolbox.createRowElement(htmlDocument, OPTION + index + "_Row4");
		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 0, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "LC" + index, functionData.rtvFieldOutputValue("LC"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 1, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "CCY" + index, functionData.rtvFieldDbValue("CCY"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 2, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "RAM" + index, functionData.rtvFieldOutputValue("RAM"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 3, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "LAM" + index, functionData.rtvFieldOutputValue("LAM"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 4, 1, "");
		cellElement.setClassName("wf_RIGHT_ALIGN");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "AAM" + index, functionData.rtvFieldOutputValue("AAM"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		cellElement = htmlToolbox.createCellElement(htmlDocument, row4, "", 5, 1, "");
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "XPD" + index, functionData.rtvFieldOutputValue("XPD"
						+ index));
		outputElement.setClassName("enquiryText");
		cellElement.appendChild(outputElement);

		// append the label to the first row
		outputElement = htmlToolbox.createDefaultSpanElement(htmlDocument, "", label);
		htmlToolbox.getInnerTableOfFirstRow(table).getFirstChild().getFirstChild().appendChild(outputElement);

		// add all the rows to the table
		table.appendChild(row2);
		if (row3 != null)
		{
			table.appendChild(row3);
		}
		table.appendChild(row4);

		return table;
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
		int msgSev = FunctionMessages.MSG_NONE;
		FunctionMessages fms = new FunctionMessages();
		List<FunctionMessage> fmarr = functionMessages.getMessages();

		// loop through the list of function messages and see if it has already been overridden
		for (int i = 0; i < fmarr.size(); i++)
		{
			FunctionMessage fm = fmarr.get(i);
			functionMsgManager.generateMessage(equationStandardSession, fms, fm.getScreenSetId(), fm.getScrnNo(),
							fm.getFieldName(), fm.getSequence(), null, fm.getEqMessage().getDsepms(), "", "",
							FunctionMessages.MSG_NONE);
		}

		functionMessages = fms;
		msgSev = functionMessages.getMsgSev();

		// no more error
		if (msgSev == FunctionMessages.MSG_NONE)
		{
			functionData.chgFieldDbValue("MSGSTS", "");
		}

		// Log all messages
		if (!functionMessages.chkNoMessage())
		{
			LOG.debug(LanguageResources.getString("FunctionValidate.ReturnedMessageFromValidate") + " : " + functionMessages);
		}

		return msgSev;
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
		functionMessages.clearMessages();
		return true;
	}

	/**
	 * Called when a session is restored by supervisor
	 * 
	 */
	@Override
	protected void restoreSupervisor()
	{
		curData = Toolbox.parseInt(functionData.rtvFieldDbValue("CURDATA"), 0);
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
		return new ArrayList<String>();
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
		functionKeys.deleteKey(FunctionKeys.KEY_PRINT);
		functionKeys.deleteKey(FunctionKeys.KEY_EXCEL);
		return functionKeys;
	}

	/**
	 * Update the field output value of the CRM function data
	 * 
	 * @param equationStandardSession
	 *            - the Equation Standard Session
	 * @param functionData
	 *            - the function data
	 */
	public static void updateFieldOutputValue(EquationStandardSession equationStandardSession, FunctionData functionData)
	{
		int curData = Toolbox.parseInt(functionData.rtvFieldDbValue("CURDATA"), 0);

		for (int index = 1; index <= curData; index++)
		{
			functionData.chgFieldOutputValue("CUR" + index, EqDataToolbox.editCcy(equationStandardSession, "", functionData
							.rtvFieldDbValue("CUR" + index)));
			functionData.chgFieldOutputValue("AMT" + index, EqDataToolbox.editAmount(equationStandardSession, "", functionData
							.rtvFieldDbValue("AMT" + index), functionData.rtvFieldDbValue("CUR" + index), 0, 15));

			for (int j = 1; j <= 100; j++)
			{
				String is = index + "_" + j;

				// check if this exists, if it does not exists, then exit
				if (functionData.rtvFieldData("CUS" + is) == null)
				{
					break;
				}

				functionData.chgFieldOutputValue("CUS" + is, EqDataToolbox.editCustomer(equationStandardSession, "", functionData
								.rtvFieldDbValue("CUS" + is), functionData.rtvFieldDbValue("CLC" + is)));
				functionData.chgFieldOutputValue("GRP" + is, EqDataToolbox.editGroup(equationStandardSession, "", functionData
								.rtvFieldDbValue("GRP" + is)));
				functionData.chgFieldOutputValue("CNA" + is, EqDataToolbox.editCountry(equationStandardSession, "", functionData
								.rtvFieldInputValue("CNA" + is)));

				String lc = functionData.rtvFieldDbValue("LC" + is);
				if (lc.length() > 2)
				{
					functionData.chgFieldOutputValue("LC" + is, EqDataToolbox.editParameter(equationStandardSession, "", "", "S"
									+ lc.charAt(1), lc));
				}

				functionData.chgFieldOutputValue("CCY" + is, EqDataToolbox.editCcy(equationStandardSession, "", functionData
								.rtvFieldDbValue("CCY" + is)));

				String xpd = functionData.rtvFieldInputValue("XPD" + is).trim();
				if (!xpd.equals("") && !xpd.equals("0"))
				{
					functionData.chgFieldOutputValue("XPD" + is, EqDataToolbox.editDate(equationStandardSession, "", xpd,
									EqDataToolbox.DATEFORMAT_EXT));
				}

				functionData.chgFieldOutputValue("CNA1" + is, EqDataToolbox.editCountry(equationStandardSession, "", functionData
								.rtvFieldInputValue("CNA1" + is)));

				// in thousand?
				HPRecordDataModel hpRecord = new HPRecordDataModel(functionData.rtvFieldDbValue("CNA" + is), functionData
								.rtvFieldDbValue("GRP" + is), functionData.rtvFieldDbValue("CUS" + is), functionData
								.rtvFieldDbValue("CLC" + is));

				DaoFactory daoFactory = new DaoFactory();
				IHPRecordDao hpDao = daoFactory.getHPDao(equationStandardSession, hpRecord);

				hpRecord = hpDao.getHPRecord();
				if (hpRecord != null)
				{
					if (hpRecord.getInThousand().equals("Y"))
					{
						functionData.insertFieldData("INTH" + is, functionData.rtvFieldData("INTH"), EqDataToolbox.editParameter(
										equationStandardSession, "", "", "SNC75"), false);
						int decimal = EqDataToolbox.rtvDecimal(equationStandardSession, "", functionData
										.rtvFieldDbValue("CCY" + is));
						functionData.chgFieldOutputValue("RAM" + is, EqDataToolbox.editAmount(equationStandardSession, "", Toolbox
										.truncateDbAmount(functionData.rtvFieldDbValue("RAM" + is), decimal, 3), "", 0, 15));
						functionData.chgFieldOutputValue("LAM" + is, EqDataToolbox.editAmount(equationStandardSession, "", Toolbox
										.truncateDbAmount(functionData.rtvFieldDbValue("LAM" + is), decimal, 3), "", 0, 15));
						functionData.chgFieldOutputValue("AAM" + is, EqDataToolbox.editAmount(equationStandardSession, "", Toolbox
										.truncateDbAmount(functionData.rtvFieldDbValue("AAM" + is), decimal, 3), "", 0, 15));
					}
					else
					{
						functionData.insertFieldData("INTH" + is, functionData.rtvFieldData("INTH"), "", false);
						functionData.chgFieldOutputValue("RAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
										functionData.rtvFieldDbValue("RAM" + is), functionData.rtvFieldDbValue("CCY" + is), 0, 15));
						functionData.chgFieldOutputValue("LAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
										functionData.rtvFieldDbValue("LAM" + is), functionData.rtvFieldDbValue("CCY" + is), 0, 15));
						functionData.chgFieldOutputValue("AAM" + is, EqDataToolbox.editAmount(equationStandardSession, "",
										functionData.rtvFieldDbValue("AAM" + is), functionData.rtvFieldDbValue("CCY" + is), 0, 15));
					}
				}
			}
		}

	}

	/**
	 * 
	 * Return number of records
	 * 
	 * 
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
		Object crmData = functionBankFusionSrv.getBankFusionDataTypeCRM(equationStandardSession, this);
		return crmData;
	}

}