# Stratégie de test

Pour lancer les tests JUnit, Mockito & Arquillian, il vous faut vous placer dans la racine du projet et de lancer la commande suivante : `mvn clean test`

## Test JUnit

Nous avons testé nos classes model et nos classes utilitaires à l'aide de tests unitaire JUnit. 

## Test Mockito

Pour tester nos servlets, nous avons utilisé le framework Mockito.  Nous avons donc testé la classe login à l'aide de ce dernier. 

## Test Arquillian

Avec les tests Arquillian nous allons pouvoir tester les différentes DAO de notre projet. Nous avons testé toutes les méthodes des différentes DAO. La première fois que nous l'avons effectué, nous avons pu remarquer que renvoyer les ID des différents objets lors de leur ajout dans la BD était indispensable. Nous avons fait en sorte que chacun des tests soit autonome (qu'il ne dépende d'aucun autre test).

## Test JMeter

