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

import com.teste.livraria.dto.AutorDTO;
import com.teste.livraria.service.AutorService;
import com.teste.livraria.service.exception.DataIntegrityViolationException;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/autores")
@NoArgsConstructor
public class AutorController {
	
    @Autowired
    private AutorService autorService;

    @GetMapping("/findAll")
    public ResponseEntity<List<AutorDTO>> getAllAutores() {
        return ResponseEntity.ok().body(autorService.findAll());
    }

    @GetMapping("/{codAu}")
    public ResponseEntity<AutorDTO> getAutorById(@PathVariable Integer codAu) {
        return ResponseEntity.ok().body(autorService.findById(codAu));
    }

    @PostMapping("/create")
    public ResponseEntity<AutorDTO> createAutor(@Valid @RequestBody AutorDTO autorDTO) {
    	autorDTO = autorService.create(autorDTO);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{CodAu}").buildAndExpand(autorDTO.getCodAu()).toUri();
		return ResponseEntity.created(uri).body(autorDTO);
    }

    @PutMapping("/update/{codAu}")
    public ResponseEntity<AutorDTO> updateAutor(@PathVariable Integer codAu, @Valid @RequestBody AutorDTO autorDTO) {
    	autorDTO = autorService.update(codAu, autorDTO);
        return ResponseEntity.ok().body(autorDTO);
    }

    @DeleteMapping("/{codAu}")
    public ResponseEntity<Void> deleteAutor(@PathVariable Integer codAu) {
        try {
        	autorService.deleteById(codAu);
		} catch (Exception e) {
			throw new DataIntegrityViolationException("Autor não pode ser deletado! Possui livros associados");
		}
		return ResponseEntity.noContent().build();
    }
}
