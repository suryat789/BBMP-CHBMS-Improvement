-- MySQL dump 10.13  Distrib 5.5.62, for Win64 (AMD64)
--
-- Host: 10.85.27.111    Database: bms
-- ------------------------------------------------------
-- Server version	5.7.34
​
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
​
--
-- Table structure for table `bed`
--
​
DROP TABLE IF EXISTS `bed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bed` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bed_id` varchar(255) DEFAULT NULL,
  `blocked` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `occupied` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_by_msg_id` varchar(255) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `vacant` int(11) DEFAULT NULL,
  `hospital_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbntk9h6d0q05gbysrwgthudaf` (`hospital_id`),
  CONSTRAINT `FKbntk9h6d0q05gbysrwgthudaf` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `bed`
--
​
LOCK TABLES `bed` WRITE;
/*!40000 ALTER TABLE `bed` DISABLE KEYS */;
/*!40000 ALTER TABLE `bed` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `bed_audit`
--
​
DROP TABLE IF EXISTS `bed_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bed_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `audited_on` datetime(6) DEFAULT NULL,
  `bed_id` varchar(255) DEFAULT NULL,
  `blocked` int(11) DEFAULT NULL,
  `capacity` int(11) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `hospital_id` varchar(255) DEFAULT NULL,
  `occupied` int(11) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_by_msg_id` varchar(255) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `vacant` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `bed_audit`
--
​
LOCK TABLES `bed_audit` WRITE;
/*!40000 ALTER TABLE `bed_audit` DISABLE KEYS */;
/*!40000 ALTER TABLE `bed_audit` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `hospital`
--
​
DROP TABLE IF EXISTS `hospital`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospital` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `hospital_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pincode` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_by_msg_id` varchar(255) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `hospital`
--
​
LOCK TABLES `hospital` WRITE;
/*!40000 ALTER TABLE `hospital` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `hospital_audit`
--
​
DROP TABLE IF EXISTS `hospital_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hospital_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `audited_on` datetime(6) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `hospital_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `pincode` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `updated_by_msg_id` varchar(255) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `hospital_audit`
--
​
LOCK TABLES `hospital_audit` WRITE;
/*!40000 ALTER TABLE `hospital_audit` DISABLE KEYS */;
/*!40000 ALTER TABLE `hospital_audit` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `jhi_persistent_audit_event`
--
​
DROP TABLE IF EXISTS `jhi_persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_date` datetime(6) DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `principal` varchar(255) NOT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `jhi_persistent_audit_event`
--
​
LOCK TABLES `jhi_persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `jhi_persistent_audit_evt_data`
--
​
DROP TABLE IF EXISTS `jhi_persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`event_id`,`name`),
  CONSTRAINT `FK2ehnyx2si4tjd2nt4q7y40v8m` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `jhi_persistent_audit_evt_data`
--
​
LOCK TABLES `jhi_persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `jhi_persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `patient`
--
​
DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bucode` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `patient_id` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `queue_name` varchar(255) DEFAULT NULL,
  `queue_type` varchar(255) DEFAULT NULL,
  `srf_number` varchar(255) DEFAULT NULL,
  `time_added_to_queue` datetime(6) DEFAULT NULL,
  `updated_by_msg_id` varchar(255) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `patient`
--
​
LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Table structure for table `patient_audit`
--
​
DROP TABLE IF EXISTS `patient_audit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `audited_on` datetime(6) DEFAULT NULL,
  `bucode` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `patient_id` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `queue_name` varchar(255) DEFAULT NULL,
  `queue_type` varchar(255) DEFAULT NULL,
  `srf_number` varchar(255) DEFAULT NULL,
  `time_added_to_queue` datetime(6) DEFAULT NULL,
  `updated_by_msg_id` varchar(255) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `zone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
​
--
-- Dumping data for table `patient_audit`
--
​
LOCK TABLES `patient_audit` WRITE;
/*!40000 ALTER TABLE `patient_audit` DISABLE KEYS */;
/*!40000 ALTER TABLE `patient_audit` ENABLE KEYS */;
UNLOCK TABLES;
​
--
-- Dumping routines for database 'bms'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;
​
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
​
-- Dump completed on 2021-05-12 20:58:41

ALTER TABLE `patient`
ADD UNIQUE INDEX `patient_id_UNIQUE` (`patient_id` ASC),
ADD UNIQUE INDEX `srf_number_UNIQUE` (`srf_number` ASC),
ADD UNIQUE INDEX `bucode_UNIQUE` (`bucode` ASC);


ALTER TABLE `hospital`
ADD UNIQUE INDEX `hospital_id_UNIQUE` (`hospital_id` ASC);

CREATE INDEX patient_zone_IDX USING BTREE ON patient (`zone`);
CREATE INDEX patient_queue_name_IDX USING BTREE ON patient (queue_name);
CREATE INDEX patient_queue_type_IDX USING BTREE ON patient (queue_type,queue_name,time_added_to_queue);


CREATE INDEX bed_type_IDX USING BTREE ON bed (`type`);
CREATE INDEX bed_updated_on_IDX USING BTREE ON bed (updated_on);
CREATE INDEX bed_type_updated_on_IDX USING BTREE ON bed (`type`,updated_on);
