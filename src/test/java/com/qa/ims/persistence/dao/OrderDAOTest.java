package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOTest {

	private final OrderDAO dao = new OrderDAO();
	
	@Before 
	public void setup() {
		DBUtils.connect("root", "root");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final long custID = 1L;
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		items.put(2L, 100L);
		items.put(3L, 41L);
		final Order created = new Order(6L, custID, items);
		
		assertEquals(created.getOrder_id(), dao.create(created).getOrder_id());
		assertEquals(created.getCustomer_id(), dao.create(created).getCustomer_id());
	}
	
	@Test
	public void readAll() {
		List<Order> orders = new ArrayList<>();
		orders.add(new Order(1L, 1L, "2020-12-08 12:32:09"));
		orders.add(new Order(2L, 2L, "2020-12-11 14:07:22"));
		orders.add(new Order(3L, 3L, "2020-12-15 07:24:01"));
		orders.add(new Order(4L, 4L, "2020-12-19 21:57:43"));
		orders.add(new Order(5L, 5L, "2020-12-22 19:34:59"));
		
		assertNotEquals(new ArrayList<>(), dao.readAll());
	}
	
	@Test
	public void testReadOrder() {
		assertNotEquals(null, dao.read(1L));
	}
	
	
	@Test
	public void testAddToOrder() {
		assertEquals(1, dao.addToOrder(1L, 2L, 10L));
	}
	
	@Test
	public void testRemoveFromOrder() {
		assertEquals(1, dao.removeFromOrder(1L, 1L));
	}
	
	@Test
	public void testTotalPrice() {
		final Order order = new Order(1L, 9.35);
		assertEquals(order.getOrder_id(), dao.totalPrice(1L).getOrder_id());
		assertEquals(order.getOrder_id(), dao.totalPrice(1L).getOrder_id());
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, dao.delete(1L));
	}
	
	
}
