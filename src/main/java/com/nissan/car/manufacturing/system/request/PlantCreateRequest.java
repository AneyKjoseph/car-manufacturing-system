package com.nissan.car.manufacturing.system.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aney K Joseph
 *
 */

@Component
public class PlantCreateRequest {

	@JsonProperty("plant_name")
	private String plantName;

	@JsonProperty("place")
	private String place;

	@JsonProperty("country")
	private String country;

	@JsonProperty("language")
	private String language;

	@JsonProperty("active_flag")
	private Boolean activeFlag;

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public PlantCreateRequest(String plantName, String place, String country, String language) {
		super();
		this.plantName = plantName;
		this.place = place;
		this.country = country;
		this.language = language;
	}

	public PlantCreateRequest() {
		super();
	}

}
