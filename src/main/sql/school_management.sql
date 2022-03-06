-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.28 - MySQL Community Server - GPL
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
DROP DATABASE IF EXISTS `school_management`;
CREATE DATABASE IF NOT EXISTS `school_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school_management`;

-- Dumping structure for table school_management.attacment
DROP TABLE IF EXISTS `attacment`;
CREATE TABLE IF NOT EXISTS `attacment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.attacment: ~0 rows (approximately)
DELETE FROM `attacment`;
/*!40000 ALTER TABLE `attacment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attacment` ENABLE KEYS */;

-- Dumping structure for table school_management.blog
DROP TABLE IF EXISTS `blog`;
CREATE TABLE IF NOT EXISTS `blog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpxk2jtysqn41oop7lvxcp6dqq` (`user_id`),
  CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.blog: ~0 rows (approximately)
DELETE FROM `blog`;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;

-- Dumping structure for table school_management.class
DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `grade` int DEFAULT NULL,
  `school_year` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjtee0w5l26pivuxosvmxv0ff` (`user_id`),
  CONSTRAINT `FKhjtee0w5l26pivuxosvmxv0ff` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.class: ~12 rows (approximately)
DELETE FROM `class`;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` (`id`, `class_name`, `created_date`, `grade`, `school_year`, `updated_date`, `user_id`) VALUES
	(1, '10A1', NULL, 10, 2022, NULL, 20),
	(2, '10A2', NULL, 10, 2022, NULL, 20),
	(3, '10A3', NULL, 10, 2022, NULL, 27),
	(4, '10A4', NULL, 10, 2022, NULL, 28),
	(5, '11A1', NULL, 11, 2022, NULL, 20),
	(6, '11A2', NULL, 11, 2022, NULL, 20),
	(7, '11A3', NULL, 11, 2022, NULL, 20),
	(8, '11A4', NULL, 11, 2022, NULL, 20),
	(9, '12A1', NULL, 12, 2022, NULL, 20),
	(10, '12A2', NULL, 12, 2022, NULL, 20),
	(11, '12A3', NULL, 12, 2022, NULL, 20),
	(12, '12A4', NULL, 12, 2022, NULL, 20),
	(13, '12A7', NULL, 12, 2022, NULL, 38);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;

-- Dumping structure for table school_management.class_teacher_subject
DROP TABLE IF EXISTS `class_teacher_subject`;
CREATE TABLE IF NOT EXISTS `class_teacher_subject` (
  `class_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`class_id`,`user_id`),
  KEY `FKg3aq3ycx05p3qhmsg84vomes8` (`subject_id`),
  KEY `FKcyuugr3nif17s8hgifhrom8b7` (`user_id`),
  CONSTRAINT `FKcyuugr3nif17s8hgifhrom8b7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfmjly5l6h5baj18gppqmti2ud` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FKg3aq3ycx05p3qhmsg84vomes8` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.class_teacher_subject: ~0 rows (approximately)
DELETE FROM `class_teacher_subject`;
/*!40000 ALTER TABLE `class_teacher_subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `class_teacher_subject` ENABLE KEYS */;

-- Dumping structure for table school_management.mark
DROP TABLE IF EXISTS `mark`;
CREATE TABLE IF NOT EXISTS `mark` (
  `id` int NOT NULL,
  `coefficient` int DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `type` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `class_id` int NOT NULL,
  PRIMARY KEY (`subject_id`,`class_id`),
  KEY `FKkinjhmdsofm3luu3s61riey70` (`created_by`),
  KEY `FKf0ptqhygubgskvp43qv990yem` (`updated_by`),
  KEY `FKcwocngy0rfmqdhqwm3qlrfamx` (`student_id`),
  KEY `FKiwes878sbpomvpdnmem5lu3wc` (`class_id`),
  CONSTRAINT `FKcwocngy0rfmqdhqwm3qlrfamx` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FKf0ptqhygubgskvp43qv990yem` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKiwes878sbpomvpdnmem5lu3wc` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FKkinjhmdsofm3luu3s61riey70` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FKshsgnpl9oe52tc445e1in7g1a` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.mark: ~0 rows (approximately)
DELETE FROM `mark`;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;

-- Dumping structure for table school_management.role
DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.role: ~3 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'TEACHER'),
	(3, 'STUDENT'),
	(4, 'HOMEROOM_TEACHER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table school_management.student
DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `admission_year` int DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `graduate_year` int DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.student: ~5 rows (approximately)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `address`, `admission_year`, `created_date`, `dob`, `full_name`, `graduate_year`, `image`, `password`, `status`, `updated_date`, `username`) VALUES
	(1, 'HN-VN', 2021, '2022-03-02 16:32:36.670633', '2022-03-16', 'Student 1', 2023, NULL, '$2a$10$3qyV/7b2OeOAzbcRz93l7.hgwqnh.Em3r8LAt/b3BhpH4.lVMJ45G', 2, '2022-03-02 16:32:36.670633', 'student1'),
	(2, 'HN-VN', 2022, '2022-03-02 16:45:04.025696', '2022-03-16', 'Student 2', 2024, NULL, '$2a$10$Lm/fA6KRT5UmbykAoPoyZ.CwvUb/esjqgPSgT3GtXcTN2.CsyNVY.', 1, '2022-03-02 16:45:04.025696', 'student2'),
	(3, 'HN-VN', 2022, '2022-03-02 16:48:55.822554', '2022-03-08', 'Student 3', 2024, NULL, '$2a$10$BFE1LX2XauwO6ydcKkaM5OdNZoP5hfMw4nXfVjexCaIo.GFqbwi8q', 3, '2022-03-02 16:48:55.822554', 'student3'),
	(4, 'HN-VN', 2022, '2022-03-02 16:50:28.076353', '2022-03-08', 'Student 4', 2023, NULL, '$2a$10$8rosf0aIEQPqZyreyoGIiuizJiOGwcmfJ1eKH1MtrUl1o20KIcfSW', 2, '2022-03-02 16:50:28.076353', 'student4'),
	(5, 'HN-VN', 2022, '2022-03-02 17:52:04.949196', '2022-03-12', 'Student 6', 2024, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '$2a$10$Guu9m2Lwsyu3AGtukainFOAs2j9.uDHM7P6e5JI5bYh8zXJeQQny6', 1, '2022-03-02 17:52:04.949196', 'std_2022_4');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- Dumping structure for table school_management.student_class
DROP TABLE IF EXISTS `student_class`;
CREATE TABLE IF NOT EXISTS `student_class` (
  `student_id` int NOT NULL,
  `class_id` int NOT NULL,
  PRIMARY KEY (`class_id`,`student_id`),
  KEY `FK2f81ovfviq7rv4jhpdr46dk3e` (`student_id`),
  CONSTRAINT `FK2f81ovfviq7rv4jhpdr46dk3e` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FKfyryxclt2okb0bxjfhct0pv5u` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.student_class: ~0 rows (approximately)
DELETE FROM `student_class`;
/*!40000 ALTER TABLE `student_class` DISABLE KEYS */;
INSERT INTO `student_class` (`student_id`, `class_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1);
/*!40000 ALTER TABLE `student_class` ENABLE KEYS */;

-- Dumping structure for table school_management.student_evaluate
DROP TABLE IF EXISTS `student_evaluate`;
CREATE TABLE IF NOT EXISTS `student_evaluate` (
  `id` int NOT NULL,
  `academic_ability` int DEFAULT NULL,
  `conduct` int DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `evaluate` varchar(255) DEFAULT NULL,
  `semester` int DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `created_by` int DEFAULT NULL,
  `updated_by` int DEFAULT NULL,
  `student_id` int NOT NULL AUTO_INCREMENT,
  `class_id` int NOT NULL,
  PRIMARY KEY (`student_id`,`class_id`),
  KEY `FK7uyfqp7ix83nb096c55oqnr3y` (`created_by`),
  KEY `FK7r0pnaycxkc9v6hsjsblq9v51` (`updated_by`),
  KEY `FK4quyfchyfgpv037oy2seq38ku` (`class_id`),
  CONSTRAINT `FK4quyfchyfgpv037oy2seq38ku` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FK73jn5ijl2plb5g4qegcuk5889` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FK7r0pnaycxkc9v6hsjsblq9v51` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7uyfqp7ix83nb096c55oqnr3y` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.student_evaluate: ~0 rows (approximately)
DELETE FROM `student_evaluate`;
/*!40000 ALTER TABLE `student_evaluate` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_evaluate` ENABLE KEYS */;

-- Dumping structure for table school_management.subjects
DROP TABLE IF EXISTS `subjects`;
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.subjects: ~7 rows (approximately)
DELETE FROM `subjects`;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` (`id`, `subject_name`) VALUES
	(1, 'Math'),
	(2, 'Physics'),
	(3, 'Chemistry'),
	(4, 'Biology'),
	(5, 'Geography'),
	(6, 'Literature'),
	(7, 'History');
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;

-- Dumping structure for table school_management.teacher_subjects
DROP TABLE IF EXISTS `teacher_subjects`;
CREATE TABLE IF NOT EXISTS `teacher_subjects` (
  `user_id` int NOT NULL,
  `subject_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`subject_id`),
  KEY `FKdweqkwxroox2u7pbmksehx04i` (`subject_id`),
  CONSTRAINT `FKdweqkwxroox2u7pbmksehx04i` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FKockx0rkpkmbpvxh9ftrmgrx4q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.teacher_subjects: ~11 rows (approximately)
DELETE FROM `teacher_subjects`;
/*!40000 ALTER TABLE `teacher_subjects` DISABLE KEYS */;
INSERT INTO `teacher_subjects` (`user_id`, `subject_id`) VALUES
	(20, 1),
	(27, 1),
	(28, 1),
	(20, 2),
	(27, 2),
	(28, 2),
	(20, 3),
	(27, 4),
	(27, 5),
	(28, 5),
	(28, 6),
	(27, 7),
	(28, 7);
/*!40000 ALTER TABLE `teacher_subjects` ENABLE KEYS */;

-- Dumping structure for table school_management.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `created_date` datetime NOT NULL,
  `deleted` bit(1) NOT NULL,
  `dob` datetime NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `end_date` datetime NOT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_date` datetime NOT NULL,
  `updated_date` datetime NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.user: ~13 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `address`, `created_date`, `deleted`, `dob`, `email`, `end_date`, `full_name`, `image`, `password`, `phone`, `start_date`, `updated_date`, `username`) VALUES
	(20, 'HN-VN', '2022-02-26 14:39:31', b'0', '2022-02-02 00:00:00', 'admin@gmail.com', '2022-02-17 00:00:00', 'Admin', 'kJFbCY8GuWzRJ6BQ3kva.jpg', '$2a$10$F7dP80A4BkyWIRd.FF.J1OEtbH8S7I5.eZTNpJ/aG3O6W8xykk8Za', '2316544', '2022-02-15 00:00:00', '2022-02-26 14:39:31', 'admin'),
	(27, 'HN-VN', '2022-03-02 17:12:47', b'0', '2022-03-10 00:00:00', 'teacher1@gmail.com', '2022-02-14 00:00:00', 'Teacher 1', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$.0DOkskqnau9/amUtKZ8c.WEUW8ntmpwEjzjbr8R8Wm.zeM4RzhtC', '0123456789', '2022-02-08 00:00:00', '2022-03-02 17:12:47', 'teacher1'),
	(28, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 2', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher2'),
	(29, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 3', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher3'),
	(30, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 4', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher4'),
	(31, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 5', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher5'),
	(32, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 6', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher6'),
	(33, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 7', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher7'),
	(34, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 8', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher8'),
	(35, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 9', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher9'),
	(36, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 10', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher10'),
	(37, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 11', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher11'),
	(38, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02 00:00:00', 'teacher2@gmail.com', '2022-03-23 00:00:00', 'Teacher 12', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15 00:00:00', '2022-03-02 17:24:14', 'teacher12');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table school_management.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.user_role: ~13 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(20, 1),
	(27, 2),
	(28, 2),
	(29, 2),
	(30, 2),
	(31, 2),
	(32, 2),
	(33, 2),
	(34, 2),
	(35, 2),
	(36, 2),
	(37, 2),
	(38, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
