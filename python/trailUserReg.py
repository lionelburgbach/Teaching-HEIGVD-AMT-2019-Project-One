from random import randint
import hashlib
import names
from faker import Faker
fake = Faker()

fichier = open("b_trail.sql", "a")

#ICI POUR CHANGER LE NOMBRE DE USER ET DE TRAIL
nbUser = 100
nbTrail = 50000


count = 0
#header
fichier.write("/* -- Query:\n-- Date: 2019-10-17 15:44\n*/\n\nSET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;\nSET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;\nSET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';\n\nUSE sakila;\n\n")

fichier.write("SET AUTOCOMMIT=0;\nINSERT INTO user VALUES (1,'Lionel','Burgbacher','1989-03-05', NULL,'lionel.burgbacher@heig-vd.ch','d03eae2969f2036a14df1be75bb9b945cd9ef9836fde56e13d88ac7c16a6adeaaace4a626e58520deed44f27d3214f516d67e9c67f829eade42c87ee5f22b495');\nINSERT INTO user VALUES (2,'Guillaume','Blanco','1993-05-25', NULL,'guillaume.blanco@heig-vd.ch', 'fd1862846a6539112654b775145ee53ca8606b67451b0cad24c75ea2f34248dd831751ab79ea940edf37a36abadafd0865c11ca4770a20d5cd39a236661c595b');\n")
#Generate user
tablEndMail = ['@heig-vd.ch', '@gamil.com', '@trail.com', '@amtseeyounextyear.com', '@amtsucced.com', '@bestcoursever.ch', '@aaa.ch', '@aab.ch', '@aac.ch', '@aad.ch', '@aaf.ch', '@aag.ch', '@aah.ch', '@aai.ch', '@aaj.ch', '@aak.ch', '@aal.ch', '@aam.ch',]
tabMois = ['01','02','03','04','05','06','07','08','09','10','11','12']
tabJours = ['01','02','03','04','05','06','07','08','09','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24','25','26','27','28','29','30']


for i in range(3, nbUser):
    count += 1
    strVal = "INSERT INTO user VALUES ("
    firstname = names.get_first_name()
    lastname = names.get_last_name()
    mois = tabMois[randint(0,len(tabMois)-1)]
    if mois == '02':
        mois = '01'
    pwd = hashlib.sha512(firstname).hexdigest()
    strVal += str(i) + "," + "'" +firstname+ "','" +lastname+ "','" + str(randint(1950,2017)) + "-" + mois + "-" + tabJours[randint(0,len(tabJours)-1)] + "', NULL,'" +firstname.lower()+ "." +lastname.lower()+""+tablEndMail[i%(len(tablEndMail)-1)]+ "','" +pwd+ "');\n"
    fichier.write(strVal)


fichier.write("COMMIT;\n\nSET AUTOCOMMIT=0;\nINSERT INTO trail VALUES (1,'Sierre-Zinal',31,3300,'The Sierre-Zinal race is a mountain running race that takes place in the canton of Valais each August. It is also known as the race of five 4000ers (La course des cinq 4000), as five peaks over four thousand meters are visible along its path: Weisshorn (4506 m), Zinalrothorn (4221 m), Obergabelhorn (4073 m) Matterhorn (4478 m) and Dent Blanche (4357 m).','2020-08-20');\nINSERT INTO trail VALUES (2,'Matterhorn Ultraks',24,2800,'The Matterhorn Ultraks is an international skyrunning competition held for the first time in 1982. It runs every year in Zermatt (Switzerland) in August, race valid for the Skyrunner World Series till 2010.','2020-09-20');\nINSERT INTO trail VALUES (3,'Ultra-Trail du Mont-Blanc',171,10040,'The Ultra-Trail du Mont-Blanc (UTMB) is a single-stage mountain ultramarathon first held in 2003. It is a race of the Ultra-Trail World Tour.','2020-02-20');\nINSERT INTO trail VALUES (4,'Kilometre vertical de Fully',1.9,1000,'The Kilometre vertical de Fully is an international skyrunning competition held for the first time in 2001. It rans every year in July in Fully (Switzerland) and is valid for the Vertical Kilometer World Circuit.','2020-11-17');\nINSERT INTO trail VALUES (5,'Ultra-Trail Mt. Fuji',168,9500,'The Ultra-Trail Mt. Fuji is an annual ultramarathon sporting event in Yamanashi Prefecture and Shizuoka Prefecture, Japan, started in 2012.[1] The total distance is 168 km, the cumulative altitude gain is 9,500m, and the time limit is 46 hours. It is one of the Ultra-Trail World Tour.[4] It is a sister sport event of Ultra-Trail du Mont-Blanc.','2020-05-18');\nINSERT INTO trail VALUES (6,'Sierre-Zinal',31,3300,'The Sierre-Zinal race is a mountain running race that takes place in the canton of Valais each August. It is also known as the race of five 4000ers (La course des cinq 4000), as five peaks over four thousand meters are visible along its path: Weisshorn (4506 m), Zinalrothorn (4221 m), Obergabelhorn (4073 m) Matterhorn (4478 m) and Dent Blanche (4357 m).','2020-08-20');\nINSERT INTO trail VALUES (7,'Matterhorn Ultraks',24,2800,'The Matterhorn Ultraks is an international skyrunning competition held for the first time in 1982. It runs every year in Zermatt (Switzerland) in August, race valid for the Skyrunner World Series till 2010.','2020-01-20');\nINSERT INTO trail VALUES (8,'Ultra-Trail du Mont-Blanc',171,10040,'The Ultra-Trail du Mont-Blanc (UTMB) is a single-stage mountain ultramarathon first held in 2003. It is a race of the Ultra-Trail World Tour.','2021-04-20');\nINSERT INTO trail VALUES (9,'Kilometre vertical de Fully',1.9,1000,'The Kilometre vertical de Fully is an international skyrunning competition held for the first time in 2001. It rans every year in July in Fully (Switzerland) and is valid for the Vertical Kilometer World Circuit.','2020-05-13');\nINSERT INTO trail VALUES (10,'Ultra-Trail Mt. Fuji',168,9500,'The Ultra-Trail Mt. Fuji is an annual ultramarathon sporting event in Yamanashi Prefecture and Shizuoka Prefecture, Japan, started in 2012.[1] The total distance is 168 km, the cumulative altitude gain is 9,500m, and the time limit is 46 hours. It is one of the Ultra-Trail World Tour.[4] It is a sister sport event of Ultra-Trail du Mont-Blanc.','2020-11-28');\n")

#Generete trail
tabDescri = ['Trop easy AMT','AMTs Hell','See you next Year!','Tres beau trail','Le trail de la mort qui tue','Le trail de quand tu rentres chez toi t es dans le dur','Champion trail','Trail de a peu pres quelques kil','El trailo de la muerte peluda','Trail 2.0 sisi','Trail de on est la !']
tabCapa = [100,200,300,400,500,600,700,800,900,1000]
tabAnnee = ['2019','2020']

for i in range(11,nbTrail):
    count += 1
    mois = tabMois[randint(0,len(tabMois)-1)]
    if mois == '02':
        mois = '01'
    strVal = "INSERT INTO trail VALUES ("
    strVal += str(i) + "," + "'" + fake.city() + "'," + str(randint(20,60)) + "," + str(randint(1000,4000)) + ",'" + tabDescri[randint(0,len(tabDescri)-1)] + "','" + str(randint(2020,2025)) + "-" + mois + "-" + tabJours[randint(0,len(tabJours)-1)]  + "');\n"
    fichier.write(strVal)

fichier.write("COMMIT;\n\nSET AUTOCOMMIT=0;\n")

#Generate reg
index = 1
for i in range(1,nbTrail):
    
    min = randint(1, nbUser)
    max = randint(1, nbUser)
    while (min > max) and (max-min>50):
            min = randint(1, nbUser)
            max = randint(1, nbUser)
    
    for j in range(min,max):
        count += 1
        strVal = "INSERT INTO registration VALUES ("
        strVal += str(index) + ","+ str(j)+","+str(i)+");\n"
        fichier.write(strVal)
        index += 1

print(count)
fichier.write("COMMIT;\n")
fichier.close()
