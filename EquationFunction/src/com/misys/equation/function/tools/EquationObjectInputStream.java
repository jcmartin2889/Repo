package com.misys.equation.function.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

import com.misys.equation.common.access.EquationCommonContext;
import com.misys.equation.function.runtime.FunctionBankFusion;

/**
 * This class extends the existing ObjectInputStream to allow us to load BF complex types
 * 
 */
public class EquationObjectInputStream extends ObjectInputStream
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationObjectInputStream.java 17783 2014-01-16 14:14:18Z lima12 $";

	/**
	 * Constructor
	 * 
	 * @param in
	 *            - the input stream
	 * 
	 * @throws IOException
	 */
	public EquationObjectInputStream(InputStream in) throws IOException
	{
		super(in);
	}

	/**
	 * Loading of class
	 * 
	 * desc - the object stream class
	 */
	@Override
	public Class resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException
	{
		// does the name starts with "bf"
		String className = desc.getName();
		if (EquationCommonContext.isBankFusionInstalled() && className.startsWith(ComplexTypeToolbox.BF_COMPLEX_TYPE_PREFIX))
		{
			try
			{
				FunctionBankFusion fb = new FunctionBankFusion();
				Class bfClass = fb.getBFComplexTypeClass(null, className);

				if (bfClass != null)
				{
					return bfClass;
				}
			}
			catch (Exception e)
			{
			}
		}

		// use parent's method
		return super.resolveClass(desc);
	}

}
