package com.nissan.car.manufacturing.system.response;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonInclude(JsonInclude.Include. NON_NULL)
public class GroupResponse {
	
	@JsonProperty("group_code")
	private Long groupCode;
	
	@JsonProperty("group_name")
	private String groupName;
	
	@JsonProperty("active_flag")
	private Boolean activeFlag;
	
	@JsonProperty("created_date")
	private Date createdDate;

	@JsonProperty("last_updated_date")
	private Date lastUpdatedDate;
	
	@JsonProperty("zones")
	private List<ZoneResponse> zones;

	public GroupResponse() {
		super();
	}

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

	public List<ZoneResponse> getZones() {
		return zones;
	}

	public void setZones(List<ZoneResponse> zones) {
		this.zones = zones;
	}
	

}
