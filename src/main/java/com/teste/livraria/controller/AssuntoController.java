package com.teste.livraria.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.teste.livraria.dto.AssuntoDTO;
import com.teste.livraria.service.AssuntoService;
import com.teste.livraria.service.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/assuntos")
@AllArgsConstructor
@NoArgsConstructor
public class AssuntoController {
	
    @Autowired
    private AssuntoService assuntoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<AssuntoDTO>> getAllAssuntos() {
        return ResponseEntity.ok().body(assuntoService.findAll());
    }

    @GetMapping("/{codAs}")
    public ResponseEntity<AssuntoDTO> getAssuntoById(@PathVariable Integer codAs) {
        return ResponseEntity.ok().body(assuntoService.findById(codAs));
    }

    @PostMapping("/create")
    public ResponseEntity<AssuntoDTO> createAssunto(@RequestBody AssuntoDTO assuntoDTO) {
    	assuntoDTO = assuntoService.create(assuntoDTO);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codAs}").buildAndExpand(assuntoDTO.getCodAs()).toUri();
		return ResponseEntity.created(uri).body(assuntoDTO);
    }

    @PutMapping("/update/{codAs}")
    public ResponseEntity<AssuntoDTO> updateAutor(@PathVariable Integer codAs, @Valid @RequestBody AssuntoDTO assuntoDTO) {
    	assuntoDTO = assuntoService.update(codAs, assuntoDTO);
        return ResponseEntity.ok().body(assuntoDTO);
    }

    @DeleteMapping("/{codAs}")
    public ResponseEntity<Void> deleteAssunto(@PathVariable Integer codAs) {
        try {
        	assuntoService.deleteById(codAs);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Livro não pode ser deletado! Possui livros associados");
		}
		return ResponseEntity.noContent().build();
    }
}
