-- Criar a tabela Livro
CREATE TABLE Livro (
    CodL SERIAL PRIMARY KEY,
    Titulo VARCHAR(40) NOT NULL,
    Editora VARCHAR(40),
    Edicao INTEGER,
    AnoPublicacao VARCHAR(4),
    valor DECIMAL(10, 2)
);