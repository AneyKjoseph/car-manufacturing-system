/**
 * 
 */
package com.nissan.car.manufacturing.system.service;

import com.nissan.car.manufacturing.system.model.Plant;
import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.response.Response;

import java.util.List;
import java.util.Map;

/**
 * @author S Sarathkrishna
 *
 */
public interface PlantService {
	Response createPlant(PlantCreateRequest createRequest);

	Response activatePlant(String id);
	
	Response deactivatePlant(String id);

	Response updatePlant(PlantCreateRequest updateRequest, String id);

	Plant getPlantDetails(String id);

	Map<Long, Map<Long, List<Long>>> getAll();
}
