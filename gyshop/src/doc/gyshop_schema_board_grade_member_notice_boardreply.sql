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
  `comment` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gyshop`.`grade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gyshop`.`grade` ;

CREATE TABLE IF NOT EXISTS `gyshop`.`grade` (
  `gradeNo` INT NOT NULL,
  `gradeName` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`gradeNo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gyshop`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gyshop`.`member` ;

CREATE TABLE IF NOT EXISTS `gyshop`.`member` (
  `id` VARCHAR(20) NOT NULL,
  `pw` VARCHAR(20) NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `gender` VARCHAR(8) NOT NULL COMMENT '남자/여자',
  `birth` DATETIME NOT NULL,
  `tel` VARCHAR(13) NULL,
  `zipcode` VARCHAR(5) NULL,
  `addr1` VARCHAR(200) NULL,
  `addr2` VARCHAR(200) NULL,
  `email` VARCHAR(45) NOT NULL,
  `regDate` DATETIME NULL DEFAULT now(),
  `conDate` DATETIME NULL DEFAULT now(),
  `photo` VARCHAR(200) NULL,
  `status` VARCHAR(20) NULL DEFAULT '정상',
  `gradeNo` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_member_grade_idx` (`gradeNo` ASC) VISIBLE,
  CONSTRAINT `fk_member_grade`
    FOREIGN KEY (`gradeNo`)
    REFERENCES `gyshop`.`grade` (`gradeNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gyshop`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gyshop`.`notice` ;

CREATE TABLE IF NOT EXISTS `gyshop`.`notice` (
  `no` BIGINT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(400) NOT NULL,
  `content` VARCHAR(4000) NULL,
  `image` VARCHAR(200) NULL,
  `startDate` DATETIME NOT NULL DEFAULT now(),
  `endDate` DATETIME NOT NULL DEFAULT '9999-12-31',
  `writeDate` DATETIME NOT NULL DEFAULT now(),
  `hit` BIGINT NULL DEFAULT 0,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `gyshop`.`boardreply`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `gyshop`.`boardreply` ;

CREATE TABLE IF NOT EXISTS `gyshop`.`boardreply` (
  `rno` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(800) NOT NULL,
  `id` VARCHAR(20) NOT NULL,
  `no` BIGINT NOT NULL,
  `writeDate` DATETIME NOT NULL DEFAULT now(),
  PRIMARY KEY (`rno`),
  INDEX `fk_boardreply_member1_idx` (`id` ASC) VISIBLE,
  INDEX `fk_boardreply_board1_idx` (`no` ASC) VISIBLE,
  CONSTRAINT `fk_boardreply_member1`
    FOREIGN KEY (`id`)
    REFERENCES `gyshop`.`member` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_boardreply_board1`
    FOREIGN KEY (`no`)
    REFERENCES `gyshop`.`board` (`no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
