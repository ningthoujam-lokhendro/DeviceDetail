package com.ningzeta.devicedetail.dao.impl;

import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.ningzeta.devicedetail.dao.DeviceDAO;
import com.ningzeta.devicedetail.model.DeviceMapper;
import com.ningzeta.devicedetail.model.dto.DeviceDTO;

public class DeviceDAOImpl implements DeviceDAO{
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObj;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObj = new JdbcTemplate(dataSource);
	}

	@Override
	public DeviceDTO getDeviceByMAC(String mac) {
		String SQL = "select * from DeviceDetails where mac = ?";
		DeviceDTO device = jdbcTemplateObj.queryForObject(SQL, 
													   new Object[]{mac},
													   new DeviceMapper()
													  );
		return device;
	}

	@Override
	public List<DeviceDTO> getDeviceByManufacturer(String manufacturer) {
		String SQL = "select * from DeviceDetails where manufacturer = ?";
		List<DeviceDTO> devices = jdbcTemplateObj.query(SQL,
													new Object[]{manufacturer},
													new DeviceMapper()
													);
		return devices;
	}

}
