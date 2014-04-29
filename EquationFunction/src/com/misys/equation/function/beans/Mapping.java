package com.misys.equation.function.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * Represents a mapping definition
 * 
 */
public class Mapping extends Element implements Comparable<Mapping>
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Mapping.java 17190 2013-09-03 11:49:59Z Lima12 $";

	/** Mapping source */
	private String source;

	/** Mapping target */
	private String target;

	/**
	 * Construct a new empty Mapping
	 */
	public Mapping()
	{
		super();
		setLabel("");
		setDescription("");
	}
	/**
	 * Clone a Mapping
	 * 
	 * @param mapping
	 *            mapping to clone
	 * 
	 */
	public Mapping(Mapping mapping)
	{
		super();
		this.source = mapping.getSource();
		this.target = mapping.getTarget();
		setLabel("");
		setDescription("");
	}

	/**
	 * Construct a new Mapping with the specified details
	 * 
	 * @param source
	 *            mapping source
	 * @param target
	 *            mapping target
	 * 
	 */
	public Mapping(String source, String target)
	{
		super();
		this.source = source;
		this.target = target;
		setLabel("");
		setDescription("");
	}

	/**
	 * This method is required for serialisation. Don't call it, use MappingList.editMapping() instead.
	 * 
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source)
	{
		this.source = source;
	}

	/**
	 * @return the source
	 */
	public String getSource()
	{
		return source;
	}

	/**
	 * This method is required for serialisation. Don't call it, use MappingList.editMapping() instead.
	 * 
	 * @param target
	 *            the target to set
	 */
	public void setTarget(String target)
	{
		this.target = target;
	}

	/**
	 * @return the target
	 */
	public String getTarget()
	{
		return target;
	}

	/**
	 * Returns the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		return source + Toolbox.TEXT_DELIMITER + target;
	}
	/**
	 * Compare a given Mapping with this object. If mapping toString() of this object is greater than the received object, then this
	 * object is greater than the other.
	 * 
	 * @return The result is a negative integer if this String object precedes the argument string. The result is a positive integer
	 *         if this String object follows the argument string. The result is zero if the strings are equal;
	 */
	public int compareTo(Mapping o)
	{
		return this.toString().compareTo(o.toString());
	}
}
