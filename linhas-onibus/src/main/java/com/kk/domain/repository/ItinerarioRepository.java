package com.kk.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kk.domain.model.Itinerario;
import com.kk.domain.model.Linha;

@Repository
public interface ItinerarioRepository extends JpaRepository<Itinerario, Integer>{

	List<Itinerario> findByLinha(Linha linha);
}
