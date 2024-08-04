package com.teste.livraria.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.livraria.domain.Assunto;
import com.teste.livraria.dto.AssuntoDTO;
import com.teste.livraria.mapper.AssuntoMapper;
import com.teste.livraria.repository.AssuntoRepository;
import com.teste.livraria.service.exception.ObjectNotFoundException;

@Service
public class AssuntoService {
	
    @Autowired
    private AssuntoRepository assuntoRepository;
    
    @Autowired
    private AssuntoMapper assuntoMapper;

    public List<AssuntoDTO> findAll() {
        return assuntoRepository.findAll().stream().map(obj -> assuntoMapper.toAssuntoDTO(obj)).collect(Collectors.toList());
    }

    public AssuntoDTO findById(Integer id) {
    	Optional<Assunto> autor = assuntoRepository.findById(id); 
    	return assuntoMapper.toAssuntoDTO(autor.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + " Tipo: " + Assunto.class.getName())));
    }


    public AssuntoDTO create(AssuntoDTO assuntoDTO) {
        return assuntoMapper.toAssuntoDTO(assuntoRepository.save(assuntoMapper.toAssunto(assuntoDTO)));
    }

    public AssuntoDTO update(Integer codAs, AssuntoDTO assuntoDTO) {
    	assuntoDTO.setCodAs(codAs);
    	return assuntoMapper.toAssuntoDTO(assuntoRepository.save(assuntoMapper.toAssunto(assuntoDTO)));
    }

    public void deleteById(Integer id) {
    	assuntoRepository.deleteById(id);
    }
}
