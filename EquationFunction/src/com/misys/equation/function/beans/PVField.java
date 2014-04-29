package com.misys.equation.function.beans;

public class PVField extends Field
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PVField.java 7137 2010-05-04 14:51:10Z lima12 $";

	/**
	 * Default constructor for a PVField
	 */
	public PVField()
	{
		super();
	}

	/**
	 * Construct a PVField
	 * 
	 * @param id
	 *            - the PV field name
	 * @param label
	 *            - the PV short description
	 * @param description
	 *            - the PV description
	 */
	public PVField(String id, String label, String description)
	{
		super(id, label, description);
	}

	/**
	 * Copy constructor for a PVField
	 * 
	 * @param field
	 *            - the PV field
	 */
	public PVField(PVField field)
	{
		super(field);
	}

	/**
	 * Copy constructor for a Field
	 * 
	 * @param field
	 *            - the PV field
	 */
	public PVField(Field field)
	{
		super(field);
	}

	/**
	 * Determine whether standard bean validation should validate the field length
	 * 
	 * @return false, so that standard validation will not validate the field length
	 */
	@Override
	protected boolean shouldValidateFieldLength()
	{
		return false;
	}

}
