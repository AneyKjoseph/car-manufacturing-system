package com.nissan.car.manufacturing.system.response;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nissan.car.manufacturing.system.entity.Group;

/**
 * 
 * @author Aney K Joseph
 *
 */

@Component
@JsonInclude(JsonInclude.Include. NON_NULL)
public class PlantDetailsResponse {
	@JsonProperty("plant_code")
	private Long plantCode;
	
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
	
	@JsonProperty("created_date")
	private Date createdDate;

	@JsonProperty("last_updated_date")
	private Date lastUpdatedDate;
	
	@JsonProperty("groups")
	private List<GroupResponse> groups;

	public PlantDetailsResponse() {
		super();
	}


	public Long getPlantCode() {
		return plantCode;
	}


	public void setPlantCode(Long plantCode) {
		this.plantCode = plantCode;
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

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public List<GroupResponse> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupResponse> groups) {
		this.groups = groups;
	}
	
}
