-- MySQL dump 10.13  Distrib 5.1.57, for Win32 (ia32)
--
-- Host: studentspbstu.tk    Database: student
-- ------------------------------------------------------
-- Server version	5.5.40-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `commentsnews`
--

DROP TABLE IF EXISTS `commentsnews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentsnews` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NewsID` int(11) NOT NULL,
  `Сomment` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_NewsID_ID` (`NewsID`),
  CONSTRAINT `FK_NewsID_ID` FOREIGN KEY (`NewsID`) REFERENCES `news` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentsnews`
--

LOCK TABLES `commentsnews` WRITE;
/*!40000 ALTER TABLE `commentsnews` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentsnews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentsplaces`
--

DROP TABLE IF EXISTS `commentsplaces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commentsplaces` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PlaceID` int(11) NOT NULL,
  `Comment` varchar(300) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PlaceID_ID` (`PlaceID`),
  CONSTRAINT `FK_PlaceID_ID` FOREIGN KEY (`PlaceID`) REFERENCES `places` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentsplaces`
--

LOCK TABLES `commentsplaces` WRITE;
/*!40000 ALTER TABLE `commentsplaces` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentsplaces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `institutes`
--

DROP TABLE IF EXISTS `institutes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `institutes` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Title` varchar(150) NOT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `HouseNumber` int(11) DEFAULT NULL,
  `HouseCorp` int(11) DEFAULT NULL,
  `geo` varchar(100) DEFAULT NULL,
  `About` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `institutes`
--

LOCK TABLES `institutes` WRITE;
/*!40000 ALTER TABLE `institutes` DISABLE KEYS */;
/*!40000 ALTER TABLE `institutes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MailerID` int(11) NOT NULL,
  `SenderID` int(11) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Message` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MailerID_ID` (`MailerID`),
  KEY `FK_SenderID_ID` (`SenderID`),
  CONSTRAINT `FK_MailerID_ID` FOREIGN KEY (`MailerID`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_SenderID_ID` FOREIGN KEY (`SenderID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (15,736880,45,'2014-12-09 03:19:55','Привет!'),(16,736880,45,'2014-12-09 03:20:02','Привет!'),(17,736880,45,'2014-12-09 03:27:04','Привет!'),(18,45,736880,'2014-12-09 03:27:39','Привет :)!'),(19,45,736880,'2014-12-09 03:27:49','Привет :)!'),(20,45,736880,'2014-12-09 04:25:41','Хайло');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Header` varchar(150) NOT NULL,
  `Annotation` text,
  `Date` date DEFAULT NULL,
  `Text` text,
  `Photo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `places`
--

DROP TABLE IF EXISTS `places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `places` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(20) NOT NULL,
  `Title` varchar(150) NOT NULL,
  `Street` varchar(100) DEFAULT NULL,
  `HouseNumber` int(11) DEFAULT NULL,
  `HouseCorp` varchar(11) DEFAULT NULL,
  `Geo` varchar(100) DEFAULT NULL,
  `About` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `places`
--

LOCK TABLES `places` WRITE;
/*!40000 ALTER TABLE `places` DISABLE KEYS */;
/*!40000 ALTER TABLE `places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Surname` varchar(100) DEFAULT NULL,
  `InstituteID` int(11) DEFAULT NULL,
  `Group` varchar(20) DEFAULT NULL,
  `About` text,
  `Photo` varchar(200) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  `SocialType` varchar(4) NOT NULL,
  `SocialId` int(11) NOT NULL,
  `SocialToken` varchar(150) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `SocialToken` (`SocialToken`),
  KEY `InstituteID` (`InstituteID`),
  CONSTRAINT `FK_InstituteID_ID` FOREIGN KEY (`InstituteID`) REFERENCES `institutes` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=736882 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (45,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'vko',5644,'adsfa'),(736880,'Toster',NULL,NULL,NULL,NULL,NULL,NULL,'vk',155,'asd'),(736881,'Александр','Сухинин',NULL,NULL,NULL,'http://cs10529.vk.me/u12099297/e_f30dd109.jpg',NULL,'vk',12099297,'c1fcd464d30573601ea5aebbbfa75c44da9a42a2bfd0383da9ac4f832f82b1b42587b8fb57dcdfda83d5d');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `visited`
--

DROP TABLE IF EXISTS `visited`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `visited` (
  `idUser` int(11) NOT NULL,
  `idPlace` int(11) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  KEY `fk_idUser` (`idUser`),
  KEY `fk_idPlace` (`idPlace`),
  CONSTRAINT `fk_idPlace` FOREIGN KEY (`idPlace`) REFERENCES `users` (`ID`),
  CONSTRAINT `fk_idUser` FOREIGN KEY (`idUser`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `visited`
--

LOCK TABLES `visited` WRITE;
/*!40000 ALTER TABLE `visited` DISABLE KEYS */;
/*!40000 ALTER TABLE `visited` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-11 19:53:08
