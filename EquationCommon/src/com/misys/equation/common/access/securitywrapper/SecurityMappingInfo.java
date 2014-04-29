package com.misys.equation.common.access.securitywrapper;

import java.io.Serializable;

public class SecurityMappingInfo implements Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SecurityMappingInfo.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	/**
	 * Unique serial UID
	 */
	private static final long serialVersionUID = 8793799767503052814L;

	private final String table;
	private final String field;
	private final int sequence;
	private final String type;

	/**
	 * 
	 * @param field
	 * @param sequence
	 * @param type
	 */
	public SecurityMappingInfo(final String table, final String field, final int sequence, final String type)
	{
		super();
		this.table = table;
		this.field = field;
		this.sequence = sequence;
		this.type = type;
	}

	/**
	 * 
	 * @return
	 */
	public String getTable()
	{
		return table;
	}

	/**
	 * 
	 * @return
	 */
	public String getField()
	{
		return field;
	}

	/**
	 * 
	 * @return
	 */
	public int getSequence()
	{
		return sequence;
	}

	/**
	 * 
	 * @return
	 */
	public String getType()
	{
		return type;
	}

}
