package com.teste.livraria.dto.relatorio;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class LivroAutorAssuntoDTO {
	private Integer codAu;
    private String nome;
    private Integer codL;
    private String titulo;
    private String editora;
    private String anoPublicacao;
    private Integer edicao;
    private BigDecimal valor;
    private Integer codAs;
    private String descricao;

    public LivroAutorAssuntoDTO(Integer codAu, String nome, Integer codL, String titulo, String editora, String anoPublicacao, Integer edicao, BigDecimal valor, Integer codAs, String descricao) {
        this.codAu = codAu;
        this.nome = nome;
        this.codL = codL;
        this.titulo = titulo;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.edicao = edicao;
        this.valor = valor;
        this.codAs = codAs;
        this.descricao = descricao;
    }
}
