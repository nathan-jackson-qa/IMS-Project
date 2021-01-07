package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOTest {
	
	private final ItemDAO dao = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect("root", "root");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(6L, "Strawberry", 100L, 2.99);
		assertEquals(created, dao.create(created));		
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Mango", 100, 0.99));
		items.add(new Item(2L, "Apple", 100, 1.5));
		items.add(new Item(3L, "Banana", 100, 2.2));
		items.add(new Item(4L, "Orange", 100, 3));
		items.add(new Item(5L, "Kiwi", 100, 4));
		
		assertEquals(items, dao.readAll());
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Watermelon", 45L, 4.99);
		assertEquals(updated, dao.update(updated));
	}
	
	@Test
	public void testDelete() {
		assertEquals(1, dao.delete(1));
	}
}
