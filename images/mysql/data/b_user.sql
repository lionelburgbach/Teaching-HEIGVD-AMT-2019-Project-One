/*
-- Query: 
-- Date: 2019-10-17 15:44
*/

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

USE sakila;

SET AUTOCOMMIT=0;
INSERT INTO user VALUES (1,'Lionel','Burgbacher','lionel.burgbacher@heig-vd.ch','d03eae2969f2036a14df1be75bb9b945cd9ef9836fde56e13d88ac7c16a6adeaaace4a626e58520deed44f27d3214f516d67e9c67f829eade42c87ee5f22b495','1989-03-05',NULL);
INSERT INTO user VALUES (2,'Guillaume','Blanco','guillaume.blanco@heig-vd.ch','fd1862846a6539112654b775145ee53ca8606b67451b0cad24c75ea2f34248dd831751ab79ea940edf37a36abadafd0865c11ca4770a20d5cd39a236661c595b','1989-03-05',NULL);
INSERT INTO user VALUES (3,'firstname','lastname','test','ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff','2019-10-10',NULL);
COMMIT;

SET AUTOCOMMIT=0;
INSERT INTO trail VALUES (1,'Sierre-Zinal',31,3300,'The Sierre-Zinal race is a mountain running race that takes place in the canton of Valais each August. It is also known as the race of five 4000ers ("La course des cinq 4000"), as five peaks over four thousand meters are visible along its path: Weisshorn (4506 m), Zinalrothorn (4221 m), Obergabelhorn (4073 m) Matterhorn (4478 m) and Dent Blanche (4357 m).',1000,'2020-08-20');
INSERT INTO trail VALUES (2,'Matterhorn Ultraks',24,2800,'The Matterhorn Ultraks is an international skyrunning competition held for the first time in 1982. It runs every year in Zermatt (Switzerland) in August, race valid for the Skyrunner World Series till 2010.',1000,'2020-06-12');
INSERT INTO trail VALUES (3,'Ultra-Trail du Mont-Blanc',171,10040,'The Ultra-Trail du Mont-Blanc (UTMB) is a single-stage mountain ultramarathon first held in 2003. It is a race of the Ultra-Trail World Tour.',1000,'2020-07-24');
INSERT INTO trail VALUES (4,'Kilometre vertical de Fully',1.9,1000,'The Kilometre vertical de Fully is an international skyrunning competition held for the first time in 2001. It rans every year in July in Fully (Switzerland) and is valid for the Vertical Kilometer World Circuit.',1000,'2020-05-11');
INSERT INTO trail VALUES (5,'Ultra-Trail Mt. Fuji',168,9500,'The Ultra-Trail Mt. Fuji is an annual ultramarathon sporting event in Yamanashi Prefecture and Shizuoka Prefecture, Japan, started in 2012.[1] The total distance is 168 km, the cumulative altitude gain is 9,500m, and the time limit is 46 hours. It is one of the Ultra-Trail World Tour.[4] It is a sister sport event of Ultra-Trail du Mont-Blanc.',1000,'2019-09-30');
COMMIT;

SET AUTOCOMMIT=0;
INSERT INTO registration VALUES (1,3,'2020-08-20',1,2);
INSERT INTO registration VALUES (2,3,'2020-08-20',1,3);
INSERT INTO registration VALUES (3,3,'2020-08-20',1,4);
INSERT INTO registration VALUES (4,3,'2020-08-20',2,2);
INSERT INTO registration VALUES (5,3,'2020-08-20',2,3);
INSERT INTO registration VALUES (6,3,'2020-08-20',2,4);
COMMIT;

SET AUTOCOMMIT=0;
INSERT INTO result VALUES (1,1000,1);
INSERT INTO result VALUES (2,1001,2);
INSERT INTO result VALUES (3,1002,3);
INSERT INTO result VALUES (4,1003,4);
INSERT INTO result VALUES (5,1004,5);
INSERT INTO result VALUES (6,1005,6);
COMMIT;
