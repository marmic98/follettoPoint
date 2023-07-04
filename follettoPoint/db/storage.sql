DROP DATABASE IF EXISTS storage;
CREATE DATABASE storage;
USE storage;


CREATE TABLE prodotto (	
  id int primary key AUTO_INCREMENT,
  nome char(20) not null,
  descrizione char(100),
  prezzo double default 0,
  quantita int default 0,
  categoria int not null,
  sconto double default 0 
);

CREATE TABLE utente (	
  nome char(20) not null,
  cognome char(20) not null,
  email char(40) not null,
  password char(10) not null,
  tipo int not null default 0,
  indirizzo char(50) not null,
  telefono char(11) not null,
  primary key(email)
);

CREATE TABLE carrello (	
  idProdotto int not null,
  email char(40) not null,
  quantita int default 1,
  
  primary key(idProdotto, email),
  
  foreign key (idProdotto) references prodotto(id),
  foreign key (email) references utente(email)
);

CREATE TABLE ordine (	
  id int primary key AUTO_INCREMENT,
  email char(40) not null,
  stato int not null,
  data date not null,
  importo double not null,
  carta char(16) not null,
  dataSpedizione date not null,
  
  foreign key (email) references utente(email)
);

create table contiene (
	idProdotto int not null,
	idOrdine int not null,
	quantita int not null , 
	
	primary key(idProdotto, idOrdine),
	
	foreign key (idProdotto) references prodotto(id),
	foreign key (idOrdine) references ordine(id)
);