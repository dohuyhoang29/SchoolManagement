-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.22-MariaDB - mariadb.org binary distribution
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
CREATE DATABASE IF NOT EXISTS `school_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `school_management`;

-- Dumping structure for table school_management.attacment
CREATE TABLE IF NOT EXISTS `attacment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.attacment: ~0 rows (approximately)
DELETE FROM `attacment`;
/*!40000 ALTER TABLE `attacment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attacment` ENABLE KEYS */;

-- Dumping structure for table school_management.blog
CREATE TABLE IF NOT EXISTS `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpxk2jtysqn41oop7lvxcp6dqq` (`user_id`),
  CONSTRAINT `FKpxk2jtysqn41oop7lvxcp6dqq` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.blog: ~1 rows (approximately)
DELETE FROM `blog`;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;
INSERT INTO `blog` (`id`, `content`, `created_date`, `header`, `updated_date`, `user_id`) VALUES
	(1, '<p style="text-align: center; "><img src="/upload/image/blog_image/09ba593d-3d8b-437e-bdaf-0a874d89c79eroad-1072821__340.jpg" style="width: 500px; height: 250px; float: none;"></p><p style="text-align: center; ">Inther end Sumber</p><p style="text-align: center; "><br></p>', '2022-03-07 17:24:12', 'BackDor', '2022-03-07 17:24:12', 20);
/*!40000 ALTER TABLE `blog` ENABLE KEYS */;

-- Dumping structure for table school_management.class
CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `school_year` int(11) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhjtee0w5l26pivuxosvmxv0ff` (`user_id`),
  CONSTRAINT `FKhjtee0w5l26pivuxosvmxv0ff` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.class: ~13 rows (approximately)
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
CREATE TABLE IF NOT EXISTS `class_teacher_subject` (
  `id` int(11) NOT NULL,
  `subject_id` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKg3aq3ycx05p3qhmsg84vomes8` (`subject_id`),
  KEY `FKfmjly5l6h5baj18gppqmti2ud` (`class_id`),
  KEY `FKcyuugr3nif17s8hgifhrom8b7` (`user_id`),
  CONSTRAINT `FKcyuugr3nif17s8hgifhrom8b7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKfmjly5l6h5baj18gppqmti2ud` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FKg3aq3ycx05p3qhmsg84vomes8` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.class_teacher_subject: ~21 rows (approximately)
DELETE FROM `class_teacher_subject`;
/*!40000 ALTER TABLE `class_teacher_subject` DISABLE KEYS */;
INSERT INTO `class_teacher_subject` (`id`, `subject_id`, `class_id`, `user_id`) VALUES
	(1, 1, 1, 28),
	(2, 5, 1, 36),
	(3, 2, 1, 29),
	(4, 6, 1, 37),
	(5, 4, 1, 34),
	(6, 3, 1, 32),
	(7, 7, 1, 41),
	(8, 5, 2, 36),
	(9, 6, 2, 37),
	(10, 2, 2, 29),
	(11, 4, 2, 33),
	(12, 3, 2, 32),
	(13, 1, 2, 28),
	(14, 7, 2, 40),
	(15, 5, 3, 35),
	(16, 3, 3, 31),
	(17, 1, 3, 27),
	(18, 6, 3, 37),
	(19, 4, 3, 34),
	(20, 2, 3, 29),
	(21, 7, 3, 42);
/*!40000 ALTER TABLE `class_teacher_subject` ENABLE KEYS */;

-- Dumping structure for table school_management.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.hibernate_sequence: ~1 rows (approximately)
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(22);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table school_management.mark
CREATE TABLE IF NOT EXISTS `mark` (
  `id` int(11) NOT NULL,
  `coefficient` int(11) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `subject_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.mark: ~0 rows (approximately)
DELETE FROM `mark`;
/*!40000 ALTER TABLE `mark` DISABLE KEYS */;
/*!40000 ALTER TABLE `mark` ENABLE KEYS */;

-- Dumping structure for table school_management.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.role: ~4 rows (approximately)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'TEACHER'),
	(3, 'STUDENT'),
	(4, 'HOMEROOM_TEACHER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Dumping structure for table school_management.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `admission_year` int(11) DEFAULT NULL,
  `created_date` datetime(6) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `graduate_year` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_date` datetime(6) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdwhkib64u47wc4yo4hk0cub90` (`class_id`),
  CONSTRAINT `FKdwhkib64u47wc4yo4hk0cub90` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=137 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.student: ~106 rows (approximately)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `address`, `admission_year`, `created_date`, `dob`, `full_name`, `graduate_year`, `image`, `password`, `status`, `updated_date`, `username`, `class_id`) VALUES
	(1, 'HN-VN', 2021, '2022-03-02 16:32:36.670633', '2022-03-16', 'Student 1', 2023, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '$2a$10$3qyV/7b2OeOAzbcRz93l7.hgwqnh.Em3r8LAt/b3BhpH4.lVMJ45G', 2, '2022-03-02 16:32:36.670633', 'student1', 1),
	(2, 'HN-VN', 2022, '2022-03-02 16:45:04.025696', '2022-03-16', 'Student 2', 2024, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '$2a$10$Lm/fA6KRT5UmbykAoPoyZ.CwvUb/esjqgPSgT3GtXcTN2.CsyNVY.', 1, '2022-03-02 16:45:04.025696', 'student2', 1),
	(3, 'HN-VN', 2022, '2022-03-02 16:48:55.822554', '2022-03-08', 'Student 3', 2024, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '$2a$10$BFE1LX2XauwO6ydcKkaM5OdNZoP5hfMw4nXfVjexCaIo.GFqbwi8q', 2, '2022-03-02 16:48:55.822554', 'student3', 1),
	(4, 'HN-VN', 2022, '2022-03-02 16:50:28.076353', '2022-03-08', 'Student 4', 2023, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '$2a$10$8rosf0aIEQPqZyreyoGIiuizJiOGwcmfJ1eKH1MtrUl1o20KIcfSW', 2, '2022-03-02 16:50:28.076353', 'student4', 1),
	(5, 'HN-VN', 2022, '2022-03-02 17:52:04.949196', '2022-03-12', 'Student 6', 2024, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '$2a$10$Guu9m2Lwsyu3AGtukainFOAs2j9.uDHM7P6e5JI5bYh8zXJeQQny6', 1, '2022-03-02 17:52:04.949196', 'std_2022_4', 1),
	(6, '7329 Weeping Birch Center', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Ceciley Camis', 1978, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'Hu0tQY94ZMl', 1, '2022-03-02 16:32:36.670633', 'ccamis0', 2),
	(7, '29 Sugar Junction', 2012, '2022-03-02 16:32:36.670633', '2002-06-08', 'Trefor Gorce', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'vlQP1gCr', 1, '2022-03-02 16:32:36.670633', 'tgorce1', 1),
	(8, '3 Bluestem Crossing', 2010, '2022-03-02 16:32:36.670633', '2002-06-08', 'Alisa Rangell', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'ExFcXs3mBtm', 1, '2022-03-02 16:32:36.670633', 'arangell2', 1),
	(9, '0 Banding Parkway', 2005, '2022-03-02 16:32:36.670633', '2002-06-08', 'Davide Checketts', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '64To7e', 1, '2022-03-02 16:32:36.670633', 'dchecketts3', 1),
	(10, '1 Myrtle Plaza', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Val Corker', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'U43obf17EU', 1, '2022-03-02 16:32:36.670633', 'vcorker4', 1),
	(11, '19 East Trail', 1969, '2022-03-02 16:32:36.670633', '2002-06-08', 'Ranna Thynn', 1993, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'ME4MfOdVC', 1, '2022-03-02 16:32:36.670633', 'rthynn5', 1),
	(12, '51447 Lillian Court', 1993, '2022-03-02 16:32:36.670633', '2002-06-08', 'Ronna Sandland', 1999, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'sLbQtZwik3O', 1, '2022-03-02 16:32:36.670633', 'rsandland6', 1),
	(13, '080 Miller Place', 1987, '2022-03-02 16:32:36.670633', '2002-06-08', 'Cale Bayle', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'TXdV03Fu', 1, '2022-03-02 16:32:36.670633', 'cbayle7', 1),
	(14, '6 Porter Center', 1994, '2022-03-02 16:32:36.670633', '2002-06-08', 'Adina Somerset', 2003, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'RYMBGKLEm', 1, '2022-03-02 16:32:36.670633', 'asomerset8', 1),
	(15, '5371 Hermina Trail', 2004, '2022-03-02 16:32:36.670633', '2002-06-08', 'Matilda Faers', 1992, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'KKCaIuqFMb', 1, '2022-03-02 16:32:36.670633', 'mfaers9', 1),
	(16, '02835 Judy Court', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Dulcine Bowhay', 2010, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'JjXJlAmwW', 1, '2022-03-02 16:32:36.670633', 'dbowhaya', 1),
	(17, '13 Northwestern Terrace', 1998, '2022-03-02 16:32:36.670633', '2002-06-08', 'Bamby Bucknell', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'x4mlHUuaQq7', 1, '2022-03-02 16:32:36.670633', 'bbucknellb', 1),
	(18, '8815 Sachtjen Avenue', 2001, '2022-03-02 16:32:36.670633', '2002-06-08', 'Kally Ruddlesden', 2000, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'Njh21wj3', 1, '2022-03-02 16:32:36.670633', 'kruddlesdenc', 1),
	(19, '1 Reinke Court', 1994, '2022-03-02 16:32:36.670633', '2002-06-08', 'Lexi Martignoni', 2012, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'oy8vBpcTQ2', 1, '2022-03-02 16:32:36.670633', 'lmartignonid', 1),
	(20, '23 Harbort Street', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Lockwood Crookshanks', 1995, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'DfGeAI2u', 1, '2022-03-02 16:32:36.670633', 'lcrookshankse', 1),
	(21, '0 Utah Avenue', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Meagan Gummoe', 1994, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'caNZiqcSzZd', 1, '2022-03-02 16:32:36.670633', 'mgummoef', 1),
	(22, '62261 Springs Park', 1995, '2022-03-02 16:32:36.670633', '2002-06-08', 'Tobiah Kisby', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'x18sQXeH', 1, '2022-03-02 16:32:36.670633', 'tkisbyg', 1),
	(23, '03 Judy Park', 1984, '2022-03-02 16:32:36.670633', '2002-06-08', 'Trish Legg', 2001, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'YXhHIz', 1, '2022-03-02 16:32:36.670633', 'tleggh', 1),
	(24, '80 Homewood Drive', 2001, '2022-03-02 16:32:36.670633', '2002-06-08', 'Odo Halt', 1991, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '3OsoHSLSt', 1, '2022-03-02 16:32:36.670633', 'ohalti', 1),
	(25, '18 Thompson Road', 2005, '2022-03-02 16:32:36.670633', '2002-06-08', 'Elihu Schulke', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '5gFhw1', 1, '2022-03-02 16:32:36.670633', 'eschulkej', 1),
	(26, '96358 Sutherland Circle', 1992, '2022-03-02 16:32:36.670633', '2002-06-08', 'Brenna Northridge', 2001, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'GV0oXzdW4Lr', 1, '2022-03-02 16:32:36.670633', 'bnorthridgek', 1),
	(27, '310 Beilfuss Lane', 1995, '2022-03-02 16:32:36.670633', '2002-06-08', 'Clarke Code', 2005, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'JSvK7O3', 1, '2022-03-02 16:32:36.670633', 'ccodel', 1),
	(28, '15529 Reinke Hill', 1996, '2022-03-02 16:32:36.670633', '2002-06-08', 'Daniella Climar', 2001, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'REyTjQzjqa5', 1, '2022-03-02 16:32:36.670633', 'dclimarm', 1),
	(29, '06 Hintze Lane', 2008, '2022-03-02 16:32:36.670633', '2002-06-08', 'Karleen Foot', 2000, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '5Htilaa1zECw', 1, '2022-03-02 16:32:36.670633', 'kfootn', 2),
	(30, '4 Di Loreto Place', 2001, '2022-03-02 16:32:36.670633', '2002-06-08', 'Tremayne Slarke', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'uSavwrc', 1, '2022-03-02 16:32:36.670633', 'tslarkeo', 2),
	(31, '90 Bluejay Avenue', 2000, '2022-03-02 16:32:36.670633', '2002-06-08', 'Malvina Bubeer', 2009, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'kL7bxIxE', 1, '2022-03-02 16:32:36.670633', 'mbubeerp', 2),
	(32, '953 Arizona Terrace', 2000, '2022-03-02 16:32:36.670633', '2002-06-08', 'Erena Churchyard', 2000, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'mVB0Ad', 1, '2022-03-02 16:32:36.670633', 'echurchyardq', 2),
	(33, '80 Londonderry Alley', 1987, '2022-03-02 16:32:36.670633', '2002-06-08', 'Nickola Gumary', 2000, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '6RSSkinD3Q9z', 1, '2022-03-02 16:32:36.670633', 'ngumaryr', 2),
	(34, '29 Menomonie Court', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Tyson Ganley', 2005, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'NXfBz2W2HCGd', 1, '2022-03-02 16:32:36.670633', 'tganleys', 2),
	(35, '83 Luster Road', 1992, '2022-03-02 16:32:36.670633', '2002-06-08', 'Adriano McGinney', 1995, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'gKWSE4xwLu', 1, '2022-03-02 16:32:36.670633', 'amcginneyt', 2),
	(36, '40668 Sommers Lane', 2007, '2022-03-02 16:32:36.670633', '2002-06-08', 'Aimil Martill', 1994, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'UiP16VPPiTc', 1, '2022-03-02 16:32:36.670633', 'amartillu', 2),
	(37, '58 Golf View Street', 2010, '2022-03-02 16:32:36.670633', '2002-06-08', 'Dennie Eassom', 1998, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'U4CU0JfBhKC1', 1, '2022-03-02 16:32:36.670633', 'deassomv', 2),
	(38, '0 Lakeland Place', 2009, '2022-03-02 16:32:36.670633', '2002-06-08', 'Sergio Maryet', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'ykcr5Dc', 1, '2022-03-02 16:32:36.670633', 'smaryetw', 2),
	(39, '0927 Clarendon Center', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Lucilia Gregoli', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '8iuDxJoxGLX', 1, '2022-03-02 16:32:36.670633', 'lgregolix', 2),
	(40, '39 Basil Pass', 2002, '2022-03-02 16:32:36.670633', '2002-06-08', 'Tiebout Wolfindale', 2011, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'o51i9OJIHWmj', 1, '2022-03-02 16:32:36.670633', 'twolfindaley', 2),
	(41, '65458 Corry Plaza', 2002, '2022-03-02 16:32:36.670633', '2002-06-08', 'Elfrieda Ferrie', 1998, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'xILiKiXR', 1, '2022-03-02 16:32:36.670633', 'eferriez', 2),
	(42, '161 La Follette Drive', 2004, '2022-03-02 16:32:36.670633', '2002-06-08', 'Hubey Mizen', 1986, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '7G8bOsDek', 1, '2022-03-02 16:32:36.670633', 'hmizen10', 2),
	(43, '57 Comanche Parkway', 2002, '2022-03-02 16:32:36.670633', '2002-06-08', 'Rube Vittel', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'K5XALAy0xo', 1, '2022-03-02 16:32:36.670633', 'rvittel11', 2),
	(44, '964 Westerfield Pass', 2012, '2022-03-02 16:32:36.670633', '2002-06-08', 'Hilarius Prop', 1992, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'Uvq3i1', 1, '2022-03-02 16:32:36.670633', 'hprop12', 2),
	(45, '06 Bartelt Way', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Glynda Swaysland', 1994, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'Bvw2tvp91w4', 1, '2022-03-02 16:32:36.670633', 'gswaysland13', 2),
	(46, '45280 Golf View Hill', 1996, '2022-03-02 16:32:36.670633', '2002-06-08', 'Dudley Domingues', 1987, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'KcWPIR5cDwA', 1, '2022-03-02 16:32:36.670633', 'ddomingues14', 2),
	(47, '07390 Arapahoe Court', 2007, '2022-03-02 16:32:36.670633', '2002-06-08', 'Tally Vautier', 1985, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 's3Vi2G9j82c', 1, '2022-03-02 16:32:36.670633', 'tvautier15', 2),
	(48, '777 Park Meadow Parkway', 1986, '2022-03-02 16:32:36.670633', '2002-06-08', 'Vail Grestie', 1994, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'plXao1', 1, '2022-03-02 16:32:36.670633', 'vgrestie16', 2),
	(49, '14612 Killdeer Trail', 1992, '2022-03-02 16:32:36.670633', '2002-06-08', 'Abel Harradine', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'OkNGF5LwfLdN', 1, '2022-03-02 16:32:36.670633', 'aharradine17', 2),
	(50, '1908 Melrose Parkway', 2010, '2022-03-02 16:32:36.670633', '2002-06-08', 'Valli Moncreiffe', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '49Z0gZ', 1, '2022-03-02 16:32:36.670633', 'vmoncreiffe18', 2),
	(51, '97583 Buhler Drive', 1998, '2022-03-02 16:32:36.670633', '2002-06-08', 'Jobye Grimestone', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'd0asTT', 1, '2022-03-02 16:32:36.670633', 'jgrimestone19', 2),
	(52, '60981 Fairview Trail', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Amabelle Riccetti', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'VKh6BcXNP', 1, '2022-03-02 16:32:36.670633', 'ariccetti1a', 2),
	(53, '7 Corry Parkway', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Lacee Lortz', 1994, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'pMoO5sWDZj2p', 1, '2022-03-02 16:32:36.670633', 'llortz1b', 3),
	(54, '34296 Esch Road', 2005, '2022-03-02 16:32:36.670633', '2002-06-08', 'Yovonnda Beebis', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'VOll96AFOA', 1, '2022-03-02 16:32:36.670633', 'ybeebis1c', 3),
	(55, '531 Sheridan Drive', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Moises Ames', 1992, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'SuUtz1Sa', 1, '2022-03-02 16:32:36.670633', 'mames1d', 3),
	(56, '3104 Little Fleur Road', 1992, '2022-03-02 16:32:36.670633', '2002-06-08', 'Madelin Weepers', 1993, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'TOWQoK2', 1, '2022-03-02 16:32:36.670633', 'mweepers1e', 3),
	(57, '0684 Dexter Trail', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Berny Pllu', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'Y0yxVLIsH', 1, '2022-03-02 16:32:36.670633', 'bpllu1f', 3),
	(58, '5101 Heath Avenue', 2000, '2022-03-02 16:32:36.670633', '2002-06-08', 'Suzanne Cobby', 1995, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'ZUHXrToXQ', 1, '2022-03-02 16:32:36.670633', 'scobby1g', 3),
	(59, '0053 Declaration Drive', 1996, '2022-03-02 16:32:36.670633', '2002-06-08', 'Adore O\'Hoolahan', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'LKjudKkF6iNv', 1, '2022-03-02 16:32:36.670633', 'aohoolahan1h', 3),
	(60, '6 Marcy Circle', 1997, '2022-03-02 16:32:36.670633', '2002-06-08', 'Wyatt Berthelet', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'wnQiyn4AS33', 1, '2022-03-02 16:32:36.670633', 'wberthelet1i', 3),
	(61, '2 Petterle Drive', 1996, '2022-03-02 16:32:36.670633', '2002-06-08', 'Gnni Murty', 2005, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 't91EqXdAZTVb', 1, '2022-03-02 16:32:36.670633', 'gmurty1j', 3),
	(62, '7873 Vidon Center', 2009, '2022-03-02 16:32:36.670633', '2002-06-08', 'Carolann Renon', 1995, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'zH4Yfv', 1, '2022-03-02 16:32:36.670633', 'crenon1k', 3),
	(63, '8519 Troy Plaza', 1996, '2022-03-02 16:32:36.670633', '2002-06-08', 'Sabrina Hurran', 1995, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'ibfHe31d', 1, '2022-03-02 16:32:36.670633', 'shurran1l', 3),
	(64, '156 Claremont Drive', 2002, '2022-03-02 16:32:36.670633', '2002-06-08', 'Faun McClay', 2011, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'C6meUKh', 1, '2022-03-02 16:32:36.670633', 'fmcclay1m', 3),
	(65, '4589 Merrick Center', 2005, '2022-03-02 16:32:36.670633', '2002-06-08', 'Shelba Humphery', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'JcVLFeetL', 1, '2022-03-02 16:32:36.670633', 'shumphery1n', 3),
	(66, '79 Linden Avenue', 2007, '2022-03-02 16:32:36.670633', '2002-06-08', 'Nicolea Winterbotham', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'gwFxbmvKHHEQ', 1, '2022-03-02 16:32:36.670633', 'nwinterbotham1o', 3),
	(67, '640 Lien Place', 1996, '2022-03-02 16:32:36.670633', '2002-06-08', 'Bethanne Reedshaw', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'WgsxSO', 1, '2022-03-02 16:32:36.670633', 'breedshaw1p', 3),
	(68, '09945 Eagle Crest Circle', 2012, '2022-03-02 16:32:36.670633', '2002-06-08', 'Vinson Knappett', 1995, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'AR6T3f6UIqhZ', 1, '2022-03-02 16:32:36.670633', 'vknappett1q', 3),
	(69, '82740 Village Green Drive', 1998, '2022-03-02 16:32:36.670633', '2002-06-08', 'Reinhard McDermid', 2011, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'BbZvtyzML9x', 1, '2022-03-02 16:32:36.670633', 'rmcdermid1r', 3),
	(70, '66 Buhler Street', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Rufus Conaghan', 1992, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'bbQspWdls', 1, '2022-03-02 16:32:36.670633', 'rconaghan1s', 3),
	(71, '1134 Waywood Center', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Quincy Leavold', 2009, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'yvnHxk', 1, '2022-03-02 16:32:36.670633', 'qleavold1t', 3),
	(72, '0034 Logan Alley', 2009, '2022-03-02 16:32:36.670633', '2002-06-08', 'Krystyna Tourville', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'P1ygYI2M', 1, '2022-03-02 16:32:36.670633', 'ktourville1u', 3),
	(73, '842 Toban Junction', 1998, '2022-03-02 16:32:36.670633', '2002-06-08', 'Shannan Abilowitz', 2007, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'ysvtG5JqH', 1, '2022-03-02 16:32:36.670633', 'sabilowitz1v', 3),
	(74, '88 Linden Pass', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Shaylah Branscombe', 1992, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'lVO5eL', 1, '2022-03-02 16:32:36.670633', 'sbranscombe1w', 3),
	(75, '3344 Becker Circle', 1993, '2022-03-02 16:32:36.670633', '2002-06-08', 'Reeba Rappport', 2009, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'X1yZmsGaZ', 1, '2022-03-02 16:32:36.670633', 'rrappport1x', 3),
	(76, '38 Loeprich Park', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Cordelia McGrail', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '4j6zPri', 1, '2022-03-02 16:32:36.670633', 'cmcgrail1y', 3),
	(77, '3 Barby Road', 1993, '2022-03-02 16:32:36.670633', '2002-06-08', 'Cecile Adamski', 1996, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'GOFUShJc3', 1, '2022-03-02 16:32:36.670633', 'cadamski1z', 3),
	(78, '279 Fulton Circle', 1997, '2022-03-02 16:32:36.670633', '2002-06-08', 'Janie Shury', 2010, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'FTCQ7VQr', 1, '2022-03-02 16:32:36.670633', 'jshury20', 3),
	(79, '533 Monument Road', 1993, '2022-03-02 16:32:36.670633', '2002-06-08', 'Fanchette Haggish', 2001, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'Wj1vqzeki5', 1, '2022-03-02 16:32:36.670633', 'fhaggish21', 3),
	(80, '01 Anhalt Street', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Kahaleel Branch', 1991, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'E8XOYBD', 1, '2022-03-02 16:32:36.670633', 'kbranch22', 3),
	(81, '38142 Rockefeller Terrace', 1976, '2022-03-02 16:32:36.670633', '2002-06-08', 'Katherine Eddy', 2011, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'UAin7g4U', 1, '2022-03-02 16:32:36.670633', 'keddy23', 4),
	(82, '6214 Eagle Crest Street', 2007, '2022-03-02 16:32:36.670633', '2002-06-08', 'Geordie MacAlpine', 1999, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'tbx7cgFr', 1, '2022-03-02 16:32:36.670633', 'gmacalpine24', 4),
	(83, '3165 Badeau Circle', 1992, '2022-03-02 16:32:36.670633', '2002-06-08', 'Peadar Mathys', 2011, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'Sq7vQLdDYU3', 1, '2022-03-02 16:32:36.670633', 'pmathys25', 4),
	(84, '59 Eggendart Avenue', 2001, '2022-03-02 16:32:36.670633', '2002-06-08', 'Tisha Medd', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'H6TFIVbw', 1, '2022-03-02 16:32:36.202200', 'tmedd26', 4),
	(85, '41034 Hintze Street', 1980, '2022-03-02 16:32:36.670633', '2002-06-08', 'Tabbitha Borge', 2001, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'BDnwXihH', 1, '2022-03-02 16:32:36.670633', 'tborge27', 4),
	(86, '122 Mayer Parkway', 1992, '2022-03-02 16:32:36.670633', '2002-06-08', 'Chaunce Gianelli', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'knnjhPKS', 1, '2022-03-02 16:32:36.670633', 'cgianelli28', 4),
	(87, '5 Mockingbird Alley', 2001, '2022-03-02 16:32:36.670633', '2002-06-08', 'Rhett McCrainor', 1970, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'gn3w8S7GQT', 1, '2022-03-02 16:32:36.670633', 'rmccrainor29', 4),
	(88, '01006 Merrick Terrace', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Meier Saleway', 1995, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'DPrvtaEX', 1, '2022-03-02 16:32:36.670633', 'msaleway2a', 4),
	(89, '67 Schmedeman Court', 1995, '2022-03-02 16:32:36.670633', '2002-06-08', 'Yurik Winslade', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'fBJeNa1', 1, '2022-03-02 16:32:36.670633', 'ywinslade2b', 4),
	(90, '67413 High Crossing Street', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Suzanna McDermott', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'uK7ByuQIbXyw', 1, '2022-03-02 16:32:36.670633', 'smcdermott2c', 4),
	(91, '3209 Morrow Terrace', 2007, '2022-03-02 16:32:36.670633', '2002-06-08', 'Deborah Towson', 2008, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'UayaKybIwxG', 1, '2022-03-02 16:32:36.670633', 'dtowson2d', 4),
	(92, '72 Scott Way', 2007, '2022-03-02 16:32:36.670633', '2002-06-08', 'Terrance Garretson', 2009, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'NC2GfwXh', 1, '2022-03-02 16:32:36.670633', 'tgarretson2e', 4),
	(93, '64 Johnson Trail', 2012, '2022-03-02 16:32:36.670633', '2002-06-08', 'Turner Titterington', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'r2iopNW62', 1, '2022-03-02 16:32:36.670633', 'ttitterington2f', 4),
	(94, '733 Oak Valley Trail', 1996, '2022-03-02 16:32:36.670633', '2002-06-08', 'Liva Clinton', 1996, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'z3OxmGPMm', 1, '2022-03-02 16:32:36.670633', 'lclinton2g', 4),
	(95, '1 Roth Parkway', 2008, '2022-03-02 16:32:36.670633', '2002-06-08', 'Nonah Escale', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'SECXZMO5HqpN', 1, '2022-03-02 16:32:36.670633', 'nescale2h', 4),
	(96, '6 Shasta Trail', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Cirillo Merrick', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '5rWK777f', 1, '2022-03-02 16:32:36.670633', 'cmerrick2i', 4),
	(97, '4 Lake View Terrace', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Harris Pimm', 1993, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'BQck8IrqMJJ', 1, '2022-03-02 16:32:36.670633', 'hpimm2j', 4),
	(98, '34439 Shopko Parkway', 2006, '2022-03-02 16:32:36.670633', '2002-06-08', 'Georgetta Leaburn', 1986, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'FbsX5McZT', 1, '2022-03-02 16:32:36.670633', 'gleaburn2k', 4),
	(99, '6699 Lunder Point', 1999, '2022-03-02 16:32:36.670633', '2002-06-08', 'Town Trahair', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '2REvDK', 1, '2022-03-02 16:32:36.670633', 'ttrahair2l', 4),
	(100, '34 Village Crossing', 2007, '2022-03-02 16:32:36.670633', '2002-06-08', 'Arthur Digman', 1998, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'QVbsAxF', 1, '2022-03-02 16:32:36.670633', 'adigman2m', 4),
	(101, '62314 Warrior Court', 1993, '2022-03-02 16:32:36.670633', '2002-06-08', 'Zahara Harbard', 2004, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'cqm7ZtoLA', 1, '2022-03-02 16:32:36.670633', 'zharbard2n', 4),
	(102, '6 Moland Street', 2012, '2022-03-02 16:32:36.670633', '2002-06-08', 'Krystle Shead', 2001, 'yPQ0ROjAsM9BFz9xpsJl.jpg', '0RJjdPqkkngW', 1, '2022-03-02 16:32:36.670633', 'kshead2o', 4),
	(103, '4 Toban Street', 2001, '2022-03-02 16:32:36.670633', '2002-06-08', 'Myrle Eayres', 1996, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'U8phWMEH7', 1, '2022-03-02 16:32:36.670633', 'meayres2p', 4),
	(104, '42 Laurel Plaza', 2011, '2022-03-02 16:32:36.670633', '2002-06-08', 'Maighdiln Darwood', 2006, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'SMhpQye', 1, '2022-03-02 16:32:36.670633', 'mdarwood2q', 4),
	(105, '1044 Schurz Circle', 2003, '2022-03-02 16:32:36.670633', '2002-06-08', 'Raffaello Routley', 1984, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'FCXPlYG', 1, '2022-03-02 16:32:36.670633', 'rroutley2r', 4),
	(106, '59123 Ruskin Park', 2010, '2022-03-02 16:32:36.670633', '2021-06-03', 'Terra Humpatch', 1997, 'yPQ0ROjAsM9BFz9xpsJl.jpg', 'aPZZSuaT', 1, '2022-03-02 16:32:36.670633', 'thumpatch0', 4);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- Dumping structure for table school_management.student_evaluate
CREATE TABLE IF NOT EXISTS `student_evaluate` (
  `id` int(11) NOT NULL,
  `academic_ability` int(11) DEFAULT NULL,
  `conduct` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `evaluate` varchar(255) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`,`class_id`),
  KEY `FK7uyfqp7ix83nb096c55oqnr3y` (`created_by`),
  KEY `FK7r0pnaycxkc9v6hsjsblq9v51` (`updated_by`),
  KEY `FK4quyfchyfgpv037oy2seq38ku` (`class_id`),
  CONSTRAINT `FK4quyfchyfgpv037oy2seq38ku` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`),
  CONSTRAINT `FK73jn5ijl2plb5g4qegcuk5889` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`),
  CONSTRAINT `FK7r0pnaycxkc9v6hsjsblq9v51` FOREIGN KEY (`updated_by`) REFERENCES `user` (`id`),
  CONSTRAINT `FK7uyfqp7ix83nb096c55oqnr3y` FOREIGN KEY (`created_by`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.student_evaluate: ~0 rows (approximately)
DELETE FROM `student_evaluate`;
/*!40000 ALTER TABLE `student_evaluate` DISABLE KEYS */;
/*!40000 ALTER TABLE `student_evaluate` ENABLE KEYS */;

-- Dumping structure for table school_management.subjects
CREATE TABLE IF NOT EXISTS `subjects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

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
CREATE TABLE IF NOT EXISTS `teacher_subjects` (
  `user_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`subject_id`),
  KEY `FKdweqkwxroox2u7pbmksehx04i` (`subject_id`),
  CONSTRAINT `FKdweqkwxroox2u7pbmksehx04i` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`),
  CONSTRAINT `FKockx0rkpkmbpvxh9ftrmgrx4q` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.teacher_subjects: ~15 rows (approximately)
DELETE FROM `teacher_subjects`;
/*!40000 ALTER TABLE `teacher_subjects` DISABLE KEYS */;
INSERT INTO `teacher_subjects` (`user_id`, `subject_id`) VALUES
	(27, 1),
	(28, 1),
	(29, 2),
	(30, 2),
	(31, 3),
	(32, 3),
	(33, 4),
	(34, 4),
	(35, 5),
	(36, 5),
	(37, 6),
	(38, 6),
	(40, 7),
	(41, 7),
	(42, 7);
/*!40000 ALTER TABLE `teacher_subjects` ENABLE KEYS */;

-- Dumping structure for table school_management.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `deleted` bit(1) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `end_date` date NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `updated_date` datetime NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.user: ~17 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `address`, `created_date`, `deleted`, `dob`, `email`, `end_date`, `full_name`, `image`, `password`, `phone`, `start_date`, `updated_date`, `username`) VALUES
	(20, 'HN-VN', '2022-02-26 14:39:31', b'0', '2022-02-02', 'admin@gmail.com', '2022-02-17', 'Admin', 'kJFbCY8GuWzRJ6BQ3kva.jpg', '$2a$10$F7dP80A4BkyWIRd.FF.J1OEtbH8S7I5.eZTNpJ/aG3O6W8xykk8Za', '2316544', '2022-02-15', '2022-02-26 14:39:31', 'admin'),
	(27, 'HN-VN', '2022-03-02 17:12:47', b'0', '2022-03-10', 'teacher1@gmail.com', '2022-02-14', 'Teacher 1', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$F7dP80A4BkyWIRd.FF.J1OEtbH8S7I5.eZTNpJ/aG3O6W8xykk8Za', '0123456789', '2022-02-08', '2022-03-02 17:12:47', 'teacher1'),
	(28, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 2', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher2'),
	(29, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 3', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher3'),
	(30, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 4', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher4'),
	(31, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 5', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher5'),
	(32, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 6', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher6'),
	(33, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 7', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher7'),
	(34, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 8', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher8'),
	(35, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 9', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher9'),
	(36, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 10', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher10'),
	(37, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 11', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher11'),
	(38, 'HN-VN', '2022-03-02 17:24:14', b'0', '2022-03-02', 'teacher2@gmail.com', '2022-03-23', 'Teacher 12', 'JJUNVonQNdTNT3WfSIn1.jpg', '$2a$10$zxyMz/iAELsSdZ88uz4ESu8w4yqV5aXaPLbJGEBIUU79LJfM8Slcu', '0123456789', '2022-02-15', '2022-03-02 17:24:14', 'teacher12'),
	(39, '1 New Castle Trails', '2022-03-07 17:18:19', b'0', '2022-03-01', 'nam.nv.836@aptechlearning.edu.vn', '2022-04-09', 'Teacher14', 'e362b14e-d7bb-4eeb-b414-9fd0f1250872.jpg', '$2a$10$9ubKTdDaCPRi2fYsMD3d7.uvOcLkauOQNqQZLI3w.tc6.qFRD/xeC', '0355789812', '2022-02-27', '2022-03-07 17:18:19', 'Teacher14'),
	(40, '1 New Castle Trails', '2022-03-07 17:18:49', b'0', '2022-03-01', 'nam.nv.836@aptechlearning.edu.com', '2022-04-09', 'Teacher 15', '7aa846a5-5c58-40cd-9352-7382c7b02783.jpg', '$2a$10$tGBjRDiHi9o4O04rDBXc9ec2fvX2KXgDCOOvUrJEfgnBV9vyy8DUq', '0355789812', '2022-04-06', '2022-03-07 17:18:49', 'teacher 15'),
	(41, '1 New Castle Trails', '2022-03-07 17:19:19', b'1', '2022-02-27', 'nam.nv.836@aptechlearning.edu.vn.v', '2022-03-01', 'Teacher 16', '0b61a20e-59f3-4a83-ada6-480e76998965.jpg', '$2a$10$t5OOsBlnoqbTPAcO3a9InOyLovS./ZGz/f6OK0PvAVv40fgzZexKe', '0355789812', '2022-02-27', '2022-03-07 17:19:19', 'Teacher16'),
	(42, 'Saint Pauls', '2022-03-07 17:19:53', b'0', '2022-03-01', 'nam.nv.836@aptechlearning.edu.vn.com', '2022-03-03', 'Teacher 17', '57a245ca-fc10-4b78-b892-d60a75988ebd.jpg', '$2a$10$HdIZHqa5L3PajZYZgCiohuR3wBQTZuJAMWJuoWDbrGDPdmvKN2dGy', '0355789812', '2022-02-27', '2022-03-07 17:19:53', 'teacher 17');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table school_management.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Dumping data for table school_management.user_role: ~17 rows (approximately)
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
	(38, 2),
	(39, 2),
	(40, 2),
	(41, 2),
	(42, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
