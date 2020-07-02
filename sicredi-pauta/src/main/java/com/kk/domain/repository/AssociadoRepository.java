package com.kk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kk.domain.model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

	Associado findByNome(String nome);
	
}
