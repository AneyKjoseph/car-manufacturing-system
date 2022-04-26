package com.nissan.car.manufacturing.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.response.Response;
import com.nissan.car.manufacturing.system.service.ZoneService;

/**
 * 
 * @author Aney K Joseph
 *
 */

@RestController
public class ZoneController {

	@Autowired
	ZoneService zoneService;

	@PostMapping(value = "/zones")
	public ResponseEntity<Response> createZone(@RequestBody ZoneCreateRequest createRequest) {
		return new ResponseEntity(zoneService.createZone(createRequest), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/zones/activate/{id}")
    public ResponseEntity<Response> activateZone(@PathVariable String id) {
        return ResponseEntity.ok(zoneService.activateZone(id));
    }
	
	@PutMapping(value = "/zones/deactivate/{id}")
    public ResponseEntity<Response> deactivateZone(@PathVariable String id) {
        return ResponseEntity.ok(zoneService.deactivateZone(id));
    }
	
	@PutMapping(value = "/zones/{id}")
    public ResponseEntity<Response> updatePlant(@RequestBody ZoneCreateRequest updateRequest, @PathVariable String id) {
        return ResponseEntity.ok(zoneService.updateZone(updateRequest, id));
    }

}
