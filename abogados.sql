-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2023 a las 03:37:05
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dblegalmanager`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abogados`
--

CREATE TABLE `abogados` (
  `id_lawyer` int(3) NOT NULL,
  `name_lawyer` varchar(50) DEFAULT NULL,
  `lastname_lawyer` varchar(50) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `num_telephone` int(11) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `abogados`
--

INSERT INTO `abogados` (`id_lawyer`, `name_lawyer`, `lastname_lawyer`, `address`, `num_telephone`, `email`) VALUES
(1, 'Juanito', 'Rodriguez', 'CALLE 25 # 1-66', 2147483647, 'juanrodriguez@gmail.com'),
(2, 'David', 'Parra', 'CALLE 26 # 2-66', 2147483647, 'davidparra@gmail.com'),
(3, 'Alexis', 'Garcia', 'CARRERA 27 # 3-66', 2147483647, 'alexgarcia@gmail.com'),
(4, 'Catalina', 'Satizabal', 'CALLE 28 # 4-66', 2147483647, 'catazatizabal@gmail.com'),
(5, 'Hernesto', 'Suarez', 'CALLE 29 # 5-66', 2147483647, 'hernesuarez@gmail.com'),
(6, 'Olga', 'Pardo', 'CALLE 25 # 6-12', 2147483647, 'olgapardo74@gmail.com'),
(7, 'Esteban', 'Panqueva', 'CALLE 4 # 7-66', 2147483647, 'estebanpanqu3va@gmail.com'),
(8, 'Jimena', 'Zarate', 'CALLE 23 # 8-43', 2147483647, 'jimenazarate21@gmail.com'),
(9, 'Jeison', 'Jimenez', 'CALLE 22 # 9-99', 2147483647, 'jeisonkchon@gmail.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abogados`
--
ALTER TABLE `abogados`
  ADD PRIMARY KEY (`id_lawyer`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abogados`
--
ALTER TABLE `abogados`
  MODIFY `id_lawyer` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
