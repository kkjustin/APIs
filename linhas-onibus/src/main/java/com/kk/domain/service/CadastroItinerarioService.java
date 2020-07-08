package com.kk.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kk.api.model.ItinerarioOutputModel;
import com.kk.api.model.ItinerarioResumeModel;
import com.kk.domain.exception.NegocioException;
import com.kk.domain.model.Itinerario;
import com.kk.domain.model.Linha;
import com.kk.domain.repository.ItinerarioRepository;
import com.kk.domain.repository.LinhaRepository;

@Service
public class CadastroItinerarioService {
	
	@Autowired
	LinhaRepository linhaRepository;
	
	@Autowired
	ItinerarioRepository itinerarioRepository;

	public void salvarAlterarItinerario(Integer linhaId, 
			@Valid ItinerarioResumeModel itinerarioInput) {
		
		Linha linha = linhaRepository.findById(linhaId)
				.orElseThrow(() -> new NegocioException("Linha não encontrada"));
		
		Itinerario itinerario = new Itinerario();
		itinerario.setLinha(linha);
		itinerario.setLat(itinerarioInput.getLat());
		itinerario.setLng(itinerarioInput.getLng());
		
		itinerarioRepository.save(itinerario);
	}

	public List<ItinerarioOutputModel> buscarTodos() {
		
		List<Linha> linhas = linhaRepository.findAll();
		List<ItinerarioOutputModel> itinerariosOutput = new ArrayList<>();
		
		for (Linha linha : linhas) {
			List<ItinerarioResumeModel> itinerariosResume = new ArrayList<>();
			List<Itinerario> itinerarios = itinerarioRepository.findByLinha(linha);
			for (Itinerario itinerario : itinerarios) {
				itinerariosResume.add(new ItinerarioResumeModel(itinerario.getLat(), itinerario.getLng()));
			}
			itinerariosOutput.add(
					new ItinerarioOutputModel(itinerariosResume,
							linha.getCodigo(), linha.getNome()));
		}
		
		return itinerariosOutput;
	}
	
	public List<Itinerario> buscaItinerarioPorRaio(HttpServletRequest request) {		
		Double km = 0.0;
		Double lat = 0.0;
		Double lng = 0.0;
		
		try {
			km = converterParam(request.getParameter("km"));
			lat = converterParam(request.getParameter("lat"));
			lng = converterParam(request.getParameter("lng"));
		} catch (Exception e) {
			throw new NegocioException("Favor adicionar todos os parametros (km, lat, lng)");
		}
		
		if(lat < -90 || lat > 90) throw new NegocioException("Latitude inválida");
		if(lng < -180 || lng > 180) throw new NegocioException("Longitude inválida");
		
		Double deltaLat = km / 111.1;
		Double deltaLng = km / ( 111.320 * Math.cos( lat / 180.0 * Math.PI));

		Double minLat = lat - deltaLat;  
		Double maxLat = lat + deltaLat;
		Double minLng = lng - deltaLng;
		Double maxLng = lng + deltaLng;
		
		List<Itinerario> itinerarios = itinerarioRepository.findAll()
				.stream()
				.filter(it -> it.dentroDaRange(minLat, maxLat, minLng,maxLng).equals(true))
				.collect(Collectors.toList());
		
		return itinerarios;		
	}
	
	private Double converterParam(String param) {
		return Double.parseDouble(param);
	}
}
