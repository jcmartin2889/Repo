package com.misys.equation.builder.javadoc;

import com.sun.javadoc.RootDoc;
import com.sun.tools.doclets.formats.html.HtmlDoclet;
import com.sun.tools.doclets.standard.Standard;

public class EquationDoclet extends Standard
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: EquationDoclet.java 11334 2011-07-01 10:09:11Z lima12 $";

	/**
	 * This is the start of the JavaDoc processing.<br>
	 * <br>
	 * This converts the RootDoc into an EquationRootDoc in order to return only <br>
	 * valid classes and methods for Equation Java Documentation. This uses the <br>
	 * default Java standard Doclet
	 * 
	 * @param rootDoc
	 *            - the root document
	 * 
	 * @return true or false as per HtmlDoclet.start()
	 */
	public static boolean start(RootDoc rootDoc)
	{
		System.out.println("");
		System.out.println("Equation Doclet");
		EquationRootDoc equationRootDoc = new EquationRootDoc(rootDoc);
		return HtmlDoclet.start(equationRootDoc);
	}

	/**
	 * This is a sample on how to run it via Java program
	 * 
	 * @param args
	 *            - not use
	 */
	public static void main(String[] args)
	{
		// This is how to pass argument
		String as[] = new String[3];
		as[0] = "-d";
		as[1] = "c:\\\\a\\\\htmlx";
		as[2] = "C:\\MYDATA\\workspace\\EquationDevelopmentHEAD\\EquationCommon\\src\\com\\misys\\equation\\common\\access\\EquationPVData.java";

		// This is how to run it using a specific doclet
		com.sun.tools.javadoc.Main.execute("JavaDoc", "com.misys.equation.builder.javadoc.EquationDoclet", as);
	}
}
