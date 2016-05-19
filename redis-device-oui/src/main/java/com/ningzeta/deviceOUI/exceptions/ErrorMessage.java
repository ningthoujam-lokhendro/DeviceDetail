package com.ningzeta.deviceOUI.exceptions;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.http.HttpStatus;

/**
 * Error Message Class to define the Error properties.
 *
 * @author Ningthoujam Lokhendro
 * @since 5/19/2016
 */
public class ErrorMessage {
	String timestamp;
	private int status;
	private String error;
	private String exception;
	private String message;
	private String path;

	public ErrorMessage(HttpStatus error, String exception, String message, String path) {
		this.timestamp = DateTime.now().toString(DateTimeFormat.forPattern("EEE MMM dd HH:mm:ss yyyy"));
		this.status = error.value();
		this.error = error.getReasonPhrase();
		this.exception = exception;
		this.message = message;
		this.path = path;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
