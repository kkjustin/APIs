package com.kk.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VotoInputModel {

	@NotBlank
	private String resposta;
	
	@Valid
	@NotNull
	private AssociadoModel associado;

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public AssociadoModel getAssociado() {
		return associado;
	}

	public void setAssociado(AssociadoModel associado) {
		this.associado = associado;
	}
}
