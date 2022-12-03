CREATE DATABASE  IF NOT EXISTS `biblioteca`;
USE `biblioteca`;
/*HAY QUE REVISAR CAMPOS Y TIPOS DE DATOS, ADEMAS HAY QUE METERLE INFORMACION A LAS TABLAS...*/
CREATE TABLE `Artistas` (
  `id` int NOT NULL auto_increment,
  `nombre_artista` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Autores` (
  `id` int NOT NULL auto_increment,
  `nombre_autor` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Directores` (
  `id` int NOT NULL auto_increment,
  `nombre_director` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Editoriales` (
  `id` int NOT NULL auto_increment,
  `nombre_editorial` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Generos` (
  `id` int NOT NULL auto_increment,
  `nombre_genero` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Tipo_Material` (
  `id` int NOT NULL auto_increment,
  `tipo_material` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Materiales` (
  `id` varchar(11) NOT NULL, /*Codigo, dado que id es un nombre de campo generico para saber que es el PK de la tabla*/
  `titulo` varchar(45) NOT NULL,
  `codigo_tipo_material` int(11) NOT NULL,
  `codigo_autor` int(11) DEFAULT NULL,
  `numero_de_paginas` varchar(4) DEFAULT NULL,
  `codigo_editorial` int(11) DEFAULT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  /*`anio_Publicacion` varchar(4) DEFAULT NULL,*/
  `periodicidad` varchar(15) DEFAULT NULL,
  `fecha_publicacion` date DEFAULT NULL,
  `codigo_artista` int(11) DEFAULT NULL,
  `codigo_genero` int(11) DEFAULT NULL,
  `duracion` varchar(15) DEFAULT NULL,
  `numero_de_canciones` varchar(2) DEFAULT NULL,
  `codigo_director` int(11) DEFAULT NULL,
  `ubicacion` VARCHAR(100) DEFAULT NULL,
  `nombre_autor_CV` VARCHAR(150) DEFAULT NULL,
  `unidades_disponibles` int(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Tipo` (`codigo_tipo_material`),
  KEY `FK_Autor` (`codigo_autor`),
  KEY `FK_Editorial_id` (`codigo_editorial`),
  KEY `FK_Artista` (`codigo_artista`),
  KEY `FK_Genero` (`codigo_genero`),
  KEY `FK_Director` (`codigo_director`),
  CONSTRAINT `FK_Tipo` FOREIGN KEY (`codigo_tipo_material`) REFERENCES `Tipo_Material` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Autor` FOREIGN KEY (`codigo_autor`) REFERENCES `Autores` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Editorial` FOREIGN KEY (`codigo_editorial`) REFERENCES `Editoriales` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Artista` FOREIGN KEY (`codigo_artista`) REFERENCES `Artistas` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Genero` FOREIGN KEY (`codigo_genero`) REFERENCES `Generos` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Director` FOREIGN KEY (`codigo_director`) REFERENCES `Directores` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*
CREATE TABLE `Privilegios`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `guardar` INT NOT NULL,
    `actualizar` INT NOT NULL,
    `borrar` INT NOT NULL,
    `consultar` INT NOT NULL,
    `prestar` INT NOT NULL,
    `numero_prestamos` INT NOT NULL,
    `dias_prestamo` INT NOT NULL, /*En dias
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;*/

CREATE TABLE `Rol`(
	`id` INT NOT NULL AUTO_INCREMENT,
    `rol` VARCHAR(75) NOT NULL,
    `numero_prestamos` INT NOT NULL,
    `dias_prestamo` INT NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Usuarios` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `nombre` VARCHAR(150) DEFAULT NULL,
    `apellido` VARCHAR(150) DEFAULT NULL,
    `nickname` VARCHAR(150) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `pass` VARCHAR(200) NOT NULL,
    `mora` FLOAT DEFAULT NULL,
    `fecha_nacimiento` DATE NOT NULL,
    `codigo_rol` INT NOT NULL,
	PRIMARY KEY (`id`),
	KEY `FK_Rol` (`codigo_rol`),
    CONSTRAINT `FK_Rol` FOREIGN KEY (`codigo_rol`) REFERENCES `Rol` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `Prestamos` (
  `id` int NOT NULL auto_increment,
  `codigo_material` varchar(10) NOT NULL,
  `codigo_usuario` int(11) NOT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_usuario` (`codigo_usuario`),
  KEY `FK_material_id` (`codigo_material`),
  CONSTRAINT `FK_Materiales` FOREIGN KEY (`codigo_material`) REFERENCES `Materiales` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Usuario` FOREIGN KEY (`codigo_usuario`) REFERENCES `Usuarios` (`id`) ON DELETE NO ACTION ON UPDATE cascade
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
CREATE TABLE `Permisos_por_Rol` (
  `id` INT NOT NULL auto_increment,
  `codigo_rol` INT(10) NOT NULL,
  `codigo_privilegio` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Rol` (`codigo_rol`),
  KEY `FK_Privilegio` (`codigo_privilegio`),
  CONSTRAINT `FK_Rol_privilegio` FOREIGN KEY (`codigo_rol`) REFERENCES `Rol` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `FK_Privilegio` FOREIGN KEY (`codigo_privilegio`) REFERENCES `Privilegios` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;*/
