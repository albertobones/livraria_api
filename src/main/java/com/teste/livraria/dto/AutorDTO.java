package com.teste.livraria.dto;

import java.util.List;

import com.teste.livraria.domain.Autor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorDTO {
	
	private Integer codAu;    
    private String nome;
    private List<LivroDTO> livroDTOs;
	
	public AutorDTO(Autor autor) {
    	super();
    	this.codAu = autor.getCodAu();
    	this.nome = autor.getNome();
    }
}
