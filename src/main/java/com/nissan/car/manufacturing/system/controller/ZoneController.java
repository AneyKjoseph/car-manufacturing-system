package com.nissan.car.manufacturing.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.service.ZoneService;

/**
 * 
 * @author Aney K Joseph
 *
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ZoneController {

	@Autowired
	ZoneService zoneService;

	@PostMapping(value = "/zone")
	public ResponseEntity<CommonResponse> createZone(@RequestBody ZoneCreateRequest createRequest) {
		return new ResponseEntity(zoneService.createZone(createRequest), HttpStatus.CREATED);
	}

	@PutMapping(value = "/zone/activate/{id}")
	public ResponseEntity<CommonResponse> activateZone(@PathVariable String id) {
		return ResponseEntity.ok(zoneService.activateZone(id));
	}

	@PutMapping(value = "/zone/deactivate/{id}")
	public ResponseEntity<CommonResponse> deactivateZone(@PathVariable String id) {
		return ResponseEntity.ok(zoneService.deactivateZone(id));
	}

	@PutMapping(value = "/zone/{id}")
	public ResponseEntity<CommonResponse> updatePlant(@RequestBody ZoneCreateRequest updateRequest,
			@PathVariable String id) {
		return ResponseEntity.ok(zoneService.updateZone(updateRequest, id));
	}

}
