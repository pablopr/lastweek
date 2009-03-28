CREATE DATABASE IF NOT EXISTS lastweek;

CONNECT lastweek;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS ClassifiedAd;
DROP TABLE IF EXISTS Category;
DROP TABLE IF EXISTS Province;

CREATE TABLE Province (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE
	)
	TYPE = InnoDB;

CREATE TABLE Category (
	id SERIAL PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE
	)
	TYPE = InnoDB;

CREATE TABLE ClassifiedAd (
	id SERIAL PRIMARY KEY,
	title VARCHAR(80) NOT NULL,
	price DOUBLE,
	description VARCHAR(511),
	sourceURL VARCHAR(511),
	source INTEGER NOT NULL, 
	flag INTEGER NOT NULL DEFAULT 0,
	state INTEGER NOT NULL,
	hashCode VARCHAR(511) NOT NULL,
	CONSTRAINT ClassifiedAdSourcesCheck
		CHECK ((sourceURL IS NULL AND source = 0) OR (sourceURL IS NOT NULL AND source != 0))
	)
	TYPE = InnoDB;

CREATE TABLE User (
	id SERIAL PRIMARY KEY,
	name VARCHAR(80),
	email VARCHAR(80),
	phone VARCHAR(20),
	state INTEGER NOT NULL,
	CONSTRAINT UserMailOrPhoneCheck
		CHECK (email IS NOT NULL OR phone IS NOT NULL)
	)
	TYPE = InnoDB;
	
GRANT ALL PRIVILEGES ON lastweek.* TO marc@'%' IDENTIFIED BY 'marc';

INSERT INTO Province(name) VALUES ("A Coruña");
INSERT INTO Province(name) VALUES ("Álava");
INSERT INTO Province(name) VALUES ("Albacete");
INSERT INTO Province(name) VALUES ("Alicante");
INSERT INTO Province(name) VALUES ("Almería");
INSERT INTO Province(name) VALUES ("Asturias");
INSERT INTO Province(name) VALUES ("Ávila");
INSERT INTO Province(name) VALUES ("Badajoz");
INSERT INTO Province(name) VALUES ("Barcelona");
INSERT INTO Province(name) VALUES ("Burgos");
INSERT INTO Province(name) VALUES ("Cáceres");
INSERT INTO Province(name) VALUES ("Cádiz");
INSERT INTO Province(name) VALUES ("Cantabria");
INSERT INTO Province(name) VALUES ("Castellón");
INSERT INTO Province(name) VALUES ("Ceuta");
INSERT INTO Province(name) VALUES ("Ciudad Real");
INSERT INTO Province(name) VALUES ("Córdoba");
INSERT INTO Province(name) VALUES ("Cuenca");
INSERT INTO Province(name) VALUES ("Gerona");
INSERT INTO Province(name) VALUES ("Granada");
INSERT INTO Province(name) VALUES ("Guadalajara");
INSERT INTO Province(name) VALUES ("Guipúzcoa");
INSERT INTO Province(name) VALUES ("Huelva");
INSERT INTO Province(name) VALUES ("Huesca");
INSERT INTO Province(name) VALUES ("Islas Baleares");
INSERT INTO Province(name) VALUES ("Jaén");
INSERT INTO Province(name) VALUES ("León");
INSERT INTO Province(name) VALUES ("Lleida");
INSERT INTO Province(name) VALUES ("Lugo");
INSERT INTO Province(name) VALUES ("Madrid");
INSERT INTO Province(name) VALUES ("Málaga");
INSERT INTO Province(name) VALUES ("Melilla");
INSERT INTO Province(name) VALUES ("Murcia");
INSERT INTO Province(name) VALUES ("Navarra");
INSERT INTO Province(name) VALUES ("Ourense");
INSERT INTO Province(name) VALUES ("Palencia");
INSERT INTO Province(name) VALUES ("Las Palmas");
INSERT INTO Province(name) VALUES ("Pontevedra");
INSERT INTO Province(name) VALUES ("La Rioja");
INSERT INTO Province(name) VALUES ("Salamanca");
INSERT INTO Province(name) VALUES ("Segovia");
INSERT INTO Province(name) VALUES ("Sevilla");
INSERT INTO Province(name) VALUES ("Soria");
INSERT INTO Province(name) VALUES ("Tarragona");
INSERT INTO Province(name) VALUES ("Santa Cruz de Tenerife");
INSERT INTO Province(name) VALUES ("Teruel");
INSERT INTO Province(name) VALUES ("Toledo");
INSERT INTO Province(name) VALUES ("Valencia");
INSERT INTO Province(name) VALUES ("Valladolid");
INSERT INTO Province(name) VALUES ("Vizcaya");
INSERT INTO Province(name) VALUES ("Zamora");
INSERT INTO Province(name) VALUES ("Zaragoza");
