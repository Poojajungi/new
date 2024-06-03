-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 03, 2024 at 04:00 PM
-- Server version: 5.7.36
-- PHP Version: 7.1.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `medical_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorytbl`
--

DROP TABLE IF EXISTS `categorytbl`;
CREATE TABLE IF NOT EXISTS `categorytbl` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `categorytbl`
--

INSERT INTO `categorytbl` (`id`, `category_name`) VALUES
(1, 'Anti-Biotics'),
(2, 'AntiVirals');

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
CREATE TABLE IF NOT EXISTS `company` (
  `cid` int(3) NOT NULL AUTO_INCREMENT,
  `cname` varchar(50) NOT NULL,
  `caddress` varchar(100) NOT NULL,
  `city` varchar(40) NOT NULL,
  `pincode` int(6) NOT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`cid`, `cname`, `caddress`, `city`, `pincode`) VALUES
(1, 'cetapill', 'near M.G.ROAD', 'porbandar', 360575),
(2, 'cipla', 'Near mumbai ,Maharashtra', 'Mumbai', 400001);

-- --------------------------------------------------------

--
-- Table structure for table `meditbl`
--

DROP TABLE IF EXISTS `meditbl`;
CREATE TABLE IF NOT EXISTS `meditbl` (
  `mid` int(3) NOT NULL AUTO_INCREMENT,
  `mname` varchar(50) NOT NULL,
  `qty` int(5) NOT NULL,
  `rate` float NOT NULL,
  `mfg_date` date NOT NULL,
  `exp_date` date NOT NULL,
  `batch` varchar(10) NOT NULL,
  `company_name` varchar(40) NOT NULL,
  `amt` float NOT NULL,
  `category` varchar(30) NOT NULL,
  `gst` float NOT NULL,
  `tot_amt` float NOT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meditbl`
--

INSERT INTO `meditbl` (`mid`, `mname`, `qty`, `rate`, `mfg_date`, `exp_date`, `batch`, `company_name`, `amt`, `category`, `gst`, `tot_amt`) VALUES
(1, 'abc', 12, 12, '2024-01-12', '2025-12-05', 'abce', 'abced', 123, 'dgew', 325, 567),
(2, 'bcaa', 2, 10, '2024-01-22', '2026-05-25', 'neww', 'bcaa', 20, '30.0', 1, 21),
(3, 'pdm', 5, 250, '2024-03-12', '2026-04-11', 'abc321', 'cetapill', 125, 'medi', 18.75, 143.75),
(4, 'peracetamol', 40, 20, '2024-06-03', '2024-06-29', 'defer', 'cetapill', 80, 'Anti-Biotics', 12, 92),
(5, 'new', 70, 25, '2024-06-03', '2024-06-30', 'jkgj', 'cetapill', 140, 'Anti-Biotics', 21, 161),
(6, 'pj', 80, 12, '2024-06-28', '2024-07-05', 'rfref', 'cetapill', 80, 'AntiVirals', 12, 92);

-- --------------------------------------------------------

--
-- Table structure for table `productstbl`
--

DROP TABLE IF EXISTS `productstbl`;
CREATE TABLE IF NOT EXISTS `productstbl` (
  `pid` int(3) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(50) NOT NULL,
  `category_id` int(3) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `ct_id` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productstbl`
--

INSERT INTO `productstbl` (`pid`, `product_name`, `category_id`) VALUES
(1, 'peracetamol', 1),
(2, 'new', 1),
(3, 'pj', 2);

-- --------------------------------------------------------

--
-- Table structure for table `reordertbl`
--

DROP TABLE IF EXISTS `reordertbl`;
CREATE TABLE IF NOT EXISTS `reordertbl` (
  `mid` int(3) NOT NULL AUTO_INCREMENT,
  `mname` varchar(50) NOT NULL,
  `qty` int(5) NOT NULL,
  `rate` float NOT NULL,
  `mfg_date` date NOT NULL,
  `exp_date` date NOT NULL,
  `batch` varchar(10) NOT NULL,
  `company_name` varchar(40) NOT NULL,
  `amt` float NOT NULL,
  `category` varchar(30) NOT NULL,
  `gst` float NOT NULL,
  `tot_amt` float NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reordertbl`
--

INSERT INTO `reordertbl` (`mid`, `mname`, `qty`, `rate`, `mfg_date`, `exp_date`, `batch`, `company_name`, `amt`, `category`, `gst`, `tot_amt`, `order_date`) VALUES
(3, 'percetamol', 10, 20, '2024-05-16', '2025-05-29', 'dhjfygey', 'frgrg', 20, 'jhhjgfdf', 3, 23, '2024-05-27 06:13:10'),
(5, 'pdm', 90, 20, '2024-03-12', '2026-04-11', 'abc321', 'ghfghjh', 180, 'medi', 27, 207, '2024-05-29 18:03:50'),
(6, 'abc', 12, 12, '2024-01-12', '2025-12-05', 'abce', 'abced', 123, 'dgew', 325, 567, '2024-05-29 18:04:01');

-- --------------------------------------------------------

--
-- Table structure for table `returnstock`
--

DROP TABLE IF EXISTS `returnstock`;
CREATE TABLE IF NOT EXISTS `returnstock` (
  `mid` int(3) NOT NULL AUTO_INCREMENT,
  `mname` varchar(50) NOT NULL,
  `qty` int(5) NOT NULL,
  `rate` float NOT NULL,
  `mfg_date` date NOT NULL,
  `exp_date` date NOT NULL,
  `batch` varchar(10) NOT NULL,
  `company_name` varchar(40) NOT NULL,
  `amt` float NOT NULL,
  `category` varchar(30) NOT NULL,
  `gst` float NOT NULL,
  `tot_amt` float NOT NULL,
  `order_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `return_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`mid`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `returnstock`
--

INSERT INTO `returnstock` (`mid`, `mname`, `qty`, `rate`, `mfg_date`, `exp_date`, `batch`, `company_name`, `amt`, `category`, `gst`, `tot_amt`, `order_date`, `return_date`) VALUES
(1, 'bcaa', 2, 10, '2024-01-22', '2026-05-25', 'neww', 'bcaa', 20, '30.0', 1, 21, '2024-05-26 18:21:58', '2024-05-26 18:30:30'),
(2, 'abc', 12, 12, '2024-01-12', '2025-12-05', 'abce', 'abced', 123, 'dgew', 325, 567, '2024-05-26 18:21:48', '2024-05-27 06:11:11'),
(3, 'abc', 12, 12, '2024-01-12', '2025-12-05', 'abce', 'abced', 123, 'dgew', 325, 567, '2024-05-27 06:13:19', '2024-05-28 13:51:33'),
(4, 'bcaa', 2, 10, '2024-01-22', '2026-05-25', 'neww', 'bcaa', 20, '30.0', 1, 21, '2024-05-29 18:04:09', '2024-05-29 18:04:56'),
(5, 'bcaa', 2, 10, '2024-01-22', '2026-05-25', 'neww', 'bcaa', 20, '30.0', 1, 21, '2024-05-29 18:04:09', '2024-05-29 18:04:56'),
(6, 'percetamol', 10, 20, '2024-05-16', '2025-05-29', 'dhjfygey', 'frgrg', 20, 'jhhjgfdf', 3, 23, '2024-05-29 18:04:18', '2024-05-29 18:05:04'),
(7, 'percetamol', 10, 20, '2024-05-16', '2025-05-29', 'dhjfygey', 'frgrg', 20, 'jhhjgfdf', 3, 23, '2024-05-29 18:04:18', '2024-05-29 18:05:04');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `uid` int(3) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(10) NOT NULL,
  `contactNo` varchar(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`uid`, `username`, `password`, `contactNo`, `email`) VALUES
(1, 'abc', 'abc', '1234567891', 'abc@gmail.com'),
(3, 'pooja', 'pj', '1234567891', 'pj@gmail.com');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
