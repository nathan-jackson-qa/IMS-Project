package com.qa.ims.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.Utils;

public class OrderController implements CrudController<Order>{

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	
	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
	}
	@Override
	public Order create() {
		LOGGER.info("Enter the ID of the customer placing this order:");
		long custID = utils.getLong();
		
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		LOGGER.info("Enter the ID of the first item you want to add to this order:");
		long itemID = utils.getLong();
		LOGGER.info("How many of this item would you like to add to order?");
		long quantity = utils.getLong();
		items.put(itemID, quantity);
		boolean moreItems = true;
		do { 
			LOGGER.info("Would you like to add another item to this order? (Yes Or No)");
			String another = utils.getString();
			if(!another.equalsIgnoreCase("Yes"))
			{
				moreItems = false;
			}
			else
			{
				LOGGER.info("Enter the ID of the next item you want to add to this order:");
				itemID = utils.getLong();
				LOGGER.info("How many of this item would you like to add to order?");
				quantity = utils.getLong();
				items.put(itemID, quantity);
			}
		}while(moreItems == true);
		Order order = orderDAO.create(new Order(custID, items));
		LOGGER.info("Order created");
		return order;
	}
	
	@Override
	public Order read() {
		LOGGER.info("What type of information would you like to read?");
		LOGGER.info("SEARCH: Information on one order by ID");
		LOGGER.info("PRICE: Find the total price of an order by ID");
		LOGGER.info("RETURN: Return to the previous menu");
		String choice = utils.getString().toUpperCase();
		switch(choice)
		{
		case "SEARCH":
			return readByID();
		case "PRICE":
			return getPrice();
		case "RETURN":
			break;
		default :
			LOGGER.info("NOT A VALID COMMAND\n");
			read();
		}
		return null;
	}

	@Override
	public List<Order> readAll() {
		
		List<Order> orders = orderDAO.readAll();
		for(Order o : orders)
		{
			LOGGER.info(o.toString());
		}
		return orders;
	}
	
	public Order readByID() {
		LOGGER.info("Enter the ID of the order you want to display details of:");
		long id = utils.getLong();
		Order order = orderDAO.read(id);
		if(order != null)
		{
			LOGGER.info(order.toString());
			return order;
		}
		else
		{
			LOGGER.info("This ID does not match an order in the system so could not be retrieved");
			return null;
		}
	}
	
	public Order getPrice() {
		LOGGER.info("Enter the ID you wish to calculate the total cost for:");
		long id = utils.getLong();
		Order order = orderDAO.totalPrice(id);
		if(order != null)
		{
			LOGGER.info(order.displayPrice());
			return order;
		}
		else
		{
			LOGGER.info("This ID does not match an order in the system so could not be retrieved");
			return null;
		}
	}
	
	@Override
	public Order update() {
		LOGGER.info("Enter the ID of the order you wish to edit: ");
		long id = utils.getLong();
		if(orderDAO.read(id) == null)
		{
			LOGGER.info("ID does not match an order in the system\n");
			return null;
		}
		LOGGER.info("What would like to do with this order?");
		LOGGER.info("ADD: Add an item to the order");
		LOGGER.info("REMOVE: Remove an item from the order");
		LOGGER.info("CANCEL: Cancel amending order and return");
		String choice = utils.getString().toUpperCase();
		Order order = null;
		int returned = 0;
		switch(choice) {
		case "ADD" :
			returned = addToOrder(id);
			if(returned != 0)
			{
				LOGGER.info("Item Added!\n");
			}
			else
			{
				LOGGER.info("Item could not be added, as the item ID does not exists in the database\n");
			}
			break;
		case "REMOVE" :
			returned = removeFromOrder(id);
			if(returned != 0)
			{
				LOGGER.info("Item Removed!\n");
			}
			else
			{
				LOGGER.info("Item could not be removed as the item ID does not match an ID on the order\n");
			}
			break;
		case "CANCEL" :
			return null;
		default:
			update();
		}
		return order;
	}

	public int addToOrder(long order_id)
	{
		LOGGER.info("Enter the ID of the item you wish to add to the order:");
		long item_ID = utils.getLong();
		LOGGER.info("How many of this item do you want to add to this order?");
		long quantity = utils.getLong();
		int order = orderDAO.addToOrder(order_id, item_ID, quantity);
		return order;
	}
	
	public int removeFromOrder(long order_id)
	{
		LOGGER.info("Enter the ID of the item you wish to remove from this order:");
		long item_id = utils.getLong();
		return orderDAO.removeFromOrder(order_id, item_id);
	}
	
	@Override
	public int delete() {
		LOGGER.info("Please enter the ID of the order you wish to delete:");
		long id = utils.getLong();
		int returned = orderDAO.delete(id);
		if(returned == 0)
		{
			LOGGER.info("Order could not be deleted as that order ID does not exist\n");
		}
		else
		{
			LOGGER.info("Order deleted!\n");
		}
		return returned;
	}

}
