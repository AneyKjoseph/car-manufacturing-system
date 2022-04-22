/**
 * 
 */
package com.nissan.car.manufacturing.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nissan.car.manufacturing.system.model.Plant;

/**
 * @author S Sarathkrishna
 *
 */
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

}
