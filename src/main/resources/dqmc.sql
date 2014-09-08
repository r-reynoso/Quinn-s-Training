

DROP DATABASE IF EXISTS `dqmc`;
CREATE DATABASE `dqmc`;

USE `dqmc`;

GRANT ALL ON dqmc.* TO dqmc@localhost IDENTIFIED BY 'dqmc123';

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `boundries` polygon DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `chinchorro`;
CREATE TABLE `chinchorro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `urb` varchar(45) DEFAULT NULL,
  `number` int(10) DEFAULT NULL,
  `apt` int(6) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `zip` int(5) DEFAULT NULL,
  `plus4` int(4) DEFAULT NULL,
  `latitude` decimal(20,0) NOT NULL,
  `longitude` decimal(20,0) NOT NULL,
  `kmarker` int(11) DEFAULT NULL,
  `city_id` INT NOT NULL,
  FOREIGN KEY (`city_id`) REFERENCES city(`id`) 
  ON UPDATE CASCADE ON DELETE RESTRICT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
