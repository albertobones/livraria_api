package com.teste.livraria.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.teste.livraria.domain.Livro;
import com.teste.livraria.dto.LivroDTO;

@Mapper(componentModel = "spring")
public interface LivroMapper {

	LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);
	
	LivroDTO toLivroDTO(Livro livro);
	
	Livro toLivro(LivroDTO livroDTO);
}
