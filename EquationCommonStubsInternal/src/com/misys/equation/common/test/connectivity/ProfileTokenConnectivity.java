package com.misys.equation.common.test.connectivity;

import java.sql.Connection;

import junit.framework.TestCase;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400JDBCConnection;
import com.ibm.as400.access.AS400JDBCDataSource;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.CharacterDataArea;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;
import com.ibm.as400.security.auth.ProfileTokenCredential;
import com.misys.equation.common.utilities.EquationControl;
import com.misys.equation.common.utilities.EquationLogger;

public class ProfileTokenConnectivity extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: ProfileTokenConnectivity.java 16593 2013-06-24 15:32:19Z perkinj1 $";
	private static final EquationLogger LOG = new EquationLogger(ProfileTokenConnectivity.class);
	/**
	 * 
	 */
	public void testOkay()
	{
		try
		{
			String systemId = "SLOUGH1";
			String userId = "WEDDElC1";
			String password = "ACHT0NG";
			AS400 as400Base = new AS400(systemId, userId, password);

			// How to genny a token using the as400
			ProfileTokenCredential baseToken = as400Base.getProfileToken(userId, password,
							ProfileTokenCredential.TYPE_MULTIPLE_USE_RENEWABLE, 3600);

			ProgramCall program = new ProgramCall(as400Base);
			byte[] tokenBytes = new byte[32];
			try
			{
				// Initialize the name of the program to run.
				String programName = "/QSYS.LIB/QSYGENPT.PGM";

				// Set up the parameters.
				ProgramParameter[] parameterList = new ProgramParameter[6];

				// #profileToken (output)
				parameterList[0] = new ProgramParameter(32);
				// #profileName (input)
				parameterList[1] = new ProgramParameter((new AS400Text(10, 37)).toBytes("EQUIADMIN "));
				// #password (input)
				parameterList[2] = new ProgramParameter((new AS400Text(10, 37)).toBytes("*NOPWDCHK "));
				// #timeout (input)
				parameterList[3] = new ProgramParameter(new byte[] { (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF });
				// #tokenType
				parameterList[4] = new ProgramParameter((new AS400Text(1, 37)).toBytes("3"));
				// #QUSEC
				parameterList[5] = new ProgramParameter(new byte[] { 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x40, 0x40,
								0x40, 0x40, 0x40, 0x40, 0x40, 0x40 }, 16);

				// Set the program name and parameter list.
				program.setProgram(programName, parameterList);

				// Run the program.
				if (program.run() != true)
				{
					// Report failure.
					System.out.println("Program failed!");
					// Show the messages.
					AS400Message[] messagelist = program.getMessageList();
					for (int i = 0; i < messagelist.length; ++i)
					{
						// Show each message.
						System.out.println(messagelist[i]);
					}
				}
				// Else no error, get output data.
				else
				{
					tokenBytes = parameterList[0].getOutputData();
				}
			}
			catch (Exception e)
			{
				System.out.println("Program " + program.getProgram() + " issued an exception!");
				e.printStackTrace();
			}

			// pop a breakpoint around here and change the password to make sure the following code carries on regardless...

			// get a new token based on the base token one...
			// byte[] tokenBytes = baseToken.getToken();
			ProfileTokenCredential token = new ProfileTokenCredential(new AS400("SLOUGH1"), tokenBytes,
							ProfileTokenCredential.TYPE_MULTIPLE_USE_RENEWABLE, 3600);
			//
			// // pop a breakpoint on the next line, change the password and we have a problem...
			// token.refresh();
			//
			// // pop a breakpoint on the next line, change the password and all is OK! (something to do with the first refresh
			// // creating the job
			// token.refresh();

			// lets try and get an as400
			AS400 as400 = new AS400(systemId, token);
			as400.setGuiAvailable(false);

			// This will test to see if the token was good or bad
			as400.connectService(AS400.SIGNON);

			// prove we can read data froma dataaraea
			CharacterDataArea da = new CharacterDataArea(as400, "/QSYS.LIB/KLIBEQX.LIB/KAPPHS.DTAARA");
			String s = da.read();
			LOG.error(s);

			// prove we can read data from the data base
			Connection connection = null;
			AS400JDBCDataSource as400dataSource = new AS400JDBCDataSource(as400);

			as400dataSource.setPrompt(false);
			as400dataSource.setTranslateBinary(false);
			as400dataSource.setNaming("SYSTEM");
			connection = as400dataSource.getConnection();
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			EquationControl.logIntoEquation(connection, "SLOUGH1", "EQX", EquationControl.DESKTOP_MODE, null);

			// print the AS400 job attribute
			String jobId = ((AS400JDBCConnection) connection).getServerJobIdentifier();
			LOG.error("Getting a standalone iSeries job: " + jobId);
		}
		catch (Exception e)
		{
			LOG.error(e);
		}
	}
}
