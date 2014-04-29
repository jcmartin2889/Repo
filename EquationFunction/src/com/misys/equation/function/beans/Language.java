/**
 * 
 */
package com.misys.equation.function.beans;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.misys.equation.function.tools.TranslationToolbox;

/**
 * This class is used for multi-language support and contains collections of TextBeans segregated by type. Only use this class's
 * getter methods as other functionality is supplied by the TranslationToolbox class.
 * <p>
 * Text is segregated into different types: descriptions, labels, masks, regular expressions, and valid values. TextBeans are stored
 * in Languages and Languages are stored in Translations. Translations can have text related to multiple text owners as service and
 * layout text is stored in the same translation.
 * <p>
 * 
 * @see TextBean - for a description of TextBean attributes
 * @see Translation - for a description of Translation attributes
 * @see TranslationToolbox - for multi-language processes
 */
public class Language
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: Language.java 13256 2012-04-20 09:13:32Z alex.lim $";

	private String languageId;

	private final List<TextBean> labels = new ArrayList<TextBean>();
	private final List<TextBean> descriptions = new ArrayList<TextBean>();
	private final List<TextBean> masks = new ArrayList<TextBean>();
	private final List<TextBean> regularExpressions = new ArrayList<TextBean>();
	private final List<TextBean> validValues = new ArrayList<TextBean>();
	/**
	 * This method is required for serialisation. Don't call it, use TranslationToolbox.createReferenceText(Translation translation,
	 * String baseLanguage, String owner, String type, String textString) instead.
	 */
	public Language()
	{
		languageId = ""; // empty represents base text
	}
	/**
	 * This method is required for the TranslationToolbox class. Don't call it, use
	 * TranslationToolbox.generateUpdateDeleteReferenceText(Translation translation, String languageId, String owner, String type,
	 * String textString, String key, boolean isCopyField) instead.
	 */
	public Language(String languageId)
	{
		this.languageId = languageId;
	}
	/**
	 * This method is required for the TranslationToolbox class. Don't call it, use
	 * TranslationToolbox.generateUpdateDeleteReferenceText(Translation translation, String languageId, String owner, String type,
	 * String textString, String key, boolean isCopyField) instead.
	 */
	public Language(Language language, boolean clone)
	{
		this.languageId = language.getLanguageId();

		if (clone)
		{
			for (TextBean textBean : language.getLabels())
			{
				labels.add(new TextBean(textBean));
			}
			for (TextBean bean : language.getDescriptions())
			{
				descriptions.add(new TextBean(bean));
			}
			for (TextBean bean : language.getMasks())
			{
				masks.add(new TextBean(bean));
			}
			for (TextBean bean : language.getRegularExpressions())
			{
				regularExpressions.add(new TextBean(bean));
			}
			for (TextBean bean : language.getValidValues())
			{
				validValues.add(new TextBean(bean));
			}
		}
		else
		{
			labels.addAll(language.getLabels());
			descriptions.addAll(language.getDescriptions());
			masks.addAll(language.getMasks());
			regularExpressions.addAll(language.getRegularExpressions());
			validValues.addAll(language.getValidValues());
		}
	}
	/**
	 * This method is required for the TranslationToolbox class. Don't call it, use
	 * TranslationToolbox.generateUpdateDeleteReferenceText(Translation translation, String languageId, String owner, String type,
	 * String textString, String key, boolean isCopyField) instead.
	 */
	public TextBean addText(String owner, String type, String key, String text)
	{

		TextBean textBean = new TextBean(languageId, owner, type, key, text);
		addText(textBean);

		return textBean;
	}
	/**
	 * This method is required for the TranslationToolbox class. Don't call it, use
	 * TranslationToolbox.generateUpdateDeleteReferenceText(Translation translation, String languageId, String owner, String type,
	 * String textString, String key, boolean isCopyField) instead.
	 */
	public TextBean addText(String owner, String type, String key, String text, boolean logicallyDeleted)
	{

		TextBean textBean = new TextBean(languageId, owner, type, key, text, logicallyDeleted);
		addText(textBean);

		return textBean;
	}
	/**
	 * This method is required for serialisation. Don't call it, use addText(String owner, String type, String key, String text)
	 * instead.
	 * 
	 * @param label
	 */
	public void addLabel(TextBean label)
	{
		// first check if it's the right type. If not, do nothing.
		if ((label.getType()).equals(TextBean.TYPE_LABEL))
		{
			// Now check if one with the same key is already in there
			int textIndex = getIndex(labels, label);

			if (textIndex == -1)
			{
				labels.add(label);
			}
			else
			// do nothing for now
			{
			}
		}
	}

	private void removeLabel(TextBean label)
	{
		// first check if it's the right type. If not, do nothing.
		if ((label.getType()).equals(TextBean.TYPE_LABEL))
		{
			int keyIndex = getIndex(labels, label);
			if (keyIndex > -1)
			{
				labels.remove(keyIndex);
			}
		}
	}
	/**
	 * This method is required for serialisation. Don't call it, use addText(String owner, String type, String key, String text)
	 * instead.
	 * 
	 * @param description
	 */
	public void addDescription(TextBean description)
	{
		// first check if it's the right type. If not, do nothing.
		if ((description.getType()).equals(TextBean.TYPE_DESCRIPTION))
		{
			int textIndex = getIndex(descriptions, description);

			if (textIndex == -1)
			{
				descriptions.add(description);
			}
			else
			// do nothing for now
			{
			}
		}
	}
	/**
	 * This method is required for the TranslationToolbox class. Don't call it, use
	 * TranslationToolbox.removeTextFromAllLanguages(TextBean textToDelete, Translation translation)instead.
	 * 
	 * @param textBean
	 *            - the text bean to add
	 */
	public void removeText(TextBean textBean)
	{
		if (textBean.getType().equals(TextBean.TYPE_LABEL))
		{
			removeLabel(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_DESCRIPTION))
		{
			removeDescription(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_VALID_VALUE))
		{
			removeValidValue(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_MASK))
		{
			removeMask(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			removeRegularExpression(textBean);
		}
	}
	private void removeDescription(TextBean description)
	{
		// first check if it's the right type. If not, do nothing.
		if ((description.getType()).equals(TextBean.TYPE_DESCRIPTION))
		{
			// check if it's there
			int keyIndex = getIndex(descriptions, description);
			if (keyIndex > -1)
			{
				descriptions.remove(keyIndex);
			}
		}
	}
	/**
	 * This method is required for serialisation. Don't call it, use addText(String owner, String type, String key, String text)
	 * instead.
	 * 
	 * @param mask
	 */
	public void addMask(TextBean mask)
	{
		// first check if it's the right type. If not, do nothing.
		if ((mask.getType()).equals(TextBean.TYPE_MASK))
		{
			int textIndex = getIndex(masks, mask);

			if (textIndex == -1)
			{
				masks.add(mask);
			}
			else
			// do nothing for now
			{
			}
		}
	}

	private void removeMask(TextBean mask)
	{
		// first check if it's the right type. If not, do nothing.
		if ((mask.getType()).equals(TextBean.TYPE_MASK))
		{
			// check if it's there
			int keyIndex = getIndex(masks, mask);
			if (keyIndex > -1)
			{
				masks.remove(keyIndex);
			}
		}
	}
	/**
	 * This method is required for serialisation. Don't call it, use addText(String owner, String type, String key, String text)
	 * instead.
	 * 
	 * @param regularExpression
	 */
	public void addRegularExpression(TextBean regularExpression)
	{
		// first check if it's the right type. If not, do nothing.
		if ((regularExpression.getType()).equals(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			int textIndex = getIndex(regularExpressions, regularExpression);

			if (textIndex == -1)
			{
				regularExpressions.add(regularExpression);
			}
			else
			// do nothing for now
			{
			}
		}
	}

	private void removeRegularExpression(TextBean regularExpression)
	{
		// first check if it's the right type. If not, do nothing.
		if ((regularExpression.getType()).equals(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			// check if it's there
			int keyIndex = getIndex(regularExpressions, regularExpression);
			if (keyIndex > -1)
			{
				regularExpressions.remove(keyIndex);
			}
		}
	}
	/**
	 * This method is required for serialisation. Don't call it, use addText(String owner, String type, String key, String text)
	 * instead.
	 * 
	 * @param validValue
	 */
	public void addValidValue(TextBean validValue)
	{
		// first check if it's the right type. If not, do nothing.
		if ((validValue.getType()).equals(TextBean.TYPE_VALID_VALUE))
		{
			int textIndex = getIndex(validValues, validValue);

			if (textIndex == -1)
			{
				validValues.add(validValue);
			}
			else
			// do nothing for now
			{
			}
		}
	}

	private void removeValidValue(TextBean validValue)
	{
		// first check if it's the right type. If not, do nothing.
		if ((validValue.getType()).equals(TextBean.TYPE_VALID_VALUE))
		{
			// check if it's there
			int keyIndex = getIndex(validValues, validValue);
			if (keyIndex > -1)
			{
				validValues.remove(keyIndex);
			}
		}
	}

	public int getIndex(List<TextBean> list1, TextBean text1)
	{
		// This method is like a chain. Unique key of Text is (owner + language + type + key)
		Iterator<TextBean> iter = list1.iterator();

		while (iter.hasNext())
		{
			TextBean text2 = iter.next();
			if ((text2.getOwner()).equals(text1.getOwner()) && (text2.getLanguage()).equals(text1.getLanguage())
							&& (text2.getType()).equals(text1.getType()) && (text2.getKey()).equals(text1.getKey()))
			{
				return list1.indexOf(text2);
			}
		}
		return -1; // not found

	}
	/**
	 * This method is required for the TranslationToolbox class. Don't call it, use
	 * TranslationToolbox.generateUpdateDeleteReferenceText(Translation translation, String languageId, String owner, String type,
	 * String textString, String key, boolean isCopyField) instead.
	 */
	public void addText(TextBean textBean)
	{
		if (textBean.getType().equals(TextBean.TYPE_LABEL))
		{
			addLabel(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_DESCRIPTION))
		{
			addDescription(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_VALID_VALUE))
		{
			addValidValue(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_MASK))
		{
			addMask(textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			addRegularExpression(textBean);
		}
	}
	/**
	 * Clears text beans list
	 * 
	 * @param type
	 *            - the type of text bean
	 */
	public void clearText(String type)
	{
		if (type.equals(TextBean.TYPE_LABEL))
		{
			getLabels().clear();
		}
		else if (type.equals(TextBean.TYPE_DESCRIPTION))
		{
			getDescriptions().clear();
		}
		else if (type.equals(TextBean.TYPE_VALID_VALUE))
		{
			getValidValues().clear();
		}
		else if (type.equals(TextBean.TYPE_MASK))
		{
			getMasks().clear();
		}
		else if (type.equals(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			getRegularExpressions().clear();
		}
	}

	/**
	 * @return the textBeans
	 */
	public List<TextBean> getLabels()
	{
		return labels;
	}

	/**
	 * This method is required for serialisation. Don't call it, use
	 * TranslationToolbox.generateUpdateDeleteReferenceText(Translation translation, String languageId, String owner, String type,
	 * String textString, String key, boolean isCopyField) instead.
	 */
	public void setLanguageId(String languageId)
	{
		this.languageId = languageId;
	}

	/**
	 * @return the languageId
	 */
	public String getLanguageId()
	{
		return languageId;
	}

	/**
	 * @return the descriptions
	 */
	public List<TextBean> getDescriptions()
	{
		return descriptions;
	}

	/**
	 * @return the masks
	 */
	public List<TextBean> getMasks()
	{
		return masks;
	}

	/**
	 * @return the regularExpressions
	 */
	public List<TextBean> getRegularExpressions()
	{
		return regularExpressions;
	}

	/**
	 * @return the validValues
	 */
	public List<TextBean> getValidValues()
	{
		return validValues;
	}

	public List<TextBean> getType(String type)
	{
		if (type.equals(TextBean.TYPE_LABEL))
		{
			return getLabels();
		}
		else if (type.equals(TextBean.TYPE_DESCRIPTION))
		{
			return getDescriptions();
		}
		else if (type.equals(TextBean.TYPE_MASK))
		{
			return getMasks();
		}
		else if (type.equals(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			return getRegularExpressions();
		}
		else if (type.equals(TextBean.TYPE_VALID_VALUE))
		{
			return getValidValues();
		}

		return null;
	}

}
