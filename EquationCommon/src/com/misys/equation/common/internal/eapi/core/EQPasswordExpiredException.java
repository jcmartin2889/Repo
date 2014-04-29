package com.misys.equation.common.internal.eapi.core;

public class EQPasswordExpiredException extends EQActionErrorException
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQPasswordExpiredException.java 12083 2011-10-11 18:11:23Z esther.williams $";
	public EQPasswordExpiredException(String reason, String aError)
	{
		super(reason, aError);
	}
}
