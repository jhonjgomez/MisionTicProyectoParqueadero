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

-- Volcando datos para la tabla parking_reservation.puestos: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `puestos` DISABLE KEYS */;
INSERT INTO `puestos` (`id`, `tipo`, `zona`, `costo`, `disponible`) VALUES
	(1, 'carro', 'Norte', 10000.00, 1),
	(2, 'moto', 'Norte', 5000.00, 1),
	(3, 'carro', 'Oeste', 10000.00, 1),
	(4, 'moto', 'Sur', 5000.00, 1),
	(5, 'carro', 'Este', 10000.00, 1),
	(6, 'moto', 'Sur', 5000.00, 1),
	(7, 'carro', 'Norte', 10000.00, 1),
	(8, 'moto', 'Oeste', 5000.00, 0),
	(9, 'carro', 'Norte', 10000.00, 1),
	(10, 'moto', 'Oeste', 5000.00, 0),
	(11, 'carro', 'Este', 10000.00, 1),
	(12, 'moto', 'Norte', 5000.00, 1),
	(13, 'carro', 'Oeste', 10000.00, 1),
	(14, 'moto', 'Sur', 5000.00, 1),
	(15, 'carro', 'Este', 10000.00, 1),
	(16, 'moto', 'Sur', 5000.00, 1),
	(17, 'carro', 'Norte', 10000.00, 0),
	(18, 'moto', 'Oeste', 5000.00, 1),
	(19, 'carro', 'Norte', 10000.00, 1),
	(20, 'moto', 'Oeste', 5000.00, 1),
	(21, 'carro', 'Este', 10000.00, 1);
/*!40000 ALTER TABLE `puestos` ENABLE KEYS */;

-- Volcando estructura para tabla parking_reservation.reserva
CREATE TABLE IF NOT EXISTS `reserva` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `fechaEntrada` datetime NOT NULL,
  PRIMARY KEY (`id`,`username`,`fechaEntrada`) USING BTREE,
  KEY `FK__usuarios` (`username`),
  CONSTRAINT `FK__puestos` FOREIGN KEY (`id`) REFERENCES `puestos` (`id`),
  CONSTRAINT `FK__usuarios` FOREIGN KEY (`username`) REFERENCES `usuarios` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Volcando datos para la tabla parking_reservation.reserva: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;

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

-- Volcando datos para la tabla parking_reservation.usuarios: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`username`, `contrasena`, `nombre`, `apellido`, `email`, `saldo`, `premium`) VALUES
	('Mari123', '12345', 'Mariana', 'Florez', 'mflorez@mail.com', 13000.00, 1),
	('pepe', '6321', 'Pedro Pablo', 'Leon Jaramillo', 'pleon@mail.com', 58500.00, 1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
