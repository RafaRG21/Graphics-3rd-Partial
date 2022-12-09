-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 08-09-2022 a las 19:01:10
-- Versión del servidor: 5.7.39-log
-- Versión de PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `wwramd_munatiende_sav`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `catalogo_actividad`
--

CREATE TABLE `catalogo_actividad` (
  `idcatalogo_actividad` int(11) NOT NULL,
  `idcuenta` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `edificio` text,
  `activo` int(11) NOT NULL DEFAULT '1',
  `puntaje` int(11) NOT NULL DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `catalogo_actividad`
--

INSERT INTO `catalogo_actividad` (`idcatalogo_actividad`, `idcuenta`, `nombre`, `edificio`, `activo`, `puntaje`, `created_at`, `updated_at`) VALUES
(1, 9, 'Actividadtipo13', NULL, 0, 0, '2022-07-06 14:10:27', '2022-07-06 14:28:17'),
(2, 9, 'ffffff', NULL, 0, 0, '2022-07-06 14:11:56', '2022-07-06 14:28:19'),
(3, 2, '3Actividad24444444', NULL, 1, 0, '2022-07-06 14:12:32', '2022-07-06 14:27:43'),
(4, 9, 'Ir al aire libre', NULL, 0, 0, '2022-07-06 14:30:16', '2022-07-06 14:34:45'),
(5, 9, 'Actividad 2', NULL, 0, 0, '2022-07-06 14:31:43', '2022-07-06 14:34:45'),
(6, 9, 'Actividad 3', NULL, 0, 0, '2022-07-06 14:31:48', '2022-07-06 14:34:45'),
(7, 9, 'Nueva actividad 1', NULL, 1, 33, '2022-07-06 14:34:59', '2022-07-25 17:36:28'),
(8, 9, 'Actividad 2', NULL, 0, 0, '2022-07-06 14:35:04', '2022-07-06 14:35:20'),
(9, 9, 'Actividad 3', NULL, 0, 0, '2022-07-06 14:35:08', '2022-07-06 14:35:20'),
(10, 9, 'Actividad 4', '{\"edificio0\":16,\"edificio1\":47}', 1, 333, '2022-07-06 14:35:12', '2022-08-19 16:52:03'),
(11, 9, 'Ir a correr', '[]', 1, 12, '2022-07-06 19:02:54', '2022-08-19 16:55:56'),
(12, 22, 'Entrega de Despensas', NULL, 0, 0, '2022-07-07 10:15:39', '2022-09-01 10:58:30'),
(13, 22, 'Atención de servicios', NULL, 0, 0, '2022-07-07 10:15:52', '2022-09-01 10:58:26'),
(14, 22, 'comida', NULL, 0, 0, '2022-07-13 17:00:20', '2022-09-01 10:58:30'),
(15, 9, 'TERSTK', NULL, 0, 400, '2022-07-21 12:44:14', '2022-07-21 12:46:36'),
(16, 9, 'actividad x', '{\"edificio0\":1}', 1, 25, '2022-07-21 12:46:46', '2022-08-19 16:52:16'),
(17, 9, 'actividad 33', '{\"edificio0\":2}', 1, 123, '2022-07-21 12:46:55', '2022-08-19 16:55:34'),
(18, 14, 'Entrega de Despensas', NULL, 1, 12, '2022-07-25 17:54:09', '2022-07-25 17:54:09'),
(19, 23, 'ENTREGA DE DESPENSA', NULL, 1, 10, '2022-08-10 23:13:09', '2022-08-10 23:13:09'),
(20, 22, 'PROF JUAN CARRILLO', NULL, 0, 1, '2022-08-17 12:20:53', '2022-09-01 10:58:30'),
(21, 9, 'Entrega de despensa con puntos', '{\"edificio1\":1,\"edificio2\":3}', 1, 100, '2022-08-19 13:35:25', '2022-08-19 13:35:25'),
(22, 9, 'Entrega de despensa con puntos 2', '{\"edificio1\":1,\"edificio2\":3}', 1, 100, '2022-08-19 13:44:21', '2022-08-19 13:44:21'),
(23, 9, 'Activi23', '{\"edificio0\":3}', 1, 12, '2022-08-19 14:44:17', '2022-08-19 14:44:17'),
(24, 9, 'Actividad Edificada', '{\"edificio0\":48,\"edificio1\":43}', 1, 333, '2022-08-19 14:49:46', '2022-08-19 14:49:46'),
(25, 9, 'Ok Yes Activ', '{\"edificio0\":2,\"edificio1\":43,\"edificio2\":1,\"edificio3\":46}', 1, 26, '2022-08-19 16:44:36', '2022-08-19 16:44:36'),
(26, 9, 'Activi24', '[]', 0, 999, '2022-08-19 16:52:41', '2022-08-19 16:55:10'),
(27, 9, 'Act25', '{\"edificio0\":47,\"edificio1\":17,\"edificio2\":48}', 1, 123, '2022-08-19 16:55:22', '2022-08-19 16:55:22'),
(31, 9, 'tipo1', '{\"edificio1\":55,\"edificio2\":55}', 1, 100, '2022-08-26 22:06:04', '2022-08-26 22:06:04'),
(30, 9, 'tipo2', '{\"edificio1\":56,\"edificio2\":57}', 1, 100, '2022-08-26 22:05:56', '2022-08-26 22:05:56'),
(32, 13, 'Despensas', '{\"edificio0\":49}', 1, 0, '2022-08-29 17:32:02', '2022-08-29 17:32:02'),
(33, 13, 'Tinacos', '{\"edificio0\":51}', 1, 0, '2022-08-29 17:32:20', '2022-08-29 17:32:20'),
(34, 13, 'Zapatos Escolares', '{\"edificio0\":50}', 1, 0, '2022-08-29 17:32:38', '2022-08-29 17:32:38'),
(35, 13, 'Útiles escolares', '{\"edificio0\":50}', 1, 0, '2022-08-29 17:32:55', '2022-08-29 17:32:55'),
(36, 22, 'CEMENTO Y TINACOS', '[]', 1, 1, '2022-09-01 10:59:17', '2022-09-01 10:59:17'),
(37, 22, 'APOYO DIRECTO', '[]', 1, 1, '2022-09-01 10:59:27', '2022-09-01 10:59:27'),
(38, 22, 'MANITA DE GATO', '[]', 1, 5, '2022-09-01 10:59:34', '2022-09-01 10:59:34'),
(39, 22, 'MILITANTES', '[]', 1, 3, '2022-09-01 10:59:40', '2022-09-01 10:59:40'),
(40, 22, 'REG COM', '[]', 1, 1, '2022-09-01 11:00:02', '2022-09-01 11:00:02'),
(41, 22, 'COMUNICABLE', '[]', 0, 1, '2022-09-01 11:00:13', '2022-09-07 16:29:39'),
(42, 22, 'CONTACTADO', '[]', 1, 1, '2022-09-01 11:00:19', '2022-09-01 11:00:19'),
(43, 25, 'DETECCION 21', '[]', 1, 5, '2022-09-01 12:42:09', '2022-09-06 19:26:14'),
(44, 25, 'APOYO DIRECTO', '[]', 1, 1, '2022-09-01 12:42:19', '2022-09-01 12:42:19'),
(45, 25, 'CALENTADORES SOLARES', '[]', 1, 1, '2022-09-01 12:42:31', '2022-09-01 12:42:31'),
(46, 25, 'CIRCULO PRIMARIO', '[]', 1, 5, '2022-09-01 12:42:42', '2022-09-01 12:42:42'),
(47, 25, 'DESAYUNOS ESCOLARES', '[]', 1, 1, '2022-09-01 12:42:55', '2022-09-01 12:42:55'),
(48, 25, 'EE', '[]', 1, 3, '2022-09-01 12:43:10', '2022-09-01 12:43:10'),
(49, 25, 'CEMENTO', '[]', 1, 1, '2022-09-01 12:43:22', '2022-09-01 12:43:22'),
(50, 25, 'ENLACES COMUNITARIOS', '[]', 1, 3, '2022-09-01 12:43:36', '2022-09-01 12:43:36'),
(51, 25, 'MILITANTES', '[]', 1, 3, '2022-09-01 12:43:53', '2022-09-01 12:43:53'),
(52, 25, 'TINACOS', '[]', 1, 1, '2022-09-01 12:44:06', '2022-09-01 12:44:06'),
(53, 25, 'REG COM 1', '[]', 0, 1, '2022-09-01 12:44:25', '2022-09-06 19:27:46'),
(54, 25, 'REG COM 2', '[]', 0, 2, '2022-09-01 12:44:35', '2022-09-06 19:27:50'),
(55, 25, 'REG COM 3', '[]', 0, 3, '2022-09-01 12:44:43', '2022-09-06 19:27:54'),
(56, 25, 'CONTACTO', '[]', 0, 1, '2022-09-01 12:44:55', '2022-09-06 19:27:31'),
(57, 25, 'COMUNICABLE', '[]', 0, 1, '2022-09-01 12:45:01', '2022-09-06 19:27:26'),
(58, 25, 'TEST', '[]', 0, 2, '2022-09-06 18:29:30', '2022-09-06 18:40:13'),
(59, 22, 'DESPENSAS', '[]', 0, 1, '2022-09-07 16:34:53', '2022-09-07 16:35:41'),
(60, 26, '2021 DS', '[]', 1, 1, '2022-09-08 11:46:36', '2022-09-08 11:47:06'),
(61, 26, '2022 DS', '[]', 1, 1, '2022-09-08 11:46:41', '2022-09-08 11:47:09'),
(62, 26, 'CREDENCIAL', '[]', 1, 1, '2022-09-08 11:46:47', '2022-09-08 11:47:15'),
(63, 26, 'APOYOS', '[]', 1, 1, '2022-09-08 11:46:55', '2022-09-08 11:47:13'),
(64, 26, 'MEDICAMENTOS', '[]', 1, 1, '2022-09-08 11:47:00', '2022-09-08 11:47:18'),
(65, 26, 'SOLICITUDES', '[]', 1, 1, '2022-09-08 11:47:04', '2022-09-08 11:47:23'),
(66, 27, 'DET 21', '[]', 1, 3, '2022-09-08 17:06:09', '2022-09-08 17:06:09'),
(67, 27, 'RED FAM 21', '[]', 1, 3, '2022-09-08 17:06:16', '2022-09-08 17:06:16'),
(68, 27, 'MILITANTE', '[]', 1, 5, '2022-09-08 17:06:22', '2022-09-08 17:06:22');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `catalogo_actividad`
--
ALTER TABLE `catalogo_actividad`
  ADD PRIMARY KEY (`idcatalogo_actividad`),
  ADD KEY `idcuenta` (`idcuenta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `catalogo_actividad`
--
ALTER TABLE `catalogo_actividad`
  MODIFY `idcatalogo_actividad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
