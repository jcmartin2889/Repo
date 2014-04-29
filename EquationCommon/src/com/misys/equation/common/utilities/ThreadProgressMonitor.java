package com.misys.equation.common.utilities;

/**
 * Uses a ThreadLocal object linked to the current thread to access whatever Monitor is currently in use.
 * <p>
 * Use the static convenience methods to avoid having to check for null every time.
 * 
 * @author berzosa
 */
public class ThreadProgressMonitor
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ThreadProgressMonitor.java 7607 2010-06-01 17:05:56Z MACDONP1 $";
	private static final ThreadProgressMonitor INSTANCE = new ThreadProgressMonitor();
	private ThreadLocal<Monitor> local = new ThreadLocal<Monitor>();

	public static void beginTask(String name, int totalWork)
	{
		final Monitor monitor = INSTANCE.local.get();
		if (monitor != null)
		{
			monitor.beginTask(name, totalWork);
		}
	}

	public static void done()
	{
		final Monitor monitor = INSTANCE.local.get();
		if (monitor != null)
		{
			monitor.done();
		}
	}

	public static void setTaskName(String name)
	{
		final Monitor monitor = INSTANCE.local.get();
		if (monitor != null)
		{
			monitor.setTaskName(name);
		}
	}

	public static void subTask(String name)
	{
		final Monitor monitor = INSTANCE.local.get();
		if (monitor != null)
		{
			monitor.subTask(name);
		}
	}

	public static void worked(int work)
	{
		final Monitor monitor = INSTANCE.local.get();
		if (monitor != null)
		{
			monitor.worked(work);
		}
	}

	public static void setMonitor(Monitor monitor)
	{
		INSTANCE.local.set(monitor);
	}

	public static interface Monitor
	{
		public void beginTask(String name, int totalWork);

		public void done();

		public void setTaskName(String name);

		public void subTask(String name);

		public void worked(int work);
	}
}
