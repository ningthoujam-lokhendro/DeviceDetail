package com.ningzeta.devicedetail.model.dto;
/**
 * Define the Device DTO.
 * @author nlokhend
 *
 */
public class DeviceDTO {
	
	private String oui;
	private String manufacturer;
	
	public String getOui() {
		return oui;
	}
	
	public void setOui(String oui) {
		this.oui = oui;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
}
