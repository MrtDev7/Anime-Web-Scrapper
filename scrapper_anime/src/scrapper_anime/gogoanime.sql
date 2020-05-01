-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2020 at 11:43 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gogoanime`
--

-- --------------------------------------------------------

--
-- Table structure for table `download`
--

CREATE TABLE `download` (
  `ID` int(11) NOT NULL,
  `Server_Name` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Download_Type` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Download_File_Size` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Download_Quility` text COLLATE utf8_unicode_ci NOT NULL,
  `Data_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `Download_Url` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `episodes`
--

CREATE TABLE `episodes` (
  `ID` int(11) NOT NULL,
  `Episode_Titel` text COLLATE utf8_unicode_ci NOT NULL,
  `Episode_Descreption` text COLLATE utf8_unicode_ci NOT NULL,
  `Series_Name` text COLLATE utf8_unicode_ci NOT NULL,
  `Sereis_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `Episode_Number` int(11) NOT NULL,
  `Episode_View` int(11) NOT NULL,
  `Likes_Count` int(11) NOT NULL,
  `Episode_Rate` text COLLATE utf8_unicode_ci NOT NULL,
  `Episode_ReleaseDate` text COLLATE utf8_unicode_ci NOT NULL,
  `Episode_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `Saison_Number` text COLLATE utf8_unicode_ci NOT NULL,
  `Saison_Name` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE `images` (
  `ID` int(11) NOT NULL,
  `Img_Url` text COLLATE utf8_unicode_ci NOT NULL,
  `Image_Type` text COLLATE utf8_unicode_ci NOT NULL,
  `Data_ID` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `ID` int(11) NOT NULL,
  `Movie_Tital` text COLLATE utf8_unicode_ci NOT NULL,
  `Movie_desc` text COLLATE utf8_unicode_ci NOT NULL,
  `Movie_Name` text COLLATE utf8_unicode_ci NOT NULL,
  `Movie_Story` text COLLATE utf8_unicode_ci NOT NULL,
  `Movie_gender` text COLLATE utf8_unicode_ci NOT NULL,
  `Movie_Date` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Movie_quilty` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Movie_section` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Movie_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `Imdb_Key` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `View_count` text COLLATE utf8_unicode_ci NOT NULL,
  `Likes_Count` text COLLATE utf8_unicode_ci NOT NULL,
  `Imdb_Rate` text COLLATE utf8_unicode_ci NOT NULL,
  `Local_Rate` text COLLATE utf8_unicode_ci NOT NULL,
  `Search_keywords` text COLLATE utf8_unicode_ci NOT NULL,
  `Movie_Time` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `Director` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `OtherName` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `ID` int(11) NOT NULL,
  `Post_Title` text NOT NULL,
  `Post_PreviewImg` text NOT NULL,
  `Data_ID` text NOT NULL,
  `Post_Type` varchar(35) NOT NULL,
  `View_count` int(11) NOT NULL,
  `Likes_Count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `series`
--

CREATE TABLE `series` (
  `ID` int(11) NOT NULL,
  `Serie_Titel` text COLLATE utf8_unicode_ci NOT NULL,
  `Serie_Desc` text COLLATE utf8_unicode_ci NOT NULL,
  `Serie_Name` text COLLATE utf8_unicode_ci NOT NULL,
  `Serie_Story` text COLLATE utf8_unicode_ci NOT NULL,
  `Serie_genrder` text COLLATE utf8_unicode_ci NOT NULL,
  `Serie_Date` text COLLATE utf8_unicode_ci NOT NULL,
  `Serie_quilty` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Serie_section` varchar(34) COLLATE utf8_unicode_ci NOT NULL,
  `Serie_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `Imdb_Key` text COLLATE utf8_unicode_ci NOT NULL,
  `View_count` int(11) NOT NULL,
  `Likes_Count` int(11) NOT NULL,
  `Imdb_Rate` text COLLATE utf8_unicode_ci NOT NULL,
  `Local_Rate` text COLLATE utf8_unicode_ci NOT NULL,
  `Search_keywords` text COLLATE utf8_unicode_ci NOT NULL,
  `Episode_Time` text COLLATE utf8_unicode_ci NOT NULL,
  `Director` text COLLATE utf8_unicode_ci NOT NULL,
  `OtherName` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `videos`
--

CREATE TABLE `videos` (
  `ID` int(11) NOT NULL,
  `Video_Link` text COLLATE utf8_unicode_ci NOT NULL,
  `Video_type` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Data_ID` text COLLATE utf8_unicode_ci NOT NULL,
  `Video_gender` varchar(35) COLLATE utf8_unicode_ci NOT NULL,
  `Server_Name` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `download`
--
ALTER TABLE `download`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `episodes`
--
ALTER TABLE `episodes`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `images`
--
ALTER TABLE `images`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `posts`
--
ALTER TABLE `posts`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `series`
--
ALTER TABLE `series`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `videos`
--
ALTER TABLE `videos`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `download`
--
ALTER TABLE `download`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `episodes`
--
ALTER TABLE `episodes`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `images`
--
ALTER TABLE `images`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `posts`
--
ALTER TABLE `posts`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `series`
--
ALTER TABLE `series`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `videos`
--
ALTER TABLE `videos`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
