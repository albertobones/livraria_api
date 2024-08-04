package com.teste.livraria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.livraria.domain.Livro;
import com.teste.livraria.dto.LivroDTO;
import com.teste.livraria.mapper.LivroMapper;
import com.teste.livraria.repository.LivroRepository;
import com.teste.livraria.service.exception.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivroService {
	
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper livroMapper;

    public List<LivroDTO> findAll() {
        return livroRepository.findAll().stream().map(obj -> livroMapper.toLivroDTO(obj)).collect(Collectors.toList());
    }

    public LivroDTO findById(Integer id) {
    	Optional<Livro> livro = livroRepository.findById(id); 
        return livroMapper.toLivroDTO(livro.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Livro.class.getName())));
    }

    public LivroDTO create(LivroDTO livroDTO) {
        return livroMapper.toLivroDTO(livroRepository.save(livroMapper.toLivro(livroDTO)));
    }
    
    public LivroDTO update(Integer codL, LivroDTO livroDTO) {
    	livroDTO.setCodL(codL);
    	return livroMapper.toLivroDTO(livroRepository.save(livroMapper.toLivro(livroDTO)));
    }

    public void deleteById(Integer id) {
        livroRepository.deleteById(id);
    }
}
