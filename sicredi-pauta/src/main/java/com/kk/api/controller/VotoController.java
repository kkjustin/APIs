package com.kk.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kk.api.model.VotoInputModel;
import com.kk.api.model.VotoModel;
import com.kk.domain.model.Voto;
import com.kk.domain.service.GestaoVotoService;

@RestController
@RequestMapping("/pauta/{pautaId}/votar")
public class VotoController {
	
	@Autowired
	GestaoVotoService votoServico;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VotoModel adicionar(@PathVariable Long pautaId,
			@Valid @RequestBody VotoInputModel votoInput) {
		
		Voto voto = toEntity(votoInput);
		return toModel(votoServico.adicionarVoto(pautaId, voto.getResposta(), voto));
	}

	private Voto toEntity(@Valid VotoInputModel votoInput) {
		return modelMapper.map(votoInput, Voto.class);
	}

	private VotoModel toModel(Voto voto) {
		return modelMapper.map(voto, VotoModel.class);
	}
}
