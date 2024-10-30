CREATE TABLE `products` (
  `product_id` bigint NOT NULL AUTO_INCREMENT,
  `barcode_id` varchar(255) DEFAULT NULL,
  `bottle_volume` int DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `empty_bottle_price` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `purchase_price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `selling_price` double DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `product_sale_records` (
  `selling_id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `empty_bottle_received` bit(1) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `product_product_id` bigint DEFAULT NULL,
  PRIMARY KEY (`selling_id`),
  KEY `FKi6fbl2ojdm01h4g6tjw2q5dm8` (`product_product_id`),
  CONSTRAINT `FKi6fbl2ojdm01h4g6tjw2q5dm8` FOREIGN KEY (`product_product_id`) REFERENCES `products` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;