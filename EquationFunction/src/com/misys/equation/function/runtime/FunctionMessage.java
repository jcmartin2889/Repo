package com.misys.equation.function.runtime;

import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.Toolbox;
import com.misys.equation.function.beans.RelatedFields;

/**
 * This class represents a message generated at run time
 * 
 * <p>
 * This contains the following information: <br>
 * 1. the function number that generated the message <br>
 * 2. the screen number that generated the message <br>
 * 3. the field that generated the warning <br>
 * 4. the warning message <br>
 * 5. the sequence number, this will normally be 0. If will only be relevant if the same field issues the same message in 2
 * different scenarios <br>
 */
public class FunctionMessage implements Cloneable
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionMessage.java 14803 2012-11-05 11:57:09Z williae1 $";

	private int screenSetId;
	private int scrnNo;
	private String fieldName;
	private EQMessage eqMessage;
	private int sequence;

	// These fields are added to the message on UI display
	private String firstLevelText;
	private String secondLevelText;

	// These fields are needed for ESF amount limit authorisation (UTR71R)
	private String branch;
	private String amount;
	private String debitCredit;

	// Supervisor who has overridden the message
	private String authorisor;

	/**
	 * Construct a new empty message
	 */
	public FunctionMessage()
	{
		super();
		this.screenSetId = 0;
		this.scrnNo = 0;
		this.fieldName = "";
		this.sequence = 0;
		this.eqMessage = null;
		this.firstLevelText = "";
		this.secondLevelText = "";
		this.branch = "";
		this.amount = "";
		this.debitCredit = "";
		this.authorisor = "";
	}

	/**
	 * Construct a new message generated at run time
	 * 
	 * @param screenSet
	 *            - the screen set where the message has originated
	 * @param scrnNo
	 *            - the screen number where the message has originated
	 * @param fieldName
	 *            - the field name that causes the message to be generated
	 * @param eqMessage
	 *            - the message
	 * @param firstLevelText
	 *            - text to be appear together with the text
	 * @param secondLevelText
	 *            - text to be appear in the second level text
	 */
	public FunctionMessage(int screenSetId, int scrnNo, String fieldName, int sequence, EQMessage eqMessage, String firstLevelText,
					String secondLevelText)
	{
		this.screenSetId = screenSetId;
		this.scrnNo = scrnNo;
		this.fieldName = fieldName;
		this.sequence = sequence;
		this.eqMessage = eqMessage;
		this.eqMessage.setFieldNames(fieldName);
		setFirstLevelText(firstLevelText);
		setSecondLevelText(secondLevelText);
		this.branch = "";
		this.amount = "";
		this.debitCredit = "";
		this.authorisor = "";
	}

	/**
	 * Return the screen set Id
	 * 
	 * @return the screen set Id
	 */
	public int getScreenSetId()
	{
		return screenSetId;
	}

	/**
	 * Set the screen set Id
	 * 
	 * @param screenSetId
	 *            - the screen set Id
	 */
	public void setScreenSetId(int screenSetId)
	{
		this.screenSetId = screenSetId;
	}

	/**
	 * Return the screen number
	 * 
	 * @return the screen number
	 */
	public int getScrnNo()
	{
		return scrnNo;
	}

	/**
	 * Set the screen number
	 * 
	 * @param scrnNo
	 *            - the screen number
	 */
	public void setScrnNo(int scrnNo)
	{
		this.scrnNo = scrnNo;
	}

	/**
	 * Return the field name
	 * 
	 * @return the field name
	 */
	public String getFieldName()
	{
		return fieldName;
	}

	/**
	 * Set the field name
	 * 
	 * @param fieldName
	 *            - the field name
	 */
	public void setFieldName(String fieldName)
	{
		this.fieldName = fieldName;
	}

	/**
	 * Return the sequence number
	 * 
	 * @return the sequence number
	 */
	public int getSequence()
	{
		return sequence;
	}

	/**
	 * Set the sequence number
	 * 
	 * @param sequence
	 *            - the sequence number
	 */
	public void setSequence(int sequence)
	{
		this.sequence = sequence;
	}

	/**
	 * Return the Equation message
	 * 
	 * @return the Equation message
	 */
	public EQMessage getEqMessage()
	{
		return eqMessage;
	}

	/**
	 * Set the Equation message
	 * 
	 * @param eqMessage
	 *            - an Equation message
	 */

	public void setEqMessage(EQMessage eqMessage)
	{
		this.eqMessage = eqMessage;
	}

	/**
	 * Return the additional first level text
	 * 
	 * @return the additional first level text
	 */
	public String getFirstLevelText()
	{
		return firstLevelText;
	}

	/**
	 * Set the additional first level text
	 * 
	 * @param firstLevelText
	 *            - the additional first level text
	 */
	public void setFirstLevelText(String firstLevelText)
	{
		if (firstLevelText == null)
		{
			this.firstLevelText = "";
		}
		else
		{
			this.firstLevelText = firstLevelText;
		}
	}

	/**
	 * Return the additional second level text
	 * 
	 * @return the additional second level text
	 */
	public String getSecondLevelText()
	{
		return secondLevelText;
	}

	/**
	 * Set the additional second level text
	 * 
	 * @param secondLevelText
	 *            - the additional second level text
	 */
	public void setSecondLevelText(String secondLevelText)
	{
		if (secondLevelText == null)
		{
			this.secondLevelText = "";
		}
		else
		{

			this.secondLevelText = secondLevelText;
		}
	}

	/**
	 * Return the branch corresponding to the message
	 * 
	 * @return the branch corresponding to the message
	 */
	public String getBranch()
	{
		return branch;
	}

	/**
	 * Set the branch corresponding to the message
	 * 
	 * @param branch
	 *            - the branch corresponding to the message
	 */
	public void setBranch(String branch)
	{
		this.branch = branch;
	}

	/**
	 * Return the amount corresponding to the message
	 * 
	 * @return the amount corresponding to the message
	 */
	public String getAmount()
	{
		return amount;
	}

	/**
	 * Set the amount corresponding to the message
	 * 
	 * @param amount
	 *            - the amount corresponding to the message
	 */
	public void setAmount(String amount)
	{
		this.amount = amount;
	}

	/**
	 * Return the debit/credit flag corresponding to the message
	 * 
	 * @return the debit/credit flag corresponding to the message
	 */
	public String getDebitCredit()
	{
		return debitCredit;
	}

	/**
	 * Set the debit/credit flag corresponding to the message
	 * 
	 * @param debitCredit
	 *            - the debit/credit flag corresponding to the message
	 */
	public void setDebitCredit(String debitCredit)
	{
		this.debitCredit = debitCredit;
	}

	/**
	 * Return the supervisor who has overridden the message
	 * 
	 * @return the supervisor who has overridden the message
	 */
	public String getAuthorisor()
	{
		return authorisor;
	}

	/**
	 * Set the supervisor who has overridden the message
	 * 
	 * @param supervisor
	 *            - the supervisor who has overridden the message
	 */
	public void setAuthorisor(String authorisor)
	{
		this.authorisor = authorisor;
	}

	/**
	 * Return the formatted String representation
	 * 
	 * @return the formatted String representation
	 */
	public String rtvFormattedText()
	{
		return eqMessage.getFormattedMessage() + firstLevelText;
	}

	/**
	 * Return the unformatted String representation
	 * 
	 * @return the unformatted String representation
	 */
	public String rtvUnformattedText()
	{
		return eqMessage.getUnFormattedText() + firstLevelText;
	}

	/**
	 * Return the message severity
	 * 
	 * @return the message severity
	 */
	public int rtvMsgSev()
	{
		return Toolbox.parseInt(eqMessage.getSeverity(), FunctionMessages.MSG_ERROR);
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		RelatedFields relFields = new RelatedFields(fieldName);
		return eqMessage + " ||| " + "ScreenSet=" + screenSetId + " " + "Scrn=" + scrnNo + " " + "Field="
						+ relFields.getFirstField() + " " + "Sequence=" + sequence + " " + "First Level =" + firstLevelText + "  "
						+ "Second Level =" + secondLevelText;
	}

	/**
	 * Create and return a copy of the function message
	 * 
	 * @return the copy of the function message
	 */
	public FunctionMessage clone()
	{
		FunctionMessage fm = new FunctionMessage();
		fm.screenSetId = this.screenSetId;
		fm.scrnNo = this.scrnNo;
		fm.fieldName = this.fieldName;
		fm.eqMessage = this.eqMessage;
		fm.sequence = this.sequence;
		fm.firstLevelText = this.firstLevelText;
		fm.secondLevelText = this.secondLevelText;
		fm.branch = this.branch;
		fm.amount = this.amount;
		fm.debitCredit = this.debitCredit;
		fm.authorisor = this.authorisor;
		return fm;
	}

}
