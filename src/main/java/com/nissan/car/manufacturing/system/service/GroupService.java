package com.nissan.car.manufacturing.system.service;

import java.util.List;
import java.util.Optional;

import com.nissan.car.manufacturing.system.model.Group;
import com.nissan.car.manufacturing.system.model.Plant;
import com.nissan.car.manufacturing.system.request.GroupCreateRequest;
import com.nissan.car.manufacturing.system.response.Response;

public interface GroupService {

	public Response createGroup(GroupCreateRequest groupCreateRequest);

	public Response editGroup(GroupCreateRequest groupUpdateRequest, String id);

	public Response deactivateGroup(Group group);

	public Response activateGroup(Group group);

	public Optional<Group> findbyGroupCode(Long id);

	public List<Plant> getAllDetails();
}
