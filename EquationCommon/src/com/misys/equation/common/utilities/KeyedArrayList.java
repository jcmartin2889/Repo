package com.misys.equation.common.utilities;

import java.util.ArrayList;

/**
 * This collection class is a keyed ArrayList.
 * 
 * This allows access to the contained items either by list order (for iteration) or by keyed access. Various helper methods
 * relevant to EQ4 have been added to facilitate re-sequencing items (moving up or down).
 * 
 * This is intended for use in the bean collections to encapsulate the parallel key / item collections
 * 
 * @author PERKINJ1
 * 
 * @param <T>
 */
public class KeyedArrayList<T> extends ArrayList<T> implements Iterable<T>
{

	// This attribute is used to store cvs version information.
	public static final String _revision = "$Id: KeyedArrayList.java 8910 2010-08-26 15:25:20Z MACDONP1 $";
	private final ArrayList<Object> keys = new ArrayList<Object>();

	/** version number for serialisation */
	private static final long serialVersionUID = 1L;

	/**
	 * Adds an item to the collection
	 * 
	 * @param item
	 * @param key
	 * @throws IllegalArgumentException
	 */
	public void add(T item, Object key)
	{
		if (keys.contains(key))
		{
			throw new IllegalArgumentException("Duplicate key " + key);
		}
		super.add(item);
		// Maintain the parallel list of keys
		keys.add(key);
	}

	/**
	 * Adds an item to the collection
	 * 
	 * @param index
	 * @param item
	 * @param key
	 * @throws IllegalArgumentException
	 */
	public void add(int index, T item, Object key)
	{
		if (keys.contains(key))
		{
			throw new IllegalArgumentException("Duplicate key" + key);
		}
		// Allow the underlying collection to throw an exception if
		// the supplied index is invalid:
		this.add(index, item);
		// Maintain the parallel list of keys
		keys.add(index, key);
	}

	@Override
	public boolean contains(Object key)
	{
		return keys.contains(key);
	}

	public T get(Object key)
	{
		T result = null;
		int index = keys.indexOf(key);
		if (index > -1)
		{
			result = this.get(index);
		}
		return result;
	}

	/**
	 * Overrides the default implementation
	 * 
	 * This method is used by pages which populate a tree view, in order to find a tree item by indexes.
	 */
	@Override
	public int indexOf(Object key)
	{
		return keys.indexOf(key);
	}

	/**
	 * Removes an item from the collection
	 * 
	 * @param key
	 * @return boolean true if the item was removed (and therefore previously existed)
	 */
	@Override
	public boolean remove(Object key)
	{
		boolean found = false;
		int index = keys.indexOf(key);
		if (index != -1)
		{
			found = true;
			this.remove(index);
			keys.remove(index);
		}
		return found;
	}

	/**
	 * Moves the specified item down
	 * 
	 * @param key
	 * @return boolean - true if the operation succeeded, false if the key does not exist
	 * @throws ArrayIndexOutOfBoundsException
	 *             if the item is already last in the list
	 */
	public boolean moveDown(Object key)
	{
		boolean found = false;
		int index = keys.indexOf(key);
		if (index != -1)
		{
			// Can't move last item down
			if (index == keys.size())
			{
				throw new ArrayIndexOutOfBoundsException("Item is already the last item in the list");
			}
			else
			{
				found = true;

				T removed = this.remove(index);
				keys.remove(index);
				// Now re-add at required position:
				keys.add(index + 1, key);
				this.add(index + 1, removed);
			}
		}
		return found;
	}

	/**
	 * Moves the specified item below the specified item
	 * 
	 * @param itemKey
	 *            the Key of the item to move
	 * @param positionKey
	 *            The key of the item to move below. Specify null to move the item to the top of the list
	 * @return boolean - true if the operation succeeded, false if the key does not exist
	 * 
	 * @throws ArrayIndexOutOfBoundsException
	 *             if the item is already last in the list
	 */
	public boolean moveBelow(Object itemKey, Object positionKey)
	{
		boolean found = false;

		int itemIndex = keys.indexOf(itemKey);
		int positionIndex = 0;
		if (positionKey != null)
		{
			positionIndex = keys.indexOf(positionKey);
		}
		if (itemIndex != -1 && positionIndex != -1)
		{
			found = true;
			T item = this.remove(itemIndex);
			keys.remove(itemIndex);

			// Find the relative item again (removing the moved item may have changed its position)
			if (positionKey != null)
			{
				positionIndex = keys.indexOf(positionKey) + 1;
			}

			// Number of items in the list is now
			// Now re-add at required position:
			keys.add(positionIndex, itemKey);
			this.add(positionIndex, item);
		}
		return found;
	}

	/**
	 * Moves the item specified up by one position
	 * 
	 * @param key
	 * @return boolean - true if move successful, false if key does not exist
	 * @throws ArrayIndexOutOfBoundsException
	 *             if the item is already first in the list
	 */
	public boolean moveUp(Object key)
	{
		boolean found = false;
		int index = keys.indexOf(key);
		if (index != -1)
		{
			// Can't move first item up
			if (index != 0)
			{
				found = true;

				T removed = this.remove(index);
				keys.remove(index);
				// Now re-add at required position:
				keys.add(index - 1, key);
				this.add(index - 1, removed);
			}
			else
			{
				throw new ArrayIndexOutOfBoundsException("Item is first item in the list");
			}
		}
		return found;
	}

	// Added as a temporary measure
	public ArrayList<String> getKeys()
	{
		ArrayList<String> result = null;
		Object keysObject = keys;
		result = (ArrayList<String>) keysObject;
		return result;
	}
}
