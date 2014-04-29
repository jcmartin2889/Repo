package com.misys.equation.uxp.plugin;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.misys.uxp.framework.api.json.MenuItem;
import com.misys.uxp.framework.common.utilities.UXFactory;

/**
 * GSON TypeAdapter providing custom serialization and deserialization of MenuItem[] type
 * 
 * This custom deserializer class is required because the UXP MenuItem class uses a generic ArrayList collection to store its
 * children instead of a typed ArrayList<MenuItem> collection. This means that standard JSON deserialization does not work and this
 * custom deserializer is required.
 * 
 * Also, non-standard JSON is used to represent references to duplicate items ( _reference="<id>") which is not catered for by the
 * MenuItem class. This is handled by using a state of -1 in the MenuItem as a flag to indicate that this is a reference.
 * 
 * https://sites.google.com/site/gson/gson-user-guide#TOC-Custom-Serialization-and-Deserialization
 * 
 */
public class MenuItemTypeAdapter implements JsonSerializer<MenuItem[]>, JsonDeserializer<MenuItem[]>
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: FileProcessor.java 7606 2010-06-01 17:04:23Z MACDONP1 $";
	public static final Type MENUITEM_ARRAY_TYPE = new TypeToken<ArrayList<MenuItem>>()
	{
	}.getType();

	/**
	 * Utility method to deserialize MenuItem[] JSON
	 * 
	 * @param menuJson
	 *            a Json string of a MenuItem[]
	 * @return The deserialized MenuItem[]
	 */
	public static MenuItem[] deserializeMenuJson(String menuJson)
	{
		MenuItem[] result = null;
		result = (MenuItem[]) UXFactory.getObjectFromJson(menuJson, MENUITEM_ARRAY_TYPE);
		return result;
	}

	/**
	 * GSON custom deserializer implementation for MenuItem[]s
	 * 
	 * @param json
	 *            JsonElement to deserialize
	 */
	public MenuItem[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
	{

		JsonArray arr = json.getAsJsonArray();
		ArrayList<Object> children = parseMenuLevel(arr);

		// Convert the ArrayList<Object> into a MenuItem[]
		MenuItem[] result = new MenuItem[children.size()];
		for (int index = 0; index < children.size(); index++)
		{
			result[index] = (MenuItem) children.get(index);
		}
		return result;
	}

	/**
	 * Recursively process a JsonArray representing a MenuItem[]
	 * 
	 * This returns a ArrayList<Object> which is suitable for setting into a parent MenuItem using the setChildren method
	 */
	private ArrayList<Object> parseMenuLevel(JsonArray menuArray)
	{
		ArrayList<Object> myMenus = new ArrayList<Object>();
		Iterator<JsonElement> it = menuArray.iterator();
		while (it.hasNext())
		{
			JsonElement element = it.next();
			if (element instanceof JsonObject)
			{
				MenuItem menuItem = new MenuItem();
				JsonObject jsonObject = (JsonObject) element;
				for (Entry<String, JsonElement> fieldElement : jsonObject.entrySet())
				{
					// TODO: Avoid reliance on list of known fields
					if ("children".equals(fieldElement.getKey()))
					{
						if (fieldElement.getValue() instanceof JsonArray)
						{
							// Recurse through child items
							ArrayList<Object> childObjects = parseMenuLevel((JsonArray) fieldElement.getValue());
							menuItem.setChildren(childObjects);
						}
					}
					else if ("state".equals(fieldElement.getKey()))
					{
						menuItem.setState(fieldElement.getValue().getAsInt());
					}
					else if ("active".equals(fieldElement.getKey()))
					{
						menuItem.setActive(fieldElement.getValue().getAsBoolean());
					}
					else if ("id".equals(fieldElement.getKey()))
					{
						menuItem.setId(fieldElement.getValue().getAsString());
					}
					else if ("name".equals(fieldElement.getKey()))
					{
						menuItem.setName(fieldElement.getValue().getAsString());
					}
					else if ("value".equals(fieldElement.getKey()))
					{
						menuItem.setValue(fieldElement.getValue().getAsString());
					}
					else if ("type".equals(fieldElement.getKey()))
					{
						menuItem.setType(fieldElement.getValue().getAsString());
					}
					else if ("_reference".equals(fieldElement.getKey()))
					{
						// Special processing for references for duplicate items
						menuItem.setName(fieldElement.getValue().getAsString());
						menuItem.setState(-1);
					}
				}

				myMenus.add(menuItem);
			}
		}
		return myMenus;
	}

	/**
	 * Serialization implementation; converts an array of MenuItems into a menu JSON string
	 */
	@Override
	public JsonElement serialize(MenuItem[] menuItems, Type type, JsonSerializationContext context)
	{
		JsonArray result = new JsonArray();
		for (MenuItem item : menuItems)
		{
			result.add(getMenuItem(item, type, context));
		}
		return result;
	}

	/**
	 * 
	 * @param menuItem
	 *            MenuItem instance
	 * @param type
	 *            to pass
	 * @param context
	 *            to
	 * @return
	 */
	private JsonObject getMenuItem(MenuItem menuItem, Type type, JsonSerializationContext context)
	{

		JsonObject obj = new JsonObject();
		if (menuItem.getState() == -1)
		{
			// A state of -1 indicates that this is a reference.
			// Generate custom JSON:
			obj.addProperty("_reference", menuItem.getName());
		}
		else
		{
			obj.addProperty("id", menuItem.getId());
			obj.addProperty("name", menuItem.getName());
			obj.addProperty("value", menuItem.getValue());
			obj.addProperty("type", menuItem.getType());
			obj.addProperty("active", menuItem.isActive());

			ArrayList<Object> children = menuItem.getChildren();
			if (children != null)
			{
				MenuItem[] childItems = new MenuItem[children.size()];
				for (int i = 0; i < childItems.length; i++)
				{
					childItems[i] = (MenuItem) children.get(i);
				}
				obj.add("children", serialize(childItems, type, context));
			}
			obj.addProperty("state", menuItem.getState());
		}
		return obj;
	}

}
