package test.old;

import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class TestExecuteMF
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		Hashtable<String, String> mfParms;
		Date now;
		Long a;
		Long b;
		Long c;
		StringBuffer stringBuffer;
		HashMap<?, ?> bp;

		// TODO Auto-generated method stub
		// Get us a helper that will create the BF call environment
		BFCommandHelper bfCommandHelper = BFCommandHelper.getBFCommandHelper();
		bfCommandHelper.start();
		// Have a bash at logging on and printing the user
		Map<?, ?> user = bfCommandHelper.logOn("retail", "retail");
		// bfCommandHelper.printBankFusionMap(user);

		// 200
		stringBuffer = new StringBuffer();
		now = new Date();
		c = now.getTime();
		while (stringBuffer.length() < 200)
		{
			stringBuffer.append(c);
		}
		mfParms = new Hashtable<String, String>();
		mfParms.put("innyMF", stringBuffer.toString());
		mfParms.put("outtyMF", "");
		now = new Date();
		a = now.getTime();
		bp = bfCommandHelper.executeBusinessProcess("retail", "MicroflowWithAS1", mfParms);
		now = new Date();
		b = now.getTime();
		System.out.println("Milliseconds to execute BP with parm length 200");
		System.out.println(b - a);
		bfCommandHelper.printBankFusionMap(bp);

		// logoff
		bfCommandHelper.logOff();
		bfCommandHelper.closedown();
	}
}
