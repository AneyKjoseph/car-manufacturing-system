package com.nissan.car.manufacturing.system.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nissan.car.manufacturing.system.entity.Group;
import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.error.exceptions.InvalidActiveStatusException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotCreatedException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotFoundException;
import com.nissan.car.manufacturing.system.repository.GroupRepository;
import com.nissan.car.manufacturing.system.repository.PlantRepository;
import com.nissan.car.manufacturing.system.request.GroupCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.service.GroupService;
import com.nissan.car.manufacturing.system.utils.CarSystemConstants;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private PlantRepository plantRepository;

	@Override
	public CommonResponse editGroup(GroupCreateRequest groupUpdateRequest, String id) {
		CommonResponse response = new CommonResponse();
		Optional<Group> newGroup = findbyGroupCode(Long.parseLong(id));
		Group group = newGroup.get();
		try {
			if ((!groupUpdateRequest.getGroupName().isEmpty()) && (!groupUpdateRequest.getGroupName().equals(" "))) {
				group.setGroupName(groupUpdateRequest.getGroupName());
				group.setLastUpdatedDate(new Date());
				groupRepository.save(newGroup.get());
				response.setMessage(CarSystemConstants.GROUP_EDIT);
			} else {
				throw new ResourceNotFoundException(CarSystemConstants.UPDATE_ERROR);
			}
		} catch (ConstraintViolationException | DataIntegrityViolationException exception) {
			throw new ResourceNotCreatedException(exception.getMessage());
		}
		return response;
	}

	@Override

	public CommonResponse deactivateGroup(Long id) {
		Optional<Group> newGroup = findbyGroupCode(id);
		Group group = newGroup.get();
		CommonResponse response = new CommonResponse();
		if (Objects.nonNull(group.getActiveFlag()) && (group.getActiveFlag())) {
			group.setActiveFlag(false);
			group.setLastUpdatedDate(new Date());
			groupRepository.save(group);
			response.setMessage(CarSystemConstants.GROUP_DEACTIVATE);
		} else {
			throw new InvalidActiveStatusException(CarSystemConstants.GROUP_DEACTIVE_STATUS);
		}
		return response;
	}

	@Override
	public CommonResponse activateGroup(Long id) {
		Optional<Group> newGroup = findbyGroupCode(id);
		Group group = newGroup.get();
		CommonResponse response = new CommonResponse();
		if (group.getActiveFlag()) {
			throw new InvalidActiveStatusException(CarSystemConstants.GROUP_ACTIVE_STATUS);
		} else {
			group.setActiveFlag(true);
			group.setLastUpdatedDate(new Date());
			groupRepository.save(group);
			response.setMessage(CarSystemConstants.GROUP_ACTIVATE);
		}
		return response;
	}

	@Override
	public Optional<Group> findbyGroupCode(Long id) {
		Optional<Group> newGroup = groupRepository.findById(id);
		if (newGroup.isPresent()) {
			return newGroup;
		} else {
			throw new ResourceNotFoundException(CarSystemConstants.GROUP_NOT_FOUND);
		}
	}

	@Override
	public List<Plant> getAllDetails() {
		List<Plant> plants = plantRepository.findAll();
		if (plants.isEmpty()) {
			throw new ResourceNotFoundException(CarSystemConstants.EMPTY_RECORDS);
		} else {
			return plants;
		}
	}

	@Override
	public CommonResponse createGroup(GroupCreateRequest groupCreateRequest) {
		Group group = new Group();
		CommonResponse response = new CommonResponse();
		try {
			addGroupDetails(groupCreateRequest, group);
			groupRepository.save(group);
			response.setMessage(CarSystemConstants.GROUP_CREATE);
		} catch (NullPointerException | ConstraintViolationException | DataIntegrityViolationException exception) {
			throw new ResourceNotCreatedException(exception.getMessage());
		}
		return response;
	}

	public void addGroupDetails(GroupCreateRequest groupCreateRequest, Group group) {

		group.setCreatedDate(new Date());
		group.setLastUpdatedDate(new Date());

		if ((!groupCreateRequest.getGroupName().isEmpty()) && (!groupCreateRequest.getGroupName().equals(" "))) {
			group.setGroupName(groupCreateRequest.getGroupName());
		}

		if (Objects.nonNull(groupCreateRequest.isActiveFlag())) {
			group.setActiveFlag(groupCreateRequest.isActiveFlag());
		}

		if (Objects.nonNull(groupCreateRequest.getPlantCode())) {
			Plant plant = new Plant();
			plant.setPlantCode(groupCreateRequest.getPlantCode());
			group.setPlant(plant);
		}

	}

}
