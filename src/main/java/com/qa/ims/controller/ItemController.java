package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;

public class ItemController implements CrudController<Item>{

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemDAO itemDAO;
	private Utils utils;
	
	public ItemController(ItemDAO itemDAO, Utils utils) {
		super();
		this.itemDAO = itemDAO;
		this.utils = utils;
	}
	
	@Override
	public Item create() {
		LOGGER.info("Please enter the name of the item");
		String itemName = utils.getString();
		LOGGER.info("How many of this item do you have in stock?");
		long stock = utils.getLong();
		LOGGER.info("What is the price of one unit of this item?");
		double price = utils.getDouble();
		Item item = itemDAO.create(new Item(itemName, stock, price));
		LOGGER.info("Item created");
		return item;
	}

	@Override
	public List<Item> readAll() {
		List<Item> items = itemDAO.readAll();
		for(Item i : items)
		{
			LOGGER.info(i.toString());
		}
		return items;
	}

	@Override
	public Item update() {
		LOGGER.info("Enter the ID of the item you wish to edit:");
		long id = utils.getLong();
		LOGGER.info("What would you like the new product name to be?");
		String itemName = utils.getString();
		LOGGER.info("What is the new stock count of this item?");
		long stockCount = utils.getLong();
		LOGGER.info("And finally, what do you want the price of this item to be?");
		double price = utils.getDouble();
		Item item = itemDAO.update(new Item(id, itemName, stockCount, price));
		LOGGER.info("Item updated!");
		return item;
	}

	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the item you wish to delete:");
		long id = utils.getLong();
		return itemDAO.delete(id);
	}
	
}
