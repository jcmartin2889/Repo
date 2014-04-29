package com.misys.equation.function.test.beans;

import java.util.Hashtable;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAERecordDao;
import com.misys.equation.common.dao.beans.GAERecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.ApplicationContextManager;
import com.misys.equation.common.utilities.EqBeanFactory;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.tools.RepeatingDetails;

/**
 * FunctionSerialStub.class
 * 
 * @author deroset
 * 
 */
public class FunctionSerialStubTest extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FunctionSerialStubTest.java 6793 2010-03-31 12:10:20Z deroset $";
	private ApplicationContextManager applicationContextManager;
	private static final EquationLogger LOG = new EquationLogger(FunctionSerialStubTest.class);
	private EquationStandardSession session;
	private final DaoFactory daoFactory = new DaoFactory();

	@Override
	public void setUp() throws Exception
	{

		applicationContextManager = ApplicationContextManager.getInstance();
		setUpTestingEnvironment();
	}

	public void setUpTestingEnvironment()
	{

		try
		{

			TestEnvironment.setTestEnvironment();
			session = TestEnvironment.getTestEnvironment().getStandardSession();
		}
		catch (Exception exception)
		{

			LOG.error("There was an errror in the FunctionSerialStubTest", exception);
		}
	}

	private IGAERecordDao getDao()
	{
		IGAERecordDao dao = daoFactory.getGAEDao(session, new GAERecordDataModel());
		return dao;
	}

	public void testSelect()
	{

		Hashtable<String, GAERecordDataModel> models = null;
		String whereClause = "GAEATYP <> '009'";
		String key = "HCI";

		IGAERecordDao dao = getDao();
		models = dao.getGAERecordKeyedByApiId(whereClause);

		assertNotNull(models);
		assertNotNull(models.get(key));
	}

	public void testGetGAERecordBy() throws EQException
	{

		GAERecordDataModel gAERecord;
		Hashtable<String, GAERecordDataModel> models;
		String whereClause = "GAEFID='ASI'";
		String key = "ASI";

		Function function = new Function("FNC", "this is the title", "This is the description");
		InputFieldSet fieldSet = new InputFieldSet("FLDSETID", "field set label", "field set description");
		function.addInputFieldSet(fieldSet);

		IGAERecordDao dao = getDao();

		models = dao.getGAERecordKeyedByApiId(whereClause);
		gAERecord = models.get(key);
		assertNotNull(models);
		assertNotNull(gAERecord);

		fieldSet.addAPI(TestEnvironment.getTestEnvironment().getStandardSession(), gAERecord, "", true, false, true, true, true,
						"", false, false, new RepeatingDetails(), false, "", null, "");
		EqBeanFactory eqBeanFactory = EqBeanFactory.getEqBeanFactory();
		String xml = eqBeanFactory.serialiseBeanAsXML(function);
		System.out.println(xml);
	}

}
