package com.nissan.car.manufacturing.system.dao;

import java.util.Date;

public class Zone {

	private Long zoneCode;

	private String zoneName;

	private Boolean activeFlag;

	private Date createdDate;

	private Date lastUpdatedDate;

	private Group group;

	private Plant plant;

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

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	@Override
	public String toString() {
		return "Zone [zoneCode=" + zoneCode + ", zoneName=" + zoneName + ", activeFlag=" + activeFlag + ", createdDate="
				+ createdDate + ", lastUpdatedDate=" + lastUpdatedDate + ", group=" + group + ", plant=" + plant + "]";
	}

}
