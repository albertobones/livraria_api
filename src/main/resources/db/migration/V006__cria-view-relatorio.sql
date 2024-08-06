-- Criar a view do relatório
CREATE or replace VIEW view_relatorio AS
select autor."CodAu", autor."Nome", livro."CodL", livro."Titulo", livro."Editora", livro."Edicao", livro."AnoPublicacao", livro."Valor", assunto."CodAs", assunto."Descricao" from "Autor" autor
left join "Livro_Autor" livro_autor on autor."CodAu" = livro_autor."Autor_CodAu"
left join "Livro" livro on livro_autor."Livro_CodL" = livro."CodL"
left join "Livro_Assunto" livro_assunto on livro_assunto."Livro_CodL" = livro."CodL" 
left join "Assunto" assunto on assunto."CodAs" = livro_assunto."Assunto_CodAs"
order by autor."Nome", livro."Titulo", assunto."Descricao";