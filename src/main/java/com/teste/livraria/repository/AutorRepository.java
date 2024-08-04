package com.teste.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.livraria.domain.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
