package com.teste.livraria.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    
    @NotEmpty(message = "O campo TITULO DO LIVRO é obrigatório!")
	@Length(min = 3, max = 40, message = "O campo TITULO DO LIVRO deve ter entre 3 a 40 caracteres!")
    @Column(name = "Titulo")
    private String titulo;
    
    @NotEmpty(message = "O campo EDITORA DO LIVRO é obrigatório!")
	@Length(min = 3, max = 40, message = "O campo EDITORA DO LIVRO deve ter entre 3 a 40 caracteres!")
    @Column(name = "Editora")
    private String editora;
    
    @NotNull(message = "O campo EDICAO DO LIVRO é obrigatório!")
    @Column(name = "Edicao")
    private Integer edicao;
    
    @NotEmpty(message = "O campo ANO PUBLICACAO DO LIVRO é obrigatório!")
	@Length(min = 4, max = 4, message = "O campo ANO PUBLICACAO DO LIVRO deve ter 4 caracteres!")
    @Column(name = "AnoPublicacao")
    private String anoPublicacao;
    
    @NotNull(message = "O campo VALOR DO LIVRO é obrigatório!")
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
