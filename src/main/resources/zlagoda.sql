-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zlagoda
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_number` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`category_number`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (4,'Bakery'),(5,'Dairy'),(3,'Seafood');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check`
--

DROP TABLE IF EXISTS `check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check` (
  `check_number` varchar(10) NOT NULL,
  `id_employee` varchar(10) NOT NULL,
  `card_number` varchar(13) DEFAULT NULL,
  `print_date` datetime NOT NULL,
  `sum_total` decimal(13,4) NOT NULL,
  `vat` decimal(13,4) NOT NULL,
  PRIMARY KEY (`check_number`),
  KEY `fk_check_employee` (`id_employee`),
  KEY `fk_check_card` (`card_number`),
  CONSTRAINT `fk_check_card` FOREIGN KEY (`card_number`) REFERENCES `customer_card` (`card_number`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_check_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check`
--

LOCK TABLES `check` WRITE;
/*!40000 ALTER TABLE `check` DISABLE KEYS */;
/*!40000 ALTER TABLE `check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_card`
--

DROP TABLE IF EXISTS `customer_card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer_card` (
  `card_number` varchar(13) NOT NULL,
  `cust_surname` varchar(50) NOT NULL,
  `cust_name` varchar(50) NOT NULL,
  `cust_patronymic` varchar(50) DEFAULT NULL,
  `phone_number` varchar(13) NOT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `zip_code` varchar(9) NOT NULL,
  `percent` int NOT NULL,
  PRIMARY KEY (`card_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_card`
--

LOCK TABLES `customer_card` WRITE;
/*!40000 ALTER TABLE `customer_card` DISABLE KEYS */;
INSERT INTO `customer_card` VALUES ('4296f95be6ad4','gfdgfdg','FFFF','aaaa','321312321','dsadas','gfgfsdgsdf1','123123',5),('9d4744b273494','FSADASD','AAASD','tffsd','0501114212','Суми','ввфівіф','40000',8);
/*!40000 ALTER TABLE `customer_card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id_employee` varchar(10) NOT NULL,
  `empl_surname` varchar(50) NOT NULL,
  `empl_name` varchar(50) NOT NULL,
  `empl_patronymic` varchar(50) DEFAULT NULL,
  `empl_role` varchar(10) NOT NULL,
  `salary` decimal(13,4) NOT NULL,
  `date_of_birth` date NOT NULL,
  `date_of_start` date NOT NULL,
  `phone_number` varchar(13) NOT NULL,
  `city` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `zip_code` varchar(9) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('24ba03ebc0','Admin','Admin','Adminovych','MANAGER',7777777.0000,'1970-01-01','2001-12-12','380777777777','Kyiv','Streer','77777','admin','$2a$10$bRV9UdzLuIvZDByzE5fFheLTpC6tz7CV3a/CL6yX.o7TcZg.93ojS'),('aa3944ebfb','Cashier','Cashier','Cashierovych','CASHIER',777.0000,'1980-01-01','2012-12-12','380111111111','Berdychyv','Street','101010','cashier','$2a$10$XXkLVjNwI6aNx79EKJ6ee.rnTIR4EH6hjJOJSQUp0PCI/hkAyqzBm');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id_product` int NOT NULL AUTO_INCREMENT,
  `category_number` int NOT NULL,
  `product_name` varchar(50) NOT NULL,
  `characteristics` varchar(100) NOT NULL,
  PRIMARY KEY (`id_product`),
  KEY `category_number` (`category_number`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_number`) REFERENCES `category` (`category_number`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (3,4,'Pampushka','ukrainian'),(4,3,'Shrimp','from black sea'),(5,3,'Carp','alive'),(6,4,'Bread','dark'),(7,4,'Cake \"Khreshchatyk\"','new'),(8,5,'Milk','good'),(9,4,'Cookie','with jam'),(10,4,'Cake \"Sumska palyanycya\"','from sumy bakery ');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sale`
--

DROP TABLE IF EXISTS `sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale` (
  `UPC` varchar(12) NOT NULL,
  `check_number` varchar(10) NOT NULL,
  `product_number` int NOT NULL,
  `selling_price` decimal(13,4) NOT NULL,
  PRIMARY KEY (`UPC`,`check_number`),
  KEY `FK2_check_number` (`check_number`),
  CONSTRAINT `FK1_UPC` FOREIGN KEY (`UPC`) REFERENCES `store_product` (`UPC`) ON UPDATE CASCADE,
  CONSTRAINT `FK2_check_number` FOREIGN KEY (`check_number`) REFERENCES `check` (`check_number`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale`
--

LOCK TABLES `sale` WRITE;
/*!40000 ALTER TABLE `sale` DISABLE KEYS */;
/*!40000 ALTER TABLE `sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_product`
--

DROP TABLE IF EXISTS `store_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `store_product` (
  `UPC` varchar(12) NOT NULL,
  `UPC_prom` varchar(12) DEFAULT NULL,
  `id_product` int NOT NULL,
  `selling_price` decimal(13,4) NOT NULL,
  `products_number` int NOT NULL,
  `promotional_product` tinyint(1) NOT NULL,
  PRIMARY KEY (`UPC`),
  KEY `UPC_prom` (`UPC_prom`),
  KEY `id_product` (`id_product`),
  CONSTRAINT `store_product_ibfk_1` FOREIGN KEY (`UPC_prom`) REFERENCES `store_product` (`UPC`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `store_product_ibfk_2` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_product`
--

LOCK TABLES `store_product` WRITE;
/*!40000 ALTER TABLE `store_product` DISABLE KEYS */;
INSERT INTO `store_product` VALUES ('123456789111',NULL,6,12.0000,1,0);
/*!40000 ALTER TABLE `store_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-24  3:32:49
