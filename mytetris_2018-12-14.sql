# ************************************************************
# Sequel Pro SQL dump
# Version 5425
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.22)
# Database: mytetris
# Generation Time: 2018-12-14 04:38:20 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table type_inf
# ------------------------------------------------------------

DROP TABLE IF EXISTS `type_inf`;

CREATE TABLE `type_inf` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `game_name` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `type_inf` WRITE;
/*!40000 ALTER TABLE `type_inf` DISABLE KEYS */;

INSERT INTO `type_inf` (`id`, `game_name`)
VALUES
	(1,'俄罗斯方块'),
	(2,'坦克大战'),
	(3,'连连看\n');

/*!40000 ALTER TABLE `type_inf` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user_point
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user_point`;

CREATE TABLE `user_point` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(11) NOT NULL DEFAULT '',
  `point` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `user_point` WRITE;
/*!40000 ALTER TABLE `user_point` DISABLE KEYS */;

INSERT INTO `user_point` (`id`, `user_name`, `point`, `type_id`)
VALUES
	(1,'刘明',1000,1),
	(2,'奥巴马',1024,1),
	(3,'王熙凤',2048,1),
	(4,'贾宝玉',3879,1),
	(5,'小明',694,1),
	(6,'贾马尔',1397,1),
	(7,'普京',4789,1),
	(8,'凯特',2041,1);

/*!40000 ALTER TABLE `user_point` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
