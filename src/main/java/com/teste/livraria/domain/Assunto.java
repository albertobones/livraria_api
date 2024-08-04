package com.teste.livraria.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Data
public class Assunto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codas")
    private Integer codAs;
    
    @NotEmpty(message = "O campo DESCRICAO DO ASSUNTO é obrigatório!")
	@Length(min = 3, max = 100, message = "O campo DESCRICAO DO ASSUNTO deve ter entre 3 e 40 caracteres!")
	@Column(name = "descricao")
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    @JsonIgnoreProperties("assuntos")
    private List<Livro> livros;
}
