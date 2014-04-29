/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class Overrides.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class Overrides implements java.io.Serializable
{

	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	// --------------------------/
	// - Class/Member Variables -/
	// --------------------------/

	/**
	 * Field serialVersionUID.
	 */
	public static final long serialVersionUID = 1L;

	/**
	 * This specifies the type of override to be applied: ' '-No override 'A'-All warning messages 'S'-Override only messages in the
	 * override selection collection 'E'-Override all messages apart from the messages in the override exclusion collection.
	 * 
	 * The override previous warnings flag is only used to force through warnings that have been approved by the client application
	 * on a previous call to the API. Normally this is not set on the first call of a Create or Update service. To override a set of
	 * previous warnings it must be set to "A".
	 * 
	 */
	private java.lang.String _overrideType;

	/**
	 * Reserved for future use.
	 * 
	 */
	private java.util.Vector<bf.com.misys.eqf.types.header.EqMessage> _overrideSelectionList;

	/**
	 * Reserved for future use.
	 * 
	 */
	private java.util.Vector<bf.com.misys.eqf.types.header.EqMessage> _overrideExclusionList;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public Overrides()
	{
		super();
		this._overrideSelectionList = new java.util.Vector<bf.com.misys.eqf.types.header.EqMessage>();
		this._overrideExclusionList = new java.util.Vector<bf.com.misys.eqf.types.header.EqMessage>();
	}

	// -----------/
	// - Methods -/
	// -----------/

	/**
	 * 
	 * 
	 * @param vOverrideExclusion
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addOverrideExclusion(final bf.com.misys.eqf.types.header.EqMessage vOverrideExclusion)
					throws java.lang.IndexOutOfBoundsException
	{
		this._overrideExclusionList.addElement(vOverrideExclusion);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vOverrideExclusion
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addOverrideExclusion(final int index, final bf.com.misys.eqf.types.header.EqMessage vOverrideExclusion)
					throws java.lang.IndexOutOfBoundsException
	{
		this._overrideExclusionList.add(index, vOverrideExclusion);
	}

	/**
	 * 
	 * 
	 * @param vOverrideSelection
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addOverrideSelection(final bf.com.misys.eqf.types.header.EqMessage vOverrideSelection)
					throws java.lang.IndexOutOfBoundsException
	{
		this._overrideSelectionList.addElement(vOverrideSelection);
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vOverrideSelection
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void addOverrideSelection(final int index, final bf.com.misys.eqf.types.header.EqMessage vOverrideSelection)
					throws java.lang.IndexOutOfBoundsException
	{
		this._overrideSelectionList.add(index, vOverrideSelection);
	}

	/**
	 * Method enumerateOverrideExclusion.
	 * 
	 * @return an Enumeration over all bf.com.misys.eqf.types.header.EqMessage elements
	 */
	public java.util.Enumeration<? extends bf.com.misys.eqf.types.header.EqMessage> enumerateOverrideExclusion()
	{
		return this._overrideExclusionList.elements();
	}

	/**
	 * Method enumerateOverrideSelection.
	 * 
	 * @return an Enumeration over all bf.com.misys.eqf.types.header.EqMessage elements
	 */
	public java.util.Enumeration<? extends bf.com.misys.eqf.types.header.EqMessage> enumerateOverrideSelection()
	{
		return this._overrideSelectionList.elements();
	}

	/**
	 * Overrides the java.lang.Object.equals method.
	 * 
	 * @param obj
	 * @return true if the objects are equal.
	 */
	@Override()
	public boolean equals(final java.lang.Object obj)
	{
		if (this == obj)
			return true;

		if (obj instanceof Overrides)
		{

			Overrides temp = (Overrides) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._overrideType != null)
			{
				if (temp._overrideType == null)
					return false;
				if (this._overrideType != temp._overrideType)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._overrideType);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._overrideType);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideType);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideType);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._overrideType.equals(temp._overrideType))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideType);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideType);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideType);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideType);
					}
				}
			}
			else if (temp._overrideType != null)
				return false;
			if (this._overrideSelectionList != null)
			{
				if (temp._overrideSelectionList == null)
					return false;
				if (this._overrideSelectionList != temp._overrideSelectionList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._overrideSelectionList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._overrideSelectionList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideSelectionList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideSelectionList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._overrideSelectionList.equals(temp._overrideSelectionList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideSelectionList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideSelectionList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideSelectionList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideSelectionList);
					}
				}
			}
			else if (temp._overrideSelectionList != null)
				return false;
			if (this._overrideExclusionList != null)
			{
				if (temp._overrideExclusionList == null)
					return false;
				if (this._overrideExclusionList != temp._overrideExclusionList)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._overrideExclusionList);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._overrideExclusionList);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideExclusionList);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideExclusionList);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._overrideExclusionList.equals(temp._overrideExclusionList))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideExclusionList);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideExclusionList);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._overrideExclusionList);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._overrideExclusionList);
					}
				}
			}
			else if (temp._overrideExclusionList != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Method getOverrideExclusion.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the bf.com.misys.eqf.types.header.EqMessage at the given index
	 */
	public bf.com.misys.eqf.types.header.EqMessage getOverrideExclusion(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._overrideExclusionList.size())
		{
			throw new IndexOutOfBoundsException("getOverrideExclusion: Index value '" + index + "' not in range [0.."
							+ (this._overrideExclusionList.size() - 1) + "]");
		}

		return (bf.com.misys.eqf.types.header.EqMessage) _overrideExclusionList.get(index);
	}

	/**
	 * Method getOverrideExclusion.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public bf.com.misys.eqf.types.header.EqMessage[] getOverrideExclusion()
	{
		bf.com.misys.eqf.types.header.EqMessage[] array = new bf.com.misys.eqf.types.header.EqMessage[0];
		return (bf.com.misys.eqf.types.header.EqMessage[]) this._overrideExclusionList.toArray(array);
	}

	/**
	 * Method getOverrideExclusionCount.
	 * 
	 * @return the size of this collection
	 */
	public int getOverrideExclusionCount()
	{
		return this._overrideExclusionList.size();
	}

	/**
	 * Method getOverrideSelection.
	 * 
	 * @param index
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 * @return the value of the bf.com.misys.eqf.types.header.EqMessage at the given index
	 */
	public bf.com.misys.eqf.types.header.EqMessage getOverrideSelection(final int index) throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._overrideSelectionList.size())
		{
			throw new IndexOutOfBoundsException("getOverrideSelection: Index value '" + index + "' not in range [0.."
							+ (this._overrideSelectionList.size() - 1) + "]");
		}

		return (bf.com.misys.eqf.types.header.EqMessage) _overrideSelectionList.get(index);
	}

	/**
	 * Method getOverrideSelection.Returns the contents of the collection in an Array.
	 * <p>
	 * Note: Just in case the collection contents are changing in another thread, we pass a 0-length Array of the correct type into
	 * the API call. This way we <i>know</i> that the Array returned is of exactly the correct length.
	 * 
	 * @return this collection as an Array
	 */
	public bf.com.misys.eqf.types.header.EqMessage[] getOverrideSelection()
	{
		bf.com.misys.eqf.types.header.EqMessage[] array = new bf.com.misys.eqf.types.header.EqMessage[0];
		return (bf.com.misys.eqf.types.header.EqMessage[]) this._overrideSelectionList.toArray(array);
	}

	/**
	 * Method getOverrideSelectionCount.
	 * 
	 * @return the size of this collection
	 */
	public int getOverrideSelectionCount()
	{
		return this._overrideSelectionList.size();
	}

	/**
	 * Returns the value of field 'overrideType'. The field 'overrideType' has the following description: This specifies the type of
	 * override to be applied: ' '-No override 'A'-All warning messages 'S'-Override only messages in the override selection
	 * collection 'E'-Override all messages apart from the messages in the override exclusion collection.
	 * 
	 * The override previous warnings flag is only used to force through warnings that have been approved by the client application
	 * on a previous call to the API. Normally this is not set on the first call of a Create or Update service. To override a set of
	 * previous warnings it must be set to "A".
	 * 
	 * 
	 * @return the value of field 'OverrideType'.
	 */
	public java.lang.String getOverrideType()
	{
		return this._overrideType;
	}

	/**
	 * Overrides the java.lang.Object.hashCode method.
	 * <p>
	 * The following steps came from <b>Effective Java Programming Language Guide</b> by Joshua Bloch, Chapter 3
	 * 
	 * @return a hash code value for the object.
	 */
	public int hashCode()
	{
		int result = 17;

		long tmp;
		if (_overrideType != null && !org.castor.core.util.CycleBreaker.startingToCycle(_overrideType))
		{
			result = 37 * result + _overrideType.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_overrideType);
		}
		if (_overrideSelectionList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_overrideSelectionList))
		{
			result = 37 * result + _overrideSelectionList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_overrideSelectionList);
		}
		if (_overrideExclusionList != null && !org.castor.core.util.CycleBreaker.startingToCycle(_overrideExclusionList))
		{
			result = 37 * result + _overrideExclusionList.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_overrideExclusionList);
		}

		return result;
	}

	/**
	 * Method isValid.
	 * 
	 * @return true if this object is valid according to the schema
	 */
	public boolean isValid()
	{
		try
		{
			validate();
		}
		catch (org.exolab.castor.xml.ValidationException vex)
		{
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 
	 * @param out
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 */
	public void marshal(final java.io.Writer out) throws org.exolab.castor.xml.MarshalException,
					org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Marshaller.marshal(this, out);
	}

	/**
	 * 
	 * 
	 * @param handler
	 * @throws java.io.IOException
	 *             if an IOException occurs during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 */
	public void marshal(final org.xml.sax.ContentHandler handler) throws java.io.IOException,
					org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Marshaller.marshal(this, handler);
	}

	/**
     */
	public void removeAllOverrideExclusion()
	{
		this._overrideExclusionList.clear();
	}

	/**
     */
	public void removeAllOverrideSelection()
	{
		this._overrideSelectionList.clear();
	}

	/**
	 * Method removeOverrideExclusion.
	 * 
	 * @param vOverrideExclusion
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeOverrideExclusion(final bf.com.misys.eqf.types.header.EqMessage vOverrideExclusion)
	{
		boolean removed = _overrideExclusionList.remove(vOverrideExclusion);
		return removed;
	}

	/**
	 * Method removeOverrideExclusionAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public bf.com.misys.eqf.types.header.EqMessage removeOverrideExclusionAt(final int index)
	{
		java.lang.Object obj = this._overrideExclusionList.remove(index);
		return (bf.com.misys.eqf.types.header.EqMessage) obj;
	}

	/**
	 * Method removeOverrideSelection.
	 * 
	 * @param vOverrideSelection
	 * @return true if the object was removed from the collection.
	 */
	public boolean removeOverrideSelection(final bf.com.misys.eqf.types.header.EqMessage vOverrideSelection)
	{
		boolean removed = _overrideSelectionList.remove(vOverrideSelection);
		return removed;
	}

	/**
	 * Method removeOverrideSelectionAt.
	 * 
	 * @param index
	 * @return the element removed from the collection
	 */
	public bf.com.misys.eqf.types.header.EqMessage removeOverrideSelectionAt(final int index)
	{
		java.lang.Object obj = this._overrideSelectionList.remove(index);
		return (bf.com.misys.eqf.types.header.EqMessage) obj;
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vOverrideExclusion
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setOverrideExclusion(final int index, final bf.com.misys.eqf.types.header.EqMessage vOverrideExclusion)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._overrideExclusionList.size())
		{
			throw new IndexOutOfBoundsException("setOverrideExclusion: Index value '" + index + "' not in range [0.."
							+ (this._overrideExclusionList.size() - 1) + "]");
		}

		this._overrideExclusionList.set(index, vOverrideExclusion);
	}

	/**
	 * 
	 * 
	 * @param vOverrideExclusionArray
	 */
	public void setOverrideExclusion(final bf.com.misys.eqf.types.header.EqMessage[] vOverrideExclusionArray)
	{
		// -- copy array
		_overrideExclusionList.clear();

		for (int i = 0; i < vOverrideExclusionArray.length; i++)
		{
			this._overrideExclusionList.add(vOverrideExclusionArray[i]);
		}
	}

	/**
	 * 
	 * 
	 * @param index
	 * @param vOverrideSelection
	 * @throws java.lang.IndexOutOfBoundsException
	 *             if the index given is outside the bounds of the collection
	 */
	public void setOverrideSelection(final int index, final bf.com.misys.eqf.types.header.EqMessage vOverrideSelection)
					throws java.lang.IndexOutOfBoundsException
	{
		// check bounds for index
		if (index < 0 || index >= this._overrideSelectionList.size())
		{
			throw new IndexOutOfBoundsException("setOverrideSelection: Index value '" + index + "' not in range [0.."
							+ (this._overrideSelectionList.size() - 1) + "]");
		}

		this._overrideSelectionList.set(index, vOverrideSelection);
	}

	/**
	 * 
	 * 
	 * @param vOverrideSelectionArray
	 */
	public void setOverrideSelection(final bf.com.misys.eqf.types.header.EqMessage[] vOverrideSelectionArray)
	{
		// -- copy array
		_overrideSelectionList.clear();

		for (int i = 0; i < vOverrideSelectionArray.length; i++)
		{
			this._overrideSelectionList.add(vOverrideSelectionArray[i]);
		}
	}

	/**
	 * Sets the value of field 'overrideType'. The field 'overrideType' has the following description: This specifies the type of
	 * override to be applied: ' '-No override 'A'-All warning messages 'S'-Override only messages in the override selection
	 * collection 'E'-Override all messages apart from the messages in the override exclusion collection.
	 * 
	 * The override previous warnings flag is only used to force through warnings that have been approved by the client application
	 * on a previous call to the API. Normally this is not set on the first call of a Create or Update service. To override a set of
	 * previous warnings it must be set to "A".
	 * 
	 * 
	 * @param overrideType
	 *            the value of field 'overrideType'.
	 */
	public void setOverrideType(final java.lang.String overrideType)
	{
		this._overrideType = overrideType;
	}

	/**
	 * Method unmarshalOverrides.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.Overrides
	 */
	public static bf.com.misys.eqf.types.header.Overrides unmarshalOverrides(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.Overrides) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.Overrides.class, reader);
	}

	/**
	 * 
	 * 
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 */
	public void validate() throws org.exolab.castor.xml.ValidationException
	{
		org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
		validator.validate(this);
	}

}
