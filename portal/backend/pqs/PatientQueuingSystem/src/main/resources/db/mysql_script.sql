/*
Check if the contact number, srf_number, zone, hospital_id,icrmr_number, srf_number requires varchar(100). This should be common across event bus etc.
And type, bed_type,  requires varchar(50)
*/

CREATE TABLE `patient` (
  `patient_id` int(20) NOT NULL,
  `bu_number` int(20) DEFAULT NULL,
  `contact_number` varchar(100) DEFAULT NULL,
  `icmr_number` varchar(100) DEFAULT NULL,
  `srf_number` varchar(100) DEFAULT NULL,
  `zone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
);

CREATE TABLE `queue` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL,
  `patient_id` int(20) DEFAULT NULL,
  `enqueue_timestamp` timestamp NULL DEFAULT NULL,
  `updated_timestamp` timestamp NULL DEFAULT NULL,
  `zone` varchar(100) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '0',
  `push_front` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `patient_index` (`patient_id`),
  KEY `type_index` (`type`, `push_front`),
  KEY `type_zone_index` (`type`,`zone`, `push_front`),
  KEY `cron_index` (`type`,`enqueue_timestamp`,`zone`,`is_active`, `push_front`),
  CONSTRAINT `queue_ibfk_2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
);

CREATE TABLE `allotment` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `queue_id` int(20) NOT NULL,
  `patient_id` int(20) NOT NULL,
  `hospital_id` varchar(100) NOT NULL,
  `bed_type` varchar(50) DEFAULT NULL,
  `request_time` timestamp NULL DEFAULT NULL,
  `allocation_time` timestamp NULL DEFAULT NULL,
  `zone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
);
