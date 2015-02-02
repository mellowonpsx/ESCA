-- phpMyAdmin SQL Dump
-- version 4.0.10.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 28, 2015 at 02:53 PM
-- Server version: 5.1.73
-- PHP Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ESCA`
--

-- --------------------------------------------------------

--
-- Table structure for table `configuration`
--

CREATE TABLE `configuration` (
  `parameterName` varchar(100) COLLATE utf8_bin NOT NULL,
  `value` text COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`parameterName`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `configuration`
--

INSERT INTO `configuration` (`parameterName`, `value`) VALUES
('consumer_key', 'your_tw_key'),
('consumer_secret', 'your_tw_secret'),
('configured_source_twitter-flume-sampling-module', 'false'),
('source_names', '[{"name":"twitter-flume-sampling-module"},{"name":"twitter-flume-user-module"}]'),
('analysis_names', '[{"name":"sentiment-analysis-module"}]'),
('configured_analysis_sentiment-analysis-module', 'false'),
('configured_source_twitter-flume-user-module', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `geographic_report`
--

CREATE TABLE `geographic_report` (
  `full_date_time` bigint(20) NOT NULL,
  `count_entity` bigint(20) NOT NULL,
  `user_time_zone` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`full_date_time`,`user_time_zone`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `sentiment_data`
--

CREATE TABLE `sentiment_data` (
  `id` bigint(20) NOT NULL,
  `created_at` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `text` text COLLATE utf8_bin,
  `type` varchar(50) COLLATE utf8_bin NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_username` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_name` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_lang` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `user_influence` bigint(20) DEFAULT NULL,
  `user_time_zone` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `polarity` bigint(20) DEFAULT NULL,
  `sentiment` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `data_timestamp` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`,`type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `sentiment_report`
--

CREATE TABLE `sentiment_report` (
  `full_date_time` bigint(20) NOT NULL COMMENT 'each day has a single report',
  `sentiment_value` bigint(20) DEFAULT NULL COMMENT 'count(positive) - count(negative)',
  `polarity_value` bigint(20) DEFAULT NULL COMMENT 'sum(polarity)',
  `positive_sentiment` bigint(20) DEFAULT NULL COMMENT 'count(positive)',
  `negative_sentiment` bigint(20) DEFAULT NULL COMMENT 'count(negative)',
  `neutral_sentiment` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`full_date_time`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
