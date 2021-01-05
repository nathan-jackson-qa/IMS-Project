package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAOFailureTest {

	private final OrderDAO dao = new OrderDAO();

	@Before
	public void setup() {
		DBUtils.connect("root", "password");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Order created = new Order(6L, 5L, "2020-12-08 12:32:09");
		assertEquals(null, dao.create(created));
	}
	
	@Test
	public void testReadAll() {
		assertEquals(new ArrayList<>(), dao.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(null, dao.readLatest());
	}
	
	@Test
	public void testRead() {
		assertEquals(null, dao.readOrder(1L));
	}
	
	@Test
	public void testUpdate() {
		final Order updated = new Order(1L, 3L, "2020-12-09 04:21:13");
		assertEquals(null, dao.update(updated));
	}
	
	
	@Test
	public void testDelete() {
		assertEquals(0, dao.delete(1));
	}
	
	@Test
	public void testAddToOrder() {
		assertEquals(0, dao.addToOrder(1L, 1L, 1L));
	}
	
	@Test
	public void testRemoveFromOrder() {
		assertEquals(0, dao.removeFromOrder(1, 1));
	}
	
	@Test
	public void testTotalPrice() {
		assertEquals(null, dao.totalPrice(1L));
	}
}
