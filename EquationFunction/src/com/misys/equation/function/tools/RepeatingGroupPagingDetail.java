package com.misys.equation.function.tools;

import com.misys.equation.common.utilities.EqDataType;
import com.misys.equation.common.utilities.Toolbox;

public class RepeatingGroupPagingDetail
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: RepeatingGroupPagingDetail.java 7610 2010-06-01 17:10:41Z MACDONP1 $";
	boolean firstRecord;
	boolean lastRecord;
	String html;
	String invisibleHTML;

	/**
	 * Construct empty object
	 */
	public RepeatingGroupPagingDetail()
	{
		this.firstRecord = false;
		this.lastRecord = false;
		this.html = "";
		this.invisibleHTML = "";
	}

	/**
	 * Determine whether the first record
	 * 
	 * @return true if the first record
	 */
	public boolean isFirstRecord()
	{
		return firstRecord;
	}

	/**
	 * Set whether the first record
	 * 
	 * @param firstRecord
	 *            - true if the first record
	 */
	public void setFirstRecord(boolean firstRecord)
	{
		this.firstRecord = firstRecord;
	}

	/**
	 * Determine whether the last record
	 * 
	 * @return true if the last record
	 */
	public boolean isLastRecord()
	{
		return lastRecord;
	}

	/**
	 * Set whether the last record
	 * 
	 * @param lastRecord
	 *            - true if the last record
	 */
	public void setLastRecord(boolean lastRecord)
	{
		this.lastRecord = lastRecord;
	}

	/**
	 * Return the HTML string
	 * 
	 * @return the HTML string
	 */
	public String getHtml()
	{
		return html;
	}

	/**
	 * Set the HTML string
	 * 
	 * @param html
	 *            - the HTML string
	 */
	public void setHtml(String html)
	{
		this.html = html;
	}

	/**
	 * Return the invisible HTML
	 * 
	 * @return the invisible HTML
	 */
	public String getInvisibleHTML()
	{
		return invisibleHTML;
	}

	/**
	 * Set the invisible HTML
	 * 
	 * @param invisibleHTML
	 *            - the invisible HTML
	 */
	public void setInvisibleHTML(String invisibleHTML)
	{
		this.invisibleHTML = invisibleHTML;
	}

	/**
	 * Convert to string
	 * 
	 * @return the string representation
	 */
	@Override
	public String toString()
	{
		return Toolbox.cvtBooleanToYN(firstRecord) + EqDataType.GLOBAL_EQUALDELIMETER + Toolbox.cvtBooleanToYN(lastRecord)
						+ EqDataType.GLOBAL_EQUALDELIMETER + html + EqDataType.GLOBAL_EQUALDELIMETER + invisibleHTML;
	}

}
