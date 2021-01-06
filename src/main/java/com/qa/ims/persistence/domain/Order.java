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

	
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDateOrdered() {
		return dateOrdered;
	}

	public void setDateOrdered(String dateOrdered) {
		this.dateOrdered = dateOrdered;
	}

	@Override
	public String toString() {
		return "Order ID: " + order_id + ", Customer ID: " + customer_id + ", Date Ordered: " + dateOrdered;
	}
	
	public String displayPrice() {
		return "Order ID: " + order_id + ", Total Price: £" + price;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (customer_id ^ (customer_id >>> 32));
		result = prime * result + ((dateOrdered == null) ? 0 : dateOrdered.hashCode());
		result = prime * result + ((itemsOrdered == null) ? 0 : itemsOrdered.hashCode());
		result = prime * result + (int) (order_id ^ (order_id >>> 32));
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (customer_id != other.customer_id)
			return false;
		if (dateOrdered == null) {
			if (other.dateOrdered != null)
				return false;
		} else if (!dateOrdered.equals(other.dateOrdered))
			return false;
		if (itemsOrdered == null) {
			if (other.itemsOrdered != null)
				return false;
		} else if (!itemsOrdered.equals(other.itemsOrdered))
			return false;
		if (order_id != other.order_id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		return true;
	}
	
	
}
