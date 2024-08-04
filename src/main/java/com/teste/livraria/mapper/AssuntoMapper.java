package com.teste.livraria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.teste.livraria.domain.Assunto;
import com.teste.livraria.dto.AssuntoDTO;

@Mapper(componentModel = "spring")
public interface AssuntoMapper {

	AssuntoMapper INSTANCE = Mappers.getMapper(AssuntoMapper.class);
	
	AssuntoDTO toAssuntoDTO(Assunto assunto);
	
	Assunto toAssunto(AssuntoDTO assuntoDTO);
}
