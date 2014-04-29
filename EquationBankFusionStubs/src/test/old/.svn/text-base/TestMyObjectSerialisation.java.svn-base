package test.old;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestMyObjectSerialisation
{
	public TestMyObjectSerialisation()
	{
	}
	public static void main(String args[])
	{
		Object object = roundTripMyObject(createMyObject());
		Object objectBeforeSerialisation = createMyObject();
		writeMyObject(objectBeforeSerialisation);
		Object objectAfterSerialisation = readMyObject();
	}
	private static Object createMyObject()
	{
		MyObject myObject = new MyObject();
		return myObject;
	}
	private static void writeMyObject(Object o)
	{
		try
		{
			File file = new File("E:\\IBM\\workspace\\EquationBankFusionStubs\\output\\MyObject.txt");
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.flush();
			oos.close();
			fos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	private static Object readMyObject()
	{
		Object o = null;
		try
		{
			File file = new File("E:\\IBM\\workspace\\EquationBankFusionStubs\\output\\MyObject.txt");
			FileInputStream is = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(is);
			o = ois.readObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return o;
	}
	private static Object roundTripMyObject(Object objectIn)
	{
		Object objectOut = null;
		try
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(objectIn);
			oos.flush();
			oos.close();
			baos.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			bais.close();
			ObjectInputStream ois = new ObjectInputStream(bais);
			objectOut = ois.readObject();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return objectOut;
	}
}
