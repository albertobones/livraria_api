package com.teste.livraria.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name="Livro")
@AllArgsConstructor
@NoArgsConstructor
public class Livro {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodL")
    private Integer codL;
    
    @Column(name = "Titulo")
    private String titulo;
    
    @Column(name = "Editora")
    private String editora;
    
    @Column(name = "Edicao")
    private Integer edicao;
    
    @Column(name = "AnoPublicacao")
    private String anoPublicacao;
    
    @Column(name = "Valor")
    private BigDecimal valor;

    @ManyToMany
    @JoinTable(
        name = "Livro_Autor",
        joinColumns = @JoinColumn(name = "Livro_CodL"),
        inverseJoinColumns = @JoinColumn(name = "Autor_CodAu")
    )
    @JsonIgnoreProperties("livros")
    private List<Autor> autores;

    @ManyToMany
    @JoinTable(
        name = "Livro_Assunto",
        joinColumns = @JoinColumn(name = "Livro_CodL"),
        inverseJoinColumns = @JoinColumn(name = "Assunto_CodAs")
    )
    @JsonIgnoreProperties("livros")
    private List<Assunto> assuntos;
}
