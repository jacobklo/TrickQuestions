package test;

import hk.jlo.Libs.NullableArray;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NullableArrayTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAt() {
		NullableArray<Integer> arr = createNullableArray(10,1);
		Integer i = 2;
		assertTrue(arr.At(i) == 2);
	}
	
	@Test
	public void testAdd()
	{
		NullableArray<Integer> arr = createNullableArray(10,0);
		for (int i = 0 ; i <= 10 ; i++)
		{
			assertTrue(arr.contains(i));
		}
	}
	
	@Test
	public void testContains()
	{
		NullableArray<Integer> arr = createNullableArray(10,2);
		for (int i = 0 ; i <= 10 ; i=i+3)
		{
			assertTrue(arr.contains(new Integer(i)));
			assertFalse(arr.contains(new Integer(i+1)));
			assertFalse(arr.contains(new Integer(i+2)));
		}
	}
	
	@Test
	public void testEquals()
	{
		NullableArray<Integer> arr = createNullableArray(10,2);
		for (int i = 0 ; i <= 10 ; i=i+3)
		{
			assertTrue(arr.get(i).equals(new Integer(i)));
		}
		
	}
	
	@Test
	public void testRemove()
	{
		NullableArray<Integer> arr = createNullableArray(10,2);
		for (int i = 0 ; i <= 10 ; i=i+3)
		{
			assertTrue(arr.remove(i));
		}
		assertTrue(arr.size()==0);
		assertFalse(arr.remove(-1));
		assertFalse(arr.remove(1));
		
		NullableArray<Integer> arr2 = createNullableArray(10,2);
		for (int i = 0 ; i <= 10 ; i=i+3)
		{
			assertTrue(arr2.remove(new Integer(i)));
		}
		assertTrue(arr.size()==0);
	}
	
	@Test
	public void testResize()
	{
		NullableArray<Integer> arr = createNullableArray(1000000,0);
		for (int i = 0 ; i <= 1000000 ; i++)
		{
			assertTrue(arr.get(i).equals(new Integer(i)));
		}
	}
	
	///---Helper---///
	private NullableArray<Integer> createNullableArray(int howMany, int between)
	{
		NullableArray<Integer> newArray = new NullableArray<Integer>(Integer.class);
		for ( int i = 0 ; i <= howMany ; i=i+between+1)
			newArray.add(i,i);
		return newArray;
	}

}
