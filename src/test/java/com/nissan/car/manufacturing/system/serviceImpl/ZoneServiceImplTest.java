package com.nissan.car.manufacturing.system.serviceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.entity.Zone;
import com.nissan.car.manufacturing.system.error.exceptions.InvalidActiveStatusException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotCreatedException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotFoundException;
import com.nissan.car.manufacturing.system.repository.ZoneRepository;
import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ZoneServiceImplTest {
	@InjectMocks
	private ZoneServiceImpl service;
	@Mock
	private ZoneRepository repository;
	
	@Test
	public void testCreateZone() {
		ZoneCreateRequest request = buildCreateRequest();
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		Mockito.when(repository.save(Mockito.any(Zone.class))).thenReturn(zoneEntity);

		CommonResponse response = service.createZone(request);
		Assertions.assertNotNull(response);
		Assertions.assertEquals("New Zone created successfully", response.getMessage());

	}
	@Test
	public void testCreateZoneEmptyRequest() {
		ZoneCreateRequest request = new ZoneCreateRequest();
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		Mockito.when(repository.save(Mockito.any(Zone.class))).thenThrow(ConstraintViolationException.class);

		assertThatThrownBy(() -> service.createZone(request)).isInstanceOf(ResourceNotCreatedException.class);

	}
	@Test
	public void testCreateZoneThrowsException() {
		ZoneCreateRequest request = buildCreateRequest();
		Mockito.when(repository.save(Mockito.any(Zone.class))).thenThrow(ConstraintViolationException.class);

		assertThatThrownBy(() -> service.createZone(request)).isInstanceOf(ResourceNotCreatedException.class);

	}
	
	@Test
	public void testCreateZoneNotCreated() {
		ZoneCreateRequest request = buildCreateRequest();
		Zone zoneEntity = new Zone();
		Mockito.when(repository.save(Mockito.any(Zone.class))).thenReturn(zoneEntity);

		CommonResponse response = service.createZone(request);
		Assertions.assertNotNull(response);
		Assertions.assertNull(response.getMessage());
	}
	
	@Test
	public void testActivateZone() {
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		zoneEntity.setActiveFlag(false);
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.of(zoneEntity));

		CommonResponse response = service.activateZone("1");
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Zone activated successfully", response.getMessage());

	}
	@Test
	public void testActivateZoneNotFound() {
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		zoneEntity.setActiveFlag(false);
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.activateZone("1")).isInstanceOf(ResourceNotFoundException.class);
	}
	@Test
	public void testActivateZoneInvalidStatus() {
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		zoneEntity.setActiveFlag(true);
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.of(zoneEntity));

		assertThatThrownBy(() -> service.activateZone("1")).isInstanceOf(InvalidActiveStatusException.class);
	}
	
	@Test
	public void testDeActivateZone() {
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		zoneEntity.setActiveFlag(true);
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.of(zoneEntity));

		CommonResponse response = service.deactivateZone("1");
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Zone deactivated successfully", response.getMessage());

	}
	@Test
	public void testDeActivateZoneNotFound() {
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		zoneEntity.setActiveFlag(false);
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.deactivateZone("1")).isInstanceOf(ResourceNotFoundException.class);
	}
	@Test
	public void testDeActivateZoneInvalidStatus() {
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		zoneEntity.setActiveFlag(false);
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.of(zoneEntity));

		assertThatThrownBy(() -> service.deactivateZone("1")).isInstanceOf(InvalidActiveStatusException.class);
	}
	
	@Test
	public void testUpdateZone() {
		ZoneCreateRequest request = buildCreateRequest();
		request.setActiveFlag(true);
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.of(zoneEntity));

		CommonResponse response = service.updateZone(request, "1");
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Requested Zone updated successfully", response.getMessage());

	}
	
	@Test
	public void testUpdateZoneNotFound() {
		ZoneCreateRequest request = buildCreateRequest();
		Zone zoneEntity = new Zone();
		zoneEntity.setZoneCode(Long.parseLong("1"));
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.updateZone(request, "1")).isInstanceOf(ResourceNotFoundException.class);

	}
	
	@Test
	public void testUpdateZoneInvalidInput() {
		ZoneCreateRequest request = new ZoneCreateRequest();
		Zone zoneEntity = new Zone();
		Boolean activeFlag = null;
		request.setActiveFlag(activeFlag);
		zoneEntity.setZoneCode(Long.parseLong("1"));
		Mockito.when(repository.findById(zoneEntity.getZoneCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.updateZone(request, "1")).isInstanceOf(ResourceNotFoundException.class);

	}

	private ZoneCreateRequest buildCreateRequest() {
		long id = 1;
		return new ZoneCreateRequest("ZN",id,id,true);
	}

}
