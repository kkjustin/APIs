package com.kk.api.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PautaModel {

	private Long id;
	private String assunto;
	private OffsetDateTime horarioAbertura;
	private Boolean aberto;
	private ContagemVotosModel contagem;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public OffsetDateTime getHorarioAbertura() {
		return horarioAbertura;
	}
	public void setHorarioAbertura(OffsetDateTime horarioAbertura) {
		this.horarioAbertura = horarioAbertura;
	}
	public Boolean getAberto() {
		return aberto;
	}
	public void setAberto(Boolean aberto) {
		this.aberto = aberto;
	}
	public ContagemVotosModel getContagem() {
		return contagem;
	}
	public void setContagem(ContagemVotosModel contagem) {
		this.contagem = contagem;
	}
}
