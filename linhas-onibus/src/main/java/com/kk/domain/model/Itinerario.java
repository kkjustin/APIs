package com.kk.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Itinerario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Linha linha;
	
	@NotNull
	private String lat;
	
	@NotNull
	private String lng;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Boolean dentroDaRange(Double minLat, Double maxLat, Double minLng, Double maxLng) {
		Double lat = Double.parseDouble(this.lat);
		Double lng = Double.parseDouble(this.lng);
		if(lat >= minLat && lat <= maxLat &&
				lng >= minLng && lng <= maxLng) {
			return true;
		}
		return false;
	}
}
