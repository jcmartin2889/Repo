package test.old;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

import com.trapedza.bankfusion.core.CommonConstants;
import com.trapedza.bankfusion.core.DataType;
import com.trapedza.bankfusion.core.MetaDataEnum;
import com.trapedza.bankfusion.core.MetaDataWrapper;
import com.trapedza.bankfusion.core.Wrapper;
import com.trapedza.bankfusion.core.attributes.Attribute;
import com.trapedza.bankfusion.core.attributes.AttributeTypeFactory;

public class BFToolbox
{
	public static Wrapper createActivityStepBase(String name, String description, String codeProviderName,
					String superDescriptorResourceId)
	{
		Wrapper fatom = new MetaDataWrapper();
		Hashtable tags;
		String tagName;
		String tagDescription;
		Object tagDefaultValue;

		// Basic detail
		fatom.getMetaData().put(MetaDataEnum.PROP_STEP_ID, "0"); //$NON-NLS-1$
		fatom.getMetaData().put(MetaDataEnum.PROP_NAME, name);
		fatom.getMetaData().put(MetaDataEnum.PROP_DESCRIPTION, description);
		fatom.getMetaData().put(MetaDataEnum.PROP_CODE_PROVIDER_NAME, codeProviderName);
		fatom.getMetaData().put(MetaDataEnum.PROP_SUPER_DESCRIPTOR_RESOURCE_ID, superDescriptorResourceId);
		fatom.getMetaData().put(MetaDataEnum.PROP_RESOURCE_ID, name);
		// Input tags (none, added later)
		fatom.getMetaData().put(MetaDataEnum.PROP_INPUT_TAGS, new Hashtable());
		// Output tags (none, added later)
		fatom.getMetaData().put(MetaDataEnum.PROP_OUTPUT_TAGS, new Hashtable());
		// Extension points (none)
		fatom.getMetaData().put(MetaDataEnum.PROP_EXTENSION_POINTS, new Hashtable());
		// Mapping (none)
		fatom.getMetaData().put(MetaDataEnum.PROP_MAPPING, new Hashtable());
		return fatom;
	}
	public static Attribute createStringTagAttribute(String tagName, String tagDescription, Object tagDefaultValue, String tagType) // MetaDataEnum.PROP_INPUT_TAGS
																																	// or
																																	// MetaDataEnum.PROP_OUTPUT_TAGS
	{
		// Creates an attribute for a single input or output tag.
		Attribute tag;
		if (tagType.equals(MetaDataEnum.PROP_OUTPUT_TAGS))
		{
			tagDefaultValue = CommonConstants.EMPTY_STRING;
		}
		tag = AttributeTypeFactory.newAttribute(DataType.STRING, tagName, tagName);
		tag.getMetaData().put(MetaDataEnum.PROP_PERMIT_UNDEFINED, false);
		tag.getMetaData().put(MetaDataEnum.PROP_NAME, tagName);
		tag.getMetaData().put(MetaDataEnum.PROP_DESCRIPTION, tagDescription);
		tag.getMetaData().put(MetaDataEnum.PROP_CLASSNAME, CommonConstants.ATTRIBUTE_CLASS_NAME);
		tag.getMetaData().put(MetaDataEnum.PROP_DELETABLE, false);
		tag.getMetaData().put(MetaDataEnum.PROP_TAG_TYPE, tagType);
		tag.getMetaData().put(MetaDataEnum.PROP_DISPLAY_NAME, tagName);
		tag.getMetaData().put(MetaDataEnum.PROP_UPDATABLE, true);
		tag.getMetaData().put(MetaDataEnum.PROP_QUERYABLE, true);
		tag.getMetaData().put(MetaDataEnum.PROP_OUTPUT_MASK, CommonConstants.EMPTY_STRING);
		tag.getMetaData().put(MetaDataEnum.PROP_BASE_ATTRIBUTE, AttributeTypeFactory.ATTR_STRING);
		tag.getMetaData().put(MetaDataEnum.PROP_UIDISPLAY_TYPE, "TEXTBOX");
		tag.getMetaData().put(MetaDataEnum.PROP_INPUT_MASK, "");
		tag.getMetaData().put(MetaDataEnum.PROP_LABEL, tagDescription);
		tag.getMetaData().put(MetaDataEnum.PROP_VISIBLE, true);
		tag.getMetaData().put(MetaDataEnum.PROP_READONLY, false);
		tag.getMetaData().put(MetaDataEnum.PROP_DEFAULT_VALUE, tagDefaultValue);
		tag.getMetaData().put(MetaDataEnum.PROP_VALUE, CommonConstants.EMPTY_STRING);
		Hashtable parameters = new Hashtable();
		ArrayList emptyArrayList = new ArrayList();
		parameters.put(MetaDataEnum.PROP_CLIENT_VALIDATORS, emptyArrayList);
		parameters.put(MetaDataEnum.PROP_SERVER_VALIDATORS, emptyArrayList);
		tag.getMetaData().put(MetaDataEnum.PROP_PARAMETERS, parameters);
		tag.getMetaData().put(MetaDataEnum.PROP_STATE, true);
		return tag;
	}
	public static Wrapper attachActivityStepTag(Attribute tag, Wrapper fatom)
	{
		String tagType = (String) tag.getMetaData().get(MetaDataEnum.PROP_TAG_TYPE);
		String tagName = (String) tag.getMetaData().get(MetaDataEnum.PROP_NAME);
		Hashtable tags;
		// Input tag
		if (tagType == MetaDataEnum.PROP_INPUT_TAGS)
		{
			tags = (Hashtable) fatom.getMetaData().get(MetaDataEnum.PROP_INPUT_TAGS);
			tags.put(tagName, tag);
			fatom.getMetaData().put(MetaDataEnum.PROP_INPUT_TAGS, tags);
		}
		else
		// Output tag
		{
			tags = (Hashtable) fatom.getMetaData().get(MetaDataEnum.PROP_OUTPUT_TAGS);
			tags.put(tagName, tag);
			fatom.getMetaData().put(MetaDataEnum.PROP_OUTPUT_TAGS, tags);
		}
		return fatom;
	}

	public static void writeBFObject(Wrapper o, File file)
	{
		try
		{
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
	public static Object readBFFile(File file)
	{
		Object o = null;
		try
		{
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
	private Object roundTripMyObject(Object objectIn)
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
