package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class ItemTest {

	private final Item item = new Item(1L, "Apple", 100L, 9.99);
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Item.class).verify();
	}
	
	@Test
	public void testGetID() {
		long id = 1L;
		assertEquals(id, item.getId());
	}
	
	@Test
	public void testSetID() {
		long newID = 2L;
		item.setId(newID);
		assertEquals(newID, item.getId());
	}
	
	@Test
	public void testGetStock() {
		assertEquals(100L, item.getStock());
	}
	
	@Test
	public void testSetStock() {
		item.setStock(50L);
		assertEquals(50L, item.getStock());
	}
	
	@Test
	public void testGetCost() {
		assertEquals(9.99, item.getCost(), 10.);
	}
	
	@Test
	public void testSetCost() {
		item.setCost(1.37);
		assertEquals(1.37, item.getCost(), 1.);
	}
	
	@Test
	public void testGetName() {
		Item secondItem = new Item("Banana", 90L, 10.82);
		assertEquals("Banana", secondItem.getItemName());
	}
	
	@Test
	public void testSetName() {
		item.setItemName("Banana");
		assertEquals("Banana", item.getItemName());
	}
	
	@Test
	public void testToString() {
		assertEquals("ID:1, Item Name:Apple, Stock:100, Cost:9.99", item.toString());
	}
	
}

