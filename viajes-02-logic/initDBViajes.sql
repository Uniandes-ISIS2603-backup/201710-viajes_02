delete from ViajeEntity;
delete from UsuarioEntity_ReviewEntity;
delete from UsuarioEntity_ReservaEntity;
delete from UsuarioEntity_PagoEntity;
delete from UsuarioEntity_CobroEntity;
delete from UsuarioEntity_AutomovilEntity;
delete from ReviewEntity;
delete from ReservaEntity;
delete from PagoEntity;
delete from CobroEntity;
delete from AutomovilEntity;
delete from UsuarioEntity;
delete from LugarEntity;

insert into LugarEntity (id, direccion, lugar, lat, lon) values (0 , '8506 Sunbrook Place' , 'Lucija' , 45.50526 , 13.6024);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (1 , '6060 Lindbergh Alley' , 'Żelazków' , 51.85418 , 18.1743);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (2 , '1 Hintze Point' , 'Sukawaris' , -6.8041 , 105.8639);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (3 , '9524 Russell Street' , 'Sainte-Anne-de-Bellevue' , 45.40618 , -73.9456);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (4 , '4 Banding Pass' , 'Quxia' , 32.10037 , 120.15698);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (5 , '273 Kedzie Way' , 'Tivat' , 42.43639 , 18.69611);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (6 , '6440 Riverside Street' , 'Metz' , 49.1191 , 6.1727);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (7 , '46628 Granby Lane' , 'Ranao' , 16.2076 , 119.8155);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (8 , '7258 Barnett Plaza' , 'Maquiapo' , 14.98561 , 120.55855);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (9 , '026 Hoard Plaza' , 'Okigwi' , 5.83523 , 7.35989);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (10 , '53657 Twin Pines Trail' , 'Isanlu Itedoijowa' , 8.26667 , 5.83333);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (11 , '649 Hudson Park' , 'Timbiras' , -4.25528 , -43.94056);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (12 , '29 Eagle Crest Hill' , 'Maha Sarakham' , 16.18483 , 103.30067);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (13 , '6 Fairfield Court' , 'As Sulayyil' , 20.46067 , 45.57792);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (14 , '127 Spenser Crossing' , 'Shangyong' , 25.73421 , 118.15716);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (15 , '675 Gina Parkway' , 'Mislīyah' , 32.38613 , 35.2878);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (16 , '0 Red Cloud Terrace' , 'Jbaïl' , 34.12111 , 35.64806);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (17 , '1319 Briar Crest Plaza' , 'Rimba Sekampung' , 1.6756 , 101.43748);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (18 , '367 Mayer Crossing' , 'Lama' , 41.53 , -8.3567);
insert into LugarEntity (id, direccion, lugar, lat, lon) values (19 , '7994 Hooker Court' , 'Taoyuan' , 22.58041 , 113.96121);

INSERT INTO APP.AUTOMOVILENTITY (ID,CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA)
	VALUES (1,4, 'Rojo', 'Asura', 'Mercedes', 'AMG', 456789, 'MAD568');
INSERT INTO APP.AUTOMOVILENTITY (ID, CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA) 
	VALUES (2,5, 'Verde', 'Seguritos', 'Honda', 'HJK', 3456789, 'LKY678');
INSERT INTO APP.AUTOMOVILENTITY (ID, CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA) 
	VALUES (3,2, 'Negro', 'yeei', 'Ferrari', 'Lindo', 359204, 'PWÑ678');
INSERT INTO APP.AUTOMOVILENTITY (ID, CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA) 
	VALUES (4,4, 'Gris', 'Mapfe', 'Honda', 'GH6', 827402, 'HLM983');
INSERT INTO APP.AUTOMOVILENTITY (ID, CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA) 
	VALUES (5,5, 'Negro', 'Positiva', 'Volvo', 'S60', 1037483, 'GYT892');
INSERT INTO APP.AUTOMOVILENTITY (ID, CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA) 
	VALUES (6,5, 'azul', 'AIG', 'Masda', 'JU7', 567823, 'MPQ903');
INSERT INTO APP.AUTOMOVILENTITY (ID, CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA) 
	VALUES (7,4, 'Azul Noche', 'Travel', 'Renault', 'Twingo', 93024, 'MLD456');
INSERT INTO APP.AUTOMOVILENTITY (ID, CANTASIENTOS, COLOR, COMPSEGUROS, MARCA, MODELO, NUMSEGURO, PLACA) 
	VALUES (8,4, 'Rojo/Negro', 'Safe', 'Mini', 'England', 723924, 'RTP849');
    
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (1, 'ViajeroEntity', 'lcunningham0@simplemachines.org', 79, 'Female', 'Linda Cunningham', 1, 29076289, 18, 'https://robohash.org/atiureerror.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (2, 'ViajeroEntity', 'tbarnes1@slashdot.org', 53, 'Female', 'Teresa Barnes', 2, 573381344, 18, 'https://robohash.org/praesentiumharumsunt.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (3, 'ViajeroEntity', 'pchavez2@sourceforge.net', 72, 'Male', 'Patrick Chavez', 3, 462233292, 6, 'https://robohash.org/accusantiumquosprovident.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (4, 'ViajeroEntity', 'schavez3@artisteer.com', 98, 'Male', 'Scott Chavez', 4, 637595999, 12, 'https://robohash.org/temporeomnismagni.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (5, 'ViajeroEntity', 'sfowler4@symantec.com', 18, 'Male', 'Stephen Fowler', 5, 904549476, 8, 'https://robohash.org/laboriosamrecusandaepraesentium.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (6, 'ViajeroEntity', 'mhayes5@cam.ac.uk', 62, 'Female', 'Melissa Hayes', 6, 61261899, 18, 'https://robohash.org/autundevoluptas.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (7, 'ViajeroEntity', 'rlarson6@princeton.edu', 69, 'Male', 'Ralph Larson', 7, 826866243, 13, 'https://robohash.org/enimetdolores.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (8, 'ViajeroEntity', 'lmason7@uiuc.edu', 93, 'Male', 'Louis Mason', 8, 906028877, 18, 'https://robohash.org/iustosintsit.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (9, 'ViajeroEntity', 'nharrison8@livejournal.com', 40, 'Male', 'Nicholas Harrison', 9, 284081599, 16, 'https://robohash.org/eoseligendiut.jpg?size=200x200&set=set1');
insert into UsuarioEntity (id, dtype, correo, edad, genero, nombre, rating, telmovil, direccion_id, imagen) values (10, 'ViajeroEntity', 'sreid9@amazon.co.jp', 68, 'Male', 'Stephen Reid', 10, 405901148, 5, 'https://robohash.org/nequeimpediteveniet.jpg?size=200x200&set=set1');

insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (11,'ConductorEntity','j.s@gmail.com', 22, 'M', 'Juan Sebastián', 315991,1,'https://robohash.org/perspiciatisetofficia.png?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (12,'ConductorEntity','n.a@gmail.com', 25, 'F', 'Nicolas Aguilar', 28182,2,'https://robohash.org/fugiatdoloresperspiciatis.jpg?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (13,'ConductorEntity','d.f@gmail.com', 25, 'F', 'Daniela Forero', 89090,3,'https://robohash.org/fugiatdoloresperspiciatis.jpg?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (14,'ConductorEntity','r.c@gmail.com', 28, 'F', 'Rainelle Williams', 271623,3,'https://robohash.org/inmollitiadolorem.jpg?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (15,'ConductorEntity','r.c@gmail.com', 24, 'M', 'Javi Baez', 74773,4,'https://robohash.org/nondebitisdolore.png?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (16,'ConductorEntity','hh@gmail.com', 60, 'M', 'Hunter Hermsley', 8965,5,'https://robohash.org/ullamdeleniticommodi.bmp?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (17,'ConductorEntity','jc@gmail.com', 40, 'M', 'John Cena', 89332,6,'https://robohash.org/nonquasvitae.jpg?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (18,'ConductorEntity','mf@gmail.com', 19, 'F', 'Maria Fernanda', 898023,7,'https://robohash.org/etquoillum.jpg?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (19,'ConductorEntity','dm@gmail.com', 23, 'F', 'Dani Alves', 36732,8,'https://robohash.org/quiveniammaxime.jpg?size=300x300&set=set1');
insert into USUARIOENTITY (ID, DTYPE, CORREO, EDAD, GENERO, NOMBRE, TELMOVIL, DIRECCION_ID, IMAGEN) VALUES (20,'ConductorEntity','rr@gmail.com', 28, 'F', 'Ronda Rousey', 77323,9,'https://robohash.org/sapientesuntsit.bmp?size=300x300&set=set1');

insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (1,0,43,5,1,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (2,1,75,3,1,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (3,0,8,5,1,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (4,1,35,2,9,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (5,1,15,3,8,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (6,1,84,3,9,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (7,1,19,1,6,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (8,0,68,3,6,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (9,0,18,3,6,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (10,1,1,5,10,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (11,1,63,2,6,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (12,1,77,3,6,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (13,0,60,3,7,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (14,0,66,4,7,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (15,1,38,1,10,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (16,0,70,3,8,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (17,0,96,4,7,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (18,1,27,1,10,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (19,0,92,4,8,'CobroEntity');
insert into CobroEntity (id,cancelado,valor,usuariodestinatario_id,usuarioremitente_id,dtype) values (20,0,80,1,7,'CobroEntity');

insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (0 , 4, 'gran conductor',1,2);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (1 , 2,'aun le falta mucha experiencia manejando',3,4);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (2 , 3, 'es buen conductor  pero el viaje es como aburrido ',2,3);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (3 ,  5, 'gran conductor y muy buena persona ',1,4);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (4 , 4,' recomendado',3,2);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (5 , 4, 'Es buen conductor',5,4);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (6 , 2,'terrible nada recomendado',3,5);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (5 , 5,'Super recomendado',1,2);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (4 , 0, 'Cancela los viajes a ultima hora',1,4);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (3 , 4, 'Es una gran persona',2,3);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (2 , 5, 'muy divertido y hace que el viaje sea genial',1,4);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (1 ,3, 'es eonomico pero muy casual',11,1);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (0 ,1 ,  'aun no entinedo como tiene pase',12,3);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (13 , 4,'Super',13,4);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (14 ,4, 'te deja dormir tranquilo',14,2);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (15 ,2, 'tiene una personalidad complicada',15,3);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (16 ,3,'super tranuqilo',16,4);


INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (11,1);INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (11,2);INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (12,3);INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (12,4);
INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (13,5);INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (14,6);INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (15,7);INSERT INTO USUARIOENTITY_AUTOMOVILENTITY (CONDUCTORENTITY_ID, AUTOMOVILES_ID) VALUES (15,8);

insert into VIAJEENTITY (IDVIAJE, KILOMETROS, DIAYHORALLEGADA, DIAYHORAPARTIDA, GASTOSCOMPARTIDOS, CONDUCTOR_ID, AUTOMOVIL_ID, DESTINO_ID, ORIGEN_ID) VALUES (1,200,'2017-04-23','2017-04-20',50000,11,1,3,7);
insert into VIAJEENTITY (IDVIAJE, KILOMETROS, DIAYHORALLEGADA, DIAYHORAPARTIDA, GASTOSCOMPARTIDOS, CONDUCTOR_ID, AUTOMOVIL_ID, DESTINO_ID, ORIGEN_ID) VALUES (2,500,'2017-05-18','2017-04-5',80000,12,3,8,10);
insert into VIAJEENTITY (IDVIAJE, KILOMETROS, DIAYHORALLEGADA, DIAYHORAPARTIDA, GASTOSCOMPARTIDOS, CONDUCTOR_ID, AUTOMOVIL_ID, DESTINO_ID, ORIGEN_ID) VALUES (3,500,'2017-06-2','2017-04-1',120000,13,4,8,10);
insert into VIAJEENTITY (IDVIAJE, KILOMETROS, DIAYHORALLEGADA, DIAYHORAPARTIDA, GASTOSCOMPARTIDOS, CONDUCTOR_ID, AUTOMOVIL_ID, DESTINO_ID, ORIGEN_ID) VALUES (4,500,'2017-06-20','2017-04-1',80000,15,8,2,1);

insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (1, 32.06, 3, 14.19, 1, 1);
insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (2, 23.08, 1, 10.67, 2, 2);
insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (3, 45.59, 1, 14.11, 3, 3);
insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (4, 42.97, 4, 14.2, 4, 4);
-- Limitado a cantidad de viajeros y viajes
-- insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (5, 47.74, 1, 12.72, 5, 5);
-- insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (6, 22.49, 3, 17.88, 6, 6);
-- insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (7, 49.7, 3, 11.58, 7, 7);
-- insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (8, 47.33, 3, 18.54, 8, 8);
-- insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (9, 32.94, 3, 17.8, 9, 9);
-- insert into RESERVAENTITY (id, precio, puestosreservados, valorcomision, viaje_idviaje, viajero_id) values (10, 39.26, 1, 18.0, 10, 10);

insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (1, 1);
insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (2, 2);
insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (3, 3);
insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (4, 4);
-- Limitado a cantidad de viajeros y viajes
-- insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (5, 5);
-- insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (6, 6);
-- insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (7, 7);
-- insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (8, 8);
-- insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (9, 9);
-- insert into USUARIOENTITY_RESERVAENTITY (viajeroentity_id, reservas_id) values (10, 10);

insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (1, 'PagoEntity', 1, 1, 1, 11);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (2, 'PagoEntity', 1, 1, 2, 12);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (3, 'PagoEntity', 0, 1, 3, 13);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (4, 'PagoEntity', 0, 1, 4, 14);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (5, 'PagoEntity', 1, 0, 5, 15);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (6, 'PagoEntity', 0, 1, 6, 16);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (7, 'PagoEntity', 0, 1, 7, 17);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (8, 'PagoEntity', 0, 1, 8, 18);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (9, 'PagoEntity', 0, 1, 9, 19);
insert into PAGOENTITY (id, dtype, cancelado, valor, destinatario_id, remitente_id) values (10, 'PagoEntity', 1, 0, 10, 20);

insert into USUARIOEntity_REVIEWENTITY (USUARIOENTITY_ID, reviews_id) values (11,1);
insert into USUARIOEntity_REVIEWENTITY (USUARIOENTITY_ID, reviews_id) values (11,3);
insert into USUARIOEntity_REVIEWENTITY (USUARIOENTITY_ID, reviews_id) values (11,4);