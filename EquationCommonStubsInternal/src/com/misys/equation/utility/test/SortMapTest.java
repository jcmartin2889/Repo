package com.misys.equation.utility.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import com.misys.equation.common.utilities.Toolbox;

/**
 * JUnit test to verify sorting functionality of com.misys.equation.common.utilities.Toolbox sortHashMapByValues method.
 */
public class SortMapTest extends TestCase
{
	//This attribute is used to store cvs version information.
	public static final String _revision = "$Id: SortMapTest.java 11310 2011-06-27 12:20:21Z ESTHER.WILLIAMS $";
	private final Map<String, String> map = new HashMap<String, String>();

	public SortMapTest()
	{
	}

	/**
	 * Test actual sort behaviour
	 */
	public void test()
	{
		map.put("A", "A");
		map.put("C", "C");
		map.put("B", "B");
		map.put("E", "E");
		map.put("D", "D");

		Map<String, String> sortedMap = Toolbox.sortHashMapByValues(map);

		Set<String> sortedKeySet = (sortedMap.keySet());
		Set<String> expectedKeys = new HashSet<String>();
		expectedKeys.add("A");
		expectedKeys.add("B");
		expectedKeys.add("C");
		expectedKeys.add("D");
		expectedKeys.add("E");

		// Set comparison does not care about ordering
		assertEquals(sortedKeySet, expectedKeys);

		Object[] sortedValueSet = (sortedMap.values()).toArray();
		Map<String, String> expectedValuesMap = new LinkedHashMap<String, String>();
		expectedValuesMap.put("A", "A");
		expectedValuesMap.put("B", "B");
		expectedValuesMap.put("C", "C");
		expectedValuesMap.put("D", "D");
		expectedValuesMap.put("E", "E");

		Object[] expectedValues = expectedValuesMap.values().toArray();

		for (int x = 0; x < expectedValues.length; x++)
		{
			assertEquals(sortedValueSet[x], expectedValues[x]);
		}
	}

	/**
	 * The sortHashMapByValues method must not change the passed in map
	 * 
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public void testSafeToCall() throws InstantiationException, IllegalAccessException
	{
		map.put("E", "World");
		map.put("C", "Hello");
		map.put("B", "World");
		map.put("A", "Hello");
		map.put("D", "World");

		Map<String, String> backupCopy = new HashMap<String, String>();
		backupCopy.putAll(map);

		Toolbox.sortHashMapByValues(map);

		assertEquals(backupCopy, map);
	}

	/**
	 * The sortHashMapByValues method must cater for duplicate values. Where duplicate values exist, then the entries are also
	 * sorted by the key
	 */
	public void testDuplicateValues()
	{
		map.put("E", "World");
		map.put("C", "Hello");
		map.put("B", "World");
		map.put("A", "Hello");
		map.put("D", "World");

		Map<String, String> sortedMap = Toolbox.sortHashMapByValues(map);

		Set<String> sortedKeySet = (sortedMap.keySet());

		Set<String> expectedKeys = new HashSet<String>();
		expectedKeys.add("A");
		expectedKeys.add("C");
		expectedKeys.add("B");
		expectedKeys.add("D");
		expectedKeys.add("E");

		// Set comparison does not care about ordering
		assertEquals(expectedKeys, sortedKeySet);

		Object[] sortedValueSet = (sortedMap.values()).toArray();
		Map<String, String> expectedValuesMap = new LinkedHashMap<String, String>();
		expectedValuesMap.put("A", "Hello");
		expectedValuesMap.put("C", "Hello");
		expectedValuesMap.put("B", "World");
		expectedValuesMap.put("D", "World");
		expectedValuesMap.put("E", "World");

		Object[] expectedValues = expectedValuesMap.values().toArray();

		for (int x = 0; x < expectedValues.length; x++)
		{
			assertEquals(expectedValues[x], sortedValueSet[x]);
		}
	}

}
