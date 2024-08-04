-- Criar a tabela de relacionamento Livro_Autor
CREATE TABLE "Livro_Autor" (
    "Livro_CodL" INTEGER,
    "Autor_CodAu" INTEGER,
    PRIMARY KEY ("Livro_CodL", "Autor_CodAu"),
    FOREIGN KEY ("Livro_CodL") REFERENCES "Livro"("CodL"),
    FOREIGN KEY ("Autor_CodAu") REFERENCES "Autor"("CodAu")
);

-- Criar os índices para a tabela Livro_Autor
CREATE INDEX "Livro_Autor_FKIndex1" ON "Livro_Autor"("Livro_CodL");
CREATE INDEX "Livro_Autor_FKIndex2" ON "Livro_Autor"("Autor_CodAu");