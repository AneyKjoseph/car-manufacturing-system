package com.nissan.car.manufacturing.system.service;

import java.util.List;
import java.util.Optional;

import com.nissan.car.manufacturing.system.entity.Group;
import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.request.GroupCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;

public interface GroupService {

	public CommonResponse createGroup(GroupCreateRequest groupCreateRequest);

	public CommonResponse editGroup(GroupCreateRequest groupUpdateRequest, String id);

	public CommonResponse deactivateGroup(Long id);

	public CommonResponse activateGroup(Long id);

	public Optional<Group> findbyGroupCode(Long id);

	public List<Plant> getAllDetails();
}
