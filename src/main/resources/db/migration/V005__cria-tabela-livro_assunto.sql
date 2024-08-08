-- Criar a tabela de relacionamento Livro_Assunto
CREATE TABLE "Livro_Assunto" (
    "Livro_CodL" INTEGER,
    "Assunto_CodAs" INTEGER,
    PRIMARY KEY ("Livro_CodL", "Assunto_CodAs"),
    FOREIGN KEY ("Livro_CodL") REFERENCES "Livro"("CodL"),
    FOREIGN KEY ("Assunto_CodAs") REFERENCES "Assunto"("CodAs")
);

-- Criar os índices para a tabela Livro_Assunto
CREATE INDEX "Livro_Assunto_FKIndex1" ON "Livro_Assunto"("Livro_CodL");
CREATE INDEX "Livro_Assunto_FKIndex2" ON "Livro_Assunto" ("Assunto_CodAs");