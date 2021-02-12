-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: localhost    Database: payments
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

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
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clients` (
  `idclient` int NOT NULL AUTO_INCREMENT,
  `document` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`idclient`),
  UNIQUE KEY `clientes_UN` (`document`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clients`
--

LOCK TABLES `clients` WRITE;
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` VALUES (7,'31444555','maria perez'),(30,'1144222333','Lizeth Gonzalez'),(32,'14555666','pablo perez'),(33,'34555666','jorge perez'),(38,'2332423','reploid'),(50,'dddd','jorge perez');
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (58),(58),(58),(58),(58);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `idproduct` int NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '',
  `enabled` tinyint NOT NULL DEFAULT '1',
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`idproduct`),
  UNIQUE KEY `productos_UN` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (2,'001','Televisor SONY 55 pulgadas',1,120000),(3,'003','lavadora mabe',1,340000),(4,'004','Televisor LG 32 Pulgadas',1,900000),(34,'005','Televisor LG 60 Pulgadas',1,1900000);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `idpurchase` int NOT NULL AUTO_INCREMENT,
  `idproductpurchase` int NOT NULL,
  `idclientpurchase` int NOT NULL,
  `idsellerpurchase` int NOT NULL,
  `purchasedate` date NOT NULL,
  PRIMARY KEY (`idpurchase`),
  KEY `compras_FK` (`idclientpurchase`),
  KEY `compras_FK_1` (`idproductpurchase`),
  KEY `compras_FK_2` (`idsellerpurchase`),
  CONSTRAINT `compras_FK` FOREIGN KEY (`idclientpurchase`) REFERENCES `clients` (`idclient`),
  CONSTRAINT `compras_FK_1` FOREIGN KEY (`idproductpurchase`) REFERENCES `products` (`idproduct`),
  CONSTRAINT `compras_FK_2` FOREIGN KEY (`idsellerpurchase`) REFERENCES `vendors` (`idseller`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (18,2,7,9,'2021-02-06'),(19,3,7,9,'2021-02-08'),(20,3,7,9,'2021-02-08'),(21,3,7,9,'2021-02-09'),(22,3,7,9,'2021-02-10'),(26,4,7,9,'2021-02-11'),(35,4,7,9,'2021-01-19'),(54,34,50,31,'2021-02-11');
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchasestatus`
--

DROP TABLE IF EXISTS `purchasestatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchasestatus` (
  `idpurchasestatus` int NOT NULL AUTO_INCREMENT,
  `idpurchase` int NOT NULL,
  `state` tinyint NOT NULL DEFAULT '1' COMMENT '1: pendiente, 2:aprovada, 3:anulada',
  `statedate` date NOT NULL,
  PRIMARY KEY (`idpurchasestatus`),
  KEY `estadocompras_FK` (`idpurchase`),
  CONSTRAINT `estadocompras_FK` FOREIGN KEY (`idpurchase`) REFERENCES `purchases` (`idpurchase`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchasestatus`
--

LOCK TABLES `purchasestatus` WRITE;
/*!40000 ALTER TABLE `purchasestatus` DISABLE KEYS */;
INSERT INTO `purchasestatus` VALUES (27,26,1,'2021-02-11'),(28,26,2,'2021-02-10'),(36,35,1,'2021-01-19'),(37,35,2,'2021-02-10'),(55,54,1,'2021-02-11'),(57,54,2,'2021-02-11');
/*!40000 ALTER TABLE `purchasestatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendors` (
  `idseller` int NOT NULL AUTO_INCREMENT,
  `code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`idseller`),
  UNIQUE KEY `vendedores_UN` (`code`),
  KEY `vendedores_codigo_IDX` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` VALUES (9,'1010','juan perez perez',1),(31,'2020','carlos polanco',1);
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'payments'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-12 12:33:07
