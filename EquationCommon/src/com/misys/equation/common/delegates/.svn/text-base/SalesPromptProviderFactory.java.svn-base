package com.misys.equation.common.delegates;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SalesPromptProviderFactory
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SalesPromptProviderFactory.java";
	
	private ISalesPromptProvider salesPromptProvider;
	private BeanFactory bFactory;
	private static String promptContext = "injectionContext.xml";

	private void loadDependencyFile()
	{
		ApplicationContext context = new FileSystemXmlApplicationContext("file:" + getPromptContext() + "/" + promptContext);
		bFactory = context;
	}
	/**
	 * loads the bean definition from the external file and loads the class defined
	 */
	public void setSalesPromptProvider()
	{
		loadDependencyFile();
		salesPromptProvider = (ISalesPromptProvider) bFactory.getBean("SalesPromptProvider");
	}
	/**
	 * Returns the loaded class that implements the ISalesPromptProvider interface
	 * 
	 * @return the sales prompt provider class
	 */
	public ISalesPromptProvider getSalesPromptProvider()
	{
		return salesPromptProvider;
	}
	private static String getPromptContext()
	{
		// "META-INF/beans.xml"

		return System.getProperty("equationConfiguration");

	}
}
