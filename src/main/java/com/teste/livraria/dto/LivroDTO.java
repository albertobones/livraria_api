package com.teste.livraria.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.teste.livraria.domain.Livro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LivroDTO {
	
    private Integer codL;
    private String titulo;
    private String editora;
    private Integer edicao;
    private String anoPublicacao;
    private BigDecimal valor;
    
    private List<AutorDTO> autores;
    private List<AssuntoDTO> assuntos;
    
    public LivroDTO(Livro livro) {
    	super();
    	this.codL = livro.getCodL();
    	this.titulo = livro.getTitulo();
    	this.editora = livro.getEditora();
    	this.edicao = livro.getEdicao();
    	this.anoPublicacao = livro.getAnoPublicacao();
    	this.valor = livro.getValor();
    	this.autores = livro.getAutores().stream().map(obj -> new AutorDTO(obj)).collect(Collectors.toList());
    	this.assuntos = livro.getAssuntos().stream().map(obj -> new AssuntoDTO(obj)).collect(Collectors.toList());
    }
}
