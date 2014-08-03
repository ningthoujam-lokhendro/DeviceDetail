package com.ningzeta.devicedetail.dao;

import java.util.List;

import javax.sql.DataSource;

import com.ningzeta.devicedetail.model.dto.DeviceDTO;

public interface DeviceDAO {
	
	/**
	 * This is the method to be use to initialize database
	 * resources.
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) ;
	
	/**
	 * Get Device details for a given MAC.
	 * @param mac.
	 * @return Device.
	 */
	public DeviceDTO getDeviceByMAC(String mac);
	
	/**
	 * Get list of Devices for a given Manufacturer
	 * @param mac
	 * @return List of Devices.
	 */
	public List<DeviceDTO> getDeviceByManufacturer(String manufacturer);
}
