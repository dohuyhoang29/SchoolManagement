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
CREATE DATABASE IF NOT EXISTS `school_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `school_management`;

-- Dumping structure for table school_management.attacment
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

-- Dumping data for table school_management.class: ~11 rows (approximately)
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
  `class_id` int NOT NULL,
  `subject_id` int NOT NULL,
  `user_id` int NOT NULL,
  `id` int NOT NULL,
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

-- Dumping structure for table school_management.hibernate_sequence
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.hibernate_sequence: ~1 rows (approximately)
DELETE FROM `hibernate_sequence`;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` (`next_val`) VALUES
	(1);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;

-- Dumping structure for table school_management.mark
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
CREATE TABLE IF NOT EXISTS `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `admission_year` int NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `dob` date NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `graduate_year` int NOT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  `class_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_jyet50p17q01ks2bv4sn8i5r7` (`username`),
  KEY `FKdwhkib64u47wc4yo4hk0cub90` (`class_id`),
  CONSTRAINT `FKdwhkib64u47wc4yo4hk0cub90` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=301 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.student: ~0 rows (approximately)
DELETE FROM `student`;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`id`, `address`, `admission_year`, `created_date`, `dob`, `full_name`, `graduate_year`, `image`, `password`, `status`, `updated_date`, `username`, `class_id`) VALUES
	(1, '42114 Kedzie Avenue', 1999, '2022-03-09 00:00:00.000000', '2006-01-15', 'Orel Andras', 2011, NULL, '4fJmKsela', 2, '2022-03-09 00:00:00.000000', 'oandras0', 6),
	(2, '3 Derek Avenue', 1994, '2022-03-09 00:00:00.000000', '2001-10-01', 'Cad Delicate', 2010, NULL, 'yqmtDqik', 2, '2022-03-09 00:00:00.000000', 'cdelicate1', 3),
	(3, '1288 Blue Bill Park Crossing', 2011, '2022-03-09 00:00:00.000000', '2007-09-01', 'Jacquenette Devereux', 1993, NULL, 'LDFIUWPI', 3, '2022-03-09 00:00:00.000000', 'jdevereux2', 6),
	(4, '07621 Spenser Alley', 2008, '2022-03-09 00:00:00.000000', '2002-12-27', 'Tim Ferrillio', 2000, NULL, 'R1Yqy3mvCn', 3, '2022-03-09 00:00:00.000000', 'tferrillio3', 11),
	(5, '52 Scofield Lane', 2002, '2022-03-09 00:00:00.000000', '2009-01-02', 'Gunner Ludovico', 1994, NULL, 'RFczaVXQXkX', 3, '2022-03-09 00:00:00.000000', 'gludovico4', 3),
	(6, '26625 Fremont Alley', 2004, '2022-03-09 00:00:00.000000', '2002-08-13', 'Willetta Peddar', 2006, NULL, 'IrwwGX9y3M', 1, '2022-03-09 00:00:00.000000', 'wpeddar5', 12),
	(7, '72590 Hoepker Court', 1996, '2022-03-09 00:00:00.000000', '2006-06-18', 'Erskine Simcock', 1989, NULL, 'KdaFhMqfNic2', 3, '2022-03-09 00:00:00.000000', 'esimcock6', 8),
	(8, '3 Amoth Plaza', 2011, '2022-03-09 00:00:00.000000', '2004-12-31', 'Yovonnda Inglis', 2005, NULL, '6c8cOd1', 1, '2022-03-09 00:00:00.000000', 'yinglis7', 5),
	(9, '92516 Eagan Hill', 1995, '2022-03-09 00:00:00.000000', '2009-12-19', 'Paulette Conneau', 1997, NULL, 'WJbcwU5oOLtF', 2, '2022-03-09 00:00:00.000000', 'pconneau8', 5),
	(10, '81698 Pepper Wood Crossing', 2012, '2022-03-09 00:00:00.000000', '2006-07-14', 'Jehanna Anglim', 2003, NULL, 'QTzjneK8JdRw', 2, '2022-03-09 00:00:00.000000', 'janglim9', 10),
	(11, '14491 Toban Junction', 1995, '2022-03-09 00:00:00.000000', '2004-03-21', 'Dom Heijne', 2009, NULL, 'dxdF6pylxg', 3, '2022-03-09 00:00:00.000000', 'dheijnea', 9),
	(12, '2988 Kings Park', 1996, '2022-03-09 00:00:00.000000', '2002-04-17', 'Jewelle Fuidge', 1955, NULL, 'aiBPkTXVNPz', 2, '2022-03-09 00:00:00.000000', 'jfuidgeb', 11),
	(13, '84490 Golden Leaf Place', 2001, '2022-03-09 00:00:00.000000', '2007-04-13', 'Barclay Davydoch', 1996, NULL, 'heAcyCgerQU', 3, '2022-03-09 00:00:00.000000', 'bdavydochc', 9),
	(14, '8 Independence Pass', 2011, '2022-03-09 00:00:00.000000', '2008-07-11', 'Kala Commander', 2000, NULL, 'bHUYWCh', 1, '2022-03-09 00:00:00.000000', 'kcommanderd', 5),
	(15, '29 Butterfield Alley', 1998, '2022-03-09 00:00:00.000000', '2005-08-04', 'Rhianna Fettis', 2007, NULL, 'O8Ov0D', 1, '2022-03-09 00:00:00.000000', 'rfettise', 2),
	(16, '82386 Main Hill', 1991, '2022-03-09 00:00:00.000000', '2008-01-11', 'Kimbell Budd', 2013, NULL, 'lmIK53Tt4Znj', 3, '2022-03-09 00:00:00.000000', 'kbuddf', 2),
	(17, '31 Lakewood Junction', 2009, '2022-03-09 00:00:00.000000', '2008-06-05', 'Wye Carrington', 2008, NULL, 'RgZlX72Hr', 3, '2022-03-09 00:00:00.000000', 'wcarringtong', 11),
	(18, '3 Troy Junction', 2012, '2022-03-09 00:00:00.000000', '2008-11-30', 'Gus Rehor', 1996, NULL, 'kuf5mpnc7s3Q', 3, '2022-03-09 00:00:00.000000', 'grehorh', 12),
	(19, '6 Kenwood Way', 2010, '2022-03-09 00:00:00.000000', '2001-12-25', 'Morgen Whittier', 1990, NULL, '2o90S4HBTH', 3, '2022-03-09 00:00:00.000000', 'mwhittieri', 10),
	(20, '670 Mitchell Place', 1999, '2022-03-09 00:00:00.000000', '2006-02-22', 'Farley Guterson', 2006, NULL, 'tQ7FvTr', 2, '2022-03-09 00:00:00.000000', 'fgutersonj', 5),
	(21, '86 Sloan Plaza', 2009, '2022-03-09 00:00:00.000000', '2002-05-08', 'Regina Brazelton', 2008, NULL, 'rJ2GQ7e', 1, '2022-03-09 00:00:00.000000', 'rbrazeltonk', 11),
	(22, '589 Glacier Hill Park', 2005, '2022-03-09 00:00:00.000000', '2001-08-18', 'Poul Walkden', 1998, NULL, '6BoB3VRj', 2, '2022-03-09 00:00:00.000000', 'pwalkdenl', 5),
	(23, '521 Coleman Circle', 2009, '2022-03-09 00:00:00.000000', '2003-03-21', 'Kahaleel Shower', 2000, NULL, 'jjyjBXwRFhO', 1, '2022-03-09 00:00:00.000000', 'kshowerm', 6),
	(24, '49353 Bunker Hill Road', 2001, '2022-03-09 00:00:00.000000', '2006-03-25', 'Melonie Urey', 1993, NULL, 't4Dhg3', 1, '2022-03-09 00:00:00.000000', 'mureyn', 7),
	(25, '05069 Kenwood Plaza', 2000, '2022-03-09 00:00:00.000000', '2006-06-20', 'Amelita McLarens', 2009, NULL, 'CWqRtDwP', 1, '2022-03-09 00:00:00.000000', 'amclarenso', 2),
	(26, '0210 Pleasure Park', 1996, '2022-03-09 00:00:00.000000', '2009-09-05', 'Annamaria Keighley', 2010, NULL, 'Z8ZFAhERGif', 1, '2022-03-09 00:00:00.000000', 'akeighleyp', 10),
	(27, '02 6th Circle', 2005, '2022-03-09 00:00:00.000000', '2004-10-03', 'Chadd Vaney', 2011, NULL, 'R72BmM', 1, '2022-03-09 00:00:00.000000', 'cvaneyq', 1),
	(28, '72852 Valley Edge Court', 2009, '2022-03-09 00:00:00.000000', '2009-07-10', 'Wallis Bewsy', 2002, NULL, '8XR81fdsztg1', 3, '2022-03-09 00:00:00.000000', 'wbewsyr', 5),
	(29, '83193 Monica Park', 1999, '2022-03-09 00:00:00.000000', '2000-11-09', 'Juliette Mosen', 1998, NULL, 'bae7REN', 3, '2022-03-09 00:00:00.000000', 'jmosens', 9),
	(30, '62947 Susan Point', 1997, '2022-03-09 00:00:00.000000', '2006-06-04', 'Orrin Keywood', 2002, NULL, '3aY06cuFg', 3, '2022-03-09 00:00:00.000000', 'okeywoodt', 10),
	(31, '1756 Katie Parkway', 1997, '2022-03-09 00:00:00.000000', '2001-10-22', 'Martainn Jovasevic', 2003, NULL, 'L36Y2m9xxIKW', 1, '2022-03-09 00:00:00.000000', 'mjovasevicu', 8),
	(32, '3 Milwaukee Way', 2003, '2022-03-09 00:00:00.000000', '2004-04-18', 'Miner Heading', 2010, NULL, 'zKTG0Q08oZw', 2, '2022-03-09 00:00:00.000000', 'mheadingv', 5),
	(33, '0585 Johnson Avenue', 1999, '2022-03-09 00:00:00.000000', '2000-12-15', 'Tann Borleace', 2010, NULL, 'm4jc2Va', 1, '2022-03-09 00:00:00.000000', 'tborleacew', 5),
	(34, '40 Tomscot Junction', 1996, '2022-03-09 00:00:00.000000', '2006-11-04', 'Chrissy Windmill', 2004, NULL, 'UWyqtX', 2, '2022-03-09 00:00:00.000000', 'cwindmillx', 6),
	(35, '56464 Alpine Junction', 1995, '2022-03-09 00:00:00.000000', '2008-09-19', 'Isacco Merring', 1999, NULL, 'l1Kqv2TH', 3, '2022-03-09 00:00:00.000000', 'imerringy', 7),
	(36, '312 Rieder Alley', 1970, '2022-03-09 00:00:00.000000', '2007-09-12', 'Augustine Dillamore', 1996, NULL, 'E1owNg', 2, '2022-03-09 00:00:00.000000', 'adillamorez', 5),
	(37, '718 Golf Course Park', 2006, '2022-03-09 00:00:00.000000', '2006-03-03', 'Dorie Cumine', 1998, NULL, 'f8iydM95OpFJ', 3, '2022-03-09 00:00:00.000000', 'dcumine10', 3),
	(38, '41579 Park Meadow Center', 2006, '2022-03-09 00:00:00.000000', '2004-11-25', 'Reece Iowarch', 2011, NULL, 'gq7W02eb', 3, '2022-03-09 00:00:00.000000', 'riowarch11', 1),
	(39, '2 Bluestem Junction', 2013, '2022-03-09 00:00:00.000000', '2006-11-25', 'Muffin McGauhy', 2007, NULL, 'lnOXBUCix043', 2, '2022-03-09 00:00:00.000000', 'mmcgauhy12', 7),
	(40, '18 Red Cloud Place', 2006, '2022-03-09 00:00:00.000000', '2009-08-25', 'Harriet Grimm', 2011, NULL, 'ujMBYzk', 1, '2022-03-09 00:00:00.000000', 'hgrimm13', 4),
	(41, '4 Monument Junction', 1968, '2022-03-09 00:00:00.000000', '2001-06-17', 'Hewitt Clulow', 1986, NULL, 'RUUOEMGpYQ', 2, '2022-03-09 00:00:00.000000', 'hclulow14', 6),
	(42, '5 Glacier Hill Crossing', 1999, '2022-03-09 00:00:00.000000', '2004-08-23', 'Ripley Ronayne', 1987, NULL, 'RYdd6c246HH', 3, '2022-03-09 00:00:00.000000', 'rronayne15', 12),
	(43, '3876 Sunfield Circle', 1990, '2022-03-09 00:00:00.000000', '2001-11-29', 'Abagail Wickliffe', 2004, NULL, 'dTfhOjdYF0', 1, '2022-03-09 00:00:00.000000', 'awickliffe16', 11),
	(44, '4 Montana Court', 1992, '2022-03-09 00:00:00.000000', '2004-12-12', 'Electra Drew', 2008, NULL, '9bKB7HXR', 2, '2022-03-09 00:00:00.000000', 'edrew17', 2),
	(45, '121 Basil Alley', 1991, '2022-03-09 00:00:00.000000', '2006-12-14', 'Christi Brotherick', 1988, NULL, 'ID4BQXiW0', 3, '2022-03-09 00:00:00.000000', 'cbrotherick18', 11),
	(46, '7 Westridge Alley', 2002, '2022-03-09 00:00:00.000000', '2001-03-29', 'Auria Flaunders', 2010, NULL, 'sQFssVmfi', 2, '2022-03-09 00:00:00.000000', 'aflaunders19', 6),
	(47, '615 Elka Center', 2000, '2022-03-09 00:00:00.000000', '2000-04-22', 'Brenna Povah', 1997, NULL, 'sWOPWVwiOmx0', 3, '2022-03-09 00:00:00.000000', 'bpovah1a', 12),
	(48, '0 Muir Point', 1985, '2022-03-09 00:00:00.000000', '2009-10-31', 'Katrinka Ferrieri', 1996, NULL, 'P1pQXJaP4', 3, '2022-03-09 00:00:00.000000', 'kferrieri1b', 12),
	(49, '91437 Melody Center', 1994, '2022-03-09 00:00:00.000000', '2007-05-13', 'Darelle Cudd', 2001, NULL, 'YbYCmGY', 2, '2022-03-09 00:00:00.000000', 'dcudd1c', 11),
	(50, '99131 Roxbury Center', 2000, '2022-03-09 00:00:00.000000', '2007-09-12', 'Kennie Capron', 1987, NULL, 'N2mm5fdNjeWS', 1, '2022-03-09 00:00:00.000000', 'kcapron1d', 12),
	(51, '6204 Melrose Road', 2000, '2022-03-09 00:00:00.000000', '2004-12-04', 'Pat Letch', 1995, NULL, 'w00lp3Sw', 3, '2022-03-09 00:00:00.000000', 'pletch1e', 3),
	(52, '04759 Merrick Hill', 2001, '2022-03-09 00:00:00.000000', '2005-10-05', 'Giacopo Watkiss', 2001, NULL, '1rli3Dz96', 3, '2022-03-09 00:00:00.000000', 'gwatkiss1f', 5),
	(53, '24616 Annamark Place', 1993, '2022-03-09 00:00:00.000000', '2001-02-02', 'Reade Smales', 2010, NULL, 'DQf3zBNP6', 2, '2022-03-09 00:00:00.000000', 'rsmales1g', 1),
	(54, '20 Northview Lane', 1994, '2022-03-09 00:00:00.000000', '2001-11-03', 'Saxon Andreini', 1992, NULL, '8oSQYKD0', 1, '2022-03-09 00:00:00.000000', 'sandreini1h', 2),
	(55, '3 Sutteridge Point', 1985, '2022-03-09 00:00:00.000000', '2003-10-30', 'Chad Verbrugghen', 2006, NULL, 'RVY6UZa', 3, '2022-03-09 00:00:00.000000', 'cverbrugghen1i', 12),
	(56, '3440 Longview Court', 2004, '2022-03-09 00:00:00.000000', '2000-04-16', 'Baudoin Janks', 1997, NULL, 'RWPqfMHW53', 1, '2022-03-09 00:00:00.000000', 'bjanks1j', 11),
	(57, '50936 Artisan Street', 2000, '2022-03-09 00:00:00.000000', '2001-03-08', 'Rubetta Caughtry', 2008, NULL, 'hxIOsJ2uHoi', 3, '2022-03-09 00:00:00.000000', 'rcaughtry1k', 11),
	(58, '11 Havey Center', 2005, '2022-03-09 00:00:00.000000', '2006-04-21', 'Kelci Broadbent', 1999, NULL, 'luhGqiWckAc', 1, '2022-03-09 00:00:00.000000', 'kbroadbent1l', 2),
	(59, '6078 Village Way', 1992, '2022-03-09 00:00:00.000000', '2007-01-14', 'Stanford Garmanson', 2010, NULL, 'eyPSjR', 3, '2022-03-09 00:00:00.000000', 'sgarmanson1m', 8),
	(60, '0 Duke Court', 2001, '2022-03-09 00:00:00.000000', '2007-06-21', 'Nathanael Heiden', 2000, NULL, 'm6NnZL7', 2, '2022-03-09 00:00:00.000000', 'nheiden1n', 4),
	(61, '26 Russell Alley', 1999, '2022-03-09 00:00:00.000000', '2006-04-04', 'Ives Happs', 1993, NULL, '0AaBIaq', 1, '2022-03-09 00:00:00.000000', 'ihapps1o', 1),
	(62, '31 Hooker Circle', 2010, '2022-03-09 00:00:00.000000', '2003-10-09', 'Giusto Stanney', 2005, NULL, 'f9jaGUoq', 3, '2022-03-09 00:00:00.000000', 'gstanney1p', 2),
	(63, '7 Di Loreto Drive', 1999, '2022-03-09 00:00:00.000000', '2007-12-25', 'Gabriella Pittet', 2005, NULL, 'NxxLJqu', 3, '2022-03-09 00:00:00.000000', 'gpittet1q', 9),
	(64, '68 Holmberg Center', 2003, '2022-03-09 00:00:00.000000', '2004-08-24', 'Nadine Greenham', 2002, NULL, 'Y2jAW5Epy2R', 3, '2022-03-09 00:00:00.000000', 'ngreenham1r', 6),
	(65, '2 Corry Plaza', 2009, '2022-03-09 00:00:00.000000', '2000-03-09', 'Saunderson McKeighen', 1964, NULL, 'QfUlmxQL', 3, '2022-03-09 00:00:00.000000', 'smckeighen1s', 3),
	(66, '49717 Myrtle Crossing', 2001, '2022-03-09 00:00:00.000000', '2007-04-07', 'Delmar Lipmann', 2008, NULL, 'c6jpAONc4XX', 2, '2022-03-09 00:00:00.000000', 'dlipmann1t', 10),
	(67, '7 Steensland Circle', 1996, '2022-03-09 00:00:00.000000', '2009-02-01', 'Lissi Schwandermann', 2005, NULL, 'WH78Ziusn', 1, '2022-03-09 00:00:00.000000', 'lschwandermann1u', 11),
	(68, '29589 Spenser Point', 1993, '2022-03-09 00:00:00.000000', '2009-08-07', 'Ashlin Checci', 1999, NULL, 'DCY9Qtp', 3, '2022-03-09 00:00:00.000000', 'achecci1v', 4),
	(69, '30060 Blue Bill Park Pass', 2011, '2022-03-09 00:00:00.000000', '2004-01-26', 'Micheline Heselwood', 1987, NULL, 'Ceo2teN27F5', 2, '2022-03-09 00:00:00.000000', 'mheselwood1w', 7),
	(70, '946 Prairieview Junction', 2008, '2022-03-09 00:00:00.000000', '2001-03-04', 'Yancey Brooker', 2000, NULL, 'TRGakn8ySmnH', 2, '2022-03-09 00:00:00.000000', 'ybrooker1x', 12),
	(71, '26629 Birchwood Crossing', 2000, '2022-03-09 00:00:00.000000', '2009-12-28', 'Kalila Meriott', 2009, NULL, '4EciJHh', 2, '2022-03-09 00:00:00.000000', 'kmeriott1y', 9),
	(72, '52 Tennessee Hill', 2007, '2022-03-09 00:00:00.000000', '2006-08-19', 'Meyer Arent', 2007, NULL, '4U4gteaoGb', 3, '2022-03-09 00:00:00.000000', 'marent1z', 8),
	(73, '2050 Lillian Road', 1990, '2022-03-09 00:00:00.000000', '2008-01-28', 'Garry Haensel', 2009, NULL, '2WJNEg', 3, '2022-03-09 00:00:00.000000', 'ghaensel20', 4),
	(74, '1 Duke Avenue', 2010, '2022-03-09 00:00:00.000000', '2001-12-10', 'Rosalinda Piell', 1988, NULL, 'G34lFzpXr', 2, '2022-03-09 00:00:00.000000', 'rpiell21', 7),
	(75, '8 Calypso Court', 2004, '2022-03-09 00:00:00.000000', '2004-08-06', 'Crin Magowan', 2004, NULL, 'a2PoD8XXwQx', 2, '2022-03-09 00:00:00.000000', 'cmagowan22', 8),
	(76, '2278 Duke Junction', 2006, '2022-03-09 00:00:00.000000', '2000-04-29', 'Janith McQuillin', 1988, NULL, '5PMwOBIs', 2, '2022-03-09 00:00:00.000000', 'jmcquillin23', 5),
	(77, '0678 Burning Wood Park', 1990, '2022-03-09 00:00:00.000000', '2001-10-20', 'Milt Sparrowhawk', 1999, NULL, 'anHsno0BvD8', 1, '2022-03-09 00:00:00.000000', 'msparrowhawk24', 1),
	(78, '51307 Washington Terrace', 1992, '2022-03-09 00:00:00.000000', '2009-11-28', 'Dulcia Duchan', 2012, NULL, 'YO8Gq8YTas', 3, '2022-03-09 00:00:00.000000', 'dduchan25', 12),
	(79, '13 Loftsgordon Terrace', 2003, '2022-03-09 00:00:00.000000', '2005-10-14', 'Margeaux Dreigher', 2006, NULL, 'bH8VTA3', 3, '2022-03-09 00:00:00.000000', 'mdreigher26', 11),
	(80, '7 Sugar Lane', 1965, '2022-03-09 00:00:00.000000', '2001-05-05', 'Arin Woolfoot', 1989, NULL, 'yzXrSP1SraCg', 2, '2022-03-09 00:00:00.000000', 'awoolfoot27', 12),
	(81, '4664 Helena Court', 1988, '2022-03-09 00:00:00.000000', '2004-12-31', 'Sari Hughlock', 2009, NULL, 'ZYewE7', 3, '2022-03-09 00:00:00.000000', 'shughlock28', 7),
	(82, '867 Emmet Circle', 1985, '2022-03-09 00:00:00.000000', '2000-12-30', 'Rebeka O\'Towey', 2012, NULL, 'MtNyU21IQE9', 1, '2022-03-09 00:00:00.000000', 'rotowey29', 9),
	(83, '96 Eagan Road', 2006, '2022-03-09 00:00:00.000000', '2003-09-03', 'Berkley Cubberley', 2006, NULL, 'HXpehgaUGUV', 3, '2022-03-09 00:00:00.000000', 'bcubberley2a', 12),
	(84, '123 Sycamore Road', 2004, '2022-03-09 00:00:00.000000', '2000-07-17', 'Sue Summerill', 2001, NULL, '7xXH8N', 2, '2022-03-09 00:00:00.000000', 'ssummerill2b', 11),
	(85, '78222 Riverside Parkway', 1996, '2022-03-09 00:00:00.000000', '2006-03-27', 'Cullen Camilleri', 2009, NULL, '4eVIHXp', 3, '2022-03-09 00:00:00.000000', 'ccamilleri2c', 1),
	(86, '09583 Ramsey Street', 2002, '2022-03-09 00:00:00.000000', '2009-02-03', 'Abagael Lattey', 2010, NULL, '9arGiFE', 2, '2022-03-09 00:00:00.000000', 'alattey2d', 12),
	(87, '1591 Village Green Place', 1993, '2022-03-09 00:00:00.000000', '2009-09-13', 'Kennett Chaudret', 2005, NULL, 'SPrRhZ', 1, '2022-03-09 00:00:00.000000', 'kchaudret2e', 1),
	(88, '9 Prentice Circle', 2008, '2022-03-09 00:00:00.000000', '2007-05-13', 'Germayne Honnan', 2007, NULL, 'TNq5Wv8', 2, '2022-03-09 00:00:00.000000', 'ghonnan2f', 7),
	(89, '1272 5th Court', 2012, '2022-03-09 00:00:00.000000', '2001-12-18', 'Caspar Dufer', 2001, NULL, 'BiXlaGH3J7U', 2, '2022-03-09 00:00:00.000000', 'cdufer2g', 8),
	(90, '91 Barby Junction', 2000, '2022-03-09 00:00:00.000000', '2008-02-16', 'Paddy Dunkerton', 2008, NULL, 'yjQJehS', 2, '2022-03-09 00:00:00.000000', 'pdunkerton2h', 2),
	(91, '442 Oakridge Way', 1984, '2022-03-09 00:00:00.000000', '2006-09-10', 'Tyler Stiegars', 1998, NULL, 'c7lCxn6stL', 3, '2022-03-09 00:00:00.000000', 'tstiegars2i', 10),
	(92, '0798 Stuart Alley', 2006, '2022-03-09 00:00:00.000000', '2003-02-24', 'Gabriele Szubert', 1992, NULL, 'Pa3RX8u', 1, '2022-03-09 00:00:00.000000', 'gszubert2j', 11),
	(93, '4169 Sheridan Road', 2004, '2022-03-09 00:00:00.000000', '2005-10-20', 'Mamie Matzl', 1991, NULL, '71mTmxEMr', 3, '2022-03-09 00:00:00.000000', 'mmatzl2k', 6),
	(94, '49 Derek Trail', 2006, '2022-03-09 00:00:00.000000', '2002-05-10', 'Cindelyn Braznell', 2010, NULL, 'TaO8uC4NSpO', 2, '2022-03-09 00:00:00.000000', 'cbraznell2l', 2),
	(95, '6838 Rusk Pass', 2007, '2022-03-09 00:00:00.000000', '2007-06-08', 'Pembroke Jaycock', 1995, NULL, 'IHjZmu8aH', 2, '2022-03-09 00:00:00.000000', 'pjaycock2m', 12),
	(96, '25 Clemons Park', 2002, '2022-03-09 00:00:00.000000', '2003-04-11', 'Daisi Dwerryhouse', 1991, NULL, 'lBw7sc1f', 1, '2022-03-09 00:00:00.000000', 'ddwerryhouse2n', 2),
	(97, '4 Monument Place', 2010, '2022-03-09 00:00:00.000000', '2008-06-07', 'Pincas Dewitt', 2011, NULL, 'nZPhWRnyvc', 3, '2022-03-09 00:00:00.000000', 'pdewitt2o', 3),
	(98, '0569 Kenwood Hill', 1990, '2022-03-09 00:00:00.000000', '2007-09-24', 'Annabel Conyer', 1998, NULL, 'bRWNEkDI2P', 3, '2022-03-09 00:00:00.000000', 'aconyer2p', 6),
	(99, '442 Rieder Avenue', 2007, '2022-03-09 00:00:00.000000', '2005-04-05', 'Bogey Stratz', 1989, NULL, '07rd5MzghxpF', 2, '2022-03-09 00:00:00.000000', 'bstratz2q', 4),
	(100, '1 Annamark Pass', 1994, '2022-03-09 00:00:00.000000', '2001-05-09', 'Weylin Robardey', 2012, NULL, 'wkfCxnMG', 2, '2022-03-09 00:00:00.000000', 'wrobardey2r', 11),
	(101, '36500 Springs Way', 2000, '2022-03-09 00:00:00.000000', '2007-09-04', 'Barbaraanne Whipple', 2002, NULL, 'OmARZPyRq1b', 3, '2022-03-09 00:00:00.000000', 'bwhipple2s', 3),
	(102, '08547 Mallard Place', 2012, '2022-03-09 00:00:00.000000', '2003-07-27', 'Idell Heis', 1992, NULL, 'QyY9WZ7', 2, '2022-03-09 00:00:00.000000', 'iheis2t', 7),
	(103, '45950 Menomonie Trail', 2005, '2022-03-09 00:00:00.000000', '2002-01-03', 'Hymie Ash', 1989, NULL, 'LedJIxNjTwkD', 1, '2022-03-09 00:00:00.000000', 'hash2u', 3),
	(104, '6579 Bunker Hill Parkway', 2009, '2022-03-09 00:00:00.000000', '2006-10-18', 'Muire Blindt', 2010, NULL, 'q2VrNsRypN', 2, '2022-03-09 00:00:00.000000', 'mblindt2v', 8),
	(105, '22387 Paget Avenue', 2010, '2022-03-09 00:00:00.000000', '2001-09-05', 'Nathalie Ainslie', 1995, NULL, 'w42G6uc', 1, '2022-03-09 00:00:00.000000', 'nainslie2w', 9),
	(106, '14703 7th Crossing', 2003, '2022-03-09 00:00:00.000000', '2000-12-19', 'Rosabelle Dukesbury', 2009, NULL, 'KBvAKWAfaTNd', 2, '2022-03-09 00:00:00.000000', 'rdukesbury2x', 4),
	(107, '00409 Union Terrace', 2006, '2022-03-09 00:00:00.000000', '2002-01-14', 'Aylmar Westcarr', 1987, NULL, 'EunyO4G48p', 1, '2022-03-09 00:00:00.000000', 'awestcarr2y', 5),
	(108, '70982 Cody Point', 1997, '2022-03-09 00:00:00.000000', '2001-11-17', 'Genni Haws', 1988, NULL, 'BLOsVst14qhf', 3, '2022-03-09 00:00:00.000000', 'ghaws2z', 3),
	(109, '559 Dayton Way', 2011, '2022-03-09 00:00:00.000000', '2006-10-04', 'Moselle Mattiussi', 2006, NULL, '105x0ch', 2, '2022-03-09 00:00:00.000000', 'mmattiussi30', 6),
	(110, '807 Sunbrook Circle', 2008, '2022-03-09 00:00:00.000000', '2003-04-17', 'Karolina Sirrell', 2012, NULL, 'st7nzKFX', 1, '2022-03-09 00:00:00.000000', 'ksirrell31', 5),
	(111, '0 Muir Lane', 2005, '2022-03-09 00:00:00.000000', '2000-08-11', 'Alys Georgeou', 2011, NULL, 'gQHlnG6qPfx', 1, '2022-03-09 00:00:00.000000', 'ageorgeou32', 12),
	(112, '06780 Donald Parkway', 2011, '2022-03-09 00:00:00.000000', '2007-07-11', 'Beaufort Flewett', 2005, NULL, 'm5pKYEJrIx', 3, '2022-03-09 00:00:00.000000', 'bflewett33', 7),
	(113, '1 Sycamore Point', 2004, '2022-03-09 00:00:00.000000', '2007-05-20', 'Temp d\'Escoffier', 2005, NULL, 'dP8K4wW', 2, '2022-03-09 00:00:00.000000', 'tdescoffier34', 3),
	(114, '7160 Center Avenue', 2008, '2022-03-09 00:00:00.000000', '2000-08-21', 'Basile Treasure', 2007, NULL, '7Oq4M4g1', 1, '2022-03-09 00:00:00.000000', 'btreasure35', 10),
	(115, '09037 Brown Drive', 2003, '2022-03-09 00:00:00.000000', '2002-08-04', 'Olia Wilcockes', 2009, NULL, 'M5bpqt', 1, '2022-03-09 00:00:00.000000', 'owilcockes36', 6),
	(116, '7293 Saint Paul Plaza', 2000, '2022-03-09 00:00:00.000000', '2008-11-07', 'Falito Bulter', 2000, NULL, 'pQGfubPy1PjN', 2, '2022-03-09 00:00:00.000000', 'fbulter37', 3),
	(117, '09 Mitchell Center', 2012, '2022-03-09 00:00:00.000000', '2005-03-03', 'Jobina Healings', 2004, NULL, '0lyslhei', 1, '2022-03-09 00:00:00.000000', 'jhealings38', 1),
	(118, '100 Anniversary Way', 1996, '2022-03-09 00:00:00.000000', '2002-02-06', 'Emmalee Spofforth', 1999, NULL, 'fbszj0qAXcTw', 3, '2022-03-09 00:00:00.000000', 'espofforth39', 10),
	(119, '571 2nd Pass', 2007, '2022-03-09 00:00:00.000000', '2005-07-07', 'Clerkclaude Buxey', 2003, NULL, 'oQ8GqwlLFL', 2, '2022-03-09 00:00:00.000000', 'cbuxey3a', 12),
	(120, '472 Packers Terrace', 2003, '2022-03-09 00:00:00.000000', '2004-04-10', 'Darbee Cremin', 2001, NULL, 'KHxa33o', 2, '2022-03-09 00:00:00.000000', 'dcremin3b', 10),
	(121, '45154 Carioca Parkway', 2001, '2022-03-09 00:00:00.000000', '2001-06-28', 'Janean Tomlinson', 2011, NULL, 'joY2Xw3vfiri', 1, '2022-03-09 00:00:00.000000', 'jtomlinson3c', 4),
	(122, '165 Bluejay Avenue', 2004, '2022-03-09 00:00:00.000000', '2004-02-23', 'Morgen Plowes', 2006, NULL, 'ofisPGQJn3s', 1, '2022-03-09 00:00:00.000000', 'mplowes3d', 6),
	(123, '7 Rockefeller Center', 2003, '2022-03-09 00:00:00.000000', '2006-08-18', 'Tyler Tully', 1993, NULL, 'h00IYs', 3, '2022-03-09 00:00:00.000000', 'ttully3e', 3),
	(124, '337 Novick Crossing', 2008, '2022-03-09 00:00:00.000000', '2005-07-08', 'Briana Manser', 1971, NULL, 'tAmRKIH', 3, '2022-03-09 00:00:00.000000', 'bmanser3f', 11),
	(125, '9 Karstens Circle', 2007, '2022-03-09 00:00:00.000000', '2009-03-30', 'Ardra Shieber', 1997, NULL, 'EfiQ2rV', 2, '2022-03-09 00:00:00.000000', 'ashieber3g', 9),
	(126, '8 Monterey Avenue', 2000, '2022-03-09 00:00:00.000000', '2001-01-11', 'Desdemona Willford', 2007, NULL, 'bYsuDKx4', 2, '2022-03-09 00:00:00.000000', 'dwillford3h', 3),
	(127, '01117 Darwin Park', 1998, '2022-03-09 00:00:00.000000', '2001-07-03', 'Barnett Lill', 1997, NULL, 'Mk2YkHQAl9', 3, '2022-03-09 00:00:00.000000', 'blill3i', 8),
	(128, '95 Morningstar Hill', 1994, '2022-03-09 00:00:00.000000', '2008-03-17', 'Norman Juster', 1997, NULL, 'jToZkyg', 2, '2022-03-09 00:00:00.000000', 'njuster3j', 10),
	(129, '367 Buell Crossing', 2008, '2022-03-09 00:00:00.000000', '2003-06-10', 'Marcia Wymer', 2006, NULL, 'cqVgcl2LJoB', 3, '2022-03-09 00:00:00.000000', 'mwymer3k', 1),
	(130, '96722 Raven Place', 1987, '2022-03-09 00:00:00.000000', '2001-08-29', 'Kora Bertlin', 1985, NULL, 'wx0f0Y', 3, '2022-03-09 00:00:00.000000', 'kbertlin3l', 6),
	(131, '51 Hoffman Lane', 1995, '2022-03-09 00:00:00.000000', '2008-05-06', 'Burnard Whetland', 1967, NULL, '8QMhwO1zwO', 2, '2022-03-09 00:00:00.000000', 'bwhetland3m', 12),
	(132, '1714 Everett Point', 2009, '2022-03-09 00:00:00.000000', '2006-01-09', 'Kore Cicccitti', 2002, NULL, 'OmyfmEi9h5', 1, '2022-03-09 00:00:00.000000', 'kcicccitti3n', 2),
	(133, '6 Banding Drive', 2010, '2022-03-09 00:00:00.000000', '2008-03-07', 'Pembroke Cantwell', 1993, NULL, 'QFgD10V', 3, '2022-03-09 00:00:00.000000', 'pcantwell3o', 5),
	(134, '99904 Mifflin Crossing', 1995, '2022-03-09 00:00:00.000000', '2000-05-04', 'Curry Coche', 2010, NULL, '18PYHD', 2, '2022-03-09 00:00:00.000000', 'ccoche3p', 9),
	(135, '826 Lakewood Park', 2009, '2022-03-09 00:00:00.000000', '2003-01-10', 'Tamarra Livoir', 2009, NULL, '2BybWa3T', 3, '2022-03-09 00:00:00.000000', 'tlivoir3q', 3),
	(136, '6876 Browning Point', 2002, '2022-03-09 00:00:00.000000', '2002-05-15', 'Jermayne Trayling', 1989, NULL, '2KpMGGW7q962', 3, '2022-03-09 00:00:00.000000', 'jtrayling3r', 10),
	(137, '693 Delaware Drive', 1992, '2022-03-09 00:00:00.000000', '2009-03-18', 'Tallulah Higgen', 2007, NULL, 'KNrOcvYj', 2, '2022-03-09 00:00:00.000000', 'thiggen3s', 8),
	(138, '14442 Portage Crossing', 1993, '2022-03-09 00:00:00.000000', '2002-04-30', 'Benedikta Zammett', 1992, NULL, 'O5KQBg3bUki', 3, '2022-03-09 00:00:00.000000', 'bzammett3t', 11),
	(139, '5 Transport Road', 2003, '2022-03-09 00:00:00.000000', '2007-07-14', 'Jacklyn Mansford', 2012, NULL, '8nzHP7TICxq', 2, '2022-03-09 00:00:00.000000', 'jmansford3u', 7),
	(140, '4157 Leroy Drive', 1996, '2022-03-09 00:00:00.000000', '2000-07-06', 'Reynold Goodge', 1994, NULL, 'uTDjidaRJ9', 2, '2022-03-09 00:00:00.000000', 'rgoodge3v', 4),
	(141, '121 Village Trail', 1993, '2022-03-09 00:00:00.000000', '2005-10-08', 'Pegeen Mainson', 2011, NULL, '57V8M6Q', 3, '2022-03-09 00:00:00.000000', 'pmainson3w', 4),
	(142, '2369 Dennis Way', 2012, '2022-03-09 00:00:00.000000', '2007-04-28', 'Tressa Gowrich', 2011, NULL, 'VmclamG4', 2, '2022-03-09 00:00:00.000000', 'tgowrich3x', 3),
	(143, '836 Walton Plaza', 2006, '2022-03-09 00:00:00.000000', '2008-12-12', 'Wiatt Calderon', 1992, NULL, 'gJd1WVzleqN', 1, '2022-03-09 00:00:00.000000', 'wcalderon3y', 1),
	(144, '6 Elka Center', 2007, '2022-03-09 00:00:00.000000', '2003-11-06', 'Yancy Thurby', 2007, NULL, '5ovAYRdnvE', 2, '2022-03-09 00:00:00.000000', 'ythurby3z', 3),
	(145, '5545 Dawn Street', 1997, '2022-03-09 00:00:00.000000', '2007-08-03', 'Elana Matchitt', 2012, NULL, 'm84xrgYxR9', 3, '2022-03-09 00:00:00.000000', 'ematchitt40', 10),
	(146, '365 Darwin Way', 2006, '2022-03-09 00:00:00.000000', '2006-12-03', 'Thorndike Longmaid', 1989, NULL, 'JMLLP6sOGT', 3, '2022-03-09 00:00:00.000000', 'tlongmaid41', 5),
	(147, '5589 Rigney Lane', 1991, '2022-03-09 00:00:00.000000', '2001-10-18', 'Arnold Kynder', 1999, NULL, '0gGqSvIX', 2, '2022-03-09 00:00:00.000000', 'akynder42', 7),
	(148, '887 Scoville Parkway', 2001, '2022-03-09 00:00:00.000000', '2007-04-08', 'Deerdre Osbaldeston', 1994, NULL, 'wEQ5e0d6', 2, '2022-03-09 00:00:00.000000', 'dosbaldeston43', 6),
	(149, '7 Grim Place', 2010, '2022-03-09 00:00:00.000000', '2003-04-19', 'Pacorro MacKaile', 1997, NULL, '9GZCqHAMsnTN', 2, '2022-03-09 00:00:00.000000', 'pmackaile44', 7),
	(150, '3 Washington Plaza', 2010, '2022-03-09 00:00:00.000000', '2003-10-01', 'Celestyna Wendover', 2003, NULL, 'osedOgz', 3, '2022-03-09 00:00:00.000000', 'cwendover45', 3),
	(151, '876 Raven Avenue', 2013, '2022-03-09 00:00:00.000000', '2003-10-24', 'Bertie Brazur', 2005, NULL, 'nEVMbaWF0w', 2, '2022-03-09 00:00:00.000000', 'bbrazur46', 9),
	(152, '18369 Thackeray Avenue', 1994, '2022-03-09 00:00:00.000000', '2004-08-24', 'Vida Primmer', 1986, NULL, '3BQ5a6Te', 3, '2022-03-09 00:00:00.000000', 'vprimmer47', 4),
	(153, '53 Melrose Parkway', 2005, '2022-03-09 00:00:00.000000', '2005-07-01', 'Haskell Maeer', 1980, NULL, 'zYw9v6iKY', 1, '2022-03-09 00:00:00.000000', 'hmaeer48', 11),
	(154, '9769 Mendota Park', 2012, '2022-03-09 00:00:00.000000', '2008-04-25', 'Saunder Juppe', 1987, NULL, 'S1qprTZno93', 2, '2022-03-09 00:00:00.000000', 'sjuppe49', 12),
	(155, '1346 Myrtle Park', 1984, '2022-03-09 00:00:00.000000', '2002-06-07', 'Evvie Broszkiewicz', 1992, NULL, '3QRxgEHHMW', 1, '2022-03-09 00:00:00.000000', 'ebroszkiewicz4a', 10),
	(156, '68582 Dixon Road', 2006, '2022-03-09 00:00:00.000000', '2002-03-13', 'Lucilia Klimp', 1993, NULL, 'O0frMNl', 2, '2022-03-09 00:00:00.000000', 'lklimp4b', 9),
	(157, '9 Brentwood Way', 2010, '2022-03-09 00:00:00.000000', '2007-05-24', 'Rainer Le Page', 2012, NULL, '3NGMzemn', 3, '2022-03-09 00:00:00.000000', 'rle4c', 9),
	(158, '8597 Coleman Alley', 2007, '2022-03-09 00:00:00.000000', '2006-06-07', 'Anna-diana Lyle', 2007, NULL, 'OjZIdCnScHS', 2, '2022-03-09 00:00:00.000000', 'alyle4d', 12),
	(159, '159 North Drive', 2007, '2022-03-09 00:00:00.000000', '2005-06-01', 'Liz Pietrowicz', 2000, NULL, 'NsaxReUiR', 2, '2022-03-09 00:00:00.000000', 'lpietrowicz4e', 7),
	(160, '9181 Pond Drive', 2000, '2022-03-09 00:00:00.000000', '2009-04-03', 'Lexine Mower', 2009, NULL, 'LUAQFv', 3, '2022-03-09 00:00:00.000000', 'lmower4f', 6),
	(161, '0436 Buell Plaza', 2002, '2022-03-09 00:00:00.000000', '2002-04-12', 'Tait Ceillier', 1970, NULL, 'OiW5szKLDTgB', 3, '2022-03-09 00:00:00.000000', 'tceillier4g', 2),
	(162, '8 Maple Park', 2011, '2022-03-09 00:00:00.000000', '2003-03-27', 'Vale Spera', 1992, NULL, '45qmX7q', 2, '2022-03-09 00:00:00.000000', 'vspera4h', 12),
	(163, '209 Sundown Drive', 1984, '2022-03-09 00:00:00.000000', '2000-11-07', 'Mal Tapp', 2008, NULL, 'KsfbTZYz', 3, '2022-03-09 00:00:00.000000', 'mtapp4i', 5),
	(164, '19734 Fulton Court', 1998, '2022-03-09 00:00:00.000000', '2005-08-27', 'Rosie Cessford', 1993, NULL, 'jbInOT', 2, '2022-03-09 00:00:00.000000', 'rcessford4j', 7),
	(165, '66768 Prairie Rose Circle', 1996, '2022-03-09 00:00:00.000000', '2000-07-15', 'Cyrill Chafer', 1990, NULL, 'ErJwyfYF', 1, '2022-03-09 00:00:00.000000', 'cchafer4k', 4),
	(166, '5 Ridgeway Street', 1986, '2022-03-09 00:00:00.000000', '2009-12-27', 'Pierre Bostick', 2009, NULL, 'oiXMaRw5b', 3, '2022-03-09 00:00:00.000000', 'pbostick4l', 1),
	(167, '293 Warner Plaza', 2003, '2022-03-09 00:00:00.000000', '2000-08-01', 'Darby Redfield', 2009, NULL, 'Fsl65Uc6F9', 1, '2022-03-09 00:00:00.000000', 'dredfield4m', 8),
	(168, '1428 Mcguire Alley', 1992, '2022-03-09 00:00:00.000000', '2002-12-28', 'Noreen Patching', 2005, NULL, 'oBbEiA4', 3, '2022-03-09 00:00:00.000000', 'npatching4n', 4),
	(169, '21321 Caliangt Terrace', 2000, '2022-03-09 00:00:00.000000', '2008-03-01', 'Allyson Cuddihy', 2012, NULL, 'A1xMJB', 2, '2022-03-09 00:00:00.000000', 'acuddihy4o', 6),
	(170, '0 Kings Terrace', 2007, '2022-03-09 00:00:00.000000', '2004-06-17', 'Devora Merwood', 1996, NULL, 'TXxKGUupVSn', 1, '2022-03-09 00:00:00.000000', 'dmerwood4p', 7),
	(171, '899 Schmedeman Center', 2004, '2022-03-09 00:00:00.000000', '2005-04-04', 'Layton Foran', 2005, NULL, 'quus9ZQXr', 1, '2022-03-09 00:00:00.000000', 'lforan4q', 1),
	(172, '5211 Amoth Park', 2002, '2022-03-09 00:00:00.000000', '2005-01-20', 'Tani Storr', 2002, NULL, 'IUm0NWxra', 3, '2022-03-09 00:00:00.000000', 'tstorr4r', 12),
	(173, '40 Truax Junction', 1997, '2022-03-09 00:00:00.000000', '2005-11-24', 'Livia MacDermand', 2012, NULL, 'WUjIt8gS', 2, '2022-03-09 00:00:00.000000', 'lmacdermand4s', 6),
	(174, '202 Namekagon Avenue', 1992, '2022-03-09 00:00:00.000000', '2007-07-08', 'Christyna Hasard', 1988, NULL, 'QRdsRsS', 3, '2022-03-09 00:00:00.000000', 'chasard4t', 6),
	(175, '09 Crest Line Trail', 2012, '2022-03-09 00:00:00.000000', '2006-05-21', 'Washington MacTrustie', 1988, NULL, 'rdezdw6D', 2, '2022-03-09 00:00:00.000000', 'wmactrustie4u', 11),
	(176, '86 Manitowish Avenue', 2012, '2022-03-09 00:00:00.000000', '2006-11-29', 'Harriot Chitham', 1998, NULL, 'stllC3D7', 3, '2022-03-09 00:00:00.000000', 'hchitham4v', 6),
	(177, '37 Utah Place', 1992, '2022-03-09 00:00:00.000000', '2007-02-20', 'Christyna Hallworth', 1996, NULL, 'eG39Xfdogvw', 1, '2022-03-09 00:00:00.000000', 'challworth4w', 8),
	(178, '1 Maple Wood Parkway', 2008, '2022-03-09 00:00:00.000000', '2003-10-08', 'Tonye Cockcroft', 2001, NULL, 'zScIf4geUW', 2, '2022-03-09 00:00:00.000000', 'tcockcroft4x', 11),
	(179, '14598 Iowa Point', 2004, '2022-03-09 00:00:00.000000', '2003-04-07', 'Arlyn Abernethy', 2012, NULL, 'mhrY2vF', 1, '2022-03-09 00:00:00.000000', 'aabernethy4y', 1),
	(180, '708 Manufacturers Pass', 2007, '2022-03-09 00:00:00.000000', '2001-04-13', 'Gunilla Barehead', 2003, NULL, 'X0tNgmzxofM', 3, '2022-03-09 00:00:00.000000', 'gbarehead4z', 3),
	(181, '8876 Steensland Drive', 2012, '2022-03-09 00:00:00.000000', '2000-11-21', 'Gaby Grovier', 1993, NULL, 'W2ZBAWXVuK', 3, '2022-03-09 00:00:00.000000', 'ggrovier50', 12),
	(182, '11678 Hanson Crossing', 2007, '2022-03-09 00:00:00.000000', '2009-02-02', 'Tracey Seabright', 2004, NULL, 'QHOCQ8ttTov', 3, '2022-03-09 00:00:00.000000', 'tseabright51', 2),
	(183, '644 Tennessee Trail', 2009, '2022-03-09 00:00:00.000000', '2000-12-31', 'Stanwood Durrand', 1999, NULL, 'kNqlWL', 3, '2022-03-09 00:00:00.000000', 'sdurrand52', 12),
	(184, '62 Granby Parkway', 2011, '2022-03-09 00:00:00.000000', '2009-08-31', 'Christean Ricardin', 2007, NULL, 'QzuTyhQj', 2, '2022-03-09 00:00:00.000000', 'cricardin53', 2),
	(185, '33 Crownhardt Avenue', 2009, '2022-03-09 00:00:00.000000', '2005-03-01', 'Meara Addionizio', 1992, NULL, 'KT0qQrwZPJca', 1, '2022-03-09 00:00:00.000000', 'maddionizio54', 11),
	(186, '04 Duke Park', 2012, '2022-03-09 00:00:00.000000', '2002-01-26', 'Madelon Mansuer', 1992, NULL, '1bfise7FQq', 2, '2022-03-09 00:00:00.000000', 'mmansuer55', 7),
	(187, '7 Knutson Circle', 2005, '2022-03-09 00:00:00.000000', '2008-04-30', 'Tris Wallsam', 2009, NULL, 'fdTJQVX', 3, '2022-03-09 00:00:00.000000', 'twallsam56', 11),
	(188, '2 Jay Circle', 2008, '2022-03-09 00:00:00.000000', '2006-01-10', 'Dewie Cutmere', 2008, NULL, 'VuyOSAKXGA', 2, '2022-03-09 00:00:00.000000', 'dcutmere57', 10),
	(189, '1 Ridgeview Park', 1992, '2022-03-09 00:00:00.000000', '2005-07-17', 'Shelby Fowlie', 2008, NULL, 'jy1Gl4', 1, '2022-03-09 00:00:00.000000', 'sfowlie58', 1),
	(190, '102 Atwood Street', 2005, '2022-03-09 00:00:00.000000', '2006-03-03', 'Twyla Letham', 2008, NULL, 'w8t7xMhisJD', 3, '2022-03-09 00:00:00.000000', 'tletham59', 8),
	(191, '33962 Green Plaza', 2001, '2022-03-09 00:00:00.000000', '2006-08-19', 'Dorey Langford', 2008, NULL, 'iQKfyDcM', 2, '2022-03-09 00:00:00.000000', 'dlangford5a', 3),
	(192, '538 Haas Way', 1985, '2022-03-09 00:00:00.000000', '2002-09-04', 'Maggi Jurczak', 2011, NULL, '3Y81XczG', 3, '2022-03-09 00:00:00.000000', 'mjurczak5b', 6),
	(193, '07565 Sachtjen Circle', 2006, '2022-03-09 00:00:00.000000', '2002-12-23', 'Karlyn Nobriga', 1987, NULL, '9krQI6', 2, '2022-03-09 00:00:00.000000', 'knobriga5c', 12),
	(194, '46 Texas Junction', 2002, '2022-03-09 00:00:00.000000', '2003-12-23', 'Tucky Harvey', 1999, NULL, 'UE44CsOI55Z', 2, '2022-03-09 00:00:00.000000', 'tharvey5d', 10),
	(195, '62 Carberry Crossing', 2007, '2022-03-09 00:00:00.000000', '2009-09-06', 'Laural Jacqueminot', 1995, NULL, 'IkSpUomZ2jJ', 3, '2022-03-09 00:00:00.000000', 'ljacqueminot5e', 8),
	(196, '6443 Prairieview Crossing', 2002, '2022-03-09 00:00:00.000000', '2008-08-05', 'Noah De Santos', 2001, NULL, '3MmvBgCE', 2, '2022-03-09 00:00:00.000000', 'nde5f', 2),
	(197, '12 Fieldstone Hill', 2010, '2022-03-09 00:00:00.000000', '2000-10-08', 'Pavel Francklin', 2008, NULL, 'iQ9jCD5X', 1, '2022-03-09 00:00:00.000000', 'pfrancklin5g', 3),
	(198, '16374 Melby Parkway', 1985, '2022-03-09 00:00:00.000000', '2002-04-29', 'Rochette Kik', 2012, NULL, 'AEJmycedcIju', 2, '2022-03-09 00:00:00.000000', 'rkik5h', 5),
	(199, '104 Hanover Terrace', 2011, '2022-03-09 00:00:00.000000', '2004-07-18', 'Leila Lotte', 1987, NULL, '2vwSqVNibqr8', 3, '2022-03-09 00:00:00.000000', 'llotte5i', 9),
	(200, '02 Oak Valley Drive', 2000, '2022-03-09 00:00:00.000000', '2006-05-10', 'Traver Sauven', 2001, NULL, 'cYiAAibImsrE', 2, '2022-03-09 00:00:00.000000', 'tsauven5j', 11),
	(201, '3 Fremont Terrace', 2006, '2022-03-09 00:00:00.000000', '2002-11-30', 'Terese Mioni', 2006, NULL, 'v7cW0J5vcu', 1, '2022-03-09 00:00:00.000000', 'tmioni5k', 11),
	(202, '2 Green Ridge Hill', 2011, '2022-03-09 00:00:00.000000', '2001-08-24', 'Caril Blaiklock', 2007, NULL, 'BCRWkcy8tD3', 3, '2022-03-09 00:00:00.000000', 'cblaiklock5l', 3),
	(203, '75333 Waxwing Parkway', 1995, '2022-03-09 00:00:00.000000', '2001-03-02', 'Kristy Clooney', 2002, NULL, 'BCqMFZCZ', 3, '2022-03-09 00:00:00.000000', 'kclooney5m', 1),
	(204, '332 Veith Terrace', 2008, '2022-03-09 00:00:00.000000', '2005-10-11', 'Antonius Tydd', 2001, NULL, 'hF7p8EGF0u', 2, '2022-03-09 00:00:00.000000', 'atydd5n', 1),
	(205, '541 High Crossing Road', 1996, '2022-03-09 00:00:00.000000', '2001-06-23', 'Wat Prattin', 1997, NULL, 'M0lYi5', 1, '2022-03-09 00:00:00.000000', 'wprattin5o', 8),
	(206, '8 Harper Park', 2003, '2022-03-09 00:00:00.000000', '2003-04-13', 'Torin Burman', 2010, NULL, 'qLdh0a6oH', 2, '2022-03-09 00:00:00.000000', 'tburman5p', 6),
	(207, '8863 Lillian Court', 1998, '2022-03-09 00:00:00.000000', '2000-12-19', 'Taber McNeilley', 2008, NULL, 'nBN4sb96E', 2, '2022-03-09 00:00:00.000000', 'tmcneilley5q', 6),
	(208, '0652 Fuller Center', 2007, '2022-03-09 00:00:00.000000', '2002-06-29', 'Adolpho Kilgrove', 1989, NULL, 'XlETwmTU', 3, '2022-03-09 00:00:00.000000', 'akilgrove5r', 5),
	(209, '35 Dawn Plaza', 1987, '2022-03-09 00:00:00.000000', '2009-12-22', 'Sheryl Boake', 2001, NULL, 'nXgPbUkuLuV', 3, '2022-03-09 00:00:00.000000', 'sboake5s', 10),
	(210, '7 Dapin Junction', 2012, '2022-03-09 00:00:00.000000', '2004-01-09', 'Glyn Schoenfisch', 1986, NULL, 'FQzykX', 3, '2022-03-09 00:00:00.000000', 'gschoenfisch5t', 8),
	(211, '047 Carberry Terrace', 2010, '2022-03-09 00:00:00.000000', '2001-04-29', 'Romona Cafferky', 2001, NULL, 'BuBEiBx31', 1, '2022-03-09 00:00:00.000000', 'rcafferky5u', 2),
	(212, '44171 Village Trail', 2012, '2022-03-09 00:00:00.000000', '2008-11-10', 'Melba Moles', 2004, NULL, 'dEcs26m', 1, '2022-03-09 00:00:00.000000', 'mmoles5v', 9),
	(213, '581 Debra Avenue', 2007, '2022-03-09 00:00:00.000000', '2002-05-13', 'Jo-ann Klimkowski', 2006, NULL, '6lmwFUsJhZc', 1, '2022-03-09 00:00:00.000000', 'jklimkowski5w', 7),
	(214, '9 Main Center', 1988, '2022-03-09 00:00:00.000000', '2007-06-06', 'Cedric Perulli', 2009, NULL, 'pixUZtaA', 1, '2022-03-09 00:00:00.000000', 'cperulli5x', 1),
	(215, '98 Nelson Circle', 2002, '2022-03-09 00:00:00.000000', '2005-11-19', 'Garfield Okenfold', 1996, NULL, 'GClcJQQjK82H', 1, '2022-03-09 00:00:00.000000', 'gokenfold5y', 12),
	(216, '5683 Calypso Center', 2007, '2022-03-09 00:00:00.000000', '2007-02-07', 'Jasper Blaschke', 2013, NULL, '7pOUA6kxQu', 3, '2022-03-09 00:00:00.000000', 'jblaschke5z', 5),
	(217, '7 Westerfield Place', 1992, '2022-03-09 00:00:00.000000', '2004-11-24', 'Ashli Bjerkan', 1998, NULL, 'V6SYN152Yy', 1, '2022-03-09 00:00:00.000000', 'abjerkan60', 5),
	(218, '93 Brentwood Point', 1999, '2022-03-09 00:00:00.000000', '2000-04-11', 'Ari McLagan', 1985, NULL, 'T9SVhV', 2, '2022-03-09 00:00:00.000000', 'amclagan61', 9),
	(219, '0837 Fallview Parkway', 2004, '2022-03-09 00:00:00.000000', '2007-07-31', 'Ingelbert MacDuff', 1996, NULL, 'B49eb1M2FI', 1, '2022-03-09 00:00:00.000000', 'imacduff62', 3),
	(220, '3802 Hudson Park', 1986, '2022-03-09 00:00:00.000000', '2001-10-27', 'Hamnet Penhaligon', 1995, NULL, 'JXTHYk', 1, '2022-03-09 00:00:00.000000', 'hpenhaligon63', 6),
	(221, '0117 Grover Crossing', 1995, '2022-03-09 00:00:00.000000', '2002-01-31', 'Ardeen De Simoni', 1998, NULL, 'BhMSyqHcs', 1, '2022-03-09 00:00:00.000000', 'ade64', 7),
	(222, '1595 Novick Parkway', 1988, '2022-03-09 00:00:00.000000', '2008-05-19', 'Jeannie Rudeyeard', 1991, NULL, 'wmMSe39oJ', 3, '2022-03-09 00:00:00.000000', 'jrudeyeard65', 9),
	(223, '0 Spohn Trail', 1996, '2022-03-09 00:00:00.000000', '2004-10-18', 'Deerdre Grain', 1998, NULL, 'z6zJ0p7xN', 1, '2022-03-09 00:00:00.000000', 'dgrain66', 7),
	(224, '11 American Center', 2012, '2022-03-09 00:00:00.000000', '2002-12-15', 'Thomas Cranshaw', 1994, NULL, 'VWCKRrksjHv', 3, '2022-03-09 00:00:00.000000', 'tcranshaw67', 6),
	(225, '21 Oxford Road', 2007, '2022-03-09 00:00:00.000000', '2002-06-05', 'Eugenius Blood', 2004, NULL, 'Gv7kWba', 1, '2022-03-09 00:00:00.000000', 'eblood68', 11),
	(226, '9 Caliangt Terrace', 1999, '2022-03-09 00:00:00.000000', '2001-02-28', 'Burty Tamlett', 2005, NULL, 'xMtNSr', 3, '2022-03-09 00:00:00.000000', 'btamlett69', 5),
	(227, '6296 Texas Place', 1999, '2022-03-09 00:00:00.000000', '2002-03-08', 'Dwight Nannini', 2004, NULL, 'QZrgqqosUYP', 2, '2022-03-09 00:00:00.000000', 'dnannini6a', 9),
	(228, '675 Morrow Plaza', 2003, '2022-03-09 00:00:00.000000', '2000-09-04', 'Michell Hallagan', 1992, NULL, 'g8QRcmMuvVp', 3, '2022-03-09 00:00:00.000000', 'mhallagan6b', 4),
	(229, '4 Dapin Plaza', 2008, '2022-03-09 00:00:00.000000', '2002-06-22', 'Madelaine Fairlie', 2004, NULL, 'ebxrcpvEGV', 2, '2022-03-09 00:00:00.000000', 'mfairlie6c', 8),
	(230, '929 Esker Trail', 2012, '2022-03-09 00:00:00.000000', '2009-05-16', 'Gaspar Wonham', 2000, NULL, 'ATu4If', 3, '2022-03-09 00:00:00.000000', 'gwonham6d', 7),
	(231, '36 Old Gate Pass', 1996, '2022-03-09 00:00:00.000000', '2002-08-04', 'Rey Jarrad', 2011, NULL, 'bQCkTEJDFKD', 3, '2022-03-09 00:00:00.000000', 'rjarrad6e', 9),
	(232, '24 Buena Vista Way', 1997, '2022-03-09 00:00:00.000000', '2003-12-31', 'Bonnie Kitto', 1986, NULL, 'GKAKPwRK', 3, '2022-03-09 00:00:00.000000', 'bkitto6f', 3),
	(233, '5 Lawn Avenue', 1992, '2022-03-09 00:00:00.000000', '2004-04-28', 'Birch Carmo', 2012, NULL, '5VdWQdJriE01', 2, '2022-03-09 00:00:00.000000', 'bcarmo6g', 6),
	(234, '274 Quincy Point', 2008, '2022-03-09 00:00:00.000000', '2005-03-29', 'Lucio Mouatt', 1997, NULL, 'Cqr7cCX1j2eN', 1, '2022-03-09 00:00:00.000000', 'lmouatt6h', 11),
	(235, '22 Main Circle', 2011, '2022-03-09 00:00:00.000000', '2003-02-19', 'Sinclare Gerardot', 1996, NULL, 'PcJ2fj', 1, '2022-03-09 00:00:00.000000', 'sgerardot6i', 4),
	(236, '25 Arapahoe Junction', 1996, '2022-03-09 00:00:00.000000', '2001-10-22', 'Hailey Caret', 1995, NULL, 'TtdHbkgh2rU', 3, '2022-03-09 00:00:00.000000', 'hcaret6j', 12),
	(237, '55 Union Junction', 1992, '2022-03-09 00:00:00.000000', '2007-04-19', 'Sarita Bumpas', 2009, NULL, 'MYnSfvN5qk', 1, '2022-03-09 00:00:00.000000', 'sbumpas6k', 10),
	(238, '6039 Anhalt Street', 2012, '2022-03-09 00:00:00.000000', '2008-05-17', 'Conroy Perrat', 2003, NULL, 'eunZIY', 3, '2022-03-09 00:00:00.000000', 'cperrat6l', 8),
	(239, '1 Sullivan Street', 1995, '2022-03-09 00:00:00.000000', '2001-10-30', 'Esmaria Vaneschi', 2011, NULL, '1YCWCZsnq3Ia', 1, '2022-03-09 00:00:00.000000', 'evaneschi6m', 3),
	(240, '42820 Green Ridge Way', 2001, '2022-03-09 00:00:00.000000', '2000-05-18', 'Padget Hughs', 1995, NULL, 'faWFxVWAi', 1, '2022-03-09 00:00:00.000000', 'phughs6n', 6),
	(241, '6 Schiller Junction', 2005, '2022-03-09 00:00:00.000000', '2001-08-30', 'Modesta Hentze', 2006, NULL, 'I2vtJ2Eo', 2, '2022-03-09 00:00:00.000000', 'mhentze6o', 10),
	(242, '3 Brickson Park Center', 2002, '2022-03-09 00:00:00.000000', '2002-08-14', 'Tamiko Drissell', 2010, NULL, 'Giln29', 1, '2022-03-09 00:00:00.000000', 'tdrissell6p', 8),
	(243, '03 Clove Way', 2010, '2022-03-09 00:00:00.000000', '2009-05-20', 'Diego Newsham', 2000, NULL, 'Ifbo8fj5LNE', 3, '2022-03-09 00:00:00.000000', 'dnewsham6q', 8),
	(244, '838 Old Gate Center', 2010, '2022-03-09 00:00:00.000000', '2009-05-01', 'Leta Rafe', 1990, NULL, 'CMA4jO', 3, '2022-03-09 00:00:00.000000', 'lrafe6r', 10),
	(245, '3111 Victoria Junction', 2011, '2022-03-09 00:00:00.000000', '2001-06-23', 'Oswell Casseldine', 2001, NULL, 'kKWRyDHm', 2, '2022-03-09 00:00:00.000000', 'ocasseldine6s', 6),
	(246, '3002 Thackeray Circle', 1999, '2022-03-09 00:00:00.000000', '2008-12-09', 'Orlan Pinke', 1985, NULL, 'ciLX80x71l4', 2, '2022-03-09 00:00:00.000000', 'opinke6t', 10),
	(247, '3 Eggendart Pass', 1996, '2022-03-09 00:00:00.000000', '2008-02-02', 'Brandy McGraw', 1996, NULL, 'LPuzO1GwO1r', 3, '2022-03-09 00:00:00.000000', 'bmcgraw6u', 4),
	(248, '0 Nevada Terrace', 1994, '2022-03-09 00:00:00.000000', '2008-11-21', 'Dolph Eynaud', 1996, NULL, '0mfJdHIP', 1, '2022-03-09 00:00:00.000000', 'deynaud6v', 10),
	(249, '274 Kedzie Plaza', 2003, '2022-03-09 00:00:00.000000', '2005-09-11', 'Josephine Woodfin', 2008, NULL, 'DEzqwbO0', 2, '2022-03-09 00:00:00.000000', 'jwoodfin6w', 9),
	(250, '9160 Ludington Drive', 1996, '2022-03-09 00:00:00.000000', '2002-05-18', 'Cordelia Hobbema', 2002, NULL, 'aOCJLAKb', 3, '2022-03-09 00:00:00.000000', 'chobbema6x', 7),
	(251, '9 Hayes Lane', 2001, '2022-03-09 00:00:00.000000', '2004-07-23', 'Sim Rame', 2001, NULL, '5Z8A614QuP4', 1, '2022-03-09 00:00:00.000000', 'srame6y', 8),
	(252, '53261 Monument Alley', 1992, '2022-03-09 00:00:00.000000', '2005-10-18', 'Miles Zealander', 2007, NULL, '5mcWHK', 2, '2022-03-09 00:00:00.000000', 'mzealander6z', 10),
	(253, '28 Del Sol Street', 1992, '2022-03-09 00:00:00.000000', '2007-01-20', 'Curcio Arends', 1998, NULL, 'ZNyDiMbZv', 2, '2022-03-09 00:00:00.000000', 'carends70', 5),
	(254, '7 Washington Avenue', 2005, '2022-03-09 00:00:00.000000', '2003-06-26', 'Ibrahim Rawsthorn', 2007, NULL, 'gNq7TJ', 3, '2022-03-09 00:00:00.000000', 'irawsthorn71', 4),
	(255, '5 Pawling Crossing', 2008, '2022-03-09 00:00:00.000000', '2009-08-29', 'Leonore Stealey', 1997, NULL, 'q5dqo87', 2, '2022-03-09 00:00:00.000000', 'lstealey72', 9),
	(256, '9125 Nevada Park', 2003, '2022-03-09 00:00:00.000000', '2003-04-03', 'Ardath Poulson', 2007, NULL, 'k6BVjsOw', 1, '2022-03-09 00:00:00.000000', 'apoulson73', 9),
	(257, '9171 Orin Plaza', 2004, '2022-03-09 00:00:00.000000', '2001-01-31', 'Pietro Fowlie', 2013, NULL, 'WoVvaRmMP', 3, '2022-03-09 00:00:00.000000', 'pfowlie74', 11),
	(258, '4806 Hoard Avenue', 2005, '2022-03-09 00:00:00.000000', '2004-03-28', 'Gaby Winslet', 1995, NULL, 'Jsx22I3', 3, '2022-03-09 00:00:00.000000', 'gwinslet75', 5),
	(259, '404 1st Terrace', 2012, '2022-03-09 00:00:00.000000', '2002-10-19', 'Carmine Schreiner', 2011, NULL, 'Gnjru5', 2, '2022-03-09 00:00:00.000000', 'cschreiner76', 1),
	(260, '215 David Point', 2002, '2022-03-09 00:00:00.000000', '2000-11-08', 'Filberto Newlan', 2002, NULL, 'ZzOplrLf75', 3, '2022-03-09 00:00:00.000000', 'fnewlan77', 12),
	(261, '2436 Merchant Terrace', 2007, '2022-03-09 00:00:00.000000', '2009-03-12', 'Trever Ionesco', 2007, NULL, 'vm5FWu', 3, '2022-03-09 00:00:00.000000', 'tionesco78', 5),
	(262, '6 Anzinger Junction', 2003, '2022-03-09 00:00:00.000000', '2005-02-12', 'Jessey Tellenbrook', 2011, NULL, 'JX6WRstH8C', 1, '2022-03-09 00:00:00.000000', 'jtellenbrook79', 9),
	(263, '0 Village Way', 1996, '2022-03-09 00:00:00.000000', '2001-08-22', 'Tallou Bettam', 1999, NULL, 'vvnWLaK', 1, '2022-03-09 00:00:00.000000', 'tbettam7a', 12),
	(264, '48 Bluestem Junction', 1985, '2022-03-09 00:00:00.000000', '2001-07-11', 'Jo Janicki', 2010, NULL, '5iv2j98Q', 2, '2022-03-09 00:00:00.000000', 'jjanicki7b', 3),
	(265, '5 Tennessee Park', 2002, '2022-03-09 00:00:00.000000', '2001-02-10', 'Elfrieda Predohl', 2003, NULL, 'dhcA9VzBF', 2, '2022-03-09 00:00:00.000000', 'epredohl7c', 1),
	(266, '22127 Old Gate Place', 1998, '2022-03-09 00:00:00.000000', '2008-03-11', 'Agnesse Denisyev', 1992, NULL, '1ghXKQwmmdW', 2, '2022-03-09 00:00:00.000000', 'adenisyev7d', 7),
	(267, '947 Oak Valley Plaza', 1992, '2022-03-09 00:00:00.000000', '2003-06-05', 'Robert Nunson', 1996, NULL, 'OV3uXKLO5Z', 3, '2022-03-09 00:00:00.000000', 'rnunson7e', 5),
	(268, '2 Memorial Circle', 1957, '2022-03-09 00:00:00.000000', '2009-09-19', 'Yurik Belmont', 2012, NULL, 'Qg7j2Nst7rfu', 1, '2022-03-09 00:00:00.000000', 'ybelmont7f', 10),
	(269, '19 Mockingbird Plaza', 2007, '2022-03-09 00:00:00.000000', '2009-10-23', 'Sioux Mintrim', 1975, NULL, 'YLQv5sEg1E', 3, '2022-03-09 00:00:00.000000', 'smintrim7g', 2),
	(270, '67 Bayside Terrace', 2000, '2022-03-09 00:00:00.000000', '2002-03-07', 'Bondy Firbank', 1999, NULL, 'bjIPlPfzQLU0', 3, '2022-03-09 00:00:00.000000', 'bfirbank7h', 3),
	(271, '92856 Haas Drive', 2012, '2022-03-09 00:00:00.000000', '2007-10-02', 'Flori Jozwiak', 1992, NULL, 'WKb6YVre', 2, '2022-03-09 00:00:00.000000', 'fjozwiak7i', 9),
	(272, '20 Loftsgordon Circle', 1996, '2022-03-09 00:00:00.000000', '2004-10-10', 'Val Bilovus', 1995, NULL, '39Q2gG', 2, '2022-03-09 00:00:00.000000', 'vbilovus7j', 10),
	(273, '860 Morrow Place', 2004, '2022-03-09 00:00:00.000000', '2009-12-15', 'Emmet Deluce', 1996, NULL, 'P7iodrtWp', 3, '2022-03-09 00:00:00.000000', 'edeluce7k', 11),
	(274, '1172 Merry Street', 1997, '2022-03-09 00:00:00.000000', '2003-03-03', 'Laurence Ducham', 1993, NULL, 'tbrAKqetODe', 3, '2022-03-09 00:00:00.000000', 'lducham7l', 9),
	(275, '4901 Tony Drive', 1999, '2022-03-09 00:00:00.000000', '2007-01-30', 'Kipp Opy', 1991, NULL, 'GSCRcX6Hqx70', 1, '2022-03-09 00:00:00.000000', 'kopy7m', 1),
	(276, '88 Hoepker Place', 2010, '2022-03-09 00:00:00.000000', '2009-01-01', 'Ronalda Lintott', 1995, NULL, 'E9g5yZkN', 3, '2022-03-09 00:00:00.000000', 'rlintott7n', 1),
	(277, '429 Pennsylvania Hill', 2006, '2022-03-09 00:00:00.000000', '2005-03-26', 'Phelia Ceschelli', 1998, NULL, 'aUqQTOwrkTjB', 2, '2022-03-09 00:00:00.000000', 'pceschelli7o', 4),
	(278, '7084 Maryland Pass', 1996, '2022-03-09 00:00:00.000000', '2001-11-15', 'Ulberto Haswell', 2006, NULL, 'hWNijcfQKex', 2, '2022-03-09 00:00:00.000000', 'uhaswell7p', 9),
	(279, '4 Karstens Drive', 2002, '2022-03-09 00:00:00.000000', '2008-04-19', 'Costa MacAllester', 2009, NULL, 'NFtK5GjfQV5o', 3, '2022-03-09 00:00:00.000000', 'cmacallester7q', 9),
	(280, '7103 Lindbergh Drive', 1997, '2022-03-09 00:00:00.000000', '2008-06-20', 'Chip Pietron', 2000, NULL, 'KqCJL6', 3, '2022-03-09 00:00:00.000000', 'cpietron7r', 10),
	(281, '063 Moose Hill', 1992, '2022-03-09 00:00:00.000000', '2005-02-16', 'Brose Riddeough', 1993, NULL, 'JJhUVGLA', 2, '2022-03-09 00:00:00.000000', 'briddeough7s', 2),
	(282, '5 Del Sol Hill', 1992, '2022-03-09 00:00:00.000000', '2006-09-13', 'Derrek Streets', 1993, NULL, 'yca0RWx', 1, '2022-03-09 00:00:00.000000', 'dstreets7t', 7),
	(283, '68 Ohio Lane', 1996, '2022-03-09 00:00:00.000000', '2002-05-22', 'Hollis Pealing', 2010, NULL, 'dXEWqsunNgE', 3, '2022-03-09 00:00:00.000000', 'hpealing7u', 11),
	(284, '4811 Union Avenue', 1994, '2022-03-09 00:00:00.000000', '2003-07-25', 'Willy Tabner', 1995, NULL, 'WyimYG0l', 3, '2022-03-09 00:00:00.000000', 'wtabner7v', 7),
	(285, '1360 Lerdahl Pass', 1997, '2022-03-09 00:00:00.000000', '2006-09-25', 'Kelsy Farrin', 2009, NULL, 'T0MqYqN', 1, '2022-03-09 00:00:00.000000', 'kfarrin7w', 8),
	(286, '1 Bartelt Point', 2003, '2022-03-09 00:00:00.000000', '2007-08-08', 'Robinet Van der Linde', 2006, NULL, '5Ml6Ya', 3, '2022-03-09 00:00:00.000000', 'rvan7x', 1),
	(287, '9629 Heath Avenue', 2009, '2022-03-09 00:00:00.000000', '2002-05-07', 'Amitie Haggerty', 1993, NULL, 'radJUJriM', 2, '2022-03-09 00:00:00.000000', 'ahaggerty7y', 4),
	(288, '11532 Blue Bill Park Park', 2003, '2022-03-09 00:00:00.000000', '2004-05-03', 'Katharyn Isaksen', 2010, NULL, 't1sBOgc', 1, '2022-03-09 00:00:00.000000', 'kisaksen7z', 9),
	(289, '7 Leroy Crossing', 1993, '2022-03-09 00:00:00.000000', '2003-12-20', 'Judy Manger', 2002, NULL, 'tFyKJaIkcJh', 1, '2022-03-09 00:00:00.000000', 'jmanger80', 6),
	(290, '44831 Truax Avenue', 2011, '2022-03-09 00:00:00.000000', '2008-09-04', 'Maisie Fullwood', 1971, NULL, 'nlGnrXSA', 1, '2022-03-09 00:00:00.000000', 'mfullwood81', 12),
	(291, '1 Village Circle', 1997, '2022-03-09 00:00:00.000000', '2000-12-29', 'Albertina McKirdy', 2000, NULL, 'SKeL45TUEg', 1, '2022-03-09 00:00:00.000000', 'amckirdy82', 3),
	(292, '5845 Chinook Pass', 2009, '2022-03-09 00:00:00.000000', '2004-02-08', 'Jackson Hexter', 2004, NULL, '2QLHu8cNHfiY', 3, '2022-03-09 00:00:00.000000', 'jhexter83', 2),
	(293, '1 7th Drive', 2009, '2022-03-09 00:00:00.000000', '2005-09-03', 'Blane Brindle', 2004, NULL, 'N2Kx6sgkzVeP', 2, '2022-03-09 00:00:00.000000', 'bbrindle84', 7),
	(294, '8068 2nd Alley', 1995, '2022-03-09 00:00:00.000000', '2003-06-23', 'Blane Losbie', 1993, NULL, '93dffWZb5hP7', 3, '2022-03-09 00:00:00.000000', 'blosbie85', 7),
	(295, '27843 Division Place', 1992, '2022-03-09 00:00:00.000000', '2003-08-04', 'Annabal Keech', 2004, NULL, 'GBTp42oBqiV', 2, '2022-03-09 00:00:00.000000', 'akeech86', 3),
	(296, '2 Londonderry Way', 2005, '2022-03-09 00:00:00.000000', '2005-09-13', 'Ewell Oakwood', 1997, NULL, 'BjhkfuEFHc', 3, '2022-03-09 00:00:00.000000', 'eoakwood87', 10),
	(297, '22523 Haas Alley', 2005, '2022-03-09 00:00:00.000000', '2003-06-06', 'Shawn St. Hill', 2001, NULL, 'p1SlJmQ6zO3Y', 1, '2022-03-09 00:00:00.000000', 'sst88', 10),
	(298, '0972 Golf View Hill', 2008, '2022-03-09 00:00:00.000000', '2004-02-12', 'Juliana Terbrug', 1969, NULL, 'JcF1uIv', 2, '2022-03-09 00:00:00.000000', 'jterbrug89', 12),
	(299, '33449 Everett Trail', 2011, '2022-03-09 00:00:00.000000', '2008-04-28', 'Kimmie Moir', 1994, NULL, '3Rmlpp5', 3, '2022-03-09 00:00:00.000000', 'kmoir8a', 5),
	(300, '74717 Eagan Trail', 2006, '2022-03-09 00:00:00.000000', '2004-09-13', 'Elwood Everill', 2005, NULL, 'oNO4sM3pcmu', 1, '2022-03-09 00:00:00.000000', 'eeverill8b', 9);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;

-- Dumping structure for table school_management.student_evaluate
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
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(255) NOT NULL,
  `created_date` datetime(6) NOT NULL,
  `deleted` bit(1) NOT NULL,
  `dob` date NOT NULL,
  `end_date` date NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `phone` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `updated_date` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.user: ~0 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `email`, `password`, `address`, `created_date`, `deleted`, `dob`, `end_date`, `full_name`, `image`, `phone`, `start_date`, `updated_date`) VALUES
	(1, 'gblacksell0', 'gblacksell0@gnu.org', 'pBm0sXafFqju', '67065 Dovetail Terrace', '2022-07-04 04:48:01.000000', b'0', '2002-08-08', '2025-04-26', 'Giraud Blacksell', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6696218170', '2022-04-05', '2022-11-30 04:47:41.000000'),
	(2, 'dheskin1', 'dheskin1@sogou.com', 'bgYTYCTyupS', '99053 Nancy Point', '2022-06-19 07:34:27.000000', b'0', '2002-09-22', '2025-01-20', 'Dimitri Heskin', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7075727777', '2022-08-20', '2022-06-05 17:36:19.000000'),
	(3, 'dcarlsson2', 'dcarlsson2@ca.gov', '', '56 Vera Place', '2022-03-09 21:54:41.178463', b'0', '2002-09-19', '2025-05-28', 'Dennie Carlsson', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8014471502', '2022-02-10', '2022-03-09 21:54:41.178463'),
	(4, 'ewilkins3', 'ewilkins3@dmoz.org', 'YPCK8f7', '7 Farmco Road', '2022-09-12 19:14:06.000000', b'0', '2002-08-16', '2025-10-09', 'Emanuele Wilkins', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2017485010', '2022-08-13', '2022-08-28 04:42:54.000000'),
	(5, 'aclulee4', 'aclulee4@canalblog.com', 'bMbFzFUY5', '3574 Westridge Hill', '2022-06-14 07:24:43.000000', b'0', '2002-06-18', '2025-03-04', 'Adelina Clulee', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6554676810', '2022-02-17', '2022-09-08 02:01:05.000000'),
	(6, 'ldurdan5', 'ldurdan5@barnesandnoble.com', 'r7ca9xZVP', '1906 3rd Hill', '2022-07-14 10:48:50.000000', b'0', '2002-02-02', '2025-11-05', 'Lazarus Durdan', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4159390219', '2022-12-27', '2022-08-08 22:14:04.000000'),
	(7, 'rgritsunov6', 'rgritsunov6@dailymotion.com', 'V156BrNmbvu', '91 Sugar Street', '2022-04-27 10:19:05.000000', b'0', '2002-03-16', '2025-05-31', 'Ryley Gritsunov', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6349242705', '2022-09-04', '2022-11-23 10:57:31.000000'),
	(8, 'jjoddens7', 'jjoddens7@issuu.com', 'GJOD462v', '89491 Scoville Trail', '2022-06-20 15:57:44.000000', b'0', '2002-08-02', '2025-03-10', 'Johnny Joddens', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2231629949', '2022-08-15', '2022-06-28 15:06:42.000000'),
	(9, 'npettus8', 'npettus8@privacy.gov.au', 'K99NSinMRN', '36 Homewood Pass', '2022-03-17 19:57:20.000000', b'0', '2002-08-05', '2025-10-16', 'Nikkie Pettus', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3498661044', '2022-09-11', '2022-11-24 08:11:42.000000'),
	(10, 'jlangcastle9', 'jlangcastle9@wix.com', 'hSo24HnUF3c5', '156 Anniversary Point', '2022-06-09 19:12:51.000000', b'0', '2002-08-23', '2025-07-30', 'Johannes Langcastle', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7495090418', '2022-06-13', '2022-04-16 08:52:07.000000'),
	(11, 'dmoinea', 'dmoinea@t-online.de', 'VHX3EK8AP21T', '39678 Maple Wood Drive', '2022-06-08 08:09:33.000000', b'0', '2002-03-05', '2025-12-03', 'Delores Moine', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7987706412', '2022-11-19', '2022-10-02 11:38:43.000000'),
	(12, 'rsallowsb', 'rsallowsb@biblegateway.com', 'oosDIH7jC', '56953 Mcbride Alley', '2022-07-14 13:19:58.000000', b'0', '2002-07-20', '2025-02-14', 'Reinhard Sallows', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4892868584', '2022-05-05', '2022-10-07 14:55:30.000000'),
	(13, 'achazettec', 'achazettec@booking.com', 'IqPgkYEfd', '51200 Toban Parkway', '2022-07-21 00:17:25.000000', b'0', '2002-11-29', '2025-10-24', 'Ardella Chazette', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1666016879', '2022-04-08', '2022-10-16 04:02:38.000000'),
	(14, 'mslayd', 'mslayd@skyrock.com', 'yDg1Xo', '75656 Westend Way', '2022-08-25 16:06:37.000000', b'0', '2002-04-18', '2025-01-03', 'Max Slay', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7366519680', '2022-02-06', '2022-08-18 21:42:20.000000'),
	(15, 'tbenainee', 'tbenainee@sciencedirect.com', 'BJpYRh', '878 Carpenter Terrace', '2022-05-01 23:43:19.000000', b'0', '2002-09-09', '2025-07-12', 'Taryn Benaine', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7833707376', '2022-03-19', '2022-08-27 15:52:44.000000'),
	(16, 'rcreganf', 'rcreganf@cmu.edu', 'afDnwIMFZ', '845 Eagan Hill', '2022-09-14 05:52:00.000000', b'0', '2002-08-04', '2025-04-23', 'Reggi Cregan', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6099238375', '2022-02-12', '2022-05-05 04:17:58.000000'),
	(17, 'jsteadmang', 'jsteadmang@princeton.edu', 'X5VLTZSQ2oF', '0617 Mallory Lane', '2022-11-25 14:59:55.000000', b'1', '2002-10-18', '2025-11-08', 'Jill Steadman', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1978236753', '2022-09-16', '2022-03-18 11:27:38.000000'),
	(18, 'slinsleyh', 'slinsleyh@addthis.com', 'fpICV2BuO5qA', '98 Golden Leaf Point', '2022-02-01 05:22:12.000000', b'1', '2002-12-28', '2025-05-02', 'Simeon Linsley', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6915823925', '2022-09-01', '2022-12-26 05:26:44.000000'),
	(19, 'mbartolomeui', 'mbartolomeui@arizona.edu', 'Qm3aRF1c', '41 7th Road', '2022-05-13 05:34:16.000000', b'0', '2002-05-09', '2025-04-28', 'Meryl Bartolomeu', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8147095239', '2022-01-31', '2022-11-16 05:15:56.000000'),
	(20, 'rchippj', 'rchippj@va.gov', 'cDfCzWDTzF0r', '588 Brickson Park Junction', '2022-12-18 04:24:41.000000', b'1', '2002-08-27', '2025-08-07', 'Raimondo Chipp', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3675110188', '2022-12-17', '2022-03-04 12:10:05.000000'),
	(21, 'lgirhardk', 'lgirhardk@amazon.com', 'QC6jGUy', '577 Red Cloud Junction', '2022-05-07 16:39:49.000000', b'0', '2002-01-30', '2025-07-26', 'Lind Girhard', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4129431965', '2022-04-23', '2022-09-20 08:48:43.000000'),
	(22, 'ledwardesl', 'ledwardesl@lulu.com', 'XqQkSUg1', '993 Schlimgen Place', '2022-08-08 00:20:40.000000', b'0', '2002-06-09', '2025-05-27', 'Leelah Edwardes', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8855836616', '2022-01-19', '2022-04-22 05:29:57.000000'),
	(23, 'rheathcottm', 'rheathcottm@plala.or.jp', 'JNg0XR2oS', '1 John Wall Way', '2022-09-21 21:39:39.000000', b'0', '2002-08-14', '2025-08-13', 'Rudy Heathcott', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4565568632', '2022-06-28', '2022-06-19 18:05:14.000000'),
	(24, 'lhallardn', 'lhallardn@photobucket.com', 'wad2V3KQy', '78965 Brentwood Drive', '2022-01-16 16:22:29.000000', b'1', '2002-07-14', '2025-08-03', 'Llewellyn Hallard', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8501593731', '2022-05-25', '2022-07-20 14:57:43.000000'),
	(25, 'obuttrumo', 'obuttrumo@ucsd.edu', 'JWMTmEF', '45 Menomonie Terrace', '2022-10-24 21:55:44.000000', b'0', '2002-11-21', '2025-06-26', 'Ogdan Buttrum', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4844633052', '2022-07-06', '2022-10-23 00:09:49.000000'),
	(26, 'fvigursp', 'fvigursp@omniture.com', 'w3EY93R8WB', '7 Blaine Pass', '2022-08-09 00:43:34.000000', b'0', '2002-05-19', '2025-07-11', 'Fabio Vigurs', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4635057693', '2022-01-22', '2022-09-12 07:32:59.000000'),
	(27, 'acarssq', 'acarssq@exblog.jp', 'GhNBQ9bdL', '547 Hagan Hill', '2022-08-27 09:56:50.000000', b'0', '2002-02-15', '2025-03-25', 'Alwin Carss', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6118968767', '2022-05-10', '2022-12-19 10:26:08.000000'),
	(28, 'obuddingtonr', 'obuddingtonr@kickstarter.com', 'xL5RD19pdXI', '8711 Norway Maple Hill', '2022-07-31 02:16:50.000000', b'0', '2002-09-10', '2025-10-29', 'Ody Buddington', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '5888811521', '2022-08-23', '2022-01-08 01:01:34.000000'),
	(29, 'kschottlis', 'kschottlis@epa.gov', 'DhD4XBXe56N', '6847 Heffernan Place', '2022-03-05 16:53:28.000000', b'1', '2002-10-08', '2025-10-12', 'Kyla Schottli', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3544194422', '2022-08-27', '2022-07-11 02:55:16.000000'),
	(30, 'cshippamt', 'cshippamt@nationalgeographic.com', 'HFVn9dSX0Z', '4 Johnson Way', '2022-03-26 06:59:18.000000', b'1', '2002-11-05', '2025-09-29', 'Cicily Shippam', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '9388229693', '2022-12-15', '2022-10-19 15:37:11.000000'),
	(31, 'omedcraftu', 'omedcraftu@livejournal.com', 'JDbQ9ap8zvd', '29 Harper Place', '2022-05-15 13:55:49.000000', b'1', '2002-04-19', '2025-05-17', 'Orly Medcraft', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6539196598', '2022-08-12', '2022-06-09 01:46:58.000000'),
	(32, 'bcavilv', 'bcavilv@goodreads.com', 'xmFqMR45Kn1', '05 Ruskin Court', '2022-09-03 19:25:46.000000', b'1', '2002-09-21', '2025-10-22', 'Brendis Cavil', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7778733698', '2022-02-09', '2022-09-05 13:08:23.000000'),
	(33, 'kbrunrothw', 'kbrunrothw@jiathis.com', 'XfcHeu7TmG', '097 Golf View Place', '2022-09-22 01:09:03.000000', b'0', '2002-05-18', '2025-11-08', 'Kiel Brunroth', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7136820865', '2022-12-10', '2022-12-14 07:52:33.000000'),
	(34, 'jstokellx', 'jstokellx@cyberchimps.com', 'DFshMVw6w1b5', '57 Prairieview Place', '2022-02-10 16:59:59.000000', b'0', '2002-11-09', '2025-03-14', 'Jenny Stokell', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7345593044', '2022-06-17', '2022-01-01 04:39:26.000000'),
	(35, 'broundtreey', 'broundtreey@washington.edu', 'PraalaI2MolE', '0962 Barnett Court', '2022-05-12 01:53:54.000000', b'0', '2002-08-05', '2025-01-03', 'Beltran Roundtree', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3836690501', '2022-03-02', '2022-09-02 07:07:55.000000'),
	(36, 'nemesz', 'nemesz@narod.ru', 'UZclg3F0', '7 Upham Center', '2022-02-09 11:03:32.000000', b'0', '2002-07-20', '2025-09-08', 'Nicol Emes', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7831537751', '2022-09-13', '2022-09-06 07:50:47.000000'),
	(37, 'mfretson10', 'mfretson10@mlb.com', 'FEldP0senVm', '55 Tennessee Street', '2022-08-17 17:25:32.000000', b'1', '2002-10-13', '2025-02-13', 'Monti Fretson', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1912582830', '2022-11-20', '2022-04-11 17:29:35.000000'),
	(38, 'pleveridge11', 'pleveridge11@google.pl', 'CvTfnrSNpP6Q', '9058 Dapin Drive', '2022-12-23 15:20:41.000000', b'1', '2002-07-02', '2025-10-10', 'Pernell Leveridge', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7973388747', '2022-01-07', '2022-12-20 23:02:56.000000'),
	(39, 'istruis12', 'istruis12@uiuc.edu', 'hH8O9bXL5Th', '8365 Bartelt Way', '2022-08-31 01:46:24.000000', b'1', '2002-07-24', '2025-02-05', 'Ilise Struis', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4481299246', '2022-12-03', '2022-12-14 21:13:56.000000'),
	(40, 'jlarby13', 'jlarby13@mapy.cz', 'Tf4bitRM', '18677 Buena Vista Circle', '2022-01-04 06:55:39.000000', b'1', '2002-02-09', '2025-05-17', 'Jenni Larby', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6901478051', '2022-04-13', '2022-03-21 11:47:59.000000'),
	(41, 'bmoar14', 'bmoar14@blogger.com', 'VkutTVcYOAGk', '76835 Parkside Lane', '2022-02-08 17:31:32.000000', b'0', '2002-11-28', '2025-05-13', 'Bondy Moar', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1955597962', '2022-04-07', '2022-06-09 17:17:17.000000'),
	(42, 'dantonov15', 'dantonov15@indiegogo.com', 'KK4oDL0UGqHA', '2 Montana Place', '2022-06-19 23:23:29.000000', b'0', '2002-02-27', '2025-02-05', 'Der Antonov', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '5949344511', '2022-06-21', '2022-02-06 14:46:38.000000'),
	(43, 'clayborn16', 'clayborn16@walmart.com', 'dadkh2tzK0y4', '0 Montana Place', '2022-07-08 11:58:24.000000', b'0', '2002-01-04', '2025-11-07', 'Carey Layborn', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4535522991', '2022-08-05', '2022-07-12 01:54:22.000000'),
	(44, 'jnye17', 'jnye17@tuttocitta.it', '5wVwnGTkLgw', '63356 Butterfield Pass', '2022-10-31 17:17:49.000000', b'0', '2002-07-16', '2025-11-29', 'June Nye', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '9023026808', '2022-06-26', '2022-08-28 13:59:03.000000'),
	(45, 'rwegener18', 'rwegener18@amazon.de', 'i8d2lGX3', '2628 Dottie Court', '2022-02-11 23:45:08.000000', b'1', '2002-06-25', '2025-05-20', 'Rana Wegener', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7234710236', '2022-12-03', '2022-09-01 11:15:11.000000'),
	(46, 'lbarnwille19', 'lbarnwille19@cdc.gov', 'eJAXEVCgxQ', '351 Gale Center', '2022-09-01 21:37:13.000000', b'0', '2002-05-12', '2025-08-17', 'Liesa Barnwille', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7462425549', '2022-09-26', '2022-10-11 12:15:53.000000'),
	(47, 'ebart1a', 'ebart1a@yale.edu', 'Z3Pc3LjUKs', '1 Talisman Center', '2022-02-25 12:45:50.000000', b'1', '2002-12-27', '2025-08-23', 'Elana Bart', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4293625056', '2022-12-24', '2022-06-19 17:25:56.000000'),
	(48, 'dbooth1b', 'dbooth1b@mapquest.com', 'lTFtY5461n9y', '6 Independence Center', '2022-08-10 06:49:05.000000', b'0', '2002-10-11', '2025-02-05', 'Dorothy Booth', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6961085406', '2022-11-23', '2022-11-07 12:53:51.000000'),
	(49, 'mscreech1c', 'mscreech1c@cisco.com', 'KfXyA7arg4m', '16361 Clemons Circle', '2022-09-28 08:13:49.000000', b'0', '2002-04-08', '2025-12-29', 'Maighdiln Screech', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4572907215', '2022-07-13', '2022-04-21 18:23:43.000000'),
	(50, 'bboddie1d', 'bboddie1d@nydailynews.com', 'JN435GaB9', '162 Vahlen Crossing', '2022-03-21 00:37:54.000000', b'0', '2002-06-17', '2025-10-01', 'Brenda Boddie', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8251293466', '2022-01-04', '2022-04-25 12:20:18.000000'),
	(51, 'egallihaulk1e', 'egallihaulk1e@businessinsider.com', '37fzEIEl', '129 Tomscot Junction', '2022-04-07 14:12:51.000000', b'0', '2002-04-18', '2025-03-20', 'Eachelle Gallihaulk', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '5762570932', '2022-02-03', '2022-07-25 17:17:27.000000'),
	(52, 'etempleton1f', 'etempleton1f@tinypic.com', 'mzw9DIG', '30376 Bobwhite Hill', '2022-07-17 20:40:45.000000', b'0', '2002-01-06', '2025-12-10', 'Elayne Templeton', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '5125339976', '2022-11-30', '2022-01-29 01:03:28.000000'),
	(53, 'rgettings1g', 'rgettings1g@furl.net', '2V6dMs8', '7101 Kedzie Place', '2022-11-01 06:04:32.000000', b'1', '2002-12-19', '2025-12-27', 'Rossie Gettings', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '9501458593', '2022-01-07', '2022-09-05 14:56:15.000000'),
	(54, 'jschowenburg1h', 'jschowenburg1h@intel.com', 'uRMQ8rgJfRd', '474 Myrtle Parkway', '2022-06-17 13:03:44.000000', b'1', '2002-08-12', '2025-01-25', 'Johnath Schowenburg', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '9538861649', '2022-06-21', '2022-11-29 08:11:37.000000'),
	(55, 'atureville1i', 'atureville1i@mysql.com', 'GKS0Q2Z', '93626 Northwestern Road', '2022-04-18 04:15:05.000000', b'0', '2002-11-17', '2025-04-12', 'Aldo Tureville', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7476242149', '2022-08-27', '2022-06-03 08:54:51.000000'),
	(56, 'dtodaro1j', 'dtodaro1j@virginia.edu', 'FYUuHzlw5hq', '93 Hudson Park', '2022-09-09 00:25:36.000000', b'1', '2002-12-09', '2025-08-21', 'Dannye Todaro', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1697881138', '2022-09-09', '2022-09-12 22:14:23.000000'),
	(57, 'ibucklee1k', 'ibucklee1k@umn.edu', 'U9PNISdUcL', '48 East Street', '2022-10-13 03:47:28.000000', b'0', '2002-06-18', '2025-11-30', 'Ivonne Bucklee', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7886892229', '2022-02-01', '2022-05-21 18:17:04.000000'),
	(58, 'byoules1l', 'byoules1l@cnn.com', 'tHQ5O7lNkRmw', '2617 Delladonna Pass', '2022-03-14 16:04:22.000000', b'1', '2002-06-20', '2025-06-15', 'Bryon Youles', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '9429239365', '2022-12-17', '2022-01-12 02:40:41.000000'),
	(59, 'gnairy1m', 'gnairy1m@intel.com', 'jWIsEe2Ogo5e', '00867 Hoepker Park', '2022-03-05 22:46:49.000000', b'1', '2002-12-24', '2025-03-18', 'Gusta Nairy', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1507126329', '2022-04-07', '2022-11-01 12:13:36.000000'),
	(60, 'achapleo1n', 'achapleo1n@abc.net.au', 'v0Ro4I', '9 Cordelia Place', '2022-08-22 23:28:57.000000', b'0', '2002-07-09', '2025-11-26', 'Alick Chapleo', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8957542959', '2022-10-22', '2022-07-26 18:50:48.000000'),
	(61, 'mmatousek1o', 'mmatousek1o@angelfire.com', 'hWGODtiGPr5D', '8 Commercial Terrace', '2022-12-27 18:55:20.000000', b'0', '2002-06-15', '2025-06-01', 'Merralee Matousek', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8464288412', '2022-11-24', '2022-10-10 15:42:21.000000'),
	(62, 'cmumford1p', 'cmumford1p@rediff.com', 'tnNePnQVvK', '0675 Melody Road', '2022-06-12 13:40:01.000000', b'1', '2002-06-12', '2025-07-04', 'Consuela Mumford', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '9845339345', '2022-11-23', '2022-08-03 10:24:13.000000'),
	(63, 'hnaisbitt1q', 'hnaisbitt1q@unblog.fr', '4KU7S0e', '433 New Castle Junction', '2022-02-19 02:41:45.000000', b'1', '2002-05-12', '2025-10-30', 'Hollie Naisbitt', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '5507601089', '2022-09-06', '2022-06-25 17:31:48.000000'),
	(64, 'ctipens1r', 'ctipens1r@instagram.com', 'i7ozRv7s', '7772 Starling Park', '2022-01-20 06:43:27.000000', b'0', '2002-01-25', '2025-03-09', 'Cleveland Tipens', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1316573588', '2022-02-04', '2022-03-25 05:22:30.000000'),
	(65, 'hbremley1s', 'hbremley1s@jimdo.com', 'kkyhzg09hS2A', '96473 Blackbird Junction', '2022-11-13 21:46:30.000000', b'0', '2002-02-15', '2025-07-03', 'Hannie Bremley', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4726066745', '2022-07-09', '2022-05-28 13:39:21.000000'),
	(66, 'flocker1t', 'flocker1t@geocities.com', 'VY6RqJgFtQ', '3294 Oak Valley Park', '2022-06-19 06:04:35.000000', b'1', '2002-07-05', '2025-06-08', 'Franky Locker', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4766990633', '2022-05-05', '2022-10-07 12:18:14.000000'),
	(67, 'gidenden1u', 'gidenden1u@si.edu', 'YtYrqrGol', '533 Dawn Pass', '2022-03-01 10:05:06.000000', b'1', '2002-12-18', '2025-05-11', 'Georgina Idenden', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8038202927', '2022-11-06', '2022-10-16 00:51:42.000000'),
	(68, 'tyurevich1v', 'tyurevich1v@lulu.com', 'G2KEa5J', '54 Vahlen Lane', '2022-05-13 02:56:12.000000', b'1', '2002-09-08', '2025-12-18', 'Tremaine Yurevich', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1514898231', '2022-11-29', '2022-09-17 04:59:13.000000'),
	(69, 'sblyde1w', 'sblyde1w@bloglovin.com', 'dMnFNNrNidO', '6580 Badeau Terrace', '2022-01-11 01:45:52.000000', b'0', '2002-08-01', '2025-03-04', 'Stacia Blyde', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8951969710', '2022-07-30', '2022-05-07 19:10:22.000000'),
	(70, 'jruff1x', 'jruff1x@smugmug.com', 'pjf2JnQILUu', '7024 Hallows Place', '2022-07-08 10:28:43.000000', b'0', '2002-01-05', '2025-02-22', 'Jennilee Ruff', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2041521570', '2022-11-26', '2022-12-22 00:03:38.000000'),
	(71, 'aboyne1y', 'aboyne1y@scientificamerican.com', 'PFjbWNgMQhs', '12574 Heffernan Point', '2022-10-22 19:34:30.000000', b'1', '2002-07-06', '2025-05-20', 'Ami Boyne', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6159357407', '2022-11-08', '2022-07-29 12:01:17.000000'),
	(72, 'oedelheid1z', 'oedelheid1z@merriam-webster.com', 'nUzC4MOPe', '6 La Follette Circle', '2022-04-15 08:37:01.000000', b'0', '2002-04-14', '2025-11-30', 'Othelia Edelheid', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4639896712', '2022-07-29', '2022-10-07 20:03:15.000000'),
	(73, 'spunter20', 'spunter20@plala.or.jp', 'DtDAuGUgj', '5231 Cottonwood Hill', '2022-08-28 06:24:05.000000', b'1', '2002-03-03', '2025-07-18', 'Shepperd Punter', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2794606893', '2022-07-23', '2022-11-26 13:39:49.000000'),
	(74, 'echesters21', 'echesters21@issuu.com', 'L1Ubdl', '6 Marcy Alley', '2022-02-15 18:52:37.000000', b'0', '2002-01-07', '2025-01-31', 'Emmett Chesters', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7104320099', '2022-06-12', '2022-08-30 02:52:50.000000'),
	(75, 'lshipway22', 'lshipway22@chronoengine.com', 'UmniAN', '8 Eastlawn Place', '2022-07-29 08:42:02.000000', b'0', '2002-03-01', '2025-09-25', 'Lucian Shipway', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6996490906', '2022-03-23', '2022-12-21 12:02:21.000000'),
	(76, 'scuffe23', 'scuffe23@gravatar.com', 'r4TOgl', '24384 Green Crossing', '2022-04-28 06:12:00.000000', b'0', '2002-04-07', '2025-02-21', 'Sascha Cuffe', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '9147025843', '2022-09-10', '2022-05-15 07:44:14.000000'),
	(77, 'sallcorn24', 'sallcorn24@go.com', '44876HurjnGZ', '5353 Logan Plaza', '2022-01-12 12:12:31.000000', b'0', '2002-10-18', '2025-08-19', 'Shepperd Allcorn', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6331384520', '2022-06-13', '2022-04-18 04:46:37.000000'),
	(78, 'glavies25', 'glavies25@tinypic.com', 'NeYPuSndUu8o', '98125 Autumn Leaf Circle', '2022-08-08 18:42:12.000000', b'1', '2002-06-18', '2025-09-05', 'Georgeanna Lavies', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7149798524', '2022-09-24', '2022-09-10 08:01:00.000000'),
	(79, 'rpfeffle26', 'rpfeffle26@intel.com', 'z7XU2BACNN', '9 Texas Avenue', '2022-05-24 23:52:18.000000', b'1', '2002-10-19', '2025-06-12', 'Roderick Pfeffle', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4103309950', '2022-10-18', '2022-02-07 12:39:38.000000'),
	(80, 'slaunder27', 'slaunder27@ezinearticles.com', 'I8WEqZ7dmb4g', '43 Utah Pass', '2022-05-26 16:56:55.000000', b'1', '2002-03-30', '2025-01-18', 'Stanleigh Launder', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2553968802', '2022-12-22', '2022-01-12 08:42:31.000000'),
	(81, 'ghastewell28', 'ghastewell28@google.de', 'DvqnmXZjWYs', '84648 Iowa Crossing', '2022-04-03 08:14:33.000000', b'0', '2002-07-22', '2025-01-03', 'Glori Hastewell', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6539527747', '2022-05-18', '2022-06-25 10:21:12.000000'),
	(82, 'apoint29', 'apoint29@de.vu', 'cciE0tUfV', '8 Lakeland Point', '2022-09-18 02:23:40.000000', b'0', '2002-07-03', '2025-05-29', 'Ardis Point', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2424614024', '2022-04-16', '2022-03-31 11:08:17.000000'),
	(83, 'bstubbins2a', 'bstubbins2a@goo.ne.jp', 'PNsjE1Tm', '2901 Jay Terrace', '2022-12-14 15:12:29.000000', b'1', '2002-12-14', '2025-12-23', 'Burton Stubbins', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6473388883', '2022-01-26', '2022-12-21 13:04:55.000000'),
	(84, 'fhabbon2b', 'fhabbon2b@about.com', 'aX7Bm6pcl8h', '4315 Express Alley', '2022-02-28 01:48:25.000000', b'1', '2002-12-24', '2025-08-07', 'Franny Habbon', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1158576183', '2022-01-20', '2022-09-21 03:33:35.000000'),
	(85, 'spaterno2c', 'spaterno2c@prlog.org', 'TzMEjs', '0962 East Crossing', '2022-08-05 12:20:01.000000', b'0', '2002-05-09', '2025-10-19', 'Stacie Paterno', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8179452085', '2022-10-09', '2022-03-13 07:00:47.000000'),
	(86, 'ccolebourne2d', 'ccolebourne2d@netscape.com', '16tX4JIWaC9F', '9781 Duke Point', '2022-01-15 02:39:12.000000', b'1', '2002-06-24', '2025-01-17', 'Cariotta Colebourne', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4486066559', '2022-09-06', '2022-06-02 17:49:21.000000'),
	(87, 'vbromby2e', 'vbromby2e@networkadvertising.org', '1MWCXl7TEV', '070 Cordelia Drive', '2022-12-09 17:01:34.000000', b'0', '2002-12-24', '2025-06-28', 'Valentine Bromby', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '1063357187', '2022-03-18', '2022-05-21 18:44:26.000000'),
	(88, 'bfullstone2f', 'bfullstone2f@is.gd', 'eEdZ1bTp7b', '8142 Thompson Alley', '2022-09-13 11:44:21.000000', b'1', '2002-06-29', '2025-05-29', 'Bryanty Fullstone', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '5719160055', '2022-02-10', '2022-05-09 19:47:08.000000'),
	(89, 'lmanie2g', 'lmanie2g@wikispaces.com', 'vD61UMx8X', '31954 Waywood Avenue', '2022-01-28 14:04:42.000000', b'1', '2002-03-09', '2025-05-06', 'Lu Manie', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2834876820', '2022-09-11', '2022-10-21 00:10:33.000000'),
	(90, 'tbolstridge2h', 'tbolstridge2h@microsoft.com', '01JAQ0E', '20479 Russell Crossing', '2022-05-16 21:01:42.000000', b'1', '2002-11-08', '2025-06-28', 'Terrijo Bolstridge', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '6754796519', '2022-10-18', '2022-03-28 11:04:56.000000'),
	(91, 'mmatteoli2i', 'mmatteoli2i@canalblog.com', 'BThcFUGVWNQ0', '57 Orin Place', '2022-01-14 07:47:02.000000', b'0', '2002-01-14', '2025-09-18', 'Modestia Matteoli', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3926450098', '2022-07-24', '2022-03-06 05:30:37.000000'),
	(92, 'hanglim2j', 'hanglim2j@imgur.com', '4wXN1lmk', '32 Dayton Center', '2022-09-13 00:29:31.000000', b'1', '2002-10-27', '2025-10-23', 'Henri Anglim', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2051335091', '2022-06-28', '2022-01-27 02:15:16.000000'),
	(93, 'cbastie2k', 'cbastie2k@wufoo.com', '8FSUu9czH7wH', '32103 Vera Crossing', '2022-07-04 09:53:46.000000', b'0', '2002-11-10', '2025-08-18', 'Cameron Bastie', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3163004892', '2022-07-30', '2022-09-29 11:26:53.000000'),
	(94, 'zlenz2l', 'zlenz2l@hatena.ne.jp', '7XYlIjCYS', '90 Sommers Avenue', '2022-01-14 02:12:32.000000', b'0', '2002-07-29', '2025-05-09', 'Zebulon Lenz', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '2436054416', '2022-03-12', '2022-06-02 07:08:56.000000'),
	(95, 'rtompion2m', 'rtompion2m@alexa.com', 'o9o2sLGp', '4499 Morningstar Circle', '2022-12-08 06:30:33.000000', b'0', '2002-12-10', '2025-06-08', 'Randal Tompion', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4155552060', '2022-03-09', '2022-06-05 06:24:06.000000'),
	(96, 'sfateley2n', 'sfateley2n@si.edu', '2pzh4PL', '07906 Towne Road', '2022-06-07 22:38:10.000000', b'1', '2002-10-20', '2025-02-08', 'Sascha Fateley', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '4479242971', '2022-06-28', '2022-03-28 07:17:16.000000'),
	(97, 'hleefe2o', 'hleefe2o@1688.com', 'sicb0KMS', '26362 Barby Park', '2022-03-10 13:10:24.000000', b'1', '2002-08-01', '2025-08-10', 'Hammad Leefe', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3109802431', '2022-04-01', '2022-03-25 20:20:26.000000'),
	(98, 'cchoak2p', 'cchoak2p@washingtonpost.com', 'CWb5NON2s', '2 Orin Alley', '2022-06-01 00:39:22.000000', b'0', '2002-09-14', '2025-07-27', 'Corenda Choak', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '3944828593', '2022-04-01', '2022-03-12 23:32:40.000000'),
	(99, 'bguillerman2q', 'bguillerman2q@tumblr.com', 'KNyOwIBy49e3', '554 Burrows Place', '2022-02-16 12:48:10.000000', b'1', '2002-08-31', '2025-04-28', 'Blondy Guillerman', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '8313285555', '2022-03-25', '2022-05-04 15:35:45.000000'),
	(100, 'hgarden2r', 'hgarden2r@blinklist.com', 'fM2xuvp', '3 Del Sol Circle', '2022-04-27 01:09:03.000000', b'0', '2002-06-08', '2025-11-15', 'Hulda Garden', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7472902717', '2022-06-03', '2022-07-13 02:37:58.000000'),
	(101, 'admin', 'admin@gmail.com', '$2a$10$1223Y/mCqhMnd/BDk83WAOHGAPotcPp28iBbatc67mkL1pAAwLCBi', '3 Del Sol Circle', '2022-04-27 01:09:03.000000', b'0', '2002-06-08', '2025-11-15', 'Admin', 'e60c24c5-6a46-4a9b-9675-27fcdd312709.png', '7472902717', '2022-06-03', '2022-07-13 02:37:58.000000');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table school_management.user_role
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table school_management.user_role: ~0 rows (approximately)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(101, 1),
	(1, 2),
	(2, 2),
	(3, 2),
	(4, 2),
	(5, 2),
	(6, 2),
	(7, 2),
	(8, 2),
	(9, 2),
	(10, 2),
	(11, 2),
	(12, 2),
	(13, 2),
	(14, 2),
	(15, 2),
	(16, 2),
	(17, 2),
	(18, 2),
	(19, 2),
	(20, 2),
	(21, 2),
	(22, 2),
	(23, 2),
	(24, 2),
	(25, 2),
	(26, 2),
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
	(42, 2),
	(43, 2),
	(44, 2),
	(45, 2),
	(46, 2),
	(47, 2),
	(48, 2),
	(49, 2),
	(50, 2),
	(51, 2),
	(52, 2),
	(53, 2),
	(54, 2),
	(55, 2),
	(56, 2),
	(57, 2),
	(58, 2),
	(59, 2),
	(60, 2),
	(61, 2),
	(62, 2),
	(63, 2),
	(64, 2),
	(65, 2),
	(66, 2),
	(67, 2),
	(68, 2),
	(69, 2),
	(70, 2),
	(71, 2),
	(72, 2),
	(73, 2),
	(74, 2),
	(75, 2),
	(76, 2),
	(77, 2),
	(78, 2),
	(79, 2),
	(80, 2),
	(81, 2),
	(82, 2),
	(83, 2),
	(84, 2),
	(85, 2),
	(86, 2),
	(87, 2),
	(88, 2),
	(89, 2),
	(90, 2),
	(91, 2),
	(92, 2),
	(93, 2),
	(94, 2),
	(95, 2),
	(96, 2),
	(97, 2),
	(98, 2),
	(99, 2),
	(100, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
