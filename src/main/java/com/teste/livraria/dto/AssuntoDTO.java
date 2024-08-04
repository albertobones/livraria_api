package com.teste.livraria.dto;

import com.teste.livraria.domain.Assunto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssuntoDTO {

    private Integer codAs;
    private String descricao;
    
    public AssuntoDTO(Assunto assunto) {
		super();
		this.codAs = assunto.getCodAs();
		this.descricao = assunto.getDescricao();
	}
    
}
