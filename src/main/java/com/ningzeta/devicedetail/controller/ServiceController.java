package com.ningzeta.devicedetail.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ningzeta.devicedetail.dao.impl.DeviceDAOImpl;
import com.ningzeta.devicedetail.model.dto.DeviceDTO;

@RestController
public class ServiceController {

	ApplicationContext appContext = new ClassPathXmlApplicationContext("SpringBeans.xml");
	
	DeviceDAOImpl ddImpl = (DeviceDAOImpl) appContext.getBean("deviceDAO");
	
	@RequestMapping(value = "/REST/getdevicebymac/{mac}", 
					method = RequestMethod.GET)
	public DeviceDTO getDeviceByOUI(@PathVariable String mac){

		DeviceDTO device = ddImpl.getDeviceByMAC(mac);
		return device;
	}
	
	@RequestMapping(value = "/REST/getdevicebymanufacturer/{manufacturer}", 
					method = RequestMethod.GET)
	public List<DeviceDTO> getDeviceByManufacturer(@PathVariable String manufacturer) {
		
		List<DeviceDTO> deviceList = ddImpl.getDeviceByManufacturer(manufacturer);
		return deviceList;
	}

}
