package com.nissan.car.manufacturing.system.request;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aney K Joseph
 *
 */

@Component
public class ZoneCreateRequest {
	
	@JsonProperty("zone_name")
	private String zoneName;
	
	@JsonProperty("plant_code")
	private Long plantCode;
	
	@JsonProperty("group_code")
	private Long groupCode;
	
	@JsonProperty("active_flag")
	private boolean activeFlag;


	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Long getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(Long plantCode) {
		this.plantCode = plantCode;
	}

	public Long getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(Long groupCode) {
		this.groupCode = groupCode;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public ZoneCreateRequest(String zoneName) {
		super();
		this.zoneName = zoneName;
	}

	public ZoneCreateRequest() {
		super();
	}
	
}
