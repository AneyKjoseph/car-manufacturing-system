package com.nissan.car.manufacturing.system.service;

import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.response.CommonResponse;

/**
 * 
 * @author Aney K Joseph
 *
 */

public interface ZoneService {

	CommonResponse createZone(ZoneCreateRequest createRequest);

	CommonResponse activateZone(String id);

	CommonResponse deactivateZone(String id);

	CommonResponse updateZone(ZoneCreateRequest updateRequest, String id);

}
