/**
 * 
 */
package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used for multi-language support and contains collections of Languages. Only use this class's getter methods as
 * other functionality is supplied by the TranslationToolbox class.
 * <p>
 * Text is segregated into different types: descriptions, labels, masks, regular expressions, and valid values. TextBeans are stored
 * in Languages and Languages are stored in Translations. Translations can have text related to multiple text owners as service and
 * layout text is stored in the same translation.
 * <p>
 * 
 * @see TextBean - for a description of TextBean attributes
 * @see Language - for a description of Language attributes * @see TranslationToolbox - for multi-language processes
 */
public class Translation
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Translation.java 13259 2012-04-20 09:15:28Z alex.lim $";

	private String ownerId;
	private final List<Language> languages = new ArrayList<Language>();
	/**
	 * This method is required for serialisation. Don't call it, use Translation(String ownerId) instead.
	 */
	public Translation()
	{
		ownerId = "";
	}
	/**
	 * Main constructor for Translation Bean
	 * 
	 * @param ownerId
	 *            the ownerId to set
	 */
	public Translation(String ownerId)
	{
		this.ownerId = ownerId;
	}
	/**
	 * @param ownerId
	 *            the ownerId to set
	 */
	public void setOwnerId(String ownerId)
	{
		this.ownerId = ownerId;
	}

	/**
	 * @return the ownerId
	 */
	public String getOwnerId()
	{
		return ownerId;
	}

	/**
	 * @return the languages
	 */
	public List<Language> getLanguages()
	{
		return languages;
	}

	public void addLanguage(Language language)
	{
		// Check if one with the same key is already in there
		if (!searchList(languages, language))
		{
			languages.add(language);
		}
		else
		// do nothing for now
		{

		}
	}

	public void removeLanguage(Language language)
	{
		int keyIndex = getIndex(languages, language);
		if (keyIndex > -1)
		{
			languages.remove(keyIndex);
		}
		else
		// do nothing for now
		{

		}
	}

	private boolean searchList(List<Language> list1, Language language1)
	{
		// This method is like a chain. Unique key of Language is just languageId
		Iterator<Language> iter = list1.iterator();

		while (iter.hasNext())
		{
			Language language2 = iter.next();
			if ((language2.getLanguageId()).equals(language1.getLanguageId()))
			{
				return true;
			}
		}
		return false;

	}

	private int getIndex(List<Language> list1, Language language1)
	{
		// This method is like a chain. Unique key of Language is just languageId
		Iterator<Language> iter = list1.iterator();

		while (iter.hasNext())
		{
			Language language2 = iter.next();
			if ((language2.getLanguageId()).equals(language1.getLanguageId()))
			{
				return list1.indexOf(language2);
			}
		}
		return -1; // not found

	}

	/**
	 * Returns the language
	 * 
	 * @param languageId
	 * @return Language OR NULL if language is not found
	 */
	public Language getLanguage(String languageId)
	{
		for (Language lang : getLanguages())
		{
			if (lang.getLanguageId().equalsIgnoreCase(languageId))
			{
				return lang;
			}

		}
		return null;
	}

	/**
	 * This method copies all properties of the translation bean to a new translation bean
	 * 
	 * @return the cloned Translation object
	 */
	public Translation cloneTranslation()
	{
		Translation translation = new Translation(this.getOwnerId());

		for (Language language : this.getLanguages())
		{
			translation.getLanguages().add(new Language(language, true));
		}
		return translation;
	}

}
