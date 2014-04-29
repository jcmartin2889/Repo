package com.misys.equation.common.utilities;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.IDao;

/**
 * This is class is a helper which is used to load spring application files. Given that there will be one loader per application
 * this class is a singleton.
 * 
 * @author deroset
 * 
 */
public class ApplicationContextManager
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ApplicationContextManager.java 15088 2012-12-18 19:04:32Z williae1 $";
	private final String files[] = new String[] { "applicationContext.xml" };
	private ClassPathXmlApplicationContext applicationContext;

	private static ApplicationContextManager applicationContextManager;

	/**
	 * Default constructor.
	 */
	private ApplicationContextManager()
	{
		initialization();
	}

	/**
	 * This method is going to only return the class’ instance.
	 * 
	 * @return the class’ instance.
	 */
	public static ApplicationContextManager getInstance()
	{
		if (applicationContextManager == null)
		{
			applicationContextManager = new ApplicationContextManager();
		}
		return applicationContextManager;
	}

	/**
	 * This method initialise the xml class-path loader
	 */
	private void initialization()
	{
		applicationContext = new ClassPathXmlApplicationContext(files);
	}

	/**
	 * This method is going to return a pre-defined bean instance from the bean repository. <br>
	 * All beans has already been created by Spring.
	 * 
	 * @param beanName
	 *            This is the unique name which will be used to retrieve the pre-created bean.
	 * @return bean instance defined in the context.
	 * @equation.external
	 */
	public Object getBean(String beanName)
	{
		return applicationContext.getBean(beanName);
	}

	/**
	 * This method is going to return a pre-defined dao instance from the bean repository. <br>
	 * All daos has already been created by Spring. The daos will be initalised.
	 * 
	 * @param beanName
	 *            This is the unique name which will be used to retrieve the pre-created bean.
	 * 
	 * @param This
	 *            is the session which will provide the a safe transaction environment.
	 * 
	 * @return bean instance defined in the context.
	 */
	public IDao getDao(String beanName, EquationStandardSession equationStandardSession)
	{
		IDao dao = (IDao) applicationContext.getBean(beanName);
		dao.initialiseDao(equationStandardSession.getConnectionWrapper());
		return dao;
	}

	/**
	 * This method will load a DAO from Spring passing it the given DataSource. NOTE: Spring automatically gets and closes
	 * connections, hence, this method is best used with a connection pooled DataSource.
	 * <p>
	 * Since <code>daosContext.xml</code> defines DAOs as non-singletons, to get the maximum performance a pooled connection data
	 * source should be used.
	 * 
	 * @param beanName
	 *            This is the unique name which will be used to create the bean.
	 * @param dataSource
	 *            A DataSource to use for retrieving connections. For maximum performance, a pooled connection data source should be
	 *            used.
	 * 
	 * @return The DAO
	 */
	public IDao getDao(String beanName, DataSource dataSource)
	{
		IDao dao = (IDao) applicationContext.getBean(beanName);
		dao.setDataSource(dataSource);
		return dao;
	}

	public IDao getDao(String beanName, Connection connection)
	{
		IDao dao = (IDao) applicationContext.getBean(beanName);
		dao.initialiseDao(connection);
		return dao;
	}
}