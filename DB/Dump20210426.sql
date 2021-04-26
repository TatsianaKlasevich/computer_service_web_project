-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: computer_maintenance_service
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categories` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category` varchar(15) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'laptop'),(2,'printer'),(3,'UPS');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `appliance_date` date NOT NULL,
  `issue_date` date DEFAULT NULL,
  `problem` varchar(100) NOT NULL,
  `category_id_fk` int NOT NULL,
  `user_id_fk` int NOT NULL,
  `address` varchar(100) NOT NULL,
  `status` enum('checking','working','ready','cancelled') NOT NULL DEFAULT 'checking',
  `has_discount` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`),
  KEY `category_id_fk` (`category_id_fk`),
  KEY `user_id_fk` (`user_id_fk`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`category_id_fk`) REFERENCES `categories` (`category_id`),
  CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`user_id_fk`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,'2021-03-07','2021-03-12','sdf',2,2,'ул. Заславская, 12-435','checking',0),(6,'2021-03-07','2021-03-29','sdf',1,2,'ул. Заславская, 12-435','ready',0),(17,'2021-03-09','2021-03-14','не печатает',2,1,'ул. Зеленая, 24-56','checking',0),(19,'2021-03-09','2021-03-14','не включается',3,1,'ул. Полевая, 24а','checking',0),(21,'2021-03-09','2021-03-14','не включается',3,1,'ул. Зеленая, 24-56','checking',0),(37,'2021-03-13','2021-03-31','не работает',1,6,'ул. Полевая, 24а','ready',1),(40,'2021-03-28','2021-04-02','не включается',3,2,'ул. 9-я иногородняя, д.2','checking',1),(42,'2021-04-07','2021-04-07','Сильно шумит',2,6,'ул. Зеленая, 24-56','ready',0),(43,'2021-04-07','2021-04-12','не включается',3,6,'ул. Зеленая, 24-56','checking',0),(44,'2021-04-07','2021-04-12','не включается',3,6,'ул. Полевая, 24а','ready',0),(45,'2021-04-07','2021-04-12','не работает',3,6,'ул. Полевая, 24а','cancelled',0),(47,'2021-04-07','2021-04-12','не работает',3,1,'ул. Полевая, 24а','checking',0),(48,'2021-04-07','2021-04-12','не работает',3,1,'ул. Полевая, 24а','checking',0),(49,'2021-04-07','2021-04-12','не работает',3,1,'ул. Заславская, 12-435','checking',0),(52,'2021-04-07','2021-04-12','не работает',3,1,'ул. Заславская, 12-435','checking',0),(53,'2021-04-07','2021-04-12','не работает',3,1,'ул. Полевая, 24а','checking',0),(56,'2021-04-07','2021-04-12','не работает',3,1,'ул. Заславская, 12-435','checking',0),(57,'2021-04-07','2021-04-12','не работает',3,1,'ул. Заславская, 12-435','checking',0),(60,'2021-04-01','2021-04-06','не работает',3,1,'ул. Полевая, 24а','checking',1),(61,'2021-04-07','2021-04-12','не работает',1,1,'ул. Заславская, 12-435','working',0),(63,'2021-04-10','2021-04-15','Нечеткая картинка',2,1,'ул. Полевая, 24а','checking',0),(65,'2021-04-10','2021-04-15','Нечеткая картинка',2,1,'ул. Полевая, 24а','checking',0),(66,'2021-04-10','2021-04-15','не включается',2,1,'ул. Зеленая, 24-56','checking',0),(67,'2021-04-10','2021-04-15','не работает',3,1,'ул. Зеленая, 24-56','checking',0),(68,'2021-04-07','2021-04-12','не работает',3,1,'ул. Колыминская, 236-17','checking',0),(70,'2021-04-11','2021-04-16','не работает',3,6,'ул. Полевая, 24а','working',0),(71,'2021-04-11','2021-04-16','Сильно шумит',3,6,'ул. Зеленая, 24-56','cancelled',0),(72,'2021-04-15','2021-04-20','не включается',1,31,'ул. Полевая, 24а','ready',0),(73,'2021-04-26','2021-05-01','не работает',2,1,'ул. Заславская, 12-435','checking',1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_services`
--

DROP TABLE IF EXISTS `orders_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_services` (
  `order_id_fk` int NOT NULL,
  `service_id_fk` int NOT NULL,
  PRIMARY KEY (`order_id_fk`,`service_id_fk`),
  KEY `service_id_fk` (`service_id_fk`),
  CONSTRAINT `orders_services_ibfk_1` FOREIGN KEY (`order_id_fk`) REFERENCES `orders` (`order_id`),
  CONSTRAINT `orders_services_ibfk_2` FOREIGN KEY (`service_id_fk`) REFERENCES `services` (`service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_services`
--

LOCK TABLES `orders_services` WRITE;
/*!40000 ALTER TABLE `orders_services` DISABLE KEYS */;
INSERT INTO `orders_services` VALUES (4,4),(43,5),(72,5),(37,6),(72,6),(6,7),(37,7),(43,7),(44,22),(45,22),(71,24),(70,27),(6,30),(37,30),(70,32),(42,33),(61,33);
/*!40000 ALTER TABLE `orders_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `services` (
  `service_id` int NOT NULL AUTO_INCREMENT,
  `service_name` varchar(125) NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `category_id_fk` int NOT NULL,
  PRIMARY KEY (`service_id`),
  KEY `category_id_fk` (`category_id_fk`),
  CONSTRAINT `services_ibfk_1` FOREIGN KEY (`category_id_fk`) REFERENCES `categories` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `services`
--

LOCK TABLES `services` WRITE;
/*!40000 ALTER TABLE `services` DISABLE KEYS */;
INSERT INTO `services` VALUES (4,'Восстановление аккумулятора',120,1),(5,'Замена шлейфа матрицы',85,1),(6,'Ремонт элементов корпуса',50,1),(7,'Замена  BGA чипа',280,1),(9,'Чистка тракта прохождения бумаги',25,2),(10,'Замена ролика захвата бумаги',60,2),(11,'Замена датчика наличия бумаги',40,2),(17,'Замена термопленки',75,2),(18,'Замена нагревательного элемента в блоке фиксации тонера',100,2),(19,'Ремонт платы формирования изображения',200,2),(20,'Замена шлейфа сканера',25,2),(21,'Замена протяжных роликов в автоматическом лотке',50,2),(22,'Замена АКБ',80,3),(23,'Замена клемных соединений',20,3),(24,'Ремонт платы управления питанием',120,3),(26,'Калибровка и настройка',7,3),(27,'Замена реле',30,3),(28,'Ремонт кнопки выключения',15,3),(30,'Замена матрицы ноутбука',150,1),(32,'Ремонт кнопки включения',15,3),(33,'Чистка системы охлаждения',30,1),(38,'Настройка печати',15,2),(40,'Настройка программ',15,1),(41,'Замена вентилятора в системе охлаждения',75,1);
/*!40000 ALTER TABLE `services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `mail` varchar(50) NOT NULL,
  `phone` varchar(13) NOT NULL,
  `password` varchar(50) NOT NULL,
  `avatar` varchar(125) DEFAULT NULL,
  `role` enum('user','admin') NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'James','Bond','email@mail.com','+375440000000','6347467a63336476636d513d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/bond.jpg','user'),(2,'Галина','Грибалева','user@mail.com','+375440000000','4d54497a4e4455324e7a673d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/madonna.jpg','user'),(3,'админ','Вася','admin@mail.com','+375440000000','4d54497a4e4455324e7a673d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/smile.png','admin'),(4,'Галина','Гуринович','madonna@mail.com','+375440000000','4d54497a4e4455324e7a673d','','user'),(6,'Татьяна','Класевич','tanya@mail.com','+375443465623','4d54497a4e4455324e7a673d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/pchmnmr-scaledD.jpg','user'),(7,'James','Bond','bond@mail.com','+375440000000','4d54497a4e4455324e7a673d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/bond.jpg','user'),(8,'Elena','Ivanova','ivanova@mail.com','+375440000000','6347467a63336476636d513d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/madonna.jpg','user'),(9,'smile','smile','smile@mail.com','+375440000000','4d54497a4e4455324e7a673d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/bond.jpg','user'),(10,'Мария','Грибалева','maria@gmail.com','+375333563243','4d54497a4e4455324e7a673d',NULL,'user'),(12,'prodigy','prodigy','prodigy@gmail.com','+375259038945','4d54497a4e4455324e7a673d',NULL,'user'),(14,'Monro','Monro','monro@gmail.com','+375290000000','4d54497a4e4455324e7a673d',NULL,'user'),(15,'Beaty','Beaty','beaty@mail.ru','+375447080639','4d54497a4e4455324e7a673d',NULL,'user'),(16,'TATSIANA','KLASEVICH','kalss@mail.com','+375293480684','4d54497a4e4455324e7a673d',NULL,'user'),(18,'Слава','Зайцев','slava@mail.ru','+375338764535','4f4463324e54517a4d6a453d',NULL,'user'),(19,'Глеб','Топалов','gleb@gmail.com','+375448932222','4f4463324e54517a4d6a453d',NULL,'user'),(20,'Дарина','Чернявская','darina@mail.com','+375447893292','4d54497a4e4455324e7a673d',NULL,'user'),(21,'Адриано','Челентано','anri@mail.com','+375294950392','4d54497a4e4455324e7a673d',NULL,'user'),(22,'Владимир','Поздняков','vladimir@gmail.com','+375447894030','4d54497a4e4455324e7a673d',NULL,'user'),(24,'klass','klass','klass@gmail.com','+375440394035','4d54497a4e4455324e7a673d',NULL,'user'),(25,'adminNew','adminNew','admin_new@mail.com','+375333563243','4d54497a4e4455324e7a673d',NULL,'admin'),(26,'adm','adm','admi@mail.ru','+375447894030','4d54497a4e4455324e7a673d',NULL,'admin'),(27,'dkfjl','sdf','login@mail.com','+375333563243','4d54497a4e4455324e7a673d',NULL,'user'),(30,'teddy','teddy','teddy@mail.com','+375447080684','62574670624541784d6a4d30',NULL,'user'),(31,'Tanya','Klasevich','tanyanek@gmail.com','+375447080684','4d54497a4e4455324e7a673d','C:/Users/USER/IdeaProjects/computer_maintenance_service/src/main/webapp/images/uploaded/pchmnmr-scaledD.jpg','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-26 19:08:41
