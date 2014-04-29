package com.misys.equation.ui.pdfFonts;

import java.io.OutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;

public class LangFontAR extends LangFont
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: LangFontAR.java 12404 2011-12-16 13:47:08Z lima12 $";
	/**
	 * Setup the details
	 * 
	 * @param byteoutputstream
	 *            - the byte output data
	 * 
	 * @throws Exception
	 */
	@Override
	public void openOutputStream(OutputStream byteoutputstream) throws Exception
	{
		this.document = getDocument();
		this.pdfWriter = PdfWriter.getInstance(document, byteoutputstream);
		this.pdfWriter.setRunDirection(PdfWriter.RUN_DIRECTION_NO_BIDI);
		this.document.open();
		this.font = getFont();
	}

	/**
	 * Return the default font style
	 * 
	 * @return the default font style
	 * 
	 * @throws Exception
	 */
	@Override
	protected Font getFont() throws Exception
	{
		return super.getFont();
	}

	/**
	 * Write the text to the PDF
	 * 
	 * @param text
	 *            - the text to be written
	 * 
	 * @throws Exception
	 */
	@Override
	public void write(String text) throws Exception
	{
		// Paragraph para = getDefaultParagraph();
		// Chunk chunk = new Chunk(text, font);
		// para.add(chunk);
		// document.add(para);

		PdfContentByte cb = pdfWriter.getDirectContent();
		ColumnText ct = new ColumnText(cb);
		ct.setArabicOptions(ColumnText.AR_LIG);
		ct.setSimpleColumn(50, 10, 1000, 600);
		ct.setLeading(0, 1);
		ct.setRunDirection(PdfWriter.RUN_DIRECTION_LTR);
		ct.setSpaceCharRatio(PdfWriter.SPACE_CHAR_RATIO_DEFAULT);
		ct.addText(new Chunk(text, font));
		ct.go();
	}
}
