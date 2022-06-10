package com.nissan.car.manufacturing.system.response;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@JsonInclude(JsonInclude.Include. NON_NULL)
public class ZoneResponse {
	
	@JsonProperty("zone_code")
	private Long zoneCode;
	
	@JsonProperty("zone_name")
	private String zoneName;
	
	@JsonProperty("active_flag")
	private Boolean activeFlag;
	
	@JsonProperty("created_date")
	private Date createdDate;

	@JsonProperty("last_updated_date")
	private Date lastUpdatedDate;


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

	public Long getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(Long zoneCode) {
		this.zoneCode = zoneCode;
	}

	public ZoneResponse() {
		super();
	}
	

}
