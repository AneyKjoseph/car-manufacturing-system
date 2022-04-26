package com.nissan.car.manufacturing.system.error;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author Aney K Joseph
 *
 */
@Component
@JsonInclude(JsonInclude.Include. NON_NULL)
public class ErrorResponse {
	
	@JsonProperty("http_status_code")
    private Integer httpStatusCode;
    @JsonProperty("http_status_message")
    private HttpStatus httpStatusMessage;
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("error_details")
    private Map<String,String> errorDetails;
    
	public Integer getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(Integer httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public HttpStatus getHttpStatusMessage() {
		return httpStatusMessage;
	}
	public void setHttpStatusMessage(HttpStatus httpStatusMessage) {
		this.httpStatusMessage = httpStatusMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Map<String, String> getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(Map<String, String> errorDetails) {
		this.errorDetails = errorDetails;
	}
    
    
    
    

}
