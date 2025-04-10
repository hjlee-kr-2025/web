-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gyshop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gyshop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gyshop` DEFAULT CHARACTER SET utf8 ;
USE `gyshop` ;

-- -----------------------------------------------------
-- Table `gyshop`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gyshop`.`board` ;

CREATE TABLE IF NOT EXISTS `gyshop`.`board` (
  `no` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(400) NOT NULL,
  `content` VARCHAR(4000) NOT NULL,
  `writer` VARCHAR(40) NOT NULL,
  `writeDate` DATETIME NULL DEFAULT now(),
  `hit` BIGINT NULL DEFAULT 0,
  `pw` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
