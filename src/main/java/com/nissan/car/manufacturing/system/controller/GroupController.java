package com.nissan.car.manufacturing.system.controller;

import java.util.List;
import java.util.Optional;

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

import com.nissan.car.manufacturing.system.entity.Group;
import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.request.GroupCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.service.GroupService;

@RestController
@RequestMapping(value = "/group")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

	@Autowired
	private GroupService groupService;

	@PostMapping("/createGroup")
	public ResponseEntity<CommonResponse> createGroup(@RequestBody GroupCreateRequest groupCreateRequest) {
		CommonResponse response = groupService.createGroup(groupCreateRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/editGroup/{id}")
	public ResponseEntity<CommonResponse> editGroup(@RequestBody GroupCreateRequest groupUpdateRequest,
			@PathVariable String id) {
		CommonResponse response = groupService.editGroup(groupUpdateRequest, id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/deactivateGroup/{id}")
	public ResponseEntity<CommonResponse> deactivateGroup(@PathVariable Long id) {
		Optional<Group> newGroup = groupService.findbyGroupCode(id);
		CommonResponse response = groupService.deactivateGroup(newGroup.get());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/activateGroup/{id}")
	public ResponseEntity<CommonResponse> activateGroup(@PathVariable Long id) {
		Optional<Group> newGroup = groupService.findbyGroupCode(id);
		CommonResponse response = groupService.activateGroup(newGroup.get());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Plant>> getAllDetails() {
		List<Plant> plants = groupService.getAllDetails();
		return new ResponseEntity<>(plants, HttpStatus.OK);
	}

}
