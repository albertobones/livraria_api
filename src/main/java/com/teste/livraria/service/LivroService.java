package com.teste.livraria.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.teste.livraria.domain.Livro;
import com.teste.livraria.dto.LivroDTO;
import com.teste.livraria.mapper.LivroMapper;
import com.teste.livraria.repository.LivroRepository;
import com.teste.livraria.service.exception.DataIntegrityViolationException;
import com.teste.livraria.service.exception.ObjectNotFoundException;

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

    @Transactional(propagation = Propagation.REQUIRED)
    public LivroDTO create(LivroDTO livroDTO) {
    	validaListaLivros(livroDTO);
        return livroMapper.toLivroDTO(livroRepository.save(livroMapper.toLivro(livroDTO)));
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public LivroDTO update(Integer codL, LivroDTO livroDTO) {
    	validaListaLivros(livroDTO);
    	livroDTO.setCodL(codL);    	
    	return livroMapper.toLivroDTO(livroRepository.save(livroMapper.toLivro(livroDTO)));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Integer id) {
        livroRepository.deleteById(id);
    }
    
    public void validaListaLivros(LivroDTO livroDTO) {
    	if(Objects.isNull(livroDTO.getAutores()) || livroDTO.getAutores().isEmpty()) {
    		throw new DataIntegrityViolationException("Deve ser informado pelo menos um AUTOR!");
    	}
    	if(Objects.isNull(livroDTO.getAssuntos()) || livroDTO.getAssuntos().isEmpty()) {
    		throw new DataIntegrityViolationException("Deve ser informado pelo menos um ASSUNTO!");
    	}
    }
}
