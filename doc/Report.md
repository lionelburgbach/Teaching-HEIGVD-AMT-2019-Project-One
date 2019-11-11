# Rapport : Projet N°1 - Site de Randonnée

### Description globale

Nous avons créer un site permettant à des utilisateurs de créer des sessions de randonnée et de pouvoir s'y inscrire. Le client arrive sur une première page qui lui montre les différentes randonnées auxquelles il pourra s'inscrire s'il crée un compte. Après s'est inscrit au site puis s'est connecté, l'utilisateur peut en plus de voir les différentes randonnées, s'inscrire à ces dernières et voir quels autres utilisateurs y sont déjà inscrit. Il a aussi la possibilité de changer ses informations grâce à une page de profil. La page home lui permet de voir à quelle randonnée(s) il s'est inscrit et bien évidemment de pouvoir se désinscrire. Finalement, il peut se délogger de l'application.

![](Images/p1_trail.png)  

<div style="text-align:center"><font size="-1">Première page sur laquelle on arrive</font>
</div>



![](Images/home.png)

<div style="text-align:center"><font size="-1">Page home (affichant les randonnées auxquelles l'utilisateur est inscrit)</font>
</div>





![](Images/profile.png)

<div style="text-align:center"><font size="-1">Page de modification de profil</font></div>



### Description fonctionnelle

#### Page Trail (avant connexion) :

C'est la première page sur laquelle l'utilisateur arrive, il peut y voir toutes les randonnées proposé. S'il veut voir qui est déjà inscrit ou s'inscrire au trail, on est renvoyé vers la page de login.

#### Page Login :

Cette page permet à l'utilisateur de se connecter s'il possède un compte ou de s'enregistrer.

#### Page Register :

Cette page permet à l'utilisateur de s'enregistrer en fournissant son email (utilisé comme identifiant), son nom, son prénom, son password, une confirmation de ce dernier et une date de naissance.

#### Page Trail (après connexion) :

Cette page permet de créer une nouvelle randonnée en fournissant un nom, une distance, une dénivellation, une description et une date. L'utilisateur peut aussi voir la liste des randonnées, auxquelles on peut s'inscrire ou voir qui y est déjà inscrit.

#### Page Home :

Cette page montre les différentes randonnées auxquelles l'utilisateur s'est inscrit et lui permet de de désinscrire. Il peut aussi cliquer sur un lien lui permettant de voir qui est déjà inscrit à la randonnée.

#### Page Profil :

Cette page permet à l'utilisateur de modifier ces informations personnelles (sauf l'email qui sert d'identifiant). 

#### Page Data :

Cette page permet, après avoir cliqué sur le lien "see registers", d'afficher la liste des participants. L'utilisateur peut ensuite cliquer sur "see more" pour afficher les informations d'un participant en particulier.



### Description des entités

Pour gérer notre application, nous avons trois entités différentes. La première est l'entité **user**, l'entité **trail** et l'entité **registration**.

#### User :

L'entité user représente un utilisateur caractérisé par un ID unique généré automatiquement par la base de données. Des informations personnelles tel que le nom, le prénom, la date de naissance et une photo de profil. L’émail est aussi unique et sert d'identifiant pour l'utilisateur. Il possède finalement un mot de passe pour pouvoir s'authentifier.



#### Trail :

L'entité trail représente une randonnées, elle possède elle aussi un ID unique généré automatiquement par la base de données. Elle possède ensuite toutes les informations nécessaire à sa définition tel que son nom, sa distance, sa dénivellation, sa description et sa date.



#### Registration :

L'entité registration est le lien entre nos deux première entités. Elle est composé d'un  ID unique généré automatiquement par la base de données, de l'ID d'un utilisateur et de l'ID du trail auquel ce dernier souhaite s'inscrire.



![](Images/entites.jpg)