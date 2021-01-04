package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.OrderController;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Item;
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
	
//	@Test
//	public void testCreate() {
//		final long custID = 1L;
//		HashMap<Long,Long> items = new HashMap<Long,Long>();
//		items.put(1L,10L);
//		items.put(2L, 8L);
//		Order ordered = new Order(custID, items);
//	
//		Mockito.when(utils.getLong()).thenReturn(1L, 1L, 10L, 2L, 8L);
//		Mockito.when(utils.getString()).thenReturn("yes", "no");
//		Mockito.when(dao.create(ordered)).thenReturn(ordered);
//		
//		assertEquals(ordered, controller.create());
//		Mockito.verify(utils, Mockito.times(5)).getLong();
//		Mockito.verify(utils, Mockito.times(2)).getString();
//		Mockito.verify(dao, Mockito.times(1)).create(ordered);
//	}
	
	@Test
	public void testReadAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 2L, ""));
		
		Mockito.when(dao.readAll()).thenReturn(orders);
		
		assertEquals(orders, controller.readAll());
		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testReadByID() {
		Order order = new Order(1L, 1L, "");
		
		Mockito.when(utils.getString()).thenReturn("SEARCH");
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.readOrder(1L)).thenReturn(order);
		
		assertEquals(order, controller.read());
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).readOrder(1L);
		
	}
	
	@Test
	public void testReadByFalseID() {
		Order order = new Order(1L, 1L, "");
		
		Mockito.when(utils.getString()).thenReturn("SEARCH");
		Mockito.when(utils.getLong()).thenReturn(100L);
		Mockito.when(dao.readOrder(100L)).thenReturn(null);
		
		assertNotEquals(order, controller.read());
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).readOrder(100L);
	}
	
	@Test
	public void testReadPrice() {
		Order order = new Order(1L, 1L, "");
		
		Mockito.when(utils.getString()).thenReturn("Price");
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.totalPrice(1L)).thenReturn(order);
		
		assertEquals(order, controller.read());
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).totalPrice(1L);
	}
	
	@Test
	public void testFalseReadPrice() {
		Order order = new Order(1L, 1L, "");
		
		Mockito.when(utils.getString()).thenReturn("Price");
		Mockito.when(utils.getLong()).thenReturn(100L);
		Mockito.when(dao.totalPrice(100L)).thenReturn(null);
		
		assertNotEquals(order, controller.read());
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).totalPrice(100L);
	}
	
	@Test
	public void testFalseRead() {
		
		Mockito.when(utils.getString()).thenReturn("None", "Return");
		assertEquals(null, controller.read());
	}
}
