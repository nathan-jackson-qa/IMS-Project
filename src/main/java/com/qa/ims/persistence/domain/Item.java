package com.qa.ims.persistence.domain;

public class Item {
	private long id;
	private String itemName;
	private long stock;
	private double cost;
	
	public Item(String itemName, long stock, double cost)
	{
		this.itemName = itemName;
		this.stock=stock;
		this.cost = cost;
	}
	
	public Item(long id, String itemName, long stock, double cost)
	{
		this.id = id;
		this.itemName = itemName;
		this.stock=stock;
		this.cost = cost;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public long getStock() {
		return stock;
	}

	public void setStock(long stock) {
		this.stock = stock;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	

	@Override
	public String toString() {
		return "ID:"+ id + ", Item Name:" + itemName + ", Stock:" + stock + ", Cost:" + cost;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(cost);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + (int) (stock ^ (stock >>> 32));
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
		Item other = (Item) obj;
		if (Double.doubleToLongBits(cost) != Double.doubleToLongBits(other.cost))
			return false;
		if (id != other.id)
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (stock != other.stock)
			return false;
		return true;
	}



	
}
