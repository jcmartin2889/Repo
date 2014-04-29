package com.misys.equation.ui.pdfFonts;

import java.io.OutputStream;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class LangFont
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LangFont.java 12404 2011-12-16 13:47:08Z lima12 $";

	// Identifies the dimension
	public static final String DEFAULT_DIMENSION_MEASURE = "*ROWCOL";

	// For *ROWCOL - A4 landscape size - note all of these are dependent on the DEFAULT_FONTSIZE = 8
	protected static final float DEFAULT_DIMENSION_A4_LANDSCAPE_WIDTH = 132;
	protected static final float DEFAULT_DIMENSION_A4_LANDSCAPE_LENGTH = 66;

	// For *ROWCOL - A4 portrait maximum - note all of these are dependent on the DEFAULT_FONTSIZE = 8
	protected static final float DEFAULT_DIMENSION_A4_PORTRAIT_WIDTH = 100;

	protected static final int DEFAULT_FONTSIZE = 8;
	protected static final String NEWLINE = "\n";

	protected PdfWriter pdfWriter;
	protected Document document;
	protected Font font;

	// page size - default it to A4 landscape
	protected String dimension_type = DEFAULT_DIMENSION_MEASURE;
	protected float dimension_width = DEFAULT_DIMENSION_A4_LANDSCAPE_WIDTH;
	protected float dimension_length = DEFAULT_DIMENSION_A4_LANDSCAPE_LENGTH;

	/**
	 * Setup the details
	 * 
	 * @param byteoutputstream
	 *            - the byte output data
	 * 
	 * @throws Exception
	 */
	public void openOutputStream(OutputStream byteoutputstream) throws Exception
	{
		this.document = getDocument();
		this.pdfWriter = PdfWriter.getInstance(document, byteoutputstream);
		this.document.open();
		this.font = getFont();
	}

	/**
	 * Close the document
	 * 
	 * @throws Exception
	 */
	public void close() throws Exception
	{
		this.document.close();
	}

	/**
	 * Create a new page
	 * 
	 * @throws Exception
	 */
	public void nextPage() throws Exception
	{
		this.document.newPage();
	}

	/**
	 * Write the text to the PDF
	 * 
	 * @param text
	 *            - the text to be written
	 * 
	 * @throws Exception
	 */
	public void write(String text) throws Exception
	{
		Paragraph para = getDefaultParagraph();
		Chunk chunk = new Chunk(text, font);
		para.add(chunk);
		document.add(para);
	}

	/**
	 * Write the text with nextline to the PDF
	 * 
	 * @param text
	 *            - the text to be written
	 * @throws Exception
	 */
	public void writeln(String text) throws Exception
	{
		write(text + NEWLINE);
	}

	/**
	 * Write the array list of string to the PDF
	 * 
	 * @param lines
	 *            - the array of string to be printed
	 * 
	 * @throws Exception
	 */
	public void write(List<String> lines) throws Exception
	{
		for (int i = 0; i < lines.size(); i++)
		{
			write(lines.get(i));
		}
	}

	/**
	 * Return the default paragraph
	 * 
	 * @return the default paragraph
	 */
	public Paragraph getDefaultParagraph()
	{
		Paragraph para = new Paragraph();
		para.setLeading(DEFAULT_FONTSIZE);
		return para;
	}

	/**
	 * Return the default font style
	 * 
	 * @return the default font style
	 * 
	 * @throws Exception
	 */
	protected Font getFont() throws Exception
	{
		BaseFont baseFont = BaseFont.createFont("/com/misys/equation/ui/resources/COUR.TTF", BaseFont.IDENTITY_H, true);
		Font font = new Font(baseFont, DEFAULT_FONTSIZE, Font.NORMAL);
		return font;
	}

	/**
	 * Return the document
	 * 
	 * @return the document
	 */
	protected Document getDocument()
	{
		Document document = new Document(getPageSize());
		// document.setMargins(25, 25, 25, 25);
		return document;
	}

	/**
	 * Return the page size
	 * 
	 * @return the page size
	 */
	protected Rectangle getPageSize()
	{
		// A4 in portrait mode
		if (isA4Portrait())
		{
			Rectangle A4 = PageSize.A4;
			return A4;
		}

		// A4 in landscape mode
		else if (isA4Landscape())
		{
			Rectangle A4 = PageSize.A4.rotate();
			return A4;
		}

		// Try to calculate the dimension
		else if (dimension_type.equals(DEFAULT_DIMENSION_MEASURE))
		{
			// the width is compared against the length of A4 in landscape mode
			// the length will always be in A4
			float width = dimension_width * (PageSize.A4.right() / 100);
			Rectangle size = new Rectangle(width, PageSize.A4.top());
			return size;
		}

		// Default A4
		else
		{
			Rectangle A4 = PageSize.A4;
			return A4;
		}
	}

	/**
	 * Set the maximum width and length of a page
	 * 
	 * @param type
	 *            - the measurement type
	 * @param width
	 *            - the maximum width
	 * @param length
	 *            - the maximum length
	 */
	public void setSize(String type, float width, float length)
	{
		dimension_type = type;
		dimension_width = width;
		dimension_length = length;
	}

	/**
	 * Determine if A4 landscape to be use
	 * 
	 * @return true if A4 landscape to be use
	 */
	protected boolean isA4Landscape()
	{
		// measurement by *ROWCOL
		if (!dimension_type.equals(DEFAULT_DIMENSION_MEASURE))
		{
			return false;
		}

		// maximum of DEFAULT_DIMENSION_A4_LANDSCAPE_LENGTH lines
		if (dimension_length > DEFAULT_DIMENSION_A4_LANDSCAPE_LENGTH)
		{
			return false;
		}

		// must not exceed DEFAULT_DIMENSION_A4_LANDSCAPE_WIDTH characters (+10%) per line
		if (dimension_width > (1.10 * DEFAULT_DIMENSION_A4_LANDSCAPE_WIDTH))
		{
			return false;
		}

		// A4 landscape
		return true;
	}

	/**
	 * Determine if A4 portrait to be use
	 * 
	 * @return true if A4 portrait to be use
	 */
	protected boolean isA4Portrait()
	{
		// measurement by *ROWCOL
		if (!dimension_type.equals(DEFAULT_DIMENSION_MEASURE))
		{
			return false;
		}

		// must not exceed DEFAULT_DIMENSION_A4_PORTRAIT_WIDTH characters per line
		if (dimension_width > DEFAULT_DIMENSION_A4_PORTRAIT_WIDTH)
		{
			return false;
		}

		// A4 portrait
		return true;
	}

}
