package com.nissan.car.manufacturing.system.service;

import com.nissan.car.manufacturing.system.request.ZoneCreateRequest;
import com.nissan.car.manufacturing.system.response.Response;

/**
 * 
 * @author Aney K Joseph
 *
 */

public interface ZoneService {

	Response createZone(ZoneCreateRequest createRequest);

	Response activateZone(String id);

	Response deactivateZone(String id);

	Response updateZone(ZoneCreateRequest updateRequest, String id);

}
