package com.misys.equation.common.dao.beans;

/**
 * GPR Record data-model.
 * 
 * @author deroset
 * 
 */
public class GPRRecordDataModel extends AbsRecord
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GPRRecordDataModel.java 9725 2010-11-08 12:34:45Z MACDONP1 $";
	private final static String RECORD_NAME = "GPRPF";

	private String identifier;
	private String description;
	private String monitorOrAdHoc;
	private String exportGroup;
	private String exportType;
	private String linkedCustomers;
	private String monitorEnabled;
	private String monitorAdditions;
	private String monitorMaintenance;
	private String monitorDeletions;
	private String monitorAllUnits;
	private String conditions;
	private String automaticOrManualApply;
	private String propagateToAllUnits;
	private String includeAllFields;

	/**
	 * This is the unique class id used for serialisation.
	 */
	private static final long serialVersionUID = 1274173436516l;

	/**
	 * Default constructor
	 */
	public GPRRecordDataModel()
	{
		super();
		setEqFileName(RECORD_NAME);
	}

	// ---getters and setters----//

	public GPRRecordDataModel(String ruleId)
	{
		this();
		setIdentifier(ruleId);
	}

	public String getIdentifier()
	{
		return this.identifier;
	}
	public void setIdentifier(String parameter)
	{
		this.identifier = parameter;
	}
	public String getDescription()
	{
		return this.description;
	}
	public void setDescription(String parameter)
	{
		this.description = parameter;
	}
	public String getMonitorOrAdHoc()
	{
		return this.monitorOrAdHoc;
	}
	public void setMonitorOrAdHoc(String parameter)
	{
		this.monitorOrAdHoc = parameter;
	}
	public String getExportGroup()
	{
		return this.exportGroup;
	}
	public void setExportGroup(String parameter)
	{
		this.exportGroup = parameter;
	}
	public String getExportType()
	{
		return this.exportType;
	}
	public void setExportType(String parameter)
	{
		this.exportType = parameter;
	}
	public String getLinkedCustomers()
	{
		return this.linkedCustomers;
	}
	public void setLinkedCustomers(String parameter)
	{
		this.linkedCustomers = parameter;
	}
	public String getMonitorEnabled()
	{
		return this.monitorEnabled;
	}
	public void setMonitorEnabled(String parameter)
	{
		this.monitorEnabled = parameter;
	}
	public String getMonitorAdditions()
	{
		return this.monitorAdditions;
	}
	public void setMonitorAdditions(String parameter)
	{
		this.monitorAdditions = parameter;
	}
	public String getMonitorMaintenance()
	{
		return this.monitorMaintenance;
	}
	public void setMonitorMaintenance(String parameter)
	{
		this.monitorMaintenance = parameter;
	}
	public String getMonitorDeletions()
	{
		return this.monitorDeletions;
	}
	public void setMonitorDeletions(String parameter)
	{
		this.monitorDeletions = parameter;
	}
	public String getMonitorAllUnits()
	{
		return this.monitorAllUnits;
	}
	public void setMonitorAllUnits(String parameter)
	{
		this.monitorAllUnits = parameter;
	}
	public String getConditions()
	{
		return this.conditions;
	}
	public void setConditions(String parameter)
	{
		this.conditions = parameter;
	}
	public String getAutomaticOrManualApply()
	{
		return this.automaticOrManualApply;
	}
	public void setAutomaticOrManualApply(String parameter)
	{
		this.automaticOrManualApply = parameter;
	}
	public String getPropagateToAllUnits()
	{
		return this.propagateToAllUnits;
	}
	public void setPropagateToAllUnits(String parameter)
	{
		this.propagateToAllUnits = parameter;
	}
	public String getIncludeAllFields()
	{
		return this.includeAllFields;
	}
	public void setIncludeAllFields(String parameter)
	{
		this.includeAllFields = parameter;
	}
}
