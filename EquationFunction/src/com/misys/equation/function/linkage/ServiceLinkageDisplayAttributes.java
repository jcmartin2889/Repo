package com.misys.equation.function.linkage;

import com.misys.equation.function.beans.DisplayAttributes;

public class ServiceLinkageDisplayAttributes extends AbstractServiceLinkage
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ServiceLinkageDisplayAttributes.java 14806 2012-11-05 11:58:11Z williae1 $";
	// the target display attributes
	protected DisplayAttributes targetDisplayAttribute;

	/**
	 * Constructor
	 * 
	 * @param targetDisplayAttribute
	 *            - the target display item
	 */
	public ServiceLinkageDisplayAttributes(DisplayAttributes targetDisplayAttribute)
	{
		this.targetDisplayAttribute = targetDisplayAttribute;
	}

	/**
	 * Link the source display attribute into the target display attribute
	 * 
	 * @param sourceDisplayAttribute
	 *            - the source display attribute
	 * @param locationId1
	 *            - the layout where the item is
	 * @param locationId2
	 *            - the display attribute field set where the item is
	 * 
	 * @return the target display attribute
	 */
	public DisplayAttributes link(DisplayAttributes sourceDisplayAttribute, String locationId1, String locationId2)
	{
		// clear messages
		messageContainer.clear();

		// sync it
		// sync(sourceDisplayAttribute, locationId1, locationId2);

		return targetDisplayAttribute;
	}

}
