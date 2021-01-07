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

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {

	@Mock
	private Utils utils;
	
	@Mock
	private ItemDAO dao;
	
	@InjectMocks
	private ItemController controller;
	
	@Test 
	public void testCreate() {
		final String name = "Mango";
		final long stock = 10L;
		final double price = 1.99;
		final Item created = new Item(name,stock,price);
		
		when(utils.getString()).thenReturn(name);
		when(utils.getLong()).thenReturn(stock);
		when(utils.getDouble()).thenReturn(price);
		when(dao.create(created)).thenReturn(created);
		
		assertEquals(created, controller.create());
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getLong();
		verify(utils, times(1)).getDouble();
		verify(dao, times(1)).create(created);
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("Apple", 100L, 0.99));
		
		when(dao.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		verify(dao, times(1)).readAll();
	}
	
	@Test
	public void testRead() {
		final Item item = new Item(1L, "Banana", 100L, 1.50);
		
		when(utils.getLong()).thenReturn(1L);
		when(dao.read(1L)).thenReturn(item);
		
		assertEquals(item, controller.read());
		verify(dao, times(1)).read(1L);
	}
	
	@Test
	public void testFalseRead() {
		final Item item = new Item(1L, "Banana", 100L, 1.50);
		
		when(utils.getLong()).thenReturn(100L);
		when(dao.read(100L)).thenReturn(null);
		
		assertNotEquals(item, controller.read());
		verify(dao, times(1)).read(100L);
	}
	
	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "Kiwi", 85L, 0.72);
		
		when(utils.getLong()).thenReturn(updated.getId(), updated.getStock());
		when(utils.getString()).thenReturn(updated.getItemName());
		when(utils.getDouble()).thenReturn(updated.getCost());
		when(dao.update(updated)).thenReturn(updated);
		
		assertEquals(updated, controller.update());
		verify(utils, times(2)).getLong();
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getDouble();
		verify(dao, times(1)).update(updated);
	}
	
	@Test
	public void testFalseUpdate() {
		Item updated = new Item(100L, "Kiwi", 8000L, 15.82);
		
		when(utils.getLong()).thenReturn(updated.getId(), updated.getStock());
		when(utils.getString()).thenReturn(updated.getItemName());
		when(utils.getDouble()).thenReturn(updated.getCost());
		when(dao.update(updated)).thenReturn(null);
		
		assertNotEquals(updated, controller.update());
		verify(utils, times(2)).getLong();
		verify(utils, times(1)).getString();
		verify(utils, times(1)).getDouble();
		verify(dao, times(1)).update(updated);
	}
	
	@Test
	public void testDelete() {
		final long ID = 1L;
		
		when(utils.getLong()).thenReturn(ID);
		when(dao.delete(ID)).thenReturn(1);
		
		assertEquals(1, controller.delete());
		verify(dao, times(1)).delete(ID);
	}
	
	@Test
	public void testFalseDelete() {
		final long ID = 100L;
		
		when(utils.getLong()).thenReturn(ID);
		when(dao.delete(ID)).thenReturn(0);
		
		assertNotEquals(1, controller.delete());
		verify(dao, times(1)).delete(ID);
	}
}
