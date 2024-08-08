package com.teste.livraria.service;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.livraria.domain.Assunto;
import com.teste.livraria.domain.Autor;
import com.teste.livraria.domain.Livro;
import com.teste.livraria.repository.AssuntoRepository;
import com.teste.livraria.repository.AutorRepository;
import com.teste.livraria.repository.LivroRepository;

@Service
public class DBService {
	
	@Autowired
	private AutorRepository autorRepository;

	@Autowired
	private AssuntoRepository assuntoRepository;

	@Autowired
	private LivroRepository livroRepository;

	public void populaBDInicial() {
		//Autores
		Autor autor = new Autor(null, "João da Silva", null);
		Autor autor2 = new Autor(null, "Miguel Santos", null);
		Autor autor3 = new Autor(null, "Cora Coralina", null);
		Autor autor4 = new Autor(null, "Lorena Damant", null);
		//Assuntos
		Assunto assunto = new Assunto(null, "informática", null);
		Assunto assunto2 = new Assunto(null, "biologia", null);
		Assunto assunto3 = new Assunto(null, "história", null);
		Assunto assunto4 = new Assunto(null, "suspense", null);
		Assunto assunto5 = new Assunto(null, "aventura", null);
		Assunto assunto6 = new Assunto(null, "tecnologia", null);
		//Livros
		Livro livro = new Livro(null, "Java - como programar", "Deitel", 5, "2021", new BigDecimal("85.42"), Arrays.asList(autor, autor2), Arrays.asList(assunto, assunto6));
		Livro livro2 = new Livro(null, "Spring na prática", "Deitel", 7, "2023", new BigDecimal("115.11"), Arrays.asList(autor4), Arrays.asList(assunto6));
		Livro livro3 = new Livro(null, "Corpo humano", "Dr. Martin", 9, "2020", new BigDecimal("250.05"), Arrays.asList(autor3, autor), Arrays.asList(assunto3, assunto4, assunto6));
		Livro livro4 = new Livro(null, "Independência do Brasil", "Pedro A. Cabral", 3, "2015", new BigDecimal("50.33"), Arrays.asList(autor), Arrays.asList(assunto3, assunto5));
		
		autorRepository.saveAll(Arrays.asList(autor, autor2, autor3, autor4));
		assuntoRepository.saveAll(Arrays.asList(assunto, assunto2, assunto3, assunto4, assunto5, assunto6));
		livroRepository.saveAll(Arrays.asList(livro, livro2, livro3, livro4));
	}
}
