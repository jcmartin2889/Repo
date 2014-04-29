package com.misys.equation.common.internal.eapi.dataaccesslibrary;

/**
 * Serialization support.
 */
public class StoredObject
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: StoredObject.java 4646 2009-09-07 16:28:30Z weddelc1 $";

	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	private Object[] arrTxnObject = null;
	private Object[] arrTxnType = null;
	private Object[] arrTxnId = null;

	/**
	 * Return the list of objects
	 * 
	 * @return the list of objects
	 */
	public Object[] getArrTxnObject()
	{
		return arrTxnObject;
	}

	/**
	 * Return the list of object type
	 * 
	 * @return the list of object type
	 */
	public Object[] getArrTxnType()
	{
		return arrTxnType;
	}

	/**
	 * Set the list of object
	 * 
	 * @param objects
	 *            - the list of object
	 */
	public void setArrTxnObject(Object[] objects)
	{
		arrTxnObject = objects;
	}

	/**
	 * Set the list of object types
	 * 
	 * @param objects
	 *            - the list of object types
	 */
	public void setArrTxnType(Object[] objects)
	{
		arrTxnType = objects;
	}

	/**
	 * Return the list of object id
	 * 
	 * @return the list of object id
	 */
	public Object[] getArrTxnId()
	{
		return arrTxnId;
	}

	/**
	 * Set the list of object id
	 * 
	 * @param objects
	 *            - the list of object id
	 */
	public void setArrTxnId(Object[] objects)
	{
		arrTxnId = objects;
	}

}
