package com.kk.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kk.domain.model.Linha;
import com.kk.domain.repository.LinhaRepository;
import com.kk.domain.service.CadastroLinhaService;

@RestController
@RequestMapping("/db/linha")
public class LinhaController {
	
	@Autowired
	CadastroLinhaService cadastroLinhaService;
	
	@Autowired
	LinhaRepository linhaRepository;
	
	@GetMapping
	public List<Linha> buscarTodos(){
		return linhaRepository.findAll();
	}

	@PutMapping
	public void adicinarLinha(@Valid @RequestBody Linha linhaInput) {
		cadastroLinhaService.salvarAlterarLinha(linhaInput);
	}
}
