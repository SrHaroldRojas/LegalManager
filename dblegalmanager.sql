-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-05-2023 a las 14:45:57
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

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `user` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `name`, `lastname`, `email`, `phone`, `user`, `password`) VALUES
(1, 'Jhon', 'Ozuna', 'johndoe@gmail.com', '3125551234', 'jhondoe', 'pass123pass'),
(2, 'Jina', 'Ortega', 'jinao@gmail.com', '3135555678', 'jinao', 'jina313555'),
(3, 'Alicia', 'Jimenez', 'alicej@hotmail.com', '3205553456', 'alicej', 'mamatequiero123'),
(5, 'Charlie', 'Brown', 'charliebrown@gmail.com', '3105557890', 'charlieb', 'ilikepizza'),
(6, 'Stan', 'Lee', 'stanavenger@gmail.com', '555-2345', 'stanlee', 'stanlee'),
(7, 'Mike', 'Tower', 'miketowers@hotmail.com', '3025556789', 'mikej', 'calle43b'),
(8, 'Karen', 'Gomez', 'kareng@gmail.com', '3145551234', 'karenj', 'pepitoteamo'),
(9, 'David', 'ortiz', 'davidnguyen@gmail.com', '3215555678', 'davidng', 'david321'),
(15, 'Pablo', 'Detective', 'pablito123@gmail.com', '3120034231', 'pablito', '312003'),
(16, 'Andres', 'Gamboa', 'andresa@gmail.com', '3214567890', 'andres', 'gamboaandres'),
(17, 'harold', 'rojas', 'herojast@itc.edu.co', '3176871900', 'harold', '1234'),
(18, 'harold', 'mendoza', 'teriana@gmail.com', '31212', 'haroldw', '1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abogados`
--
ALTER TABLE `abogados`
  ADD PRIMARY KEY (`id_lawyer`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abogados`
--
ALTER TABLE `abogados`
  MODIFY `id_lawyer` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
