package com.ningzeta.devicedetail.model.dto;
/**
 * Define the Device DTO.
 * @author nlokhend
 *
 */
public class DeviceDTO {
	
	private String mac;
	private String manufacturer;
	
	public String getMac() {
		return mac;
	}
	
	public void setMac(String mac) {
		this.mac = mac;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
