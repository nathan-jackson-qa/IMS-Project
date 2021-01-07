package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.CustomerController;
import com.qa.ims.persistence.dao.CustomerDAO;
import com.qa.ims.persistence.domain.Customer;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

	@Mock
	private Utils utils;

	@Mock
	private CustomerDAO dao;

	@InjectMocks
	private CustomerController controller;

	@Test
	public void testCreate() {
		final String firstName = "barry", surname = "scott";
		final Customer created = new Customer(firstName, surname);

		when(utils.getString()).thenReturn(firstName, surname);
		when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());
		verify(utils, times(2)).getString();
		verify(dao, times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Harry", "Harrison"));

		when(dao.readAll()).thenReturn(customers);

		assertEquals(customers, controller.readAll());
		verify(dao, times(1)).readAll();
	}

	@Test
	public void testRead() {
		final Customer customer = new Customer(1L, "Harry", "Harrison");
		
		when(utils.getLong()).thenReturn(1L);
		when(dao.read(1L)).thenReturn(customer);
		
		assertEquals(customer, controller.read());
		verify(dao, times(1)).read(1L);
	}
	
	@Test
	public void testFalseRead() {
		final Customer customer = new Customer(1L, "Harry", "Harrison");
		
		when(utils.getLong()).thenReturn(10L);
		when(dao.read(10L)).thenReturn(null);
		
		assertNotEquals(customer, controller.read());
		verify(dao, times(1)).read(10L);
	}
	
	@Test
	public void testUpdate() {
		Customer updated = new Customer(1L, "chris", "perrins");

		when(utils.getLong()).thenReturn(updated.getId());
		when(utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
		when(dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());
		verify(utils, times(1)).getLong();
		verify(utils, times(2)).getString();
		verify(dao, times(1)).update(updated);
	}
	
	@Test public void testFalseUpdate() {		
		Customer updated = new Customer(10L, "Manny", "Mammoth");
		
		when(utils.getLong()).thenReturn(10L);
		when(utils.getString()).thenReturn("Manny","Mammoth");
		when(dao.update(updated)).thenReturn(null);
		
		assertNotEquals(updated, controller.update());
		verify(utils, times(1)).getLong();
		verify(utils, times(2)).getString();
		verify(dao, times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		when(utils.getLong()).thenReturn(ID);
		when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, controller.delete());
		verify(utils, times(1)).getLong();
		verify(dao,times(1)).delete(ID);
	}
	
	@Test
	public void testFalseDelete() {
		final long ID = 100L;

		when(utils.getLong()).thenReturn(ID);
		when(dao.delete(ID)).thenReturn(0);

		assertNotEquals(1, controller.delete());
		verify(utils, times(1)).getLong();
		verify(dao, times(1)).delete(ID);
	}

}
