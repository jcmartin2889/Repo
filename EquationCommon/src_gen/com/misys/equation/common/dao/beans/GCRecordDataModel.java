package com.misys.equation.common.dao.beans;

/**
 * GC Record data-model.
 * 
 * @author deroset
 * 
 */
public class GCRecordDataModel extends AbsRecord
{
	private final static String RECORD_NAME = "GCPF";

	private String menuId;
	private String menuTitle;
	private String c01;
	private String fid1;
	private String fid2;
	private String fid3;
	private String fid4;
	private String fid5;
	private String fid6;
	private String fid7;
	private String fid8;
	private String fid9;
	private int dte;
	private String optionMenuType;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1276150829496l;

	/**
	 * Default constructor
	 */
	public GCRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public String getMenuId()
	{
		return this.menuId;
	}
	public void setMenuId(String parameter)
	{
		this.menuId = parameter;
	}
	public String getMenuTitle()
	{
		return this.menuTitle;
	}
	public void setMenuTitle(String parameter)
	{
		this.menuTitle = parameter;
	}
	public String getC01()
	{
		return this.c01;
	}
	public void setC01(String parameter)
	{
		this.c01 = parameter;
	}
	public String getFid1()
	{
		return this.fid1;
	}
	public void setFid1(String parameter)
	{
		this.fid1 = parameter;
	}
	public String getFid2()
	{
		return this.fid2;
	}
	public void setFid2(String parameter)
	{
		this.fid2 = parameter;
	}
	public String getFid3()
	{
		return this.fid3;
	}
	public void setFid3(String parameter)
	{
		this.fid3 = parameter;
	}
	public String getFid4()
	{
		return this.fid4;
	}
	public void setFid4(String parameter)
	{
		this.fid4 = parameter;
	}
	public String getFid5()
	{
		return this.fid5;
	}
	public void setFid5(String parameter)
	{
		this.fid5 = parameter;
	}
	public String getFid6()
	{
		return this.fid6;
	}
	public void setFid6(String parameter)
	{
		this.fid6 = parameter;
	}
	public String getFid7()
	{
		return this.fid7;
	}
	public void setFid7(String parameter)
	{
		this.fid7 = parameter;
	}
	public String getFid8()
	{
		return this.fid8;
	}
	public void setFid8(String parameter)
	{
		this.fid8 = parameter;
	}
	public String getFid9()
	{
		return this.fid9;
	}
	public void setFid9(String parameter)
	{
		this.fid9 = parameter;
	}
	public int getDte()
	{
		return this.dte;
	}
	public void setDte(int parameter)
	{
		this.dte = parameter;
	}
	public String getOptionMenuType()
	{
		return this.optionMenuType;
	}
	public void setOptionMenuType(String parameter)
	{
		this.optionMenuType = parameter;
	}
}