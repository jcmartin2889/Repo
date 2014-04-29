package com.misys.equation.common.dao.beans;

public class INORDRecordDataModel extends AbsRecord
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: INORDRecordDataModel.java 4546 2009-08-27 23:01:54Z esther.williams $";
	private static final long serialVersionUID = 1L;
	private final static String RECORD_NAME = "INORDPF";

	private String orderNumber; // INDDRNO
	private String productCode; // INDDPRD
	private String componentType1; // INDDCTP
	private String machine; // INDDMCH
	private int installSequence; // INDDSEQ
	private String parentReleaseFlag; // INDDPARREL
	private String component; // INDCPRD
	private String componentDescription; // INDCDES
	private String componentMachine; // INDCMCH
	private String componentType2; // INDCLVL
	private int numberOfObjects; // INDCOBJ
	private int currentNumberOfObjects; // INDCUROBJ
	private int sizeMB; // INDCMB
	private String selectionField; // INDDSEL
	private String libLibraryName; // INDDENLIB
	private String filLibraryName; // INDDENFIL
	private String wrkLibraryName; // INDDENWRK
	private String inpLibraryName; // INDDENINP
	/**
	 * Construct an empty file
	 * 
	 */
	public INORDRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	/**
	 */
	public INORDRecordDataModel(String orderNumber, String productCode, String componentType1, String machine, int installSequence,
					String parentReleaseFlag, String component, String componentDescription, String componentMachine,
					String componentType2, int numberOfObjects, int currentNumberOfObjects, int sizeMB, String selectionField,
					String libLibraryName, String filLibraryName, String wrkLibraryName, String inpLibraryName)
	{
		super();
		setEqFileName(RECORD_NAME);

		this.setOrderNumber(orderNumber);
		this.setProductCode(productCode);
		this.setComponentType1(componentType1);
		this.setMachine(machine);
		this.setInstallSequence(installSequence);
		this.setParentReleaseFlag(parentReleaseFlag);
		this.setComponent(component);
		this.setComponentDescription(componentDescription);
		this.setComponentMachine(componentMachine);
		this.setComponentType2(componentType2);
		this.setNumberOfObjects(numberOfObjects);
		this.setCurrentNumberOfObjects(currentNumberOfObjects);
		this.setSizeMB(sizeMB);
		this.setSelectionField(selectionField);
		this.setLibLibraryName(libLibraryName);
		this.setFilLibraryName(filLibraryName);
		this.setWrkLibraryName(wrkLibraryName);
		this.setInpLibraryName(inpLibraryName);

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

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getComponentType1()
	{
		return componentType1;
	}

	public void setComponentType1(String componentType1)
	{
		this.componentType1 = componentType1;
	}

	public String getMachine()
	{
		return machine;
	}

	public void setMachine(String machine)
	{
		this.machine = machine;
	}

	public int getInstallSequence()
	{
		return installSequence;
	}

	public void setInstallSequence(int installSequence)
	{
		this.installSequence = installSequence;
	}

	public String getParentReleaseFlag()
	{
		return parentReleaseFlag;
	}

	public void setParentReleaseFlag(String parentReleaseFlag)
	{
		this.parentReleaseFlag = parentReleaseFlag;
	}

	public String getComponent()
	{
		return component;
	}

	public void setComponent(String component)
	{
		this.component = component;
	}

	public String getComponentDescription()
	{
		return componentDescription;
	}

	public void setComponentDescription(String componentDescription)
	{
		this.componentDescription = componentDescription;
	}

	public String getComponentMachine()
	{
		return componentMachine;
	}

	public void setComponentMachine(String componentMachine)
	{
		this.componentMachine = componentMachine;
	}

	public String getComponentType2()
	{
		return componentType2;
	}

	public void setComponentType2(String componentType2)
	{
		this.componentType2 = componentType2;
	}

	public int getNumberOfObjects()
	{
		return numberOfObjects;
	}

	public void setNumberOfObjects(int numberOfObjects)
	{
		this.numberOfObjects = numberOfObjects;
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

	public String getSelectionField()
	{
		return selectionField;
	}

	public void setSelectionField(String selectionField)
	{
		this.selectionField = selectionField;
	}

	public String getLibLibraryName()
	{
		return libLibraryName;
	}

	public void setLibLibraryName(String libLibraryName)
	{
		this.libLibraryName = libLibraryName;
	}

	public String getFilLibraryName()
	{
		return filLibraryName;
	}

	public void setFilLibraryName(String filLibraryName)
	{
		this.filLibraryName = filLibraryName;
	}

	public String getWrkLibraryName()
	{
		return wrkLibraryName;
	}

	public void setWrkLibraryName(String wrkLibraryName)
	{
		this.wrkLibraryName = wrkLibraryName;
	}

	public String getInpLibraryName()
	{
		return inpLibraryName;
	}

	public void setInpLibraryName(String inpLibraryName)
	{
		this.inpLibraryName = inpLibraryName;
	}

	// ---getters and setters----//

}