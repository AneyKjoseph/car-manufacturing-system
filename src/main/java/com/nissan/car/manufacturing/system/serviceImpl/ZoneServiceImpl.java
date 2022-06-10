package com.nissan.car.manufacturing.system.serviceImpl;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
import com.nissan.car.manufacturing.system.repository.ZoneRepository;
import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.service.ZoneService;
import com.nissan.car.manufacturing.system.utils.CarSystemConstants;

/**
 * 
 * @author Aney K Joseph
 *
 */

@Service
public class ZoneServiceImpl implements ZoneService {

	@Autowired
	ZoneRepository zoneRepository;

	@Override
	public CommonResponse createZone(ZoneCreateRequest createRequest) {
		Zone zoneEntity = new Zone();
		CommonResponse response = new CommonResponse();
		mapRequestToEntity(zoneEntity, createRequest);
		try {
			Date currentTime = new Date();
			zoneEntity.setCreatedDate(currentTime);
			zoneEntity.setLastUpdatedDate(currentTime);

			Zone created = zoneRepository.save(zoneEntity);
			if (Objects.nonNull(created.getZoneCode())) {
				response.setMessage(CarSystemConstants.ZONE_CREATE_SUCCESS);
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
		if (Objects.nonNull(createRequest.getActiveFlag())) {
			zoneEntity.setActiveFlag(createRequest.getActiveFlag());

		}

	}

	@Override
	public CommonResponse activateZone(String id) {
		CommonResponse response = new CommonResponse();

		Optional<Zone> zoneOptional = zoneRepository.findById(Long.parseLong(id));
		if (zoneOptional.isPresent()) {
			Zone zoneEntity = zoneOptional.get();
			if (zoneEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(CarSystemConstants.ZONE_ACTIVE_STATUS);
			} else {
				zoneEntity.setActiveFlag(true);
				zoneEntity.setLastUpdatedDate(new Date());
				zoneRepository.save(zoneEntity);
				response.setMessage(CarSystemConstants.ZONE_ACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(CarSystemConstants.ZONE_NOT_FOUND);
		}

		return response;
	}

	@Override
	public CommonResponse deactivateZone(String id) {
		CommonResponse response = new CommonResponse();

		Optional<Zone> zoneOptional = zoneRepository.findById(Long.parseLong(id));
		if (zoneOptional.isPresent()) {
			Zone zoneEntity = zoneOptional.get();
			if (!zoneEntity.getActiveFlag()) {
				throw new InvalidActiveStatusException(CarSystemConstants.ZONE_DEACTIVE_STATUS);
			} else {
				zoneEntity.setActiveFlag(false);
				zoneEntity.setLastUpdatedDate(new Date());
				zoneRepository.save(zoneEntity);
				response.setMessage(CarSystemConstants.ZONE_DEACTIVATED_SUCCESS);
			}
		} else {
			throw new ResourceNotFoundException(CarSystemConstants.ZONE_NOT_FOUND);
		}

		return response;
	}

	@Override
	public CommonResponse updateZone(ZoneCreateRequest updateRequest, String id) {
		CommonResponse response = new CommonResponse();

		Optional<Zone> zoneOptional = zoneRepository.findById(Long.parseLong(id));
		if (zoneOptional.isPresent()) {
			Zone zoneEntity = zoneOptional.get();
			mapRequestToEntity(zoneEntity, updateRequest);
			zoneEntity.setLastUpdatedDate(new Date());
			zoneRepository.save(zoneEntity);
			response.setMessage(CarSystemConstants.ZONE_UPDATE_SUCCESS);

		} else {
			throw new ResourceNotFoundException(CarSystemConstants.ZONE_NOT_FOUND);
		}

		return response;
	}

}
