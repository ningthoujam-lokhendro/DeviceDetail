package com.ningzeta.deviceOUI.exceptions;

/**
 * InvalidOUIException for the holding Invalid OUI.
 * @author Ningthoujam Lokhendro
 * @since 5/19/2016
 */
public class InvalidOUIException extends RuntimeException {

	String oui;

	public String getOui() {
		return oui;
	}

	public void setOui(String oui) {
		this.oui = oui;
	}

	@Override
	public String getMessage() {
		return "OUI " + this.oui + " id invalid";
	}

	public InvalidOUIException(String oui) {
		this.oui = oui;
	}
}
