/**
 * 
 */
package com.nissan.car.manufacturing.system.serviceImpl;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.nissan.car.manufacturing.system.error.exceptions.InvalidActiveStatusException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotCreatedException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotFoundException;
import com.nissan.car.manufacturing.system.model.Plant;
import com.nissan.car.manufacturing.system.repository.PlantRepository;
import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.response.Response;
import com.nissan.car.manufacturing.system.service.PlantService;

/**
 * @author S Sarathkrishna
 *
 */
@Service
public class PlantServiceImpl implements PlantService {
	private static final String PLANT_CREATE_SUCCESS = "New plant created successfully";
	private static final String PLANT_ACTIVE_STATUS = "Plant already in active status";
	private static final String PLANT_ACTIVATED_SUCCESS = "Plant activated successfully";
	private static final String PLANT_DEACTIVATED_SUCCESS = "Plant deactivated successfully";
	private static final String PLANT_DEACTIVE_STATUS = "Plant already in deactived status";
	private static final String PLANT_NOT_FOUND = "Requested Plant not found";
	private static final String PLANT_UPDATE_SUCCESS = "Requested Plant updated successfully";

	@Autowired
	PlantRepository plantRepository;

	@Override
	public Response createPlant(PlantCreateRequest createRequest) {

		Plant plantEntity = new Plant();
		Response response = new Response();
		mapRequestToPlantEntity(createRequest, plantEntity);
		try {
			Date currentTime = new Date();
			plantEntity.setCreatedDate(currentTime);
			plantEntity.setLastUpdatedDate(currentTime);
			Plant created = plantRepository.save(plantEntity);
			if (Objects.nonNull(created.getPlantCode())) {
				response.setMessage(PLANT_CREATE_SUCCESS);
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
		if (Objects.nonNull(request.isActiveFlag())) {
			plantEntity.setActiveFlag(request.isActiveFlag());

		}

	}

	@Override
	public Response activatePlant(String id) {
		Response response = new Response();

		Optional<Plant> plantOptional = plantRepository.findById(Long.parseLong(id));
		if (plantOptional.isPresent()) {
			Plant plantEntity = plantOptional.get();
			if (plantEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(PLANT_ACTIVE_STATUS);
			} else {
				plantEntity.setActiveFlag(true);
				plantEntity.setLastUpdatedDate(new Date());
				plantRepository.save(plantEntity);
				response.setMessage(PLANT_ACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(PLANT_NOT_FOUND);
		}

		return response;
	}

	@Override
	public Response deactivatePlant(String id) {
		Response response = new Response();

		Optional<Plant> plantOptional = plantRepository.findById(Long.parseLong(id));
		if (plantOptional.isPresent()) {
			Plant plantEntity = plantOptional.get();
			if (Objects.nonNull(plantEntity.getActiveFlag()) && !plantEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(PLANT_DEACTIVE_STATUS);
			} else {
				plantEntity.setActiveFlag(false);
				plantEntity.setLastUpdatedDate(new Date());
				plantRepository.save(plantEntity);
				response.setMessage(PLANT_DEACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(PLANT_NOT_FOUND);
		}

		return response;
	}

	@Override
	public Response updatePlant(PlantCreateRequest updateRequest, String id) {
		Response response = new Response();

		Optional<Plant> plantOptional = plantRepository.findById(Long.parseLong(id));
		if (plantOptional.isPresent()) {
			Plant plantEntity = plantOptional.get();
			mapRequestToPlantEntity(updateRequest, plantEntity);

			plantEntity.setLastUpdatedDate(new Date());
			plantRepository.save(plantEntity);
			response.setMessage(PLANT_UPDATE_SUCCESS);

		} else {
			throw new ResourceNotFoundException(PLANT_NOT_FOUND);
		}

		return response;
	}

	@Override
	public Plant getPlantDetails(String id) {
		Optional<Plant> plantEntity = plantRepository.findById(Long.parseLong(id));
		if (plantEntity.isPresent()) {
			return plantEntity.get();
		} else {
			throw new ResourceNotFoundException(PLANT_NOT_FOUND);
		}
	}

}
