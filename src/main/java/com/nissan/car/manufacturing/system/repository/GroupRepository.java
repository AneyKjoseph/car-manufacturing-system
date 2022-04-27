package com.nissan.car.manufacturing.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nissan.car.manufacturing.system.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

}
