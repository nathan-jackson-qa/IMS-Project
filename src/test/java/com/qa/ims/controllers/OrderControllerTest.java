package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

	@Mock
	private Utils utils;
	
	@Mock
	private OrderDAO dao;
	
	@InjectMocks
	private OrderController controller;
	
	@Test
	public void testCreate(){
		final long custID = 1L;
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		items.put(2L, 100L);
		items.put(3L, 41L);
		final Order ordered = new Order(custID, items);
	
		when(utils.getLong()).thenReturn(1L, 2L, 100L, 3L, 41L);
		when(utils.getString()).thenReturn("yes", "no");
		when(dao.create(any(Order.class))).thenReturn(ordered);
		
		assertEquals(ordered, controller.create());
		verify(utils, times(5)).getLong();
		verify(utils, times(2)).getString();
		verify(dao, times(1)).create(any(Order.class));
	}
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 2L, ""));
		
		when(dao.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		verify(dao, times(1)).readAll();
	}
	
	@Test
	public void testReadByID() {
		Order order = new Order(1L, 1L, "");
		
		when(utils.getString()).thenReturn("SEARCH");
		when(utils.getLong()).thenReturn(1L);
		when(dao.readOrder(1L)).thenReturn(order);
		
		assertEquals(order, controller.read());
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).readOrder(1L);
		
	}
	
	@Test
	public void testReadByFalseID() {
		Order order = new Order(1L, 1L, "");
		
		when(utils.getString()).thenReturn("SEARCH");
		when(utils.getLong()).thenReturn(100L);
		when(dao.readOrder(100L)).thenReturn(null);
		
		assertNotEquals(order, controller.read());
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).readOrder(100L);
	}
	
	@Test
	public void testReadPrice() {
		Order order = new Order(1L, 1L, "");
		
		when(utils.getString()).thenReturn("Price");
		when(utils.getLong()).thenReturn(1L);
		when(dao.totalPrice(1L)).thenReturn(order);
		
		assertEquals(order, controller.read());
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).totalPrice(1L);
	}
	
	@Test
	public void testFalseReadPrice() {
		Order order = new Order(1L, 1L, "");
		
		when(utils.getString()).thenReturn("Price");
		when(utils.getLong()).thenReturn(100L);
		when(dao.totalPrice(100L)).thenReturn(null);
		
		assertNotEquals(order, controller.read());
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).totalPrice(100L);
	}
	
	@Test
	public void testFalseRead() {
		when(utils.getString()).thenReturn("None", "Return");
		
		assertEquals(null, controller.read());
		verify(utils, times(2)).getString();
	}
	
	@Test
	public void testAddItem() {
		final long orderID = 1L, itemID = 4L, quantity = 6L;
		final long custID = 1L;
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		items.put(2L, 100L);
		items.put(3L, 41L);
		items.put(itemID, quantity);
		final Order order = new Order(custID, items);
		
		when(utils.getLong()).thenReturn(orderID, itemID, quantity);
		when(utils.getString()).thenReturn("ADD");
		when(dao.readOrder(orderID)).thenReturn(order);
		when(dao.addToOrder(orderID, itemID, quantity)).thenReturn(1);
	
		
		assertEquals(null, controller.update());
		verify(utils, times(3)).getLong();
		verify(dao, times(1)).readOrder(orderID);
		verify(dao, times(1)).addToOrder(orderID, itemID, quantity);
	}
	
	@Test
	public void testFalseAddItem() {
		final long orderID = 100L, itemID = 4L, quantity = 6L;
		final long custID = 1L;
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		items.put(2L, 100L);
		items.put(3L, 41L);
		items.put(itemID, quantity);
		final Order order = new Order(custID, items);
		
		when(utils.getLong()).thenReturn(orderID, itemID, quantity);
		when(utils.getString()).thenReturn("ADD");
		when(dao.readOrder(orderID)).thenReturn(order);
		when(dao.addToOrder(orderID, itemID, quantity)).thenReturn(0);
	
		
		assertEquals(null, controller.update());
		verify(utils, times(3)).getLong();
		verify(dao, times(1)).readOrder(orderID);
		verify(dao, times(1)).addToOrder(orderID, itemID, quantity);
	}
	
	@Test
	public void testRemoveItem() {
		final long orderID = 1L, itemID = 4L;
		final long custID = 1L;
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		items.put(2L, 100L);
		items.put(3L, 41L);
		final Order order = new Order(custID, items);
		
		when(utils.getLong()).thenReturn(orderID, itemID);
		when(utils.getString()).thenReturn("REMOVE");
		when(dao.readOrder(orderID)).thenReturn(order);
		when(dao.removeFromOrder(orderID, itemID)).thenReturn(1);
	
		
		assertEquals(null, controller.update());
		verify(utils, times(2)).getLong();
		verify(dao, times(1)).readOrder(orderID);
		verify(dao, times(1)).removeFromOrder(orderID, itemID);
	}
	
	@Test
	public void testFalseRemoveItem() {
		final long orderID = 1L, itemID = 4L;
		final long custID = 1L;
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		items.put(2L, 100L);
		items.put(3L, 41L);
		final Order order = new Order(custID, items);
		
		when(utils.getLong()).thenReturn(orderID, itemID);
		when(utils.getString()).thenReturn("REMOVE");
		when(dao.readOrder(orderID)).thenReturn(order);
		when(dao.removeFromOrder(orderID, itemID)).thenReturn(0);
	
		
		assertEquals(null, controller.update());
		verify(utils, times(2)).getLong();
		verify(dao, times(1)).readOrder(orderID);
		verify(dao, times(1)).removeFromOrder(orderID, itemID);
	}
	
	@Test
	public void testUpdateWrongID() {
		when(utils.getLong()).thenReturn(100L);
		when(dao.readOrder(100L)).thenReturn(null);
		
		assertEquals(null, controller.update());
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).readOrder(100L);
	}
	
	@Test
	public void testCancelledUpdate() {
		final long custID = 1L;
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		items.put(2L, 100L);
		items.put(3L, 41L);
		final Order order = new Order(custID, items);
		
		when(utils.getLong()).thenReturn(1L);
		when(utils.getString()).thenReturn("wrong", "CANCEL");
		when(dao.readOrder(1L)).thenReturn(order);
		
		assertEquals(null, controller.update());
		verify(utils, times(2)).getLong();
		verify(dao, times(2)).readOrder(1L);
	}
	
	@Test
	public void testDelete() {
		final long orderID = 1L;
		
		when(utils.getLong()).thenReturn(orderID);
		when(dao.delete(orderID)).thenReturn(1);
		
		assertEquals(1, controller.delete());
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).delete(orderID);
	}
	
	@Test
	public void testFalseDelete() {
		final long orderID = -1L;
		
		when(utils.getLong()).thenReturn(orderID);
		when(dao.delete(orderID)).thenReturn(0);
		
		assertNotEquals(1, controller.delete());
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).delete(orderID);
	}
}
