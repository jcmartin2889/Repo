package com.misys.equation.common.dao.beans;

import java.io.Serializable;

public class AbsRecord implements Serializable, Cloneable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: AbsRecord.java 9667 2010-11-03 17:03:15Z MACDONP1 $";

	/** Serialization version Id */
	private static final long serialVersionUID = 1L;
	// file name
	private String eqFileName;

	// library where the file name is located
	private String library;

	/**
	 * Return the file name
	 */
	public String getEqFileName()
	{
		return eqFileName;
	}

	/**
	 * Set the file name
	 * 
	 * @param fileName
	 *            - the file name
	 */
	protected void setEqFileName(String eqFileName)
	{
		this.eqFileName = eqFileName;
	}

	/**
	 * Return the library where the file is located
	 * 
	 * @return the library where the file is located
	 */
	public String getLibrary()
	{
		return library;
	}

	/**
	 * Set the library where the file is located
	 * 
	 * @param library
	 *            - the library where the file is located
	 */
	public void setLibrary(String library)
	{
		this.library = library;
	}

	/**
	 * Creates a shallow clone of this record data model.
	 */
	@Override
	public AbsRecord clone()
	{
		try
		{
			// use native clone to create a shallow clone
			return (AbsRecord) super.clone();
		}
		catch (CloneNotSupportedException cnse)
		{
			throw new IllegalStateException("Unable to clone bean!", cnse);
		}
	}
}