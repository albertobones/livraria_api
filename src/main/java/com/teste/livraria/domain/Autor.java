package com.teste.livraria.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Autor {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codau")
    private Integer codAu;
    
    @NotEmpty(message = "O campo NOME DO AUTOR é obrigatório!")
	@Length(min = 3, max = 100, message = "O campo NOME DO AUTOR deve ter entre 3 e 40 caracteres!")
	@Column(name = "nome")
    private String nome;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnoreProperties("autores")
    private List<Livro> livros;
}
