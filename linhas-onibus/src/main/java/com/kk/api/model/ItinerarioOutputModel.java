package com.kk.api.model;

import java.util.List;

public class ItinerarioOutputModel {

	private List<ItinerarioResumeModel> itinerarios;
	private String codigo;
	private String nome;
	
	public ItinerarioOutputModel(List<ItinerarioResumeModel> itinerarios, String codigo, String nome) {
		this.itinerarios = itinerarios;
		this.codigo = codigo;
		this.nome = nome;
	}
	
	public ItinerarioOutputModel() {
		
	}
	
	public List<ItinerarioResumeModel> getItinerarios() {
		return itinerarios;
	}
	public void setItinerarios(List<ItinerarioResumeModel> itinerarios) {
		this.itinerarios = itinerarios;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
