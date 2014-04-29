package com.misys.equation.function.test.beans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import junit.framework.TestCase;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.DaoFactory;
import com.misys.equation.common.dao.IGAZRecordDao;
import com.misys.equation.common.dao.beans.GAZRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.common.test.TestEnvironment;
import com.misys.equation.common.utilities.ApplicationContextManager;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.tools.XMLToolbox;

/**
 * FunctionSerialStub.class
 * 
 * @author deroset
 * 
 */
public class GetServiceSource extends TestCase
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: GetServiceSource.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	private ApplicationContextManager applicationContextManager;
	private static final EquationLogger LOG = new EquationLogger(GetServiceSource.class);
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

			LOG.error("There was an errror in the GetServiceSource", exception);
		}
	}

	public void testGetFunctionXML() throws EQException
	{
		Function function = XMLToolbox.getXMLToolbox().getFunction(session, "GMA", true);
		InputStream stream = XMLToolbox.getBeanAsStream(function);
		File file = new File("C:\\temp\\function.eqf");
		if (file.exists())
		{
			file.delete();
		}
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			byte[] b = new byte[256];
			while (stream.read(b) > -1)
			{
				fileOutputStream.write(b);
				Arrays.fill(b, (byte) 0x20);
			}
			fileOutputStream.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testGetLayoutXML() throws EQException
	{
		Layout layout = XMLToolbox.getXMLToolbox().getLayout(session, "GMA", true);
		InputStream stream = XMLToolbox.getBeanAsStream(layout);
		File file = new File("C:\\temp\\layout.eql");
		if (file.exists())
		{
			file.delete();
		}
		try
		{
			file.createNewFile();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try
		{
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			byte[] b = new byte[256];
			while (stream.read(b) > -1)
			{
				fileOutputStream.write(b);
				Arrays.fill(b, (byte) 0x20);
			}
			fileOutputStream.close();
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testGetFunctionJava() throws EQException
	{
		// Option
		String optionId = "GMA";
		// Have a bash...
		try
		{
			File folders = new File("C:\\temp\\function\\");
			folders.mkdirs();
			File file = new File("C:\\temp\\function\\" + optionId + ".class");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			if (file.exists())
			{
				file.delete();
			}
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// retrieve the GAZ record
			GAZRecordDataModel gazRecord = new GAZRecordDataModel(optionId, "", "", " ");
			IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);
			gazRecord = dao.getGAZRecord();
			boolean found = (gazRecord != null);

			if (found)
			{
				String actualClassName = gazRecord.getClassName().trim();
				byte[] classBytes = gazRecord.getClassByte();
				fileOutputStream.write(classBytes);
			}
			System.out.println("Function Java Done");
			fileOutputStream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void testGetLayoutJava() throws EQException
	{
		// Option
		String optionId = "GMA";
		// Have a bash...
		try
		{
			File folders = new File("C:\\temp\\layout\\");
			folders.mkdirs();
			File file = new File("C:\\temp\\layout\\" + optionId + ".class");
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			if (file.exists())
			{
				file.delete();
			}
			try
			{
				file.createNewFile();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// retrieve the GAZ record
			GAZRecordDataModel gazRecord = new GAZRecordDataModel(optionId, "", "", "L");
			IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);
			gazRecord = dao.getGAZRecord();
			boolean found = (gazRecord != null);

			if (found)
			{
				String actualClassName = gazRecord.getClassName().trim();
				byte[] classBytes = gazRecord.getClassByte();
				fileOutputStream.write(classBytes);
			}
			System.out.println("Layout Java Done");
			fileOutputStream.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public void testGetBFComplexTypeJava() throws EQException
	{
		// Option
		String optionId = "GMA";
		// Have a bash...
		try
		{
			File folders = new File("C:\\temp\\BFComplex\\");
			folders.mkdirs();

			// retrieve the GAZ record
			GAZRecordDataModel gazRecord = new GAZRecordDataModel(optionId, "", "EQ_CMN_addMaintainPartyGMA_SRV_REL_row", "T");
			IGAZRecordDao dao = daoFactory.getGAZDao(session, gazRecord);
			gazRecord = dao.getGAZRecord();
			boolean found = (gazRecord != null);

			if (found)
			{
				String actualClassName = gazRecord.getClassName().trim();
				File file = new File("C:\\temp\\BFComplex\\" + actualClassName + ".class");
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				if (file.exists())
				{
					file.delete();
				}
				try
				{
					file.createNewFile();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				byte[] classBytes = gazRecord.getClassByte();
				fileOutputStream.write(classBytes);
				fileOutputStream.close();
			}
			System.out.println("Complex Type Java Done");

		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
