CREATE DATABASE  IF NOT EXISTS `happyhousefinal` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `happyhousefinal`;
-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: happyhousefinal
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `qno` int DEFAULT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uptTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `qno_fk` (`qno`),
  CONSTRAINT `qno_fk` FOREIGN KEY (`qno`) REFERENCES `qna` (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attention`
--

DROP TABLE IF EXISTS `attention`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attention` (
  `no` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) DEFAULT NULL,
  `atten` varchar(100) DEFAULT NULL,
  `lat` varchar(50) DEFAULT NULL,
  `lng` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no`),
  KEY `userId_fk` (`userid`),
  CONSTRAINT `userId_fk` FOREIGN KEY (`userid`) REFERENCES `member` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attention`
--

LOCK TABLES `attention` WRITE;
/*!40000 ALTER TABLE `attention` DISABLE KEYS */;
/*!40000 ALTER TABLE `attention` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `userid` varchar(50) NOT NULL,
  `userpw` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES ('ssafy','ssafy','싸피','ssafy@ssafy.com');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qna`
--

DROP TABLE IF EXISTS `qna`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qna` (
  `no` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(500) NOT NULL,
  `registTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uptTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`no`),
  KEY `qna_userId_fk` (`userid`),
  CONSTRAINT `qna_userId_fk` FOREIGN KEY (`userid`) REFERENCES `member` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qna`
--

LOCK TABLES `qna` WRITE;
/*!40000 ALTER TABLE `qna` DISABLE KEYS */;
INSERT INTO `qna` VALUES (14,'ssafy','test1','test1','2021-11-19 05:08:59','2021-11-19 05:08:59'),(15,'ssafy','test2','test2','2021-11-19 05:08:59','2021-11-19 05:08:59'),(16,'ssafy','test3','test3','2021-11-19 05:08:59','2021-11-19 05:08:59'),(17,'ssafy','test4','test4','2021-11-19 05:08:59','2021-11-19 05:08:59'),(18,'ssafy','test5','test5','2021-11-19 05:08:59','2021-11-19 05:08:59'),(19,'ssafy','test6','test6','2021-11-19 05:08:59','2021-11-19 05:08:59'),(20,'ssafy','test7','test7','2021-11-19 05:08:59','2021-11-19 05:08:59'),(21,'ssafy','test8','test8','2021-11-19 05:08:59','2021-11-19 05:08:59'),(22,'ssafy','test9','test9','2021-11-19 05:08:59','2021-11-19 05:08:59'),(23,'ssafy','test10','test10','2021-11-19 05:08:59','2021-11-19 05:08:59'),(24,'ssafy','test11','test11','2021-11-19 05:08:59','2021-11-19 05:08:59'),(25,'ssafy','test12','test12','2021-11-19 05:08:59','2021-11-19 05:08:59'),(26,'ssafy','test13','test13','2021-11-19 05:08:59','2021-11-19 05:08:59');
/*!40000 ALTER TABLE `qna` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-22 14:32:53
