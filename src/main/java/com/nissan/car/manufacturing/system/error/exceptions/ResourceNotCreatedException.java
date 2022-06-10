package com.nissan.car.manufacturing.system.error.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Aney K Joseph
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotCreatedException extends RuntimeException {
	
	public ResourceNotCreatedException(String exception) {
        super(exception);
    }
}
