package com.kk.api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kk.api.model.PautaModel;
import com.kk.domain.model.Pauta;
import com.kk.domain.repository.PautaRepository;
import com.kk.domain.service.GestaoPautaService;

@RestController
@RequestMapping("/pautas")
public class PautaController {

	@Autowired
	PautaRepository pautaRepository;
	
	@Autowired
	GestaoPautaService gestaoPauta;
	
	@Autowired
	ModelMapper modelMapper;
	
	@GetMapping
	public List<PautaModel> listar() {
		return gestaoPauta.buscarDados(pautaRepository.findAll());	
	}

	@GetMapping("/{pautaId}")
	public ResponseEntity<PautaModel> buscar(@PathVariable Long pautaId) {
		
		Optional<Pauta> pauta = pautaRepository.findById(pautaId);
		
		if(pauta.isPresent()) {
			return ResponseEntity.ok(gestaoPauta.converterParaModel(pauta.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pauta adicionar(@Valid @RequestBody Pauta pauta) {
		pauta.setVotos(null);
		return pautaRepository.save(pauta);
	}
	
	@PutMapping("/{pautaId}/iniciar")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void iniciar(@PathVariable Long pautaId,
						HttpServletRequest request) {
		gestaoPauta.iniciar(pautaId, request);
	}
}
