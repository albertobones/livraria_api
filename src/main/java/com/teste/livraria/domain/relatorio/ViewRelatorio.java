package com.teste.livraria.domain.relatorio;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "view_relatorio")
public class ViewRelatorio {

	@Id
    @Column(name = "CodL")
    private Integer codL;

    @Column(name = "CodAu")
    private Integer codAu;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Titulo")
    private String titulo;

    @Column(name = "Editora")
    private String editora;

    @Column(name = "AnoPublicacao")
    private String anoPublicacao;

    @Column(name = "Edicao")
    private Integer edicao;

    @Column(name = "Valor")
    private BigDecimal valor;

    @Column(name = "CodAs")
    private Integer codAs;

    @Column(name = "Descricao")
    private String descricao;


}
