DROP DATABASE IF EXISTS mexicho;
CREATE DATABASE mexicho;
\c mexicho
CREATE SCHEMA usuarios;
CREATE SCHEMA marcadores;
CREATE SCHEMA temas;
CREATE SCHEMA comentarios;

CREATE TABLE usuarios.usuario(
    id_usuario integer NOT NULL,
    nombre varchar NOT NULL,
    correo varchar NOT NULL,
    contraseña varchar NOT NULL,
    rol varchar NOT NULL,
    url_imagen varchar, 
    CONSTRAINT "PK_usuario" PRIMARY KEY (id_usuario));
COMMENT ON TABLE usuarios.usuario IS 'Tabla donde se almacenan la informacion de  los usuarios, se otorga una id única a cada usuario que se agrega a la BD';

CREATE TABLE temas.tema(
    id_tema integer NOT NULL,
    id_usuario integer, 
    titulo_tema varchar NOT NULL,
    descripcion_tema varchar,
    color_tema varchar NOT NULL,
    CONSTRAINT "PK_tema" PRIMARY KEY (id_tema),
    CONSTRAINT "FK_tema" FOREIGN KEY (id_usuario) REFERENCES usuarios.usuario(id_usuario) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT "U_tema" UNIQUE (id_tema, id_usuario));    
COMMENT ON TABLE temas.tema IS 'Tabla donde se almacenan los temas, se otorga una id única a cada tema que se agrega a la BD';

CREATE TABLE marcadores.marcador(
    id_marcador integer NOT NULL,
    id_tema integer NOT NULL,
    titulo_marcador varchar NOT NULL,
    descripcion_marcador varchar,
    fecha_marcador date NOT NULL,
    url_foto varchar NOT NULL,
    latitud float8 NOT NULL,
    longitud float8 NOT NULL,
    estado varchar NOT NULL,
    CONSTRAINT "PK_marcador" PRIMARY KEY (id_marcador),
    CONSTRAINT "FK_marcador" FOREIGN KEY (id_tema) REFERENCES temas.tema(id_tema) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT "U_marcador" UNIQUE (id_marcador,id_tema));    
COMMENT ON TABLE marcadores.marcador IS 'Tabla donde se almacenan los marcadores, se otorga una id única a cada marcador que se agrega a la BD';


CREATE TABLE comentarios.comentario(
    id_comentario integer NOT NULL, 
    id_comentarista integer NOT NULL,
    id_marcador integer NOT NULL,
    id_tema integer NOT NULL,
    comentario varchar NOT NULL,
    CONSTRAINT "PK_comentario" PRIMARY KEY (id_comentario),
    CONSTRAINT "FK1_comentario" FOREIGN KEY (id_comentarista) REFERENCES usuarios.usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT "FK2_comentario" FOREIGN KEY (id_marcador,id_tema) REFERENCES marcadores.marcador(id_marcador,id_tema) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT "U_comentario" UNIQUE (id_comentario,id_comentarista, id_marcador, id_tema));    
COMMENT ON TABLE comentarios.comentario IS 'Tabla donde se almacenan los comentarios, se otorga una id única a cada comentario que se agrega a la BD';
    
CREATE TABLE comentarios.calificacion(
    id_calificador integer NOT NULL,
    id_comentario integer NOT NULL, 
    id_comentarista integer NOT NULL,
    id_marcador integer NOT NULL,
    id_tema integer NOT NULL, 
    calificacion integer NOT NULL,
    CONSTRAINT "FK1_calificacion" FOREIGN KEY (id_calificador) REFERENCES usuarios.usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT "FK2_calificacion" FOREIGN KEY (id_comentario,id_comentarista, id_marcador,id_tema) REFERENCES comentarios.comentario(id_comentario,id_comentarista, id_marcador,id_tema) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT "U_calificacion" UNIQUE (id_calificador,id_comentarista,id_comentario,id_marcador,id_tema));    
COMMENT ON TABLE comentarios.calificacion IS 'Tabla donde se almacenan las calificaciones a los comentarios';
