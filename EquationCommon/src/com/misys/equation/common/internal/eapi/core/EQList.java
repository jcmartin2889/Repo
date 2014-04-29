// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQList: Definition of an Equation list
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.misys.equation.common.utilities.EQCommonConstants;
import com.misys.equation.common.utilities.EquationLogger;

/***********************************************************************************************************************************
 * Represents repeating rows of data in Equation.
 * <P>
 * Equation lists are like fixed format recordsets. They have a fixed set of columns and have repeating rows of values for those
 * columns. The fields defining the columns can consist of both input and output only fields.
 * <P>
 * The EQList class is used by the EQObject class when the function / prompt implements a subfile.
 * <P>
 * The list data (rows) are stored in an ArrayList. Each row in the ArrayList is a HashMap of EQField objects keyed by the field
 * names supplied in the list definition arrays. These EQField objects also have the field attributes specified in the list
 * definition.
 * <P>
 * A list is ordered into a number of pages, with each page containing a specific number of rows. The default page size is
 * configured through the API_DEFAULT_LIST_SIZE value in the Application Environment but this can be overriden through the
 * setPageSize method. Note that this value must be set before the first retrieval, it cannot be modifed once any data has been
 * retrieved.
 * <P>
 * The list holds a page marker (like a book mark or a cursor). The page numbers start at zero and the current page number is
 * returned by getCurrentPage. The page marker is used to specify which pages of data should be retrieved in the next call to
 * Equation.
 * <P>
 * It also allows an application to scroll through the list without having to manage the list itself. The application can move the
 * current page through the list using the nextPage and previousPage methods and determine whether the page is at the begining or
 * end of the list with the isFirstPage and isLastPage methods.
 * <P>
 * The application can use the getPageStartIndex and getPageEndIndex methods to determine the row numbers for the start and end of
 * the current page. It can then access the required rows in the list to retrieve the row data for that page.
 * <P>
 * Note that EQList is a container and list management object. It does not actually retrieve or write list data to the database.
 * <P>
 * Depending on the implementation of the individual list in Equation, all of the possible list data (rows) may or may not be
 * returned in a single call. This is indicated by isComplete which returns false until all data has been retrieved. Until then
 * nextPage should be invoked and whilst this returns false further retrievals should be made.
 * <P>
 * See the samples for further usage details.
 * <P>
 * 
 * @see com.misys.equation.ebs.samples.EQPromptSample
 * @author Misys International Banking Systems Ltd.
 */
public class EQList implements java.io.Serializable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQList.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*******************************************************************************************************************************
	 * Copyright <a href="http://www.misys.com"> Misys International Banking Systems Ltd, 2006 </a>
	 */
	public static final String copyright = "Copyright Misys International Banking Systems Ltd, 2006";
	/*******************************************************************************************************************************
	 * The interface version.
	 * <P>
	 * The minimum version of Equation that this interface is compatible with.
	 */
	public static final String equationVersion = "CX011";
	private static final EquationLogger LOG = new EquationLogger(EQList.class);
	/*
	 * Controls
	 */
	protected char BEGIN; // 0-1
	private int NUMREQ; // 1-6
	protected int[] NUMRET;
	protected static final int EQLIST_CONTROLS_SIZE = 56;
	protected static final int nListParameters = 10;
	protected static final String EQLIST_BLANK_CONTROLS = EQCommonConstants.initBlankStringBuffer(EQLIST_CONTROLS_SIZE).toString();
	protected static final int EQLIST_LIST_PARAM_SIZE = 9999;
	protected static final String EQLIST_BLANK_LIST = EQCommonConstants.initBlankStringBuffer(EQLIST_LIST_PARAM_SIZE).toString();

	/*
	 * Others
	 */
	int nRows;
	int page; // the page currently requested
	int numPagesCached; // how many we have retrieved
	private int lastPageNum; // the last available page
	protected boolean modified;
	protected boolean valid;
	private boolean readOnly;
	protected ArrayList rows;
	private static int nDefaultListSize;
	private static String strDefaultControls;
	private static String strFirstRetrievalControls;
	private EQObjectMetaData metaData = null;
	static
	{
		try
		{
			EQEnvironment app = EQEnvironment.getAppEnvironment();
			String strSize = app.getProperty("API_DEFAULT_LIST_SIZE");
			nDefaultListSize = Integer.parseInt(strSize);
			StringBuffer s = new StringBuffer(EQLIST_BLANK_CONTROLS);
			// first retrieval string has initial character of 'Y' to indicate
			// a retrieval is required
			s.setCharAt(0, 'Y');
			s.replace(1, 6, "00000");
			// overwrite with actual value required
			String strNumReq = Integer.toString(nDefaultListSize);
			s.replace(6 - strNumReq.length(), 6, strNumReq);
			s.replace(6, 56, "00000000000000000000000000000000000000000000000000");
			strFirstRetrievalControls = s.toString();
			// default control string has initial character ' ' to indicate
			// no retrieval is required
			s.setCharAt(0, ' ');
			strDefaultControls = s.toString();
		}
		catch (EQException e)
		{
			// set up standard defaults
			nDefaultListSize = 16;
			strFirstRetrievalControls = "Y0001600000000000000000000000000000000000000000000000000";
			strDefaultControls = " 0001600000000000000000000000000000000000000000000000000";
		}
	}
	/**
	 * An internal class for holding the control state for an indiviudal row's data. An instance of this class is stored in each
	 * rows HashMap.
	 */
	private class EQRowControl
	{
		char[] controls;
		public EQRowControl(char[] image, int startIndex, int length)
		{
			controls = new char[length];
			System.arraycopy(image, startIndex, controls, 0, length);
		}
		/**
		 * @return Returns the controls.
		 */
		public char[] getControls()
		{
			return controls;
		}
		public int getRowNum()
		{
			String strRowNum = new String(controls, 0, 5);
			return Integer.parseInt(strRowNum);
		}
		public char getType()
		{
			return controls[5];
		}
		public void setType(char newType)
		{
			controls[5] = newType;
		}
		public boolean isModified()
		{
			return controls[6] == 'Y' ? true : false;
		}
		public void setModified()
		{
			controls[6] = 'Y';
		}
		public char getVisibility(int index)
		{
			return controls[7 + index];
		}
		public boolean isProtected(int index)
		{
			return controls[7 + index] == 'P' ? true : false;
		}
	}
	/*******************************************************************************************************************************
	 * Default Constructor
	 */
	@SuppressWarnings("unused")
	private EQList()
	{
	}
	/*******************************************************************************************************************************
	 * Constructor given an EQObjectMetaData of field definitions.
	 */
	protected EQList(EQObjectMetaData metaData)
	{
		privateReset();
		NUMREQ = nDefaultListSize;
		this.metaData = metaData;
	}
	/*******************************************************************************************************************************
	 * Resets all of the list's data attributes to its uninitialised state.
	 * <P>
	 * Note that this does not reset the list's column definition or the page size. Only the data content of the list.
	 * <P>
	 * To reset the definition use the setListDefinition method.
	 */
	protected void reset()
	{
		privateReset();
	}
	/*******************************************************************************************************************************
	 * We have this method as an EQList is not abstract. Thus if an EQList is created we need to initialise it during construction
	 * through this method rather than calling any virutal method when we are subclassed - as this would cause the subclasses reset
	 * method to get called twice before construction was complete.
	 */
	private void privateReset()
	{
		BEGIN = 'Y';
		NUMRET = new int[nListParameters];
		for (int nLCount = 0; nLCount < nListParameters; nLCount++)
		{
			NUMRET[nLCount] = 0;
		}
		// action = "Begin";
		page = 0;
		rowsReset();
	}
	/*******************************************************************************************************************************
	 * Reset the number of rows returned.
	 */
	protected void resetNumberOfRowsReturned()
	{
		for (int nLCount = 0; nLCount < nListParameters; nLCount++)
		{
			NUMRET[nLCount] = 0;
		}
	}
	/*******************************************************************************************************************************
	 * Removes all of the list's cached row data and reset the page marker to zero.
	 */
	protected void rowsReset()
	{
		valid = false;
		modified = false;
		readOnly = false;
		nRows = 0;
		page = 0;
		numPagesCached = 0;
		lastPageNum = -1; // unknown
		rows = new ArrayList();
	}
	/*******************************************************************************************************************************
	 * Determine if all of the list data has been retrieved.
	 * <P>
	 * 
	 * @return true if all list date has been retrieved and cached.
	 */
	public boolean isComplete()
	{
		return lastPageNum >= 0 || BEGIN == 'L';
	}
	/*******************************************************************************************************************************
	 * Determine if all of the rows for the current page have been retrieved.
	 * <P>
	 * 
	 * @return true if all the rows are cached for the current page, false if further retrieval is required.
	 */
	public boolean isPageComplete()
	{
		return isComplete() ? true : page < numPagesCached ? true : false;
	}
	/**
	 * Determine if the list has been succesfully validated.
	 * <P>
	 * Until a succesful validation call to Equation the list is marked invalid.
	 * <P>
	 * If the application modifies any field's value after the list has been validated it is marked as invalid again. Note that this
	 * only applies if the application modifies field values through the list's setFieldValue method. Validity is not tracked if
	 * field objects are modified directly.
	 * <P>
	 * 
	 * @return true if the data has been validated by Equation.
	 */
	public boolean isValid()
	{
		return valid;
	}
	/**
	 * Determine if the list has been modified.
	 * <P>
	 * Note that this is only true if the application modifies field values through the function or list's setFieldValue method.
	 * Validity is not tracked if field objects are modified directly.
	 * 
	 * @return whether the function contains data that has not been saved. This is true when the object has had fields set but has
	 *         not yet been written to the database.
	 */
	public boolean isModified()
	{
		return modified;
	}
	/*******************************************************************************************************************************
	 * Specifies that the attributes of the list and its data can no longer be modified.
	 * <P>
	 * This is done automatically when a succesful database update has occured.
	 * <P>
	 * However, reset can be used to reinitialise the object.
	 */
	public void setReadOnly()
	{
		readOnly = true;
		int numRows = getRows().size();
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		for (int rCount = 0; rCount < numRows; rCount++)
		{
			HashMap hmFields = (HashMap) rows.get(rCount);
			for (int nCount = 0; nCount < metaData.nListInputFields; nCount++)
			{
				eqFieldDefinition = metaData.arrListInputFieldDefinitions.get(nCount);
				eqField = (EQField) hmFields.get(eqFieldDefinition.fieldName);
				eqField.setProtected();
			}
		}
	}
	/**
	 * Determine if the list has been set in read-only mode.
	 * <P>
	 * 
	 * @return true if the list has been marked read-only.
	 */
	public boolean isReadOnly()
	{
		return readOnly;
	}
	/*******************************************************************************************************************************
	 * Moves the page marker to the next page.
	 * <P>
	 * This method moves the bookmark forward one page. The data for that page may or may not have been retrieved. The method does
	 * not cause a request for the data if it is required, rather it returns false indicating that a request is required. It is the
	 * application's responsibility to perform the retrieval, for example through a prompt call.
	 * <P>
	 * 
	 * @return false if the requested page needs to be retrieved or true if it is already cached.
	 */
	public boolean nextPage()
	{
		if (page == lastPageNum)
		{
			// already on last page so can't request any more
			return true;
		}
		else if (page + 1 < numPagesCached)
		{
			// page is in cache so don't need to make a request
			page++;
			return true;
		}
		else
		{
			page++;
			return false;
		}
	}
	/*******************************************************************************************************************************
	 * Moves the page marker to the previous page.
	 * <P>
	 * This method moves the bookmark backwards one page. The data for that page may or may not have been retrieved. The method does
	 * not cause a request for the data if it is required, rather it returns false indicating that a request is required. It is the
	 * application's responsibility to perform the retrieval, for example through a prompt call.
	 * <P>
	 * 
	 * @return false if the requested page needs to be retrieved or true if it is already cached.
	 */
	public boolean previousPage()
	{
		page--;
		if (page < 0)
		{
			page = 0;
		}
		return page < numPagesCached ? true : false;
	}
	/*******************************************************************************************************************************
	 * Determine if the page marker is on the first page.
	 * <P>
	 * 
	 * @return true if the current page is the first page.
	 */
	public boolean isFirstPage()
	{
		return BEGIN == 'Y' || page == 0;
	}
	/*******************************************************************************************************************************
	 * Determine if the page marker is on the last page.
	 * <P>
	 * In prompt based lists the last page is not known until it is retrieved.
	 * <P>
	 * 
	 * @return true if the current page is the last page - i.e no more pages available
	 */
	public boolean isLastPage()
	{
		return page == lastPageNum ? true : rows.size() == 0 ? true : false;
	}
	/*******************************************************************************************************************************
	 * Set the size of a page.
	 * <P>
	 * This method can only be used to set the data before row data is retrieved.
	 * <P>
	 * 
	 * @param pageSize
	 *            the number of rows for each page.
	 * @throws EQException
	 *             if called after data has already been retrieved
	 */
	public void setPageSize(int pageSize) throws EQException
	{
		if (rows.size() > 0)
		{
			throw new EQException("EQlist: Cannot change page size after row data has been retrieved.");
		}
		NUMREQ = pageSize;
		if (LOG.isDebugEnabled())
		{
			LOG.debug("NUMREQ: " + NUMREQ);
		}
	}
	/*******************************************************************************************************************************
	 * Get the size of a page.
	 * <P>
	 * 
	 * @return the number of rows per page.
	 */
	public int getPageSize()
	{
		return NUMREQ;
	}
	/*******************************************************************************************************************************
	 * Get the current page number.
	 * <P>
	 * Page numbers start at zero.
	 * <P>
	 * 
	 * @return the number of the page the page market is currently on.
	 */
	public int getCurrentPage()
	{
		return page;
	}
	/*******************************************************************************************************************************
	 * Set the value of the last page in the list.
	 * <P>
	 * This value is normally set by the framework when all data has been retrieved. This method is provided to allow the
	 * application to mark the list as complete so that no further retrieval occurs, and for depersisting EQList objects.
	 * <P>
	 * 
	 * @param pageNum
	 *            the page number to be marked as the last page
	 * 
	 */
	public void setLastPage(int pageNum)
	{
		lastPageNum = pageNum;
		numPagesCached = pageNum + 1;
		BEGIN = 'L';
	}
	/*******************************************************************************************************************************
	 * Get the number of the last page in the list.
	 * <P>
	 * This is the total size of the list in pages. For prompts this is not known until the last page has been retrieved. Until
	 * then, this method will return -1 indicating it is unknown.
	 * <P>
	 * 
	 * @return the page number of the last page, -1 if not yet known.
	 */
	public int getLastPage()
	{
		return lastPageNum;
	}
	/*******************************************************************************************************************************
	 * Get the row index for the first row in the current page.
	 * <P>
	 * This value, together with the end index, can be used to extract the row data for the current page.
	 * <P>
	 * 
	 * @return the index for the first row on the current page - zero based
	 */
	public int getPageStartIndex()
	{
		int startIndex = (page * NUMREQ);
		int numRows = getRows().size();
		if (startIndex > numRows)
		{
			startIndex = numRows;
		}
		return startIndex;
	}
	/*******************************************************************************************************************************
	 * Get the row index for the last row in the current page.
	 * <P>
	 * This value, together with the start index, can be used to extract the row data for the current page.
	 * <P>
	 * 
	 * @return the index for the last row on the current page
	 */
	public int getPageEndIndex()
	{
		int endIndex = getPageStartIndex() + NUMREQ;
		int numRows = getRows().size();
		if (endIndex > numRows)
		{
			endIndex = numRows;
		}
		return endIndex;
	}
	/*******************************************************************************************************************************
	 * Get the number of rows in the current list.
	 * <P>
	 * This returns the number of rows in the currently retrieved list. It does not return the maximum number of rows available, if
	 * there is more list data to retrieve.
	 * <P>
	 * 
	 * @return the number of retireved rows.
	 */
	public int getNumRows()
	{
		return rows.size();
	}
	/**
	 * Get the list data rows.
	 * <P>
	 * The list data (rows) are stored in an ArrayList. Each row in the ArrayList is a HashMap of EQInputField and EQOutputField
	 * objects keyed by the input and output field names supplied in the list definition arrays.
	 * <P>
	 * 
	 * @return the ArrayList of rows of data in the list.
	 */
	public ArrayList getRows()
	{
		return rows;
	}
	/*******************************************************************************************************************************
	 * Get a specific row of data.
	 * <P>
	 * The HashMap returned contains all EQField objects in the row keyed by the field names supplied in the list definition arrays.
	 * It also contains the control data for the row.
	 * <P>
	 * 
	 * @param rowNum
	 *            the index of the row required.
	 * @return the row of data, contained in a HashMap.
	 */
	public HashMap getRow(int rowNum)
	{
		return (HashMap) rows.get(rowNum);
	}
	/*******************************************************************************************************************************
	 * Get a field in the list.
	 * <P>
	 * 
	 * @param rowNum
	 *            the row index on which the field is required.
	 * @param fieldName
	 *            the name of the field (column) required.
	 * @return an EQInputField or EQOutputField
	 */
	public EQField getField(int rowNum, String fieldName)
	{
		return ((EQField) ((HashMap) rows.get(rowNum)).get(fieldName));
	}
	/*******************************************************************************************************************************
	 * Get the value of a field in the list.
	 * <P>
	 * Note this method is equivalent to <code>getField(int, String).getValue()</code>.
	 * <P>
	 * 
	 * @param rowNum
	 *            the row index on which the field value is required.
	 * @param fieldName
	 *            the name of the field (column) required.
	 * @return the value of the field.
	 */
	public String getFieldValue(int rowNum, String fieldName)
	{
		return ((EQField) ((HashMap) rows.get(rowNum)).get(fieldName)).getValue();
	}
	/*******************************************************************************************************************************
	 * Set the value of a field in the list.
	 * <P>
	 * 
	 * @param rowNum
	 *            the row index for the field.
	 * @param fieldName
	 *            the name of the field to set.
	 * @param value
	 *            the new value for the field.
	 * @return true if the field is modified.
	 */
	public boolean setFieldValue(int rowNum, String fieldName, String value)
	{
		if (isReadOnly())
		{
			throw new IllegalStateException("Can't set field values in readOnly mode");
		}
		EQField eqField = (EQField) ((HashMap) rows.get(rowNum)).get(fieldName);
		if (eqField.setValue(value))
		{
			valid = false;
			modified = true;
			return true;
		}
		return false;
	}
	/*******************************************************************************************************************************
	 * Get all fields in a specified row.
	 * <P>
	 * The HashMap returned contains all EQField objects in the row keyed by the field names supplied in the list definition arrays.
	 * It only contains the fields i.e. the row control data is not included.
	 * <P>
	 * 
	 * @param rowNum
	 *            the row index for the fields.
	 * @return a HashMap of all fields in the selected row
	 */
	@SuppressWarnings("unchecked")
	public HashMap getRowFields(int rowNum)
	{
		// Create the HashMap that will be returned
		HashMap fieldsOnlyMap = new HashMap();
		// Get the required row
		HashMap rowMap = (HashMap) rows.get(rowNum);
		// Check each entry in the row and if it is an EQField then add it to
		// the HashMap that will be returned.
		Iterator iter = rowMap.keySet().iterator();
		while (iter.hasNext())
		{
			Object key = iter.next();
			Object value = rowMap.get(key);
			if (value.getClass().getName().equals("com.misys.equation.common.core.EQField"))
			{
				fieldsOnlyMap.put(key, value);
			}
		}
		return fieldsOnlyMap;
	}
	/*******************************************************************************************************************************
	 * Set the list's values from an image of rows.
	 * <P>
	 * This method is not for public use. The EQSerializer utility uses this method.
	 * <P>
	 * The image passed should be a contiguous block of field values for the input and output fields on each row, in the order
	 * specified in the list definition. The total length of the image should be exactly equal to the row image size multiplied by
	 * the number of rows supplied.
	 * <P>
	 * 
	 * @param image
	 *            the image of row data.
	 * @param numRows
	 *            the number of rows in the image.
	 */
	public void setListFromImage(String image, int numRows)
	{
		String imageArray[] = new String[1];
		imageArray[0] = image;
		NUMRET[0] = numRows;
		for (int nLCount = 1; nLCount < nListParameters; nLCount++)
		{
			NUMRET[nLCount] = 0;
		}
		setList(imageArray);
	}
	/*******************************************************************************************************************************
	 * Get the EQList's controls, i.e control parameters for a call.
	 */
	protected String getControls()
	{
		StringBuffer s = new StringBuffer(EQLIST_BLANK_CONTROLS);
		s.setCharAt(0, BEGIN);
		s.replace(1, 6, "00000");
		// overwrite with actual value required
		String strNumReq = Integer.toString(NUMREQ);
		s.replace(6 - strNumReq.length(), 6, strNumReq);
		// number of rows being returned in each parameter
		for (int i = 0; i < nListParameters; i++)
		{
			s.replace((i * 5) + 6, (i * 5) + 11, "00000");
			// overwrite with actual value required
			String strNumRet = Integer.toString(NUMRET[i]);
			s.replace(((i * 5) + 11) - strNumRet.length(), (i * 5) + 11, strNumRet);
		}
		return s.toString();
	}
	/*******************************************************************************************************************************
	 * Get the default controls for a call.
	 */
	protected static String getDefaultControls()
	{
		return strDefaultControls;
	}
	/*******************************************************************************************************************************
	 * Get the default EQList controls for a call.
	 */
	protected static String getControlsForFirstRetrieval()
	{
		return strFirstRetrievalControls;
	}
	/*******************************************************************************************************************************
	 * Set the EQList's controls, i.e control parameters returned from call.
	 */
	protected void setControls(String s)
	{
		int nControlsLength = s.length();
		// We ignore chars 1 to 6, they are input only
		for (int nLCount = 0; nLCount < nListParameters; nLCount++)
		{
			if (nControlsLength >= (nLCount * 5) + 6)
			{
				String strNumRet = s.substring((nLCount * 5) + 6, (nLCount * 5) + 11);
				if (strNumRet.length() == 0)
				{
					NUMRET[nLCount] = 0;
				}
				else
				{
					NUMRET[nLCount] = Integer.parseInt(strNumRet);
				}
			}
			else
			{
				NUMRET[nLCount] = 0;
			}
		}
		if (nControlsLength >= 1)
		{
			BEGIN = s.charAt(0);
		}
		else
		{
			BEGIN = ' ';
		}
	}
	/*******************************************************************************************************************************
	 * @return an image string containing the entire list.
	 */
	protected String[] getListImage()
	{
		// We currently do not have any gaps in the image
		String sImages[] = new String[nListParameters];
		int numRows = rows.size();
		int realRowSize = metaData.nRowImageSize + metaData.nRowControlSize;
		if (realRowSize * numRows > (EQLIST_LIST_PARAM_SIZE * nListParameters))
		{
			LOG.error("EQList:getList - max size exceeeded - truncating");
		}
		int numRowsPerParameter = EQLIST_LIST_PARAM_SIZE / realRowSize;
		int numNeededListParameters = numRows / numRowsPerParameter;
		numNeededListParameters += numRows % numRowsPerParameter > 0 ? 1 : 0;
		int rCount = 0;
		// calculate max required image space
		int imageSize = numRowsPerParameter * realRowSize;
		char image[] = new char[imageSize];
		EQFieldDefinition eqFieldDefinition = null;
		for (int i = 0; i < numNeededListParameters; i++)
		{
			int nImagePointer = 0;
			int rowStart = 0;
			String strFieldValue;
			// initialse with blanks
			EQLIST_BLANK_LIST.getChars(0, imageSize, image, 0);
			int pCount = 0;
			while (rCount < numRows && pCount < numRowsPerParameter)
			{
				System.arraycopy(((EQRowControl) ((HashMap) rows.get(rCount)).get(ROWCONTROL)).getControls(), 0, image, rowStart,
								metaData.nRowControlSize);
				// nImagePointer, metaData.nRowControlSize);
				// nImagePointer += metaData.nRowControlSize;
				for (int nCount = 0; nCount < metaData.nListInputFields; nCount++)
				{
					eqFieldDefinition = metaData.arrListInputFieldDefinitions.get(nCount);
					strFieldValue = ((EQField) ((HashMap) rows.get(rCount)).get(eqFieldDefinition.fieldName)).getValue();
					nImagePointer = rowStart + metaData.nRowControlSize + eqFieldDefinition.dataSetImageOffset - 1;
					strFieldValue.getChars(0, strFieldValue.length(), image, nImagePointer);
					// nImagePointer += eqFieldDefinition.maxSize;
					if (LOG.isDebugEnabled())
					{
						LOG.debug("nImagePointer " + nImagePointer + ' ' + eqFieldDefinition.fieldName + ' '
										+ eqFieldDefinition.maxSize + ' ' + strFieldValue);
					}
				}
				for (int nCount = 0; nCount < metaData.nListOutputFields; nCount++)
				{
					eqFieldDefinition = metaData.arrListOutputFieldDefinitions.get(nCount);
					strFieldValue = ((EQField) ((HashMap) rows.get(rCount)).get(eqFieldDefinition.fieldName)).getValue();
					nImagePointer = rowStart + metaData.nRowControlSize + eqFieldDefinition.dataSetImageOffset - 1;
					strFieldValue.getChars(0, strFieldValue.length(), image, nImagePointer);
					// nImagePointer += eqFieldDefinition.maxSize;
					if (LOG.isDebugEnabled())
					{
						LOG.debug("nImagePointer " + nImagePointer + ' ' + eqFieldDefinition.fieldName + ' '
										+ eqFieldDefinition.maxSize + ' ' + strFieldValue);
					}
				}
				rowStart = rowStart + metaData.nRowControlSize + metaData.nRowImageSize;
				rCount++;
				pCount++;
			}
			sImages[i] = EQCommonConstants.rightTrimmedString(image, 0, imageSize);
			NUMRET[i] = pCount;
		}
		for (int i = numNeededListParameters; i < nListParameters; i++)
		{
			sImages[i] = "";
			NUMRET[i] = 0;
		}
		return sImages;
	}
	/*******************************************************************************************************************************
	 * @return a string containing a row's values.
	 */
	protected String getRowImage(int rowNum)
	{
		// We currently do not have any gaps in the image
		char image[] = new char[metaData.nRowImageSize + metaData.nRowControlSize];
		int nImagePointer = 0;
		String strFieldValue;
		// initialse with blanks
		EQLIST_BLANK_LIST.getChars(0, metaData.nRowImageSize + metaData.nRowControlSize, image, 0);
		System.arraycopy(image, 0, ((EQRowControl) ((HashMap) rows.get(rowNum)).get(ROWCONTROL)).getControls(), 0,
						metaData.nRowControlSize);
		// nImagePointer += metaData.nRowControlSize;
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		for (int nCount = 0; nCount < metaData.nListInputFields; nCount++)
		{
			eqFieldDefinition = metaData.arrListInputFieldDefinitions.get(nCount);
			eqField = (EQField) ((HashMap) rows.get(rowNum)).get(eqFieldDefinition.fieldName);
			if (eqField != null)
			{
				strFieldValue = eqField.getValue();
				nImagePointer = metaData.nRowControlSize + eqFieldDefinition.dataSetImageOffset - 1;
				strFieldValue.getChars(0, strFieldValue.length(), image, nImagePointer);
			}
			// nImagePointer += eqFieldDefinition.maxSize;
		}
		for (int nCount = 0; nCount < metaData.nListOutputFields; nCount++)
		{
			eqFieldDefinition = metaData.arrListOutputFieldDefinitions.get(nCount);
			eqField = (EQField) ((HashMap) rows.get(rowNum)).get(eqFieldDefinition.fieldName);
			if (eqField != null)
			{
				strFieldValue = eqField.getValue();
				nImagePointer = metaData.nRowControlSize + eqFieldDefinition.dataSetImageOffset - 1;
				strFieldValue.getChars(0, strFieldValue.length(), image, nImagePointer);
			}
			// nImagePointer += eqFieldDefinition.maxSize;
		}
		return EQCommonConstants.rightTrimmedString(image, 0, metaData.nRowImageSize + metaData.nRowControlSize);
	}
	/*******************************************************************************************************************************
	 * Name of the HashMap entry for the EQRowControl object.
	 */
	private static final String ROWCONTROL = "RC";
	/*******************************************************************************************************************************
	 * Set the EQList's values returned from call.
	 */
	@SuppressWarnings("unchecked")
	protected void setList(String imageArray[])
	{
		// We currently do not have any gaps in the image
		int totalNumRet = 0;
		EQFieldDefinition eqFieldDefinition = null;
		EQField eqField = null;
		for (int i = 0; i < imageArray.length; i++)
		{
			if (NUMRET[i] == 0)
			{
				break;
			}
			totalNumRet += NUMRET[i];
			char[] charImage = imageArray[i].toCharArray();
			int nImagePointerStart = 0, nImagePointerEnd = 0, nImageLength = imageArray[i].length();
			for (int rCount = 0; rCount < NUMRET[i]; rCount++)
			{
				if (LOG.isDebugEnabled())
				{
					int nRowEnd = nImagePointerStart + metaData.nRowControlSize + metaData.nRowImageSize;
					if (nRowEnd > imageArray[i].length())
					{
						nRowEnd = imageArray[i].length();
					}
					LOG.debug(imageArray[i].substring(nImagePointerStart, nRowEnd));
				}
				HashMap hmFields = new HashMap(metaData.nListInputFields + metaData.nListOutputFields + 2, 1.0f);
				EQRowControl eqRowControl = new EQRowControl(charImage, nImagePointerStart, metaData.nRowControlSize);
				nImagePointerStart += metaData.nRowControlSize;
				hmFields.put(ROWCONTROL, eqRowControl);
				for (int nCount = 0; nCount < metaData.nListInputFields; nCount++)
				{
					eqFieldDefinition = metaData.arrListInputFieldDefinitions.get(nCount);
					nImagePointerEnd = nImagePointerStart + eqFieldDefinition.maxSize;
					if (nImagePointerEnd > nImageLength)
					{
						nImagePointerEnd = nImageLength;
					}
					eqField = new EQField(eqFieldDefinition);
					if (nImagePointerStart < nImageLength)
					{
						eqField.internal_SetValue(EQCommonConstants.rightTrimmedString(charImage, nImagePointerStart,
										nImagePointerEnd));
						eqField.setVisibility(eqRowControl.getVisibility(nCount));
					}
					hmFields.put(eqFieldDefinition.fieldName, eqField);
					nImagePointerStart = nImagePointerEnd;
				}
				for (int nCount = 0; nCount < metaData.nListOutputFields; nCount++)
				{
					eqFieldDefinition = metaData.arrListOutputFieldDefinitions.get(nCount);
					nImagePointerEnd = nImagePointerStart + eqFieldDefinition.maxSize;
					if (nImagePointerEnd > nImageLength)
					{
						nImagePointerEnd = nImageLength;
					}
					eqField = new EQField(eqFieldDefinition);
					if (nImagePointerStart < nImageLength)
					{
						eqField.internal_SetValue(EQCommonConstants.rightTrimmedString(charImage, nImagePointerStart,
										nImagePointerEnd));
					}
					hmFields.put(eqFieldDefinition.fieldName, eqField);
					nImagePointerStart = nImagePointerEnd;
				}
				rows.add(hmFields);
			}
		} // end loop over array
			// calculate how many pages are cached
		numPagesCached = rows.size() / NUMREQ; // truncates
		if (BEGIN == 'L')
		{
			// all pages returned - see if we go over a page break
			if (numPagesCached * NUMREQ < rows.size())
			{
				numPagesCached++;
			}
			lastPageNum = numPagesCached - 1;
			if (lastPageNum < 0)
			{
				lastPageNum = 0;
			}
		}
		modified = false;
		valid = true;
		if (LOG.isInfoEnabled())
		{
			LOG.info("Current page: " + page);
			LOG.info("Last page: " + lastPageNum);
			LOG.info("BEGIN: " + BEGIN);
			LOG.info("NUMRET: " + totalNumRet);
			LOG.info("Number of pages cached: " + numPagesCached);
		}
	}
}
