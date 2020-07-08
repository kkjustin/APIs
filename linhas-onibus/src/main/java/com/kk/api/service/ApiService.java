package com.kk.api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kk.api.model.ItinerarioModel;
import com.kk.api.model.LinhaModel;

@Service
public class ApiService {

	@Autowired
	RestTemplate restTemplate;

	public List<LinhaModel> findAllLinhas() {
		ResponseEntity<List<LinhaModel>> linhasResponse = restTemplate.exchange(
				"http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<LinhaModel>>() {
				});
		return linhasResponse.getBody();
	}

	public ItinerarioModel findItinerarioById(String linhaId) {
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://www.poatransporte.com.br/php/facades/process.php").queryParam("a", "il")
				.queryParam("p", linhaId);

		ResponseEntity<Map<String, Object>> itinerarioResponse;
		try {
			itinerarioResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null,
					new ParameterizedTypeReference<Map<String, Object>>() {
					});
		} catch (RestClientException e) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		ItinerarioModel model = mapper.convertValue(itinerarioResponse.getBody(), ItinerarioModel.class);

		return model;
	}

	public List<LinhaModel> findLinhaByNome(String nome) {
		List<LinhaModel> linhas = findAllLinhas();
		return linhas.stream().filter(linha -> linha.getNome().contains(nome.toUpperCase())).collect(Collectors.toList());
	}
}
