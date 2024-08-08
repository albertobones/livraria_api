package com.teste.livraria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.teste.livraria.domain.Autor;
import com.teste.livraria.dto.AutorDTO;

@Mapper(componentModel = "spring")
public interface AutorMapper {

	AutorMapper INSTANCE = Mappers.getMapper(AutorMapper.class);
	
	AutorDTO toAutorDTO(Autor autor);
	
	Autor toAutor(AutorDTO autorDTO);
}
