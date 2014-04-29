package com.misys.equation.function.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.misys.equation.common.access.EquationStandardSession;
import com.misys.equation.common.dao.beans.HBXRecordDataModel;
import com.misys.equation.common.internal.eapi.core.EQException;
import com.misys.equation.function.beans.DisplayAttributes;
import com.misys.equation.function.beans.DisplayAttributesSet;
import com.misys.equation.function.beans.DisplayButtonLink;
import com.misys.equation.function.beans.DisplayGroup;
import com.misys.equation.function.beans.DisplayItemList;
import com.misys.equation.function.beans.DisplayLabel;
import com.misys.equation.function.beans.Element;
import com.misys.equation.function.beans.Function;
import com.misys.equation.function.beans.IDisplayItem;
import com.misys.equation.function.beans.InputField;
import com.misys.equation.function.beans.InputFieldSet;
import com.misys.equation.function.beans.InputGroup;
import com.misys.equation.function.beans.Language;
import com.misys.equation.function.beans.Layout;
import com.misys.equation.function.beans.TextBean;
import com.misys.equation.function.beans.Translation;
import com.misys.equation.function.beans.WorkField;
import com.misys.equation.function.beans.valid.FunctionProblemLocation;
import com.misys.equation.function.beans.valid.MessageContainer;
import com.misys.equation.function.language.LanguageResources;

/**
 * This class is used for multi-language support and contains helper methods for managing text in the translation and language
 * classes. Helper methods for processing text in Functions and Layouts are included.
 * <p>
 * Text is segregated into different types in a : descriptions, labels, masks, regular expressions, and valid values. TextBeans are
 * stored in type collections with Languages and Languages are stored in Translations.
 * 
 * Translations can have text related to multiple text owners as service and layout text is stored in the same translation. To
 * distinguish between services and layouts the suffix is appended to the id (e.g. xxx.eqf and xxx.eql). Translations as a whole
 * have a main owner and for services and layouts this is the service id + suffix (e.g. xxx.eqf). There are two types of reusable
 * translation - misysText.eqt and bankText.eqt.
 * 
 * This class contains a cache of used reference text keys for a translation and type. This is used to prevent conflicts in number
 * allocation when translations are being created by many editors.
 * <p>
 * 
 * @see TextBean - for a description of TextBean attributes
 * @see Language - for a description of Language attributes
 * @see Translation - for a description of Translation attributes
 */
public class TranslationToolbox
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: TranslationToolbox.java 14804 2012-11-05 11:57:35Z williae1 $";

	/** Instance of this class **/
	private static TranslationToolbox translationToolbox;

	/** Cache of the translation last used reference keys/number */
	private Hashtable<String, Long> translationIdCache = new Hashtable<String, Long>();
	public static final long MISYS_FIRST_REFERENCE_TEXT = 1;
	public static final long BANK_FIRST_REFERENCE_TEXT = 1000000001;
	public static final String MISYS_PREFIX_REFERENCE_TEXT = "0";
	public static final String BANK_PREFIX_REFERENCE_TEXT = "1";
	/*
	 * Constructor
	 */
	private TranslationToolbox()
	{
	}

	/*
	 * Get the singleton TranslationToolbox
	 */
	private static TranslationToolbox getTranslationToolbox()
	{
		synchronized (TranslationToolbox.class)
		{
			if (translationToolbox == null)
			{
				translationToolbox = new TranslationToolbox();

			}
		}
		return translationToolbox;
	}

	/**
	 * Returns the next translation referenceText id for the owner and type. Cache of last used key is updated.
	 * 
	 * @param translation
	 * @param languageId
	 * @param type
	 * @param owner
	 * @return reference text id
	 * @throws EQException
	 */
	private synchronized long getNextReferenceTextId(Translation translation, String languageId, String type, String owner,
					boolean isMisysMode) throws EQException
	{
		String prefix = BANK_PREFIX_REFERENCE_TEXT;
		long first = BANK_FIRST_REFERENCE_TEXT;
		if (isMisysMode)
		{
			prefix = MISYS_PREFIX_REFERENCE_TEXT;
			first = MISYS_FIRST_REFERENCE_TEXT;
		}

		Long nextTranslationId = null;

		String translationId = translation.getOwnerId() + ":" + type;

		nextTranslationId = translationIdCache.get(translationId);

		if (nextTranslationId == null)
		{
			// If the language is not found, this is the first entry, return 1
			Language language = translation.getLanguage(languageId);

			if (language == null)
			{
				translationIdCache.put(translationId, first);
				return first;
			}

			// If the type is not found, this is the first entry, return 1
			List<TextBean> textBeanList = language.getType(type);
			if (textBeanList == null || textBeanList.isEmpty())
			{
				translationIdCache.put(translationId, first);
				return first;
			}

			try
			{
				long highestNumber = 1000000000;
				if (isMisysMode)
				{
					highestNumber = 0;
				}
				long currentNumber = 0;
				for (TextBean textBean : textBeanList)
				{
					String key = textBean.getKey();
					currentNumber = Long.parseLong(key);
					if (key.startsWith(prefix) && currentNumber > highestNumber)
					{
						highestNumber = currentNumber;
					}
				}
				// TODO This only works if the translation file keys are sorted
				// nextTranslationId = Long.parseLong(textBeanList.get(textBeanList.size() - 1).getKey());
				nextTranslationId = highestNumber;

			}
			catch (NumberFormatException e)
			{
				throw new EQException(e);
			}

		}

		nextTranslationId++;

		// Double check this key has not been used
		boolean found = true;
		while (found == true)
		{
			String referenceIDCounter = String.format("%010d", nextTranslationId);
			TextBean textBean = findReferenceText(translation, languageId, type, referenceIDCounter);
			if (textBean == null)
			{
				found = false;
			}
			else
			{
				nextTranslationId++;
			}
		}
		translationIdCache.put(translationId, nextTranslationId);
		return nextTranslationId;
	}
	/**
	 * Removes from the cache of last used referenceTexts those for the specified owner
	 * 
	 * @param owner
	 */
	public static void removeTranslationFromCache(String owner)
	{
		owner = owner + ":";
		List<String> keys = new ArrayList<String>(10);

		for (String key : TranslationToolbox.getTranslationToolbox().translationIdCache.keySet())
		{
			if (key.startsWith(owner))
			{
				keys.add(key);
			}
		}

		for (String key : keys)
		{
			TranslationToolbox.getTranslationToolbox().translationIdCache.remove(key);
		}

		keys.clear();
	}
	/**
	 * Merges the changes between translation instances. At the end both translations will be identical. Changes are added to the
	 * base and where the same keys are in both, translation with change will prevail.
	 * 
	 * @param baseTranslation
	 * @param translationWithChanges
	 * @param changeTextOwner
	 */
	public static synchronized void reconcileChanges(Translation baseTranslation, Translation translationWithChanges,
					String changeTextOwner)
	{
		// Update the translation with changes from the base translation.
		// Ensure that reference ids are added sequentially for each language from the new translation
		for (Language languageWithChanges : translationWithChanges.getLanguages())
		{
			// check if the language exists in the old translation
			Language languageFromBase = baseTranslation.getLanguage(languageWithChanges.getLanguageId());

			// if not found, then user added a new language, add this one to the old translation
			if (languageFromBase == null)
			{
				baseTranslation.addLanguage(languageWithChanges);
			}
			else
			{
				// if found, then we have to insert new the changes
				replaceTextForOwner(languageFromBase, changeTextOwner, languageWithChanges.getLabels(), TextBean.TYPE_LABEL);
				replaceTextForOwner(languageFromBase, changeTextOwner, languageWithChanges.getDescriptions(),
								TextBean.TYPE_DESCRIPTION);
				replaceTextForOwner(languageFromBase, changeTextOwner, languageWithChanges.getMasks(), TextBean.TYPE_MASK);
				replaceTextForOwner(languageFromBase, changeTextOwner, languageWithChanges.getRegularExpressions(),
								TextBean.TYPE_REGULAR_EXPRESSION);
				replaceTextForOwner(languageFromBase, changeTextOwner, languageWithChanges.getValidValues(),
								TextBean.TYPE_VALID_VALUE);
			}

		}

		translationWithChanges.getLanguages().clear();
		translationWithChanges.getLanguages().addAll(baseTranslation.getLanguages());

	}
	/**
	 * Removes the text from language when owner does not match and type does match
	 * 
	 * @param language
	 * @param owner
	 * @param type
	 */
	private static void removeTextForAllOtherOwners(Language language, String owner, String type)
	{
		for (int index = 0; index < language.getType(type).size(); index++)
		{
			TextBean bean = language.getType(type).get(index);

			// if owned by owner, then remove
			if (!bean.getOwner().equalsIgnoreCase(owner))
			{
				language.removeText(bean);
				index--;

			}
		}
	}
	/**
	 * Replaces text in a translation
	 * 
	 * @param translation
	 * @param textBean
	 */
	public static void replaceText(Translation translation, TextBean textBean)
	{
		Language language = translation.getLanguage(textBean.getLanguage());
		List<TextBean> textBeans = language.getType(textBean.getType());
		int textIndex = language.getIndex(textBeans, textBean);
		replaceText(language, textBean, textIndex);
	}

	/**
	 * Replaces the text for an owner in a language from a collection to texts with change
	 * 
	 * @param baseLanguage
	 * @param changeTextOwner
	 * @param beansWithNewChanges
	 * @param type
	 */
	private static void replaceTextForOwner(Language baseLanguage, String changeTextOwner, List<TextBean> beansWithNewChanges,
					String type)

	{

		// Get the text from the language
		List<TextBean> baseTextBeans = baseLanguage.getType(type);

		// Sort text according to the key value. Add to the sorted text first the base and then the changes - but only changes for
		// the specified owner.
		Map<String, TextBean> sortedMap = new TreeMap<String, TextBean>();
		for (TextBean textBean : baseTextBeans)
		{
			// Addition of base text must be unconditioned.
			// Synchronisation of layout with service can add and delete layout text.
			// When synchronisation happens, layout text is only added in the service translation instance.
			// When base translation is from the service we need to add both service and layout text here.

			// if (textBean.getOwner().equals(baseTextOwner))
			// {
			sortedMap.put(textBean.getKey(), textBean);
			// }
		}

		for (TextBean textBean : beansWithNewChanges)
		{
			if (textBean.getOwner().equals(changeTextOwner))
			{
				sortedMap.put(textBean.getKey(), textBean);
			}
		}
		// Update the base language with the sorted map. First clear the language and then add the text.
		if (!sortedMap.values().isEmpty())
		{
			baseLanguage.clearText(type);
		}
		for (TextBean bean : sortedMap.values())
		{

			baseLanguage.addText(bean);

		}

	}
	/**
	 * Sort the text by key
	 * 
	 * @param language
	 * @param type
	 */
	private static void sortText(Language language, String type)
	{
		// Get the text from the language
		List<TextBean> textBeans = language.getType(type);
		// Sort text according to the key value. Add to the sorted text
		Map<String, TextBean> sortedMap = new TreeMap<String, TextBean>();
		for (TextBean textBean : textBeans)
		{
			sortedMap.put(textBean.getKey(), textBean);
		}
		// Update the base language with the sorted map. First clear the language and then add the text.
		if (!sortedMap.values().isEmpty())
		{
			language.clearText(type);
		}
		for (TextBean bean : sortedMap.values())
		{
			language.addText(bean);
		}
	}
	/**
	 * Removes text from the translation for all owners that are not the translation main owner. This method is used to remove
	 * layout translations from the translation containing service and layout text.
	 * 
	 * @param translation
	 */
	public static void removeTextForAllOtherOwners(Translation translation)
	{
		for (Language language : translation.getLanguages())
		{
			removeTextForAllOtherOwners(language, translation.getOwnerId(), TextBean.TYPE_LABEL);
			removeTextForAllOtherOwners(language, translation.getOwnerId(), TextBean.TYPE_DESCRIPTION);
			removeTextForAllOtherOwners(language, translation.getOwnerId(), TextBean.TYPE_MASK);
			removeTextForAllOtherOwners(language, translation.getOwnerId(), TextBean.TYPE_REGULAR_EXPRESSION);
			removeTextForAllOtherOwners(language, translation.getOwnerId(), TextBean.TYPE_VALID_VALUE);
		}

	}
	/**
	 * Returns whether the text for type and key exists
	 * 
	 * @param translation
	 * @param type
	 * @param key
	 * @return true if the text for type and key exists
	 */
	public static boolean isReferenceTextKeyUsed(Translation translation, String type, String key)
	{
		for (Language language : translation.getLanguages())
		{
			Iterator<TextBean> iter = language.getType(type).iterator();
			while (iter.hasNext())
			{
				TextBean textBean = iter.next();
				if ((textBean.getKey()).equals(key))
				{
					return true;
				}
			}

		}
		return false;
	}
	/**
	 * Returns the text from the translation based on the language, owner, type and key
	 * 
	 * @param translation
	 * @param languageId
	 * @param owner
	 * @param type
	 * @param key
	 * @return TextBean
	 */
	public static TextBean findReferenceText(Translation translation, String languageId, String owner, String type, String key)
	{
		Language language = translation.getLanguage(languageId);
		if (language != null)
		{
			// This method is like a chain. Unique key of text is (owner + language + type + key)
			Iterator<TextBean> iter = language.getType(type).iterator();

			while (iter.hasNext())
			{
				TextBean textBean = iter.next();
				if ((textBean.getOwner()).equals(owner) && (textBean.getLanguage()).equals(languageId)
								&& (textBean.getKey()).equals(key))
				{
					return textBean;
				}
			}
			return null;

		}

		return null;
	}
	/**
	 * Returns the text from the translation based on the language, type and key
	 * 
	 * @param translation
	 * @param languageId
	 * @param type
	 * @param key
	 * @return TextBean
	 */
	private static TextBean findReferenceText(Translation translation, String languageId, String type, String key)
	{
		Language language = translation.getLanguage(languageId);
		if (language != null)
		{
			// Unique key of text is (owner + language + type + key)
			Iterator<TextBean> iter = language.getType(type).iterator();

			while (iter.hasNext())
			{
				TextBean textBean = iter.next();
				if ((textBean.getLanguage()).equals(languageId) && (textBean.getKey()).equals(key))
				{
					return textBean;
				}
			}
			return null;

		}

		return null;
	}
	/**
	 * Copies referenceText between translations and changes the owner
	 * 
	 * @param sourceTranslation
	 * @param languageId
	 * @param sourceOwner
	 * @param type
	 * @param sourceKey
	 * @param targetTranslation
	 * @param targetOwner
	 * @return referenceText id
	 */
	public static String copyReferenceText(Translation sourceTranslation, String languageId, String sourceOwner, String type,
					String sourceKey, Translation targetTranslation, String targetOwner, boolean isMisysMode)

	{
		TextBean textBean = TranslationToolbox.findReferenceText(sourceTranslation, languageId, sourceOwner, type, sourceKey);
		// If text is found
		if (textBean != null && textBean.getText() != null)
		{
			String referenceIDCounter = generateUpdateDeleteReferenceText(targetTranslation, languageId, targetOwner, type,
							textBean.getText(), sourceKey, true, isMisysMode);
			// Find the text in any other language and copy them as well
			Language sourceLanguage = null;

			String sourceLanguageId = null;
			for (int x = 0; x < sourceTranslation.getLanguages().size(); x++)
			{
				sourceLanguage = sourceTranslation.getLanguages().get(x);
				sourceLanguageId = sourceLanguage.getLanguageId();
				if (!sourceLanguageId.equals(languageId))
				{
					Language targetLanguage = targetTranslation.getLanguage(sourceLanguageId);
					if (targetLanguage == null)
					{

						targetLanguage = new Language();
						targetLanguage.setLanguageId(sourceLanguageId);
						targetTranslation.addLanguage(targetLanguage);
					}

					TextBean associatedTextBean = TranslationToolbox.findReferenceText(sourceTranslation, sourceLanguageId,
									sourceOwner, type, sourceKey);
					if (associatedTextBean != null)
					{
						targetLanguage.addText(targetOwner, type, referenceIDCounter, associatedTextBean.getText());
					}
				}
			}
			return referenceIDCounter;
		}
		else
		{
			return null;
		}
	}
	/**
	 * Adds text to the translation where the key is known. If the key exists, the current instance with the key is replaced.
	 * 
	 * This method should NOT be used for services, layouts or prompt validate reference text where the key is calculated and the
	 * highest key must be last in the language collections of text.
	 * 
	 * @param translation
	 * @param textBean
	 */
	public static void addText(Translation translation, TextBean textBean)
	{

		Language language = translation.getLanguage(textBean.getLanguage());

		if (language == null)
		{
			language = new Language();

			language.setLanguageId(textBean.getLanguage());
			language.addText(textBean);
			translation.addLanguage(language);
		}
		else
		{
			// check first if text already exists in language
			List<TextBean> translatedTextList = language.getType(textBean.getType());

			int textIndex = language.getIndex(translatedTextList, textBean);

			if (textIndex == -1)
			{
				language.addText(textBean);
			}
			else
			{
				replaceText(language, textBean, textIndex);
			}
		}
	}
	/**
	 * Replaces text in language
	 * 
	 * @param language
	 * @param textBean
	 * @param index
	 */
	private static void replaceText(Language language, TextBean textBean, int index)
	{

		if (textBean.getType().equals(TextBean.TYPE_LABEL))
		{
			language.getLabels().set(index, textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_DESCRIPTION))
		{
			language.getDescriptions().set(index, textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_MASK))
		{
			language.getMasks().set(index, textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			language.getRegularExpressions().set(index, textBean);
		}
		else if (textBean.getType().equals(TextBean.TYPE_VALID_VALUE))
		{
			language.getValidValues().set(index, textBean);
		}
	}
	/**
	 * Generates, updates or deletes reference text. If the key exists, text will be updated. If the text has been changed to be
	 * empty, text will be deleted. New languages may be made if they do not already exist.
	 * 
	 * @param translation
	 * @param languageId
	 * @param owner
	 * @param type
	 * @param textString
	 * @param key
	 * @param isCopyField
	 * @return referenceText id
	 */
	public static String generateUpdateDeleteReferenceText(Translation translation, String languageId, String owner, String type,
					String textString, String key, boolean isCopyField, boolean isMisysMode)
	{
		Language language = translation.getLanguage(languageId);

		// if language is not on the list, add the new language
		if (language == null)
		{
			language = new Language(languageId);
			translation.addLanguage(language);
		}

		// find the text if it already exists
		String referenceIDCounter = "";

		if (!isCopyField)
		{
			TextBean textBean = findReferenceText(translation, languageId, owner, type, key);
			if (textBean != null)
			{
				if (!textString.trim().equalsIgnoreCase("") && !textString.trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
				{
					textBean.setText(textString);
					referenceIDCounter = textBean.getKey();

				}
				else
				{
					// Remove text bean when its been changed from some text to no text
					TranslationToolbox.removeText(textBean, translation);

				}
				return referenceIDCounter;
			}
		}

		// Create new text bean if there is text
		if (!textString.trim().equalsIgnoreCase("") && !textString.trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
		{
			// Make sure key is not used regardless of owner
			TextBean textBean = findReferenceText(translation, languageId, type, key);
			// Use the original key
			if (textBean == null && (key != null && key.length() > 0))
			{
				referenceIDCounter = key;
				// Create Text Bean and add to language
				language.addText(owner, type, referenceIDCounter, textString);
				// ensure largest key is last
				sortText(language, type);
				// remove text key from cache so that it will be recalculated next time
				TranslationToolbox.removeTranslationFromCache(owner);
			}
			else
			{
				// get the latest key and increment by 1
				referenceIDCounter = "";

				try
				{
					referenceIDCounter = String.format("%010d", TranslationToolbox.getTranslationToolbox().getNextReferenceTextId(
									translation, language.getLanguageId(), type, translation.getOwnerId(), isMisysMode));
				}
				catch (Exception e)
				{
					String prefix = BANK_PREFIX_REFERENCE_TEXT;
					if (isMisysMode)
					{
						prefix = MISYS_PREFIX_REFERENCE_TEXT;
					}
					referenceIDCounter = prefix + "000000001";
				}
				// Create Text Bean and add to language
				language.addText(owner, type, referenceIDCounter, textString);
			}

		}

		return referenceIDCounter;
	}
	/**
	 * Changes reference text owner
	 * 
	 * @param translation
	 * @param newOwner
	 *            - The new text owner
	 * @param oldOwner
	 *            - The old text owner
	 */
	public static void changeTextOwner(Translation translation, String newOwner, String oldOwner)
	{
		for (Language language : translation.getLanguages())
		{
			TranslationToolbox.changeTextOwner(language, newOwner, oldOwner);
		}
	}
	/**
	 * Changes reference text owner
	 * 
	 * @param language
	 * @param newOwner
	 *            - The new text owner
	 * @param oldOwner
	 *            - The old text owner
	 */
	private static void changeTextOwner(Language language, String newOwner, String oldOwner)
	{
		for (TextBean bean : language.getLabels())
		{
			if (bean.getOwner().equalsIgnoreCase(oldOwner))
			{
				bean.setOwner(newOwner);
			}
		}

		for (TextBean bean : language.getDescriptions())
		{
			if (bean.getOwner().equalsIgnoreCase(oldOwner))
			{
				bean.setOwner(newOwner);
			}
		}

		for (TextBean bean : language.getValidValues())
		{
			if (bean.getOwner().equalsIgnoreCase(oldOwner))
			{
				bean.setOwner(newOwner);
			}
		}

		for (TextBean bean : language.getMasks())
		{
			if (bean.getOwner().equalsIgnoreCase(oldOwner))
			{
				bean.setOwner(newOwner);
			}
		}

		for (TextBean bean : language.getRegularExpressions())
		{
			if (bean.getOwner().equalsIgnoreCase(oldOwner))
			{
				bean.setOwner(newOwner);
			}
		}
	}
	/**
	 * Removes the references text used by an input field set
	 * 
	 * @param inputFieldSet
	 * @param languageId
	 * @param owner
	 * @param translation
	 */
	public static void removeText(InputFieldSet inputFieldSet, String languageId, String owner, Translation translation)
	{

		for (int x = 0; x < inputFieldSet.getInputFields().size(); x++)
		{
			InputField inputField = inputFieldSet.getInputFields().get(x);
			TranslationToolbox.removeText(inputField, languageId, owner, translation);
		}
		removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, inputFieldSet.getDescription(), translation);
	}
	/**
	 * Removes the references text used by display items
	 * 
	 * @param items
	 * @param languageId
	 * @param owner
	 * @param serviceTranslation
	 * @param layoutTranslation
	 */
	public static void removeText(DisplayItemList items, String languageId, String owner, Translation serviceTranslation,
					Translation layoutTranslation)
	{
		for (IDisplayItem item : items)
		{
			if (item instanceof DisplayAttributes)
			{

				if (((DisplayAttributes) item).getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_LABEL, ((DisplayAttributes) item).getLabel(),
									serviceTranslation);
					if (serviceTranslation != layoutTranslation)
					{
						TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_LABEL,
										((DisplayAttributes) item).getLabel(), layoutTranslation);
					}
				}
				if (((DisplayAttributes) item).getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, ((DisplayAttributes) item)
									.getDescription(), serviceTranslation);
					if (serviceTranslation != layoutTranslation)
					{
						TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, ((DisplayAttributes) item)
										.getDescription(), layoutTranslation);
					}
				}
			}
			else if (item instanceof DisplayGroup)
			{
				if (((DisplayGroup) item).getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_LABEL, ((DisplayGroup) item).getLabel(),
									serviceTranslation);
					if (serviceTranslation != layoutTranslation)
					{
						TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_LABEL, ((DisplayGroup) item).getLabel(),
										layoutTranslation);
					}
				}
				if (((DisplayGroup) item).getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, ((DisplayGroup) item)
									.getDescription(), serviceTranslation);
					if (serviceTranslation != layoutTranslation)
					{
						TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, ((DisplayGroup) item)
										.getDescription(), layoutTranslation);
					}
				}
				// Actually recurse
				removeText(((DisplayGroup) item).getDisplayItems(), languageId, owner, serviceTranslation, layoutTranslation);

			}

		}
	}
	/**
	 * Adds text back to the translation from previously deleted text.
	 * 
	 * @param translation
	 * @param languageId
	 * @param owner
	 * @param displayItem
	 * @param textBeansRemoved
	 */
	public static void addText(Translation translation, String languageId, String owner, IDisplayItem displayItem,
					Map<String, TextBean> textBeansRemoved, boolean isMisysMode)
	{
		if (displayItem instanceof DisplayAttributes || displayItem instanceof DisplayGroup)
		{
			Element element = (Element) displayItem;
			TextBean textLAB = textBeansRemoved.get(element.getId() + TextBean.TYPE_LABEL);
			if (textLAB != null)
			{
				generateUpdateDeleteReferenceText(translation, languageId, owner, TextBean.TYPE_LABEL, textLAB.getText(), textLAB
								.getKey(), false, isMisysMode);

			}
			TextBean textDSC = textBeansRemoved.get(element.getId() + TextBean.TYPE_DESCRIPTION);
			if (textDSC != null)
			{
				generateUpdateDeleteReferenceText(translation, languageId, owner, TextBean.TYPE_DESCRIPTION, textDSC.getText(),
								textDSC.getKey(), false, isMisysMode);
			}
		}
		// Check the mask if this is a DisplayAttribute element
		if (displayItem instanceof DisplayAttributes)
		{
			DisplayAttributes displayAttribute = (DisplayAttributes) displayItem;
			TextBean textMSK = textBeansRemoved.get(displayAttribute.getId() + TextBean.TYPE_MASK);

			if (textMSK != null)
			{
				generateUpdateDeleteReferenceText(translation, languageId, owner, TextBean.TYPE_MASK, textMSK.getText(), textMSK
								.getKey(), false, isMisysMode);
			}
		}

	}
	/**
	 * Removes the references text for an element and keeps track of text beans that are deleted.
	 * 
	 * @param element
	 * @param languageId
	 * @param owner
	 * @param serviceTranslation
	 * @param layoutTranslation
	 * @param textBeansRemoved
	 */
	public static void removeText(Element element, String languageId, String owner, Translation serviceTranslation,
					Translation layoutTranslation, Map<String, TextBean> textBeansRemoved)
	{
		TextBean textLAB = findReferenceText(layoutTranslation, languageId, TextBean.TYPE_LABEL, element.getLabel());
		if (textLAB != null)
		{
			textBeansRemoved.put(element.getId() + TextBean.TYPE_LABEL, textLAB);
		}
		TextBean textDSC = findReferenceText(layoutTranslation, languageId, TextBean.TYPE_DESCRIPTION, element.getDescription());
		if (textDSC != null)
		{
			textBeansRemoved.put(element.getId() + TextBean.TYPE_DESCRIPTION, textDSC);
		}
		// Check the mask if this is a DisplayAttribute element
		if (element instanceof DisplayAttributes)
		{
			DisplayAttributes displayAttribute = (DisplayAttributes) element;
			TextBean textMSK = findReferenceText(layoutTranslation, languageId, TextBean.TYPE_MASK, displayAttribute.getMask());

			if (textMSK != null)
			{
				textBeansRemoved.put(element.getId() + TextBean.TYPE_MASK, textMSK);
			}
		}
		removeText(element, languageId, owner, serviceTranslation, layoutTranslation);
	}
	/**
	 * Removes the references text used by display items
	 * 
	 * @param element
	 * @param languageId
	 * @param owner
	 * @param serviceTranslation
	 * @param layoutTranslation
	 */
	public static void removeText(Element element, String languageId, String owner, Translation serviceTranslation,
					Translation layoutTranslation)
	{

		if (element.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_LABEL, element.getLabel(), serviceTranslation);
			if (serviceTranslation != layoutTranslation)
			{
				TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_LABEL, element.getLabel(), layoutTranslation);
			}
		}
		if (element.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, element.getDescription(),
							serviceTranslation);
			if (serviceTranslation != layoutTranslation)
			{
				TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, element.getDescription(),
								layoutTranslation);
			}
		}
		// Check the mask if this is a DisplayAttribute element
		if (element instanceof DisplayAttributes)
		{
			DisplayAttributes displayAttribute = (DisplayAttributes) element;

			// Check the mask
			if (displayAttribute.getMaskType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				TranslationToolbox.removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, displayAttribute.getMask(),
								layoutTranslation);
			}
		}
	}
	/**
	 * Removes the references text used in an input group
	 * 
	 * @param group
	 * @param set
	 * @param languageId
	 * @param owner
	 * @param translation
	 * @param removeInputAssociations
	 */
	public static void removeText(InputGroup group, InputFieldSet set, String languageId, String owner, Translation translation,
					boolean removeInputAssociations)
	{

		if (removeInputAssociations)
		{
			List<InputField> removes = set.getInputGroupFields(group.getId());
			for (InputField exField : removes)
			{
				removeText(exField, languageId, owner, translation);
			}
		}

		// remove input group
		removeText(group, languageId, owner, translation);

	}
	/**
	 * Removes the references text used by an input field
	 * 
	 * @param inputField
	 * @param languageId
	 * @param owner
	 * @param translation
	 */
	public static void removeText(InputField inputField, String languageId, String owner, Translation translation)
	{
		if (inputField.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, inputField.getDescription(), translation);

		}
		if (inputField.getRegExpType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			removeText(languageId, owner, TextBean.TYPE_REGULAR_EXPRESSION, inputField.getRegExp(), translation);

		}
		if (inputField.getValidValuesType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			removeText(languageId, owner, TextBean.TYPE_VALID_VALUE, inputField.getValidValues(), translation);

		}
	}
	/**
	 * Removes the references text used by a work field
	 * 
	 * @param element
	 * @param languageId
	 * @param owner
	 * @param translation
	 */
	public static void removeText(Element element, String languageId, String owner, Translation translation)
	{
		if (element.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			removeText(languageId, owner, TextBean.TYPE_DESCRIPTION, element.getDescription(), translation);

		}

	}
	/**
	 * Validate reference text for function is used
	 * 
	 * @param translation
	 * @param function
	 * @param messageContainer
	 */
	public static void validateReferenceText(Translation translation, Function function, MessageContainer messageContainer)

	{
		String baseLanguage = function.getBaseLanguage();
		if (baseLanguage != null && baseLanguage.length() > 0)
		{
			Language language = translation.getLanguage(baseLanguage);
			if (language != null)
			{
				for (TextBean textBean : language.getLabels())
				{
					if (textBean.getOwner().equals(function.rtvTextOwner()))
					{
						if (!isFunctionReferenceTextUsed(textBean.getKey(), function))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(function));
						}

					}
				}
				for (TextBean textBean : language.getDescriptions())
				{
					if (textBean.getOwner().equals(function.rtvTextOwner()))
					{
						if (!isFunctionReferenceTextUsed(textBean.getKey(), function))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(function));
						}

					}
				}
				for (TextBean textBean : language.getMasks())
				{
					if (textBean.getOwner().equals(function.rtvTextOwner()))
					{
						if (!isFunctionReferenceTextUsed(textBean.getKey(), function))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(function));
						}

					}
				}
				for (TextBean textBean : language.getRegularExpressions())
				{
					if (textBean.getOwner().equals(function.rtvTextOwner()))
					{
						if (!isFunctionReferenceTextUsed(textBean.getKey(), function))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(function));
						}

					}
				}
				for (TextBean textBean : language.getValidValues())
				{
					if (textBean.getOwner().equals(function.rtvTextOwner()))
					{
						if (!isFunctionReferenceTextUsed(textBean.getKey(), function))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(function));
						}

					}
				}
			}
		}
	}
	/**
	 * Validate reference text for layout is used
	 * 
	 * @param translation
	 * @param layout
	 * @param messageContainer
	 */
	public static void validateReferenceText(Translation translation, Layout layout, MessageContainer messageContainer)

	{
		String baseLanguage = layout.getBaseLanguage();
		if (baseLanguage != null && baseLanguage.length() > 0)
		{
			Language language = translation.getLanguage(baseLanguage);

			if (language != null)
			{
				for (TextBean textBean : language.getLabels())
				{
					if (textBean.getOwner().equals(layout.rtvTextOwner()))
					{
						if (!isLayoutReferenceTextUsed(textBean.getKey(), layout))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(layout));
						}

					}
				}
				for (TextBean textBean : language.getDescriptions())
				{
					if (textBean.getOwner().equals(layout.rtvTextOwner()))
					{
						if (!isLayoutReferenceTextUsed(textBean.getKey(), layout))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(layout));
						}

					}
				}
				for (TextBean textBean : language.getMasks())
				{
					if (textBean.getOwner().equals(layout.rtvTextOwner()))
					{
						if (!isLayoutReferenceTextUsed(textBean.getKey(), layout))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(layout));
						}

					}
				}
				for (TextBean textBean : language.getRegularExpressions())
				{
					if (textBean.getOwner().equals(layout.rtvTextOwner()))
					{
						if (!isLayoutReferenceTextUsed(textBean.getKey(), layout))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(layout));
						}

					}
				}
				for (TextBean textBean : language.getValidValues())
				{
					if (textBean.getOwner().equals(layout.rtvTextOwner()))
					{
						if (!isLayoutReferenceTextUsed(textBean.getKey(), layout))
						{
							messageContainer.addErrorMessageId(LanguageResources.getFormattedString(
											"Language.ReferenceCodeNotUsed", new String[] { textBean.getKey(),
															textBean.getLanguage(), textBean.getText(), textBean.getType() }),
											new FunctionProblemLocation(layout));
						}

					}
				}
			}
		}
	}
	/**
	 * Checks whether reference text owned by the function is still used
	 * 
	 * @param referenceTextKey
	 * @param function
	 * @return true if there are references in the function for the reference text
	 */
	private static boolean isFunctionReferenceTextUsed(String referenceTextKey, Function function)
	{
		// Check the description
		if (function.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			if (function.getDescription().trim().equals(referenceTextKey))
			{
				return true;
			}
		}
		// Check the label
		if (function.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			if (function.getLabel().trim().equals(referenceTextKey))
			{
				return true;
			}
		}

		for (int x = 0; x < function.getInputFieldSets().size(); x++)
		{
			InputFieldSet fieldSet = function.getInputFieldSets().get(x);

			// Check the description
			if (fieldSet.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (fieldSet.getDescription().trim().equals(referenceTextKey))
				{
					return true;
				}
			}
			// Check input fields
			for (int y = 0; y < fieldSet.getInputFields().size(); y++)
			{
				InputField field = fieldSet.getInputFields().get(y);

				// Check the description
				if (field.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					if (field.getDescription().trim().equals(referenceTextKey))
					{
						return true;
					}
				}
				// Check the field set reg expression
				if (field.getRegExpType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					if (field.getRegExp().trim().equals(referenceTextKey))
					{
						return true;
					}
				}

				// Check the field set valid values
				if (field.getValidValuesType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					if (field.getValidValues().trim().equals(referenceTextKey))
					{
						return true;
					}
				}

			}
			for (int y = 0; y < fieldSet.getInputGroups().size(); y++)
			{
				InputGroup inputGroup = fieldSet.getInputGroups().get(y);
				// Check the description
				if (inputGroup.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					if (inputGroup.getDescription().trim().equals(referenceTextKey))
					{
						return true;
					}
				}
				// Check the label
				if (inputGroup.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					if (inputGroup.getLabel().trim().equals(referenceTextKey))
					{
						return true;
					}
				}

			}

		}

		for (int y = 0; y < function.getWorkFields().size(); y++)
		{
			WorkField workField = function.getWorkFields().get(y);
			// Check the description
			if (workField.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (workField.getDescription().trim().equals(referenceTextKey))
				{
					return true;
				}
			}
			// Check the label
			if (workField.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (workField.getLabel().trim().equals(referenceTextKey))
				{
					return true;
				}
			}

		}
		return false;
	}
	/**
	 * Checks whether reference text owned by the layout is still used
	 * 
	 * @param referenceTextKey
	 * @param layout
	 * @return true if there are references in the layout for the reference text
	 */
	private static boolean isLayoutReferenceTextUsed(String referenceTextKey, Layout layout)
	{

		// Check the description
		if (layout.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			if (layout.getDescription().trim().equals(referenceTextKey))
			{
				return true;
			}
		}

		// Check the label
		if (layout.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			if (layout.getLabel().trim().equals(referenceTextKey))
			{
				return true;
			}
		}

		for (int x = 0; x < layout.getDisplayAttributesSets().size(); x++)
		{
			DisplayAttributesSet displaySet = layout.getDisplayAttributesSets().get(x);

			// Check the description
			if (displaySet.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (displaySet.getDescription().trim().equals(referenceTextKey))
				{
					return true;
				}
			}

			// Check the label
			if (displaySet.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (displaySet.getLabel().trim().equals(referenceTextKey))
				{
					return true;
				}
			}
			// Check the child item references (including sub children)
			if (isLayoutReferenceTextUsed(referenceTextKey, displaySet.getDisplayItems()))
			{
				return true;
			}

		}

		return false;
	}

	/**
	 * Checks whether reference text owned by the layout child items is still used
	 * 
	 * @param referenceTextKey
	 * @param items
	 * @return true if there are references in the layout for the reference text
	 */
	private static boolean isLayoutReferenceTextUsed(String referenceTextKey, DisplayItemList items)
	{
		if (items == null)
		{
			return false;
		}

		Element element = null;
		for (int index = 0; index < items.size(); index++)
		{
			IDisplayItem item = items.get(index);

			element = (Element) item;
			// Check the description
			if (element.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (element.getDescription().trim().equals(referenceTextKey))
				{
					return true;
				}
			}
			// Check the mask if this is a DisplayAttribute element
			if (element instanceof DisplayAttributes)
			{
				DisplayAttributes displayAttribute = (DisplayAttributes) element;

				// Check the mask
				if (displayAttribute.getMaskType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
				{
					if (displayAttribute.getMask().trim().equals(referenceTextKey))
					{
						return true;
					}
				}

			}
			// Check the label
			if (element.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (element.getLabel().trim().equals(referenceTextKey))
				{
					return true;
				}
			}
			// If this is a DisplayGroup, check the child items. Check until the most innermost child
			if (item instanceof DisplayGroup)
			{

				// Check the child item references (including sub children)
				if (isLayoutReferenceTextUsed(referenceTextKey, ((DisplayGroup) item).getDisplayItems()))

				{
					return true;
				}
			}

		}
		return false;
	}
	/**
	 * Removes text
	 * 
	 * @param languageId
	 * @param owner
	 * @param type
	 * @param key
	 * @param translation
	 */
	public static void removeText(String languageId, String owner, String type, String key, Translation translation)
	{
		TextBean textToDelete = new TextBean(languageId, owner, type, key, "");
		if (textToDelete != null)
		{
			removeText(textToDelete, translation);
		}
	}
	/**
	 * Removes text
	 * 
	 * @param textToDelete
	 * @param translation
	 */
	public static void removeText(TextBean textToDelete, Translation translation)
	{

		for (int x = 0; x < translation.getLanguages().size(); x++)
		{
			Language language = translation.getLanguages().get(x);
			language.removeText(textToDelete);
		}
	}
	/**
	 * Removes empty text
	 * 
	 * @param translation
	 */
	public static void removeEmptyTextAndSort(Translation translation)
	{
		Translation translationClone = translation.cloneTranslation();

		for (int x = 0; x < translationClone.getLanguages().size(); x++)
		{
			Language language = translation.getLanguages().get(x);

			removeEmptyText(language, TextBean.TYPE_LABEL);
			sortText(language, TextBean.TYPE_LABEL);
			removeEmptyText(language, TextBean.TYPE_DESCRIPTION);
			sortText(language, TextBean.TYPE_DESCRIPTION);
			removeEmptyText(language, TextBean.TYPE_MASK);
			sortText(language, TextBean.TYPE_MASK);
			removeEmptyText(language, TextBean.TYPE_REGULAR_EXPRESSION);
			sortText(language, TextBean.TYPE_REGULAR_EXPRESSION);
			removeEmptyText(language, TextBean.TYPE_VALID_VALUE);
			sortText(language, TextBean.TYPE_VALID_VALUE);

		}
	}
	/**
	 * Removes empty text
	 * 
	 * @param language
	 * @param type
	 */
	private static void removeEmptyText(Language language, String type)
	{
		List<TextBean> textList = new ArrayList<TextBean>();

		if (type.equalsIgnoreCase(TextBean.TYPE_LABEL))
		{
			textList = language.getLabels();
		}
		else if (type.equalsIgnoreCase(TextBean.TYPE_DESCRIPTION))
		{
			textList = language.getDescriptions();
		}
		else if (type.equalsIgnoreCase(TextBean.TYPE_MASK))
		{
			textList = language.getMasks();
		}
		else if (type.equalsIgnoreCase(TextBean.TYPE_REGULAR_EXPRESSION))
		{
			textList = language.getRegularExpressions();
		}
		else if (type.equalsIgnoreCase(TextBean.TYPE_VALID_VALUE))
		{
			textList = language.getValidValues();
		}

		for (int i = 0; i < textList.size(); i++)
		{
			TextBean textBean = textList.get(i);

			if (textBean.getText().trim().equals("") || textBean.getText().trim().length() == 0)
			{

				language.removeText(textBean);

				i--;
			}
		}
	}
	/**
	 * Copies translation to a new translation
	 * 
	 * @param copyFromTranslation
	 * @param copyToTranslation
	 */
	public static void copyTranslation(Translation copyFromTranslation, Translation copyToTranslation)
	{
		for (int x = 0; x < copyFromTranslation.getLanguages().size(); x++)
		{
			Language language = new Language();
			copyLanguage(language, copyFromTranslation.getLanguages().get(x));
			copyToTranslation.removeLanguage(language);
			copyToTranslation.addLanguage(language);
		}
	}
	/**
	 * Copies language to a new language
	 * 
	 * @param targetLanguage
	 * @param copyLanguage
	 */
	private static void copyLanguage(Language targetLanguage, Language copyLanguage)
	{
		targetLanguage.setLanguageId(copyLanguage.getLanguageId());

		targetLanguage.getLabels().addAll(copyLanguage.getLabels());
		targetLanguage.getDescriptions().addAll(copyLanguage.getDescriptions());
		targetLanguage.getMasks().addAll(copyLanguage.getMasks());
		targetLanguage.getRegularExpressions().addAll(copyLanguage.getRegularExpressions());
		targetLanguage.getValidValues().addAll(copyLanguage.getValidValues());
	}
	/**
	 * Changes text language
	 * 
	 * @param translation
	 * @param copyFromLanguageId
	 * @param copyToLanguageId
	 * @param owner
	 */
	public static void changeTextLanguage(Translation translation, String copyFromLanguageId, String copyToLanguageId, String owner)
	{
		Language copyFromLanguage = translation.getLanguage(copyFromLanguageId);
		if (copyFromLanguage != null)
		{
			changeTextLanguage(copyFromLanguage.getLabels(), TextBean.TYPE_LABEL, translation, copyFromLanguage, copyToLanguageId,
							owner);
			changeTextLanguage(copyFromLanguage.getDescriptions(), TextBean.TYPE_DESCRIPTION, translation, copyFromLanguage,
							copyToLanguageId, owner);
			changeTextLanguage(copyFromLanguage.getMasks(), TextBean.TYPE_MASK, translation, copyFromLanguage, copyToLanguageId,
							owner);
			changeTextLanguage(copyFromLanguage.getRegularExpressions(), TextBean.TYPE_REGULAR_EXPRESSION, translation,
							copyFromLanguage, copyToLanguageId, owner);
			changeTextLanguage(copyFromLanguage.getValidValues(), TextBean.TYPE_VALID_VALUE, translation, copyFromLanguage,
							copyToLanguageId, owner);
		}
	}

	/**
	 * Changes text language
	 * 
	 * @param texts
	 * @param type
	 * @param translation
	 * @param copyFromLanguage
	 * @param copyToLanguageId
	 * @param owner
	 */
	private static void changeTextLanguage(List<TextBean> texts, String type, Translation translation, Language copyFromLanguage,
					String copyToLanguageId, String owner)
	{
		for (int y = 0; y < texts.size(); y++)
		{
			TextBean textBean = texts.get(y);
			if (textBean.getOwner().equalsIgnoreCase(owner))
			{
				Language copyToLanguage = translation.getLanguage(copyToLanguageId);
				if (copyToLanguage == null)
				{
					copyToLanguage = new Language();
					copyToLanguage.setLanguageId(copyToLanguageId);
					translation.addLanguage(copyToLanguage);
				}

				copyToLanguage.addText(textBean);
				copyFromLanguage.removeText(textBean);

				textBean.setLanguage(copyToLanguageId);
				y--;
			}
		}
	}
	/**
	 * Updates the text owner of function and all child elements
	 * 
	 * @param function
	 * @param newOwner
	 */
	public static void updateTextOwners(Function function, String newOwner)
	{
		// function
		setTextOwner(function, newOwner);

		// input field set
		for (InputFieldSet inputFieldSet : function.getInputFieldSets())
		{
			setTextOwner(inputFieldSet, newOwner);

			// input fields
			for (InputField inputField : inputFieldSet.getInputFields())
			{
				setTextOwner(inputField, newOwner);

			}
			for (InputGroup inputGroup : inputFieldSet.getInputGroups())
			{
				setTextOwner(inputGroup, newOwner);
			}
		}

		// work fields
		for (WorkField workField : function.getWorkFields())
		{
			setTextOwner(workField, newOwner);
		}
	}
	/**
	 * Updates the text owner of layout and all child elements
	 * 
	 * @param layout
	 * @param newOwner
	 */
	public static void updateTextOwner(Layout layout, String newOwner)
	{
		// function
		setTextOwner(layout, newOwner);

		// display attribute set
		for (DisplayAttributesSet attributeSet : layout.getDisplayAttributesSets())
		{
			setTextOwner(attributeSet, newOwner);

			// display items
			updateTextOwner(attributeSet.getDisplayItems(), newOwner);
		}

	}

	/**
	 * This will update the owner all elements in the DisplayItemList
	 * 
	 * @param displayItems
	 *            - the display item
	 * @param newOwner
	 *            - the new text owner
	 */
	private static void updateTextOwner(DisplayItemList displayItems, String newOwner)
	{
		// display items can be a DisplayGroup, DisplayAttributes, DisplayLabel
		for (IDisplayItem item : displayItems)
		{
			setTextOwner((Element) item, newOwner);

			// DisplayGroup can have displayItems
			if (item instanceof DisplayGroup)
			{
				DisplayGroup displayGroup = (DisplayGroup) item;
				updateTextOwner(displayGroup.getDisplayItems(), newOwner);
			}
		}
	}
	/**
	 * Updates the Text owner of the description, label, mask, regular expression and valid values
	 * 
	 * @param element
	 *            - element to update
	 * @param newOwner
	 *            - the new text owner
	 */
	private static void setTextOwner(Element element, String newOwner)
	{
		if (Element.TEXT_VALUE_REFERENCE.equals(element.getDescriptionType()))
		{
			// Description
			if (element.getDescriptionTextOwner().length() > 0)
			{
				element.setDescriptionTextOwner(newOwner);
			}
		}

		if (Element.TEXT_VALUE_REFERENCE.equals(element.getLabelType()))
		{
			// Label
			if (element.getLabelTextOwner().length() > 0)
			{
				element.setLabelTextOwner(newOwner);
			}
		}

		if (element instanceof InputField)
		{
			InputField inputField = (InputField) element;

			// Valid Values
			if (Element.TEXT_VALUE_REFERENCE.equals(inputField.getValidValuesType()))
			{
				if (inputField.getValidValuesTextOwner().length() > 0)
				{
					inputField.setValidValuesTextOwner(newOwner);
				}

			}

			// Regular Expression
			if (Element.TEXT_VALUE_REFERENCE.equals(inputField.getRegExpType()))
			{
				if (inputField.getRegExpTextOwner().length() > 0)
				{
					inputField.setRegExpTextOwner(newOwner);
				}

			}
		}

		else if (element instanceof DisplayAttributes)
		{
			// Mask Type
			DisplayAttributes displayAttributes = (DisplayAttributes) element;
			if (Element.TEXT_VALUE_REFERENCE.equals(displayAttributes.getMaskType()))
			{
				if (displayAttributes.getMaskTextOwner().length() > 0)
				{
					displayAttributes.setMaskTextOwner(newOwner);
				}

			}
		}
	}
	/**
	 * Update text references in the layout by finding the text in the source translation and creating a new references in the
	 * target translation.
	 * 
	 * @param sourceTranslation
	 * @param targetTranslation
	 * @param defaultTextTypeAsReferenceText
	 *            - boolean indicating if text should be default as reference text
	 * @throws EQException
	 */
	public static void updateTextReferences(Layout layout, Translation sourceTranslation, Translation targetTranslation,
					boolean defaultTextTypeAsReferenceText, boolean isMisysMode) throws EQException
	{
		String languageId = layout.getBaseLanguage();
		String targetOwner = layout.rtvTextOwner();
		updateTextReferences(layout, sourceTranslation, targetTranslation, languageId, targetOwner, defaultTextTypeAsReferenceText,
						isMisysMode);

		for (int x = 0; x < layout.getDisplayAttributesSets().size(); x++)
		{

			DisplayAttributesSet displaySet = layout.getDisplayAttributesSets().get(x);
			updateTextReferences(displaySet, sourceTranslation, targetTranslation, languageId, targetOwner,
							defaultTextTypeAsReferenceText, isMisysMode);

			// Check the child item references (including sub children)
			updateTextReferences(displaySet.getDisplayItems(), sourceTranslation, targetTranslation, languageId, targetOwner,
							defaultTextTypeAsReferenceText, isMisysMode);

		}
	}
	/**
	 * Update text references in the display item list by finding the text in the source translation and creating a new references
	 * in the target translation.
	 * 
	 * @param items
	 * @param sourceTranslation
	 * @param targetTranslation
	 * @param languageId
	 * @param targetOwner
	 * @param defaultTextTypeAsReferenceText
	 * @throws EQException
	 */
	public static void updateTextReferences(DisplayItemList items, Translation sourceTranslation, Translation targetTranslation,
					String languageId, String targetOwner, boolean defaultTextTypeAsReferenceText, boolean isMisysMode)
					throws EQException
	{

		for (int index = 0; index < items.size(); index++)
		{
			Element element = (Element) items.get(index);

			updateTextReferences(element, sourceTranslation, targetTranslation, languageId, targetOwner,
							defaultTextTypeAsReferenceText, isMisysMode);
			// If this is a DisplayGroup, check the child items. Check until the most innermost child
			if (element instanceof DisplayGroup)
			{
				updateTextReferences(((DisplayGroup) element).getDisplayItems(), sourceTranslation, targetTranslation, languageId,
								targetOwner, defaultTextTypeAsReferenceText, isMisysMode);

			}

		}

	}
	/**
	 * Update text references in the element by finding the text in the source translation and creating a new references in the
	 * target translation.
	 * 
	 * @param element
	 * @param sourceTranslation
	 * @param targetTranslation
	 * @param languageId
	 * @param targetOwner
	 * @param defaultTextTypeAsReferenceText
	 * @throws EQException
	 */
	public static void updateTextReferences(Element element, Translation sourceTranslation, Translation targetTranslation,
					String languageId, String targetOwner, boolean defaultTextTypeAsReferenceText, boolean isMisysMode)
					throws EQException
	{
		// Description
		if (element.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			if (!element.getDescription().trim().equalsIgnoreCase("")
							&& !element.getDescription().trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
			{
				String textKey = TranslationToolbox.copyReferenceText(sourceTranslation, languageId, element
								.getDescriptionTextOwner(), TextBean.TYPE_DESCRIPTION, element.getDescription(), targetTranslation,
								targetOwner, isMisysMode);
				// If text bean is found
				if (textKey != null)
				{
					element.setDescription(textKey);
					element.setDescriptionTextOwner(targetOwner);
				}
				else
				{
					element.setDescription("");
					element.setDescriptionTextOwner("");
				}
			}
			else
			{
				element.setDescriptionTextOwner("");
			}
		}
		if (defaultTextTypeAsReferenceText && element.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_TEXT))
		{
			if (!element.getDescription().trim().equalsIgnoreCase("")
							&& !element.getDescription().trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
			{
				String newReference = TranslationToolbox.generateUpdateDeleteReferenceText(targetTranslation, languageId,
								targetOwner, TextBean.TYPE_DESCRIPTION, element.getDescription(), "", true, isMisysMode);

				element.setDescription(newReference);
				if (element.getDescription().length() > 0)
				{
					element.setDescriptionTextOwner(targetOwner);
				}
			}
			else
			{
				element.setDescriptionTextOwner("");
			}
			element.setDescriptionType(Element.TEXT_VALUE_REFERENCE);
		}
		// Label
		if (element.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
		{
			if (!element.getLabel().trim().equalsIgnoreCase("")
							&& !element.getLabel().trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
			{
				String textKey = TranslationToolbox.copyReferenceText(sourceTranslation, languageId, element.getLabelTextOwner(),
								TextBean.TYPE_LABEL, element.getLabel(), targetTranslation, targetOwner, isMisysMode);
				// If text bean is found
				if (textKey != null)
				{
					element.setLabel(textKey);
					element.setLabelTextOwner(targetOwner);
				}
				else
				{
					element.setLabel("");
					element.setLabelTextOwner("");
				}
			}
			else
			{
				element.setLabelTextOwner("");
			}
		}

		if (defaultTextTypeAsReferenceText && element.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_TEXT))
		{
			if (!element.getLabel().trim().equalsIgnoreCase("")
							&& !element.getLabel().trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
			{
				String newReference = TranslationToolbox.generateUpdateDeleteReferenceText(targetTranslation, languageId,
								targetOwner, TextBean.TYPE_LABEL, element.getLabel(), "", true, isMisysMode);

				element.setLabel(newReference);
				if (element.getLabel().length() > 0)
				{
					element.setLabelTextOwner(targetOwner);
				}
			}
			else
			{
				element.setLabelTextOwner("");
			}
			element.setLabelType(Element.TEXT_VALUE_REFERENCE);
		}
		// Mask
		if (element instanceof DisplayAttributes)
		{
			DisplayAttributes displayAttribute = (DisplayAttributes) element;
			if (displayAttribute.getMaskType().equalsIgnoreCase(Element.TEXT_VALUE_REFERENCE))
			{
				if (!displayAttribute.getMask().trim().equalsIgnoreCase("")
								&& !displayAttribute.getMask().trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
				{
					String textKey = TranslationToolbox.copyReferenceText(sourceTranslation, languageId, displayAttribute
									.getMaskTextOwner(), TextBean.TYPE_MASK, displayAttribute.getMask(), targetTranslation,
									targetOwner, isMisysMode);
					// If text bean is found
					if (textKey != null)
					{
						displayAttribute.setMask(textKey);
						displayAttribute.setMaskTextOwner(targetOwner);
					}
					else
					{
						displayAttribute.setMask("");
						displayAttribute.setMaskTextOwner("");
					}
				}
				else
				{
					displayAttribute.setMaskTextOwner("");
				}
			}
			if (defaultTextTypeAsReferenceText && displayAttribute.getMaskType().equalsIgnoreCase(Element.TEXT_VALUE_TEXT))
			{
				if (!displayAttribute.getMask().trim().equalsIgnoreCase("")
								&& !displayAttribute.getMask().trim().equalsIgnoreCase(Element.DEFAULT_TEXT_VALUE))
				{
					String newReference = TranslationToolbox.generateUpdateDeleteReferenceText(targetTranslation, languageId,
									targetOwner, TextBean.TYPE_MASK, displayAttribute.getMask(), "", true, isMisysMode);

					displayAttribute.setMask(newReference);
					if (displayAttribute.getMask().length() > 0)
					{
						displayAttribute.setMaskTextOwner(targetOwner);
					}
				}
				else
				{
					displayAttribute.setMaskTextOwner("");
				}
				displayAttribute.setMaskType(Element.TEXT_VALUE_REFERENCE);
			}
		}

	}
	/**
	 * Setup default text type and text in an element based on default setting to be used
	 * 
	 * @param element
	 * @param defaultTextTypeAsReferenceText
	 */
	public static void setupDefaultReference(Element element, boolean defaultTextTypeAsReferenceText)
	{
		if (defaultTextTypeAsReferenceText)
		{
			// Description
			element.setDescriptionType(Element.TEXT_VALUE_REFERENCE);
			element.setDescription("");
			// Labels for Service elements will never be reference text or reusable text
			if (element instanceof DisplayAttributes || element instanceof DisplayAttributesSet || element instanceof DisplayGroup
							|| element instanceof DisplayLabel || element instanceof DisplayButtonLink)
			{
				// Label
				element.setLabelType(Element.TEXT_VALUE_REFERENCE);
				element.setLabel("");
			}
			if (element instanceof InputField)
			{
				InputField inputField = (InputField) element;

				inputField.setRegExpType(Element.TEXT_VALUE_REFERENCE);
				inputField.setRegExp("");

				inputField.setValidValuesType(Element.TEXT_VALUE_REFERENCE);
				inputField.setValidValues("");
			}
		}
	}
	/**
	 * Setup default text type and text in an element when created from an API
	 * 
	 * @param originalInputFields
	 * @param inputFieldSet
	 * @param translation
	 * @param languageId
	 * @param owner
	 */
	public static void setupDefaultReference(Translation translation, List<String> originalInputFields,
					InputFieldSet inputFieldSet, String languageId, String owner, boolean defaultTextTypeAsReferenceText,
					boolean isMisysMode)
	{
		if (defaultTextTypeAsReferenceText)
		{
			List<InputField> inputFields = inputFieldSet.getInputFields();
			for (int x = 0; x < inputFields.size(); x++)
			{
				InputField inputField = inputFields.get(x);
				// If input field is new and not already existing in InputFieldset then set text to reference text
				// Reprocessing fields will loose data that may have been setup already by the user.
				if (!originalInputFields.contains(inputField.getId()))
				{
					// Description
					inputField.setDescriptionType(Element.TEXT_VALUE_REFERENCE);
					inputField.setDescription(TranslationToolbox.generateUpdateDeleteReferenceText(translation, languageId, owner,
									TextBean.TYPE_DESCRIPTION, inputField.getDescription(), "", false, isMisysMode));
					if (inputField.getDescription().length() > 0)
					{
						inputField.setDescriptionTextOwner(owner);
					}
					else
					{
						inputField.setDescriptionTextOwner("");
					}
					// Regular Expression
					inputField.setRegExpType(Element.TEXT_VALUE_REFERENCE);
					inputField.setRegExp(TranslationToolbox.generateUpdateDeleteReferenceText(translation, languageId, owner,
									TextBean.TYPE_REGULAR_EXPRESSION, inputField.getRegExp(), "", false, isMisysMode));
					if (inputField.getRegExp().length() > 0)
					{
						inputField.setRegExpTextOwner(owner);
					}
					else
					{
						inputField.setRegExpTextOwner("");
					}

					// Valid Values
					inputField.setValidValuesType(Element.TEXT_VALUE_REFERENCE);
					inputField.setValidValues(TranslationToolbox.generateUpdateDeleteReferenceText(translation, languageId, owner,
									TextBean.TYPE_VALID_VALUE, inputField.getValidValues(), "", false, isMisysMode));
					if (inputField.getValidValues().length() > 0)
					{
						inputField.setValidValuesTextOwner(owner);
					}
					else
					{
						inputField.setValidValuesTextOwner("");
					}
				}
			}

		}
	}
	/**
	 * Setup default text type and text in an layout
	 * 
	 * @param translation
	 * @param layout
	 * @param optionId
	 * @param defaultTextTypeAsReferenceText
	 */
	public static void setupDefaultReference(Translation translation, Layout layout, String optionId,
					boolean defaultTextTypeAsReferenceText, boolean isMisysMode)

	{
		if (defaultTextTypeAsReferenceText)
		{
			// Layout

			// Description
			if (!layout.getDescriptionType().equals(Element.TEXT_VALUE_REFERENCE))
			{
				layout.setDescriptionType(Element.TEXT_VALUE_REFERENCE);
				layout.setDescription("");
			}

			// Label
			if (!layout.getLabelType().equals(Element.TEXT_VALUE_REFERENCE))
			{
				layout.setLabelType(Element.TEXT_VALUE_REFERENCE);
				layout.setLabel("");
			}

			// Group
			DisplayGroup displayGroup = (DisplayGroup) layout.getDisplayAttributesSet(Function.PRIMARY_ID).getDisplayItems().get(
							DisplayGroup.FULL_KEY_GROUP_ID);
			// Description
			if (!displayGroup.getDescriptionType().equals(Element.TEXT_VALUE_REFERENCE))
			{
				displayGroup.setDescriptionType(Element.TEXT_VALUE_REFERENCE);
				displayGroup.setDescription("");
			}
			// Label
			if (!displayGroup.getLabelType().equals(Element.TEXT_VALUE_REFERENCE))
			{
				displayGroup.setLabelType(Element.TEXT_VALUE_REFERENCE);
				String reference = TranslationToolbox.generateUpdateDeleteReferenceText(translation, layout.getBaseLanguage(),
								layout.rtvTextOwner(), TextBean.TYPE_LABEL, displayGroup.getLabel(), "", false, isMisysMode);
				displayGroup.setLabel(reference);
			}
			if (displayGroup.getLabel().length() > 0)
			{
				displayGroup.setLabelTextOwner(layout.rtvTextOwner());
			}

			for (int x = 0; x < layout.getDisplayAttributesSets().size(); x++)
			{
				DisplayAttributesSet displayAttributeSet = layout.getDisplayAttributesSets().get(x);
				for (int y = 0; y < displayAttributeSet.getDisplayItems().size(); y++)
				{
					IDisplayItem displayItem = displayAttributeSet.getDisplayItems().get(y);
					if (displayItem instanceof DisplayGroup)
					{
						DisplayGroup dpGroup = (DisplayGroup) displayItem;
						for (int z = 0; z < dpGroup.getDisplayItems().size(); z++)
						{
							DisplayAttributes dpAttributes = (DisplayAttributes) dpGroup.getDisplayItems().get(z);
							// Description
							if (!dpAttributes.getDescriptionType().equals(Element.TEXT_VALUE_REFERENCE))
							{
								dpAttributes.setDescriptionType(Element.TEXT_VALUE_REFERENCE);
								dpAttributes.setDescription("");
							}
							// Label
							if (!dpAttributes.getLabelType().equals(Element.TEXT_VALUE_REFERENCE))
							{
								dpAttributes.setLabelType(Element.TEXT_VALUE_REFERENCE);
								dpAttributes.setLabel("");
							}
							// Mask
							if (!dpAttributes.getMaskType().equals(Element.TEXT_VALUE_REFERENCE))
							{
								dpAttributes.setMaskType(Element.TEXT_VALUE_REFERENCE);
								dpAttributes.setMask("");
							}
						}
					}
					else if (displayItem instanceof DisplayAttributes)
					{
						DisplayAttributes dpAttributes = (DisplayAttributes) displayItem;
						// Description
						if (!dpAttributes.getDescriptionType().equals(Element.TEXT_VALUE_REFERENCE))
						{
							dpAttributes.setDescriptionType(Element.TEXT_VALUE_REFERENCE);
							dpAttributes.setDescription("");
						}
						// Label
						if (!dpAttributes.getLabelType().equals(Element.TEXT_VALUE_REFERENCE))
						{
							dpAttributes.setLabelType(Element.TEXT_VALUE_REFERENCE);
							dpAttributes.setLabel("");
						}
						// Mask
						if (!dpAttributes.getMaskType().equals(Element.TEXT_VALUE_REFERENCE))
						{
							dpAttributes.setMaskType(Element.TEXT_VALUE_REFERENCE);
							dpAttributes.setMask("");
						}
					}
				}
			}

		}
	}
	/**
	 * Adds the text owner to display attributes if the description or label are reusable references
	 * 
	 * If the session is passed, this method also update the label to the value of the reusable text if the label type is
	 * Element.TEXT_VALUE_REUSABLE_REFERENCE
	 * 
	 * @param session
	 * @param inputFieldSet
	 * @param owner
	 * @param languageId
	 */
	public static void addTextOwnerToInputFields(EquationStandardSession session, InputFieldSet inputFieldSet, String owner,
					String languageId)
	{
		if (session != null)
		{
			if (inputFieldSet.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				String reusableText = FunctionToolbox.getReuseableText(session, owner, languageId, inputFieldSet.getLabel(),
								TextBean.TYPE_LABEL);
				inputFieldSet.setLabel(reusableText);
				inputFieldSet.setLabelType(Element.TEXT_VALUE_TEXT);
			}
		}

		for (InputField inputField : inputFieldSet.getInputFields())
		{
			if (inputField.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				inputField.setDescriptionTextOwner(owner);
			}

			if (inputField.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				if (session != null)
				{

					String reusableText = FunctionToolbox.getReuseableText(session, owner, languageId, inputField.getLabel(),
									TextBean.TYPE_LABEL);
					inputField.setLabel(reusableText);
					inputField.setLabelType(Element.TEXT_VALUE_TEXT);

				}
				else
				{
					inputField.setLabelTextOwner(owner);
				}
			}

		}
	}

	/**
	 * Adds a description and label text owner to display attributes if the description or label are reusable references
	 * 
	 * @param attributeSet
	 * @param owner
	 * @param languageId
	 */
	public static void addTextOwnerToDisplayAttributes(DisplayAttributesSet attributeSet, String owner, String languageId)
	{
		DisplayAttributes displayAttributes;
		for (IDisplayItem item : attributeSet.getDisplayItems())
		{
			displayAttributes = (DisplayAttributes) item;
			if (displayAttributes.getDescriptionType().equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				displayAttributes.setDescriptionTextOwner(owner);
			}

			if (displayAttributes.getLabelType().equalsIgnoreCase(Element.TEXT_VALUE_REUSABLE_REFERENCE))
			{
				displayAttributes.setLabelTextOwner(owner);
			}

		}
	}
	/**
	 * This will return the label text based on the type of the element
	 * <p>
	 * If the element is a Reference type, it will search the text from the translation bean
	 * <p>
	 * If the element is a Reusable reference type, it will search the text from the HBX records data cache
	 * <p>
	 * Otherwise will, return the label of the element
	 * 
	 * @param reusableTextHBXRecords
	 * @param translation
	 * @param baseLanguage
	 * @param element
	 * @return label text
	 */
	public static String getLabelText(Map<String, HBXRecordDataModel> reusableTextHBXRecords, Translation translation,
					String baseLanguage, Element element)
	{
		String text = "";
		if (Element.TEXT_VALUE_REFERENCE.equals(element.getLabelType()))
		{
			TextBean textBean = findReferenceText(translation, baseLanguage, element.getLabelTextOwner(), TextBean.TYPE_LABEL,
							element.getLabel());
			if (textBean != null)
			{
				text = textBean.getText();
			}
		}
		else if (Element.TEXT_VALUE_REUSABLE_REFERENCE.equals(element.getLabelType()))
		{
			HBXRecordDataModel record = reusableTextHBXRecords.get(element.getLabelTextOwner() + baseLanguage + TextBean.TYPE_LABEL
							+ element.getLabel());

			if (record != null)
			{
				text = record.getText();
			}

		}
		else if (Element.TEXT_VALUE_TEXT.equals(element.getLabelType()))
		{
			text = element.getLabel();
		}

		return text;
	}
	/**
	 * This will return the description text based on the type of the element
	 * <p>
	 * If the element is a Reference type, it will search the text from the translation bean
	 * <p>
	 * If the element is a Reusable reference type, it will search the text from the HBX records data cache
	 * <p>
	 * Otherwise will, return the description of the element
	 * 
	 * @param reusableTextHBXRecords
	 * @param translation
	 * @param baseLanguage
	 * @param element
	 * @return description text
	 */
	public static String getDescriptionText(Map<String, HBXRecordDataModel> reusableTextHBXRecords, Translation translation,
					String baseLanguage, Element element)
	{
		String text = "";
		if (Element.TEXT_VALUE_REFERENCE.equals(element.getDescriptionType()))
		{
			TextBean textBean = findReferenceText(translation, baseLanguage, element.getDescriptionTextOwner(),
							TextBean.TYPE_DESCRIPTION, element.getDescription());
			if (textBean != null)
			{
				text = textBean.getText();
			}
		}
		else if (Element.TEXT_VALUE_REUSABLE_REFERENCE.equals(element.getDescriptionType()))
		{
			HBXRecordDataModel record = reusableTextHBXRecords.get(element.getDescriptionTextOwner() + baseLanguage
							+ TextBean.TYPE_DESCRIPTION + element.getDescription());

			if (record != null)
			{
				text = record.getText();
			}

		}
		else if (Element.TEXT_VALUE_TEXT.equals(element.getDescriptionType()))
		{
			text = element.getDescription();
		}

		return text;
	}
	/**
	 * This will return a list of translation owners for a service
	 * 
	 * @param session
	 * @param serviceId
	 * @return list of translation owners for a service
	 * @throws EQException
	 */
	public static ArrayList<String> getTranslationOwnersForService(EquationStandardSession session, String serviceId)
					throws EQException
	{
		Set<String> owners = new HashSet<String>(session.getUnit().getRecords().getEQ4LayoutsForService(serviceId).keySet());
		ArrayList<String> ownersList = new ArrayList<String>();
		for (String owner : owners)
		{
			ownersList.add(owner + Layout.LAYOUT_FILE_SUFFIX);
		}
		ownersList.add(serviceId + Function.SERVICE_FILE_NAME_SUFFIX);
		// If the service has never been deployed before then assume there is a layout matching the name of the service
		if (!owners.contains(serviceId + Layout.LAYOUT_FILE_SUFFIX))
		{
			ownersList.add(serviceId + Layout.LAYOUT_FILE_SUFFIX);
		}
		return ownersList;
	}
}
