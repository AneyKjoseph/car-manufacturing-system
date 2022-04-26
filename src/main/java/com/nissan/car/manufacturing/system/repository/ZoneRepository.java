package com.nissan.car.manufacturing.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nissan.car.manufacturing.system.model.Zone;

/**
 * 
 * @author Aney K Joseph
 *
 */
@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

}
