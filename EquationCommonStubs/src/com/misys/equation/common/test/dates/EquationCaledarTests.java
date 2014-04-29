package com.misys.equation.common.test.dates;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.misys.equation.common.dates.EquationCalendar;
import com.misys.equation.common.test.EquationTestCase;
import com.misys.equation.common.test.TestEnvironment;

import com.misys.equation.common.utilities.EquationLogger;

/**
 * This test case tests the basics for the EquationCalendar and CustomCalendar classes.
 * 
 * @author camillen
 * 
 */
public class EquationCaledarTests extends EquationTestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id:";

	private static final EquationLogger LOG = new EquationLogger(EquationCaledarTests.class);
	private String sessionId;

	/**
	 * Setup
	 */
	@Override
	public void setUp() throws Exception
	{
		super.setUp();
		// Prepare the data needed for this test final String sessionId =
		this.sessionId = TestEnvironment.getTestEnvironment().getSessionId();
	}

	public void testConvertToEquationDate()
	{
		// YYYMMDD
		// 01012000
		// 1000101

		// 01011999
		// 9900101

		try
		{
			Calendar javaCaledar = new GregorianCalendar();
			javaCaledar.set(Calendar.DAY_OF_MONTH, 1);
			javaCaledar.set(Calendar.MONTH, 0);
			javaCaledar.set(Calendar.YEAR, 2000);
			javaCaledar.set(Calendar.HOUR, 0);
			javaCaledar.set(Calendar.MINUTE, 0);
			javaCaledar.set(Calendar.SECOND, 0);
			javaCaledar.set(Calendar.MILLISECOND, 0);

			EquationCalendar eqCalendar = new EquationCalendar("1000101");

			assertTrue(eqCalendar.isSameDateIgnoreTime(javaCaledar));
		}
		catch (Exception e)
		{
			LOG.error(e);
			assertTrue(false);
		}

	}

	public void testConvertFromEquationDate()
	{
		try
		{
			String equationDate = "1010100";
			EquationCalendar calendar = new EquationCalendar(equationDate);
			EquationCalendar calendar2 = new EquationCalendar(calendar.getTime());

			assertEquals(calendar, calendar2);

		}
		catch (Exception e)
		{
			LOG.error(e);
			assertTrue(false);
		}

	}

}
