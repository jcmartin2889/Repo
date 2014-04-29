package com.misys.equation.common.utilities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.misys.equation.common.access.EquationPVFieldMetaData;
import com.misys.equation.common.access.EquationPVMetaData;
import com.misys.equation.common.access.EquationUser;
import com.misys.equation.common.internal.eapi.core.EQException;

public class EquationPVMetaDataCache implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationPVMetaDataCache.java 11001 2011-05-19 14:03:00Z MACDONP1 $";

	private static final long serialVersionUID = 6746759061344885956L;

	private final EquationPVMetaData pvMetaData;
	private boolean displayed;

	private String hdrNames;
	private String hdrDescs;
	private String hdrStarts;
	private String hdrLens;
	private String hdrDbs;
	private String hdrPos;
	private int numberOfLinesHeader;

	private final Hashtable<String, String> hdrDescsFormatted;

	/**
	 * Construct a cache for the Equation PV Meta Data
	 * 
	 * @param pvMetaData
	 *            - the PV meta data
	 */
	public EquationPVMetaDataCache(EquationPVMetaData pvMetaData)
	{
		this.pvMetaData = pvMetaData;
		this.displayed = false;
		this.hdrNames = "";
		this.hdrDescs = "";
		this.hdrStarts = "";
		this.hdrLens = "";
		this.hdrDbs = "";
		this.hdrPos = "";
		this.numberOfLinesHeader = 1;

		this.hdrDescsFormatted = new Hashtable<String, String>();
	}

	/**
	 * Return the PV meta data|
	 * 
	 * @return the PV meta data|
	 */
	public EquationPVMetaData getPvMetaData()
	{
		return pvMetaData;
	}

	/**
	 * Return the list of header names (javascript compatible)
	 * 
	 * @return the list of header names
	 */
	public String getHdrNames()
	{
		return hdrNames;
	}

	/**
	 * Return the list of header description (javascript compatible)
	 * 
	 * @return the list of header description (javascript compatible)
	 */
	public String getHdrDescs()
	{
		return hdrDescs;
	}

	/**
	 * Return the list of header index (javascript compatible)
	 * 
	 * @return the list of header index (javascript compatible)
	 */
	public String getHdrStarts()
	{
		return hdrStarts;
	}

	/**
	 * Return the list of header length (javascript compatible)
	 * 
	 * @return the list of header length (javascript compatible)
	 */
	public String getHdrLens()
	{
		return hdrLens;
	}

	/**
	 * Return the list of header db (javascript compatible)
	 * 
	 * @return the list of header db (javascript compatible)
	 */
	public String getHdrDbs()
	{
		return hdrDbs;
	}

	/**
	 * Return the list of header position (javascript compatible)
	 * 
	 * @return the list of header position (javascript compatible)
	 */
	public String getHdrPoss()
	{
		return hdrPos;
	}

	/**
	 * Return the number of lines for the header
	 * 
	 * @return the number of lines for the header
	 */
	public int getNumberOfLinesHeader()
	{
		return numberOfLinesHeader;
	}

	/**
	 * Setup other information for PV display
	 */
	public void setupDisplay() throws EQException
	{
		try
		{
			// if the PV has already been processed, do not processed anymore
			if (displayed)
			{
				return;
			}

			List<String> arrHdrDescs = new ArrayList<String>();
			List<Integer> arrHdrStarts = new ArrayList<Integer>();
			List<Integer> arrHdrLens = new ArrayList<Integer>();
			List<String> arrHdrDbs = new ArrayList<String>();
			List<String> arrHdrPos = new ArrayList<String>();
			for (int i = 0; i < pvMetaData.rtvNumberOfColumn(); i++)
			{
				EquationPVFieldMetaData fieldMetaData = pvMetaData.rtvFieldMetaData(pvMetaData.getHdrNames().get(i));
				arrHdrDescs.add(putBR(fieldMetaData.getHeader(), fieldMetaData.getLength()));
				arrHdrStarts.add(fieldMetaData.getIndex());
				arrHdrLens.add(fieldMetaData.getLength());
				arrHdrDbs.add(fieldMetaData.getDb());
				arrHdrPos.add(pvMetaData.getHdrPositions().get(i));
			}

			hdrNames = Toolbox.arrayToStringRplForJavaScript(pvMetaData.getHdrNames(), "'");
			hdrDescs = Toolbox.arrayToStringRplForJavaScript(arrHdrDescs, "'");
			hdrStarts = Toolbox.arrayToStringRplForJavaScript(arrHdrStarts, "'");
			hdrLens = Toolbox.arrayToStringRplForJavaScript(arrHdrLens, "'");
			hdrDbs = Toolbox.arrayToStringRplForJavaScript(arrHdrDbs, "'");
			hdrPos = Toolbox.arrayToStringRplForJavaScript(arrHdrPos, "'");

			// count the number of lines for the header
			int idx = hdrPos.indexOf("NT");
			while (idx >= 0)
			{
				numberOfLinesHeader++;
				idx = hdrPos.indexOf("NT", idx + 1);
			}

			// mark the PV as displayed and processed
			displayed = true;
		}
		catch (Exception e)
		{
			throw new EQException("EquationPVMetaDataCache: setupDisplay - Failed " + pvMetaData.getId(), e);
		}
	}

	/**
	 * Return the list of header description in user's language (javascript compatible)
	 * 
	 * @return the list of header description in user's language (javascript compatible)
	 */
	public String rtvHdrDescs(EquationUser eqUser)
	{
		// English, then return if already been formatted
		String formatted = hdrDescsFormatted.get(eqUser.getLanguageId());
		if (formatted != null)
		{
			return formatted;
		}

		// Retrieve it
		List<String> arrHdrDescs = new ArrayList<String>();

		for (int i = 0; i < pvMetaData.rtvNumberOfColumn(); i++)
		{
			EquationPVFieldMetaData fieldMetaData = pvMetaData.rtvFieldMetaData(pvMetaData.getHdrNames().get(i));
			arrHdrDescs.add(putBR(fieldMetaData.rtvHeader(eqUser), fieldMetaData.getLength()));
		}

		// convert so Java script compatible
		formatted = Toolbox.arrayToStringRplForJavaScript(arrHdrDescs, "'");
		hdrDescsFormatted.put(eqUser.getLanguageId(), formatted);

		return formatted;
	}

	/**
	 * When a description is longer than the field, then put a BR in order to wrap the description
	 * 
	 * @param desc
	 *            - the description
	 * @param length
	 *            - the field length
	 * 
	 * @return the description with embedded BR
	 */
	private String putBR(String desc, int length)
	{
		// minimum length for the description
		int maxLen = (int) (length * 1.5);
		String breakBR = "<BR>";

		// split the description
		String[] arrDesc = desc.split(" ");

		// only one element, then return it
		if (arrDesc.length <= 1)
		{
			return desc;
		}

		// reconstruct the string with next line
		else
		{
			StringBuilder str = new StringBuilder(arrDesc[0]);
			int curLength = arrDesc[0].length();
			for (int i = 1; i < arrDesc.length; i++)
			{
				// is this going to exceed the maximum length per line, put a break
				if (curLength + arrDesc[i].length() > maxLen)
				{
					str.append(breakBR);
					str.append(arrDesc[i]);
				}

				// just add it without a break
				else
				{
					str.append(" " + arrDesc[i]);
				}
			}
			return str.toString();
		}
	}

}
