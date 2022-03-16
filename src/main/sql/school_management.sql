-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               8.0.27 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for school_management
CREATE DATABASE IF NOT EXISTS `school_management` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school_management`;

-- Dumping structure for table school_management.attacment
CREATE TABLE IF NOT EXISTS `attacment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.attacment: ~0 rows (approximately)
DELETE FROM `attacment`;
/*!40000 ALTER TABLE `attacment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attacment` ENABLE KEYS */;

-- Dumping structure for table school_management.blog
CREATE TABLE IF NOT EXISTS `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpxk2jtysqn41oop7lvxcp6dqq` (`user_id`),
  CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.blog: ~0 rows (approximately)
DELETE FROM `blog`;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;

-- Dumping structure for table school_management.class
CREATE TABLE IF NOT EXISTS `class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(10) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `grade` int NOT NULL,
  `school_year` int NOT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjtee0w5l26pivuxosvmxv0ff` (`user_id`),
  CONSTRAINT `FKhjtee0w5l26pivuxosvmxv0ff` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.class: ~12 rows (approximately)
DELETE FROM `class`;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` (`id`, `class_name`, `created_date`, `grade`, `school_year`, `updated_date`, `user_id`) VALUES
	(1, '10A1', NULL, 10, 2022, NULL, 2),
	(2, '10A2', '2022-03-10 00:00:00.000000', 10, 2022, '2022-03-11 16:58:17.000000', 1),
	(3, '10A3', '2022-03-10 00:00:00.000000', 10, 2022, '2022-03-11 16:50:15.000000', 1),
	(4, '10A4', '2022-03-10 00:00:00.000000', 10, 2022, '2022-03-10 00:00:00.000000', 1),
	(5, '11A1', '2022-03-10 00:00:00.000000', 11, 2022, '2022-03-10 00:00:00.000000', 1),
	(6, '11A2', '2022-03-10 00:00:00.000000', 11, 2022, '2022-03-10 00:00:00.000000', 1),
	(7, '11A3', '2022-03-10 00:00:00.000000', 11, 2022, '2022-03-10 00:00:00.000000', 1),
	(8, '11A4', '2022-03-10 00:00:00.000000', 11, 2022, '2022-03-10 00:00:00.000000', 1),
	(9, '12A1', '2022-03-10 00:00:00.000000', 12, 2022, '2022-03-10 14:40:22.000000', 1),
	(10, '12A2', '2022-03-10 00:00:00.000000', 12, 2022, '2022-03-10 14:39:42.000000', 1),
	(11, '12A3', '2022-03-10 00:00:00.000000', 12, 2022, '2022-03-10 00:00:00.000000', 1),
	(12, '12A4', '2022-03-10 00:00:00.000000', 12, 2022, '2022-03-10 00:00:00.000000', 1);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;

-- Dumping structure for table school_management.class_teacher_subject
CREATE TABLE IF NOT EXISTS `class_teacher_subject` (
  `id` int NOT NULL,
  `subject_id` int DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg3aq3ycx05p3qhmsg84vomes8` (`subject_id`),
  KEY `FKfmjly5l6h5baj18gppqmti2ud` (`class_id`),
  KEY `FKcyuugr3nif17s8hgifhrom8b7` (`user_id`),
  CONSTRAINT `FKcyuugr3nif17s8hgifhrom8b7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfmjly5l6h5baj18gppqmti2ud` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FKg3aq3ycx05p3qhmsg84vomes8` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.class_teacher_subject: ~0 rows (approximately)
DELETE FROM `class_teacher_subject`;
/*!40000 ALTER TABLE `class_teacher_subject` DISABLE KEYS */;
INSERT INTO `class_teacher_subject` (`id`, `subject_id`, `class_id`, `user_id`) VALUES
	(14, 8, 1, 2),
	(15, 6, 1, NULL),
	(16, 7, 1, NULL),
	(17, 3, 1, NULL),
	(18, 4, 1, NULL),
	(19, 2, 1, NULL),
	(20, 1, 1, NULL),
	(21, 9, 1, NULL),
	(22, 5, 1, NULL);
/*!40000 ALTER TABLE `class_teacher_subject` ENABLE KEYS */;

-- Dumping structure for table school_management.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.hibernate_sequence: ~0 rows (approximately)
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(23);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table school_management.mark
CREATE TABLE IF NOT EXISTS `mark` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coefficient` float DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `subjects_id` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkinjhmdsofm3luu3s61riey70` (`created_by`),
  KEY `FKigcxa2iu41rq3bpmj6tage1ue` (`subjects_id`),
  KEY `FKf0ptqhygubgskvp43qv990yem` (`updated_by`),
  KEY `FKowe2x7exr25ndsor8yq0b9710` (`student_id`),
  CONSTRAINT `FKf0ptqhygubgskvp43qv990yem` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKigcxa2iu41rq3bpmj6tage1ue` FOREIGN KEY (`subjects_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FKkinjhmdsofm3luu3s61riey70` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKowe2x7exr25ndsor8yq0b9710` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.mark: ~0 rows (approximately)
DELETE FROM `mark`;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
INSERT INTO `mark` (`id`, `coefficient`, `created_date`, `semester`, `type`, `updated_date`, `created_by`, `student_id`, `subjects_id`, `updated_by`) VALUES
	(1, 8, '2022-03-16 15:29:15.000000', 1, 4, '2022-03-16 15:29:23.000000', 2, 3, 8, 2);
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;

-- Dumping structure for table school_management.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.role: ~2 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'TEACHER'),
	(3, 'HOMEROOM_TEACHER'),
	(4, 'STUDENT');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table school_management.student_evaluate
CREATE TABLE IF NOT EXISTS `student_evaluate` (
  `id` int NOT NULL AUTO_INCREMENT,
  `academic_ability` int DEFAULT NULL,
  `conduct` int DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `evaluate` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `semester` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7uyfqp7ix83nb096c55oqnr3y` (`created_by`),
  KEY `FK7r0pnaycxkc9v6hsjsblq9v51` (`updated_by`),
  KEY `FKbde9kc2v4sqr6h88qhluodju7` (`student_id`),
  CONSTRAINT `FK7r0pnaycxkc9v6hsjsblq9v51` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7uyfqp7ix83nb096c55oqnr3y` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKbde9kc2v4sqr6h88qhluodju7` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.student_evaluate: ~0 rows (approximately)
DELETE FROM `student_evaluate`;
/*!40000 ALTER TABLE `student_evaluate` DISABLE KEYS */;
INSERT INTO `student_evaluate` (`id`, `academic_ability`, `conduct`, `created_date`, `evaluate`, `semester`, `updated_date`, `created_by`, `updated_by`, `student_id`) VALUES
	(1, 4, 1, '2022-03-16 00:00:00.000000', 'Good', 1, '2022-03-16 00:00:00.000000', NULL, NULL, NULL);
/*!40000 ALTER TABLE `student_evaluate` ENABLE KEYS */;

-- Dumping structure for table school_management.subjects
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gaix2pna1ulbxhdl4kbq9yglt` (`subject_name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.subjects: ~9 rows (approximately)
DELETE FROM `subjects`;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`id`, `subject_name`) VALUES
	(8, 'Biology'),
	(6, 'Chemistry'),
	(7, 'Civic Education'),
	(3, 'English'),
	(4, 'History'),
	(2, 'Literature'),
	(1, 'Maths'),
	(9, 'Physical Education'),
	(5, 'Physics');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;

-- Dumping structure for table school_management.teacher_subjects
CREATE TABLE IF NOT EXISTS `teacher_subjects` (
  `user_id` int NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`subject_id`,`user_id`),
  KEY `FKockx0rkpkmbpvxh9ftrmgrx4q` (`user_id`),
  CONSTRAINT `FKdweqkwxroox2u7pbmksehx04i` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FKockx0rkpkmbpvxh9ftrmgrx4q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.teacher_subjects: ~100 rows (approximately)
DELETE FROM `teacher_subjects`;
/*!40000 ALTER TABLE `teacher_subjects` DISABLE KEYS */;
INSERT INTO `teacher_subjects` (`user_id`, `subject_id`) VALUES
	(2, 8),
	(4, 8);
/*!40000 ALTER TABLE `teacher_subjects` ENABLE KEYS */;

-- Dumping structure for table school_management.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  `user_info_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKh98qmq3hqffkhv8pw266v2vb4` (`user_info_id`),
  CONSTRAINT `FKh98qmq3hqffkhv8pw266v2vb4` FOREIGN KEY (`user_info_id`) REFERENCES `user_info` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.user: ~3 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `address`, `created_date`, `dob`, `email`, `full_name`, `image`, `password`, `phone`, `updated_date`, `username`, `user_info_id`) VALUES
	(1, 'HN-VN', '2022-03-15 13:12:00.000000', '2002-03-15', 'admin@gmail.com', 'Admin', '', '$2a$10$FD3pv3AWRdYwIrKo2dw0aeEKbZxm1X1NuVy70VyJPcUgNPZu61HN2', '1324654798', '2022-03-15 16:31:41.526508', 'admin', 1),
	(2, 'HN-VN', '2022-03-15 13:30:00.000000', '2022-03-16', 'teacher1@gmail.com', 'Teacher 1', 'fe5d39e6-75b7-4dfc-85bf-5ae585332a31.png', '$2a$10$FD3pv3AWRdYwIrKo2dw0aeEKbZxm1X1NuVy70VyJPcUgNPZu61HN2', '13465789', '2022-03-16 08:38:10.929574', 'teacher1', 4),
	(3, 'HN-VN', '2022-03-15 16:18:00.000000', '2002-09-29', 'student1@gmail.com', 'Student 1', '', '$2a$10$FD3pv3AWRdYwIrKo2dw0aeEKbZxm1X1NuVy70VyJPcUgNPZu61HN2', '321465978', '2022-03-16 08:34:18.652119', 'abc', 17),
	(4, 'HN-VN', '2022-03-16 13:28:50.059833', '2022-03-16', 'teacher2@gmail.com', 'Teacher 2', '', '$2a$10$iWfe0ZEe5jmguoAmf9uaIOkpxWeJe82PVZ0yc73er5ZfpEMp7m7GK', '132465978', '2022-03-16 13:28:50.059833', 'teacher2', 19);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table school_management.user_info
CREATE TABLE IF NOT EXISTS `user_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `admission_year` int DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `graduate_year` int DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjyjddfgmhcm0wath4c97oed2g` (`class_id`),
  CONSTRAINT `FKjyjddfgmhcm0wath4c97oed2g` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.user_info: ~3 rows (approximately)
DELETE FROM `user_info`;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` (`id`, `admission_year`, `deleted`, `end_date`, `graduate_year`, `start_date`, `status`, `class_id`) VALUES
	(1, 1234, b'0', '2022-03-15', 12345, '2022-03-15', 1, 1),
	(4, NULL, b'0', '2022-03-30', NULL, '2022-03-16', NULL, NULL),
	(17, 2022, NULL, NULL, 2024, NULL, 1, 1),
	(19, NULL, b'0', '2022-03-30', NULL, '2022-03-09', NULL, NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;

-- Dumping structure for table school_management.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table school_management.user_role: ~4 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(1, 1),
	(2, 2),
	(4, 2),
	(2, 3),
	(3, 4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
