package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class OrderTest {

	Order order;
	HashMap<Long,Long> items = new HashMap<Long,Long>(); 
	
	@Before
	public void setup() {
		items.put(1L, 8L);
		items.put(2L, 10L);
		order = new Order(1L, 2L, 0.11, "01/01/2000", items);
	}
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order.class).verify();
	}
	
	@Test
	public void testGetOrderID() {
		Order newOrder = new Order(1L, 2L, items);
		assertEquals(1L, newOrder.getOrder_id());
	}
	
	@Test
	public void testSetOrderID() {
		order.setOrder_id(5L);
		assertEquals(5L, order.getOrder_id());
	}
	
	@Test
	public void testGetCustomerID() {
		assertEquals(2L, order.getCustomer_id());
	}
	
	@Test
	public void testSetCustomerID() {
		order.setCustomer_id(4L);
		assertEquals(4L, order.getCustomer_id());
	}
	
	@Test 
	public void testGetItemsOrdered() {
		Order newOrder = new Order(4L, items);
		assertEquals(items, newOrder.getItemsOrdered());
	}
	
	@Test
	public void testSetItemsOrdered() {
		HashMap<Long, Long> newItems = new HashMap<Long,Long>();
		newItems.put(3L, 3L);
		newItems.put(5L, 5L);
		newItems.put(4L,  20L);
		order.setItemsOrdered(newItems);
		assertEquals(newItems, order.getItemsOrdered());
	}
	
	@Test
	public void testAddItem() {
		items.put(5L, 5L);
		order.addItem(5L, 5L);
		assertEquals(items, order.getItemsOrdered());
	}
	
	@Test
	public void testDeleteItem() {
		items.remove(2L);
		order.deleteItem(2L);
	}
	
	@Test
	public void testGetDateOrdered() {
		Order newOrder = new Order(2L, 3L, "08/04/2020");
		assertEquals("08/04/2020",newOrder.getDateOrdered());
	}
	
	@Test
	public void testSetDateOrdered() {
		order.setDateOrdered("04/04/2020");
		assertEquals("04/04/2020", order.getDateOrdered());
	}
	
	@Test
	public void testGetPrice() {
		Order pricedOrder = new Order(1L, 9.99);
		assertEquals(9.99, pricedOrder.getPrice(), 10.);
	}
	
	@Test
	public void testSetPrice() {
		order.setPrice(2.55);
		assertEquals(2.55, order.getPrice(), 2.6);
	}
	
	@Test
	public void testToString() {
		order.setDateOrdered("14/01/2021");
		assertEquals("Order ID: 1, Customer ID: 2, Date Ordered: 14/01/2021", order.toString());
	}
	
	@Test
	public void testDisplayPrice() {
		order.setPrice(1.99);
		assertEquals("Order ID: 1, Total Price: £1.99", order.displayPrice());
	}
	
}
