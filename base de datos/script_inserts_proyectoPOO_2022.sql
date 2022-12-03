USE `biblioteca`;
/*Registros Artistas*/
INSERT INTO `Artistas`(nombre_artista) VALUES('Jimi Hendrix');
INSERT INTO `Artistas`(nombre_artista) VALUES('Axl Rose');
INSERT INTO `Artistas`(nombre_artista) VALUES('Harrison Ford');
INSERT INTO `Artistas`(nombre_artista) VALUES('Clint Eastwood');
INSERT INTO `Artistas`(nombre_artista) VALUES('Arnold Schwarzenegger');
INSERT INTO `Artistas`(nombre_artista) VALUES('Gary Lockwood');

/*Registros Autores*/
INSERT INTO `Autores`(nombre_autor) VALUES('William Shakespeare');
INSERT INTO `Autores`(nombre_autor) VALUES('Agatha Christie');
INSERT INTO `Autores`(nombre_autor) VALUES('Dr. Seuss');
INSERT INTO `Autores`(nombre_autor) VALUES('Akira Toriyama');
INSERT INTO `Autores`(nombre_autor) VALUES('J. K. Rowling');

/*Registro Directores*/
INSERT INTO `Directores`(nombre_director) VALUES('J. J. Abrams');
INSERT INTO `Directores`(nombre_director) VALUES('David Lynch');
INSERT INTO `Directores`(nombre_director) VALUES('Martin Scorsese');
INSERT INTO `Directores`(nombre_director) VALUES('Steven Soderbergh');
INSERT INTO `Directores`(nombre_director) VALUES('Quentin Tarantino');
INSERT INTO `Directores`(nombre_director) VALUES('Stanley Kubrick');

/*Registro Editorial*/
INSERT INTO `Editoriales`(nombre_editorial) VALUES('Editorial Santillana');
INSERT INTO `Editoriales`(nombre_editorial) VALUES('ESE EDICIONES');
INSERT INTO `Editoriales`(nombre_editorial) VALUES('Editorial Jurídica Salvadoreña');
INSERT INTO `Editoriales`(nombre_editorial) VALUES('Libros La Ceiba');
INSERT INTO `Editoriales`(nombre_editorial) VALUES('Montañas de Fuego Internacional');

/*Registro Genero*/
INSERT INTO `Generos`(nombre_genero) VALUES('Suspenso');
INSERT INTO `Generos`(nombre_genero) VALUES('Acción');
INSERT INTO `Generos`(nombre_genero) VALUES('Drama');
INSERT INTO `Generos`(nombre_genero) VALUES('Infantil');
INSERT INTO `Generos`(nombre_genero) VALUES('Familiar');
INSERT INTO `Generos`(nombre_genero) VALUES('pop rock');
INSERT INTO `Generos`(nombre_genero) VALUES('Rock');

/*Registros para tipo de material*/
INSERT INTO `Tipo_Material`(tipo_material) VALUES('CDA');
INSERT INTO `Tipo_Material`(tipo_material) VALUES('DVD');
INSERT INTO `Tipo_Material`(tipo_material) VALUES('Revista');
INSERT INTO `Tipo_Material`(tipo_material) VALUES('Libro');
INSERT INTO `Tipo_Material`(tipo_material) VALUES('Obra');
INSERT INTO `Tipo_Material`(tipo_material) VALUES('Tesis');

/*Registro Socios*/
/*INSERT INTO `Socios`(nombre, documento, telefono, correo_electronico, fecha_nacimiento) VALUES('Jorge Díaz', 'dc150489', '22222', 'jedc@yopmail.com', '1995/04/09');
INSERT INTO `Socios`(nombre, documento, telefono, correo_electronico, fecha_nacimiento) VALUES('Eduardo Trujillo', 'et2022', '33333', 'et@yopmail.com', '1990/05/06');
INSERT INTO `Socios`(nombre, documento, telefono, correo_electronico, fecha_nacimiento) VALUES('Erick Alas', 'ea2022', '44444', 'EA@yopmail.com', '1998/05/03');
INSERT INTO `Socios`(nombre, documento, telefono, correo_electronico, fecha_nacimiento) VALUES('Eduardo Matias', 'em2022', '55555', 'em@yopmail.com', '1994/09/18');
*/
/Registros materiales/
INSERT INTO `materiales`
(id, titulo, codigo_tipo_material, codigo_autor, numero_de_paginas, codigo_editorial, isbn, periodicidad, fecha_publicacion, codigo_artista, codigo_genero, duracion, numero_de_canciones, codigo_director, ubicacion, nombre_autor_CV, unidades_disponibles)
VALUES
('DVD00001', '2001: A Space Odyssey', 2, null, null, null, null, null, '1968/04/06', 6, 1, 142, null, 6, 'Seccion A, sucursal Escalon','Nombre Autor', 20);
INSERT INTO `materiales`
(id, titulo, codigo_tipo_material, codigo_autor, numero_de_paginas, codigo_editorial, isbn, periodicidad, fecha_publicacion, codigo_artista, codigo_genero, duracion, numero_de_canciones, codigo_director, ubicacion, nombre_autor_CV, unidades_disponibles)
VALUES
('CDA00001', 'Axis: Bold as Love', 1, null, null, null, null, null, '1967/12/01', 1, 6, 39, 13, null, 'Seccion B, sucursal Escalon','Nombre Autor', 15);
INSERT INTO `materiales`
(id, titulo, codigo_tipo_material, codigo_autor, numero_de_paginas, codigo_editorial, isbn, periodicidad, fecha_publicacion, codigo_artista, codigo_genero, duracion, numero_de_canciones, codigo_director, ubicacion, nombre_autor_CV, unidades_disponibles)
VALUES
('REV00001', 'Vanidades', 3, null, null, 2, null, null, '1995/05/02', null, null, null, 30, null, 'Seccion C, sucursal Escalon','Nombre Autor', 20);
INSERT INTO `materiales`
(id, titulo, codigo_tipo_material, codigo_autor, numero_de_paginas, codigo_editorial, isbn, periodicidad, fecha_publicacion, codigo_artista, codigo_genero, duracion, numero_de_canciones, codigo_director, ubicacion, nombre_autor_CV, unidades_disponibles)
VALUES
('LIB00001', 'Harry Potter: La Piedra Filosofal', 4, 5, 254, 1, '8478884459', null, '1999/01/01', null, 4, null, null, null, 'Seccion D, sucursal Escalon','Nombre Autor',7);
/*Insert para tipo usuario o rol*/
INSERT INTO `rol`(rol, numero_prestamos, dias_prestamo) VALUES('Administrador',99,99);
INSERT INTO `rol`(rol, numero_prestamos, dias_prestamo) VALUES('Profesor',7,7);
INSERT INTO `rol`(rol, numero_prestamos, dias_prestamo) VALUES('Alumno',3,5);

/*Registro para usuario*/
INSERT INTO `Usuarios`(nombre, apellido, nickname, email, pass, mora, fecha_nacimiento, codigo_rol) VALUES('Jorge Eduardo', 'Díaz Córdova', 'StarDestroyer', 'jorge@gmail.com', '123456', 0.00, '1995/09/04', 1);
INSERT INTO `Usuarios`(nombre, apellido, nickname, email, pass, mora, fecha_nacimiento, codigo_rol) VALUES('Eduardo', 'Trujillo', 'Trujillo', 'Trujillo@gmail.com', '123456', 0.00, '1990/10/10', 1);
INSERT INTO `Usuarios`(nombre, apellido, nickname, email, pass, mora, fecha_nacimiento, codigo_rol) VALUES('Erick', 'Alas', 'Erick', 'erick@gmail.com', '123456', 0.00, '1995/01/01', 1);

/*Registro prestamos*/
INSERT INTO `Prestamos`(codigo_material, codigo_usuario, fecha_prestamo, fecha_devolucion) VALUES('DVD00001', 1, '2022/09/27', '2022/10/01');
INSERT INTO `Prestamos`(codigo_material, codigo_usuario, fecha_prestamo, fecha_devolucion) VALUES('CDA00001', 2, '2022/08/27', '2022/09/12');
INSERT INTO `Prestamos`(codigo_material, codigo_usuario, fecha_prestamo, fecha_devolucion) VALUES('LIB00001', 3, '2022/09/20', '2022/09/30');

/*registro privilegios*//*
INSERT INTO `Privilegios`(guardar, actualizar, borrar, consultar, prestar, numero_prestamos, dias_prestamo) VALUES(1,1,1,1,1,99,99);
INSERT INTO `Privilegios`(guardar, actualizar, borrar, consultar, prestar, numero_prestamos, dias_prestamo) VALUES(0,0,0,1,1,6,7);
INSERT INTO `Privilegios`(guardar, actualizar, borrar, consultar, prestar, numero_prestamos, dias_prestamo) VALUES(0,0,0,1,1,3,2);*/

/*registro rol privilegio*/ /*
INSERT INTO `Permisos_por_Rol`(codigo_rol, codigo_privilegio) VALUES(1,1);
INSERT INTO `Permisos_por_Rol`(codigo_rol, codigo_privilegio) VALUES(2,2);
INSERT INTO `Permisos_por_Rol`(codigo_rol, codigo_privilegio) VALUES(3,3);*/

