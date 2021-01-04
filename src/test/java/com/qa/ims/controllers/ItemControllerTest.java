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
		
		Mockito.when(utils.getString()).thenReturn(name);
		Mockito.when(utils.getLong()).thenReturn(stock);
		Mockito.when(utils.getDouble()).thenReturn(price);
		Mockito.when(dao.create(created)).thenReturn(created);
		
		assertEquals(created, controller.create());
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).create(created);
	}
	
	@Test
	public void testReadAll() {
		List<Item> items = new ArrayList<>();
		items.add(new Item("Apple", 100L, 0.99));
		
		Mockito.when(dao.readAll()).thenReturn(items);
		
		assertEquals(items, controller.readAll());
		
		Mockito.verify(dao, Mockito.times(1)).readAll();
	}
	
	@Test
	public void testRead() {
		final Item item = new Item(1L, "Banana", 100L, 1.50);
		
		Mockito.when(utils.getLong()).thenReturn(1L);
		Mockito.when(dao.readItem(1L)).thenReturn(item);
		
		assertEquals(item, controller.read());
		Mockito.verify(dao, Mockito.times(1)).readItem(1L);
	}
	
	@Test
	public void testFalseRead() {
		final Item item = new Item(1L, "Banana", 100L, 1.50);
		
		Mockito.when(utils.getLong()).thenReturn(100L);
		Mockito.when(dao.readItem(100L)).thenReturn(null);
		
		assertNotEquals(item, controller.read());
		Mockito.verify(dao, Mockito.times(1)).readItem(100L);
	}
	
	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "Kiwi", 85L, 0.72);
		
		Mockito.when(utils.getLong()).thenReturn(updated.getId(), updated.getStock());
		Mockito.when(utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(utils.getDouble()).thenReturn(updated.getCost());
		Mockito.when(dao.update(updated)).thenReturn(updated);
		
		assertEquals(updated, controller.update());
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testFalseUpdate() {
		Item updated = new Item(100L, "Kiwi", 8000L, 15.82);
		
		Mockito.when(utils.getLong()).thenReturn(updated.getId(), updated.getStock());
		Mockito.when(utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(utils.getDouble()).thenReturn(updated.getCost());
		Mockito.when(dao.update(updated)).thenReturn(null);
		
		assertNotEquals(updated, controller.update());
		Mockito.verify(utils, Mockito.times(2)).getLong();
		Mockito.verify(utils, Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getDouble();
		Mockito.verify(dao, Mockito.times(1)).update(updated);
	}
	
	@Test
	public void testDelete() {
		final long ID = 1L;
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(1);
		
		assertEquals(1, controller.delete());
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
	
	@Test
	public void testFalseDelete() {
		final long ID = 100L;
		
		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(dao.delete(ID)).thenReturn(0);
		
		assertNotEquals(1, controller.delete());
		Mockito.verify(dao, Mockito.times(1)).delete(ID);
	}
}
