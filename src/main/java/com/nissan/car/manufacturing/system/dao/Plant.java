package com.nissan.car.manufacturing.system.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Plant {

	private Long plantCode;

	private String plantName;

	private Boolean activeFlag;

	private String place;

	private String country;

	private String language;

	private Date createdDate;

	private Date lastUpdatedDate;

	private List<Group> groups = new ArrayList<Group>();

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

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
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

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "Plant [plantCode=" + plantCode + ", plantName=" + plantName + ", activeFlag=" + activeFlag + ", place="
				+ place + ", country=" + country + ", language=" + language + ", createdDate=" + createdDate
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", groups=" + groups + "]";
	}

}
