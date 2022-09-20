-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.10.1-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para parking_reservation
CREATE DATABASE IF NOT EXISTS `parking_reservation` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `parking_reservation`;

-- Volcando estructura para tabla parking_reservation.puestos
CREATE TABLE IF NOT EXISTS `puestos` (
  `id` int(11) NOT NULL,
  `tipo` varchar(100) DEFAULT NULL,
  `zona` varchar(100) DEFAULT NULL,
  `costo` double(22,2) DEFAULT NULL,
  `disponible` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla parking_reservation.reserva
CREATE TABLE IF NOT EXISTS `reserva` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `hora_reserva` datetime NOT NULL,
  `hora_salida` datetime NOT NULL,
  PRIMARY KEY (`id`,`username`,`hora_reserva`) USING BTREE,
  KEY `FK__usuarios` (`username`),
  CONSTRAINT `FK__puestos` FOREIGN KEY (`id`) REFERENCES `puestos` (`id`),
  CONSTRAINT `FK__usuarios` FOREIGN KEY (`username`) REFERENCES `usuarios` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla parking_reservation.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `username` varchar(100) NOT NULL,
  `contrasena` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `saldo` double(22,2) DEFAULT NULL,
  `premium` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
