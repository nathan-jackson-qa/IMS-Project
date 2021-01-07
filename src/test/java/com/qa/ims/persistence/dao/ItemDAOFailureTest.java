package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

public class ItemDAOFailureTest {

	private final ItemDAO dao = new ItemDAO();
	
	@Before
	public void setup() {
		DBUtils.connect("root", "password");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Item created = new Item(6L, "Strawberry", 100L, 2.99);
		assertEquals(null, dao.create(created));
	}
	
	@Test
	public void testReadAll() {
		
		assertEquals(new ArrayList<>(),dao.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(null,dao.readLatest());
	}
	
	@Test
	public void testRead() {
		assertEquals(null, dao.read(1L));
	}
	
	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Satsuma", 2L, 0.99);
		assertEquals(null, dao.update(updated));
	}
	
	
	@Test
	public void testDelete() {
		assertEquals(0, dao.delete(-1));
	}
}
