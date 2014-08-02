package com.ningzeta.devicedetail.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ningzeta.devicedetail.model.dto.DeviceDTO;

public class DeviceMapper implements RowMapper<DeviceDTO>{

	@Override
	public DeviceDTO mapRow(ResultSet resultSet, int arg1) throws SQLException {
		DeviceDTO device = new DeviceDTO();
		device.setOui(resultSet.getString("oui"));
		device.setManufacturer(resultSet.getString("manufacturer"));
		
		return device;
	}
}
