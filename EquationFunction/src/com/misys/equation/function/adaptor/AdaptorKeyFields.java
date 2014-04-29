package com.misys.equation.function.adaptor;

/**
 * A class to simplify the management of the three GAZPF key fields that are specific to different user exit types.
 */

public class AdaptorKeyFields
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AdaptorKeyFields.java 6793 2010-03-31 12:10:20Z deroset $";
	/**
	 * The GAZFLD value This was originally strictly the field name, but
	 */
	private String gazfld = "";
	private String gazpvn = "";
	private String gaztyp = "";
	public String getGazfld()
	{
		return gazfld;
	}
	public void setGazfld(String gazfld)
	{
		this.gazfld = gazfld;
	}
	public String getGazpvn()
	{
		return gazpvn;
	}
	public void setGazpvn(String gazpvn)
	{
		this.gazpvn = gazpvn;
	}
	public String getGaztyp()
	{
		return gaztyp;
	}
	public void setGaztyp(String gaztyp)
	{
		this.gaztyp = gaztyp;
	}
}
