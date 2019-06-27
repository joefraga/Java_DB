# creamos la base de datos
CREATE DATABASE IF NOT EXISTS javadb;

#seleccionar la base de datos
USE javadb;

#crear tablas
CREATE TABLE `colores` (
  `IdColor` int(11) NOT NULL,
  `DescColor` varchar(40) NOT NULL,
  `Estatus` int(11) NOT NULL,
  `Fecha` date NOT NULL,
  PRIMARY KEY (`IdColor`),
  KEY `COLAKDescColor` (`DescColor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `javadb`.`articulos` (
  `IdArticulo` INT NOT NULL,
  `DescArticulo` VARCHAR(40) NOT NULL,
  `IdColor` INT NOT NULL,
  `Precio` DECIMAL(12,2) NOT NULL,
  `Estatus` INT NOT NULL,
  `Fecha` DATE NOT NULL,
  PRIMARY KEY (`IdArticulo`),
  INDEX `ARTAKDescArticulo` (`DescArticulo` ASC) VISIBLE,
  INDEX `ARTFKColor_idx` (`IdColor` ASC) VISIBLE,
  CONSTRAINT `ARTFKColor`
    FOREIGN KEY (`IdColor`)
    REFERENCES `javadb`.`colores` (`IdColor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

insert into colores (DescColor,Estatus,Fecha) values('Blanco',0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR'))); 
insert into colores (DescColor,Estatus,Fecha) values('Negro',0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR'))); 
insert into colores (DescColor,Estatus,Fecha) values('Azul',0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR'))); 
insert into colores (DescColor,Estatus,Fecha) values('Rojo',0,STR_TO_DATE(REPLACE('15/05/2019','/','.'), GET_FORMAT(date,'EUR'))); 
insert into colores (DescColor,Estatus,Fecha) values('Amarillo',0,STR_TO_DATE(REPLACE('15/05/2019','/','.'), GET_FORMAT(date,'EUR'))); 
insert into colores (DescColor,Estatus,Fecha) values('Verde',0,STR_TO_DATE(REPLACE('15/05/2019','/','.'), GET_FORMAT(date,'EUR'))); 
select * from colores;

insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Camisa',1,280,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Pantalon',2,250,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Saco',4,1200,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Calcetines',1,120,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Bufanda',4,320,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Sweter',5,900,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Chamarra',3,3200,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Camisa Lash',3,550,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Pantalon V',3,800,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Playera Polo',4,600,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Saco Versage',5,5500,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Precio,Estatus,Fecha) values('Pantalon Levis',3,750,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
select * from articulos;

alter table articulos 
add `Seccion` varchar(40)
after `IdColor`;

update articulos set Seccion='Ropa' where seccion is null;

insert into articulos (DescArticulo,IdColor,Seccion,Precio,Estatus,Fecha) values('Martillo',1,'Ferreteria',100,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Seccion,Precio,Estatus,Fecha) values('Marro',2,'Ferreteria',120,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Seccion,Precio,Estatus,Fecha) values('Clavo',2,'Ferreteria',50,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Seccion,Precio,Estatus,Fecha) values('Tronillos 3/8',2,'Ferreteria',40,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));
insert into articulos (DescArticulo,IdColor,Seccion,Precio,Estatus,Fecha) values('Pinzas',2,'Ferreteria',160,0,STR_TO_DATE(REPLACE('01/01/2019','/','.'), GET_FORMAT(date,'EUR')));