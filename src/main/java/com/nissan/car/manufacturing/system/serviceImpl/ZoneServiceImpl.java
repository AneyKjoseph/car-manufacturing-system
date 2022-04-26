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
import com.nissan.car.manufacturing.system.model.Group;
import com.nissan.car.manufacturing.system.model.Plant;
import com.nissan.car.manufacturing.system.model.Zone;
import com.nissan.car.manufacturing.system.repository.ZoneRepository;
import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.response.Response;
import com.nissan.car.manufacturing.system.service.ZoneService;

/**
 * 
 * @author Aney K Joseph
 *
 */

@Service
public class ZoneServiceImpl implements ZoneService {

	private static final String ZONE_CREATE_SUCCESS = "New Zone created successfully";
	private static final String ZONE_ACTIVE_STATUS = "Zone already in active status";
	private static final String ZONE_ACTIVATED_SUCCESS = "Zone activated successfully";
	private static final String ZONE_DEACTIVATED_SUCCESS = "Zone deactivated successfully";
	private static final String ZONE_DEACTIVE_STATUS = "Zone already in deactived status";
	private static final String ZONE_NOT_FOUND = "Requested Zone not found";
	private static final String ZONE_UPDATE_SUCCESS = "Requested Zone updated successfully";

	@Autowired
	ZoneRepository zoneRepository;

	@Override
	public Response createZone(ZoneCreateRequest createRequest) {
		Zone zoneEntity = new Zone();
		Response response = new Response();
		mapRequestToEntity(zoneEntity, createRequest);
		try {
			Date currentTime = new Date();
			zoneEntity.setCreatedDate(currentTime);
			zoneEntity.setLastUpdatedDate(currentTime);

			Zone created = zoneRepository.save(zoneEntity);
			if (Objects.nonNull(created.getZoneCode())) {
				response.setMessage(ZONE_CREATE_SUCCESS);
			}

		} catch (ConstraintViolationException | DataIntegrityViolationException exception) {
			throw new ResourceNotCreatedException(exception.getMessage());
		}
		return response;
	}

	private void mapRequestToEntity(Zone zoneEntity, ZoneCreateRequest createRequest) {
		if (Objects.nonNull(createRequest.getZoneName())) {
			zoneEntity.setZoneName(createRequest.getZoneName());
		}
		if (Objects.nonNull(createRequest.getGroupCode())) {
			Group groupEntity = new Group();
			groupEntity.setGroupCode(createRequest.getGroupCode());
			zoneEntity.setGroup(groupEntity);

		}
		if (Objects.nonNull(createRequest.getPlantCode())) {
			Plant plantEntity = new Plant();
			plantEntity.setPlantCode(createRequest.getPlantCode());
			zoneEntity.setPlant(plantEntity);
		}
		if (Objects.nonNull(createRequest.isActiveFlag())) {
			zoneEntity.setActiveFlag(createRequest.isActiveFlag());

		}

	}

	@Override
	public Response activateZone(String id) {
		Response response = new Response();

		Optional<Zone> zoneOptional = zoneRepository.findById(Long.parseLong(id));
		if (zoneOptional.isPresent()) {
			Zone zoneEntity = zoneOptional.get();
			if (zoneEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(ZONE_ACTIVE_STATUS);
			} else {
				zoneEntity.setActiveFlag(true);
				zoneEntity.setLastUpdatedDate(new Date());
				zoneRepository.save(zoneEntity);
				response.setMessage(ZONE_ACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(ZONE_NOT_FOUND);
		}

		return response;
	}

	@Override
	public Response deactivateZone(String id) {
		Response response = new Response();

		Optional<Zone> zoneOptional = zoneRepository.findById(Long.parseLong(id));
		if (zoneOptional.isPresent()) {
			Zone zoneEntity = zoneOptional.get();
			if (Objects.nonNull(zoneEntity.getActiveFlag()) && !zoneEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(ZONE_DEACTIVE_STATUS);
			} else {
				zoneEntity.setActiveFlag(false);
				zoneEntity.setLastUpdatedDate(new Date());
				zoneRepository.save(zoneEntity);
				response.setMessage(ZONE_DEACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(ZONE_NOT_FOUND);
		}

		return response;
	}

	@Override
	public Response updateZone(ZoneCreateRequest updateRequest, String id) {
		Response response = new Response();

		Optional<Zone> zoneOptional = zoneRepository.findById(Long.parseLong(id));
		if (zoneOptional.isPresent()) {
			Zone zoneEntity = zoneOptional.get();
			if (Objects.nonNull(updateRequest.getZoneName())) {
				zoneEntity.setZoneName(updateRequest.getZoneName());
				zoneEntity.setLastUpdatedDate(new Date());
				zoneRepository.save(zoneEntity);
				response.setMessage(ZONE_UPDATE_SUCCESS);
			} else {
				throw new ResourceNotFoundException("Can't update");
			}

		} else {
			throw new ResourceNotFoundException(ZONE_NOT_FOUND);
		}

		return response;
	}

}
