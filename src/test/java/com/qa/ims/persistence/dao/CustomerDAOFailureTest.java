package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.DBUtils;

public class CustomerDAOFailureTest {
	
	private final CustomerDAO DAO = new CustomerDAO();

	@Before
	public void setup() {
		DBUtils.connect("root", "password");
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testCreate() {
		final Customer created = new Customer(6L, "Alex", "Allison");
		assertEquals(null, DAO.create(created));
	}
	
	@Test
	public void testReadAll() {
		
		assertEquals(new ArrayList<>(),DAO.readAll());
	}
	
	@Test
	public void testReadLatest() {
		assertEquals(null,DAO.readLatest());
	}
	
	@Test
	public void testRead() {
		assertEquals(null, DAO.readCustomer(1L));
	}
	
	@Test
	public void testUpdate() {
		final Customer updated = new Customer(-1L, "Chris", "Perrins");
		assertEquals(null, DAO.update(updated));
	}
	
	
	@Test
	public void testDelete() {
		assertEquals(0, DAO.delete(1));
	}
	
}
