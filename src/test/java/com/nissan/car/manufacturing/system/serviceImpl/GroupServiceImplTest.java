package com.nissan.car.manufacturing.system.serviceImpl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nissan.car.manufacturing.system.entity.Group;
import com.nissan.car.manufacturing.system.entity.Plant;
import com.nissan.car.manufacturing.system.error.exceptions.InvalidActiveStatusException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotCreatedException;
import com.nissan.car.manufacturing.system.error.exceptions.ResourceNotFoundException;

import com.nissan.car.manufacturing.system.repository.GroupRepository;
import com.nissan.car.manufacturing.system.request.GroupCreateRequest;
import com.nissan.car.manufacturing.system.utils.CarSystemConstants;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceImplTest {

	@Autowired
	private GroupServiceImpl groupServiceImpl;

	@MockBean
	private GroupRepository groupRepository;

	@Test
	public void creatGroupTest() {

		Plant plant = new Plant();
		plant.setPlantCode(1l);

		Group group = new Group();
		group.setGroupName("test");
		group.setActiveFlag(true);
		group.setPlant(plant);

		GroupCreateRequest groupCreateRequest = createRequest();

		when(groupRepository.save(Mockito.any(Group.class))).thenReturn(group);
		assertEquals(CarSystemConstants.GROUP_CREATE, groupServiceImpl.createGroup(groupCreateRequest).getMessage());
	}

	@Test
	public void creatGroupException() {

		GroupCreateRequest groupCreateRequest = new GroupCreateRequest();
		when(groupRepository.save(Mockito.any(Group.class))).thenThrow(ConstraintViolationException.class);
		assertThatThrownBy(() -> groupServiceImpl.createGroup(groupCreateRequest))
				.isInstanceOf(ResourceNotCreatedException.class);

	}

	@Test
	public void creatGroupInvalidActiveStatus() {

		GroupCreateRequest groupCreateRequest = new GroupCreateRequest();
		groupCreateRequest.setGroupName("test");
		groupCreateRequest.setPlantCode(1l);

		when(groupRepository.save(Mockito.any(Group.class))).thenThrow(ConstraintViolationException.class);
		assertThatThrownBy(() -> groupServiceImpl.createGroup(groupCreateRequest))
				.isInstanceOf(ResourceNotCreatedException.class);

	}

	@Test
	public void creatGroupEmptyPlanCode() {

		GroupCreateRequest groupCreateRequest = new GroupCreateRequest();
		groupCreateRequest.setGroupName("");
		groupCreateRequest.setActiveFlag(false);
		groupCreateRequest.setPlantCode(null);

		when(groupRepository.save(Mockito.any(Group.class))).thenThrow(ConstraintViolationException.class);
		assertThatThrownBy(() -> groupServiceImpl.createGroup(groupCreateRequest))
				.isInstanceOf(ResourceNotCreatedException.class);

	}

	@Test
	public void creatGroupEmptyGroupName() {

		GroupCreateRequest groupCreateRequest = new GroupCreateRequest();
		groupCreateRequest.setGroupName(" ");
		groupCreateRequest.setActiveFlag(true);
		groupCreateRequest.setPlantCode(1l);

		when(groupRepository.save(Mockito.any(Group.class))).thenThrow(ConstraintViolationException.class);
		assertThatThrownBy(() -> groupServiceImpl.createGroup(groupCreateRequest))
				.isInstanceOf(ResourceNotCreatedException.class);

	}

	@Test
	public void activateGroupTest() {

		Plant plant = new Plant();
		plant.setPlantCode(1l);

		Group group = new Group();
		group.setGroupName("test");
		group.setGroupCode(1l);
		group.setActiveFlag(false);
		group.setPlant(plant);

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertEquals(CarSystemConstants.GROUP_ACTIVATE,
				groupServiceImpl.activateGroup(group.getGroupCode()).getMessage());

	}

	@Test
	public void activateGroupNotFound() {

		Group group = new Group();

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.empty());
		assertThatThrownBy(() -> groupServiceImpl.activateGroup(group.getGroupCode()))
				.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void activateGroupInvalidStatus() {

		Plant plant = new Plant();
		plant.setPlantCode(1l);

		Group group = new Group();
		group.setGroupName("test");
		group.setGroupCode(1l);
		group.setActiveFlag(true);
		group.setPlant(plant);

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertThatThrownBy(() -> groupServiceImpl.activateGroup(group.getGroupCode()))
				.isInstanceOf(InvalidActiveStatusException.class);
	}

	@Test
	public void deactivateGroupTest() {

		Plant plant = new Plant();
		plant.setPlantCode(1l);

		Group group = new Group();
		group.setGroupName("test");
		group.setGroupCode(1l);
		group.setActiveFlag(true);
		group.setPlant(plant);

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertEquals(CarSystemConstants.GROUP_DEACTIVATE,
				groupServiceImpl.deactivateGroup(group.getGroupCode()).getMessage());

	}

	@Test
	public void deactivateGroupNotFound() {

		Group group = new Group();

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.empty());
		assertThatThrownBy(() -> groupServiceImpl.deactivateGroup(group.getGroupCode()))
				.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void deactivateGroupInvalidStatus() {

		Plant plant = new Plant();
		plant.setPlantCode(1l);

		Group group = new Group();
		group.setGroupName("test");
		group.setGroupCode(1l);
		group.setActiveFlag(false);
		group.setPlant(plant);

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertThatThrownBy(() -> groupServiceImpl.deactivateGroup(group.getGroupCode()))
				.isInstanceOf(InvalidActiveStatusException.class);
	}

	@Test
	public void editGroupTest() {

		Plant plant = new Plant();
		plant.setPlantCode(1l);

		Group group = new Group();
		group.setGroupName("test");
		group.setGroupCode(1l);
		group.setActiveFlag(true);
		group.setPlant(plant);

		GroupCreateRequest groupCreateRequest = createRequest();

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertEquals(CarSystemConstants.GROUP_EDIT, groupServiceImpl.editGroup(groupCreateRequest, "1").getMessage());
	}

	@Test
	public void deactivateGroupNullStatus() {

		Plant plant = new Plant();
		plant.setPlantCode(1l);

		Group group = new Group();
		group.setGroupName("test");
		group.setGroupCode(1l);
		group.setActiveFlag(null);
		group.setPlant(plant);

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertThatThrownBy(() -> groupServiceImpl.deactivateGroup(group.getGroupCode()))
				.isInstanceOf(InvalidActiveStatusException.class);
	}

	@Test
	public void editGroupNotFound() {

		Group group = new Group();

		GroupCreateRequest groupUpdateRequest = createRequest();

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.empty());
		assertThatThrownBy(() -> groupServiceImpl.editGroup(groupUpdateRequest, "1"))
				.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void editGroupBlankGroupName() {

		Group group = new Group();
		group.setGroupCode(1l);

		GroupCreateRequest groupUpdateRequest = new GroupCreateRequest();
		groupUpdateRequest.setActiveFlag(true);
		groupUpdateRequest.setGroupName(" ");
		groupUpdateRequest.setPlantCode(1l);

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertThatThrownBy(() -> groupServiceImpl.editGroup(groupUpdateRequest, "1"))
				.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void editGroupEmptyGroupName() {

		Group group = new Group();
		group.setGroupCode(1l);

		GroupCreateRequest groupUpdateRequest = new GroupCreateRequest();
		groupUpdateRequest.setActiveFlag(true);
		groupUpdateRequest.setPlantCode(1l);
		groupUpdateRequest.setGroupName("");

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		assertThatThrownBy(() -> groupServiceImpl.editGroup(groupUpdateRequest, "1"))
				.isInstanceOf(ResourceNotFoundException.class);
	}

	@Test
	public void editGroupException() {

		Group group = new Group();
		group.setGroupCode(1l);

		GroupCreateRequest groupUpdateRequest = createRequest();

		when(groupRepository.findById(group.getGroupCode())).thenReturn(Optional.of(group));
		when(groupRepository.save(Mockito.any(Group.class))).thenThrow(ConstraintViolationException.class);
		assertThatThrownBy(() -> groupServiceImpl.editGroup(groupUpdateRequest, "1"))
				.isInstanceOf(ResourceNotCreatedException.class);

	}

	public GroupCreateRequest createRequest() {
		GroupCreateRequest groupCreateRequest = new GroupCreateRequest();
		groupCreateRequest.setActiveFlag(true);
		groupCreateRequest.setGroupName("test");
		groupCreateRequest.setPlantCode(1l);
		return groupCreateRequest;
	}

}
