package com.nissan.car.manufacturing.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.request.GroupCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.service.GroupService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/group")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@PostMapping("/createGroup")
	@ApiOperation("For creating a group")
	public ResponseEntity<CommonResponse> createGroup(@RequestBody GroupCreateRequest groupCreateRequest) {
		return new ResponseEntity<>(groupService.createGroup(groupCreateRequest), HttpStatus.OK);
	}

	@PutMapping("/editGroup/{id}")
	@ApiOperation("For editing an existing group")
	public ResponseEntity<CommonResponse> editGroup(@RequestBody GroupCreateRequest groupUpdateRequest,
			@PathVariable String id) {
		return new ResponseEntity<>(groupService.editGroup(groupUpdateRequest, id), HttpStatus.OK);
	}

	@PutMapping("/deactivateGroup/{id}")
	@ApiOperation("For deactivating a group")
	public ResponseEntity<CommonResponse> deactivateGroup(@PathVariable Long id) {
		return new ResponseEntity<>(groupService.deactivateGroup(id), HttpStatus.OK);
	}

	@PutMapping("/activateGroup/{id}")
	@ApiOperation("For activating a group")
	public ResponseEntity<CommonResponse> activateGroup(@PathVariable Long id) {
		return new ResponseEntity<>(groupService.activateGroup(id), HttpStatus.OK);
	}

	@GetMapping("/getAll")
	@ApiOperation("For getting all details")
	public ResponseEntity<List<Plant>> getAllDetails() {
		return new ResponseEntity<>(groupService.getAllDetails(), HttpStatus.OK);
	}

}
