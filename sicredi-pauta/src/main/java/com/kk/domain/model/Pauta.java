package com.kk.domain.model;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_NULL)
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String assunto;
	
	@Column(name = "horario_abertura")
	private OffsetDateTime horararioAbertura;
	
	private Integer espera;
	
	@OneToMany(mappedBy = "pauta")
	private List<Voto> votos = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public OffsetDateTime getHorararioAbertura() {
		return horararioAbertura;
	}

	public void setHorararioAbertura(OffsetDateTime horararioAbertura) {
		this.horararioAbertura = horararioAbertura;
	}
	
	public Integer getEspera() {
		return espera;
	}

	public void setEspera(Integer espera) {
		this.espera = espera;
	}

	public List<Voto> getVotos() {
		return votos;
	}

	public void setVotos(List<Voto> votos) {
		this.votos = votos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pauta other = (Pauta) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public void iniciar() {
		setHorararioAbertura(OffsetDateTime.now());
	}
	
	public Boolean isAberto() {
		if(this.espera != null) {
			return this.horararioAbertura != null 
					&& ChronoUnit.MINUTES.between(this.horararioAbertura, OffsetDateTime.now()) < this.espera ?
							true : false;
		} else {
			return false;
		}
	}
}
