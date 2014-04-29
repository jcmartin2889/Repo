package com.misys.equation.ui.helpers;

import java.io.ByteArrayOutputStream;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.common.internal.eapi.core.EQException;

public class EqPDFWriter
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EqPDFWriter.java 13191 2012-04-05 10:04:33Z jonathan.perkins $";
	private Document pdfDoc;
	private ByteArrayOutputStream pdfOutputStream;

	/**
	 * Constructor to create a new PDF document
	 * 
	 */
	public EqPDFWriter() throws EQException
	{
		open();
	}

	/**
	 * Return the PDF output stream
	 * 
	 * @return the PDF output stream
	 */
	public ByteArrayOutputStream getPdfOutputStream()
	{
		return pdfOutputStream;
	}

	/**
	 * Opens the PDF
	 * 
	 * @throws EQException
	 */
	public void open() throws EQException
	{
		try
		{
			pdfOutputStream = new ByteArrayOutputStream();
			pdfDoc = new Document(PageSize.A4.rotate());
			pdfDoc.open();
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			throw new EQException("EqPDFWriter: open Failed: ", e);
		}
	}

	/**
	 * Closes the PDF
	 * 
	 * @throws EQException
	 */
	public void close() throws EQException
	{
		try
		{
			pdfDoc.close();
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			throw new EQException("EqPDFWriter: close Failed: ", e);
		}

	}

	/**
	 * Write to the PDF
	 * 
	 * @param str
	 *            - the string to be printed
	 * 
	 * @throws EQException
	 */
	public void write(String str) throws EQException
	{
		write(str, FontFactory.COURIER, 8);
	}

	/**
	 * Write to the PDF
	 * 
	 * @param str
	 *            - the string to be printed
	 * @param fontName
	 *            - font name
	 * @param fontSize
	 *            - font size
	 * 
	 * @throws EQException
	 */
	public void write(String str, String fontName, int fontSize) throws EQException
	{
		try
		{
			Paragraph para = new Paragraph(str, FontFactory.getFont(fontName, fontSize));
			para.setLeading(fontSize);
			pdfDoc.add(para);
		}
		catch (Exception e)
		{
			EquationCommonContext.getContext().getLOG().error(e);
			throw new EQException("EqPDFWriter: write Failed: ", e);
		}
	}

	/**
	 * Write the array list of string to the PDF
	 * 
	 * @param lines
	 *            - the array of string to be printed
	 * @param fontName
	 *            - font name
	 * @param fontSize
	 *            - font size
	 * 
	 * @throws EQException
	 */
	public void write(List<String> lines, String fontName, int fontSize) throws EQException
	{
		for (int i = 0; i < lines.size(); i++)
		{
			write(lines.get(i), fontName, fontSize);
		}
	}

	/**
	 * Write the array list of string to the PDF in the default font/size
	 * 
	 * @param lines
	 *            - the array of string to be printed
	 * 
	 * @throws EQException
	 */
	public void write(List<String> lines) throws EQException
	{
		write(lines, FontFactory.COURIER, 8);
	}
}
