CREATE DATABASE IF NOT EXISTS ims;

CREATE TABLE IF NOT EXISTS customers (
customer_id INT NOT NULL AUTO_INCREMENT,
firstName VARCHAR(45) NOT NULL,
lastName VARCHAR(45) NOT NULL,
PRIMARY KEY(order_id)
);

CREATE TABLE IF NOT EXISTS orders (
order_id INT NOT NULL AUTO_INCREMENT,
customer_id INT NOT NULL,
placed DATETIME DEFAULT CURRENT_TIMESTAMP,
fulfilled DATETIME,
PRIMARY KEY(order_id),
FOREIGN KEY(customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE IF NOT EXISTS items(
item_id INT NOT NULL AUTO_INCREMENT,
itemName VARCHAR(45) NOT NULL,
stockCount int NOT NULL,
price double NOT NULL,
PRIMARY KEY(item_id)
);

CREATE TABLE IF NOT EXISTS orders_items (
order_id INT NOT NULL,
item_id INT NOT NULL,
quantity int NOT NULL,
FOREIGN KEY(order_id) REFERENCES orders(order_id),
FOREIGN KEY(item_id) REFERENCES items(item_id)
);