package com.kk.api.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItinerarioModel {

	@JsonProperty("idlinha")
	private String idLinha;

	private String nome;

	private String codigo;

	private Map<Integer, Localizacao> localizacoes = new HashMap<>();

	public class Localizacao {

		private String lat;
		private String lng;

		public Localizacao(String lat, String lng) {
			super();
			this.lat = lat;
			this.lng = lng;
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
	}

	public String getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(String idLinha) {
		this.idLinha = idLinha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@JsonAnyGetter
	public Map<Integer, Localizacao> getLocalizacoes() {
		return localizacoes;
	}

	public void setLocalizacoes(Map<Integer, Localizacao> localizacoes) {
		this.localizacoes = localizacoes;
	}

	@SuppressWarnings("unlikely-arg-type")
	@JsonAnySetter
	public void adicionarLocalizacao(String key, Object values) {
		if (this.localizacoes == null) {
            this.localizacoes = new HashMap<>();
        }
		if(values instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<Integer, String> map = (Map<Integer, String>) values;
			
			Integer keyInt = Integer.parseInt(key);			
			localizacoes.put(keyInt, new Localizacao(map.get("lat"), map.get("lng")));
		}
	}
}
