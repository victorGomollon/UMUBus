CREATE SCHEMA IF NOT EXISTS PRUEBAS;

DROP TABLE IF EXISTS PRUEBAS.USERS;

CREATE TABLE PRUEBAS.USERS(
		identificador VARCHAR(36) PRIMARY KEY NOT NULL,
		nombre VARCHAR(100) NOT NULL,
		edad INTEGER NOT NULL
	);