package com.nissan.car.manufacturing.system.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

/**
 * @author S Sarathkrishna
 *
 */
@Entity
@Table(name = "zones")
public class Zone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long zoneCode;

	@NotNull
	@Column(name = "zone_name", unique = true)
	private String zoneName;

	@Column(name = "active_flag")
	private Boolean activeFlag;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "last_updated_date")
	private Date lastUpdatedDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "groups_code")
	private Group group;

	@ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "plant_code")
	private Plant plant;

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public Long getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(Long zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
