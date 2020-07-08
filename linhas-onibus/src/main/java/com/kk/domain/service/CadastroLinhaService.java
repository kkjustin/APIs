package com.kk.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kk.domain.model.Linha;
import com.kk.domain.repository.LinhaRepository;

@Service
public class CadastroLinhaService {
	
	@Autowired
	LinhaRepository linhaRepository;

	public Linha salvarAlterarLinha(Linha linhaInput) {
		Linha linhaExistente = linhaRepository.findByCodigo(linhaInput.getCodigo());
		
		if(linhaExistente != null) {
			linhaExistente.setNome(linhaInput.getNome());
			return linhaRepository.save(linhaExistente);
		} else {
			linhaExistente = linhaRepository.findByNome(linhaInput.getNome());
			if(linhaExistente != null) {
				linhaExistente.setCodigo(linhaInput.getCodigo());
				return linhaRepository.save(linhaExistente);
			}
		}
		return linhaRepository.save(linhaInput);
	}
}
