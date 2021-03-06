package com.misys.equation.ui.tools;

public class CategorySlot
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: CategorySlot.java 9962 2010-11-18 17:31:39Z MACDONP1 $";
	// The list of category
	private final String[] category;

	// If this is the last, then this contains the content of each category
	private final StringBuilder[] content;

	// If this is not the last, then this contains the list of sub-category
	private final CategorySlot[] subcategory;

	/**
	 * Construct a last category
	 * 
	 * @param categoryName
	 *            - the category name
	 */
	public CategorySlot(String[] category)
	{
		this.category = category;
		this.subcategory = null;

		// initialise the content
		this.content = new StringBuilder[category.length];
		for (int i = 0; i < category.length; i++)
		{
			this.content[i] = new StringBuilder();
		}
	}

	/**
	 * Return the list of categories
	 * 
	 * @return the list of categories
	 */
	public String[] getCategory()
	{
		return category;
	}

	/**
	 * Return the sub-category
	 * 
	 * @param i
	 *            - index
	 * 
	 * @return the sub-category
	 */
	public CategorySlot getSubcategory(int i)
	{
		return subcategory[i];
	}

	/**
	 * Return the content
	 * 
	 * @param i
	 *            - index
	 * 
	 * @return the sub-category
	 */
	public StringBuilder getContent(int i)
	{
		return content[i];
	}

	/**
	 * Return the content
	 * 
	 * @param i
	 *            - index
	 * 
	 * @return the sub-category
	 */
	public StringBuilder[] getContent()
	{
		return content;
	}

	/**
	 * Set the content
	 * 
	 * @param i
	 *            - index
	 * @param content
	 *            - content
	 * 
	 * @return the sub-category
	 * 
	 * @param true - successful update
	 */
	public boolean setContent(int i, String content)
	{
		this.content[i] = new StringBuilder(content);
		return true;
	}

	/**
	 * Set the content
	 * 
	 * @param i
	 *            - index
	 * @param content
	 *            - content
	 * 
	 * @return the sub-category
	 * 
	 * @param true - successful update
	 */
	public boolean appendContent(int i, String content)
	{
		this.content[i].append(content);
		return true;
	}

	// ------------------------------------------ 2 LEVELS: BEGIN

	/**
	 * Construct a category structure with 2 levels
	 * 
	 * @param category
	 *            - the main category
	 * @param subcategory
	 *            - the sub category of the main category
	 */
	public CategorySlot(String[] category, String[] subcategory)
	{
		this.category = category;
		this.content = null;

		// create the sub-category for each category
		this.subcategory = new CategorySlot[category.length];
		for (int i = 0; i < category.length; i++)
		{
			this.subcategory[i] = new CategorySlot(subcategory);
		}
	}

	/**
	 * Add a content to the specified category (2 levels)
	 * 
	 * @param categoryIndex
	 *            - the main category
	 * @param subcategoryIndex
	 *            - the sub category within the main category
	 * @param content
	 *            - the details to be added
	 * 
	 * @return true - successful update
	 */
	public boolean addContent(int categoryIndex, int subcategoryIndex, String content)
	{
		CategorySlot subcategory = this.subcategory[categoryIndex];
		subcategory.appendContent(subcategoryIndex, content);
		return true;
	}

	// ------------------------------------------ 2 LEVELS: END

}
