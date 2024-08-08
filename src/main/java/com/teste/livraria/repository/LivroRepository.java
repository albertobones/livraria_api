package com.teste.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.livraria.domain.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {
}
