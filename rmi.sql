-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.18-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for rmi
CREATE DATABASE IF NOT EXISTS `rmi` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `rmi`;

-- Dumping structure for table rmi.candidates
CREATE TABLE IF NOT EXISTS `candidates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Dumping data for table rmi.candidates: ~3 rows (approximately)
/*!40000 ALTER TABLE `candidates` DISABLE KEYS */;
INSERT INTO `candidates` (`id`, `code`, `name`) VALUES
	(14, '02', 'tesfa'),
	(15, '03', 'fedhasa'),
	(16, '04', 'zelalem'),
	(17, '01', 'moa');
/*!40000 ALTER TABLE `candidates` ENABLE KEYS */;

-- Dumping structure for table rmi.ipinfo
CREATE TABLE IF NOT EXISTS `ipinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ipNo` varchar(50) DEFAULT NULL,
  `subnet` varchar(50) DEFAULT NULL,
  `wanIp` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ipNo_wanIp` (`ipNo`,`wanIp`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Dumping data for table rmi.ipinfo: ~5 rows (approximately)
/*!40000 ALTER TABLE `ipinfo` DISABLE KEYS */;
INSERT INTO `ipinfo` (`id`, `ipNo`, `subnet`, `wanIp`, `status`) VALUES
	(1, '10.10.25.55', '255.555', '11.99.00', 0),
	(3, 'bbb', '099', '8878', 0),
	(4, '100', '121', '121', 0),
	(5, '121', '121', '121', 0),
	(6, '11212', '121', '1211', 0),
	(7, '1', '1', '1', 0);
/*!40000 ALTER TABLE `ipinfo` ENABLE KEYS */;

-- Dumping structure for table rmi.parammxvote
CREATE TABLE IF NOT EXISTS `parammxvote` (
  `mxVote` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table rmi.parammxvote: ~0 rows (approximately)
/*!40000 ALTER TABLE `parammxvote` DISABLE KEYS */;
INSERT INTO `parammxvote` (`mxVote`) VALUES
	(3);
/*!40000 ALTER TABLE `parammxvote` ENABLE KEYS */;

-- Dumping structure for table rmi.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table rmi.users: ~2 rows (approximately)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `name`, `username`, `password`) VALUES
	(1, 'a b c', 'a', 'a'),
	(2, 'b b b', 'b', 'b'),
	(3, 'cc', 'c', 'c');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

-- Dumping structure for table rmi.vote
CREATE TABLE IF NOT EXISTS `vote` (
  `int` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `voter` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`int`),
  UNIQUE KEY `code_voter` (`code`,`voter`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- Dumping data for table rmi.vote: ~3 rows (approximately)
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` (`int`, `code`, `voter`) VALUES
	(94, '02', 'b'),
	(102, '02', 'c'),
	(103, '04', 'a');
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
