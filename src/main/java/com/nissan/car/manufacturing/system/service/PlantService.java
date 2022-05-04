/**
 * 
 */
package com.nissan.car.manufacturing.system.service;

import java.util.List;
import java.util.Map;

import com.nissan.car.manufacturing.system.request.PlantCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;
import com.nissan.car.manufacturing.system.response.PlantDetailsResponse;

/**
 * @author S Sarathkrishna
 *
 */
public interface PlantService {
	CommonResponse createPlant(PlantCreateRequest createRequest);

	CommonResponse activatePlant(String id);
	
	CommonResponse deactivatePlant(String id);

	CommonResponse updatePlant(PlantCreateRequest updateRequest, String id);

	PlantDetailsResponse getPlantDetails(String id);

	Map<Long, Map<Long, List<Long>>> getAll();
}
