DROP DATABASE IF EXISTS storage;
CREATE DATABASE storage;
USE storage;


CREATE TABLE prodotto (	
  id int primary key AUTO_INCREMENT,
  nome varchar(20) not null,
  descrizione varchar(100),
  prezzo double default 0,
  quantita int default 0,
  categoria int not null,
  sconto double default 0,
  iva double default 0
);

CREATE TABLE utente (	
  nome varchar(20) not null,
  cognome varchar(20) not null,
  email varchar(40) not null,
  password varchar(10) not null,
  tipo int not null default 0,
  indirizzo varchar(50) not null,
  telefono varchar(20) not null,
  carta varchar(20) not null,
  primary key(email)
);

CREATE TABLE ordine (	
  id int primary key AUTO_INCREMENT,
  email varchar(40) not null,
  stato int not null,
  data date not null,
  importo double not null,
  metodoPagamento varchar(20) not null,
  dataSpedizione date not null,
  indirizzo varchar(45) not null,
  
  foreign key (email) references utente(email)
);

CREATE TABLE contiene (
    idOrdine int,
    idProdotto int,
    descrizione varchar(100),
    prezzo double,
    quantita int,
    categoria int,
    sconto double,
    iva double,
    FOREIGN KEY (idOrdine)
REFERENCES ordine(id)
);