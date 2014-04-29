// ------------------------------------------------------------------------------
// Copyright Misys IBS
//
// Owner: Des Middlemass
//
// Class: EQBlowfish: Helper class for encryption prior to passing to Equation
//
// Note that decryption routines are not shipped.
//
// Modified version of public BlowfishJ source
// ------------------------------------------------------------------------------
package com.misys.equation.common.internal.eapi.encryption;

import java.util.Arrays;
import java.util.Random;

import com.misys.equation.common.utilities.EquationLogger;

public class EQBlowfish
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EQBlowfish.java 9706 2010-11-05 14:59:15Z MACDONP1 $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(EQBlowfish.class);
	EQBlowfishCBC m_bfc;

	// /////////////////////////////////////////////////////////////////////////
	private static byte[] defaultKey = { 0x06c, 0x073, 0x06f, 0x069, 0x069, 0x065, 0x05e, 0x025, 0x026, 0x02a, 0x028, 0x052, 0x044,
					0x07b, 0x044, 0x038 };

	static Random _rnd;
	static
	{
		_rnd = new Random();
	}

	/**
	 * Constructor
	 * 
	 * use default key.
	 */
	public EQBlowfish()
	{
		m_bfc = new EQBlowfishCBC(defaultKey, 0, defaultKey.length, 0);
	}

	/**
	 * Constructor
	 * 
	 * use key supplied.
	 */
	public EQBlowfish(byte[] key)
	{
		m_bfc = new EQBlowfishCBC(key, 0, key.length, 0);
	}

	/**
	 * Generate next key
	 * 
	 * @param key
	 *            - the key
	 */
	static public void generateKey(byte[] key)
	{
		_rnd.nextBytes(key);
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * Encrypts a string (treated in Unicode) using the internal random generator.
	 * 
	 * @param sPlainText
	 *            string to encrypt
	 * 
	 * @return encrypted string in binhex format
	 */
	public String encryptString(char[] sPlainText)
	{
		long lCBCIV = _rnd.nextLong();
		return encStr(sPlainText, lCBCIV);
	}

	/**
	 * Encrypts a string (treated in Unicode) to a byte array using the internal random generator.
	 * 
	 * @param sPlainText
	 *            string to encrypt
	 * 
	 * @return encrypted string as a byte array
	 */
	public byte[] encryptStringToBytes(char[] sPlainText)
	{
		long lCBCIV = _rnd.nextLong();
		return encStrToBytes(sPlainText, lCBCIV);
	}

	// /////////////////////////////////////////////////////////////////////////
	/**
	 * Internal routine for string encryption, returning Hex String
	 * 
	 * @param sPlainText
	 *            - string to encrypt
	 * @param lNewCBCIV
	 *            - a key for encryption
	 * 
	 * @return encrypted string
	 */
	private String encStr(char[] sPlainText, long lNewCBCIV)
	{
		int nI, nPos, nStrLen;
		char cActChar;
		byte bPadVal;
		byte[] buf;
		byte[] newCBCIV;
		int nNumBytes;
		nStrLen = sPlainText.length;
		nNumBytes = ((nStrLen << 1) & ~7) + 8;
		buf = new byte[nNumBytes];
		nPos = 0;
		for (nI = 0; nI < nStrLen; nI++)
		{
			cActChar = sPlainText[nI];
			buf[nPos++] = (byte) ((cActChar >> 8) & 0x0ff);
			buf[nPos++] = (byte) (cActChar & 0x0ff);
		}
		bPadVal = (byte) (nNumBytes - (nStrLen << 1));
		while (nPos < buf.length)
		{
			buf[nPos++] = bPadVal;
		}
		// System.out.println("CBCIV = [" + Long.toString(lNewCBCIV) + "] hex = [" + Long.toHexString(lNewCBCIV) + "]");
		// System.out.print("unencryp bytes=[");
		// for (int i = 0; i < nNumBytes; i++){
		// System.out.print( (int)buf[i]);
		// System.out.print( ",");
		// }
		// System.out.println("]");
		m_bfc.setCBCIV(lNewCBCIV);
		m_bfc.encrypt(buf, 0, buf, 0, nNumBytes);
		// System.out.print(" encryp bytes=[");
		// for (int i = 0; i < nNumBytes; i++){
		// System.out.print( (int)buf[i]);
		// System.out.print( ",");
		// }
		// System.out.println("]");
		String strEncrypt = EQBinConverter.bytesToHexStr(buf, 0, nNumBytes);
		newCBCIV = new byte[EQBlowfishECB.BLOCKSIZE];
		EQBinConverter.longToByteArray(lNewCBCIV, newCBCIV, 0);
		String strCBCIV = EQBinConverter.bytesToHexStr(newCBCIV, 0, EQBlowfishECB.BLOCKSIZE);
		// System.out.println("encrypt = [" + strEncrypt + "]");
		// System.out.println("strCBCIV = [" + strCBCIV + "]");
		return strCBCIV + strEncrypt;
	}

	// /////////////////////////////////////////////////////////////////////////
	// /////////////////////////////////////////////////////////////////////////
	/**
	 * Internal routine for string encryption, returning byte array
	 * 
	 * @param sPlainText
	 *            - string to encrypt
	 * @param lNewCBCIV
	 *            - a key for encryption
	 * 
	 * @return encrypted string in bytes
	 */
	private byte[] encStrToBytes(char[] sPlainText, long lNewCBCIV)
	{
		int nI, nPos, nStrLen;
		char cActChar;
		byte bPadVal;
		byte[] buf;
		int nNumBytes;
		nStrLen = sPlainText.length;
		nNumBytes = ((nStrLen << 1) & ~7) + 8;
		buf = new byte[nNumBytes];
		// System.out.println("CBCIV = [" + Long.toString(lNewCBCIV) + "] hex = [" + Long.toHexString(lNewCBCIV) + "]");
		// System.out.print("text = [");
		// for (int i = 0; i < sPlainText.length; i++ ) {
		// System.out.print( sPlainText[i]);
		// System.out.print( ",");
		// }
		// System.out.println("]");
		// System.out.println("Alocated " + nNumBytes + " byte buffer");
		// System.out.println("Buffer length = " + buf.length + " bytes");
		nPos = 0;
		for (nI = 0; nI < nStrLen; nI++)
		{
			cActChar = sPlainText[nI];
			buf[nPos++] = (byte) ((cActChar >> 8) & 0x0ff);
			buf[nPos++] = (byte) (cActChar & 0x0ff);
		}
		bPadVal = (byte) (buf.length - (nStrLen << 1));
		while (nPos < buf.length)
		{
			buf[nPos++] = bPadVal;
		}
		// int bytesPrinted = 0;
		// System.out.print("unencryp bytes=[");
		// for (int i = 0; i < nNumBytes; i++){
		// System.out.print( (int)buf[i]);
		// System.out.print( ",");
		// bytesPrinted++;
		// }
		// System.out.println("]");
		// System.out.println("Buf length check " + nNumBytes + " = " + buf.length);
		// System.out.println("Bytes printed = " + bytesPrinted);
		m_bfc.setCBCIV(lNewCBCIV);
		m_bfc.encrypt(buf, 0, buf, 0, buf.length);
		// System.out.println("Encrypted buffer length = " + buf.length + " bytes");
		// System.out.print(" encryp bytes=[");
		// for (int i = 0; i < nNumBytes; i++){
		// System.out.print( buf[i]);
		// System.out.print( ",");
		// }
		// System.out.println("]");
		int totalNumBytes = EQBlowfishECB.BLOCKSIZE + buf.length;
		byte[] result = new byte[totalNumBytes];
		EQBinConverter.longToByteArray(lNewCBCIV, result, 0);
		int count = 0;
		for (int i = EQBlowfishECB.BLOCKSIZE; i < totalNumBytes; i++)
		{
			result[i] = buf[count++];
		}
		// System.out.print(" CBCIV bytes=[");
		// for (int i = 0; i < EQBlowfishCBC.BLOCKSIZE; i++){
		// System.out.print( result[i]);
		// System.out.print( ",");
		// }
		// System.out.println("]");
		// System.out.println("Final Buffer length = " + result.length + " bytes");
		// System.out.print(" final bytes=[");
		// for (int i = 0; i < totalNumBytes; i++){
		// System.out.print( result[i]);
		// System.out.print( ",");
		// }
		// System.out.println("]");
		return result;
	}

	/**
	 * Decrypts a string (treated in Unicode) to a String.
	 * 
	 * @param encryptedString
	 *            the encrypted password to be decrypted
	 * 
	 * @return decrypted string
	 */
	public String decryptString(String encryptedString)
	{
		// convert String to byte array
		byte[] password = new byte[encryptedString.length() * 2];
		int index = 0;
		for (int i = 0; i < encryptedString.length(); i++)
		{
			char character = encryptedString.charAt(i);
			password[index++] = (byte) ((character >> 8) & 0x0ff);
			password[index++] = (byte) (character & 0x0ff);
		}

		return Arrays.toString(password);
	}

	/**
	 * Decrypts a byte array (treated in Unicode) to String
	 * 
	 * @param encryptedBytes
	 *            the encrypted password to be decrypted
	 * 
	 * @return decrypted string as a char array
	 */
	public char[] decryptBytesToString(byte[] encryptedBytes)
	{
		// The first BLOCKSIZE (8) bytes contain the CBCIV value
		int numEncBytes = encryptedBytes.length - EQBlowfishECB.BLOCKSIZE;
		if (numEncBytes <= 0)
		{
			LOG.error("numEncBytes length invalid - " + Integer.valueOf(numEncBytes).toString());
			return null;
		}
		long inCBCIV = EQBinConverter.byteArrayToLong(encryptedBytes, 0);

		// set the CBCIV
		m_bfc.setCBCIV(inCBCIV);

		// decryption
		byte[] decryptedBytes = new byte[numEncBytes];
		m_bfc.decrypt(encryptedBytes, EQBlowfishECB.BLOCKSIZE, decryptedBytes, 0, numEncBytes);

		// THE FOLLOWING COMMENTED CODE DOES NOT WORK. POSITIVE AND NEGATIVE NUMBERS RETURNED
		// get number of bytes that are just 'padding'
		// int numPad = decryptedBytes[decryptedBytes.length - 1];
		int numPad = 0;

		// Convert decrypted bytes to chars
		StringBuffer result = EQBinConverter.byteArrayToStringBuffer(decryptedBytes, 0, decryptedBytes.length - numPad);

		return result.toString().toCharArray();

	}

	/**
	 * Destroys (clears) the encryption engine, after that the instance is not valid anymore.
	 */
	public void destroy()
	{
		if (m_bfc != null)
		{
			m_bfc.cleanUp();
			m_bfc = null;
		}
	}
}