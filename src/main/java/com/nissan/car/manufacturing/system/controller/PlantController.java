package com.nissan.car.manufacturing.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.car.manufacturing.system.model.Plant;
import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.response.Response;
import com.nissan.car.manufacturing.system.service.PlantService;

/**
 * @author S Sarathkrishna
 *
 */
@RestController
public class PlantController {
	
	@Autowired
	PlantService plantService;
	
	@PostMapping(value = "/plants")
    public ResponseEntity<Response> createPlant(@RequestBody PlantCreateRequest createRequest) {
        return new ResponseEntity(plantService.createPlant(createRequest), HttpStatus.CREATED);
    }
	
	@PutMapping(value = "/plants/activate/{id}")
    public ResponseEntity<Response> activatePlant(@PathVariable String id) {
        return ResponseEntity.ok(plantService.activatePlant(id));
    }
	
	@PutMapping(value = "/plants/deactivate/{id}")
    public ResponseEntity<Response> deactivatePlant(@PathVariable String id) {
        return ResponseEntity.ok(plantService.deactivatePlant(id));
    }
	
	@PutMapping(value = "/plants/{id}")
    public ResponseEntity<Response> updatePlant(@RequestBody PlantCreateRequest updateRequest, @PathVariable String id) {
        return ResponseEntity.ok(plantService.updatePlant(updateRequest, id));
    }
	
	@GetMapping(value = "/plants/{id}")
    public ResponseEntity<Plant> getPlantDetails(@PathVariable String id) {
        return ResponseEntity.ok(plantService.getPlantDetails(id));
    }

}
