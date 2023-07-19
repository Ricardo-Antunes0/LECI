
CREATE SCHEMA ginasio;
go

CREATE TABLE ginasio.Pessoa(
	ID INT NOT NULL PRIMARY KEY,
	Nome VARCHAR(30) NOT NULL,
	Contacto CHAR(9) NOT NULL,
	Email VARCHAR(40),
);	

-- sor disse que n valia a pena ter nome como chave primaria 


CREATE TABLE ginasio.Atleta (
	N_atleta INT NOT NULL PRIMARY KEY,
	Nome VARCHAR(30) NOT NULL, 
	Idade INT NOT NULL,
	Genero CHAR(1) NOT NULL, 
	Modalidade VARCHAR(30) NOT NULL CHECK(Modalidade IN (' Men’s Bodybuilding','Men’s Physique', 'Classic Physique', 'Bikini Fitness', 'Wellness Fitness'),
	Categoria VARCHAR(30),
	Data_inscricao DATE,
	Restricao_alimentar VARCHAR(45),
	Restricao_treino VARCHAR(45),
	ID_nutri INT NOT NULL REFERENCES ginasio.Pessoa(ID),
	ID_treinador INT NOT NULL REFERENCES ginasio.Pessoa(ID),
);


CREATE TABLE ginasio.Treinador(
	Numero_atletas INT NOT NULL CHECK ( Numero_atletas <= 10),
	ID_treinador INT NOT NULL PRIMARY KEY,	
	Modalidade VARCHAR(30) NOT NULL CHECK(Modalidade IN(' Men’s Bodybuilding','Men’s Physique', 'Classic Physique', 'Bikini Fitness', 'Wellness Fitness')),
	CONSTRAINT TreinadorFK 
		FOREIGN KEY (ID_treinador) REFERENCES ginasio.Pessoa(ID) ON DELETE CASCADE  -- ver isto melhor
);

CREATE TABLE ginasio.Nutricionista(
	ID_nutri INT NOT NULL PRIMARY KEY,
	FOREIGN KEY (ID_nutri) REFERENCES ginasio.Pessoa(ID) ON DELETE CASCADE,
);

CREATE TABLE ginasio.Plano(
	Data DATE NOT NULL PRIMARY KEY,
	CHECK(Data >= ),
	-- DATA DO PLANO TEM DE SER MAIOR QUE A DATA ATUAL
);

CREATE TABLE ginasio.Plano_alimentacao(
	Alimentacao NOT NULL,
	FOREIGN KEY (Data) REFERENCES ginasio.Plano(data) ON DELETE CASCADE,
	CONSTRAINT PlanoAliPK
		PRIMARY KEY (Alimentacao, Data),
);

CREATE TABLE ginasio.Plano_treino(
	Treino NOT NULL,
	FOREIGN KEY (Data) REFERENCES ginasio.Plano(data) ON DELETE CASCADE,
	CONSTRAINT PlanoTreinoPK
		PRIMARY KEY(Treino, Data)
);

CREATE TABLE ginasio.Pertence();

CREATE TABLE ginasio.Ginasio();

CREATE TABLE ginasio.Fotos();
