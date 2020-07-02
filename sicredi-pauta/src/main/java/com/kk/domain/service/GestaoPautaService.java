package com.kk.domain.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kk.api.model.ContagemVotosModel;
import com.kk.api.model.PautaModel;
import com.kk.domain.model.Pauta;
import com.kk.domain.repository.PautaRepository;

@Service
public class GestaoPautaService {

	@Autowired
	PautaRepository pautaRepository;

	@Autowired
	ModelMapper modelMapper;

	public List<PautaModel> buscarDados(List<Pauta> pautas) {
		List<PautaModel> pautaModelList = new ArrayList<>();

		for (Pauta pauta : pautas) {
			PautaModel pautaModel = converterParaModel(pauta);
			pautaModelList.add(pautaModel);
		}
		return pautaModelList;
	}
	
	public PautaModel converterParaModel(Pauta pauta) {
		ContagemVotosModel contagem;
		contagem = new ContagemVotosModel();
		PautaModel pautaModel = null;
		
		if(!pauta.getVotos().isEmpty()) {
			contagem.setVotosNao(
					(int) pauta.getVotos().stream()
					.filter(voto -> "NAO".equals(voto.getResposta()))
					.count());
			contagem.setVotosSim(
					(int) pauta.getVotos().stream()
					.filter(voto -> "SIM".equals(voto.getResposta()))
					.count());
		}
		if (pauta.isAberto()) {
			if(pautaModel == null) pautaModel = toModel(pauta);
			pautaModel.setAberto(true);
		} else {
			if(pautaModel == null) pautaModel = toModel(pauta);
			pautaModel.setAberto(false);
		}
		if(!pauta.getVotos().isEmpty()) pautaModel.setContagem(contagem);
		return pautaModel;
	}

	private PautaModel toModel(Pauta pauta) {
		return modelMapper.map(pauta, PautaModel.class);
	}

	public void iniciar(Long pautaId, HttpServletRequest request) {
		Pauta pauta = pautaRepository.findById(pautaId).get();
		Integer espera;
		
		try {
			espera = Integer.parseInt(request.getParameter("espera"));
		} catch (NumberFormatException e) {
			espera = 1;
		}
		
		pauta.setEspera(espera);
		pauta.iniciar();
		pautaRepository.save(pauta);
	}
}
