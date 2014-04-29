package com.misys.equation.common.dao;

import java.util.List;
import java.util.Map;

public interface IGPEServiceRecordDao extends IDao
{
	public List<Map<String, Object>> getBalances(String unit, String system);
	public List<Map<String, Object>> getOvernightPositions(String yDate, String pDate, String unit, String system);
	public List<Map<String, Object>> getOvernightPostings(String yDate, String pDate, String unit, String system);
	public List<Map<String, Object>> getPositionsByDate(String maxDate, String unit, String system);
}
