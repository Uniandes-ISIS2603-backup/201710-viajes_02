delete from ViajeEntity;
delete from UsuarioEntity_ReviewEntity;
delete from UsuarioEntity_ReservaEntity;
delete from UsuarioEntity_PagoEntity;
delete from UsuarioEntity_CobroEntity;
delete from UsuarioEntity_AutomovilEntity;
delete from ReservaEntity_UsuarioEntity;
delete from ReviewEntity;
delete from ReservaEntity;
delete from PagoEntity;
delete from CobroEntity;
delete from AutomovilEntity;
delete from UsuarioEntity;
delete from LugarEntity;

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
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (1 ,3, 'es eonomico pero muy casual',4,1);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (0 ,1 ,  'aun no entinedo como tiene pase',4,3);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (3 , 4,'Super',2,4);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (4 ,4, 'te deja dormir tranquilo',4,2);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (5 ,2, 'tiene una personalidad complicada',4,3);
insert into ReviewEntity (id, calificacion, coment,idcalificado,idcalificador) values (6 ,3,'super tranuqilo',5,4);

