CREATE TABLE `books` (
  `id` bigint AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `launch_date` datetime NOT NULL,
  `price` decimal(65,2) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) 
