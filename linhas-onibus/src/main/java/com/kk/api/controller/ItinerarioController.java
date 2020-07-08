package com.kk.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kk.api.model.ItinerarioOutputModel;
import com.kk.api.model.ItinerarioResumeModel;
import com.kk.domain.model.Itinerario;
import com.kk.domain.service.CadastroItinerarioService;

@RestController
@RequestMapping("/db/itinerario")
public class ItinerarioController {
	
	@Autowired
	CadastroItinerarioService itinerarioService;

	@GetMapping
	public  List<ItinerarioOutputModel> buscarTodos() {
		return itinerarioService.buscarTodos();
	}
	
	@PutMapping("/{linhaId}")
	public void adicionarItinerario(@Valid @RequestBody ItinerarioResumeModel itinerarioInput,
			@PathVariable Integer linhaId) {
		itinerarioService.salvarAlterarItinerario(linhaId, itinerarioInput);
	}
	
	@GetMapping("/porkm")
	public ResponseEntity<List<Itinerario>> itinerariosByKmRaio(HttpServletRequest request) {
		
		List<Itinerario> itinerarios = itinerarioService.buscaItinerarioPorRaio(request);
		
		if(!itinerarios.isEmpty()) {
			return ResponseEntity.ok(itinerarios);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
