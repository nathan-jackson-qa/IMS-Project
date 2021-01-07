package com.qa.ims.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class CustomerTest {

	private final Customer customer = new Customer(1L, "Mark", "Marky");
	
	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}
	
	@Test
	public void testGetID() {
		long id = 1L;
		assertEquals(Long.valueOf(id), customer.getId());
	}
	
	@Test
	public void testSetID() {
		long newID = 2L;
		customer.setId(newID);
		assertEquals(Long.valueOf(newID), customer.getId());
	}
	
	@Test
	public void testGetFirstName() {
		Customer second = new Customer("Andrew", "Andrews");
		assertEquals("Andrew", second.getFirstName());
	}
	
	@Test
	public void testGetSurname() {
		Customer second = new Customer("Andrew", "Andrews");
		assertEquals("Andrews", second.getSurname());
	}
	
	@Test
	public void testToString() {
		assertEquals("Customer ID:1 First Name:Mark Last Name:Marky", customer.toString());
	}
	
	
	
	
	
	

}
