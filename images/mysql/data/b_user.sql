/*
-- Query: 
-- Date: 2019-10-17 15:44
*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE sakila;

SET AUTOCOMMIT=0;

INSERT INTO user VALUES (1,'lionel','burgbacher','lionel.burgbacher@heig-vd.ch','lionel');
INSERT INTO user VALUES (2,'adlg','adlg','adlg@amt.ch','amt');
COMMIT;
