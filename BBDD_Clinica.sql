DROP DATABASE IF EXISTS pacientes;
CREATE DATABASE pacientes ;
USE pacientes;

CREATE TABLE  IF NOT EXISTS datos (
  idPaciente INT NOT NULL AUTO_INCREMENT,
  Nombre VARCHAR(45) NOT NULL,
  Apellidos VARCHAR(45) NOT NULL,
  DNI VARCHAR(45) NOT NULL,
  Direccion VARCHAR(45) NOT NULL,
  Telefono VARCHAR(45) NOT NULL,
  Especialista VARCHAR(45) NOT NULL,  
  PRIMARY KEY (idPaciente));
  
CREATE TABLE  IF NOT EXISTS facturas (
  idFactura INT NOT NULL AUTO_INCREMENT,
  idPaciente VARCHAR(45) NOT NULL,
  Precio VARCHAR(45) NOT NULL,
  PRIMARY KEY (idFactura));
  
INSERT INTO datos (idPaciente, nombre, Apellidos, DNI, Direccion, Telefono, Especialista)
	VALUES (0, 'RAMON', 'GABALDON PALENCIA', '06071535H', 'Callejón Lorem ipsum dolor sit, 74A 18ºE', '6595678594', 'Cardiólogo');
INSERT INTO datos (idPaciente, nombre, Apellidos, DNI, Direccion, Telefono, Especialista)
    VALUES (0, 'BEATRIZ', 'PEREDA PRESA', '38964495B', 'Paseo Lorem, 295A 1ºD', '6996985460', 'Dermatólogo');
INSERT INTO datos (idPaciente, nombre, Apellidos, DNI, Direccion, Telefono, Especialista)
    VALUES (0, 'ANDRES', 'PIÑERO GALDEANO', '18206286S', 'Avenida Lorem, 17B 4ºG', '702165703', 'Dermatólogo');
INSERT INTO datos (idPaciente, nombre, Apellidos, DNI, Direccion, Telefono, Especialista)
    VALUES (0, 'FRANCISCA', 'CORDERO CUBERO', '14244494L', 'Travesía Lorem ipsum dolor, 189A 4ºG', '6139362145', 'Neumólogo');
INSERT INTO datos (idPaciente, nombre, Apellidos, DNI, Direccion, Telefono, Especialista)
    VALUES (0, 'NURIA', 'DONAIRE VILLALBA', '07812528A', 'Calle Lorem ipsum dolor, 197B', '7378929201', 'Cardiólogo');
    
INSERT INTO facturas (idFactura, idPaciente, Precio)
	VALUES (0, '1', '915');
INSERT INTO facturas (idFactura, idPaciente, Precio)
    VALUES (0, '1', '975');
INSERT INTO facturas (idFactura, idPaciente, Precio)
    VALUES (0, '2', '600');
INSERT INTO facturas (idFactura, idPaciente, Precio)
    VALUES (0, '2', '150');
INSERT INTO facturas (idFactura, idPaciente, Precio)
    VALUES (0, '3', '120');
INSERT INTO facturas (idFactura, idPaciente, Precio)
    VALUES (0, '3', '550');
INSERT INTO facturas (idFactura, idPaciente, Precio)
    VALUES (0, '4', '299');
INSERT INTO facturas (idFactura, idPaciente, Precio)
    VALUES (0, '4', '975');
INSERT INTO facturas (idFactura, idPaciente, Precio)   
    VALUES (0, '5', '150');
INSERT INTO facturas (idFactura, idPaciente, Precio)   
    VALUES (0, '5', '600');
