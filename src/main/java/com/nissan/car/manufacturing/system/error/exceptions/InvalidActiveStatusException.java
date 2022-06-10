package com.nissan.car.manufacturing.system.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * 
 * @author Aney K Joseph
 *
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidActiveStatusException extends RuntimeException {
	
	public InvalidActiveStatusException(String exception) {
        super(exception);
    }

}
