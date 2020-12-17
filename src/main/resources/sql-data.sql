USE `ims`;
INSERT INTO `customers` (`firstName`, `lastName`) VALUES ('Harry', 'Harrison');
INSERT INTO `customers` (`firstName`, `lastName`) VALUES ('Iman', 'Ingram');
INSERT INTO `customers` (`firstName`, `lastName`) VALUES ('Jordan', 'Jones');
INSERT INTO `customers` (`firstName`, `lastName`) VALUES ('Luke', 'Lohan');
INSERT INTO `customers` (`firstName`, `lastName`) VALUES ('Mike', 'Monroe');

INSERT INTO `orders` (`customer_id`) VALUES ('1');
INSERT INTO `orders` (`customer_id`) VALUES ('2');
INSERT INTO `orders` (`customer_id`) VALUES ('3');
INSERT INTO `orders` (`customer_id`) VALUES ('4');
INSERT INTO `orders` (`customer_id`) VALUES ('5');

INSERT INTO `items` (`itemName`, `stockCount`, `price`) VALUES ('Mango', 100, 0.99);
INSERT INTO `items` (`itemName`, `stockCount`, `price`) VALUES ('Apple', 100, 1.50);
INSERT INTO `items` (`itemName`, `stockCount`, `price`) VALUES ('Banana', 100, 2.20);
INSERT INTO `items` (`itemName`, `stockCount`, `price`) VALUES ('Orange', 100, 3);
INSERT INTO `items` (`itemName`, `stockCount`, `price`) VALUES ('Kiwi', 100, 4);

INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (1, 1, 5);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (1, 3, 2);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (2, 2, 4);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (3, 4, 1);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (3, 1, 3);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (3, 5, 7);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (4, 2, 8);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (4, 4, 4);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (5, 1, 6);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (5, 3, 5);
INSERT INTO `orders_items` (`order_id`, `item_id`, `quantity`) VALUES (5, 5, 9);
