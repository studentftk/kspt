/*
MySQL Backup
Source Server Version: 5.5.25
Source Database: spbstu
Date: 31.10.2014 10:55:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `commentsnews`
-- ----------------------------
DROP TABLE IF EXISTS `commentsnews`;
CREATE TABLE `commentsnews` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NewsID` int(11) NOT NULL,
  `Сomment` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_NewsID_ID` (`NewsID`),
  CONSTRAINT `FK_NewsID_ID` FOREIGN KEY (`NewsID`) REFERENCES `news` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `commentsplaces`
-- ----------------------------
DROP TABLE IF EXISTS `commentsplaces`;
CREATE TABLE `commentsplaces` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PlaceID` int(11) NOT NULL,
  `Comment` varchar(300) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_PlaceID_ID` (`PlaceID`),
  CONSTRAINT `FK_PlaceID_ID` FOREIGN KEY (`PlaceID`) REFERENCES `places` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
--  Table structure for `institutes`
-- ----------------------------
DROP TABLE IF EXISTS `institutes`;
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

-- ----------------------------
--  Table structure for `messages`
-- ----------------------------
DROP TABLE IF EXISTS `messages`;
CREATE TABLE `messages` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MailerID` int(11) NOT NULL,
  `SenderID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_MailerID_ID` (`MailerID`),
  KEY `FK_SenderID_ID` (`SenderID`),
  CONSTRAINT `FK_MailerID_ID` FOREIGN KEY (`MailerID`) REFERENCES `users` (`ID`),
  CONSTRAINT `FK_SenderID_ID` FOREIGN KEY (`SenderID`) REFERENCES `users` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `news`
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Header` varchar(150) NOT NULL,
  `Annotation` text,
  `Date` date DEFAULT NULL,
  `Text` text,
  `Photo` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `places`
-- ----------------------------
DROP TABLE IF EXISTS `places`;
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

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) DEFAULT NULL,
  `Surname` varchar(100) DEFAULT NULL,
  `InstituteID` int(11) DEFAULT NULL,
  `Group` varchar(20) DEFAULT NULL,
  `About` text,
  `Photo` varchar(200) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `InstituteID` (`InstituteID`),
  CONSTRAINT `FK_InstituteID_ID` FOREIGN KEY (`InstituteID`) REFERENCES `institutes` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
