package com.nissan.car.manufacturing.system.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * @author S Sarathkrishna
 *
 */
@Entity
@Table(name = "plants")
public class Plant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long plantCode;

	@Column(name = "plant_name", unique = true, nullable = false)
	private String plantName;

	@Column(name = "active_flag")
	private Boolean activeFlag;

	@Column(name = "place")
	private String place;

	@Column(name = "country")
	private String country;

	@Column(name = "language")
	private String language;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;

	@JsonManagedReference(value = "plant")
	@OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
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

		for (Group g : groups) {
			g.setPlant(this);
		}
	}

}
