package com.ningzeta.deviceOUI.controller;

import com.ningzeta.deviceOUI.exceptions.ErrorMessage;
import com.ningzeta.deviceOUI.exceptions.InvalidOUIException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller Advice for handling Errors and Exceptions.
 *
 * @author Ningthoujam Lokhendro
 * @since 5/19/2016
 */

@ControllerAdvice
public class ExceptionProcessor {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({InvalidOUIException.class})
	@ResponseBody
	ErrorMessage handleBadRequest(Exception ex, HttpServletRequest request) {
		logException(ex);
		return new ErrorMessage(HttpStatus.BAD_REQUEST, ex.getClass().getName(),ex.getMessage(),
				request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString());
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	@ExceptionHandler(Exception.class)
	ErrorMessage handleException(Exception ex, HttpServletRequest request) {
		logException(ex);
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getClass().getName(),
				ex.getMessage(),
				request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString());
	}

	private void logException(final Exception exception) {
		exception.printStackTrace();
	}

}
