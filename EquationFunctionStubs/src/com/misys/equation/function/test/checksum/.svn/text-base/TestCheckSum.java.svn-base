package com.misys.equation.function.test.checksum;

import java.io.FileInputStream;
import java.security.MessageDigest;

public class TestCheckSum
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	public static void main(String args[]) throws Exception
	{

		String datafile = "checksumtest/ChecksumTestFile.log";

		// For checksum value in MD5 format , you need to change the MessageDigest : "MD5"
		// For checksum value in SHA1 format , you need to change the MessageDigest : "SHA1"
		MessageDigest md = MessageDigest.getInstance("SHA1");
		FileInputStream fis = new FileInputStream(datafile);
		byte[] dataBytes = new byte[1024];

		int nread = 0;

		while ((nread = fis.read(dataBytes)) != -1)
		{
			md.update(dataBytes, 0, nread);
		}
		;

		byte[] mdbytes = md.digest();

		// convert the byte to hex format
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mdbytes.length; i++)
		{
			sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		System.out.println("Digest(in hex format):: " + sb.toString());

	}
}
