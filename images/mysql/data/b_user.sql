/*
-- Query: 
-- Date: 2019-10-17 15:44
*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE sakila;

SET AUTOCOMMIT=0;
INSERT INTO user VALUES (1,'lionel','burgbacher','lionel.burgbacher@heig-vd.ch','lionel','1989-03-05',1);
INSERT INTO user VALUES (2,'amt','amt','amt@amt.ch','amt','2019-10-10',1);
COMMIT;

SET AUTOCOMMIT=0;
INSERT INTO trail VALUES (1,'Sierre-Zinal',31,3.3,'Super trail entre Sierre et Zianle, plus de 2200 dénivenlé positive et 1100 négative.',1000,'2019-08-20');
INSERT INTO trail VALUES (2,'100 milles',169.93,3.3,'IDK',1000,'2019-08-22');
COMMIT;
