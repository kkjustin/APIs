package com.kk.api.model;

import javax.validation.constraints.NotNull;

public class ItinerarioResumeModel {

	@NotNull
	private String lat;
	
	@NotNull
	private String lng;
	
	public ItinerarioResumeModel(@NotNull String lat, @NotNull String lng) {
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
