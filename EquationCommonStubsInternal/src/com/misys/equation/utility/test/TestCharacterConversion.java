package com.misys.equation.utility.test;

import junit.framework.TestCase;

import com.ibm.as400.access.AS400BidiTransform;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.BidiConversionProperties;
import com.ibm.as400.access.BidiStringType;
import com.misys.equation.common.utilities.Toolbox;

// Via Save and Restore
public class TestCharacterConversion extends TestCase
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TestCharacterConversion.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	public void test()
	{
		// Have a bash...
		try
		{
			BidiConversionProperties bidiProps = new BidiConversionProperties();
			AS400BidiTransform abt;
			abt = new AS400BidiTransform(420);
			String convBefore = "REGULAR ش لا";
			// String convAfter = abt.toAS400Layout(convBefore);
			AS400Text text = new AS400Text(35, 420);
			byte[] b = new byte[35];
			// text.toBytes("REGULAR ع", b, 0, bidiProps);
			text.toBytes(convBefore, b, 0, BidiStringType.DEFAULT);
			System.out.println("DFLT: " + Toolbox.cvtBytesToHexString(b));
			String s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.NONE);
			System.out.println("NONE: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST4);
			System.out.println("ST04: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST5);
			System.out.println("ST05: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST6);
			System.out.println("ST06: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST7);
			System.out.println("ST07: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST8);
			System.out.println("ST08: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST9);
			System.out.println("ST09: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST10);
			System.out.println("ST10: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);
			text.toBytes(convBefore, b, 0, BidiStringType.ST11);
			System.out.println("ST11: " + Toolbox.cvtBytesToHexString(b));
			s2 = (String) text.toObject(b);
			System.out.println(s2);

			testCharacterReturn();

			System.out.println("done");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void testCharacterReturn()
	{

		String s2 = "all work makes\r\njack a dull by";
		String s3 = "";
		System.out.println(s2);
		s2 = Toolbox.convertUnicodeCharacterReturnToEqMark(s2);
		s3 = s2;
		System.out.println(s2);

		s2 = Toolbox.convertEqMarkToUnicodeCharacterReturn(s2);
		System.out.println(s2);

		s3 = Toolbox.convertEqMarkToHtmlBR(s3);
		System.out.println(s3);

	}
}
