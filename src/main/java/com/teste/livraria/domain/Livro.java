package com.teste.livraria.domain;

import lombok.Data;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Livro {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codl")
    private Integer codL;
    
    private String titulo;
    private String editora;
    private Integer edicao;
    @Column(name = "anopublicacao")
    private String anoPublicacao;
    private BigDecimal valor;

    @ManyToMany
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_codl"),
        inverseJoinColumns = @JoinColumn(name = "autor_codau")
    )
    @JsonIgnoreProperties("livros")
    private List<Autor> autores;

    @ManyToMany
    @JoinTable(
        name = "livro_assunto",
        joinColumns = @JoinColumn(name = "livro_codl"),
        inverseJoinColumns = @JoinColumn(name = "assunto_codas")
    )
    @JsonIgnoreProperties("livros")
    private List<Assunto> assuntos;
}
