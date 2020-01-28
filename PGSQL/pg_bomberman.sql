DROP TABLE Joueur;
DROP TABLE Historique;


CREATE TABLE Joueur (
	id SERIAL not null,
	email varchar(100) not null,
	motDePasse varchar(100) not null,
	pseudo varchar(100) not null,
	date_create date not null,
	sexe int not null, 
	niveau int not null,
	PRIMARY KEY(id)
);


CREATE TABLE Historique (
	id_partie int not null,
	id_joueur int not null,
	date_partie date not null,
	type_partie int not null, 
	score int not null,
	aGagne boolean not null,
	PRIMARY KEY (id_partie,id_joueur)

);
