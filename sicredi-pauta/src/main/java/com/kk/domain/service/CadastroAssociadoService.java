package com.kk.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kk.domain.exception.NegocioException;
import com.kk.domain.model.Associado;
import com.kk.domain.repository.AssociadoRepository;

@Service
public class CadastroAssociadoService {

	@Autowired
	AssociadoRepository associadoRepository;
	
	public Associado salvar(Associado associado) {
		
		Associado associadoExistente = associadoRepository.findByNome(associado.getNome());
		
		if(associadoExistente != null) {
			throw new NegocioException("Já existe um associado com este nome");
		} else {
			return associadoRepository.save(associado);
		}
	}
}
