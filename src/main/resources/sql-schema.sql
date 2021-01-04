DROP DATABASE IF EXISTS `ims`;
CREATE DATABASE IF NOT EXISTS `ims`;
USE `ims` ;

CREATE TABLE IF NOT EXISTS `ims`.`customers`(
`customer_id` INT(11) NOT NULL AUTO_INCREMENT,
`firstName` VARCHAR(45) NOT NULL,
`lastName` VARCHAR(45) NOT NULL,
PRIMARY KEY(customer_id)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders`(
`order_id` INT(11) NOT NULL AUTO_INCREMENT,
`customer_id` INT(11) NOT NULL,
`placed` DATETIME DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(order_id),
FOREIGN KEY(customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS `ims`.`items`(
`item_id` INT(11) NOT NULL AUTO_INCREMENT,
`itemName` VARCHAR(45) NOT NULL,
`stockCount` INT(11) NOT NULL,
`price` double NOT NULL,
PRIMARY KEY(item_id)
);
CREATE TABLE IF NOT EXISTS `ims`.`orders_items`(
`order_id` INT(11) NOT NULL,
`item_id` INT(11) NOT NULL,
`quantity` INT(11) NOT NULL,
FOREIGN KEY(order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
FOREIGN KEY(item_id) REFERENCES items(item_id) ON DELETE CASCADE
);
