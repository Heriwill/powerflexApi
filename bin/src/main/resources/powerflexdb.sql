USE master if exists (SELECT * FROM SYS.databases WHERE name = 'powerflexdb')

DROP DATABASE powerflexdb
GO

CREATE DATABASE powerflexdb
GO 

USE powerflexdb
GO


CREATE TABLE usuarios (
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
cpf VARCHAR(100) NULL,
email VARCHAR(45) NOT NULL,
password VARCHAR(250) NOT NULL,
cod_status BIT NOT NULL,
)

CREATE TABLE telefones(
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
numero VARCHAR(15) NOT NULL,
cod_status BIT NOT NULL,
usuario_id BIGINT NOT NULL,
CONSTRAINT fk_telefone_usuario_id FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
)

--Recursos necess�rios para o sistema do login
CREATE TABLE papeis(
id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome_papel VARCHAR(45) NOT NULL,
descricao_papel VARCHAR(250) NULL,
cod_status_papel BIT NULL
)

CREATE TABLE usuarios_papeis(
usuario_id BIGINT NOT NULL,
papel_id BIGINT NOT NULL,
CONSTRAINT fk_usuarios_papeis_usuario_id FOREIGN KEY(usuario_id) REFERENCES usuarios(id),
CONSTRAINT fk_usuarios_papeis_papel_id FOREIGN KEY(papel_id) REFERENCES papeis(id)
)
