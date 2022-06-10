package com.nissan.car.manufacturing.system.dao;

import java.util.Date;
import java.util.List;

public class Group {

	private Long groupCode;

	private String groupName;

	private Boolean activeFlag;

	private Date createdDate;

	private Date lastUpdatedDate;

	private List<Zone> zones;

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
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	@Override
	public String toString() {
		return "Group [groupCode=" + groupCode + ", groupName=" + groupName + ", activeFlag=" + activeFlag
				+ ", createdDate=" + createdDate + ", lastUpdatedDate=" + lastUpdatedDate + ", zones=" + zones
				+ ", plant=" + plant + "]";
	}

}
