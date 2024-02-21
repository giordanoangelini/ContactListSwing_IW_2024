-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Creato il: Feb 21, 2024 alle 08:38
-- Versione del server: 5.7.39
-- Versione PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rubrica_db`
--
CREATE DATABASE IF NOT EXISTS `rubrica_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `rubrica_db`;

-- --------------------------------------------------------

--
-- Struttura della tabella `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `cognome` varchar(50) DEFAULT NULL,
  `indirizzo` varchar(100) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `eta` int(11) DEFAULT NULL,
  `persona` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE `utente` (
  `username` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `utente`
--

INSERT INTO `utente` (`username`, `password`) VALUES
('giordano', '$2a$10$U5i3KkTbeds2SIMcyWg5tulio8w1FWo8mthfyDHJgTNZPall4tOWC'),
('lorenzo', '$2a$10$Y5DLsJhyxZ1xV9AdvDac0.3mDanZYEAOlpH7V25JrxrkqFcXb1L0W');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_UTENTE` (`persona`);

--
-- Indici per le tabelle `utente`
--
ALTER TABLE `utente`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `FK_UTENTE` FOREIGN KEY (`persona`) REFERENCES `utente` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
