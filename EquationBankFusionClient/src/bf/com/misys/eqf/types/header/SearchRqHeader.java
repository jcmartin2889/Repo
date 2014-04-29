/*
 * This class was automatically generated with <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML Schema. $Id$
 */

package bf.com.misys.eqf.types.header;

/**
 * Class SearchRqHeader.
 * 
 * @version $Revision$ $Date$
 */
@SuppressWarnings("serial")
public class SearchRqHeader implements java.io.Serializable
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
	 * Field _rqHeader.
	 */
	private bf.com.misys.eqf.types.header.RqHeader _rqHeader;

	/**
	 * Field _pvId.
	 */
	private java.lang.String _pvId;

	/**
	 * This specifies the decode for the PV.
	 * 
	 */
	private java.lang.String _decode;

	/**
	 * This is used to control paging direction. ' ' - indicates first page 'F' - indicates forwards 'B' - indicates backwards
	 * 
	 */
	private java.lang.String _direction;

	/**
	 * Field _maxResults.
	 */
	private java.lang.Integer _maxResults;

	/**
	 * Colon delimited list of filter fields
	 * 
	 */
	private java.lang.String _filterFields;

	/**
	 * Colon delimited list of key field values used for paging
	 * 
	 */
	private java.lang.String _pagingFields;

	/**
	 * Field _uiControlRq.
	 */
	private bf.com.misys.eqf.types.header.UiControlRq _uiControlRq;

	// ----------------/
	// - Constructors -/
	// ----------------/

	public SearchRqHeader()
	{
		super();
	}

	// -----------/
	// - Methods -/
	// -----------/

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

		if (obj instanceof SearchRqHeader)
		{

			SearchRqHeader temp = (SearchRqHeader) obj;
			boolean thcycle;
			boolean tmcycle;
			if (this._rqHeader != null)
			{
				if (temp._rqHeader == null)
					return false;
				if (this._rqHeader != temp._rqHeader)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._rqHeader);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._rqHeader);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rqHeader);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rqHeader);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._rqHeader.equals(temp._rqHeader))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rqHeader);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rqHeader);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._rqHeader);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._rqHeader);
					}
				}
			}
			else if (temp._rqHeader != null)
				return false;
			if (this._pvId != null)
			{
				if (temp._pvId == null)
					return false;
				if (this._pvId != temp._pvId)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._pvId);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._pvId);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pvId);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pvId);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._pvId.equals(temp._pvId))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pvId);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pvId);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pvId);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pvId);
					}
				}
			}
			else if (temp._pvId != null)
				return false;
			if (this._decode != null)
			{
				if (temp._decode == null)
					return false;
				if (this._decode != temp._decode)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._decode);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._decode);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._decode);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._decode);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._decode.equals(temp._decode))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._decode);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._decode);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._decode);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._decode);
					}
				}
			}
			else if (temp._decode != null)
				return false;
			if (this._direction != null)
			{
				if (temp._direction == null)
					return false;
				if (this._direction != temp._direction)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._direction);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._direction);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._direction);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._direction);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._direction.equals(temp._direction))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._direction);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._direction);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._direction);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._direction);
					}
				}
			}
			else if (temp._direction != null)
				return false;
			if (this._maxResults != null)
			{
				if (temp._maxResults == null)
					return false;
				if (this._maxResults != temp._maxResults)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._maxResults);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._maxResults);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._maxResults);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._maxResults);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._maxResults.equals(temp._maxResults))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._maxResults);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._maxResults);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._maxResults);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._maxResults);
					}
				}
			}
			else if (temp._maxResults != null)
				return false;
			if (this._filterFields != null)
			{
				if (temp._filterFields == null)
					return false;
				if (this._filterFields != temp._filterFields)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._filterFields);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._filterFields);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._filterFields);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._filterFields);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._filterFields.equals(temp._filterFields))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._filterFields);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._filterFields);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._filterFields);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._filterFields);
					}
				}
			}
			else if (temp._filterFields != null)
				return false;
			if (this._pagingFields != null)
			{
				if (temp._pagingFields == null)
					return false;
				if (this._pagingFields != temp._pagingFields)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._pagingFields);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._pagingFields);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pagingFields);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pagingFields);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._pagingFields.equals(temp._pagingFields))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pagingFields);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pagingFields);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._pagingFields);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._pagingFields);
					}
				}
			}
			else if (temp._pagingFields != null)
				return false;
			if (this._uiControlRq != null)
			{
				if (temp._uiControlRq == null)
					return false;
				if (this._uiControlRq != temp._uiControlRq)
				{
					thcycle = org.castor.core.util.CycleBreaker.startingToCycle(this._uiControlRq);
					tmcycle = org.castor.core.util.CycleBreaker.startingToCycle(temp._uiControlRq);
					if (thcycle != tmcycle)
					{
						if (!thcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRq);
						}
						;
						if (!tmcycle)
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRq);
						}
						;
						return false;
					}
					if (!thcycle)
					{
						if (!this._uiControlRq.equals(temp._uiControlRq))
						{
							org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRq);
							org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRq);
							return false;
						}
						org.castor.core.util.CycleBreaker.releaseCycleHandle(this._uiControlRq);
						org.castor.core.util.CycleBreaker.releaseCycleHandle(temp._uiControlRq);
					}
				}
			}
			else if (temp._uiControlRq != null)
				return false;
			return true;
		}
		return false;
	}

	/**
	 * Returns the value of field 'decode'. The field 'decode' has the following description: This specifies the decode for the PV.
	 * 
	 * 
	 * @return the value of field 'Decode'.
	 */
	public java.lang.String getDecode()
	{
		return this._decode;
	}

	/**
	 * Returns the value of field 'direction'. The field 'direction' has the following description: This is used to control paging
	 * direction. ' ' - indicates first page 'F' - indicates forwards 'B' - indicates backwards
	 * 
	 * 
	 * @return the value of field 'Direction'.
	 */
	public java.lang.String getDirection()
	{
		return this._direction;
	}

	/**
	 * Returns the value of field 'filterFields'. The field 'filterFields' has the following description: Colon delimited list of
	 * filter fields
	 * 
	 * 
	 * @return the value of field 'FilterFields'.
	 */
	public java.lang.String getFilterFields()
	{
		return this._filterFields;
	}

	/**
	 * Returns the value of field 'maxResults'.
	 * 
	 * @return the value of field 'MaxResults'.
	 */
	public java.lang.Integer getMaxResults()
	{
		return this._maxResults;
	}

	/**
	 * Returns the value of field 'pagingFields'. The field 'pagingFields' has the following description: Colon delimited list of
	 * key field values used for paging
	 * 
	 * 
	 * @return the value of field 'PagingFields'.
	 */
	public java.lang.String getPagingFields()
	{
		return this._pagingFields;
	}

	/**
	 * Returns the value of field 'pvId'.
	 * 
	 * @return the value of field 'PvId'.
	 */
	public java.lang.String getPvId()
	{
		return this._pvId;
	}

	/**
	 * Returns the value of field 'rqHeader'.
	 * 
	 * @return the value of field 'RqHeader'.
	 */
	public bf.com.misys.eqf.types.header.RqHeader getRqHeader()
	{
		return this._rqHeader;
	}

	/**
	 * Returns the value of field 'uiControlRq'.
	 * 
	 * @return the value of field 'UiControlRq'.
	 */
	public bf.com.misys.eqf.types.header.UiControlRq getUiControlRq()
	{
		return this._uiControlRq;
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
		if (_rqHeader != null && !org.castor.core.util.CycleBreaker.startingToCycle(_rqHeader))
		{
			result = 37 * result + _rqHeader.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_rqHeader);
		}
		if (_pvId != null && !org.castor.core.util.CycleBreaker.startingToCycle(_pvId))
		{
			result = 37 * result + _pvId.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_pvId);
		}
		if (_decode != null && !org.castor.core.util.CycleBreaker.startingToCycle(_decode))
		{
			result = 37 * result + _decode.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_decode);
		}
		if (_direction != null && !org.castor.core.util.CycleBreaker.startingToCycle(_direction))
		{
			result = 37 * result + _direction.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_direction);
		}
		if (_maxResults != null && !org.castor.core.util.CycleBreaker.startingToCycle(_maxResults))
		{
			result = 37 * result + _maxResults.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_maxResults);
		}
		if (_filterFields != null && !org.castor.core.util.CycleBreaker.startingToCycle(_filterFields))
		{
			result = 37 * result + _filterFields.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_filterFields);
		}
		if (_pagingFields != null && !org.castor.core.util.CycleBreaker.startingToCycle(_pagingFields))
		{
			result = 37 * result + _pagingFields.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_pagingFields);
		}
		if (_uiControlRq != null && !org.castor.core.util.CycleBreaker.startingToCycle(_uiControlRq))
		{
			result = 37 * result + _uiControlRq.hashCode();
			org.castor.core.util.CycleBreaker.releaseCycleHandle(_uiControlRq);
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
	 * Sets the value of field 'decode'. The field 'decode' has the following description: This specifies the decode for the PV.
	 * 
	 * 
	 * @param decode
	 *            the value of field 'decode'.
	 */
	public void setDecode(final java.lang.String decode)
	{
		this._decode = decode;
	}

	/**
	 * Sets the value of field 'direction'. The field 'direction' has the following description: This is used to control paging
	 * direction. ' ' - indicates first page 'F' - indicates forwards 'B' - indicates backwards
	 * 
	 * 
	 * @param direction
	 *            the value of field 'direction'.
	 */
	public void setDirection(final java.lang.String direction)
	{
		this._direction = direction;
	}

	/**
	 * Sets the value of field 'filterFields'. The field 'filterFields' has the following description: Colon delimited list of
	 * filter fields
	 * 
	 * 
	 * @param filterFields
	 *            the value of field 'filterFields'.
	 */
	public void setFilterFields(final java.lang.String filterFields)
	{
		this._filterFields = filterFields;
	}

	/**
	 * Sets the value of field 'maxResults'.
	 * 
	 * @param maxResults
	 *            the value of field 'maxResults'.
	 */
	public void setMaxResults(final java.lang.Integer maxResults)
	{
		this._maxResults = maxResults;
	}

	/**
	 * Sets the value of field 'pagingFields'. The field 'pagingFields' has the following description: Colon delimited list of key
	 * field values used for paging
	 * 
	 * 
	 * @param pagingFields
	 *            the value of field 'pagingFields'.
	 */
	public void setPagingFields(final java.lang.String pagingFields)
	{
		this._pagingFields = pagingFields;
	}

	/**
	 * Sets the value of field 'pvId'.
	 * 
	 * @param pvId
	 *            the value of field 'pvId'.
	 */
	public void setPvId(final java.lang.String pvId)
	{
		this._pvId = pvId;
	}

	/**
	 * Sets the value of field 'rqHeader'.
	 * 
	 * @param rqHeader
	 *            the value of field 'rqHeader'.
	 */
	public void setRqHeader(final bf.com.misys.eqf.types.header.RqHeader rqHeader)
	{
		this._rqHeader = rqHeader;
	}

	/**
	 * Sets the value of field 'uiControlRq'.
	 * 
	 * @param uiControlRq
	 *            the value of field 'uiControlRq'.
	 */
	public void setUiControlRq(final bf.com.misys.eqf.types.header.UiControlRq uiControlRq)
	{
		this._uiControlRq = uiControlRq;
	}

	/**
	 * Method unmarshalSearchRqHeader.
	 * 
	 * @param reader
	 * @throws org.exolab.castor.xml.MarshalException
	 *             if object is null or if any SAXException is thrown during marshaling
	 * @throws org.exolab.castor.xml.ValidationException
	 *             if this object is an invalid instance according to the schema
	 * @return the unmarshaled bf.com.misys.eqf.types.header.SearchRqHeader
	 */
	public static bf.com.misys.eqf.types.header.SearchRqHeader unmarshalSearchRqHeader(final java.io.Reader reader)
					throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
	{
		return (bf.com.misys.eqf.types.header.SearchRqHeader) org.exolab.castor.xml.Unmarshaller.unmarshal(
						bf.com.misys.eqf.types.header.SearchRqHeader.class, reader);
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
