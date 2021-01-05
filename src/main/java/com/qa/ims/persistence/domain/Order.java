package com.qa.ims.persistence.domain;

import java.util.HashMap;

public class Order {
	long order_id;
	long customer_id;
	double price;
	String dateOrdered;
	HashMap<Long, Long> itemsOrdered = new HashMap<Long, Long>();
	
	public Order (long customer_id, HashMap<Long,Long> items)
	{
		this.customer_id = customer_id;
		this.itemsOrdered = items;
	}
	
	public Order (long order_id, long customer_id, HashMap<Long,Long> items)
	{
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.itemsOrdered = items;
	}
	
	public Order(long order_id, long customer_id, String dateOrdered)
	{
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.dateOrdered = dateOrdered;
	}
	
	public Order(long order_id, long customer_id, double price, String dateOrdered, HashMap<Long, Long> itemsOrdered) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.price = price;
		this.dateOrdered = dateOrdered;
		this.itemsOrdered = itemsOrdered;
	}

	public Order(long order_id, double price)
	{
		this.order_id = order_id;
		this.price = price;
	}
	
	public long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getDateOrdered() {
		return dateOrdered;
	}
	
	public void setDateOrdered(String dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	public HashMap<Long, Long> getItemsOrdered() {
		return itemsOrdered;
	}

	public void setItemsOrdered(HashMap<Long, Long> itemsOrdered) {
		this.itemsOrdered = itemsOrdered;
	}
	
	public void addItem(long itemID, long quantityOrdered)
	{
		itemsOrdered.put(itemID, quantityOrdered);
	}
	
	public void deleteItem(long itemID)
	{
		itemsOrdered.remove(itemID);
	}

	@Override
	public String toString() {
		return "Order ID: " + order_id + ", Customer ID: " + customer_id + ", Date Ordered: " + dateOrdered;
	}
	
	public String displayPrice() {
		return "Order ID: " + order_id + ", Total Price: �" + price;
	}
	
	
	
}
