package com.nissan.car.manufacturing.system.serviceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	public void testGetAllNotFound() {
		Map<Long, Map<Long, List<Long>>> maps = new HashMap<>();

		List<Plant> plants = new ArrayList<>();

		Mockito.when(repository.findAll()).thenReturn(plants);
		Map<Long, Map<Long, List<Long>>> all = service.getAll();
		Assertions.assertEquals(maps, all);
	}

	@Test
	public void testGetAll() {
		Map<Long, Map<Long, List<Long>>> maps = new HashMap<>();
		List<Long> list = new ArrayList<Long>();
		Map<Long, List<Long>> map = new HashMap<>();
		List<Plant> plants = new ArrayList<>();
		Plant plant = createPlant();
		plants.add(plant);

		list.add(plant.getGroups().get(0).getZones().get(0).getZoneCode());
		map.put(plant.getGroups().get(0).getGroupCode(), list);
		maps.put(plant.getPlantCode(), map);

		Mockito.when(repository.findAll()).thenReturn(plants);
		Map<Long, Map<Long, List<Long>>> all = service.getAll();
		Assertions.assertEquals(maps, all);
	}

	private Plant createPlant() {
		Plant plant = new Plant();
		plant.setPlantCode(1L);
		plant.setPlantName("TestPlant");
		List<Group> groups = createGroup(plant);
		plant.setGroups(groups);
		return plant;
	}

	private List<Group> createGroup(Plant plant) {
		List<Group> groups = new ArrayList<Group>();
		Group group = new Group();
		group.setGroupCode(1L);
		group.setGroupName("TestGroup");
		group.setPlant(plant);
		List<Zone> zones = createZone(plant, group);
		groups.add(group);
		group.setZones(zones);
		return groups;
	}

	private List<Zone> createZone(Plant plant, Group group) {
		List<Zone> zones = new ArrayList<Zone>();
		Zone zone = new Zone();
		zone.setZoneCode(1L);
		zone.setZoneName("TestZone");
		zone.setGroup(group);
		zone.setPlant(plant);
		zones.add(zone);
		return zones;
	}

	private PlantCreateRequest buildCreateRequest() {
		return new PlantCreateRequest("PL1", "TVM", "IND", "MAL");
	}

}
