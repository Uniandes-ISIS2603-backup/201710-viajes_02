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