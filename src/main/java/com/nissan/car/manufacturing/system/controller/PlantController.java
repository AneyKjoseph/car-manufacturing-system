package com.nissan.car.manufacturing.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.response.PlantDetailsResponse;
import com.nissan.car.manufacturing.system.service.PlantService;

import io.swagger.annotations.ApiOperation;

/**
 * @author S Sarathkrishna
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PlantController {

	@Autowired
	PlantService plantService;

	@PostMapping(value = "/plant")
	public ResponseEntity<CommonResponse> createPlant(@RequestBody PlantCreateRequest createRequest) {
		return new ResponseEntity(plantService.createPlant(createRequest), HttpStatus.CREATED);
	}

	@PutMapping(value = "/plant/activate/{id}")
	public ResponseEntity<CommonResponse> activatePlant(@PathVariable String id) {
		return ResponseEntity.ok(plantService.activatePlant(id));
	}

	@PutMapping(value = "/plant/deactivate/{id}")
	public ResponseEntity<CommonResponse> deactivatePlant(@PathVariable String id) {
		return ResponseEntity.ok(plantService.deactivatePlant(id));
	}

	@PutMapping(value = "/plant/{id}")
	public ResponseEntity<CommonResponse> updatePlant(@RequestBody PlantCreateRequest updateRequest,
			@PathVariable String id) {
		return ResponseEntity.ok(plantService.updatePlant(updateRequest, id));
	}

	@GetMapping(value = "/plant/{id}")
	public ResponseEntity<PlantDetailsResponse> getPlantDetails(@PathVariable String id) {
		return ResponseEntity.ok(plantService.getPlantDetails(id));
	}

	@GetMapping(value = "/get/all")
	public ResponseEntity<Map<Long, Map<Long, List<Long>>>> getAll() {
		return ResponseEntity.ok(plantService.getAll());
	}

	@GetMapping("/getAll")
	@ApiOperation("For getting all details")
	public ResponseEntity<List<Plant>> getAllDetails() {
		return new ResponseEntity<>(plantService.getAllDetails(), HttpStatus.OK);
	}
}
