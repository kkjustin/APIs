package com.kk.api.model;

public class VotoModel {

	private Long id;
	private PautaResumoModel pauta;
	private AssociadoResumoModel associado;
	private String resposta;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public PautaResumoModel getPauta() {
		return pauta;
	}
	public void setPauta(PautaResumoModel pauta) {
		this.pauta = pauta;
	}
	public AssociadoResumoModel getAssociado() {
		return associado;
	}
	public void setAssociado(AssociadoResumoModel associado) {
		this.associado = associado;
	}
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
}
