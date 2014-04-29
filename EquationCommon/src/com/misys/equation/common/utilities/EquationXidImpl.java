package com.misys.equation.common.utilities;

import javax.transaction.xa.Xid;

/**
 * 
 */
public class EquationXidImpl implements Xid
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationXidImpl.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	protected int formatId;
	protected byte gtrid[];
	protected byte bqual[];
	public EquationXidImpl()
	{
	}
	public EquationXidImpl(int formatId, byte gtrid[], byte bqual[])
	{
		this.formatId = formatId;
		this.gtrid = gtrid;
		this.bqual = bqual;
	}
	public int getFormatId()
	{
		return formatId;
	}
	public byte[] getBranchQualifier()
	{
		return bqual;
	}
	public byte[] getGlobalTransactionId()
	{
		return gtrid;
	}
}