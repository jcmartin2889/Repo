package com.misys.equation.common.globalprocessing.calculators;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weddelc1
 */
public class NostroPositionsResult extends AbstractGPEnquiryResult
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: NostroPositionsResult.java 10447 2011-02-07 10:29:34Z WRIGHTP1 $";
	private static final long serialVersionUID = 1L;
	private List<NostroPosition> nostroPositions;

	public NostroPositionsResult()
	{
		nostroPositions = new ArrayList<NostroPosition>();
	}

	public List<NostroPosition> getNostroPositions()
	{
		return nostroPositions;
	}

	public void setNostroPositions(ArrayList<NostroPosition> nostroPositions)
	{
		this.nostroPositions = nostroPositions;
	}

	public NostroPosition getANewNostroPositions()
	{
		return new NostroPosition();
	}

	public NostroPositionCalculator getANewNostroPositionCalculator()
	{
		return new NostroPositionCalculator();
	}

	public void setANewNostroPositions(NostroPosition nostroPositions)
	{
		this.nostroPositions.add(nostroPositions);
	}

	/**
	 * 
	 */
	public static class NostroPositionCalculator
	{
		private double currentBalance = 0;

		void calculateBalance(String opnwr, String opnwp)
		{
			currentBalance = currentBalance + (parseDouble(opnwr) - parseDouble(opnwp));
		}

		String calculateBalancePositionForOneDay(String gjbal)
		{
			Double balance = currentBalance + parseDouble(gjbal);
			NumberFormat formatter = new DecimalFormat("#######.##");
			String formatedBalance = formatter.format(balance.doubleValue());
			return formatedBalance.toString();
		}

		public Double getCurrentBalance()
		{
			return currentBalance;
		}

		public void setCurrentBalance(double currentBalance)
		{
			this.currentBalance = currentBalance;
		}

		private double parseDouble(String longToBeParsed)
		{
			return Double.parseDouble(longToBeParsed);
		}
	}
}