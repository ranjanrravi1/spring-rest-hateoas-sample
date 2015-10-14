/**
 * 
 */
package com.project.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 492086
 *
 */
@ControllerAdvice
public class DefaultExceptionHandler {

	// Unsupported Media Type
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ClientErrorInformation> handleException(
			HttpServletRequest req, Exception e) {
		ClientErrorInformation error = new ClientErrorInformation(e.toString(),
				req.getRequestURI());
		return new ResponseEntity<ClientErrorInformation>(error,
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ClientErrorInformation> rulesForCustomerNotFound(
			HttpServletRequest req, HttpMediaTypeNotSupportedException e) {
		ClientErrorInformation error = new ClientErrorInformation(e.getMessage(),
				req.getRequestURI());
		return new ResponseEntity<ClientErrorInformation>(error,
				HttpStatus.NOT_FOUND);
	}

}
