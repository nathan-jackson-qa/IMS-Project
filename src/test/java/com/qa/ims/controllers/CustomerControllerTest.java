package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

		Mockito.when(utils.getString()).thenReturn(firstName, surname);
		Mockito.when(dao.create(created)).thenReturn(created);

		assertEquals(created, controller.create());
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}

	@Test
	public void testReadAll() {
		List<Customer> customers = new ArrayList<>();
		customers.add(new Customer(1L, "Harry", "Harrison"));

		Mockito.when(dao.readAll()).thenReturn(customers);

		assertEquals(customers, controller.readAll());
		Mockito.verify(dao, Mockito.times(1)).readAll();
	}

	@Test
	public void testRead() {
		final Customer customer = new Customer(1L, "Harry", "Harrison");
		
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.readCustomer(1L)).thenReturn(customer);
		
		assertEquals(customer, controller.read());
		Mockito.verify(dao, Mockito.times(1)).readCustomer(1L);
	}
	
	@Test
	public void testFalseRead() {
		final Customer customer = new Customer(1L, "Harry", "Harrison");
		
		Mockito.when(utils.getLong()).thenReturn(10L);
		Mockito.when(dao.readCustomer(10L)).thenReturn(null);
		
		assertNotEquals(customer, controller.read());
		Mockito.verify(dao, Mockito.times(1)).readCustomer(10L);
	}
	
	@Test
	public void testUpdate() {
		Customer updated = new Customer(1L, "chris", "perrins");

		Mockito.when(utils.getLong()).thenReturn(updated.getId());
		Mockito.when(utils.getString()).thenReturn(updated.getFirstName(), updated.getSurname());
		Mockito.when(dao.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).update(updated);
	}
	
	@Test public void testFalseUpdate() {		
		Customer updated = new Customer(10L, "Manny", "Mammoth");
		
		Mockito.when(utils.getLong()).thenReturn(10L);
		Mockito.when(utils.getString()).thenReturn("Manny","Mammoth");
		Mockito.when(dao.update(updated)).thenReturn(null);
		
		assertNotEquals(updated, controller.update());
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(2)).getString();
		Mockito.verify(dao, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);

		assertEquals(1L, controller.delete());
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
	
	@Test
	public void testFalseDelete() {
		final long ID = 100L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(0);

		assertNotEquals(1, controller.delete());
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}

}
