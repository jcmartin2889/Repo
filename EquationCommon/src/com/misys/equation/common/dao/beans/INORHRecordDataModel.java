package com.misys.equation.common.dao.beans;

public class INORHRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: INORHRecordDataModel.java 4546 2009-08-27 23:01:54Z esther.williams $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "INORHPF";
	private String orderNumber; // INHORNO
	private String customerCode; // INHOCUS
	private int despatchDate; // INHODDT
	private int cutDate; // INOXDT
	private String despatchType; // INHODTP
	private String customerOrderNumber; // INHOCNO
	private String mediaType; // INHOMED
	private String destination; // INHODST
	private String despatchMethod; // INHODMT
	private String generateSource; // INHOSRC
	private String as400TargetRelease; // INHOTGTRLS
	private String orderText; // INHORDTXT
	private String parentOrder; // INHOPARENT
	private String mediaText; // INHMEDTXT
	private String despatchTypeText; // INHDSPTXT
	private String despatchCode; // INHDSPCODE
	private String licenceType; // INHLICTP
	private int currentNumberOfObjects; // INHOCUROBJ
	private int sizeMB; // INHOCMB
	/**
	 * Construct an empty file
	 * 
	 */
	public INORHRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 */
	public INORHRecordDataModel(String orderNumber, String customerCode, int despatchDate, int cutDate, String despatchType,
					String customerOrderNumber, String mediaType, String destination, String despatchMethod, String generateSource,
					String as400TargetRelease, String orderText, String parentOrder, String mediaText, String despatchTypeText,
					String despatchCode, String licenceType, int currentNumberOfObjects, int sizeMB)
	{
		super();
		setEqFileName(RECORD_NAME);

		this.setOrderNumber(orderNumber);
		this.setCustomerCode(customerCode);
		this.setDespatchDate(despatchDate);
		this.setCutDate(cutDate);
		this.setDespatchType(despatchType);
		this.setCustomerOrderNumber(customerOrderNumber);
		this.setMediaType(mediaType);
		this.setDestination(destination);
		this.setDespatchMethod(despatchMethod);
		this.setGenerateSource(generateSource);
		this.setAs400TargetRelease(as400TargetRelease);
		this.setOrderText(orderText);
		this.setParentOrder(parentOrder);
		this.setMediaText(mediaText);
		this.setDespatchTypeText(despatchTypeText);
		this.setDespatchCode(despatchCode);
		this.setLicenceType(licenceType);
		this.setCurrentNumberOfObjects(currentNumberOfObjects);
		this.setSizeMB(sizeMB);
	}

	// ---getters and setters----//

	public String getOrderNumber()
	{
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber)
	{
		this.orderNumber = orderNumber;
	}

	public String getCustomerCode()
	{
		return customerCode;
	}

	public void setCustomerCode(String customerCode)
	{
		this.customerCode = customerCode;
	}

	public int getDespatchDate()
	{
		return despatchDate;
	}

	public void setDespatchDate(int despatchDate)
	{
		this.despatchDate = despatchDate;
	}
	public int getCutDate()
	{
		return cutDate;
	}

	public void setCutDate(int cutDate)
	{
		this.cutDate = cutDate;
	}
	public String getDespatchType()
	{
		return despatchType;
	}

	public void setDespatchType(String despatchType)
	{
		this.despatchType = despatchType;
	}

	public String getCustomerOrderNumber()
	{
		return customerOrderNumber;
	}

	public void setCustomerOrderNumber(String customerOrderNumber)
	{
		this.customerOrderNumber = customerOrderNumber;
	}

	public String getMediaType()
	{
		return mediaType;
	}

	public void setMediaType(String mediaType)
	{
		this.mediaType = mediaType;
	}

	public String getDestination()
	{
		return destination;
	}

	public void setDestination(String destination)
	{
		this.destination = destination;
	}

	public String getDespatchMethod()
	{
		return despatchMethod;
	}

	public void setDespatchMethod(String despatchMethod)
	{
		this.despatchMethod = despatchMethod;
	}

	public String getGenerateSource()
	{
		return generateSource;
	}

	public void setGenerateSource(String generateSource)
	{
		this.generateSource = generateSource;
	}

	public String getAs400TargetRelease()
	{
		return as400TargetRelease;
	}

	public void setAs400TargetRelease(String as400TargetRelease)
	{
		this.as400TargetRelease = as400TargetRelease;
	}

	public String getOrderText()
	{
		return orderText;
	}

	public void setOrderText(String orderText)
	{
		this.orderText = orderText;
	}

	public String getParentOrder()
	{
		return parentOrder;
	}

	public void setParentOrder(String parentOrder)
	{
		this.parentOrder = parentOrder;
	}

	public String getMediaText()
	{
		return mediaText;
	}

	public void setMediaText(String mediaText)
	{
		this.mediaText = mediaText;
	}

	public String getDespatchTypeText()
	{
		return despatchTypeText;
	}

	public void setDespatchTypeText(String despatchTypeText)
	{
		this.despatchTypeText = despatchTypeText;
	}

	public String getDespatchCode()
	{
		return despatchCode;
	}

	public void setDespatchCode(String despatchCode)
	{
		this.despatchCode = despatchCode;
	}

	public String getLicenceType()
	{
		return licenceType;
	}

	public void setLicenceType(String licenceType)
	{
		this.licenceType = licenceType;
	}

	public int getCurrentNumberOfObjects()
	{
		return currentNumberOfObjects;
	}

	public void setCurrentNumberOfObjects(int currentNumberOfObjects)
	{
		this.currentNumberOfObjects = currentNumberOfObjects;
	}

	public int getSizeMB()
	{
		return sizeMB;
	}

	public void setSizeMB(int sizeMB)
	{
		this.sizeMB = sizeMB;
	}

	// ---getters and setters----//

}
