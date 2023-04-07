-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bumblebee
-- ------------------------------------------------------
-- Server version	5.7.32-log

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `adminUniqueId` varchar(100) NOT NULL,
  `adminFirstName` varchar(45) NOT NULL,
  `adminLastName` varchar(45) NOT NULL,
  `adminEmail` varchar(45) NOT NULL,
  `adminPassword` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `adminUniqueId_UNIQUE` (`adminUniqueId`),
  UNIQUE KEY `adminEmail_UNIQUE` (`adminEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'d05ebf67-1b8b-4853-b43d-b2b6e972e878','Jhon','Mike','Jhon@gmail.com','8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brandName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `brandName_UNIQUE` (`brandName`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (18,'damro'),(19,'dell'),(17,'ikea'),(14,'Nokia'),(15,'samsung'),(16,'sony');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `categoryName_UNIQUE` (`categoryName`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (8,'elecronics'),(7,'furniture');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerUniqueId` varchar(45) NOT NULL,
  `customerFirstName` varchar(45) NOT NULL,
  `customerLastName` varchar(45) NOT NULL,
  `customerDOB` date NOT NULL,
  `customerEmail` varchar(45) NOT NULL,
  `password` varchar(256) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `customerUniqueId_UNIQUE` (`customerUniqueId`),
  UNIQUE KEY `customerEmail_UNIQUE` (`customerEmail`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (20,'c72ebb63-f1a5-419d-a421-4111e849ab5f','jhon','mike','2000-04-05','abc@gmail.com','a17444550e2c127b02ea1c197bcffa422c21713040f53d5c2ca7925419bccf7f'),(21,'8484cfaf-9e7e-490d-b576-c0789d6d5d97','Smith','Mary','1991-04-06','Mary@gmail.com','07d9a223daf607ecaa18e317ef0950e84bacb681396100c061cc8b4c4668147a'),(22,'3392d4d8-0588-421f-8bd8-1958955f3f7c','Jones','Lou','1990-04-06','Lou@gmail.com','8ebd36e4ecd5352111992fbf636aa70eab26362d74de313fcffced6203e386f7'),(23,'8e3afd67-71c8-4d97-afd6-198aed74e102','Sam','Wilson','1994-06-07','wilson@gmail.com','35f3b09b521a07dbaade45422cda8b78ec66cd477d2bf8e4cf538b8194862d7e'),(24,'00bd017e-3b47-4742-94cf-2ec62c668c48','Bill','Green','2000-06-07','green@gmail.com','7d9204025d2b329804f35df7930bc2ff033ccaef44945fceeea5ab3307b21600'),(25,'cccb487e-e447-46b8-8723-39c61d78fb63','King','Lucy','1993-10-13','lucy@gmail.com','d78ff06fb3b7052d83453b963ade8b6390aa5450cd91c8d37061be623f20017c'),(26,'19d6b2ad-b14f-4e7b-b480-c13da63b2dad','Peter','Parker','1958-06-18','parker@gmail.com','475cea6d61d9e46b4674a003b588275125be66ec4d73f349bf5185268ba4bf92'),(27,'e6d52fd0-04ad-48c6-9900-7f181c3763bf','Charles','Henry','1982-10-12','henry@gmail.com','0452be820c695c45e245aec15ab3916a2d106015e57bb7cf8f04abcf48bb2132'),(28,'c5720512-6389-4ddb-8a63-dbe8ae11a0f8','Jhon','Andrew','1993-10-13','andrew@gmail.com','b115ffaa989d46cbe5a79a37f0ad698a66fc13445043b105a1f4156062701569'),(29,'1f11d887-ca49-458c-8168-62484f34021a','John','Doe','2000-01-01','johndoe@example.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inventory`
--

DROP TABLE IF EXISTS `inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inventoryUniqueId` varchar(45) NOT NULL,
  `inventoryQuantity` int(11) NOT NULL,
  `inventoryUpdatedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `inventoryForProductId` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `inventoryUniqueId_UNIQUE` (`inventoryUniqueId`),
  KEY `inventoryForProduct_idx` (`inventoryForProductId`),
  CONSTRAINT `inventoryForProductId` FOREIGN KEY (`inventoryForProductId`) REFERENCES `product` (`productUniqueId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory`
--

LOCK TABLES `inventory` WRITE;
/*!40000 ALTER TABLE `inventory` DISABLE KEYS */;
INSERT INTO `inventory` VALUES (18,'e8d49fec-98de-4dc2-9666-195e85f194e0',5,'2023-04-06 11:16:55','ebf1987a-0b70-46f8-9697-f920768b1d5b'),(19,'9d7cfcd0-eaf5-4c71-806f-0511d54c5538',20,'2023-04-06 11:17:10','285618f1-4013-4b2d-ade4-639ff9c18101'),(20,'de438fd0-136d-49db-b108-db34676a0743',-3,'2023-04-06 11:18:31','285618f1-4013-4b2d-ade4-639ff9c18101'),(21,'48f10735-9d31-4f37-9c87-6f9371651176',5,'2023-04-06 11:19:07','d23ff52e-037b-4e60-bba4-a84fc2f1709c');
/*!40000 ALTER TABLE `inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loanUniqueId` varchar(45) DEFAULT NULL,
  `loanAmount` double NOT NULL,
  `noOfInstallment` int(11) NOT NULL,
  `loanBalance` varchar(45) NOT NULL,
  `loanForProduct` varchar(45) DEFAULT NULL,
  `loanOfCustomer` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loanUniqueId_UNIQUE` (`loanUniqueId`),
  KEY `loanOfCustomer_idx` (`loanOfCustomer`),
  KEY `loanForProduct_idx` (`loanForProduct`),
  CONSTRAINT `loanForProduct` FOREIGN KEY (`loanForProduct`) REFERENCES `product` (`productUniqueId`) ON DELETE CASCADE ON UPDATE SET NULL,
  CONSTRAINT `loanOfCustomer` FOREIGN KEY (`loanOfCustomer`) REFERENCES `customer` (`customerUniqueId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan`
--

LOCK TABLES `loan` WRITE;
/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
INSERT INTO `loan` VALUES (1,'6432337f-b4a9-4d64-a7b8-3df7c470e2d4',12000,4,'6000','ebf1987a-0b70-46f8-9697-f920768b1d5b','8e3afd67-71c8-4d97-afd6-198aed74e102'),(2,'3b738c14-7fba-4b31-91a4-a6e5197168d9',9000,3,'3000','a07211c9-b361-482a-af4c-ee20b1b037de','8484cfaf-9e7e-490d-b576-c0789d6d5d97'),(3,'65cb9b99-4c54-43b1-bb56-a1afb3601b13',6950,2,'3475','d23ff52e-037b-4e60-bba4-a84fc2f1709c','8484cfaf-9e7e-490d-b576-c0789d6d5d97');
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productUniqueId` varchar(45) NOT NULL,
  `productName` varchar(45) NOT NULL,
  `productPrice` double NOT NULL,
  `productQuantity` int(11) NOT NULL DEFAULT '0',
  `productDescription` varchar(256) DEFAULT NULL,
  `productBrand` int(11) DEFAULT NULL,
  `productCategory` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `productUniqueId_UNIQUE` (`productUniqueId`),
  KEY `productBrand_idx` (`productBrand`),
  KEY `productCategory_idx` (`productCategory`),
  CONSTRAINT `productBrand` FOREIGN KEY (`productBrand`) REFERENCES `brand` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `productCategory` FOREIGN KEY (`productCategory`) REFERENCES `category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (15,'ebf1987a-0b70-46f8-9697-f920768b1d5b','phone',10500,5,'normal keypad phone',15,8),(16,'a07211c9-b361-482a-af4c-ee20b1b037de','office_chair',9000,0,'Blue Cushioned',18,7),(17,'285618f1-4013-4b2d-ade4-639ff9c18101','Keyboard',2500,17,'black, wireless',19,8),(18,'d23ff52e-037b-4e60-bba4-a84fc2f1709c','laptop stand',6950,5,'adjustable',17,7);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-07 16:38:15
