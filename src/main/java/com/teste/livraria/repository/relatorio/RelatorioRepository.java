package com.teste.livraria.repository.relatorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.teste.livraria.domain.relatorio.ViewRelatorio;
import com.teste.livraria.dto.relatorio.LivroAutorAssuntoDTO;

@Repository
public interface RelatorioRepository extends CrudRepository<ViewRelatorio, Integer> {

	@Query("SELECT new com.teste.livraria.dto.relatorio.LivroAutorAssuntoDTO(v.codAu, v.nome, v.codL, v.titulo, v.editora, v.anoPublicacao, v.edicao, v.valor, v.codAs, v.descricao) " +
	           "FROM ViewRelatorio v " +
	           "ORDER BY v.nome, v.titulo, v.descricao")
    List<LivroAutorAssuntoDTO> findAllLivroAutorAssunto();
}