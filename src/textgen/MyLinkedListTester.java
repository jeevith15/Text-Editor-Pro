/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 * @modified by Dong Pei
 * @modified on May. 2017
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		
		// add null return exception
		try {
			emptyList.add(null);
			fail("Check add null value");
		}
		catch (NullPointerException e) {
			
		}
		
		// regular cases
		boolean addShort = shortList.add("C");
		assertEquals("Check add more", true, addShort);
		assertEquals("Check add more", "C", shortList.get(2));
		assertEquals("Check add more", 3, shortList.size());
		
		// add into a empty list
		boolean addEmpty = emptyList.add(2);
		assertEquals("Check add more", true, addEmpty);
		assertEquals("Check add more", (Integer)2, emptyList.get(0));
		assertEquals("Check add more", 1, emptyList.size());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Check size method", 2, shortList.size());
		assertEquals("Check size method", 0, emptyList.size());
		assertEquals("Check size method", 3, list1.size());
		assertEquals("Check size method", 3, list1.size());
		assertEquals("Check size method", 10, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		// add node with a null as value
		try {
			shortList.add(1, null);
			fail("Check add null value");
		}
		catch (NullPointerException e) {
			
		}
		
		// add a node out of bond index 
		try {
			shortList.add(6, null);
			fail("Check add out of bond");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// add next to head
		shortList.add(0, "C");
		assertEquals("Check add more", "C", shortList.get(0));
		assertEquals("Check add more", 3, shortList.size());
		
		// add next to tail
		shortList.add(2, "D");
		assertEquals("Check add more", "D", shortList.get(2));
		assertEquals("Check add more", 4, shortList.size());

		
		// add in the middle
		shortList.add(1, "E");
		assertEquals("Check add more", "C", shortList.get(0));
		assertEquals("Check add more", 5, shortList.size());

		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		try {
			shortList.set(1, null);
			fail("Check add null value");
		}
		catch (NullPointerException e) {
			
		}
		
		// add a node out of bond index 
		try {
			shortList.set(6, "V");
			fail("Check add out of bond");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// add next to head
		shortList.set(0, "C");
		assertEquals("Check set method", "C", shortList.get(0));
		assertEquals("Check set method", "B", shortList.get(1));
		assertEquals("Check set method", 2, shortList.size());
	    
	}
}
