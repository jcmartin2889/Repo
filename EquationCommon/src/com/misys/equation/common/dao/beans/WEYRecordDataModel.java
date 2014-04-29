package com.misys.equation.common.dao.beans;

import com.misys.equation.common.utilities.Toolbox;

/**
 * SessionRecord data-model.
 * 
 * @author deroset
 * 
 */
public class WEYRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: WEYRecordDataModel.java 14972 2012-11-26 18:34:19Z williae1 $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "WEYPF";

	private String userId; // WEYUID
	private String optionId; // WEYOID
	private String sessionId; // WEYSID
	private String transactionId; // WEYTID
	private String loadId; // WEYLID
	private String loadType; // WEYTYP
	private int gsOffset; // WEYGOF
	private byte[] beforeImage; // WEYDTA
	private String beforeImageEQ = ""; // WEYBDTA

	/**
	 * Default constructor
	 */
	public WEYRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);

	}

	/**
	 * Construct a new record key
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id
	 * @param transactionId
	 *            - transaction id
	 * @param userId
	 *            - user id
	 * @param loadId
	 *            - load id
	 */
	public WEYRecordDataModel(String optionId, String sessionId, String transactionId, String userId, String loadId)
	{
		super();
		setEqFileName(RECORD_NAME);

		setOptionId(optionId);
		setSessionId(sessionId);
		setTransactionId(transactionId);
		setUserId(userId);
		setLoadId(loadId);
	}

	/**
	 * Construct a new record with details
	 * 
	 * @param optionId
	 *            - option id
	 * @param sessionId
	 *            - session id
	 * @param transactionId
	 *            - transaction id
	 * @param userId
	 *            - user id
	 * @param loadId
	 *            - load id
	 * @param loadType
	 *            - load transaction type
	 * @param gsOffSet
	 *            - GS offset
	 * @param beforeImage
	 *            - before image data in bytes
	 */
	public WEYRecordDataModel(String optionId, String sessionId, String transactionId, String userId, String loadId,
					String loadType, int gsOffset, byte[] beforeimage, String beforeImageEQ)
	{
		super();
		setEqFileName(RECORD_NAME);

		setUserId(userId);
		setOptionId(optionId);
		setSessionId(sessionId);
		setTransactionId(transactionId);
		setLoadId(loadId);
		setLoadType(loadType);
		setGsOffset(gsOffset);
		setBeforeImage(beforeimage);
		setBeforeImageEQ(beforeImageEQ);
	}

	/**
	 * Return the keys
	 * 
	 * @return the keys
	 */
	public String getKey()
	{
		return sessionId + userId + transactionId + optionId + loadId;
	}

	// ---getters and setters----//

	/**
	 * Return the user id
	 * 
	 * @return the user id
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * Set the user id
	 * 
	 * @param userId
	 *            - the user id
	 */
	public void setUserId(String userId)
	{
		this.userId = Toolbox.trim(userId, 10);
	}

	/**
	 * Return the option id
	 * 
	 * @return the option id
	 */
	public String getOptionId()
	{
		return optionId;
	}

	/**
	 * Set the option id
	 * 
	 * @param optionId
	 *            - the option id
	 */
	public void setOptionId(String optionId)
	{
		this.optionId = Toolbox.trim(optionId, 3);
	}

	/**
	 * Return the session id
	 * 
	 * @return the session id
	 */
	public String getSessionId()
	{
		return sessionId;
	}

	/**
	 * Set the session id
	 * 
	 * @param sessionId
	 *            - the session id
	 */
	public void setSessionId(String sessionId)
	{
		this.sessionId = Toolbox.trim(sessionId, 50);
	}

	/**
	 * Return the transaction id
	 * 
	 * @return the transaction id
	 */
	public String getTransactionId()
	{
		return transactionId;
	}

	/**
	 * Set the transaction id
	 * 
	 * @param transactionId
	 *            - the transaction id
	 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = Toolbox.trim(transactionId, 50);
	}

	/**
	 * Return the load id
	 * 
	 * @return the load id
	 */
	public String getLoadId()
	{
		return loadId;
	}

	/**
	 * Set the load id
	 * 
	 * @param loadId
	 *            - the load id
	 */
	public void setLoadId(String loadId)
	{
		this.loadId = Toolbox.trim(loadId, 50);
	}

	/**
	 * Return the load transaction type
	 * 
	 * @return the load transaction type
	 */
	public String getLoadType()
	{
		return loadType;
	}

	/**
	 * Set the load transaction type
	 * 
	 * @param loadType
	 *            - the load transaction type
	 */
	public void setLoadType(String loadType)
	{
		this.loadType = Toolbox.trim(loadType, 10);
	}

	/**
	 * Return the GS offset
	 * 
	 * @return the GS offset
	 */
	public int getGsOffset()
	{
		return gsOffset;
	}

	/**
	 * Set the GS offset
	 * 
	 * @param gsOffset
	 *            - the GS offset
	 */
	public void setGsOffset(int gsOffset)
	{
		this.gsOffset = gsOffset;
	}

	/**
	 * Return the before image
	 * 
	 * @return the before image
	 */
	public byte[] getBeforeImage()
	{
		return beforeImage;
	}

	/**
	 * Set the before image
	 * 
	 * @param beforeImage
	 *            - the before image
	 */
	public void setBeforeImage(byte[] beforeImage)
	{
		this.beforeImage = beforeImage;
	}

	/**
	 * Return the before image - EQ
	 * 
	 * @return the before image EQ
	 */
	public String getBeforeImageEQ()
	{
		return beforeImageEQ;
	}

	/**
	 * Set the before image - EQ
	 * 
	 * @param beforeImage
	 *            - the before image EQ
	 */
	public void setBeforeImageEQ(String beforeImage)
	{
		this.beforeImageEQ = beforeImage;
	}

}