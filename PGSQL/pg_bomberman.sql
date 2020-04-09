/*
DROP TABLE Joueur;
DROP TABLE Historique;


CREATE TABLE Joueur (
	id SERIAL not null,
	email varchar(100) not null,
	motDePasse varchar(100) not null,
	pseudo varchar(100) not null,
	date_create date not null,
	sexe int not null, 
	chemin_avatar varchar(100) not null,
	niveau int not null,
	PRIMARY KEY(id)
);


CREATE TABLE Historique (
	id_partie int not null,
	id_joueur int REFERENCES Joueur (id),
	date_partie date not null,
	type_partie int not null, 
	score int not null,
	aGagne boolean not null,
	PRIMARY KEY (id_partie,id_joueur)

);

CREATE TABLE Ami (
	id_ami1 int REFERENCES Joueur (id),
	id_ami2 int REFERENCES Joueur (id),
	PRIMARY KEY (id_ami1,id_ami2)


)

CREATE TABLE Connectes( 
	id_session varchar(100) not null,
	id_joueur  int REFERENCES Joueur(id),
	PRIMARY KEY (id_session)
);
*/
/*
INSERT INTO Historique values
(4845,1,'30/12/1998',1,7500,false),
(4845,2,'30/12/1998',1,15450,true),
(4845,3,'30/12/1998',1,12500,false),
(5096,1,'21/01/2001',2,25000,true),
(5096,3,'21/01/2001',2,10400,false)
*/


