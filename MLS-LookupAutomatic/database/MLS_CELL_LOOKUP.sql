-- MySQL dump 10.15  Distrib 10.0.34-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: MLS_CELL_LOOKUP
-- ------------------------------------------------------
-- Server version	10.0.34-MariaDB-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `CELL_DB`
--

DROP TABLE IF EXISTS `CELL_DB`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CELL_DB` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `RADIO` varchar(5) NOT NULL,
  `MCC` int(11) NOT NULL,
  `NET` int(11) NOT NULL,
  `AREA` int(11) NOT NULL,
  `CELL` int(11) NOT NULL,
  `UNIT` varchar(45) DEFAULT NULL,
  `LON` decimal(11,8) NOT NULL,
  `LAT` decimal(11,8) NOT NULL,
  `RANGES` int(11) NOT NULL,
  `SAMPLES` varchar(45) NOT NULL,
  `CHANGEABLE` int(1) NOT NULL,
  `CREATED` varchar(15) NOT NULL,
  `UPDATED` varchar(15) NOT NULL,
  `AVERAGE_SIGNAL` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UN_COL` (`RADIO`,`MCC`,`NET`,`AREA`,`CELL`)
) ENGINE=InnoDB AUTO_INCREMENT=21371014 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `COUNTER`
--

DROP TABLE IF EXISTS `COUNTER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COUNTER` (
  `COUNTER_VALUE` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `MARKER`
--

DROP TABLE IF EXISTS `MARKER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MARKER` (
  `MARKER_VALUE` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-15 11:13:31
