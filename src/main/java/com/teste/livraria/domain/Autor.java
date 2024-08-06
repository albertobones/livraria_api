package com.teste.livraria.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="Autor")
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodAu")
    private Integer codAu;
    
    @NotEmpty(message = "O campo NOME DO AUTOR é obrigatório!")
	@Length(min = 3, max = 40, message = "O campo NOME DO AUTOR deve ter entre 3 e 40 caracteres!")
	@Column(name = "Nome")
    private String nome;

    @ManyToMany(mappedBy = "autores")
    @JsonIgnoreProperties("autores")
    private List<Livro> livros;
}
