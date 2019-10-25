/*
-- Query: 
-- Date: 2019-10-17 15:44
*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE sakila;

SET AUTOCOMMIT=0;
INSERT INTO user VALUES (1,'lionel','burgbacher','lionel.burgbacher@heig-vd.ch','lionel','1989-03-05');
INSERT INTO user VALUES (2,'amt','amt','amt@amt.ch','amt','2019-10-10');
COMMIT;

SET AUTOCOMMIT=0;
INSERT INTO trail VALUES (1,'Sierre-Zinal',31,3300,'The Sierre-Zinal race is a mountain running race that takes place in the canton of Valais each August. It is also known as the race of five 4000ers ("La course des cinq 4000"), as five peaks over four thousand meters are visible along its path: Weisshorn (4506 m), Zinalrothorn (4221 m), Obergabelhorn (4073 m) Matterhorn (4478 m) and Dent Blanche (4357 m).',1000,'2020-08-20');
INSERT INTO trail VALUES (2,'Matterhorn Ultraks',24,2800,'The Matterhorn Ultraks is an international skyrunning competition held for the first time in 1982. It runs every year in Zermatt (Switzerland) in August, race valid for the Skyrunner World Series till 2010.',1000,'2020-06-12');
INSERT INTO trail VALUES (3,'Ultra-Trail du Mont-Blanc',171,10040,'The Ultra-Trail du Mont-Blanc (UTMB) is a single-stage mountain ultramarathon first held in 2003. It is a race of the Ultra-Trail World Tour.',1000,'2020-07-24');
INSERT INTO trail VALUES (4,'Kilometre vertical de Fully',1.9,1000,'The Kilometre vertical de Fully is an international skyrunning competition held for the first time in 2001. It rans every year in July in Fully (Switzerland) and is valid for the Vertical Kilometer World Circuit.',1000,'2020-05-11');
INSERT INTO trail VALUES (5,'Ultra-Trail Mt. Fuji',168,9500,'The Ultra-Trail Mt. Fuji is an annual ultramarathon sporting event in Yamanashi Prefecture and Shizuoka Prefecture, Japan, started in 2012.[1] The total distance is 168 km, the cumulative altitude gain is 9,500m, and the time limit is 46 hours. It is one of the Ultra-Trail World Tour.[4] It is a sister sport event of Ultra-Trail du Mont-Blanc.',1000,'2019-09-30');
COMMIT;
