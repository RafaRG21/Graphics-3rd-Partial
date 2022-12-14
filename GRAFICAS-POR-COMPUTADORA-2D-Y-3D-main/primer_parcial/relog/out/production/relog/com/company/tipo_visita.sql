-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 08-09-2022 a las 19:01:18
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
-- Estructura de tabla para la tabla `tipo_visita`
--

CREATE TABLE `tipo_visita` (
  `idtipo` int(11) NOT NULL,
  `idcuenta` int(11) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `numero_opcion` int(11) NOT NULL,
  `emoji` varchar(10) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `activo` int(11) NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipo_visita`
--

INSERT INTO `tipo_visita` (`idtipo`, `idcuenta`, `nombre`, `observaciones`, `numero_opcion`, `emoji`, `created_at`, `updated_at`, `activo`) VALUES
(1, 2, 'Informes', 'informacion', 3, '', '2023-03-22 12:53:00', '2023-03-22 12:55:00', 1),
(2, 16, 'Edificio Z', 'El edificio del misterio', 4, '', '2023-03-22 19:10:00', '2023-03-22 19:10:00', 1),
(3, 9, 'William', 'hol', 0, '', '2028-03-22 19:09:00', '2030-03-22 17:28:00', 0),
(4, 9, 'William', 'fff', 1, '', '2029-03-22 15:56:00', '2030-03-22 17:28:00', 0),
(5, 9, 'frr', 'rf444', 2, '', '2029-03-22 16:03:00', '2030-03-22 17:28:00', 0),
(6, 9, 'William', 'hhh', 3, '', '2029-03-22 16:51:00', '2030-03-22 17:28:00', 0),
(7, 9, 'Nuevo tipo recargado', 'ninguna45567ssss', 4, '', '2030-03-22 17:06:00', '2030-03-22 17:28:00', 0),
(8, 9, 'nuevo tipo 2', 'asad', 5, '', '2030-03-22 17:08:00', '2030-03-22 17:28:00', 0),
(9, 9, 'Ninguno', 'Ninguna', 1, '', '2030-03-22 17:30:00', '2030-03-22 17:32:00', 0),
(10, 9, 'Tipo 2', 'Ninguna', 2, '', '2030-03-22 17:30:00', '2030-03-22 17:33:00', 0),
(11, 9, 'Tipo 3', 'Ninguna', 3, '', '2030-03-22 17:32:00', '2030-03-22 17:33:00', 0),
(12, 9, 'Tipo 4', 'Ninguna', 4, '', '2030-03-22 17:32:00', '2030-03-22 17:33:00', 0),
(13, 9, 'Insignificante', 'No hay', 1, '', '2030-03-22 17:33:00', '2030-03-22 17:41:00', 0),
(14, 9, 'Nueva 2', 'ssss', 2, '', '2030-03-22 17:36:00', '2030-03-22 17:41:00', 0),
(15, 9, 'Nueva 3', 'sssss', 3, '', '2030-03-22 17:36:00', '2030-03-22 17:42:00', 0),
(16, 9, 'Nueva 4', 'ssss', 4, '', '2030-03-22 17:36:00', '2030-03-22 17:42:00', 0),
(17, 9, 'sss', '4567', 5, '', '2030-03-22 17:37:00', '2030-03-22 17:42:00', 0),
(18, 9, '123456', 'sssss', 6, '', '2030-03-22 17:38:00', '2030-03-22 17:41:00', 0),
(19, 9, 'ssss', '4567', 7, '', '2030-03-22 17:38:00', '2030-03-22 17:42:00', 0),
(20, 9, 'Nuevo tipo', 'Ninguna', 1, '', '2030-03-22 17:42:00', '2030-03-22 17:42:00', 1),
(21, 9, 'Nuevo tipo 2', 'Asi es', 2, '', '2030-03-22 17:42:00', '2030-03-22 17:42:00', 1),
(22, 9, 'Inesperada', 'Es una visita inesperada', 3, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(23, 14, '4913', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(24, 14, '4914', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(25, 14, '4915', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(26, 14, '4916', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(27, 14, '4917', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(28, 14, '4918', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(29, 14, '4919', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(30, 14, '4920', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(31, 14, '4921', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(32, 14, '4922', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(33, 14, '4923', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(34, 14, '4924', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(35, 14, '4925', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(36, 14, '4926', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(37, 14, '4927', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(38, 14, '4928', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(39, 14, '4929', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(40, 14, '4930', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(41, 14, '4931', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(42, 14, '4932', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(43, 14, '4933', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(44, 14, '4934', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(45, 14, '4935', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(46, 14, '4936', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(47, 14, '4937', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(48, 14, '4938', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(49, 14, '4939', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(50, 14, '4940', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(51, 14, '4941', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(52, 14, '4942', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(53, 14, '4943', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(54, 14, '4944', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(55, 14, '4945', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(56, 14, '4946', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(57, 14, '4947', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(58, 14, '4948', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(59, 14, '4949', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(60, 14, '4950', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(61, 14, '4951', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(62, 14, '4952', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(63, 14, '4953', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(64, 14, '4954', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(65, 14, '4955', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(66, 14, '4956', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(67, 14, '4957', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(68, 14, '4958', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(69, 14, '4959', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(70, 14, '4960', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(71, 14, '4961', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(72, 14, '4962', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(73, 14, '4963', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(74, 14, '4964', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(75, 14, '4965', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(76, 14, '4966', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(77, 14, '4967', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(78, 14, '4968', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(79, 14, '4969', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(80, 14, '4970', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(81, 14, '4971', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(82, 14, '4972', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(83, 14, '4973', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(84, 14, '4974', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(85, 14, '4975', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(86, 14, '4976', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(87, 14, '4977', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(88, 14, '4978', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(89, 14, '4979', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(90, 14, '4980', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(91, 14, '4981', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(92, 14, '4982', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(93, 14, '4983', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(94, 14, '4984', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(95, 14, '4985', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(96, 14, '4986', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(97, 14, '4987', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(98, 14, '4989', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(99, 14, '4990', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(100, 14, '4992', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(101, 14, '4993', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(102, 14, '4994', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(103, 14, '4995', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(104, 14, '4996', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(105, 14, '4997', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(106, 14, '4998', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(107, 14, '4999', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(108, 14, '5000', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(109, 14, '5001', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(110, 14, '5002', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(111, 14, '5003', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(112, 14, '5004', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(113, 14, '5005', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(114, 14, '5006', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(115, 14, '5007', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(116, 14, '5008', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(117, 14, '5009', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(118, 14, '5010', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(119, 14, '5012', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(120, 14, '5013', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(121, 14, '5014', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(122, 14, '5015', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(123, 14, '5016', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(124, 14, '5017', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(125, 14, '5018', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(126, 14, '5019', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(127, 14, '5020', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(128, 14, '5021', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(129, 14, '5022', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(130, 14, '5023', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(131, 14, '5024', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(132, 14, '5025', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(133, 14, '5026', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(134, 14, '5027', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(135, 14, '5028', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(136, 14, '5029', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(137, 14, '5030', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(138, 14, '5031', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(139, 14, '5032', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(140, 14, '5033', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(141, 14, '5034', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(142, 14, '5035', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(143, 14, '5036', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(144, 14, '5037', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(145, 14, '5038', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(146, 14, '5039', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(147, 14, '5040', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(148, 14, '5041', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(149, 14, '5042', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(150, 14, '5043', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(151, 14, '5044', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(152, 14, '5045', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(153, 14, '5046', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(154, 14, '5047', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(155, 14, '5048', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(156, 14, '5049', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(157, 14, '5050', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(158, 14, '5051', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(159, 14, '5052', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(160, 14, '5053', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(161, 14, '5054', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(162, 14, '5055', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(163, 14, '5056', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(164, 14, '5057', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(165, 14, '5058', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(166, 14, '5059', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(167, 14, '5060', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(168, 14, '5061', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(169, 14, '5062', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(170, 14, '5063', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(171, 14, '5064', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(172, 14, '5065', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(173, 14, '5066', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(174, 14, '5067', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(175, 14, '5068', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(176, 14, '5069', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(177, 14, '5070', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(178, 14, '5071', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(179, 14, '5072', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(180, 14, '5073', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(181, 14, '5074', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(182, 14, '5075', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(183, 14, '5076', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(184, 14, '5077', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(185, 14, '5078', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(186, 14, '5079', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(187, 14, '5080', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(188, 14, '5081', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(189, 14, '5082', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(190, 14, '5083', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(191, 14, '5084', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(192, 14, '5085', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(193, 14, '5086', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(194, 14, '5087', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(195, 14, '5088', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(196, 14, '5089', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(197, 14, '5090', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(198, 14, '5098', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(199, 14, '5099', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(200, 14, '5100', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(201, 14, '5101', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(202, 14, '5102', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(203, 14, '5103', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(204, 14, '5104', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(205, 14, '5105', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(206, 14, '5106', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(207, 14, '5107', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(208, 14, '5108', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(209, 14, '5109', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(210, 14, '5110', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(211, 14, '5111', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(212, 14, '5112', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(213, 14, '5113', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(214, 14, '5114', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(215, 14, '5115', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(216, 14, '5116', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(217, 14, '5117', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(218, 14, '5118', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(219, 14, '5119', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(220, 14, '5120', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(221, 14, '5121', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(222, 14, '5122', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(223, 14, '5123', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(224, 14, '5124', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(225, 14, '5125', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(226, 14, '5126', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(227, 14, '5127', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(228, 14, '5128', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(229, 14, '5129', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(230, 14, '5130', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(231, 14, '5131', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(232, 14, '5132', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(233, 14, '5133', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(234, 14, '5134', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(235, 14, '5135', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(236, 14, '5136', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(237, 14, '5137', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(238, 14, '5138', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(239, 14, '5139', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(240, 14, '5140', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(241, 14, '5141', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(242, 14, '5142', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(243, 14, '5143', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(244, 14, '5144', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(245, 14, '5145', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(246, 14, '5146', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(247, 14, '5147', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(248, 14, '5148', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(249, 14, '5149', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(250, 14, '5150', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(251, 14, '5151', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(252, 14, '5152', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(253, 14, '5153', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(254, 14, '5154', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(255, 14, '5155', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(256, 14, '5156', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(257, 14, '5157', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(258, 14, '5158', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(259, 14, '5159', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(260, 14, '5160', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(261, 14, '5161', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(262, 14, '5162', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(263, 14, '5163', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(264, 14, '5164', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(265, 14, '5165', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(266, 14, '5166', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(267, 14, '5167', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(268, 14, '5168', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(269, 14, '5169', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(270, 14, '5170', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(271, 14, '5171', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(272, 14, '5172', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(273, 14, '5173', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(274, 14, '5174', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(275, 14, '5175', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(276, 14, '5176', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(277, 14, '5177', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(278, 14, '5178', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(279, 14, '5179', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(280, 14, '5180', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(281, 14, '5181', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(282, 14, '5182', '', 1, '', '2030-03-22 17:43:00', '2030-03-22 17:43:00', 1),
(283, 14, '1089', 'SECTOR', 2, '', '2022-07-20 11:15:44', '2022-07-20 11:15:44', 1),
(284, 13, 'APOYO', NULL, 1, '', '2022-08-25 13:23:11', '2022-08-25 13:23:11', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tipo_visita`
--
ALTER TABLE `tipo_visita`
  ADD PRIMARY KEY (`idtipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tipo_visita`
--
ALTER TABLE `tipo_visita`
  MODIFY `idtipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=285;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
