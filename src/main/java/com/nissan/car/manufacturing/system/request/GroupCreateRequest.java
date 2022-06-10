package com.nissan.car.manufacturing.system.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class GroupCreateRequest {

	@JsonProperty("group_name")
	private String groupName;

	@JsonProperty("plant_code")
	private Long plantCode;

	@JsonProperty("active_flag")
	private Boolean activeFlag;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(Long plantCode) {
		this.plantCode = plantCode;
	}

	public Boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

}
