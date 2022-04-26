package com.nissan.car.manufacturing.system.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

/**
 * @author S Sarathkrishna
 *
 */
@Entity
@Table(name = "groups")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long groupCode;

	@NotNull
	@Column(name = "group_name", unique = true)
	private String groupName;

	@Column(name = "active_flag")
	private Boolean activeFlag;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;

	@JsonManagedReference
	@OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
	private List<Zone> zones;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.MERGE)
	@JoinColumn(name = "plant_code")
	private Plant plant;

	public Long getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Long groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public List<Zone> getZones() {
		return zones;
	}

	public void setZones(List<Zone> zones) {
		this.zones = zones;

		for (Zone z : zones) {
			z.setGroup(this);
		}
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

}
