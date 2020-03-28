package edu.ycp.CS320_Minotaurs_Labyrinth.classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ItemTest {

private Item testItem;
	
	@Before
	public void setUp() {
		
		testItem = new Item("test", 1, true, false, true, 10, "testItem", "test");
		
	}
	
	@Test
	public void testDescriptionMethods() {
		
		assertEquals("test", testItem.getDescription());
	}
	
	@Test
	public void testEffectMethods() {
		
		assertEquals(1, testItem.getEffect());
	}
	
	@Test
	public void testFlammableMethods() {
		
		assertTrue(testItem.getFlammable());
	}
	
	@Test
	public void testLitMethods() {
		testItem.setLit(true);
		assertTrue(testItem.getLit());
	}
	
	@Test
	public void testThrowableMethods() {
		assertTrue(testItem.getThrowable());
	}
	
	@Test
	public void testNameMethods() {
		testItem.setName("test");
		assertEquals("test",testItem.getName());
	}
	
	@Test
	public void testVarietyMethods() {
		assertEquals("test", testItem.getVariety());
	}
	
	@Test
	public void testPrintDescription() {
		fail("Not yet implemented");
		
	}
	
	@Test
	public void testAddEffect() {
		
		fail("Not yet implemented");
	}
	

}
