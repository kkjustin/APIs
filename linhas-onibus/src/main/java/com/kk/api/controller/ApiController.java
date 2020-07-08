package com.kk.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kk.api.model.ItinerarioModel;
import com.kk.api.model.LinhaModel;
import com.kk.api.service.ApiService;

@RestController
public class ApiController {

	@Autowired
	ApiService apiService;

	@GetMapping("/linhas")
	public ResponseEntity<List<LinhaModel>> listarLinhas(HttpServletRequest request) {
		String nome = request.getParameter("nome");
		
		if(nome == null) return ResponseEntity.ok(apiService.findAllLinhas());
		
		List<LinhaModel> linhas = apiService.findLinhaByNome(nome);
		if(!linhas.isEmpty()) {
			return ResponseEntity.ok(linhas);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/itinerario/{linhaId}")
	public ResponseEntity<ItinerarioModel> buscarItinerario(@PathVariable String linhaId) {
		ItinerarioModel itinerario = apiService.findItinerarioById(linhaId);

		if (itinerario != null) {
			return ResponseEntity.ok(itinerario);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
