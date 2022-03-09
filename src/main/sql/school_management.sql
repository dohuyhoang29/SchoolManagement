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
	
CREATE TABLE class_teacher_subject(
	id INT AUTO_INCREMENT PRIMARY KEY,
	user_id INT ,
	subject_id INT ,
	class_id INT ,
	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (subject_id) REFERENCES subjects(id),
	FOREIGN KEY (class_id) REFERENCES class(id)
);

SELECT * FROM class_teacher_subject s WHERE s.class_id =1 AND s.subject_id = 5

insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Ceciley Camis', 'ccamis0', 'Hu0tQY94ZMl', '03/23/2021', '7329 Weeping Birch Center', 1, 'Aluminum', 1999, 1978, '07/03/2021', '07/04/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Trefor Gorce', 'tgorce1', 'vlQP1gCr', '07/31/2021', '29 Sugar Junction', 1, 'Glass', 2012, 2007, '07/11/2021', '08/21/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Alisa Rangell', 'arangell2', 'ExFcXs3mBtm', '04/22/2021', '3 Bluestem Crossing', 0, 'Plexiglass', 2010, 2004, '11/21/2021', '07/05/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Davide Checketts', 'dchecketts3', '64To7e', '04/21/2021', '0 Banding Parkway', 0, 'Plexiglass', 2005, 1997, '03/31/2021', '02/09/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Val Corker', 'vcorker4', 'U43obf17EU', '11/24/2021', '1 Myrtle Plaza', 0, 'Glass', 2006, 2006, '03/18/2021', '12/11/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Ranna Thynn', 'rthynn5', 'ME4MfOdVC', '09/27/2021', '19 East Trail', 1, 'Steel', 1969, 1993, '03/28/2021', '04/17/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Ronna Sandland', 'rsandland6', 'sLbQtZwik3O', '12/23/2021', '51447 Lillian Court', 1, 'Granite', 1993, 1999, '09/03/2021', '07/26/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Cale Bayle', 'cbayle7', 'TXdV03Fu', '10/17/2021', '080 Miller Place', 0, 'Glass', 1987, 1997, '11/11/2021', '09/09/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Adina Somerset', 'asomerset8', 'RYMBGKLEm', '06/10/2021', '6 Porter Center', 1, 'Plastic', 1994, 2003, '11/05/2021', '01/07/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Matilda Faers', 'mfaers9', 'KKCaIuqFMb', '11/15/2021', '5371 Hermina Trail', 0, 'Vinyl', 2004, 1992, '03/15/2021', '01/23/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Dulcine Bowhay', 'dbowhaya', 'JjXJlAmwW', '01/23/2022', '02835 Judy Court', 1, 'Glass', 2006, 2010, '11/15/2021', '09/27/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Bamby Bucknell', 'bbucknellb', 'x4mlHUuaQq7', '07/05/2021', '13 Northwestern Terrace', 0, 'Vinyl', 1998, 2007, '10/19/2021', '01/28/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Kally Ruddlesden', 'kruddlesdenc', 'Njh21wj3', '07/07/2021', '8815 Sachtjen Avenue', 1, 'Brass', 2001, 2000, '01/20/2022', '09/18/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Lexi Martignoni', 'lmartignonid', 'oy8vBpcTQ2', '12/29/2021', '1 Reinke Court', 1, 'Glass', 1994, 2012, '02/14/2022', '04/25/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Lockwood Crookshanks', 'lcrookshankse', 'DfGeAI2u', '11/06/2021', '23 Harbort Street', 1, 'Granite', 2011, 1995, '12/31/2021', '04/23/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Meagan Gummoe', 'mgummoef', 'caNZiqcSzZd', '08/21/2021', '0 Utah Avenue', 0, 'Rubber', 2006, 1994, '06/22/2021', '03/04/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Tobiah Kisby', 'tkisbyg', 'x18sQXeH', '11/03/2021', '62261 Springs Park', 0, 'Wood', 1995, 2004, '03/24/2021', '09/11/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Trish Legg', 'tleggh', 'YXhHIz', '12/07/2021', '03 Judy Park', 1, 'Aluminum', 1984, 2001, '10/29/2021', '08/09/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Odo Halt', 'ohalti', '3OsoHSLSt', '09/11/2021', '80 Homewood Drive', 1, 'Steel', 2001, 1991, '04/29/2021', '03/25/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Elihu Schulke', 'eschulkej', '5gFhw1', '01/10/2022', '18 Thompson Road', 1, 'Plastic', 2005, 2007, '07/18/2021', '02/12/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Brenna Northridge', 'bnorthridgek', 'GV0oXzdW4Lr', '04/05/2021', '96358 Sutherland Circle', 1, 'Rubber', 1992, 2001, '06/29/2021', '04/01/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Clarke Code', 'ccodel', 'JSvK7O3', '10/25/2021', '310 Beilfuss Lane', 0, 'Rubber', 1995, 2005, '05/18/2021', '09/23/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Daniella Climar', 'dclimarm', 'REyTjQzjqa5', '03/02/2022', '15529 Reinke Hill', 1, 'Plastic', 1996, 2001, '10/13/2021', '06/28/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Karleen Foot', 'kfootn', '5Htilaa1zECw', '05/28/2021', '06 Hintze Lane', 1, 'Vinyl', 2008, 2000, '03/06/2022', '02/23/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Tremayne Slarke', 'tslarkeo', 'uSavwrc', '06/13/2021', '4 Di Loreto Place', 1, 'Vinyl', 2001, 2004, '08/02/2021', '09/15/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Malvina Bubeer', 'mbubeerp', 'kL7bxIxE', '09/03/2021', '90 Bluejay Avenue', 0, 'Vinyl', 2000, 2009, '02/08/2022', '01/19/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Erena Churchyard', 'echurchyardq', 'mVB0Ad', '04/01/2021', '953 Arizona Terrace', 1, 'Plexiglass', 2000, 2000, '06/11/2021', '10/31/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Nickola Gumary', 'ngumaryr', '6RSSkinD3Q9z', '09/14/2021', '80 Londonderry Alley', 1, 'Plastic', 1987, 2000, '01/18/2022', '03/26/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Tyson Ganley', 'tganleys', 'NXfBz2W2HCGd', '07/31/2021', '29 Menomonie Court', 0, 'Plastic', 2006, 2005, '02/03/2022', '01/16/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Adriano McGinney', 'amcginneyt', 'gKWSE4xwLu', '06/12/2021', '83 Luster Road', 1, 'Aluminum', 1992, 1995, '07/31/2021', '03/26/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Aimil Martill', 'amartillu', 'UiP16VPPiTc', '07/11/2021', '40668 Sommers Lane', 1, 'Stone', 2007, 1994, '04/01/2021', '07/17/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Dennie Eassom', 'deassomv', 'U4CU0JfBhKC1', '05/06/2021', '58 Golf View Street', 0, 'Granite', 2010, 1998, '05/20/2021', '03/25/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Sergio Maryet', 'smaryetw', 'ykcr5Dc', '08/09/2021', '0 Lakeland Place', 0, 'Stone', 2009, 2004, '11/04/2021', '02/25/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Lucilia Gregoli', 'lgregolix', '8iuDxJoxGLX', '03/01/2022', '0927 Clarendon Center', 1, 'Stone', 2006, 2007, '04/07/2021', '01/02/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Tiebout Wolfindale', 'twolfindaley', 'o51i9OJIHWmj', '10/22/2021', '39 Basil Pass', 1, 'Glass', 2002, 2011, '05/14/2021', '04/16/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Elfrieda Ferrie', 'eferriez', 'xILiKiXR', '07/24/2021', '65458 Corry Plaza', 1, 'Steel', 2002, 1998, '11/07/2021', '02/16/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Hubey Mizen', 'hmizen10', '7G8bOsDek', '07/06/2021', '161 La Follette Drive', 1, 'Stone', 2004, 1986, '12/19/2021', '01/29/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Rube Vittel', 'rvittel11', 'K5XALAy0xo', '10/05/2021', '57 Comanche Parkway', 0, 'Vinyl', 2002, 2006, '01/24/2022', '07/13/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Hilarius Prop', 'hprop12', 'Uvq3i1', '09/27/2021', '964 Westerfield Pass', 0, 'Wood', 2012, 1992, '10/12/2021', '03/19/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Glynda Swaysland', 'gswaysland13', 'Bvw2tvp91w4', '08/06/2021', '06 Bartelt Way', 0, 'Steel', 2011, 1994, '02/03/2022', '07/03/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Dudley Domingues', 'ddomingues14', 'KcWPIR5cDwA', '08/24/2021', '45280 Golf View Hill', 1, 'Stone', 1996, 1987, '12/17/2021', '07/03/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Tally Vautier', 'tvautier15', 's3Vi2G9j82c', '10/27/2021', '07390 Arapahoe Court', 0, 'Wood', 2007, 1985, '03/26/2021', '01/20/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Vail Grestie', 'vgrestie16', 'plXao1', '02/11/2022', '777 Park Meadow Parkway', 1, 'Plastic', 1986, 1994, '02/14/2022', '06/18/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Abel Harradine', 'aharradine17', 'OkNGF5LwfLdN', '04/03/2021', '14612 Killdeer Trail', 1, 'Wood', 1992, 1997, '02/18/2022', '05/27/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Valli Moncreiffe', 'vmoncreiffe18', '49Z0gZ', '05/04/2021', '1908 Melrose Parkway', 1, 'Glass', 2010, 2006, '12/25/2021', '10/12/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Jobye Grimestone', 'jgrimestone19', 'd0asTT', '06/27/2021', '97583 Buhler Drive', 0, 'Steel', 1998, 2006, '03/19/2021', '06/06/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Amabelle Riccetti', 'ariccetti1a', 'VKh6BcXNP', '12/23/2021', '60981 Fairview Trail', 0, 'Brass', 2011, 2007, '10/21/2021', '04/29/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Lacee Lortz', 'llortz1b', 'pMoO5sWDZj2p', '10/28/2021', '7 Corry Parkway', 0, 'Brass', 1999, 1994, '05/01/2021', '05/06/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Yovonnda Beebis', 'ybeebis1c', 'VOll96AFOA', '11/09/2021', '34296 Esch Road', 1, 'Glass', 2005, 1997, '01/12/2022', '05/29/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Moises Ames', 'mames1d', 'SuUtz1Sa', '03/05/2022', '531 Sheridan Drive', 0, 'Plexiglass', 1999, 1992, '04/11/2021', '02/11/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Madelin Weepers', 'mweepers1e', 'TOWQoK2', '12/05/2021', '3104 Little Fleur Road', 1, 'Plexiglass', 1992, 1993, '08/13/2021', '10/22/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Berny Pllu', 'bpllu1f', 'Y0yxVLIsH', '05/19/2021', '0684 Dexter Trail', 1, 'Steel', 2011, 2007, '12/19/2021', '11/11/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Suzanne Cobby', 'scobby1g', 'ZUHXrToXQ', '03/08/2021', '5101 Heath Avenue', 1, 'Rubber', 2000, 1995, '06/18/2021', '09/18/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Adore O''Hoolahan', 'aohoolahan1h', 'LKjudKkF6iNv', '03/02/2022', '0053 Declaration Drive', 0, 'Granite', 1996, 2006, '11/18/2021', '05/21/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Wyatt Berthelet', 'wberthelet1i', 'wnQiyn4AS33', '02/08/2022', '6 Marcy Circle', 0, 'Plastic', 1997, 2004, '10/28/2021', '03/14/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Gnni Murty', 'gmurty1j', 't91EqXdAZTVb', '01/29/2022', '2 Petterle Drive', 1, 'Plexiglass', 1996, 2005, '07/05/2021', '11/14/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Carolann Renon', 'crenon1k', 'zH4Yfv', '01/17/2022', '7873 Vidon Center', 1, 'Stone', 2009, 1995, '03/20/2021', '04/27/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Sabrina Hurran', 'shurran1l', 'ibfHe31d', '05/10/2021', '8519 Troy Plaza', 0, 'Stone', 1996, 1995, '06/05/2021', '08/25/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Faun McClay', 'fmcclay1m', 'C6meUKh', '08/31/2021', '156 Claremont Drive', 1, 'Aluminum', 2002, 2011, '11/08/2021', '03/04/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Shelba Humphery', 'shumphery1n', 'JcVLFeetL', '05/29/2021', '4589 Merrick Center', 1, 'Stone', 2005, 2004, '06/22/2021', '04/03/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Nicolea Winterbotham', 'nwinterbotham1o', 'gwFxbmvKHHEQ', '03/14/2021', '79 Linden Avenue', 0, 'Wood', 2007, 1997, '01/08/2022', '02/06/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Bethanne Reedshaw', 'breedshaw1p', 'WgsxSO', '07/03/2021', '640 Lien Place', 0, 'Steel', 1996, 2007, '11/09/2021', '07/23/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Vinson Knappett', 'vknappett1q', 'AR6T3f6UIqhZ', '02/12/2022', '09945 Eagle Crest Circle', 0, 'Steel', 2012, 1995, '06/19/2021', '08/15/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Reinhard McDermid', 'rmcdermid1r', 'BbZvtyzML9x', '07/09/2021', '82740 Village Green Drive', 0, 'Brass', 1998, 2011, '05/06/2021', '04/07/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Rufus Conaghan', 'rconaghan1s', 'bbQspWdls', '10/14/2021', '66 Buhler Street', 0, 'Vinyl', 1999, 1992, '03/27/2021', '08/25/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Quincy Leavold', 'qleavold1t', 'yvnHxk', '01/10/2022', '1134 Waywood Center', 1, 'Glass', 1999, 2009, '07/10/2021', '10/07/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Krystyna Tourville', 'ktourville1u', 'P1ygYI2M', '11/20/2021', '0034 Logan Alley', 1, 'Wood', 2009, 2007, '09/21/2021', '12/31/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Shannan Abilowitz', 'sabilowitz1v', 'ysvtG5JqH', '08/26/2021', '842 Toban Junction', 0, 'Vinyl', 1998, 2007, '03/04/2022', '05/14/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Shaylah Branscombe', 'sbranscombe1w', 'lVO5eL', '07/30/2021', '88 Linden Pass', 1, 'Stone', 1999, 1992, '09/21/2021', '03/28/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Reeba Rappport', 'rrappport1x', 'X1yZmsGaZ', '02/20/2022', '3344 Becker Circle', 0, 'Aluminum', 1993, 2009, '05/25/2021', '09/04/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Cordelia McGrail', 'cmcgrail1y', '4j6zPri', '06/20/2021', '38 Loeprich Park', 1, 'Plexiglass', 2011, 2004, '02/16/2022', '06/03/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Cecile Adamski', 'cadamski1z', 'GOFUShJc3', '06/29/2021', '3 Barby Road', 0, 'Plastic', 1993, 1996, '02/27/2022', '06/15/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Janie Shury', 'jshury20', 'FTCQ7VQr', '01/24/2022', '279 Fulton Circle', 0, 'Plexiglass', 1997, 2010, '04/18/2021', '10/21/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Fanchette Haggish', 'fhaggish21', 'Wj1vqzeki5', '04/19/2021', '533 Monument Road', 0, 'Wood', 1993, 2001, '07/20/2021', '05/09/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Kahaleel Branch', 'kbranch22', 'E8XOYBD', '08/25/2021', '01 Anhalt Street', 0, 'Wood', 2011, 1991, '07/16/2021', '03/06/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Katherine Eddy', 'keddy23', 'UAin7g4U', '11/30/2021', '38142 Rockefeller Terrace', 1, 'Steel', 1976, 2011, '09/14/2021', '12/28/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Geordie MacAlpine', 'gmacalpine24', 'tbx7cgFr', '04/20/2021', '6214 Eagle Crest Street', 0, 'Glass', 2007, 1999, '01/13/2022', '06/05/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Peadar Mathys', 'pmathys25', 'Sq7vQLdDYU3', '07/01/2021', '3165 Badeau Circle', 1, 'Rubber', 1992, 2011, '01/17/2022', '12/20/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Tisha Medd', 'tmedd26', 'H6TFIVbw', '01/29/2022', '59 Eggendart Avenue', 1, 'Glass', 2001, 1997, '12/06/2021', '03/24/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Tabbitha Borge', 'tborge27', 'BDnwXihH', '09/06/2021', '41034 Hintze Street', 1, 'Aluminum', 1980, 2001, '06/23/2021', '01/23/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Chaunce Gianelli', 'cgianelli28', 'knnjhPKS', '10/10/2021', '122 Mayer Parkway', 0, 'Vinyl', 1992, 1997, '05/06/2021', '05/09/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Rhett McCrainor', 'rmccrainor29', 'gn3w8S7GQT', '06/12/2021', '5 Mockingbird Alley', 1, 'Steel', 2001, 1970, '04/27/2021', '04/08/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Meier Saleway', 'msaleway2a', 'DPrvtaEX', '02/24/2022', '01006 Merrick Terrace', 1, 'Rubber', 1999, 1995, '03/17/2021', '03/27/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Yurik Winslade', 'ywinslade2b', 'fBJeNa1', '08/20/2021', '67 Schmedeman Court', 0, 'Wood', 1995, 2006, '09/22/2021', '01/12/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Suzanna McDermott', 'smcdermott2c', 'uK7ByuQIbXyw', '02/02/2022', '67413 High Crossing Street', 1, 'Stone', 2006, 2004, '02/06/2022', '12/29/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Deborah Towson', 'dtowson2d', 'UayaKybIwxG', '07/13/2021', '3209 Morrow Terrace', 0, 'Wood', 2007, 2008, '09/23/2021', '06/14/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Terrance Garretson', 'tgarretson2e', 'NC2GfwXh', '12/31/2021', '72 Scott Way', 0, 'Brass', 2007, 2009, '06/17/2021', '08/15/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Turner Titterington', 'ttitterington2f', 'r2iopNW62', '01/18/2022', '64 Johnson Trail', 1, 'Rubber', 2012, 2006, '09/04/2021', '10/05/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Liva Clinton', 'lclinton2g', 'z3OxmGPMm', '11/03/2021', '733 Oak Valley Trail', 1, 'Rubber', 1996, 1996, '06/23/2021', '07/28/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Nonah Escale', 'nescale2h', 'SECXZMO5HqpN', '01/31/2022', '1 Roth Parkway', 1, 'Glass', 2008, 2004, '09/18/2021', '06/21/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Cirillo Merrick', 'cmerrick2i', '5rWK777f', '03/25/2021', '6 Shasta Trail', 1, 'Granite', 2006, 1997, '06/28/2021', '09/21/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Harris Pimm', 'hpimm2j', 'BQck8IrqMJJ', '07/16/2021', '4 Lake View Terrace', 0, 'Plastic', 2011, 1993, '08/02/2021', '04/09/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Georgetta Leaburn', 'gleaburn2k', 'FbsX5McZT', '12/11/2021', '34439 Shopko Parkway', 0, 'Granite', 2006, 1986, '06/22/2021', '01/31/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Town Trahair', 'ttrahair2l', '2REvDK', '05/26/2021', '6699 Lunder Point', 1, 'Plexiglass', 1999, 2004, '07/30/2021', '05/20/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Arthur Digman', 'adigman2m', 'QVbsAxF', '04/11/2021', '34 Village Crossing', 0, 'Wood', 2007, 1998, '09/03/2021', '09/27/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Zahara Harbard', 'zharbard2n', 'cqm7ZtoLA', '07/19/2021', '62314 Warrior Court', 0, 'Glass', 1993, 2004, '01/10/2022', '04/01/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Krystle Shead', 'kshead2o', '0RJjdPqkkngW', '07/16/2021', '6 Moland Street', 1, 'Aluminum', 2012, 2001, '08/02/2021', '10/16/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Myrle Eayres', 'meayres2p', 'U8phWMEH7', '05/10/2021', '4 Toban Street', 0, 'Wood', 2001, 1996, '03/13/2021', '06/29/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Maighdiln Darwood', 'mdarwood2q', 'SMhpQye', '09/13/2021', '42 Laurel Plaza', 1, 'Glass', 2011, 2006, '06/18/2021', '12/27/2021');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Raffaello Routley', 'rroutley2r', 'FCXPlYG', '03/22/2021', '1044 Schurz Circle', 1, 'Steel', 2003, 1984, '06/20/2021', '01/14/2022');
insert into student (full_name, username, password, dob, address, status, image, admission_year, graduate_year, created_date, updated_date) values ('Terra Humpatch', 'thumpatch0', 'aPZZSuaT', '2021-06-03 21:33:50', '59123 Ruskin Park', 0, 'Plexiglass', 2010, 1997, '01/24/2022', '05/26/2021');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

insert into student_class (class_id, student_id) values (2, 6);
insert into student_class (class_id, student_id) values (2, 7);
insert into student_class (class_id, student_id) values (2, 8);
insert into student_class (class_id, student_id) values (2, 9);
insert into student_class (class_id, student_id) values (2, 10);
insert into student_class (class_id, student_id) values (2, 11);
insert into student_class (class_id, student_id) values (2, 12);
insert into student_class (class_id, student_id) values (2, 13);
insert into student_class (class_id, student_id) values (2, 14);
insert into student_class (class_id, student_id) values (2, 15);
insert into student_class (class_id, student_id) values (2, 16);
insert into student_class (class_id, student_id) values (2, 17);
insert into student_class (class_id, student_id) values (2, 18);
insert into student_class (class_id, student_id) values (2, 19);
insert into student_class (class_id, student_id) values (2, 20);
insert into student_class (class_id, student_id) values (2, 21);
insert into student_class (class_id, student_id) values (2, 22);
insert into student_class (class_id, student_id) values (2, 23);
insert into student_class (class_id, student_id) values (2, 24);
insert into student_class (class_id, student_id) values (2, 25);
insert into student_class (class_id, student_id) values (2, 26);
insert into student_class (class_id, student_id) values (2, 27);
insert into student_class (class_id, student_id) values (2, 28);
insert into student_class (class_id, student_id) values (2, 29);
insert into student_class (class_id, student_id) values (3, 30);
insert into student_class (class_id, student_id) values (3, 31);
insert into student_class (class_id, student_id) values (3, 32);
insert into student_class (class_id, student_id) values (3, 33);
insert into student_class (class_id, student_id) values (3, 34);
insert into student_class (class_id, student_id) values (3, 35);
insert into student_class (class_id, student_id) values (3, 36);
insert into student_class (class_id, student_id) values (3, 37);
insert into student_class (class_id, student_id) values (3, 38);
insert into student_class (class_id, student_id) values (3, 39);
insert into student_class (class_id, student_id) values (4, 40);
insert into student_class (class_id, student_id) values (4, 41);
insert into student_class (class_id, student_id) values (4, 42);
insert into student_class (class_id, student_id) values (4, 43);
insert into student_class (class_id, student_id) values (4, 44);
insert into student_class (class_id, student_id) values (4, 45);
insert into student_class (class_id, student_id) values (4, 46);
insert into student_class (class_id, student_id) values (4, 47);
insert into student_class (class_id, student_id) values (4, 48);
insert into student_class (class_id, student_id) values (4, 49);
insert into student_class (class_id, student_id) values (5, 50);
insert into student_class (class_id, student_id) values (5, 51);
insert into student_class (class_id, student_id) values (5, 52);
insert into student_class (class_id, student_id) values (5, 53);
insert into student_class (class_id, student_id) values (5, 54);
insert into student_class (class_id, student_id) values (5, 55);
insert into student_class (class_id, student_id) values (5, 56);
insert into student_class (class_id, student_id) values (5, 57);
insert into student_class (class_id, student_id) values (5, 58);
insert into student_class (class_id, student_id) values (5, 59);
insert into student_class (class_id, student_id) values (6, 60);
insert into student_class (class_id, student_id) values (6, 61);
insert into student_class (class_id, student_id) values (6, 62);
insert into student_class (class_id, student_id) values (6, 63);
insert into student_class (class_id, student_id) values (6, 64);
insert into student_class (class_id, student_id) values (6, 65);
insert into student_class (class_id, student_id) values (6, 66);
insert into student_class (class_id, student_id) values (6, 67);
insert into student_class (class_id, student_id) values (6, 68);
insert into student_class (class_id, student_id) values (6, 69);
insert into student_class (class_id, student_id) values (7, 70);
insert into student_class (class_id, student_id) values (7, 71);
insert into student_class (class_id, student_id) values (7, 72);
insert into student_class (class_id, student_id) values (7, 73);
insert into student_class (class_id, student_id) values (7, 74);
insert into student_class (class_id, student_id) values (7, 75);
insert into student_class (class_id, student_id) values (7, 76);
insert into student_class (class_id, student_id) values (7, 77);
insert into student_class (class_id, student_id) values (7, 78);
insert into student_class (class_id, student_id) values (7, 79);
insert into student_class (class_id, student_id) values (8, 80);
insert into student_class (class_id, student_id) values (8, 81);
insert into student_class (class_id, student_id) values (8, 82);
insert into student_class (class_id, student_id) values (8, 83);
insert into student_class (class_id, student_id) values (8, 84);
insert into student_class (class_id, student_id) values (8, 85);
insert into student_class (class_id, student_id) values (8, 86);
insert into student_class (class_id, student_id) values (8, 87);
insert into student_class (class_id, student_id) values (8, 88);
insert into student_class (class_id, student_id) values (8, 89);
insert into student_class (class_id, student_id) values (9, 90);
insert into student_class (class_id, student_id) values (9, 91);
insert into student_class (class_id, student_id) values (9, 92);
insert into student_class (class_id, student_id) values (9, 93);
insert into student_class (class_id, student_id) values (9, 94);
insert into student_class (class_id, student_id) values (9, 95);
insert into student_class (class_id, student_id) values (9, 96);
insert into student_class (class_id, student_id) values (9, 97);
insert into student_class (class_id, student_id) values (9, 98);
insert into student_class (class_id, student_id) values (9, 99);
insert into student_class (class_id, student_id) values (10, 100);
 


/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
