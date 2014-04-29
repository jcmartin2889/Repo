package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.List;

import com.misys.equation.common.access.EquationStandardMultipleValidation;
import com.misys.equation.common.access.EquationStandardValidation;
import com.misys.equation.common.utilities.EquationPVMetaDataHelper;

public class FunctionValidateMultiplePVHelper
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionValidateMultiplePVHelper.java 9442 2010-10-12 09:42:28Z MACDONP1 $";
	ArrayList<Integer> rowIndexes;
	ArrayList<String> rowIdStrings;
	ArrayList<Boolean> inputMappingExists;
	EquationStandardMultipleValidation equationStandardMultipleValidation;

	private static final long totalSize = 9000;
	long length;

	/**
	 * Construct a multiple PV helper
	 */
	public FunctionValidateMultiplePVHelper()
	{
		this.equationStandardMultipleValidation = new EquationStandardMultipleValidation();
		this.inputMappingExists = new ArrayList<Boolean>();
		this.rowIndexes = new ArrayList<Integer>();
		this.rowIdStrings = new ArrayList<String>();
		this.length = 0;
	}

	/**
	 * Clear the list
	 */
	public void clear()
	{
		rowIndexes.clear();
		rowIdStrings.clear();
		inputMappingExists.clear();
		equationStandardMultipleValidation.clear();
		length = 0;
	}

	/**
	 * Add to the list
	 * 
	 * @param row
	 *            - the row within the repeating data
	 * @param inputField
	 *            - the input field
	 * @param fieldData
	 *            - the field data
	 * @param inputMappingExist
	 *            - input mapping exists flag
	 * @param equationStandardValidation
	 *            - the Equation Standard Validation
	 */
	public void add(int row, String rowIdString, boolean inputMappingExist, EquationStandardValidation equationStandardValidation)
	{
		rowIndexes.add(row);
		rowIdStrings.add(rowIdString);
		inputMappingExists.add(inputMappingExist);
		equationStandardMultipleValidation.add(equationStandardValidation);
		length += equationStandardValidation.getEquationPVData().getPvMetaData()
						.rtvDSCCNLength(equationStandardValidation.getDataInput().length())
						+ EquationPVMetaDataHelper.LEN_DSEPMS; // include length of DSEPMS
	}

	/**
	 * Return the list of PV to be validated
	 * 
	 * @return the list of PV to be validated
	 */
	public EquationStandardMultipleValidation getEquationStandardMultipleValidation()
	{
		return equationStandardMultipleValidation;
	}

	/**
	 * Return the list of input mapping exist flags
	 * 
	 * @return the list of input mapping exist flags
	 */
	public List<Boolean> getInputMappingExists()
	{
		return inputMappingExists;
	}

	/**
	 * Return the list of row indexes
	 * 
	 * @return the list of row indexes
	 */
	public List<Integer> getRowIndexes()
	{
		return rowIndexes;
	}

	/**
	 * Return the list of row id string indexes
	 * 
	 * @return the list of row id string indexes
	 */
	public List<String> getRowIdStrings()
	{
		return rowIdStrings;
	}

	/**
	 * Return the current length of the buffer
	 * 
	 * @return the current length of the buffer
	 */
	public boolean isExceedBuffer(int length)
	{
		return (this.length + length + EquationPVMetaDataHelper.LEN_DSEPMS) > totalSize; // include length of DSEPMS
	}

}
