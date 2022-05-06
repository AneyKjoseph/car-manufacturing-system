package com.nissan.car.manufacturing.system.serviceImpl;

import java.util.*;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nissan.car.manufacturing.system.entity.Group;
import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.entity.Zone;
import com.nissan.car.manufacturing.system.error.exceptions.InvalidActiveStatusException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotCreatedException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotFoundException;
import com.nissan.car.manufacturing.system.repository.PlantRepository;
import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.response.GroupResponse;
import com.nissan.car.manufacturing.system.response.PlantDetailsResponse;
import com.nissan.car.manufacturing.system.response.ZoneResponse;
import com.nissan.car.manufacturing.system.service.PlantService;
import com.nissan.car.manufacturing.system.utils.CarSystemConstants;

/**
 * @author S Sarathkrishna
 *
 */
@Service
public class PlantServiceImpl implements PlantService {

	@Autowired
	PlantRepository plantRepository;

	@Override
	public CommonResponse createPlant(PlantCreateRequest createRequest) {

		Plant plantEntity = new Plant();
		CommonResponse response = new CommonResponse();
		mapRequestToPlantEntity(createRequest, plantEntity);
		try {
			Date currentTime = new Date();
			plantEntity.setCreatedDate(currentTime);
			plantEntity.setLastUpdatedDate(currentTime);
			Plant created = plantRepository.save(plantEntity);
			if (Objects.nonNull(created.getPlantCode())) {
				response.setMessage(CarSystemConstants.PLANT_CREATE_SUCCESS);
			}

		} catch (ConstraintViolationException | DataIntegrityViolationException exception) {
			throw new ResourceNotCreatedException(exception.getMessage());
		}
		return response;
	}

	private void mapRequestToPlantEntity(PlantCreateRequest request, Plant plantEntity) {
		/* plantEntity.setActiveFlag(true); */
		if (Objects.nonNull(request.getPlantName())) {
			plantEntity.setPlantName(request.getPlantName());

		}
		if (Objects.nonNull(request.getPlace())) {
			plantEntity.setPlace(request.getPlace());

		}
		if (Objects.nonNull(request.getCountry())) {
			plantEntity.setCountry(request.getCountry());

		}
		if (Objects.nonNull(request.getLanguage())) {
			plantEntity.setLanguage(request.getLanguage());

		}
		if (Objects.nonNull(request.getActiveFlag())) {
			plantEntity.setActiveFlag(request.getActiveFlag());

		}

	}

	@Override
	public CommonResponse activatePlant(String id) {
		CommonResponse response = new CommonResponse();

		Optional<Plant> plantOptional = plantRepository.findById(Long.parseLong(id));
		if (plantOptional.isPresent()) {
			Plant plantEntity = plantOptional.get();
			if (plantEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(CarSystemConstants.PLANT_ACTIVE_STATUS);
			} else {
				plantEntity.setActiveFlag(true);
				plantEntity.setLastUpdatedDate(new Date());
				plantRepository.save(plantEntity);
				response.setMessage(CarSystemConstants.PLANT_ACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(CarSystemConstants.PLANT_NOT_FOUND);
		}

		return response;
	}

	@Override
	public CommonResponse deactivatePlant(String id) {
		CommonResponse response = new CommonResponse();

		Optional<Plant> plantOptional = plantRepository.findById(Long.parseLong(id));
		if (plantOptional.isPresent()) {
			Plant plantEntity = plantOptional.get();
			if (!plantEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(CarSystemConstants.PLANT_DEACTIVE_STATUS);
			} else {
				plantEntity.setActiveFlag(false);
				plantEntity.setLastUpdatedDate(new Date());
				plantRepository.save(plantEntity);
				response.setMessage(CarSystemConstants.PLANT_DEACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(CarSystemConstants.PLANT_NOT_FOUND);
		}

		return response;
	}

	@Override
	public CommonResponse updatePlant(PlantCreateRequest updateRequest, String id) {
		CommonResponse response = new CommonResponse();

		Optional<Plant> plantOptional = plantRepository.findById(Long.parseLong(id));
		if (plantOptional.isPresent()) {
			Plant plantEntity = plantOptional.get();
			mapRequestToPlantEntity(updateRequest, plantEntity);

			plantEntity.setLastUpdatedDate(new Date());
			plantRepository.save(plantEntity);
			response.setMessage(CarSystemConstants.PLANT_UPDATE_SUCCESS);

		} else {
			throw new ResourceNotFoundException(CarSystemConstants.PLANT_NOT_FOUND);
		}

		return response;
	}

	@Override
	public PlantDetailsResponse getPlantDetails(String id) {
		PlantDetailsResponse response = new PlantDetailsResponse();
		Optional<Plant> plantEntity = plantRepository.findById(Long.parseLong(id));
		if (plantEntity.isPresent()) {
			mapEntityToResponse(response,plantEntity.get());
			return response;
		} else {
			throw new ResourceNotFoundException(CarSystemConstants.PLANT_NOT_FOUND);
		}
	}

	private void mapEntityToResponse(PlantDetailsResponse response, Plant plant) {
		response.setPlantCode(plant.getPlantCode());
		response.setPlantName(plant.getPlantName());
		response.setPlace(plant.getPlace());
		response.setCountry(plant.getCountry());
		response.setLanguage(plant.getLanguage());
		response.setActiveFlag(plant.getActiveFlag());
		response.setCreatedDate(plant.getCreatedDate());
		response.setLastUpdatedDate(plant.getLastUpdatedDate());
		response.setGroups(plant.getGroups().stream()
				.map(groupEntity -> mapGroupEntityResponse(groupEntity))
				.collect(Collectors.toList()));
		
	}

	private GroupResponse mapGroupEntityResponse(Group group) {
		GroupResponse groupResponse = new GroupResponse();
		groupResponse.setGroupName(group.getGroupName());
		groupResponse.setGroupCode(group.getGroupCode());
		groupResponse.setActiveFlag(group.getActiveFlag());
		groupResponse.setCreatedDate(group.getCreatedDate());
		groupResponse.setLastUpdatedDate(group.getLastUpdatedDate());
		groupResponse.setZones(group.getZones().stream()
				.map(zone -> mapZoneEntityResponse(zone))
				.collect(Collectors.toList()));
		return groupResponse;
	}
	
	

	private ZoneResponse mapZoneEntityResponse(Zone zone) {
		ZoneResponse zoneResponse = new ZoneResponse();
		zoneResponse.setZoneCode(zone.getZoneCode());
		zoneResponse.setZoneName(zone.getZoneName());
		zoneResponse.setActiveFlag(zone.getActiveFlag());
		zoneResponse.setCreatedDate(zone.getCreatedDate());
		zoneResponse.setLastUpdatedDate(zone.getLastUpdatedDate());
		return zoneResponse;
	}

	@Override
	public Map<Long, Map<Long, List<Long>>> getAll() {
		Map<Long, Map<Long, List<Long>>> plants = new HashMap<>();
		plantRepository.findAll().stream()
				.forEach(plant -> {
					Map<Long, List<Long>> map = new HashMap<>();
					plant.getGroups().stream().forEach(group ->{
						map.put(group.getGroupCode(),group.getZones().stream().map(Zone::getZoneCode).collect(Collectors.toList()));
						plants.put(plant.getPlantCode(),map);
					});
					plants.put(plant.getPlantCode(),map);
				});
		return  plants;
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

}
