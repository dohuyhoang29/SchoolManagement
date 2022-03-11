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
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

insert into student_class (class_id, student_id) values (2, 6);
insert into student_class (class_id, student_id) values (2, 7);
insert into student_class (class_id, student_id) values (2, 8);
insert into student_class (class_id, student_id) values (2, 9);
insert into student_class (class_id, student_id) values (2, 10);
insert into student_class (class_id, student_id) values (2, 11);school_management
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
 
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('81 Fremont Park', '2020-05-31 21:56:43', 0, '2021-12-05', 'smaruszewski0@icio.us', '2021-12-06 18:34:37', 'Shirley Maruszewski', 'Bertrando', 'Shirley', '9411201777', '12/22/2021', '10/10/2021', 'smaruszewski0');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('35 4th Trail', '2020-05-20 07:07:14', 0, '2021-11-28', 'ncraighill1@amazon.de', '2021-06-21 09:19:11', 'Nevil Craighill', 'Starla', 'Nevil', '4048376405', '2/16/2022', '8/31/2021', 'ncraighill1');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('6 Redwing Park', '2022-02-22 13:00:50', 1, '2021-07-20', 'amorrice2@nationalgeographic.com', '2022-01-28 19:44:43', 'Aile Morrice', 'Tim', 'Aile', '8202241108', '11/4/2021', '4/18/2021', 'amorrice2');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('687 Pierstorff Alley', '2021-08-12 15:52:19', 1, '2021-04-07', 'ojacob3@indiatimes.com', '2022-02-19 00:37:32', 'Oran Jacob', 'Marena', 'Oran', '9253667022', '4/2/2021', '4/9/2021', 'ojacob3');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('06074 Johnson Parkway', '2021-07-15 18:41:39', 1, '2021-10-19', 'pcarbery4@cnn.com', '2022-02-27 04:28:16', 'Philippine Carbery', 'Adler', 'Philippine', '2292731620', '7/17/2021', '9/24/2021', 'pcarbery4');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('698 Mitchell Way', '2021-01-28 11:33:38', 1, '2021-10-19', 'ogatling5@merriam-webster.com', '2021-04-20 22:47:40', 'Odey Gatling', 'Bathsheba', 'Odey', '6817191429', '5/6/2021', '8/23/2021', 'ogatling5');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('4594 Springview Plaza', '2019-08-14 10:14:51', 0, '2021-06-06', 'eklausewitz6@histats.com', '2022-03-06 05:54:56', 'Ellene Klausewitz', 'Dido', 'Ellene', '8198905622', '5/25/2021', '9/17/2021', 'eklausewitz6');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('0914 Lerdahl Alley', '2020-07-13 06:45:07', 0, '2021-09-28', 'sroomes7@webmd.com', '2021-09-06 19:53:55', 'Susan Roomes', 'Cherri', 'Susan', '2712693072', '10/19/2021', '8/21/2021', 'sroomes7');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('6 Scofield Plaza', '2021-11-24 16:06:09', 1, '2021-06-24', 'cpostle8@woothemes.com', '2021-09-08 23:13:39', 'Crista Postle', 'Mersey', 'Crista', '9967354656', '2/18/2022', '9/26/2021', 'cpostle8');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('43 David Street', '2019-12-08 11:19:41', 0, '2021-03-28', 'bdugget9@bigcartel.com', '2021-04-30 15:03:17', 'Billy Dugget', 'Patty', 'Billy', '8004959084', '12/31/2021', '4/22/2021', 'bdugget9');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('4 Barnett Circle', '2019-09-09 15:29:15', 1, '2021-11-03', 'ysunnera@miitbeian.gov.cn', '2022-01-19 17:24:09', 'Yardley Sunner', 'Biddie', 'Yardley', '3158792175', '5/21/2021', '7/7/2021', 'ysunnera');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('70 Eggendart Trail', '2022-01-02 04:23:15', 1, '2021-03-10', 'ogargettb@geocities.com', '2021-07-26 20:38:15', 'Orelie Gargett', 'Roxanna', 'Orelie', '7026540843', '10/27/2021', '12/26/2021', 'ogargettb');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('1 Canary Pass', '2020-04-13 20:14:32', 0, '2021-03-21', 'jaddisc@free.fr', '2022-02-12 11:57:19', 'Julienne Addis', 'Bria', 'Julienne', '6224488091', '7/25/2021', '3/25/2021', 'jaddisc');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('21 Bluestem Drive', '2021-04-28 04:19:41', 1, '2021-03-29', 'ggallehawkd@about.me', '2021-08-27 08:39:41', 'Goddard Gallehawk', 'Shaylynn', 'Goddard', '8698727896', '11/6/2021', '1/18/2022', 'ggallehawkd');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('90078 Darwin Junction', '2020-05-25 01:11:43', 0, '2021-10-20', 'acallwaye@ucla.edu', '2021-09-25 00:35:42', 'Aindrea Callway', 'Ham', 'Aindrea', '2067343392', '7/2/2021', '9/10/2021', 'acallwaye');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('447 Brown Crossing', '2021-08-29 13:54:32', 1, '2021-06-02', 'kkieraf@archive.org', '2021-08-19 00:55:35', 'Kip Kiera', 'Alfreda', 'Kip', '8233106172', '7/17/2021', '8/14/2021', 'kkieraf');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('8 Dorton Drive', '2020-03-26 02:23:48', 1, '2021-04-26', 'jcoolbearg@opensource.org', '2022-01-14 01:28:05', 'Joachim Coolbear', 'Karissa', 'Joachim', '9284267572', '9/30/2021', '9/15/2021', 'jcoolbearg');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('86346 International Trail', '2021-01-21 21:33:04', 0, '2021-04-22', 'kdewsburyh@intel.com', '2021-12-28 05:23:44', 'Kris Dewsbury', 'Harald', 'Kris', '9459119239', '8/12/2021', '10/31/2021', 'kdewsburyh');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('868 Helena Parkway', '2019-10-22 01:26:46', 0, '2021-05-11', 'nmcbethi@deviantart.com', '2021-10-15 03:27:46', 'Nahum McBeth', 'Harper', 'Nahum', '4392786699', '6/23/2021', '11/29/2021', 'nmcbethi');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('1 Rigney Hill', '2019-07-15 08:48:50', 1, '2021-10-05', 'evisickj@scientificamerican.com', '2021-05-03 15:27:49', 'Elijah Visick', 'Berny', 'Elijah', '6067330461', '9/16/2021', '1/15/2022', 'evisickj');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('293 Morrow Center', '2020-01-26 07:09:33', 0, '2021-06-09', 'ncolterk@hibu.com', '2021-12-09 22:34:52', 'Nicoline Colter', 'Mathilda', 'Nicoline', '7885541267', '12/26/2021', '2/28/2022', 'ncolterk');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('01 Loftsgordon Avenue', '2020-09-18 08:57:50', 1, '2021-03-13', 'flukerl@google.it', '2021-04-30 22:14:12', 'Freemon Luker', 'Rori', 'Freemon', '9918569420', '8/8/2021', '10/3/2021', 'flukerl');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('20048 Fuller Crossing', '2021-11-20 08:14:31', 1, '2022-02-15', 'cpallatinam@goo.ne.jp', '2022-01-09 18:15:25', 'Chilton Pallatina', 'Storm', 'Chilton', '4598006679', '3/10/2021', '10/26/2021', 'cpallatinam');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('57 Summer Ridge Street', '2021-06-23 16:53:12', 1, '2021-05-27', 'kledgewayn@sohu.com', '2021-12-03 15:52:09', 'Kelly Ledgeway', 'Gordon', 'Kelly', '8398126706', '2/21/2022', '1/30/2022', 'kledgewayn');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('16 Arrowood Court', '2021-09-15 04:15:01', 0, '2022-01-17', 'kbrayshayo@eepurl.com', '2022-01-11 19:02:16', 'Kelli Brayshay', 'Farrel', 'Kelli', '4953151468', '12/19/2021', '6/28/2021', 'kbrayshayo');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('5705 Florence Junction', '2021-08-27 16:18:12', 0, '2021-10-16', 'nbarkawayp@dion.ne.jp', '2022-02-15 15:01:14', 'Nettle Barkaway', 'Anatol', 'Nettle', '7571242016', '10/10/2021', '9/29/2021', 'nbarkawayp');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('08915 Mariners Cove Avenue', '2020-03-15 19:09:35', 0, '2021-07-29', 'enelq@1688.com', '2021-11-26 00:56:15', 'Edlin Nel', 'Julia', 'Edlin', '5372142554', '7/30/2021', '2/12/2022', 'enelq');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('94761 Gulseth Park', '2020-10-19 17:49:35', 0, '2021-11-30', 'bbettlesr@tumblr.com', '2021-07-01 04:26:27', 'Brianne Bettles', 'Marnie', 'Brianne', '6175700071', '7/12/2021', '10/11/2021', 'bbettlesr');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('56 Karstens Place', '2021-10-22 03:20:25', 1, '2021-06-12', 'jgriffiths@myspace.com', '2021-07-09 14:17:24', 'Jodie Griffith', 'Craggie', 'Jodie', '5361683788', '12/6/2021', '11/11/2021', 'jgriffiths');
insert into user (address, created_date, deleted, dob, email, end_date, full_name, image, password, phone, start_date, updated_date, username) values ('80 Bashford Street', '2019-07-28 17:53:50', 0, '2021-08-07', 'jfowliet@ask.com', '2021-07-04 00:37:29', 'Jaynell Fowlie', 'Lucais', 'Jaynell', '2934049160', '9/5/2021', '10/7/2021', 'jfowliet');

insert into user_role (user_id, role_id) values (43, 2);
insert into user_role (user_id, role_id) values (44, 2);
insert into user_role (user_id, role_id) values (45, 2);
insert into user_role (user_id, role_id) values (46, 2);
insert into user_role (user_id, role_id) values (47, 2);
insert into user_role (user_id, role_id) values (48, 2);
insert into user_role (user_id, role_id) values (49, 2);
insert into user_role (user_id, role_id) values (50, 2);
insert into user_role (user_id, role_id) values (51, 2);
insert into user_role (user_id, role_id) values (52, 2);
insert into user_role (user_id, role_id) values (53, 2);
insert into user_role (user_id, role_id) values (54, 2);
insert into user_role (user_id, role_id) values (55, 2);
insert into user_role (user_id, role_id) values (56, 2);
insert into user_role (user_id, role_id) values (57, 2);
insert into user_role (user_id, role_id) values (58, 2);
insert into user_role (user_id, role_id) values (59, 2);
insert into user_role (user_id, role_id) values (60, 2);
insert into user_role (user_id, role_id) values (61, 2);
insert into user_role (user_id, role_id) values (62, 2);
insert into user_role (user_id, role_id) values (63, 2);
insert into user_role (user_id, role_id) values (64, 2);
insert into user_role (user_id, role_id) values (65, 2);
insert into user_role (user_id, role_id) values (66, 2);
insert into user_role (user_id, role_id) values (67, 2);
insert into user_role (user_id, role_id) values (68, 2);
insert into user_role (user_id, role_id) values (69, 2);
insert into user_role (user_id, role_id) values (70, 2);
insert into user_role (user_id, role_id) values (71, 2);
insert into user_role (user_id, role_id) values (72, 2);

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
