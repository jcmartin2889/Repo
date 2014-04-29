package com.misys.equation.uxp.plugin;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.misys.equation.common.utilities.EquationLogger;
import com.misys.uxp.framework.api.json.ResponseItem;
import com.misys.uxp.framework.api.json.UserPreferenceItem;
import com.misys.uxp.framework.api.metadata.IUXServiceData;
import com.misys.uxp.framework.common.utilities.UXFactory;
import com.misys.uxp.framework.impl.service.UXResponseMessage;

/**
 * A custom GSon class to handle de-serialization of UxResponseMessages from JSON
 */
public class UXResponseMessageDeserializer implements JsonDeserializer<UXResponseMessage>
{
	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: UXResponseMessageDeserializer.java 306 2013-04-16 13:46:09Z jonathan.perkins $";
	/** Logger instance */
	private static final EquationLogger LOG = new EquationLogger(UXResponseMessageDeserializer.class);

	private Type entryType = new TypeToken<Map<String, String>>()
	{
	}.getType();

	public UXResponseMessage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext ctx) throws JsonParseException
	{
		UXResponseMessage result = new UXResponseMessage();
		ResponseItem responseItem = new ResponseItem();
		HashMap<String, String> responseBody = new HashMap<String, String>();
		JsonObject obj = json.getAsJsonObject();

		String mtype = null;
		String uxsdJson = null; // To hold uxsd until we know what it contains

		Entry<String, JsonElement> entry = obj.entrySet().iterator().next();
		if (entry == null)
			return null;
		if ("responseItem".equals(entry.getKey()) && entry.getValue() instanceof JsonObject)
		{
			result.setResponseItem(responseItem);

			JsonObject itemObj = (JsonObject) entry.getValue();
			Iterator<Entry<String, JsonElement>> it = itemObj.entrySet().iterator();
			while (it.hasNext())
			{
				Entry<String, JsonElement> itemEntry = it.next();
				// LOG.info("responseItem child: [" + itemEntry.getKey() + "]");
				if ("responseBody".equals(itemEntry.getKey()) && itemEntry.getValue() instanceof JsonObject)
				{
					JsonObject rbObj = (JsonObject) itemEntry.getValue();
					Iterator<Entry<String, JsonElement>> rbit = rbObj.entrySet().iterator();
					while (rbit.hasNext())
					{
						Entry<String, JsonElement> rbItemEntry = rbit.next();
						LOG.debug(rbItemEntry.getKey());
						if ("uxsd".equals(rbItemEntry.getKey()))
						{
							// uxsd value is a String (JsonPrimitive)
							uxsdJson = ((JsonPrimitive) rbItemEntry.getValue()).getAsString();
						}
					}
				}
				else if ("header".equals(itemEntry.getKey()) && itemEntry.getValue() instanceof JsonObject)
				{
					HashMap<String, String> headerMap = new HashMap<String, String>();
					JsonObject hdrObj = (JsonObject) itemEntry.getValue();
					Iterator<Entry<String, JsonElement>> hdrIterator = hdrObj.entrySet().iterator();
					while (hdrIterator.hasNext())
					{
						Entry<String, JsonElement> hdrItemEntry = hdrIterator.next();
						LOG.debug(hdrItemEntry.getKey());
						JsonPrimitive hdrObjItem = (JsonPrimitive) hdrItemEntry.getValue();
						headerMap.put(hdrItemEntry.getKey(), hdrObjItem.getAsString());
						if ("mtype".equals(hdrItemEntry.getKey()))
						{
							mtype = hdrObjItem.getAsString();
						}
					}
					responseItem.setHeader(headerMap);
				}
			}

			// Now, after processing the header, we know the message type
			if (uxsdJson != null)
			{
				if ("ex".equals(mtype))
				{
					// For an exception, just put the exception text back in the uxsd item:
					responseBody.put("uxsd", uxsdJson);
				}
				else
				{
					// A real UXServiceData
					result.setUXServiceData(processUXSD(uxsdJson));
				}
			}

			if (responseBody != null && responseBody.size() > 0)
			{
				result.getResponseItem().setResponseBody(responseBody);
			}

		}

		return result;
	}

	private IUXServiceData processUXSD(String uxsdJson)
	{
		IUXServiceData serviceData = UXFactory.getInitializedUXServiceData();
		HashMap<String, String> dataMap = new HashMap<String, String>();
		serviceData.setData(dataMap);

		Object uxsdObject = UXFactory.getObjectFromJson(uxsdJson, entryType);
		Map<String, String> uxsdEntries = (Map<String, String>) uxsdObject;
		for (Entry<String, String> uxsdEntry : uxsdEntries.entrySet())
		{
			LOG.debug(uxsdEntry.getKey());
			if ("prefs".equals(uxsdEntry.getKey()))
			{
				// Value is a string representing a UserPreferenceItem class:
				String prefsJson = uxsdEntry.getValue();
				Object prefsObject = UXFactory.getObjectFromJson(prefsJson, UserPreferenceItem.class);
				UserPreferenceItem prefs = (UserPreferenceItem) prefsObject;
				dataMap.put("prefs", prefsJson);
			}
			else
			{
				dataMap.put(uxsdEntry.getKey(), uxsdEntry.getValue());
			}
		}
		return serviceData;
	}
}
