package com.teste.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teste.livraria.domain.Assunto;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto, Integer> {
}
