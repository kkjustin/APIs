package com.kk.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kk.domain.model.Associado;
import com.kk.domain.repository.AssociadoRepository;
import com.kk.domain.service.CadastroAssociadoService;

@RestController
@RequestMapping("/associados")
public class AssociadoController {
	
	@Autowired
	AssociadoRepository associadoRepository;
	
	@Autowired
	CadastroAssociadoService associadoService;
	
	@GetMapping
	public List<Associado> listar() {
		return associadoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Associado adicionar(@Valid @RequestBody Associado associado) {
		return associadoService.salvar(associado);
	}
}
