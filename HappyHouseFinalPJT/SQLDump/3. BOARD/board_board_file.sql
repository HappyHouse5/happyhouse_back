-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: board
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
-- Table structure for table `board_file`
--

DROP TABLE IF EXISTS `board_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board_file` (
  `FILE_ID` int NOT NULL AUTO_INCREMENT,
  `BOARD_ID` int NOT NULL,
  `FILE_NAME` varchar(500) NOT NULL,
  `FILE_SIZE` int NOT NULL,
  `FILE_CONTENT_TYPE` varchar(500) NOT NULL,
  `FILE_URL` varchar(500) NOT NULL,
  `REG_DT` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FILE_ID`),
  KEY `BOARD_FILE_FK_idx` (`BOARD_ID`),
  CONSTRAINT `BOARD_FILE_FK` FOREIGN KEY (`BOARD_ID`) REFERENCES `board` (`BOARD_ID`) on delete cascade on update cascade
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board_file`
--

LOCK TABLES `board_file` WRITE;
/*!40000 ALTER TABLE `board_file` DISABLE KEYS */;
# INSERT INTO `board_file` VALUES (11,3163,'권진우_portfolio.docx',41416,'application/haansoftdocx','upload/2ce64560-cac0-4255-9e0b-9c5446936d96.docx','2021-04-30 19:41:18'),(13,3165,'권진우_portfolio.docx',41416,'application/haansoftdocx','upload/7517985d-c882-4202-87b4-10620f0d3e96.docx','2021-04-30 19:54:53'),(14,3165,'권진우_Portfolio.pptx',4423707,'application/haansoftpptx','upload/fa6d8a57-3ca3-4d1d-8906-a3912355a05d.pptx','2021-04-30 19:54:53'),(15,3165,'권진우_portfolio_docx.pdf',150751,'application/pdf','upload/771e8f40-3626-4a29-8129-e4ef9d1cdc4a.pdf','2021-04-30 19:54:53'),(16,3165,'권진우_Portfolio_ppt.pdf',1152169,'application/pdf','upload/85354c90-b5a0-451d-8fe7-ac19cc99736b.pdf','2021-04-30 19:54:53'),(18,3164,'권진우_portfolio_docx.pdf',150751,'application/pdf','upload/5d8ba381-112d-46b6-8883-fbcc198fd6a1.pdf','2021-04-30 20:07:10'),(19,3169,'졸업증명서.jpg',1390369,'image/jpeg','upload/0565499d-3d24-42e2-8f7c-05168fa3e45a.jpg','2021-05-21 11:09:06');
/*!40000 ALTER TABLE `board_file` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-24 21:09:30
