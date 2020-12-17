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
	public List<Order> readAll() {
		List<Order> orders = orderDAO.readAll();
		for(Order o : orders)
		{
			LOGGER.info(o.toString());
		}
		return null;
	}

	@Override
	public Order update() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
