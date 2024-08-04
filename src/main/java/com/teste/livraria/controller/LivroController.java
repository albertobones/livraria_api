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

import com.teste.livraria.dto.LivroDTO;
import com.teste.livraria.service.LivroService;
import com.teste.livraria.service.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/livros")
public class LivroController {
	
    @Autowired
    private LivroService livroService;

    @GetMapping("/findAll")
    public ResponseEntity<List<LivroDTO>> getAllLivros() {
    	return ResponseEntity.ok().body(livroService.findAll());
    }

    @GetMapping("/{codL}")
    public ResponseEntity<LivroDTO> getLivroById(@PathVariable Integer codL) {
    	return ResponseEntity.ok().body(livroService.findById(codL));
    }

    @PostMapping("/create")
    public ResponseEntity<LivroDTO> createLivro(@Valid @RequestBody LivroDTO livroDTO) {
    	livroDTO = livroService.create(livroDTO);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codL}").buildAndExpand(livroDTO.getCodL()).toUri();
		return ResponseEntity.created(uri).body(livroDTO);
    }

    @PutMapping("/update/{codL}")
    public ResponseEntity<LivroDTO> updateLivro(@PathVariable Integer codL, @Valid @RequestBody LivroDTO livroDTO) {
        livroDTO = livroService.update(codL, livroDTO);        
        return ResponseEntity.ok().body(livroDTO);
    }

    @DeleteMapping("/{codL}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Integer codL) {
        try {
        	livroService.deleteById(codL);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Livro não pode ser deletado! Possui livros associados");
		}
		return ResponseEntity.noContent().build();
    }
}
