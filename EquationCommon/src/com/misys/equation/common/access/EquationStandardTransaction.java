package com.misys.equation.common.access;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ibm.as400.access.AS400Text;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.datastructure.EqDS_DSJRNKY;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.internal.eapi.core.EQMessage;
import com.misys.equation.common.utilities.Toolbox;

/**
 * This class provides methods for setting Equation API fields before the transaction is executed and for getting fields after the
 * transaction is executed - a single GS record is supported.
 */
public class EquationStandardTransaction implements IEquationStandardObject
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationStandardTransaction.java 15073 2012-12-18 18:54:11Z williae1 $";

	private static final long serialVersionUID = 1L;

	/** Equation desktop function update and recovery program in iSeries */
	public final static String EDF_PGM = "W90FRR";
	/**
	 * EQ4 service program root ("W90F"). This value is used in the GAEFPR (program root) field on the GAE file when writing out
	 * service definition GAE records.
	 */
	public final static String EDF_ROOT = "W90F";
	public final static String GSPrefix = "GS";
	/**
	 * 3 character root of the EQ4 Service program ("W90"). This value is used when writing GA/GB records, where it is concatenated
	 * with the option id to form a six character program name
	 */
	public final static String EDF_SHORT_ROOT = "W90";
	public final static String EDF_JRNF = "GZW903";
	// public final static String EDF_APP = "ED";

	private String id = "";
	private String apiName = "";
	private String mode = "";
	private boolean validTransaction = false;
	private String gzFormatName = "";
	private String gsFormatName = "";
	private EquationDataStructureData gzDSData;
	private EquationDataStructureData gsDSData;
	private int gsOffset = 0;
	private byte byte4000 = 0x20;
	private byte byte9999 = 0x20;
	private int bytePositionCP = 9999; // 9999 or 4000
	private String aext = " ";
	private String arec = " ";
	private List<EQMessage> errorList = new ArrayList<EQMessage>();
	private List<EQMessage> warningList = new ArrayList<EQMessage>();
	private byte[] crmData;
	private String workStationId;
	private byte[] beforeImage;

	private EquationDataStructureData dsgyCtr;
	private EquationDataStructureData dsgyDet;
	private final EquationDataStructureData dsJrnKey;
	/**
	 * Construct a standard transaction with default journal name
	 * 
	 * @param inputApiName
	 *            - API program name + option id
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * 
	 * @throws EQException
	 */
	public EquationStandardTransaction(String inputApiName, EquationStandardSession equationStandardSession) throws EQException
	{
		GAERecordDataModel gaeRecordDataModel = null;

		if (inputApiName.length() > 6)
		{
			String optionId = inputApiName.substring(6).trim();
			gaeRecordDataModel = equationStandardSession.getUnit().getRecords().getGAERecord(optionId);
		}
		if (gaeRecordDataModel != null && !gaeRecordDataModel.getHeaderJournalFileName().equals(""))
		{
			this.gzFormatName = gaeRecordDataModel.getHeaderJournalFileName();
		}
		else
		{
			setGZFormatName(inputApiName);
		}
		setAPIName(inputApiName);
		defaultMode(inputApiName);
		initialiseTransaction(equationStandardSession);
		this.workStationId = "";
		if (gaeRecordDataModel != null && !gaeRecordDataModel.getDetailJournalFileName().equals(""))
		{
			this.gsFormatName = gaeRecordDataModel.getDetailJournalFileName();
			this.gsOffset = gaeRecordDataModel.getRepeatingDataOffset();
			initialiseTransactionGS(equationStandardSession);
		}
		else
		{
			this.gsFormatName = "";
			this.gsOffset = 0;
		}
		this.beforeImage = null;
		this.dsgyCtr = null;
		this.dsgyDet = null;
		this.dsJrnKey = new EquationDataStructureData(new EqDS_DSJRNKY());
	}

	/**
	 * Construct a standard transaction with the specified journal names
	 * 
	 * @param inputApiName
	 *            - API program name + option id
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * @param gzName
	 *            - GZ journal name
	 * @param gsName
	 *            - GS journal name
	 * @param gsOffset
	 *            - position of GS record within the DSAIM
	 * 
	 * @throws EQException
	 */
	public EquationStandardTransaction(String inputApiName, EquationStandardSession equationStandardSession, String gzName,
					String gsName, int gsOffset) throws EQException
	{
		gzFormatName = gzName;
		setAPIName(inputApiName);
		defaultMode(inputApiName);
		initialiseTransaction(equationStandardSession);
		this.workStationId = "";
		this.gsFormatName = "";
		this.gsOffset = 0;
		this.dsgyCtr = null;
		this.dsgyDet = null;
		this.dsJrnKey = new EquationDataStructureData(new EqDS_DSJRNKY());

		// GS has been specified, then initialise as well
		if (!gsName.equals(""))
		{
			gsFormatName = gsName;
			initialiseTransactionGS(equationStandardSession);
			this.gsOffset = gsOffset;
		}
	}

	/**
	 * Construct a standard transaction with GZ and GS data
	 * 
	 * @param inputApiName
	 *            - api name + option id
	 * @param equationStandardSession
	 *            - the Equation standard session
	 * @param gsOffset
	 *            - position of GS record within the DSAIM
	 * @throws EQException
	 */
	public EquationStandardTransaction(String inputApiName, EquationStandardSession equationStandardSession, int gsOffset)
					throws EQException
	{
		// initialise GZ
		setGZFormatName(inputApiName);
		setAPIName(inputApiName);
		defaultMode(inputApiName);
		initialiseTransaction(equationStandardSession);

		// initialise GS
		setGSFormatName(inputApiName);
		initialiseTransactionGS(equationStandardSession);
		this.gsOffset = gsOffset;
		this.workStationId = "";
		this.dsgyCtr = null;
		this.dsgyDet = null;
		this.dsJrnKey = new EquationDataStructureData(new EqDS_DSJRNKY());
	}

	/**
	 * Generate the default GS journal name
	 * 
	 * @param inputApiName
	 *            - API program name
	 */
	private void setGSFormatName(String inputApiName)
	{
		gsFormatName = "GS" + inputApiName.substring(0, 3) + "1";
	}

	/**
	 * Return the GS journal name
	 * 
	 * @return the GS journal name
	 */
	public String getGSFormatName()
	{
		return gsFormatName;
	}

	/**
	 * Set the API program name
	 * 
	 * @param inputApiName
	 *            - the API program name
	 */
	private void setAPIName(String inputApiName)
	{
		if (this.id.equals("") || this.apiName.equals(id))
		{
			id = inputApiName;
		}

		apiName = inputApiName;
	}

	/**
	 * Set the default GZ journal name
	 * 
	 * @param inputApiName
	 *            - API program name
	 */
	private void setGZFormatName(String inputApiName)
	{
		if (inputApiName.startsWith(EDF_PGM))
		{
			// An EQ4.0 service journal name is based on the option Id, not the program root
			// strip off the front EDF_PGM and assume it is suffixed with the option id
			gzFormatName = "GZX" + inputApiName.substring(EDF_PGM.length()) + "1";
		}
		else
		{
			// Existing EQ/3 journals use the program root
			gzFormatName = "GZ" + inputApiName.substring(0, 3) + "1";
		}
	}

	/**
	 * Set the default function mode based on the API program name
	 * 
	 * @param inputApiName
	 *            - API program name
	 */
	private void defaultMode(String inputApiName)
	{
		String apiType = inputApiName.substring(3, 4).toUpperCase();

		// Set the mode according to the API name
		if (apiType.equals(FCT_ADD))
		{
			mode = FCT_ADD;
		}
		else if (apiType.equals(FCT_MNT))
		{
			mode = FCT_MNT;
		}
		else if (apiType.equals("C"))
		{
			mode = FCT_DEL;
		}
		else if (apiType.equals(FCT_DEL))
		{
			mode = FCT_DEL;
		}
		else
		{
			mode = FCT_MNT;
		}
	}

	/**
	 * Set the function mode
	 * 
	 * @param mode
	 *            - the function mode
	 * @equation.external
	 */
	public void setMode(String mode)
	{
		this.mode = mode;
	}

	/**
	 * Set the transaction is valid
	 * 
	 * @param valid
	 *            - true, if the transaction is valid
	 */
	public void setValid(boolean valid)
	{
		validTransaction = valid;
	}

	/**
	 * Return whether the transaction is valid
	 * 
	 * @return true if the transaction is valid
	 * @equation.external
	 */
	public boolean getValid()
	{
		return validTransaction;
	}

	/**
	 * Return the API program name
	 * 
	 * @return the API program name
	 */
	public String getAPIName()
	{
		return apiName;
	}

	/**
	 * Return the GZ journal name
	 * 
	 * @return the GZ journal name
	 */
	public String getGZFormatName()
	{
		return gzFormatName;
	}

	/**
	 * Return the function mode
	 * 
	 * @return the function mode
	 */
	public String getMode()
	{
		return mode;
	}

	/**
	 * Initialise the GZ journal data structure
	 * 
	 * @throws EQException
	 */
	private void initialiseTransaction(EquationStandardSession equationStandardSession) throws EQException
	{
		gzDSData = new EquationDataStructureData(equationStandardSession.getEquationDataStructure(gzFormatName));
	}

	/**
	 * Initialise the GS journal data structure
	 * 
	 * @throws EQException
	 */
	private void initialiseTransactionGS(EquationStandardSession equationStandardSession) throws EQException
	{
		gsDSData = new EquationDataStructureData(equationStandardSession.getEquationDataStructure(gsFormatName));
	}

	/**
	 * Set the GZ or GS journal field name
	 * 
	 * @param fieldName
	 *            - GZ/GS field name
	 * @param fieldValue
	 *            - field value
	 * @equation.external
	 */
	public void setFieldValue(String fieldName, String fieldValue)
	{
		if (fieldName.startsWith(GSPrefix))
		{
			gsDSData.setFieldValue(fieldName, fieldValue);
		}
		else
		{
			gzDSData.setFieldValue(fieldName, fieldValue);
		}
	}

	/**
	 * Get the byte value for clean payment API
	 * 
	 * @return the byte value for clean payment API
	 */
	public byte getByteCP()
	{
		if (bytePositionCP == 9999)
		{
			return getByte9999();
		}
		else
		{
			return getByte4000();
		}
	}

	/**
	 * Set the byte value for clean payment API
	 * 
	 * @param value
	 *            - the byte value for clean payment API
	 */
	public void setByteCP(String value)
	{
		if (bytePositionCP == 9999)
		{
			setByte9999(value);
		}
		else
		{
			setByte4000(value);
		}
	}

	/**
	 * Set whether the byte control position for CP is 4000 or 9999
	 * 
	 * @param bytePositionCP
	 *            - the byte position 4000 or 9999
	 */
	public void setBytePositionCP(int bytePositionCP)
	{
		this.bytePositionCP = bytePositionCP;
	}

	/**
	 * @return the bytePositionCP
	 */
	public int getBytePositionCP()
	{
		return bytePositionCP;
	}

	/**
	 * Set the byte at position 4000
	 * 
	 * @param value
	 *            - the byte at position 4000
	 */
	private void setByte4000(String value)
	{
		AS400Text as400Text = new AS400Text(1);
		byte4000 = as400Text.toBytes(value)[0];
	}

	/**
	 * Return the byte at position 4000
	 * 
	 * @return the byte at position 4000
	 */
	private byte getByte4000()
	{
		return byte4000;
	}

	/**
	 * Set the byte at position 9999
	 * 
	 * @param value
	 *            - the byte at position 9999
	 */
	private void setByte9999(String value)
	{
		AS400Text as400Text = new AS400Text(1);
		byte9999 = as400Text.toBytes(value)[0];
	}

	/**
	 * Return the byte at position 9999
	 * 
	 * @return the byte at position 9999
	 */
	private byte getByte9999()
	{
		return byte9999;
	}

	/**
	 * Set the GS journal field name
	 * 
	 * @param fieldName
	 *            - GS field name
	 * @param fieldValue
	 *            - field value
	 * @equation.external
	 */
	public void setGSFieldValue(String fieldName, String fieldValue)
	{
		gsDSData.setFieldValue(fieldName, fieldValue);
	}

	/**
	 * Set the GZ journal fields
	 * 
	 * @param bytes
	 *            - the GZ journal fields in bytes
	 */
	public void setGzBytes(byte[] bytes)
	{
		gzDSData.setBytes(bytes);
	}

	/**
	 * Set the GS journal fields
	 * 
	 * @param bytes
	 *            - the GS journal fields in bytes
	 */
	public void setGsBytes(byte[] bytes)
	{
		gsDSData.setBytes(bytes);
	}

	/**
	 * Return the GZ fields in bytes
	 * 
	 * @return the GZ fields in bytes
	 */
	protected byte[] getGzBytes()
	{
		return gzDSData.getBytes();
	}

	/**
	 * Return the GS fields in bytes
	 * 
	 * @return the GS fields in bytes
	 */
	public byte[] getGsBytes()
	{
		return gsDSData.getBytes();
	}

	/**
	 * Return the data in DSAIM format
	 * 
	 * @return the data in DSAIM format
	 */
	public byte[] getBytes()
	{
		int length;
		int gzLength = getGzBytes().length;
		if (gsDSData == null && gsOffset == 0)
		{
			length = gzLength;
		}
		else
		{
			if (gsOffset == 0)
			{
				gsOffset = gzLength + 1;
			}
			length = (gsOffset - 1) + getGsBytes().length;
		}

		// special thing for CP
		if (getByteCP() != 0x20 && length < bytePositionCP)
		{
			length = bytePositionCP;
		}

		// create the byte array with the desired length
		byte[] targetByte = new byte[length];

		// GZ
		System.arraycopy(getGzBytes(), 0, targetByte, 0, gzLength);

		// initialise the rest of the array
		if (length > gzLength)
		{
			Arrays.fill(targetByte, gzLength, targetByte.length, (byte) 0x40);
		}

		// GS
		if (gsDSData != null && gsOffset != 0)
		{
			length = getGsBytes().length;
			System.arraycopy(getGsBytes(), 0, targetByte, gsOffset - 1, length);
		}

		// Some API programs look at position 4000/9999, so set if it should be
		if (getByteCP() != 0x20)
		{
			targetByte[bytePositionCP - 1] = getByteCP();
		}

		// Return the DSAIM bytes
		return targetByte;
	}

	/**
	 * Set the data bytes
	 * 
	 * @param data
	 *            - the data bytes
	 */
	public void setBytes(byte[] data)
	{
		gzDSData.setBytes(data);
		// TODO to support GZ/GS this need to be changed !
	}

	/**
	 * Return the String representation
	 * 
	 * @return the String representation
	 */
	@Override
	public String toString()
	{
		StringBuffer serialisation = new StringBuffer(gzDSData.toString());
		if (!(gsDSData == null))
		{
			serialisation.append(gsDSData.toString());
		}
		return serialisation.toString();
	}

	/**
	 * Return the field value of the GZ/GS field
	 * 
	 * @param fieldName
	 *            - field name in GZ/GS file
	 * @equation.external
	 */
	public String getFieldValue(String fieldName)
	{
		if (fieldName.startsWith(GSPrefix))
		{
			return gsDSData.getFieldValue(fieldName);
		}
		else
		{
			return gzDSData.getFieldValue(fieldName);
		}
	}

	/**
	 * Return the field value of the GS field
	 * 
	 * @param fieldName
	 *            - field name in GS file
	 * @equation.external
	 */
	public String getGSField(String fieldName)
	{
		return gsDSData.getFieldValue(fieldName);
	}

	/**
	 * Return the list of errors
	 * 
	 * @return - the list of errors
	 * @equation.external
	 */
	public List<EQMessage> getErrorList()
	{
		return errorList;
	}

	/**
	 * Set the list of errors
	 * 
	 * @param errorList
	 *            - the list of errors
	 */
	public void setErrorList(List<EQMessage> errorList)
	{
		this.errorList = errorList;
	}

	/**
	 * Return the list of warnings
	 * 
	 * @return the list of warnings
	 * @equation.external
	 */
	public List<EQMessage> getWarningList()
	{
		return warningList;
	}

	/**
	 * Set the list of warnings
	 * 
	 * @param warningList
	 *            - the list of warnings
	 */
	public void setWarningList(List<EQMessage> warningList)
	{
		this.warningList = warningList;
	}

	/**
	 * Return the GZ data
	 * 
	 * @return the GZ data
	 */
	public EquationDataStructureData getGzDSData()
	{
		return gzDSData;
	}

	/**
	 * Return the GS data
	 * 
	 * @return the GS data
	 */
	public EquationDataStructureData getGsDSData()
	{
		return gsDSData;
	}

	/**
	 * Return the apply during external input flag
	 * 
	 * @return the apply during external input flag
	 */
	public String getAext()
	{
		return aext;
	}

	/**
	 * Set the apply during external input flag
	 * 
	 * @param aext
	 *            the apply during external input flag
	 * @equation.external
	 */
	public void setAext(String aext)
	{
		this.aext = aext;
	}

	/**
	 * Return the apply during recovery flag
	 * 
	 * @return the apply during recovery flag
	 */
	public String getArec()
	{
		return arec;
	}

	/**
	 * Set the apply during recovery flag
	 * 
	 * @param arec
	 *            - the apply during recovery flag
	 * @equation.external
	 */
	public void setArec(String arec)
	{
		this.arec = arec;
	}

	/**
	 * Get the CRM data
	 * 
	 * @return the CRM data
	 */
	public byte[] getCrmData()
	{
		return crmData;
	}

	/**
	 * Set the CRM data
	 * 
	 * @param crmData
	 *            - the CRM data
	 */
	public void setCrmData(byte[] crmData)
	{
		this.crmData = crmData;
	}

	/**
	 * Set the workstation Id
	 * 
	 * @return the workstation Id
	 */
	public String getWorkStationId()
	{
		return workStationId;
	}

	/**
	 * Set the workstation Id
	 * 
	 * @param workStationId
	 *            - the workstation Id
	 * @equation.external
	 */
	public void setWorkStationId(String workStationId)
	{
		this.workStationId = Toolbox.trim(workStationId, 4);
	}

	/**
	 * Return the GS offset in DSAIM
	 * 
	 * @return the GS offset in DSAIM
	 */
	public int getGsOffset()
	{
		return gsOffset;
	}

	/**
	 * Set the GS offset in DSAIM
	 * 
	 * @param gsOffset
	 *            - the GS offset in DSAIM
	 */
	public void setGsOffset(int gsOffset)
	{
		this.gsOffset = gsOffset;
	}

	/**
	 * Return the the before image
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
	 *            the before image
	 */
	public void setBeforeImage(byte[] beforeImage)
	{
		this.beforeImage = beforeImage;
	}

	/**
	 * Return the list of messages
	 * 
	 * @return the list of messages
	 * @equation.external
	 */
	public List<EQMessage> getMessages()
	{
		if (getValid())
		{
			return getWarningList();
		}
		else
		{
			return getErrorList();
		}
	}

	/**
	 * Return the id of this transaction
	 * 
	 * @return the id of this transaction
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * Set the id of this transaction
	 * 
	 * @param id
	 *            - the id of this transaction
	 * @equation.external
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * Return the GY override control
	 * 
	 * @return the GY override control
	 */
	public EquationDataStructureData getDsgyCtr()
	{
		return dsgyCtr;
	}

	/**
	 * Set the GY override control
	 * 
	 * @param dsgyCtr
	 *            - the GY override control
	 */
	public void setDsgyCtr(EquationDataStructureData dsgyCtr)
	{
		this.dsgyCtr = dsgyCtr;
	}

	/**
	 * Return the GY override details
	 * 
	 * @return the GY override details
	 */
	public EquationDataStructureData getDsgyDet()
	{
		return dsgyDet;
	}

	/**
	 * Set the GY override details
	 * 
	 * @param dsgyDet
	 *            - the GY override details
	 */
	public void setDsgyDet(EquationDataStructureData dsgyDet)
	{
		this.dsgyDet = dsgyDet;
	}

	/**
	 * Return the journal key
	 * 
	 * @return the journal key
	 * @equation.external
	 */
	public EquationDataStructureData getDsJrnKey()
	{
		return dsJrnKey;
	}
}