package com.misys.equation.function.runtime;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.function.adaptor.AttributesAdaptor;
import com.misys.equation.function.adaptor.LayoutAdaptor;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.FunctionData;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.RepeatingDataManager;
import com.misys.equation.function.beans.RepeatingFieldData;

/**
 * This class a represents a function printer
 * 
 */
public class FunctionExcel
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionExcel.java 11994 2011-10-02 23:35:43Z fraramos $";
	private final Workbook workBook = new HSSFWorkbook();
	private final Sheet sheet;
	private final FunctionHandlerData fhd;
	private final FunctionData functionData;
	private AttributesAdaptor attributeAdaptor;
	private final EquationUser eqUser;
	private final Function function;
	private final Layout layout;
	private final List<DisplayAttributesSet> displayAttributesSets;
	private final Map<String, CellStyle> styles;
	private int rownum = 0;
	private int offset = 0;
	private int maxcols = 0;
	private LayoutAdaptor layoutAdaptor;
	private EquationStandardSession session;
	/**
	 * @param funcHandler
	 */
	public FunctionExcel(FunctionHandler funcHandler)
	{
		styles = createStyles(workBook);
		fhd = funcHandler.getFhd();
		functionData = funcHandler.rtvFunctionData();
		eqUser = fhd.getEquationUser();
		// get all the output fieldSets
		function = funcHandler.getFhd().getScreenSetHandler().rtvScreenSetMain().getFunction();
		layout = funcHandler.getFhd().getScreenSetHandler().rtvScreenSetMain().getLayout();
		displayAttributesSets = layout.getDisplayAttributesSets();
		layoutAdaptor = funcHandler.getFhd().getScreenSetHandler().rtvScreenSetMain().getLayoutAdaptor();
		session = funcHandler.rtvEquationSession();
		// **********************************************
		// Initialise the worksheet
		// **********************************************
		sheet = workBook.createSheet(layout.getId());

		// turn off gridlines
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);
		sheet.setFitToPage(true);
		sheet.setHorizontallyCenter(true);
		PrintSetup printSetup = sheet.getPrintSetup();
		printSetup.setLandscape(true);

		// the following three statements are required only for HSSF
		sheet.setAutobreaks(true);
		printSetup.setFitHeight((short) 1);
		printSetup.setFitWidth((short) 1);

		// Pop in some details
		Row row = sheet.createRow(rownum);
		Cell cell = row.createCell(offset);
		cell.setCellValue("User");
		cell.setCellStyle(styles.get("label"));
		cell = row.createCell(offset + 1);
		cell.setCellValue(eqUser.getUserId());
		cell.setCellStyle(styles.get("value"));
		rownum++;

		// Date
		row = sheet.createRow(rownum);
		cell = row.createCell(offset);
		cell.setCellValue("Date");
		cell.setCellStyle(styles.get("label"));
		cell = row.createCell(offset + 1);
		cell.setCellValue(eqUser.getEquationUnit().getProcessingDate());
		cell.setCellStyle(styles.get("value"));
		rownum++;

		// System
		row = sheet.createRow(rownum);
		cell = row.createCell(offset);
		cell.setCellValue("System");
		cell.setCellStyle(styles.get("label"));
		cell = row.createCell(offset + 1);
		cell.setCellValue(eqUser.getEquationUnit().getSystem().getSystemId());
		cell.setCellStyle(styles.get("value"));
		rownum++;

		// Unit
		row = sheet.createRow(rownum);
		cell = row.createCell(offset);
		cell.setCellValue("Unit");
		cell.setCellStyle(styles.get("label"));
		cell = row.createCell(offset + 1);
		cell.setCellValue(eqUser.getEquationUnit().getUnitId());
		cell.setCellStyle(styles.get("value"));
		rownum++;

		print();

		// resize the columns...
		sheet.autoSizeColumn(0);
		sheet.autoSizeColumn(1);
		sheet.autoSizeColumn(2);
		sheet.autoSizeColumn(3);
		for (int i = 3; i <= maxcols; i++)
		{
			sheet.autoSizeColumn(i);
		}
	}

	/**
	 * Print all the details in the excel sheet
	 */
	private void print()
	{
		try
		{
			// **********************************************
			// loop all the display groups
			// **********************************************
			for (int i = 0; i < displayAttributesSets.size(); i++)
			{
				// Retrieve the field set
				DisplayAttributesSet displayAttributesSet = displayAttributesSets.get(i);

				// the header row
				offset = 0;
				sheet.createRow(rownum);
				rownum++;
				Row headerRow = sheet.createRow(rownum);
				Cell headerCell = headerRow.createCell(offset);
				headerCell.setCellValue(displayAttributesSet.rtvLabel(fhd.getEquationUser()));
				headerCell.setCellStyle(styles.get("header"));
				headerCell = headerRow.createCell(offset + 1);
				headerCell.setCellStyle(styles.get("header"));
				headerCell = headerRow.createCell(offset + 2);
				headerCell.setCellStyle(styles.get("header"));
				headerCell = headerRow.createCell(offset + 3);
				headerCell.setCellStyle(styles.get("header"));
				rownum++;
				sheet.createRow(rownum);
				rownum++;

				// Loop the fields in the group
				offset++;
				DisplayItemList displayItems = displayAttributesSet.getDisplayItems();
				printDisplayItems(function.getInputFieldSet(displayAttributesSet.getId()), displayAttributesSet, displayItems);
			}
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
		}
	}

	/**
	 * Print the set of display items
	 * 
	 * @param inputFieldSet
	 * @param displayAttributeSet
	 * @param displayItems
	 * @throws EQException
	 */
	private void printDisplayItems(InputFieldSet inputFieldSet, DisplayAttributesSet displayAttributeSet,
					DisplayItemList displayItems) throws EQException
	{
		try
		{
			// display all the key fields
			for (int i = 0; i < displayItems.size(); i++)
			{
				if (displayItems.get(i) instanceof DisplayAttributes)
				{
					DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(i);
					InputField inputField = inputFieldSet.getInputField(displayAttributes.getId());
					attributeAdaptor = layoutAdaptor.getAttributesAdaptor(session, displayAttributes.getId());
					if (attributeAdaptor.isVisible(displayAttributes))
					{
						Row row = sheet.createRow(rownum);
						Cell cell = row.createCell(offset);
						cell.setCellValue(displayAttributes.rtvLabel(eqUser));
						cell.setCellStyle(styles.get("label"));
						cell = row.createCell(offset + 1);
						cell.setCellValue(functionData.rtvFieldInputValue(inputField.getId()));
						cell.setCellStyle(styles.get("value"));
						cell = row.createCell(offset + 2);
						cell.setCellValue(functionData.rtvFieldOutputValue(inputField.getId()));
						cell.setCellStyle(styles.get("value"));
						rownum++;
					}
				}
				else if (displayItems.get(i) instanceof DisplayGroup)
				{
					DisplayGroup displayGroup = (DisplayGroup) displayItems.get(i);
					// the header row
					sheet.createRow(rownum);
					rownum++;
					Row headerRow = sheet.createRow(rownum);
					Cell headerCell = headerRow.createCell(offset);
					headerCell.setCellValue(displayGroup.rtvLabel(fhd.getEquationUser()));
					rownum++;
					if (DisplayGroup.isRepeating(displayGroup))
					{
						// need to append lots more header fillers
						for (int j = 1; j <= displayGroup.getDisplayItems().size(); j++)
						{
							headerCell.setCellStyle(styles.get("header"));
							headerCell = headerRow.createCell(offset + j);
						}
						printDisplayItemsRepeating(inputFieldSet, displayAttributeSet, displayGroup.getDisplayItems());
					}
					else
					{
						// pop in an additional 2 header cells
						headerCell.setCellStyle(styles.get("header"));
						headerCell = headerRow.createCell(offset + 1);
						headerCell.setCellStyle(styles.get("header"));
						headerCell = headerRow.createCell(offset + 2);
						headerCell.setCellStyle(styles.get("header"));
						printDisplayItems(inputFieldSet, displayAttributeSet, displayGroup.getDisplayItems());
					}
					// popout a breaker
					sheet.createRow(rownum);
					rownum++;
				}
			}
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	/**
	 * print a repeating set of display items, requires horizontal processing
	 * 
	 * @param inputFieldSet
	 * @param displayAttributeSet
	 * @param displayItems
	 * @throws EQException
	 */
	private void printDisplayItemsRepeating(InputFieldSet inputFieldSet, DisplayAttributesSet displayAttributeSet,
					DisplayItemList displayItems) throws EQException
	{
		try
		{
			int rownnumSave = rownum;
			// display all the key fields
			for (int i = 0; i < displayItems.size(); i++)
			{
				if (displayItems.get(i) instanceof DisplayAttributes)
				{
					// reset the row
					rownum = rownnumSave;

					// save the maxcols
					if (i > maxcols)
					{
						maxcols = i;
					}

					// output the label (column heading)
					DisplayAttributes displayAttributes = (DisplayAttributes) displayItems.get(i);
					InputField inputField = inputFieldSet.getInputField(displayAttributes.getId());
					if (displayAttributes.getVisible().equals(DisplayAttributes.VISIBLE_YES))
					{
						Row row;
						if (i == 0)
						{
							row = sheet.createRow(rownum);
						}
						else
						{
							row = sheet.getRow(rownum);
						}
						Cell cell = row.createCell(offset + i);
						cell.setCellValue(displayAttributes.rtvLabel(eqUser));
						cell.setCellStyle(styles.get("label"));
						rownum++;

						// output the value (columnular)
						RepeatingFieldData fieldData = (RepeatingFieldData) functionData.rtvFieldData(inputField.getId());
						RepeatingDataManager repeatingDataManager = fieldData.rtvRepeatingDataManager();
						repeatingDataManager.moveFirst();
						while (repeatingDataManager.next())
						{
							if (i == 0)
							{
								row = sheet.createRow(rownum);
							}
							else
							{
								row = sheet.getRow(rownum);
							}
							cell = row.createCell(offset + i);
							String value = "";
							if (inputField.getDataType().equals(EqDataType.TYPE_CHAR))
							{
								value = repeatingDataManager.getDbValue(inputField.getId());
							}
							else
							{
								value = repeatingDataManager.getOutputValue(inputField.getId());
								if (value.trim().equals(""))
								{
									value = repeatingDataManager.getInputValue(inputField.getId());
								}
							}
							cell.setCellValue(value);
							cell.setCellStyle(styles.get("value"));
							rownum++;
						}
					}
				}
				else if (displayItems.get(i) instanceof DisplayGroup)
				{
					throw new EQException("Unexpected display group inside repeating group");
				}
			}
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			if (e instanceof EQException)
			{
				throw (EQException) e;
			}
			else
			{
				throw new EQException(e);
			}
		}
	}

	/**
	 * create a library of cell styles
	 */
	private static Map<String, CellStyle> createStyles(Workbook wb)
	{
		Map<String, CellStyle> styles = new HashMap<String, CellStyle>();

		// creating a custom palette for the workbook
		HSSFPalette palette = ((HSSFWorkbook) wb).getCustomPalette();

		// replacing the standard blue with Misys CYAN
		palette.setColorAtIndex(HSSFColor.BLUE.index, (byte) 0, (byte) 153, (byte) 255);

		// Header
		CellStyle style;
		Font headerFont = wb.createFont();
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerFont.setColor(HSSFColor.WHITE.index);
		style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFont(headerFont);
		styles.put("header", style);

		// Label
		Font font1 = wb.createFont();
		font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(font1);
		styles.put("label", style);

		// Value
		style = createBorderedStyle(wb);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setWrapText(true);
		styles.put("value", style);
		return styles;
	}

	/**
	 * Create a boxed style for a given workbook
	 * 
	 * @param wb
	 * @return
	 */
	private static CellStyle createBorderedStyle(Workbook wb)
	{
		CellStyle style = wb.createCellStyle();
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setRightBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
		return style;
	}

	/**
	 * @return the workbook associated to the spreadsheet
	 */
	public Workbook getWorkBook()
	{
		return workBook;
	}
}