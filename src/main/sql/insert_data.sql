CONNECT lastweek;

INSERT INTO PROVINCE(NAME) VALUES ('A Coruña');
INSERT INTO PROVINCE(NAME) VALUES ('Álava');
INSERT INTO PROVINCE(NAME) VALUES ('Albacete');
INSERT INTO PROVINCE(NAME) VALUES ('Alicante');
INSERT INTO PROVINCE(NAME) VALUES ('Almería');
INSERT INTO PROVINCE(NAME) VALUES ('Asturias');
INSERT INTO PROVINCE(NAME) VALUES ('Ávila');
INSERT INTO PROVINCE(NAME) VALUES ('Badajoz');
INSERT INTO PROVINCE(NAME) VALUES ('Barcelona');
INSERT INTO PROVINCE(NAME) VALUES ('Burgos');
INSERT INTO PROVINCE(NAME) VALUES ('Cáceres');
INSERT INTO PROVINCE(NAME) VALUES ('Cádiz');
INSERT INTO PROVINCE(NAME) VALUES ('Cantabria');
INSERT INTO PROVINCE(NAME) VALUES ('Castellón');
INSERT INTO PROVINCE(NAME) VALUES ('Ceuta');
INSERT INTO PROVINCE(NAME) VALUES ('Ciudad Real');
INSERT INTO PROVINCE(NAME) VALUES ('Córdoba');
INSERT INTO PROVINCE(NAME) VALUES ('Cuenca');
INSERT INTO PROVINCE(NAME) VALUES ('Gerona');
INSERT INTO PROVINCE(NAME) VALUES ('Granada');
INSERT INTO PROVINCE(NAME) VALUES ('Guadalajara');
INSERT INTO PROVINCE(NAME) VALUES ('Guipúzcoa');
INSERT INTO PROVINCE(NAME) VALUES ('Huelva');
INSERT INTO PROVINCE(NAME) VALUES ('Huesca');
INSERT INTO PROVINCE(NAME) VALUES ('Islas Baleares');
INSERT INTO PROVINCE(NAME) VALUES ('Jaén');
INSERT INTO PROVINCE(NAME) VALUES ('León');
INSERT INTO PROVINCE(NAME) VALUES ('Lleida');
INSERT INTO PROVINCE(NAME) VALUES ('Lugo');
INSERT INTO PROVINCE(NAME) VALUES ('Madrid');
INSERT INTO PROVINCE(NAME) VALUES ('Málaga');
INSERT INTO PROVINCE(NAME) VALUES ('Melilla');
INSERT INTO PROVINCE(NAME) VALUES ('Murcia');
INSERT INTO PROVINCE(NAME) VALUES ('Navarra');
INSERT INTO PROVINCE(NAME) VALUES ('Ourense');
INSERT INTO PROVINCE(NAME) VALUES ('Palencia');
INSERT INTO PROVINCE(NAME) VALUES ('Las Palmas');
INSERT INTO PROVINCE(NAME) VALUES ('Pontevedra');
INSERT INTO PROVINCE(NAME) VALUES ('La Rioja');
INSERT INTO PROVINCE(NAME) VALUES ('Salamanca');
INSERT INTO PROVINCE(NAME) VALUES ('Segovia');
INSERT INTO PROVINCE(NAME) VALUES ('Sevilla');
INSERT INTO PROVINCE(NAME) VALUES ('Soria');
INSERT INTO PROVINCE(NAME) VALUES ('Tarragona');
INSERT INTO PROVINCE(NAME) VALUES ('Santa Cruz de Tenerife');
INSERT INTO PROVINCE(NAME) VALUES ('Teruel');
INSERT INTO PROVINCE(NAME) VALUES ('Toledo');
INSERT INTO PROVINCE(NAME) VALUES ('Valencia');
INSERT INTO PROVINCE(NAME) VALUES ('Valladolid');
INSERT INTO PROVINCE(NAME) VALUES ('Vizcaya');
INSERT INTO PROVINCE(NAME) VALUES ('Zamora');
INSERT INTO PROVINCE(NAME) VALUES ('Zaragoza');

INSERT INTO CATEGORY(NAME) VALUES ('coches');
INSERT INTO CATEGORY(NAME) VALUES ('motos');

INSERT INTO SUBCATEGORY(NAME,CATEGORY_ID) VALUES('deportivos',1);
INSERT INTO SUBCATEGORY(NAME,CATEGORY_ID) VALUES('clasicos',1);
INSERT INTO USER_DATA(NAME, EMAIL, PHONE, STATE) values ('Hija cachonda', 'cachonda@posadareal.zam', 654879856, 0);

INSERT INTO SUBCATEGORY(NAME, CATEGORY_ID) values ('De carreras', 2);
INSERT INTO SUBCATEGORY(NAME, CATEGORY_ID) values ('Cutrescooters', 2);

INSERT INTO CLASSIFIED_AD(TITLE, PRICE, DESCRIPTION, SOURCE_URL, SOURCE, FLAG, STATE, CREATION_DATE, PUBLICATION_DATE, HASH_CODE, CATEGORY_ID, SUBCATEGORY_ID, PROVINCE_ID, USER_DATA_ID)
				values('Consolator plus for felix', 100, 'Si esta cancion no te importe...', 'www.lastweek.com', 0, 0, 0, '2009-02-28 00:00:01', '2009-02-28 00:00:01', 'jaxcoud', 1, 1, 1, 1);
INSERT INTO CLASSIFIED_AD(TITLE, PRICE, DESCRIPTION, SOURCE_URL, SOURCE, FLAG, STATE, CREATION_DATE, PUBLICATION_DATE, HASH_CODE, CATEGORY_ID, SUBCATEGORY_ID, PROVINCE_ID, USER_DATA_ID)
				values('Mega Octopus in ebay', 100, 'Pulpo atroz', 'www.ebayanuncios.com', 1, 0, 0, '2009-02-21 00:00:01', '2009-02-21 00:00:01', 'laksjd', 1, 2, 1, 1);
INSERT INTO CLASSIFIED_AD(TITLE, PRICE, DESCRIPTION, SOURCE_URL, SOURCE, FLAG, STATE, CREATION_DATE, PUBLICATION_DATE, HASH_CODE, CATEGORY_ID, SUBCATEGORY_ID, PROVINCE_ID, USER_DATA_ID)
				values('Cosa brutal', 10, 'Megadescripcion', 'www.lastweek.com', 0, 0, 0, '2009-01-28 00:00:01', '2009-01-28 00:00:01', 'lasdf', 2, 1, 1, 1);
INSERT INTO CLASSIFIED_AD(TITLE, PRICE, DESCRIPTION, SOURCE_URL, SOURCE, FLAG, STATE, CREATION_DATE, PUBLICATION_DATE, HASH_CODE, CATEGORY_ID, SUBCATEGORY_ID, PROVINCE_ID, USER_DATA_ID)
				values('Objeto inquietante', 100, 'Tiembla', 'www.loquo.com', 2, 0, 0, '2009-02-21 00:00:01', '2009-02-21 00:00:01', 'laksjd', 2, 2, 1, 1);				
