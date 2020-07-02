package com.kk.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kk.domain.exception.EntidadeNaoEncontradaException;
import com.kk.domain.exception.NegocioException;
import com.kk.domain.model.Associado;
import com.kk.domain.model.Pauta;
import com.kk.domain.model.Voto;
import com.kk.domain.repository.AssociadoRepository;
import com.kk.domain.repository.PautaRepository;
import com.kk.domain.repository.VotoRepository;

@Service
public class GestaoVotoService {
	
	@Autowired
	PautaRepository pautaRepository;
	
	@Autowired
	AssociadoRepository associadoRepository;
	
	@Autowired
	VotoRepository votoRepository;

	public Voto adicionarVoto(Long pautaId, String votoResposta, Voto voto) {
		
		votoResposta = votoResposta.replace("ã", "a").trim().toUpperCase();
		
		if(!"SIM".equals(votoResposta)
				&& !"NAO".equals(votoResposta)) {
			throw new NegocioException("Voto inválido: utilizar somente SIM/NAO.");
		}
		
		Pauta pauta = buscar(pautaId);
		
		if(!pauta.isAberto()) {
			throw new EntidadeNaoEncontradaException("Esta pauta não está aberta");
		}
		
		Associado associado = buscarAssociado(voto.getAssociado().getId());
		
		if(pauta.getVotos().stream()
			.anyMatch(cond -> cond.getAssociado().equals(associado))) {
			throw new NegocioException("Associado já votou");
		}
		
	    voto.setAssociado(associado);
		voto.setPauta(pauta);
		voto.setResposta(votoResposta);
		
		return votoRepository.save(voto);
	}

	private Associado buscarAssociado(Long associadoId) {
		return associadoRepository.findById(associadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Associado não encontrado"));
	}

	private Pauta buscar(Long pautaId) {
		return pautaRepository.findById(pautaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Pauta não encontrada"));
	}
}
