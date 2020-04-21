-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: selection_сommittee
-- ------------------------------------------------------
-- Server version	5.7.29-0ubuntu0.18.04.1

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admins` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `last_name` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL DEFAULT '0',
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `admins_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` (`id`, `last_name`, `role_id`, `email`, `password`, `first_name`) VALUES (1,'Donets',0,'donets.study@gmail.com','donets','Alex'),(2,'Tolstoluzkii',0,'jedi@gmail.com','jedi','Yevgen'),(3,'Gray',0,'grayenterprises@gmail.com','red','Christian'),(4,'Воля',0,'volia@gmail.com','volia','Павел');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applications` (
  `id_app` int(11) NOT NULL AUTO_INCREMENT,
  `id_faculty` int(11) NOT NULL,
  `id_enrollee` int(11) NOT NULL,
  PRIMARY KEY (`id_app`),
  KEY `applications_ibfk_1` (`id_faculty`),
  KEY `applications_enrollees_id_fk` (`id_enrollee`),
  CONSTRAINT `applications_enrollees_id_fk` FOREIGN KEY (`id_enrollee`) REFERENCES `enrollees` (`id`) ON DELETE CASCADE,
  CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`id_faculty`) REFERENCES `faculties` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
INSERT INTO `applications` (`id_app`, `id_faculty`, `id_enrollee`) VALUES (27,1,1),(28,2,1),(29,3,1),(30,1,3);
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `certificates`
--

DROP TABLE IF EXISTS `certificates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `certificates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_enrollee` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `certificate_subjects__fk` (`id_subject`),
  KEY `certificates_ibfk_1` (`id_enrollee`),
  CONSTRAINT `certificate_subjects__fk` FOREIGN KEY (`id_subject`) REFERENCES `disciplines` (`id_d`),
  CONSTRAINT `certificates_ibfk_1` FOREIGN KEY (`id_enrollee`) REFERENCES `enrollees` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificates`
--

LOCK TABLES `certificates` WRITE;
/*!40000 ALTER TABLE `certificates` DISABLE KEYS */;
INSERT INTO `certificates` (`id`, `id_enrollee`, `id_subject`, `mark`) VALUES (1,1,11,12),(2,1,13,12),(3,1,8,12),(4,1,4,12),(5,1,9,12),(6,1,3,12),(7,1,12,12),(8,1,1,12),(9,1,15,12),(10,1,2,12),(11,1,5,12),(12,1,7,12),(13,3,11,10),(14,3,13,10),(15,3,8,10),(16,3,4,10),(17,3,9,10),(18,3,3,10),(19,3,12,10),(20,3,1,10),(21,3,15,10),(22,3,2,10),(23,3,5,10),(24,3,7,10);
/*!40000 ALTER TABLE `certificates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `disciplines`
--

DROP TABLE IF EXISTS `disciplines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `disciplines` (
  `id_d` int(11) NOT NULL AUTO_INCREMENT,
  `discipline_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id_d`),
  UNIQUE KEY `name` (`discipline_name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `disciplines`
--

LOCK TABLES `disciplines` WRITE;
/*!40000 ALTER TABLE `disciplines` DISABLE KEYS */;
INSERT INTO `disciplines` (`id_d`, `discipline_name`) VALUES (11,'biology'),(13,'chemistry'),(8,'economic'),(4,'english'),(9,'geography'),(3,'history'),(12,'literature'),(1,'math'),(15,'physical education'),(2,'physics'),(5,'rus'),(7,'ukr');
/*!40000 ALTER TABLE `disciplines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollees`
--

DROP TABLE IF EXISTS `enrollees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enrollees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `city` varchar(255) NOT NULL,
  `region` varchar(255) NOT NULL,
  `school` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `sec_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `accessAllowed` tinyint(1) NOT NULL DEFAULT '1',
  `entered` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `enrollees_password_uindex` (`password`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollees`
--

LOCK TABLES `enrollees` WRITE;
/*!40000 ALTER TABLE `enrollees` DISABLE KEYS */;
INSERT INTO `enrollees` (`id`, `email`, `city`, `region`, `school`, `first_name`, `sec_name`, `last_name`, `password`, `role_id`, `accessAllowed`, `entered`) VALUES (1,'aseevbogdan@gmail.com','Kharkiv','Kharkiv',6,'Bogdan','Andreevich','Aseev','aseev',1,1,1),(2,'bdk@gmail.com','Kharkiv','Kharkiv',142,'Vladislava','Vitalievna','Budko','budko',1,0,0),(3,'talisman4@gmail.com','Kharkiv','Kharkiv',26,'Anastasia','Oleksandrivna','Popova','popova',1,1,1),(4,'cbbonamente@gmail.com','Kharkiv','Kharkiv',16,'Yen','Viet','Bach','bach',1,0,0),(5,'redhot@gmail.com','Donetsk','Donetsk',32,'Konstantin','Anatolevich','Nagornyy','konst',1,1,0);
/*!40000 ALTER TABLE `enrollees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculties`
--

DROP TABLE IF EXISTS `faculties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculties` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `count_budget` int(11) NOT NULL,
  `count_total` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculties`
--

LOCK TABLES `faculties` WRITE;
/*!40000 ALTER TABLE `faculties` DISABLE KEYS */;
INSERT INTO `faculties` (`id`, `name`, `count_budget`, `count_total`) VALUES (1,'FKN',180,200),(2,'RBECS',120,140),(3,'Biological',140,180),(4,'Geology',100,130),(5,'Historical',140,160),(6,'Psychology',100,120),(7,'Sociological',130,150);
/*!40000 ALTER TABLE `faculties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirements`
--

DROP TABLE IF EXISTS `requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requirements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_faculty` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `min_mark` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `requirements_faculties_id_fk` (`id_faculty`),
  KEY `requirements_subjects_id_fk` (`id_subject`),
  CONSTRAINT `requirements_disciplines_id_d_fk` FOREIGN KEY (`id_subject`) REFERENCES `disciplines` (`id_d`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `requirements_faculties_id_fk` FOREIGN KEY (`id_faculty`) REFERENCES `faculties` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirements`
--

LOCK TABLES `requirements` WRITE;
/*!40000 ALTER TABLE `requirements` DISABLE KEYS */;
INSERT INTO `requirements` (`id`, `id_faculty`, `id_subject`, `min_mark`) VALUES (1,1,1,190),(2,1,4,190),(3,1,7,180),(4,2,2,160),(5,2,1,150),(6,2,3,140),(7,3,2,170),(8,3,13,180),(9,3,11,180),(10,4,12,170),(11,4,9,170),(12,4,4,180),(13,5,3,160),(14,5,4,170),(15,5,12,180),(16,6,3,150),(17,6,12,150),(18,6,4,170),(19,7,3,160),(20,7,4,170),(21,7,12,160);
/*!40000 ALTER TABLE `requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `result` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_faculty` int(11) DEFAULT NULL,
  `id_enrolee` int(11) DEFAULT NULL,
  `allowed` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `statement_enrollees_id_fk` (`id_enrolee`),
  KEY `result_faculties_id_fk` (`id_faculty`),
  CONSTRAINT `result_faculties_id_fk` FOREIGN KEY (`id_faculty`) REFERENCES `faculties` (`id`),
  CONSTRAINT `statement_enrollees_id_fk` FOREIGN KEY (`id_enrolee`) REFERENCES `enrollees` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` (`id`, `id_faculty`, `id_enrolee`, `allowed`) VALUES (1,2,1,1),(2,1,3,1),(3,1,1,1);
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statement`
--

DROP TABLE IF EXISTS `statement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `statement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_application` int(11) NOT NULL,
  `ser_mark` int(11) DEFAULT '0',
  `id_fac` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `results_applications_id_fk` (`id_application`),
  KEY `competition_faculties_id_fk` (`id_fac`),
  CONSTRAINT `competition_faculties_id_fk` FOREIGN KEY (`id_fac`) REFERENCES `faculties` (`id`),
  CONSTRAINT `results_applications_id_fk` FOREIGN KEY (`id_application`) REFERENCES `applications` (`id_app`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statement`
--

LOCK TABLES `statement` WRITE;
/*!40000 ALTER TABLE `statement` DISABLE KEYS */;
/*!40000 ALTER TABLE `statement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zno`
--

DROP TABLE IF EXISTS `zno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_enrollee` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `zno_ibfk_2` (`id_subject`),
  KEY `zno_ibfk_1` (`id_enrollee`),
  CONSTRAINT `zno_ibfk_1` FOREIGN KEY (`id_enrollee`) REFERENCES `enrollees` (`id`) ON DELETE CASCADE,
  CONSTRAINT `zno_ibfk_2` FOREIGN KEY (`id_subject`) REFERENCES `disciplines` (`id_d`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zno`
--

LOCK TABLES `zno` WRITE;
/*!40000 ALTER TABLE `zno` DISABLE KEYS */;
INSERT INTO `zno` (`id`, `id_enrollee`, `id_subject`, `mark`) VALUES (1,1,2,180),(2,1,4,180),(3,1,15,180),(4,1,1,180),(5,1,3,180),(6,1,13,180),(7,1,11,180),(8,3,1,180),(9,3,4,170),(10,3,7,190);
/*!40000 ALTER TABLE `zno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-21 14:50:33
