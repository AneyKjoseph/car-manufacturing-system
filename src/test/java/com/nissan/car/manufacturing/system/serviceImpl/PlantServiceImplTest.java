package com.nissan.car.manufacturing.system.serviceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
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

import com.nissan.car.manufacturing.system.entity.Group;
import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.entity.Zone;
import com.nissan.car.manufacturing.system.error.exceptions.InvalidActiveStatusException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotCreatedException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotFoundException;
import com.nissan.car.manufacturing.system.repository.PlantRepository;
import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.response.PlantDetailsResponse;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PlantServiceImplTest {

	@InjectMocks
	private PlantServiceImpl service;
	@Mock
	private PlantRepository repository;

	@Test
	public void testCreatePlant() {
		PlantCreateRequest request = buildCreateRequest();
		request.setActiveFlag(true);
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		Mockito.when(repository.save(Mockito.any(Plant.class))).thenReturn(plantEntity);

		CommonResponse response = service.createPlant(request);
		Assertions.assertNotNull(response);
		Assertions.assertEquals("New plant created successfully", response.getMessage());

	}

	@Test
	public void testCreatePlantEmptyRequest() {
		PlantCreateRequest request = new PlantCreateRequest();
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		Mockito.when(repository.save(Mockito.any(Plant.class))).thenThrow(ConstraintViolationException.class);

		assertThatThrownBy(() -> service.createPlant(request)).isInstanceOf(ResourceNotCreatedException.class);

	}

	@Test
	public void testCreatePlantNotCreated() {
		PlantCreateRequest request = buildCreateRequest();
		Plant plantEntity = new Plant();
		Mockito.when(repository.save(Mockito.any(Plant.class))).thenReturn(plantEntity);

		CommonResponse response = service.createPlant(request);
		Assertions.assertNotNull(response);
		Assertions.assertNull(response.getMessage());
	}

	@Test
	public void testActivatePlant() {
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		plantEntity.setActiveFlag(false);
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.of(plantEntity));

		CommonResponse response = service.activatePlant("1");
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Plant activated successfully", response.getMessage());

	}

	@Test
	public void testActivatePlantNotFound() {
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		plantEntity.setActiveFlag(false);
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.activatePlant("1")).isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void testActivatePlantInvalidStatus() {
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		plantEntity.setActiveFlag(true);
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.of(plantEntity));

		assertThatThrownBy(() -> service.activatePlant("1")).isInstanceOf(InvalidActiveStatusException.class);
	}

	@Test
	public void testDeActivatePlant() {
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		plantEntity.setActiveFlag(true);
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.of(plantEntity));

		CommonResponse response = service.deactivatePlant("1");
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Plant deactivated successfully", response.getMessage());

	}

	@Test
	public void testDeActivatePlantNotFound() {
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		plantEntity.setActiveFlag(false);
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.deactivatePlant("1")).isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void testDeActivatePlantInvalidStatus() {
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		plantEntity.setActiveFlag(false);
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.of(plantEntity));

		assertThatThrownBy(() -> service.deactivatePlant("1")).isInstanceOf(InvalidActiveStatusException.class);
	}

	@Test
	public void testUpdatePlant() {
		PlantCreateRequest request = buildCreateRequest();
		request.setActiveFlag(true);
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.of(plantEntity));

		CommonResponse response = service.updatePlant(request, "1");
		Assertions.assertNotNull(response);
		Assertions.assertEquals("Requested Plant updated successfully", response.getMessage());

	}

	@Test
	public void testUpdatePlantNotFound() {
		PlantCreateRequest request = buildCreateRequest();
		request.setActiveFlag(true);
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.updatePlant(request, "1")).isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	public void testGetPlantDetails() {
		PlantCreateRequest request = buildCreateRequest();
		request.setActiveFlag(true);
		Plant plantEntity = new Plant();
		Group group = new Group();
		Zone zone = new Zone();
		List<Zone> zoneList = new ArrayList<Zone>();
		zoneList.add(zone);
		group.setZones(zoneList);
		List<Group> groupList = new ArrayList<Group>();
		groupList.add(group);
		plantEntity.setGroups(groupList);
		plantEntity.setPlantCode(Long.parseLong("1"));
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.of(plantEntity));

		PlantDetailsResponse plantResponse = service.getPlantDetails("1");
		Assertions.assertNotNull(plantResponse);

	}

	@Test
	public void testGetPlantNotFound() {
		PlantCreateRequest request = buildCreateRequest();
		request.setActiveFlag(true);
		Plant plantEntity = new Plant();
		plantEntity.setPlantCode(Long.parseLong("1"));
		Mockito.when(repository.findById(plantEntity.getPlantCode())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> service.getPlantDetails("1")).isInstanceOf(ResourceNotFoundException.class);

	}

	@Test
	public void getAllDetails() {

		List<Plant> plants = new ArrayList<Plant>();
		Plant plantEntity = new Plant();
		Group group = new Group();
		Zone zone = new Zone();
		List<Zone> zoneList = new ArrayList<Zone>();
		zoneList.add(zone);
		group.setZones(zoneList);
		List<Group> groupList = new ArrayList<Group>();
		groupList.add(group);
		plantEntity.setGroups(groupList);
		plantEntity.setPlantCode(Long.parseLong("1"));
		Plant plantEntity2 = new Plant();
		plantEntity2.setGroups(groupList);
		plantEntity2.setPlantCode(Long.parseLong("1"));
		plants.add(plantEntity);
		plants.add(plantEntity2);

		Mockito.when(repository.findAll()).thenReturn(plants);

		assertEquals(plants, service.getAllDetails());

	}

	@Test
	public void getAllDetailsPlantNotFound() {

		List<Plant> plants = new ArrayList<Plant>();

		Mockito.when(repository.findAll()).thenReturn(plants);

		assertThatThrownBy(() -> service.getAllDetails()).isInstanceOf(ResourceNotFoundException.class);

	}

	private PlantCreateRequest buildCreateRequest() {
		return new PlantCreateRequest("PL1", "TVM", "IND", "MAL");
	}

}
