package com.ningzeta.deviceOUI.controller;

import com.ningzeta.deviceOUI.domains.OUI;
import com.ningzeta.deviceOUI.exceptions.InvalidOUIException;
import com.ningzeta.deviceOUI.repository.RedisRepository;
import com.ningzeta.deviceOUI.services.DeviceServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest Controller
 *
 * @author Ningthoujam Lokhendro
 * @since 5/18/2016
 */

@RestController
@RequestMapping(value = "/api")
public class DeviceController {

	@Autowired
	private DeviceServices deviceServices;

	@ApiOperation("Get the Device using OUI")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Bad Request. Device is invalid"),
			@ApiResponse(code = 404, message = "Device not found"),
			@ApiResponse(code = 200, message = "Success", response = OUI.class)
	})
	@RequestMapping(value = "/devices/oui/{oui}",
					method = RequestMethod.GET,
					produces = "application/json")
	public OUI getDeviceByOUI(@PathVariable("oui") String oui) {

		log.debug("**** Recieved getDeviceByOUI request ****");
		if (!validateOUI(oui))
			throw new InvalidOUIException(oui);

		return this.deviceServices.getDeviceByOUI(oui);
	}

	@ApiOperation("Get the Device using Vendor")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Bad Request. Device is invalid"),
			@ApiResponse(code = 404, message = "Device not found"),
			@ApiResponse(code = 200, message = "Success", response = OUI.class, responseContainer = "List")
	})
	@RequestMapping(value = "/devices/vendor/{vendor}",
			method = RequestMethod.GET,
			produces = "application/json")
	public Object getDeviceByVendor(@PathVariable("vendor") String vendor) {
		log.debug("**** Recieved getDeviceByVendor request ****");
		return this.deviceServices.getDeviceByVendor(vendor);
	}

	@ApiOperation("Get OUI data from IEEE and store to Redis")
	@RequestMapping(value = "/generate",
			method = RequestMethod.GET,
			produces = "application/json")
	public Object[] generateOUI() {
		log.debug("**** Recieved generateOUI request ****");
		Object[] obj = this.deviceServices.processIEEEOUI();
		return obj;
	}

	/**
	 * Validate OUI is not null and valid OUI.
	 * @param oui
	 * @return
	 */
	private Boolean validateOUI(String oui) {
		String ouiRegex = "^([0-9A-Fa-f]{2}[:-]){2}([0-9A-Fa-f]{2})$";
		return (oui != null) && (oui.matches(ouiRegex));
	}

	private static final Logger log = LoggerFactory.getLogger(DeviceController.class);

}
