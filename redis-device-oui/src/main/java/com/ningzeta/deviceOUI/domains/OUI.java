package com.ningzeta.deviceOUI.domains;

/**
 * OUI Class that describe the vendor and the OUI of the device.
 *
 * @author Ningthoujam Lokhendro
 * @since 5/18/2016
 */
public class OUI {

	String oui;
	String vendor;

	public String getOui() {
		return oui;
	}

	public void setOui(String oui) {
		this.oui = oui;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public OUI(String oui, String vendor) {
		this.oui = oui;
		this.vendor = vendor;
	}

	public OUI(){}
}
