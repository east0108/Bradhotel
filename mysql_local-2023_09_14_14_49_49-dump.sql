-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotel
-- ------------------------------------------------------
-- Server version	8.0.22

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `total_amount` int NOT NULL,
  `created_date` timestamp NOT NULL,
  `last_modified_date` timestamp NOT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,6,100110,'2022-06-02 08:51:49','2022-06-02 08:51:49'),(7,6,1700000,'2023-09-11 03:47:57','2023-09-11 03:47:57'),(8,6,1700000,'2023-09-11 07:22:07','2023-09-11 07:22:07'),(9,6,1700000,'2023-09-11 07:23:28','2023-09-11 07:23:28'),(10,6,1700000,'2023-09-11 07:24:40','2023-09-11 07:24:40'),(11,6,1700000,'2023-09-13 07:14:25','2023-09-13 07:14:25');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `order_item_id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `amount` int NOT NULL,
  PRIMARY KEY (`order_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES (1,1,5,2,60),(2,1,6,5,50),(3,1,7,1,100000),(4,7,9,1,500000),(5,7,10,2,1200000),(6,8,9,1,500000),(7,8,10,2,1200000),(8,9,9,1,500000),(9,9,10,2,1200000),(10,10,9,1,500000),(11,10,10,2,1200000),(12,11,9,1,500000),(13,11,10,2,1200000);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(128) NOT NULL,
  `category` varchar(32) NOT NULL,
  `image_url` varchar(256) NOT NULL,
  `price` int NOT NULL,
  `stock` int NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `created_date` timestamp NOT NULL,
  `last_modified_date` timestamp NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (5,'蘋果（澳洲）','FOOD','https://cdn.pixabay.com/photo/2016/11/30/15/00/apples-1872997_1280.jpg',30,10,'這是來自澳洲的蘋果！','2022-03-19 09:00:00','2022-03-22 10:00:00'),(6,'蘋果（日本北海道）','FOOD','https://cdn.pixabay.com/photo/2017/09/26/13/42/apple-2788662_1280.jpg',300,5,'這是來自日本北海道的蘋果！','2022-03-19 10:30:00','2022-03-19 10:30:00'),(7,'好吃又鮮甜的蘋果橘子','FOOD','https://cdn.pixabay.com/photo/2021/07/30/04/17/orange-6508617_1280.jpg',10,50,NULL,'2022-03-20 01:00:00','2022-03-24 07:00:00'),(8,'Toyota','CAR','https://cdn.pixabay.com/photo/2014/05/18/19/13/toyota-347288_1280.jpg',100000,5,NULL,'2022-03-20 01:20:00','2022-03-20 01:20:00'),(9,'BMW','CAR','https://cdn.pixabay.com/photo/2018/02/21/03/15/bmw-m4-3169357_1280.jpg',500000,2,'渦輪增壓，直列4缸，DOHC雙凸輪軸，16氣門','2022-03-20 04:30:00','2023-09-13 07:14:25'),(10,'Benz','CAR','https://cdn.pixabay.com/photo/2017/03/27/14/56/auto-2179220_1280.jpg',600000,0,NULL,'2022-03-21 12:10:00','2023-09-13 07:14:25'),(11,'Tesla','CAR','https://cdn.pixabay.com/photo/2021/01/15/16/49/tesla-5919764_1280.jpg',450000,5,'世界最暢銷的充電式汽車','2022-03-21 15:30:00','2022-03-21 15:30:00');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL,
  `created_date` timestamp NOT NULL,
  `last_modified_date` timestamp NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,'test6@gmail.com','202cb962ac59075b964b07152d234b70','2023-09-08 09:12:39','2023-09-08 09:12:39');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-14 14:49:49
