-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: gyshop
-- ------------------------------------------------------
-- Server version	8.4.4

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
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (6,'곰돌이','곰돌이','/upload/image/bear4.jpg','2025-04-14 13:23:16','kim',0),(7,'석양','석양','/upload/image/tree-51.jpg','2025-04-14 13:23:40','kim',2),(8,'자작나무길','맞나요?','/upload/image/bg01.jpg','2025-04-14 13:23:56','kim',1),(10,'커피한잔','따뜻한 커피\r\n커피2잔','/upload/image/coffee-gray1.jpg','2025-04-14 13:24:32','kim',9),(11,'java','java','/upload/image/notice.jpg','2025-04-15 11:35:10','hjlee',0),(12,'커피','커피','/upload/image/coffee-pink.jpg','2025-04-15 11:35:30','hjlee',0),(13,'나무','나무','/upload/image/tree-42.jpg','2025-04-15 11:35:52','hjlee',2);
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-16 15:26:49
