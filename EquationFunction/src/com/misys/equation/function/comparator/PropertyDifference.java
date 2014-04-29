package com.misys.equation.function.comparator;

import com.misys.equation.function.language.LanguageResources;

public class PropertyDifference
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: PropertyDifference.java 10940 2011-05-11 21:57:20Z perkinj1 $";

	/** Sub-element has been added */
	public static final int FLAG_INSERTED = 1;
	/** Sub-element has been deleted */
	public static final int FLAG_DELETED = 2;
	/** Property has changed */
	public static final int FLAG_CHANGED = 3;
	/** Sub-element position within the list has changed */
	public static final int FLAG_INDEX = 4;
	/** Any exception */
	public static final int FLAG_EXCEPTION = 5;

	int flag;
	String property;
	String value1;
	String value2;
	Object object1;
	Object object2;

	public PropertyDifference(String property, String value1, String value2, int flag)
	{
		this.property = property;
		this.value1 = value1;
		this.value2 = value2;
		this.flag = flag;
		this.object1 = null;
		this.object2 = null;
	}

	public Object getObject1()
	{
		return object1;
	}

	public void setObject1(Object object1)
	{
		this.object1 = object1;
	}

	public Object getObject2()
	{
		return object2;
	}

	public void setObject2(Object object2)
	{
		this.object2 = object2;
	}

	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	@Override
	public String toString()
	{
		return getText1();
	}

	/**
	 * Return the text for value 1
	 * 
	 * @return the text for value 1
	 */
	public String getText1()
	{
		StringBuilder builder = new StringBuilder();

		if (flag == FLAG_INSERTED)
		{
			builder.append(LanguageResources.getString("PropertyDifference.TextNew"));
		}
		else if (flag == FLAG_DELETED)
		{
			builder.append(LanguageResources.getString("PropertyDifference.TextDeleted"));
		}
		else if (flag == FLAG_CHANGED)
		{
			builder.append(property);
			builder.append(" ");
			builder.append(LanguageResources.getFormattedString("PropertyDifference.TextChange", new String[] { value2, value1 }));
		}
		else if (flag == FLAG_INDEX)
		{
			builder.append(LanguageResources.getFormattedString("PropertyDifference.TextIndex", new String[] { value2, value1 }));
		}
		else if (flag == FLAG_EXCEPTION)
		{
			builder.append(property);
			builder.append(" ");
			builder.append(LanguageResources.getString("PropertyDifference.TextException"));
		}

		return builder.toString();
	}
}