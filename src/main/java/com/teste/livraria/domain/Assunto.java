package com.teste.livraria.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@Entity
@Data
@Table(name="Assunto")
@AllArgsConstructor
@NoArgsConstructor
public class Assunto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CodAs")
    private Integer codAs;
    
    @NotEmpty(message = "O campo DESCRICAO DO ASSUNTO é obrigatório!")
	@Length(min = 3, max = 20, message = "O campo DESCRICAO DO ASSUNTO deve ter entre 3 e 20 caracteres!")
	@Column(name = "Descricao")
    private String descricao;

    @ManyToMany(mappedBy = "assuntos")
    @JsonIgnoreProperties("assuntos")
    private List<Livro> livros;
}
