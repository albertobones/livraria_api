package com.teste.livraria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.livraria.domain.Autor;
import com.teste.livraria.dto.AutorDTO;
import com.teste.livraria.mapper.AutorMapper;
import com.teste.livraria.repository.AutorRepository;
import com.teste.livraria.service.exception.ObjectNotFoundException;

@Service
public class AutorService {
	
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private AutorMapper autorMapper;
    
    public List<AutorDTO> findAll() {
        return autorRepository.findAll().stream().map(obj -> autorMapper.toAutorDTO(obj)).collect(Collectors.toList());
    }

    public AutorDTO findById(Integer id) {
    	Optional<Autor> autor = autorRepository.findById(id); 
        return autorMapper.toAutorDTO(autor.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Autor.class.getName())));
    }

    public AutorDTO create(AutorDTO autorDTO) {
        return autorMapper.toAutorDTO(autorRepository.save(autorMapper.toAutor(autorDTO)));
    }

    public AutorDTO update(Integer codAu, AutorDTO autorDTO) {
    	autorDTO.setCodAu(codAu);
    	return autorMapper.toAutorDTO(autorRepository.save(autorMapper.toAutor(autorDTO)));
    }
    
    public void deleteById(Integer id) {
        autorRepository.deleteById(id);
    }
    
}
