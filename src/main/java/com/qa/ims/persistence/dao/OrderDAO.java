package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order;
import com.qa.ims.utils.DBUtils;

public class OrderDAO implements Dao<Order> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Order create(Order order) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();) {
			statement.executeUpdate("INSERT INTO ims.orders(customer_id) values(" + order.getCustomer_id() + ")");
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
			return null;
		}
		HashMap<Long,Long> items = order.getItemsOrdered();
		for(Entry<Long,Long> i : items.entrySet())
		{
			try (Connection connection = DBUtils.getInstance().getConnection();
					Statement statement = connection.createStatement();) {
				statement.executeUpdate("INSERT INTO ims.orders_items(order_id, item_id, quantity) values(" + readLatest().getOrder_id() + ","
						+ i.getKey() + "," + i.getValue() + ")");
			} catch (Exception e) {
				LOGGER.debug(e);
				LOGGER.error(e.getMessage());
				return null;
			}
		}
		return readLatest();
	}

	private Order readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM ims.orders ORDER BY order_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Order> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order update(Order t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
		long id = resultSet.getLong("order_id");
		long customer_id = resultSet.getLong("customer_id");
		HashMap<Long,Long> items = new HashMap<Long,Long>();
		return new Order(id, customer_id, items);
	}

}