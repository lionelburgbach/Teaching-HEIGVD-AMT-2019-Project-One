-- MySQL Script generated by MySQL Workbench
-- Fri Nov  8 17:04:53 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sakila
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `sakila` ;

-- -----------------------------------------------------
-- Schema sakila
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sakila` DEFAULT CHARACTER SET utf8 ;
USE `sakila` ;

-- -----------------------------------------------------
-- Table `sakila`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sakila`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `date` DATE NOT NULL,
  `profile_picture` LONGBLOB NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sakila`.`trail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sakila`.`trail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `length` DOUBLE NOT NULL,
  `up_and_down` DOUBLE NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sakila`.`registration`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sakila`.`registration` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_user_fk` INT NOT NULL,
  `id_trail_fk` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_user_fk_idx` (`id_user_fk` ASC),
  INDEX `id_trail_fk_idx` (`id_trail_fk` ASC),
  CONSTRAINT `id_user_fk`
    FOREIGN KEY (`id_user_fk`)
    REFERENCES `sakila`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_trail_fk`
    FOREIGN KEY (`id_trail_fk`)
    REFERENCES `sakila`.`trail` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE USER 'user1';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
