package com.kk.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kk.domain.model.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

}
