package com.kk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kk.domain.model.Linha;

@Repository
public interface LinhaRepository extends JpaRepository<Linha, Integer>{

	Linha findByNome(String nome);
	Linha findByCodigo(String codigo);
}
